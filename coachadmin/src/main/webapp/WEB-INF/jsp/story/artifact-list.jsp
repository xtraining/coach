<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function assginTag(){
	var url = "${ctx}/story/artifact/assignTag.htm";
	var idsStr = "";
	var idElement = document.getElementsByName("ids");
	var length = idElement.length;
	for(var i = 0; i < length; i++){
		if(idElement[i].checked && idElement[i].value != ""){
			idsStr += idElement[i].value + ",";
		}
	}
	if(idsStr == ""){
		alertMsg.warn("请选择故事。");
		return;
	}
	idsStr = idsStr.substring(0, idsStr.length-1);
	var options = "{height:300, width:580, minH:40, minW:50, total:20, max:false, mask:true, resizable:true, drawable:true, maxable:false,minable:false,fresh:false}";
	$.pdialog.open(url+"?artifactIds="+idsStr, "设置标签", "设置标签", options);
}

</script>
    
<form id="pagerForm" method="post" action="${ctx}/story/artifact/list.htm">
	<input type="hidden" name="title" value="${searchDto.title}" />
	<input type="hidden" name="type" value="${searchDto.type}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/artifact/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					名称&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" name="title" value="${searchDto.title}"/>
				</td>
				<td>
				    <label>类型：</label>
					<select name="type" class="combox">
						<option value="">全部</option>	
						<option value="0" <c:if test="${searchDto.type == 0}">selected</c:if>>专辑</option>		
						<option value="1" <c:if test="${searchDto.type == 1}">selected</c:if>>单集</option>	
					</select>
				</td>	
			</tr>
			<tr>
				<td>
					分类ID：<input type="text" name="categoryId" class="digits" value="${searchDto.categoryId}"/>
				</td>
				<td>
					分类名称：<input type="text" name="categoryName" value="${searchDto.categoryName}"/>
				</td>
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
			<li><a class="add" href="${ctx}/story/artifact/add.htm" target="navTab" title="新增故事" rel="故事详情"><span>新增故事</span></a></li>
			<li><a class="add" href="${ctx}/story/artifact/addAlbum.htm" target="navTab" title="新增专辑" rel="故事详情"><span>新增专辑</span></a></li>
			<li><a class="edit" href="javascript:assginTag();"><span>设置标签</span></a></li>
			<li><a title="确实要删除这些内容吗?" warn="请选择故事" target="selectedTodo" rel="ids" postType="string" href="${ctx}/story/artifact/delete.htm" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%" align="left">故事ID</th>
				<th align="left">名称</th>
				<th width="15%" align="left">时长(秒)</th>
				<th width="15%" align="left">类型</th>
				<th width="15%" align="left">文件名</th>
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
					<a href="${ctx}/story/artifact/edit.htm" target="navTab" title="故事详情" style="color:#00F;">${item.title}</a>
				</td>
				<td>
					${item.duration}
				</td>
				<td>
					<c:choose>
					<c:when test="${item.type == 0}"><a href="${ctx}/story/artifact/sublist.htm?artifactId=${item.id}" target="navTab" title="故事详情" style="color:#00F;">专辑</a></c:when> 
					<c:when test="${item.type == 1}">单集</c:when> 
					</c:choose>
				</td>
				<td>
					${item.fileName}
				</td>
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