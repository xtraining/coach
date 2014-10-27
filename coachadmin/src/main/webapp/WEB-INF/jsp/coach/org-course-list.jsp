<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
    
<form id="pagerForm" method="post" action="${ctx}/coach/org/coachList.htm?orgId=${orgId}">
	<input type="hidden" name="courseId" value="${searchDto.courseId}" />
	<input type="hidden" name="courseName" value="${searchDto.courseName}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/coach/org/courseList.htm?orgId=${orgId}" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					课程ID：<input type="text" name="courseId" class="digits" value="${searchDto.courseId}"/>
				</td>
				<td>
					课程名称：<input type="text" name="courseName" value="${searchDto.courseName}"/>
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
			<li><a class="add" href="${ctx}/coach/org/addCourse.htm?orgId=${searchDto.orgId}" target="navTab" title="新增课程" rel="机构教练"><span>新增课程</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" align="left">课程ID</th>
				<th align="left">课程名称</th>
				<th align="left">所属机构</th>
				<th align="left">重复周期</th>
				<th align="left">开始时间</th>
				<th align="left">结束时间</th>
				<th align="left">到期日期</th>
				<th align="left">教练状态</th>
				<th width="15%" align="left">创建时间</th>
			 	<th width="15%" align="left">操作</th> 
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}">
			<tr target="sid_user" rel="${item.courseId}">
				<td>
					<input name="ids" value="${item.courseId}" type="checkbox">
				</td>
				<td>
					${item.courseId}
				</td>
				<td>
					<a href="${ctx}/coach/org/courseDetail.htm?courseId=${item.courseId}" target="navTab" title="修改课程" style="color:#00F;"  rel="机构教练">${item.name}</a>
				</td>
				<td>
				    ${item.orgName}
				</td>
				<td>${item.recycleDay}</td>
				<td>${item.startTime}</td>
				<td>${item.endTime}</td>
				<td>${item.expiryDate}</td>
				<td>
					<c:choose>
					  <c:when test="${empty item.coachCourseId}">   
					  	 <a href="${ctx}/coach/org/assignCourse.htm?courseId=${item.courseId}" target="dialog" mask="true" title="分派教练" style="color:#00F;">分派教练</a>
					  </c:when> 
					  <c:otherwise>   
					 	 <c:choose> 
	  						<c:when test="${item.coachCourseStatus == 0}"> 
	  						   ${item.coachName} &nbsp; 未处理
	  						   <a href="${ctx}/coach/org/assignCourse.htm?courseId=${item.courseId}&orgId=${searchDto.orgId}" target="dialog" mask="true" title="分派教练" style="color:#00F;">重新分派</a>
	  						</c:when>  
	  						<c:when test="${item.coachCourseStatus == 1}"> 
	  						   ${item.coachName} &nbsp; 已接受
	  						</c:when>  
	  						<c:when test="${item.coachCourseStatus == 0}"> 
	  						   ${item.coachName} &nbsp; 已拒绝
	  						   <a href="${ctx}/coach/org/assignCourse.htm?courseId=${item.courseId}&orgId=${searchDto.orgId}" target="dialog" mask="true" title="分派教练" style="color:#00F;">重新分派</a>
	  						</c:when>  
	  					 </c:choose>				 	 
					  </c:otherwise> 
					</c:choose>
				</td>
				<td>${item.createTime}</td>
				<td>
					<a href="${ctx}/coach/org/deleteCourse.htm?courseId=${item.courseId}" target="navTab" title="删除课程" style="color:#00F;">删除课程</a>					  
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