set hidden param parseThreshold = 150000;

show user;
-- USER이(가) "HR"입니다.

---- *** 회원 테이블 생성하기 *** ----
select *
from user_tables
where table_name = 'JDBC_MEMBER';

create table jdbc_member
(userseq       number        not null    -- 회원번호
,userid        varchar2(30)  not null    -- 회원아이디
,passwd        varchar2(30)  not null    -- 회원암호
,name          varchar2(20)  not null    -- 회원명
,mobile        varchar2(20)              -- 연락처
,point         number(10) default 0      -- 포인트
,registerday   date default sysdate      -- 가입일자 
,status        number(1) default 1       -- status 컬럼의 값이 1 이면 정상, 0 이면 탈퇴 
,constraint PK_jdbc_member primary key(userseq)
,constraint UQ_jdbc_member unique(userid)
,constraint CK_jdbc_member check( status in(0,1) )
);

create sequence userseq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;


select *
from jdbc_member
order by userseq asc;




---- *** 게시판 테이블 생성하기 *** ----
create table jdbc_board
(boardno       number        not null          -- 글번호
,fk_userid     varchar2(30)  not null          -- 작성자아이디
,subject       varchar2(100) not null          -- 글제목
,contents      varchar2(200) not null          -- 글내용
,writeday      date default sysdate not null   -- 작성일자
,viewcount     number default 0 not null       -- 조회수 
,boardpasswd   varchar2(20) not null           -- 글암호 
,constraint PK_jdbc_board primary key(boardno)
,constraint FK_jdbc_board foreign key(fk_userid) references jdbc_member(userid) 
);


create sequence board_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

desc jdbc_board;

select *
from jdbc_board
order by boardno desc;

select *
from jdbc_member
where userid = ''; -- rs.next()가 있는지 확인한다

update jdbc_member set status = 1
where userid = 'leess';

commit;

-----------------------------------------------------------------------------
insert into jdbc_board(boardno, fk_userid, subject, contents, boardpasswd)
values(board_seq.nextval, 'eomjh', '짜장면', '맛있어요', '1234');

insert into jdbc_board(boardno, fk_userid, subject, contents, boardpasswd)
values(board_seq.nextval, 'leess', '돈까스', '좋아해요~!!', '1234');

insert into jdbc_board(boardno, fk_userid, subject, contents, boardpasswd)
values(board_seq.nextval, 'eomjh', '치킨', '맥주와 함께', '1234');

insert into jdbc_board(boardno, fk_userid, subject, contents, boardpasswd)
values(board_seq.nextval, 'leehr', '피자', '아주 좋아해요~~!!', '1234');

commit;


select *
from jdbc_board
where boardno = 2 and fk_userid = 'leess';


select *
from jdbc_board
where boardno = 35434 and fk_userid = 'leess';

select *
from jdbc_board
where boardno = 2;

select *
from jdbc_board
where boardno = 2 and fk_userid = 'leess';


String sql = "select B.boardno, B.subject, M.name\n"+
"     , to_char(B.writeday, 'yyyy-mm-dd hh24:mi:ss'), B.viewcount\n"+
"from jdbc_board B join jdbc_member M\n"+
"ON B.fk_userid = M.userid\n"+
"order by boardno desc";



select *
from jdbc_member


--- BOARD_SEQ 시퀀스를 사용했을때 다음에 들어올 값을 알고자 할 경우
select last_number
from user_sequences
where sequence_name = 'BOARD_SEQ'


---- *** 댓글 테이블 생성하기 *** ----
create table jdbc_comment 
(commentno   number        not null    -- 댓글번호 
,fk_boardno  number        not null    -- 원글의 글번호 
,fk_userid   varchar2(30)  not null    -- 사용자ID
,contents    varchar2(200) not null    -- 댓글내용 
,writeday    date default sysdate      -- 작성일자
,constraint  PK_jdbc_comment  primary key(commentno) 
,constraint  FK_jdbc_comment_fk_boardno foreign key(fk_boardno) 
             references jdbc_board(boardno) on delete cascade 
,constraint  FK_jdbc_comment_fk_userid  foreign key(fk_userid) 
             references jdbc_member(userid) 
);


create sequence seq_comment
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;


select *
from jdbc_comment;

insert into jdbc_comment(commentno, fk_boardno, fk_userid, contents)
values(1, '1234123', 'leess', '연습');
/*
    오류 보고 -
    ORA-02291: integrity constraint (HR.FK_JDBC_COMMENT_FK_BOARDNO) violated - parent key not found
*/
rollback;


select *
from jdbc_board B JOIN jdbc_member M
on b.fk_userid = m.userid

select boardno, subject, contents, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss'), viewcount, M.name
from jdbc_board B JOIN jdbc_member M
ON B.fk_userid = M.userid
where boardno = ?


select C.contents, M.name, C.writeday
from
(select contents, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday, fk_userid
 from jdbc_comment
 where fk_boardno = 4) C JOIN jdbc_member M
ON C.fk_userid = M.userid;

---------------------------------------------------------------------------------------------------------
--- 글제목 다음에 딸린 댓글의 개수를 보여주고자 한다. ---

select B.boardno, B.subject, M.name
      , to_char(B.writeday, 'yyyy-mm-dd hh24:mi:ss'), B.viewcount
from jdbc_board B join jdbc_member M
ON B.fk_userid = M.userid
order by boardno desc;
 
 
select fk_boardno, count(*)
from jdbc_comment
group by fk_boardno;


select B.boardno, B.subject, M.name
      , to_char(B.writeday, 'yyyy-mm-dd hh24:mi:ss'), B.viewcount
      , nvl(C.Commentcnt, 0)
from jdbc_board B JOIN jdbc_member M
ON B.fk_userid = M.userid
LEFT JOIN (select fk_boardno, count(*) as commentcnt
           from jdbc_comment
           group by fk_boardno) C
ON B.boardno = C.fk_boardno
order by 1 desc