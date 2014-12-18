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

function assginCategory(){
	var url = "${ctx}/story/artifact/assignCategory.htm";
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
	$.pdialog.open(url+"?artifactIds="+idsStr, "设置分类", "设置分类", options);
}

function viewFile(url, title){
	var timenow = new Date().getTime(); 
	window.open(url, title,'height=700,width=1100,top=50,left=50,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes, status=yes'); 
}
</script>
<form id="pagerForm" method="post" action="${ctx}/story/artifact/list.htm">
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
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/artifact/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					名称&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" name="title" value="${searchDto.title}"/>
				</td>
				<td>
				    <label>有无分类：</label>
					<select name="hasCategory" class="combox">
						<option value="">全部</option>	
						<option value="0" <c:if test="${searchDto.hasCategory == 0}">selected</c:if>>无分类</option>		
						<option value="1" <c:if test="${searchDto.hasCategory == 1}">selected</c:if>>有分类</option>	
					</select>
				</td>	
				<td>
					<label>分类：</label>
					<select name="categoryId" id="categoryId" class="combox">
						<option value="">全部</option>	
						<c:forEach var="item" items="${categories}">
						<option value="${item.id}" <c:if test="${searchDto.categoryId == item.id}">selected</c:if>>${item.name}</option>		
						</c:forEach>
						</select>
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
			<li><a class="add" href="${ctx}/story/artifact/add.htm" target="navTab" title="新增故事" rel="故事详情"><span>新增故事</span></a></li>
			<%-- <li><a class="add" href="${ctx}/story/artifact/addAlbum.htm" target="navTab" title="新增专辑" rel="故事详情"><span>新增专辑</span></a></li> --%>
			<!-- <li><a class="edit" href="javascript:assginTag();"><span>批量设置标签</span></a></li>  -->
			<li><a class="edit" href="javascript:assginCategory();"><span>批量设置分类</span></a></li> 
			<li><a title="确实要删除这些内容吗?" warn="请选择故事" target="selectedTodo" rel="ids" postType="string" href="${ctx}/story/artifact/delete.htm" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="6%" align="left">故事ID</th>
				<th width="20%" align="left">名称</th>
				<th width="15%" align="left">分类</th>
				<th width="10%" align="left">播放次数</th>
				<th width="8%" align="left">时长(秒)</th>
		<!-- 		<th width="8%" align="left">类型</th> -->
				<th width="15%" align="left">文件名</th>
				<th width="8%" align="left">状态</th>
				<th width="10%" align="left">操作</th>
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
					<a href="${ctx}/story/artifact/edit.htm?artifactId=${item.id}" target="navTab" title="故事详情" style="color:#00F;">${item.title}</a>
				</td>
				<td>
					${item.categoryName}
				</td>
				<td>
					${item.hitNum}
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