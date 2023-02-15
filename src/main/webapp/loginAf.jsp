<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	MemberDao dao = MemberDao.getInstance();
	
	/* boolean isTrue = dao.login(id, pwd);
	if(isTrue == true) {
		out.print("로그인 성공!");
	} else out.print("로그인 실패!"); */
	
	MemberDto dto = dao.login(id, pwd);
	if(dto != null) {
		// 로그인 회원정보
		//request.getSession().setAttribute("login", dto);
		session.setAttribute("login", dto);
		// 세션의 유효기간
		//session.setMaxInactiveInterval(60 * 60 * 2); //2시간
%>
<script type="text/javascript">
alert("환영합니다. <%=dto.getId()%>님");
location.href = "./bbslist.jsp";
</script>
		
<%
	} else {
%>
<script type="text/javascript">
alert("아이디나 패스워드를 확인하십시오");
location.href = "login.jsp";
</script>
<%
	}
%>