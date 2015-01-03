<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script>

</script>
    
<form id="pagerForm" method="post" action="${ctx}/sms/send/list.htm">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sms/send/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			<%-- 	<td>
				    <label>运营商：</label>
					<select name="spName" class="combox">
						<option value="">全部</option>	
						<c:forEach var="item" items="${spNameList}">
						<option value="${item}" <c:if test="${searchDto.spName == item}">selected</c:if>>${item}</option>		
						</c:forEach>
					</select>
				</td>	
				<td>
				    <label>省：</label>
					<select name="areaId" class="combox">
						<option value="">全部</option>	
						<option value="-1">其他</option>	
						<c:forEach var="item" items="${areaList}">
						<option value="${item.id}" <c:if test="${searchDto.areaId == item.id}">selected</c:if>>${item.name}</option>		
						</c:forEach>
					</select>
				</td>	 --%>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/sms/send/add.htm" target="navTab" title="新建"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些任务吗?" warn="请选择任务" target="selectedTodo" rel="ids" postType="string" href="${ctx}/sms/send/delete.htm" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="3%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" align="left">ID</th>
				<th width="25%" align="left">内容</th>
				<th width="10%" align="left">省市</th>
				<th width="10%" align="left">运营商</th>
				<th width="10%" align="left">分组名</th>
				<th width="10%" align="left">发送号码数</th>
				<th width="10%" align="left">类型</th>
				<th width="5%" align="left">状态</th>
				<th width="12%" align="left">创建时间</th>
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
					${item.message}
				</td>
				<td>
				 	${item.areaName}
				</td>
				<td>
					${item.spName}
				</td>
				<td>
					${item.tagName}
				</td>
				<td>
					<a href="${ctx}/sms/send/subtaskList.htm?sendTaskId=${item.id}" target="navTab" title="发送分组" rel="发送分组" style="color:#00F;">${item.sendNum}</a>
				</td>
				<td>
					<c:choose>
					<c:when test="${item.sendType == 0}"><div style='color:Peru'>立即发送</div></c:when> 
					<c:when test="${item.sendType == 1}">定时发送</c:when> 
					</c:choose>
				</td>
				<td>
					<c:choose>
					<c:when test="${item.status == 0}">正常</c:when> 
					<c:when test="${item.status == 1}">取消</c:when> 
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