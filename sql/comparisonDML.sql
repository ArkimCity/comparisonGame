INSERT INTO category VALUES ('라면 월드컵');
INSERT INTO category VALUES ('강아지 월드컵');

INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','웰시코기','<img src="https://images.mypetlife.co.kr/content/uploads/2019/09/04222847/dog-panting-1024x683.jpg">','testParam1_1');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','닥스훈트','<img src="https://i.ytimg.com/vi/as9jLS78MhA/maxresdefault.jpg">','testParam1_2');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','진돗개','<img src="https://image.dongascience.com/Photo/2017/06/14964815289707.jpg">','testParam1_3');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','슈나우저','<img src="https://myanimals.com/ko/wp-content/uploads/2018/04/Schnauzer-dogs-300x200.jpg">','testParam1_4');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','포메라니안','<img src="https://animalcomp.files.wordpress.com/2017/05/ed8faceba9944.png?w=640">','testParam1_5');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','치와와','<img src="https://cdn.imweb.me/upload/S201701095872e8d14d5b3/8600d399e3d61.jpg">','testParam1_6');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','말티즈','<img src="https://babypet.kr/data/file/wiki/3731587261_UuQhJBHg_fa008ba9c8850391d8e46f9593fc6fe221703f75.jpg">','testParam1_7');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'강아지 월드컵','비숑','<img src="https://t1.daumcdn.net/cfile/tistory/999B01335A26D81302">','testParam1_8');

INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'라면 월드컵','신라면','<img src="https://www.costco.co.kr/medias/sys_master/images/h64/h96/9867844452382.jpg">','testParam2_1');
INSERT INTO gameData VALUES (gameData_dataId_seq.nextval,'라면 월드컵','삼양라면','<img src="https://www.samyangfoods.com/upload/product/20180913/20180913135237972039.jpg">','testParam2_2');

INSERT INTO users VALUES ('testid1','testpw1','권오민','testnn1');
INSERT INTO users VALUES ('testid2','testpw2','권희성','testnn2');
INSERT INTO users VALUES ('testid3','testpw3','이민재','testnn3');
INSERT INTO users VALUES ('test1@test.com','testpw1','테스트1','테스트닉1');

INSERT INTO score VALUES (score_scoreId_seq.nextval,'라면 월드컵','testid1',20);
INSERT INTO score VALUES (score_scoreId_seq.nextval,'강아지 월드컵','testid2',35);
INSERT INTO score VALUES (score_scoreId_seq.nextval,'강아지 월드컵','testid3',30);

COMMIT;
