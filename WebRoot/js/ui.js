	function selectBanji() {
		document.getElementById("selectBanji").className = "div1";
	}
	
	function showMessage(message, studentNum){
		var errorUser = document.getElementById("errUser");
		var errorPassword = document.getElementById("errPassword");
		document.getElementById("u2").value = studentNum;
		if(message == "用户名不存在"){
			errorUser.className = "alertUserName";
		}else if(message == "密码错误"){
			errorUser.className = "hidden";
			errorPassword.className = "alertPassword";
		}
	}
	
	function checkMobile(modifyiedPhoneNum){ 
		if(!( /^1[3,5,8]\d{9}$/.test(modifyiedPhoneNum.value))){ 
			alert(modifyiedPhoneNum.value);
			modifyiedPhoneNum.focus(); 
			return false; 
		}else{
			return true;
		}
	} 
	
	function checkEmail(mail){
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(!(reg.test(mail.value))){
			alert(mail.value);
			mail.focus();
			return false;
		}else{
			return true;
		}
	}
	
	function validate(path) {
		var submit = $("#login");
		var studentNum = $("#u2");
		var password = $("#u3");
		var nullUser = $("#nullUser");
		var nullPassword = $("#nullPassword");
		if (studentNum.val() != "null" && studentNum.val() != ""
				&& password.val() != "null" && password.val() != "") {
			nullPassword.attr("class",  "hidden");
			nullUser.attr("class",  "hidden");
			$.ajax({
				type: "POST",
				url: path + "ajax/LoginAction!validateStudent",
				data: {studentNum : studentNum.val(), password : password.val()},
				//"studentNum="+ studentNum.val() + "&password=" + password.val(),
				success: function(msg){
//					if(msg == undefined)
//						$("#loginForm").attr("action", path + "LoginAction").submit();
					if(msg == "验证成功"){
						//$.post(path + "/LoginAction" ,{"studentNum" : studentNum.val(), "password" : password.val(), "userType" : "host"});
						$("#loginForm").attr("action", path + "LoginAction").submit();
					}else{
						if(msg == "用户名不存在"){
							nullUser.attr("class", "hidden");
							$("#errUser").attr("class", "alertUserName");
						}else{
							$("#errUser").attr("class", "hidden");
						}
						if(msg == "密码错误"){
							nullPassword.attr("class",  "hidden");
							$("#errPassword").attr("class", "alertPassword");
						}else{
							$("#errPassword").attr("class", "hidden");
						}
					}
				}
			});
		} else {
			if (studentNum.val() == "null" || studentNum.val() == "") {
				document.getElementById("errUser").className = "hidden";
				nullUser.attr("class", "alertUserName");
			} else {
				nullUser.attr("class", "hidden");
			}
			if (password.val() == "null" || password.val() == "") {
				document.getElementById("errPassword").className = "hidden";
				nullPassword.attr("class",  "alertPassword");
			} else {
				nullPassword.attr("class",  "hidden");
			}
		}
//		var submit = document.getElementById(id);
//		var studentNum = document.getElementById("u2");
//		var password = document.getElementById("u3");
//		var nullUser = document.getElementById("nullUser");
//		var nullPassword = document.getElementById("nullPassword");
//		if (studentNum.value != "null" && studentNum.value != ""
//				&& password.value != "null" && password.value != "") {
//			submit.type = "submit";
//			submit.submit;
//		} else {
//			if (studentNum.value == "null" || studentNum.value == "") {
//				document.getElementById("errUser").className = "hidden";
//				nullUser.className = "alertUserName";
//			} else {
//				nullUser.className = "hidden";
//			}
//			if (password.value == "null" || password.value == "") {
//				document.getElementById("errPassword").className = "hidden";
//				nullPassword.className = "alertPassword";
//			} else {
//				nullPassword.className = "hidden";
//			}
//		}
	}

	function modifyPhoneNum(studentNum,path){
		document.getElementById("phoneNumDiv").className = "hidden";
		var modifyiedPhoneNum = document.createElement("input");
		modifyiedPhoneNum.type = "text";
		modifyiedPhoneNum.value = "请输入手机号";
		modifyiedPhoneNum.onfocus = function(){
			if(modifyiedPhoneNum.value == "请输入手机号"){
				modifyiedPhoneNum.value = "";
			};
		};
		modifyiedPhoneNum.onblur = function(){
			if(modifyiedPhoneNum.value == ""){
				modifyiedPhoneNum.value = "请输入手机号";
			};
		};
		
		modifyiedPhoneNum.name = phoneNum;
		var tel = document.getElementById("tel");
		tel.appendChild(modifyiedPhoneNum);
		var save = document.createElement("input");
		save.value = "保存";
		save.type = "button";
		save.onclick = function() {
			if(checkMobile(modifyiedPhoneNum)){
				$.ajax({
					type: "POST",
					url: path + "/upload!modifyPhoneNum",
					data: "phoneNum=" + modifyiedPhoneNum.value + "&studentNum=" + studentNum,
					success: function(msg){
						msg = msg.replace("\"", "");
						msg = msg.replace("\"", "");
						tel.removeChild(save);
						tel.removeChild(modifyiedPhoneNum);
						tel.removeChild(cencel);
						document.getElementById("phoneNumDiv").className = "phoneNumDiv";
						document.getElementById("phoneNum").value = msg;
					}
				});
//				location.href = path + "/upload!modifyPhoneNum?phoneNum=" 
//				+ modifyiedPhoneNum.value + "&studentNum=" + studentNum;
			}else{
				alert("请输入正确的手机号码！");
			}
		};
		var cencel = document.createElement("input");
		cencel.value = "取消";
		cencel.type = "button";
		cencel.onclick = function() {
			tel.removeChild(save);
			tel.removeChild(modifyiedPhoneNum);
			tel.removeChild(cencel);
			document.getElementById("phoneNumDiv").className = "phoneNumDiv";
		};
		tel.appendChild(save);
		tel.appendChild(cencel);
	}
	
	function modifyMail(studentNum,path){
		document.getElementById("mailDiv").className = "hidden";
		var modifyedMail = document.createElement("input");
		modifyedMail.type = "text";
		modifyedMail.value = "请输邮箱地址";
		modifyedMail.onfocus = function(){
			if(modifyedMail.value == "请输邮箱地址"){
				modifyedMail.value = "";
			};
		};
		modifyedMail.onblur = function(){
			if(modifyedMail.value == ""){
				modifyedMail.value = "请输邮箱地址";
			};
		};
		
		modifyedMail.name = mail;
		var tel = document.getElementById("mail");
		tel.appendChild(modifyedMail);
		var save = document.createElement("input");
		save.value = "保存";
		save.type = "button";
		save.onclick = function() {
			if(checkEmail(modifyedMail)){//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				$.ajax({
					type: "POST",
					url: path + "/upload!modifyMail",
					data: "mail=" + modifyedMail.value + "&studentNum=" + studentNum,
					success: function(msg){
						msg = msg.replace("\"", "");
						msg = msg.replace("\"", "");
						tel.removeChild(save);
						tel.removeChild(modifyedMail);
						tel.removeChild(cencel);
						document.getElementById("mailDiv").className = "phoneNumDiv";
						document.getElementById("email").value = msg;
					}
				});
//				location.href = path + "/upload!modifyPhoneNum?phoneNum=" 
//				+ modifyiedPhoneNum.value + "&studentNum=" + studentNum;
			}else{
				alert("请输入正确的手机号码！");
			}
		};
		var cencel = document.createElement("input");
		cencel.value = "取消";
		cencel.type = "button";
		cencel.onclick = function() {
			tel.removeChild(save);
			tel.removeChild(modifyedMail);
			tel.removeChild(cencel);
			document.getElementById("mailDiv").className = "";
		};
		tel.appendChild(save);
		tel.appendChild(cencel);
	}
	
	function g(o) {
		return document.getElementById(o);
	}
	
	function changeCurrent(n) {
		g('nav_photo').className = 'nav_photo';
		g('nav_share').className = 'nav_share';
		g('nav_qunbbs').className = 'nav_qunbbs';
		g('nav_member').className = 'nav_member';
		g('nav_activity').className = 'nav_activity';
		g(n).className = n + ' current';
		//alert(g(n).className);

		document.getElementById("albums").className = "hidden";
		document.getElementById("banjiInfo").className = "hidden";
		document.getElementById("personerInfo").className = "hidden";
//		document.getElementById("members").className = "hidden";
		document.getElementById("tab").className = "hidden";
		//document.getElementById("tab1").className = "hidden";
		document.getElementById("personerInfo").className="hidden";
		document.getElementById("activitiesInfo").className="hidden";
		if (n == "nav_qunbbs") {
//			document.getElementById("members").className = "tab";
			document.getElementById("tab").className = "main_inner";
			//document.getElementById("tab1").className = "tab";
		}else if(n == "nav_photo"){
			document.getElementById("albums").className = "main_inner";
		}else if(n == "nav_share"){
			document.getElementById("banjiInfo").className = "main_inner";
		}else if(n == "nav_member"){
			document.getElementById("personerInfo").className = "main_inner";
		}else if(n == "nav_activity"){
			document.getElementById("activitiesInfo").className = "main_inner";
		}
	}
	
	function upload() {
		var selectAlbum = document.getElementById("selectAlbum");

		selectAlbum.className = "div1";

	}
	
	function cencel() {
		document.getElementById("selectAlbum").className = "hidden";
	}
	
	function selectCencel() {
		document.getElementById("selectBanji").className = "hidden";
	}
	
	function f_add(studentNum,path) {	
		var div1 = document.createElement("div");
		div1.id = "div1";
		div1.className = "div1";
		var div2 = document.createElement("div");
		div2.id = "div2";
		div2.className = "div2";
		div2.innerHTML = "相册名：";
		var btn = document.createElement("input");
		btn.type = "text";
		btn.id = "xcName";
		div2.appendChild(btn);
		var br1 = document.createElement("br");
		div2.appendChild(br1);
		var br2 = document.createElement("br");
		div2.appendChild(br2);
		var addbtn = document.createElement("input");
		addbtn.type = "button";
		addbtn.value = "创建";
		addbtn.onclick = function() {
			var xcName = document.getElementById("xcName");
			if (xcName.value == "") {
				alert("相册名不能为空！");
				xcName.focus();
				return;
			}
			location.href = path + "/AddAlbumAction?albumname="
					+ xcName.value + "&studentNum=" + studentNum;
			var div3 = document.getElementById("div1");
			div3.parentNode.removeChild(div3);
		};
		div2.appendChild(addbtn);
		var closebtn = document.createElement("input");
		closebtn.type = "button";
		closebtn.value = "关闭";
		closebtn.onclick = function() {
			var div3 = document.getElementById("div1");
			div3.parentNode.removeChild(div3);
		};
		div2.appendChild(closebtn);
		div1.appendChild(div2);
		document.body.appendChild(div1);
	}
	
	function showModifyAvatar(){
		document.getElementById("modifyAvatar").className = "div1";
	}
	
	function modifyAvatar(path, imgPath){
		var modifyForm = $("#modifyForm");
		modifyForm.ajaxSubmit({
			success: function(html, status){
//				alert("haha");
				$("#modifyAvatar").attr("class", "hidden");
//				var result = html.replace("<pre>", "");
//	            result = result.replace("</pre>", "");
//				var json = eval("("+result+")");
				$("#avatarId").attr("src", imgPath + html.path);
			}
		});
	}
	
	function checkStudentInfo(){
		var studentName = document.getElementById("studentName");
		var gender = document.getElementById("gender");
		var studentNum = document.getElementById("studentNum");
		var nullStudentName = document.getElementById("nullStudentName");
		var nullGender = document.getElementById("nullGender");
		var nullStudentNum = document.getElementById("nullStudentNum");
		var result = false;
		if(studentName.value != "null" && studentName.value != "" && gender.value != "null" && gender.value != "" && studentNum.value != "null" && studentNum.value != ""){
			result = true;
		}else {
			if(studentName.value == "null" || studentName.value == ""){
				nullStudentName.className = "";
				result = false;
			}else{
				nullStudentName.className = "hidden";
			}
			if(gender.value == "null" || gender.value == ""){
				nullGender.className = "";
				result = false;
			}else{
				nullGender.className = "hidden";
			}
			if(studentNum.value == "null" || studentNum.value == ""){
				nullStudentNum.className = "";
				result = false;
			}else{
				nullStudentNum.className = "hidden";
			}
		}
		return result;
	}
	
	function removeStudentInfo(path, id, optStudentNum){
		var tr = $(id).parent().parent();
		$.ajax({
			type: "POST",
			url: path + "/upload!deleteStudentInfo",
			data: "optStudentNum=" + optStudentNum,
			success: function(msg){
				alert(msg);
				tr.remove();
			}
			
		});
	}
	
	function saveStudentInfo(path){
		if(checkStudentInfo()){
			var addStudentInfoForm = $("#addStudentInfoForm");
//			var saveStudentInfoButton = document.getElementById("saveStudentInfoButton");
//			saveStudentInfoButton.type = "submit";
			addStudentInfoForm.ajaxSubmit({
			success: function(html, status){
					var json = html;
					//alert(html);
//					var result = html.replace("<pre>", "");
//		            result = result.replace("</pre>", "");
//					var json = eval("("+result+")");
					//$("#newStudent").attr("class", "");
					$("#addStudentForm").attr("class", "hidden");
					//alert(json.studentName);
					var members = document.getElementById("members");
					var tr = document.createElement("tr");
					var td1 = document.createElement("td");
					td1.innerHTML = json.studentName;
					tr.appendChild(td1);
					var td2 = document.createElement("td");
					td2.innerHTML = json.gender;
					tr.appendChild(td2);
					var td3 = document.createElement("td");
					td3.innerHTML = json.studentNum;
					tr.appendChild(td3);
					var td4 = document.createElement("td");
					td4.innerHTML = json.phoneNum;
					tr.appendChild(td4);
					var td5 = document.createElement("td");
					var button = document.createElement("input");
					button.type = "button";
					button.value = "删除";
					button.onclick = function(){
						removeStudentInfo(path, this, td3.innerHTML);
					}
					td5.appendChild(button);
					tr.appendChild(td5);
					members.appendChild(tr);
					//$("#members").after("<tr><td><span>" + json.studentName + "</span></td><td><span>" + json.gender +　"</span></td><td><span>" + json.studentNum + "</span></td><td><span>" + json.phoneNum + "</span></td></tr>");
//					$("#newStudentName").text(json.studentName);
//					$("#newGender").text(json.gender);
//					$("#newStudentNum").text(json.studentNum);
//					$("#newPhoneNum").text(json.phoneNum);
				}
			});
			//saveStudentInfoButton.submit;
			document.getElementById("checkStudentInfo").className = "hidden";
		}else{
			document.getElementById("checkStudentInfo").className = "";
			checkStudentInfo();
		}
	}
	
	function addStudentInfo(){
		document.getElementById("addStudentForm").className = "tab";
	}
	
	function addCencel(){
		document.getElementById("addStudentForm").className = "hidden";
		document.getElementById("checkStudentInfo").className = "hidden";
	}
	
	function showHonorMembers(){
		document.getElementById("honorMembers").className = "memberList";
	}
	
	function hideHonorMembers(){
		document.getElementById("honorMembers").className = "hidden";
	}
	
	function addHonorInfo(){
		document.getElementById("addHonorDiv").className = "div1";
	}
	
	function addActivityInfo(){
		document.getElementById("addActivityDiv").className = "div1";
	}
	
	function closeAddHonorInfoDiv(){
		document.getElementById("addHonorDiv").className = "hidden";
	}
	
	function closeAddActivitiyInfoDiv(){
		document.getElementById("addActivityDiv").className = "hidden";
	}
	
	function saveCode(tableid) {
		var winname = window.open('', 'newwin', '');

		var strHTML = document.getElementById(tableid).innerHTML;//根据表格的id获取内容

		winname.document.open('text/html', 'replace');

		winname.document.writeln(strHTML);

		winname.document.execCommand('saveas', '', '*.xls'); //保存
		winname.close();
	}
	
	function myprint(tableid) {
		var winname = window.open('', 'newwin', '');
		var strHTML = document.getElementById(tableid).innerHTML;

		winname.document.open('text/html', 'replace');

		winname.document.writeln(strHTML);
		winname.document.execCommand('print'); //打印
		winname.close();
	}
	
	var values = "";

	function checkItem(e) {
		showItem("item");
	}

	function showItem(itemName) {
		var aa = document.getElementsByName(itemName);
		for ( var i = 0; i < aa.length; i++) {
			if (aa[i].checked) {
				if (values == "") {
					values = aa[i].value;
				} else {
					values = values + "," + aa[i].value;
				}
			}
		}
		document.getElementById("honorStudentName").value = values;
		values = "";
	};
	
	function showMembers(id, size){
		for(i = 1; i <= size; i ++){
			if(document.getElementById(i) != null)
				document.getElementById(i).className = "hidden";
		}
		if(id == "" || document.getElementById(id) == null)
			return;
		document.getElementById(id).className = "";
	}
	
	function showMembers_1(id, size){
		for(i = 1; i <= size; i ++){
			if(document.getElementById(i+'_1') != null)
				document.getElementById(i+'_1').className = "hidden";
		}
		if(id == "" || document.getElementById(id+'_1') == null)
			return;
		document.getElementById(id+'_1').className = "";
	}

	function test(){
		var nullUser = $("#selection");
		alert(nullUser.val());
	}
	
	function addHonor(){
		$("#addHonorForm").submit();
//		ajaxSubmit({
//			success: function(html, status){
//				alert(html);
//				$("#honorAlbum").attr("class", "div1");
//			}
//		});
	}
	
	function addActivity(){
		$("#addActivityForm").submit();
//		ajaxSubmit({
//			success: function(html, status){
//				alert(html);
//				$("#honorAlbum").attr("class", "div1");
//			}
//		});
	}

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
	}
	
	function addMore_Honor() {
		var td = document.getElementById("moreHonorPhoto");
		var br = document.createElement("br");
		var input = document.createElement("input");

		var button = document.createElement("input");

		input.type = "file";
		input.name = "upload";

		button.type = "button";
		button.value = "删除";

		button.onclick = function() {
			td.removeChild(br);
			td.removeChild(input);
			td.removeChild(button);
		};

		td.appendChild(br);
		td.appendChild(input);
		td.appendChild(button);
	}
	
	function addMore_Honor_1(id) {
		var td = document.getElementById(id);
		var br = document.createElement("br");
		var input = document.createElement("input");

		var button = document.createElement("input");

		input.type = "file";
		input.name = "upload";

		button.type = "button";
		button.value = "删除";

		button.onclick = function() {
			td.removeChild(br);
			td.removeChild(input);
			td.removeChild(button);
		};

		td.appendChild(br);
		td.appendChild(input);
		td.appendChild(button);
	}
	
	function addMoreActivity() {
		var td = document.getElementById("moreActivity");
		var br = document.createElement("br");
		var input = document.createElement("input");

		var button = document.createElement("input");

		input.type = "file";
		input.name = "upload";

		button.type = "button";
		button.value = "删除";

		button.onclick = function() {
			td.removeChild(br);
			td.removeChild(input);
			td.removeChild(button);
		};

		td.appendChild(br);
		td.appendChild(input);
		td.appendChild(button);
	}
	
	function addMoreActivity_1(){
		var td = document.getElementById("moreActivity_1");
		var br = document.createElement("br");
		var input = document.createElement("input");

		var button = document.createElement("input");

		input.type = "file";
		input.name = "upload";

		button.type = "button";
		button.value = "删除";

		button.onclick = function() {
			td.removeChild(br);
			td.removeChild(input);
			td.removeChild(button);
		};

		td.appendChild(br);
		td.appendChild(input);
		td.appendChild(button);
	}
	
	function switchFunction(value){
		var switchFunction = document.getElementById("switchFunction");
		if("添加班级管理员" == value || "添加班级管理员" == switchFunction.ie9_value ){
			document.getElementById("addManagerDiv").className = "";
			document.getElementById("appoint").className = "hidden";
			document.getElementById("sumDiv").className = "hidden";
		}else if("统计班级获奖信息" == value || "统计班级获奖信息" == switchFunction.ie9_value ){
			sumHonor();
			document.getElementById("addManagerDiv").className = "hidden";
			document.getElementById("appoint").className = "hidden";
			document.getElementById("sumDiv").className = "";
		}else{
			appoint();
			document.getElementById("addManagerDiv").className = "hidden";
			document.getElementById("appoint").className = "";
			document.getElementById("sumDiv").className = "hidden";
		}
	}
	
	function modifyActivityInfo(activityTitle,activityDate,activityAdd,activityContent,activityId){
		$("#modifyActivityDiv").attr("class", "div1");
		$("#modify_activityTitle").attr("value", activityTitle);
		$("#modify_activityDate").attr("value", activityDate);
		$("#modify_activityAdd").attr("value", activityAdd);
		$("#modify_activityContent").attr("value", activityContent);
		$("#modify_activityId").attr("value", activityId); 
	}
	
	function closeModifyActivitiyInfoDiv(){
		$("#modifyActivityDiv").attr("class", "hidden");
	}
	
	function modifyActivity(){
		$("#modifyActivityForm").submit();
	}
	
	function removePhoto(ele, prefix, id){
		ele.className = "hidden";
		var photoId = prefix + id;
		var formId = "modify" + prefix.toUpperCase().substring(0,1) + prefix.substring(1,prefix.length-1) + "Form";
		var form = document.getElementById(formId);
		var input = document.createElement("input");
		input.type = "hidden";
		input.value = id;
		input.name = "photoId";
		form.appendChild(input);
		//document.getElementById("moidfiedPhoto").className="hidden";
		//document.getElementById("moidfiedPhotoDiv").className="hidden";
		//$("#photoId").attr("style", "");
		//document.getElementById(photoId).style="";
		document.getElementById(photoId).className="hidden";
	}
	
	function modifyHonorInfo(type){
		if("collective" == type)
			$("#modifyHonorDiv").attr("class", "div1");
		else
			$("#modifyHonorDiv_personer").attr("class", "div1");
	}
	