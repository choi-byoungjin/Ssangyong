package smart_java.byteio;
import java.io.*;
import java.util.*;

public class ByteIO {

	public void ios() {  // InputStream/OutputStream
		System.out.println("[InputStrem/OutputStream 실습]");
		int input=0;
		int count=0;
		InputStream myIn = System.in;  // 표준 입력
		OutputStream myOut = System.out;  // 표준 출력
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
	public void fios() {  // FileInputStream/FileOutputStream
		System.out.println("[File 실습]");
		Scanner s = new Scanner(System.in); 
		System.out.print("File Name => ");
		String fname = s.nextLine();
		fname = new String(fname).trim();
		File file = new File(fname);
		fInfo(file);
		s.close();  // Scanner Close
		
		System.out.println("[FileInputStrem/FileOutputStream 실습 : 파일 복사 프로그램]");
		File source = file;
		File target = new File("aaa.txt");
		int data = 0;
		int size = 0;
		try {
			FileInputStream fis = new FileInputStream(source);
			FileOutputStream fos = new FileOutputStream(target);
			while( (data=fis.read()) != -1 ) {
				fos.write(data);
				size++;
			}
			fis.close();
			fos.close();
		} catch( FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch( Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Copy Size : " +size +"byte");

	}
	public void fInfo(File file) { // File
		System.out.println("Path = "+file.getAbsolutePath());
		System.out.println("Date = "+new Date(file.lastModified()));
		System.out.println("Size = "+file.length());
		System.out.println("Read = "+file.canRead());
		System.out.println("Write= "+file.canWrite());
	}
	public void bdio() {
		System.out.println("[BufferedInputStrem/BufferedOutputStream 실습 : 파일 복사 프로그램]");
		byte[] buffer = new byte[256];  // 읽어올 byte 버퍼 배열
		int ret=0;
		int count=0;
		File source = new File(".project");
		File target = new File("bbb.txt");

		try {
			FileInputStream fis = new FileInputStream(source);
			FileOutputStream fos = new FileOutputStream(target);
				
			BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos,256);

			while( (ret=bis.read(buffer)) != -1 ) {
				bos.write(buffer,0,ret);  //ret : 읽어온 바이트수
				bos.flush();
				count++;
	            System.out.println("read("+ret+","+count+"),available="+bis.available());
			}
			fis.close();
			fos.close();
		} catch( Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void dio() {	// DataInputStrem/DataOutputStream
		System.out.println("[DataInputStrem/DataOutputStream 실습]");
		try {
			FileOutputStream fos = new FileOutputStream("ccc.txt");
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeBoolean(true);
			dos.writeChar('a');
			dos.writeByte(100);
			dos.writeInt(2000);
			dos.writeLong(30000L);
			dos.writeFloat(3.14f);
			dos.writeDouble(3.14);
			dos.writeUTF("Hello");
			dos.close();
			fos.close();
			FileInputStream fis = new FileInputStream("ccc.txt");
			DataInputStream dis = new DataInputStream(fis);
			System.out.println("boolean="+dis.readBoolean());
			System.out.println("char="+dis.readChar());
			System.out.println("byte="+dis.readByte());
			System.out.println("int="+dis.readInt());
			System.out.println("long="+dis.readLong());
			System.out.println("float="+dis.readFloat());
			System.out.println("double="+dis.readDouble());
			System.out.println("UTF="+dis.readUTF());
			fis.close();
			dis.close();
		} catch( Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void oio() {		// ObjectInputStrem/ObjectOutputStream
		System.out.println("[ObjectInputStrem/ObjectOutputStream 실습]");
		try {
			FileOutputStream fos = new FileOutputStream("ddd.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			String data1 = "Java";
			Integer data2 = 100;
			Double data3 = 100.23;
			oos.writeObject(data1);
			oos.writeObject(data2);
			oos.writeObject(data3);
			oos.close();
			fos.close();
				
			FileInputStream fis = new FileInputStream("ddd.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			data1 = (String)ois.readObject();
			data2 = (Integer)ois.readObject();
			data3 = (Double)ois.readObject();
			ois.close();
			fis.close();
				
			System.out.println("data1 = "+data1);
			System.out.println("data2 = "+data2);
			System.out.println("data3 = "+data3);
		} catch( FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch( Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void pStream() {  	// PrintStrem
		System.out.println("[PrintStrem 실습]");
		try {
			PrintStream ps = new PrintStream("eee.txt");
			ps.println('a');
			ps.println(10);
			ps.println(3.14f);
			ps.println(3.14);
			ps.println("Hello");
			ps.close();
		} catch( Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		ByteIO bio = new ByteIO();
		bio.ios();
		//bio.fios();
		//bio.bdio();
		//bio.dio();
		//bio.oio();
		//bio.pStream();
	}

}
