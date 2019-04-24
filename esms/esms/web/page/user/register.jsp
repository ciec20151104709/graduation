<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>电子超市注册</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="css/user/login.css" type="text/css" />

<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='jquery/jquery.page.js'></script>
<script language='javascript' src='bootstrap/js/bootstrap.min.js'></script>

<script language="javascript" src="bootstrap/js/bootbox.min.js"></script>
</head>
<body>
	<form>
		<div class="regist">
			<div class="regist_center">
				<div class="regist_top">
					<div class="left fl">会员注册</div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="regist_main center">
					<div class="username">
						用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input class="shurukuang"
							type="text" id="account" placeholder="请输入你的账号" />
					</div>
					<div class="username">
						用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input class="shurukuang"
							type="text" id="username" placeholder="请输入你的名字" />
					</div>
					<div class="username">
						密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input
							class="shurukuang" type="password" id="password"
							placeholder="请输入你的密码" />
					</div>
					<div class="username">
						性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:&nbsp;&nbsp; <select
							id="sex">
							<option value="3">未知</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>
					<div class="username">
						手&nbsp;&nbsp;机&nbsp;&nbsp;号:&nbsp;&nbsp;<input class="shurukuang"
							type="text" id="phoneNum" placeholder="请填写正确的手机号" />
					</div>
					<div class="username">
						地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:&nbsp;&nbsp;<input
							class="shurukuang" type="text" id="address" placeholder="请填写正确的地址" />
					</div>
				</div>
				<div class="regist_submit">
					<input class="submit" type="button" onclick="register();" value="立即注册">
				</div>

			</div>
		</div>
	</form>
	<script type="text/javascript">
		function register() {
			var account = $("#account").val();
			var username = $("#username").val();
			var password = $("#password").val();
			var sex = $("#sex").val();
			var phoneNum = $("#phoneNum").val();
			var address = $("#address").val();
			if (username == "" || password == "" || sex == "" || phoneNum == "" || address == "") {
				alert("请填写全部");
				return;
			}
			//通过ajax保存
			$.ajax({
				url : "user/register",
				type : "POST",
				dataType : "text",
				data : {
					account: account,
					username : username,
					password : password,
					sex: sex,
					phoneNum: phoneNum,
					address: address
				},
				success : function(ret) {
					if (ret == "ERROR") {
						alert("注册失败");
					} else {
						alert("注册成功");
						window.close();
					}
				},
				error : function(res) {
					alert("注册失败，请重新操作！");
				}
			});
		}
	</script>
</body>
</html>