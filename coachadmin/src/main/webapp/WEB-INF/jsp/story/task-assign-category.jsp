<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function save(){
	var $form = $($("#assignCoachForm"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/story/task/saveCategory.htm',
			data:$("#assignCoachForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
			    var aaa = jQuery.parseJSON(data);
				switch(aaa){
						case "success":	
							dialogAjaxDone({"statusCode":"200", "message":"入库成功。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "input":	
							dialogAjaxDone({"statusCode":"300", "message":"请选择分类。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							dialogAjaxDone({"statusCode":"300", "message":"设置失败，请重试。", "navTabId":"", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>
<div class="pageContent">
	<form name="assignCoachForm" id="assignCoachForm" method="post" action="" class="pageForm required-validate">
	<input type="hidden" name="taskIds" value="${taskIds}"/>
	<h2 class="contentTitle">选择分类</h2>
		<div class="pageFormContent nowrap" layoutH="96">	
			<p>
				<label>分类名称: </label>
					<select name="categoryId" id="categoryId" class="combox">
						<option value="-1">请选择分类</option>	
						<c:forEach var="item" items="${categories}">
						<option value="${item.id}" >${item.name}</option>		
						</c:forEach>
					</select>
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
