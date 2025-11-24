# 📘 Documentación: Servicio Web Marketplace UCA

## 🔗Enlace de la API
**`https://pnc-proyecto-final-grupo-03-s01-production.up.railway.app`**

## ❗ Información importante sobre _funcionalidades y validaciones_

- La autenticación **JWT** es obligatoria para los endpoints protegidos.
- Todas las rutas que comienzan con **`/user/auth/`** son accesibles sin necesidad de autenticación.
- Todas las rutas que comienzan con **`/admin/`** solo pueden ser accedidas por usuarios con el rol de **administrador**.
- Las imágenes se almacenan en **Cloudinary** con URL públicas.
- Los datos se almacenan en una base de datos **PostgreSQL** ejecutada en un **Droplet de Digital Ocean**.
- Se realiza una verificación de acceso al correo electrónico mediante un código opt el cual se almacena en una base de datos temporal **Redis** que se ejecuta en otro **Droplet de Digital Ocean**
- Solo se pueden registrar correos electrónicos con el dominio **`@uca.edu.sv`**.
- Las contraseñas deben cumplir con los siguientes requisitos:
  - Tener al menos **8 caracteres**.
  - Incluir al menos **un número**.
  - Contener letras **mayúsculas o minúsculas**.
- Los números de teléfono deben cumplir con los siguientes requisitos:
  - Debe ser de **8 caracteres**
  - **Sin guiones**, **sin prefijo** y **sin separación** entre ellos. Por ejemplo: `78787878`
- [**Diagrama de la base de datos**](https://ucaedusv-my.sharepoint.com/:i:/g/personal/00087022_uca_edu_sv/IQDDUguxOJhNT7ZoZMrPRTyhAWFG4UH5QcChbI13a_iDnUI?e=s1dDCD)
- Un **administrador** puede acceder a todas las rutas detalladas a continuación.

### 🔐 Credenciales del usuario administrador

- **Email:** `admin@uca.edu.sv`  
- **Password:** `admin123`

## 🔐 Autenticación y Usuarios

### `POST /user/auth/register`
Se envía unicament el correo que se desea registrar para poder validarlo.

**Headers:**
- Content-Type: application/json

**Body:**
```json
{
  "email": "email@uca.edu.sv"
}
```

**Respuesta esperada:** 200 Ok ✅
```json
{
  "data": "Sent to email: email@uca.edu.sv",
  "message": "Email sent successfully"
}
```
---
### `POST /user/auth/verify`
Se envía la información correspondiente junto con un codigo de verificación para crear un usuario

**Headers:**
- Content-Type: application/json

**Body:**
```json
{
  "name": "name",
  "email": "email@uca.edu.sv",
  "password": "Password123", 
  "faculty": "faculty",
  "phoneNumber": "77777777",
  "otp":"123456"
}
```

**Respuesta esperada:** 201 Created ✅
```json
{
  "data": "token",
  "message": "User registered successfully"
}
```
---
###  `POST /user/auth/login`
Autentica un usuario y devuelve un token JWT.

**Headers:**
- Content-Type: application/json

**Body:**
```json
{
  "email": "email@uca.edu.sv",
  "password": "Password123"
}
```

**Respuesta esperada:** 201 created ✅
```json
{
  "data": "token",
  "message": "Access granted"
}
```
---
###  `PATCH admin/user/reassign`
Dado un email se busca un usuario y se le cambia por el rol pasado.

**Headers:**
-  Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Params:** `email= email@uca.edu.sv` `role= ROLE`

**Respuesta esperada:** 200 Ok ✅
```json
{
  "data": " ",
  "message": "User reassigned successfully"
}
```
---
###  `PATCH /user/password`
Cambia la contraseña del usuario autenticado.

**Headers:**
-  Authorization: Bearer token
- Content-Type: application/json

**Body:** 
```json
{
  "oldPassword": "Password123",
  "newPassword": "Password123"
}
```

**Respuesta esperada:** 202 Accepted ✅
```json
{
  "data": " ",
  "message": "Password reset successfully"
}
```
---
###  `PATCH /user/phoneNumber`
Cambia el número de celular del usuario autenticado.

**Headers:**
-  Authorization: Bearer token
- Content-Type: application/json

**Body:** 
```json
{
  "phoneNumber": "78787878"
}
```

**Respuesta esperada:** 200 Ok ✅
```json
{
  "data": " ",
  "message": "Phone Number updated successfully"
}
```
---
###  `GET /user/all`
Obtiene todos los usuarios que son usuarios

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"name": "name",
			"email": "email@uca.edu.sv",
			"phoneNumber": "77777777",
			"rating": 0,
			"facultyName": "facultyName"
		}
	],
	"message": "Ok"
}
```
---
###  `GET admin/user/all`
Obtiene todos los usuarios que son admin

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"name": "name",
			"email": "email@uca.edu.sv",
			"phoneNumber": "77777777",
			"rating": 0,
			"facultyName": "facultyName"
		}
	],
	"message": "Ok"
}
```
---
###  `GET /user/email`
Obtiene un usuario a partir del correo

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Params:** `email= email@uca.edu.sv`

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"name": "name",
			"email": "email@uca.edu.sv",
			"phoneNumber": "77777777",
			"rating": 0,
			"facultyName": "facultyName"
		}
	],
	"message": "Ok"
}
```
---
###  `GET /user/name`
Obtiene todos los usuarios dado un nombre

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Params:** `name = name`

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"name": "name",
			"email": "email@uca.edu.sv",
			"phoneNumber": "77777777",
			"rating": 0,
			"facultyName": "facultyName"
		}
	],
	"message": "Ok"
}
```
---
###  `GET /user/faculty`
Obtiene todos los usuarios dado una facultad

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Params:** `faculty= facultyName`

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"name": "name",
			"email": "email@uca.edu.sv",
			"phoneNumber": "77777777",
			"rating": 0,
			"facultyName": "facultyName"
		}
	],
	"message": "Ok"
}
```
---
###  `GET /user/rating`
Obtiene todos los usuarios dado un rating

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Params:** `rating= 0`

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"name": "name",
			"email": "email@uca.edu.sv",
			"phoneNumber": "77777777",
			"rating": 0,
			"facultyName": "facultyName"
		}
	],
	"message": "Ok"
}
```
## 📝 Category

###  `POST /admin/category/create`
Crea una categoria nueva

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
  "name": "name",
  "description": "description"
}
```

**Respuesta esperada:** 201 Created ✅
```json
{
"data": {
		"name": "name",
		"description": "description"
	},
	"message": "Category Created"
}
```
---
###  `GET /category/all`
Obtiene todas las categorias

**Headers:**
- Authorization: Bearer token

**Body:** No Body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"name": "name",
			"description": "description"
		}
	],
	"message": "Ok"
}
```
---
###  `GET /category/name`
Obtiene una categoría dado el nombre

**Headers:**
- Authorization: Bearer token

**Body:** No Body

**Params:** `name= name`

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"name": "name",
			"description": "description"
		}
	],
	"message": "Ok"
}
```
---
###  `PATCH /admin/category/update`
Actualiza una categoria

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
  "name": "name",
  "newName": "newName",
  "newDescription": "newDescription"
}
```

**Respuesta esperada:** 202 Accepted ✅
```json
{
  "data": {
		"code": "code",
		"name": "name",
		"description": "description"
	},
	"message": "Category Updated"
}
```

## 🛒 Product

###  `POST /product/create`
Crea un producto

**Headers:**
- Authorization: Bearer token
- Content-Type: multipart/form-data  ❗**IMPORTANTE**

**Body:** Debe ser multipart/form-data
- **product:**
	```json
	{
	  "product": "product",
	  "descriptoion": "descriptoion",
	  "price": 0.0,
	  "condition": "condition",
	  "categoryName": "categoryName"
	}
	```
- **images:**
		Aquí se carga el archivo de la imagen en formato .jpg o .png
		_Aclaración: si se desean subir muchas imagenes, simplemente, se repite el campo images las veces necesarias_

**Respuesta esperada:** 201 Created ✅
```json
{
	"data": {
		"id": "id",
		"product": "product",
		"description": "description",
		"price": 0.0,
		"condition": "condition",
		"images": [
			"https://res.cloudinary.com/rodrigoumanzor/image/upload/v1750696419/marketplace-uca/nombre-de-imagen.jpg"
		],
		"categoryName": "categoryName",
		"userName": "userName"
	},
	"message": "Product created successfully"
}
```
---
###  `PATCH /product/update`
Actualiza un producto. _Solo se permite actualizar el precio del producto_

**Headers:**
- Authorization: Bearer <token>
- Content-Type: application/json

**Body:**
```json
	{
	  "id": "id",
	  "price": 0.0
	}
```
	
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": {
		"id": "id",
		"product": "product",
		"description": "description",
		"price": 0.0,
		"condition": "condition",
		"images": [
			"https://res.cloudinary.com/rodrigoumanzor/image/upload/v1750696419/marketplace-uca/nombre-de-imagen.jpg"
		],
		"categoryName": "categoryName",
		"userName": "userName"
	},
	"message": "Product updated successfully"
}
```
---
### `PATCH admin/product/activation/{id}`
Activa un producto dado un id

**Headers:**
- Authorization: Bearer token

**Body:** No body

**Params:** `id= id`
	
**Respuesta esperada:** 200 Ok ✅
`Product activated successfully `

---
### `GET /product/`
Obtiene todos los productos

**Headers:**
- Authorization: Bearer token

**Body:** No body
	
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
		"id": "id",
		"product": "product",
		"description": "description",
		"price": 0.0,
		"condition": "condition",
		"images": [
			"https://res.cloudinary.com/rodrigoumanzor/image/upload/v1750696419/marketplace-uca/nombre-de-imagen.jpg"
		],
		"categoryName": "categoryName",
		"userName": "userName"
		}
	],
	"message": "Ok"
}
```
---
### `GET admin/product/`
Obtiene todos los productos marcados como inactivos

**Headers:**
- Authorization: Bearer token

**Body:** No body
	
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
		"id": "id",
		"product": "product",
		"description": "description",
		"price": 0.0,
		"condition": "condition",
		"images": [
			"https://res.cloudinary.com/rodrigoumanzor/image/upload/v1750696419/marketplace-uca/nombre-de-imagen.jpg"
		],
		"categoryName": "categoryName",
		"userName": "userName"
		}
	],
	"message": "Ok"
}
```
---
### `GET /product/{id}`
Obtiene un producto a partir del id

**Headers:**
- Authorization: Bearer token

**Body:** No body
	
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": {
		"id": "id",
		"product": "product",
		"description": "description",
		"price": 0.0,
		"condition": "condition",
		"images": [
			"https://res.cloudinary.com/rodrigoumanzor/image/upload/v1750696419/marketplace-uca/nombre-de-imagen.jpg"
		],
		"categoryName": "categoryName",
		"userName": "userName"
	},
	"message": "Ok"
}
```
---
### `GET /product/my`
Obtiene un producto a partir del usuario autenticado

**Headers:**	
- Authorization: Bearer token

**Body:** No body
	
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
		"code": "code",
		"product": "product",
		"description": "description",
		"price": 0.0,
		"condition": "condition",
		"images": [
			"https://res.cloudinary.com/rodrigoumanzor/image/upload/v1750696419/marketplace-uca/nombre-de-imagen.jpg"
		],
		"categoryName": "categoryName",
		"userName": "userName"
		}
	],
	"message": "Ok"
}
```
---
### `GET /product/user`
Obtiene un producto a partir del email de un usuario

**Headers:**	
- Authorization: Bearer token

**Body:** No body

**Params:** `email= email@uca.edu.sv`
	
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
		"code": "code",
		"product": "product",
		"description": "description",
		"price": 0.0,
		"condition": "condition",
		"images": [
			"https://res.cloudinary.com/rodrigoumanzor/image/upload/v1750696419/marketplace-uca/nombre-de-imagen.jpg"
		],
		"categoryName": "categoryName",
		"userName": "userName"
		}
	],
	"message": "Ok"
}
```
---
### `GET /product/category/{name}`
Obtiene un producto a partir de la categoria indicada

**Headers:**	
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body
	
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
		"id": "id",
		"product": "product",
		"description": "description",
		"price": 0.0,
		"condition": "condition",
		"images": [
			"https://res.cloudinary.com/rodrigoumanzor/image/upload/v1750696419/marketplace-uca/nombre-de-imagen.jpg"
		],
		"categoryName": "categoryName",
		"userName": "userName"
		},
	],
	"message": "Ok"
}
```
---
### `DELETE /product/delete/{id}`
Elimina un producto a partir del id indicado

**Headers:**	
- Authorization: Bearer token

**Body:** No body
	
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": null,
	"message": "Product deleted successfully"	
}
```

## ✏️ Comments

### `POST /comments/create`
Crea un nuevo comentario.

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
	"productId":"productId",
	"comment":"comment"
}
```
**Respuesta esperada:** 201 Created ✅
```json
{
	"data": {
		"id": "commentId",
		"comment": "comment",
		"createdAt": "5 minutos ago",
		"updatedAt": null,
		"username": "email@uca.edu.sv",
		"responses": [],
		"parentId": null,
		"productId": "productId"
	},
	"message": "Ok"
}
```
---
### `POST /comments/reply`
Crea una respuesta al comentario indicado.

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
	"commentIdToReply":"commentIdToReply",
	"comment":"comment"
}
```
**Respuesta esperada:** 201 Created ✅
```json
{
	"data": {
		"id": "id",
		"comment": "comment",
		"createdAt": "5 minutos ago",
		"updatedAt": null,
		"responseCount": 0,
		"username": "email@uca.edu.sv",
		"parentId": "parentId",
		"productId": "productId"
	},
	"message": "Ok"
}
```
---
### `PATCH /comments/update`
Actualiza un comentario

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
	"commentId":"commentId",
	"comment":"comment"
}
```
**Respuesta esperada:** 201 Created ✅
```json
{
	"data": {
		"id": "id",
		"comment": "comment",
		"createdAt": "5 minutos ago",
		"updatedAt": null,
		"responseCount": 0,
		"username": "email@uca.edu.sv",
		"parentId": "parentId",
		"productId": "productId"
	},
	"message": "Ok"
}
```
---
### `GET /comments/{id}`
Obtiene un comentario a partir del id.

**Headers:**
- Authorization: Bearer token

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": {
		"id": "id",
		"comment": "comment",
		"createdAt": "5 minutos ago",
		"updatedAt": null,
		"responseCount": 0,
		"username": "email@uca.edu.sv",
		"parentId": "parentId",
		"productId": "productId"
	},
	"message": "Ok"
}
```
---
### `GET /comments/product/{id}`
Obtiene los comentarios a partir del id de un producto.

**Headers:**
- Authorization: Bearer token

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"id": "id",
			"comment": "comment",
			"createdAt": "5 minutos ago",
			"updatedAt": null,
			"responseCount": 1,
			"username": "email@uca.edu.sv",
			"parentId": null,
			"productId": "productId"
		},
		{
			"id": "id",
			"comment": "comment",
			"createdAt": "5 minutos ago",
			"updatedAt": null,
			"responseCount": 1,
			"username": "email@uca.edu.sv",
			"parentId": null,
			"productId": "productId"
		}
	],
	"message": "Ok"
}
```
---
### `GET /comments/user`
Obtiene los comentarios a partir del usuario autenticado.

**Headers:**
- Authorization: Bearer token

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"id": "id",
			"comment": "comment",
			"createdAt": "5 minutos ago",
			"updatedAt": null,
			"responseCount": 1,
			"username": "email@uca.edu.sv",
			"parentId": null,
			"productId": "productId"
		},
		{
			"id": "id",
			"comment": "comment",
			"createdAt": "5 minutos ago",
			"updatedAt": null,
			"responseCount": 1,
			"username": "email@uca.edu.sv",
			"parentId": null,
			"productId": "productId"
		}
	],
	"message": "Ok"
}
```
---
### `GET /comments/responses/{id}`
Obtiene las respuestas a partir del id de un comentario.

**Headers:**
- Authorization: Bearer token

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"id": "id",
			"comment": "comment",
			"createdAt": "5 minutos ago",
			"updatedAt": null,
			"responseCount": 1,
			"username": "email@uca.edu.sv",
			"parentId": null,
			"productId": "productId"
		},
		{
			"id": "id",
			"comment": "comment",
			"createdAt": "5 minutos ago",
			"updatedAt": null,
			"responseCount": 1,
			"username": "email@uca.edu.sv",
			"parentId": null,
			"productId": "productId"
		}
	],
	"message": "Ok"
}
```
---
### `GET /comments/product/relevance/{id}`
Obtiene los comentarios ordenados por relevancia a partir del id de un producto

**Headers:**
- Authorization: Bearer token

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"id": "id",
			"comment": "comment",
			"createdAt": "5 minutos ago",
			"updatedAt": null,
			"responseCount": 1,
			"username": "email@uca.edu.sv",
			"parentId": null,
			"productId": "productId"
		},
		{
			"id": "id",
			"comment": "comment",
			"createdAt": "5 minutos ago",
			"updatedAt": null,
			"responseCount": 1,
			"username": "email@uca.edu.sv",
			"parentId": null,
			"productId": "productId"
		}
	],
	"message": "Ok"
}
```
---
### `DELETE /comments/delete/{id}`
Elimina un comentario a partir del id de un comentario.

**Headers:**
- Authorization: Bearer token

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": []
	"message": "Comment deleted successfully"
}
```
---
## 🏫 Faculty

### `POST /admin/faculty/create`
Crea una nueva facultad.

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
	"facultyName":"facultyName"
}
```
**Respuesta esperada:** 201 Created ✅
```json
{
	"data": null,
	"message": "Faculty created successfully"
}
```
---
### `PATCH /admin/faculty/update`
Actualiza una facultad.

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
	"facultyName":"facultyName",
	"newFacultyName":"newFacultyName"
}
```
**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"facultyName": "facultyName"
		}
	],
	"message": "Faculty updated successfully"
}
```
---
### `GET /admin/faculty/all`
Obtiene todas las facultades

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"facultyName": "facultyName"
		},
	],
	"message": "Faculty updated successfully"
}
```
---
### `GET /admin/faculty/name`
Obtiene una facultad a partir del nombre

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Params:** `name= name`

**Respuesta esperada:** 200 Ok ✅
```json
{
	"data": [
		{
			"facultyName": "facultyName"
		},
	],
	"message": "Faculty updated successfully"
}
```

## ❤️Likes

### `POST /likes/add`
Dar like a un product.

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
	"productId":"productId"
}
```
**Respuesta esperada:** 201 Created ✅
```json
{
	"data": {
		"name": "name",
		"email": "email@uca.edu.sv",
		"product": "productId"
	},
	"message": "ok"
}
```
### `GET /likes/`
Obtener todos los likes del usuario autenticado.

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok✅
```json
{
	"data": {
		"name": "name",
		"email": "email@uca.edu.sv",
		"product": "productId"
	},
	"message": "ok"
}
```

### `DELETE /likes/delete/{id}`
Eliminar un like a partir de un id.

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok✅
```json
{
	"data": " ",
	"message": "Deleted successfully"
}
```
## 💯Reseñas

### `POST /reviews/create`
Crea una review

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:**
```json
{
	"userEmail":"email@uca.edu.sv",
	"rating":"2",
	"comment":"comment",
}
```
**Respuesta esperada:** 201 Created ✅
```json
{
	"data": {
		"id": "id",
		"rating": 2,
		"comment": "comment",
		"reviewerId": "reviewerId",
		"reviewer": "email@uca.edu.sv",
		"revieweeId": "reviewerId",
		"reviewee": "email@uca.edu.sv"
	},
	"message": "Ok"
}
```
### `GET /reviews/{id}`
Obtener una review a partir de su id

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok✅
```json
{
	"data": {
		"id": "id",
		"rating": 2,
		"comment": "comment",
		"reviewerId": "reviewerId",
		"reviewer": "email@uca.edu.sv",
		"revieweeId": "reviewerId",
		"reviewee": "email@uca.edu.sv"
	},
	"message": "Ok"
}
```

### `GET /review/product/{id}`
Obtener todas las reviews a partir del id de un producto

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok✅
```json
{
	"data": {
		"id": "id",
		"rating": 2,
		"comment": "comment",
		"reviewerId": "reviewerId",
		"reviewer": "email@uca.edu.sv",
		"revieweeId": "reviewerId",
		"reviewee": "email@uca.edu.sv"
	},
	"message": "Ok"
}
```
### `GET /review/seller/{username}`
Obtener todas las reviews hacía un usuario que posee ese username. El username de un usuario es su email.

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok✅
```json
{
	"data": {
		"id": "id",
		"rating": 2,
		"comment": "comment",
		"reviewerId": "reviewerId",
		"reviewer": "email@uca.edu.sv",
		"revieweeId": "reviewerId",
		"reviewee": "email@uca.edu.sv"
	},
	"message": "Ok"
}
```
### `GET /review/user`
Obtener todas las reviews del usuario autenticado

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok✅
```json
{
	"data": {
		"id": "id",
		"rating": 2,
		"comment": "comment",
		"reviewerId": "reviewerId",
		"reviewer": "email@uca.edu.sv",
		"revieweeId": "reviewerId",
		"reviewee": "email@uca.edu.sv"
	},
	"message": "Ok"
}
```
### `Patch /review/update`
Actualizar una review

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** 
```json
{
	"data": {
		"reviewId": "id",
		"rating": 2,
		"comment": "comment"
	},
	"message": "Ok"
}
```

**Respuesta esperada:** 200 Ok✅
```json
{
	"data": {
		"id": "id",
		"rating": 2,
		"comment": "comment",
		"reviewerId": "reviewerId",
		"reviewer": "email@uca.edu.sv",
		"revieweeId": "reviewerId",
		"reviewee": "email@uca.edu.sv"
	},
	"message": "Ok"
}
```
### `Delete /review/delete/{id}`
Eliminar una review a partir de un id

**Headers:**
- Authorization: Bearer token
- Content-Type: application/json

**Body:** No body

**Respuesta esperada:** 200 Ok✅
```json
{
	"data": []
	"message": "Ok"
}
```
## 🚨 Códigos de Error
| Código HTTP | Significado | Excepción Interna | Causa Común |
| :--- | :--- | :--- | :--- |
| **200 OK** | Éxito | - | Petición correcta. |
| **201 Created** | Creado | - | Recurso creado exitosamente. |
| **204 No Content** | Sin Contenido | `RoleAlreadySet` | Intentar asignar un rol que el usuario ya tiene. |
| **400 Bad Request** | Error Cliente | `MethodArgumentNotValid` | Fallo de validación (Regex email, Password corto, campos vacíos). |
| **401 Unauthorized** | No Autorizado | `UserNoAuthorized` | Credenciales incorrectas, Token expirado o inválido. |
| **403 Forbidden** | Prohibido | `EmailNotVerify`, `ImpossibleAction` | OTP incorrecto, Email no verificado, intentar borrar al Super Admin. |
| **404 Not Found** | No Encontrado | `UserNotFound`, `ProductNotFound`, `CategoryNotFound`, `ReviewNotFound` | El ID (UUID) o recurso solicitado no existe. |
| **409 Conflict** | Conflicto | `UserAlreadyExist`, `CategoryAlreadyExists` | El correo o nombre de categoría ya está registrado. |
| **500 Server Error** | Error Interno | `IOException`, `FailedToSaveImage` | Error al conectar con Cloudinary o fallo inesperado. |

¡Llegaste al final de la documentación! 
Ahora oficialmente hablas _JSON fluido_. 🧠💻
