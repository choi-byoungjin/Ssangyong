package smart_java.chario;
import java.io.*;

public class CharIO {

	public void iorw() {  // InputStreamReader/OutputStreamWriter
		System.out.println("[InputStremReader/OutputStreamWriter 실습]");
		int input=0;
		int count=0;
		Reader myIn = new InputStreamReader(System.in);  // Reader는 추상 클래스
		Writer myOut = new OutputStreamWriter(System.out);  // Writer는 추상 클래스
		try {
			while((input=myIn.read()) != -1) {  
				if (input=='X' || input=='x') break;
				count++;
				myOut.write(input);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("count = "+count);
		try {
			myIn.close();  
			myOut.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void frw() {  // FileReader/FileWriter
		System.out.println("[FileReader/FileWriter 실습 : 파일 복사 프로그램]");
		File source = new File(".project");;
		File target = new File("111.txt");
		int data = 0;
		int size = 0;
		try {
			FileReader fr = new FileReader(source);
			FileWriter fw = new FileWriter(target);
			while( (data=fr.read()) != -1 ) {
				fw.write(data);
				size++;
			}
			fr.close();
			fw.close();
		} catch( FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch( Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Copy Size : " +size +"byte");

	}
	public void brw() {  // BufferedReader/BufferedWriter
		System.out.println("[BufferedReader/BufferedWriter 실습 : 파일 복사 프로그램]");
		File source = new File(".project");
		File target = new File("222.txt");
		String ret = null;
		int count = 0;
		try {
			FileReader fr = new FileReader(source);
			FileWriter fw = new FileWriter(target);
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			while( (ret=br.readLine()) != null ) {
				bw.write(ret);
				bw.newLine();
				bw.flush();
				count++;
			}
			System.out.println("Line="+count);
			br.close();
			bw.close();
			fr.close();
			fw.close();
			
		} catch( Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void pWriter() {  	// PrintWriter
		System.out.println("[PrintWriter 실습]");
		try {
			PrintWriter pw = new PrintWriter("333.txt");
			pw.println("Java");
			pw.println("Program");
			pw.println(1000);
			pw.flush();
			pw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		CharIO cio = new CharIO();
		cio.iorw();
		//cio.frw();
		//cio.brw();
		//cio.pWriter();
	}

}
