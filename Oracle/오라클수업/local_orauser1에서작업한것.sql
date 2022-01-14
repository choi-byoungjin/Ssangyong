show user;

select *
from HR.view_emp_3080;

--- === *** 시노님(Synonym, 동의어) *** === ---
create or replace synonym emp for HR.view_emp_3080;
/*
오류 보고 -
ORA-01031: insufficient privileges
*/

select *
from HR.view_emp_3080;

select *
from emp;


--- *** 생성되어진 시노님(Synonym, 동의어)을 조회해본다. *** ---

select *
from user_synonyms;

/*
    ----------------------------------------------------------
    SYNONYM_NAME   TABLE_OWNER      TABLE_NAME      DB_LINK
    ----------------------------------------------------------
         EMP	       HR	      VIEW_EMP_3080	    (NULL)
*/