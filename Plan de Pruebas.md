# 📋 Plan de Pruebas - Marketplace UCA

**Versión 1.0** | **Estado: Activo** | **Cobertura: 100%**

---

## 📌 Información General del Plan

### 🎯 Objetivo

Verificar que las nuevas funcionalidades propuestas para Marketplace UCA —incluyendo el sistema de comentarios, reseñas de vendedores, verificación institucional por correo UCA y rediseño visual— cumplan con los requisitos funcionales y no funcionales.
Este plan asegura que las mejoras implementadas sean robustas, seguras, usables y coherentes, garantizando que la plataforma mantenga su exclusividad, seguridad y usabilidad para la comunidad de la Universidad Centroamericana "José Simeón Cañas".

### 📊 Alcance

Este plan cubre pruebas funcionales y no funcionales para las **13 historias de usuario** definidas en el documento, divididas en **6 épicas**:

1. **Implementación de Comentarios**: Desarrollo completo del sistema de comentarios, incluyendo visualización, filtrado, creación, edición y eliminación.

2. **Implementación de Reseñas y Calificación de Vendedores**: Creación del sistema de reseñas y calificaciones de vendedores, así como su correspondiente visualización.

3. **Reconstrucción del Apartado Visual**: Rediseño integral de la interfaz con coherencia visual, accesibilidad y mejora de la experiencia de usuario.

4. **Verificación Institucional por Correo UCA**: Sistema de validación automática para direcciones con el dominio institucional "@uca.edu.sv".

5. **Sistema de Favoritos**: Sistema de gestión y visualización de la sección de elementos marcados como favoritos.

6. **Optimización de Sesión y Navegación**: Mejoras en la persistencia de la sesión, seguridad de navegación y navegación basada en roles.

### 🚀 Estrategia de Pruebas

| Tipo de Prueba | Descripción |
|----------------|-------------|
| **Pruebas Funcionales** | Verificación de criterios de aceptación de cada mejora implementada |
| **Pruebas No Funcionales** | Seguridad, rendimiento, usabilidad y compatibilidad |
| **Niveles de Prueba** | Unitarias, Integración, Sistema y Aceptación de Usuario |
| **Enfoque** | Pruebas iterativas con validación continua del equipo |


---

## 💬 Épica 1: Implementación de Comentarios

### 📋 Historia de Usuario 1: Creación y Gestión de Comentarios

#### ✍️ CP-001: Prueba Funcional - Ciclo Completo de Gestión de Comentarios

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** José Andrés Valdés Jacobo  
**QA:** Graciela María Osegueda Hernández

**Precondiciones:**
- Usuario registrado y autenticado
- Al menos un producto disponible en el catálogo
- Producto con sección de comentarios habilitada.

**📝 Pasos de Ejecución:**

1. Iniciar sesión como usuario registrado
2. Navegar a la página de detalles de un producto
3. Crear un nuevo comentario.
4. Verificar que aparece en la lista ordenado por fecha (primero)
5. Hacer clic en "Editar" del comentario recién creado, modificar el texto.
6. Hacer clic en "Eliminar" del comentario
7. Cerrar sesión e intentar comentar sin autenticación

**✨ Resultado Esperado:**

✓ Los usuarios registrados pueden crear comentarios  
✓ Los comentarios se guardan en la base de datos y se muestran por fecha descendente  
✓ Cada usuario solo puede editar y eliminar sus propios comentarios  
✓ Los cambios de edición se reflejan inmediatamente y persisten  
✓ Solo el autor puede eliminar sus propios comentarios  
✓ La eliminación requiere confirmación y elimina el comentario  
✓ Si no hay sesión, no aparece el campo para comentar


**📋 Criterios de Aceptación Verificados:** AC1 (crear, editar, eliminar), AC2 (solo registrados), AC3 (persistencia BD), AC4 (orden por fecha)

---

### 📋 Historia de Usuario 2: Visualización Completa de Comentarios

#### ✍️ CP-002: Prueba Funcional - Visualización y Carga Dinámica de Comentarios

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Oscar Ernesto Menjívar Ayala  
**QA:** José Andrés Valdés Jacobo

**Precondiciones:**
- Producto con múltiples comentarios 
- Comentarios con texto de diferentes longitudes
- Usuario autenticado

**📝 Pasos de Ejecución:**

1. Acceder a la página de detalles de un producto con comentarios
2. Localizar la sección dedicada de comentarios
3. Verificar que todos los comentarios se muestran completos (sin recortes)
4. Leer comentarios largos para verificar que el texto no está truncado
5. Crear un nuevo comentario y verificar que aparece sin recargar la página
6. Eliminar un comentario y verificar actualización dinámica
7. Verificar que los comentarios están ordenados cronológicamente
8. Inspeccionar que la carga es dinámica sin reload completo

**✨ Resultado Esperado:**

✓ Todos los comentarios se muestran completos y legibles, sin recortes de texto  
✓ Existe una sección dedicada y claramente identificable para comentarios  
✓ Los comentarios están organizados de forma ordenada y accesible  
✓ La visualización es clara con separación entre comentarios  
✓ La creación de nuevos comentarios actualiza la lista dinámicamente  
✓ La eliminación actualiza la interfaz sin recargar toda la página  
✓ No se requiere refresh del navegador para ver cambios  
✓ La experiencia de carga es fluida y rápida

**📋 Criterios de Aceptación Verificados:** AC1 (completos y legibles), AC2 (sección dedicada), AC3 (ordenada y accesible), AC4 (carga dinámica)

---

### 📋 Historia de Usuario 3: Filtrado y Respuesta de Comentarios

#### ✍️ CP-003: Prueba Funcional - Sistema Completo de Respuestas y Filtrado

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Rodrigo Umanzor Velásquez  
**QA:** Graciela María Osegueda Hernández

**Precondiciones:**
- Usuario autenticado
- Producto con comentarios existentes
- Algunos comentarios con respuestas anidadas

**📝 Pasos de Ejecución:**

1. Acceder a un producto que tenga múltiples comentarios
2. Verificar que el selector de filtros esté visible
3. Aplicar los filtros “Más recientes” y “Más relevantes”
4. Hacer clic en “Responder” en un comentario específico
5. Escribir una respuesta y publicarla
6. Responder a esa respuesta (segundo nivel de anidación)
7. Verificar que la jerarquía visual sea clara (indentación, líneas, etc.)
8. Cambiar entre los filtros y verificar que las respuestas se mantengan asociadas a su comentario padre

**✨ Resultado Esperado:**

✓ “Más recientes” ordena por fecha descendente y “Más relevantes” por engagement  
✓ El cambio de filtro es instantáneo y sin recargar la página  
✓ Los usuarios pueden responder y las respuestas se guardan correctamente  
✓ Las respuestas se muestran anidadas con jerarquía visual clara y soportan múltiples niveles  
✓ Al filtrar, las respuestas se mantienen asociadas a su comentario padre  
✓ No hay problemas de rendimiento incluso con respuestas concurrentes

**📋 Criterios de Aceptación Verificados:** AC1 (responder), AC2 (anidación), AC3 (filtrado), AC4 (sin errores)

---

## ⭐ Épica 2: Implementación de Reseñas y Calificación de Vendedores

### 📋 Historia de Usuario 4: Sistema de Calificación de Vendedores

#### ✍️ CP-004: Prueba Funcional - Sistema Completo de Calificación de Vendedores

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** David Ernesto Mejía Oliva  
**QA:** José Andrés Valdés Jacobo

**Precondiciones:**
- Usuario comprador autenticado
- Vendedores con diferentes calificaciones existentes
- Base de datos con sistema de promedios implementado

**📝 Pasos de Ejecución:**

1. Acceder al perfil de un vendedor sin calificaciones previas
2. Registrar una calificación inicial de 5 estrellas  
3. Validar que el promedio se actualiza y muestra de forma inmediata
4. Registrar una segunda calificación (otro usuario) de 3 estrellas  
5. Verificar que el promedio se recalcula correctamente (4.0 estrellas)  
6. Confirmar en la base de datos la persistencia del nuevo promedio
7. Editar la calificación propia de 5 a 4 estrellas
8. Verificar la actualización dinámica del promedio en tiempo real
9. Eliminar una calificación y comprobar el recálculo automático 
10. Validar la correcta integración entre frontend (visualización) y backend (cálculo)

**✨ Resultado Esperado:**

✓ El usuario puede asignar puntuaciones entre 1 y 5 estrellas
✓ El promedio de calificaciones se calcula automáticamente al agregar/editar/eliminar  
✓ La base de datos refleja los cambios en tiempo real.  
✓ El promedio se muestra con precisión (p. ej., 4.5, 3.2)
✓ La integración entre frontend (React) y backend (Spring Boot) es estable y correcta
✓ Los cambios son inmediatos sin necesidad de recargar la página 
✓ Se gestionan correctamente múltiples calificaciones de distintos usuarios
✓ La persistencia en PostgreSQL es consistente y confiable

**📋 Criterios de Aceptación Verificados:** AC1 (puntuación 1-5), AC2 (promedio automático), AC3 (tiempo real), AC4 (integración frontend-backend)

---

### 📋 Historia de Usuario 5: Publicación de Reseñas

#### ✍️ CP-005: Prueba Funcional - Gestión Completa de Reseñas de Vendedores

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** David Ernesto Mejía Oliva  
**QA:** Graciela María Osegueda Hernández

**Precondiciones:**
- Usuario comprador autenticado
- Al menos dos vendedores disponibles
- Vendedor con reseñas existentes

**📝 Pasos de Ejecución:**

1. Acceder al perfil del vendedor y crear una reseña (texto + 5 estrellas)
2. Verificar que la reseña aparece primera y muestra texto, fecha, puntuación y autor
3. Confirmar que el promedio del vendedor se actualiza
4. Editar la reseña propia (cambiar texto y calificación) y verificar la actualización del promedio
5. Intentar editar una reseña ajena (debe fallar)
6. Eliminar la reseña propia, confirmar y verificar que desaparece y el promedio se recalcula
7. Validar en base de datos que todos los cambios se persisten correctamente

**✨ Resultado Esperado:**

✓ El formulario de reseña incluye texto y calificación (1–5 estrellas)  
✓ La reseña se guarda y aparece primero por fecha reciente  
✓ Muestra texto, fecha, puntuación y autor  
✓ El promedio de calificación del vendedor se actualiza automáticamente  
✓ Solo el autor puede editar o eliminar su reseña  
✓ Las ediciones y eliminaciones recalculan el promedio en tiempo real  
✓ Intentos de editar reseñas ajenas devuelven error 403  
✓ La eliminación requiere confirmación  
✓ Todos los datos persisten correctamente en PostgreSQL

**📋 Criterios de Aceptación Verificados:** AC1 (texto, fecha, puntuación), AC2 (editar/eliminar), AC3 (persistencia BD), AC4 (ordenamiento)

---

### 📋 Historia de Usuario 6: Visualización de Reputación de Vendedores

#### 👀 CP-006: Prueba Funcional - Visualización de Reputación y Acceso a Reseñas

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Oscar Ernesto Menjívar Ayala  
**QA:** José Andrés Valdés Jacobo

**Precondiciones:**
- Varios vendedores con diferentes promedios de calificación
- Vendedores con diferentes cantidades de reseñas
- Usuario navegando por la plataforma

**📝 Pasos de Ejecución:**

1. Navegar por el catálogo de productos
2. Visualizar tarjetas de productos en el catálogo
3. Verificar que cada tarjeta muestra el promedio de calificación del vendedor
4. Verificar formato visual (estrellas o número)
5. Hacer clic en el perfil de un vendedor
6. Verificar que el promedio es claramente visible en el perfil
7. Verificar que se muestra la cantidad total de reseñas (ej: "4.5 estrellas - 23 reseñas")
8. Acceder a la sección de reseñas del vendedor
9. Verificar que las reseñas más recientes aparecen primero
10. Comparar con otro vendedor para verificar consistencia de visualización

**✨ Resultado Esperado:**

✓ El perfil del vendedor muestra su promedio de calificación visible y destacado  
✓ Se muestra la cantidad total de reseñas junto al promedio  
✓ El formato es claro (ej: "4.5 ★ - 23 reseñas")  
✓ Las reseñas más recientes aparecen primero cuando se accede al detalle  
✓ La información es accesible desde la vista principal sin necesidad de clicks adicionales  
✓ El promedio se muestra en tarjetas de productos del catálogo  
✓ La visualización es consistente en todas las secciones de la plataforma  
✓ Los usuarios pueden tomar decisiones de compra informadas rápidamente

**📋 Criterios de Aceptación Verificados:** AC1 (promedio visible), AC2 (cantidad de reseñas), AC3 (más recientes primero), AC4 (clara y accesible)

---

## 🎨 Épica 3: Reconstrucción del Apartado Visual

### 📋 Historia de Usuario 7: Diseño de Interfaz Coherente

#### ✍️ CP-007: Prueba Funcional - Coherencia Visual y Responsividad Global

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Gabriela Sofia Quinteros Ramírez  
**QA:** Graciela María Osegueda Hernández

**Precondiciones:**
- Sistema con rediseño completamente implementado
- Acceso a todas las secciones de la plataforma
- Dispositivos o emuladores de diferentes tamaños

**📝 Pasos de Ejecución:**

1. Navegar por las secciones principales (Inicio, Catálogo, Detalle de Producto, Perfil de Vendedor y Mis Favoritos) verificando coherencia en estructura, colores, tipografía, espaciado y estilo de botones
2. Validar con DevTools que la paleta de colores, fuentes y pesos tipográficos sean uniformes
3. Revisar que la tarjeta de producto muestre correctamente imagen, título, precio y vendedor en catálogo y en perfiles
4. Probar el diseño en múltiples resoluciones (desktop, laptop, tablet y móvil) comprobando que no exista scroll horizontal
5. Confirmar el funcionamiento del menú hamburguesa en móvil y que los botones tengan tamaño adecuado para interacción táctil

**✨ Resultado Esperado:**

✓ Consistencia visual en estructura, colores, tipografía y espaciado  
✓ Componentes ordenados y con comportamiento predecible  
✓ Tarjeta de producto funciona en catálogo y perfiles  
✓ Muestra imagen, título, precio y vendedor (con placeholder si falta imagen)   
✓ Diseño responsive sin scroll horizontal en ninguna resolución  
✓ Botones aptos para interacción táctil y textos legibles en móvil 
✓ Menú se adapta a hamburguesa en pantallas pequeñas


**📋 Criterios de Aceptación Verificados:** AC1 (coherencia), AC2 (colores/tipografía), AC3 (disposición ordenada), AC4 (componente de productos)

---

### 📋 Historia de Usuario 8: Funcionalidad de Elementos Visuales

#### ✍️ CP-008: Prueba Funcional - Funcionalidad y Claridad de Todos los Elementos

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Gabriela Sofia Quinteros Ramírez  
**QA:** Graciela María Osegueda Hernández

**Precondiciones:**
- Sistema completamente implementado
- Documentación de elementos eliminados

**📝 Pasos de Ejecución:**

1. Recorrer todas las páginas principales y verificar botones visibles y funcionales
2. Confirmar que el cursor indica elementos clickeables y que botones deshabilitados no responden  
3. Revisar que no existan elementos sin función: botones, decorativos o enlaces vacíos  
4. Verificar que los nombres de secciones del menú sean claros y descriptivos
5. Validar contraste de texto sobre fondos   


**✨ Resultado Esperado:**

✓ Todos los botones y elementos interactivos funcionan y son claramente indicados  
✓ No existen elementos decorativos sin función  
✓ Nombres de secciones descriptivos y comprensibles  
✓ Usuarios nuevos navegan exitosamente 
✓ Elementos importantes visibles, con contraste y tamaños adecuados  
✓ Contraste cumple con lectura fácil y accesible   
✓ Sin errores críticos de accesibilidad

**📋 Criterios de Aceptación Verificados:** AC1 (botones funcionales), AC2 (sin decorativos), AC3 (nombres descriptivos), AC4 (visibilidad y comprensibilidad)

---

## ✅ Épica 4: Verificación Institucional por Correo UCA

### 📋 Historia de Usuario 9: Validación de Correo Institucional

#### ✍️ CP-009: Prueba Funcional - Sistema Completo de Validación Institucional

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Rodrigo Umanzor Velásquez  
**QA:** Graciela María Osegueda Hernández

**Precondiciones:**
- Sistema de registro implementado con validación
- Página de registro accesible
- Herramientas de prueba de API

**📝 Pasos de Ejecución:**

1. **Correo institucional válido:** registrar con `estudiante@uca.edu.sv` y verificar éxito y login
2. **Correos externos:** probar Gmail, Outlook, Yahoo; verificar rechazo con mensaje apropiado
3. **Dominios similares:** probar `usuario@uca.com` y `usuario@uca.edu.com`; deben ser rechazados
4. **Formato incorrecto:** probar sin @, incompleto o con espacios; verificar mensajes de error
5. **Seguridad backend:** enviar registros no válidos vía Postman; verificar rechazo (400) y validación robusta


**✨ Resultado Esperado:**

✓ Solo se permiten correos institucionales @uca.edu.sv; externos y dominios similares son rechazados  
✓ Mensajes de error claros y específicos para correos inválidos  
✓ Validación consistente en frontend y backend  
✓ Registro solo completo con correo válido y exclusivo de la plataforma  
✓ Formatos inválidos muestran errores apropiados  

**📋 Criterios de Aceptación Verificados:** AC1 (validación automática), AC2 (rechazo externos), AC3 (mensaje claro), AC4 (registro solo con válidos), AC5 (exclusividad), AC6 (funciona QA)

---

## ❤️ Épica 5: Sistema de Favoritos

### 📋 Historia de Usuario 10: Gestión de Productos Favoritos

#### ⭐ CP-010: Prueba Funcional - Sistema Completo de Gestión de Favoritos

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Oscar Ernesto Menjívar Ayala  
**QA:** José Andrés Valdés Jacobo

**Precondiciones:**
- Usuario comprador autenticado
- Catálogo con productos disponibles
- Sistema de favoritos implementado en frontend y backend

**📝 Pasos de Ejecución:**

1. Navegar por el catálogo de productos
2. Localizar el ícono de "corazón" en una tarjeta de producto
3. Hacer clic en el ícono para agregar a favoritos
4. Verificar que el ícono cambia de estado (vacío a lleno) inmediatamente
5. Recargar la página y verificar que el estado se mantiene
6. Acceder a la vista de detalles del producto
7. Verificar que el ícono también refleja el estado de favorito
8. Agregar 5 productos más a favoritos desde diferentes vistas
9. Hacer clic nuevamente en un corazón lleno para quitar de favoritos
10. Verificar que el estado cambia inmediatamente (lleno a vacío)
11. Acceder a la base de datos y verificar persistencia correcta
12. Cerrar sesión, iniciar con otro usuario y verificar que favoritos son independientes

**✨ Resultado Esperado:**

✓ El usuario puede agregar productos a favoritos desde tarjeta o vista detalle  
✓ El usuario puede quitar productos de favoritos con un clic  
✓ El ícono de "corazón" cambia de estado inmediatamente (lleno/vacío)  
✓ El cambio visual es instantáneo sin recargar la página  
✓ La selección se guarda en la base de datos correctamente  
✓ Los favoritos persisten al recargar la página  
✓ Los favoritos persisten entre sesiones  
✓ Cada usuario tiene su propia lista independiente de favoritos  

**📋 Criterios de Aceptación Verificados:** AC1 (agregar/quitar desde tarjeta o detalle), AC2 (cambio inmediato de ícono), AC3 (persistencia en BD)

---

### 📋 Historia de Usuario 11: Visualización de Sección de Favoritos

#### 📋 CP-011: Prueba Funcional - Visualización y Gestión desde Sección de Favoritos

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Oscar Ernesto Menjívar Ayala  
**QA:** José Andrés Valdés Jacobo

**Precondiciones:**
- Usuario autenticado con varios productos en favoritos
- Usuario sin productos en favoritos (para probar mensaje vacío)
- Menú de navegación implementado

**📝 Pasos de Ejecución:**

1. **Con favoritos existentes:**
   - Iniciar sesión con usuario que tiene favoritos guardados
   - Localizar "Favoritos" en el menú de navegación
   - Hacer clic para acceder a la sección
   - Verificar que se muestran todos los productos guardados
   - Hacer clic en un producto para ver sus detalles
   - Verificar redirección correcta a página de detalles
   - Regresar y quitar un producto de favoritos desde esta vista

2. **Sin favoritos:**
   - Cerrar sesión y crear usuario nuevo o limpiar favoritos
   - Acceder a la sección "Favoritos"
   - Verificar que se muestra mensaje amigable
   - Verificar que el mensaje es claro (ej: "No tienes favoritos aún")
   - Verificar que hay enlace o botón para ir al catálogo

3. **Accesibilidad:**
   - Verificar que "Favoritos" es visible en el menú principal
   - Verificar que la sección es de fácil acceso 

*✨ Resultado Esperado:**

✓ Existe sección "Favoritos" accesible desde el menú principal  
✓ La sección muestra todos los productos marcados como favoritos  
✓ Si no hay favoritos, se muestra mensaje amigable y claro  
✓ El mensaje vacío incluye orientación (ej: "Explora el catálogo")  
✓ Desde favoritos, el usuario puede acceder directamente al detalle del producto  
✓ El usuario puede gestionar favoritos desde esta vista  
✓ La navegación es intuitiva y el acceso es rápido  
✓ La sección está bien integrada con el resto de la plataforma

**📋 Criterios de Aceptación Verificados:** AC1 (sección en menú), AC2 (mensaje amigable si vacío), AC3 (acceso directo a detalles)

---

## 🔐 Épica 6: Optimización de Sesión y Navegación

### 📋 Historia de Usuario 12: Persistencia y Seguridad de Sesión

#### 🔒 CP-012: Prueba Funcional - Sistema Completo de Gestión de Sesión

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Oscar Ernesto Menjívar Ayala  
**QA:** José Andrés Valdés Jacobo

**Precondiciones:**
- Sistema de autenticación implementado
- Formulario de login con opción "Recuérdame"
- Usuario de prueba registrado

**📝 Pasos de Ejecución:**

1. **Prueba de "Recuérdame"**
    - Iniciar sesión con credenciales válidas marcando "Recuérdame".  
    - Cerrar y reabrir el navegador.  
    - Confirmar que la sesión permanece activa sin volver a autenticar.

2. **Prueba sin "Recuérdame"**
    - Iniciar sesión sin marcar la opción.  
    - Cerrar y reabrir el navegador.  
    - Verificar que se solicita iniciar sesión nuevamente.

3. **Prueba de cierre de sesión**
    - Iniciar sesión y seleccionar “Cerrar sesión”.  
    - Validar la aparición del componente/mensaje de confirmación.  
    - Cancelar para comprobar que la sesión sigue activa.  
    - Repetir el cierre, confirmar y verificar:  
        - Redirección a la página de inicio.  
        - Eliminación de credenciales locales.  
        - Redirección al login al intentar acceder a rutas protegidas.

4. **Prueba de seguridad**
    - Confirmar eliminación de tokens en localStorage/sessionStorage.  
    - Validar que tokens expirados no permiten acceso.  
    - Confirmar que el backend invalida correctamente la sesión.

**✨ Resultado Esperado:**

✓ El login incluye opción visible "Recuérdame"  
✓ Con "Recuérdame" marcado, la sesión persiste tras cerrar el navegador  
✓ Sin "Recuérdame", la sesión termina al cerrar el navegador  
✓ Al presionar "Cerrar sesión" aparece componente de confirmación visual  
✓ El usuario puede cancelar el cierre de sesión  
✓ Al confirmar, se eliminan credenciales locales correctamente  
✓ Al confirmar cierre, se redirige a página de inicio  
✓ No se puede acceder a rutas protegidas después del cierre  
✓ El sistema maneja tokens de forma segura

**📋 Criterios de Aceptación Verificados:** AC1 ("Recuérdame"), AC2 (confirmación de cierre), AC3 (eliminación de credenciales y redirección)

### 📋 Historia de Usuario 13: Navegación Adaptativa por Roles

#### ✍️ CP-013: Prueba Funcional - Sistema Completo de Navegación por Roles y Control de Acceso

**Tipo:** Funcional  
**Prioridad:** 🔴 Alta  
**Encargado:** Oscar Ernesto Menjívar Ayala  
**QA:** Graciela María Osegueda Hernández

**Precondiciones:**
- Sistema con roles implementados (Comprador, Administrador)
- Dos usuarios de prueba con roles diferentes
- Rutas protegidas configuradas

**📝 Pasos de Ejecución:**

1. **Sin autenticación:** solo se muestran Inicio, Catálogo, Iniciar Sesión y Registrarse; rutas admin redirigen al login
2. **Comprador:** navegación muestra solo opciones de usuario; rutas admin y botones no son accesibles; 403 o mensaje de permisos
3. **Administrador:** navegación incluye opciones admin y funciones de comprador; acceso a todas las rutas admin permitido
4. **Seguridad backend:** intentos de acceder a rutas admin con token de comprador devuelven 403; manipulación de token bloqueada
5. **Actualización dinámica:** cierre e inicio de sesión actualizan la barra de navegación inmediatamente sin recarga  
6. **Indicadores de rol:** badges visibles, opciones admin diferenciadas, nivel de acceso comprensible

**✨ Resultado Esperado:**

✓ Sin autenticación, solo se muestran opciones públicas; usuarios no autenticados redirigidos al login  
✓ La navegación se actualiza dinámicamente al iniciar/cerrar sesión sin recargar la página  
✓ Compradores solo ven opciones de usuario; panel admin no visible  
✓ Administradores ven todas las opciones (usuario + admin)  
✓ Intentos de acceso a rutas protegidas sin permisos retornan 403 con mensaje adecuado  
✓ Endpoints de API validan roles en cada petición; no se pueden manipular tokens para elevar privilegios  
✓ El rol del usuario es visualmente identificable mediante badge o etiqueta  
✓ La interfaz refleja correctamente el estado de autenticación en todo momento

**📋 Criterios de Aceptación Verificados:** AC1 (navegación oculta enlaces no permitidos), AC2 (validación de permisos), AC3 (actualización dinámica)

---

## 📊 Matriz de Trazabilidad - Casos Críticos

| Historia de Usuario | Caso de Prueba | Cobertura | Prioridad |
|---------------------|----------------|-----------|-----------|
| HU-1: Creación y Gestión de Comentarios | CP-001 | Ciclo completo CRUD + restricciones | 🔴 Alta |
| HU-2: Visualización Completa de Comentarios | CP-002 | Visualización completa + carga dinámica | 🔴 Alta |
| HU-3: Filtrado y Respuesta de Comentarios | CP-003 | Filtrado + respuestas anidadas | 🔴 Alta |
| HU-4: Sistema de Calificación de Vendedores | CP-004 | Calificación + promedios automáticos | 🔴 Alta |
| HU-5: Publicación de Reseñas | CP-005 | CRUD completo + calificaciones + permisos | 🔴 Alta |
| HU-6: Visualización de Reputación de Vendedores | CP-006 | Visualización de reputación + acceso a reseñas | 🔴 Alta |
| HU-7: Diseño Coherente | CP-007 | Coherencia visual + responsividad global | 🔴 Alta |
| HU-8: Funcionalidad Visual | CP-008 | Funcionalidad + usabilidad + accesibilidad | 🔴 Alta |
| HU-9: Validación Correo Institucional | CP-009 | Validación completa + seguridad | 🔴 Alta |
| HU-10: Gestión de Productos Favoritos | CP-010 | Agregar/quitar favoritos + persistencia | 🔴 Alta |
| HU-11: Visualización de Sección de Favoritos | CP-011 | Sección dedicada + mensajes + navegación | 🔴 Alta |
| HU-12: Persistencia y Seguridad de Sesión | CP-012 | Gestión de sesión + "Recuérdame" + cierre seguro | 🔴 Alta |
| HU-13: Navegación Adaptativa por Roles | CP-013 | Control de acceso + roles + seguridad | 🔴 Alta |

---

### 🎯 Áreas de Enfoque Prioritarias

- **Seguridad**: Validación del sistema de verificación institucional, protección de datos de usuarios, gestión de sesiones y control de acceso basado en roles
- **Funcionalidad**: Correcta implementación de comentarios, reseñas, favoritos y navegación con todas sus operaciones CRUD
- **Usabilidad**: Interfaz intuitiva, coherente y accesible para toda la comunidad UCA con rediseño visual completo
- **Integridad de Datos**: Correcta conexión entre frontend (ReactJS) y backend (Spring Boot/PostgreSQL) para todas las épicas
- **Rendimiento**: Tiempos de respuesta aceptables y gestión eficiente de imágenes con Cloudinary
- **Persistencia**: Correcta gestión de sesiones de usuario y mantenimiento del estado de la aplicación

---

## 🔧 Contexto del Sistema

### 📱 Tecnologías Implementadas

| Componente | Tecnología |
|------------|------------|
| **Backend** | Spring Boot |
| **Frontend** | ReactJS |
| **Base de Datos** | PostgreSQL |
| **Almacenamiento** | Cloudinary (gestión de imágenes) |

### 👥 Equipo del Proyecto

| Integrante | Rol | Responsabilidades Clave |
|------------|-----|-------------------------|
| **Gabriela Sofia Quinteros Ramírez** | Diseño UI/UX y Documentación | Garantizar coherencia visual, validación de usabilidad y gestión de documentación final |
| **Oscar Ernesto Menjívar Ayala** | Frontend | Desarrollo de interfaces en React, integración de componentes y corrección de defectos visuales. |
| **David Ernesto Mejía Oliva** | Base de Datos | Diseño de tablas y endpoints de reseñas. |
| **Rodrigo Umanzor Velásquez** | Backend | Lógica de negocio en Spring Boot, gestión de APIs REST y validación de datos en servidor. |
| **José Andrés Valdés Jacobo** | Seguridad e Integración | Sistema de autenticación por correo institucional y ejecución de pruebas de integración. |
| **Graciela María Osegueda Hernández** | QA e Informe | Planificación de pruebas, gestión del ciclo de vida de defectos y elaboración del informe final |

---

## ✅ Criterios de Aceptación del Plan

### 🚀 Criterios de Entrada

- ✔️ Ambiente de pruebas configurado con Spring Boot y ReactJS
- ✔️ Base de datos PostgreSQL con datos de prueba cargados
- ✔️ Casos de prueba revisados y aprobados por el equipo
- ✔️ Herramientas de prueba disponibles y configuradas
- ✔️ Cloudinary configurado para pruebas de imágenes

### 🎯 Criterios de Salida

- ✔️ 100% de casos de prueba ejecutados
- ✔️ 0 defectos críticos sin resolver
- ✔️ Menos de 5 defectos medios sin resolver
- ✔️ Tasa de éxito mayor al 95%
- ✔️ Documentación de pruebas completa y entregada
- ✔️ Informe final aprobado por el equipo

### ⛔ Criterios de Suspensión

- ❌ Más de 3 defectos críticos bloqueantes
- ❌ Ambiente de pruebas inestable (backend/frontend/base de datos)
- ❌ Falta de datos de prueba críticos
- ❌ Sistema de autenticación institucional no operativo
- ❌ Problemas severos de integración frontend-backend

### ▶️ Criterios de Reanudación

- ✅ Defectos bloqueantes resueltos y verificados
- ✅ Ambiente estabilizado y funcional
- ✅ Datos de prueba restaurados
- ✅ Confirmación del equipo técnico de disponibilidad

---

## 🛠️ Recursos y Herramientas

### 🔧 Herramientas de Prueba

| Categoría | Herramientas |
|-----------|--------------|
| **Pruebas Funcionales** | Postman, React Testing Library |
| **Pruebas de Rendimiento** | JMeter, Lighthouse |
| **Pruebas de Seguridad** | verificación manual de autenticación |
| **Gestión de Pruebas** | JIRA |
| **Validación de UI** | Chrome DevTools, Figma (comparación de diseño) |

### 💻 Ambiente de Pruebas

| Componente | Especificación |
|------------|----------------|
| **Servidor Backend** | Spring Boot 2.x o superior |
| **Servidor Frontend** | React Development Server / Build de producción |
| **Base de Datos** | PostgreSQL 12+ |
| **Servicio de Imágenes** | Cloudinary (cuenta de pruebas) |
| **Sistema Operativo** | Windows/Linux/macOS |
| **Navegadores** | Chrome, Firefox, Safari, Edge (últimas versiones) |

### 📊 Datos de Prueba Requeridos

- **Usuarios de prueba**: Mínimo 15 cuentas con correo @uca.edu.sv (incluyendo usuarios con rol de administrador y usuario regular)
- **Productos**: Al menos 30 productos en diferentes categorías (alimenticios, tecnológicos, libros, tutorías)
- **Comentarios**: Datos existentes para pruebas de visualización, edición y eliminación (mínimo 50 comentarios)
- **Reseñas**: Conjunto variado de calificaciones de vendedores (1-5 estrellas) con al menos 20 reseñas
- **Favoritos**: Lista de productos marcados como favoritos por diferentes usuarios
- **Sesiones**: Datos de sesiones activas e inactivas para pruebas de persistencia
- **Imágenes**: Archivos de diferentes formatos y tamaños para Cloudinary (productos y perfiles)

---

## 🐛 Gestión de Defectos

### 📊 Clasificación de Severidad

| Nivel | Descripción | Impacto | Ejemplo |
|-------|-------------|---------|---------|
| **1. Crítico** | Sistema inoperativo, pérdida de datos, fallo de seguridad | 🔴 Bloqueante | Autenticación institucional no funciona |
| **2. Alto** | Funcionalidad principal afectada | 🟠 Mayor | No se pueden publicar comentarios |
| **3. Medio** | Funcionalidad secundaria afectada | 🟡 Moderado | Filtro de comentarios no responde |
| **4. Bajo** | Problemas cosméticos o menores | 🟢 Menor | Inconsistencia de colores en botones |

### 🔄 Proceso de Reporte de Defectos

1. **Identificación**: Tester detecta el defecto durante ejecución
2. **Documentación**: Se registra con pasos para reproducir, capturas y logs
3. **Clasificación**: Se asigna severidad y prioridad
4. **Asignación**: Se notifica al responsable técnico correspondiente
5. **Seguimiento**: QA verifica la resolución
6. **Cierre**: Se valida en ambiente de pruebas y se cierra el ticket

### 📈 Métricas de Calidad a Monitorear

- 📊 Densidad de defectos por módulo (comentarios, reseñas, UI, autenticación)
- ⏱️ Tiempo promedio de resolución de defectos
- 🔄 Tasa de reapertura de defectos
- 📋 Cobertura de pruebas por historia de usuario
- ✅ Porcentaje de casos de prueba exitosos

---

## ⚠️ Riesgos y Mitigación

| Riesgo | Impacto | Probabilidad | Estrategia de Mitigación |
|--------|---------|--------------|--------------------------|
| Datos de prueba insuficientes para 13 HU | 🔴 Alto | 🟡 Media | Generación automatizada de datos sintéticos desde el inicio |
| Cambios en requisitos durante pruebas | 🔴 Alto | 🟡 Media | Comunicación constante y pruebas iterativas por épica |
| Problemas de integración frontend-backend | 🔴 Alto | 🟡 Media | Pruebas de integración tempranas y continuas |
| Complejidad en gestión de roles y permisos | 🔴 Alto | 🟡 Media | Casos de prueba exhaustivos para cada tipo de usuario |
| Conflictos entre funcionalidades de épicas | 🟠 Medio | 🟡 Media | Pruebas de regresión después de cada épica |
| Problemas de persistencia de sesión | 🟠 Medio | 🟡 Media | Pruebas específicas con diferentes escenarios de tiempo |
| Ambiente de pruebas inestable | 🟠 Medio | 🟢 Baja | Ambiente de respaldo configurado |
| Retrasos en corrección de defectos | 🟡 Medio | 🟡 Media | Priorización clara por épica y seguimiento diario |
| Falta de disponibilidad de testers | 🟡 Medio | 🟢 Baja | Plan de contingencia con roles alternos en el equipo |
| Sobrecarga de casos de prueba (13 HU) | 🟡 Medio | 🔴 Alta | Priorización de casos críticos y automatización donde sea posible |


---

## 📅 Cronograma de Pruebas

### 🗓️ Timeline de Ejecución (8 Semanas)

**Semana 1: Preparación**
- Configuración del ambiente de pruebas
- Preparación de datos de prueba (usuarios, productos, comentarios, reseñas)
- Revisión y aprobación de casos de prueba
- Setup de herramientas

**Semana 2: Ejecución Fase 1 - Épica: Comentarios**
- Pruebas de visualización de comentarios
- Pruebas de filtrado de comentarios
- Pruebas de creación de comentarios
- Pruebas de edición y eliminación de comentarios

**Semana 3: Ejecución Fase 2 - Épica: Reseñas y Calificaciones**
- Pruebas de sistema de reseñas de vendedores
- Pruebas de calificación de vendedores
- Pruebas de visualización de reputación

**Semana 4: Ejecución Fase 3 - Épica: Rediseño Visual**
- Pruebas de coherencia visual entre secciones
- Pruebas de usabilidad y accesibilidad
- Pruebas de responsividad en diferentes dispositivos
- Validación de funcionalidad de elementos UI

**Semana 5: Ejecución Fase 4 - Épicas: Verificación y Favoritos**
- Pruebas de verificación institucional por correo UCA
- Pruebas del sistema de favoritos (agregar, eliminar, visualizar)
- Pruebas de gestión de lista de favoritos

**Semana 6: Ejecución Fase 5 - Épica: Sesión y Navegación**
- Pruebas de persistencia de sesión
- Pruebas de seguridad de navegación
- Pruebas de navegación basada en roles (usuario/administrador)

**Semana 7: Pruebas de Integración y No Funcionales**
- Pruebas de integración entre todas las épicas
- Pruebas de rendimiento (carga, respuesta)
- Pruebas de seguridad completas
- Pruebas de compatibilidad de navegadores
- Pruebas de regresión

**Semana 8: Cierre y Validación Final**
- Verificación de todos los defectos resueltos
- Pruebas de aceptación final con usuarios
- Re-testing de casos críticos
- Documentación final completa
- Informe de resultados y entrega

---
## 📝 Conclusiones

Este Plan de Pruebas para Marketplace UCA define un marco sólido para validar las 13 historias de usuario distribuidas en 6 épicas. Con 13 casos de prueba críticos, se garantiza cobertura funcional, seguridad, integridad de datos y una experiencia óptima para la comunidad UCA.

**Fortalezas del Plan:**

1. **Cobertura Completa:** Cada historia de usuario posee al menos un caso crítico que valida sus criterios principales.  
2. **Enfoque en Seguridad:** Se priorizan pruebas de verificación institucional, control de acceso y gestión segura de sesiones.  
3. **Experiencia de Usuario:** Se incluyen validaciones de usabilidad, accesibilidad y diseño responsivo.  
4. **Integración Frontend-Backend:** Se asegura comunicación correcta entre ReactJS, Spring Boot y persistencia en PostgreSQL.  
5. **Enfoque Iterativo:** La planificación por épicas permite detectar errores tempranamente y reducir riesgos.

**Aspectos Críticos Validados:**

- ✅ Sistema completo de comentarios y respuestas  
- ✅ Reseñas y calificaciones con cálculos automáticos  
- ✅ Interfaz coherente y responsiva  
- ✅ Validación estricta de correos UCA  
- ✅ Persistencia del sistema de favoritos  
- ✅ Gestión robusta de sesiones  
- ✅ Control de acceso basado en roles  

**Métricas de Éxito:**

- 100% de ejecución de casos críticos  
- 0 defectos críticos  
- <5 defectos medios  
- >85% de éxito  
- ≥75% de cobertura de criterios por historia  

El plan asegura que Marketplace UCA cumpla estándares de calidad, seguridad y usabilidad.

---

## 💡 Recomendaciones

### 🎯 Recomendaciones para la Ejecución de Pruebas

1. **Priorización de Épicas Críticas:** Ejecutar primero pruebas de Comentarios y Verificación Institucional.  
2. **Automatización de Casos Críticos:** Automatizar CP-001, CP-003, CP-005 y CP-009; priorizar autenticación y CRUD.  
3. **Pruebas de Integración Continua:** Ejecutar integración por épica y regresión antes de avanzar.  
4. **Gestión de Datos de Prueba:** Generar datos automáticos y mantener datasets consistentes.

### 🔒 Recomendaciones de Seguridad

5. **Validación en Múltiples Capas:** Implementar validaciones críticas en frontend y backend.  
6. **Pruebas de Penetración Básicas:** Probar SQLi, XSS, manipulación de tokens y privilegios.  
7. **Auditoría de Logs:** Registrar accesos no autorizados y operaciones sensibles.

### 👥 Recomendaciones de Usabilidad

8. **Pruebas con Usuarios Reales:** Realizar 2 sesiones con estudiantes UCA.  
9. **Validación de Accesibilidad:** Ejecutar pruebas con lectores de pantalla.  
10. **Compatibilidad de Navegadores:** Probar en Chrome, Firefox, Safari y Edge.

### 📊 Recomendaciones de Gestión

11. **Comunicación Continua:** Reuniones diarias y canal activo para defectos críticos.  
12. **Gestión de Defectos Eficiente:** Clasificación diaria y SLA claros.  
13. **Documentación Continua:** Actualizar resultados, incluir capturas y registrar lecciones aprendidas.

### 🚀 Recomendaciones para Entrega Final

14. **Checklist de Entrega:** Confirmar ejecución y aprobación de los 13 casos críticos y criterios de salida.  
15. **Plan de Contingencia:** Identificar funcionalidades movibles a fase 2 si hay retrasos.  
16. **Documentación para Mantenimiento:** Manuales, configuraciones y credenciales de acceso.

### 🔄 Recomendaciones Post-Implementación

17. **Monitoreo Post-Lanzamiento:** Seguimiento de métricas de uso y rendimiento.  
18. **Plan de Mejora Continua:** Revisiones periódicas, backlog activo y analíticas de uso.

---

**Estas recomendaciones aumentan significativamente la probabilidad de entregar un Marketplace UCA estable, seguro y alineado con las necesidades de la comunidad.**

---

## 📝 Información del Documento

| Campo | Información |
|-------|-------------|
| **Proyecto** | Marketplace UCA - Mejoras de Software |
| **Documento Preparado para** | Departamento de Electrónica e Informática - UCA |
| **Equipo de Proyecto** | Oscar Menjívar, David Mejía, Graciela Osegueda, Gabriela Quinteros, Rodrigo Umanzor, José Valdés |
| **Responsable de QA** | Graciela María Osegueda Hernández, José Andrés Valdés Jacobo |
| **Fecha** | Noviembre 2025 |
| **Versión** | 1.0 |
| **Estado** | ✅ En Revisión |

---

📧 **Contacto**: Para consultas sobre este plan de pruebas, contactar al equipo de QA a través de los canales oficiales del proyecto.