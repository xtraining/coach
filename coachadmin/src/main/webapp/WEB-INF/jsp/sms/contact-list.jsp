<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script>

</script>
    
<form id="pagerForm" method="post" action="${ctx}/sms/contact/list.htm">
	<input type="hidden" name="spName" value="${searchDto.spName}" />
	<input type="hidden" name="areaId" value="${searchDto.areaId}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sms/contact/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				    <label>运营商：</label>
					<select name="spName" class="combox">
						<option value="">全部</option>	
						<c:forEach var="item" items="${spNameList}">
						<option value="${item}" <c:if test="${searchDto.spName == item}">selected</c:if>>${item}</option>		
						</c:forEach>
					</select>
				</td>	
				<td>
				    <label>地区：</label>
					<select class="combox" name="provinceId" ref="w_combox_city" refUrl="${ctx}/sms/area/list.htm?provinceId={value}">
						<option value="">全部</option>	
						<option value="-1">其他</option>	
						<c:forEach var="item" items="${provinceList}">
						<option value="${item.id}" <c:if test="${searchDto.provinceId == item.id}">selected</c:if>>${item.name}</option>		
						</c:forEach>
					</select>
				</td>
				<td>
				    <label>分组名：</label>
					<select name="tagName" class="combox">
						<option value="">全部</option>	
						<c:forEach var="item" items="${tagNameList}">
						<option value="${item}" <c:if test="${searchDto.tagName == item}">selected</c:if>>${item}</option>		
						</c:forEach>
					</select>
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
			<li><a class="add" href="${ctx}/sms/contact/add.htm?sourceFrom=0" target="dialog" mask="true" width="800" title="添加号码"><span>添加</span></a></li>
			<li><a class="add" href="${ctx}/sms/contact/add.htm?sourceFrom=1" target="navTab" title="Excel导入"><span>Excel导入</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些手机号吗?" warn="请选择手机号" target="selectedTodo" rel="ids" postType="string" href="${ctx}/sms/contact/delete.htm" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" align="left">ID</th>
				<th width="20%" align="left">号码</th>
				<th width="15%" align="left">省市</th>
				<th width="15%" align="left">运营商</th>
				<th width="20%" align="left">分组名</th>
				<th width="10%" align="left">重复次数</th>
				<th width="12%" align="left">添加时间</th>
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
					${item.phoneNumber}
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
					${item.repeatTimes}
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