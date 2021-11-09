CREATE TABLE tab_post_tags
(
    post_id BIGINT NOT NULL,
    tag_id  BIGINT NOT NULL
);

ALTER TABLE tab_post_tags
    ADD CONSTRAINT post_tags_id_pk PRIMARY KEY (post_id, tag_id),
    ADD CONSTRAINT post_tags_tag_id_fk FOREIGN KEY (tag_id) REFERENCES tab_tags (id),
    ADD CONSTRAINT post_tags_post_id_fk FOREIGN KEY (post_id) REFERENCES tab_posts (id);
