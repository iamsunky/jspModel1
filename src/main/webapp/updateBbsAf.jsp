<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    int seq = Integer.parseInt(request.getParameter("seq"));
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    
    boolean isS = BbsDao.getInstance().update(seq, title, content);
    if(isS) {
    	%>
    	<script>
    		alert('성공적으로 글이 수정되었습니다.');
    		location.href = "bbsdetail.jsp?seq="+<%=seq%>;
    	</script>
    	<%
    } else {
    	%>
    	<script>
    		alert('글 수정에 실패하였습니다.');
    		location.href = "updateBbs.jsp?seq="+<%=seq%>;
    	</script>
    	<%
    }
    %>