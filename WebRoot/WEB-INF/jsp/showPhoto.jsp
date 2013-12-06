<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="tk.sweetvvck.utils.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link href="style/style.css" rel="stylesheet" type="text/css">
<title>相册</title>

</head>

<body class="main">
	
	<div class="main_inner" style="margin-top: 60px">
	<h2 style="margin-bottom: 20px; margin-top: 20px; margin-left: 10px;">相册：${photoList[0].album.albumname }</h1>
		<s:iterator value="#request.photoList" id="id" status="st">

				<div
					style="width:130px; height:150px; float:left; margin:4px; border:#EEEEAA solid 3px; text-align:center; display:inline; overflow:hidden;">
					<a
						href='<%=request.getContextPath()
						+ "/"
						+ UploadConfigurationRead.getInstance().getConfigItem(
								"uploadFilePath") + "/"%><s:property value="#id.path"/>'
						target='_blank'>
						<div
							style="width:120px; height:90px; margin:5px; overflow:hidden; clear:both">
							<img
								src='<%=request.getContextPath()
						+ "/"
						+ UploadConfigurationRead.getInstance().getConfigItem(
								"uploadFilePath") + "/"%><s:property value="#id.path"/>'
								width="120" height="90" border="0" />
						</div> 『<s:property value="#id.name" />』<br /> <font color="#AAAAAA">[<s:property
								value="#id.uploadDate" />]</font> </a>
				</div>
			</s:iterator>
		</div>
	</body>
</html>
