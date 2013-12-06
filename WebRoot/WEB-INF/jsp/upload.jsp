<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>upload</title>

</head>
<%----%>
<%--	<body>--%>
<%----%>
<%--		<s:actionerror />--%>
<%--		<s:fielderror />--%>
<%--		<s:form action="upload.action" method="POST" enctype="multipart/form-data">--%>
<%--			<tr>--%>
<%--				<td colspan="2">--%>
<%--					<h1>--%>
<%--						文件上传示例--%>
<%--					</h1>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<s:file name="upload" label="上传文件"/>--%>
<%--			<s:file name="upload" label="上传文件"/>--%>
<%--			<s:submit value="上   传" />--%>
<%--		</s:form>--%>
<%--	</body>--%>
<br/>
<body>

	<script type="text/javascript">
	<!--addMore函数可以提供上传多个文件上传-->
		function addMore() {
		var td = document.getElementById("more");
		var br = document.createElement("br");
		var input = document.createElement("input");

		var input2 = document.createElement("input");
		var input3 = document.createElement("input");

		var button = document.createElement("input");

		input.type = "file";
		input.name = "upload";

		input2.type = "text";
		input2.name = "photoTitles";

		input3.type = "text";
		input3.name = "photoDescribes";

		button.type = "button";
		button.value = "删除";

		button.onclick = function() {
			td.removeChild(br);
			td.removeChild(input);
			td.removeChild(input2);
			td.removeChild(input3);
			td.removeChild(button);
		};

		td.appendChild(br);
		td.appendChild(input);
		td.appendChild(input2);
		td.appendChild(input3);
		td.appendChild(button);
	};
	</script>

	<!--<h3><font color="red">上传文件类型后缀为doc,ppt,xls,pdf,txt,java，每个文件大小不能大于50M</font></h3>-->

	<s:form action="upload.action" theme="simple" method="post"
		enctype="multipart/form-data">

		<table align="center" width="70%" border="1">
			<tr>
				<td>上传文件${request.fileUploadErrorMessage}</td>
				<td id="more"><s:file name="upload"></s:file> 标题<input
						type="text" name="photoTitles"> 描述<input type="text"
						name="photoDescribes"> 
						<input name="albumname" hidden="true" value="${albumname}"/>
						<input name="studentNum" hidden="true" value="${studentNum}"/>
						<input type="button" value="上传更多..." onclick="addMore()">
						<input type="hidden" name="albumId" value="${albumId}"/>
				</td>
			</tr>
			<tr>
				<td><s:submit value=" 确认 "></s:submit>
				</td>
				<td><s:reset value=" 重置 "></s:reset>
				</td>
			</tr>

		</table>

	</s:form>

</body>


</html>
