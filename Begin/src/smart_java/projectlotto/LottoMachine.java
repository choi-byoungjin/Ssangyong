package smart_java.projectlotto;
import java.util.*;

public class LottoMachine {
	ArrayList<Lotto> list;
	
	public LottoMachine() {
		list = new ArrayList<Lotto>();
	}
	public void menu() {
		Scanner s = new Scanner(System.in);
		int game = 0;
		do {
			System.out.print("몇 게임을 할까요?(0:종료) : ");
			String input = s.nextLine();
			game = Integer.parseInt(input);
			if( game != 0 ) {
				list.clear();
				for(int i=0; i<game; i++) {
					Lotto lotto = new Lotto();
					lotto.generate();
					list.add(lotto);
				}
				System.out.println("Date : "+Lotto.getDate());
				for(Lotto lotto : list) {
					System.out.println(lotto.getNumber());
				}
				System.out.println();
			}
		} while( game != 0 );
		s.close();
	}
	
	public static void main(String[] args) {
		System.out.println("[로또 프로그램]");
		
		LottoMachine lm = new LottoMachine();
		lm.menu();
	}

}
