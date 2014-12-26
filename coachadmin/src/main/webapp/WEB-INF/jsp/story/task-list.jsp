<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script>

function assginCategory(){
	var url = "${ctx}/story/task/acceptWithCategory.htm";
	var idsStr = "";
	var idElement = document.getElementsByName("ids");
	var length = idElement.length;
	for(var i = 0; i < length; i++){
		if(idElement[i].checked && idElement[i].value != ""){
			idsStr += idElement[i].value + ",";
		}
	}
	if(idsStr == ""){
		alertMsg.warn("请选择下载任务。");
		return;
	}
	idsStr = idsStr.substring(0, idsStr.length-1);
	var options = "{height:300, width:580, minH:40, minW:50, total:20, max:false, mask:true, resizable:true, drawable:true, maxable:false,minable:false,fresh:false}";
	$.pdialog.open(url+"?taskIds="+idsStr, "批量入库-设置分类", "批量入库-设置分类", options);
}

</script>
    
<form id="pagerForm" method="post" action="${ctx}/story/task/list.htm">
	<input type="hidden" name="keyword" value="${keyword}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/task/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					任务关键字：<input type="text" name="keyword" class="" value="${keyword}"/>
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
			<li><a class="add" href="${ctx}/story/task/add.htm?sourceFrom=0" target="dialog" mask="true" title="喜马拉雅"><span>喜马拉雅</span></a></li>
			<li><a class="add" href="${ctx}/story/task/add.htm?sourceFrom=1" target="dialog" mask="true" width="800" title="蜻蜓FM"><span>蜻蜓FM</span></a></li>
			<li><a class="edit" href="javascript:assginCategory();"><span>批量分类入库</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些任务吗?" warn="请选择任务" target="selectedTodo" rel="ids" postType="string" href="${ctx}/story/task/delete.htm" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" align="left">任务ID</th>
				<th width="10%" align="left">来源</th>
				<th align="left">地址</th>
				<th width="12%" align="left">创建时间</th>
				<th width="15%" align="left">下载状态(成功数/总数)</th>
				<th width="15%" align="left">入库状态(已入库数/总数)</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.id}">
				<td>
					<input name="ids" value="${item.id}" type="checkbox">
				</td>
				<td>
					<a href="${ctx}/story/task/detail.htm?taskId=${item.id}&sourceFrom=${item.sourceFrom}" target="navTab" title="任务详情" rel="任务详情" style="color:#00F;">${item.id}</a>
				</td>
				<td>
				 	 <c:choose> 
  						<c:when test="${item.sourceFrom == 0}"> 
  						             喜马拉雅
  						</c:when>  
  						<c:when test="${item.sourceFrom == 1}"> 
  						             蜻蜓FM
  						</c:when> 
  						<c:otherwise>
  							未知
  						</c:otherwise>
  					 </c:choose>
				</td>
				<td>
					${item.url}
				</td>
				<td>${item.createTime}</td>
				<td>
					${item.downloadedNum} / ${item.totalNum}
				</td>
				<td>
					${item.importedNum} / ${item.totalNum}
					<c:choose> 
  						<c:when test="${item.totalNum == 0}"> 
  						             无下载任务
  						</c:when>  
  						<c:when test="${item.totalNum == item.importedNum}"> 
  						             全部入库
  						</c:when> 
  						<c:otherwise>
  							<font color="red">部分入库</font>
  						</c:otherwise> 
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