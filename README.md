# ☁️ Servicio Web: Marketplace UCA

## 📝 Descripción del servicio

*Marketplace UCA* es un servicio web que permite a los usuarios publicar productos para realizar compras y ventas dentro de la comunidad. Su principal característica es su exclusividad, ya que solo pueden registrarse personas con correos *@uca.edu.sv*.  
El sistema incluye validaciones de seguridad que rechazan automáticamente cualquier cuenta que no cumpla con este requisito.

Los usuarios pueden *comentar* productos, *marcarlos como favoritos, **calificarlos* y dejar *reseñas* tanto a compradores como a vendedores.

Para mantener un entorno seguro y confiable, los usuarios con rol de *administrador* pueden *aprobar o rechazar* nuevas publicaciones antes de que se muestren en el marketplace.


## 🧩 Problemática identificada

Este proyecto surge como una alternativa para que toda la comunidad pueda comprar y vender productos de forma sencilla. Muchos estudiantes adquieren libros, calculadoras u otros artículos que luego ya no utilizan, y es precisamente para resolver este tipo de situaciones que nace *Marketplace UCA*.

Además, somos conscientes de la cantidad de emprendedores dentro de la comunidad, por lo que también buscamos brindarles un espacio donde puedan promocionar y vender sus productos.



## ‼️ Consideraciones de software

El proyecto ha sido desarrollado utilizando *Spring Boot* junto con *Java 21*.  
###  🔖 Dependencias principales
- **Spring Boot:**  JPA, Security, Validation, Web Starter, Test, Security Test, Data Redis
    
- **PostgreSQL**
    
- **Lombok**
    
- **JJWT:**   jjwt-api, jjwt-impl, jjwt-jackson
    
- **Cloudinary**
    
-    **PrettyTime**
    

### 📌 Función de estas dependencias

-   *JJWT* → creación, validación y manipulación de tokens *JWT*.
    
-   *Cloudinary* → almacenamiento de imágenes en la nube.
    
-   *PrettyTime* → permite mostrar fechas en formato relativo, como “hace 2 horas”.


## ⚙️ Instalación

### 1️⃣ Preparación del entorno

El proceso de instalación consta de dos partes:  
*(1) preparación del entorno* y *(2) descarga del proyecto*.

Para la preparación del entorno, es necesario tener *Docker* instalado, ya que se usarán las imágenes de *PostgreSQL* y *Redis*.  

A continuación se muestran los comandos para crear los contenedores correspondientes:
```Bash
docker run --name uca-postgres -e POSTGRES_PASSWORD=admin123 -p 5432:5432 -d postgres
```
```Bash
docker run --name uca-redis -p 6379:6379 -d redis
``` 

Una vez creados los contenedores y verificado que estén funcionando correctamente, puedes continuar con la segunda parte.

### 2️⃣ Descarga del proyecto
Dentro del archivo descargado se encuentran las siguientes carpetas:

-   *Backend* → contiene el código fuente del proyecto (esta carpeta debe abrirse en tu editor de código).
    
-   *Documentación* → incluye toda la información técnica y descriptiva del sistema.
    
### 3️⃣ Configuración previa
Antes de ejecutar el proyecto, es necesario configurar las *variables de entorno*.  
Más adelante se detallan todas las variables necesarias junto con los valores que deben asignarse.

## 🛠️ Variables de Entorno
A continuación se presentan las variables de entorno necesarias para ejecutar este proyecto.  
Cada variable incluye una breve descripción de su propósito.
```env
# PostgreSQL
DB_LINK=localhost:5432/marketplace         # Host, puerto y base de datos
DB_USER=postgres                           # Usuario de la base de datos
DB_PASSWORD=your_secure_db_password        # Contraseña del usuario

# Redis
REDIS_HOST=localhost                       # Host donde corre Redis
REDIS_PORT=6379                            # Puerto de Redis

# Email (SMTP)
EMAIL_HOST=smtp.gmail.com                  # Servidor SMTP
EMAIL_PORT=587                             # Puerto TLS
EMAIL=your_email@example.com               # Correo remitente
EMAIL_PASSWORD=your_email_app_password     # App password o clave SMTP

# JWT
TOKEN_SECRET_KEY=your_very_secret_key      # Clave secreta para firmar tokens
TOKEN_EXPIRATION_TIME=900000               # Expiración del token (ms)

# Cloudinary
CLOUD_KEY=your_cloudinary_api_key          # API Key de Cloudinary
CLOUD_NAME=your_cloud_name                 # Nombre del cloud
CLOUD_SECRET=your_cloudinary_api_secret    # API Secret
```

## ▶️ Ejecución
Una vez realizados todos los pasos anteriores, podemos ejecutar nuestro proyecto y acceder a él mediante un cliente HTTP como *Insomnia, **Postman*, o cualquier otra herramienta similar, para comenzar a consumir el contenido de la API.
