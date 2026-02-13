# PSLTasks API

PSLTasks es una API REST para la gestión de proyectos y tareas, inspirada en un sistema tipo Trello simplificado. 
Permite manejar usuarios, proyectos y tareas, desarrollada con **Spring Boot** y **PostgreSQL**, con entorno de pruebas **H2**, documentación con **Swagger** y pruebas unitarias con **JUnit**.

## Tecnologías

- **Backend:** Java 21 (LTS), Spring Boot, Spring Web, Spring Data JPA
- **Base de datos:** PostgreSQL (entorno producción), H2 (entorno desarrollo/testing)
- **Documentación API:** Swagger / OpenAPI
- **Pruebas:** JUnit 5
- **Build Tool:** Gradle
- **Control de versiones:** Git


## Objetivo del Proyecto

Demostrar:
* Diseño limpio en aplicaciones Spring Boot
* Separación de responsabilidades
* Configuración por perfiles
* Uso correcto de JPA y relaciones
* Documentación profesional de API

## Stack Tecnológico

- Java 21 (LTS)
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL (entorno producción)
- H2 Database (entorno desarrollo/testing)
- Swagger / OpenAPI
- JUnit 5
- Gradle

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
│   │   ├── security     # (Preparado para futura implementación JWT)
│   │   ├── service      # Lógica de negocio
│   │   └── util         # Utilidades
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
    - username: String
    - email: String
    - password: String
    - roles: Set<Role>

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
    - assignedTo: User

## Endpoints Principales

| Método | Ruta                     | Descripción                  |
|--------|--------------------------|-----------------------------|
| GET    | /api/users               | Listar usuarios             |
| POST   | /api/users               | Crear usuario               |
| GET    | /api/projects            | Listar proyectos            |
| POST   | /api/projects            | Crear proyecto              |
| GET    | /api/projects/{id}       | Obtener proyecto por ID     |
| GET    | /api/tasks               | Listar tareas               |
| POST   | /api/tasks               | Crear tarea                 |
| PUT    | /api/tasks/{id}          | Actualizar tarea            |
| DELETE | /api/tasks/{id}          | Eliminar tarea              |




## Configuración y Ejecución

El proyecto utiliza perfiles de Spring para separar entornos:

- `dev` → Base de datos en memoria H2
- `prod` → PostgreSQL


### Entorno Desarrollo (H2)

El perfil `dev` utiliza una base de datos en memoria H2, ideal para pruebas rápidas y desarrollo local.

#### Desde un IDE (IntelliJ recomendado)

1. Abrir el proyecto.
2. Esperar a que Gradle descargue las dependencias.
3. Configurar el perfil activo como `dev` (si no está definido por defecto).
4. Ejecutar la clase principal `PslTasksApplication`.

#### Desde línea de comandos

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
````



### Entorno Producción (PostgreSQL)

Configurar `application-prod.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/app_bd
spring.datasource.username=app_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

#### Ejecutar con perfil producción

```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

---

## Documentación API

Swagger disponible en:

```
http://localhost:8080/swagger-ui.html
```

---

## Testing

Las pruebas unitarias están implementadas con JUnit 5.

Ejecutar tests:

```bash
./gradlew test
```



## Posibles mejoras

* Implementación completa de seguridad con JWT
* Paginación y filtros avanzados
* Tests de integración con @SpringBootTest
* Dockerización del proyecto
* CI/CD



