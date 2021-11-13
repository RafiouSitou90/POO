CREATE TABLE IF NOT EXISTS tab_user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

ALTER TABLE tab_user_roles
    ADD CONSTRAINT user_roles_id_pk PRIMARY KEY (user_id, role_id),
    ADD CONSTRAINT user_roles_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES tab_users (id),
    ADD CONSTRAINT user_roles_role_id_fk
        FOREIGN KEY (role_id)
            REFERENCES tab_roles (id);
-- END OF SCHEMA DEFINITION


-- INSERT
INSERT INTO tab_user_roles (user_id, role_id)
VALUES
       (1, 1), (1, 2), (1, 3),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1), (5, 2),
       (6, 1), (6, 2),
       (7, 1), (7, 2),
       (8, 1), (8, 2), (8, 3),
       (9, 1), (9, 2), (9, 3),
       (10, 1), (10, 2), (10, 3);
