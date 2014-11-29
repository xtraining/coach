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
			url:'${ctx}/story/category/create.htm',
			data:$("#taskForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "success":	
							dialogAjaxDone({"statusCode":"200", "message":"创建成功。", "navTabId":"分类管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						default:
							dialogAjaxDone({"statusCode":"300", "message":"创建失败，请重试。", "navTabId":"分类管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>
<div class="pageContent">
	<form name="taskForm" id="taskForm" method="post" action="" class="pageForm required-validate">
	<h2 class="contentTitle">分类信息</h2>
		<div class="pageFormContent" layoutH="96">	
			<dl>
				<dt>名称: </dt>
				<dd><input type="text" name="name" size="60" class="required" maxlength="128"/></dd>
			</dl>
			<dl>
				<dt>权重: </dt>
				<dd><input type="text" name="categoryOrder" size="60" class="digits required" maxlength="128" value="0"/></dd>
			</dl>			
			<dl>
				<dt>状态： </dt>
				<dd>
					<input type="radio" name="status" value="0" checked/>显示 <input type="radio" name="status" value="1" />隐藏
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
