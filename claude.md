# LABORATORIO VII – REQUERIMIENTOS E INSTRUCCIONES DEL PROYECTO

## 1. CONTEXTO GENERAL

Este proyecto corresponde al Laboratorio VII de programación, cuyo objetivo es reforzar los conocimientos adquiridos en el Módulo III mediante el desarrollo de tres APIs REST utilizando Java y Spring Boot.

El laboratorio consiste en desarrollar tres sistemas independientes para administrar profesores, proveedores y empleados.

El desarrollo debe cumplir todos los requisitos establecidos por el docente y mantener una estructura organizada, código legible y buenas prácticas de programación.

Este documento funciona como guía principal para cualquier asistente de programación (Claude Code, Codex u otros) que participe en el desarrollo del proyecto.

---

## 2. OBJETIVO PRINCIPAL

Desarrollar tres APIs REST independientes utilizando Java y Spring Boot, implementando:

- Arquitectura organizada por capas.
- Almacenamiento de datos mediante listas en memoria.
- Operaciones de creación y consulta.
- Filtros por especialidad o categoría.
- Validaciones de datos.
- Manejo de excepciones.
- Códigos de estado HTTP adecuados.
- Pruebas unitarias.
- Protección de endpoints mediante API Key.

El código debe ser funcional, sencillo de comprender y apropiado para un estudiante de Ingeniería en Sistemas.

---

## 3. ESTRUCTURA GENERAL DEL REPOSITORIO

El proyecto debe mantener la siguiente organización:

LABORATORIO_VII/
│
├── Ejercicio1_Profesores/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   │       └── java/
│   ├── pom.xml
│   ├── README.md
│   └── .gitignore
│
├── Ejercicio2_Proveedores/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   │       └── java/
│   ├── pom.xml
│   ├── README.md
│   └── .gitignore
│
└── Ejercicio3_Empleados/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   └── resources/
    │   └── test/
    │       └── java/
    ├── pom.xml
    ├── README.md
    └── .gitignore

Cada ejercicio debe ser un proyecto Spring Boot independiente, con su propia configuración, dependencias y pruebas.

---

## 4. TECNOLOGÍAS

Utilizar las siguientes tecnologías:

- Lenguaje: Java.
- Framework: Spring Boot.
- Gestión de dependencias: Maven.
- API: REST.
- Almacenamiento: ArrayList o listas en memoria.
- Pruebas: JUnit 5 y Mockito cuando corresponda.
- Validaciones: Jakarta Bean Validation.
- Herramientas de pruebas HTTP: Postman.
- Control de versiones: Git Bash.
- Repositorio remoto: GitHub.
- Editor: Visual Studio Code.

Antes de agregar dependencias, verificar la compatibilidad entre la versión de Java y Spring Boot.

No incorporar bases de datos, Docker, servicios externos ni tecnologías que no sean necesarias para cumplir el laboratorio. (Todo se probará en postman)

---

# 5. EJERCICIO 1: API DE PROFESORES

## Objetivo

Desarrollar una API REST para administrar profesores.

## Modelo Profesor

Debe contener los siguientes atributos:

- id
- nombre
- apellido
- correo
- especialidad
- estado

## Funcionalidades obligatorias

1. Crear un profesor.
2. Consultar todos los profesores.
3. Consultar profesores filtrando por especialidad.
4. Consultar un profesor por ID.

## Validaciones

- El nombre no puede estar vacío.
- El apellido no puede estar vacío.
- El correo debe tener un formato válido.
- Los datos deben validarse antes de registrar un profesor.

## Manejo de excepciones

Cuando se consulte un profesor mediante un ID inexistente, la API debe responder:

HTTP 404 NOT FOUND.

## Pruebas unitarias obligatorias

- Crear un profesor correctamente.
- Obtener un profesor existente.
- Intentar obtener un profesor inexistente.

## Seguridad

Todos los endpoints deben estar protegidos mediante una API Key.

La API debe validar la clave antes de permitir el acceso a cualquier endpoint protegido.

Las solicitudes sin clave o con una clave incorrecta deben rechazarse mediante HTTP 401 UNAUTHORIZED.

---

# 6. EJERCICIO 2: API DE PROVEEDORES

## Objetivo

Desarrollar una API REST para administrar proveedores.

## Modelo Proveedor

Debe contener los siguientes atributos:

- id
- nombre
- apellido
- correo
- categoria
- estado

## Funcionalidades obligatorias

1. Crear un proveedor.
2. Consultar todos los proveedores.
3. Consultar proveedores filtrando por categoría.
4. Consultar un proveedor por ID.

## Validaciones

- El nombre no puede estar vacío.
- El apellido no puede estar vacío.
- El correo debe tener un formato válido.
- Los datos deben validarse antes de registrar un proveedor.

## Manejo de excepciones

Cuando se consulte un proveedor mediante un ID inexistente, la API debe responder:

HTTP 404 NOT FOUND.

## Pruebas unitarias obligatorias

- Crear un proveedor correctamente.
- Obtener un proveedor existente.
- Intentar obtener un proveedor inexistente.

## Seguridad

Todos los endpoints deben estar protegidos mediante una API Key.

Las solicitudes sin clave o con una clave incorrecta deben rechazarse mediante HTTP 401 UNAUTHORIZED.

---

# 7. EJERCICIO 3: API DE EMPLEADOS

## Objetivo

Desarrollar una API REST para administrar empleados.

## Modelo Empleado

Debe contener los siguientes atributos:

- id
- nombre
- apellido
- correo
- especialidad
- estado

## Funcionalidades obligatorias

1. Crear un empleado.
2. Consultar todos los empleados.
3. Consultar empleados filtrando por especialidad.
4. Consultar un empleado por ID.

## Validaciones

- El nombre no puede estar vacío.
- El apellido no puede estar vacío.
- El correo debe tener un formato válido.
- Los datos deben validarse antes de registrar un empleado.

## Manejo de excepciones

Cuando se consulte un empleado mediante un ID inexistente, la API debe responder:

HTTP 404 NOT FOUND.

## Pruebas unitarias obligatorias

- Crear un empleado correctamente.
- Obtener un empleado existente.
- Intentar obtener un empleado inexistente.

## Seguridad

Todos los endpoints deben estar protegidos mediante una API Key.

Las solicitudes sin clave o con una clave incorrecta deben rechazarse mediante HTTP 401 UNAUTHORIZED.

---

# 8. ARQUITECTURA Y ORGANIZACIÓN DEL CÓDIGO

Cada proyecto debe implementar una arquitectura por capas.

## Paquetes requeridos

- controller: recibe las solicitudes HTTP y devuelve las respuestas.
- service: contiene la lógica de negocio.
- model: contiene las entidades o modelos de datos.
- repository: administra los datos almacenados en memoria.
- exception: contiene las excepciones personalizadas y su manejo.
- config: contiene las configuraciones de seguridad y API Key.

## Responsabilidades

### Controller

- Definir los endpoints.
- Recibir los datos enviados por el cliente.
- Delegar las operaciones al servicio.
- Retornar respuestas HTTP adecuadas.

### Service

- Implementar la lógica de negocio.
- Validar las operaciones necesarias.
- Coordinar el acceso al repositorio.

### Repository

- Almacenar los registros en memoria mediante listas.
- Generar identificadores únicos.
- Buscar registros por ID.
- Consultar todos los registros.
- Filtrar por especialidad o categoría.

### Model

- Representar los datos de cada entidad.
- Definir atributos, constructores y métodos necesarios.

### Exception

- Implementar el manejo centralizado de errores.
- Controlar los casos de registros inexistentes.
- Retornar respuestas claras y apropiadas.

### Config

- Implementar la validación de API Key.
- Proteger los endpoints.
- Mantener la clave configurable y evitar exponer secretos innecesariamente.

---

# 9. REQUISITOS TÉCNICOS IMPORTANTES

## Almacenamiento

Los datos deben almacenarse en listas en memoria.

No utilizar MySQL, PostgreSQL, MongoDB ni ninguna otra base de datos.

Los registros deben conservarse mientras la aplicación permanezca ejecutándose. No es necesario implementar persistencia después de reiniciar.

## API REST

Utilizar los métodos HTTP adecuados:

- POST: crear registros.
- GET: consultar registros.
- GET con parámetros: filtrar registros.
- GET con ID: consultar un registro específico.

## Códigos HTTP

Utilizar como mínimo:

- 200 OK: consulta exitosa.
- 201 CREATED: registro creado.
- 400 BAD REQUEST: datos inválidos.
- 401 UNAUTHORIZED: API Key ausente o incorrecta.
- 404 NOT FOUND: registro inexistente.
- 500 INTERNAL SERVER ERROR: error interno no controlado.

## API Key

Implementar un mecanismo de protección mediante un filtro o interceptor de Spring.

La API Key debe recibirse mediante un encabezado HTTP, por ejemplo:

X-API-KEY: mi-clave-secreta

La clave debe configurarse mediante propiedades de la aplicación o variables de entorno.

No almacenar claves reales directamente en GitHub.

---

# 10. BUENAS PRÁCTICAS DE DESARROLLO

Todo el desarrollo debe respetar las siguientes instrucciones:

1. Utilizar nombres claros para clases, variables y métodos.
2. Mantener una estructura organizada.
3. Evitar duplicar código innecesariamente.
4. Utilizar principios básicos de programación orientada a objetos.
5. Evitar métodos excesivamente largos.
6. Mantener separadas las responsabilidades de cada capa.
7. Evitar dependencias innecesarias.
8. No implementar funcionalidades que no solicita el docente.
9. Mantener el código sencillo y fácil de explicar.
10. Utilizar comentarios únicamente cuando aporten claridad.

IMPORTANTE:

El proyecto debe tener calidad académica y profesional, pero no debe utilizar arquitecturas excesivamente complejas ni soluciones avanzadas que dificulten comprender el funcionamiento del código.

Priorizar soluciones claras, directas y funcionales.

---

# 11. VALIDACIÓN Y PRUEBAS

Cada ejercicio debe ejecutarse y probarse individualmente.

Utilizaré Postman para verificar asi que dentro de cada carpeta de ejrcicio añadir un prubapostamjerciciox.json:

- Creación de registros.
- Consulta de todos los registros.
- Filtrado por especialidad o categoría.
- Consulta por ID existente.
- Consulta por ID inexistente.
- Validación de campos vacíos.
- Validación de correos inválidos.
- Acceso con API Key correcta.
- Acceso sin API Key.
- Acceso con API Key incorrecta.

También se deben ejecutar las pruebas unitarias.



---


# 12. INSTRUCCIONES PARA CLAUDE CODE Y CODEX

Cualquier asistente de programación que trabaje en este repositorio debe cumplir estas reglas:

1. Leer este documento antes de comenzar cualquier modificación.
2. Inspeccionar la estructura y los archivos existentes.
3. Identificar qué ejercicio se está desarrollando.
4. No modificar otros ejercicios sin autorización.
5. Respetar los requisitos establecidos por el docente.
6. No eliminar funcionalidades que ya estén funcionando.
7. No sobrescribir configuraciones sin revisar su contenido.
8. No agregar tecnologías innecesarias.
9. Ejecutar las pruebas después de realizar cambios.
10. Informar los archivos creados o modificados.
11. Informar los errores encontrados y las soluciones aplicadas.
12. No afirmar que una funcionalidad funciona si no se ha comprobado.

# 13. CRITERIO FINAL DE ÉXITO

El Laboratorio VII se considerará terminado cuando:

- Existan los tres proyectos Spring Boot independientes.
- Los tres proyectos compilen y se ejecuten correctamente.
- Cada API cumpla todas las operaciones solicitadas.
- Los datos se almacenen en listas en memoria.
- Las validaciones funcionen.
- Los errores se manejen correctamente.
- Los endpoints estén protegidos mediante API Key.
- Las pruebas unitarias estén implementadas y aprobadas.
- El repositorio de GitHub tenga un historial de commits.
- Cada ejercicio tenga su documentación.
- Exista evidencia del funcionamiento de los tres ejercicios.
- El PDF final esté preparado conforme a las instrucciones del docente.

**Prioridad absoluta: cumplir todos los requisitos del laboratorio con código funcional, organizado, sencillo y fácil de comprender y defender académicamente.**