package smart.kbdfile;
import java.io.*;

public class KbdFile {
	BufferedReader kbd; // 키보드 입력
	String saveName;	// 저장할 파일이름

	public KbdFile() {
		Reader r = new InputStreamReader(System.in);
		kbd = new BufferedReader(r);
	}
	public void readName() {
		System.out.print("저장할 파일이름을 입력하세요 : ");
		try {
			saveName = kbd.readLine();
			saveName = new String(saveName).trim();
		} catch(IOException e) {
			System.out.println("저장할 파일이름을 입력받는 중 에러가 발생했습니다.");
			e.printStackTrace();
		}
	}

	public void readSaveData() {
		String line = "";
		System.out.println("저장할 데이터를 입력하세요~종료(x,X)");
		try {
			File f = new File(saveName);
			PrintWriter save = new PrintWriter(f);

			while( (line = kbd.readLine()) != null ) {
				if( line.equals("x") || line.equals("X")) break;
				save.println(line);
			}
			save.flush();
			save.close();
			System.out.println(saveName+ " : 저장 성공!! ");
		} catch(IOException e) {
			System.out.println("저장할 데이터를 입력받는아 저장하는 중 에러가 발생했습니다.");
			e.printStackTrace();
		}
	}
	public void readFile() {
		System.out.println(saveName + " : 읽어오기");
		String line = "";
		try {
			File f = new File(saveName);
			FileReader fr = new FileReader(f);
			BufferedReader file = new BufferedReader(fr);
			while( (line = file.readLine()) != null ) {
				System.out.println(line);
			}
			file.close();
			fr.close();
		} catch(IOException e) {
			System.out.println("파일에서 데이터를 읽어오는 중 에러가 발생했습니다.");
			e.printStackTrace();
		}
	}
	public void closeStream() {
		try {
			kbd.close();
		} catch(IOException e) {
			System.out.println("키보드 스트림 닫기 중 에러가 발생했습니다.");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KbdFile kf = new KbdFile();
		kf.readName();
		kf.readSaveData();
		kf.readFile();
		kf.closeStream();
	}

}
