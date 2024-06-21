
## Endpoints

### Obtener Todas las Tareas

- **URL**: `/`
- **Método**: `GET`
- **Descripción**: Retorna una lista de todas las tareas.
- **Respuestas**:
  - **200 OK**: Éxito al obtener la lista de tareas.

### Obtener Tarea por ID

- **URL**: `/{id}`
- **Método**: `GET`
- **Descripción**: Retorna una tarea específica por su ID.
- **Parámetros URL**:
  - **id** (required): ID de la tarea a obtener.
- **Respuestas**:
  - **200 OK**: Éxito al obtener la tarea.
  - **404 Not Found**: La tarea con el ID especificado no fue encontrada.

### Crear Tarea

- **URL**: `/`
- **Método**: `POST`
- **Descripción**: Crea una nueva tarea.
- **Cuerpo de la Petición**:
  - **description** (required): Descripción de la tarea.
  - **active** (optional): Estado de la tarea, activa o no.
- **Respuestas**:
  - **201 Created**: Tarea creada exitosamente.

### Actualizar Tarea

- **URL**: `/{id}`
- **Método**: `PUT`
- **Descripción**: Actualiza una tarea existente.
- **Parámetros URL**:
  - **id** (required): ID de la tarea a actualizar.
- **Cuerpo de la Petición**:
  - **description** (required): Nueva descripción de la tarea.
  - **active** (required): Estado actualizado de la tarea.
- **Respuestas**:
  - **200 OK**: Tarea actualizada exitosamente.
  - **404 Not Found**: No se encontró la tarea especificada.

### Eliminar Tarea

- **URL**: `/{id}`
- **Método**: `DELETE`
- **Descripción**: Elimina una tarea.
- **Parámetros URL**:
  - **id** (required): ID de la tarea a eliminar.
- **Respuestas**:
  - **204 No Content**: Tarea eliminada exitosamente.
  - **404 Not Found**: No se encontró la tarea especificada para eliminar.

## Formato de Datos

### Task Object

```json
{
  "id": "int",
  "description": "string",
  "creationDate": "datetime",
  "active": "boolean"
}
