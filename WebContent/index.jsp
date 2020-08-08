<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
<h2>Hello World!</h2>

<% response.sendRedirect("login");%><br/><br/>

<%-- 	<c:if test="${sessionBean.roleID==2}">
<% response.sendRedirect("forumHomePage");%><br/><br/>
</c:if>
<c:if test="${sessionBean.roleID==1}">
<% response.sendRedirect("getInboxData");%><br/><br/>
</c:if> --%>
</body>
</html>
