set hidden param parseThreshold = 150000;

show user;
-- USER이(가) "HR"입니다.

create table jdbc_tbl_memo
(no        number(4)
,name      varchar2(20) not null
,msg       varchar2(200) not null
,writeday  date default sysdate
,constraint PK_jdbc_tbl_memo_no primary key(no)
);
-- Table JDBC_TBL_MEMO이(가) 생성되었습니다.


create sequence jdbc_seq_memo
start with 1
increment by 1 
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence JDBC_SEQ_MEMO이(가) 생성되었습니다.
