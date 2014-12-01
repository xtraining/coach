<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function save(){
	var $form = $($("#taskForm"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/story/tagimage/saveAssignTag.htm',
			data:$("#taskForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "success":	
							navTabAjaxDone({"statusCode":"200", "message":"设置成功。", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "input":
							navTabAjaxDone({"statusCode":"300", "message":"请关联标签。", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;
						default:
							navTabAjaxDone({"statusCode":"300", "message":"设置失败，请重试。", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>
<div class="pageContent">
	<form name="taskForm" id="taskForm" method="post" action="" class="pageForm required-validate">
	<input type="hidden" name="imageId" value="${imageId}"/>
	<h2 class="contentTitle">设置标签</h2>
		<div class="pageFormContent" layoutH="96">
			<div class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected"><a href="javascript:void(0)"><span>关联标签</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height: 350px;">
				<div>
					<table class="list nowrap itemDetail" addButton="添加" width="100%">
						<thead>
							<tr>
								<th type="text" name="tag[#index#].tagOrder" defaultVal="#index#" size="5" fieldClass="digits">序号</th>
								<th type="lookup" name="tag[#index#].name" lookupGroup="tag[#index#]" lookupUrl="${ctx}/story/tag/select.htm" size="60" fieldClass="required">标签名称</th>
								<th type="del" width="60">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${tagList}" varStatus="status">  
						<tr class="unitBox">
							<td>
								<input class="digits required textInput" type="text" size="10" value="${status.index+1}" name="tag['${status.index}'].tagOrder">
							</td>
							<td>
								<input type="hidden" name="tag['${status.index}'].id" value="${item.id}">
								<input class="required readonly textInput" type="text" size="60" lookuppk="id" name="tag['${status.index}'].name" value="${item.name}">
								<a class="btnLook" title="查找带回" lookuppk="id" lookupgroup="tag['${status.index}']" href="${ctx}/story/tag/select.htm">查找带回</a>
							</td>
							<td>
								<a class="btnDel " href="javascript:void(0)">删除</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
					</table>
				</div>
			</div>
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
			</div>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="save();">创建</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
