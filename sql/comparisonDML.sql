INSERT INTO category VALUES ('라면 월드컵');
INSERT INTO category VALUES ('강아지 월드컵');

INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','웰시코기','<img src="https://images.mypetlife.co.kr/content/uploads/2019/09/04222847/dog-panting-1024x683.jpg">','testParam1');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','닥스훈트','<img src="https://i.ytimg.com/vi/as9jLS78MhA/maxresdefault.jpg">','testParam2');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'라면 월드컵','신라면','<img src="https://www.costco.co.kr/medias/sys_master/images/h64/h96/9867844452382.jpg">','testParam3');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'라면 월드컵','삼양라면','<img src="https://www.samyangfoods.com/upload/product/20180913/20180913135237972039.jpg">','testParam4');

INSERT INTO users VALUES ('testid1','testpw1','권오민','testnn1');
INSERT INTO users VALUES ('testid2','testpw2','권희성','testnn2');
INSERT INTO users VALUES ('testid3','testpw3','이민재','testnn3');

INSERT INTO score VALUES (score_scoreId_seq.nextval,'라면 월드컵','testid1',20);
INSERT INTO score VALUES (score_scoreId_seq.nextval,'강아지 월드컵','testid2',35);
INSERT INTO score VALUES (score_scoreId_seq.nextval,'강아지 월드컵','testid3',30);

COMMIT;