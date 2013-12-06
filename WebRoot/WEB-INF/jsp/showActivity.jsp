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
		<div class="div3">
					<table style="width: 80%;height: 80%;padding: 10;" border="0">
						<tr height="30px;">
							<td width="100px">活动名称：</td>
							<td>
								${activity.activityTitle}
							</td>
						</tr>
						<tr height="30px;">
							<td>活动时间：</td>
							<td>
								${activity.activityDate}
							</td>
						</tr>
						<tr height="30px;">
							<td>活动地点：</td>
							<td>	
								${activity.activityAdd}
							</td>
						</tr>
						<tr height="30px;">
							<td>活动内容：</td>
							<td>
								${activity.activityContent}
							</td>
						</tr>
						<tr height="30px;">
							<td>活动相册：</td>
							<td>
								<c:forEach items="${activity.activityAlbum.photos }" var="id">
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
