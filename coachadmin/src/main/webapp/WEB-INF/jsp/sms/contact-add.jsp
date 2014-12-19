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
			url:'${ctx}/sms/contact/create.htm',
			data:$("#taskForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "\"success\"":	
							dialogAjaxDone({"statusCode":"200", "message":"添加成功。", "navTabId":"号码管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						default:
							dialogAjaxDone({"statusCode":"300", "message":"添加失败，请重试。", "navTabId":"号码管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>
<div class="pageContent">
	<form name="taskForm" id="taskForm" method="post" action="" class="pageForm required-validate">
	<h2 class="contentTitle">添加</h2>
		<div class="pageFormContent" layoutH="96">	
			<dl>
				<dt>号码：</dt>
				<dd>
				<textarea name="phoneNumbers" cols="70" rows="8" maxlength="2000" class="required"></textarea>
				<span class="info">多个号码用英文逗号“,”分割</span>
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
