<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- blist -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
			<!--  header include -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
			<!-- navigation include -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board/list" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items = "${ubmap.boardlist }" var="userboardVo">
					<tr>
						<td>${userboardVo.boardno }</td>
						<td><a href="${pageContext.request.contextPath }/board/view?boardno=${userboardVo.boardno }&kwd=${ubmap.kwd}&crtPage=${ubmap.crtPage}">${userboardVo.title }</a></td>
						<td>${userboardVo.name }</td>
						<td>${userboardVo.hit }</td>
						<td>${userboardVo.regDate }</td>
						
						
						
						<c:choose>
							<c:when test="${empty authUser }">	<!-- 로그인 안했을 때 -->
								<td></td>
							</c:when>
							
							<c:otherwise>	<!-- 로그인 했을때  -->
								<c:choose>
									<c:when test="${authUser.no  eq userboardVo.user_no}">
										<td><a href="${pageContext.request.contextPath }/board/delete?deleteno=${userboardVo.boardno }" class="del">삭제</a></td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
							
						</c:choose>
						</tr>
					</c:forEach>
				
				</table>
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${ubmap.kwd }">
								<c:if test="${ubmap.prev }">
									<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${startPageBtnNo-1}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${ubmap.startPageBtnNo }" end = "${ubmap.endPageBtnNo }" var= "idx"> 
										<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${idx}">${idx}</a></li>
								</c:forEach>
		
								<c:if test = "${ubmap.next }">
									<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${startPageBtnNo+1}">▶</a></li>
								</c:if>
							</c:when>
							
							<c:otherwise>				<!-- 키워드가 있을 때  -->
								<c:if test="${ubmap.prev }">
									<li><a href="${pageContext.request.contextPath }/board/list?kwd=${ubmap.kwd }&crtPage=${startPageBtnNo-1}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${ubmap.startPageBtnNo }" end = "${ubmap.endPageBtnNo }" var= "idx"> 
										<li><a href="${pageContext.request.contextPath }/board/list?kwd=${ubmap.kwd }&crtPage=${idx}">${idx}</a></li>
								</c:forEach>
		
								<c:if test = "${ubmap.next }">
									<li><a href="${pageContext.request.contextPath }/board/list?kwd=${ubmap.kwd }&crtPage=${startPageBtnNo+1}">▶</a></li>
								</c:if>
							</c:otherwise>	
						</c:choose>
					</ul>
				</div>				
				<c:choose>
					<c:when test="${empty authUser }"> <!-- 로그인 안했을때 -->
									
					</c:when>
					<c:otherwise>
						<div class="bottom">
						<a href="${pageContext.request.contextPath }/board/writeform" id="new-book">글쓰기</a>
						</div>
					</c:otherwise>
				</c:choose>
						
						
			</div>
		</div>
		
		<!--  footer include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>
</html>