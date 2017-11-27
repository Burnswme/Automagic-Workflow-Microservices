CREATE TABLE board (
  bd_id INT,
  bd_name VARCHAR2(4000) NOT NULL,
  bd_startdate TIMESTAMP NOT NULL,
  bd_duration INT NOT NULL,
  PRIMARY KEY (bd_id)
);

CREATE TABLE swimlane (
  sl_id INT,
  bd_id INT,
  sl_name VARCHAR2(4000),
  sl_position INT,
  PRIMARY KEY (sl_id),
  FOREIGN KEY (bd_id) REFERENCES board (bd_id)
);

CREATE TABLE story (
  st_id INT,
  sl_id INT,
  st_title VARCHAR2(4000),
  st_desc VARCHAR2(4000),
  st_points INT,
  st_completed TIMESTAMP,
  st_position INT,
  PRIMARY KEY (st_id),
  FOREIGN KEY (sl_id) REFERENCES swimlane (sl_id)
);

CREATE TABLE task (
  tsk_id INT,
  st_id INT,
  tsk_name VARCHAR2(4000),
  tsk_completed NUMBER(1) DEFAULT 0,
  tsk_position INT,
  PRIMARY KEY (tsk_id),
  FOREIGN KEY (st_id) REFERENCES story (st_id)
);