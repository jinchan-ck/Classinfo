<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="tk.sweetvvck.utils.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<title>${honor.honorName}</title>
		<link href="<%=path%>/style/style.css" rel="stylesheet" type="text/css">

	</head>

	<body class="main">
		<div id="addHonorDivInner" class="div3">
					<table style="width: 80%;height: 80%;padding: 10;" border="0">
						<tr height="30px;">
							<td width="100px">比赛名称：</td>
							<td>
								${honor.competitionName }
							</td>
						</tr>
						<tr height="30px;">
							<td>比赛级别：</td>
							<td>
								${honor.level }
							</td>
						</tr>
						<tr height="30px;">
							<td>荣誉名称：</td>
							<td>	
								${honor.honorName }
							</td>
						</tr>
						<tr height="30px;">
							<td>荣誉类型：</td>
							<td>
								<c:if test="${honor.honorType == 'personer' }">
					          		个人荣誉
					          	</c:if>
								<c:if test="${honor.honorType != 'personer' }">
					          		集体荣誉
					          	</c:if>
							</td>
						</tr>
						<tr height="30px;">
							<td>指导老师：</td>
							<td>
								${honor.instructor }
							</td>
						</tr>
						<tr height="30px;" id="member" style="display: table-row;">
							<td width="100px">参赛人员：</td>
							<td>
								<c:if test="${honor.honorType == 'personer' }">
									<c:forEach var="honorStudent" items="${honor.students}">
					          			${honorStudent.studentName},
					          		</c:forEach>
					          	</c:if>
					          	<c:if test="${honor.honorType != 'personer' }">
					          		全班同学
					          	</c:if>
							</td>
						</tr>
						<tr height="30px;">
							<td>获奖时间：</td>
							<td>${honor.prizeDate }
							</td>
						</tr>
						<tr height="30px;">
							<td>获奖证书：</td>
							<td>
								<s:property value="#honor.album.photos"/>
								<c:forEach items="${honor.album.photos }" var="id">
									
								
									<div
										style="width:130px; height:150px; float:left; margin:4px; border:#EEEEEE solid 3px; text-align:center; display:inline; overflow:hidden;">
										<a
											href='<%=request.getContextPath()
											+ "/"
											+ UploadConfigurationRead.getInstance().getConfigItem(
													"uploadFilePath") + "/"%>${id.path}'
											target='_blank'>
											<div
												style="width:120px; height:90px; margin:5px; overflow:hidden; clear:both">
												<img
													src='<%=request.getContextPath()
											+ "/"
											+ UploadConfigurationRead.getInstance().getConfigItem(
													"uploadFilePath") + "/"%>${id.path}'
													width="120" height="90" border="0" />
											</div> 『${id.name }』<br /> <font color="#AAAAAA">[<s:property
													value="#id.uploadDate" />]</font> </a>
									</div>
								</c:forEach>
							</td>
						</tr>
						
					</table>
			</div>

	</body>
</html>
