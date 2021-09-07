CREATE TABLE task(
  id bigint primary key auto_increment,
  title VARCHAR(200),
  description VARCHAR(200),
  points INT
);

INSERT INTO
  task(title, description, points)
VALUES
  (
    'Criar banco de dados',
    'Criar bd oracle na nuvem',
    300
  );

INSERT INTO
  task(title, description, points)
VALUES
  (
    'Protótipo',
    'Criar protótipo de alta fidelidade',
    150
  );

INSERT INTO
  task(title, description, points)
VALUES
  (
    'Modelagem de dados',
    'Criar modelo lógico dos dados',
    200
  );
