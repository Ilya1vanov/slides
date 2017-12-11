INSERT INTO app_role (id, authority) VALUES (1, 'STANDARD_USER');
INSERT INTO app_role (id, authority) VALUES (2, 'ADMIN_USER');

-- USER
-- non-encrypted password: 123456
INSERT INTO app_user (id, first_name, last_name, email, password, username) VALUES (1, 'John', 'Doe', 'com.ilya.ivanov@yandex.ru', '$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2', 'john.doe');
INSERT INTO app_user (id, first_name, last_name, email, password, username) VALUES (2, 'Ilya', 'Ivanov', 'com.ilya.ivanov@gmail.com', '$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2', 'admin.admin');


-- PRESENTATION
-- INSERT INTO presentation (id, title, owner_id) VALUES (1, 'Title', 2);

INSERT INTO user_authorities(user_id, role_id) VALUES(1,1);
INSERT INTO user_authorities(user_id, role_id) VALUES(2,1);
INSERT INTO user_authorities(user_id, role_id) VALUES(2,2);
