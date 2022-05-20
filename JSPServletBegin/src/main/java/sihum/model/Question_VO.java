package sihum.model;

public class Question_VO {

	private String question_no;    // 시험문제고유번호 시퀀스
	private String fk_subject_no;  // 과목번호 참조 
	private String fk_test_round;  // 과목회차 참조
	private String question;       // 시험문제
	private String correct;        // 정답번호
	
	Subject_VO subject; 
	Test_round_VO  test_round;
	
	public String getQuestion_no() {
		return question_no;
	}
	
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}
	
	public String getFk_subject_no() {
		return fk_subject_no;
	}
	
	public void setFk_subject_no(String fk_subject_no) {
		this.fk_subject_no = fk_subject_no;
	}
	
	public String getFk_test_round() {
		return fk_test_round;
	}
	
	public void setFk_test_round(String fk_test_round) {
		this.fk_test_round = fk_test_round;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getCorrect() {
		return correct;
	}
	
	public void setCorrect(String correct) {
		this.correct = correct;
	}
	
	public Subject_VO getSubject() {
		return subject;
	}
	
	public void setSubject(Subject_VO subject) {
		this.subject = subject;
	}
	
	public Test_round_VO getTest_round() {
		return test_round;
	}
	
	public void setTest_round(Test_round_VO test_round) {
		this.test_round = test_round;
	}
	
}
