CREATE TABLE tab_comments
(
    id           BIGSERIAL NOT NULL,
    content      TEXT      NOT NULL,
    published_at TIMESTAMP,
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP,
    post_id      BIGINT    NOT NULL,
    user_id      BIGINT    NOT NULL
);

ALTER TABLE tab_comments
    ADD CONSTRAINT comment_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT comment_post_id_fk
        FOREIGN KEY (post_id)
            REFERENCES tab_posts (id),
    ADD CONSTRAINT comment_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES tab_users (id);
