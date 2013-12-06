<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tk.sweetvvck.utils.*"%>
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
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/setday.js"></script>
<script type="text/javascript" src="<%=path%>/js/ui.js"></script>
<title>${student.studentName}欢迎来到${banji.banjiName}班级小站</title>
<link href="<%=path%>/style/style.css" rel="stylesheet" type="text/css">
<link href="style/forms.css" rel="stylesheet" type="text/css">
<!--<%=path%>/style/-->
</head>

<body onload="" class="Homebody">
	<div class="app_group_header" id="group_header_container">
		<div class="app_group_inner">
			<h1 class="group_name">
				<a href="javascript:void(0)"> <i class="i_group i_group_logo"></i>
					<span> ${banji.banjiName}</span> </a>
			</h1>
			<div class="nav">
				<a id="nav_photo" class="nav_photo current" title="班级相册"
					onClick="javascript:changeCurrent(this.id)"> <i
					class="i_group album"></i> <span class="visual_none">班级相册</span> </a>
				<a id="nav_qunbbs" class="nav_qunbbs" title="班级成员"
					onClick="changeCurrent(this.id)"> <i
					class="i_group i_menu_qunbbs"></i> <span class="visual_none">班级成员</span> </a> 
				<a id="nav_activity" class="nav_activity" title="班级活动"
					onClick="javascript:changeCurrent(this.id)"> <i
					class="i_group activity"></i> <span class="visual_none">班级活动</span> </a>
				<a id="nav_share" class="nav_share" title="班级信息"
					onClick="changeCurrent(this.id)"> <i class="i_group class_info"></i>
					<span class="visual_none">班级信息</span> </a> 
				<a id="nav_member"
					class="nav_member" title="个人信息" onClick="changeCurrent(this.id)">
					<i class="i_group i_menu_member"></i> <span class="visual_none">个人信息</span>
				</a>
			</div>
				<a href="RedirectAction" style="float: right; font-size: 12px; color: white; margin-bottom: 20px;">安全退出</a>
		</div>
	</div>
	<div class="main">
		<div class="hidden" id="activitiesInfo">
			<table width="100%" cellspacing="0" cellpadding="3" border="0" class="datelist">
				<tr class="datelisthead">
					<c:if test="${activities != '[]' && activities != null}">
						<td width="20%;" height="30px">活动名称：</td>
						<td>活动时间：</td>
						<td>活动地点：</td>
						<td>活动内容：</td>
					</c:if>
					<c:if test="${activities == '[]' || activities == null}">
						<td>暂无活动信息~</td>
					</c:if>
					<td width="40px"><c:if test="${student.isManager && userType != 'visitor'}">
							<input type="button" value="添加"
								onClick="javascript:addActivityInfo()" />
						</c:if>
					</td>
				</tr>
				<c:forEach items="${activities}" var="activity">
					<tr>
						<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showActivityAction?albumId=' + ${activity.activityId });">${activity.activityTitle}</td>
						<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showActivityAction?albumId=' + ${activity.activityId });">${activity.activityDate}</td>
						<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showActivityAction?albumId=' + ${activity.activityId });">${activity.activityAdd}</td>
						<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showActivityAction?albumId=' + ${activity.activityId });">${activity.activityContent}</td>
						<td width="40px">
							<c:if test="${student.isManager}">
								<input type="button" value="修改"
									onclick="modifyActivityInfo('${activity.activityTitle}','${activity.activityDate}','${activity.activityAdd}','${activity.activityContent}','${activity.activityId }')" />
								<input type="button" value="删除"
									onclick="document.location.href='<%=path%>/upload!deleteActivity?ActivityId=${activity.activityId }&studentNum=${student.studentNum}'" />
							</c:if>
						</td>
						
					</tr>
					<tr><td colspan="5">
								<div id="modifyActivityDiv" class="hidden">
			<div id="modifyActivityDivInner" class="div3">
				<div class="titlebar">
					<h1>修改活动</h1>
					<a class="close" href="javascript:closeModifyActivitiyInfoDiv();"> 关闭</a>
				</div>
				<form action="upload!addActivity" method="post" id="modifyActivityForm" enctype="multipart/form-data">
					<input type="hidden" name="studentNum" value="${student.studentNum }"/>
					<input type="hidden" name="activityId" id="modify_activityId"/>
					<table style="width: 80%;height: 80%;padding: 10;" border="0">
						<tr height="15%">
							<td width="100px">活动名称：</td>
							<td>
								<input type="text" name="activityTitle" id="modify_activityTitle"
									style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr height="15%">
							<td width="100px">活动时间：</td>
							<td>
								<input type="text" name="activityDate" id="modify_activityDate"
									style="width: 300px; height: 25px" readOnly
								onfocus="SelectDate(this,'yyyy-MM-dd',310,-25)"  />
							</td>
						</tr>
						<tr height="15%">
							<td width="100px">活动地点：</td>
							<td>
								<input type="text" name="activityAdd" id="modify_activityAdd"
									style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr height="15%">
							<td width="100px">活动内容：</td>
							<td>
								<input type="text" name="activityContent" id="modify_activityContent"
									style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr>
							<td>
								活动照片：
							</td>
							<td>
								<c:forEach items="${activity.activityAlbum.photos }" var="id">
									<div id="activity_${id.id }" >
										<div style="width:130px; height:150px; float:left; margin:4px; border:#EEEEEE solid 3px; text-align:center; display:inline; overflow:hidden;">
											<a id="moidfiedPhoto"
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
											<a href="javascript:void(0)" onclick="removePhoto(this, 'activity_', '${id.id}')">删除</a>
										</div>
									</div>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>上传照片：</td>
							<td id="moreActivity_1">
									<input type="file" name="upload" /> 
									<input type="button" value="上传更多..." onclick="addMoreActivity_1()">
							</td>
						</tr>
					</table>
					<div align="left" style="padding-left: 100px;">
						<input type="button" onclick="modifyActivity()" value="确认修改" />
					</div>
				</form>
			</div>
		</div>
		</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="addActivityDiv" class="hidden">
			<div id="addActivityDivInner" class="div3">
				<div class="titlebar">
					<h1>添加活动</h1>
					<a class="close" href="javascript:closeAddActivitiyInfoDiv();"> 关闭</a>
				</div>
				<form action="upload!addActivity" method="post" id="addActivityForm" enctype="multipart/form-data">
					<input type="hidden" name="studentNum" value="${student.studentNum }"/>
					<table style="width: 80%;height: 80%;padding: 10;" border="0">
						<tr height="15%">
							<td width="100px">活动名称：</td>
							<td>
								<input type="text" name="activityTitle"
									style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr height="15%">
							<td width="100px">活动时间：</td>
							<td>
								<input type="text" name="activityDate"
									style="width: 300px; height: 25px" readOnly
								onfocus="SelectDate(this,'yyyy-MM-dd',310,-25)"  />
							</td>
						</tr>
						<tr height="15%">
							<td width="100px">活动地点：</td>
							<td>
								<input type="text" name="activityAdd"
									style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr height="15%">
							<td width="100px">活动内容：</td>
							<td>
								<input type="text" name="activityContent"
									style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr>
							<td>上传照片：</td>
							<td id="moreActivity">
									<input type="file" name="upload" /> 
									<input type="button" value="上传更多..." onclick="addMoreActivity()">
							</td>
						</tr>
					</table>
					<div align="left" style="padding-left: 100px;">
						<input type="button" onclick="addActivity()" value="确认添加" />
					</div>
				</form>
			</div>
		</div>

		<div id="addHonorDiv" class="hidden">
			<div id="addHonorDivInner" class="div3">
				<div class="titlebar">
					<h1>添加奖励</h1>
					<a class="close" href="javascript:closeAddHonorInfoDiv();"> 关闭</a>
				</div>
				<form action="upload!addHonors" method="post" id="addHonorForm" enctype="multipart/form-data">
					<input type="hidden" name="studentNum" value="${student.studentNum }"/>
					<table style="width: 80%;height: 80%;padding: 10;" border="0">
						<tr height="15%">
							<td width="100px">比赛名称：</td>
							<td><input type="text" name="competitionName"
								style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr>
							<td>比赛级别：</td>
							<td>
								<select name='level'>
									<option value="国家级">国家级</option>
									<option value="市级">市级</option>
									<option value="校级">校级</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>荣誉名称：</td>
							<td><input type="text" name="honorName"
								style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr>
							<td>荣誉类型：</td>
							<td><select name='honorType'
								onblur="javascript:if(this.value=='collective'){document.getElementById('honorStudentName').value='全班同学';document.getElementById('member').style.display='none';}else{document.getElementById('member').style.display='table-row';};">
									<option value="personer">个人荣誉</option>
									<c:if test="${student.isManager}">
										<option value="collective">集体荣誉</option>
									</c:if>
							</select>
							</td>
						</tr>
						<tr>
							<td>指导老师：</td>
							<td><input type="text" name="instructor"
								style="width: 300px; height: 25px" />
							</td>
						</tr>
						<tr height="15%" id="member" style="display: table-row;">
							<td width="100px">参赛人员：</td>
							<td><input type="text" readonly="readonly"
								onmouseout="javascript:document.getElementById('honorMembers').className='hidden';"
								onclick="javascript:document.getElementById('honorMembers').className='memberList';"
								name="honorStudentName" id="honorStudentName"
								style="width: 300px; height: 25px">
								<div class="hidden" id="honorMembers"
									onmouseover="javascript:document.getElementById('honorMembers').className='memberList';"
									onmouseout="javascript:document.getElementById('honorMembers').className='hidden';">
									<c:forEach items="${banji.students}" var="honorStudent">
										<input
											onmouseover="javascript:document.getElementById('honorMembers').className='memberList';"
											onfocus="javascript:document.getElementById('honorMembers').className='memberList';"
											type="checkbox" name="item"
											value="${honorStudent.studentName}" onclick="checkItem(this)">
										${honorStudent.studentName}&nbsp;
									</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td>获奖时间：</td>
							<td><input type="text" name="prizeDate"
								style="width: 300px; height: 25px" readOnly
								onfocus="SelectDate(this,'yyyy-MM-dd',310,-25)">
							</td>
						</tr>
						<tr>
							<td>上传文件：</td>
							<td id="moreHonorPhoto">
									<input type="file" name="upload" /> 
									<input type="button" value="上传更多..." onclick="addMore_Honor()">
							</td>
						</tr>
						
					</table>
					<div align="left" style="padding-left: 100px;">
						<input type="button" onclick="addHonor()" value="确认添加" />
					</div>
				</form>
			</div>
		</div>
		<div class="hidden" id="tab">
			<div id="print">
				<table width="100%" id="members" height="97"
					border="1" align="center" cellpadding="0" cellspacing="1">
					<tr>
						<td>姓名</td>
						<td>性别</td>
						<td>学号</td>
						<td>职务</td>
						<td>联系方式</td>
						<input value='${student.isManager}' style="display: none;" />
						<td width="40px"><c:if test="${student.isManager && userType != 'visitor'}">
								<input type="button" value="添加"
									onclick="javascript:addStudentInfo()" />
							</c:if>
						</td>
					</tr>
					<tr id="checkStudentInfo" class="hidden">
						<td >
							<span class="hidden" id="nullStudentName" style="color: red">
								学生姓名不能为空
							</span>
						</td>
						<td>
							<span class="hidden" id="nullGender" style="color: red">
								学生性别不能为空
							</span>
						</td>
						<td>
							<span class="hidden" id="nullStudentNum" style="color: red">
								学生学号不能为空
							</span>
						</td>
					</tr>
					<tr id="addStudentForm" class="hidden">
						<form action="upload!addStudentInfo" method="post" id="addStudentInfoForm">
							<td>
								<input style="width: 100%;height: 100%;" id="studentName" name="studentName" type="text" />
							</td>
							<td>
								<input style="width: 100%;height: 100%;" id="gender" name="gender" type="text" />
							</td>
							<td>
								<input name="optStudentNum" type="text" id="studentNum" style="width: 100%;height: 100%;" /> 
								<input type="hidden" name="studentNum" value="${student.studentNum}" />
							</td>
							<td>
								<input name="position" type="text" style="width: 100%;height: 100%;" />
							</td>
							<td>
								<input name="phoneNum" type="text" style="width: 100%;height: 100%;" />
							</td>
							<td width="40px">
								<input type="button" value="保存" onclick="javascript:saveStudentInfo('<%=path%>')" id="saveStudentInfoButton"/> 
								<input type="button" value="取消" onclick="javascript:addCencel()" />
							</td>
						</form>
					</tr>
					<s:iterator value="#request.banji.students" id="studentItem">
						<tr style="border: thin;">
							<td width="25%"><s:property
									value="#studentItem.studentName" />
							</td>
							<td width="25%"><s:property value="#studentItem.gender" />
							</td>
							<td width="25%"><s:property
									value="#studentItem.studentNum" />
							</td>
							<td width="25%"><s:property value="#studentItem.position" />
							</td>
							<td width="25%"><s:property value="#studentItem.phoneNum" />
							</td>
							<%-- <td width="40px">
								<c:if test="${student.isManager}">
									<input type="button" value="删除"
										onclick="removeStudentInfo('<%=path%>', this, '<s:property value="#studentItem.studentNum"/>')" />
								</c:if>
							</td> --%>
						</tr>
<%-- document.location.href='<%=path%>/upload!deleteStudentInfo?optStudentNum=<s:property value="#studentItem.studentNum"/>&studentNum=${student.studentNum}' --%>
					</s:iterator>
					<tr id="newStudent" class="hidden">
						<td>
							<span id="newStudentName"></span>
						</td>
						<td>
							<span id="newGender"></span>
						</td>
						<td>
							<span id="newStudentNum"></span>
						</td>
						<td>
							<span id="newPhoneNum"></span>
						</td>
					</tr>
				</table>
			</div>
			<input id="saveAs" type="button" value="另存为 Excel"
				onclick="saveCode('print')" /> <input id="print" type="button"
				value="打印表格" onClick="myprint('print')" />
		</div>

		<div class="hidden" id="banjiInfo">
			<table width="100%" border="1">
				<tr>
					<td width="15%" height="30px">班级名称：</td>
					<td width="85%">${banji.banjiName}</td>
				</tr>
				<tr>
					<td width="15%" height="30px">班级邮箱：</td>
					<td width="85%">${banji.banjiMail}</td>
				</tr>
				<tr>
					<td height="30px">班级人数：</td>
					<td><s:property value="#request['banji.students'].size()" />
					</td>
				</tr>
				<tr>
					<td height="100%">班级荣誉：</td>
					<td>
						<table width="100%"  border="1" >
							<tr>
								<td width="15%">集体荣誉：</td>
								<td>
									<table width="100%" cellspacing="0" cellpadding="3" border="0" class="datelist">
										<tr class="datelisthead">
											<c:if test="${collectiveHonor != '[]' && collectiveHonor != null}">
												<td width="20%;" height="30px">荣誉名称：</td>
												<td>等级：</td>
												<td>获奖名称：</td>
												<td>指导老师：</td>
												<td>获奖者：</td>
											</c:if>
											<c:if test="${collectiveHonor == '[]' || collectiveHonor == null}">
												<td>暂无集体获奖信息~</td>
											</c:if>
											<td width="40px"><c:if test="${student.isManager && userType != 'visitor'}">
													<input type="button" value="添加"
														onClick="javascript:addHonorInfo()" />
												</c:if>
											</td>
										</tr>
										<c:forEach items="${collectiveHonor}"
											var="collectiveHonor">
											<tr height="100%;">
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${collectiveHonor.honorId});">
													${collectiveHonor.competitionName}
												</td>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${collectiveHonor.honorId});">
													${collectiveHonor.level}
												</td>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${collectiveHonor.honorId});">
													${collectiveHonor.honorName}
												</td>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${collectiveHonor.honorId});">
													${collectiveHonor.instructor}
												</td>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${collectiveHonor.honorId});">
													全班同学
												</td>
												<td width="40px">
													<c:if test="${student.isManager}">
														<input type="button" value="修改"
															onclick="modifyHonorInfo('collective')" />
														<input type="button" value="删除"
															onclick="document.location.href='<%=path%>/upload!deleteHonors?honorId=${collectiveHonor.honorId}&studentNum=${student.studentNum}'" />
													</c:if>
												</td>
											</tr>
											<tr>
												<td colspan="5">
													<div id="modifyHonorDiv" class="hidden">
														<div id="modifyHonorDivInner" class="div3">
															<div class="titlebar">
																<h1>修改奖励</h1>
																<a class="close" href="javascript:void(0)" onclick="javascript:document.getElementById('modifyHonorDiv').className='hidden'"> 关闭</a>
															</div>
															<form action="upload!addHonors" method="post" id="modifyHonorForm" enctype="multipart/form-data">
																<input type="hidden" name="studentNum" value="${student.studentNum }"/>
																<input type="hidden" id="modify_honorId" name="honorId" value="${collectiveHonor.honorId}">
																<table style="width: 80%;height: 80%;padding: 10;" border="0">
																	<tr height="15%">
																		<td width="100px">比赛名称：</td>
																		<td><input type="text" name="competitionName"
																			style="width: 300px; height: 25px" value="${collectiveHonor.competitionName}"/>
																		</td>
																	</tr>
																	<tr>
																		<td>比赛级别：</td>
																		<td>
																			<select name='level'>
																				<option value="国家级">国家级</option>
																				<option value="市级">市级</option>
																				<option value="校级">校级</option>
																			</select>
																		</td>
																	</tr>
																	<tr>
																		<td>荣誉名称：</td>
																		<td><input type="text" name="honorName" value="${collectiveHonor.honorName}"
																			style="width: 300px; height: 25px" />
																		</td>
																	</tr>
																	<tr>
																		<td>荣誉类型：</td>
																		<td>
																			<input name="honorType" type="hidden" readonly="readonly" value="collective"/>
																			<input type="text" readonly="readonly" value="集体荣誉"/>
																		</td>
																	</tr>
																	<tr>
																		<td>指导老师：</td>
																		<td><input type="text" name="instructor" value="${collectiveHonor.instructor}"
																			style="width: 300px; height: 25px" />
																		</td>
																	</tr>
																	<tr>
																		<td>
																			荣誉证书：
																		</td>
																		<td>
																			<!-- <s:iterator value="<s:property value='#collectiveHonor.album.photos'/>" var="id">
																				<s:property value='#id'/>
																			</s:iterator> -->
																			<c:forEach items="${collectiveHonor.album.photos}" var="id">
																				<div id="honor_${id.id }">
																					<div style="width:130px; height:150px; float:left; margin:4px; border:#EEEEEE solid 3px; text-align:center; display:inline; overflow:hidden;">
																						<a id="moidfiedPhoto_1"
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
																							<a href="javascript:void(0)" onclick="removePhoto(this, 'honor_', '${id.id}')">删除</a>
																					</div>
																				</div>
																			</c:forEach>
																		</td>
																	</tr>
																	<tr  id="member" style="display: table-row;">
																		<td width="100px">参赛人员：</td>
																		<td>
																			<input type="text" readonly="readonly" value="全班同学"/>
																		</td>
																	</tr>
																	<tr>
																		<td>获奖时间：</td>
																		<td><input type="text" name="prizeDate" value="${collectiveHonor.prizeDate}"
																			style="width: 300px; height: 25px" readOnly 
																			onfocus="SelectDate(this,'yyyy-MM-dd',310,-25)">
																		</td>
																	</tr>
																	
																	<tr>
																		<td>上传文件：</td>
																		<td id="moreHonorPhoto_1">
																				<input type="file" name="upload" /> 
																				<input type="button" value="上传更多..." onclick="addMore_Honor_1('moreHonorPhoto_1')">
																		</td>
																	</tr>
																	
																</table>
																<div align="left" style="padding-left: 100px;">
																	<input type="submit" value="确认修改" />
																</div>
															</form>
														</div>
													</div>
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
							<tr>
								<td>个人荣誉：</td>
								<td>
									<table width="100%" cellspacing="0" cellpadding="3" border="0" class="datelist">
										<tr class="datelisthead">
											<c:if test="${personerHonor != '[]' && personerHonor != null}">
												<td width="20%;" height="30px">荣誉名称：</td>
												<td>等级：</td>
												<td>获奖名称：</td>
												<td>指导老师：</td>
												<td>获奖者：</td>
											</c:if>
											<c:if test="${personerHonor == '[]' ||  personerHonor == null}">
												<td>暂无个人获奖信息~</td>
											</c:if>
											<td width="40px"><c:if test="${userType != 'visitor'}">
													<input type="button" value="添加"
														onClick="javascript:addHonorInfo()" />
												</c:if>
											</td>
										</tr>
										<c:forEach items="${personerHonor}" var="personerHonor1">
											<tr>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${personerHonor1.honorId });">${personerHonor1.competitionName}</td>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${personerHonor1.honorId });">${personerHonor1.level}</td>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${personerHonor1.honorId });">${personerHonor1.honorName}</td>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${personerHonor1.honorId });">${personerHonor1.instructor}</td>
												<td style="cursor: pointer;" onclick="javascript:window.open('<%=path%>/showHonorAction?albumId=' + ${personerHonor1.honorId });">
													<c:forEach var="honorStudent"
														items="${personerHonor1.students}">
				          								${honorStudent.studentName},
				          							</c:forEach>
												</td>
												<td width="40px">
													<c:if test="${student.isManager}">
														<input type="button" value="修改"
																	onclick="modifyHonorInfo('personer')" />
														<input type="button" value="删除"
															onclick="document.location.href='<%=path%>/upload!deleteHonors?honorId=${personerHonor1.honorId }&studentNum=${student.studentNum}'" />
													</c:if>
												</td>
											</tr>
											<tr>
												<td colspan="5">
													<div id="modifyHonorDiv_personer" class="hidden">
														<div id="modifyHonorDivInner" class="div3">
															<div class="titlebar">
																<h1>修改奖励</h1>
																<a class="close" href="javascript:void(0)" onclick="javascript:document.getElementById('modifyHonorDiv_personer').className='hidden'"> 关闭</a>
															</div>
															<form action="upload!addHonors" method="post" id="modifyHonorForm" enctype="multipart/form-data">
																<input type="hidden" name="studentNum" value="${student.studentNum }"/>
																<input type="hidden" id="modify_honorId" name="honorId" value="${personerHonor1.honorId}">
																<table style="width: 80%;height: 80%;padding: 10;" border="0">
																	<tr height="15%">
																		<td width="100px">比赛名称：</td>
																		<td><input type="text" name="competitionName"
																			style="width: 300px; height: 25px" value="${personerHonor1.competitionName}"/>
																		</td>
																	</tr>
																	<tr>
																		<td>比赛级别：</td>
																		<td>
																			<select name='level'>
																				<option value="国家级">国家级</option>
																				<option value="市级">市级</option>
																				<option value="校级">校级</option>
																			</select>
																		</td>
																	</tr>
																	<tr>
																		<td>荣誉名称：</td>
																		<td><input type="text" name="honorName" value="${personerHonor1.honorName}"
																			style="width: 300px; height: 25px" />
																		</td>
																	</tr>
																	<tr>
																		<td>荣誉类型：</td>
																		<td>
																			<input name="honorType" type="hidden" readonly="readonly" value="personer"/>
																			<input type="text" readonly="readonly" value="个人荣誉"/>
																		</td>
																	</tr>
																	<tr>
																		<td>指导老师：</td>
																		<td><input type="text" name="instructor" value="${personerHonor1.instructor}"
																			style="width: 300px; height: 25px" />
																		</td>
																	</tr>
																	<tr>
																		<td>
																			荣誉证书：
																		</td>
																		<td>
																			<!-- <s:iterator value="<s:property value='#collectiveHonor.album.photos'/>" var="id">
																				<s:property value='#id'/>
																			</s:iterator> -->
																			<c:forEach items="${personerHonor1.album.photos}" var="id">
																				<div id="honor_${id.id }">
																					<div style="width:130px; height:150px; float:left; margin:4px; border:#EEEEEE solid 3px; text-align:center; display:inline; overflow:hidden;">
																						<a id="moidfiedPhoto_1"
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
																							<a href="javascript:void(0)" onclick="removePhoto(this, 'honor_', '${id.id}')">删除</a>
																					</div>
																				</div>
																			</c:forEach>
																		</td>
																	</tr>
																	<tr  id="member" style="display: table-row;">
																		<td width="100px">参赛人员：</td>
																		<td>
																			<c:forEach var="honorStudent"
																				items="${personerHonor1.students}">
										          								${honorStudent.studentName},
										          							</c:forEach>
																		</td>
																	</tr>
																	<tr>
																		<td>获奖时间：</td>
																		<td><input type="text" name="prizeDate" value="${personerHonor1.prizeDate}"
																			style="width: 300px; height: 25px" readOnly 
																			onfocus="SelectDate(this,'yyyy-MM-dd',310,-25)">
																		</td>
																	</tr>
																	
																	<tr>
																		<td>上传文件：</td>
																		<td id="moreHonorPhoto_1">
																				<input type="file" name="upload" /> 
																				<input type="button" value="上传更多..." onclick="addMore_Honor_1('moreHonorPhoto_1')">
																		</td>
																	</tr>
																	
																</table>
																<div align="left" style="padding-left: 100px;">
																	<input type="submit" value="确认修改" />
																</div>
															</form>
														</div>
													</div>
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div class="hidden" id="personerInfo">
			<s:if test="#{student.studentNum!=null}">
				<div id="labelForPersonerInfo">个人信息</div>
				<div class="avatar">
					<img id="avatarId"
						src='<%=request.getContextPath() + "/" + UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath") + "/"%>${avatar.path}'
						width="101" height="100"> <a style="color: blue; "
						href="javascript:showModifyAvatar()">[更改头像]</a>
				</div>
				<div class="personerInfo item">
					<div style="width:100px; float:left; font-size:16px;">
						<label for="banji" style="width:100px">所在班级：</label>
					</div>
					<input name="banji" type="text" value="${banji.banjiName}" disabled />
				</div>
				<div class="personerInfo item">
					<div style="width:100px; float:left; font-size:16px;">
						<label for="studentName" style="width:100px">姓 名：</label>
					</div>
					<input name="studentName" value="${student.studentName}"
						type="text" disabled />
				</div>
				<div class="personerInfo item">
					<div style="width:100px; float:left; font-size:16px;">
						<label for="studentNum">学 号：</label>
					</div>
					<input name="studentNum" value="${student.studentNum}" type="text"
						disabled />
				</div>
				<div class="personerInfo item">
					<div style="width:100px; float:left; font-size:16px;">
						<label for="gender">性 别：</label>
					</div>
					<input name="gender" value="${student.gender}" type="text" disabled />
				</div>
				<div id="tel" class="personerInfo item">
					<div style="width:100px; float:left; font-size:16px;">
						<label for="phoneNum">联系方式：</label>
					</div>
					<div id="phoneNumDiv" class="phoneNumDiv">
						<input id="phoneNum" name="phoneNum" value="${student.phoneNum}"
							type="text" disabled="disabled" /> <a id="modify"
							style="color: red;"
							href="javascript:modifyPhoneNum(${student.studentNum},'<%=path%>')">[更改联系方式]</a>
					</div>
				</div>
				<div id="mail" class="personerInfo item">
					<div style="width:100px; float:left; font-size:16px;">
						<label for="email">邮 箱：</label>
					</div>
					<div id="mailDiv" class="phoneNumDiv">
						<input id="email" name="mail" value="${student.mail}"
							type="text" disabled="disabled" /> <a id="modify"
							style="color: red;"
							href="javascript:modifyMail(${student.studentNum},'<%=path%>')">[更改邮箱]</a>
					</div>
				</div>
			</s:if>
			<s:else>
				<h1>您还未登录，请登录后再查看个人信息......</h1>
			</s:else>
			<!--  <div style="clear:both"/>通过清除float解决父div自适应子div高度的问题 -->
		</div>
		<div id="albums" class="main_inner">
			<div class="createAlbum">
				<s:if test="#{student.studentNum!=null}">
					<a href="javascript:upload()" onClick="javascript:upload()"
						class="btn_pic_upload"> <span class="icon_m icon_upload_m">+</span>
						<span class="text">上传照片</span> </a>

					<div class="create_photo">

						<a class="a" href="javascript:f_add(${student.studentNum },'<%=path%>')"><font
							size="4">新增相册</font> </a>
					</div>
				</s:if>
			</div>
			<c:if test="${albumList[0].albumId != null }">
				<div class="showAlbum">
					<s:iterator value="#request.albumList" id="id" status="st">
						<div
							style="width:130px; height:150px; float:left; margin:40 40 0 0px; border:gray solid 3px; text-align:center; display:inline; overflow:hidden;">
							<a href='<%=path + "/"%>RedirectActionToShowPhotoView?albumId=<s:property value="#id.albumId"/>'
								target='_blank'>
								<div
									style="width:120px; height:90px; margin:5px; overflow:hidden; clear:both">
									<img src='<%=request.getContextPath() + "/"
									+ UploadConfigurationRead.getInstance()
									.getConfigItem("uploadFilePath") + "/"%><s:property value="#id.photos.iterator.next.path"/>'
										width="120" height="90" border="0" />
								</div> 『 <s:property value="#id.albumname" /> 』<br /> <font
								color="#AAAAAA">[ <s:property
										value="#id.photos.iterator.next.uploadDate" /> ] </font> </a>
						</div>
						<!-- <div style="clear:both"/>通过清除float解决父div自适应子div高度的问题 -->
					</s:iterator>
				</div>
			</c:if>
			<c:if test="${albumList[0].albumId == null }">
				<h1>暂无相册......</h1>
			</c:if>
			<%-- <s:if test="#{banji.albums==null}">
			</s:if>
			<s:else>
				fjksadfjdlsfjdslkfjkl
			</s:else> --%>
		</div>
	</div>

	<div id="selectAlbum" class="hidden">
		<div class="div2">
			<form action="AddAlbumAction">
				选择相册： <select name="albumId" id="selection">
					<s:iterator value="#request.albumList" id="album">
						<option selected="selected"
							value="<s:property value="#album.albumId"/>">
							<s:property value="#album.albumname" />
						</option>
					</s:iterator>
				</select> 或： <a class="a" href="javascript:f_add(${student.studentNum })"><font
					size="1">新增相册</font> </a> <br /> <br /> <input name="studentNum"
					value="${student.studentNum}" type="hidden" /> <input
					type="submit" value="确定" /> <input style="margin-left: 20px"
					type="button" value="取消" onClick="cencel()" />
			</form>
		</div>
	</div>

	<div id="modifyAvatar" class="hidden">
		<div class="div2">
			<s:form action="upload!modifyAvatar" theme="simple" method="post"
				enctype="multipart/form-data" style="padding-left: 50px;" id="modifyForm">

				<table align="center" border="1">
					<tr>
						<td>头像：</td>
						<td><s:file name="upload" id="uploadAvatar"></s:file> <input
							name="albumname" hidden="true" value="${albumname}" /> <input
							name="studentNum" hidden="true" value="${studentNum}" /> <input
							type="hidden" name="albumId" value="${albumId}" />
						</td>
					</tr>
					<tr>
						<td><input type="button" value=" 确认 " onclick="javascript:modifyAvatar('<%=basePath%>',
						'<%=request.getContextPath() + "/" + UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath") + "/"%>')">
						</td>
						<td>
							<input type="button" value="取消" onClick="javascript:document.getElementById('modifyAvatar').className='hidden'"/>
						</td>
					</tr>

				</table>

			</s:form>
		</div>
	</div>
	<%-- <div id="honorAlbum" class="hidden" >
		<div class="div2">
			<s:form action="upload.action" theme="simple" method="post"
				enctype="multipart/form-data">
	
				<table align="center" border="1">
					<tr>
						<td>上传文件${request.fileUploadErrorMessage}</td>
						<td id="more"><s:file name="upload"></s:file> 
							<input id="albumName" name="albumname" hidden="true" value=""/>
							<input name="studentNum" hidden="true" value="${studentNum}"/>
							<input type="button" value="上传更多..." onclick="addMore_Honor()">
							<input id="albumType" type="hidden" name="albumType" value=""/>
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
		</div>
	</div> --%>
</body>
</html>
