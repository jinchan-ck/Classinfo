<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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

<title>班级信息管理系统</title>
<link href="style/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ui.js"></script>
</head>

<body class="indexBody">
	<DIV class="main_container">
		<DIV id=u0_container class="u0_container">
			<DIV id=u1 class="u1">
				<DIV id=u1_rtf>
					<p style="text-align:center;">
						<span
							style="font-size:36px;font-weight:bold;font-style:normal;text-decoration:none;color:#FFFFFF;">班级信息管理系统</span>
					</p>
				</DIV>
			</DIV>
		</DIV>

		<span id="nullUser" class="hidden" >用户名不能为空</span> 
		<span id="nullPassword" class="hidden">密码不能为空</span>
		<span id="errUser" class="hidden" >用户名不存在</span> 
		<span id="errPassword" class="hidden">密码错误</span>
		<form name="login" method="post" action="" id="loginForm">
		  <INPUT id=u2 name="studentNum" align="middle" type=text
				class="u2 form_sketch"> <INPUT id=u3 name="password"
				type="password" class="u3 form_sketch" align="middle">

			
			<DIV id=u6_container class="u6_container">
				<input type="hidden" name="userType" value="host"/>
				<input class="button" id="login" type="button" tabindex="3"
					value="登 录" onClick="javascript:validate('<%=basePath%>')"/>
			</DIV>
		</form>
		<DIV class="u7_container">
			<input class="button" id="visit" type="button" tabindex="3"
				value="访 客" onClick="selectBanji()"/>
		</DIV>
		
		<div class="hidden" id="selectBanji">
			<div class="div2">
				班级： <select id="visitBanjiSelection">
						<s:iterator value="#request.banjiList" id="banji">
							<option selected="selected" value="<s:property value="#banji.banjiId"/>"><s:property value="#banji.banjiName"/></option>
						</s:iterator>
				</select> <br /> <br /> <input type="button"
					onclick="javascript:location.href='<%=path%>/LoginAction?banjiId=' + document.getElementById('visitBanjiSelection').value +'&userType=visitor'"
					value="确定" /> <input style="margin-left: 20px" type="button"
					value="取消" onClick="selectCencel()" />
			</div>
		</div>
		
	</DIV>

</body>
</html>
