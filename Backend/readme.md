
# Task Manager API & Frontend

Este proyecto incluye una API para gestionar tareas y un frontend en React para interactuar con esta API.

## Requisitos

- Java 11 o superior
- Maven
- Node.js y npm

## Configuración del Backend (API)

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/task-manager.git
cd task-manager/backend
```

### Paso 2: Configurar el Archivo `application.properties`

Crea un archivo `src/main/resources/application.properties` con el siguiente contenido:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

### Paso 3: Construir y Ejecutar la Aplicación

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

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/task-manager.git
cd task-manager/frontend
```

### Paso 2: Instalar Dependencias

```bash
npm install
```

### Paso 3: Ejecutar la Aplicación

```bash
npm start
```

El frontend estará disponible en `http://localhost:3000`.

## Estructura del Proyecto

```
/task-manager
|-- /backend
|   |-- /src
|   |   |-- /main
|   |       |-- /java
|   |       |   |-- /com/taskManager/api
|   |       |       |-- TaskManagerApplication.java
|   |       |       |-- controller/TaskController.java
|   |       |       |-- dto/RequestDTO.java
|   |       |       |-- dto/ResponseDTO.java
|   |       |       |-- exception/GlobalExceptionHandler.java
|   |       |       |-- service/TaskService.java
|   |       |       |-- service/TaskServiceImpl.java
|   |       |-- /resources
|   |           |-- application.properties
|-- /frontend
|   |-- /public
|   |   |-- index.html
|   |-- /src
|   |   |-- /app
|   |   |   |-- store.js
|   |   |-- /features
|   |   |   |-- /tasks
|   |   |       |-- taskSlice.js
|   |   |-- /components
|   |   |   |-- TaskForm.js
|   |   |   |-- TaskList.js
|   |   |   |-- EditTaskForm.js
|   |   |-- App.js
|   |   |-- App.css
|   |   |-- index.js
```

## Validaciones

### Validaciones en el Backend

El backend utiliza validaciones de Spring Boot para asegurar que la descripción de la tarea no esté vacía.

**RequestDTO.java**
```java
import javax.validation.constraints.NotBlank;

public class RequestDTO {

    private Long id;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private LocalDateTime creationDate;

    private boolean active;

    // Getters and Setters
}
```

### Validaciones en el Frontend

El frontend incluye validaciones en los formularios para asegurarse de que la descripción de la tarea no esté vacía.

**TaskForm.js**
```javascript
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { createTask } from '../features/tasks/taskSlice';

const TaskForm = () => {
  const [description, setDescription] = useState('');
  const [error, setError] = useState('');
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (description.trim() === '') {
      setError('Description cannot be empty');
      return;
    }
    dispatch(createTask({ description, active: true }));
    setDescription('');
    setError('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        placeholder="Enter task description"
      />
      {error && <span className="error">{error}</span>}
      <button type="submit">Add Task</button>
    </form>
  );
};

export default TaskForm;
```

**EditTaskForm.js**
```javascript
import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { updateTask } from '../features/tasks/taskSlice';

const EditTaskForm = ({ task, onCancel }) => {
  const [description, setDescription] = useState(task ? task.description : '');
  const [error, setError] = useState('');

  useEffect(() => {
    if (task) {
      setDescription(task.description);
    }
  }, [task]);

  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (description.trim() === '') {
      setError('Description cannot be empty');
      return;
    }
    if (task) {
      dispatch(updateTask({ id: task.id, task: { description, active: task.active } }));
      onCancel();
    }
    setError('');
  };

  if (!task) {
    return null;
  }

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        placeholder="Enter task description"
      />
      {error && <span className="error">{error}</span>}
      <button type="submit">Update Task</button>
      <button type="button" onClick={onCancel}>Cancel</button>
    </form>
  );
};

export default EditTaskForm;
```
