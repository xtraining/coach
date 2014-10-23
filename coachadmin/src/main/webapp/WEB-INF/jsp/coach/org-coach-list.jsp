<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/coach/org/coachList.htm?orgId=${orgId}">
	<input type="hidden" name="coachId" value="${searchDto.coachId}" />
	<input type="hidden" name="coachName" value="${searchDto.coachName}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/coach/org/coachList.htm?orgId=${orgId}" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					教练ID：<input type="text" name="coachId" class="digits" value="${searchDto.coachId}"/>
				</td>
				<td>
					姓名：<input type="text" name="coachName" value="${searchDto.coachName}"/>
				</td>
				<td>&nbsp;</td>
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
			<li><a class="add" href="${ctx}/coach/org/addCoach.htm?orgId=${orgId}" target="navTab" title="新增教练" rel="机构教练"><span>新增教练</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" align="left">教练ID</th>
				<th align="left">教练姓名</th>
				<th align="left">证据类型</th>
				<th align="left">证据号码</th>
				<th width="15%" align="left">创建时间</th>
			 	<th width="15%" align="left">操作</th> 
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.coachId}">
				<td>
					<input name="ids" value="${item.coachId}" type="checkbox">
				</td>
				<td>
					${item.coachId}
				</td>
				<td>
					<a href="${ctx}/coach/org/coachDetail.htm?orgCoachId=${item.orgCoachId}&orgId=${orgId}" target="navTab" title="修改教练" style="color:#00F;"  rel="机构教练">${item.coachName}</a>
				</td>
				<td>
				   <c:if test="${item.idType eq '0'}">
					身份证
				   </c:if>
				   <c:if test="${item.idType eq '1'}">
					护照
				   </c:if>
				</td>
				<td>${item.idNumber}</td>
				<td>${item.createTime}</td>
				<td>
					<a href="${ctx}/coach/org/deleteCoach.htm?orgCoachId=${item.orgCoachId}" target="navTab" title="删除教练" style="color:#00F;">删除教练</a>					  
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