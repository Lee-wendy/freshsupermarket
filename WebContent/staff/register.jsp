<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>员工 注册</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="Access-Control-Allow-Origin" content="*">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="../lib/layui-v2.5.5/css/layui.css" media="all">
		<!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		<style>
			body {background-image:url("../images/bg.jpg");height:100%;width:100%;}
        #container{height:100%;width:100%;}
        input:-webkit-autofill {-webkit-box-shadow:inset 0 0 0 1000px #fff;background-color:transparent;}
        .admin-login-background {width:300px;height:300px;position:absolute;left:50%;top:40%;margin-left:-150px;margin-top:-100px;}
        .admin-header {text-align:center;margin-bottom:20px;color:#ffffff;font-weight:bold;font-size:40px}
        .admin-input {border-top-style:none;border-right-style:solid;border-bottom-style:solid;border-left-style:solid;height:50px;width:300px;padding-bottom:0px;}
        .admin-input::-webkit-input-placeholder {color:#a78369}
        .layui-icon-username {color:#a78369 !important;}
        .layui-icon-username:hover {color:#9dadce !important;}
        .layui-icon-password {color:#a78369 !important;}
        .layui-icon-password:hover {color:#9dadce !important;}
        .admin-input-username {border-top-style:solid;border-radius:10px 10px 0 0;}
        .admin-input-verify {border-radius:0 0 10px 10px;}
        .admin-button {margin-top:20px;font-weight:bold;font-size:18px;width:300px;height:50px;border-radius:5px;background-color:#a78369;border:1px solid #d8b29f}
        .admin-icon {margin-left:260px;margin-top:10px;font-size:30px;}
        i {position:absolute;}
        .admin-captcha {position:absolute;margin-left:205px;margin-top:-40px;}
    </style>
	</head>
	<body>
		<div id="container">
			<div></div>
			<div class="admin-login-background">
				<div class="admin-header">
					<span>注册</span>
				</div>
				<form class="layui-form" action="<%=request.getContextPath()%>/staff/register" method="post">
					<div>
						<i class="layui-icon layui-icon-username admin-icon"></i>
						<input type="text" name="staffname" placeholder="请输入员工姓名" autocomplete="off" class="layui-input admin-input admin-input-username">
					</div>
					<div>
						<i class="layui-icon layui-icon-password admin-icon"></i>
						<input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input admin-input">
					</div>
					<div>
						<i class="layui-icon layui-icon-password admin-icon"></i>
						<input type="password" name="ackpassword" placeholder="确认密码" autocomplete="off" class="layui-input admin-input">
					</div>
					<div>
						<button class="layui-btn admin-button" lay-submit="" lay-filter="login">注 册</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
