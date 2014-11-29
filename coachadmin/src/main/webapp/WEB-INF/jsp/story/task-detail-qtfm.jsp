<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/story/task/detail.htm?taskId=${taskId}&sourceFrom=${sourceFrom}">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/task/detail.htm?taskId=${taskId}&sourceFrom=${sourceFrom}" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					任务ID：${taskId}
				</td>
			</tr>
		</table>		
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">刷新</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="2000" layoutH="108">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="3%" align="left">下载ID</th>
				<th width="3%" align="left">类型</th>
				<th width="10%" align="left">标题</th>
				<th width="10%"  align="left">副标题</th>
				<th width="5%" align="left">时长(秒)</th>
				<th width="5%" align="left">状态</th>
				<th width="10%" align="left">创建时间</th>
				<th align="left">图片地址</th>
				<th align="left">音频地址</th>
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
					<c:choose> 
  						<c:when test="${item.type == 0}"> 
  						              封面
  						</c:when> 
  						<c:otherwise>
  							内容
  						</c:otherwise>
  					</c:choose>
  				</td>
				<td>
					${item.title}
				</td>
				<td>
					${item.subtitle}
				</td>
				<td>
					${item.duration}
				</td>
				<td>
				 	 <c:choose> 
  						<c:when test="${item.status == 0}"> 
  						             等待中
  						</c:when>  
  						<c:when test="${item.status == 1}"> 
  						             下载中&nbsp;&nbsp; <a href="${ctx}/story/task/redownload.htm?downloadTaskId=${item.id}" target="ajaxTodo" style="color:#00F;">重试</a>
  						</c:when> 
  						<c:when test="${item.status == 2}"> 
  						             保存中
  						</c:when> 
  						<c:when test="${item.status == 3}"> 
  						             完成
  						</c:when> 
  						<c:otherwise>
  							失败&nbsp;&nbsp; <a href="${ctx}/story/task/redownload.htm?downloadTaskId=${item.id}" target="ajaxTodo" style="color:#00F;">重试</a>
  						</c:otherwise>
  					 </c:choose>
				</td>
				<td>
					${item.createTime}
				</td>
				<td>
					${item.imageUrl}
				</td>
				<td>
					${item.fileUrl}
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