CREATE USER p3board IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO p3board;
------------------------------------------------------------------
conn p3board/p4ssw0rd
------------------------------------------------------------------
CREATE TABLE BOARD (
  BD_ID INTEGER,
  BD_NAME VARCHAR2(4000) NOT NULL,
  BD_STARTDATE TIMESTAMP NOT NULL,
  BD_DURATION INTEGER NOT NULL,
  PRIMARY KEY(BD_ID)
);

CREATE SEQUENCE BOARD_SEQ
START WITH 1
INCREMENT BY 1;
------------------------------------------------------------------

CREATE USER p3boarduser IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO p3boarduser;
------------------------------------------------------------------
conn p3boarduser/p4ssw0rd
------------------------------------------------------------------
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
------------------------------------------------------------------

CREATE USER p3history IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO p3history;
------------------------------------------------------------------
conn p3history/p4ssw0rd
------------------------------------------------------------------
CREATE TABLE HISTORY ( 
  HIST_ID INTEGER,
  HIST_ACTION VARCHAR2(4000),
  BU_ID INTEGER,
  BD_ID INTEGER,
  HIST_TIMESTAMP TIMESTAMP,
  PRIMARY KEY(HIST_ID)
);

CREATE SEQUENCE HISTORY_SEQ
START WITH 1
INCREMENT BY 1;
------------------------------------------------------------------

CREATE USER p3login IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO p3login;
------------------------------------------------------------------
conn p3login/p4ssw0rd
------------------------------------------------------------------
CREATE TABLE LOGIN (
  LOGIN_USERNAME VARCHAR2(255),
  LOGIN_PASSWORD VARCHAR2(255) NOT NULL,
  PRIMARY KEY(LOGIN_USERNAME)
);
------------------------------------------------------------------

CREATE USER p3story IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO p3story;
------------------------------------------------------------------
conn p3story/p4ssw0rd
------------------------------------------------------------------
CREATE TABLE STORY (
  ST_ID INTEGER,
  ST_TITLE VARCHAR2(500),
  ST_SWIMLANE INTEGER,
  ST_DESC VARCHAR2(4000),
  ST_POINTS INTEGER,
  ST_COMPLETED TIMESTAMP,
  ST_POSITION INTEGER,
  PRIMARY KEY(ST_ID)
);

CREATE SEQUENCE STORY_SEQ
START WITH 1
INCREMENT BY 1;
------------------------------------------------------------------

CREATE USER p3swimlane IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO p3swimlane;
------------------------------------------------------------------
conn p3swimlane/p4ssw0rd
------------------------------------------------------------------
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
------------------------------------------------------------------

CREATE USER p3task IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO p3task;
------------------------------------------------------------------
conn p3task/p4ssw0rd
------------------------------------------------------------------
CREATE TABLE TASK (
  TSK_ID INTEGER,
  ST_ID INTEGER NOT NULL,
  TSK_NAME VARCHAR2(4000) NOT NULL,
  TSK_COMPLETED NUMBER(1) DEFAULT 0,
  TSK_POSITION INTEGER,
  PRIMARY KEY(TSK_ID)
);

CREATE SEQUENCE TASK_SEQ
START WITH 1
INCREMENT BY 1;
------------------------------------------------------------------

CREATE USER p3role IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO p3role;
------------------------------------------------------------------
conn p3role/p4ssw0rd
------------------------------------------------------------------
CREATE TABLE USER_BOARD_ROLE (
  UBR_ID INTEGER,
  BD_ID INTEGER,
  USER_ID INTEGER,
  ROLE_ID INTEGER,
  PRIMARY KEY(UBR_ID)
);

CREATE SEQUENCE UBR_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TABLE ROLE_TYPE (
  RT_ID INTEGER,
  RT_NAME VARCHAR2(4000),
  PRIMARY KEY(RT_ID)
);

INSERT INTO ROLE_TYPE VALUES (1, "Admin");
INSERT INTO ROLE_TYPE VALUES (2, "Owner");
INSERT INTO ROLE_TYPE VALUES (3, "Collaborator");
------------------------------------------------------------------
COMMIT;
EXIT;
