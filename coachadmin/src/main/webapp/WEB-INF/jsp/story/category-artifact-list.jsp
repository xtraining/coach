<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">

function viewFile(url, title){
	var timenow = new Date().getTime(); 
	window.open(url, title,'height=700,width=1100,top=50,left=50,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes, status=yes'); 
}
function saveOrder(orderSeq, itemId){
	var idsStr = "";
	var keywordOrderArr = document.getElementsByName("artifactCategoryOrder");
	var keywordOrder = keywordOrderArr[orderSeq].value;
	idsStr = idsStr.substring(0, idsStr.length-1);
	var urlStr = "${ctx}/story/category/saveOrder.htm?itemId="+itemId+"&categoryId=${searchDto.categoryId}&categoryOrder="+keywordOrder;
	$.ajax({
			type:'POST',
			url:urlStr,
			data:"",//序列化表单里所有的内容
			success: function(data){
				dialogAjaxDone({"statusCode":"200", "message":"保存成功。", "navTabId":"", "forwardUrl":"", "callbackType":"", "rel":""});	
							
			}
			});
}
</script>
<form id="pagerForm" method="post" action="${ctx}/story/artifact/listForCategory.htm">
	<input type="hidden" name="title" value="${searchDto.title}" />
	<input type="hidden" name="type" value="${searchDto.type}" />
	<input type="hidden" name="categoryId" value="${searchDto.categoryId}" />
	<input type="hidden" name="categoryName" value="${searchDto.categoryName}" />
	<input type="hidden" name="tagId" value="${searchDto.tagId}" />
	<input type="hidden" name="tagName" value="${searchDto.tagName}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/artifact/listForCategory.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					分类ID：<input type="text" name="categoryId" class="digits" readonly value="${searchDto.categoryId}"/>
				</td>
				<td>
					分类名称：<input type="text" name="categoryName" readonly value="${searchDto.categoryName}"/>
				</td>
				<td>
					故事名称&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" name="title" value="${searchDto.title}"/>
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%" align="left">故事ID</th>
				<th align="left">名称</th>
				<th width="10%" align="left">时长(秒)</th>
				<!-- <th width="15%" align="left">类型</th> -->
				<th width="15%" align="left">文件名</th>
				<th width="10%" align="left">状态</th>
				<th width="25%" align="left">操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}" varStatus="varSta">
			<tr target="sid_user" rel="${item.id}">
				<td>
					<input name="ids" value="${item.id}" type="checkbox">
				</td>
				<td>
					${item.id}
				</td>
				<td>
					<a href="${ctx}/story/artifact/edit.htm?artifactId=${item.id}" target="navTab" title="故事详情" style="color:#00F;">${item.title}</a>
				</td>
				<td>
					${item.duration}
				</td>
				<%-- <td>
					<c:choose>
					<c:when test="${item.type == 0}"><div style='color:Peru'>专辑</div></c:when> 
					<c:when test="${item.type == 1}">单集</c:when> 
					</c:choose>
				</td> --%>
				<td>
					${item.fileName}
				</td>
				<td>
					<c:choose>
					<c:when test="${item.status == 0}">显示</c:when> 
					<c:when test="${item.status == 2}">隐藏</c:when> 
					</c:choose>
				</td>
				<td>					
				  <a href="javascript:viewFile('${item.fileUrl}', '试听')" style="color:#00F;">试听</a>
				  &nbsp;&nbsp;
				  <a href="javascript:viewFile('${item.imageUrl}', '缩略图')" style="color:#00F;">缩略图</a>
				  &nbsp;&nbsp;
				  <input type="text" name="artifactCategoryOrder" size="5" class="digits required" maxlength="5" value="${item.artifactCategoryOrder}"/>
				  <a href="javascript:saveOrder(${varSta.index}, ${item.id});" style="color:#00F;">保存权重</a>
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