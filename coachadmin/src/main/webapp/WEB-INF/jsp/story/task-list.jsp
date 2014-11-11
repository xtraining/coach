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
			<li><a class="add" href="${ctx}/story/task/add.htm" target="dialog" mask="true" width="600" height="400" title="新增"><span>新增</span></a></li>
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
				<th align="left">描述</th>
				<th width="20%" align="left">进度</th>
				<th width="10%" align="left">状态</th>
				<th width="15%" align="left">创建时间</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.taskId}">
				<td>
					<input name="ids" value="${item.taskId}" type="checkbox">
				</td>
				<td>
					${item.taskId}
				</td>
				<td>
				 	 <c:choose> 
  						<c:when test="${item.sourceFrom == 0}"> 
  						             喜马拉雅
  						</c:when>  
  						<c:otherwise>
  							未知
  						</c:otherwise>
  					 </c:choose>
				</td>
				<td>
					${item.url}
				</td>
				<td>
					${item.description}
				</td>
				<td>
					正在下载第${item.taskNum}个文件
				</td>
				<td>
				 	 <c:choose> 
  						<c:when test="${item.status == 0}"> 
  						             正常
  						</c:when>  
  						<c:otherwise>
  							未知
  						</c:otherwise>
  					 </c:choose>
				</td>
				<td>${item.createTime}</td>
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