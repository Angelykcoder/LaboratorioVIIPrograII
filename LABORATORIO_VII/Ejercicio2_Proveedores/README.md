# Ejercicio 2: API de Proveedores

API REST independiente desarrollada con Java 17 y Spring Boot. Los proveedores se almacenan en una lista en memoria y se pierden al detener la aplicación.

## Requisitos y ejecución

- Java 17 o superior
- Maven 3.6 o superior

Desde esta carpeta, ejecutar `mvn spring-boot:run`. La API queda en `http://localhost:8082`.

La clave local de demostración es `clave-proveedores-local`. Para configurar otra sin guardarla en el repositorio, definir la variable de entorno `API_KEY` antes de iniciar.

## Seguridad

Todas las solicitudes deben incluir el encabezado `X-API-KEY` con la clave configurada. Si falta o no coincide, la API responde HTTP 401.

## Endpoints

| Método | Ruta | Respuesta |
|---|---|---|
| POST | `/api/proveedores` | Crea proveedor; responde 201 |
| GET | `/api/proveedores` | Lista todos; responde 200 |
| GET | `/api/proveedores?categoria=Limpieza` | Filtra por categoría sin distinguir mayúsculas |
| GET | `/api/proveedores/{id}` | Obtiene por ID; responde 404 si no existe |

Ejemplo del JSON para crear:

```json
{
  "nombre": "Ana",
  "apellido": "López",
  "correo": "ana@example.com",
  "categoria": "Limpieza",
  "estado": true
}
```

Nombre, apellido, correo y categoría son obligatorios; el correo debe tener formato válido. `estado` es booleano y, si no se envía, inicia como `true`.

## Pruebas y Postman

Ejecutar las pruebas unitarias con `mvn test`. Importar `pruebapostmanejercicio2.json` en Postman. La colección usa la clave local predeterminada y cubre creación, consultas, filtro, validación, ID inexistente y accesos sin clave/con clave incorrecta.
