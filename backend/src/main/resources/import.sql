INSERT INTO tb_position (name) VALUES ('Desenvolvedor');
INSERT INTO tb_position (name) VALUES ('Product Owner');
INSERT INTO tb_position (name) VALUES ('Scrum Master');


INSERT INTO tb_profile (name) VALUES ('Junior');
INSERT INTO tb_profile (name) VALUES ('Pleno');
INSERT INTO tb_profile (name) VALUES ('Senior');

INSERT INTO tb_user (date_register, company_position_id, name, cpf, birth_date, gender) VALUES ('1990-01-01T00:00:00Z', 1, 'Jose', '12345678900', '2023-06-04T00:00:00Z', 'F');
INSERT INTO tb_user (date_register, company_position_id, name, cpf, birth_date, gender) VALUES ('1990-01-01T00:00:00Z', 1, 'Rosana', '12345778900', '2023-06-04T00:00:00Z', 'M');

INSERT INTO tb_user_profile (profile_id, user_id) VALUES (1,1);