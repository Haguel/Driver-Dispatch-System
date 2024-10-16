CREATE TABLE IF NOT EXISTS role (
    id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS app_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS app_user_role (
    id BIGSERIAL PRIMARY KEY,
    app_user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

INSERT INTO role (role_name) VALUES ('ROLE_DISPATCHER'), ('ROLE_ADMIN');
