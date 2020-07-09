<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>生鲜助手</title>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/font.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/weadmin.css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/lib/layui/layui.js" charset="utf-8"></script>

	</head>

	<body>
		<!-- 顶部开始 -->
		<div class="container">
			<div class="logo">
				<a href="./index">生鲜助手</a>
			</div>
			<div class="left_open">
				<!-- <i title="展开左侧栏" class="iconfont">&#xe699;</i> -->
				<i title="展开左侧栏" class="layui-icon layui-icon-shrink-right"></i>
				
			</div>
			<ul class="layui-nav right" lay-filter="">
				<li class="layui-nav-item">
					<a href="javascript:;">${staffname}</a>
					<dl class="layui-nav-child">
						<!-- 二级菜单 -->
						<dd>
							<a onclick="WeAdminShow('个人信息','http://www.baidu.com')">个人信息</a>
						</dd>
						<dd>
						<a href="./login">切换账号</a>
						</dd>
						<dd>
							<a class="loginout" href="./login">退出</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item to-index">
					<a href="https://www.jiuwei.com/" target="_blank">前台首页</a>
				</li>
			</ul>

		</div>
		<!-- 顶部结束 -->
		<!-- 中部开始 -->
		<!-- 左侧菜单开始 -->
		<div class="left-nav"><div id="side-nav">
				<ul id="nav">
					<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe6b8;</i>
							<cite>人员管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="<%=request.getContextPath()%>/admin/userlist">
								
									<i class="iconfont">&#xe6a7;</i>
									<cite>用户信息</cite>

								</a>
							</li>
							<li>
								<a _href="<%=request.getContextPath()%>/pages/member/del.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>员工信息</cite>

								</a>
							</li>
							<li>
								<a href="javascript:;">
									<i class="iconfont">&#xe70b;</i>
									<cite>会员信息</cite>
									<i class="iconfont nav_right">&#xe697;</i>
								</a>
								<ul class="sub-menu">
									<li>
										<a _href="<%=request.getContextPath()%>/pages/member/addInput.html">
											<i class="iconfont">&#xe6a7;</i>
											<cite>输入框操作</cite>
										</a>
									</li>
									<li>
										<a _href="./pages/404.html">
											<i class="iconfont">&#xe6a7;</i>
											<cite>三级菜单演示</cite>
										</a>
									</li>
									<li>
										<a _href="./pages/404.html">
											<i class="iconfont">&#xe6a7;</i>
											<cite>导航菜单演示</cite>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe705;</i>
							<cite>生鲜管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="<%=request.getContextPath()%>/admin/categorylist">
									<i class="iconfont">&#xe6a7;</i>
									<cite>生鲜类别</cite>
								</a>
							</li>
							<li>
								<a _href="<%=request.getContextPath()%>/pages/article/category.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>商品信息</cite>
								</a>
							</li>
							<li>
								<a _href="<%=request.getContextPath()%>/pages/article/category.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>菜谱信息</cite>
								</a>
							</li>
							<li>
								<a _href="<%=request.getContextPath()%>/pages/article/category.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>菜谱推荐</cite>
								</a>
							</li>
						</ul>
					</li>
										<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe726;</i>
							<cite>优惠管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="<%=request.getContextPath()%>/pages/admin/role.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>优惠券</cite>
								</a>
							</li>
							<li>
								<a _href="<%=request.getContextPath()%>/pages/admin/cate.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>满折优惠</cite>
								</a>
							</li>
							<li>
								<a _href="<%=request.getContextPath()%>/pages/admin/rule.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>限时促销</cite>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe6ce;</i>
							<cite>商品采购</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="<%=request.getContextPath()%>/pages/order/list.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>采购列表</cite>
								</a>
							</li>
						</ul>
					</li>
										<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe723;</i>
							<cite>订单管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="<%=request.getContextPath()%>/pages/order/list.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>订单列表</cite>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<div id="side-nav"></div>
		</div>
		<!-- <div class="x-slide_left"></div> -->
		<!-- 左侧菜单结束 -->
		<!-- 右侧主体开始 -->
		<div class="page-content">
			<div class="layui-tab tab" lay-filter="wenav_tab" id="WeTabTip" lay-allowclose="true">
				<ul class="layui-tab-title" id="tabName">
					<li>我的桌面</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<iframe src='<%=request.getContextPath()%>/pages/welcome.html' frameborder="0" scrolling="yes" class="weIframe"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div class="page-content-bg"></div>
		<!-- 右侧主体结束 -->
		<!-- 中部结束 -->
		<!-- 底部开始 -->
		<div class="footer">
			<div class="copyright">Copyright ©2018 WeAdmin v1.0 All Rights Reserved</div>
		</div>
		<!-- 底部结束 -->
		<script type="text/javascript">
//			layui扩展模块的两种加载方式-示例
//		    layui.extend({
//			  admin: '{/}../../static/js/admin' // {/}的意思即代表采用自有路径，即不跟随 base 路径
//			});
//			//使用拓展模块
//			layui.use('admin', function(){
//			  var admin = layui.admin;
//			});
			layui.config({
				base: '<%=request.getContextPath()%>/static/js/'
				,version: '101100'
			}).use('admin');
			layui.use(['jquery', 'admin', 'menu'], function(){
				var $ = layui.jquery;
			});

		</script>
	</body>
	<!--Tab菜单右键弹出菜单-->
	<ul class="rightMenu" id="rightMenu">
        <li data-type="fresh">刷新</li>
        <li data-type="current">关闭当前</li>
        <li data-type="other">关闭其它</li>
        <li data-type="all">关闭所有</li>
    </ul>

</html>