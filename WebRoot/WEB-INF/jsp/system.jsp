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
<link href="style/forms.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ui.js"></script>
<script type="text/javascript">
	
	function checkSystemAdmin(){
		var username = document.getElementById("sysName");
		var password = document.getElementById("sysPassword");
		<c:forEach items="${systemAdmins}" var="admin"> 
		 	if(username.value == '${admin.username}' && password.value == '${admin.password}'){
		 		document.getElementById("sysDiv").className = "hidden";
		 		//document.getElementById("systemAdmin").className = "hidden";
		 		document.getElementById("addAdmin").className = "div3";
		 		return;
		 	}
		</c:forEach>  
		alert("用户名或密码错误！");
	}
	
	function selectYear(){
		var selection = document.getElementById("year");
		var all = document.createElement("option");
		all.innerHTML = "全部";
		selection.appendChild(all);
		var today = new Date().getFullYear();
		for ( var int = today-19; int < today + 1; int++) {
			var option = document.createElement("option");
			option.innerHTML = int;
			selection.appendChild(option);
		}
	}
	
	function appoint(){
		var members = document.getElementById("members");
		if(document.getElementsByTagName("tbody") != null){
			for(var i = 1; i < document.getElementsByTagName("tbody").length; i ++){
				document.getElementsByTagName("tbody").item(i).className = "";
			}
		}
		var tbody = document.createElement("tbody");
		tbody.id == "tbody";
		members.appendChild(tbody);
	}
	
	function sumHonor(){
		var honorTable = document.getElementById("honorTable");
		if(document.getElementsByTagName("tbody") != null){
			for(var i = 1; i < document.getElementsByTagName("tbody").length; i ++){
				document.getElementsByTagName("tbody").item(i).className = "hidden";
			}
		}
		var honorTableBody = document.getElementById("honorTableBody");
		var tbody = document.createElement("tbody");
		tbody.id == "tbody";
		honorTable.appendChild(tbody);
		honorTableBody.className = "hidden";
		<c:forEach items="${honors}" var="honor"> 
			//honorTable.removeChild(honorTableBody);
			var banji = document.getElementById("banjiSelection");
			var year = document.getElementById("year");
			var level = document.getElementById("levelSelection");
			var type = document.getElementById("typeSelection");
			if(banji.value == "${honor.banji.banjiId}"){
				//alert("${honor.banji.banjiId}");
				if("${honor.prizeDate}".indexOf(year.value.substring(2,4), 1) > 0 || year.value == "全部"){
					//alert("${honor.level} == " + level.value);
					if("${honor.level}" == level.value || level.value == "全部"){
						level = document.getElementById("levelSelection");
						if("${honor.honorType}" == type.value || type.value == "全部"){
							var tr = document.createElement("tr");
							var prizeDate = document.createElement("td");
							var honorType = document.createElement("td");
							var honorName = document.createElement("td");
							var level = document.createElement("td");
							var competitionName = document.createElement("td");
							var instructor = document.createElement("td");
							var honorMembers = document.createElement("td");
							prizeDate.innerHTML = "${honor.prizeDate}".substring(0, 11);
							if("${honor.honorType}" == "personer"){
								honorType.innerHTML = "个人荣誉";
							}else{
								honorType.innerHTML = "班级荣誉";
							}
							honorName.innerHTML = "${honor.honorName}";
							level.innerHTML = "${honor.level}";
							competitionName.innerHTML = "${honor.competitionName}";
							instructor.innerHTML = "${honor.instructor}";
							if ("${honor.honorType}" == "personer"){
								<c:forEach items="${honor.students}" var="student"> 
									honorMembers.innerHTML += "${student.studentName},";
								</c:forEach>
							}else{
								honorMembers.innerHTML = "全班同学";
							}
							
							tr.appendChild(prizeDate);
							tr.appendChild(honorType);
							tr.appendChild(honorName);
							tr.appendChild(level);
							tr.appendChild(competitionName);
							tr.appendChild(instructor);
							tr.appendChild(honorMembers);
							tbody.appendChild(tr);
						}
					}
				}
				tr.className = "hand";
				tr.onclick = function(){
					window.open("<%=path%>/showHonorAction?albumId=" + "${honor.honorId}");
				};
			}else{
								
			}
		</c:forEach>
	}	
	
</script>


</head>

<body class="indexBody" 
	  onload="javascript:sumHonor();selectYear();switchFunction(document.getElementById('switchFunction').value);showMembers(document.getElementById('selection').value, '${fn:length(banjiList)}');showMembers_1(document.getElementById('selection_1').value, '${fn:length(banjiList)}');"><!--  onLoad="showMessage('${message}', '${studentNum}')" -->
	<%-- <%	
		session.setAttribute("banjiList", request.getAttribute("banjiList")); 
	%> --%>
	<!-- <div id="addAdmin" class="hidden"> -->
		<div id="addAdmin" class="hidden">
			<div class="titlebar">
					<select id="switchFunction" onchange="switchFunction(this.value)"
						style="margin-top: 3px; padding-left: 0px; margin-left: -400px; background-color: black; border: 0px; color: white;">
						<option value="任命班干部">任命班干部</option>
						<option selected="selected" value="添加班级管理员">更新班级管理员</option>
						<option value="统计班级获奖信息">统计班级获奖信息</option><!-- 在ie中必须设置option个的value，不然不能得到select的value；但是在fire等浏览器中，若没有设置option的value，option标签中的文本就是select的value -->
					</select>
					<a class="close" onclick="javascript:document.getElementById('addAdmin').className='hidden';document.getElementById('sysDiv').className='div1';" href="javascript:void(0)"> 关闭
					</a>
			</div>
			<div id="sumDiv" class="hidden" style="padding: 40px; text-align: left;">
				<div style="float: left; height: 30px;">
					班级：<select id="banjiSelection">
							<s:iterator value="#request.banjiList" id="banji">
								<option selected="selected" value="<s:property value="#banji.banjiId"/>"><s:property value="#banji.banjiName"/></option>
							</s:iterator>
						</select>
					年份：<select id="year">
							
						</select>
					等级：<select id="levelSelection">
							<option value="全部">全部</option>
							<option value="国家级">国家级</option>
							<option value="市级">市级</option>
							<option value="校级">校级</option>
						</select>
					类型：<select id="typeSelection">
							<option value="全部">全部</option>
							<option value="personer">个人荣誉</option>
							<option value="collective">班级荣誉</option>
						</select>
				</div>
				<div>
					<input type="button" class="button" value="查询" style="margin-left: 30px; margin-top: -5px; height: 30px;" onclick="sumHonor()">
				</div>
				<div style="clear: both;"></div>
				<div style="margin: 10px;">
					<table cellspacing="0" cellpadding="3" border="0" id="honorTable" class="datelist">
							<tr class="datelisthead">
								<td>获奖时间</td><td>荣誉类型</td><td>荣誉名称</td><td>等级</td><td>获奖名称</td><td>指导老师</td><td>获奖者</td>
							</tr>
						<tbody id="honorTableBody">
								<s:iterator value="#request.honors" id="honor">
									<tr>
										<td>
											<s:property value="#honor.prizeDate"/>
										</td>
										<td>
											<s:if test="#honor.honorType=='personer'">
												个人荣誉
											</s:if>
											<s:else>
												班级荣誉
											</s:else>
										</td>
										<td>
											<s:property value="#honor.honorName"/>
										</td>
										<td>
											<s:property value="#honor.level"/>
										</td>
										<td>
											<s:property value="#honor.competitionName"/>
										</td>
										<td>
											<s:property value="#honor.instructor"/>
										</td>
										<td>
											<s:if test="#honor.honorType=='personer'">
												<s:iterator value="#honor.students" id="students">
													<s:property value="#students.studentName"/>,
												</s:iterator>
											</s:if>
											<s:else>
												全班同学
											</s:else>
										</td>
									</tr>
								</s:iterator>
						</tbody>
					</table>
				</div>
		</div>
		<div id="addManagerDiv" style="padding: 40px;">
				<div style="text-align: left;">
					班级：
					<select id="selection" onfocus="javascript:showMembers(this.value, '${fn:length(banjiList)}');"
					onchange="javascript:showMembers(this.value, '${fn:length(banjiList)}');"
						onblur="javascript:showMembers(this.value, '${fn:length(banjiList)}');">
						<s:iterator value="#request.banjiList" id="banji">
							<option selected="selected" value="<s:property value="#banji.banjiId"/>"><s:property value="#banji.banjiName"/></option>
						</s:iterator>
					</select>
				</div>
				<div style="margin-top: 40px;">
					<c:forEach items="${banjiList}" var="banji">
						<div class="hidden" id="${banji.banjiId}" style="padding: 5px;">
							<form action="RedirectAction!addManager" method="get">
								<input type="hidden" value="${banji.banjiId} " name="albumId"/>
								<div>
									<c:forEach items="${banji.students}" var="honorStudent">
									<div style="width: 70px; float: left; text-align: left; height: 30px;">
										<c:if test="${honorStudent.isManager}">
											<input type="checkbox" checked="checked" name="item" value="${honorStudent.studentName}" onclick="checkItem(this)"
													check
											>
										</c:if>
										<c:if test="${!honorStudent.isManager }">
											<input type="checkbox" name="item" value="${honorStudent.studentName}" onclick="checkItem(this)"
													check
											>
										</c:if>
											${honorStudent.studentName}
									</div>
									</c:forEach>
								</div>
								<div style="clear: both;"></div>
								<div>
									<input type="submit" value="确认更新" class="button">
								</div>
							</form>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="appoint" class="hidden">
				<div style="text-align: left;">
					班级：
					<select id="selection_1" onfocus="javascript:showMembers_1(this.value, '${fn:length(banjiList)}');"
					onchange="javascript:showMembers_1(this.value, '${fn:length(banjiList)}');"
						onblur="javascript:showMembers_1(this.value, '${fn:length(banjiList)}');">
						<s:iterator value="#request.banjiList" id="banji">
							<option selected="selected" value="<s:property value="#banji.banjiId"/>"><s:property value="#banji.banjiName"/></option>
						</s:iterator>
					</select>
				</div>
				<c:forEach items="${banjiList}" var="banji">
					<div class="hidden" id="${banji.banjiId}_1" style="padding: 5px;">
						<form action="RedirectAction!appoint" method="post">
							<input type="hidden" value="${banji.banjiId} " name="albumId"/>
							<div>
								<table width="100%" id="members" height="97"
									border="1" align="center" cellpadding="0" cellspacing="1">
									<tr>
										<td>姓名</td>
										<td>性别</td>
										<td>学号</td>
										<td>职位</td>
									</tr>
									<c:forEach items="${banji.students}" var="student">
										<tr style="border: thin;">
											<td width="25%">
												${student.studentName }
												<input type="hidden" name="item" value="${student.studentNum }"/>
											</td>
											<td width="25%">
												${student.gender }
											</td>
											<td width="25%">
												${student.studentNum }
											</td>
											<td width="25%">
												<input name="positions" value="${student.position }" type="text" style="width: 100%;height: 100%;"/>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div style="clear: both;"></div>
							<div>
								<input type="submit" value="确认更新" class="button">
							</div>
						</form>
					</div>
				</c:forEach>
			</div>
		</div>
	<DIV class="main_container">
		
		<div id="sysDiv" class="div1">
			<div class="div2">
				<div style="margin-bottom: 30px;">
					<h1>
						系统管理员
					</h1>
				</div>
				<div>
					用户名：
					<input id="sysName" name="sysName" type=text style="height: 30px;"> 
				</div>
				<div style="margin-top: 10px; margin-bottom: 10px;">
					密&nbsp;&nbsp;&nbsp;&nbsp;码：
					<input id="sysPassword" name="sysPassword" type="password" class="" align="middle" style="height: 30px;">
				</div>
				<div>
					<input style="margin-left: 100px; margin-top: 10px; height: 30px;" class="button" type="button" tabindex="3" value="登 录" onClick="javascript:checkSystemAdmin()"/>
				</div>
			</div>
		</div>
	
	</DIV>

</body>
</html>
