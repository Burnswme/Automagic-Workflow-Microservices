CREATE TABLE BOARD_USER (
  BU_ID INTEGER,
  BU_USERNAME VARCHAR2(255),
  BU_FN VARCHAR2(255),
  BU_LN VARCHAR2(255),
  BU_EMAIL VARCHAR2(255),
  BU_ADMIN NUMBER(1) DEFAULT 0,
  PRIMARY KEY(BU_ID)
);

CREATE SEQUENCE BOARD_USER_SEQ
START WITH 2
INCREMENT BY 1;
