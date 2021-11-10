CREATE TABLE tab_tags
(
    id         BIGSERIAL    NOT NULL,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);

ALTER TABLE tab_tags
    ADD CONSTRAINT tag_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT tag_name_unique UNIQUE (name);
