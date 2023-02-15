<%@page import="dto.MemberDto"%>
<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
int seq = Integer.parseInt(request.getParameter("seq"));

BbsDao dao = BbsDao.getInstance();
dao.incHit(seq);
BbsDto dto = dao.getBbs(seq);

%>
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
<link href="styles/bootstrap.min.css" rel="stylesheet">
<link href="styles/buttons.css" rel="stylesheet">
<title>글 상세보기</title>
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
<h1>글 상세보기</h1>
	<table>
	<col width="100px">
		<tr>
			<th>작성자</th>
			<td><%=dto.getId() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%=dto.getWdate() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=dto.getReadcount() %></td>
		</tr>
		<tr>
			<th>정보</th>
			<td><%=dto.getRef() %>-<%=dto.getStep() %>-<%=dto.getDepth() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=dto.getContent() %></td>
		</tr>
	</table>
	<br />
	<br />
	<button class="btn btn-primary btn-lg btn-radius" onclick="answerBbs(<%=dto.getSeq()%>)">답글</button>
	<button class="btn btn-secondary btn-lg btn-radius" onclick="location.href='bbslist.jsp'">글목록</button>
	
	<%
	if(dto.getId().equals(login.getId())) {
	%>
	<button class="btn btn-secondary btn-lg btn-radius" onclick="updateBbs(<%=dto.getSeq()%>)">수정</button>
	<button class="btn btn-danger btn-lg btn-radius" onclick="deleteBbs(<%=dto.getSeq()%>)">삭제</button>
	<%	
	}
	%>
	
</div>

<script type="text/javascript">
function answerBbs(seq){
	location.href = "answer.jsp?seq="+seq;
}
function updateBbs(seq){
	location.href = "updateBbs.jsp?seq="+seq;
}
function deleteBbs(seq){
	location.href = "deleteBbs.jsp?seq="+seq;
}
</script>
</body>
</html>