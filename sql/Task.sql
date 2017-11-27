CREATE TABLE task (
  tsk_id INT,
  st_id INT,
  tsk_name VARCHAR2(4000),
  tsk_completed NUMBER(1) DEFAULT 0,
  tsk_position INT,
  PRIMARY KEY (tsk_id)
);