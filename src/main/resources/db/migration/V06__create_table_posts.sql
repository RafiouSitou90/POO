CREATE TABLE tab_posts
(
    id           BIGSERIAL    NOT NULL,
    title        VARCHAR(100) NOT NULL,
    slug         VARCHAR(255) NOT NULL,
    content      TEXT         NOT NULL,
    image_url    VARCHAR(255) NOT NULL,
    status       VARCHAR(30)  NOT NULL,
    published_at TIMESTAMP,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP,
    category_id  BIGINT       NOT NULL,
    user_id      BIGINT       NOT NULL
);

ALTER TABLE tab_posts
    ADD CONSTRAINT post_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT post_title_unique UNIQUE (title),
    ADD CONSTRAINT post_slug_unique UNIQUE (slug),
    ADD CONSTRAINT post_category_id_fk
        FOREIGN KEY (category_id)
            REFERENCES tab_categories (id),
    ADD CONSTRAINT post_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES tab_users (id);
-- END OF SCHEMA DEFINITION


-- INSERT
INSERT INTO tab_posts (title, slug, content, image_url, status, published_at, created_at, category_id,
                       user_id)
SELECT 'Accusantium molestias nam perspiciatis quibusdam quo dolorum #1' || p.i,
       'accusantium-molestias-nam-perspiciatis-quibusdam-quo-dolorum-' || p.i,
       'Aperiam vitae ipsa explicabo rerum et distinctio. Corrupti ea rerum aut. Nisi nemo praesentium incidunt ' ||
       'quibusdam laboriosam assumenda quis #' || p.i,
       'https://lorempixel.com/g/1680/1050/transport/', 'PUBLISHED', NOW() - (RANDOM() * INTERVAL '1 year'),
       NOW() - ( INTERVAL '2 years'),
       1, 1
FROM GENERATE_SERIES(1, 10) AS p(i);

INSERT INTO tab_posts (title, slug, content, image_url, status, created_at, category_id,
                       user_id)
SELECT 'Accusantium molestias nam perspiciatis quibusdam quo dolorum #1' || p.i,
       'accusantium-molestias-nam-perspiciatis-quibusdam-quo-dolorum-' || p.i,
       'Aperiam vitae ipsa explicabo rerum et distinctio. Corrupti ea rerum aut. Nisi nemo praesentium incidunt ' ||
       'quibusdam laboriosam assumenda quis #' || p.i, 'https://lorempixel.com/g/1680/1050/transport/', 'DRAFT',
       NOW() - ( INTERVAL '2 years'), 2, 2
FROM GENERATE_SERIES(11, 20) AS p(i);

INSERT INTO tab_posts (title, slug, content, image_url, status, created_at, category_id,
                       user_id)
SELECT 'Accusantium molestias nam perspiciatis quibusdam quo dolorum #1' || p.i,
       'accusantium-molestias-nam-perspiciatis-quibusdam-quo-dolorum-' || p.i,
       'Aperiam vitae ipsa explicabo rerum et distinctio. Corrupti ea rerum aut. Nisi nemo praesentium incidunt ' ||
       'quibusdam laboriosam assumenda quis #' || p.i,
       'https://lorempixel.com/g/1680/1050/transport/', 'DRAFT', NOW() - ( INTERVAL '2 years'), 3, 3
FROM GENERATE_SERIES(21, 30) AS p(i);

INSERT INTO tab_posts (title, slug, content, image_url, status, created_at, category_id,
                       user_id)
SELECT 'Accusantium molestias nam perspiciatis quibusdam quo dolorum #1' || p.i,
       'accusantium-molestias-nam-perspiciatis-quibusdam-quo-dolorum-' || p.i,
       'Aperiam vitae ipsa explicabo rerum et distinctio. Corrupti ea rerum aut. Nisi nemo praesentium incidunt ' ||
       'quibusdam laboriosam assumenda quis #' || p.i,
       'https://lorempixel.com/g/1680/1050/transport/', 'DRAFT', NOW() - ( INTERVAL '2 years'), 3, 5
FROM GENERATE_SERIES(31, 40) AS p(i);
