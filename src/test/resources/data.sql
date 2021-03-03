DROP TABLE IF EXISTS content;

CREATE TABLE content (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  description VARCHAR(250) NOT NULL,
  content VARCHAR(4000) DEFAULT NULL
);

INSERT INTO content (id, title, description, content) VALUES
  (1, 'Title 1', 'Desc 1', 'Content 1'),
  (2, 'Title 2', 'Desc 2', 'Content 2');