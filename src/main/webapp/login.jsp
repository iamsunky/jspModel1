<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>

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

<h2>Login Page</h2>
<div class="center">
	<form action="loginAf.jsp" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input id="id" name="id" size="20"><br />
					<input type="checkbox" id="chk_save_id">id저장
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="pwd" size="20"><br />
					<p id="pid"></p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="log-in">
					<a href="regi.jsp">회원가입</a>
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
/*
 * 										자료형			저장 영역
 	cookie : id, pw 저장	== String		Client
 	session : login한 정보	== Object		Server
 */
 
let user_id = $.cookie("user_id");
 
if(user_id != null) {
	$("#id").val(user_id);
	$("#chk_save_id").prop("checked", true);
}
 
$("#chk_save_id").click(function(){
	if($("#chk_save_id").is(":checked")) {
		if($("#id").val().trim() == "") {
			alert('id를 입력해주세요');
			$("#chk_save_id").prop("checked", false);
		} else {
			// cookie를 저장 (expires : 유효기간)
			$.cookie("user_id", $("#id").val().trim(), { expires:7, path:'./' });
		}
	} else {
		$.removeCookie("user_id", { path:'./' });
	}
});
</script>

</body>
</html>