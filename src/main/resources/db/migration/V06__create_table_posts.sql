CREATE TABLE tab_posts
(
    id           BIGINT       NOT NULL,
    title        VARCHAR(100) NOT NULL,
    slug         VARCHAR(255) NOT NULL,
    content      TEXT         NOT NULL,
    image_url    VARCHAR(255) NOT NULL,
    status       VARCHAR(30)  NOT NULL,
    published_at TIMESTAMP    NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP,
    category_id  BIGINT       NOT NULL,
    user_id      BIGINT       NOT NULL
);

ALTER TABLE tab_posts
    ADD CONSTRAINT post_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT post_tittle_unique UNIQUE (title),
    ADD CONSTRAINT post_slug_unique UNIQUE (slug),
    ADD CONSTRAINT post_category_id_fk FOREIGN KEY (category_id) REFERENCES tab_categories (id),
    ADD CONSTRAINT post_user_id_fk FOREIGN KEY (user_id) REFERENCES tab_users (id);