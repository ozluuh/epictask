INSERT INTO task(title, description, points, status, user_id) VALUES('Criar banco de dados','Criar bd oracle na nuvem',300,10, 1);
INSERT INTO task(title, description, points, status, user_id) VALUES('Protótipo','Criar protótipo de alta fidelidade',150,60, 1);
INSERT INTO task(title, description, points, status, user_id) VALUES('Modelagem de dados','Criar modelo lógico dos dados',200,95, 1);

INSERT INTO user(email, password, name, github_user) VALUES('teste@fakemail.com','$2a$12$m2MQOl/T6q76zriCIhCEJe6Qg7P775So7EPtv.Fraj2D2y3vauvAO', 'fake admin', 'ozluuh');
INSERT INTO user(email, password, name, github_user) VALUES('mail@fakemail.com','$2a$12$m2MQOl/T6q76zriCIhCEJe6Qg7P775So7EPtv.Fraj2D2y3vauvAO', 'fake user', 'ozluuh');
INSERT INTO user(email, password, name, github_user) VALUES('fake@testmail.com','$2a$12$m2MQOl/T6q76zriCIhCEJe6Qg7P775So7EPtv.Fraj2D2y3vauvAO', 'fake user 2', 'ozluuh');

INSERT INTO role(name) VALUES('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO user_roles(user_id, roles_id) VALUES(1,1), (2,2), (3,2);
