# PSLTasks API

PSLTasks es una API REST sencilla para la gestión de proyectos y tareas, inspirada en un sistema tipo Trello. 
Permite manejar usuarios, proyectos y tareas, desarrollada con **Spring Boot** y **PostgreSQL**, con entorno de pruebas **H2**.

## Stack Tecnológico

- **Backend:** Java 21 (LTS), Spring Boot, Spring Web, Spring Data JPA
- **Base de datos:** PostgreSQL (entorno producción), H2 (entorno desarrollo)
- **Documentación API:** 
- **Pruebas:** 
- **Build Tool:** Gradle
- **Control de versiones:** Git


## Objetivo del Proyecto

Demostrar:
* Diseño limpio en aplicaciones Spring Boot
* Separación de responsabilidades
* Configuración por perfiles
* Uso correcto de JPA y relaciones
* Documentación profesional de API


## Arquitectura

```

src
├── main
│   ├── java/com/psltasks
│   │   ├── config       # Configuraciones de la aplicación
│   │   ├── controller   # Exposición de endpoints REST
│   │   ├── exception    # Manejo de errores
│   │   ├── model        # Entidades: User, Project, Task
│   │   ├── repository   # Acceso a datos con JPA
│   │   ├── service      # Lógica de negocio
│   └── resources
│       ├── application-dev.properties  # Entorno de desarrollo
│       ├── application-prod.properties # Entorno de producción
│       ├── application.properties
│       ├── static
│       └── templates
└── test
└── java/com/psltasks
└── PslTasksApplicationTests.java
```

## Modelo de datos

- **User**
    - id: Long
    - name: String
    - email: String
    - projects: List<projects>

- **Project**
    - id: Long
    - name: String
    - description: String
    - owner: User
    - tasks: List<Task>

- **Task**
    - id: Long
    - title: String
    - description: String
    - status: Enum (TODO, IN_PROGRESS, DONE)
    - project: Project

## Endpoints Principales

| Método  | Ruta                 | Descripción                 |
|---------|----------------------|-----------------------------|
| GET     | /users               | Listar usuarios             |
| GET     | /users/{id}          | Obtener usuario por ID      |
| POST    | /users               | Crear usuario               |
| PUT     | /users/{id}          | Actualizar usuario por ID   |
| DELETE  | /users/{id}          | Eliminar usuario por ID     |
| GET     | /projects            | Listar proyectos            |
| GET     | /projects/{id}       | Obtener proyecto por ID     |
| POST    | /projects            | Crear proyecto              |
| PUT     | /projects/{id}       | Actualizar proyecto por ID  |
| GET     | /tasks               | Listar tareas               |
| GET     | /tasks{id}           | Listar tareas por ID        |
| POST    | /tasks               | Crear tarea                 |
| PUT     | /tasks/{id}          | Actualizar tarea por ID     |
| DELETE  | /tasks/{id}          | Eliminar tarea por ID       |



## Configuración y Ejecución

El proyecto utiliza perfiles de Spring para separar entornos:

- `dev` → Base de datos en memoria H2
- `prod` → PostgreSQL


### Entorno Desarrollo (H2)

El perfil `dev` utiliza una base de datos en memoria H2, ideal para pruebas rápidas y desarrollo local.


### Entorno Producción (PostgreSQL)

Configurar `application-prod.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/app_bd
spring.datasource.username=app_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
### Ejecución
0. Clonar repositorio
```bash
git clone <https://github.com/pierosantana/psltasks.git>
```
1. Abrir el proyecto desde un IDE (IntelliJ recomendado)
2. Esperar a que Gradle descargue las dependencias
3. Configurar el perfil activo como `dev` o `prod` (si no está definido por defecto)
4. Ejecutar la clase principal `PslTasksApplication`


## Proximas mejoras

* Documentación API
* Testing JUnit 5



