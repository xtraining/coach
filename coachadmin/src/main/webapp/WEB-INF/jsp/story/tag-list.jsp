<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/story/tag/list.htm">
	<input type="hidden" name="name" value="${name}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/tag/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					标签名称：<input type="text" name="name" value=""/>
				</td>
			</tr>
		</table>		
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/story/tag/add.htm" target="dialog" mask="true" title="新增标签" rel="标签详情"><span>新增标签</span></a></li>
			<li><a title="确实要删除这些标签吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/story/tag/delete.htm" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%" align="left">标签ID</th>
				<th align="left">名称</th>
				<th align="10%">权重</th>
				<th width="15%" align="left">状态</th>
				<th width="15%" align="left">创建时间</th>
				<th width="15%" align="left">操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.id}">
				<td>
					<input name="ids" value="${item.id}" type="checkbox">
				</td>
				<td>
					${item.id}
				</td>
				<td>
					<a href="${ctx}/story/tag/edit.htm?tagId=${item.id}" target="dialog" mask="true" title="修改标签" rel="标签详情" style="color:#00F;">${item.name}</a>
				</td>
				<td>${item.tagOrder}</td>
				<td>
					<c:choose>
					<c:when test="${item.status == 0}">显示</c:when> 
					<c:when test="${item.status == 1}">隐藏</c:when> 
					</c:choose>
				</td>
				<td>
					${item.createTime}
				</td>
				<td>					
				  <a href="${ctx}/story/artifact/list.htm?tagId=${item.id}" target="navTab" rel="故事管理" title="故事管理" style="color:#00F;">关联故事</a>
				  &nbsp;&nbsp;
				  <a href="${ctx}/story/tagimage/list.htm?tagId=${item.id}" target="navTab" rel="图库管理" title="图库管理" style="color:#00F;">关联图片</a>	
				</td>
			</tr>
			</c:forEach>
			</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>共${totalCount}条</span>
		</div>		
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="20" pageNumShown="10" currentPage="${currentNum}"></div>
	</div>
</div>