CREATE TABLE tab_users
(
    id         BIGSERIAL    NOT NULL,
    username   VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    is_enabled BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);

ALTER TABLE tab_users
    ADD CONSTRAINT user_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT user_username_unique UNIQUE (username),
    ADD CONSTRAINT user_email_unique UNIQUE (email);
