CREATE TABLE SWIMLANE (
  SL_ID INTEGER,
  BD_ID INTEGER,
  SL_NAME VARCHAR2(255),
  SL_POSITION NUMBER(10),
  PRIMARY KEY(SL_ID)
);

CREATE SEQUENCE SWIMLANE_SEQ
START WITH 1
INCREMENT BY 1;
