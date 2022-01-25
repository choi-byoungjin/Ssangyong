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


select *
from jdbc_tbl_memo
order by no desc;

/*
insert into jdbc_tbl_memo(no, name, msg)
values(jdbc_seq_memo.nextval, '홍길동', '안녕하세요?');
*/

select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday 
from jdbc_tbl_memo
order by no desc;


select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday from jdbc_tbl_memo order by no desc;     


select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday 
from jdbc_tbl_memo
where no = '1'
order by no desc;

select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday 
from jdbc_tbl_memo
where name = '서영학'
order by no desc;


select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday 
from jdbc_tbl_memo
where msg like '%'||'저녁'||'%'
order by no desc;

select *
from user_tables
where table_name = 'JDBC_TBL_EXAMTEST';

select *
from user_tables
where table_name = 'JDBC_TBL_MEMO';


select *
from JDBC_TBL_EXAMTEST;

select *
from user_sequences
where sequence_name = 'JDBC_SEQ_EXAMTEST';






