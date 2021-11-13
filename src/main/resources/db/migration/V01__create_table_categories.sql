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
-- END OF SCHEMA DEFINITION


-- INSERT
INSERT INTO tab_categories (name, slug, created_at)
VALUES
       ('Music', 'music', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Fashion', 'fashion', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Car', 'car', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Real Estate', 'real-estate', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Beauty', 'beauty', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Travel', 'travel', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Design', 'design', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Food', 'food', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Weeding', 'weeding', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Movie', 'movie', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Photography', 'photography', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Law', 'law', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Health', 'health', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Green', 'green', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Technology', 'technology', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('SEO', 'seo', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('History', 'history', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Marketing', 'marketing', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Lifestyle', 'lifestyle', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('University', 'university', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Dog', 'dog', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Money', 'money', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Business', 'business', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Fitness', 'fitness', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Education', 'education', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Science', 'science', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Shopping', 'shopping', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Entertainment', 'entertainment', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Sports', 'sports', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Cat', 'cat', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Social Media', 'social-media', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Wine', 'wine', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Celebrity Gossip', 'celebrity-gossip', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('DIY', 'diy', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Nature', 'nature', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Gaming', 'gaming', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Pet', 'pet', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Finance', 'finance', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Political', 'political', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Career', 'career', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Parenting', 'parenting', NOW() - (RANDOM() * INTERVAL '30 days')),
       ('Economics', 'economics', NOW() - (RANDOM() * INTERVAL '30 days'));
