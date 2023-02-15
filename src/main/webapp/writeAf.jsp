<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = request.getParameter("id");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BbsDao dao = BbsDao.getInstance();
	boolean b = dao.write(id, title, content);

	if(b) {
		%>
	<script>
		alert("성공적으로 글이 등록되었습니다!");
		location.href = "bbslist.jsp";
	</script>
	<%
	}
%>