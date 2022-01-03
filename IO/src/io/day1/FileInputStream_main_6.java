package io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileInputStream_main_6 {

/*
    ※ Data Source (File, 키보드, 원격 컴퓨터)
	: 데이터의 근원
    
    ※ Data Destination (파일, 모니터, 프린터, 메모리)
    : 데이터가 최종적으로 도착하는 곳

    Data Sourceㅇ======>ㅇ 프로그램 ㅇ======>ㅇ Data Destination
               입력스트림           출력스트림
              InputStream       OutputStream          
   
      
      === c:\iotestdata\korea.txt 파일을 읽어서 그 내용을 모니터(콘솔화면)에 출력하는 예제 ===
                  
      1. 데이터소스  : 파일로 부터 읽어들임   (노드스트림: FileInputStream) 
      2. 데이터목적지 : 결과물을 모니터에 출력 (노드스트림: System.out)
      
      >>>> FileInputStream
         - Node 스트림(접속점이 파일인 입력스트림)
         - 1byte 기반 스트림.
        
      >>>>> System.out :
                    부모클래스가 추상클래스 OutputStream(기본 출력 스트림) 타입인 것으로서
                    접속점(빨대)이 콘솔화면(모니터)인 출력 스트림(PrintStream)이다.
                    
       -- Node(접속점)가 콘솔화면(모니터)인 출력스트림이다.
       -- 1byte 기반 스트림이다.
       -- 주요 메소드 : println(String str),
                     print(String str),
                     write(int b)      
               
*/		
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print(">> 읽을 파일의 이름(절대경로)을 입력하세요 => ");
		
		String fileName = sc.nextLine();
		// C:\iotestdata\korea.txt
		// 입력해준 파일명이 데이터소스가 된다.
		
		FileInputStream fist = null;
		
		try {
			fist = new FileInputStream(fileName);
			
			byte[] dataArr = new byte[10];
			// dataArr 변수의 용도는 빨대(System.in, 키보드)에서 흡입할때 단위크기를 10byte로 하는 것이다. 
			// 또한 dataArr의 용도는 빨대(System.in, 키보드)에서 1번 흡입할때 마다 흡입한 내용물을 저장하는 용도로 쓰인다. 
			
			int inputLength = 0;
			// inputLength 변수의 용도는 빨대(System.in, 키보드)에서 흡입한 실제 크기(길이)를 나타내는 용도임.
			
			int totalByte = 0; // byte수 누적용도 
			int cnt = 0;       // while문의 반복회수를 알기위한것	
			
			while( (inputLength = fist.read(dataArr)) != -1) {
				/*
				   fist.read(dataArr) 메소드는 해당 파일에서
				   데이터를 배열 dataArr 크기인 10byte 씩 잘라서 읽는데
				   실제 읽어온 byte 크기(int 타입)를 inputLength 에 넣어준다.
				   이어서 읽어온 내용물을 배열 dataArr 에 저장시킨다.
				   
				   만약에 파일의 내용물에서 읽어들일 데이터가 없다라면 -1 을 리턴시켜준다.
				   즉, 파일속의 내용물이 끝이 아니라면 계속해서 while{ } 부분을 실행해라는 말이다.
				*/
				
				System.out.write(dataArr, 0, inputLength); // 모니터(콘솔화면)에 출력
				System.out.flush();
				
				totalByte += inputLength;
				
				cnt++; // 반복회수
			}// end of while-------------------------
			
			System.out.println("\n=========================");
			System.out.println("총 " + totalByte + "byte");
			System.out.println("반복회수 " + cnt + "번 반복함");
			System.out.println("===========================");
			/*
			   총 57byte
			   반복회수 6번 반복함
			*/
			
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " 파일은 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fist != null)
					fist.close();
			} catch (IOException e) {}
		}
				
		sc.close();

	}// end of main(String[] args)--------------------

}
