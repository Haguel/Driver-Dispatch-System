INSERT INTO app_user (username, password)
VALUES ('admin', '$2a$10$ZqEG4X80MzK/HRO3uxF1Suj5sSPfBcu3gYHYJsVLJHPXuYUPkT.RS');

INSERT INTO app_user_role (app_user_id, role_id)
VALUES (1, 1), (1, 2);
