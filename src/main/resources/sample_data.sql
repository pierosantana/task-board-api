-- Tabla de usuarios
CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL
);

-- Tabla de proyectos
CREATE TABLE projects (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          description VARCHAR(255),
                          owner_id BIGINT,
                          FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- Tabla de tareas
CREATE TABLE tasks (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(100) NOT NULL,
                       description VARCHAR(255),
                       status VARCHAR(20),
                       project_id BIGINT,
                       FOREIGN KEY (project_id) REFERENCES projects(id)
);

-- Usuarios
INSERT INTO users (name, email) VALUES ('Piero Santana', 'piero@example.com');
INSERT INTO users (name, email) VALUES ('Ana López', 'ana@example.com');

-- Proyectos
INSERT INTO projects (name, description, owner_id) VALUES ('Proyecto Backend', 'Proyecto de ejemplo para backend', 1);
INSERT INTO projects (name, description, owner_id) VALUES ('Proyecto Frontend', 'Proyecto de ejemplo para frontend', 2);

-- Tareas
INSERT INTO tasks (title, description, status, project_id) VALUES ('Configurar DB', 'Configurar la base de datos H2', 'TODO', 1);
INSERT INTO tasks (title, description, status, project_id) VALUES ('Crear API', 'Desarrollar endpoints REST', 'IN_PROGRESS', 1);
INSERT INTO tasks (title, description, status, project_id) VALUES ('Documentación', 'Escribir documentación del proyecto', 'DONE', 1);

INSERT INTO tasks (title, description, status, project_id) VALUES ('Diseño UI', 'Crear mockups', 'TODO', 2);
INSERT INTO tasks (title, description, status, project_id) VALUES ('Implementar UI', 'Desarrollar componentes de interfaz', 'IN_PROGRESS', 2);
INSERT INTO tasks (title, description, status, project_id) VALUES ('Test UI', 'Probar la interfaz y corregir errores', 'DONE', 2);