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

<title>相册</title>

</head>

<body>

	<div class="tdLabel">
		<label for="doUpload_upload" class="label"> 所有相册: </label>
	</div>
	<div>
		<s:iterator value="#request.albumList" id="id" status="st">
			<div
				style="width:130px; height:150px; float:left; margin:4px; border:#EEEEEE solid 3px; text-align:center; display:inline; overflow:hidden;">
				<a
					href='<%=path + "/"%>RedirectActionToShowPhotoView?albumId=<s:property value="#id.albumId"/>'
					target='_blank'>
					<div
						style="width:120px; height:90px; margin:5px; overflow:hidden; clear:both">
						<img
							src='<%=request.getContextPath()
						+ "/"
						+ UploadConfigurationRead.getInstance().getConfigItem(
								"uploadFilePath") + "/"%><s:property value="#id.photos.iterator.next.path"/>'
							width="120" height="90" border="0" />
					</div> 『<s:property value="#id.albumname" />』<br /> <font
					color="#AAAAAA">[<s:property
							value="#id.photos.iterator.next.uploadDate" />] </font> </a>
			</div>
		</s:iterator>
	</div>
</body>
</html>
