package my.day17.c.collection;

import java.util.ArrayList;
import java.util.List;

/*
   == ArrayList == 
   1. 출력시 저장된 순서대로 나온다.
   2. 중복된 데이터를 저장할 수 있다.
   3. 데이터를 읽어오는 속도는 ArrayList 가 LinkedList 보다 상대적으로 빠르다.
   4. 순차적으로 데이터를 추가/삭제하는 경우에는 ArrayList 가 LinkedList 보다 상대적으로 빠르다.
   5. 일반적인 데이터 추가/삭제는 데이터 중간 중간마다 발생하므로 이러한 경우에는 ArrayList 가 LinkedList 보다 상대적으로 느리다.
   6. 결과값은 ArrayList 를 사용하든지 LinkedList 를 사용하든지 동일한 결과값을 가진다.
   7. LinkedList 보다는 ArrayList 를 사용하도록 하자.
*/

public class ArrayList_main_2 {

	public static void main(String[] args) {
		
		// 1. Member 클래스의 객체만을 저장할 수 있는 ArrayList 객체 mbrList 를 생성하시오.
		List<Member> mbrList = new ArrayList<>();	// 메모리 주소는 mbrList가 가리킨다. // 어떤 클래스는 인터페이스로 받을 수 있다. == 다형성
		
		// 2. Member 클래스의 객체 6개를 생성하여 mbrList 에 저장하세요.
		mbrList.add(new Member("youjs","qwer1234$","유재석"));
	    mbrList.add(new Member("eom","qwer1234$","엄정화"));
	    mbrList.add(new Member("kanghd","qwer1234$","강호동"));
	    mbrList.add(new Member("leess","qwer1234$","이순신"));
	    mbrList.add(new Member("kimth","qwer1234$","김태희"));
	    mbrList.add(new Member("kangkc","qwer1234$","강감찬"));
	    
	    mbrList.add(new Member());
	    mbrList.add(new Member("leess","qwer1234$","이순신"));
	    mbrList.add(new Member("leemh","qwer1234$","이민호"));


	    // 3. mbrList 에 저장되어진 모든 회원들의 정보를 출력하도록 Member 클래스에 정의된 infoPrint() 메소드를 실행하세요.
	    //	  mbrList 에 저장되어진 Member 객체의 개수는 mbrList.size()로 알아온다.
	    for (int i = 0; i < mbrList.size(); i++) {
			mbrList.get(i).infoPrint();
		}
	    
	    // 또는
	    System.out.println("\n~~~~~~~~~~~~~~~~ 또는 ~~~~~~~~~~~~~~~~~~~~\n");
	    
	    for (Member mbr : mbrList) { // 객체 아무이름으로 지역변수 설정 : 콜렉션 이름
			 mbr.infoPrint();
		}
	    
	    
	    System.out.println("\n~~~~~~~~~~~~~~~~ [퀴즈1] ~~~~~~~~~~~~~~~~~~~~\n");
	    /*
	      [퀴즈1]
	      mbrList 에 저장되어진 Member 객체들 중에서
	      id 값이 "leess" 인 회원만 그 회원의 정보를 출력하세요.
	     */
	    boolean isFind = false;
	    String id = "leess";
	    for (int i = 0; i < mbrList.size(); i++) {
			if(id.equals(mbrList.get(i).getId())) {
				mbrList.get(i).infoPrint();
				isFind = true;
			}
			
		}
	    if(!isFind) {
	    	System.out.println(id+"이라는 ID를 가지는 회원은 없습니다.");
	    }
	    
	    System.out.println("\n~~~~~~~~~~~~~~~~또는~~~~~~~~~~~~~~~~~\n");
	    
	    isFind = false;
	    id = "leess";
	                                         
	    for (Member mbr : mbrList) {
	    	if("leess".equals(mbr.getId())) {
	    		mbr.infoPrint();
	    		isFind = true;
	    	}
	    }
	    
	    if(!isFind) {
	    	System.out.println(id+"이라는 ID를 가지는 회원은 없습니다.");
	    }
	    
	    System.out.println("\n~~~~~~~~~~~~~~~~ [퀴즈2] ~~~~~~~~~~~~~~~~~~~~\n");
	    /*
	      [퀴즈2]
	      mbrList 에 저장되어진 Member 객체들 중에서
	      name 이 "이" 씨인 화원만 그 회원의 정보를 출력하세요
	     */
//	    for (Member mbr : mbrList) {
//	    	if(mbr.getName().charAt(0) == '이')
//	    		mbr.infoPrint();
//	    }
//	    
	    isFind = false;
	    String first_name= "이";
	    
	    for (int i = 0; i < mbrList.size(); i++) {
			if( mbrList.get(i).getName() != null && mbrList.get(i).getName().startsWith(first_name)) {
				mbrList.get(i).infoPrint();
				isFind = true;
			}
			
		}
	    if(!isFind) {
	    	System.out.println(first_name +" 으로 시작하는 name을 가지는 회원은 없습니다.");
	    }
	    

	    System.out.println("\n~~~~~~~~~~~~~~~~또는~~~~~~~~~~~~~~~~~\n");

	    isFind = false;
	    first_name = "이";
	                                         
	    for (Member mbr : mbrList) {
	    	if(mbr.getName() != null && 
	    	   mbr.getName().startsWith(first_name)) {
	    		mbr.infoPrint();
	    		isFind = true;
	    	}
	    }
	    
	    if(!isFind) {
	    	System.out.println(id+"이라는 ID를 가지는 회원은 없습니다.");
	    }
	    
	    
	    
	    // *** ArrayList 타입인 mbrList 에 새로운 Member 객체 추가시 
	    //     특정  index(위치)에 들어가도록 할 수 있다.
	    System.out.println("\n ~~~ mbrList 에 새로운 Member 객체 추가하기 ~~~ ");
	   
	    mbrList.add(new Member("seolh", "qwer1234$", "설현"));
	    // index 값이 없으면 mbrList 의 맨 뒤에 추가된다.
	    
	    mbrList.add(3, new Member("chaew", "qwer1234$", "차은우"));
	    //			3이 특정 인덱스이다.
	    // 유재석(0) 엄정화(1) 강호동(2) 이순신(3) 으로 되어있었는데
	    // 유재석(0) 엄정화(1) 강호동(2) 차은우(3) 이순신(4) 으로 되어진다. 
	    
	    for (Member mbr : mbrList) {
			 mbr.infoPrint();
		}
	    
	    
	    
	    // *** ArrayList 타입인 mbrList 에 저장되어진 Member 객체 삭제하기 *** 
	    System.out.println("\n ~~~ ArrayList 타입인 mbrList 에 저장되어진 Member 객체 삭제하기 ~~~ ");
	    
	    System.out.println(">> 삭제하기 전 mbrList.size() => "+ mbrList.size() +" <<");
	    // >> 삭제하기 전 mbrList.size() => 11 <<

	    mbrList.remove(3); // mbrList.remove(삭제할 Member 객체의 인덱스번호);
	    System.out.println(">> 삭제 후 mbrList.size() => "+ mbrList.size() +" <<");
	    
	    for (Member mbr : mbrList) {
			 mbr.infoPrint();
		}
	    
	    
	    System.out.println("\n~~~~~~~~ [퀴즈3] ~~~~~~~~~~~~\n");
        /*
          [퀴즈3]
          mbrList 에 저장되어진 Member 객체들 중에서
          name 이  "이" 씨인 회원들은 삭제하고
          삭제한 후 mbrList 에 저장되어진 Member 객체들의 정보를 출력하세요
        */
	    
	    isFind = false;
	    first_name= "이";
	    
	    for (int i = mbrList.size()-1; i >= 0 ; i--) {
			if( mbrList.get(i).getName() != null && 
				mbrList.get(i).getName().startsWith(first_name)) {
				mbrList.remove(i);
				isFind = true;
			}
			else {
				isFind = false;
			}
		}
	    if(!isFind) {
	    	System.out.println(first_name +" 으로 시작하는 name을 가지는 회원은 없습니다.");
	    }
	    for (Member mbr : mbrList) {
			 mbr.infoPrint();
		}
	    
	}// end of main(String[] args)-----------------------------------------------------

}

