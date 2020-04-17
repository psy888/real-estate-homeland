--app roles
INSERT INTO sc.app_role(role_id, role_name)
VALUES (1, 'ROLE_ADMINISTRATOR');

INSERT INTO sc.app_role(role_id, role_name)
VALUES (2, 'ROLE_AGENT');

INSERT INTO sc.app_role(role_id, role_name)
VALUES (3, 'ROLE_CLIENT');

-- user
INSERT INTO sc."user"(id, email, encrypted_password, first_name, enabled, last_name, phone_number)
VALUES ('f3306280-0d53-4cd4-a3e4-5b76f859f733', 'psy888@mail.ru',
        '$2a$10$wzLcCu23FCa10B6R02TrgOrfUJ16WtpDXohPZY2wD73Z9LGzBi9Sa', 'Pavel', true, null, '123345654');

INSERT INTO sc."user"(id, email, encrypted_password, first_name, enabled, last_name, phone_number)
VALUES ('8b07d2ec-144d-493d-839d-1fc166a3792a', 'admin@admin.com',
        '$2a$10$MNDamN5N1CJMLugNNeuTtevIHYq1lhiIhdAlmbEli9i31sjod6Mx6', 'Admin', true, 'Admin', '3213213');



-- user role --
--Agent
INSERT INTO sc.user_role(id, role_id, user_id)
VALUES (1, 2, 'f3306280-0d53-4cd4-a3e4-5b76f859f733');
--Admin
INSERT INTO sc.user_role(id, role_id, user_id)
VALUES (2, 1, '8b07d2ec-144d-493d-839d-1fc166a3792a');
