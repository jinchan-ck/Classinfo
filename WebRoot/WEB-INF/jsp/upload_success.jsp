<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="tk.sweetvvck.utils.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>上传成功</title>
<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />
</head>

<body>
	<table class="wwFormTable">
		<tr>

			<td colspan="2">
				<h1>上传成功</h1></td>
		</tr>

		<tr>
			<td class="tdLabel"><label for="doUpload_upload" class="label">
					内容类型: </label></td>
			<td><s:property value="uploadContentType" /></td>
		</tr>

		<tr>
			<td class="tdLabel"><label for="doUpload_upload" class="label">
					文件路径: </label></td>
			<td><s:property value="uploadFileName" /></td>
		</tr>


		<tr>
			<td class="tdLabel"><label for="doUpload_upload" class="label">
					文件内容: </label></td>
			<td><s:iterator value="#request.uploadFiles" id="id" status="st">

					<div
						style="width:130px; height:150px; float:left; margin:4px; border:#EEEEEE solid 3px; text-align:center; display:inline; overflow:hidden;">
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
							</div> 『<s:property value="#id.photoTitle" />』<br /> <font color="#AAAAAA">[<s:property
									value="#id.uploadDate" />]</font> </a>
					</div>
				</s:iterator>
			</td>
		</tr>

		<tr>
			<td class="tdLabel"><label for="doUpload_upload" class="label">
					临时文件: </label></td>
			<td><s:property value="upload" /></td>
		</tr>
	</table>





</body>
</html>
