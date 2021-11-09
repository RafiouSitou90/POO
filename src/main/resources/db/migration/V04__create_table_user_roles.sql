CREATE TABLE tab_user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

ALTER TABLE tab_user_roles
    ADD CONSTRAINT role_role_id_pk PRIMARY KEY (user_id, role_id),
    ADD CONSTRAINT role_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES tab_users (id),
    ADD CONSTRAINT role_roles_role_id_fk FOREIGN KEY (role_id) REFERENCES tab_roles (id);
