package io.day2.b;

import java.io.*;

public class OutputStreamWriter_main_5 {

/*
    소스 - "C:/iotestdata/오늘의 날씨.txt"
    FileReader ==> 2byte 기반
    
    브릿지 스트림 OutputStreamWriter => 1byte 를 2byte 로 변경 
    목적지 - 출력노드 스트림 System.out => 1byte기반
*/
	public static void main(String[] args) {

		try {
			String srcFileName = "C:/iotestdata/오늘의 날씨.txt"; // 해당파일의 글자를 모니터에 출력해준다.
			FileReader fr = new FileReader(srcFileName);
			// fr 는 2byte 기반임.
			
			OutputStreamWriter ostWriter = new OutputStreamWriter(System.out); 
			// 모니터에 출력되는 것은 1byte 기반인데 이것은 2byte 기반으로 변경해주는 것이다.
	
			int input = 0;
			
			while( (input = fr.read()) != -1) { // Syetem.in.read()는 1바이트 기반이다.
				ostWriter.write(input);
				ostWriter.flush();
			}// end of while------------------------------------

			ostWriter.close();
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}// end of main(String[] args)------------------------------------
/*
 	실행은 아래와 같이 명령프롬프트에서 한다.
 	
 	C:\Users\choib\git\Ssangyong\IO\bin>java io.day2.b.OutputStreamWriter_main_5
	오늘은 아침부터 춥다가 오후에는 조금 풀리겠습니다. 저녁에는 눈이 온대요.ㅊ
 */
}
