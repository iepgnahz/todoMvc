CREATE TABLE task (
  id        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  text      VARCHAR(255)    NOT NULL,
  completed INT             NOT NULL,
  available INT             NOT NULL
);
