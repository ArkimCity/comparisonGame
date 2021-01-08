DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE userword CASCADE CONSTRAINTS;
DROP TABLE community CASCADE CONSTRAINTS;

DROP SEQUENCE community_id_seq;
DROP SEQUENCE userword_id_seq;

CREATE SEQUENCE userword_id_seq;
CREATE SEQUENCE community_id_seq;

CREATE TABLE users (
        userId                VARCHAR2(30)        PRIMARY KEY,
        password        VARCHAR2(20)        NOT NULL,
        userName        VARCHAR2(10)        NOT NULL,
        nickname        VARCHAR2(30)
);

CREATE TABLE userword (
        saveId        NUMBER(5)                PRIMARY KEY,
        userId        VARCHAR2(30)        NOT NULL,
        word        VARCHAR2(255) UNIQUE
);

CREATE TABLE community (
        postId                NUMBER(5)                PRIMARY KEY,
        userId                VARCHAR2(30)        NOT NULL,
        word                VARCHAR2(255),
        thumbsUp        NUMBER(5)
);

ALTER TABLE userword ADD CONSTRAINT FK_userword_UserId FOREIGN KEY (userId)
REFERENCES users (userId);
ALTER TABLE community ADD CONSTRAINT FK_community_UserId FOREIGN KEY (userId)
REFERENCES users (userId);