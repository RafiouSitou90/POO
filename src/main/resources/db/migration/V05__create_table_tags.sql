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
-- END OF SCHEMA DEFINITION


-- INSERT
INSERT INTO tab_tags(name, created_at)
SELECT 'Tag-Name-' || a.n, (NOW() - (RANDOM() * INTERVAL '30 days'))
FROM GENERATE_SERIES(1, 25) AS a(n);

