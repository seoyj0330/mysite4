<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

								<%--  이미 서블렛에서 넘어올때 따로 불러오지 않아도 사용가능 <%	String result =request.getParameter("result");%> --%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
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
					
					<form id="login-form" name="loginform" method="get" action="${pageContext.request.contextPath }/user/login">

						<label class="block-label" for="email">이메일</label> 
						<input id="email" name="email" type="text" value=""> 

						<label class="block-label">패스워드</label> 
						<input name="password" type="password" value="">
						
								<c:if test = "${param.result eq 'fail'}">			<%-- <% if("fail".equals(result)) { %> --%>
								<P>로그인이 실패했습니다. 다시입력해주세요</P>
								</c:if>
	
						<input type="submit" value="로그인">
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
				<!--  footer include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div> <!-- /container -->


</body>
</html>