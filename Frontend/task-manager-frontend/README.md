
# Task Manager API & Frontend

Este proyecto incluye una API para gestionar tareas y un frontend en React para interactuar con esta API.

## Requisitos

- Java 11 o superior
- Maven
- Node.js y npm

## Configuración del Backend (API)

### Paso 1: Configurar el Archivo `application.properties`

Crea un archivo `src/main/resources/application.properties` con el siguiente contenido:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

> **Nota**: Este proyecto utiliza una base de datos H2 en memoria. No es necesario un script SQL para configurar la base de datos, ya que H2 se inicializa automáticamente con el esquema y los datos necesarios al arrancar la aplicación.

### Paso 2: Construir y Ejecutar la Aplicación

```bash
mvn clean install
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080/api/tasks`.

### Endpoints de la API

- `GET /api/tasks`: Obtener todas las tareas
- `GET /api/tasks/{id}`: Obtener una tarea por ID
- `POST /api/tasks`: Crear una nueva tarea
- `PUT /api/tasks/{id}`: Actualizar una tarea existente
- `DELETE /api/tasks/{id}`: Eliminar una tarea

## Configuración del Frontend

### Paso 1: Instalar Dependencias

```bash
npm install
```

### Paso 2: Ejecutar la Aplicación

```bash
npm start
```

El frontend estará disponible en `http://localhost:3000`.

