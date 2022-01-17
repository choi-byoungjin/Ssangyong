set hidden param parseThreshold = 150000;

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
   -- DML(Data Manuplation Language[데이터조작어] ==> select, insert, update, delete, merge) 명령어로 
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
    
    
    select '쌍용교육센터 서울교육대학교 교육문화원'
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 1, 1) -- 3
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 1 번째 부터 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -1, 1) -- 16
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 역순으로 1 번째 부터 2 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -6, 1) -- 10
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 역순으로 6 번째 부터 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -6, 2) -- 3
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 역순으로 6 번째 부터 2 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         --  그러한 값이 없다라면 0 이 나온다.
         
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -6, 3) -- 0
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 역순으로 6 번째 부터 3 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         --  그러한 값이 없다라면 0 이 나온다.
         
         , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -6) -- 10
         --  '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나온 위치를 찾는데
         --  출발점이 역순으로 6 번째 부터 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         --  출발점만 나오면 뒤에 , 1 이 생략된 것이다.
         
    from dual;
        
        
    select fileno 파일번호
        , substr(filepath, instr(filepath, '\', -1)+1) 파일명
    from tbl_files;
    
    
    -- 1.7  lpad : 왼쪽부터 문자를 자리채움 *** ---
    -- 1.8  lpad : 왼쪽부터 문자를 자리채움 *** ---
    select lpad('교육센터',10,'*') 
    -- 10 byte를 확보해서 거기에 '교육센터'를 넣습니다. 넣은 후 빈공간(2byte)이 있으면 왼쪽부터 '*' 로 채워라
        , rpad('교육센터',10,'*')
    from dual;
    
    
    -- 1.9  ltrim : 왼쪽부터 문자를 제거한다, 문자가 없으면 공백 제거 **** ---
    -- 1.10 rtrim : 오른쪽부터 문자를 제거한다, 문자가 없으면 공백 제거 **** ---
    -- 1.11 trim  : 왼쪽, 오른쪽부터 공백을 제거한다 **** ---
    select ltrim('abbbaaacccddaabcdTaabdcaSSS', 'abcd')
          ,rtrim('abbbaaacccddaabcdTaabdcd', 'abcd')
          ,rtrim(ltrim('abbbaaacccddaabcdTaabdcd', 'abcd'), 'abcd')
    from dual;
    
    select '쌍용' || '                           교육                    센터',
           '쌍용' || ltrim('                           교육                    센터')
    from dual;
    
    select '쌍용                     ' || '교육                    센터',
           rtrim('쌍용                     ') || '교육                    센터'
    from dual;
    
    select '쌍용' || '                  교육                 ' ||'센터',
           '쌍용' || trim('                  교육                 ') ||'센터'
    from dual;
   
   
   -- 1.12  translate ---
   select translate('010-2345-6789'
                   ,'0123456789'
                   ,'영일이삼사오육칠팔구')
   from dual;
   
   
   -- 1.13 replace ---
   select replace('쌍용교육센터 서울교육대학교 교육문화원'
                , '교육'
                , 'education')
   from dual;
   
   
   -- 1.14 langth ==> 문자열의 길이를 알려주는 것 ---
   select length('쌍용center')
   from dual;


   -- 1.15 langthb ==> 문자열의 byte수를 알려주는 것 ---   
   select lengthb('쌍용center')
   from dual;
   


    -------------- >> 2. 숫자 함수 << ---------------------
    
    -- 2.1 mod : 나머지를 구해주는 것
    select 5/2, mod(5,2), trunc(5/2)
    from dual;
    
    --     2.5      1        2
    
    
    
    -- 2.2 round : 반올림을 해주는 것
    select 94.547
        ,  round(94.547)     --95
        ,  round(94.547,0)   --95        0 은 정수 1자리까지만 나타내어준다.
        ,  round(94.547,1)   --94.5      1 은 소수 첫째자리까지만 나타내어준다.
        ,  round(94.547,2)   --94.55     2 은 소수 둘째자리까지만 나타내어준다.
        ,  round(94.547,-1)  --90       -1 은 정수 10자리까지만 나타내어준다.
        ,  round(94.547,-2)  --100       -2 은 정수 100자리까지만 나타내어준다.
    from dual;
    
    
    -- 2.3 trunc : 절삭을 해주는 것
    select 94.547
        ,  trunc(94.547)     --95
        ,  trunc(94.547,0)   --94       0 은 정수 1자리까지만 나타내어준다.
        ,  trunc(94.547,1)   --94       1 은 소수 첫째자리까지만 나타내어준다.
        ,  trunc(94.547,2)   --94.5     2 은 소수 둘째자리까지만 나타내어준다.
        ,  trunc(94.547,-1)  --90      -1 은 정수 10자리까지만 나타내어준다.
        ,  trunc(94.547,-2)  --0       -2 은 정수 100자리까지만 나타내어준다.
    from dual;
    
    
    -- *** [성적처리] *** --
    create table tbl_sungjuk
    (hakbun      varchar2(20)
    ,name        varchar2(20)
    ,kor         number(3)
    ,eng         number(3)
    ,math        number(3)
    );
    
    select *
    from tbl_sungjuk;
    
    --- *** 데이터 입력하기 *** ---
    insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist001','한석규',90,92,93);
    insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist002','두석규',100,100,100);
    insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist003','세석규',71,72,73);
    insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist004','네석규',89,87,81);
    insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist005','오석규',60,50,40);
    insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist006','육석규',80,81,87);
    
    commit;
    
    
    
    --------------------------------------------------------------------------------------------------------------------------------------
    학번  성명  국어  영어  수학  총점  평균(소수부 첫째자리까지 나타내되 반올림)  학점(평균이 90 이상이면 'A' 90미만 80이상이면 'B' .... 60 미만이면 'F' )
    --------------------------------------------------------------------------------------------------------------------------------------
    
    
    select hakbun 학번 
          ,name 성명  
          ,kor 국어  
          ,eng 영어  
          ,math 수학  
          ,kor + eng + math 총점  
          ,round((kor + eng + math)/3, 1) 평균  
        --,trunc(round((kor + eng + math)/3, 1), -1)
          ,case trunc(round((kor + eng + math)/3, 1), -1)
            when 100 then 'A'
            when 90 then 'A'
            when 80 then 'B'
            when 70 then 'C'
            when 60 then 'D'
            else 'F' 
            end 학점
        
          ,decode(trunc(round((kor + eng + math)/3, 1), -1), 100, 'A'
                                                           ,  90, 'A'
                                                           ,  80, 'B'
                                                           ,  70, 'C'
                                                           ,  60, 'D'
                                                                , 'F') 학점2
          ,case
            when trunc(round((kor + eng + math)/3, 1), -1) in (100, 90) then 'A'
            when trunc(round((kor + eng + math)/3, 1), -1) = 80           then 'B'
            when trunc(round((kor + eng + math)/3, 1), -1) = 70           then 'C'
            when trunc(round((kor + eng + math)/3, 1), -1) = 60           then 'D'
            else 'F'
            end 학점3
    from tbl_sungjuk
    
    
    -- 2.4  power : 거듭제곱
    select 2*2*2*2*2, power(2,5) -- 2의 5승
    from dual;
    
    
    -- 2.5  sqrt : 제곱근
    select sqrt(16), sqrt(3), sqrt(2)
    from dual;
    
    
    -- 2.6  sin, cos, tan, asin, acos, atan
    select sin(90), cos(90), tan(90), asin(0.3), acos(0.3), atan(0.3)
    from dual;
    
    
    -- 2.7 log
    select log(10, 100)
    from dual;
    
    
    -- 2,8 sign  ==>  결과값이 양수람ㄴ 1, 결과값이 0 이면 0, 결과값이 음수라면 -1
    select sign(5-2), sign(5-5), sign(2-5)
    from dual;
    
    
    -- 2.9 ceil(실수)  ==>  입력되어진 실수보다 큰 최소의 정수를 나타내어준다.
    --     ceil(정수)  ==>  입력되어진 정수를 그대로 나타내어준다.
    select ceil(10.1), ceil(-10.1)
    from dual;


    -- 2.10 floor(실수)  ==>  입력되어진 실수보다 작은 최대의 정수를 나타내어준다.
    --      floor(정수)  ==>  입력되어진 정수를 그대로 나타내어준다.
    select floor(10.1), floor(-10.1), floor(10), floor(-10)
    from dual;
    

    ----------- >> 3. 날짜 함수 << -----------------

    /*
        날짜1 + 숫자 = 날짜2 ==> 날짜1 에서 숫자(단위가 일수)만큼 더한 값이 날짜2 가 된다.
        날짜1 - 숫자 = 날짜2 ==> 날짜1 에서 숫자(단위가 일수)만큼 뺀 값이 날짜2 가 된다.

        여기서 중요한 것은 숫자의 단위는 일수 이다.
        
        날짜1 - 날짜2 = 숫자  ==> 결과값인 숫자의 단위는 일수이다.
    */
    select
            sysdate - 1, to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 어제시각
           ,sysdate, to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재시각
    from dual
    
    -- 단위환산 --
    /*
        1 kg = 1000 g
        1 g = 1/1000 kg
        
        1 일 = 24 시간
        1 시간 = 60 분
        1 분 = 1/60 시간
        1 분 = 60 초
        1 초 = 1/60 분
    */
    
    --- *** [퀴즈] 현재시각으로 부터 1일 2시간 3분 4초 뒤를 나타내세요 *** ---
    
    select
            to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재
           ,to_char(sysdate + 1 + (2/24) + 3/(24*60) + 4/(24*60*60), 'yyyy-mm-dd hh24:mi:ss') "1일 2시간 3분 4초 뒤"
    from dual


    -- 3.1 to_yminterval('년-월'), to_dsinterval('일 시:분:초')
    /*
        to_yminteval 은 년 과 월을 나타내어 
        연산자가 + 이면 날짜에서 더해주는 것이고,
        연산자가 - 이면 날짜에서 빼주는 것이고,
        
        to_dsinterval 은 일 시간 분 초를 나타내어
        연산자가 + 이면 날짜에서 더해주는 것이고,
        연산자가 - 이면 날짜에서 빼주는 것이고,
    */
    -- 현재시각으로 부터 1년 2개월 3일 4시간 5분 6초 뒤를 나타내세요
    select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재시각
          ,sysdate + to_yminterval('01-02') + to_dsinterval('003 04:05:06')
          ,to_char(sysdate + to_yminterval('01-02') + to_dsinterval('003 04:05:06'), 'yyyy-mm-dd hh24:mi:ss') as "1년2개월3일4시간5분6초"
    from dual

    
    -- 3.2 add_months(날짜, 숫자)
    /*
        ==> 숫자가 양수이면 날짜에서 숫자 개월수 만큼 더해준 날짜를 나타내는 것이고,
            숫자가 음수이면 날짜에서 숫자 개월수 만큼 뺀 날짜를 나타내는 것이다.
            
        여기서 숫자의 단위는 개월수 이다.
    */
    select
            to_char(add_months(sysdate, -2), 'yyyy-mm-dd hh24:mi:ss') "2개월전",
            to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') "현재시각",
            to_char(add_months(sysdate, 2), 'yyyy-mm-dd hh24:mi:ss') "2개월후"
    from dual;
    
    --- *** 내일 홍길동이 군대에 입대를 한다. 복무기간이 18개월 이라면 제대일자(년-월-일) 를 구하세요.. 
    select to_char(add_months(sysdate+1, 18), 'yyyy-mm-dd') "제대일자"
    from dual;
    
    
    -- 3.3 months_between(날짜1, 날짜2)
    /*
          날짜1 에서 날짜2 를 뺀 값으로 그 결과는 숫자가 나오는데 결과물 숫자의 단위는 개월수 이다.
          즉, 두 날짜의 개월차이를 구할 때 사용한다.
        
     */
    select sysdate + 3
    from dual;
    -- 날짜1 - 날짜2 = 숫자  ==> 결과값인 숫자의 단위는 일수이다.
    
    select months_between(add_months(sysdate,3), sysdate),  --  3
           months_between(sysdate, add_months(sysdate,3))   -- -3
    from dual;
     
     
    -- 3.4 last_day(특정날짜)
    --     ==> 특정날짜가 포함된 달력에서 맨 마지막날짜를 알려주는 것이다.
    select sysdate, last_day(sysdate)
    from dual;
    
    select last_day('2022-02-01'),
           last_day(to_date('2022-01-01', 'yyyy-mm-dd')),
           last_day('2020-02-01'),
           last_day('2024-02-01')
    from dual;
    
    -- 3.5  next_day(특정날짜, '일') '일'~'토'
    --      ==> 특정날자로 부터 다음번에 돌아오는 가장 빠른 '일'~'토'의 날짜를 알려주는 것
    
    selecr sysdate
        ,  next_day(sysdate, '금')
        ,  next_day(sysdate, '월')
        ,  next_day(sysdate, '목')
    from dual;
    -- 22/02/28	22/01/31	20/02/29	24/02/29\
    
    
    -- 3.6 extract ==> 날짜에서 년, 월, 일을 숫자형태로 추출해주는 것이다.
    --     참고로 to_char() ==> 날짜에서 년, 월, 일을 문자형태로 추출해주는 것이다.
    select sysdate
        ,  to_char(sysdate, 'yyyy')     -- 2022
        ,  extract(year from sysdate)   -- 2022
        ,  to_char(sysdate, 'mm')       -- 01
        ,  extract(month from sysdate)  -- 1
        ,  to_char(sysdate, 'dd')       -- 06
        ,  extract(day from sysdate)    -- 6
        
        ,  to_char(sysdate, 'hh24')
        ,  to_char(sysdate, 'mm')
        ,  to_char(sysdate, 'ss')
    from dual;
    
    
    ----------- >> 4. 변환 함수 << -----------------
    
    -- 4.1  to_char(날짜, '형태')  ==> 날짜를 '형태' 모양으로 변환시켜주는 것이다.
    --      to_char(숫자, '형태')  ==> 숫자를 '형태' 모양으로 변환시켜주는 것이다.
    
    --- 날짜를 문자형태로 변환하기 ---
     select to_char(sysdate, 'yyyy')  AS 년도
          , to_char(sysdate, 'mm')    AS 월
          , to_char(sysdate, 'dd')    AS 일
          , to_char(sysdate, 'hh24')  AS "24시간"
          , to_char(sysdate, 'am hh') AS "12시간"
          , to_char(sysdate, 'pm hh') AS "12시간"
          , to_char(sysdate, 'mi')    AS 분
          , to_char(sysdate, 'ss')    AS 초
          , to_char(sysdate, 'q')     AS 분기       -- 1월~3월 => 1,   4월~6월 => 2,   7월~9월 => 3,    10월~12월 => 4 
          , to_char(sysdate, 'day')   AS 요일명     -- 월요일(Windows) , Monday(Linux) 
          , to_char(sysdate, 'dy')    AS 줄인요일명  -- 월(Windows) , Mon(Linux)
     from dual;
     
     select to_char(sysdate, 'd')  -- sysdate 의 주의 일요일 부터(지금은 2022년 1월 2일)  sysdate(지금은 2022년 1월 6일) 까지 며칠째 인지를 알려주는 것이다.
                                   -- 1(일요일)  2(월요일) 3(화요일) 4(수요일) 5(목요일) 6(금요일) 7(토요일) 
     from dual;
     
     
     select case to_char(sysdate, 'd')
     from dual;
     
     select case to_char(sysdate, 'd')
            when '1' then '일'
            when '2' then '월'
            when '3' then '화'
            when '4' then '수'
            when '5' then '목'
            when '6' then '금'
            when '7' then '토'
            end "오늘의요일명1"
        ,  decode(to_char(sysdate, 'd'), '1', '일'
                                         '2', '월'
                                         '3', '화'
                                         '4', '수'
                                         '5', '목'
                                         '6', '금'
                                         '7', '토') 오늘의요일명2
    from dual;
     
    select to_char(sysdate, 'dd')   -- sysdate 의 월 1일 부터(지금은 2022년 1월 1일) sysdate 까지 며칠째 인지를 알려주는 것이다.
        ,  to_char(sysdate, 'ddd')  -- sysdate 의 년도 1월1일 부터(지금은 2022년 1월 1일) sysdate 까지 며칠째 인지를 알려주는 것이다.
    from dual;
    
    select to_char(add_months(sysdate, 1), 'dd') -- add_months(sysdate, 1) 은 2022년 2월 6일 이다.
        ,  to_char(add_months(sysdate, 1), 'ddd')-- add_months(sysdate, 1) 은 2022년 2월 6일 이다.
    from dual   
    
    --- *** 숫자를 문자형태로 변환하기 *** ---
    select 1234567890
        ,  to_char(1234567890, '9,999,999,999')
        ,  to_char(1234567890, '$9,999,999,999')
        ,  to_char(1234567890, 'L9,999,999,999') -- L 은 그 나라의 화폐기호가 나온다.
    from dual;
    
    
    select 100
        ,  to_char(100, '999.0')    -- 100.0
        ,  95.7
        ,  to_char(95.7, '999.0')   -- 95.7
        ,  to_char(95.7, '999.00')  -- 95.70
        ,  to_char(95.78, '999.00') -- 95.78
    from dual;
    
    
    -- 4.2 to_date(문자, '형태') ==> 문자를 '형태' 모양으로 날짜형태로 변환시켜주는 것.
    select '2022-01-06' + 1
    from dual;
    -- ORA-01722: invalid number
    
    select to_date('2022-01-06', 'yyyy-mm-dd') + 1
        ,  to_date('2022/01/06', 'yyyy/mm/dd') + 1
        ,  to_date('20220106', 'yyyymmdd') + 1
    from dual;
    
    select to_date('2022-02-28', 'yyyy-mm-dd') + 1 -- 2022-02-29 은 달력에 없으므로 오류
    from dual;
    
    select to_date('2020-02-28', 'yyyy-mm-dd') + 1
    from dual;
    
    
    -- 4.3 to_number(문자) ==> 숫자모양을 가지는 문자를 숫자형태로 변환시켜주는 것이다.
    select '12345', to_number('12345')
    from dual;
    
    select '50' + 10 -- 자동형변환이 되어짐.
        ,  to_number('50')+10
    from dual;
    
    select to_number('홍길동')
    from dual;
    -- ORA-01722: invalid number
    
    ----------- >> 5. 기타 함수 << ----------------
    -
    -- 5.1  case when then else end  ==> !! 암기 !!
    select case 5-2
           when 4 then '5-2=4 입니다.'
           when 1 then '5-2=1 입니다.'
           when 3 then '5-2=3 입니다.'
           else '나는 수학을 몰라요'
           end
    from dual;

    select case
           when 4 > 5 then '4는 5보다 큽니다.'
           when 5 > 7 then '5는 7보다 큽니다.'
           when 3 > 2 then '3은 2보다 큽니다.'
           else '나는 수학을 몰라요'
           end 결과
    from dual;
    
    
    -- 5.2  decode  ==> !!! 암기 !!!
    select decode(5-2, 4, '5-2=4 입니다.'
                     , 1, '5-2=1 입니다.'
                     , 0, '5-2=3 입니다.'
                        , '나는 수학을 몰라요') 결과
    from dual;
    
    
    -- 5.3  greatest, least
    select greatest(10, 90, 100, 80)    -- 나열되어진것들 중에 제일 큰값을 알려주는 것
         , least(10, 90, 100, 80)       -- 나열되어진것들 중에 제일 작은값을 알려주는 것
    from dual;
    -- 100	10
    
    select greatest('김유신','허준','고수','엄정화')
         , least('김유신','허준','고수','엄정화')
    from dual
    -- 허준	고수
    
    
    -- 5.4  rank ==> 등수(석차)구하기, dense_rank ==> 서열구하기
    select employee_id as 사원번호
         , first_name || ' ' || last_name 사원명
         , nvl(salary + (salary*commission_pct), salary) 월급
         , department_id 부서번호
         , rank() over(partition by department_id
                        order by nvl(salary + (salary*commission_pct), salary) desc) as 부서내월급등수
         , rank() over(order by nvl(salary + (salary*commission_pct), salary) desc) as 전체월급등수
         , dense_rank() over(partition by department_id
                        order by nvl(salary + (salary*commission_pct), salary) desc) as 부서내월급서열
         , dense_rank() over(order by nvl(salary + (salary*commission_pct), salary) desc) as 전체월급서열
         
    from employees;
    order by 부서번호;
    
    -- employees 테이블에서 월급이 가장 많이 버는 등수로 1등부터 10등까지인 사원들만
    -- 사원번호, 사원명, 월급, 등수를 나타내세요.
    
    select *
    from employees
    where rank() over(order by nvl(salary + (salary*commission_pct), salary) desc) between 1 and 10
    -- 오류!! rank() 및 dense_rank() 함수는 where 절에 바로 사용할 수 없다.
    -- inline view 를 통해서 해야 한다.
    
    select V.*
    from
    (
    select employee_id, first_name || ' ' || last_name as full_name
         , nvl(salary + (salary*commission_pct), salary) as month_sal
         , rank() over(order by nvl(salary + (salary*commission_pct), salary) desc) as rank_month_sal
    from employees
    ) V
    where V.rank_month_sal between 1 and 10;
    
    -- V는 생략가능하다. --
    select *
    from
    (
    select employee_id, first_name || ' ' || last_name as full_name
         , nvl(salary + (salary*commission_pct), salary) as month_sal
         , rank() over(order by nvl(salary + (salary*commission_pct), salary) desc) as rank_month_sal
    from employees
    ) V
    where rank_month_sal between 1 and 10;
    
    
     /*
        --- [퀴즈] ---
        employees 테이블에서 모든 사원들에 대해
        사원번호, 사원명, 주민번호, 성별, 현재나이, 월급, 입사일자, 정년퇴직일, 정년까지근무개월수, 퇴직금 을 나타내세요.
        
        여기서 정년퇴직일이라 함은 
        해당 사원의 생월이 3월에서 8월에 태어난 사람은 
        해당사원의 나이(한국나이)가 63세가 되는 년도의 8월 31일로 하고,
        해당사원의 생월이 9월에서 2월에 태어난 사람은 
        해당사원의 나이(한국나이)가 63세가 되는 년도의 2월말일(2월28일 또는 2월29일)로 한다.
   
        정년까지근무개월수 ==> 입사일자로 부터 정년퇴직일 까지 개월차이 
        months_between(정년퇴직일, 입사일자)
        
        퇴직금 ==> 근무년수 * 월급       26개월근무 ==> 2년2개월 ==> 2년*월급
        
    */
    select employee_id AS 사원번호
         , first_name || ' ' || last_name AS 사원명
         , jubun AS 주민번호
         , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS 성별
    
    --   , 현재년도 - 태어난년도( 주민번호앞의2자리 + 1900 OR 주민번호앞의2자리 + 2000 ) + 1 AS 현재나이
         , extract(year from sysdate) - ( substr(jubun,1,2) + case when substr(jubun, 7, 1) in('1','2') then 1900 else 2000 end ) + 1 AS 현재나이
         
         , nvl(salary + (salary * commission_pct), salary) AS 월급
         , hire_date AS 입사일자
         
         --  정년퇴직일은 해당사원의 나이(한국나이)가 63세가 되는 년도 
         --  어떤 사원의 현재 나이가 62세 => 63세가 되는 년도  add_months(sysdate, (63-62)*12)
         --  어떤 사원의 현재 나이가 40세 => 63세가 되는 년도  add_months(sysdate, (63-40)*12)
         --  어떤 사원의 현재 나이가 57세 => 63세가 되는 년도  add_months(sysdate, (63-57)*12) 
         --  to_char( add_months(sysdate, (63-현재나이)*12), 'yyyy') || '-08-31'
         
         ,  last_day( to_char( add_months(sysdate, (63-( extract(year from sysdate) - ( substr(jubun,1,2) + case when substr(jubun, 7, 1) in('1','2') then 1900 else 2000 end ) + 1 ))*12), 'yyyy') || 
                      case when to_number( substr(jubun,3,2) ) between 3 and 8 then '-08-01' else '-02-01' end  
                     ) AS 정년퇴직일
    --   ,  months_between(정년퇴직일, hire_date) 정년까지근무개월수
         ,  trunc(months_between(last_day( to_char( add_months(sysdate, (63-( extract(year from sysdate) - ( substr(jubun,1,2) + case when substr(jubun, 7, 1) in('1','2') then 1900 else 2000 end ) + 1 ))*12), 'yyyy') || 
                      case when to_number( substr(jubun,3,2) ) between 3 and 8 then '-08-01' else '-02-01' end  
                     ), hire_date)) 정년까지근무개월수
                     
    --   ,  trunc( 정년까지근무개월수/12 ) * 월급 퇴직금
         ,  trunc( (trunc(months_between(last_day( to_char( add_months(sysdate, (63-( extract(year from sysdate) - ( substr(jubun,1,2) + case when substr(jubun, 7, 1) in('1','2') then 1900 else 2000 end ) + 1 ))*12), 'yyyy') || 
                      case when to_number( substr(jubun,3,2) ) between 3 and 8 then '-08-01' else '-02-01' end  
                     ), hire_date)) )/12 ) * nvl(salary + (salary * commission_pct), salary) 퇴직금
    from employees;
    
    ---------------------------------------------------------------------------------------------------------------
    ------- !!!!!"inline view" 를 사용하여 구해본다.!!!!!----------
    select employee_id, full_name, jubun, gender, age, month_sal, hire_date
         , last_day(to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
           case when to_number( substr(jubun, 3, 2)) between 3 and 8 then '-08-01'
           else '-02-01'
           end) as 정년퇴직일
    --   , months_between(정년퇴직일, hire_date) as 정년까지근무개월수
         , trunc(
                 months_between(last_day(
                                         to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
                                         case when to_number( substr(jubun, 3, 2)) between 3 and 8 then '-08-01' else '-02-01' end
                                         ), hire_date) 
            )as 정년까지근무개월수
    --   , trunc( 정년까지근무개월수/12 ) * 월급 AS 퇴직금
         , trunc( trunc(
                        months_between(last_day(
                                                to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
                                                case when to_number( substr(jubun, 3, 2)) between 3 and 8 then '-08-01' else '-02-01' end
                                         ), hire_date) 
                 )/12 
            ) * month_sal AS 퇴직금
    from
    (
    select employee_id  
         , first_name || ' ' || last_name  as full_name
         , jubun 
         , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end as gender
         , extract(year from sysdate) - ( substr(jubun,1,2) + case when substr(jubun, 7, 1) in('1','2') then 1900 else 2000 end ) + 1 as age
         , nvl(salary + (salary * commission_pct), salary) as month_sal
         , hire_date  
         
    from employees
    ) V -- V를 "inline view" 라고 부른다
    
    
    ----- **** !!!!! 아주 중요중요중요중요중요중요중요중요중요중요중요중요중요중요 아주 !!!!! **** ------
    -- VIEW(뷰)란? 테이블은 아니지만 select 되어진 결과물을 마치 테이블 처럼 보는것(간주하는 것)이다.
    -- VIEW(뷰) 는 2가지 종류가 있다.
    -- 첫번째로 inline view 가 있고, 두번째로 stored view 가 있다. 
    -- inline view 는 바로 위의 예제에 보이는 V 인 것이다. 즉, select 구문을 괄호( )를 쳐서 별칭(예 : V)을 부여한 것을 말한다.
    -- stored view 는 복잡한 SQL(Structured Query Language == 정형화된 질의어)을 저장하여 select 문을 간단하게 사용하고자 할 때 쓰인다.
    -- 그래서 inline view 는 1회성이고, stored view는 언제든지 불러내서 재사용이 가능하다.
    -- 보안성을 높일 수 있다.
    
    --- *** Stored View(저장된 뷰) 생성하기 *** ---
    /*
        create or replace view 뷰명  --> 만약에 저장된 뷰로 뷰명이 없으면 새로이 생성해주고(create), 뷰명이 이미 존재한다라면 그 이전의 select 문을 없애고 지금의 select 문으로 바꾸어라(replae)는 말이다.
        as
        select 문;
    */
   create or replace view view_employee_retire
   as 
   select employee_id, first_name || ' ' || last_name AS FULL_NAME
        , salary, department_id
   from employees
   where department_id in (20,30);
   -- View VIEW_EMPLOYEE_RETIRE이(가) 생성되었습니다.
   
   select *
   from view_employee_retire;
   
   
   create or replace view view_employee_retire
   as 
   select employee_id, full_name, jubun, gender, age, month_sal, hire_date
     
        , last_day(
                   to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
                   case when to_number( substr(jubun, 3, 2) ) between 3 and 8 then '-08-01' else '-02-01' end  
          ) AS RETIREMENT_DATE
 
       ,  trunc(
                months_between(last_day(
                                       to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
                                       case when to_number( substr(jubun, 3, 2) ) between 3 and 8 then '-08-01' else '-02-01' end  
                                      ) , hire_date) 
          ) AS RETIRE_WORKING_MONTHS_NUM
     
       , trunc( 
                trunc(
                       months_between(last_day(
                                               to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
                                               case when to_number( substr(jubun, 3, 2) ) between 3 and 8 then '-08-01' else '-02-01' end  
                                      ) , hire_date) 
                ) /12 
         ) * month_sal  AS SEVERANCE_PAY
   from 
   ( 
    select employee_id
         , first_name || ' ' || last_name AS FULL_NAME
         , jubun
         , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS GENDER
         , extract(year from sysdate) - ( substr(jubun,1,2) + case when substr(jubun, 7, 1) in('1','2') then 1900 else 2000 end ) + 1 AS AGE 
         , nvl(salary + (salary * commission_pct), salary) AS MONTH_SAL
         , hire_date 
    from employees
   ) V; 
   -- View VIEW_EMPLOYEE_RETIRE이(가) 생성되었습니다.
   
   select * from tab;
   
   desc view_employee_retire;
   
   select *
   from view_employee_retire;
   
   select *
   from view_employee_retire
   where gender = '여' and trunc(age, -1) in (40,50);
   
   select *
   from view_employee_retire
   where gender = '남' and retire_working_months_num >= 500;
   
   --- Stored View(저장된 뷰) 가 뭐가 있는지 알아봅니다. ---
   select *
   from user_views;
   
   --- Stored View(저장된 뷰) 중에 VIEW_EMPLOYEE_RETIRE 의 원본소스를 알아봅니다. ---
   select text
   from user_views
   where view_name = 'VIEW_EMPLOYEE_RETIRE';
   /*
   "select employee_id, full_name, jubun, gender, age, month_sal, hire_date
     
        , last_day(
                   to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
                   case when to_number( substr(jubun, 3, 2) ) between 3 and 8 then '-08-01' else '-02-01' end  
          ) AS RETIREMENT_DATE
 
       ,  trunc(
                months_between(last_day(
                                       to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
                                       case when to_number( substr(jubun, 3, 2) ) between 3 and 8 then '-08-01' else '-02-01' end  
                                      ) , hire_date) 
          ) AS RETIRE_WORKING_MONTHS_NUM
     
       , trunc( 
                trunc(
                       months_between(last_day(
                                               to_char( add_months(sysdate, (63-age)*12), 'yyyy') || 
                                               case when to_number( substr(jubun, 3, 2) ) between 3 and 8 then '-08-01' else '-02-01' end  
                                      ) , hire_date) 
                ) /12 
         ) * month_sal  AS SEVERANCE_PAY
   from 
   ( 
    select employee_id
         , first_name || ' ' || last_name AS FULL_NAME
         , jubun
         , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS GENDER
         , extract(year from sysdate) - ( substr(jubun,1,2) + case when substr(jubun, 7, 1) in('1','2') then 1900 else 2000 end ) + 1 AS AGE 
         , nvl(salary + (salary * commission_pct), salary) AS MONTH_SAL
         , hire_date 
    from employees
   ) V"
   */
   
   
   
   -- 5.5  lag 함수, lead 함수
   --      ==> 게시판에서 특정글을 조회할때 많이 사용한다. !!!
   
    create table tbl_board
    (boardno       number                -- 글번호 
    ,subject       varchar2(4000)        -- 글제목   varchar2 의 최대크기는 4000 이다. 4000 보다 크면 오류!!!
    ,content       Nvarchar2(2000)       -- 글내용   Nvarchar2 의 최대크기는 2000 이다. 2000 보다 크면 오류!!!
    ,userid        varchar2(40)          -- 글쓴이의 ID
    ,registerday   date default sysdate  -- 작성일자   default sysdate 은 데이터 입력시 registerday 컬럼의 값을 생략하면 기본적으로 sysdate 가 입력된다는 말이다. 
    ,readcount     number(10)            -- 조회수
    );
    
    insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
    values(1, '안녕하세요', '글쓰기 연습입니다', 'leess',  sysdate, 0);  
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_board
    values(2, '반갑습니다', '모두 취업대박 나십시오', 'eomjh',  sysdate, 0);  
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_board(subject, boardno, content, userid, registerday, readcount)
    values('건강하세요', 3, '로또 1등을 기원합니다', 'youks',  sysdate, 0); 
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
    values(4, '기쁘고 감사함이 넘치는 좋은 하루되세요', '늘 행복하세요', 'leess',  default, 0);
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_board(boardno, subject, content, userid, readcount)
    values(5, '오늘도 좋은 하루되세요', '늘 감사합니다', 'hongkd', 0);
    -- 1 행 이(가) 삽입되었습니다.
    
    commit;
    -- 커밋 완료.

    select *
    from tbl_board;
    
    select boardno
         , case when length(subject) > 7 then substr(subject, 1, 5) || '..' else subject end as subject
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as registerday
    from tbl_board
    order by boardno desc;
    
    /*
        ----------------------------------------------------------------------------------------------------------------------------------------
        이전글번호   이전글제목   이전글작성일자              글번호   글제목         글작성일자            다음글번호      다음글제목       다음글작성일자
        ----------------------------------------------------------------------------------------------------------------------------------------
        4       	기쁘고 감..	2022-01-07 10:18:00       5	    오늘도 좋..	2022-01-07 10:18:00     null          null            null
        3	        건강하세요	2022-01-07 10:18:00       4     기쁘고 감..	2022-01-07 10:18:00     5	        오늘도 좋..	2022-01-07 10:18:00
        2	        반갑습니다	2022-01-07 10:17:54       3	    건강하세요	2022-01-07 10:18:00     4       	기쁘고 감..	2022-01-07 10:18:00
        1	        안녕하세요	2022-01-07 10:17:51       2	    반갑습니다	2022-01-07 10:17:54     3	        건강하세요	2022-01-07 10:18:00
        null    	null        null                      1	    안녕하세요	2022-01-07 10:17:51     2	        반갑습니다	2022-01-07 10:17:54
    */
    select T.*
    from
    (
    select lead(boardno, 1) over(order by boardno desc) as 이전글번호
            -- boardno (글번호) 컬럼의 값을 내림차순으로 정렬했을때 아래쪽으로 1칸 내려간 행의 boardno 값을 가져오는 것이다.
        ,  lead(Subject, 1) over(order by boardno desc) as 이전글제목
            -- boardno (글제목) 컬럼의 값을 내림차순으로 정렬했을때 아래쪽으로 1칸 내려간 행의 Subject 값을 가져오는 것이다.
        ,  lead(registerday, 1) over(order by boardno desc) as 이전글작성일자
            -- boardno (작성일자) 컬럼의 값을 내림차순으로 정렬했을때 아래쪽으로 1칸 내려간 행의 registerday 값을 가져오는 것이다.
        
        ,  boardno 글번호
        ,  Subject 글제목
        ,  registerday 글작성일자
        
        ,  lag(boardno, 1) over(order by boardno desc) 다음글번호
            -- boardno (글번호) 컬럼의 값을 내림차순으로 정렬했을때 위으로 1칸 올라간 행의 boardno 값을 가져오는 것이다.
        ,  lag(Subject, 1) over(order by boardno desc) 다음글제목
            -- boardno (글제목) 컬럼의 값을 내림차순으로 정렬했을때 위으로 1칸 올라간 행의 Subject 값을 가져오는 것이다.
        ,  lag(registerday, 1) over(order by boardno desc) 다음글작성일자
            -- boardno (작성일자) 컬럼의 값을 내림차순으로 정렬했을때 위으로 1칸 올라간 행의 registerday 값을 가져오는 것이다.
        
    from
    (
    select 
           boardno
         , case when length(subject) > 7 then substr(subject, 1, 5) || '..' else subject end as Subject
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    ) V
    ) T
    where T.글번호 = 3;
    
    
    
    
    select lead(boardno) over(order by boardno desc) as 이전글번호
            -- 숫자가 없으면 1이 생략된 것이다.
        ,  lead(Subject) over(order by boardno desc) as 이전글제목
        ,  lead(registerday) over(order by boardno desc) as 이전글작성일자
        
        ,  boardno 글번호
        ,  Subject 글제목
        ,  registerday 글작성일자
        
        ,  lag(boardno) over(order by boardno desc) 다음글번호
        ,  lag(Subject) over(order by boardno desc) 다음글제목
        ,  lag(registerday) over(order by boardno desc) 다음글작성일자
        
    from
    (
    select 
           boardno
         , case when length(subject) > 7 then substr(subject, 1, 5) || '..' else subject end as Subject
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    ) V
    
    --------------------------------------------------------------------------
    -- *** case when then else end 구문을 사용하여 로그인 메시지 보여주기 *** --
    
   create table tbl_members
    (userid    varchar2(20)
    ,passwd    varchar2(20)
    ,name      varchar2(20)
    ,addr      varchar2(100)
    );
    
    insert into tbl_members(userid, passwd, name, addr)
    values('kimys','abcd','김유신','서울');
    
    insert into tbl_members(userid, passwd, name, addr)
    values('young2','abcd','이영이','서울');
    
    insert into tbl_members(userid, passwd, name, addr)
    values('leesa','abcd','이에리사','서울');
    
    insert into tbl_members(userid, passwd, name, addr)
    values('park','abcd','박이남','서울');
    
    insert into tbl_members(userid, passwd, name, addr)
    values('leebon','abcd','이본','서울');
    
    commit;  
    
    select *
    from tbl_members
    where userid = 'kimys' and passwd = 'abcd';
    
    /*
       -- [퀴즈] --
       tbl_members 테이블에서
       userid 및 passwd 가 모두 올바르면 '로그인성공' 을 보여주고,
       userid 는 올바르지만 passwd 가 틀리면 '암호가 틀립니다' 을 보여주고,
       userid 가 존재하지 않으면 '아이디가 존재하지 않습니다' 을 보여주려고 한다.
    */
    select case ( select count(*)
                  from tbl_members
                  where userid = 'kimys' and psswd = 'abcd' )
           when 1 then '로그인성공'
           else(
                 case ( select count(*)
                        from tbl_members
                        where userid = 'kimys' )
                 when 1 then '암호가 틀립니다'
                 else '아이디가 존재하지 않습니다'
                 end
                )
           end as 로그인결과
    from dual;
    
    
    
    -------------------------------------------------------------------------
    
    ------ >> 그룹함수(집계함수) << ------
    /*
        1. sum      -- 합계
        2. avg      -- 평균
        3. max      -- 최대값
        4. min      -- 최소값
        5. count    -- select 되어서 나온 결과물의 행의 개수
        6. variance -- 분산
        7. stddev   -- 표준편차
        
        분산    : 분산의 제곱근이 표준편차 (평균에서 떨어진 정도)
        표준편차 : 표준편차의 제곱승이 분산 (평균과의 차액)
        
        >> 주식투자 <<
    A - 50  60  40  50  55  45  52  48     평균 50  A는 B보다 상대적으로 편차가 적음.      -- 안정투자
    B - 10  90  20  80  30  70  90  10     평균 50  B는 A보다 상대적으로 편차가 크다.      -- 투기성투자(위험을 안고서 투자함)
    
    분산과 표준편차는 어떤 의사결정시 도움이 되는 지표이다.
        
    */
    
    select salary, to_char(salary, '$99,999')
    from employees; -- 107 개행
    
    select sum(salary)  -- 그룹함수는 결과물이 1개행만 나온다.
    from employees;
    
    select salary, sum(salary)
    from employees; -- 테이블은 다각형이 아니므로 이것은 오류이다.
    
    ---- !!! 중요중요중요 !!! ----
    -- 그룹함수(집계함수)에서는 null 이 있으면 무조건 null 은 제외시킨 후 연산을 한다.!!
    -- 그룹함수(집계함수)를 사용하면 1개의 결과물을 가진다.
    
    select 1+100+5234+null
    from dual;
    
    select sum(salary * commission_pct)
    from employees
    where commission_pct is not null;
    
    select sum(salary * commission_pct)
         , count(salary)         -- 107
         , count(commission_pct) --  35
    from employees;
    
    desc employees;
    
    select count(*)              -- 107
          ,count(department_id)  -- 106 department_id 컬럼의 값이 null 이 아닌 개수
          ,count(commission_pct) --  35 commission_pct 컬럼의 값이 null 이 아닌 개수
          ,count(employee_id)    -- 107
          ,count(email)          -- 107
    from employees;
    
    
    select sum(salary * commission_pct)      -- 합계
         , avg(salary * commission_pct)      -- 평균
         , max(salary * commission_pct)      -- 최대값
         , min(salary * commission_pct)      -- 최소값
         , count(salary * commission_pct)    -- null 을 제외한 개수
         , variance(salary * commission_pct) -- 분산
         , stddev(salary * commission_pct)   -- 표준편차
    from employees;
    
    
    ----- **** avg() 평균을 구할 때는 주의를 요한다. **** -----
    
    -- employees 테이블에서 수당액(salary * commission_pct)의 평균을 나타내세요.
    select sum(salary * commission_pct)/count(salary * commission_pct)
            -- 2105.428571428571428571428571428571428571
            --  / 35
            --  / 107
            
          , avg(salary * commission_pct)
            -- 2105.428571428571428571428571428571428571
            
          , sum(salary * commission_pct)/count(*)
            -- 73690 / 107
        
          , sum( nvl(salary * commission_pct, 0) ) / count( nvl(salary * commission_pct, 0) )
            -- 73690 / 107
            
          , avg( nvl(salary * commission_pct, 0) )
    from employees;
    
    
    select salary * commission_pct
         , nvl(salary * commission_pct, 0)
    from employees;
    
    
    select count(salary * commission_pct)           --  35
         , count( nvl(salary * commission_pct, 0) ) -- 107
    from employees;
    
    
    -- sum(10+20+null+30)  ==>  60
    -- sum(10+20+0+30)     ==>  60
    select avg(salary * commission_pct)
            -- 수당액을 받는 사람들만의 수당액 평균
         , avg( nvl(salary * commission_pct, 0) )
            -- 수당이 null 인 사람은 0 으로 간주한 모든 사원들의 평균
    from employees;
    
    
    ---- *** 그룹함수(집계함수)와 함께 사용되어지는 group by 절에 대해서 알아본다. ** ----
    
    --- employees 테이블에서 부서번호별로 인원수를 나타내세요. ---
    
    
    /*
        ---------------------
         부서번호    인원수
        ---------------------
            10       1
            20       2
            30      15
            40       3
            50      20
            ...     ...
    */
    
    select department_id 부서번호
         , count(*) 인원수
    from employees
    group by department_id  -- department_id 컬럼의 값이 같은것 끼리 그룹을 짓는다.
    order by 1;
    
    
    --- employees 테이블에서 성별로 인원수를 나타내세요. ---
    
    /*
        ---------------------
          성별   인원수
        ---------------------
           남     56
           여     51
    */
    
    select GENDER, count(*)
    from
    (
    select case when substr(jubun,7,1) in ('2','4') then '여' else '남' end as GENDER
    from employees
    ) V
    group by GENDER
    ORDER BY 1;
    
    ---- [퀴즈] employees 테이블에서 연령대별로 인원수를 나타내세요. ----
   /*
      -------------------
       연령대       인원수
      -------------------
        10
        20
        30
        40
        50
        60
        
        나이 ==> 현재년도 - 태어난년도 + 1
                extract(year from sysdate)
                substr(jubun,1,2) + case when substr(jubun,7,1) in ('1','2') then 1900 else 2000 end + 1
   */
   
   -- trunc(16, -1) = 10
   
   select V.AGE_LINE 연령대
        , count(*) 인원수
   from
   (
    select trunc(extract(year from sysdate) - (substr(jubun,1,2) + case when substr(jubun,7,1) in ('1','2') then 1900 else 2000 end) + 1, -1) as AGE_LINE
    from employees
   ) V
   group by V.AGE_LINE
   order by 1;
  
   
   ----- ***** 1차 그룹, 2차 그룹 짓기 ***** -----
   
   --- employees 테이블에서 부서번호별, 성별 인원수를 나타내세요. ---
   /*
        -----------------------
        부서번호    성별    인원수
        -----------------------
          ....    ....
          50       남      10
          50       여      15
          60       남       5
          60       여      10
          ....    ....
   */
   select V.department_id as 부서번호
        , V.GENDER as 성별
        , count(*) as 인원수
   from
   (
    select department_id
         , case when substr(jubun,7,1) in ('2','4') then '여' else '남' end as GENDER
    from employees
   ) V
   group by V.department_id, V.GENDER
   order by 1;
   
   
   ---- [퀴즈] employees 테이블에서 연령대별, 성별 인원수를 나타내세요. ----
   /*
      -------------------------
       연령대     성별    인원수
      -------------------------
        10        남
        10        여
        20        남
        20        여
        30        남
        30        여
        ...       ...
   */
   
   select V.AGE_LINE as 연령대
        , V.GENDER as 성별
        , count(*) as 인원수
   from
   (
    select trunc(extract(year from sysdate) - (substr(jubun,1,2) + case when substr(jubun,7,1) in ('1','2') then 1900 else 2000 end) + 1, -1) as AGE_LINE
          ,case when substr(jubun,7,1) in ('2','4') then '여' else '남' end as GENDER
    from employees
   ) V
   group by V.AGE_LINE, V.GENDER
   order by 1;
   
   
   -----------------------------------------------------------------------------
   
   -- *** 요약값을 보여주는 rollup, cube, grouping sets, grouping 에 대해서 알아본다. *** --
  
  ------ >>>>> 요약값(rollup, cube, grouping sets) <<<<< ------
  /*
      1. rollup(a,b,c) 은 grouping sets( (a,b,c),(a,b),(a),() ) 와 같다.
    
         group by rollup(department_id, gender) 은
         group by grouping sets( (department_id, gender), (department_id), () ) 와 같다.
  
      2. cube(a,b,c) 은 grouping sets( (a,b,c),(a,b),(b,c),(a,c),(a),(b),(c),() ) 와 같다.
 
         group by cube(department_id, gender) 은
         group by grouping sets( (department_id, gender), (department_id), (gender), () ) 와 같다.
  */
   
   -- employees 테이블에서 부서번호별로 인원수를 나타내면서 동시에 전체인원수도 나타내세요. --
   
   select department_id as 부서번호
        , count(*) as 인원수
   from employees
   group by rollup(department_id);
   
   
   select decode(grouping(department_id), 0, nvl(to_char(department_id), '인턴') -- grouping(department_id) 은 결과값이 오로지 2개만 나온다. 0 또는 1 인데, 0이라함은 department_id 컬럼의 값으로 그룹을 지었다는 말이고, 1 이라함은 그룹을 안지었다는 말이다.
                                           , '전체') as 부서번호
        , count(*) as 인원수
   from employees
   group by rollup(department_id);
   
   
   select decode(grouping(department_id), 0, nvl(to_char(department_id), '인턴') 
                                           , '전체') as 부서번호
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 1) as 퍼센티지
   from employees
   group by rollup(department_id);
   
   
   -- employees 테이블에서 성별로 인원수를 나타내면서 동시에 전체인원수도 나타내세요. --
   
   /*
   -------------------------
    성별   인원수   퍼센티지
   -------------------------
     남     56      52%   
     여     51      48%
    전체    107     100
   */
   
   
   select decode(grouping(V.GENDER), 0, V.GENDER, '전체') as 성별
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
    
   from
   (
   select case when substr(jubun, 7, 1) in ('2', '4') then '여자' else '남자' end as GENDER
   from employees
   ) V
   group by rollup(V.GENDER);

    
    -- employees 테이블에서 부서번호별, 성별로 인원수를 나타내면서 동시에 전체인원수도 나타내세요. --
    /*
   ------------------------------
    부서번호 성별   인원수   퍼센티지
   ------------------------------
   
   */
   -- rollup 사용시 --
   select decode (grouping(V.department_id), 0, nvl(to_char(V.department_id), '인턴')
                                              , ' ') as 부서번호 -- 그루핑 되어진 값이 0 이라면 그루핑 되어진 값이고 1이면(그룹이 지어지지 않음) 전체값
        
        , decode (grouping(v.gender), 0, V.GENDER 
                                       , ' ') as 성별
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
   from
   (
    select department_id
         , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end || '자' as GENDER
    from employees
    ) V
    group by rollup(V.department_id, V.GENDER);
    
   -- rollup 사용시( 순서변경 ) --
   select decode (grouping(v.gender), 0, V.GENDER 
                                       , ' ') as 성별
        , decode (grouping(V.department_id), 0, nvl(to_char(V.department_id), '인턴')
                                              , ' ') as 부서번호 -- 그루핑 되어진 값이 0 이라면 그루핑 되어진 값이고 1이면(그룹이 지어지지 않음) 전체값
        
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
   from
   (
    select department_id
         , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end || '자' as GENDER
    from employees
    ) V
    group by rollup(V.GENDER, V.department_id);
     
    
    ---------------------------------------------------------------------------------------------------------------------
    -- grouping sets 사용시 --
   select decode (grouping(V.department_id), 0, nvl(to_char(V.department_id), '인턴')
                                              , ' ') as 부서번호 -- 그루핑 되어진 값이 0 이라면 그루핑 되어진 값이고 1이면(그룹이 지어지지 않음) 전체값
        
        , decode (grouping(v.gender), 0, V.GENDER 
                                       , ' ') as 성별
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
   from
   (
    select department_id
         , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end || '자' as GENDER
    from employees
    ) V
    group by grouping sets((V.department_id, V.GENDER), (V.department_id), ());
   
   -- grouping sets 사용시( 순서변경 ) --
   select decode (grouping(v.gender), 0, V.GENDER 
                                       , ' ') as 성별
        , decode (grouping(V.department_id), 0, nvl(to_char(V.department_id), '인턴')
                                              , ' ') as 부서번호 -- 그루핑 되어진 값이 0 이라면 그루핑 되어진 값이고 1이면(그룹이 지어지지 않음) 전체값
        
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
   from
   (
    select department_id
         , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end || '자' as GENDER
    from employees
    ) V
    group by grouping sets( (V.GENDER, V.department_id), (V.GENDER), ()); -- 뒤에서부터 없어진다.
   
    
    -------------------------------------------------------------------------------------------
   -- cube 사용시 -- 
    select decode (grouping(V.department_id), 0, nvl(to_char(V.department_id), '인턴')
                                               , ' ') as 부서번호 
        , decode (grouping(v.gender), 0, V.GENDER 
                                       , ' ') as 성별
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
   from
   (
    select department_id
         , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end || '자' as GENDER
    from employees
    ) V
    group by cube(V.department_id, V.GENDER)
--  order by 1;
    order by v.department_id
    
    
    -------------------------------------------------------------------------------------------
   -- grouping sets 사용시 -- 
    select decode (grouping(V.department_id), 0, nvl(to_char(V.department_id), '인턴')
                                               , ' ') as 부서번호 
        , decode (grouping(v.gender), 0, V.GENDER 
                                       , ' ') as 성별
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
   from
   (
    select department_id
         , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end || '자' as GENDER
    from employees
    ) V
    group by grouping sets( (V.department_id, V.GENDER), (v.department_id), (v.gender), () )
    order by v.department_id
    
    
    -------------------------------------------------------------------------------------------
   -- grouping sets 사용시 -- 
    select decode (grouping(V.department_id), 0, nvl(to_char(V.department_id), '인턴')
                                               , ' ') as 부서번호 
        , decode (grouping(v.gender), 0, V.GENDER 
                                       , ' ') as 성별
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
   from
   (
    select department_id
         , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end || '자' as GENDER
    from employees
    ) V
    group by grouping sets( (V.department_id, V.GENDER), () )
    order by v.department_id
    
    
      -------------------------------------------------------------------------------------------
   -- grouping sets 사용시 -- 
    select decode (grouping(V.department_id), 0, nvl(to_char(V.department_id), '인턴')
                                               , ' ') as 부서번호 
        , decode (grouping(v.gender), 0, V.GENDER 
                                       , ' ') as 성별
        , count(*) as 인원수
        , round(count(*) / (select count(*) from employees) * 100, 0) || '%' as 퍼센티지
   from
   (
    select department_id
         , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end || '자' as GENDER
    from employees
    ) V
    group by grouping sets( (V.department_id), (V.GENDER), () )
    order by v.department_id
    
    
    
    
    ---------- ======= ****   having 그룹함수조건절   **** ======= ----------
   /*
       group by 절을 사용하여 그룹함수의 값을 나타내었을때
       그룹함수의 값이 특정 조건에 해당하는 것만 추출하고자 할때는 where 절을 사용하는 것이 아니라
       having 그룹함수조건절 을 사용해야 한다.
   */
   
   -- employees 테이블에서 사원이 10명 이상 근무하는 부서번호와 그 인원수를 나타내세요.
   
   select department_id
        , count(*)
   from employees
   where count(*) >= 10 -- 특정 행만 올리는 것인데 기준이 없으므로 오류가 발생한다(select부터가 아닌 from부터이므로)
   group by department_id
   
   -- 그룹함수에 대한 조건절은 having에 써야한다.
   select department_id as 부서번호
        , count(*) as 인원수
   from employees
   group by department_id
   having count(*) >= 10
   order by 인원수 desc;
   
   
   --- [퀴즈] employees 테이블에서 부서번호별로 월급의 합계를 나타내었을 때
   --        부서번호별 월급의 합계가 50000 이상인 부서에 대해서만
   --        부서번호, 월급의 합계를 나타내세요.
   
   select department_id as 부서번호
        , to_char(sum( nvl(salary + (salary * commission_pct), salary) )
                    , '999,999')as 월급합계
   from employees
   group by department_id
   having sum( nvl(salary + (salary * commission_pct), salary) ) >= 50000
   order by 2 desc;
   
   
   ------- **** !!! 누적(누계)에 대해서 알아봅니다. !!! **** ---------
   /*
        sum(누적되어야할 컬럼명) over(order by 누적되어질 기준이 되는 컬럼명 asc[desc] )
        
        sum(누적되어야할 컬럼명) over(partition by 그룹화 되어질 컬럼명 
                                   order by 누적되어질 기준이 되는 컬럼명 asc[desc] )
   */
   
create table tbl_panmae
 (panmaedate  date
 ,jepumname   varchar2(20)
 ,panmaesu    number
 );
-- Table TBL_PANMAE이(가) 생성되었습니다.
    
 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-2), '새우깡', 10);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-2)+1, '새우깡', 15); 

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-2)+2, '감자깡', 20);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-2)+3, '새우깡', 10);
 
 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-2)+3, '새우깡', 3);
 
 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-1), '고구마깡', 7);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-1)+1, '새우깡', 8); 

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-1)+2, '감자깡', 10);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( add_months(sysdate,-1)+3, '감자깡', 5);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate - 4, '허니버터칩', 30);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate - 3, '고구마깡', 15);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate - 2, '고구마깡', 10);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate - 1, '허니버터칩', 20);


 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate, '새우깡', 10);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate, '새우깡', 10);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate, '감자깡', 5);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate, '허니버터칩', 15);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate, '고구마깡', 20);

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate, '감자깡', 10); 

 insert into tbl_panmae(panmaedate, jepumname, panmaesu)
 values( sysdate, '새우깡', 10);

 commit;
 
 select *
 from tbl_panmae;
 
 -- *** tbl_panmae 테이블에서 '새우깡' 에 대한 일별판매량과 일별누적판매량을 나타내세요 *** --
 select to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') as panmaedate
      , panmaesu
 from tbl_panmae
 where jepumname = '새우깡'
 
 --------------------------------------
    판매일자   일별판매량   일별누적판매량
 --------------------------------------
  2021-11-10 	10           10
  2021-11-11 	15           25
  2021-11-13 	13           38
  2021-12-11 	 8           46
  2022-01-10	30           76
  -------------------------------------
  
  select to_char(panmaedate, 'yyyy-mm-dd') as 판매일자
        ,sum(panmaesu) as 일별판매량
    --  ,sum(누적되어야 할 칼럼명) over(order by 누적되어질 기준이 되는 컬럼명 asc[desc])
        ,sum(sum(panmaesu)) over(order by to_char(panmaedate, 'yyyy-mm-dd') asc) as 일별누적판매량
  from tbl_panmae
  where jepumname = '새우깡'
  group by to_char(panmaedate, 'yyyy-mm-dd')
  order by 판매일자
  
  
  -- *** tbl_panmae 테이블에서 모든제품에 대한 일별판매량과 일별누적판매량을 나타내세요 *** --
  
 -------------------------------------------------
  제품명     판매일자      일별판매량   일별누적판매량
 -------------------------------------------------
  감자깡   2021-11-12	        20	        20
  감자깡   2021-12-12	        10	        30
  감자깡   2021-12-13       	 5	        35
  감자깡   2022-01-10	        15	        50
  
  ....    ..........       ...          ....
  
  새우깡   2021-11-10 	    10          10
  새우깡   2021-11-11 	    15          25
  새우깡   2021-11-13 	    13          38
  새우깡   2021-12-11 	     8          46
  새우깡   2022-01-10	        30          76
  ------------------------------------------------
 
    select jepumname as 제품명
         , to_char(panmaedate, 'yyyy-mm-dd') as 판매일자
         , sum(panmaesu) as 일별판매량
    /*   , sum(누적되어야할 컬럼명) over(partition by 그룹화 되어질 컬럼명 
                                   order by 누적되어질 기준이 되는 컬럼명 asc[desc] )
    */
         , sum(sum(panmaesu)) over(partition by jepumname
                                   order by to_char(panmaedate, 'yyyy-mm-dd') asc )
           as 일별누적판매량
    from tbl_panmae
    group by jepumname, to_char(panmaedate, 'yyyy-mm-dd');
    
    --------------------------------------------------------------------------------
    
    create or replace view view_nujukPanmae
    as 
    select jepumname
         , to_char(panmaedate, 'yyyy-mm-dd') as PANMAEDATE
         , sum(panmaesu) as DAILY_SALES
         , sum(sum(panmaesu)) over(partition by jepumname
                                   order by to_char(panmaedate, 'yyyy-mm-dd') asc )
            as DAILY_NUJUK_SALES
    from tbl_panmae
    group by jepumname, to_char(panmaedate, 'yyyy-mm-dd');
    -- View VIEW_NUJUKPANMAE이(가) 생성되었습니다.
    
    
    select *
    from VIEW_NUJUKPANMAE
    where jepumname in('감자깡', '새우깡');


    -- *** [퀴즈] tbl_panmae 테이블에서 판매일자가 1개월전 '01'일(즉, 현재가 2022년 1월10일 이므로 2021년 12월 1일) 부터 판매된 
    --           모든 제품에 대해 일별판매량과 일별누적판매량을 나타내세요.. ***
    select sysdate
         , add_months(sysdate, -1)
         , to_date(to_char(add_months(sysdate, -1), 'yyyy-mm-') || '01', 'yyyy-mm-dd')
         , last_day(add_months(sysdate, -2)) +1
    from dual
    
    
    select jepumname as 제품명
         , to_char(panmaedate, 'yyyy-mm-dd') as 판매일자
         , sum(panmaesu) as 일별판매량
         , sum(sum(panmaesu)) over(partition by jepumname
                                   order by to_char(panmaedate, 'yyyy-mm-dd') asc )
           as 일별누적판매량
    from tbl_panmae
    where panmaedate >= to_date(to_char(add_months(sysdate, -1), 'yyyy-mm-') || '01', 'yyyy-mm-dd')
    group by jepumname, to_char(panmaedate, 'yyyy-mm-dd');
    
    
    
    select jepumname as 제품명
         , to_char(panmaedate, 'yyyy-mm-dd') as 판매일자
         , sum(panmaesu) as 일별판매량
         , sum(sum(panmaesu)) over(partition by jepumname
                                   order by to_char(panmaedate, 'yyyy-mm-dd') asc )
           as 일별누적판매량
    from tbl_panmae
    where panmaedate >= last_day(add_months(sysdate, -2)) +1
    group by jepumname, to_char(panmaedate, 'yyyy-mm-dd');
    
    
    
    ------- ==== *** 아래처럼 나오도록 하세요 *** ==== -------
    
    -------------------------------------------------
    전체사원수   10대   20대   30대   40대   50대   60대
    -------------------------------------------------
       107      16    18     21     20    16     16
    -------------------------------------------------
    
    select count(v.AGELINE) as 전체사원수
         , sum(decode(v.ageline, 10, 1)) as "10대" -- 별칭에 있어서 첫글자가 숫자라면 반드시 ""로 처리해야한다.
         , sum(decode(v.ageline, 20, 1)) as "20대"
         , sum(decode(v.ageline, 30, 1)) as "30대"
         , sum(decode(v.ageline, 40, 1)) as "40대"
         , sum(decode(v.ageline, 50, 1)) as "50대"
         , sum(decode(v.ageline, 60, 1)) as "60대"
    from
    (
    select trunc(extract(year from sysdate) - (substr(jubun,1,2) + case when substr(jubun,7,1) in ('1','2') then 1900 else 2000 end) + 1, -1) as AGELINE
    from employees
    ) V
    
    
    
    select count(v.AGELINE) as 전체사원수
         , decode(v.ageline, 10, 1) as "10대" 
         , decode(v.ageline, 20, 1) as "20대"
         , decode(v.ageline, 30, 1) as "30대"
         , decode(v.ageline, 40, 1) as "40대"
         , decode(v.ageline, 50, 1) as "50대"
         , decode(v.ageline, 60, 1) as "60대"
    from
    (
    select trunc(extract(year from sysdate) - (substr(jubun,1,2) + case when substr(jubun,7,1) in ('1','2') then 1900 else 2000 end) + 1, -1) as AGELINE
    from employees
    ) V
    
    
    ---------- ===== *** [퀴즈] 아래처럼 나오도록 하세요 *** ===== ----------
   
   select employee_id, first_name, job_id
   from employees;
   
   
   --------------------------------------------------------------------------------------------------------------------------------------
     직종ID          남자기본급여평균    여자기본급여평균     기본급여평균    (남자기본급여평균 - 기본급여평균)       (여자기본급여평균 - 기본급여평균)
   --------------------------------------------------------------------------------------------------------------------------------------
     ........           ....              ....             ....                   ...                                 ...     
     FI_ACCOUNT         7900              7950             7920                   -20                                  30 
     IT_PROG            5700              6000             5760                   -60                                 240 
     ........           ....              ....             ....                   ...                                 ...   
   --------------------------------------------------------------------------------------------------------------------------------------
   -- 직종별 급여 평균
   
   
   select v.job_id as 직종ID
        , trunc(avg(v.MAN_SALARY)) as "남자급여평균"
        , trunc(avg(v.WOMAN_SALARY)) as "여자급여평균"
        , trunc(avg(salary)) as "기본급여평균"
        , trunc((avg(v.MAN_SALARY) - avg(salary))) as "(남자 - 기본급여평균)"
        , trunc((avg(v.WOMAN_SALARY) - avg(salary))) as "(여자 - 기본급여평균)"
   from
   (
   select job_id
        , case when substr(jubun, 7, 1) in ('1','3') then '남' else '여' end as gender
        , salary
        , case when substr(jubun, 7, 1) in ('1','3') then salary end as MAN_SALARY
        , case when substr(jubun, 7, 1) in ('2','4') then salary end as WOMAN_SALARY
   from employees
   )v
   group by v.job_id
   
   
   
   -------------------------------------------------------------------------------------
   
   ----- ===== **** Sub Query(서브쿼리) **** ===== -----
   
   /*
       -- Sub Query (서브쿼리)란?
       select 문속에 또 다른 select 문이 포함되어져 있을 때 포함되어진 select 문을 Sub Query (서브쿼리)라고 부른다.
       
       
       select ...
       from .....  ==> Main Query(메인쿼리 == 외부쿼리)
       where ... in (select ... 
                     from .....) ==> Sub Query (서브쿼리 == 내부쿼리)
   */
   
   /*
       employees 테이블에서
       기본급여가 제일 많은 사원과 기본급여가 제일 적은 사원의 정보를
       사원번호, 사원명, 기본급여로 나타내세요...
   */
   /*
    - SELECT 문에 있는 서브쿼리 : Scalar Subquery
    - FROM 절에 있는 서브쿼리 : Inline View
    - WHERE 절에 있는 서브쿼리 : Subquery
   */
   from employees
   where salary = (employees 테이블에서 salary 의 최대값) OR
         salary = (employees 테이블에서 salary 의 최소값);
         
   employees 테이블에서 salary 의 최대값 ==> select max(salary) from employees ==> 24000
   employees 테이블에서 salary 의 최대값 ==> select min(salary) from employees ==>  2100
   
   
   select employee_id as 사원번호
        , first_name || ' ' || last_name as 사원명
        , salary as 기본급여
   from employees
   where salary = (select max(salary) from employees) OR
         salary = (select min(salary) from employees);
    
   /*
   ----------------------------
   사원번호     사원명     기본급여
   ----------------------------
    100    Steven King   24000
    200    TJ Olson       2100
   ----------------------------
   */
   
   
   /*
      [퀴즈]
      employees 테이블에서 부서번호가 60, 80번 부서에 근무하는 사원들중에
      월급이 50번 부서 직원들의 '평균월급' 보다 많은 사원들만 
      부서번호, 사원번호, 사원명, 월급을 나타내세요....
   */
   
   
   select department_id as 부서번호
        , employee_id as 사원번호
        , first_name || last_name as 사원명
        , NVL( salary + (salary * commission_pct), salary) as 월급
   from employees
   where NVL( salary + (salary * commission_pct), salary) > (select avg(NVL( salary + (salary * commission_pct), salary))
                                                             from employees 
                                                             where department_id = '50')
     and department_id in ('60','80')
   order by 1, 4 desc;
   
   ----------------------------------------------------------------------------
   
   create table tbl_authorbook
   (bookname       varchar2(100) -- 도서명
   ,authorname     varchar2(20)  -- 작가명
   ,loyalty        number(5)
   );
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('자바프로그래밍','이순신',1000);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('로빈슨크루소','한석규',800);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('로빈슨크루소','이순신',500);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('조선왕조실록','엄정화',2500);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('그리스로마신화','유관순',1200);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('그리스로마신화','이혜리',1300);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('그리스로마신화','서강준',1700);

   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('어린왕자','김유신',1800);
   
   commit;
   
   
   select * 
   from tbl_authorbook;
   
   ---  tbl_authorbook 테이블에서 공저(도서명은 동일하지만 작가명이 다른 도서)로 지어진 도서정보를 나타내세요... ---
   
   /*
       ---------------------------------
         도서명         작가명    로얄티
       ---------------------------------  
         로빈슨크루소    한석규    800
         로빈슨크루소    이순신    500
         그리스로마신화  유관순   1200
         그리스로마신화  이혜리   1300
         그리스로마신화  서강준   1700
       ---------------------------------  
   */
   
   select bookname, count(*)
   from tbl_authorbook
   group by bookname
/*
    ------------------------
    bookname       count(*)
    ------------------------
    로빈슨크루소	      2
    그리스로마신화	      3
    자바프로그래밍	      1
    조선왕조실록	      1
    어린왕자	          1
*/


   select bookname, count(*)
   from tbl_authorbook
   group by bookname
   having count(*) > 1
   
   
   select bookname
   from tbl_authorbook
   group by bookname
   having count(*) > 1
   
   
   select *
   from tbl_authorbook
   where bookname in( select bookname
                      from tbl_authorbook
                      group by bookname
                      having count(*) > 1 );
                      

  ------- **** Sub Query (서브쿼리)에서 사용되어지는 ANY , ALL 에 대해서 알아봅니다. **** --------
   /*
       Sub Query (서브쿼리) 에서 사용되어지는 ANY 는 OR 와 흡사하고, 
       Sub Query (서브쿼리) 에서 사용되어지는 ALL 은 AND 와 흡사하다.
   */
   
   -- employees 테이블에서 salary 가 30번 부서에 근무하는 사원들의 salary 와 동일한 사원들만 추출하세요..
   -- 단, 출력시 30번 부서에 근무하는 사원은 제외합니다.
   
   from employees
   where salary = (30번 부서에 근무하는 사원들의 salary);
   
   -- 30번 부서에 근무하는 사원들의 salary
   select salary 
   from employees
   where department_id = 30;
   /*
       11000
        3100
        2900
        2800
        2600
        2500
   */
   
   select employee_id, first_name, salary, department_id 
   from employees
   where department_id != 30 AND 
         salary in(select salary 
                   from employees
                   where department_id = 30)
   order by 4, 3 desc;
   
   
   select employee_id, first_name, salary, department_id 
   from employees
   where department_id != 30 AND 
         salary =ANY (select salary 
                      from employees
                      where department_id = 30)
   order by 4, 3 desc;
   
   /*
      기본급여(salary)가 제일 많은 사원만
      사원번호, 사원명, 기본급여(salary)를 나타내세요.
   */
   from employees
   where salary = (사원들중에 기본급여(salary)가 제일큰값);
   
   select employee_id, first_name, salary, department_id 
   from employees
   where salary = (select max(salary) from employees);
   
   select employee_id, first_name, salary, department_id 
   from employees
   where salary >=ALL (select salary from employees);
   -- ALL 은 모든 것이 참일때만 보여진다.
   
   /*
      기본급여(salary)가 제일 많은 사원을 제외한 나머지 사원들만 
      사원번호, 사원명, 기본급여(salary)를 나타내세요.
   */
   select employee_id, first_name, salary, department_id 
   from employees
   where salary != (select max(salary) from employees);
   
   select employee_id, first_name, salary, department_id 
   from employees
   where salary < (select max(salary) from employees);
   
   -- OR 는 참이 1개만이라도 포함되면 참이다.
   select employee_id, first_name, salary, department_id 
   from employees
   where salary <ANY (select salary from employees); 
   -- 자동으로 salary 컬럼에 대해 오름차순으로 정렬되어져 나온다. 
   /*
         salary < (24000
         24000     17000
                    9000
                    ....
                    2100)
   */  
   
   
   /*
      commission_pct 가 제일 많은 사원만   
      사원번호, 사원명, commission_pct 를 나타내세요.
   */
   from employees
   where commission_pct = (commission_pct 가 제일큰값);
   
   select employee_id, first_name, commission_pct, department_id 
   from employees
   where commission_pct = (select max(commission_pct) from employees);
   -- 145	John	0.4	  80
   
   select employee_id, first_name, commission_pct, department_id 
   from employees
   where commission_pct >=ALL (select commission_pct from employees);
   -- 위의 처럼 하면 결과물은 아무것도 안나온다.
   -- *** Sub Query 절에서 사용하는 ALL 은 사용시 주의를 요한다.
   --     Sub Query 절에서 select 되어지는 결과물에 NULL 이 존재하지 않도록 만들어야 한다. !!!
   
   select employee_id, first_name, commission_pct, department_id 
   from employees
   where commission_pct >=ALL (select commission_pct from employees
                               where commission_pct is not null);
   -- 145	John	0.4	  80
   -- 위의 처럼 Sub Query 절에서 select 되어지는 결과물에 NULL 이 존재하지 않도록 만들어야 한다. !!!                                
   
   
   /*
      commission_pct 가 제일 많은 사원을 제외한 나머지 사원들만 
      사원번호, 사원명, commission_pct 를 나타내세요.
   */
   select employee_id, first_name, commission_pct, department_id 
   from employees
   where commission_pct != (select max(commission_pct) from employees);
   -- 34명 출력됨.
   
   select employee_id, first_name, commission_pct, department_id 
   from employees
   where commission_pct < (select max(commission_pct) from employees);
   -- 34명 출력됨.
   
   select employee_id, first_name, commission_pct, department_id 
   from employees
   where commission_pct <ANY (select commission_pct from employees);
   -- 34명 출력됨.
   -- 자동으로 commission_pct 컬럼에 대해 오름차순으로 정렬되어져 나온다. 
   
   
   
   
   
   
   ------ ===== *** Pairwise(쌍) Sub Query *** ===== ------
   /*
      employees 테이블에서
      부서번호별로 salary 가 최대인 사원과
      부서번호별로 salary 가 최소인 사원의 정보를
      부서번호, 사원번호, 사원명, 기본급여를 나타내세요....
   */
   select department_id, salary 
   from employees
   order by 1, 2;
   
   /*
      원하는 값
      -------------------------------
       department_id     salary
      -------------------------------
        10	             4400
        20	             6000
        20	            13000
        30	             2500
        30	            11000
        40	             6500
        50	             2100
        50	             8200
        60	             4200
        60	             9000
        ...              ....
   */
   select department_id AS 부서번호
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
   from employees
   where (department_id, salary) in (select department_id, min(salary)
                                     from employees 
                                     group by department_id)
         OR
         (department_id, salary) in (select department_id, max(salary)
                                     from employees 
                                     group by department_id)
   order by 부서번호, 기본급여; 
   
   
   select department_id AS 부서번호
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
   from employees
   where (NVL(department_id, -9999), salary) in (select NVL(department_id, -9999), min(salary)
                                                 from employees 
                                                 group by department_id)
         OR
         (NVL(department_id, -9999), salary) in (select NVL(department_id, -9999), max(salary)
                                                 from employees 
                                                 group by department_id)
   order by 부서번호, 기본급여;





   ------ ===== *** Pairwise(쌍) Sub Query *** ===== ------
   /*
      employees 테이블에서
      부서번호별로 salary 가 최대인 사원과
      부서번호별로 salary 가 최소인 사원의 정보를
      부서번호, 사원번호, 사원명, 기본급여를 나타내세요....
   */
   select department_id, salary 
   from employees
   order by 1, 2;
   
   /*
      원하는 값
      -------------------------------
       department_id     salary
      -------------------------------
        10	             4400
        20	             6000
        20	            13000
        30	             2500
        30	            11000
        40	             6500
        50	             2100
        50	             8200
        60	             4200
        60	             9000
        ...              ....
   */
   select department_id AS 부서번호
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
   from employees
   where (department_id, salary) in (select department_id, min(salary)
                                     from employees 
                                     group by department_id)
         OR
         (department_id, salary) in (select department_id, max(salary)
                                     from employees 
                                     group by department_id)
   order by 부서번호, 기본급여; 
   
   
   select department_id AS 부서번호
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
   from employees
   where (NVL(department_id, -9999), salary) in (select NVL(department_id, -9999), min(salary)
                                                 from employees 
                                                 group by department_id)
         OR
         (NVL(department_id, -9999), salary) in (select NVL(department_id, -9999), max(salary)
                                                 from employees 
                                                 group by department_id)
   order by 부서번호, 기본급여;





   --------- ===== **** 상관서브쿼리(== 서브상관쿼리) ****  ===== ---------    
   /*
      상관서브쿼리 이라함은 Main Query(== 외부쿼리)에서 사용된 테이블(뷰)에 존재하는 컬럼이
      Sub Query(== 내부쿼리)의 조건절(where절, having절)에 사용되어질때를 
      상관서브쿼리(== 서브상관쿼리)라고 부른다.
   */
   
   -- employees 테이블에서 기본급여에 대해 전체등수 및 부서내등수를 구하세요.
   -- 첫번째 방법은 rank() 함수를 사용하여 구해본다.
   select department_id AS 부서번호   
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
        , rank() over(order by salary desc) AS 전체등수 
        , rank() over(partition by department_id 
                      order by salary desc) AS 부서내등수 
   from employees
   order by 부서번호, 기본급여 desc;
   
   
   -- employees 테이블에서 기본급여에 대해 전체등수 및 부서내등수를 구하세요.
   -- 두번째 방법은 count(*) 를 이용하여 상관서브쿼리를 사용하여 구해본다.
   select salary 
   from employees
   order by salary desc;
   
   -- 자신의 급여가 14000 이라면 몇등일까?
   select count(*)+ 1 AS 등수
   from employees
   where salary > 14000;
   
   update employees set department_id = null
   where employee_id = 100;
   
   select department_id AS 부서번호   
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
        
        , (select count(*)+ 1
           from employees
           where salary > E.salary) AS 전체등수 
           
        , (select count(*)+ 1
           from employees
           where NVL(department_id, -9999) = NVL(E.department_id, -9999) AND 
                 salary > E.salary) AS 부서내등수 
   from employees E
   order by 부서번호, 기본급여 desc;

   rollback;
   
   select employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        
        , (select count(*)+ 1
           from employees
           where salary > E.salary) AS 전체등수 
           
        , (select count(*)+ 1
           from employees
           where NVL(department_id, -9999) = NVL(E.department_id, -9999) AND 
                 salary > E.salary) AS 부서내등수 
   from employees E
   order by 전체등수 asc;
   
   
   ----- === *** Sub Query 를 사용하여 테이블을 생성할 수 있습니다. *** === -----
   create table tbl_employees_3060
   as
   select department_id
        , employee_id
        , first_name || ' ' || last_name AS ENAME
        , nvl(salary + (salary * commission_pct), salary) AS MONTHSAL
        , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS GENDER
        , jubun
   from employees
   where department_id in (30, 60);
   -- Table TBL_EMPLOYEES_3060이(가) 생성되었습니다.
   
   select * from tab;
   
   select * 
   from TBL_EMPLOYEES_3060;
   
   
   ----- === *** employees 테이블을 가지고 데이터 없이 
   --            employees 테이블의 구조만 동일한 tbl_employees_sub 이라는
   --            테이블을 생성하겠습니다. *** === -----
   select *
   from employees
   where 1=1;
   
   select *
   from employees
   where 1=2;
   
   select *
   from employees
   where 2=2;
   
   
   create table tbl_employees_sub
   as 
   select *
   from employees
   where 1=2;
   -- Table TBL_EMPLOYEES_SUB이(가) 생성되었습니다.
   
   select * from tab;
   
   select * 
   from TBL_EMPLOYEES_SUB;
   
   desc TBL_EMPLOYEES_SUB;
   
   
   ---- **** !!!! 필수로 꼭 알아두시길 바랍니다. !!!! **** ----
   -- === 상관서브쿼리(=서브상관쿼리)를 사용한 UPDATE 처리하기 === --
   /*
      회사에 입사하셔서 delete 또는 update 를 할 때 먼저 반드시 해당 테이블을 백업해 두시고
      그런 다음에 작업을 하시길 바랍니다. 백업본이 있으면 실수를 하더라도 복구가 가능하기 때문입니다.
   */
   create table tbl_employees_backup_220111
   as
   select * 
   from employees;
   -- Table TBL_EMPLOYEES_BACKUP_220111이(가) 생성되었습니다.
   
   select * 
   from tbl_employees_backup_220111;
   
   update employees set first_name = '순신', last_name = '이';
   -- 107개 행 이(가) 업데이트되었습니다.
   
   commit;
   -- 커밋 완료.
   
   select * 
   from employees;
   
   update employees E set first_name = ( select first_name 
                                         from tbl_employees_backup_220111
                                         where employee_id = E.employee_id)
                        , last_name = ( select last_name 
                                        from tbl_employees_backup_220111
                                        where employee_id = E.employee_id); 
   -- 107개 행 이(가) 업데이트되었습니다.
   -- 위와같이 올바르게 복구 되기 위해서는 Sub Query 절의 where 에서 사용된 
   -- employee_id 컬럼은 고유한 값만 가지는 컬럼이어야 한다.
   
   select * 
   from employees;
   
   commit;
   -- 커밋 완료.
   
   
   
   ----- === *** Sub Query 절을 사용하여 데이터를 입력(insert) 할 수 있습니다. *** === -----
   select * 
   from TBL_EMPLOYEES_3060;
   
   desc TBL_EMPLOYEES_3060;
   
   insert into TBL_EMPLOYEES_3060
   select department_id
        , employee_id
        , first_name || ' ' || last_name AS ENAME
        , nvl(salary + (salary * commission_pct), salary) AS MONTHSAL
        , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS GENDER
        , jubun
   from employees
   where department_id in (40, 50)
   order by 1;
   -- 46개 행 이(가) 삽입되었습니다.
   
   select * 
   from TBL_EMPLOYEES_3060;
   
 --  rollback;
   commit;
   
   
   ----- === *** Sub Query 절을 사용하여 데이터를 수정(update) 할 수 있습니다. *** === -----
   update TBL_EMPLOYEES_3060 set monthsal = ( select TRUNC( AVG(nvl(salary +(salary * commission_pct), salary)) )
                                              from employees
                                              where department_id = 50
                                            )
   where department_id = 30;
   -- 6개 행 이(가) 업데이트되었습니다.
   
   commit;
   
   select * 
   from TBL_EMPLOYEES_3060;
   
   
   ----- === *** Sub Query 절을 사용하여 데이터를 삭제(delete) 할 수 있습니다. *** === -----
   
   delete from TBL_EMPLOYEES_3060
   where department_id = ( select department_id
                           from employees
                           where employee_id = 118 )

   rollback
   
   
   
   ---------------------------------------------------------------------------------------
   ---------------------------------------------------------------------------------------
                -- !!!! 중요 JOIN 은 면접에 가시면 무조건 물어봅니다.!!      중요 !!!! --
   ---------------------------------------------------------------------------------------
   
              ------- ====== **** JOIN **** ====== --------
   /*
       JOIN(조인)은 테이블(뷰)과 테이블(뷰)을 합치는 것을 말하는데 
       행(ROW) 과 행(ROW)을 합치는 것이 아니라, 컬럼(COLUMN) 과 컬럼(COLUMN)을 합치는 것을 말한다.
       위에서 말한 행(ROW) 과 행(ROW)을 합치는 것은 UNION 연산자를 사용하는 것이다.
   
       -- 면접질문 : INNER JOIN(내부조인) 과 OUTER JOIN(외부조인)의 차이점에 대해 말해보세요.
       -- 면접질문 : JOIN 과 UNION 의 차이점에 대해서 말해보세요.
       
       
       A = {1, 2, 3}    원소가 3개
       B = {a, b}       원소가 2개
       
       A ⊙ B = { (1,a), (1,b)
                 ,(2,a), (2,b)
                 ,(3,a), (3,b) }
                 
       데카르트곱(수학)  ==> 원소의 곱 :   3 * 2 = 6개(모든 경우의 수)
       --> 수학에서 말하는 데카르트곱을 데이터베이스에서는 Catersian Product(카테시안 프로덕트) 라고 부른다.
       
       
       JOIN  =>  SQL 1992 CODE 방식  -->  테이블(뷰) 과 테이블(뷰) 사이에 콤마(,)를 찍어주는 것.  
                                         콤마(,)를 찍어주는 것을 제외한 나머지 문법은 데이터베이스 밴더(회사) 제품마다 조금씩 다르다.   
       
       JOIN  =>  SQL 1999 CODE 방식(ANSI) --> 테이블(뷰) 과 테이블(뷰) 사이에 JOIN 이라는 단어를 넣어주는 것.
                                             ANSI(표준화) SQL
   */
   
   select *
   from employees;
   
   select count(*)
   from employees;
   
   select *
   from departments;
   
   select count(*)
   from departments;
   
   select *
   from employees , departments; --> SQL 1992 CODE 방식
   
   
   select count(*)
   from employees , departments; --> SQL 1992 CODE 방식
                                 -- 2889 개행
                                 
    select 107*27
    from dual;
    
    select * 
    from employees cross join departments; --> SQL 1999 CODE 방식(ANSI)
    
    select count(*)
    from employees CROSS JOIN departments; --> SQL 1999 CODE 방식
                                          -- 2889 개행
    
    /*
      1. CROSS JOIN 은 프로야구를 예로 들면 10개팀이 있는데 
         각 1팀당 경기를 몇번해야 하는지 구할때 쓰인다. 1팀당 모든 팀과 경기를 펼쳐야 한다. 
         
      2. CROSS JOIN 은 그룹함수로 나온 1개의 행을 가지고 어떤 데이터 값을 얻으려고 할때 사용된다. 
    */
    --- 100개행 * 1개행 == 100개행
    
    -- [ CROSS JOIN 사용 예 ]
    /*
      사원번호    사원명    부서번호    기본급여    모든사원들의기본급여평균    기본급여평균과의차액    
      이 나오도록 하세요..
    */
    select employee_id AS 사원번호
         , first_name || ' ' || last_name AS 사원명
         , department_id AS 부서번호
         , salary AS 기본급여
         , avg(salary) as 기본급여평균
    from employees; -- 오류!
    
    select employee_id AS 사원번호
         , first_name || ' ' || last_name AS 사원명
         , department_id AS 부서번호
         , salary AS 기본급여
    from employees;  -- 107 개행
    
    select trunc(avg(salary)) as 기본급여평균  -- 6461
    from employees;  -- 1 개행
    
    -- ( 사원번호    사원명    부서번호    기본급여 ) + (기본급여평균)
    
    select a.employee_id
         , FULLNAME
         , department_id
         , salary
         , AVG_SALARY
         , salary - AVG_SALARY as "평균차액"
    from
    (
    select employee_id  
         , first_name || ' ' || last_name as FULLNAME
         , department_id 
         , salary 
    from employees
    ) A
    ,
    (
    select trunc(avg(salary)) as AVG_SALARY  -- 6461
    from employees
    ) B;
    
    
    select a.employee_id
         , FULLNAME
         , department_id
         , salary
         , AVG_SALARY
         , salary - AVG_SALARY as "평균차액"
    from
    (
    select employee_id  
         , first_name || ' ' || last_name as FULLNAME
         , department_id 
         , salary 
    from employees
    ) A
    CROSS JOIN
    (
    select trunc(avg(salary)) as AVG_SALARY  -- 6461
    from employees
    ) B; -- SQL 1999 CODE
    
    
    ---- **** EQUI JOIN (SQL 1992 CODE 방식) **** ----
    /*
        [EQUI JOIN 예]
        
        부서번호    부서명     사원번호    사원명     기본급여
        이 나오도록 하세요...
    */
    
    /*   부서번호                       부서명         사원번호    사원명     기본급여
       -----------                     ------       ------------------------------
       departments.department_id       departments             employees
       employees.department_id
    */
    
    select *
    from employees, departments  -- SQL 1992 CODE
    where employees.department_id = departments.department_id;  -- 조인조건절
    
    
    select *
    from employees E, departments D -- SQL 1992 CODE
    where E.department_id = D.department_id;  -- 조인조건절
    -- E.department_id(부서번호) 값이 NULL 인 것은 출력되지 않는다.
    
    
    select *
    from employees E, departments D -- SQL 1992 CODE
    where E.department_id = D.department_id(+);  -- 조인조건절
    -- (+) 가 없는 쪽의 테이블인 employee E 테이블의 모든 행들을 먼저 출력해줍니다. 
    -- 즉 107 개 행을 모두 출력한 후 다음에 조인조건절이 들어간다.
    -- E.department_id(부서번호) 값이 NULL 인 것은 출력되지 않는다.
    
    
    ---- **** INNER JOIN [내부조인] SQL 1999 CODE 방식 **** ----
    select *
    from employees E INNER JOIN departments D
    on E.department_id = D.department_id;
    
    
     ---- **** LEFT (OUTER) JOIN [외부조인] (SQL 1999 CODE 방식) **** ----
    select *
 -- from employees E LEFT OUTER JOIN departments D  -- SQL 1999 CODE 
    from employees E LEFT JOIN departments D  -- OUTER 는 생략가능하다.
    ON E.department_id = D.department_id; -- 조인조건절
    -- LEFT OUTER JOIN 글자를 기준으로 왼쪽에 기술된 employees E 테이블의 모든 행들을 먼저 출력해줍니다. 
    -- 즉 107개 행을 모두 출력한 후 그런 다음에 조인조건절에 들어간다. 
    -- E.department_id(부서번호) 값이 NULL 인 것도 출력된다.
 
 
    ---- **** RIGHT (OUTER) JOIN [외부조인] (SQL 1999 CODE 방식) **** ----
    select *
 -- from employees E RIGHT OUTER JOIN departments D  -- SQL 1999 CODE 
    from employees E RIGHT JOIN departments D  -- OUTER 는 생략가능하다. 
    ON E.department_id = D.department_id; -- 조인조건절
    -- RIGHT OUTER JOIN 글자를 기준으로 오른쪽에 기술된 departments D 테이블의 모든 행들을 먼저 출력해줍니다. 
    -- 즉 27개 행을 모두 출력한 후 그런 다음에 조인조건절에 들어간다. 
    -- E.department_id(부서번호) 값이 NULL 인 것은 출력되지 않는다.
    
    
    ---- **** FULL (OUTER) JOIN [외부조인] (SQL 1999 CODE 방식) **** ----
    select *
 -- from employees E FULL OUTER JOIN departments D  -- SQL 1999 CODE 
    from employees E FULL JOIN departments D  -- OUTER 는 생략가능하다. 
    ON E.department_id = D.department_id; -- 조인조건절
    -- FULL OUTER JOIN 글자를 기준으로 양쪽에 기술된 employees E 테이블 과 departments D 테이블의 모든 행들을 먼저 출력해줍니다. 
    -- 즉 107개행 과 27개 행을 모두 출력한 후 그런 다음에 조인조건절에 들어간다. 
    -- E.department_id(부서번호) 값이 NULL 인 것도 출력되고 
    -- 페이퍼 부서인 부서번호 120번 부터 270번 부서까지도 출력된다.
    
    
    ---- === *** JOIN 을 사용한 응용문제 *** === ----
    
    /*
        아래와 같이 나오도록 하세요.
        
        -----------------------------------------------------------------------
         부서번호   사원번호   사원명   기본급여   부서평균기본급여    부서평균과의차액
        -----------------------------------------------------------------------
        
        
        ---------------------------       --------------------------------------------
          부서번호   부서평균기본급여          부서번호   사원번호   사원명    기본급여
        ---------------------------       --------------------------------------------
            10          3500                 10        1001     홍길동    3700
            20          4000                 10        1002     이순신    2500
            30          2800                 20        2001     엄정화    3500
            ..          ....                 20        2002     유관순    4500
            110         3200                 ..        ....     .....    .....
         --------------------------       ---------------------------------------------
    */
    
    --- *** 아래의 풀이는 부서번호가 NULL 인 Kimberely Grant 는 안 나온다. *** ---
    
    select v1.department_id as 부서번호
         , v2.employee_id as 사원번호
         , v2.ENAME as 사원명
         , v2.salary as 기본급여
         , v1.DEPT_AVG_SAL as 부서평균기본급여
         , (v2.salary - v1.DEPT_AVG_SAL) as 부서평균과의차액
    from
    (
    select department_id
         , trunc(avg(salary)) as DEPT_AVG_SAL
    from employees
    group by department_id
    ) V1
    JOIN
    (
    select department_id
         , employee_id
         , first_name || ' ' || last_name as ENAME
         , salary
    from employees
    ) V2
    ON V1.department_id = V2.department_id
    order by 1, 4 desc

    
    --- *** 아래의 풀이는 틀린 풀이다. *** ---
    
    select v1.department_id as 부서번호
         , v2.employee_id as 사원번호
         , v2.ENAME as 사원명
         , v2.salary as 기본급여
         , v1.DEPT_AVG_SAL as 부서평균기본급여
         , (v2.salary - v1.DEPT_AVG_SAL) as 부서평균과의차액
    from
    (
    select department_id
         , trunc(avg(salary)) as DEPT_AVG_SAL
    from employees
    group by department_id
    ) V1
    LEFT JOIN
    (
    select department_id
         , employee_id
         , first_name || ' ' || last_name as ENAME
         , salary
    from employees
    ) V2
    ON V1.department_id = V2.department_id
    order by 1, 4 desc;
    
    
    --- *** 아래의 풀이는 틀린 풀이다. *** ---
    
    select v1.department_id as 부서번호
         , v2.employee_id as 사원번호
         , v2.ENAME as 사원명
         , v2.salary as 기본급여
         , v1.DEPT_AVG_SAL as 부서평균기본급여
         , (v2.salary - v1.DEPT_AVG_SAL) as 부서평균과의차액
    from
    (
    select department_id
         , trunc(avg(salary)) as DEPT_AVG_SAL
    from employees
    group by department_id
    ) V1
    FULL JOIN
    (
    select department_id
         , employee_id
         , first_name || ' ' || last_name as ENAME
         , salary
    from employees
    ) V2
    ON V1.department_id = V2.department_id
    order by 1, 4 desc;
    
    
    --- *** 아래의 풀이가 올바른 풀이다. *** ---
    
    select v1.department_id as 부서번호
         , v2.employee_id as 사원번호
         , v2.ENAME as 사원명
         , v2.salary as 기본급여
         , v1.DEPT_AVG_SAL as 부서평균기본급여
         , (v2.salary - v1.DEPT_AVG_SAL) as 부서평균과의차액
    from
    (
    select department_id
         , trunc(avg(salary)) as DEPT_AVG_SAL
    from employees
    group by department_id
    ) V1
    FULL JOIN
    (
    select department_id
         , employee_id
         , first_name || ' ' || last_name as ENAME
         , salary
    from employees
    ) V2
    ON nvl(V1.department_id, -9999) = nvl(V2.department_id, -9999)
    order by 1, 4 desc;

/*
    [퀴즈] 아래와 같이 나오도록 하세요.
    
    -----------------------------------------------------------------------------------------------------------------
         부서번호   사원번호   사원명   기본급여   부서평균기본급여    부서평균과의차액   부서내기본급여등수   전체기본급여등수
    -----------------------------------------------------------------------------------------------------------------
*/

    select v1.department_id as 부서번호
         , v2.employee_id as 사원번호
         , v2.ENAME as 사원명
         , v2.salary as 기본급여
         , v1.DEPT_AVG_SAL as 부서평균기본급여
         , (v2.salary - v1.DEPT_AVG_SAL) as 부서평균과의차액
         , rank() over(partition by v1.department_id
                        order by salary desc) as 부서내기본급여등수
         , rank() over(order by salary desc) 전체기본급여등수
    from
    (
    select department_id
         , trunc(avg(salary)) as DEPT_AVG_SAL
    from employees
    group by department_id
    ) V1
    JOIN
    (
    select department_id
         , employee_id
         , first_name || ' ' || last_name as ENAME
         , salary
    from employees
    ) V2
    ON nvl(V1.department_id, -9999) = nvl(V2.department_id, -9999)
    order by 8;
    
    
/*
    [퀴즈] 부서번호가 10, 30, 50 번 부서에 근무하는 사원들만 아래와 같이 나오도록 하세요.
    
    -----------------------------------------------------------------------------------------------------------------
         부서번호   사원번호   사원명   기본급여   부서평균기본급여    부서평균과의차액   부서내기본급여등수   전체기본급여등수
    -----------------------------------------------------------------------------------------------------------------
*/

    select v1.department_id as 부서번호
         , v2.employee_id as 사원번호
         , v2.ENAME as 사원명
         , v2.salary as 기본급여
         , v1.DEPT_AVG_SAL as 부서평균기본급여
         , (v2.salary - v1.DEPT_AVG_SAL) as 부서평균과의차액
         , rank() over(partition by v1.department_id
                        order by salary desc) as 부서내기본급여등수
         , rank() over(order by salary desc) 전체기본급여등수
    from
    (
    select department_id
         , trunc(avg(salary)) as DEPT_AVG_SAL
    from employees
    where department_id in (10, 30, 50)
    group by department_id
    ) V1
    JOIN
    (
    select department_id
         , employee_id
         , first_name || ' ' || last_name as ENAME
         , salary
    from employees
    where department_id in (10, 30, 50)
    ) V2
    ON nvl(V1.department_id, -9999) = nvl(V2.department_id, -9999) 
    order by 8; -- 필요한 것만 가져오고 매핑해야함
    
    
    --- 또는
    --- ===== *** WITH 절을 사용한 inline view 를 통해 JOIN 하기 *** ===== ---
    
    
    WITH V1 as (
        select department_id
             , trunc(avg(salary)) as DEPT_AVG_SAL
        from employees
        where department_id in (10, 30, 50)
        group by department_id
    ),
    V2 as (
        select department_id
             , employee_id
             , first_name || ' ' || last_name as ENAME
             , salary
        from employees
        where department_id in (10, 30, 50)
    )
    
    select v1.department_id as 부서번호
         , v2.employee_id as 사원번호
         , v2.ENAME as 사원명
         , v2.salary as 기본급여
         , v1.DEPT_AVG_SAL as 부서평균기본급여
         , (v2.salary - v1.DEPT_AVG_SAL) as 부서평균과의차액
         , rank() over(partition by v1.department_id
                        order by salary desc) as 부서내기본급여등수
         , rank() over(order by salary desc) 전체기본급여등수
    from V1 join v2
    on v1.department_id = v2.department_id
    
    
    
    
    
        ---------- ======= **** NON-EQUI JOIN  **** ======= ---------- 
/*
     조인조건절에 사용되는 컬럼의 값이 특정한 범위에 속할 때 사용하는 것이 NON-EQUI JOIN 이다.
     NON-EQUI JOIN 에서는 조인조건절에 = 을 사용하는 것이 아니라 between A and B 를 사용한다.
*/

  -- 소득세율 지표 관련 테이블을 생성한다. 
  create table tbl_taxindex
  (lowerincome   number       -- 연봉의 최저
  ,highincome    number       -- 연봉의 최대
  ,taxpercent    number(2,2)  -- 세율  -0.99 ~ 0.99 
  );
   
  insert into tbl_taxindex(lowerincome,highincome,taxpercent)
  values(1, 99999, 0.02);

  insert into tbl_taxindex(lowerincome,highincome,taxpercent)
  values(100000, 149999, 0.05);

  insert into tbl_taxindex(lowerincome,highincome,taxpercent)
  values(150000, 199999, 0.08);

  insert into tbl_taxindex(lowerincome,highincome,taxpercent)
  values(200000, 10000000000000000, 0.1);

  commit;
  
  select * 
  from tbl_taxindex;
  
  ------------------------------------------------------
   사원번호     사원명     연봉     세율      소득세액
  ------------------------------------------------------
    1001       홍길동    50000    0.02      50000 *  0.02
    1002       엄정화   170000    0.08     170000 *  0.08
    ....       ......  ......    .....     .............
  
  
   --- SQL 1992 CODE ---
   /*
       사원번호     사원명     연봉     세율 
       --------------------------    =====
            employees 테이블          tbl_taxindex 테이블 
   */
   
   select employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , nvl(salary + (salary * commission_pct), salary)*12 AS 연봉
        , taxpercent AS 세율 
        , nvl(salary + (salary * commission_pct), salary)*12 * taxpercent AS 소득세액
   from employees E , tbl_taxindex T  -- SQL 1992 CODE
-- where nvl(E.salary + (E.salary * E.commission_pct), E.salary)*12 between T.lowerincome and T.highincome;  -- 조인조건절 
-- 또는
   where nvl(salary + (salary * commission_pct), salary)*12 between lowerincome and highincome;  -- 조인조건절 
   
    
   select employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , nvl(salary + (salary * commission_pct), salary)*12 AS 연봉
        , taxpercent AS 세율 
        , nvl(salary + (salary * commission_pct), salary)*12 * taxpercent AS 소득세액
   from employees E JOIN tbl_taxindex T  -- SQL 1999 CODE
   ON nvl(salary + (salary * commission_pct), salary)*12 between lowerincome and highincome; -- 조인조건절  
  
  
    
    
    
    
     ------------------ ===== **** SELF JOIN(자기조인) **** ===== ------------------ 
   /*
       자기자신의 테이블(뷰)을 자기자신의 테이블(뷰)과 JOIN 시키는 것을 말한다.
       이때 반드시 테이블(뷰)에 대한 alias(별칭)를 달리 주어서 실행해야 한다.
   */
   
   --- 아래처럼 나오도록 하세요... ---
   -------------------------------------------------------------------------------------------------------
    사원번호              사원명             이메일     급여      직속상관번호             직속상관명
  employee_id   first_name || last_name    email     salary   employee_id      first_name || last_name
  -------------------------------------------------------------------------------------------------------
     100             Steven King           SKING     24000     null                 null 
     102             Lex De Haan           LDEHAAN   17000     100                  Steven King
     103             Alexander   Hunold       AHUNOLD   9000      102                  Lex De Haan
     104             Bruce Ernst           BERNST    6000      103                  Alexander Hunold
  
  
  select *
  from employees
  order by employee_id asc;
  
  
  -- SQL 1992 CODE
  select *
  from employees E1 , employees E2
  where E1.manager_id = E2.employee_id;
  
  
  select *
  from employees E1 , employees E2
  where E1.manager_id = E2.employee_id(+);
  
  
  select E1.employee_id AS 사원번호
       , E1.first_name || ' ' || E1.last_name AS 사원명
       , E1.email AS 이메일
       , E1.salary AS 급여      
       , E2.employee_id AS 직속상관번호
       , E2.first_name || ' ' || E2.last_name 직속상관명
  from employees E1 , employees E2
  where E1.manager_id = E2.employee_id(+)
  order by E1.employee_id;
  
  
  -- SQL 1999 CODE
  select E1.employee_id AS 사원번호
       , E1.first_name || ' ' || E1.last_name AS 사원명
       , E1.email AS 이메일
       , E1.salary AS 급여      
       , E2.employee_id AS 직속상관번호
       , E2.first_name || ' ' || E2.last_name 직속상관명
  from employees E1 LEFT JOIN employees E2
  ON E1.manager_id = E2.employee_id
  order by E1.employee_id;
  
  
  select * 
  from tbl_authorbook;
   
   --- SELF JOIN(자기조인)을 사용하여 
   --- tbl_authorbook 테이블에서 공저(도서명은 동일하지만 작가명이 다른 도서)로 지어진 도서정보를 나타내세요... ---
   
   /*
       ---------------------------------
         도서명         작가명    로얄티
       ---------------------------------  
         로빈슨크루소    한석규    800
         로빈슨크루소    이순신    500
         그리스로마신화  유관순   1200
         그리스로마신화  이혜리   1300
         그리스로마신화  서강준   1700
       ---------------------------------  
   */
  
   --- SQL 1992 CODE ---
   select *
   from tbl_authorbook A1 , tbl_authorbook A2
   where A1.bookname = A2.bookname AND A1.authorname != A2.authorname;  -- 조인조건절
   
   -- select 되어진 행의 결과물에서 중복된 행이 여러번 안 나오고 1번만 나오도록 하려면
   -- select 바로 다음에 distinct 를 사용하면 된다.
   select A1.*
   from tbl_authorbook A1 , tbl_authorbook A2
   where A1.bookname = A2.bookname AND A1.authorname != A2.authorname;  -- 조인조건절
   
   
   select distinct A1.*
   from tbl_authorbook A1 , tbl_authorbook A2
   where A1.bookname = A2.bookname AND A1.authorname != A2.authorname; 
   
   
   /*   !!!! select 문에서 distinct 와 order by 절을 함께 사용할때는 조심해야 한다. !!!!
        select 문에 distict 가 있는 경우 order by 절에는 select 문에서 사용된 컬럼만 들어올 수 있다.
        또는 select 문에 distict 가 있는 경우 order by 절을 사용하지 않아야 한다.
   */
   select distinct A1.*
   from tbl_authorbook A1 , tbl_authorbook A2
   where A1.bookname = A2.bookname AND A1.authorname != A2.authorname
   order by 1;
   
   
   select distinct A1.*
   from tbl_authorbook A1 , tbl_authorbook A2
   where A1.bookname = A2.bookname AND A1.authorname != A2.authorname
   order by 1, 3 desc;
   
   
   select A1.bookname, A1.authorname
   from tbl_authorbook A1 , tbl_authorbook A2
   where A1.bookname = A2.bookname AND A1.authorname != A2.authorname
   order by A1.bookname, A1.loyalty desc;  -- 잘 나온다.
   
   
   select distinct A1.bookname, A1.authorname 
   from tbl_authorbook A1 , tbl_authorbook A2
   where A1.bookname = A2.bookname AND A1.authorname != A2.authorname
   order by A1.bookname, A1.loyalty desc;
   -- ORA-01791: not a SELECTed expression
   -- select 다음에 distinct 를 사용했는데 order by 절에서는 select 다음에 보여지는
   -- 컬럼이 아닌 컬럼으로 사용했기에 오류가 발생한다. 
   
   select distinct A1.bookname, A1.authorname
   from tbl_authorbook A1 , tbl_authorbook A2
   where A1.bookname = A2.bookname AND A1.authorname != A2.authorname
   order by A1.bookname, A1.authorname desc;
   
   
   --- SQL 1999 CODE ---
   select distinct A1.bookname, A1.authorname
   from tbl_authorbook A1 JOIN tbl_authorbook A2
   ON A1.bookname = A2.bookname AND A1.authorname != A2.authorname
   order by A1.bookname, A1.authorname desc;
    
   ----- ==== **** Multi Table JOIN(다중 테이블 조인) **** ==== -----
   
   --> 3개 이상의 테이블(뷰)을 가지고 조인 시켜주는 것이다.
   
   /*
       
      --------------------------------------------------------------------------------------------------------------------------
         대륙명        국가명                       부서주소                    부서번호   부서명      사원번호  사원명       기본급여
      --------------------------------------------------------------------------------------------------------------------------   
         Americas     United States of America     Seattle 2004 Charade Rd      90      Executive   100    Steven King   24000
   
   
         대륙명   ==>  regions.region_name                                    regions.region_id 
         국가명   ==>  countries.country_name                                 countries.region_id       countries.country_id
         부서주소  ==> locations.city || ' ' || locations.street_address      locations.country_id      locations.location_id
         부서명   ==> departments.department_name                             departments.location_id   departments.department_id 
         사원명   ==> employees.first_name || ' ' || employees.last_name      employees.department_id
   */
   
   select * from tab;
   
   select *
   from REGIONS; 
   
   select * 
   from COUNTRIES;
   
   select * 
   from LOCATIONS;
   
   select *
   from DEPARTMENTS;
   
   select *
   from EMPLOYEES;
   
   
   select *
   from REGIONS R
   JOIN COUNTRIES C
   ON R.region_id = C.region_id
   JOIN LOCATIONS L
   ON C.country_id = L.country_id 
   JOIN DEPARTMENTS D
   ON L.location_id = D.location_id
   JOIN EMPLOYEES E
   ON D.department_id = E.department_id
   order by 1;
   
   
   select *
   from REGIONS R
   JOIN COUNTRIES C
   ON R.region_id = C.region_id
   JOIN LOCATIONS L
   ON C.country_id = L.country_id 
   JOIN DEPARTMENTS D
   ON L.location_id = D.location_id 
   RIGHT JOIN EMPLOYEES E
   ON D.department_id = E.department_id
   order by 1;
   
  
   select R.region_name AS 대륙명
        , C.country_name AS 국가명
        , L.city || ' ' || L.street_address AS 부서주소
        , E.department_id AS 부서번호
        , D.department_name AS 부서명
        , E.employee_id AS 사원번호
        , E.first_name || ' ' || E.last_name AS 사원명
        , E.salary AS 기본급여
   from REGIONS R
   JOIN COUNTRIES C
   ON R.region_id = C.region_id
   JOIN LOCATIONS L
   ON C.country_id = L.country_id 
   JOIN DEPARTMENTS D
   ON L.location_id = D.location_id 
   RIGHT JOIN EMPLOYEES E
   ON D.department_id = E.department_id
   order by 1;
   
   
   /*
       부서번호가 30번, 90 번에 근무하는 사원들만 아래와 같이 나오도록 하세요..   
      --------------------------------------------------------------------------------------------------------------------------
         대륙명        국가명                       부서주소                    부서번호   부서명      사원번호  사원명       기본급여
      --------------------------------------------------------------------------------------------------------------------------   
         Americas     United States of America     Seattle 2004 Charade Rd      90      Executive   100    Steven King   24000
   */
   
  
   select R.region_name AS 대륙명
        , C.country_name AS 국가명
        , L.city || ' ' || L.street_address AS 부서주소
        , E.department_id AS 부서번호
        , D.department_name AS 부서명
        , E.employee_id AS 사원번호
        , E.first_name || ' ' || E.last_name AS 사원명
        , E.salary AS 기본급여
   from REGIONS R
   JOIN COUNTRIES C
   ON R.region_id = C.region_id
   JOIN LOCATIONS L
   ON C.country_id = L.country_id 
   JOIN DEPARTMENTS D
   ON L.location_id = D.location_id 
   JOIN EMPLOYEES E
   ON D.department_id = E.department_id
   WHERE E.department_id IN(30,90) 
   order by 1;
   
   -- 또는
   select R.region_name AS 대륙명
        , C.country_name AS 국가명
        , L.city || ' ' || L.street_address AS 부서주소
        , E.department_id AS 부서번호
        , D.department_name AS 부서명
        , E.employee_id AS 사원번호
        , E.first_name || ' ' || E.last_name AS 사원명
        , E.salary AS 기본급여
   from REGIONS R
   JOIN COUNTRIES C
   ON R.region_id = C.region_id
   JOIN LOCATIONS L
   ON C.country_id = L.country_id 
   JOIN DEPARTMENTS D
   ON L.location_id = D.location_id 
   JOIN ( SELECT *
          FROM EMPLOYEES
          WHERE department_id IN(30,90) ) E
   ON D.department_id = E.department_id
   order by 1;
  
  
  
   --- [대분류 검색]
   --- *** 'Americas' 대륙에 근무하는 사원들만 
   --      국가명     부서주소     부서번호   부서명    사원번호  사원명   기본급여 를 나타내세요.
   WITH
   V AS
   (   
       SELECT R.region_id, region_name,
              C.country_id, country_name,
              L.location_id, street_address, postal_code, city, state_province,
              D.department_id, department_name, D.manager_id, 
              employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, E.manager_id AS SASUNO, jubun 
       from REGIONS R
       JOIN COUNTRIES C
       ON R.region_id = C.region_id
       JOIN LOCATIONS L
       ON C.country_id = L.country_id 
       JOIN DEPARTMENTS D
       ON L.location_id = D.location_id
       JOIN EMPLOYEES E
       ON D.department_id = E.department_id
   )
   select country_name AS 국가명
        , city || ' ' || street_address AS 부서주소
        , department_id AS 부서번호
        , department_name AS 부서명
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
   from V
   where region_name = 'Americas';
    
    
    
   --- [중분류 검색] 
   --- *** 'Seattle' 도시에 근무하는 사원들만 
   --      부서주소     부서번호   부서명    사원번호  사원명   기본급여 를 나타내세요.
   WITH
   V AS
   (   
       SELECT R.region_id, region_name,
              C.country_id, country_name,
              L.location_id, street_address, postal_code, city, state_province,
              D.department_id, department_name, D.manager_id, 
              employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, E.manager_id AS SASUNO, jubun 
       from REGIONS R
       JOIN COUNTRIES C
       ON R.region_id = C.region_id
       JOIN LOCATIONS L
       ON C.country_id = L.country_id 
       JOIN DEPARTMENTS D
       ON L.location_id = D.location_id
       JOIN EMPLOYEES E
       ON D.department_id = E.department_id
   )
   select city || ' ' || street_address AS 부서주소
        , department_id AS 부서번호
        , department_name AS 부서명
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
   from V
   where region_name = 'Americas'
         AND city = 'Seattle';
   
   
   
   --- [소분류 검색]
   --- *** 'Finance' 부서명에 근무하는 사원들만 
   --      부서번호   부서명    사원번호  사원명   기본급여 를 나타내세요.
   
   WITH
   V AS
   (   
       SELECT R.region_id, region_name,
              C.country_id, country_name,
              L.location_id, street_address, postal_code, city, state_province,
              D.department_id, department_name, D.manager_id, 
              employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, E.manager_id AS SASUNO, jubun 
       from REGIONS R
       JOIN COUNTRIES C
       ON R.region_id = C.region_id
       JOIN LOCATIONS L
       ON C.country_id = L.country_id 
       JOIN DEPARTMENTS D
       ON L.location_id = D.location_id
       JOIN EMPLOYEES E
       ON D.department_id = E.department_id
   )
   select department_id AS 부서번호
        , department_name AS 부서명
        , employee_id AS 사원번호
        , first_name || ' ' || last_name AS 사원명
        , salary AS 기본급여
   from V
   where region_name = 'Americas'
         AND city = 'Seattle'
         AND department_name = 'Finance';
         
         
         
   --- [퀴즈] 아래와 같이 나오도록 하세요..
   /*
       -------------------------------------------------------------------------------------------------
        부서번호           부서명                부서장성명                     사원번호   사원명   입사일 
       ------------------------------------------------------------------------------------------------- 
       D.department_id    D.department_name    E.first_name || E.last_name
       E.department_id 
       
                   D.manager_id                E.employee_id
                 (부서장의 사원번호)              (사원번호)
   */      
   WITH
   V1 AS
   (select D.department_id 
         , department_name 
         , D.manager_id 
         , employee_id
         , first_name || ' ' || last_name AS MANAGER_NAME 
   from departments D JOIN employees E
   ON D.manager_id = E.employee_id
   )
   ,
   V2 AS
   (
    select employee_id
         , first_name || ' ' || last_name AS ENAME
         , hire_date
         , department_id
    from employees
   )
   select V1.department_id AS 부서번호
        , V1.department_name AS 부서명
        , V1.MANAGER_NAME AS 부서장성명
        , V2.employee_id AS 사원번호
        , V2.ENAME AS 사원명
        , to_char(V2.hire_date, 'yyyy-mm-dd') AS 입사일 
   from V1 JOIN V2 
   ON V1.department_id = V2.department_id
   order by 1;
   
   
   WITH
   V1 AS
   (select D.department_id 
         , department_name 
         , D.manager_id 
         , employee_id
         , first_name || ' ' || last_name AS MANAGER_NAME 
   from departments D JOIN employees E
   ON D.manager_id = E.employee_id
   )
   ,
   V2 AS
   (
    select employee_id
         , first_name || ' ' || last_name AS ENAME
         , hire_date
         , department_id
    from employees
   )
   select V1.department_id AS 부서번호
        , V1.department_name AS 부서명
        , V1.MANAGER_NAME AS 부서장성명
        , V2.employee_id AS 사원번호
        , V2.ENAME AS 사원명
        , to_char(V2.hire_date, 'yyyy-mm-dd') AS 입사일 
   from V1 RIGHT JOIN V2 
   ON V1.department_id = V2.department_id
   order by 1; 
    
    
    
    
    ------ ====== **** SET Operator(SET 연산자, 집합연산자) **** ======= ------
    /*
        -- 종류 --
        1. UNION 
        2. UNION ALL
        3. INTERSECT
        4. MINUS
        
        -- 면접시 JOIN 과 UNION 의 차이점에 대해서 말해 보세요~~~ !! --
        
    ==>  UNION 은 테이블(뷰)과 테이블(뷰)을 합쳐서 보여주는 것으로써,
         이것은 행(ROW)과 행(ROW)을 합친 결과를 보여주는 것이다.

    A = { a, x, b, e, g }
          -     -
    B = { c, d, a, b, y, k, m}    
                -  -    
    A ∪ B = {a, b, c, d, e, g, k, m, x, y}  ==> UNION               
                                             {a, b, c, d, e, g, k, m, x, y}

                                             ==> UNION ALL 
                                             {a, x, b, e, g, c, d, a, b, y, k, m} 

    A ∩ B = {a,b}  ==> INTERSECT
                       {a,b}

    A - B = {x,e,g} ==> MINUS 
                        {x,e,g}

    B - A = {c,d,y,k,m} ==> MINUS 
                           {c,d,y,k,m}
 */
    
    select *
    from tbl_panmae;
    
    -- tbl_panmae 테이블에서 2달전에 해당하는 월(현재가 2022년 1월 이므로 2021년 11월)에 판매한 데이터를
    -- tbl_panmae_202111 이라는 테이블로 생성하세요.
    create table tbl_panmae_202111
    as
    select *
    from tbl_panmae
    where to_char(panmaedate, 'yyyy-mm') = to_char(add_months(sysdate, -2), 'yyyy-mm')
    
    select *
    from tbl_panmae_202111
    -- Table TBL_PANMAE_202111이(가) 생성되었습니다.

    
    
    
    -- tbl_panmae 테이블에서 1달전에 해당하는 월(현재가 2022년 1월 이므로 2021년 11월)에 판매한 데이터를
    -- tbl_panmae_202112 이라는 테이블로 생성하세요.
    create table tbl_panmae_202112
    as
    select *
    from tbl_panmae
    where to_char(panmaedate, 'yyyy-mm') = to_char(add_months(sysdate, -1), 'yyyy-mm')
    
    select *
    from tbl_panmae_202112
    -- Table TBL_PANMAE_202111이(가) 생성되었습니다.

    
    -- tbl_panmae 테이블에서 이번달에 해당하는 월(현재가 2022년 1월)에 판매되어진 정보만 남겨두고 나머지는 모두 삭제하세요.
    delete from tbl_panmae
    where to_char(panmaedate, 'yyyy-mm') < to_char(sysdate, 'yyyy-mm');
    -- 9개 행 이(가) 삭제되었습니다.
    
    select *
    from tbl_panmae;
    
    commit;
    
    ---- *** 최근 3개월간 판매되어진 정보를 가지고 제품별 판매량의 합계를 추출하세요 *** ----
    select *
    from tbl_panmae_202111 -- 2달전
    
    select *
    from tbl_panmae_202112 -- 1달전
    
    select *
    from tbl_panmae; -- 이번달
    
    
    
    select *
    from tbl_panmae_202111 -- 2달전
    UNION
    select *
    from tbl_panmae_202112 -- 1달전
    UNION
    select *
    from tbl_panmae; -- 이번달
    -- UNION 을 하면 항상 첫번째 컬럼(지금은 panmaedate)을 기준으로 오름차순 정렬되어 나온다.
    -- 그래서 2021년 11월 데이터부터 먼저 나온다.
    
    
    select *
    from tbl_panmae -- 이번달
    UNION ALL
    select *
    from tbl_panmae_202111 -- 2달전
    UNION ALL
    select *
    from tbl_panmae_202112 -- 1달전
    -- UNION ALL 을 하면 항상 정렬 없이 그냥 순서대로 행을 붙여서 보여줄 뿐이다.
    -- 그래서 맨처음이 tbl_panmae 테이블이고, 그 다음이 tbl_panmae_202111 테이블이고, 마지막이 tbl_panmae_202112 테이블이다.
    
    select jepumname as 제품명
         , sum(panmaesu) as 판매합계
    from
    (
    select *
    from tbl_panmae_202111 -- 2달전
    UNION
    select *
    from tbl_panmae_202112 -- 1달전
    UNION
    select *
    from tbl_panmae
    ) V
    group by jepumname
    order by 2 desc;
    
    -- 또는
    WITH V AS
    (
    select *
    from tbl_panmae_202111 -- 2달전
    UNION
    select *
    from tbl_panmae_202112 -- 1달전
    UNION
    select *
    from tbl_panmae
    )
    select jepumname as 제품명
         , sum(panmaesu) as 판매합계
    from V
    group by jepumname
    order by 2 desc;
    
    
    ---- *** [퀴즈] 최근 3개월간 판매되어진 정보를 가지고 
    --             아래와 같이 제품명, 판매년월, 판매량의합계, 백분율(%) 을 추출하세요 *** ----  
     
     ------------------------------------------------
      제품명     판매년월     판매량의합계    백분율(%)
     ------------------------------------------------
      감자깡     2021-11       20            8.2
      감자깡     2021-12       15            6.2
      감자깡     2022-01       15            6.2
      감자깡                   50           20.6
      새우깡     2021-11       38           15.6
      새우깡     2021-12        8            3.3
      새우깡     2022-01       30           12.3
      새우깡                   76           31.3
      .....     .......       ...          ....
      전체                    243          100.0
     ------------------------------------------------  
        
        
    -- 틀린것
    select sum(V.panmaesu)
    from
    (
    select panmaesu
    from tbl_panmae_202111 -- 2달전
    UNION
    select panmaesu
    from tbl_panmae_202112 -- 1달전
    UNION
    select panmaesu
    from tbl_panmae
    ) V
    
    -- 올바른것
    select sum(V.panmaesu)
    from
    (
    select panmaesu
    from tbl_panmae_202111 -- 2달전
    UNION ALL
    select panmaesu
    from tbl_panmae_202112 -- 1달전
    UNION ALL
    select panmaesu
    from tbl_panmae
    ) V
    
    
    select decode( grouping(jepumname), 0, jepumname, '전체') AS 제품명
    -- 또는  
    --  ,  NVL(jepumname, '전체') AS 제품명
        , decode( grouping(to_char(panmaedate, 'yyyy-mm')), 0, to_char(panmaedate, 'yyyy-mm'), ' ' ) AS 판매년월
    -- 또는  
    --  , NVL( to_char(panmaedate, 'yyyy-mm'), ' ')  AS 판매년월
        , sum(panmaesu) AS 판매량의합계
        , round( sum(panmaesu)/(
                                select sum(V_2.panmaesu) -- 243 
                                from       
                                (      
                                   select panmaesu
                                   from tbl_panmae_202111 
                                   UNION ALL 
                                   select panmaesu
                                   from tbl_panmae_202112 
                                   UNION ALL
                                   select panmaesu
                                   from tbl_panmae
                                ) V_2
                              )*100 , 1) AS "백분율(%)"
  from       
  (      
   select *
   from tbl_panmae_202111 
   UNION
   select *
   from tbl_panmae_202112 
   UNION
   select *
   from tbl_panmae
  ) V 
  group by grouping sets( (jepumname, to_char(panmaedate, 'yyyy-mm')), (jepumname), () );  
  -- 또는
  -- group by rollup(jepumname, to_char(panmaedate, 'yyyy-mm'));  
    
    
    
    
    
    ----- ===== **** INTERSECT(교집합) **** ===== -----
    insert into tbl_panmae_202111 (panmaedate, jepumname, panmaesu)
    values( to_date('2021-09-01', 'yyyy-mm-dd'), '쵸코파이', 10);
    
    insert into tbl_panmae_202112 (panmaedate, jepumname, panmaesu)
    values( to_date('2021-09-01', 'yyyy-mm-dd'), '쵸코파이', 10);
    
    insert into tbl_panmae (panmaedate, jepumname, panmaesu)
    values( to_date('2021-09-01', 'yyyy-mm-dd'), '쵸코파이', 10);
    
    ----------------------------------------------------------------
    
    insert into tbl_panmae_202111 (panmaedate, jepumname, panmaesu)
    values( to_date('2021-09-01 10:00:00', 'yyyy-mm-dd hh24:mi:ss'), '쵸코파이', 2);
    
    insert into tbl_panmae_202112 (panmaedate, jepumname, panmaesu)
    values( to_date('2021-09-01 14:00:00', 'yyyy-mm-dd hh24:mi:ss'), '쵸코파이', 2);
    
    insert into tbl_panmae (panmaedate, jepumname, panmaesu)
    values( to_date('2021-09-01 16:00:00', 'yyyy-mm-dd hh24:mi:ss'), '쵸코파이', 2);
    
    commit
    
    select *
    from tbl_panmae_202111
    INTERSECT
    select *
    from tbl_panmae_202112 
    INTERSECT
    select *
    from tbl_panmae
    -- 21/09/01	쵸코파이	10
    
    delete from tbl_panmae_202111
    where to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') || jepumname || panmaesu in ( select to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') || jepumname || panmaesu
                                                                                     from tbl_panmae_202111 
                                                                                     INTERSECT
                                                                                     select to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') || jepumname || panmaesu
                                                                                     from tbl_panmae_202112 
                                                                                     INTERSECT
                                                                                     select to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') || jepumname || panmaesu
                                                                                     from tbl_panmae )
    -- 1개 행 이(가) 삭제되었습니다.

    delete from tbl_panmae_202112
    where to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') || jepumname || panmaesu in ( select to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') || jepumname || panmaesu
                                                                                     from tbl_panmae_202112 
                                                                                     INTERSECT
                                                                                     select to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') || jepumname || panmaesu
                                                                                     from tbl_panmae )
   -- 1개 행 이(가) 삭제되었습니다.
                                                                                     
    
    commit
    
    
    ----- ===== **** MINUS(차집합) **** ===== -----
    select * from tab;
    
    select *
    from TBL_EMPLOYEES_BACKUP_220111;
    
    select * 
    from employees
    where employee_id IN(173, 185, 195)
    
    select *
    from TBL_EMPLOYEES_BACKUP_220111
    where employee_id IN(173, 185, 195)

    delete from employees
    where employee_id IN(173, 185, 195)
    -- 3개 행 이(가) 삭제되었습니다.

    commit
    -- 커밋 완료

    -- *** 개발자가 실수로 employees 테이블에 있던 사원들을 삭제(delete)했다. 그런데 누구를 삭제했는지 모른다.!!!!
    --     백업받은 TBL_EMPLOYEES_BACKUP 테이블을 이용하여 삭제된 사원들을 다시 복구하도록 하겠다. *** ---

    select *
    from TBL_EMPLOYEES_BACKUP_220111
    MINUS
    select *
    from employees
    
    select * 
    from employees
    where employee_id IN(173, 185, 195)
    -- 없음.
    
    insert into employees
    select *
    from TBL_EMPLOYEES_BACKUP_220111
    MINUS
    select *
    from employees
    -- 3개 행 이(가) 삽입되었습니다.

    commit;
    
    select * 
    from employees
    where employee_id IN(173, 185, 195)
    -- 있음. 즉, 복구완료함.
    
    
    
    ----------- ====== **** Pseudo(의사, 유사, 모조) Column **** ====== -----------
    ------ Pseudo(의사) Column 은 rowid 와 rownum 이 있다.
      
    /*
      1. rowid
         rowid 는 오라클이 내부적으로 사용하기 위해 만든 id 값으로써 행에 대한 id값 인데
         오라클 전체내에서 고유한 값을 가진다.
    */
    
    
    
    create table tbl_heowon
    (userid     varchar2(20),
     name       varchar2(20),
     address    varchar2(100)
    );
    
    insert into tbl_heowon(userid, name, address) values('leess','이순신','서울');
    insert into tbl_heowon(userid, name, address) values('eomjh','엄정화','인천');
    insert into tbl_heowon(userid, name, address) values('kangkc','강감찬','수원');
    
    insert into tbl_heowon(userid, name, address) values('leess','이순신','서울');
    insert into tbl_heowon(userid, name, address) values('eomjh','엄정화','인천');
    insert into tbl_heowon(userid, name, address) values('kangkc','강감찬','수원');
        
    insert into tbl_heowon(userid, name, address) values('leess','이순신','서울');
    insert into tbl_heowon(userid, name, address) values('eomjh','엄정화','인천');
    insert into tbl_heowon(userid, name, address) values('kangkc','강감찬','수원');
        
    commit;
    
    select *
    from tbl_heowon;
    
    select userid, name, address, rowid
    from tbl_heowon;
    
    select userid, name, address, rowid
    from tbl_heowon
    where rowid in ('AAAE8pAAEAAAAG2AAA'
                  , 'AAAE8pAAEAAAAG2AAB'
                  , 'AAAE8pAAEAAAAG2AAC');
    
    delete from tbl_heowon
    where rowid > 'AAAE8pAAEAAAAG2AAC'
    
    commit
    
    
    select *
    from tbl_heowon;
    
    
    /*
       2. rownum (!!!! 게시판 등 웹에서 아주 많이 사용됩니다. !!!!)
    */
    select boardno as 글번호
         , subject as 글제목
         , userid as 글쓴이
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as 작성일자
    from tbl_board
    order by boardno desc;
    
    --------------------------------------------------------------------------------------
    번호    글번호       글제목                            글쓴이         작성일자   
    --------------------------------------------------------------------------------------
    1       5	    오늘도 좋은 하루되세요	                hongkd	    2022-01-07 10:18:00
    2       4	    기쁘고 감사함이 넘치는 좋은 하루되세요	    leess	    2022-01-07 10:18:00
    3       3	    건강하세요	                        youks	    2022-01-07 10:18:00
    4       2	    반갑습니다	                        eomjh	    2022-01-07 10:17:54
    5       1	    안녕하세요	                        leess	    2022-01-07 10:17:51
    --------------------------------------------------------------------------------------
                    1  2  3  ==> 페이지바
    
    select  rownum -- rownum(행번호)은 기본적으로 insert 되어진 순서대로 나온다.
         , boardno as 글번호
         , subject as 글제목
         , userid as 글쓴이
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as 작성일자
    from tbl_board
    
    
    select rownum
         , boardno 
         , subject 
         , userid 
         , REGISTERDAY
    from
    (
    select boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    order by boardno desc
    ) V
    
    --또는
    WITH V AS
    (
    select boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    order by boardno desc
    )
    select rownum
         , boardno , subject , userid , REGISTERDAY
    from V
    
    
    -- 또는 rownum 을 사용하지 않고 row_number() 함수로 사용해서 나타낼 수 있다.
    
    
    select row_number() over(order by boardno desc)
         , boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    order by boardno desc
    
    
    
    /*
        한 페이지당 2개씩 보여주고자 한다.
        
        1 페이지 ==>  rownum : 1 ~ 2   boardno : 5 ~ 4      
        2 페이지 ==>  rownum : 3 ~ 4   boardno : 3 ~ 2     
        3 페이지 ==>  rownum : 5 ~ 6   boardno : 1          
    */
    -- [틀린 SQL문]
    --  1 페이지 ==>  rownum : 1 ~ 2  /  boardno : 5 ~ 4
    
    select rownum
         , boardno 
         , subject 
         , userid 
         , REGISTERDAY
    from
    (
    select boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    order by boardno desc
    ) V
    where rownum between 1 and 2;
    
    
    --  2 페이지 ==>  rownum : 3 ~ 4  /  boardno : 3 ~ 2
    
    select rownum
         , boardno 
         , subject 
         , userid 
         , REGISTERDAY
    from
    (
    select boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    order by boardno desc
    ) V
    where rownum between 3 and 4;
    -- rownum 은 where 절에 바로 쓸 수가 없다!!!!!!!
    -- 그래서 rownum 을 가지는 컬럼의 별칭을 만든 후에 inline view 를 사용해야만 된다.!!!!!!!
    
    
    
    -- [올바른 SQL문]
    --  1 페이지 ==>  rownum : 1 ~ 2  /  boardno : 5 ~ 4
  select boardno, subject, userid, registerday
  from
  (
    select rownum as RNO, boardno, subject, userid, registerday
    from
   (
    select boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    order by boardno desc
    ) V
  ) T
  where T.RNO between 1 and 2; -- 1페이지
    
    --  2 페이지 ==>  rownum : 3 ~ 4  /  boardno : 3 ~ 2
    
  select boardno, subject, userid, registerday
  from
  (
    select rownum as RNO, boardno, subject, userid, registerday
    from
   (
    select boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    order by boardno desc
    ) V
  ) T
  where T.RNO between 3 and 4; -- 2페이지
  
    
    -- 3 페이지 ==>  rownum : 5 ~ 6  /  boardno : 1
    
  select boardno, subject, userid, registerday
  from
  (
    select rownum as RNO, boardno, subject, userid, registerday
    from
   (
    select boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    order by boardno desc
    ) V
  ) T
  where T.RNO between 5 and 6; -- 3페이지
      
    
  -- 또는
  -- rownum 을 사용하지 않고 row_number() 함수를 사용하여 페이징 처리를 해봅니다.
  
  -- [틀린 SQL문] --
    select row_number() over(order by boardno desc)
         , boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    where row_number() over(order by boardno desc) between 1 and 2;
  
    -- 오류!!! row_number() over(order by boardno desc) 은 where 절에 바로 쓸 수가 없다.!!!!
    -- 그러므로 이것 또한 inline view 를 사용해야 한다.
    
    -- [올바른 SQL문] --
    
    -- 1 페이지 ==>  rownum_number() : 1 ~ 2  /  boardno : 5 ~ 4
    /*
    >>> rownum : 1 ~ 1 를 구하는 공식 <<<
    currentShowPageNo * sizePerPage) - (sizePerPage - 1); -- 1
                    1 * 2 - ( 2 - 1 ) ==> 1
                    
        sizePerPage 가 10인 서울교대 게시판
                    1 * 10 - ( 10 - 1 ) ==> 1
    
    (currentShowPageNo * sizePerPage);
                    1 * 2 ==> 2
            sizePerPage 가 10인 서울교대 게시판
                    1 * 10 ==> 10
  */
    select 
    from
    (
    select row_number() over(order by boardno desc)
         , boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    ) V
    where RNO between 1 and 2;
    
    
    -- 2 페이지 ==>  rownum_number() : 3 ~ 4  /  boardno : 3 ~ 2
    /*
    >>> rownum : 1 ~ 2 를 구하는 공식 <<<
    currentShowPageNo * sizePerPage) - (sizePerPage - 1); -- 3
                    2 * 2 - ( 2 - 1 ) ==> 3
                    
        sizePerPage 가 10인 서울교대 게시판
                    2 * 10 - ( 10 - 1 ) ==> 11
    
    (currentShowPageNo * sizePerPage);
                    2 * 2 ==> 4
            sizePerPage 가 10인 서울교대 게시판
                    2 * 10 ==> 20
  */
    select 
    from
    (
    select row_number() over(order by boardno desc)
         , boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    ) V
    where RNO between 3 and 4;
    
    
    -- 3 페이지 ==>  rownum_number() : 5 ~ 6  /  boardno : 1
    /*
    >>> rownum : 1 ~ 2 를 구하는 공식 <<<
    currentShowPageNo * sizePerPage) - (sizePerPage - 1); -- 3
                    3 * 2 - ( 2 - 1 ) ==> 5
                    
        sizePerPage 가 10인 서울교대 게시판
                    3 * 10 - ( 10 - 1 ) ==> 21
    
    (currentShowPageNo * sizePerPage);
                    3 * 2 ==> 6
            sizePerPage 가 10인 서울교대 게시판
                    3 * 10 ==> 30
  */
    select 
    from
    (
    select row_number() over(order by boardno desc)
         , boardno 
         , subject 
         , userid 
         , to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') as REGISTERDAY
    from tbl_board
    ) V
    where RNO between 5 and 6; -- 3 페이지
    
    
    
    
    -------- **** 데이터 조작어(DML == Data Manuplation Language) **** ---------
    --- DML 문은 기본적으로 수동 commit 이다.
    --- 즉, DML 문을 수행한 다음에는 바로 디스크(파일)에 적용되지 않고 commit 해야만 적용된다.
    --- 그래서 DML 문을 수행한 다음에 디스크(파일)에 적용치 않고자 한다라면 rollback 하면 된다.
    
    1. insert  --> 데이터 입력
    2. update  --> 데이터 수정
    3. delete  --> 데이터 삭제
    4. merge   --> 데이터 병합 
    
    insert 는 문법이
    insert into 테이블명(컬럼명1,컬럼명2,...) values(값1,값2,...); 
    이다.
  
  
    ※ Unconditional insert all  -- ==>조건이 없는 insert 
    [문법] insert all 
           into 테이블명1(컬럼명1, 컬럼명2, ....)
           values(값1, 값2, .....)
           into 테이블명2(컬럼명3, 컬럼명4, ....)
           values(값3, 값4, .....)
           SUB Query문;
  
    create table tbl_emp1
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(6)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );       
   -- Table TBL_EMP1이(가) 생성되었습니다.
   
   
   create table tbl_emp1_backup
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(6)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );  
   -- Table TBL_EMP1_BACKUP이(가) 생성되었습니다. 
   
   select * from tbl_emp1
   select * from tbl_emp1_backup
   
  insert all 
  into tbl_emp1(empno, ename, monthsal, gender, manager_id, department_id, department_name)
  values(employee_id, ename, month_sal, gender||'자', manager_id, department_id, department_name)
  into tbl_emp1_backup(empno, ename, monthsal, gender, manager_id, department_id, department_name)
  values(employee_id, ename, month_sal, gender||'자', manager_id, department_id, department_name)
  select employee_id
        , first_name || ' ' || last_name AS ename 
        , nvl(salary + (salary * commission_pct), salary) AS month_sal
        , case when substr(jubun,7,1) in('1','3') then '남' else '여' end AS gender
        , E.manager_id
        , E.department_id
        , department_name
   from employees E LEFT JOIN departments D 
   on E.department_id = D.department_id
   order by E.department_id asc, employee_id asc;
  
  commit
   
  ※ Conditional insert all -- ==> 조건이 있는 insert 
   조건(where절)에 일치하는 행들만 특정 테이블로 찾아가서 insert 하도록 하는 것이다.
      
   create table tbl_emp_dept30
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(4)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );

   create table tbl_emp_dept50
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(4)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );

   create table tbl_emp_dept80
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(4)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   ); 
  
  
   insert all 
   when department_id = 30 then
   into tbl_emp_dept30 (empno, ename, monthsal, gender, manager_id, department_id, department_name)
   values(employee_id, ename, month_sal, gender, manager_id, department_id, department_name)
   when department_id = 50 then
   into tbl_emp_dept50 (empno, ename, monthsal, gender, manager_id, department_id, department_name)
   values(employee_id, ename, month_sal, gender, manager_id, department_id, department_name)
   when department_id = 80 then
   into tbl_emp_dept80 (empno, ename, monthsal, gender, manager_id, department_id, department_name)
   values(employee_id, ename, month_sal, gender, manager_id, department_id, department_name)
   select employee_id
        , first_name || ' ' || last_name AS ename 
        , nvl(salary + (salary * commission_pct), salary) AS month_sal
        , case when substr(jubun,7,1) in('1','3') then '남' else '여' end AS gender
        , E.manager_id
        , E.department_id
        , department_name
   from employees E LEFT JOIN departments D 
   on E.department_id = D.department_id
   where E.department_id IN(30, 50, 80)
   order by E.department_id asc, employee_id asc;
  -- 85개 행 이(가) 삽입되었습니다.

  commit
  
  select *
  from tbl_emp_dept30;
  
  select *
  from tbl_emp_dept50;
  
  select *
  from tbl_emp_dept80;
  
  
  
  
  
  
   -------- ====== ****   merge(병합)   **** ====== --------
   -- 어떤 2개 이상의 테이블에 존재하는 데이터를 다른 테이블 한곳으로 모으는것(병합)이다.
  
   1. 탐색기에서 C:\oraclexe\app\oracle\product\11.2.0\server\network\ADMIN 에 간다.
   
   2. tnsnames.ora 파일을 메모장으로 연다.
   
   3. TEACHER =
      (DESCRIPTION =
        (ADDRESS = (PROTOCOL = TCP)(HOST = 211.238.142.72)(PORT = 1521))
        (CONNECT_DATA =
          (SERVER = DEDICATED)
          (SERVICE_NAME = XE)
        )
      )
     을 추가한다.
     HOST = 211.238.142.72 이 연결하고자 하는 원격지 오라클서버의 IP 주소이다.
     그런데 전제조건은 원격지 오라클서버(211.238.142.72)의 방화벽에서 포트번호 1521 을 허용으로 만들어주어야 한다.
     
     그리고 TEACHER 를 'Net Service Name 네트서비스네임(넷서비스명)' 이라고 부른다.
     
     
   4. 명령프롬프트를 열어서 원격지 오라클서버(211.238.142.72)에 연결이 가능한지 테스트를 한다. 
      C:\Users\sist>tnsping TEACHER 5

        Used TNSNAMES adapter to resolve the alias
        Attempting to contact (DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 211.238.142.72)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = XE)))
        OK (0 msec)
        OK (40 msec)
        OK (10 msec)
        OK (30 msec)
        OK (20 msec)
   
   5.  데이터베이스 링크(database link) 만들기
    
    create database link teacherServer
    connect to hr identified by cclass   -- 이때 hr 과 암호 cclass 는 연결하고자 하는 원격지 오라클서버(211.238.142.72)의 계정명과 암호 이다. 
    using 'TEACHER';                     -- TEACHER 는 Net Service Name 네트서비스네임(넷서비스명) 이다.
    -- Database link TEACHERSERVER이(가) 생성되었습니다.
   
   update employees set first_name = '병진', last_name = '최'
   where employee_id = 100;
   -- 1 행 이(가) 업데이트되었습니다.

   commit
   
   
   select *
   from employees  -- 로컬서버
   order by employee_id;
   
   
   select *
   from employees@XE  -- 로컬서버 -- 원칙상으로 @로컬이름 이 필요함
   order by employee_id;

   
   select *
   from employees@teacherServer  -- 원격지 오라클서버(211.238.142.72)
   order by employee_id;
   
    
    
    --- *** 생성되어진 데이터베이스 링크를 조회해봅니다. *** ---   
    
    select *
    from user_db_links;
    /*
        ----------------------------------------------------------------------------------
        DB_LINK            USERNAME         PASSWORD        HOST            CREATED
        ----------------------------------------------------------------------------------
        TEACHERSERVER       HR                NULL          TEACHER         22/01/14
                                                        --  TEACHER 는 Netservice name
    */
    
    
    --- *** 생성되어진 데이터베이스 링크를 삭제해봅니다. *** ---   
    drop database link TEACHERSERVER;
    -- Database link TEACHERSERVER이(가) 삭제되었습니다.


    create database link bonjumlink
    connect to hr identified by cclass   
    using 'TEACHER';                     
    -- Database link BONJUMLINK이(가) 생성되었습니다.
    
    
    -- 각 지점은 tbl_reservation_Cbyeongjin 이라는 테이블을 생성한다.
    create table tbl_reservation_Cbyeongjin
    (rsvno       varchar2(20)    -- 예약고유번호
    ,memberid    varchar2(20)    -- 회원ID
    ,ticketcnt   number          -- 티켓개수
    ,constraint PK_tbl_reservation_Cbyeongjin primary key(rsvno)
    );
    -- Table TBL_RESERVATION_SEOYOUNGHAK이(가) 생성되었습니다.
    
    
    insert into tbl_reservation_Cbyeongjin(rsvno, memberid, ticketcnt)
    values('Cbyeongjin001', '최병진', 2);
    
    commit;

    -- 아래는 본점DB서버(샘PC)에서만 하는 것이다.
    create table tbl_reservation_merge
    (rsvno       varchar2(20)    -- 예약고유번호
    ,memberid    varchar2(20)    -- 회원ID
    ,ticketcnt   number          -- 티켓개수
    ,constraint PK_tbl_reservation_merge primary key(rsvno)
    );
    -- Table TBL_RESERVATION_MERGE이(가) 생성되었습니다.
    
    select *
    from tbl_reservation_merge; -- 샘이 하는것
    
    select *
    from tbl_reservation_merge@bonjumlink; -- 여러분들이 하는 것
    
    select * 
    from tbl_reservation_Cbyeongjin;
    
    
    -- 아래는 여러분(지사)들이 하는 것입니다.
    merge into tbl_reservation_merge@bonjumlink R  -- Remote
    using tbl_reservation_Cbyeongjin L  -- Local
    on (L.rsvno = R.rsvno)
    when matched then
        update set R.memberid = L.memberid
                 , R.ticketcnt = L.ticketcnt
    when not matched then
        insert(rsvno, memberid, ticketcnt) values(L.rsvno, L.memberid, L.ticketcnt); -- insert into 하지 않는다
    -- 1 행 이(가) 병합되었습니다.

    commit
   
    select *
    from tbl_reservation_merge@bonjumlink;
    
    select * 
    from tbl_reservation_Cbyeongjin;
    
    update tbl_reservation_Cbyeongjin set memberid = 'Choi B.J', ticketcnt = 8
    where rsvno = 'Cbyeongjin001'
   
    merge into tbl_reservation_merge@bonjumlink R  -- Remote
    using tbl_reservation_Cbyeongjin L  -- Local
    on (L.rsvno = R.rsvno)
    when matched then
        update set R.memberid = L.memberid
                 , R.ticketcnt = L.ticketcnt
    when not matched then
        insert(rsvno, memberid, ticketcnt) values(L.rsvno, L.memberid, L.ticketcnt);
   
   
    insert into tbl_reservation_Cbyeongjin(rsvno, memberid, ticketcnt)
    values('Cbyeongjin002', '최병진two', 20);
    
    commit;
   
    ----- **** 데이터 질의어(DQL == Data Query Language) **** -----
    --> DQL 은 select 를 말한다.
    
    
    ----- **** 트랜잭션 제어어(TCL == Transaction Control Language) **** -----
    --> TCL 은 commit, rollback 을 말한다.
   
   -- *** Transaction(트랜잭션) 처리 *** --
   --> Transaction(트랜잭션)이라 함은 관련된 일련의 DML로 이루어진 한꾸러미(한세트)를 말한다.
   --> Transaction(트랜잭션)이라 함은 데이터베이스의 상태를 변환시키기 위하여 논리적 기능을 수행하는 하나의 작업단위를 말한다. 
   /*
      예>   네이버카페(다음카페)에서 활동
            글쓰기(insert)를 1번하면 내포인트 점수가 10점이 올라가고(update),
            댓글쓰기(insert)를 1번하면 내포인트 점수가 5점이 올라가도록 한다(update)
           
           위와같이 정의된 네이버카페(다음카페)에서 활동은 insert 와 update 가 한꾸러미(한세트)로 이루어져 있는 것이다.
           이와 같이 서로 다른 DML문이 1개의 작업을 이룰때 Transaction(트랜잭션) 처리라고 부른다.
           
           Transaction(트랜잭션) 처리에서 가장 중요한 것은 
           모든 DML문이 성공해야만 최종적으로 모두 commit 을 해주고,
           DML문중에 1개라도 실패하면 모두 rollback 을 해주어야 한다는 것이다. 
           
           예를 들면 네이버카페(다음카페)에서 글쓰기(insert)가 성공 했다라면
           그 이후에 내포인트 점수가 10점이 올라가는(update) 작업을 해주고, update 작업이 성공했다라면
           commit 을 해준다. 
           만약에 글쓰기(insert) 또는 10점이 올라가는(update) 작업이 실패했다라면
           rolllback 을 해준다.
           이러한 실습은 자바에서 하겠습니다.
   */
   
   ---- **** === ROLLBACK TO SAVEPOINT === **** ----
             --> 특정 시점까지 rollback 을 할 수 있습니다.
    
    insert --> 글쓰기     성공
   
    update --> 포인트증가  성공
   
    commit; -- insert, update 모두 성공한 후 커밋해야 함.
    rollback -- insert, update 중 하나만 실패해도 롤백해야 한다.
         
            
    select *
    from employees
    where department_id = 50;
   
    update employees set first_name = '몰라'
    where department_id = 50;
    -- 45개 행 이(가) 업데이트되었습니다.

    savepoint point_1;
    -- Savepoint이(가) 생성되었습니다.
    
    delete from employees
    where department_id is null;
    -- 1 행 이(가) 삭제되었습니다.

    select first_name
    from employees
    where department_id = 50;
    -- 전부다 '몰라' 로 나온다.
    
    
    select *
    from employees
    where department_id is null;
    -- 행이 없다.
    
    Rollback To Savepoint point_1;
    -- 롤백 완료
    -- savepoint point_1; 이 선언되어진 이후로 실행된 DML문을 rollback 시킨다.
    /*
       그러므로
       delete from employees
       where department_id is null; 만 롤백시킨다.
    */
  
    select *
    from employees
    where department_id is null;
    -- 행이 나온다.
  
    select first_name
    from employees
    where department_id = 50;
    -- 전부다 '몰라' 로 나온다.
  
    rollback --> commit; 한 이후로 수행되어진 모든 DML문을 롤백시킨다.
    -- 롤백 완료.

    select first_name
    from employees
    where department_id = 50;
    -- first_name 컬럼의 값이 원상복구됨.
  
  
  
  
  
    -------- **** 데이터 정의어(DDL == Data Defination Language) **** ---------
    ==> DDL : create, drop, alter, truncate 
    --> 여기서 중요한 것은 DDL 문을 실행을 하면 자동적으로 commit; 이 되어진다.
    --  즉, auto commit 되어진다.
    
    
    select *
    from employees
    where employee_id = 100;
    -- salary ==> 24000
    -- email  ==> SKING
    
    update employees set salary = 43000, email = 'qwerasdfz'
    where employee_id = 100;
    
    create table tbl_imsi
    (no number
    ,name varchar2(20)
    );
    -- Table TBL_IMSI이(가) 생성되었습니다.

    -- DDL 문을 실행했으므로 자동적으로 commit; 이 되어진다.
    
    
    select *
    from employees
    where employee_id = 100;
    
    rollback;
    -- 롤백 완료 
    
    
    select *
    from employees
    where employee_id = 100;
    -- 위에서 DDL문(create)을 실행했으므로 자동적으로 commit; 이 되었기 때문에
    -- rollback 안 됨.

        
    update employees set salary = 24000
                       , email = 'SKING'
                       , first_name = 'Steven'
                       , last_name = 'King'
    where employee_id = 100;
    
    commit
    
    select *
    from employees
    where employee_id = 100;
    
    
    ------ ====== **** TRUNCATE table 테이블명; **** ====== ------  
    --> TRUNCATE table 테이블명; 을 실행하면 테이블명 에 존재하던 모든 행(row)들을 삭제해주고,
    --  테이블명에 해당하는 테이블은 완전초기화 가 되어진다.
    --  중요한 사실은 TRUNCATE table 테이블명; 은 DDL 문이기에 auto commit; 되어지므로 rollback 이 불가하다.   
    --  delete from 테이블명; 을 실행하면 이것도 테이블명 에 존재하던 모든 행(row)들을 삭제해준다.
    --  이것은 DML문 이므로 rollback 이 가능하다.
    
    create table tbl_emp_copy_1
    as
    select * from employees;
    -- Table TBL_EMP_COPY_1이(가) 생성되었습니다.
    
    select *
    from tbl_emp_copy_1;
    
    delete from tbl_emp_copy_1;
    -- 107개 행 이(가) 삭제되었습니다.
    
    select count(*)
    from tbl_emp_copy_1; -- 0
    
    rollback;
    -- 롤백 완료
    
    select count(*)
    from tbl_emp_copy_1; -- 107
    
    truncate table tbl_emp_copy_1;
    -- Table TBL_EMP_COPY_1이(가) 잘렸습니다.

    select *
    from tbl_emp_copy_1;
    
    select count(*)
    from tbl_emp_copy_1; -- 0
    
    rollback
    
    select *
    from tbl_emp_copy_1;
    
    select count(*)
    from tbl_emp_copy_1; -- 0
    
    
    
    
    
    -------- **** 데이터 제어어(DCL == Data Control Language) **** ---------
    ==> DCL : grant(권한을 부여하기), revoke(권한 회수하기)
    --> 여기서 중요한 것은 DCL 문을 실행을 하면 자동적으로 commit; 이 되어진다.
    -- 즉, auto commit 되어진다.
    
    
    ---- *** SYS 또는 SYSTEM 에서 아래와 같은 작업을 한다. 시작 *** ----
    show user;
    -- USER이(가) "SYS"입니다.
    -- orauser1 이라는 오라클 일반사용자 계정을 생성합니다. 암호는 cclass 라고 하겠습니다.
    create user orauser1 identified by cclass default tablespace users;
    -- User ORAUSER1이(가) 생성되었습니다.
    
    -- 생성되어진 오라클 일반사용자 계정인 orauser1 에게 오라클 서버에 접속이 되어지고,
    -- 접속이 되어진 후 테이블 등을 생성할 수 있는 권한을 부여해주겠다.
    grant connect, resource, unlimited tablespace to orauser1
    -- Grant을(를) 성공했습니다.

    ---- *** SYS 또는 SYSTEM 에서 아래와 같은 작업을 한다. 종료 *** ----
    
    
    ---- *** HR 에서 아래와 같은 작업을 한다. *** ----
    show user;
    -- USER이(가) "HR"입니다.
    
    -- 현재 오라클 서버에 접속된 사용자가 HR 이므로 HR.employees 대신에 employees 을 쓰면 HR.employees 으로 인식해준다.
    select *
    from HR.employees;
    
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 select 할 수 있도록 권한을 부여하겠습니다.
    grant select on employees to orauser1;    
    -- Grant을(를) 성공했습니다.
    
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 update 할 수 있도록 권한을 부여하겠습니다.
    grant update on employees to orauser1;    
    -- Grant을(를) 성공했습니다.
    
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 delete 할 수 있도록 권한을 부여하겠습니다.
    grant delete on employees to orauser1;    
    -- Grant을(를) 성공했습니다.
    
    
    
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 delete 할 수 있도록 부여한 권한을 회수하겠습니다.
    revoke delete on employees from orauser1;    
    -- Revoke을(를) 성공했습니다.
    
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 update 할 수 있도록 부여한 권한을 회수하겠습니다.
    revoke update on employees from orauser1;    
    -- Revoke을(를) 성공했습니다.
    
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 select 할 수 있도록 부여한 권한을 회수하겠습니다.
    revoke select on employees from orauser1;    
    -- Revoke을(를) 성공했습니다.
    
    
    
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 select, update, delete 할 수 있도록 권한을 부여하겠습니다.
    grant select, update, delete on employees to orauser1;    
    -- Grant을(를) 성공했습니다.
    
     -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 select, update, delete 할 수 있도록 부여한 권한을 회수하겠습니다.
    revoke select, update, delete on employees from orauser1;    
    -- Revoke을(를) 성공했습니다.
    
    show user;
    -- USER이(가) "HR"입니다.
    
    select *
    from employees;
    /*
        == Stored View 를 생성하는 이유 2가지 ==
        첫번째, 복잡한 SQL문을 간단히 만들어서 나중에 또 쓸려고.
        두번째, 민감한 정보가 들어있는 테이블에 있어서 공개할 행과 공개할 컬럼만 따로
               만들어서 오라클 사용자에게 부여하고자 할 때 Stored View 를 생성한다.
    */
    create or replace view view_emp_3080
    as
    select employee_id
         , first_name
         , last_name
         , hire_date
         , salary
         , commission_pct
         , department_id
         , substr(jubun, 1, 6) as birthday 
    from employees
    where department_id in (30,50,80);
    -- View VIEW_EMP_3080이(가) 생성되었습니다.
    
    grant select, update, delete on view_emp_3080 to orauser1; -- 뷰를 사용해서 일정 권한을 넘겨줌으로써 보안성을 높일 수 있다.
    -- Grant을(를) 성공했습니다.
    
    
    
    ---- *** SYS 또는 SYSTEM 에서 아래와 같은 작업을 한다. 시작 *** ----
    show user;
    -- USER이(가) "SYS"입니다.
    
    grant create synonym to orauser1;
    -- Grant을(를) 성공했습니다.
    
    ----- *** sys 또는 system 에서 아래와 같은 작업을 한다. 종료 *** ----
    
    
    ----- *** ORAUSER1 에서 아래와 같은 작업을 한다. 시작 *** ----
    SHOW USER
    
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
    
    
    ----- *** ORAUSER1 에서 아래와 같은 작업을 한다. 종료 *** ----
    
    
    
    
    
    
    
    
    
    ----- **** ==== 시퀀스(sequence) ==== **** -----
    
    -- 시퀀스(sequence)란? 쉽게 생각하면 은행에서 발급해주는 대기번호표 와 비슷한 것이다.
    -- 시퀀스(sequence)는 숫자로 이루어져 있으며 매번 정해진 증가치 만큼 숫자가 증가되어지는 것이다.    
   
    /*
      create sequence seq_yeyakno   -- seq_yeyakno 은 시퀀스(sequence) 이름이다.
      start with 1    -- 첫번째 출발은 1 부터 한다.
      increment by 1  -- 증가치 값    2 3 4 5 ......
      maxvalue 5      -- 최대값이 5 이다.
   -- nomaxvalue      -- 최대값이 없는 무제한. 계속 증가시키겠다는 말이다.
      minvalue 2      -- 최소값이 2 이다. cycle 이 있을때만 minvalue 에 주어진 값이 사용된다. 
                     --                nocycle 일 경우에는 minvalue 에 주어진 값이 사용되지 않는다.
                     -- minvalue 숫자 에 해당하는 숫자 값은 start with 숫자 에 해당하는 숫자 값과 같든지 
                     -- 아니면 start with 숫자 에 해당하는 숫자보다 작아야 한다.
                     
   -- nominvalue      -- 최소값이 없다.   
      cycle           -- 반복을 한다.
   -- nocycle         -- 반복이 없는 직진.
      nocache;
    */
    
    create sequence seq_yeyakno_1
    start with 1    -- 첫번째 출발은 1 부터 한다.
    increment by 1  -- 증가치는 1 이다. 즉 1씩 증가한다.
    maxvalue 5      -- 최대값이 5 이다.
    minvalue 2      -- 최소값이 2 이다.
    cycle           -- 반복을 한다.
    nocache;
    -- 결과 : 1 3 5 7 9
    /*
    오류 보고 -
    ORA-04006: START WITH cannot be less than MINVALUE 
    
    minvalue 숫자에 해당하는 start with 숫자에 해당하는 숫자 값과 같든지 
    또는 start with 숫자에 해당하는 숫자보다 작아야 한다.
    */
    
    drop sequence seq_yeyakno_2; -- 시퀀스 삭제하기
    drop sequence seq_yeyakno_1; -- 시퀀스 삭제하기
    -- Sequence SEQ_YEYAKNO_1이(가) 삭제되었습니다.
    
    create sequence seq_yeyakno_1
    start with 2    -- 첫번째 출발은 2 부터 한다.
    increment by 1  -- 증가치는 1 이다. 즉 1씩 증가한다.
    maxvalue 5      -- 최대값이 5 이다.
    minvalue 1      -- 최소값이 1 이다.
    cycle           -- 반복을 한다.
    nocache;
    -- 결과 : 2 3 4 5 1 2 3 4 5 1 2 3 4 5
    -- Sequence SEQ_YEYAKNO_1이(가) 생성되었습니다.
    
    
    ----- **** ==== 시퀀스(sequence) ==== **** -----
    
   -- 시퀀스(sequence)란? 쉽게 생각하면 은행에서 발급해주는 대기번호표 와 비슷한 것이다.
   -- 시퀀스(sequence)는 숫자로 이루어져 있으며 매번 정해진 증가치 만큼 숫자가 증가되어지는 것이다.    
   
   /*
     create sequence seq_yeyakno   -- seq_yeyakno 은 시퀀스(sequence) 이름이다.
     start with 1    -- 첫번째 출발은 1 부터 한다.
     increment by 1  -- 증가치 값    2 3 4 5 ......
     maxvalue 5      -- 최대값이 5 이다.
  -- nomaxvalue      -- 최대값이 없는 무제한. 계속 증가시키겠다는 말이다.
     minvalue 2      -- 최소값이 2 이다. cycle 이 있을때만 minvalue 에 주어진 값이 사용된다. 
                     --                nocycle 일 경우에는 minvalue 에 주어진 값이 사용되지 않는다.
                     -- minvalue 숫자 에 해당하는 숫자 값은 start with 숫자 에 해당하는 숫자 값과 같든지 
                     -- 아니면 start with 숫자 에 해당하는 숫자보다 작아야 한다.
                     
  -- nominvalue      -- 최소값이 없다.   
     cycle           -- 반복을 한다.
  -- nocycle         -- 반복이 없는 직진.
     nocache;
  */   
    
   create sequence seq_yeyakno_1
   start with 1    -- 첫번째 출발은 1 부터 한다.
   increment by 1  -- 증가치는 1 이다. 즉 1씩 증가한다.
   maxvalue 5      -- 최대값이 5 이다. 
   minvalue 2      -- 최소값이 2 이다.
   cycle           -- 반복을 한다.
   nocache;
  /*
   오류 보고 -
   ORA-04006: START WITH cannot be less than MINVALUE
   
   minvalue 숫자 에 해당하는 숫자 값은 start with 숫자 에 해당하는 숫자 값과 같든지
   또는 start with 숫자 에 해당하는 숫자보다 작아야 한다.
  */
  
  drop sequence seq_yeyakno_1; -- 시퀀스 삭제하기 
  -- Sequence SEQ_YEYAKNO_1이(가) 삭제되었습니다.
    
  create sequence seq_yeyakno_1
  start with 2    -- 첫번째 출발은 2 부터 한다.
  increment by 1  -- 증가치는 1 이다. 즉 1씩 증가한다.
  maxvalue 5      -- 최대값이 5 이다. 
  minvalue 1      -- 최소값이 1 이다.
  cycle           -- 반복을 한다.
  nocache;  
  -- 2 3 4 5 1 2 3 4 5 1 2 3 4 5
  -- Sequence SEQ_YEYAKNO_1이(가) 생성되었습니다.
  
  
  ---- *** 생성되어진 시퀀스(sequence)를 조회해 봅니다. *** ----
  select *
  from user_sequences;
   
  select last_number   -- 다음번에 들어올 시퀀스 값을 미리 알려주는 것이다. 
  from user_sequences
  where sequence_name = 'SEQ_YEYAKNO_1';
    
    
  create table tbl_board_test_1
  (boardno         number
  ,subject         varchar2(100)
  ,registerdate    date default sysdate
  );  
  -- Table TBL_BOARD_TEST_1이(가) 생성되었습니다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno_1.nextval, '첫번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 start 값이 2 이었다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno_1.nextval, '두번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다.  
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno_1.nextval, '세번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다. 
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno_1.nextval, '네번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다. 
  -- seq_yeyakno_1 시퀀스의 maxvalue 값이 5 이었고, cycle 이었다. 즉, 반복을 한다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno_1.nextval, '다섯번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 minvalue 값이 1 이었고, cycle 이었으므로
  -- maxvalue 값이 사용되어진 다음에 들어오는 시퀀스 값은 minvalue 값인 1 이 들어온다.  
  
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno_1.nextval, '여섯번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno_1.nextval, '일곱번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다. 
  
  select *
  from tbl_board_test_1;
  
  rollback;
  -- 롤백 완료.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno_1.nextval, '여덟번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다. 
  
  select * 
  from tbl_board_test_1;
  
  /*
     seq_yeyakno_1 시퀀스값의 사용은 
     2(start)  3  4  5(maxvalue) 1(minvalue) 2 3 4 5(maxvalue) 1(minvalue) 2 3 4 5 1 2 3 ...... 
     와 같이 사용된다.
  */
  
  commit;
  
  delete from tbl_board_test_1;
  
  create sequence seq_yeyakno_2
  start with 1    -- 첫번째 출발은 1 부터 한다.
  increment by 1  -- 증가치는 1 이다. 즉 1씩 증가한다.
  nomaxvalue      -- 최대값이 없는 무제한. 계속 증가시키겠다는 말이다.
  nominvalue      -- 최소값이 없다.
  nocycle         -- 반복을 안한다.
  nocache;
  -- Sequence SEQ_YEYAKNO_2이(가) 생성되었습니다.
  
  ---- *** 생성되어진 시퀀스(sequence)를 조회해 봅니다. *** ----
  select *
  from user_sequences;
    
  create table tbl_board_test_2
  (boardno         number
  ,subject         varchar2(100)
  ,registerdate    date default sysdate
  );  
  -- Table TBL_BOARD_TEST_2이(가) 생성되었습니다.
  
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '첫번째 글입니다.');
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '두번째 글입니다.');
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '세번째 글입니다.');
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '네번째 글입니다.');
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '다섯번째 글입니다.');
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '여섯번째 글입니다.');
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '일곱번째 글입니다.');
  
  select *
  from tbl_board_test_2;
  
  rollback;
  -- 롤백 완료.
  
  -- *** 다음번에 들어올 seq_yeyakno_2 시퀀스의 값이 얼마가 들어오는지 알고 싶다. *** --
  select last_number   -- 다음번에 들어올 시퀀스 값을 미리 알려주는 것이다. 
  from user_sequences
  where sequence_name = 'SEQ_YEYAKNO_2';
  
  
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '여덟번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_2 시퀀스의 increment 값이 1 이었다. 
  
  select * 
  from tbl_board_test_2;
  
  insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '아홉번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_2 시퀀스의 increment 값이 1 이었다.
  
  select * 
  from tbl_board_test_2;
  
  -- *** 시퀀스 SEQ_YEYAKNO_2 이 마지막으로 사용되어진 값을 알아보려고 한다. *** --
  select seq_yeyakno_2.currval 
  from dual;  -- 9
  
  -- *** 다음번에 들어올 seq_yeyakno_2 시퀀스의 값이 얼마가 들어오는지 알고 싶다. *** --
  select last_number   -- 다음번에 들어올 시퀀스 값을 미리 알려주는 것이다. 
  from user_sequences
  where sequence_name = 'SEQ_YEYAKNO_2';  -- 10
  
  --- *** 시퀀스(sequence) 삭제하기 *** ---
  drop sequence seq_yeyakno_2;
  -- Sequence SEQ_YEYAKNO_2이(가) 삭제되었습니다.
  
  
  ---- *** 생성되어진 시퀀스(sequence)를 조회해 봅니다. *** ----
  select *
  from user_sequences;
  
  
  
  
  -- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --
  -----------------------------------------------------------------------------
  -- ***** === Constraint(제약조건)을 사용하여 테이블을 생성해 보겠습니다. === ***** --
  
  /*
     >>>> 제약조건(Constraint)의 종류 <<<<
     
     --   제약조건의 이름은 오라클 전체에서 고유해야 한다.
     --   제약조건의 이름은 30 byte 이내이어야 한다.
     
     1. Primary Key(기본키, 대표식별자) 제약 [P]  -- 하나의 테이블당 오로지 1개만 생성할 수 있다.
                                               -- 어떤 컬럼에 Primary Key(기본키) 제약을 주면 그 컬럼에는 자동적으로 NOT NULL 이 주어지면서 동시에 그 컬럼에는 중복된 값은 들어올 수 없고 오로지 고유한 값만 들어오게 되어진다.
                                               -- 컬럼 1개를 가지고 생성된 Primary Key 를 Single Primary Key 라고 부르고,
                                               -- 컬럼 2개 이상을 가지고 생성된 Primary Key 를 Composite(복합) Primary Key 라고 부른다.
     
     2. Unique 제약 [U]              -- 하나의 테이블당 여러개를 생성할 수 있다.                                 
                                    -- 어떤 컬럼에 Unique 제약을 주면 그 컬럼에는 NULL 을 허용할 수 있으며, 그 컬럼에는 중복된 값은 들어올 수 없고 오로지 고유한 값만 들어오게 되어진다.             
                                    -- Unique Key 중에 후보키, 후보식별자가 되려면 해당 컬럼은 NOT NULL 이어야 한다. 
  
     3. Foreign Key(외래키) 제약 [R]  -- 하나의 테이블당 여러개를 생성할 수 있다. 
                                     -- Foreign Key(외래키) 제약에 의해 참조(Reference)받는 컬럼은 반드시 NOT NULL 이어야 하고, 중복된 값을 허락하지 않는 고유한 값만 가지는 컬럼이어야 한다. 
                                     
     4. Check 제약 [C]               -- 하나의 테이블당 여러개를 생성할 수 있다.
                                    -- insert(입력) 또는 update(수정) 시 어떤 컬럼에 입력되거나 수정되는 데이터값을 아무거나 허락하는 것이 아니라 조건에 맞는 데이터값만 넣고자 할 경우에 사용되는 것이다.
  
     5. NOT NULL 제약 [C]            -- 하나의 테이블당 여러개를 생성할 수 있다.
                                    -- 특정 컬럼에 NOT NULL 제약을 주면 그 컬럼에는 반드시 데이터값을 주어야 한다는 말이다. 
  */  
  
  
  ---- >>> Primary Key(기본키, 대표식별자) 제약에 대해서 알아봅니다. <<< ----
  
  ---- ***  "고객" 이라는 테이블을 생성해 보겠습니다. *** ----
  create table tbl_gogek
  (gogekID     varchar2(30)  primary key  -- Column Level 제약조건
  ,gogekName   varchar2(30)  
  ,gogekPhone  varchar2(30)  
  );
  -- Table TBL_GOGEK이(가) 생성되었습니다.
  
  drop table tbl_gogek purge;
  -- Table TBL_GOGEK이(가) 삭제되었습니다.
  
  create table tbl_gogek
  (gogekID     varchar2(30) 
  ,gogekName   varchar2(30)
  ,gogekPhone  varchar2(30)
  ,constraint tbl_gogek_gogekID_PK primary key(gogekID) -- Row Level 제약조건
                                   -- Single Primary Key
  );
  -- Table TBL_GOGEK이(가) 생성되었습니다.
  
  desc tbl_gogek;
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone) values(null, '이순신', null);
  /*
    오류 보고 -
    ORA-01400: cannot insert NULL into ("HR"."TBL_GOGEK"."GOGEKID")
  */
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone) values('leess', '이순신', null);
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone) values('leess', '이삼식', '010-5678-0987');
  /*
    gogekID 컬럼에 Primary key를  Column Level 제약조건으로 주었을시 
    오류 보고 -
    ORA-00001: unique constraint (HR.SYS_C007013) violated
  */
  /*
    gogekID 컬럼에 Primary key를  Row Level 제약조건으로 주었을시 
    오류 보고 -
    ORA-00001: unique constraint (HR.TBL_GOGEK_GOGEKID_PK) violated
  */
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone) values('samsik', '이삼식', '010-5678-0987');
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone) values('soonshin', '이순신', null);
  -- 1 행 이(가) 삽입되었습니다.
  
  commit;
  
  select *
  from tbl_gogek;
  
  --- *** tbl_gogek 테이블에 생성되어진 제약조건을 조회해 보겠습니다. *** ---
  select *
  from user_constraints
  where table_name = 'TBL_GOGEK';
  
  --- *** tbl_gogek 테이블에 생성되어진 제약조건을 조회해 보는데 어떤 컬럼에 생성되어 있는지 조회해 보겠습니다. *** ---
  select *
  from user_cons_columns
  where table_name = 'TBL_GOGEK';
  
  --- *** 일반적으로 제약조건을 조회할 경우에는 JOIN 을 통해서 조회합니다. *** ---
  select *
  from user_constraints A JOIN user_cons_columns B
  ON A.constraint_name = B.constraint_name
  where A.table_name = 'TBL_GOGEK';
  
  
  create table tbl_jumun
  (gogekID       varchar2(30)
  ,productCode   varchar2(30)
  ,productName   varchar2(30)
  ,jumunSu       number
  ,jumunDate     date default sysdate
  ,constraint tbl_jumun_gogekID_PK primary key(gogekID)
  ,constraint tbl_jumun_productCode_PK primary key(productCode)
  );
  /*
    오류 보고 -
    ORA-02260: table can have only one primary key
    -- primary key 는 하나의 테이블당 오로지 1개만 생성할 수 있다.
  */
  
  create table tbl_jumun
  (gogekID       varchar2(30)
  ,productCode   varchar2(30)
  ,productName   varchar2(30)
  ,jumunSu       number
  ,jumunDate     date default sysdate
  ,constraint tbl_jumun_PK primary key(gogekID, productCode)
                                    -- Composite(복합) Primary Key
  );
  -- Table TBL_JUMUN이(가) 생성되었습니다.
  
  desc tbl_jumun;
 /*
    이름          널?       유형           
    ----------- -------- ------------ 
    GOGEKID     NOT NULL VARCHAR2(30) 
    PRODUCTCODE NOT NULL VARCHAR2(30) 
    PRODUCTNAME          VARCHAR2(30) 
    JUMUNSU              NUMBER       
    JUMUNDATE            DATE  
 */
  
  insert into tbl_jumun(gogekID, productCode, productName, jumunsu)
  values('leess','swk','새우깡',10);
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_jumun(gogekID, productCode, productName, jumunsu)
  values('leess','kjk','감자깡',20);
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_jumun(gogekID, productCode, productName, jumunsu)
  values('samsik','swk','새우깡',10);
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_jumun(gogekID, productCode, productName, jumunsu)
  values('leess','swk','새우깡',50);
  /*
    오류 보고 -
    ORA-00001: unique constraint (HR.TBL_JUMUN_PK) violated
  */
  
  commit;
  
  drop table tbl_jumun purge;
  -- Table TBL_JUMUN이(가) 삭제되었습니다.
  
  create table tbl_jumun
  (gogekID       varchar2(30)
  ,productCode   varchar2(30)
  ,productName   varchar2(30)
  ,jumunSu       number
  ,jumunDate     date default sysdate
  ,constraint tbl_jumun_PK primary key(gogekID, productCode, jumunDate)
                                    -- Composite(복합) Primary Key
  );
  -- Table TBL_JUMUN이(가) 생성되었습니다.
  
  insert into tbl_jumun(gogekID, productCode, productName, jumunsu)
  values('leess','swk','새우깡',10);
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_jumun(gogekID, productCode, productName, jumunsu)
  values('leess','kjk','감자깡',20);
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_jumun(gogekID, productCode, productName, jumunsu)
  values('samsik','swk','새우깡',10);
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_jumun(gogekID, productCode, productName, jumunsu)
  values('leess','swk','새우깡',50);
  -- 1 행 이(가) 삽입되었습니다.
  
  commit;
  
  select *
  from tbl_jumun;
  
  
  --- *** 일반적으로 제약조건을 조회할 경우에는 JOIN 을 통해서 조회합니다. *** ---
  select *
  from user_constraints A JOIN user_cons_columns B
  ON A.constraint_name = B.constraint_name
  where A.table_name = 'TBL_GOGEK' and A.constraint_type = 'P';
  -- TBL_GOGEK 테이블에 존재하는 Primary Key 제약조건을 조회하고자 하는 것이다.
  -- Single Primary Key
  
  select *
  from user_constraints A JOIN user_cons_columns B
  ON A.constraint_name = B.constraint_name
  where A.table_name = 'TBL_JUMUN' and A.constraint_type = 'P';
  -- TBL_JUMUN 테이블에 존재하는 Primary Key 제약조건을 조회하고자 하는 것이다.
  -- Composite(복합) Primary Key
  
  
  
  ---- >>> Unique Key(후보키, 후보식별자) 제약에 대해서 알아봅니다. <<< ----
  --   Unique Key 중에 후보키, 후보식별자가 되려면 해당 컬럼은 NOT NULL 이어야 한다.
  --   아래의 예제에서는 gogekEmail 컬럼이 후보키, 후보식별자가 된다.
  
  ---- ***  "고객" 이라는 테이블을 생성해 보겠습니다. *** ----
  drop table tbl_gogek purge;
  -- Table TBL_GOGEK이(가) 삭제되었습니다.
  
  create table tbl_gogek
  (gogekID     varchar2(30)
  ,gogekName   varchar2(30) not null 
  ,gogekPhone  varchar2(30) -- null  ==> null 을 허용함 
  ,gogekEmail  varchar2(50) not null 
  ,constraint PK_tbl_gogek_gogekID primary key(gogekID)
  ,constraint UQ_tbl_gogek_gogekPhone unique(gogekPhone)  -- gogekPhone 컬럼에 unique 제약을 준것이다. 
  ,constraint UQ_tbl_gogek_gogekEmail unique(gogekEmail)  -- gogekEmail 컬럼에 unique 제약을 준것이다.
  );
  -- Table TBL_GOGEK이(가) 생성되었습니다.
  
  desc tbl_gogek;
/*
     이름         널?       유형           
    ---------- -------- ------------ 
    GOGEKID    NOT NULL VARCHAR2(30) 
    GOGEKNAME  NOT NULL VARCHAR2(30) 
    GOGEKPHONE          VARCHAR2(30) 
    GOGEKEMAIL NOT NULL VARCHAR2(50) 
*/
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone, gogekEmail) 
  values('leess', '이순신', '010-5678-0987', 'leess@gmail.com');
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone, gogekEmail) 
  values('eomjh', '엄정화', null, 'eomjh@gmail.com');
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone, gogekEmail) 
  values('youks', '유관순', null, 'youks@gmail.com');
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone, gogekEmail) 
  values('seokj', '서강준', '010-5678-0987', 'seokj@gmail.com');
  /*
    오류 보고 -
    ORA-00001: unique constraint (HR.UQ_TBL_GOGEK_GOGEKPHONE) violated
  */
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone, gogekEmail) 
  values('seokj', '서강준', '010-3567-1234', 'eomjh@gmail.com');
  /*
    오류 보고 -
    ORA-00001: unique constraint (HR.UQ_TBL_GOGEK_GOGEKEMAIL) violated
  */
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone, gogekEmail) 
  values('seokj', '서강준', '010-3567-1234', 'seokj@gmail.com');
  -- 1 행 이(가) 삽입되었습니다.
  
  insert into tbl_gogek(gogekID, gogekName, gogekPhone, gogekEmail) 
  values('leehr', '이혜리', '010-7788-0090', null);
  /*
    오류 보고 -
    ORA-01400: cannot insert NULL into ("HR"."TBL_GOGEK"."GOGEKEMAIL")
  */
  
  commit;
  
  select * 
  from tbl_gogek;
  
  
  --- *** Composite(복합) Unique Key 예제 *** ---
  create table tbl_jumun_2
  (gogekID       varchar2(30) not null 
  ,productCode   varchar2(30) not null 
  ,productName   varchar2(30)
  ,jumunSu       number
  ,jumunDate     date default sysdate not null 
  ,constraint UQ_tbl_jumun_2 unique(gogekID, productCode, jumunDate)
                               -- Composite(복합) Unique Key
  );
  -- Table TBL_JUMUN_2이(가) 생성되었습니다.
  
  desc tbl_jumun_2;
  
  
  --- *** 일반적으로 제약조건을 조회할 경우에는 JOIN 을 통해서 조회합니다. *** ---
  select *
  from user_constraints A JOIN user_cons_columns B
  ON A.constraint_name = B.constraint_name
  where A.table_name = 'TBL_GOGEK' and A.constraint_type = 'U';
  -- TBL_GOGEK 테이블에 존재하는 Unique Key 제약조건을 조회하고자 하는 것이다.
  -- Unique Key
  -- Single Unique Key 
  
  select *
  from user_constraints A JOIN user_cons_columns B
  ON A.constraint_name = B.constraint_name
  where A.table_name = 'TBL_JUMUN_2' and A.constraint_type = 'U';
  -- TBL_JUMUN_2 테이블에 존재하는 Unique Key 제약조건을 조회하고자 하는 것이다.
  -- Unique Key
  -- Composite(복합) Unique Key 
  
  
  
  
  ---- >>> Check 제약에 대해서 알아봅니다. <<< ----
  
  create table tbl_sawon
  (sano        number                      -- 사원번호
  ,saname      varchar2(20) not null       -- 사원명
  ,salary      number(5) not null          -- 급여는 커미션 보다 커야 한다. 
  ,commission  number(5)                   -- 커미션은 0 이상이어야 한다. 
  ,jik         varchar2(20) default '사원'  -- 직급의 종류는 '사장','부장','과장','대리','사원' 만 가능하다.  
  ,email       varchar2(50) not null        -- 이메일
  ,hire_date   date default sysdate         -- 입사일자
  
  ,constraint PK_tbl_sawon_sano primary key(sano)
  ,constraint CK_tbl_sawon_salary_commission check(commission >= 0 and salary > commission)
  ,constraint CK_tbl_sawon_jik check( jik in('사장','부장','과장','대리','사원') )
  ,constraint UQ_tbl_sawon_email unique(email)
  );
  -- Table TBL_SAWON이(가) 생성되었습니다.
  
  insert into tbl_sawon(sano, saname, salary, commission, jik, email)
  values(1001, '홍길동', 500, 1000, '과장', 'hongkd@naver.com');
  /*
    오류 보고 -
    ORA-02290: check constraint (HR.CK_TBL_SAWON_SALARY_COMMISSION) violated
  */
  
  insert into tbl_sawon(sano, saname, salary, commission, jik, email)
  values(1001, '홍길동', 500, -100, '과장', 'hongkd@naver.com');
  /*
    오류 보고 -
    ORA-02290: check constraint (HR.CK_TBL_SAWON_SALARY_COMMISSION) violated
  */
  
  insert into tbl_sawon(sano, saname, salary, commission, jik, email)
  values(1001, '홍길동', 500, 100, '장군', 'hongkd@naver.com');
  /*
    오류 보고 -
    ORA-02290: check constraint (HR.CK_TBL_SAWON_JIK) violated
  */
  
  insert into tbl_sawon(sano, saname, salary, commission, jik, email)
  values(1001, '홍길동', 500, 100, '과장', 'hongkd@naver.com');
  -- 1 행 이(가) 삽입되었습니다.
  
  commit;
  -- 커밋 완료.
  
  
  
  
  ---- >>> Foreign Key(외래키, 참조키) 제약에 대해서 알아보겠습니다. <<< ----
  
  ---  *** 고객들의 예약을 받아주는 "예약" 테이블을 생성해보겠습니다. *** ---
  
  select A.constraint_name, A.constraint_type, A.search_condition,
         B.column_name, B.position
  from user_constraints A JOIN user_cons_columns B
  ON A.constraint_name = B.constraint_name
  where A.table_name = 'TBL_GOGEK';
  
  --- 어떤 한명의 고객은(예: leess  이순신) 예약을 1번도 안 할 수도 있고,
  --- 예약을 딱 1번만 할 수 있고, 예약을 여러번 할 수도 있다.
  drop table tbl_yeyak purge;
  
  select *
  from tbl_yeyak
  
  create table tbl_yeyak
  (yeyakno      number  --> 예약번호. 예약번호의 값은 NOT NULL 이면서 고유한 값만 가져야 한다.
                        --  그러므로 yeyakno 컬럼에는 Primary Key 제약을 주어야 한다.
                        /*
                            예약번호는 사용자가 수동적으로 입력치 않고 자동적으로 들어와야 한다.
                            그리고 예약번호는 매번 그 숫자가 증가되면서 고유해야 한다.
                            이렇게 되려면 sequence 를 사용하면 된다.
                        */
  ,fk_gogekID   varchar2(30) not null -- 고객아이디 
                                      -- fk_gogekID 컬럼에 들어올 수 있는 값은 tbl_gogek 테이블의 gogekId 컬럼의 값만 들어와야 한다. 
                                      -- 참조를 받는 컬럼은 (여기서는 tbl_gogek 테이블의 gogekid 임) 반드시 고유한 값을 가지는 컬럼이어야 한다.
                                      -- 즉, 참조를 받는 컬럼은 (여기서는 tbl_gogek 테이블의 gogekid 임) Primary Key 또는 Unique 제약을 가져야 한다.
                                      
  ,ticketCnt    number(2) not null    -- 예약티켓개수   
                                      -- 데이터 타입이 number(2) 이므로 -99 ~ 99 값들이 들어온다.
                                      -- 그런데 예약티켓개수는 1번 예약에 최대 10개 까지만 허락하고자 한다.
                                      -- 즉, ticketCnt 컬럼에 들어오는 값은 1 ~ 10 까지만 가능하도록 해야 한다.
                                      -- 이러한 경우 값을 검사해야 하므로 Check 제약을 사용하면 된다.
  ,registerDay date default sysdate
  ,constraint PK_tbl_yeyak_yeyakno primary key(yeyakno)
  ,constraint Fk_tbl_yeyak_fk_gogekID Foreign key(fk_gogekID) references tbl_gogek
  /*
      tbl_yeyak 테이블의 fk_gogekID 컬럼에는 foreign key 제약을 만들었는데
      그 뜻은 tbl_yeyak 테이블의 fk_gogekID 컬럼에 입력되거나 수정되어지는 값은 반드시 tbl_gogek 테이블의 gogekID 컬럼에 존재하는 값들만 가능하다는 말이다.
      즉, tbl_gogek 테이블의 gogekid 컬럼에 존재하지 않는 값은 tbl_yeyak 테이블의 fk_gogekID 컬럼에 들어올 수 없다는 말이다.
      그리고 중요한 것은 tbl_gogek 의 gogekid 컬럼은 식별자 컬럼(Primary Key, Unique Key)이어야 한다. 
  */
  ,constraint CK_tbl_yeyak_ticketCnt check( 1<= ticketCnt and ticketCnt <= 10)
  );
  
  
  create sequence seq_tbl_yeyak
  start with 1
  increment by 1
  nomaxvalue
  nominvalue
  nocycle
  nocache;
  
  insert into tbl_yeyak(yeyakno, fk_gogekID, ticketCnt)
  values(seq_tbl_yeyak.nextval, 'leess', 1)
  
  insert into tbl_yeyak(yeyakno, fk_gogekID, ticketCnt)
  values(seq_tbl_yeyak.nextval, 'leess', 5)
  
  insert into tbl_yeyak(yeyakno, fk_gogekID, ticketCnt)
  values(seq_tbl_yeyak.nextval, 'superman', 3)
  /*
    오류 보고 -
    ORA-02291: integrity constraint (HR.FK_TBL_YEYAK_FK_GOGEKID) violated - parent key not found
    -- 참조를 받는 테이블이 parent 테이블
  */
  
  insert into tbl_yeyak(yeyakno, fk_gogekID, ticketCnt)
  values(seq_tbl_yeyak.nextval, 'eomjh', 3)
  
  insert into tbl_yeyak(yeyakno, fk_gogekID, ticketCnt)
  values(seq_tbl_yeyak.nextval, 'eomjh', 10)
  
  insert into tbl_yeyak(yeyakno, fk_gogekID, ticketCnt)
  values(seq_tbl_yeyak.nextval, 'eomjh', 7)
  
  commit
  
  ---- **** foreign key 제약이 있는 테이블을 "자식" 테이블 이라고 하고, 
  ----      foreign key 에 의해 참조를 받는 테이블을 "부모" 테이블 이라고 한다. **** ----
  
  --- "자식" 테이블(여기서는 tbl_yeyak 테이블)에 입력되어진 데이터가 "부모" 테이블(여기서는 tbl_gogek)에 존재하는 경우에
  --- "부모" 테이블의 행을 삭제할 때 어떻게 되어지는지 알아봅니다.
  select *
  from tbl_yeyak; -- "자식" 테이블
  /*
    ----------------------------------------------------
     yeyakno    fk_gogekid    ticketcnt    registerday
    ----------------------------------------------------
        1         leess           5         22/01/17
        3	      eomjh	          3	        22/01/17
        5	      eomjh	          10	    22/01/17
        6	      eomjh	          7	        22/01/17
  */
  
  select *
  from tbl_gogek; -- "부모" 테이블
  /*
    ------------------------------------------------------------
      gogekid   gogekname     gogekphone         gogekemail
    ------------------------------------------------------------
       leess	  이순신	     010-5678-9087	   leess@gmail.com
       eomjh	  엄정화		                   eomjh@gmail.com
       youks	  유관순		                   youks@gmail.com
       seokj	  서강준	     010-3567-1234	   seokj@gmail.com
    ------------------------------------------------------------
  */
  
    delete from tbl_gogek
    where gogekid = 'seokj';
    -- 1 행 이(가) 삭제되었습니다.

    delete from tbl_gogek
    where gogekid = 'leess';
    /*
        오류 보고 -
        ORA-02292: integrity constraint (HR.FK_TBL_YEYAK_FK_GOGEKID) violated - child record found
        -- 자식 테이블인 예약테이블에 이순신을 참조하는 행이 남아있기때문에 삭제할 수 없다 
    */
    select *
    from tbl_gogek
/*
    오류 보고 -
    ORA-02292: integrity constraint (HR.FK_TBL_YEYAK_FK_GOGEKID) violated - child record found
    
    child ==> 자식 테이블인 tbl_yeyak 을 말한다.
    child record found 말은 tbl_yeyak 테이블에 존재하는 1         leess           5         22/01/17 행을 말한다.
*/

     --- [!!중요!!] tbl_gogek 테이블에 모든 행들을 삭제하려고 한다. 
  delete from tbl_gogek; -- [부모] 테이블 
  /*
    오류 보고 -
    ORA-02292: integrity constraint (HR.FK_TBL_YEYAK_FK_GOGEKID) violated - child record found
    
    HR.FK_TBL_YEYAK_FK_GOGEKID 은 tbl_gogek 테이블의 자식테이블인 곳에서 Foreign Key 제약조건 이름으로 FK_TBL_YEYAK_FK_GOGEKID 이 사용되어지고 있다.
    
    그러면 Foreign Key 제약조건 이름으로 FK_TBL_YEYAK_FK_GOGEKID 을 사용하는 테이블명을 알아와야 한다.
  */
 
  -- *** Foreign Key 제약조건 이름으로 FK_TBL_YEYAK_FK_GOGEKID 을 사용하는 테이블명 알아오기 *** --
  select table_name
  from user_constraints
  where constraint_type = 'R' and constraint_name = 'FK_TBL_YEYAK_FK_GOGEKID';
  -- TBL_YEYAK

 
  -- !!!!! [퀴즈] TBL_YEYAK 테이블에 존재하는 제약조건 중에 Foreign Key 제약조건을 조회하는데 아래와 같이 나오도록 하세요.. !!!!!
  ----------------------------------------------------------------------------------------------------------
   제약조건명                   제약조건타입     컬럼명          참조를받는부모테이블명       참조를받는식별자컬럼명
  ----------------------------------------------------------------------------------------------------------
   FK_TBL_YEYAK_FK_GOGEKID         R         FK_GOGEKID          TBL_GOGEK                 GOGEKID 
 
 
   select constraint_name, constraint_type, r_constraint_name
   from user_constraints
   where table_name = 'TBL_YEYAK' and constraint_type = 'R';
   /*
     -------------------------------------------------------------------
      constraint_name           constraint_type    r_constraint_name
     -------------------------------------------------------------------
      FK_TBL_YEYAK_FK_GOGEKID          R           PK_TBL_GOGEK_GOGEKID
   */
   
   select constraint_name, column_name
   from user_cons_columns
   where table_name = 'TBL_YEYAK';
 /*
    ------------------------------------------
     constraint_name            column_name
    ------------------------------------------   
     SYS_C007040               FK_GOGEKID
     SYS_C007041               TICKETCNT
     CK_TBL_YEYAK_TICKETCNT       TICKETCNT
     PK_TBL_YEYAK_YEYAKNO       YEYAKNO
     FK_TBL_YEYAK_FK_GOGEKID   FK_GOGEKID
 */
   
   select constraint_name, table_name, column_name
   from user_cons_columns
   where constraint_name = (select r_constraint_name
                            from user_constraints
                            where table_name = 'TBL_YEYAK' and constraint_type = 'R');
 /*  
    ---------------------------------------------------
     constraint_name        table_name     column_name
    ---------------------------------------------------
     PK_TBL_GOGEK_GOGEKID   TBL_GOGEK      GOGEKID
 */
 
 WITH
 A as
 (
   select constraint_name, constraint_type, r_constraint_name
   from user_constraints
   where table_name = 'TBL_YEYAK' and constraint_type = 'R'
 )
 , 
 B as
 (
   select constraint_name, column_name
   from user_cons_columns
   where table_name = 'TBL_YEYAK'
 )
 ,
 C as
 (
   select constraint_name, table_name, column_name
   from user_cons_columns
   where constraint_name = (select r_constraint_name
                            from user_constraints
                            where table_name = 'TBL_YEYAK' and constraint_type = 'R')
 )
 select A.constraint_name AS 제약조건명
      , A.constraint_type AS 제약조건타입
      , B.column_name AS 컬럼명
      , C.table_name AS 참조를받는부모테이블
      , C.column_name AS 참조를받는식별자컬럼
 from A JOIN B
 ON A.constraint_name = B.constraint_name
 JOIN C
 ON A.r_constraint_name = C.constraint_name;
 
 delete from tbl_gogek;
 /*
    오류 보고 -
    ORA-02292: integrity constraint (HR.FK_TBL_YEYAK_FK_GOGEKID) violated - child record found
 */
 
 -- TBL_YEYAK 테이블에 존재하는 foreign key 제약조건인 FK_TBL_YEYAK_FK_GOGEKID 을 삭제 하겠습니다.
 alter table TBL_YEYAK
 drop constraint FK_TBL_YEYAK_FK_GOGEKID;
 -- Table TBL_YEYAK이(가) 변경되었습니다.

select *
from tbl_gogek;

delete from tbl_gogek;
-- 3개 행 이(가) 삭제되었습니다.

commit



 ---- **** ==== !!!! Foreign Key 생성시 on delete cascade 옵션을 주는 것 !!!! ==== **** ----

 create table tbl_sangpum  --> "상품" 테이블
 (sangpum_code      varchar2(20)
 ,sangpum_name      varchar2(20) not null
 ,price             number(10)
 ,constraint PK_tbl_sangpum_sangpum_code primary key(sangpum_code)
 );
 -- Table TBL_SANGPUM이(가) 생성되었습니다.

 insert into tbl_sangpum(sangpum_code, sangpum_name, price) values('swk', '새우깡', 3000); 
 insert into tbl_sangpum(sangpum_code, sangpum_name, price) values('kjk', '감자깡', 4000);
 insert into tbl_sangpum(sangpum_code, sangpum_name, price) values('ypr', '양파링', 5000);
 
 commit;
 
 create table tbl_sangpum_review
 (review_no        number                       -- 후기글번호
 ,fk_sangpum_code  varchar2(20) not null        -- 후기를 남길 상품코드
 ,review_contents  Nvarchar2(2000) not null     -- 후기내용물
 ,register_day     date default sysdate         -- 작성일자
 ,constraint PK_tbl_sangpum_review primary key(review_no)
 ,constraint FK_tbl_sangpum_review foreign key(fk_sangpum_code) references tbl_sangpum(sangpum_code)
 );
 
 insert into tbl_sangpum_review(review_no, fk_sangpum_code, review_contents)
 values(1, 'swk', '아주 맛있어요!!');
 
 insert into tbl_sangpum_review(review_no, fk_sangpum_code, review_contents)
 values(2, 'kjk', '맛이 고소해요~~');
 
 insert into tbl_sangpum_review(review_no, fk_sangpum_code, review_contents)
 values(3, 'swk', '가성비 만점!!');
 
 commit;

 select *
 from tbl_sangpum_review;
 
 select *
 from tbl_sangpum;
 
 delete from tbl_sangpum
 where sangpum_code = 'ypr';
 -- 1 행 이(가) 삭제되었습니다.

 delete from tbl_sangpum
 where sangpum_code = 'swk';
 /*
    오류 보고 -
    ORA-02292: integrity constraint (HR.FK_TBL_SANGPUM_REVIEW) violated - child record found
 */
 
 delete from tbl_sangpum_review
 where fk_sangpum_code = 'swk';
 -- 2개 행 이(가) 삭제되었습니다.

 select *
 from tbl_sangpum_review;
 
 delete from tbl_sangpum
 where sangpum_code = 'swk';
 -- 1 행 이(가) 삭제되었습니다.

 rollback;
 
 drop table tbl_sangpum_review
 
 
 create table tbl_sangpum_review_2
 (review_no        number                       -- 후기글번호
 ,fk_sangpum_code  varchar2(20) not null        -- 후기를 남길 상품코드
 ,review_contents  Nvarchar2(2000) not null     -- 후기내용물
 ,register_day     date default sysdate         -- 작성일자
 ,constraint PK_tbl_sangpum_review_2 primary key(review_no)
 ,constraint FK_tbl_sangpum_review_2 foreign key(fk_sangpum_code) references tbl_sangpum(sangpum_code) on delete cascade
 /*
    on delete cascade 를 해주었으므로 부모테이블인 tbl_sangpum 테이블에서 행을 delete 를 할 때 먼저 자식테이블인 tbl_sangpum_review_2 테이블에서 자식레코드(행)를 먼저 delete를 해준다.
 */
 );
 -- Table TBL_SANGPUM_REVIEW_2이(가) 생성되었습니다.

 insert into tbl_sangpum_review_2(review_no, fk_sangpum_code, review_contents)
 values(1, 'swk', '아주 맛있어요!!');
 
 insert into tbl_sangpum_review_2(review_no, fk_sangpum_code, review_contents)
 values(2, 'kjk', '맛이 고소해요~~');
 
 insert into tbl_sangpum_review_2(review_no, fk_sangpum_code, review_contents)
 values(3, 'swk', '가성비 만점!!');
 
 commit;

 select *
 from tbl_sangpum_review_2;
 
 select *
 from tbl_sangpum;
 
 delete from tbl_sangpum
 where sangpum_code = 'ypr';
 -- 1 행 이(가) 삭제되었습니다.

 commit;

 delete from tbl_sangpum
 where sangpum_code = 'swk';
 /*
    오류 보고 -
    ORA-02292: integrity constraint (HR.FK_TBL_SANGPUM_REVIEW) violated - child record found
 */
 
 delete from tbl_sangpum_review_2
 where fk_sangpum_code = 'swk';
 -- 2개 행 이(가) 삭제되었습니다.

 select *
 from tbl_sangpum_review_2;
 
 delete from tbl_sangpum
 where sangpum_code = 'swk';
 -- 1 행 이(가) 삭제되었습니다.
 /*
    on delete cascade 를 해주었으므로 부모테이블인 tbl_sangpum 테이블에서 행을 delete 를 할 때 먼저 자식테이블인 tbl_sangpum_review_2 테이블에서 자식레코드(행)를 먼저 delete를 해준다.    
    즉, 아래의 DML 문이 먼저 자동적으로 실행되어진다.
    delete from tbl_sangpum_review_2
    where fk_sangpum_code = 'swk';
    -- 2개 행 이(가) 삭제되었습니다.
 */
 
 select *
 from tbl_sangpum_review_2;
 
 select *
 from tbl_sangpum;
 
 select *
 from tbl_sangpum_review_2;
 
 select *
 from tbl_sangpum;
 
 select *
 from departments;
 
 select * 
 from employees;
 
 update employees set department_id = 500
 where employee_id = 100;
 
 delete from departments
 where department_id = 60;
 
 rollback;
 
 
 
 
 
 ---- **** ==== !!!! Foreign Key 생성시 on delete set null 옵션을 주는 것 !!!! ==== **** ----