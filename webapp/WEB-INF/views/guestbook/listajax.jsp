<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
									<%-- 다 jstl로 하기때문에 자바코드는 필요없고 불러낼 필요X  <%@ page import = "com.javaex.vo.GuestbookVo" %>
																				  <%@ page import = "java.util.List" %>
																												<!-- attribute가 object타입이기 때문에 형변환이 필요함  -->
																			  	 <% List<GuestbookVo> glist = (List<GuestbookVo>) request.getAttribute("list"); %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
	
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
				<div id="guestbook">
						
					
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input id="addBtn" type="submit" VALUE=" 확인 " /></td>
							</tr> 	
						</table>
			
					<ul id="listArea">
					
					</ul>
					<input id="nextBtn" type="button" value="다음글 5개 가지고오기">
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!--  footer include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div> <!-- /container -->

	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="text" name="modalNo" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

</body>

<script type= "text/javascript">
var page =1;

$(document).ready(function(){			//시작할때 다섯개 뿌려주고

	fetchList();

	});
	

$("#nextBtn").on("click", function(){			//누를때마다 다섯개씩 나오게 
		page = page +1;
		
		console.log(page);
		
		fetchList();
		
	});
	

/*  $("#delBtn").on("click", function(){
	console.log("delBtn");
	$("#del-pop").modal();
});  */
 
$("ul").on("click",".delBtn", function(){	//게시물중에 삭제버튼을 하나 눌렀을때
	
	 var $this = $(this);
	 var no = $this.data("no");		//삭제를 누를 게시물 데이터의 no값을 넣어주기

	 $("#modalNo").val(no);  //modal에 값을 넣기 
	 $("#modalPassword").val(""); //값을 내가 적어주어야하니까  ""이거만 넣기
	
	 $("#del-pop").modal();	//모달창 뜨게하기
 }); 


$("#btn_del").on("click", function(){
	var no = $("#modalNo").val();		//modalNo에 들어간 값을 꺼내서 no에 넣어줌
	var password = $("#modalPassword").val();
	
	console.log(no);
	
	var modalgbVo = { 
						no : no,
						password : password
			
	}

	
	$.ajax({
		//Controller에 보내는형
		url : "${pageContext.request.contextPath }/guestbook/api/delete",		
		type : "post",
		contentType : "application/json", 
		data : JSON.stringify(modalgbVo),			//메소드임 그래서 기능으로보고()

		//Controller 받아올때
		dataType : "json",
		success : function(modalgbVo){
			$(modalgbVo).remove();
			$("#modalPassword").val("");
			
			$("#del-pop").modal("hide");	//모달창 닫기
			
			location.reload();	//새로고침 1번
		},
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
		});
});


$("#addBtn").on("click", function(){
	console.log("addBtn");
	
	var name = $("[name=name]").val();
	var password = $("[name=password]").val();
	var content = $("[name=content]").val();
	
	var gbVo= {
						name : name,
						password : password,
						content : content
	}
	
	$.ajax({
		//Controller에 보내는형
		url : "${pageContext.request.contextPath }/guestbook/api/add",		
		type : "post",
		contentType : "application/json", 
		data : JSON.stringify(gbVo),			//메소드임 그래서 기능으로보고()

		//Controller 받아올때
		dataType : "json",
		success : function(gbVo){
			console.log(gbVo);
				render(gbVo, "up");
		},
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});

function fetchList(){
	
	$.ajax({
		//Controller에 보내는형
		url : "${pageContext.request.contextPath }/guestbook/api/list",		
		type : "post",
		/* contentType : "application/json",  */
		data : {page : page},			//객체로 받아주니까 {}

		//Controller 받아올때
		dataType : "json",
		success : function(gbList){
			console.log(gbList);
			
			for(var i=0; i<gbList.length; i++){
				render(gbList[i], "down");
			}

		},
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}	
	
	
function render(guestbookVo, updown){
	
	var str ="";
	str +="<li>";
	str +="		<table>";
	str +=" 		<tr>";
	str +="            <td>[" + guestbookVo.no + "]</td>";
	str +=" 		   <td>" + guestbookVo.name +"</td>"	
	str +=" 		   <td>" + guestbookVo.regDate + "</td>"
	str +=" 		   <td><button type='button' class='delBtn' data-no="+guestbookVo.no+">삭제</button>삭제</td>"
	str +="         </tr>"
	str +="         <tr>"
	str +="            <td colspan=4>" + guestbookVo.content + "</td>"
	str +="			</tr>";
	str +="		</table>";
	str +="   <br>";
	str +="</li>";
	
	
	if(updown =="up"){
		$("#listArea").prepend(str);
		
	} else if(updown =="down"){
		$("#listArea").append(str);
		
	} else {
		console.log("오류");
	}
}


</script>

</html>