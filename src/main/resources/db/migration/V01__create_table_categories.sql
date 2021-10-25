CREATE TABLE tab_categories
(
    id         BIGSERIAL    NOT NULL,
    name       VARCHAR(100) NOT NULL,
    slug       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);

ALTER TABLE tab_categories
    ADD CONSTRAINT category_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT category_name_unique UNIQUE (name),
    ADD CONSTRAINT category_slug_unique UNIQUE (slug);