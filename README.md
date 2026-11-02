<div align="center">

# Laboratorio VII

### APIs REST para la gestión de profesores, proveedores y empleados

Proyecto académico compuesto por tres aplicaciones independientes construidas con **Java 17** y **Spring Boot**. Cada API permite registrar y consultar información, validar solicitudes y proteger sus rutas con una clave de acceso.

<br>

![Java 17](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-6DB33F?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)

</div>

---

## Contenido

- [Descripción](#descripción)
- [Aplicaciones](#aplicaciones)
- [Tecnologías](#tecnologías)
- [Requisitos](#requisitos)
- [Instalación y ejecución](#instalación-y-ejecución)
- [Uso de las APIs](#uso-de-las-apis)
- [Estructura del repositorio](#estructura-del-repositorio)
- [Pruebas y Postman](#pruebas-y-postman)
- [Notas](#notas)

## Descripción

Este repositorio agrupa tres ejercicios de desarrollo backend. Cada ejercicio se ejecuta por separado y expone una API REST para una entidad distinta:

| Aplicación | Recurso | Puerto | Ruta base |
|---|---|---:|---|
| Ejercicio 1 | Profesores | `8081` | `/api/profesores` |
| Ejercicio 2 | Proveedores | `8082` | `/api/proveedores` |
| Ejercicio 3 | Empleados | `8083` | `/api/empleados` |

Las aplicaciones siguen una organización por responsabilidades —controlador, servicio, repositorio y modelo— e incorporan validación de datos y manejo de errores. Los registros se conservan en memoria durante la ejecución.

## Aplicaciones

<details>
<summary><strong>Ejercicio 1 · API de Profesores</strong></summary>

Administra profesores con nombre, apellido, correo, especialidad y estado. Permite listar, filtrar por especialidad y consultar por identificador.

- Proyecto: [`LABORATORIO_VII/Ejercicio1_Profesores`](LABORATORIO_VII/Ejercicio1_Profesores)
- Documentación específica: [`README.md`](LABORATORIO_VII/Ejercicio1_Profesores/README.md)
- Colección Postman: [`pruebapostmanejercicio1.json`](LABORATORIO_VII/Ejercicio1_Profesores/pruebapostmanejercicio1.json)
- Clave local predeterminada: `clave-desarrollo-profesores`

</details>

<details>
<summary><strong>Ejercicio 2 · API de Proveedores</strong></summary>

Administra proveedores con nombre, apellido, correo, categoría y estado. Permite listar, filtrar por categoría y consultar por identificador.

- Proyecto: [`LABORATORIO_VII/Ejercicio2_Proveedores`](LABORATORIO_VII/Ejercicio2_Proveedores)
- Documentación específica: [`README.md`](LABORATORIO_VII/Ejercicio2_Proveedores/README.md)
- Colección Postman: [`pruebapostmanejercicio2.json`](LABORATORIO_VII/Ejercicio2_Proveedores/pruebapostmanejercicio2.json)
- Clave local predeterminada: `clave-proveedores-local`

</details>

<details>
<summary><strong>Ejercicio 3 · API de Empleados</strong></summary>

Administra empleados con nombre, apellido, correo, especialidad y estado. Permite listar, filtrar por especialidad y consultar por identificador.

- Proyecto: [`LABORATORIO_VII/Ejercicio3_Empleados`](LABORATORIO_VII/Ejercicio3_Empleados)
- Documentación específica: [`README.md`](LABORATORIO_VII/Ejercicio3_Empleados/README.md)
- Colección Postman: [`pruebapostmanejercicio3.json`](LABORATORIO_VII/Ejercicio3_Empleados/pruebapostmanejercicio3.json)
- Clave local predeterminada: `clave-empleados-local`

</details>

## Tecnologías

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Web** para los controladores REST
- **Spring Validation** para validar los datos de entrada
- **Maven** para dependencias y compilación
- **JUnit 5 y Mockito** (Spring Boot Starter Test) para pruebas
- **Postman** para ejecutar solicitudes de ejemplo

## Requisitos

- JDK 17 o superior
- Maven 3.6 o superior
- Postman, opcional, para importar las colecciones de solicitudes

Comprueba las instalaciones con:

```bash
java -version
mvn -version
```

## Instalación y ejecución

Clona el repositorio y entra en la carpeta de la aplicación que quieras iniciar:

```bash
git clone <URL_DEL_REPOSITORIO>
cd <CARPETA_DEL_REPOSITORIO>/LABORATORIO_VII/Ejercicio1_Profesores
mvn spring-boot:run
```

Para iniciar los otros ejercicios, cambia la última parte de la ruta:

```text
Ejercicio2_Proveedores
Ejercicio3_Empleados
```

Cada aplicación queda disponible en `http://localhost` con el puerto indicado en la tabla de [aplicaciones](#descripción). Como usan puertos distintos, pueden ejecutarse simultáneamente.

### Configurar la clave de acceso

Todas las solicitudes protegidas requieren el encabezado `X-API-KEY`. Cada aplicación incluye una clave local predeterminada para facilitar la ejecución. Para usar una clave propia, define la variable de entorno `API_KEY` antes de iniciar la aplicación.

**PowerShell:**

```powershell
$env:API_KEY = "mi-clave-segura"
mvn spring-boot:run
```

**Linux o macOS:**

```bash
export API_KEY="mi-clave-segura"
mvn spring-boot:run
```

La variable se configura en la terminal y aplica a la aplicación que se inicia desde ella. Las claves predeterminadas son solo para desarrollo local.

## Uso de las APIs

Las tres APIs comparten operaciones equivalentes. Cambian el nombre del recurso y el campo de filtro según la entidad.

| Método | Ruta | Descripción | Respuesta habitual |
|---|---|---|---|
| `POST` | `/api/{recurso}` | Crea un registro | `201 Created` |
| `GET` | `/api/{recurso}` | Lista los registros | `200 OK` |
| `GET` | `/api/{recurso}?{filtro}={valor}` | Filtra los registros | `200 OK` |
| `GET` | `/api/{recurso}/{id}` | Busca por identificador | `200 OK` o `404 Not Found` |

Envía `X-API-KEY` en cada solicitud. Si falta o no coincide con la clave configurada, la respuesta es `401 Unauthorized`. Los datos inválidos producen `400 Bad Request`.

### Ejemplo: crear un profesor

```http
POST http://localhost:8081/api/profesores
Content-Type: application/json
X-API-KEY: clave-desarrollo-profesores
```

```json
{
  "nombre": "Ana",
  "apellido": "López",
  "correo": "ana@correo.com",
  "especialidad": "Matemática",
  "estado": true
}
```

Los campos de texto requeridos dependen del recurso:

| Recurso | Campo de filtro | Campos de texto requeridos |
|---|---|---|
| Profesores | `especialidad` | `nombre`, `apellido`, `correo`, `especialidad` |
| Proveedores | `categoria` | `nombre`, `apellido`, `correo`, `categoria` |
| Empleados | `especialidad` | `nombre`, `apellido`, `correo`, `especialidad` |

El correo debe tener formato válido. `estado` es booleano y, si se omite, su valor predeterminado es `true`. Los filtros no distinguen entre mayúsculas y minúsculas.

### Ejemplos de consulta

```http
GET http://localhost:8081/api/profesores
X-API-KEY: clave-desarrollo-profesores
```

```http
GET http://localhost:8082/api/proveedores?categoria=Limpieza
X-API-KEY: clave-proveedores-local
```

```http
GET http://localhost:8083/api/empleados/1
X-API-KEY: clave-empleados-local
```

## Estructura del repositorio

```text
LABORATORIO_VII/
├── Ejercicio1_Profesores/
│   ├── src/main/java/       # API, lógica, modelo y configuración
│   ├── src/test/java/       # Pruebas automatizadas
│   ├── pom.xml
│   └── pruebapostmanejercicio1.json
├── Ejercicio2_Proveedores/
│   ├── src/main/java/
│   ├── src/test/java/
│   ├── pom.xml
│   └── pruebapostmanejercicio2.json
└── Ejercicio3_Empleados/
    ├── src/main/java/
    ├── src/test/java/
    ├── pom.xml
    └── pruebapostmanejercicio3.json
```

En cada aplicación, los paquetes separan las funciones principales:

- `controller`: rutas y respuestas HTTP.
- `service`: lógica de negocio.
- `repository`: acceso y almacenamiento de registros.
- `model`: estructura y validaciones de la entidad.
- `config`: filtro de autenticación por API Key.
- `exception`: errores y respuestas centralizadas.

## Pruebas y Postman

Para ejecutar las pruebas de un ejercicio, entra en su carpeta y ejecuta:

```bash
mvn test
```

Importa el archivo `pruebapostmanejercicioN.json` correspondiente en Postman. Inicia primero la API asociada y envía la clave requerida en `X-API-KEY`. Las colecciones incluyen solicitudes para crear y consultar registros, filtrar, revisar validaciones y comprobar respuestas de autenticación y de identificadores inexistentes.

## Notas

- Cada ejercicio es una aplicación Maven independiente y tiene su propio `pom.xml`.
- El almacenamiento es en memoria; los datos se reinician al detener la aplicación.
- Las claves predeterminadas están pensadas para pruebas locales. Configura `API_KEY` con un valor propio en otros entornos.

<div align="center">
  <sub>Laboratorio VII · Desarrollo de APIs REST con Spring Boot</sub>
</div>
