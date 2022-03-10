
------- ***** JSPServletBegin/chap05.oracle 에서 작업한 것 ***** -------

show user;
-- USER이(가) "SYS"입니다.

create user jspbegin_user identified by cclass default tablespace users; -- cclass 는 암호
-- User JSPBEGIN_USER이(가) 생성되었습니다.

grant connect, resource, create view, unlimited tablespace to jspbegin_user;
-- Grant을(를) 성공했습니다.

show user;
-- USER이(가) "JSPBEGIN_USER"입니다.

