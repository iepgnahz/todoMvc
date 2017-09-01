CREATE TABLE task (
  id        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  text      VARCHAR(255)    NOT NULL,
  status INT             NOT NULL,
  available INT             NOT NULL
);
