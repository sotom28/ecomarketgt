# MICROSERVICIOS:
 # User  Managegement API
 Este proyecto es un API RESt desarrollado con Spring Boot que permirte la gestion de usuarios, Incluye operacion de CRUD(Crear, leer, Actualizar, eliminar ,  ya que esta trabajando directamente con entidades jpa.
 
 
## Características

- API REST para gestión de usuarios y perfiles
- Conexión a base de datos Oracle
- Arquitectura basada en servicios
- Ejemplo de estructura de proyecto limpia
- Maven Version 4.0.0

## Requisitos

- Java 17+
- Oracle Database (Cloud o local)
- Spring boot: 
- lombok : 
- Spring Data JPA : 

## Configuración

Edita el archivo `src/main/resources/application.properties` con tus datos de conexión Oracle:

```properties
spring.datasource.url=jdbc:oracle:thin:@<tu_host>/<tu_servicio>?TNS_ADMIN=<ruta_wallet>

```

## Ejecución

Con Maven Wrapper (recomendado):

```bash
./mvnw spring-boot:run
```
En Windows:
```bash
mvnw.cmd spring-boot:run
```

La API estará disponible en:  
`http://localhost:8080/api/usuario`

 
 la escructura del proyecto


## Endpoints principales

- `GET /api/usuario/listar` - Listar todos los usuarios
- `POST /api/usuario/guardar` - Guardar un usuario
- `PUT /api/usuario/actualizar/{id}` - Actualizar usuario
- `DELETE /api/usuario/eliminarporid/{id}` - Eliminar usuario
- `GET /api/usuario/buscarnombre/{nombres}` - Buscar usuario por nombre
- `GET /api/usuario/buscaremail/{email}` - Buscar usuario por email
- `GET /api/usuario/buscarrut/{rut}` - Buscar usuario por rut

## Variables de entorno recomendadas

- `SERVER_PORT` (puerto del servidor,  8080)
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

## Ejemplo de petición

```json
POST /api/usuario/guardar
{
  "nombre": "Juan Perez",
  "email": "juan@correo.com",
  "perfil": "ADMIN"
}
```







 

