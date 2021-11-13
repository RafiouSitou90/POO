CREATE TABLE tab_users
(
    id         BIGSERIAL    NOT NULL,
    username   VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    is_enabled BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);

ALTER TABLE tab_users
    ADD CONSTRAINT user_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT user_username_unique UNIQUE (username),
    ADD CONSTRAINT user_email_unique UNIQUE (email);
-- END OF SCHEMA DEFINITION


-- INSERT
INSERT INTO tab_users (username, email, password, first_name, last_name, is_enabled, created_at)
VALUES
       ('johndoe', 'johndoe@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'John', 'Doe', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('simple_user_1', 'simple_user_1@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'Simple', 'User 1', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('simple_user_2', 'simple_user_2@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'Simple', 'User 2', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('simple_user_3', 'simple_user_3@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'Simple', 'User 3', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('user_admin_1', 'user_admin_1@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'User', 'Admin 1', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('user_admin_2', 'user_admin_2@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'User', 'Admin 2', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('user_admin_3', 'user_admin_3@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'User', 'Admin 3', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('super_admin_1', 'super_admin_1@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'Super', 'Admin 1', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('super_admin_2', 'super_admin_2@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'Super', 'Admin 2', TRUE, NOW() - (RANDOM() * INTERVAL '30 days')),
       ('super_admin_3', 'super_admin_3@example.com',
        '$argon2id$v=19$m=4096,t=3,p=1$DtPrrGF4qCU8G4XOl9LUkA$pzVv0tnP0UStX5G7ou/+JURfCyYobrllicR8FaPOvOQ',
        'Super', 'Admin 3', TRUE, NOW() - (RANDOM() * INTERVAL '30 days'));
