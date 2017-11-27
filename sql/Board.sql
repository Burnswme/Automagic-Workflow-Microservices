CREATE TABLE board (
  bd_id INT,
  bd_name VARCHAR2(4000) NOT NULL,
  bd_startdate TIMESTAMP NOT NULL,
  bd_duration INT NOT NULL,
  PRIMARY KEY (bd_id)
);