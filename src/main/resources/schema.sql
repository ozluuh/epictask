CREATE TABLE task(
  id bigint primary key auto_increment,
  title VARCHAR(200) not null,
  description VARCHAR(200) not null,
  points INT not null
);

CREATE TABLE user(
  id bigint primary key auto_increment,
  email varchar(200) not null,
  password varchar(200) not null,
  name varchar(200) not null
);
