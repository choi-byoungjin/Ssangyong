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


String sql = "select B.boardno, B.subject, M.name\n"+
"     , to_char(B.writeday, 'yyyy-mm-dd hh24:mi:ss'), B.viewcount\n"+
"from jdbc_board B join jdbc_member M\n"+
"ON B.fk_userid = M.userid\n"+
"order by boardno desc";