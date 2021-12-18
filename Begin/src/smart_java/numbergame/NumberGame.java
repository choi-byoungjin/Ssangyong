package smart_java.numbergame;
import java.util.*;

public class NumberGame {

	int random_number;
	int count;
	
	public NumberGame() {
		Random r = new Random();
		random_number = r.nextInt(100) + 1; // 0부터 99까지이므로 1을 더하여 1부터 100까지
		count = 0;
	}
	int findNumber(int num) {
		return num - random_number;
	}
	void incrementCount() {
		count++;
	}
	int getcount() {
		return count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kbd = new Scanner(System.in);
		NumberGame ng = new NumberGame();
		
		do {
			System.out.print("숫자를 입력하세요! : ");
			String value = kbd.nextLine();
			int int_value = Integer.parseInt(value);  // 문자열 숫자를 숫자로 변환
			ng.incrementCount();
			
			int result = ng.findNumber(int_value);
			if( result < 0 ) {
				System.out.println(int_value+" 보다 커야 합니다! ("+ ng.getcount()+")");
			} else if( result > 0 ) {
				System.out.println(int_value+" 보다 작아야 합니다! ("+ ng.getcount()+")");
			} else if( result == 0 ) {
				System.out.println(ng.getcount()+"번 만에 맞추셨습니다.");
				break;
			}
		} while(true);
		
	}

	
}
