show user;
-- USER이(가) "HR"입니다.

select * from dba_users;
-- ORA-00942: table or view does not exist
-- dba_users 은 관리자만 조회할 수 있는 것이지 일반 사용자인 hr 은 조회가 불가하다는 말이다.


-- //////////////////////////////////////////////////////////////// --

/*
   ORACLE 은 관계형 데이터베이스(Relation DataBase) 관리 시스템(Management System) 이다. 
   
   RDBMS(Relation DataBase Management System)란 ?
   ==> 데이터를 열(Column, Field)과 행(Row, Record, Tuple) 으로 이루어진 테이블(Table, Entity(개체), Relation) 형태로  
       저장해서 관리하는 시스템을 말한다.
*/

-- ** 현재 오라클 서버에 접속되어진 사용자(지금은 hr)가 만든(소유의) 테이블(Table) 목록을 조회하겠다.  
select * 
from tab;
/*
----------------------------
TNAME               TABTYPE
----------------------------
COUNTRIES	        TABLE
DEPARTMENTS	        TABLE
EMPLOYEES	        TABLE
EMP_DETAILS_VIEW	VIEW   (VIEW 는 테이블은 아니지만 select 되어진 결과물을 마치 테이블 처럼 보는것) 
JOBS	            TABLE
JOB_HISTORY	        TABLE
LOCATIONS	        TABLE
REGIONS	            TABLE
*/

-- sql 명령어는 대,소문자를 구분하지 않습니다.
-- 테이블명과 컬럼명도 대,소문자를 구분하지 않습니다.
SELECT *
FROM DEPARTMENTS;

select *
from departments;

SELECT *
FROM departments;

SElecT *
FroM deparTMents;

select DEPARTMENT_ID, DEPARTMENT_NAME
from departments;

select department_id, department_name
from departments;

select depARTMent_id, deparTMENT_NAMe
from departments;

SELECT department_id, department_name
FROM departments;

--- !!! 저장되어진 데이터 값 만큼은 반드시 대,소문자를 구분합니다. !!! ---
SELECT * 
FROM departments
WHERE department_name = 'IT'; -- 조회되어서 나온다.

SELECT * 
FROM departments
WHERE department_name = 'it'; -- 아무것도 안나온다.

SELECT * 
FROM departments
WHERE department_name = 'It'; -- 아무것도 안나온다.

SELECT * 
FROM departments
WHERE department_name = 'Sales';

-------------------------------------------------------------------------

--- 어떤 테이블의 컬럼의 구성을 알고자 한다라면 아래와 같이 하면 됩니다.
describe departments;  -- departments 테이블의 컬럼(column)의 정보를 알려주는 것이다.
-- 또는
desc departments;  -- "부서" 테이블
/*
이름              널?       유형           
--------------- -------- ------------ 
DEPARTMENT_ID   NOT NULL NUMBER(4)    
DEPARTMENT_NAME NOT NULL VARCHAR2(30) 
MANAGER_ID               NUMBER(6)    
LOCATION_ID              NUMBER(4)
*/

select *
from employees;  -- "사원" 테이블 

desc employees;
/*
    이름                                   널?        유형           
    --------------                        --------  ------------ 
    EMPLOYEE_ID    (사원번호)              NOT NULL   NUMBER(6)      -999999 ~ 999999 이내의 값만 들어온다.   
    FIRST_NAME     (이름)                            VARCHAR2(20)   문자열 최대 20 byte 까지만 들어온다.
    LAST_NAME      (성)                   NOT NULL   VARCHAR2(25)   문자열 최대 25 byte 까지만 들어온다.
    EMAIL          (이메일)               NOT NULL    VARCHAR2(25) 
    PHONE_NUMBER   (연락처)                           VARCHAR2(20) 
    HIRE_DATE      (입사일자)             NOT NULL    DATE           날짜만 들어온다.
    JOB_ID         (직종ID)               NOT NULL    VARCHAR2(10) 
    SALARY         (기본급여)                         NUMBER(8,2)     -999999.99 ~ 999999.99
    COMMISSION_PCT (커미션[수당]퍼센티지)               NUMBER(2,2)    -0.99 ~ 0.99
    MANAGER_ID     (직속상관[사수]의 사원번호)          NUMBER(6)    
    DEPARTMENT_ID  (해당사원이 근무하는 부서번호)        NUMBER(4) 
*/

select *
from LOCATIONS;  -- 부서의 위치정보를 알려주는 테이블 

select *
from COUNTRIES;  -- 국가정보를 알려주는 테이블 

select * 
from REGIONS;    --  대륙정보를 알려주는 테이블 

-----------------------------------------------------------------------------

/*
   ==== 아주아주아주아주아주아주아주아주아주아주아주아주아주 중요 !!!!!!!! ====
   ==== !!!! 필수 암기 !!!! ====
   
   ==== 어떠한 테이블(또는 뷰)에서 데이터 정보를 꺼내와 보는 명령어인 select 의 처리 순서 ==== 
   
   select 컬럼명, 컬럼명, ..  -- 5  컬럼명 대신에 *(아스테리크) 을 쓰면 모든 컬럼을 뜻하는 것이다.  
   from 테이블명(또는 뷰명)   -- 1
   where 조건절              -- 2  where 조건절이 뜻하는 것은 해당 테이블명(또는 뷰명)에서 조건에 만족하는 행(row)을 메모리(RAM)에 로딩(올리는것)을 해주는 것이다. 
   group by 절              -- 3 
   having 그룹함수조건절      -- 4
   order by 절              -- 6 
   
*/

------------------------------------------------------------------------------

----- *** NULL 을 처리해주는 함수 *** -----
----- NULL 은 존재하지 않는 것이므로 4칙연산(+ - * /)에 NULL 이 포함되어지면 그 결과는 무조건 NULL 이 된다.  

-- 1. NVL

   select '안녕하세요'
   from dual; -- dual 은 select 다음에 나오는 값들을 화면에 보여주기 위한 용도로 쓰이는 가상테이블이다. 

   select 1+2, 2-1, 3*2, 5/2,
          1+null, 2-null, 0*null, 5/null
   from dual;
   
   select nvl(7,3), nvl(null,3),
          nvl('이순신','거북선'), nvl(null, '거북선')
   from dual;
   
   
-- 2. NVL2   
   
   select nvl2(7,3,2), nvl2(null,3,2),
          nvl2('이순신','거북선','구국영웅'), nvl2(null, '거북선','구국영웅')
   from dual;
   

   -- employees(직원) 테이블에서 부서번호(department_id) 60번에 근무하는 사원들만
   -- 사원번호, 사원명, 기본급여, 커미션퍼센티지, 부서번호를 나타내세요.
   select employee_id, first_name, last_name, salary, commission_pct, department_id  
   from employees 
   where department_id = 60;  -- where 절이 있으므로 department_id 컬럼의 값이 60인 행들만 메모리(RAM)에 올라간다. 
   
   
   -- employees(직원) 테이블에서 모든 사원들에 대해
   -- 사원번호, 사원명, 기본급여, 커미션퍼센티지, 부서번호를 나타내세요.
   select employee_id, first_name, last_name, salary, commission_pct, department_id
   from employees;
   -- where 절이 없으므로 employees 테이블에 있는 모든 행들이 메모리(RAM)에 올라간다. 
   
   -- commission_pct 컬럼이 뜻하는 바는 다음과 같다.
   -- commission_pct 컬럼의 값이 null 이라면 수당이 존재하지 않는다는 말이고,
   -- commission_pct 컬럼의 값이 0.3 이라면 salary(기본급여)의 30%가 수당이 된다는 말이다.
   -- SALALRY   COMMISSION_PCT   SALALRY*COMMISSION_PCT
   --  1000         0.3                 300
   --  2000         0.3                 600
   --  4000         0.3                1200
   --  5000         null               null 
   
   
   -- employees(직원) 테이블에서 모든 사원들에 대해
   -- 사원번호, 사원명, 기본급여, 커미션퍼센티지, 월급, 부서번호를 나타내세요.
   --> "월급" 은 기본급여 + 수당액을 합친것을 말한다. 
   
   select employee_id, first_name, last_name, salary, commission_pct, 
          salary + (salary * commission_pct), department_id
   from employees; -- 잘못구한것
   
   select employee_id, first_name, last_name, salary, commission_pct, 
          NVL( salary + (salary * commission_pct), salary), department_id
   from employees; -- 올바르게 구한것
   
   select employee_id, first_name, last_name, salary, commission_pct, 
          NVL( salary + (salary * commission_pct), salary), 
          NVL2( commission_pct, salary + (salary * commission_pct), salary),   
          department_id
   from employees; -- 올바르게 구한것
   
   
   --- ***  컬럼에 대해 별칭(Alias) 부여하기 *** ---
   select employee_id AS "사원번호" 
        , first_name "이름"  -- 별칭(Alias) 에서 AS 는 생략가능함.
        , last_name 성       -- 별칭(Alias) 에서 "" 는 생략가능함.
        , salary "기본 급여"  -- 별칭(Alias) 에서 공백을 주고자 한다라면 반드시 "" 로 해주어야한다.
        , commission_pct 커미션퍼센티지
        , NVL( salary + (salary * commission_pct), salary) 월급1
        , NVL2( commission_pct, salary + (salary * commission_pct), salary) 월급2
        , department_id 부서번호
   from employees;
   
   
   ------------------ **** 비교연산자 **** ------------------ 
   
   1. 같다                    = 
   2. 같지않다                !=  <>  ^= 
   3. 크다. 작다              >   <
   4. 같거나크다. 같거나작다    >=       <= 
   5. NULL 은 존재하지 않는 것이므로 비교대상이 될 수가 없다.
      그러므로 비교연산( =  != <> ^= >  <  >=  <= )을 할수가 없다.
      그래서 비교연산을 하려면 nvl()함수, nvl2()함수를 사용하여 처리한다. 

    -- 오라클에서 컬럼들을 붙일때(연결할때)는 문자 타입이든 숫자 타입이든 날짜 타입이든 관계없이 || 를 쓰면 된다. \
    select sysdate  -- 현재날짜시각을 알려주는 것
    from dual;
    
    select '서울시' || '' || '마포구 쌍용강북교육센터', 001234
    from dual;

    -- employees(직원) 테이블에서 부서번호(department_id) 60번에 근무하는 사원들만
    -- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
    select employee_id 사원번호
         , first_name || last_name 사원명
         , NVL( salary + (salary * commission_pct), salary) 월급
         , department_id 부서번호
    from employees
    where department_id = 60;
    
    desc employees;
    
    select first_name, department_id, nvl(department_id, -9999)
    from employees;
    
    select department_id
    from departments;  -- "부서" 테이블
    
    -- employees(직원) 테이블에서 부서번호(department_id) NULL인 사원들만
    -- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
    select employee_id 사원번호
         , first_name || last_name 사원명
         , NVL( salary + (salary * commission_pct), salary) 월급
         , department_id 부서번호
    from employees
    where nvl(department_id, -9999) = -9999;
    
    -- 또는
    
    select employee_id 사원번호
         , first_name || last_name 사원명
         , NVL( salary + (salary * commission_pct), salary) 월급
         , department_id 부서번호
    from employees
    where department_id is null; -- NULL 은 is 를 사용하여 구한다.
                                 -- department_id 컬럼의 값이 null 인 행들만 메모리(RAM)에 퍼올리는 것이다.
                                 
    -- employees(직원) 테이블에서 부서번호(department_id) 60번에 근무하지 않는 사원들만
    -- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
    select employee_id 사원번호
         , first_name || last_name 사원명
         , NVL( salary + (salary * commission_pct), salary) 월급
         , department_id 부서번호
    from employees
    where nvl(department_id, -9999) != 60 -- 아래는 60만 제외하지않고 null도 제외한다 테이블 구조에서 null을 허용하는 컬럼인가 확인이 필요함
--  where department_id != 60;
--  where department_id <> 60;
--  where department_id ^= 60

    -- employees(직원) 테이블에서 부서번호(department_id)가 NULL 이 아닌 사원들만
    -- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
    select employee_id 사원번호
         , first_name || last_name 사원명
         , NVL( salary + (salary * commission_pct), salary) 월급
         , department_id 부서번호
    from employees
    where nvl(department_id, -9999) != -9999;
    
    -- 또는
    
    select employee_id 사원번호
         , first_name || last_name 사원명
         , NVL( salary + (salary * commission_pct), salary) 월급
         , department_id 부서번호
    from employees
    where department_id is not null;
    
    -- 또는
    
    select employee_id 사원번호
         , first_name || last_name 사원명
         , NVL( salary + (salary * commission_pct), salary) 월급
         , department_id 부서번호
    from employees
    where not department_id is null;
    
    -- 또는
    
    select employee_id 사원번호
         , first_name || last_name 사원명
         , NVL( salary + (salary * commission_pct), salary) 월급
         , department_id 부서번호
    from employees
    where not (department_id is null);
    
--------------------------------------------------------------------------------

 --- *** select 되어져 나온 데이터를 정렬(오름차순정렬, 내릴차순정렬)하여 보여주기 *** ---
    select employee_id, first_name, last_name, salary, department_id
    from employees
    order by salary asc -- asc 은 생략가능하다.
    
 -- employees(직원) 테이블에서 부서번호(department_id)가 NULL 이 아닌 사원들만
 -- 사원번호, 사원명, 월급, 부서번호를 나타내는데 월급의 내림차순으로 보여주세요.
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 where department_id is not null
 order by 월급 desc
 
 -- 또는
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 where department_id is not null
 order by 3 desc
 
 -- employees(직원) 테이블에서 부서번호(department_id)가 NULL 이 아닌 사원들만
 -- 사원번호, 사원명, 월급, 부서번호를 나타내는데 
 -- 1차로 부서번호의 오름차순으로 정렬한 후,
 -- 동일한 부서번호 내에서는 월급의 내림차순으로 보여주세요.
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 where department_id is not null
 order by 부서번호 asc ,월급 desc
 
 -- 또는
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 where department_id is not null
 order by 4 asc ,3 desc
 
 -- employees(직원) 테이블에서 부서번호(department_id)가 NULL 이 아닌 사원들만
 -- 사원번호, 사원명, 월급, 부서번호를 나타내는데 
 -- 1차로 부서번호의 오름차순으로 정렬한 후,
 -- 동일한 부서번호 내에서는 월급의 내림차순으로 보여주세요.
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 order by 부서번호, 월급 desc;
 -- 오라클에서 null 은 존재하지 않는 것이므로 정렬시 가장 큰것으로 간주한다.
 -- 그러므로 오름차순 정렬시 null 은 맨 뒤에 나오고,
 -- 내림차순 정렬시 null 은 맨 처음에 나온다.
 /*
    참고로 Microsoft 사에서 만든 MS-SQL 서버에서는 null 은 정렬시 가장 작은 것으로 간주하기에
    오라클과 반대로 나온다. 
    즉, 오름차순 정렬시 null 은 맨 처음에 나오고, 내림차순 정렬시 null 은 맨 뒤에 나온다.    
 */
 
 /*
    employees 테이블에서 수당퍼센티지가 null 인 사원들만
    사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되
    먼저 부서번호의 오름차순으로 정렬한 후 동일한 부서번호 내에서는 월급의 내림차순으로 
    나타내세요
 */
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 where commission_pct is null
 order by 부서번호, 월급 desc;
 
 /*
    employees 테이블에서 부서번호가 50번 부서에 근무하지 않는 사원들만
    사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되
    먼저 부서번호의 오름차순으로 정렬한 후 동일한 부서번호 내에서는 월급의 내림차순으로 
    나타내세요
 */
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 where department_id = 50
 order by 부서번호, 월급 desc
 
 /*
    employees 테이블에서 월급이 10000 이상인 사원들만
    사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되
    먼저 부서번호의 오름차순으로 정렬한 후 동일한 부서번호 내에서는 월급의 내림차순으로 
    나타내세요
 */
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 where nvl(salary + (salary * commission_pct), salary) > 10000 -- '월급 > 10000' 불가
 order by 부서번호, 월급 desc
 
 
 -------- *** AND OR IN() NOT 연산자 *** --------
 /*
    employees 테이블에서 80번 부서에 근무하는
    사원들만 사원번호, 사원명, 기본급여, 부서번호를 나타내세요.
 */
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   nvl(salary + (salary * commission_pct), salary) 월급
    ,   department_id 부서번호
 from employees
 where department_id = 80 and
       salary >= 10000;

/*
    employees 테이블에서 30번, 60번, 80번 부서에 근무하는 사원들중에 기본급여가 10000 이상인
    사원들만 사원번호, 사원명, 기본급여, 부서번호를 나타내세요.
 */
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   salary 기본급여
    ,   department_id 부서번호
 from employees
 where department_id = 30 or
       department_id = 60 or
       department_id = 80 
 order by 부서번호 asc;

 -- 또는
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   salary 기본급여
    ,   department_id 부서번호
 from employees
 where department_id in (30,60,80) 
 order by 부서번호 asc;
 
 
 /*
    employees 테이블에서 30번, 60번, 80번 부서에 근무하지 않는
    사원들만 사원번호, 사원명, 기본급여, 부서번호를 나타내세요.
 */
 
select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   salary 기본급여
    ,   department_id 부서번호
 from employees
 where department_id != 30 and
       department_id != 60 and
       department_id != 80 
 order by 부서번호 asc;
 
 -- null 제외되지 않게 작성
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   salary 기본급여
    ,   department_id 부서번호
 from employees
 where NVL(department_id, -9999) != 30 and
       NVL(department_id, -9999) != 60 and
       NVL(department_id, -9999) != 80 
 order by 부서번호 asc;

 -- 또는
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   salary 기본급여
    ,   department_id 부서번호
 from employees
 where nvl(department_id, -9999) not in (30,60,80) -- in은 or
 order by 부서번호 asc;
 
 -- 또는
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   salary 기본급여
    ,   department_id 부서번호
 from employees
 where not nvl(department_id, -9999) in (30,60,80) -- in은 or
 order by 부서번호 asc;
 
 /*
    [퀴즈]
   -- employees 테이블에서 부서번호가 30, 50, 60번 부서에 근무하는 사원들중에 
   -- 연봉(월급*12)이 20000 이상 60000 이하인 사원들만 
   -- 사원번호, 사원명, 연봉(월급*12), 부서번호를 나타내되 
   -- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 연봉의 내림차순으로 나타내세요
 */
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   (nvl(salary + (salary * commission_pct), salary)) * 12 연봉
    ,   department_id 부서번호
 from employees
 where department_id = 30 or department_id = 50 or department_id = 60
    and (nvl(salary + (salary * commission_pct), salary)) * 12 >= 20000
    and (nvl(salary + (salary * commission_pct), salary)) * 12 <= 60000
 order by 부서번호, 연봉 desc --> 틀린풀이이다.
 
 /*
    !!! and 와 or 가 혼용되어지면 우선순위는 and 가 먼저 실행된다.
    연산자에 있어서 가장 최우선은 괄호( ) 가 제일 우선한다.
 */
 
 select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   (nvl(salary + (salary * commission_pct), salary)) * 12 연봉
    ,   department_id 부서번호
 from employees
 where (department_id = 30 or department_id = 50 or department_id = 60)
    and (nvl(salary + (salary * commission_pct), salary)) * 12 >= 20000
    and (nvl(salary + (salary * commission_pct), salary)) * 12 <= 60000
 order by 부서번호, 연봉 desc --> 옳은풀이
 
 -- 또는
 
  select employee_id 사원번호
    ,   first_name || ' ' || last_name 사원명
    ,   (nvl(salary + (salary * commission_pct), salary)) * 12 연봉
    ,   department_id 부서번호
 from employees
 where department_id in (30,50,60)
    and (nvl(salary + (salary * commission_pct), salary)) * 12 >= 20000
    and (nvl(salary + (salary * commission_pct), salary)) * 12 <= 60000
 order by 부서번호, 연봉 desc --> 옳은풀이
                            --> IN() 은 괄호가 있는 OR 이다.
 -- 대용량 데이터는 in 보다 or이 더 빠르다
 
 
 ---- *** == 현재시각을 알려주는 것 == *** ----
 select sysdate, current_date, 
        localtimestamp, current_timestamp,
        systimestamp
 from dual;
 
 /*
   날짜타입은 date 이다.
   date 타입의 기본적인 표현방식은 'RR/MM/DD' 으로 나타내어진다.
   RR 은 년도의 2자리만 나타내어주는데 50 ~ 99 는  1950 ~ 1999 을 말하는 것이다.
   RR 은 년도의 2자리만 나타내어주는데 00 ~ 49 는  2000 ~ 2049 을 말하는 것이다.
   MM 은 월이고, DD 는 일이다.
*/
select sysdate 
    ,  to_char(sysdate, 'yyyy-mm-dd am hh:mi:ss')
    ,  to_char(sysdate, 'yyyy-mm-dd pm hh:mi:ss')
    ,  to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss')
    ,  to_char(sysdate, 'yyyy/mm/dd hh24:mi:ss')
from dual;

desc employees;
-- HIRE_DATE 컬럼의 타입은 DATE(날짜)이다.

select employee_id 사원번호
    ,  first_name || ' ' || last_name 사원명
    ,  hire_date 입사일자1 -- 'RR/MM/DD'
    ,  to_char(hire_date, 'yyyy-mm-dd am hh24:mi:ss') 입사일자2
from employees;

-- employees 테이블에서 employee_id 컬럼의 값이 154 인 사원의 hire_date 컬럼의 값을
-- '2006-12-31 09:00:00' 으로 변경하겠다.

update employees set hire_date = '2006-12-31 09:00:00' -- = 은 대입한다는 말이다.
where employee_id = 154; -- employee_id = 154 의 = 은 갇다는 말이다.
/*
    오류 보고 -
    ORA-01861: literal does not match format string
*/


update employees set hire_date = to_date('2006-12-31 09:00:00', 'yyyy-mm-dd hh24:mi:ss')
where employee_id = 154; 
-- 1 행 이(가) 업데이트되었습니다.

commit;
-- 커밋 완료.

-- employees 테이블에서 입사일자가 2005년 1월 1일 부터 2006년 12월 31일 까지 입사한 사원들만 
-- 사원번호, 사원명, 입사일자를 나타내세요.

select employee_id 사원번호
    ,  first_name || ' ' || last_name 사원명
    ,  hire_date 입사일자1 -- 'RR/MM/DD'
    ,  to_char(hire_date, 'yyyy-mm-dd am hh24:mi:ss') 입사일자2
from employees
where '05/01/01' <= hire_date and hire_date <= '06/12/31'
order by 입사일자1 asc -- 틀린풀이

--- !!!!! 중요 !!!!  날짜를 나타낼때 시,분,초 가 없는 년,월,일만 나타내어주면 자동적으로 0시0분0초가 된다.
---                 즉, 자정(그날의 시작)을 뜻한다.

select employee_id 사원번호
    ,  first_name || ' ' || last_name 사원명
    ,  hire_date 입사일자1 -- 'RR/MM/DD'
    ,  to_char(hire_date, 'yyyy-mm-dd am hh24:mi:ss') 입사일자2
from employees
where '05/01/01' < hire_date and hire_date < '07/01/01'
order by 입사일자1 asc -- 올바른풀이


'A' --> 65
'a' --> 97
'0' --> 48
' ' --> 32
select ascii('A'), ascii('a'), ascii('0'), ascii(' ')
from dual;
-- 65	97	48	32

select chr(65), chr(97), chr(48), chr(32)
from dual;\

-- employees 테이블에서 first_name 컬럼의 값이 'Elj' 부터 'I' 까지인 데이터를 가지는 
 -- 사원들만 first_name 을 출력하세요.
 select first_name 
 from employees
 where 'Elj' <= first_name and first_name <= 'I'
 order by 1;
 -- 'Elj' 'Elja' 'EljaSt' 'Eljxafdsff'~~~~~~~~~~ 'HAsdf' 'HAysfdsf' 'I' / 'IA' 'IAsfsfd'
 
 select first_name 
 from employees
 where first_name between 'Elj' and 'I'
 order by 1;
 
 /*
       대용량 데이터베이스인 경우 IN 연산자 보다는 OR 를 사용하기를 권장하고,
       대용량 데이터베이스인 경우 between A and B 보다는 >= and <= 을 사용할 것을 권장한다.
       왜냐하면 IN 연산자는 내부적으로 OR 로 변경된 후 실행되고, 
       between A and B 도 내부적으로 >= and <= 으로 변경된 후 실행되기 때문이다. 
       
       -- 대용량 데이터베이스의 기준은 어떤 테이블의 행의 개수가 100만건을 넘을 경우를 말한다.
       -- 소규모 데이터베이스의 기준은 어떤 테이블의 행의 개수가 1만건 미만인 경우를 말한다.
 */
 
 
   ------ *** employees 테이블에 jubun(주민번호) 이라는 컬럼을 추가해봅니다. *** --------
   /*
       jubun(주민번호) 컬럼의 값을 입력할때는 '-' 는 빼고 숫자로만 입력할 것입니다.
       jubun(주민번호) 컬럼의 값을 입력할 때 맨 처음 첫자리에 0 이 들어올 수 있다라면 
       number 타입이 아니라 varchar2 타입으로 해야 한다.
       왜냐하면 number 타입으로 해주면 맨 앞에 입력한 값이 0 일때는 0이 생략 되어지기 때문이다.
       맨 앞의 0 도 나오게 하려면 컬럼의 데이터 타입은 varchar2 가 되어야 한다.
   */
   select 0105053234567, '0105054234567'
   from dual;
   --     105053234567	 0105054234567
   
   alter table employees
   add jubun varchar2(13);  -- employees 테이블에 jubun 이라는 컬럼을 추가해주는 것.
   -- Table EMPLOYEES이(가) 변경되었습니다.
   
   desc employees;
   
   select *
   from employees;
   
   update employees set jubun = '6010151234567'
   where employee_id = 100;
   -- 1 행 이(가) 업데이트되었습니다.
   
   rollback; 
   -- 롤백 완료.
   -- commit; 한 이후에 DML(Data Manuplation Language[데이터조작어] ==> insert, update, delete, merge) 명령어로 
   -- 변경되어진 것을 이전상태로 되돌리는 것.
   
   update employees set jubun = '6010151234567'
   where employee_id = 100;
   -- 1 행 이(가) 업데이트되었습니다.
   
   commit; 
   -- 커밋 완료.
   -- DML(Data Manuplation Language[데이터조작어] ==> insert, update, delete, merge) 명령어로 
   -- 변경되어진 것을 디스크에 적용시키는 것이다.
   -- commit; 한 이후로 rollback; 해봐야 이전상태로 되돌아 가지 않는다. 
 
   update employees set jubun = '8510151234567'
   where employee_id = 101;
   -- 1 행 이(가) 업데이트되었습니다.
   
   update employees set jubun = '6510151234567'
   where employee_id = 102;
   -- 1 행 이(가) 업데이트되었습니다.

   select *
   from employees;
   
   rollback;
   
   select *
   from employees;
   
   ------------------------------------------------------------
    update employees set jubun = '6010151234567'  
    where employee_id = 100;

    update employees set jubun = '8510151234567'
    where employee_id = 101;
    
    update employees set jubun = '6510152234567'
    where employee_id = 102;
    
    update employees set jubun = '7510151234567'
    where employee_id = 103;
    
    update employees set jubun = '6110152234567'
    where employee_id = 104;
    
    update employees set jubun = '6510151234567'
    where employee_id = 105;
    
    update employees set jubun = '6009201234567'
    where employee_id = 106;
    
    update employees set jubun = '0803153234567'
    where employee_id = 107;
    
    update employees set jubun = '0712154234567'
    where employee_id = 108;
    
    update employees set jubun = '8810151234567'
    where employee_id = 109;
    
    update employees set jubun = '8908152234567'
    where employee_id = 110;
    
    update employees set jubun = '9005052234567'
    where employee_id = 111;
    
    update employees set jubun = '6610151234567'
    where employee_id = 112;
    
    update employees set jubun = '6710151234567'
    where employee_id = 113;
    
    update employees set jubun = '6709152234567'
    where employee_id = 114;
    
    update employees set jubun = '6110151234567'
    where employee_id = 115;
    
    update employees set jubun = '6009301234567'
    where employee_id = 116;
    
    update employees set jubun = '6110152234567'
    where employee_id = 117;
    
    update employees set jubun = '7810151234567'
    where employee_id = 118;
    
    update employees set jubun = '7909151234567'
    where employee_id = 119;
    
    update employees set jubun = '7702152234567'
    where employee_id = 120;
    
    update employees set jubun = '7009151234567'
    where employee_id = 121;
    
    update employees set jubun = '7111011234567'
    where employee_id = 122;
    
    update employees set jubun = '8010131234567'
    where employee_id = 123;
    
    update employees set jubun = '8110191234567'
    where employee_id = 124;
    
    update employees set jubun = '9012132234567'
    where employee_id = 125;
    
    update employees set jubun = '9406251234567'
    where employee_id = 126;
    
    update employees set jubun = '9408252234567'
    where employee_id = 127;
    
    update employees set jubun = '9204152234567'
    where employee_id = 128;
    
    update employees set jubun = '8507251234567'
    where employee_id = 129;
    
    update employees set jubun = '6511111234567'
    where employee_id = 130;
    
    update employees set jubun = '0010153234567'
    where employee_id = 131;
    
    update employees set jubun = '0005254234567'
    where employee_id = 132;
    
    update employees set jubun = '0110194234567'
    where employee_id = 133;
    
    update employees set jubun = '0412154234567'
    where employee_id = 134;
    
    update employees set jubun = '0503253234567'
    where employee_id = 135;
    
    update employees set jubun = '9510012234567'
    where employee_id = 136;
    
    update employees set jubun = '9510021234567'
    where employee_id = 137;
    
    update employees set jubun = '9610041234567'
    where employee_id = 138;
    
    update employees set jubun = '9610052234567'
    where employee_id = 139;
    
    update employees set jubun = '7310011234567'
    where employee_id = 140;
    
    update employees set jubun = '7310092234567'
    where employee_id = 141;
    
    update employees set jubun = '7510121234567'
    where employee_id = 142;
    
    update employees set jubun = '7612012234567'
    where employee_id = 143;
    
    update employees set jubun = '7710061234567'
    where employee_id = 144;
    
    update employees set jubun = '7810052234567'
    where employee_id = 145;
    
    update employees set jubun = '6810251234567'
    where employee_id = 146;
    
    update employees set jubun = '6811062234567'
    where employee_id = 147;
    
    update employees set jubun = '6712052234567'
    where employee_id = 148;
    
    update employees set jubun = '6011251234567'
    where employee_id = 149;
    
    update employees set jubun = '6210062234567'
    where employee_id = 150;
    
    update employees set jubun = '6110191234567'
    where employee_id = 151;
    
    update employees set jubun = '5712062234567'
    where employee_id = 152;
    
    update employees set jubun = '5810231234567'
    where employee_id = 153;
    
    update employees set jubun = '6311051234567'
    where employee_id = 154;
    
    update employees set jubun = '6010182234567'
    where employee_id = 155;
    
    update employees set jubun = '6110191234567'
    where employee_id = 156;
    
    update employees set jubun = '6210112234567'
    where employee_id = 157;
    
    update employees set jubun = '6311132234567'
    where employee_id = 158;
    
    update employees set jubun = '8511112234567'
    where employee_id = 159;
    
    update employees set jubun = '8710131234567'
    where employee_id = 160;
    
    update employees set jubun = '8710072234567'
    where employee_id = 161;
    
    update employees set jubun = '9010171234567'
    where employee_id = 162;
    
    update employees set jubun = '9112072234567'
    where employee_id = 163;
    
    update employees set jubun = '9110241234567'
    where employee_id = 164;
    
    update employees set jubun = '9212251234567'
    where employee_id = 165;
    
    update employees set jubun = '9310232234567'
    where employee_id = 166;
    
    update employees set jubun = '9811151234567'
    where employee_id = 167;
    
    update employees set jubun = '9810252234567'
    where employee_id = 168;
    
    update employees set jubun = '9910301234567'
    where employee_id = 169;
    
    update employees set jubun = '0910153234567'
    where employee_id = 170;
    
    update employees set jubun = '1011153234567'
    where employee_id = 171;
    
    update employees set jubun = '1006153234567'
    where employee_id = 172;
    
    update employees set jubun = '1111154234567'
    where employee_id = 173;
    
    update employees set jubun = '1209103234567'
    where employee_id = 174;
    
    update employees set jubun = '1207154234567'
    where employee_id = 175;
    
    update employees set jubun = '0906153234567'
    where employee_id = 176;
    
    update employees set jubun = '0812113234567'
    where employee_id = 177;
    
    update employees set jubun = '9810132234567'
    where employee_id = 178;
    
    update employees set jubun = '8712111234567'
    where employee_id = 179;
    
    update employees set jubun = '8310012234567'
    where employee_id = 180;
    
    update employees set jubun = '6510191234567'
    where employee_id = 181;
    
    update employees set jubun = '6510221234567'
    where employee_id = 182;
    
    update employees set jubun = '6510232234567'
    where employee_id = 183;
    
    update employees set jubun = '8512131234567'
    where employee_id = 184;
    
    update employees set jubun = '8510182234567'
    where employee_id = 185;
    
    update employees set jubun = '7510192234567'
    where employee_id = 186;
    
    update employees set jubun = '8512192234567'
    where employee_id = 187;
    
    update employees set jubun = '9511151234567'
    where employee_id = 188;
    
    update employees set jubun = '7509302234567'
    where employee_id = 189;
    
    update employees set jubun = '8510161234567'
    where employee_id = 190;
    
    update employees set jubun = '9510192234567'
    where employee_id = 191;
    
    update employees set jubun = '0510133234567'
    where employee_id = 192;
    
    update employees set jubun = '0810194234567'
    where employee_id = 193;
    
    update employees set jubun = '0910183234567'
    where employee_id = 194;
    
    update employees set jubun = '1010134234567'
    where employee_id = 195;
    
    update employees set jubun = '9510032234567'
    where employee_id = 196;
    
    update employees set jubun = '9710181234567'
    where employee_id = 197;
    
    update employees set jubun = '9810162234567'
    where employee_id = 198;
    
    update employees set jubun = '7511171234567'
    where employee_id = 199;
    
    update employees set jubun = '7810172234567'
    where employee_id = 200;
    
    update employees set jubun = '7912172234567'
    where employee_id = 201;
    
    update employees set jubun = '8611192234567'
    where employee_id = 202;
    
    update employees set jubun = '7810252234567'
    where employee_id = 203;
    
    update employees set jubun = '7803251234567'
    where employee_id = 204;
    
    update employees set jubun = '7910232234567'
    where employee_id = 205;
    
    update employees set jubun = '8010172234567'
    where employee_id = 206;
    
    commit;
        
    select *
    from employees;
   
   -------------------------------------------------------------
   
   ------ ***** ===== like 연산자 ===== ***** -------
   select * 
   from employees
   where department_id = 30;
   
   select * 
   from employees
   where department_id like 30;
   
   /*
        like 연산자와 함께 사용되어지는 % 와 _ 를 wild character 라고 부른다.
        like 연산자와 함께 사용되어지는 % 의 뜻은 글자수와는 관계없이 글자가 있든지 없든지 관계없다라는 말이고,
        like 연산자와 함께 사용되어지는 _ 의 뜻은 반드시 아무글자 1개만을 뜻하는 것이다.
   */
   
   -- employees 테이블에서 여자 1990년생과 남자 1991년생의 사원들만 
   -- 사원번호, 사원명, 주민번호를 나타내세요.
   select employee_id AS "사원번호"
        , first_name || ' ' || last_name AS "사원명"
        , jubun AS "주민번호"
   from employees
   where jubun like '90____2%' OR 
         jubun like '91____1%';
         
   
   -- employees 테이블에서 first_name 컬럼의 값이 'J'로 시작하는 사원들만
   -- 사원번호, 이름, 성, 기본급여를 나타내세요.
   select employee_id, first_name, last_name, salary 
   from employees
   where first_name like 'J%';
         
         
   -- employees 테이블에서 first_name 컬럼의 값이 's'로 끝나는 사원들만
   -- 사원번호, 이름, 성, 기본급여를 나타내세요.
   select employee_id, first_name, last_name, salary 
   from employees
   where first_name like '%s';
   
   
   -- employees 테이블에서 first_name 컬럼의 값중에 'ee' 라는 글자가 들어있는 사원들만
   -- 사원번호, 이름, 성, 기본급여를 나타내세요.
   select employee_id, first_name, last_name, salary 
   from employees
   where first_name like '%ee%';
   
   
   -- employees 테이블에서 first_name 컬럼의 값중에 'e' 가 2개이상 들어있는 사원들만
   -- 사원번호, 이름, 성, 기본급여를 나타내세요.
   select employee_id, first_name, last_name, salary 
   from employees
   where first_name like '%e%e%';
   
   
   -- employees 테이블에서 last_name 컬럼의 값이 첫글자는 'F' 이고 두번째 글자는 아무거나 이고
   -- 세번째 글자는 소문자 'e' 이며 4번째 부터는 글자가 있든지 없든지 상관없는 사원들만 
   -- 사원번호, 이름, 성, 기본급여를 나타내세요. 
   select employee_id, first_name, last_name, salary 
   from employees
   where last_name like 'F_e%';
   
   
   -- *** like 연산자와 함께 사용되어지는 % 와 _ 인 wild character 의 기능을 escape(탈출) 시키기 *** -- 
   select * from tab;
   
  
   
   -- tbl_watch 테이블 속에 데이터를 입력해주는 것이다.--
   insert into tbl_watch(watch_name, bigo) values('금시계', '순금고급시계');
   -- 1 행 이(가) 삽입되었습니다. 
   
   select *
   from tbl_watch;
   
-- rollback;
   commit;
   
   insert into tbl_watch(watch_name, bigo) values('고급순은시계', '아주좋은시계');
  /*
   오류 보고 -
   ORA-12899: value too large for column "HR"."TBL_WATCH"."WATCH_NAME" (actual: 18, maximum: 10)
  */
   
   insert into tbl_watch(watch_name, bigo) values('은시계', '아주좋은시계');
   -- 1 행 이(가) 삽입되었습니다.
   
   commit;
   
   delete from tbl_watch;   -- tbl_watch 테이블에 저장된 모든 행들을 삭제시키는 것이다. 
   -- 2개 행 이(가) 삭제되었습니다. 
   
   select *
   from tbl_watch;
   
   rollback;
   
   delete from tbl_watch
   where watch_name = '금시계'; 
   -- 1 행 이(가) 삭제되었습니다.
   -- tbl_watch 테이블에 저장된 행들중에서 watch_name 컬럼의 값이 '금시계' 인 행을 삭제시키는 것이다.
   
   delete from tbl_watch;
   -- 2개 행 이(가) 삭제되었습니다.
   
   commit;
   
   
    drop table tbl_watch purge;
   -- Table TBL_WATCH이(가) 삭제되었습니다.
   
   create table tbl_watch
   (watch_name  varchar2(10)   -- watch_name 컬럼에 들어올 수 있는 데이터는 문자열 최대 10byte 까지만 허용한다. 
   ,bigo        Nvarchar2(100) -- bigo 컬럼에 들어올 수 있는 데이터는 문자열 최대 10글자 까지만 허용한다. 
   );
   -- Table TBL_WATCH이(가) 생성되었습니다.
   
   insert into tbl_watch(watch_name, bigo) values('금시계', '순금 99.99% 함유 고급시계');
   insert into tbl_watch(watch_name, bigo) values('은시계', '고객만족도 99.99점 획득한 고급시계');
   
   commit;
   
   -- tbl_watch 테이블에서 bigo 컬럼에 99.99% 라는 글자가 들어있는 행만 추출하세요 
   select *
   from tbl_watch
   where bigo like '%99.99\%%' escape '\';
   --  escape 문자로 '\' 을 주었으므로 '\' 다음에 나오는 % 1개만 wild character 기능에서 탈출시켜 버리므로 % 는 진짜 글자 퍼센트(%) 로 된다.

   select *
   from tbl_watch
   where bigo like '%99.99a%%' escape 'a';   
   
   select *
   from tbl_watch
   where bigo like '%99.991%%' escape '1';   
   
   -----------------------------------------------------------------------------
   
        ------ >> 단일행 함수 << ------
    /*
        ※ 단일행 함수의 종류
        
        1. 문자 함수
        2. 숫자 함수
        3. 날짜 함수
        4. 변환 함수
        5. 기타 함수
    */
    
    
    --------- >> 1. 문자 함수 << ---------
    
    -- 1.1 upper('문자열') ==> '문자열' 을 모두 대문자로 변환시켜주는 것.
    select 'kOreA SEoul', upper('kOreA SEoul')
    from dual;
    -- kOreA SEoul	    KOREA SEOUL 
   
   
    -- 1.2 lower('문자열') ==> '문자열' 을 모두 소문자로 변환시켜주는 것.
    select 'kOreA SEoul', lower('kOreA SEoul')
    from dual;
    -- kOreA SEoul	    korea seoul
    
    -- 1.3 initcap('문자열') ==> '문자열' 을 단어별(구분자 공백)로 첫글자만 대문자, 나머지는 모두 소문자로 변환시켜주는 것.
    select 'kOreA SEoul', initcap('kOreA SEoul')
    from dual;
    
    select last_name, upper(last_name), lower(last_name)
    from employees;
    
    select *
    from employees
    where last_name = 'King';
    
    select *
    from employees
    where upper(last_name) = upper('king');
    
    select *
    from employees
    where lower(last_name) = lower('KING');
    
    
    select *
    from employees
    where lower(last_name) = lower('kINg');
    
    select *
    from employees
    where initcap(last_name) = initcap('kINg');
    
    
    -- 1.4  substr('문자열', 시작글자번호, 뽑아야 할 글자 수)
    --      ==> '문자열' 중에 문자열의 일부분을 선택해올 때 사용한다.
    
    select '쌍용교육센터'
        , substr('쌍용교육센터', 2, 3) -- (글자, 인덱스, 글자수)
    from dual;
    
    select '쌍용교육센터'
        , substr('쌍용교육센터', 2, 3) -- 용교육
        , substr('쌍용교육센터', 2)    -- 용교육센터
    from dual;
    
    
    --- *** substr()함수를 사용하여 employees 테이블에서 성별이 '여자'인 사원들만 
    --      사원번호, 사원명, 주민번호를 나타내세요. *** 
    
    select  employee_id 사원번호
        ,  first_name || ' ' || last_name 사원명
        ,  jubun 주민번호
    from employees
    where substr(jubun, 7, 1) in ('2', '4');
    
    --- *** substr()함수를 사용하여 employees 테이블에서 1990년 ~ 1995년에 태어난 사원들중 
    --      성별이 '남자'인 사원들만 사원번호, 사원명, 주민번호를 나타내세요. ***
    
    select  employee_id 사원번호
        ,  first_name || ' ' || last_name 사원명
        ,  jubun 주민번호
    from employees
    where substr(jubun, 7, 1) = '1' 
    and substr(jubun, 1, 2) between '90' and '95'
    
    -- 1.5  instr : 어떤 문자열에서 명명된 문자열의 위치를 알려주는 것 **** ---
    select '쌍용교육센터 서울교육대학교 교육문화원'
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 1, 1)
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 1 번째 부터 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 1, 2)
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 1 번째 부터 2 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 4, 1)
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 4 번째 부터 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 4, 3)
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 4 번째 부터 3 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         --  그러한 값이 없다라면 0 이 나온다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 1)
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 1 번째 부터 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         --  출발점만 나오면 뒤에 , 1 이 생략된 것이다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 4)
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 4 번째 부터 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         --  출발점만 나오면 뒤에 , 1 이 생략된 것이다.
         
    from dual;
    
    
    -- 1.6  reverse : 어떤 문자열을 거꾸로 보여주는 것이다. **** ---
    select 'ORACLE', reverse('ORACLE')
         , '대한민국', reverse('대한민국'),  reverse( reverse('대한민국') )
    from dual;
    
    
    ------ [퀴즈] -------
    create table tbl_files
    (fileno      number(3)
    ,filepath    varchar2(200)
    );
    
    insert into tbl_files(fileno, filepath) values(1, 'c:\myDocuments\resume.hwp');
    insert into tbl_files(fileno, filepath) values(2, 'd:\mymusic.mp3');
    insert into tbl_files(fileno, filepath) values(3, 'c:\myphoto\2021\07\face.jpg');
    
    commit;
    
    select fileno, filepath
    from tbl_files;
    
    --- 아래와 같이 나오도록 select 문을 완성하세요 ---
    /*
        --------------------------------------
        파일번호    파일명
        --------------------------------------
            1       resume.hwp
            2       mymusic.mp3
            3       face.jpg
    */
    select fileno 파일번호
        , substr(filepath, instr(filepath, '\', -1)+1) 파일명
    from tbl_files; -- 내답
    
    
    select fileno, filepath, revers(filepath),
        -- substr( reverse(filepath), 1, ? )
        -- ==> ? 는 뽑아야할 글자길이인데 reverse(filepath) 에서 최초로 '\' 가 나오는 위치값
        -- ? ==> instr( reverse(filepath), '\', 1, 1)
    
            substr( reverse(filepath), 1, instr( reverse(filepath), '\', 1, 1))
            
            reverse(substr( reverse(filepath), 1, instr( reverse(filepath), '\', 1)-1))
    from tbl_files;
    
    select fileno 파일번호,
        reverse(substr( reverse(filepath), 1, instr( reverse(filepath), '\', 1)-1)) 파일명
    from tbl_files
        