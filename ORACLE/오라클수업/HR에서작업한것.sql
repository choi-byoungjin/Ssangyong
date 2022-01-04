SHOW USER;
-- USER이(가) "HR"입니다.

SELECT * FROM DBA_USERS;
-- ORA-00942: table or view does not exist
-- DBA_USERS 은 관리자만 조회할 수 있는 것이지 일반 사용자인 HR 은 조회가 불가하다는 말이다.


-- ////////////////////////////////////////////////////////////////////////////// --

/*
    ORACLE 은 관계형 데이터베이스(Relation DataBase) 관리 시스템(Management System) 이다.
    
    RDBMS(Relation DataBase Management System)란 ?
    ==> 데이터를 열(Column, Field)과 행(Row, Record, Tuple)으로 이루어진 테이블(Table, Entity(개체), Relation) 형태로 
        저장해서 관리하는 시스템을 말한다.
*/

-- ** 현재 오라클 서버에 접속되어진 사용자(지금은 HR)가 만든(소유의) 테이블(Table) 목록을 조회하겠다.
SELECT * 
FROM TAB;
/*
---------------------------
TNAME               TABTYPE
---------------------------
COUNTRIES	        TABLE
DEPARTMENTS	        TABLE
EMPLOYEES	        TABLE
EMP_DETAILS_VIEW	VIEW    (VIEW 는 테이블은 아니지만 SELECT 되어진 결과물을 마치 테이블처럼 보는 것)
JOBS	            TABLE
JOB_HISTORY	        TABLE
LOCATIONS	        TABLE
REGIONS	            TABLE
*/

-- SQL 명령어는 대,소문자를 구분하지 않는다.
-- 테이블명과 컬럼명도 대,소문자를 구분하지 않는다.

SELECT *
FROM DEPARTMENTS;

select *
from departments;

SELECT DEPARTMENT_ID, DEPARTMENT_NAME
FROM DEPARTMENTS;

select department_id, department_name
from departments;

--- !!! 저장되어진 데이터 값 만큼은 반드시 대,소문자를 구분합니다. !!! ---
SELECT *
FROM DEPARTMENTS
WHERE DEPARTMENT_NAME = 'IT'; -- 조회되어서 나온다.

SELECT *
FROM DEPARTMENTS
WHERE DEPARTMENT_NAME = 'it'; -- 아무것도 안나온다.

SELECT *
FROM DEPARTMENTS
WHERE DEPARTMENT_NAME = 'It'; -- 아무것도 안나온다.

SELECT *
FROM DEPARTMENTS
WHERE DEPARTMENT_NAME = 'Sales'; 

--------------------------------------------------------------------------------

--- 어떤 테이블의 컬럼의 구성을 알고자 한다라면 아래와 같이 하면 된다.
DESCRIBE DEPARTMENTS; -- DEPARTMENTS 테이블의 컬럼(COLUMN)의 정보를 알려주는 것이다.
-- 또는 
DESC DEPARTMENTS; --"부서" 테이블

/*
    이름              널?       유형           
    --------------- -------- ------------ 
    DEPARTMENT_ID   NOT NULL NUMBER(4)    
    DEPARTMENT_NAME NOT NULL VARCHAR2(30) 
    MANAGER_ID               NUMBER(6)    
    LOCATION_ID              NUMBER(4)    
*/

SELECT *
FROM EMPLOYEES; -- "사원" 테이블

DESC EMPLOYEES;
/*
    이름                                          널?              유형           
    -------------------------------------      --------       ------------ 
    EMPLOYEE_ID     (사원번호)                  NOT NULL       NUMBER(6)        -999999 ~ 999999    
    FIRST_NAME      (이름)                                     VARCHAR2(20)    문자열 최대 20 byte 까지만 들어온다.    
    LAST_NAME       (성)                       NOT NULL       VARCHAR2(25)     문자열 최대 25 byte 까지만 들어온다.
    EMAIL           (이메일)                    NOT NULL       VARCHAR2(25)     
    PHONE_NUMBER    (연락처)                                   VARCHAR2(20) 
    HIRE_DATE       (입사일)                    NOT NULL       DATE            날짜만 들어온다.
    JOB_ID          (직종)                     NOT NULL       VARCHAR2(10) 
    SALARY          (기본급여)                                 NUMBER(8,2)      -999999.99 ~ 999999.99
    COMMISSION_PCT  (커미션[수당]퍼센티지)                       NUMBER(2,2)     -0.99 ~ 0.99
    MANAGER_ID      (직속상관[사수]의 사원번호)                  NUMBER(6)
    DEPARTMENT_ID   (해당사원이 근무하는 부서번호)                NUMBER(4)    
*/

SELECT *
FROM LOCATIONS; -- 부서의 위치정보를 알려주는 테이블

SELECT *
FROM COUNTRIES; -- 국가정보를 알려주는 테이블

SELECT *
FROM REGIONS;   -- 대륙정보를 알려주는 테이블

--------------------------------------------------------------------------------

/*
    ==== 아주아주아주아주아주아주아주아주아주아주 중요!!!!! ====
    ==== !! 필수 암기 !! ====
    
    ==== 어떠한 테이블(또는 뷰)에서 데이터 정보를 꺼내와 보는 명령어인 SELECT 의 처리 순서 ====
    
    SELECT 컬럼명, 컬럼명,...  -- 5 컬럼명 대신에 *(아스테리크) 을 쓰면 모든 컬럼을 뜻하는 것이다.
    FROM 테이블명(또는 뷰명)   -- 1
    WHERE 조건절              -- 2 WHERE 조건절이 뜻하는 것은 해당 테이블명(또는 뷰명)에서 조건에 만족하는 행(ROW)을 메모리(RAM)에 로딩(올리는것)을 해주는 것이다.
    GROUP BY 절              -- 3
    HAVING 그룹함수조건절      -- 4
    ORDER BY 절              -- 6
*/

--------------------------------------------------------------------------------

----- *** NULL 을 처리해주는 함수 *** -----
----- NULL은 존재하지 않는 것이므로 4칙연산(+ - * /)에 NULL 이 포함되어지면 그 결과는 무조건 NULL 이 된다.

-- 1. NVL

   SELECT '안녕하세요'
   FROM DUAL; -- DUAL 은 SELECT 다음에 나오는 값들을 화면에 보여주기 위한 용도로 쓰이는 가상테이블이다.
   
   SELECT 1+2, 2-1, 3*2, 5/2,
          1+NULL, 2-NULL, 3*NULL, 5/NULL
   FROM DUAL;

   SELECT NVL(7,3), NVL(NULL, 3),
          NVL('이순신','거북선'), NVL(NULL, '거북선')
   FROM DUAL;
   
   
-- 2. NVL2

   SELECT NVL2(7,3,2), NVL2(NULL, 3,2),
          NVL2('이순신','거북선','구국영웅'), NVL2(NULL, '거북선', '구국영웅')
   FROM DUAL;
   
   
   -- EMPLOYEES(직원) 테이블에서 부서번호(DEPARTMENT_ID) 60번에 근무하는 사원들만
   -- 사원번호, 사원명, 기본급여, 커미션퍼센티지, 부서번호를 나타내세요.
   
   SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT, DEPARTMENT_ID
   FROM EMPLOYEES
   WHERE DEPARTMENT_ID = 60;  -- WHERE 절이 있으므로 DEPARTMENT_ID 컬럼의 값이 60인 행들만 메모리(RAM) 에 올라간다.
   
   -- EMPLOYEES(직원) 테이블에서 모든 사원들에 대해
   -- 사원번호, 사원명, 기본급여, 커미션퍼센티지, 부서번호를 나타내세요.
   
   SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT, DEPARTMENT_ID
   FROM EMPLOYEES;
   -- WHERE 절이 없으므로 EMPLOYEES 테이블에 있는 모든 행들이 메모리(RAM)에 올라간다.
   -- COMMISSION_PCT 컬럼이 뜻하는 바는 다음과 같다.
   -- COMMISSION_PCT 컬럼의 값이 NULL 이라면 수당이 존재하지 않는다는 말이고,
   -- COMMISSION_PCT 컬럼의 값이 0.3 이라면 SALARY(기본급여)의 30%가 수당이 된다는 말이다.
   -- SALARY   COMMISSION_PCT   SALARY*COMMISSION_PCT
   --  1000         0.3                 300
   --  2000         0.3                 600
   --  4000         0.3                1200
   --  5000         NULL               NULL
   
   
   -- EMPLOYEES(직원) 테이블에서 부서번호(DEPARTMENT_ID) 60번에 근무하는 사원들만
   -- 사원번호, 사원명, 기본급여, 커미션퍼센티지, 부서번호를 나타내세요.
   --> 월급은 기본급여 + 수당액을 합친것을 말한다.
   
   SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT, 
          NVL(SALARY + (SALARY * COMMISSION_PCT), SALARY) AS 월급,
          DEPARTMENT_ID
   FROM EMPLOYEES;
   
   
   --- *** 컬럼에 대해 별칭(ALIAS) 부여하기 *** ---
   SELECT EMPLOYEE_ID AS "사원번호"
        , FIRST_NAME "이름" -- AS는 생략가능
        , LAST_NAME 성    -- ""는 생략가능
        , SALARY "기본 급여"  -- 공백을 주려면 ""필요
        , COMMISSION_PCT 커미션퍼센티지
        , NVL(SALARY + (SALARY * COMMISSION_PCT), SALARY) 월급
        , NVL2( COMMISSION_PCT, SALARY + (SALARY * COMMISSION_PCT), SALARY) 월급2
        , DEPARTMENT_ID 부서번호
   FROM EMPLOYEES;
   
   
   ---------------- **** 비교연산자 **** --------------------
