package smart_java.projectlotto;
import java.util.*;

public class Lotto {
	Vector<Integer> lotto;
	
	public Lotto() {
		lotto = new Vector<Integer>();
	}
	public void generate() {
		Random r = new Random();
		while(lotto.size()<6) {
			int number = r.nextInt(46);
			if(number != 0 && !lotto.contains(number)) {
				lotto.add(number);
			}
		}
		Collections.sort(lotto);
	}
	public String getNumber() {
		String msg = "";
		for(Integer num : lotto) 
			msg += "["+num+"]";
		return msg;
	}
	public static String getDate() {
		Calendar now = Calendar.getInstance();
		String msg = "";
		msg += now.get(Calendar.YEAR)+"/";
		msg += now.get(Calendar.MONTH)+1+"/";
		msg += now.get(Calendar.DATE)+"(";
		
		msg += now.get(Calendar.HOUR_OF_DAY)+":";
		msg += now.get(Calendar.MINUTE)+":";
		msg += now.get(Calendar.SECOND)+")";
		return msg;
	}
	
}

