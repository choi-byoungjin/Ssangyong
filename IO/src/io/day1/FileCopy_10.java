package io.day1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileCopy_10 {

/*
    === 파일로 부터 입력받은 것을 파일에 기록(출력)하기로 한다. ===
    
    1. 데이터 소스  : 파일로 부터 입력받음     [노드스트림 : FileInputStream]
    2. 데이터 목적지 : 결과를 특정파일에 출력함  [노드스트림 : FileOutputStream]
*/
	
	public static void main(String[] args) {
		
		 Scanner sc = new Scanner(System.in);
		 
		 System.out.print(">> 복사할 원본파일명(절대경로) 입력 => "); 
		 String srcFileName = sc.nextLine();
		 
		 System.out.print(">> 목적 파일명(절대경로) 입력 => "); 
		 String targetFileName = sc.nextLine();
		 
		 int totalByte = 0;  // byte수 누적용도
		 int cnt = 0;        // 반복회수
		 	 
		 
		 // === 우리는 파일원본크기가 2Mb 이내는 복사를 허락해주지만 2Mb 초과한 경우에는 복사를 불허하도록 만들어 보겠다. === // 
		 
		 // 소스 File 객체 생성하기 
		 // String 타입인 srcFileName이 실제 File 클래스의 객체로 만들어야만 파일의 크기(용량)를 알아와서 용량제한을 걸수 있게 된다.
		 
		 if(srcFileName != null && targetFileName != null) {
		 
			try {
				 File srcFile = new File(srcFileName);
				 long srcFileSize = srcFile.length();  // 파일의 크기를 알려준다.
				 System.out.println(">> 원본 파일 크기 : " + srcFileSize + "byte");
				 
				 long maxSize = 1024*1024*2; // 2Mb
				 
				 if(srcFileSize > maxSize) {
					 // 원본 파일의 크기가 2Mb 를 초과한 경우
					 System.out.println(">> [경고] 원본 파일의 크기가 2Mb 를 초과했으므로 복사할 수 없습니다. <<"); 
					 
					 sc.close();
					 return; // main()메소드 종료
				 }
				 
				 else {
					// 원본 파일의 크기가 2Mb 를 초과하지 않은 경우
					
					 FileInputStream fist = new FileInputStream(srcFile);
					 // FileInputStream 생성 : 접속점이 파일인 것으로 특정 파일에 빨대를 꽂아 파일의 내용물을 1byte 기반으로 빨아들이는 입력노드 스트림이다.
					 
					 File targetFile = new File(targetFileName);
					 FileOutputStream fost = new FileOutputStream(targetFile);
					 // FileOutputStream 생성 : 접속점이 파일인 것으로 특정 파일에 빨대를 꽂아 파일의 내용물을 1byte 기반으로 기록해주는(써주는) 출력노드 스트림이다.
					 
					 byte[] dataArr = new byte[4096];  // 4096 byte == 4kb
					 int inputLength = 0;
					 
					 while( (inputLength = fist.read(dataArr)) != -1 ) {
						 fost.write(dataArr, 0, inputLength); // 파일에 쓰기
						 fost.flush();
						 
						 totalByte += inputLength;
						 cnt++;
					 }// end of while----------------------
					 
					 fost.close();
					 fist.close(); 
				 }
			 
			 } catch(FileNotFoundException e) {
				 e.printStackTrace();
			 } catch(IOException e) {
				 e.printStackTrace();
			 } 
			
			 System.out.println(targetFileName + "에 쓰기 완료!! " + totalByte + "byte 복사됨.");
			 System.out.println("반복회수 : " + cnt + "번 반복함.");
			
		 } // end of if-------------------
		 
		 else {
			 // srcFileName 이 null 인 경우 
			
			 System.out.println(">> 원본소스파일에 문제가 있어서 복사가 불가합니다. <<");
		 
		 } // end of else------------------
		 
		 sc.close();

	}// end of main(String[] args)-------------------------

}
