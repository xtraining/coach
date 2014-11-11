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
			url:'${ctx}/story/task/create.htm',
			data:$("#taskForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "\"success\"":	
							dialogAjaxDone({"statusCode":"200", "message":"创建成功。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "\"input\"":	
							dialogAjaxDone({"statusCode":"300", "message":"请选择来源网站。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							dialogAjaxDone({"statusCode":"300", "message":"分派失败，请重试。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>
<div class="pageContent">
	<form name="taskForm" id="taskForm" method="post" action="" class="pageForm required-validate">
	<h2 class="contentTitle">下载任务</h2>
		<div class="pageFormContent nowrap" layoutH="96">	
			<p>
				<label>来源网站: </label>
					<select name="sourceFrom" class="combox">
						<option value="0">喜马拉雅</option>	
					</select>
			</p>
			<p>
				<label>地址: </label>
				<textarea name="url" cols="90" rows="10" maxlength="2000" class="required"></textarea>
				<span>如果是多个地址请用;分割</span>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="save();">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
