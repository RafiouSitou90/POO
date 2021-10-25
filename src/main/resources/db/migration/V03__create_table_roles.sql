CREATE TABLE tab_roles
(
    id         BIGSERIAL    NOT NULL,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);

ALTER TABLE tab_roles
    ADD CONSTRAINT role_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT role_name_unique UNIQUE (name);
