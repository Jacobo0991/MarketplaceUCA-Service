# 📒 Manual de Desarrollo

## 🔄 Proceso de Desarrollo 

El proyecto se ha desarrollado utilizando **Git** como herramienta de control de versiones, siguiendo **Git Flow** como estrategia de ramificación. Se cuenta con una rama **master**, que contiene la versión más estable y completa del proyecto, y una rama **develop**, donde se integran todas las ramas utilizadas para el desarrollo individual de funcionalidades.

A partir de la rama **develop** se generan dos tipos de ramas:

1.  **Hot-fix**: para realizar modificaciones rápidas o correcciones menores.
    
2.  **Feature branches**: cada una corresponde a una funcionalidad específica que se desea implementar.
    

Una vez finalizado el trabajo en una rama de funcionalidad, esta se fusiona (merge) con **develop**. Cuando **develop** se considere completa y estable, se realiza un merge hacia **master**, garantizando que esta contenga siempre la versión más confiable del proyecto.

Se recomienda seguir este mismo patrón durante el desarrollo. Es importante que, al finalizar una funcionalidad o corregir un error, se **elimine la rama correspondiente**, de modo que al final solo queden **dos ramas principales**: **master** y **develop**.

## ✅Agregar Nuevas Funcionalidades

Para agregar una nueva funcionalidad, se recomienda seguir la **estrategia de ramificación (branch strategy)** propuesta anteriormente. Una vez ubicada en la rama correcta, se debe definir claramente la funcionalidad o corrección que se desea implementar.

Una característica destacada del proyecto es su **arquitectura por capas** y la aplicación de los **principios SOLID**, por lo que se recomienda seguirlos y respetarlos en cada desarrollo.

Es altamente aconsejable **revisar el código existente**, ya que muchas soluciones a problemas futuros pueden aprovechar los diseños que ya se han implementado. Por ejemplo, si se desea agregar un apartado de chat y permitir que el usuario edite únicamente los mensajes que él envía, el problema sería **cómo restringir la edición a los propios elementos del usuario**. Realizando una revisión del proyecto, es posible encontrar un diseño previo que resuelva un problema similar, lo que permite **mantener el código estandarizado, más fácil de mantener y escalable**.

## 🤝🏻 Convenciones de código

Se sugiere encarecidamente seguir estas reglas para mantener **coherencia y orden** en el código:  

1. **Separación de capas y responsabilidades**, respetando la arquitectura del proyecto (por ejemplo, controller, service, repository).  

2. **Funciones y métodos cortos**, idealmente que cumplan **una sola responsabilidad**.  

3. **Evitar código duplicado**, reutilizando funciones, métodos o servicios existentes siempre que sea posible.  

4. **Manejo adecuado de excepciones**, evitando capturas genéricas como `Exception` y proporcionando mensajes claros.  

5. **Nombres de clases en PascalCase** y representativos del objeto o función que cumplen.  
   - Ejemplo: `UserController`, `ProductService`.  

6. **Nombres de variables en inglés**, utilizando el estilo **camelCase**.  
   - Ejemplo: `userId`.  

7. **Nombres de funciones específicos**, siguiendo la misma convención que los nombres de variables.  

8. **Constantes en mayúsculas con guiones bajos**, para diferenciarlas de variables normales.  
   - Ejemplo: `MAX_LOGIN_ATTEMPTS`.  

9. **Comentarios en español**, para una mejor comprensión del código.  

10. **Convención para interfaces e implementaciones**:  
    - Al desarrollar una **interfaz**, el nombre del archivo debe iniciar con `i` en minúscula seguido del nombre de la clase.  
		- Ejemplo: `iUserService.java`.  
    - Al desarrollar una **implementación**, se debe agregar `Impl` al final del nombre del archivo.  
      - Ejemplo: `UserServiceImpl.java`.  

11. **Nombres de archivos según la carpeta**, todos los archivos de una misma carpeta deben reflejar el nombre de esa carpeta para mantener coherencia.  
    - Ejemplo: si se crea una carpeta `utils`, el archivo debe llamarse `TokenUtils.java`, `DateUtils.java`, etc.  

12. **Respetar la línea vertical de 120 caracteres** que aparece en el IDE (generalmente llamada **ruler** o **guideline**), para mantener el código legible.

