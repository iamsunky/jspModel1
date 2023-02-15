<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// getAttribute()의 리턴 타입은 Object이다.
MemberDto login = (MemberDto)session.getAttribute("login");
if(login == null) {
	%>
	<script>
	alert('로그인 해 주십시오');
	location.href = "login.jsp";
	</script>
	<%
}

%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 600px;
}

th, td {
	border: 1px solid black;
	padding: 10px 15px;
}
</style>
</head>
<body>
<h1>글 작성하기</h1>
<form action="writeAf.jsp" method="post">
<table>
<tr>
	<th>ID</th>
	<td><input name="id" value=<%=login.getId() %> size="auto" readonly></td>
</tr>
<tr>
	<th>Title</th>
	<td><input placeholder="제목을 입력해주세요" size="70" name="title"></td>
</tr>
<tr>
	<th>Content</th>
	<td><textarea rows="8" cols="80" placeholder="내용을 입력해주세요" name="content"></textarea>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="글 등록">
	</td>
</table>
</form>
</body>
</html>