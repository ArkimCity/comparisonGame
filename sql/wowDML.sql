INSERT INTO users VALUES ('ok1231','testpw1','권오민','testnn1');
INSERT INTO users VALUES ('rabbit7','testpw2','권희성','testnn2');
INSERT INTO users VALUES ('wonder','testpw3','김민건','testnn3');
INSERT INTO users VALUES ('iloveca','testpw4','김성호','testnn4');
INSERT INTO users VALUES ('gajagi','testpw5','조윤혜','testnn5');
INSERT INTO users VALUES ('imangel','testpw6','김재웅','testnn6');
INSERT INTO users VALUES ('basket','testpw7','김준형','testnn7');
INSERT INTO users VALUES ('wateris','testpw8','김창훈','testnn8');
INSERT INTO users VALUES ('lovelet','testpw9','양호준','testnn9');


INSERT INTO userword VALUES (userword_id_seq.nextval,'ok1231','인형');
INSERT INTO userword VALUES (userword_id_seq.nextval,'rabbit7','손목');
INSERT INTO userword VALUES (userword_id_seq.nextval,'wonder','사리');
INSERT INTO userword VALUES (userword_id_seq.nextval,'iloveca','사위');
INSERT INTO userword VALUES (userword_id_seq.nextval,'gajagi','영국');
INSERT INTO userword VALUES (userword_id_seq.nextval,'imangel','연필');
INSERT INTO userword VALUES (userword_id_seq.nextval,'basket','시멘트');
INSERT INTO userword VALUES (userword_id_seq.nextval,'wateris','산소');
INSERT INTO userword VALUES (userword_id_seq.nextval,'lovelet','연구소');


INSERT INTO community VALUES (community_id_seq.nextval,'ok1231','인형','2');
INSERT INTO community VALUES (community_id_seq.nextval,'rabbit7','손목','4');
INSERT INTO community VALUES (community_id_seq.nextval,'wonder','사리','1');
INSERT INTO community VALUES (community_id_seq.nextval,'iloveca','사위','2');
INSERT INTO community VALUES (community_id_seq.nextval,'gajagi','영국','6');
INSERT INTO community VALUES (community_id_seq.nextval,'imangel','연필','3');
INSERT INTO community VALUES (community_id_seq.nextval,'basket','시멘트','1');
INSERT INTO community VALUES (community_id_seq.nextval,'wateris','산소','5');
INSERT INTO community VALUES (community_id_seq.nextval,'lovelet','연구소','2');

COMMIT;