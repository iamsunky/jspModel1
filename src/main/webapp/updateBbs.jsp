<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int seq = Integer.parseInt(request.getParameter("seq"));
BbsDto dto = BbsDao.getInstance().getBbs(seq);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="styles/bootstrap.min.css" rel="stylesheet">
<link href="styles/buttons.css" rel="stylesheet">
<title>글 수정하기</title>
<style type="text/css">
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 800px;
}

th, td {
	border: 1px solid black;
	padding: 15px 15px;
}
</style>
</head>
<body>
	<div align="center">
		<h1>글 수정하기</h1>
		<form action = "updateBbsAf.jsp" method="post">
		<input type="hidden" name="seq" value="<%=dto.getSeq() %>" />
		<table>
			<col width="100px">
			<tr>
				<th>작성자</th>
				<td><%=dto.getId()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input name="title" value="<%=dto.getTitle()%>" size="80"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="10" cols="80"><%=dto.getContent()%></textarea></td>
			</tr>
		</table>
		<br />
		<input type="submit" class="btn btn-primary btn-lg btn-radius" value="수정">
		<button class="btn btn-secondary btn-lg btn-radius"
			onclick="location.href='bbslist.jsp'">취소</button>
		</form>
	</div>
</body>
</html>