<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script>

</script>
    
<form id="pagerForm" method="post" action="${ctx}/sms/send/subtaskList.htm?sendTaskId=${sendTaskId}">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sms/send/subtaskList.htm?sendTaskId=${sendTaskId}" method="post">
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
				<th width="3%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" align="left">分组ID</th>
				<th width="25%" align="left">预计发送时间</th>
				<th width="10%" align="left">状态</th>
				<th width="10%" align="left">发送结果</th>
				<th width="10%" align="left">发送条数</th>
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
					${item.sendTime}
				</td>
				<td>
					<c:choose>
					<c:when test="${item.status == -1}"><div style='color:Peru'>取消</div></c:when> 
					<c:when test="${item.status == 0}">等待发送</c:when> 
					<c:when test="${item.status == 1}">暂停</c:when> 
					<c:when test="${item.status == 2}">已发送</c:when> 
					</c:choose>
				</td>
				<td>
				 	${item.resultCode}
				</td>
				<td>
				 	${item.sendNum}
				</td>
				<%-- <td>
					<c:choose>
					<c:when test="${item.resultCode == 0}"><div style='color:Peru'>立即发送</div></c:when> 
					<c:when test="${item.resultCode == 1}">定时发送</c:when> 
					</c:choose>
				</td> --%>
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