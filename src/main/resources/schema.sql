CREATE TABLE task(
  id bigint primary key auto_increment,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(200) NOT NULL,
  points INT NOT NULL,
  status INT DEFAULT 0,
  user_id INT
);

CREATE TABLE user(
  id bigint primary key auto_increment,
  email VARCHAR(200) NOT NULL,
  password VARCHAR(200) NOT NULL,
  name VARCHAR(200) NOT NULL,
  github_user varchar(200) NOT NULL
);

CREATE TABLE role(
  id int primary key auto_increment,
  name varchar(200)
);

CREATE TABLE user_roles(
  user_id int,
  roles_id int
);
