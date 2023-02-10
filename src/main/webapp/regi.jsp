<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style type="text/css">
.center {
	margin: auto;
	width: 60%;
	border: 3px solid #ff0000;
	padding: 10px;
}

tr, th, td {
	border: 1px solid black;
	padding: 10px 15px;
}
</style>
</head>
<body>

<h2>회원가입</h2>
<p>환영합니다</p>

<div class="center">
	<form action="regiAf.jsp" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input name="id" id="id" size="20" placeholder="사용할 ID를 입력해주세요"><br />
					<p id="idcheck" style="font-size: 14px"></p>
					<input type="button" id="idChkBtn" value="ID 중복확인">
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input name="pwd" id="pwd" size="20">
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input name="name" id="name" size="20">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="email" name="email" size="20">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="회원가입">
				</td>
			</tr>			
		</table>
	</form>
</div>
<script type="text/javascript">
$(document).ready( () => {
	
	$("#idChkBtn").on("click", function() {
		
		$.ajax({
			url: "idcheck.jsp",
			data: { id : $("#id").val() },
			type: "post",
			success: function(msg){
				if(msg.trim() == "yes") {
					$("#idcheck").css("color", "#0000ff");
					$("#idcheck").text("사용할 수 있는 ID입니다.");
				} else {
					$("#idcheck").css("color", "#ff0000");
					$("#idcheck").text("이미 사용중인 ID입니다.");
					$("#id").val("");
				}
			},
			error: function(){
				console.log("error");
			}
		})
		
	});
});
</script>
</body>
</html>