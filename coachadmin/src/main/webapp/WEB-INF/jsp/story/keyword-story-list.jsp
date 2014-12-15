<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function saveOrder(keywordOrderSeq, itemId, itemType){
	var idsStr = "";
	var keywordOrderArr = document.getElementsByName("keywordOrder");
	var keywordOrder = keywordOrderArr[keywordOrderSeq].value;
	idsStr = idsStr.substring(0, idsStr.length-1);
	var urlStr = "${ctx}/story/keyword/saveOrder.htm?id="+itemId+"&type="+itemType+"&keywordId=${keywordId}&keywordOrder="+keywordOrder;
	$.ajax({
			type:'POST',
			url:urlStr,
			data:"",//序列化表单里所有的内容
			success: function(data){
				dialogAjaxDone({"statusCode":"200", "message":"保存成功。", "navTabId":"", "forwardUrl":"", "callbackType":"", "rel":""});	
							
			}
			});
}
</script>
<form id="pagerForm" method="post" action="${ctx}/story/keyword/storyList.htm?keywordId=${keywordId}">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="NumPerPage" value="20" />
</form>

<div class="pageHeader">
	<form  name="objForm" id="objForm" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/story/keyword/storyList.htm?keywordId=${keywordId}" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					关键字：${name}
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
	<div class="panelBar">
		<ul class="toolBar">
				<li>
				</li>
				<li>
				</li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="136">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="3%" align="left">ID</th>
				<th width="10%" align="left">名称</th>
				<th width="10%"  align="left">描述</th>
				<th width="5%" align="left">类型</th>
				<th width="15%" align="left">权重</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${responseList}" varStatus="varSta">
			<tr target="sid_user" rel="${item.id}">
				<td>
					<input name="id" value="${item.id}" type="checkbox">
				</td>
				<td>
					${item.id}
				</td>
				<td>
					${item.name}
				</td>
				<td>
					${item.description}
				</td>
			    <td>
					<c:choose> 
  						<c:when test="${item.type == 0}"> 
  						 	故事          
  						</c:when> 
  						<c:otherwise>
  							<font color="blue">专辑</font>
  						</c:otherwise>
  					</c:choose>
  				</td> 
				<td>
					<input type="text" name="keywordOrder" size="5" class="digits required" maxlength="5" value="${item.keywordOrder}"/>
					<a href="javascript:saveOrder(${varSta.index}, ${item.id}, ${item.type});" style="color:#00F;">保存权重</a>
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