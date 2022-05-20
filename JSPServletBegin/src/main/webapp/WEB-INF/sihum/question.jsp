<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
    //     /JSPServletBegin 
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타이머만들기 및 객체배열을 사용한 객관식문제 만들기</title>
<style type="text/css">

	div#timer {
	/* 	border: solid 1px gray; */
		padding: 30px 0 0 20px;
		color: blue;
		font-size: 20pt;
	} 
	
	div#quiz_display {
		margin-top: 30px;
		padding-left: 20px; 
	}
	
	li {
		line-height: 30px;
	}
	
	div.ox { 
		font-size: 10pt;
		font-weight: bold;
		padding-bottom: 20px;
		margin-top: -10px;
	}

</style>

<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
	
    $.ajax({
			url:"<%= ctxPath%>/sihum/question_json.do", 
		//	type:"get",       // 생략하면 get 임. type:"post",  주의할것은 method 가 아니라 type 이라는 것임!!
			dataType:"json",  // 주의할 사항은 datatype 이 아니라 dataType 이다.
		//	async:false,      // 동기처리
		//  async:true,       // 비동기처리(기본값임) 
    		success: function(json) { // 콜백함수 
		    	           // json => ajax 요청에 의해 url(/JSPServletBegin/sihum/question_json.do)로 부터 리턴받은 데이터이다. 
		    	console.log(JSON.stringify(json)); 
		    	// JSON.stringify(자바스크립트객체); 은 자바스크립트객체를 JSON 문자열로 변환해주는 것이다. 
		    	  
		    	////////////////////////////////////////////////////////////////////////
		    	let time = 600;  // 타이머 시간을 10분으로 지정함
	  
				// 타이머 함수 만들기
				const timer = function() {
					 
					let minute = "";
					let second = "";
					  
					minute = parseInt(time / 60); // 소수부는 없애 버리고 정수만 가져오는 것이다.
					if(minute < 10) {
					   minute = "0" + minute; 
					}
					  
					second = parseInt(time % 60);
					if(second < 10) {
					   second = "0" + second;
					}
						  		  
					const html = minute+":"+second;
					$("div#timer").html(html);
						  
					time--;
				    if(time < 0) {
					   alert("시험시간 종료!!\n자동으로 제출됩니다.");
							  
					   clearInterval(setTimer); // 타이머 삭제하기
					                            // setTimer 는 중단해야할 setInterval 함수를 가리키는 것이다.
					   $("button#btnSubmit").prop("disabled",true); // "제출하기" 버튼 비활성화                          
						 //	check(); // 채점하는 함수 호출                         
					}
			    };// end of const timer = function() {}-------------------------
	  
				timer(); // 타이머 함수 호출
   
		 		// setInterval(function(){ timer(); }, 1000); // 1초 마다 주기적으로 타이머 함수 호출하도록 지정
		 		// 또는
		    	const setTimer = setInterval(timer, 1000);  // 1초 마다 주기적으로 타이머 함수 호출하도록 지정   
		    	//////////////////////////////////////////////////////////////////////////////////////
		    	
		        // 퀴즈문항을 html 로 만들기 
		        let html = "";
		        
		        json.forEach( (item, index) => {
		        // json => ajax 요청에 의해 url(/JSPServletBegin/sihum/question_json.do)로 부터 리턴받은 데이터이다. 
		          html += "<p id='q"+index+"'>"+item.question+"</p>";
		      	  
		      	  html += "<ol>";
		      	  
		      	  for(let key in item.answers) { // 어떤 객체의 속성(키)들을 모두 불러올때는 for문에서 of 가 아니라 in 을 사용한다. 
		      		  html += "<li>"+item.answers[key]+"<input type='radio' name='question"+index+"' value='"+key+"' /></li>";
		      		              // 객체명.속성명 은 속성명에는 변수가 사용될 수 없다.
		      		              // 객체명[속성명] 은 속성명에 변수가 사용될 수 있다.
		      		              // ${item.answers[key]} 는 "부산" 과 같은 것을 말하는 것이다.
		      		              
		      		              // 라디오는 반드시 name 값이 동일해야 한다.
		      		              // value 값은 item.answers 의 속성명인 1 2 3 4 로 되어진다.
		      	  }
		      	  
		      	  html += "</ol>";
		      	  html += "<div class='ox' id='ox"+index+"'></div>";   // 퀴즈문항에 대해 정답인지 틀린것인지를 보여줄 장소 
		        } );
		
		        $("div#quiz_display").html(html); // 퀴즈문항을 보여줄 장소 
		        
		        
		        // 채점하는 함수 만들기 
		        const check = function() {
		      	  
		      	  let answerCount = 0; // 정답개수 누적용 
		      	  
		      	  json.forEach( (item, index) => {
		      		  console.log((index+1)+"번 문제 정답은 " + item.correct);
		      		  
		      		  // 퀴즈문항 뒤에 정답번호 공개하기
		      		  $("p#q"+index).append("&nbsp;<span style='color:blue'>"+item.correct+"</span>")
		      		  // 선택자.append("내용") 은 선택자 바로 뒤에 "내용"을 덧붙여 주는 것이다.
		      		  
		      		//  let radio_length = document.getElementsByName(`question${index}`).length;
		      		  let radio_length = $("input:radio[name='question"+index+"']").length;
		      		  console.log((index+1)+"번 문제 라디오 개수는 " + radio_length);
		      		  
		      		  let isCheckAnswer = false; // 라디오의 선택유무 검사용
		      		  let radio_checked_length =  $("input:radio[name='question"+index+"']:checked").length;
		      		  if (radio_checked_length > 0) {isCheckAnswer = true;}
		      		  console.log("답을 선택하셨나요? : " + isCheckAnswer);
		      		  
		      		  let userAnswer;
		      		  if(isCheckAnswer) { // 답을 선택한 경우
		      			  userAnswer = $("input:radio[name='question"+index+"']:checked").val();
		      			  
		      		  }
		      		  else { // 답을 선택하지 않은 경우 
		      			  userAnswer = "선택안함";
		      		  }
		      		  console.log("사용자가 선택한 답번호 : " + userAnswer);
		      		  console.log("");
		      		  
		      		  if( userAnswer == item.correct ) {
		      			  answerCount++;
		      			  $("div#ox"+index).html("<span style='color:blue'>정답</span>");
		      		  }  
		      		  else {
		      			  $("div#ox"+index).html("<span style='color:red'>틀림</span>");
		      		  }
		      		  
		      	  } );// end of json.forEach( (item, index) )------------------------------------------ 
		      	  		      	  
		      	  $("div#score").html("<span style='font-weight:bold'>정답개수 : "+answerCount+"</span>");
		        };// end of const check = function() {}----------------------------------
		        
		        // 제출하기 버튼 클릭시 이벤트 처리하기 
		        $("button#btnSubmit").click(function(){
		        	if(confirm("정말로 제출하시겠습니까?")) {
			  			 alert("제출이 완료되었습니다.");  
			  			 clearInterval(setTimer);  // 타이머 삭제하기
			  			 $("div#timer").html("00:00");
			  		     $("button#btnSubmit").prop("disabled",true);  // "제출하기" 버튼 비활성화 
			  		     $("button#btnSubmit").prop("disabled",false); // "제출하기" 버튼 활성화 
			  		     check(); // 채점하는 함수 호출
			      	}
		        });
		    	   
    	},
    	
   		error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
    });	// end of $.ajax({})---------------------------------------	
    
  }); // end of $(document).ready()--------------------------------
</script>
</head>
<body>
    <div id="timer"></div>
    <div id="quiz_display"></div>

    <button type="button" id="btnSubmit" style="border: solid 1px tomato; color: tomato; padding: 5px; border-radius: 5px;">제출하기</button>   
    <div id="score" style="margin-top: 10px;"></div>  
</body>
</html>
