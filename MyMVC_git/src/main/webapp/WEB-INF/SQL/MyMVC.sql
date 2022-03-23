
------- ***** MyMVC 에서 작업한 것 ***** -------

show user;
-- USER이(가) "SYS"입니다.

create user jspbegin_user identified by cclass default tablespace users; -- cclass 는 암호
-- User JSPBEGIN_USER이(가) 생성되었습니다.

grant connect, resource, create view, unlimited tablespace to jspbegin_user;
-- Grant을(를) 성공했습니다.

show user;
-- USER이(가) "JSPBEGIN_USER"입니다.

create table tbl_main_image
(imgno           number not null
,imgfilename     varchar2(100) not null
,constraint PK_tbl_main_image primary key(imgno)
);

create sequence seq_main_image
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

insert into tbl_main_image(imgno, imgfilename) values(seq_main_image.nextval, '미샤.png');  
insert into tbl_main_image(imgno, imgfilename) values(seq_main_image.nextval, '원더플레이스.png'); 
insert into tbl_main_image(imgno, imgfilename) values(seq_main_image.nextval, '레노보.png'); 
insert into tbl_main_image(imgno, imgfilename) values(seq_main_image.nextval, '동원.png'); 

commit;

select imgno, imgfilename
from tbl_main_image 
order by imgno asc;

----- **** 회원 테이블 생성 **** ------
-- drop table tbl_member purge;
alter table tbl_member
modify userid varchar2(40);

create table tbl_member
(userid             varchar2(40)   not null  -- 회원아이디
,pwd                varchar2(200)  not null  -- 비밀번호 (SHA-256 암호화 대상)
,name               varchar2(30)   not null  -- 회원명
,email              varchar2(200)  not null  -- 이메일 (AES-256 암호화/복호화 대상)
,mobile             varchar2(200)            -- 연락처 (AES-256 암호화/복호화 대상) 
,postcode           varchar2(5)              -- 우편번호
,address            varchar2(200)            -- 주소
,detailaddress      varchar2(200)            -- 상세주소
,extraaddress       varchar2(200)            -- 참고항목
,gender             varchar2(1)              -- 성별   남자:1  / 여자:2
,birthday           varchar2(10)             -- 생년월일   
,coin               number default 0         -- 코인액
,point              number default 0         -- 포인트 
,registerday        date default sysdate     -- 가입일자 
,lastpwdchangedate  date default sysdate     -- 마지막으로 암호를 변경한 날짜  
,status             number(1) default 1 not null     -- 회원탈퇴유무   1: 사용가능(가입중) / 0:사용불능(탈퇴) 
,idle               number(1) default 0 not null     -- 휴면유무      0 : 활동중  /  1 : 휴면중 
,constraint PK_tbl_member_userid primary key(userid)
,constraint UQ_tbl_member_email  unique(email)
,constraint CK_tbl_member_gender check( gender in('1','2') )
,constraint CK_tbl_member_status check( status in(0,1) )
,constraint CK_tbl_member_idle check( idle in(0,1) )
);
-- Table TBL_MEMBER이(가) 생성되었습니다.

select *
from tbl_member
order by registerday desc;


commit
delete from tbl_member where userid = 'leess';

----- **** 로그인기록 테이블 생성 **** -------
create table tbl_loginhistory
(fk_userid   varchar2(40) not null 
,logindate   date default sysdate not null
,clientip    varchar2(20) not null
,constraint FK_tbl_loginhistory foreign key(fk_userid) 
                                references tbl_member(userid)  
);
-- Table TBL_LOGINHISTORY이(가) 생성되었습니다.

select fk_userid, to_char(logindate, 'yyyy-mm-dd hh24:mi:ss') as logindate, clientip
from tbl_loginhistory;

--- *** 로그인을 처리하기 위한 sql문 작성하기 ** ---
desc tbl_member;

SELECT userid, name, email, mobile, postcode, address, detailaddress, extraaddress, gender
    , birthyyyy, birthmm, birthdd, coin, point, registerday, pwdchangegap
    , nvl(lastlogingap, trunc(months_between(sysdate, registerday) ) ) as lastlogingap
FROM
(
select userid, name, email, mobile, postcode, address, detailaddress, extraaddress, gender
    , substr(birthday,1,4) as birthyyyy, substr(birthday, 6, 2) as birthmm, substr(birthday,9) as birthdd
    , coin, point, to_char(registerday, 'yyyy-mm-dd') as registerday
    , trunc(months_between(sysdate, lastpwdchangedate),0) as pwdchangegap -- 3개월이 넘어가면 비밀번호 변경문구가 뜬다 -- ',0'은 생략가능하다.
from tbl_member
where status = 1 and userid = 'leess' and pwd = '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382'
) M
CROSS JOIN
(
select months_between(sysdate, max(logindate)) as lastlogingap -- 마지막 로그인 한 날짜가 1년이 지나면 휴면상태가 된다.
from tbl_loginhistory
where fk_userid = 'leess'
) H



--- *** 마지막으로 로그인 한지 12개월이 초과되었을 경우 테스트하려고 하는 것임.*** ---

update tbl_member set registerday = add_months(registerday, -13)
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.

update tbl_member set lastpwdchangedate = add_months(lastpwdchangedate, -13)
where userid = 'leess';

update tbl_loginhistory set logindate = add_months(logindate, -13)
where fk_userid = 'leess';

commit

-- ** 원상복구하기 **
update tbl_member set registerday = add_months(registerday, 13)
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.

update tbl_member set lastpwdchangedate = add_months(lastpwdchangedate, 13)
where userid = 'leess';

update tbl_member set idle = 0
where userid = 'leess';


update tbl_loginhistory set logindate = add_months(logindate, 13)
where fk_userid = 'leess';

commit
----------------------


--- *** 비밀번호를 변경한지 3개월이 초과되었을 경우 테스트하려고 하는 것임.*** ---
update tbl_member set lastpwdchangedate = add_months(lastpwdchangedate, -5)
where userid = 'leess'; 

commit


-- ** 원상복구하기 ** --
update tbl_member set lastpwdchangedate = add_months(lastpwdchangedate, 5)
where userid = 'leess'; 

commit
