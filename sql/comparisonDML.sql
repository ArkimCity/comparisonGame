INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','testTitle1','testImg1','testParam1');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'라면 월드컵','testTitle2','testImg2','testParam2');

INSERT INTO users VALUES ('testid1','testpw1','권오민','testnn1');
INSERT INTO users VALUES ('testid2','testpw2','권희성','testnn2');
INSERT INTO users VALUES ('testid3','testpw3','이민재','testnn3');

INSERT INTO category VALUES ('라면 월드컵');
INSERT INTO category VALUES ('강아지 월드컵');

INSERT INTO score VALUES (score_scoreId_seq.nextval,'라면 월드컵','testid1',20);
INSERT INTO score VALUES (score_scoreId_seq.nextval,'강아지 월드컵','testid2',35);
INSERT INTO score VALUES (score_scoreId_seq.nextval,'강아지 월드컵','testid3',30);

COMMIT;