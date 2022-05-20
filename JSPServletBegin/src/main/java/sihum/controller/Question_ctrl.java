package sihum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sihum/question.do")
public class Question_ctrl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		// !!!! ===  View 단(.jsp 파일) 지정하기 === !!!!
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sihum/question.jsp"); 
		dispatcher.forward(request, response);
		/*
		   웹브라우저 상에서 URL 주소는 그대로 /sihum/question.do 인데 
		   웹브라우저 상에 보여지는 내용물은 /WEB-INF/sihum/question.jsp 의 내용이 보여진다. 
		*/
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		doGet(request, response);
	}
	
}
