package io.util;

import java.io.*;

public class FileManager {

	public static String reading(String fileName) {
		
		try {
			// 해당 파일에 노드 연결 생성(빨대꽂기)
			FileReader fr = new FileReader(fileName);
			
			int data = 0;
			StringBuilder sb = new StringBuilder();
			
			do {
				data = fr.read(); // fr 이라는 파일로 부터 글자(char)1개(2byte)씩 읽어들임.
				                  // fr 이라는 파일로 부터 읽어들인 글자(char)가 없으면 -1 을 리턴시켜줌. 
				
				if(data != -1) 
				// 파일내용물에서 읽어온것이 존재하는 경우 
					sb.append((char)data);
				else 
				// 파일내용물에서 더이상 읽어올것이 없는 경우 
					break;
				
			} while (true);
			// end of do~while---------------------
			
			fr.close(); // 빨대제거
			
			return sb.toString();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			return fileName + " 이라는 파일은 없습니다!!";
		} catch(IOException e) {
			e.printStackTrace();
			return fileName + " 이 손상되었습니다!!";
		}
		
	}// end of public static String reading(String fileName)---------
	
	
	public static String reading_2(String fileName) throws FileNotFoundException, IOException {
		
		// 해당 파일에 노드 연결 생성(빨대꽂기)
		FileReader fr = new FileReader(fileName);
		
		char[] dataArr = new char[10];
		int dataLength = 0;
		
		StringBuilder sb = new StringBuilder();
		
		do {
			dataLength = fr.read(dataArr);
			// fr 파일로 부터 dataArr 배열의 크기인 글자(char)10개씩 읽어들임.
			// 읽어들인 글자는 char[] 타입의 배열인 dataArr 에 저장시킨후
			// 읽어들인 글자수(길이)는 dataLength 에 저장시킨다.
			// 그런데 fr 파일이 손상되었을시 IOException 이 발생된다. 
			
			if(dataLength != -1) {
			   // 파일내용물에서 읽어온것이 존재하는 경우
			   String str = new String(dataArr, 0, dataLength);
			   // new String(dataArr, 0, dataLength) 은
			   // char[] 타입의 배열인 dataArr 에서 0번째 인덱스 글자부터 
			   // dataLength 갯수만큼 뽑아서 String 타입으로 만든다는 말이다.
				
			   sb.append(str);
			}	
			else 
			// 파일내용물에서 더이상 읽어올것이 없는 경우 
				break;
			
		} while (true);
		// end of do~while---------------------
		
		fr.close(); // 빨대제거
		
		return sb.toString();
		
	}// end of public static String reading_2(String fileName)--------- 
	
	
	public static void charFileCopy(String srcFileName, String targetFileName) throws FileNotFoundException, IOException { // 메소드를 처리하는 쪽에서 예외처리 해라
		
		// 해당 소스파일에 노드 연결 생성(빨대꽂기)
		FileReader fr = new FileReader(srcFileName);
		
		// 해당 타겟파일에 노드 연결 생성(빨대꽂기)
		FileWriter fw = new FileWriter(targetFileName);
		
		char[] dataArr = new char[10];
		int dataLength = 0;
		
		do {
			dataLength = fr.read(dataArr); 
			// fr 파일로 부터 글자(char)10개씩 읽어들임.
            // 읽어들인 글자는 char[] 타입의 배열인 dataArr 에 저장시킨후
            // 읽어들인 글자수(길이)는 dataLength 에 저장시킨다.
            // 그런데 fr 파일이 손상되었을시 IOException 이 발생된다.
			
			if(dataLength != -1) {
				fw.write(dataArr, 0, dataLength); // 0번째부터 실제 읽어온 길이만큼
				fw.flush();
			}
			
			else {
				break;
			}
			
		} while (true);
		// end of do~while----------------------------------------------------------------------
		
		fw.close(); // 빨대제거
		fr.close(); // 빨대제거
		
	}// end of public static void charFileCopy(String srcFileName, String targetFileName)
	
	   
	
	
	
}
