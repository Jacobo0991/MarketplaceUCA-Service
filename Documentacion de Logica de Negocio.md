
# 📂 Documentación de Lógica de Negocio

Esta sección detalla las reglas operativas y las restricciones de integridad de datos implementadas en el backend del sistema **Marketplace UCA**.

##  1️⃣ Diagramas UML

### 1.2. Diagramas de Secuencia:

**Flujo de Registro con OTP**
Representa el flujo técnico de validación de correo institucional y generación de código.

  > 🔗 **[Ver Diagrama de Secuencia en SharePoint](https://ucaedusv-my.sharepoint.com/:i:/g/personal/00060422_uca_edu_sv/IQCIAOvbwqh9QqV8jg6xwREFAaMl26eCuD6WokUAN5nbrGM?e=S1jDqv)**
-----
## 2️⃣ Documentación de Procesos:

### 📜 1. Reglas de Negocio Documentadas 

Lista exhaustiva de las reglas lógicas que gobiernan el comportamiento del sistema.

### 🔐 1.1 Identidad, Seguridad y Acceso

1.  **Restricción de Dominio Institucional:** El sistema es exclusivo para la comunidad universitaria. Se bloquea cualquier intento de registro con un correo que no finalice estrictamente en `@uca.edu.sv`.
2.  **Verificación de Identidad (OTP):**
    * El registro no es inmediato. Requiere una validación de correo mediante un Código de Un Solo Uso (OTP).
    * **Regla de Expiración:** El código OTP generado se almacena en Redis con una vida útil exacta de **8 minutos**. Pasado este tiempo, el código se invalida automáticamente.
3.  **Inmutabilidad del Super Administrador:**
    * El usuario con credenciales `admin@uca.edu.sv` está protegido a nivel de código.
    * **Restricción:** No es posible cambiar su rol, eliminarlo o degradarlo a usuario normal. Cualquier intento lanza la excepción `ImpossibleAction`.
4.  **Gestión de Sesiones (Stateful JWT):**
    * A diferencia de los JWT estándar, el sistema valida la existencia del token en la base de datos antes de autorizar una petición (`TokenUtils.validateToken`). Esto permite revocar accesos (Logout real) eliminando el token de la tabla.
5.  **Unicidad de Credenciales:**
    * No pueden existir dos usuarios con el mismo `username` (email).
    * No pueden existir dos usuarios con el mismo número de teléfono (implícito en modelo de datos).
6.  **Validación de Roles:**
    * Si se intenta reasignar a un usuario un rol que ya posee (ej. asignar ADMIN a quien ya es ADMIN), el sistema detecta la redundancia y detiene el proceso lanzando `RoleAlreadySet`.

### 🛒 1.2 Gestión de Productos y Mercado

7.  **Política de "Moderación por Defecto" (Soft-Create):**
    * Al crear un producto (`createProduct`), el sistema fuerza su estado a `active = false`.
    * **Consecuencia:** El producto queda oculto en los listados públicos hasta que un Administrador apruebe su publicación mediante el endpoint de activación.
8.  **Restricción de Edición de Integridad:**
    * Para evitar fraudes (ej. cambiar un producto barato por uno caro después de publicarlo), la edición de productos es restrictiva.
    * **Regla:** Una vez creado, el vendedor **solo puede modificar el Precio**. El título, descripción, condición e imágenes son inmutables.
9.  **Unicidad de Categorías:**
    * No es posible crear dos categorías con el mismo nombre. El sistema valida la existencia previa antes de la inserción (`CategoryAlreadyExists`).

### ⭐ 1.3 Interacciones Sociales (Reseñas y Comentarios)

10. **Cálculo de Reputación en Tiempo Real:**
    * El "Rating" de un usuario no se edita directamente. Se calcula matemáticamente (promedio) cada vez que se agrega una nueva reseña directa al **perfil del vendedor**. 
11. **Soberanía de Edición (Ownership):**
    * **Reseñas:** Solo el autor original de una reseña tiene permisos para editar su contenido o eliminarla. El sistema verifica que `reviewer.id == sessionUser.id` antes de proceder.
12. **Jerarquía de Comentarios:**
    * Los comentarios soportan anidación. Un comentario puede ser "raíz" (asociado a un producto) o "respuesta" (asociado a un comentario padre).
    * **Regla:** No se puede responder a un comentario que no existe o ha sido eliminado (`CommentNotFound`).

---

### 🛡️ 2. Matriz de Validaciones 

A continuación, se detallan las validaciones exactas (longitudes, formatos, expresiones regulares) que se aplican en cada formulario de entrada (DTOs).

### 👤 Entidad: Usuario (`User`)


| Campo | Restricción Técnica | Detalle de la Regla |
| :--- | :--- | :--- |
| **email** | `@Email(regexp = "^[a-zA-Z0-9._%+-]+@uca\\.edu\\.sv$")` | Debe ser un correo con formato válido y dominio estricto `@uca.edu.sv`. |
| **password** | `@Pattern(regexp = "^[a-zA-Z0-9]{8,}$")` | Mínimo 8 caracteres. Solo admite caracteres alfanuméricos (letras y números). |
| **phoneNumber** | `@Pattern(regexp = "^[267]\\d{7}$")` | Longitud exacta de 8 dígitos. **Debe comenzar obligatoriamente con 2, 6 o 7**. |
| **name** | `@NotBlank`, `@NotNull` | No puede estar vacío ni ser nulo. |
| **faculty** | `@NotBlank` | Debe coincidir con el nombre de una facultad registrada. |
| **otp** | `@NotBlank` | Código de verificación obligatorio. |

### 📦 Entidad: Producto (`Product`)

| Campo | Restricción Técnica | Detalle de la Regla |
| :--- | :--- | :--- |
| **product** (Nombre) | `@NotBlank`, `@NotNull` | Nombre del producto obligatorio. |
| **description** | `@Size(min = 1, max = 300)` | Descripción obligatoria, con un límite máximo de **300 caracteres**. |
| **price** | `@Positive`, `@NotNull` | El precio debe ser numéricamente mayor a 0. |
| **condition** | `@NotBlank` | Estado del ítem (ej. "Nuevo", "Usado") obligatorio. |
| **categoryName** | `@NotBlank` | Debe coincidir con una categoría existente en la base de datos. |
| **images** | `List<MultipartFile>` | Se procesan internamente. Si falla la subida a Cloudinary, lanza error 500. |

### 💬 Entidad: Comentario (`Comments`)

| Campo | Restricción Técnica | Detalle de la Regla |
| :--- | :--- | :--- |
| **comment** | `@Size(min = 1, max = 250)` | El texto del comentario tiene un límite máximo de **250 caracteres**. |
| **productId** | `@NotBlank`, `UUID` | ID del producto obligatorio y debe existir. |
| **commentIdToReply**| `@NotBlank` | (Solo en respuestas) ID del comentario padre obligatorio. |

### ⭐ Entidad: Reseña (`Review`)

| Campo | Restricción Técnica | Detalle de la Regla |
| :--- | :--- | :--- |
| **rating** | `@Min(1)`, `@Max(5)` | Valor entero estrictamente entre 1 y 5. |
| **comment** | `@Size(min = 1, max = 250)` | Texto de la reseña limitado a **250 caracteres**. |
| **sellerUsername** (o `reviewee`) | `@Email`, `@NotBlank` | **Correo/Usuario del vendedor** al que se está calificando. No depende de un producto. |
| **reviewId** | `@NotBlank` | (Solo en updates) ID de la reseña a modificar. |

### 🏫 Entidad: Facultad y Categoría (`Faculty`, `Category`)

| Campo | Restricción Técnica | Detalle de la Regla |
| :--- | :--- | :--- |
| **facultyName** | `@Size(min = 1, max = 100)` | Nombre de la facultad limitado a **100 caracteres**. |
| **name** (Categoría) | `@NotNull`, `@NotEmpty` | Nombre de categoría obligatorio. Único en base de datos. |
| **description** | `@Size(max = 250)` | Descripción de categoría limitada a **250 caracteres**. |

-----

## 3️⃣ Documentación de Dominio

Esta sección establece el vocabulario común, traza los requisitos funcionales y ejemplifica el uso del sistema **Marketplace UCA** en situaciones reales.

### 📖 1. Glosario de Términos

Definición de los conceptos clave que componen el ecosistema de la aplicación.

| Término | Definición en el Contexto del Sistema |
| :--- | :--- |
| **OTP (One-Time Password)** | Código numérico de seguridad temporal generado por el sistema. Es indispensable para validar que el usuario posee el correo institucional. Tiene una vida útil estricta de 8 minutos. |
| **Reviewer (Evaluador)** | Usuario que emite una calificación y/o comentario sobre una transacción. En el modelo de negocio, asume el rol de comprador. |
| **Reviewee (Evaluado)** | Usuario que recibe una calificación. Su reputación (Rating promedio) se ve afectada positiva o negativamente por esta acción. Generalmente es el vendedor. |
| **Soft-Create (Creación Pasiva)** | Estado inicial de un producto (`Inactivo`). El ítem se registra en la base de datos pero permanece oculto al público hasta su moderación. |
| **Stateful JWT** | Token de seguridad que, a diferencia del estándar, mantiene un estado en la base de datos. Esto permite al sistema revocar el acceso (cerrar sesión) forzosamente si es necesario. |
| **Parent Comment (Hilo)** | Comentario principal asociado directamente a un producto. Funciona como el inicio de un hilo de conversación. |
| **Reply (Respuesta)** | Comentario anidado que responde a un "Parent Comment". Permite la interacción bidireccional entre vendedor y comprador. |
| **Super Admin** | Cuenta raíz (`admin@uca.edu.sv`) con privilegios inmutables. El sistema protege esta cuenta contra eliminación o cambios de rol accidentales. |

-----

### 🗺️ 2. User Stories Mapeadas a Funcionalidades

Matriz de trazabilidad que conecta las necesidades del usuario con la implementación técnica existente.

### 👤 Gestión de Identidad (Estudiantes)

| ID | Historia de Usuario | Funcionalidad / Endpoint Implementado |
| :--- | :--- | :--- |
| **US-01** | "Como estudiante (`00060422`), quiero iniciar mi registro validando mi correo UCA." | **Solicitud de OTP** <br> `POST /user/auth/register` |
| **US-02** | "Como estudiante, quiero ingresar el código recibido para activar mi cuenta." | **Verificación y Creación** <br> `POST /user/auth/verify` |
| **US-03** | "Como usuario, quiero cambiar mi contraseña si sospecho que es insegura." | **Reset Password** <br> `PATCH /user/password` |
| **US-04** | "Como usuario, quiero actualizar mi número de contacto para las ventas." | **Update Phone** <br> `PATCH /user/phoneNumber` |

### 🛒 Gestión de Mercado (Vendedores)

| ID | Historia de Usuario | Funcionalidad / Endpoint Implementado |
| :--- | :--- | :--- |
| **US-05** | "Como vendedor, quiero publicar un producto con imágenes y descripción." | **Crear Producto** (Multipart) <br> `POST /product/create` |
| **US-06** | "Como vendedor, quiero ajustar el precio de mi producto si el mercado cambia." | **Actualizar Precio** <br> `PATCH /product/update` |
| **US-07** | "Como vendedor, quiero eliminar un producto que ya vendí por fuera." | **Eliminar Producto** <br> `DELETE /product/delete/{id}` |
| **US-08** | "Como vendedor, quiero ver mis propios productos publicados." | **Mis Productos** <br> `GET /product/my` |

### 🛡️ Moderación (Administradores)

| ID | Historia de Usuario | Funcionalidad / Endpoint Implementado |
| :--- | :--- | :--- |
| **US-09** | "Como Admin, quiero ver qué productos están pendientes de aprobación." | **Listar Inactivos** <br> `GET /admin/product/` |
| **US-10** | "Como Admin, quiero activar un producto para que sea visible a todos." | **Activar Producto** <br> `PATCH /admin/product/activation/{id}` |
| **US-11** | "Como Admin, quiero crear nuevas categorías para organizar el mercado." | **Crear Categoría** <br> `POST /admin/category/create` |

### 🤝 Interacción (Comunidad)

| ID | Historia de Usuario | Funcionalidad / Endpoint Implementado |
| :--- | :--- | :--- |
| **US-12** | "Como comprador, quiero preguntar detalles en una publicación." | **Comentar** <br> `POST /comments/create` |
| **US-13** | "Como comprador, quiero calificar al vendedor para construir confianza." | **Reseñar (Review)** <br> `POST /reviews/create` |
| **US-14** | "Como usuario, quiero guardar productos que me interesan." | **Dar Like** <br> `POST /likes/add` |

-----

### 🧪 3. Casos de Negocio y Escenarios

Ejemplos concretos de flujos de uso real, validados contra la lógica de negocio del backend.

### ✅ Escenario A: El Ciclo de Venta Exitoso

**Contexto:** El estudiante `00060422` vende un libro y el estudiante `00055566` lo compra.

1.  **Publicación:**
      * El usuario `00060422` sube el libro "Cálculo de Stewart".
      * **Sistema:** Registra el producto, asigna estado **Inactivo** y guarda las imágenes en la nube.
2.  **Aprobación:**
      * El Administrador revisa la cola de pendientes y aprueba el libro.
      * **Sistema:** Cambia el estado a **Activo**. El producto aparece en el Feed general y por categoría.
3.  **Interacción:**
      * El usuario `00055566` comenta: "¿Es la 7ma edición?".
      * El vendedor `00060422` responde: "Sí, es la séptima".
4.  **Conclusión:**
      * Tras la compra física, `00055566` ingresa al sistema y califica con **5 Estrellas**.
      * **Sistema:** Recalcula el promedio de `00060422`. Si tenía 0 reseñas, ahora su rating es 5.0.

### ⛔ Escenario B: Intento de Registro Fraudulento

**Contexto:** Un usuario intenta ingresar al sistema sin credenciales universitarias válidas.

1.  **Validación de Dominio:**
      * El usuario intenta registrarse con `estudiante@gmail.com`.
      * **Sistema:** Rechaza la petición inmediatamente. El formato no cumple con la regla `@uca.edu.sv`.
2.  **Validación de OTP:**
      * El usuario intenta con `00099988@uca.edu.sv` (válido) y el sistema envía el código `505050` a ese correo.
      * El usuario malintencionado intenta adivinar el código enviando `123456`.
      * **Sistema:** Compara con Redis. Al no coincidir (Error 403), el usuario no se crea en la base de datos.
