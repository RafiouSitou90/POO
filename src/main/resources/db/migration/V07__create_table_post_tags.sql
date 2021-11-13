CREATE TABLE tab_post_tags
(
    post_id BIGINT NOT NULL,
    tag_id  BIGINT NOT NULL
);

ALTER TABLE tab_post_tags
    ADD CONSTRAINT post_tags_id_pk PRIMARY KEY (post_id, tag_id),
    ADD CONSTRAINT post_tags_post_id_fk
        FOREIGN KEY (post_id)
            REFERENCES tab_posts (id),
    ADD CONSTRAINT post_tags_tag_id_fk
        FOREIGN KEY (tag_id)
            REFERENCES tab_tags (id);
-- END OF SCHEMA DEFINITION



-- INSERT
INSERT INTO tab_post_tags (post_id, tag_id)
SELECT 1, pt.n
FROM GENERATE_SERIES(1, 5) AS pt(n);

INSERT INTO tab_post_tags (post_id, tag_id)
SELECT 2, pt.n
FROM GENERATE_SERIES(6, 10) AS pt(n);

INSERT INTO tab_post_tags (post_id, tag_id)
SELECT 3, pt.n
FROM GENERATE_SERIES(11, 15) AS pt(n);

INSERT INTO tab_post_tags (post_id, tag_id)
SELECT 4, pt.n
FROM GENERATE_SERIES(16, 20) AS pt(n);

INSERT INTO tab_post_tags (post_id, tag_id)
SELECT 5, pt.n
FROM GENERATE_SERIES(21, 25) AS pt(n);
