<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>员工登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/font.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/weadmin.css">
    <script src="./lib/layui/layui.js" charset="utf-8"></script>

</head>
<body class="login-bg">
    
    <div class="login">
        <div class="message">员工登录</div>
        <div id="darkbannerwrap"></div>
        
        <form action ="<%=request.getContextPath()%>/login" method="post" class="layui-form" >
            <input name="staffname" placeholder="员工姓名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input class="loginin" value="登录" style="width:100%;" type="submit">
            <p align="right"><small>没有有账户？<a href="<%=request.getContextPath()%>/register"><font color="#01AAED">点此注册</a></p>
            <hr class="hr20" >
        </form>
    </div>
    <!-- 底部结束 -->
</body>
</html>