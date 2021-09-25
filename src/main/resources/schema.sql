CREATE TABLE task(
  id bigint primary key auto_increment,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(200) NOT NULL,
  points INT NOT NULL,
  status INT DEFAULT 0
);

CREATE TABLE USER(
  id bigint primary key auto_increment,
  email VARCHAR(200) NOT NULL,
  password VARCHAR(200) NOT NULL,
  NAME VARCHAR(200) NOT NULL
);
