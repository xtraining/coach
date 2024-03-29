<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/coach/coach/list.htm">
	<input type="hidden" name="coachId" value="${searchDto.coachId}" />
	<input type="hidden" name="coachName" value="${searchDto.coachName}" />
	<input type="hidden" name="phoneNumber" value="${searchDto.phoneNumber}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/coach/coach/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					教练ID：<input type="text" name="coachId" class="digits" value="${searchDto.coachId}"/>
				</td>
				<td>
					姓名：<input type="text" name="coachName" value="${searchDto.coachName}"/>
				</td>
				<td>
					手机号：<input type="text" name="phoneNumber" value="${searchDto.phoneNumber}"/>
				</td>
				<td>
				    <label>教练类型：</label>
					<select name="type" class="combox">
						<option value="">全部</option>	
						<option value="0" <c:if test="${searchDto.type eq '0'}">selected</c:if>>公众</option>	
						<option value="1" <c:if test="${searchDto.type eq '0'}">selected</c:if>>机构</option>		
					</select>
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
			<li><a class="add" href="modules/member!addFake.action" target="navTab" title="新增"><span>新增</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" align="left">教练ID</th>
				<th align="left">教练姓名</th>
				<th align="left">手机号</th>
				<th align="left">教练状态</th>
				<th width="15%" align="left">注册时间</th>
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
					<a href="${ctx}/coach/coach/detail.htm?uid=${item.coachId}" target="navTab" title="修改会员" style="color:#00F;">${item.coachName}</a>
				</td>
				<td>
					${item.phoneNumber}
				</td>
				<td>
				 	 <c:choose> 
  						<c:when test="${item.status == 0}"> 
  						             匹配
  						</c:when>  
  						<c:when test="${item.status == 1}"> 
  						   	教练未接受
  						</c:when>  
  						<c:when test="${item.status == 2}"> 
  						   	 绑定（机构邀请）
  						</c:when>  
  						<c:when test="${item.status == 3}"> 
  						             教练拒绝
  						</c:when> 
  						<c:when test="${item.status == 4}"> 
  						    机构未接受
  						</c:when> 
  						<c:when test="${item.status == 5}"> 
  						    绑定（教练申请）
  						</c:when> 
  						<c:when test="${item.status == 6}"> 
  						    机构拒绝
  						</c:when> 
  						<c:when test="${item.status == 7}"> 
  						   教练解 绑
  						</c:when> 
  						<c:when test="${item.status == 8}"> 
  						    机构解绑
  						</c:when> 
  						<c:otherwise>
  							未匹配
  						</c:otherwise>
  					 </c:choose>
				</td>
				<td>${item.createTime}</td>
				<td>
					<a href="${ctx}/coach/coach/delete.htm?coachId=${item.coachId}" target="navTab" title="删除教练" style="color:#00F;">删除</a>
					&nbsp;&nbsp;
					<a href="${ctx}/coach/coach/courseList.htm?coachId=${item.coachId}" target="navTab" title="教练课程" style="color:#00F;">教练课程</a>						  
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