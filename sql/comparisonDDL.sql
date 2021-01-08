DROP TABLE gameData CASCADE CONSTRAINTS;
DROP TABLE user CASCADE CONSTRAINTS;
DROP TABLE score CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;

DROP SEQUENCE score_scoreId_seq;
DROP SEQUENCE gameData_dataId_seq;

CREATE SEQUENCE score_scoreId_seq;
CREATE SEQUENCE gameData_dataId_seq;

CREATE TABLE gameData (
    dataId              NUMBER(5)               PRIMARY KEY,
    category          VARCHAR2(50)         NOT NULL,
    title                  VARCHAR2(50)         NOT NULL,
    imgsrc             VARCHAR2(255)       NOT NULL,
    parameter       VARCHAR2(255)       NOT NULL
);

CREATE TABLE users (
    userId              VARCHAR2(30)         PRIMARY KEY,
    password        VARCHAR2(20)         NOT NULL,
    userName       VARCHAR2(10)          NOT NULL,
    nickName       VARCHAR2(20)         NOT NULL
);

CREATE TABLE score (
    scoreId            NUMBER(5)                PRIMARY KEY,
    category         VARCHAR2(50)          NOT NULL,
    userId             VARCHAR2(30)          NOT NULL,
    score              VARCHAR2(255)        NOT NULL
);

CREATE TABLE category (
    category         VARCHAR2(50)          PRIMARY KEY
);

ALTER TABLE score ADD CONSTRAINT FK_category_to_score
FOREIGN KEY (category) REFERENCES category (category);

ALTER TABLE score ADD CONSTRAINT FK_users_userId
FOREIGN KEY (userId) REFERENCES users (userId);

ALTER TABLE gameData ADD CONSTRAINT FK_category_to_gameData
FOREIGN KEY (category) REFERENCES category (category);