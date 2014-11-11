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
					<li><a href="../coach/org/list.htm" target="navTab" rel="机构管理" title="机构管理">机构管理</a></li>
					<li><a href="../coach/coach/list.htm" target="navTab" rel="教练管理" title="教练管理">教练管理</a></li>
					<li><a href="../coach/coach/unbindCoachList.htm" target="navTab" rel="待绑定教练管理" title="待绑定教练管理">待绑定教练管理</a></li>
				</ul>
			</li>
		</ul>
	</div>		
</div>