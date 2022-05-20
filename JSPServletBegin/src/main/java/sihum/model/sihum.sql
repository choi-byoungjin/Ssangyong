
---- ======== ****** 객관식 시험문제 관련 ****** ======== ----

show user;
-- USER이(가) "JSPBEGIN_USER"입니다.

---------------------------------------------------------------------------------------------------
-- [과목] 테이블
create table tbl_subject  
(subject_no    number                  -- 과목번호
,subject_name  varchar2(100) not null  -- 과목명 
,constraint PK_tbl_subject primary key(subject_no)
);
-- Table TBL_SUBJECT이(가) 생성되었습니다.

insert into tbl_subject(subject_no, subject_name) values(1,'JAVA');
insert into tbl_subject(subject_no, subject_name) values(2,'ORACLE');
insert into tbl_subject(subject_no, subject_name) values(3,'HTML');
commit;

select * 
from tbl_subject;


-- [과목별시험회차] 테이블
create table tbl_test_round
(fk_subject_no   number not null   -- 과목번호참조
,test_round      number not null   -- 과목회차
,test_day        date not null     -- 시험일자
,constraint PK_tbl_test_round primary key(fk_subject_no,test_round)
);
-- Table TBL_TEST_ROUND이(가) 생성되었습니다.
insert into tbl_test_round(fk_subject_no, test_round, test_day) values(1,1,sysdate);
commit;

select * 
from tbl_test_round;


-- [과목별시험회차에 딸린 시험문제] 테이블
create table tbl_question
(question_no    number            -- 시험문제고유번호 시퀀스
,fk_subject_no  number not null   -- 과목번호 참조 
,fk_test_round  number not null   -- 과목회차 참조
,question       varchar2(200)     -- 시험문제
,correct        number(1)         -- 정답번호
,constraint PK_tbl_question primary key(question_no) 
,constraint FK_tbl_question foreign key(fk_subject_no,fk_test_round) 
                            references tbl_test_round(fk_subject_no,test_round) on delete cascade
);
-- Table TBL_QUESTION이(가) 생성되었습니다.

create sequence seq_tbl_question
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_QUESTION이(가) 생성되었습니다.

insert into tbl_question(question_no, fk_subject_no, fk_test_round, question, correct)
values(seq_tbl_question.nextval, 1, 1, '문제1. 대한민국의 수도는?', 3);

insert into tbl_question(question_no, fk_subject_no, fk_test_round, question, correct)
values(seq_tbl_question.nextval, 1, 1, '문제2. 1+1은?', 2); 

insert into tbl_question(question_no, fk_subject_no, fk_test_round, question, correct)
values(seq_tbl_question.nextval, 1, 1, '문제3. 미국의 수도는?', 4);

commit;

select * 
from tbl_question;

select question_no, question, correct 
from tbl_question
where fk_subject_no = 1 and fk_test_round = 1;


-- [시험문제의 정답보기선택] 테이블
create table tbl_answers
(answers_no        number                  -- 정답보기선택 고유번호
,fk_question_no    number  not null        -- 시험문제고유번호 참조
,answer            varchar2(200) not null  -- 정답보기선택
,constraint PK_tbl_answers primary key(answers_no) 
,constraint FK_tbl_answers foreign key(fk_question_no)  
                            references tbl_question(question_no) on delete cascade
);
-- Table TBL_ANSWERS이(가) 생성되었습니다.

create sequence seq_tbl_answers
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_TBL_ANSWERS이(가) 생성되었습니다.

insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 1, '부산');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 1, '수원');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 1, '서울');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 1, '인천');

insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 2, '1');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 2, '2');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 2, '3');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 2, '4');

insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 3, '뉴욕');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 3, '파리');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 3, '로스앤젤러스');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 3, '워싱턴');
insert into tbl_answers(answers_no, fk_question_no, answer) values(seq_tbl_answers.nextval, 3, '런던');

commit;

select *
from tbl_answers;

select answer 
from tbl_answers
where fk_question_no = 1;

select answer 
from tbl_answers
where fk_question_no = 2;

select answer 
from tbl_answers
where fk_question_no = 3;


