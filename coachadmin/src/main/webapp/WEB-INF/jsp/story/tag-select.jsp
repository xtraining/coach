<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<form id="pagerForm" method="post" action="${ctx}/story/tag/select.htm">
	<input type="hidden" name="name" value="${name}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx}/story/tag/select.htm" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>标签名称：</label>
				<input type="text" name="name" value=""/>
			</li>	 
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="10%" align="left">标签ID</th>
				<th align="left">名称</th>
				<th align="10%">权重</th>
				<th width="15%" align="left">状态</th>
				<th width="15%" align="left">选择带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.id}">
				<td>
					${item.id}
				</td>
				<td>
					${item.name}
				</td>
				<td>${item.tagOrder}</td>
				<td>
					<c:choose>
					<c:when test="${item.status == 0}">显示</c:when> 
					<c:when test="${item.status == 1}">隐藏</c:when> 
					</c:choose>
				</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'${item.id}', name:'${item.name}', temp:''})" title="Select">Select</a>
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
