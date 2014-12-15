<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/story/keyword/list.htm">
	<input type="hidden" name="name" value="${name}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/keyword/list.htm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					关键字：<input type="text" name="name" value="${name}"/>
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
			<%-- <li><a class="add" href="${ctx}/story/tag/add.htm" target="dialog" mask="true" title="新增标签" rel="标签详情"><span>新增标签</span></a></li>
			<li><a title="确实要删除这些标签吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/story/tag/delete.htm" class="delete"><span>批量删除</span></a></li> --%>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<!-- <th width="10%" align="left">关键字ID</th> -->
				<th align="left">关键字</th>
				<!-- <th align="10%">权重</th>-->
				<th width="15%" align="left">查询次数</th>
				<!-- <th width="15%" align="left">创建时间</th> -->
				<th width="15%" align="left">操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.id}">
				<td>
					<input name="ids" value="${item.id}" type="checkbox">
				</td>
			<%-- 	<td>
					${item.id}
				</td> --%>
				<td>
					${item.name}
				</td>
				<%-- <td>${item.tagOrder}</td> --%>
				<%-- <td>
					<c:choose>
					<c:when test="${item.status == 0}">显示</c:when> 
					<c:when test="${item.status == 1}">隐藏</c:when> 
					</c:choose>
				</td> --%>
				<td>
					${item.countNum}
				</td>
			    <td>	
			     <% com.zhiqin.coach.admin.dto.KeywordDTO keywordDto = (com.zhiqin.coach.admin.dto.KeywordDTO)pageContext.getAttribute("item");
			        String newName = java.net.URLEncoder.encode(keywordDto.getName(),"UTF-8");
			     %>	
				  <a href='${ctx}/story/keyword/storyList.htm?name=<%=newName %>' target="navTab" title="故事列表" rel="故事列表" style="color:#00F;">故事列表</a>
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