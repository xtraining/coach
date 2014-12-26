<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function saveAccept(){
	var categoryId = document.getElementById("categoryId");
	if(categoryId.value == -1){
	 	alertMsg.warn("请选择分类");
		return;
	}
	var idsStr = "";
	var idElement = document.getElementsByName("downloadTaskIds");
	var length = idElement.length;
	for(var i = 0; i < length; i++){
		if(idElement[i].checked && idElement[i].value != ""){
			idsStr += idElement[i].value + ",";
		}
	}
	if(idsStr == ""){
		alertMsg.warn("请选择故事。");
		return;
	}
	idsStr = idsStr.substring(0, idsStr.length-1);
	var urlStr = "${ctx}/story/task/saveAccept.htm?taskId=0&downloadTaskIds="+idsStr+"&categoryId="+categoryId.value;
	$.ajax({
			type:'POST',
			url:urlStr,
			data:"",//序列化表单里所有的内容
			success: function(data){
				dialogAjaxDone({"statusCode":"200", "message":"入库成功。", "navTabId":"任务详情", "forwardUrl":"", "callbackType":"", "rel":"任务详情"});	
							
			}
			});
}
function redownload(){
alert(1);
	var urlStr = "${ctx}/story/task/redownloadAll.htm?taskId=${taskId}&sourceFrom=${sourceFrom}";
	$.ajax({
			type:'POST',
			url:urlStr,
			data:"",//序列化表单里所有的内容
			success: function(data){
				dialogAjaxDone({"statusCode":"200", "message":"全部错误重新下载开始", "navTabId":"任务详情", "forwardUrl":"", "callbackType":"", "rel":"任务详情"});	
							
			}
			});
}
</script>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><select name="categoryId" id="categoryId" class="combox">
						<option value="-1">请选择分类</option>	
						<c:forEach var="item" items="${categories}">
						<option value="${item.id}" >${item.name}</option>		
						</c:forEach>
					</select>
				</li>
				<li>
					<a class="add" href="javascript:saveAccept();" ><span>批量入库</span></a></li>
					</li>
			<li class="line">line</li>
			<li>
					<a class="edit" href="javascript:redownload();" ><span>全部错误重新下载</span></a></li>
					</li>
		</ul>
	</div>
	<table class="table" width="2000" layoutH="136">
		<thead>
			<tr>
				<th width="2%" align="left"><input type="checkbox" group="downloadTaskIds" class="checkboxCtrl"></th>
				<th width="3%" align="left">下载ID</th>
				<!-- <th width="3%" align="left">类型</th> -->
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
					<input name="downloadTaskIds" value="${item.id}" type="checkbox">
				</td>
				<td>
					${item.id}
				</td>
				<%-- <td>
					<c:choose> 
  						<c:when test="${item.type == 0}"> 
  						              封面
  						</c:when> 
  						<c:otherwise>
  							内容
  						</c:otherwise>
  					</c:choose>
  				</td> --%>
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
  						             已入库
  						</c:when> 
  						<c:when test="${item.status == 3}"> 
  						            下载完成
  						</c:when> 
  						<c:otherwise>
  							下载失败&nbsp;&nbsp; <a href="${ctx}/story/task/redownload.htm?downloadTaskId=${item.id}" target="ajaxTodo" style="color:#00F;">重试</a>
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