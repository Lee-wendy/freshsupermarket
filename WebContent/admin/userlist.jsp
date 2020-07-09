<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>生鲜助手后台管理系统</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/font.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/weadmin.css">
		<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
		<!--[if lt IE 9]>
	      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
	      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	</head>

	<body>
		<div class="weadmin-nav">
			<span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">管理员管理</a>
        <a>
          <cite>管理员列表</cite></a>
      </span>
			<a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
				<i class="layui-icon" style="line-height:30px">ဂ</i></a>
		</div>
		<div class="weadmin-body">
			<div class="layui-row">
				<form class="layui-form layui-col-md12 we-search">
					<div class="layui-inline">
						<input class="layui-input" placeholder="开始日" name="start" id="start">
					</div>
					<div class="layui-inline">
						<input class="layui-input" placeholder="截止日" name="end" id="end">
					</div>
					<div class="layui-inline">
						<input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
					</div>
					<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
				</form>
			</div>
			<div class="weadmin-block">
				<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
				<button class="layui-btn" onclick="WeAdminShow('添加用户','./add.html')"><i class="layui-icon"></i>添加</button>
				<span class="fr" style="line-height:40px">共有数据：88 条</span>
			</div>
			<table class="layui-table">
				<thead>
					<tr>
						<th>
							<div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
						</th>
						<th>用户编号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>手机号</th>
						<th>邮箱</th>
						<th>所在城市</th>
						<th>注册时间</th>
						<th>是否会员</th>
						<th>会员截止时间</th>
						<th>操作</th>
				</thead>
				<tbody>
				<c:forEach items="${userlist}" var="item" varStatus="i">  
					<tr>
						<td>
							<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
						</td>
						<td>${item.usercode }</td>
						<td>${item.name }</td>
						<td>${item.sex }</td>
						<td>${item.phone }</td>
						<td>${item.email }</td>
						<td>${item.city }</td>
						<td>${item.registerdate }</td>
						<td class="td-status">
							<span class="layui-btn layui-btn-normal layui-btn-xs">${item.isvip }</span></td >
						<td>${item.vipdeadline }</td>
						<td>
							<a title="编辑" href="<%=request.getContextPath()%>/admin/useredit?usercode=${item.usercode}">
								<i class="layui-icon">&#xe642;</i>
							</a>
							<a title="删除" href="<%=request.getContextPath()%>/admin/userdelete?usercode=${item.usercode}">
								<i class="layui-icon">&#xe640;</i>
							</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page">
				<div>
				<c:if test ="${page!='1'}">
					<a class="prev" href="<%=request.getContextPath()%>/admin/userlist?page=${page-1}">&lt;&lt;</a>
				</c:if>
					<span class="current">${page}</span>
				<c:if test ="${(page+1)<=allpage}">
					<a class="num" href="<%=request.getContextPath()%>/admin/userlist?page=${page+1}">${page+1}</a>
				</c:if>
				<c:if test ="${(page+2)<allpage}">
					<a class="num" href="<%=request.getContextPath()%>/admin/userlist?page=${page+2}">${page+2}</a>
				</c:if>
				<c:if test ="${page>allpage}">
					<a class="num" href="<%=request.getContextPath()%>/admin/userlist?page=${allpage}">${allpage}</a>
				</c:if>
				<c:if test ="${(page+1)<=allpage}">
					<a class="next" href="<%=request.getContextPath()%>/admin/userlist?page=${page+1}">&gt;&gt;</a>
				</c:if>
				</div>
			</div>
		</div>
		<script src="<%=request.getContextPath()%>/lib/layui/layui.js" charset="utf-8"></script>
    	<script src="<%=request.getContextPath()%>/static/js/eleDel.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>