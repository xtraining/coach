<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/story/top/list.htm">
	<input type="hidden" name="name" value="${name}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/top/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					名称：<input type="text" name="name" value=""/>
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
			<li><a class="add" href="${ctx}/story/top/add.htm" target="navTab" title="新增推荐" rel="推荐详情"><span>新增推荐</span></a></li>
			<li><a title="确实要删除这些推荐内容吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/story/top/delete.htm" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%" align="left">合集ID</th>
				<th align="left">名称</th>
				<th align="10%">权重</th>
				<!-- <th width="15%" align="left">类型</th> -->
				<th width="15%" align="left">开始时间</th>
				<th width="15%" align="left">状态</th>
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
					<a href="${ctx}/story/top/edit.htm?topId=${item.id}" target="navTab" title="推荐详情" rel="推荐详情" style="color:#00F;">${item.name}</a>
				</td>
				<td>${item.topOrder}</td>
				<%-- <td>
					<c:choose>
					<c:when test="${item.type == 0}">故事</c:when> 
					<c:when test="${item.type == 1}">书</c:when> 
					</c:choose>
				</td> --%>
				<td>${item.startTime}</td>
				<td>
					<c:choose>
					<c:when test="${item.status == 0}">显示</c:when> 
					<c:when test="${item.status == 1}">隐藏</c:when> 
					</c:choose>
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