CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Insert Roles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Insert Users
INSERT INTO users (username, password, email) VALUES ('user', '{bcrypt}123', '');
INSERT INTO users (username, password, email) VALUES ('admin', '{bcrypt}586391', 'mkizhevsk@gmail.com');

-- Associate Users with Roles
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- john_doe as ROLE_USER
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- jane_smith as ROLE_ADMIN


