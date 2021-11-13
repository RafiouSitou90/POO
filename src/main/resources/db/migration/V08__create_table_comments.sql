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
-- END OF SCHEMA DEFINITION


-- INSERT
INSERT INTO tab_comments (content, published_at, created_at, post_id, user_id)
SELECT 'Voluptatem soluta perferendis facere. Aliquam odio veniam libero autem cupiditate itaque. A officiis id ' ||
       'occaecati voluptatem ea. Dolorum aliquid sunt ipsum qui. #' || c.n,
       (NOW() - (RANDOM() * INTERVAL '6 months')), (NOW() - (RANDOM() * INTERVAL '6 months')), 1, 1
FROM GENERATE_SERIES(1, 50) AS c(n);

INSERT INTO tab_comments (content, published_at, created_at, post_id, user_id)
SELECT 'Voluptatem soluta perferendis facere. Aliquam odio veniam libero autem cupiditate itaque. A officiis id ' ||
       'occaecati voluptatem ea. Dolorum aliquid sunt ipsum qui. #' || c.n,
       (NOW() - (RANDOM() * INTERVAL '6 months')), (NOW() - (RANDOM() * INTERVAL '6 months')), 1, 2
FROM GENERATE_SERIES(51, 75) AS c(n);

INSERT INTO tab_comments (content, published_at, created_at, post_id, user_id)
SELECT 'Voluptatem soluta perferendis facere. Aliquam odio veniam libero autem cupiditate itaque. A officiis id ' ||
       'occaecati voluptatem ea. Dolorum aliquid sunt ipsum qui. #' || c.n,
       (NOW() - (RANDOM() * INTERVAL '6 months')), (NOW() - (RANDOM() * INTERVAL '6 months')), 1, 3
FROM GENERATE_SERIES(76, 100) AS c(n);
/*
INSERT INTO tab_comments (content, published_at, created_at, post_id, user_id)
VALUES
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0),
       ('', '', NOW(), 0, 0);
*/