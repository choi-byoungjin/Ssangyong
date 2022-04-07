
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

select *
from tbl_member

--- 오라클에서 프로시저를 사용하여 회원을 대량으로 입력 (insert) 하겠습니다. ---

-- 이메일을 대량으로 넣기 위해서 어쩔 수 없이 email에 대한 unique 제약을 없애도록 한다. --

select *
from user_constraints
where table_name = 'TBL_MEMBER';

alter table tbl_member
drop constraint UQ_TBL_MEMBER_EMAIL;

------

create or replace procedure pcd_member_insert
(p_userid   IN  varchar2
,p_name     IN  varchar2
,p_gender   IN  varchar2)
is
begin
    for i in 1..100 loop
        insert into tbl_member(userid, pwd, name, email, mobile, postcode, address, detailaddress, extraaddress, gender, birthday)
        values(p_userid || i, '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382', p_name||i, 'XavYyzVdgXO7t1pwFMLHVzrBnnSajqQnlFHSBxQAHH4=', 'rZBJfTseXXuOwqBJxi3gmg==', '21360', '인천 부평구 장제로 161', '507호', ' (부평동, 두름파크)', p_gender, '1994-05-08');
    end loop;
end pcd_member_insert;
-- Procedure PCD_MEMBER_INSERT이(가) 컴파일되었습니다.

exec pcd_member_insert('iyou','아이유','2');
-- PL/SQL 프로시저가 성공적으로 완료되었습니다.

exec pcd_member_insert('chaew','차은우','1');
-- PL/SQL 프로시저가 성공적으로 완료되었습니다.

commit;

insert into tbl_member(userid, pwd, name, email, mobile, postcode, address, detailaddress, extraaddress, gender, birthday)
values('kimys', '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382', '김유신', 'XavYyzVdgXO7t1pwFMLHVzrBnnSajqQnlFHSBxQAHH4=', 'rZBJfTseXXuOwqBJxi3gmg==', '21360', '인천 부평구 장제로 161', '507호', ' (부평동, 두름파크)', '1', '1994-05-08');

insert into tbl_member(userid, pwd, name, email, mobile, postcode, address, detailaddress, extraaddress, gender, birthday)
values('choimh', '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382', '최민호', 'XavYyzVdgXO7t1pwFMLHVzrBnnSajqQnlFHSBxQAHH4=', 'rZBJfTseXXuOwqBJxi3gmg==', '21360', '인천 부평구 장제로 161', '507호', ' (부평동, 두름파크)', '1', '1994-05-08');


-- === 페이징 처리를 위한 sql 문 작성하기 === --
select rownum, userid, name, email, gender
from tbl_member
where userid != 'admin'
and rownum between 1 and 10; -- 나온다.


select rownum, userid, name, email, gender
from tbl_member
where userid != 'admin'
and rownum between 11 and 20; -- 안나온다.

select rno, userid, name, email, gender
from
(
    select rownum AS rno, userid, name, email, gender
    from
    (
        select userid, name, email, gender
        from tbl_member
        where userid != 'admin'
        order by registerday desc
    ) V
) T
where rno between 1 and 10; -- 1 페이지 (한 페이지당 10개를 보여줄때)

select rno, userid, name, email, gender
from
(
    select rownum AS rno, userid, name, email, gender
    from
    (
        select userid, name, email, gender
        from tbl_member
        where userid != 'admin'
        order by registerday desc
    ) V
) T
where rno between 11 and 20; -- 2 페이지 (한 페이지당 10개를 보여줄때)

select rno, userid, name, email, gender
from
(
    select rownum AS rno, userid, name, email, gender
    from
    (
        select userid, name, email, gender
        from tbl_member
        where userid != 'admin'
        order by registerday desc
    ) V
) T
where rno between 21 and 30; -- 3 페이지 (한 페이지당 10개를 보여줄때)

/*
    >>> where rno between A and B
    A 와 B 를 구하는 공식 <<<
    
    currentShowPageNo 은 보고자 하는 페이지 번호이다. 즉, 1페이지, 2페이지, 3페이지... 를 말한다.
    sizePerPage 는 한페이지당 보여줄 행의 개수를 말한다. 즉, 3개, 5개, 10개를 보여줄때의 개수를 말한다.
    
    A 는 (currentShowPage * sizePerPage) - (sizePerPage -1) 이다.
    B 는 (currentShowPage * sizePerPage) 이다.
    
    where rno between 1 and 10; -- 1 페이지 (한페이지당 10개를 보여줄 때)
    A 는 (1 * 10) - (10 - 1) ==> 10 - 9 ==> 1
    B 는 (1 * 10) ==> 10
    
    where rno between 11 and 20; -- 2 페이지 (한페이지당 10개를 보여줄 때)
    A 는 (2 * 10) - (10 - 1) ==> 20 - 9 ==> 11
    B 는 (2 * 10) ==> 20
    
    where rno between 21 and 30; -- 2 페이지 (한페이지당 10개를 보여줄 때)
    A 는 (3 * 10) - (10 - 1) ==> 30 - 9 ==> 21
    B 는 (3 * 10) ==> 30
*/

select *
from tbl_member
where name like '%' || '유' || '%'
order by registerday desc;

select *
from tbl_member
where userid like '%' || 'you' || '%'
order by registerday desc;


-------------------------------------------------------------------------------------------------------

-- 검색이 없는 총회원수 알아오기

select count(*) -- 206
from tbl_member
where userid != 'admin';

-- 검색이 있는 총회원수 알아오기

select *
from tbl_member
where userid != 'admin' and name like '%'||'유'||'%';

-- 검색이 없는 전체페이지수 알아오기

select ceil(count(*)/10), ceil(count(*)/5), ceil(count(*)/3)
from tbl_member
where userid != 'admin';

-- 검색이 있는 전체페이지수 알아오기

select ceil(count(*)/10), ceil(count(*)/5), ceil(count(*)/3)
from tbl_member
where userid != 'admin' and name like '%'||'유'||'%';
-- 11 21 34

/*
   카테고리 테이블명 : tbl_category 

   컬럼정의 
     -- 카테고리 대분류 번호  : 시퀀스(seq_category_cnum)로 증가함.(Primary Key)
     -- 카테고리 코드(unique) : ex) 전자제품  '100000'
                                  의류      '200000'
                                  도서      '300000' 
     -- 카테고리명(not null)  : 전자제품, 의류, 도서           
  
*/ 
-- drop table tbl_category purge; 
create table tbl_category
(cnum    number(8)     not null  -- 카테고리 대분류 번호
,code    varchar2(20)  not null  -- 카테고리 코드
,cname   varchar2(100) not null  -- 카테고리명
,constraint PK_tbl_category_cnum primary key(cnum)
,constraint UQ_tbl_category_code unique(code)
);

-- drop sequence seq_category_cnum;
create sequence seq_category_cnum 
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

insert into tbl_category(cnum, code, cname) values(seq_category_cnum.nextval, '100000', '전자제품');
insert into tbl_category(cnum, code, cname) values(seq_category_cnum.nextval, '200000', '의류');
insert into tbl_category(cnum, code, cname) values(seq_category_cnum.nextval, '300000', '도서');
commit;

-- 나중에 넣습니다.
-- insert into tbl_category(cnum, code, cname) values(seq_category_cnum.nextval, '400000', '식품');
-- commit;

-- insert into tbl_category(cnum, code, cname) values(seq_category_cnum.nextval, '500000', '신발');
-- commit;

/*
delete from tbl_category
where code = '500000';

commit;
*/

select cnum, code, cname
from tbl_category
order by cnum asc;

-- drop table tbl_spec purge;
create table tbl_spec
(snum    number(8)     not null  -- 스펙번호       
,sname   varchar2(100) not null  -- 스펙명         
,constraint PK_tbl_spec_snum primary key(snum)
,constraint UQ_tbl_spec_sname unique(sname)
);

-- drop sequence seq_spec_snum;
create sequence seq_spec_snum
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

insert into tbl_spec(snum, sname) values(seq_spec_snum.nextval, 'HIT');
insert into tbl_spec(snum, sname) values(seq_spec_snum.nextval, 'NEW');
insert into tbl_spec(snum, sname) values(seq_spec_snum.nextval, 'BEST');

commit;

select snum, sname
from tbl_spec
order by snum asc;

---- *** 제품 테이블 : tbl_product *** ----
-- drop table tbl_product purge; 
create table tbl_product
(pnum           number(8) not null       -- 제품번호(Primary Key)
,pname          varchar2(100) not null   -- 제품명
,fk_cnum        number(8)                -- 카테고리코드(Foreign Key)의 시퀀스번호 참조
,pcompany       varchar2(50)             -- 제조회사명
,pimage1        varchar2(100) default 'noimage.png' -- 제품이미지1   이미지파일명
,pimage2        varchar2(100) default 'noimage.png' -- 제품이미지2   이미지파일명 
,prdmanual_systemFileName varchar2(200)            -- 파일서버에 업로드되어지는 실제 제품설명서 파일명 (파일명이 중복되는 것을 피하기 위해서 중복된 파일명이 있으면 파일명뒤에 숫자가 자동적으로 붙어 생성됨)
,prdmanual_orginFileName  varchar2(200)            -- 웹클라이언트의 웹브라우저에서 파일을 업로드 할때 올리는 제품설명서 파일명 
,pqty           number(8) default 0      -- 제품 재고량
,price          number(8) default 0      -- 제품 정가
,saleprice      number(8) default 0      -- 제품 판매가(할인해서 팔 것이므로)
,fk_snum        number(8)                -- 'HIT', 'NEW', 'BEST' 에 대한 스펙번호인 시퀀스번호를 참조
,pcontent       varchar2(4000)           -- 제품설명  varchar2는 varchar2(4000) 최대값이므로
                                         --          4000 byte 를 초과하는 경우 clob 를 사용한다.
                                         --          clob 는 최대 4GB 까지 지원한다.
                                         
,point          number(8) default 0      -- 포인트 점수                                         
,pinputdate     date default sysdate     -- 제품입고일자
,constraint  PK_tbl_product_pnum primary key(pnum)
,constraint  FK_tbl_product_fk_cnum foreign key(fk_cnum) references tbl_category(cnum)
,constraint  FK_tbl_product_fk_snum foreign key(fk_snum) references tbl_spec(snum)
);

-- drop sequence seq_tbl_product_pnum;
create sequence seq_tbl_product_pnum
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

-- 아래는 fk_snum 컬럼의 값이 1 인 'HIT' 상품만 입력한 것임. 
insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '스마트TV', 1, '삼성', 'tv_samsung_h450_1.png','tv_samsung_h450_2.png', 100,1200000,800000, 1,'42인치 스마트 TV. 기능 짱!!', 50);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북', 1, '엘지', 'notebook_lg_gt50k_1.png','notebook_lg_gt50k_2.png', 150,900000,750000, 1,'노트북. 기능 짱!!', 30);  

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '바지', 2, 'S사', 'cloth_canmart_1.png','cloth_canmart_2.png', 20,12000,10000, 1,'예뻐요!!', 5);       

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '남방', 2, '버카루', 'cloth_buckaroo_1.png','cloth_buckaroo_2.png', 50,15000,13000, 1,'멋져요!!', 10);       
       
insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '보물찾기시리즈', 3, '아이세움', 'book_bomul_1.png','book_bomul_2.png', 100,35000,33000, 1,'만화로 보는 세계여행', 20);       
       
insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '만화한국사', 3, '녹색지팡이', 'book_koreahistory_1.png','book_koreahistory_2.png', 80,130000,120000, 1,'만화로 보는 이야기 한국사 전집', 60);
       
commit;

-- 아래는 fk_cnum 컬럼의 값이 1 인 '전자제품' 중 fk_snum 컬럼의 값이 1 인 'HIT' 상품만 입력한 것임. 
insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북1', 1, 'DELL', '1.jpg','2.jpg', 100,1200000,1000000,1,'1번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북2', 1, '에이서','3.jpg','4.jpg',100,1200000,1000000,1,'2번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북3', 1, 'LG전자','5.jpg','6.jpg',100,1200000,1000000,1,'3번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북4', 1, '레노버','7.jpg','8.jpg',100,1200000,1000000,1,'4번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북5', 1, '삼성전자','9.jpg','10.jpg',100,1200000,1000000,1,'5번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북6', 1, 'HP','11.jpg','12.jpg',100,1200000,1000000,1,'6번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북7', 1, '레노버','13.jpg','14.jpg',100,1200000,1000000,1,'7번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북8', 1, 'LG전자','15.jpg','16.jpg',100,1200000,1000000,1,'8번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북9', 1, '한성컴퓨터','17.jpg','18.jpg',100,1200000,1000000,1,'9번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북10', 1, 'MSI','19.jpg','20.jpg',100,1200000,1000000,1,'10번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북11', 1, 'LG전자','21.jpg','22.jpg',100,1200000,1000000,1,'11번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북12', 1, 'HP','23.jpg','24.jpg',100,1200000,1000000,1,'12번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북13', 1, '레노버','25.jpg','26.jpg',100,1200000,1000000,1,'13번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북14', 1, '레노버','27.jpg','28.jpg',100,1200000,1000000,1,'14번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북15', 1, '한성컴퓨터','29.jpg','30.jpg',100,1200000,1000000,1,'15번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북16', 1, '한성컴퓨터','31.jpg','32.jpg',100,1200000,1000000,1,'16번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북17', 1, '레노버','33.jpg','34.jpg',100,1200000,1000000,1,'17번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북18', 1, '레노버','35.jpg','36.jpg',100,1200000,1000000,1,'18번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북19', 1, 'LG전자','37.jpg','38.jpg',100,1200000,1000000,1,'19번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북20', 1, 'LG전자','39.jpg','40.jpg',100,1200000,1000000,1,'20번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북21', 1, '한성컴퓨터','41.jpg','42.jpg',100,1200000,1000000,1,'21번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북22', 1, '에이서','43.jpg','44.jpg',100,1200000,1000000,1,'22번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북23', 1, 'DELL','45.jpg','46.jpg',100,1200000,1000000,1,'23번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북24', 1, '한성컴퓨터','47.jpg','48.jpg',100,1200000,1000000,1,'24번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북25', 1, '삼성전자','49.jpg','50.jpg',100,1200000,1000000,1,'25번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북26', 1, 'MSI','51.jpg','52.jpg',100,1200000,1000000,1,'26번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북27', 1, '애플','53.jpg','54.jpg',100,1200000,1000000,1,'27번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북28', 1, '아수스','55.jpg','56.jpg',100,1200000,1000000,1,'28번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북29', 1, '레노버','57.jpg','58.jpg',100,1200000,1000000,1,'29번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북30', 1, '삼성전자','59.jpg','60.jpg',100,1200000,1000000,1,'30번 노트북', 60);

commit;


-- 아래는 fk_cnum 컬럼의 값이 1 인 '전자제품' 중 fk_snum 컬럼의 값이 2 인 'NEW' 상품만 입력한 것임. 
insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북31', 1, 'MSI','61.jpg','62.jpg',100,1200000,1000000,2,'31번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북32', 1, '삼성전자','63.jpg','64.jpg',100,1200000,1000000,2,'32번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북33', 1, '한성컴퓨터','65.jpg','66.jpg',100,1200000,1000000,2,'33번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북34', 1, 'HP','67.jpg','68.jpg',100,1200000,1000000,2,'34번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북35', 1, 'LG전자','69.jpg','70.jpg',100,1200000,1000000,2,'35번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북36', 1, '한성컴퓨터','71.jpg','72.jpg',100,1200000,1000000,2,'36번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북37', 1, '삼성전자','73.jpg','74.jpg',100,1200000,1000000,2,'37번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북38', 1, '레노버','75.jpg','76.jpg',100,1200000,1000000,2,'38번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북39', 1, 'MSI','77.jpg','78.jpg',100,1200000,1000000,2,'39번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북40', 1, '레노버','79.jpg','80.jpg',100,1200000,1000000,2,'40번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북41', 1, '레노버','81.jpg','82.jpg',100,1200000,1000000,2,'41번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북42', 1, '레노버','83.jpg','84.jpg',100,1200000,1000000,2,'42번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북43', 1, 'MSI','85.jpg','86.jpg',100,1200000,1000000,2,'43번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북44', 1, '한성컴퓨터','87.jpg','88.jpg',100,1200000,1000000,2,'44번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북45', 1, '애플','89.jpg','90.jpg',100,1200000,1000000,2,'45번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북46', 1, '아수스','91.jpg','92.jpg',100,1200000,1000000,2,'46번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북47', 1, '삼성전자','93.jpg','94.jpg',100,1200000,1000000,2,'47번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북48', 1, 'LG전자','95.jpg','96.jpg',100,1200000,1000000,2,'48번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북49', 1, '한성컴퓨터','97.jpg','98.jpg',100,1200000,1000000,2,'49번 노트북', 60);

insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, pqty, price, saleprice, fk_snum, pcontent, point)
values(seq_tbl_product_pnum.nextval, '노트북50', 1, '레노버','99.jpg','100.jpg',100,1200000,1000000,2,'50번 노트북', 60);

commit;        

select *
from tbl_product
order by pnum desc;

select * from tbl_category

select pnum, pname, code, pcompany, pimage1, pimage2, pqty, price, saleprice, sname, pcontent, point, pinputdate
 from
     (
        select row_number() over(order by P.pnum desc) AS RNO
             , P.pnum, P.pname, C.code, P.pcompany, P.pimage1, P.pimage2, P.pqty, P.price, P.saleprice, S.sname, P.pcontent, P.point 
             , to_char(P.pinputdate, 'yyyy-mm-dd') AS pinputdate
        from tbl_product P
        JOIN tbl_category C
        ON P.fk_cnum = C.cnum
        JOIN tbl_spec S
        ON P.fk_snum = S.snum
        where S.sname = 'HIT'
     ) V
     where V.RNO between 1 and 8; --1페이지

----- >>> 하나의 제품속에 여러개의 이미지 파일 넣어주기 <<< ------ 
select *
from tbl_product
order by pnum;  

create table tbl_product_imagefile
(imgfileno     number         not null   -- 시퀀스로 입력받음.
,fk_pnum       number(8)      not null   -- 제품번호(foreign key)
,imgfilename   varchar2(100)  not null   -- 제품이미지파일명
,constraint PK_tbl_product_imagefile primary key(imgfileno)
,constraint FK_tbl_product_imagefile foreign key(fk_pnum) references tbl_product(pnum) on delete cascade 
);


create sequence seqImgfileno
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

select imgfileno, fk_pnum, imgfilename
from tbl_product_imagefile
order by imgfileno desc;

seq_tbl_product_pnum.nextval

--- *** 시퀀스 seq_tbl_product_pnum 이 다음번에 사용될 번호를 알아오고자 한다. *** ---

select *
from tbl_product;

select last_number
from user_sequences
where sequence_name = 'SEQ_TBL_PRODUCT_PNUM';

--- *** 시퀀스 seq_tbl_product_pnum 에서 채번 한다. *** ---
select SEQ_TBL_PRODUCT_PNUM.nextval
from dual;

--- *** 시퀀스 seq_tbl_product_pnum 이 현재 사용중인 번호를 알아오고자 한다. *** ---
select SEQ_TBL_PRODUCT_PNUM.currval
from dual;


select *
from tbl_product
order by pnum desc;

select imgfileno, fk_pnum, imgfilename
from tbl_product_imagefile
order by imgfileno desc;


select *
from tbl_product
where pnum = 63;


select *
from tbl_product_imagefile
where fk_pnum = 63;

select *
from tbl_product P JOIN tbl_product_imagefile I
ON P.pnum = I.fk_pnum
where p.pnum = 63;


select *
from tbl_product
where pnum = 36;

select *
from tbl_product_imagefile
where fk_pnum = 36;