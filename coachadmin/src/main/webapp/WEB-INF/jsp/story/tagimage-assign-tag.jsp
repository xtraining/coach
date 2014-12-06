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
							dialogAjaxDone({"statusCode":"200", "message":"设置成功。", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "input":
							dialogAjaxDone({"statusCode":"300", "message":"请关联标签。", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;
						default:
							dialogAjaxDone({"statusCode":"300", "message":"设置失败，请重试。", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
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
			<dl>
				<dt>标签：</dt>
				<dd>
				<input type="text" name="tags" value="${tags}" size="60"/>
				<span class="info">多个标签用英文分号逗号分割</span>
				</dd>
			</dl>
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
