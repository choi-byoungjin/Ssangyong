# 쌍용강북교육센터 자바국비지원교육

5번 출석체크 : 9시 11시 14시 16시 18시 

## 12월 06일 (1일차)

환경 : jdk 1.8 (8u311)
Java SE = standard Edition

현업에서도 java8을 가장 많이 쓴다. 다른 버전은 과금이 있기때문

수업스케줄
목표 : 웹프로그래머로 취업!!!
1. Java
2. Oracle
3. Java + Oracle ==> JDBC
4. HTML5, CSS, JavaScript, jQuery, BootStrap
5. JSP(Java Server Page) / Servlet ==> 쇼핑몰 만들기 수업
6. Semi Project (팀프로젝트) : JSP/Servlet 사용하여 개발 ==> 주제:쇼핑몰 ==> 실력상승단계!!!
7. Spring Framework
8. Final Project (팀프로젝트) : Spring Framwork 사용하여 개발 ==> 주제:자유 ==> 대부분 GroupWare, LMS(Learning Management System, 학사관리시스템)

도움말 사이트
https://docs.oracle.com/javase/8/docs/api/index.html

작업관리자 Java Update Scheduler 사용 안 함 설정 필요 - 버전 변경 방지

- 환경 변수 설정
  - CLASSPATH 점 하나는 class파일을 현재 디렉토리에 만드는 것을 말한다.
  - %JAVA_HOME%\bin = cmd에서 자바컴파일러 명령어 바로 쓸 수 있게 컴파일러의 경로 설정을 미리 해두는 것

javac.exe = 자바 컴파일러

cd = change directory

HelloExam.java파일 참조

- 컴파일이란
고급언어를 저급언어(기계어)로 바꿔주는 것
1. 고급언어(java파일)
2. ASCII 코드로 10진수로 변경
3. 10진수를 2진수(기계어)로 변경
4. class 파일 생성됨

java EE = enterprice edition = java + web

소스파일은 src
클래스파일은 bin


## 12월 07일 (2일차)

패키지명을 일일이 타이핑하지 않도록 import 한다.
같은 패키지에 있는 것은 import 할 필요가 없다.
member 클래스가 다른 패키지에 있을 경우 import가 필요하다.

non-static 변수 에러 - 인스턴스 변수로 선언된 것은 인스턴스 변수로 사용해야 한다.

- 맴버변수 => instance 변수와 static 변수를 합친 것을 멤버변수라고 부른다.  
instance 변수 및 static 변수는 초기화를 하지 않더라도 자동적으로 초기화가 되어진다.  
정수형인 데이터타입(byte, short, int, long)은 자동적으로 0으로 초기화 되고,  
실수형인 데이터타입(float, double)은 자동적으로 0.0으로 초기화 되고,  
문자형인 데이터타입(char)는 자동적으로 ' '으로 초기화가 되고,  
String을 포함한 클래스 타입은 자동적으로 null로 초기화가 된다.
  
- 지역변수는 반드시 초기화(어떤 변수에 처음부터 값을 부여해주는 것)를 해주어야 한다.  
지역변수는 { } 내에서만 사용되어지기 때문에 { }을 벗어나는 순간 지역변수는 자동적으로 메모리(RAM)에서 삭제가 되어진다.  

- 문자열과 문자열 사이의 +는 문자열 결합을 뜻하는 것이고,  
숫자와 숫자 사이의 + 는 더하기(plus)를 뜻하는 것이고,  
문자열과 숫자 사이의 + 는 문자열결합을 뜻하는 것이다.

- 인스턴스화 = 객체를 생성하는 것

- new 연산자로 class라는 설계도를 중심으로 물건을 하나 만들어 메모리에 올린다.

### 자료형

- 원시형 타입(Primitibe Type)
  - 정수형(byte, short, int, long)  
    - 자바에서 정수형의 기본타입은 int이다.  
    - 정수형의 값이 -2,147,483,648 ~ 2,147,483,647 범위를 벗어난 것(long)이라면 반드시 숫자 뒤에 소문자 l 또는 대문자 L을 붙여야 한다.
  
  - 실수형(float, double)  
    - 자바에서 실수형의 기본타입은 double이다.  
    - float 형태로 나타내기 위해서는 실수 뒤에 반드시 소문자 f 또는 대문자 F를 붙여야 한다.
  
  - 문자형(char)  
    - 자바는 유니코드 체계를 사용하므로 문자형 타입인 char는 2byte이며, 범위는 0~65535 이다.  
    - int(4byte) 아래의 크기인 byte(1byte), short(2byte), char(2byte) 타입이 
    사칙연산을 만나면 자동적으로 int 타입으로 자동 형변환이 발생된다.
  - boolean타입
    - 참(true) 또는 거짓(false)을 담아준다
    - 크기가 1byte이다.

- 참조형 타입(Reference Type)
  - 클래스 객체(==object ==instance) 타입
  - 메모리상에 저장되어진 객체의 메모리 주소를 참조하는 것이다.
  - 메모리상의 크기는 4byte 를 차지한다.

### 변수의 명명규칙
1. 변수명의 길이에는 제한이 없다.
2. 대,소문자의 구분이 있다.
3. 첫글자는 숫자는 안된다.
4. 특수문자는 '_' 와 '$'만 사용이 가능하다.
5. 예약어는 변수명으로 사용불가하다.
6. 필수사항은 아니지만 관례상 카멜표기법과 스네이크표기법을 따른다.

- 데이터 값이 숫자로만 이루어지더라도 맨 앞에 0이 올 수 있는 경우라면 String 타입으로 해야 한다.

### 생성자
- 생성자는 인스턴스(객체)화 할 때 사용되어지는 일종의 메소드라고 보면 된다.
- 파라미터가 없는 생성자를 기본생성자(default constructor)라고 부른다.
- 모든 클래스는 기본생성자를 명기하지 않으면 기본생성자가 없는 것이 아니라 생략된다.
- 파라미터가 있는 생성자의 주목적은 필드르 초기화시켜 주는 것이다.
- 파라미터가 있는 생성자를 만들면 자동적으로 생략되어져 있던 기본생성자가 삭제가 되어져 버린다.
- 지역변수와 필드명이 동일할 경우 지역변수명이 더 우선하므로 모두 지역변수명으로 되어져 버린다.
- this는 자기자신 클래스을 뜻하는 것이다.(대명사)


## 12월 08일 (3일차)

### 메소드

- 메소드는 반드시 타입이 있어야 한다.
- 메소드 생성시 void 는 리턴타입이 없는 타입이다.
- 메소드의 타입과 리턴 타입은 같아야 한다.

#### 메소드의 오버로딩(overloading)
1. 메소드의 이름은 같더라도 파라미터의 개수가 다르면 다른 메소드로 취급하므로 메소드 중복이 아니다.
2. 메소드의 이름이 같고, 파라미터의 개수도 같더라도 파라미터의 데이터타입의 순서가 다르면 다른 메소드로 취급하므로 메소드 중복이 아니다.
3. 메소드의 이름이 같고, 파라미터의 개수도 같고, 파라미터의 데이터타입의 순서도 같고, 단 return 타입은 다를 경우라도 메소드 중복이라고 본다.  
- 그러므로 메소드의 이름이 같지만 메소드가 중복이 안되려면 리턴타입은 필요가 없고 오로지 파라미터가 달라야 한다.

### 형변환

#### 자동 형변환(묵시적 형변환)
- 데이터타입의 크기가 작은 것에서 크기가 큰 쪽으로는 자동적으로 형변환이 발생된다.
- byte(1byte) --> short(2byte) --> int(4byte) --> long(8byte)
- float(4byte) --> double(8byte)
  - 정수타입은 실수타입으로 자동형변환이 발생한다.
- char(2byte)
  - char타입은 int형으로 자동형변환이 발생한다.

#### 강제 형변환(casting)
- 데이터타입의 크기가 큰 것을 작은 것으로 강제적으로 형변환 시키는 것을 말한다.
  - 크기가작은타입 = (크기가작은타입)크기가큰타입
    - 정수 = (정수)실수
  - 실수를 정수로 강제적으로 형변환하는 것  
  소수부는 무조건 절삭을 해버리고 정수만 나온다.
  
### 연산자

1. 산술연산자
- +, -, /, %, <<, >>, >>>

2. 증감연산자
- ++, --
  - 후위증감연산자(a++, b--) 는 다른 연산을 다 마친 이후에 1씩 증감한다.
  - 전위증감연산자(++a, --b) 는 맨먼저 1씩 증감을 마친 이후에 다른 연산을 한다.

3. bit별 not 연산자
- ~
  - 주어진 정수값을 32bit 형태의 2진수로 바꾸어서 나타내는데 0bit는 1bit로 바꾸고, 1bit는 0bit로 바꾸어 주는 것이다.
  - 첫번째 bit는 부호비트로 사용되어지는데  
  0 은 +임(양수)을 의미하고,  
  1 은 -임(음수)을 의미한다.
  
4. 논리 부정 연산자
- !
  - 참, 거짓

5. bit 연산자
- & (엠퍼센트), | (vertical line), ^
  - & (and 연산자) => 2개 bit 모두 1 일때만 1, 나머지는 0
  - | (or 연산자) => 2개 bit 중에서 적어도 1개가 1이면 1, 모두 0 이어야만 0
  - ^ (xor 연산자) => 2개 bit 중에서 서로 달라야만 1, 같으면 0

6. 논리연산자
- &, |, &&, ||
  - && 연산자는 F 가 나오는 순간 다음 연산은 생략한다. & 연산자는 F 가 나와도 모두 연산한다.

7. 비교연산자
- == 같다, != 같지않다.

8. 할당연산자 (연산 후 대입 연산자)
- +=, -=, *=, /=, %=

9. 삼항연산자
- 변수선언 = (조건식) ? 값1 : 값2
  - 변수를 선언하고 나서 값을 부여하고자 할 때 사용되어지는데  
  조건이 참이라면 변수에 값1을 대입해주고,  
  만약에 조건식이 거짓이라면 변수에 값2를 대입해준다.


## 12월 09일 (4일차)

- numberformatexception은 java.lang 패키지  
- InputMismatchException은 java.util 패키지  
- 중괄호 범위를 고려해서 변수를 선언해야 함  

- 문자열을 비교할 때는 ==, != 이 아닌 equals를 사용한다.  
  - 문자열1.equals(문자열2)
- 스위치문에는 byte, short, int, char, String 만 들어올 수 있다.  
- int 타입을 문자열로 형변환 시켜주는 방법
  - String str_num1 = Integer.toString(num1)
  - String str_num2 = String.valueOf(num2)

- main() 메소드내에서 return; 은 main()메소드에서 작업중인 것을 종료해라는 말이다.

### Scanner

- sc.nextLine(); 은 키보드로부터 입력받은 문장을 읽어들이는 것인데  
엔터(종결신호)까지 모두 읽어들인 후 스캐너 버퍼에 아무것도 남기지 않는다.

- sc.nextInt(); 는 정수를 읽어들이는 것인데 종결자가 공백 또는 엔터이다.  
종결자 앞까지 입력해준 정수를 읽어온다.  
sc.nextInt(); 를 사용하면 스캐너(sc)버퍼에는 종결자가 삭제되는 것이 아니라 그대로 남아있게 되므로 sc.nextLint();를 통해 남아있던 찌꺼기를 비우는 것이 필요하다.

- sc.nextDouble(); 는 실수를 읽어들이는 것이다.
- sc.next(); 는 단어를 읽어들이는 것이다.

- Integer.parseInt(inputStr); 은 문자열 inputStr 을 int로 바꾸어주는 것이다.


### Wrapper

- 기본자료형(원시형)은 데이터 저장 및 4칙연산에서만 사용하는 것이고,  
Wrapper 클래스는 데이터 저장 및 4칙연산 뿐만 아니라 아주 다양한 기능의 메소드가 제공되므로 다방면으로 사용되어진다.

- Boxing vs UnBoxing
  - Boxing(박싱, 포장을 하는것)
    - 기본자료형(boolean, byte, short, int, long, char, float, double)으로 되어진 변수를  
    객체타입인 Wrapper 클래스(Boolean, Byte, Short, Int, Long, Char, Float, Double)  
    타입의 객체로 만들어주는 것을 말한다.
  
  - UnBoxing(언박싱, 포장을 푸는 것)
    - Wrapper 클래로 되어져 있는 객체를 기본자료형으로 만들어주는 것을 말한다.


## 12월 10일 (5일차)

new로 선언할 경우 메모리 주소를 비교하고
new로 선언하지 않을 경우 값을 비교한다.


## 12월 13일 (6일차)

유효성 검사 - 데이터가 올바른지 아닌지 검사하는 것
