<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ${title} </title>
</head>
<body>
	<style>
		.main{
			width:800px;
			height:600px;
			margin:0 auto;
			display:flex;
			justify-content:center;
			align-items:center;
			flex-direction:column;
		}
	</style>
	<div class="main">
		<img src="<%=request.getContextPath()%>/static/images/loading.gif"  height="160" width="200"/>
		<h3>${title }</h3>
		<p>${info }</p>
		<p><span>3</span>秒后跳转页面</p>
	</div>
	<script>var num=3;
		setInterval(function(){
			if(num == 0){
				location.href="<%=request.getContextPath()%>${httpUrl}"
			}
			num--;
			var spanDom = document.querySelector("span");
			spanDom.innerHTML = num;
		},1000)
	</script>
</body>
</html>