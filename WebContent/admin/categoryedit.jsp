<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>修改生鲜类别</title>
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
		<div class="weadmin-body">
			<form class="layui-form" action="<%=request.getContextPath()%>/admin/addcategory" method="post">
				<div class="layui-form-item">
					<label for="L_usercode" class="layui-form-label"   >
		                <span class="we-red"></span>类别编号
		            </label>
					<div class="layui-input-inline">
						<input type="text" id="L_categorycode" value="${categorycode }" name="categorycode" disabled="disabled" lay-verify="required|nikename" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_username" class="layui-form-label">
					    <span class="we-red"></span>类别名称
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_categoryname" value="${item.categoryname }" name="categoryname" lay-verify="required|nikename" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label" >
		                <span class="we-red"></span>类别描述
		            </label>
					<div class="layui-input-inline">
						<input type="text" id="Lc_describe" value="${items.categorydescribe }" name="categorydescribe" lay-verify="categorydescribe" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_repass" class="layui-form-label"></label>
					<button class="layui-btn" lay-filter="add" lay-submit="">修改</button>
					<input type="hidden" name="dataId" id="dataId" value="" />
				</div>
			</form>
		</div>
		<script src="<%=request.getContextPath()%>/lib/layui/layui.js" charset="utf-8"></script>
		<script type="text/javascript">
			layui.extend({
				admin: '{/}../../static/js/admin'
			});
			layui.use(['form', 'jquery', 'admin','layer'], function() {
				var form = layui.form,
					$ = layui.jquery,
					admin = layui.admin,
					layer = layui.layer;

				//自定义验证规则
				form.verify({
					nikename: function(value) {
						if(value.length < 5) {
							return '昵称至少得5个字符啊';
						}
					}
				});
				//页面初始化加载
				$(function(){
					setTimeout(function(){
						frameVal();
					},100);
				});  
				function frameVal(){
					var dataId = $('input[name="dataId"]').val();
					var index = parent.layer.getFrameIndex(window.name); 
				    parent.layui.jquery("#memberList tr").each(function(){
				    	if($(this).attr('data-id')==dataId){
				    		console.log($(this));
				    		var tdArr=$(this).children('td');
				    		var username = tdArr.eq(2).text(); //姓名
							var sex = tdArr.eq(3).text(); //性别
							var phone = tdArr.eq(4).text(); //电话
							var email = tdArr.eq(5).text(); //邮箱
							var address = tdArr.eq(6).text(); //地址
							
							$('input[name="username"]').val(username);
							console.log("sex:"+sex);
							$('input[name="sex"][value="'+sex+'"]').attr("checked",true);
							$('input[name="phone"]').val(phone);
							$('input[name="email"]').val(email);
							$('input[name="address"]').val(address);
							form.render();
				    	}
				    });
				}
				//监听提交
				form.on('submit(add)', function(data) {
					console.log(data);
					//发异步，把数据提交给php
					layer.alert("增加成功", {
						icon: 6
					}, function() {
						// 获得frame索引
						var index = parent.layer.getFrameIndex(window.name);
						//关闭当前frame
						parent.layer.close(index);
					});
					return false;
				});

			});
		</script>
	</body>

</html>