<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
			<!--  header include -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
			<!-- navigation include -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
			
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="get" action="${pageContext.request.contextPath }/user/join">
						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="">
	
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="">
						<input id="CheckEmailbtn" type="button" value="id 중복체크">
						<div id= "checkMsg"></div>
						
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="">
						
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</fieldset>
						
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>
						
						<input type="submit" value="가입하기">
						
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
				<!--  footer include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div> <!-- /container -->

</body>
<script type="text/javascript">
	$("#CheckEmailbtn").on("click",function(){
		var email = $("#email").val();		//인풋 박스인 val 이용해서 빼내기
		console.log(email);
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/user/api/emailcheck",		
			type : "post",
			/*  contentType : "application/json",  */
			data : {email: email},			//앞은 글자(구별할 수 있는 key값), 뒤에는 실제 데이터 

			dataType : "json",
			success : function(result){
				console.log(result);
				if(result==true) {
					$("#checkMsg").text("사용할 수 있는 이메일입니다.");
				} else {
					$("#checkMsg").text("사용 중인 이메일입니다.");
				}
				/*성공시 처리해야될 코드 작성*/
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});

</script>
</html>