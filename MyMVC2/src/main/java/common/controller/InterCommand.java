package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterCommand {

	void execute(HttpServletRequest request, HttpServletResponse response) throws Exception; // command properties에 만들어질 value는 이 메소드 재정의(Override)를 하게 만든다.
	
}
