<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<form id="pagerForm" method="post" action="${ctx}/story/artifact/select.htm">
	<input type="hidden" name="title" value="${searchDto.title}" />
	<input type="hidden" name="type" value="${searchDto.type}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${ctx}/story/artifact/select.htm" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					名称&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" name="title" value="${searchDto.title}"/>
				</td>
				<%-- <td>
				    <label>类型：</label>
					<select name="type" class="combox">
						<option value="">全部</option>	
						<option value="0" <c:if test="${searchDto.type == 0}">selected</c:if>>专辑</option>		
						<option value="1" <c:if test="${searchDto.type == 1}">selected</c:if>>单集</option>	
					</select>
				</td>	 --%>
				<td>
					分类ID：<input type="text" name="categoryId" class="digits" value="${searchDto.categoryId}"/>
				</td>
				<td>
					分类名称：<input type="text" name="categoryName" value="${searchDto.categoryName}"/>
				</td>
				<%-- <td>
					标签ID：<input type="text" name="tagId" class="digits" value="${searchDto.tagId}"/>
				</td>
				<td>
					标签名称：<input type="text" name="tagName" value="${searchDto.tagName}"/>
				</td> --%>
			</tr>
		</table>	
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
				<th width="10%" align="left">故事ID</th>
				<th align="left">名称</th>
			<!-- 	<th width="15%" align="left">类型</th> -->
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
					${item.title}
				</td>
			<%-- 	<td>
					<c:choose>
					<c:when test="${item.type == 1}">单集</c:when> 
					<c:when test="${item.type == 0}">专辑</c:when> 
					</c:choose>
				</td> --%>
				<td>
					<c:choose>
					<c:when test="${item.status == 0}">显示</c:when> 
					<c:when test="${item.status == 2}">隐藏</c:when> 
					</c:choose>
				</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'${item.id}', title:'${item.title}', temp:''})" title="Select">Select</a>
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
