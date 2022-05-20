package sihum.model;

public class Test_round_VO {

	private String fk_subject_no; // 과목번호참조
	private String test_round;    // 과목회차
	private String test_day;      // 시험일자
	
	Subject_VO subject;
	
	public String getFk_subject_no() {
		return fk_subject_no;
	}
	
	public void setFk_subject_no(String fk_subject_no) {
		this.fk_subject_no = fk_subject_no;
	}
	
	public String getTest_round() {
		return test_round;
	}
	
	public void setTest_round(String test_round) {
		this.test_round = test_round;
	}
	
	public String getTest_day() {
		return test_day;
	}
	
	public void setTest_day(String test_day) {
		this.test_day = test_day;
	}
	
	public Subject_VO getSubject() {
		return subject;
	}

	public void setSubject(Subject_VO subject) {
		this.subject = subject;
	}
	
}
