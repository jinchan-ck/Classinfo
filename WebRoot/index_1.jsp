<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	/* response.sendRedirect(path + "/RedirectAction"); */
	response.setStatus(302);
	response.setHeader("location", path + "/RedirectAction");
	response.setHeader("Content-Encoding", "gzip");
%>
