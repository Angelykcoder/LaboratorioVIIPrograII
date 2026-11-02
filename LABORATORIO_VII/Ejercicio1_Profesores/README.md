# Ejercicio 1 – API de Profesores

API REST con Spring Boot que administra profesores. Los datos se guardan en una lista en memoria (se pierden al reiniciar).

## Requisitos

- Java 17 o superior
- Maven 3.9+

## Ejecutar

```bash
mvn spring-boot:run
```

La API queda en `http://localhost:8081`.

## API Key

Todos los endpoints exigen el encabezado `X-API-KEY`. Sin clave o con clave incorrecta se responde `401 Unauthorized`.

La clave se configura con la variable de entorno `API_KEY` (propiedad `app.api-key`). Si no existe, se usa un valor de desarrollo local definido en `application.properties`.

```bash
# Windows (PowerShell)
$env:API_KEY = "mi-clave-secreta"
mvn spring-boot:run
```

## Endpoints

| Método | Ruta                                 | Descripción                       | Respuestas        |
|--------|--------------------------------------|-----------------------------------|-------------------|
| POST   | `/api/profesores`                    | Crear un profesor                 | 201, 400, 401     |
| GET    | `/api/profesores`                    | Listar todos                      | 200, 401          |
| GET    | `/api/profesores?especialidad=Física`| Filtrar por especialidad          | 200, 401          |
| GET    | `/api/profesores/{id}`               | Consultar por ID                  | 200, 401, 404     |

### Ejemplo de cuerpo (POST)

```json
{
  "nombre": "Ana",
  "apellido": "López",
  "correo": "ana@correo.com",
  "especialidad": "Matemática",
  "estado": "ACTIVO"
}
```

- `nombre`, `apellido`: obligatorios (no vacíos).
- `correo`: obligatorio y con formato válido.
- `estado`: opcional; por defecto `ACTIVO`.
- El filtro por especialidad no distingue mayúsculas de minúsculas.

## Estructura

- `controller`: endpoints REST.
- `service`: lógica de negocio.
- `model`: entidad `Profesor` con validaciones.
- `repository`: almacenamiento en `ArrayList`.
- `exception`: excepción 404 y manejo centralizado de errores.
- `config`: filtro de API Key.

## Pruebas

```bash
mvn test
```

- `ProfesorServiceTest`: crear, obtener existente y obtener inexistente (JUnit 5 + Mockito).
- `ProfesorApiTest`: API Key (401), creación (201), validaciones (400) y ID inexistente (404).

Para Postman, importar `pruebapostmanejercicio1.json` (con la API corriendo). Las variables `baseUrl` y `apiKey` se pueden ajustar en la colección.
