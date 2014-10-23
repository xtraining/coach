<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/coach/org/list.htm">
	<input type="hidden" name="orgId" value="${searchDto.orgId}" />
	<input type="hidden" name="orgName" value="${searchDto.orgName}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="orgListForm" id="orgListForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/coach/org/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					机构ID：<input type="text" name="orgId" class="digits" value="${searchDto.orgId}"/>
				</td>
				<td>
					名称：<input type="text" name="orgName" value="${searchDto.orgName}"/>
				</td>
				<!--td>
					<select name="searchMemberDto.status" class="combox">
						<option value="">会员状态</option>	
						<option value="0" <s:if test="%{#request.searchMemberDto.statusr == 0}">selected</s:if>>正常</option>		
						<option value="1" <s:if test="%{#request.searchMemberDto.status == 1}">selected</s:if>>删除</option>	
						<option value="2" <s:if test="%{#request.searchMemberDto.status == 2}">selected</s:if>>锁定</option>	
					</select>
				</td-->
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
			<li><a class="add" href="${ctx}/coach/org/add.htm" target="navTab" title="新增"><span>新增</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" align="left">机构ID</th>
				<th align="left">名称</th>
				<th width="15%" align="left">创建时间</th>
			 	<th width="15%" align="left">操作</th> 
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.orgId}">
				<td>
					<input name="ids" value="${item.orgId}" type="checkbox">
				</td>
				<td>
					${item.orgId}
				</td>
				<td>
					<a href="${ctx}/coach/org/detail.htm?orgId=${item.orgId}" target="navTab" title="修改机构" style="color:#00F;">${item.orgName}</a>
				</td>
				<td>${item.createTime}</td>
				<td>
					<a href="${ctx}/coach/org/delete.htm?orgId=${item.orgId}" target="ajaxTodo" title="确定要删除该机构吗？" style="color:#00F;">删除</a>	
					&nbsp;&nbsp;	
					<a href="${ctx}/coach/org/coachList.htm?orgId=${item.orgId}" target="navTab" title="机构教练" style="color:#00F;">机构教练</a>			  
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