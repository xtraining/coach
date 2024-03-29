<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智勤教育后台管理系统</title>
<link href="static_resource/themes/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="static_resource/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static_resource/js/jquery.form.min.js"></script>
<script type="text/javascript" src="static_resource/js/admin/login.js"></script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#"><img src="static_resource/themes/default/images/login_logo.png" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						
					</ul>
				</div>
				<h2 class="login_title"><img src="static_resource/themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="javascript:login();">
					<p>
						<label>用户名：</label>
						<input type="text" name="username" id="username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" id="password" size="20" class="login_input" />
					</p>
					<p>
						<label>验证码：</label>
						<input class="code" type="text" name="valificationCode" id="valificationCode" size="5" maxlength="6"/>
						<span  style="cursor:pointer" onclick="changeValidateCode($('#valificationCodeImg'))">
						<img id="valificationCodeImg"  src="login/capcha.htm" alt="验证码" width="80" height="30"/></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
					<br/>
					<span id="msg"></span>
				</form>
			</div>
			<div class="login_banner"><img src="static_resource/themes/default/images/login_banner.png" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
				<div class="login_inner">
					<!---p>您可以使用 随时查看当前的用户使用状况 ，随时查，随时改</p>
					<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
					<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p-->
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2014 www.hupu.com Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>