# Ejercicio 3: API de Empleados

API REST independiente desarrollada con Java 17 y Spring Boot. Los empleados se almacenan en una lista en memoria y se pierden al detener la aplicación.

## Requisitos y ejecución

- Java 17 o superior
- Maven 3.6 o superior

Desde esta carpeta, iniciar con `mvn spring-boot:run`. La API queda en `http://localhost:8083`.

La clave local de demostración es `clave-empleados-local`. Para usar una clave propia, definir la variable de entorno `API_KEY` antes de iniciar.

## Seguridad

Todas las solicitudes deben enviar el encabezado `X-API-KEY` con la clave configurada. Si falta o no coincide, la API responde HTTP 401.

## Endpoints

| Método | Ruta | Respuesta |
|---|---|---|
| POST | `/api/empleados` | Crea empleado; responde 201 |
| GET | `/api/empleados` | Lista todos; responde 200 |
| GET | `/api/empleados?especialidad=Sistemas` | Filtra por especialidad sin distinguir mayúsculas |
| GET | `/api/empleados/{id}` | Obtiene por ID; responde 404 si no existe |

Ejemplo del JSON para crear:

```json
{
  "nombre": "Carlos",
  "apellido": "Garcia",
  "correo": "carlos@example.com",
  "especialidad": "Sistemas",
  "estado": true
}
```

Nombre, apellido, correo y especialidad son obligatorios. El correo debe tener formato válido. `estado` es booleano y, si no se envía, inicia como `true`.

## Pruebas y Postman

Ejecutar las pruebas unitarias con `mvn test`. Importar `pruebapostmanejercicio3.json` en Postman. La colección cubre creación, consultas, filtro, validación, ID inexistente y accesos sin clave o con clave incorrecta.
