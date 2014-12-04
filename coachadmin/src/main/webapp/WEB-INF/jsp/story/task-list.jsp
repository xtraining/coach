<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/story/task/list.htm">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/task/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					任务ID：<input type="text" name="coachId" class="digits" value=""/>
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
				<th width="10%" align="left">入库状态</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.id}">
				<td>
					<input name="ids" value="${item.id}" type="checkbox">
				</td>
				<td>
					<a href="${ctx}/story/task/detail.htm?taskId=${item.id}&sourceFrom=${item.sourceFrom}" target="navTab" title="任务详情" style="color:#00F;">${item.id}</a>
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
					${item.progress}
				</td>
				<td>
					${item.progress}
				</td>
				<td>	
				 	<%-- <c:choose> 
  						<c:when test="${item.status == 2}"> 
  						             已入库
  						</c:when>  
  						<c:otherwise>
						  <a href="${ctx}/story/task/accept.htm?taskId=${item.id}&downloadTaskId=0" target="navTab" title="入库" rel="入库" style="color:#00F;">入库</a>	
  						</c:otherwise>
  					 </c:choose>	 --%>	
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