<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智勤教育后台管理系统</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" /> 
<link href="<%=path %>/static_resource/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path %>/static_resource/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path %>/static_resource/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=path %>/static_resource/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="<%=path %>/static_resource/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="<%=path %>/static_resource/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<!-- <style type="text/css">
	#header{height:85px}
	#leftside, #container, #splitBar, #splitBarProxy{top:90px}
</style> -->


<script src="<%=path %>/static_resource/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/jquery.form.min.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<script src="<%=path %>/static_resource/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.database.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.history.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%=path %>/static_resource/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="<%=path %>/static_resource/bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="<%=path %>/static_resource/js/dwz.regional.zh.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	DWZ.init("<%=path %>/static_resource/js/dwz.frag.xml", {
		loginUrl:"login.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
			var menuItemsInSession ='${sessionScope.menuInforInSession}';
			$('#menuInforInSession').html(menuItemsInSession);
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="#">标志</a>
				<ul class="nav">	
					<li><a href="modules/sys-user!editMyself.action" target="navTab" title="修改用户">欢迎您：${sessionScope.sys_user_in_session.realName}</a></li>
					<li><a href="../coach/logout.htm">退出</a></li>
				</ul>				
			</div>

		<!-- 	<div id="navMenu">
				<ul>
					<li class="selected"><a href="../coach/coach/menu.htm"><span>教练宝</span></a></li>
					<li><a href="sidebar_2.html"><span>智勤云校</span></a></li>
					<li><a href="../story/story/menu.htm"><span>智勤C端</span></a></li>
				</ul>
			</div> -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>内容管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder" id="menuInforInSession">							
						</ul>
						<ul class="tree treeFolder">
							<li><a href="tabsPage.html" target="navTab">菜单</a>
								<ul>
									<!-- <li><a href="../coach/org/list.htm" target="navTab" rel="机构管理" title="机构管理">机构管理</a></li>
									<li><a href="../coach/coach/list.htm" target="navTab" rel="教练管理" title="教练管理">教练管理</a></li>
									<li><a href="../coach/coach/unbindCoachList.htm" target="navTab" rel="待绑定教练管理" title="待绑定教练管理">待绑定教练管理</a></li> -->
									<li><a href="../story/top/list.htm" target="navTab" rel="首页推荐" title="首页推荐">首页推荐</a></li>
									<li><a href="../story/category/list.htm" target="navTab" rel="分类管理" title="分类管理">分类管理</a></li>
									<li><a href="../story/artifact/list.htm" target="navTab" rel="故事管理" title="故事管理">故事管理</a></li>
									<li><a href="../story/keyword/list.htm" target="navTab" rel="关键字管理" title="关键字管理">关键字管理</a></li>
									<!-- <li><a href="../story/tag/list.htm" target="navTab" rel="标签管理" title="标签管理">标签管理</a></li> -->
									<li><a href="../story/tagimage/list.htm" target="navTab" rel="图库管理" title="图库管理">图库管理</a></li>
									<li><a href="../story/task/list.htm" target="navTab" rel="任务管理" title="任务管理">任务管理</a></li>
								</ul>
							</li>
						</ul>
					</div>				
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">							
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">							
						</div>						
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2014智勤教育</div>

</body>
</html>