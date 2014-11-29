<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>内容管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder" id="menuInforInSession">							
		</ul>
		<ul class="tree treeFolder">
			<li><a href="tabsPage.html" target="navTab">菜单</a>
				<ul>
					<li><a href="../story/top/list.htm" target="navTab" rel="首页推荐" title="首页推荐">首页推荐</a></li>
					<li><a href="../story/artifact/list.htm" target="navTab" rel="故事管理" title="故事管理">故事管理</a></li>
					<li><a href="../story/tag/list.htm" target="navTab" rel="标签管理" title="标签管理">标签管理</a></li>
					<li><a href="../story/task/list.htm" target="navTab" rel="任务管理" title="任务管理">任务管理</a></li>
				</ul>
			</li>
		</ul>
	</div>		
</div>