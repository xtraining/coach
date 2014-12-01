<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function viewImage(url){
	var timenow = new Date().getTime(); 
	window.open(url,'Tag Image','height=700,width=1100,top=50,left=50,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes, status=yes'); 
}
</script>
<form id="pagerForm" method="post" action="${ctx}/story/tagimage/list.htm">
	<input type="hidden" name="tagId" value="${searchDto.tagId}" />
	<input type="hidden" name="tagName" value="${searchDto.tagName}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/tagimage/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					标签ID：<input type="text" name="tagId" class="digits" value="${searchDto.tagId}"/>
				</td>
				<td>
					标签名称：<input type="text" name="tagName" value="${searchDto.tagName}"/>
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
			<li><a class="add" href="${ctx}/story/tagimage/add.htm" target="navTab" title="新增图片" rel="图片详情"><span>新增图片</span></a></li>
			<li><a title="确实要删除这些图片吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/story/tagimage/delete.htm" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%" align="left">图片ID</th>
				<th width="40%" align="left">图片URL</th>
				<th>所属标签</th>
				<th width="20%">操作</th>
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
					<a href="javascript:viewImage('${item.url}')" style="color:#00F;">${item.url}</a>
				</td>
				<td>${item.tagNameList}</td>
				<td>
				 <a href="${ctx}/story/tagimage/assignTag.htm?imageId=${item.id}" target="navTab" rel="设置标签" title="设置标签" style="color:#00F;">设置标签</a>
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