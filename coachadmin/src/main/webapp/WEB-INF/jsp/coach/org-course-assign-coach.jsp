<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
function save(){
	var $form = $($("#assignCoachForm"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/coach/org/saveAssign.htm',
			data:$("#assignCoachForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "\"success\"":	
							navTabAjaxDone({"statusCode":"200", "message":"创建成功。", "navTabId":"机构课程", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "\"input\"":	
							navTabAjaxDone({"statusCode":"300", "message":"请选择教练。", "navTabId":"机构课程", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							navTabAjaxDone({"statusCode":"300", "message":"分派失败，请重试。", "navTabId":"机构管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>
<div class="pageContent">
	<form name="assignCoachForm" id="assignCoachForm" method="post" action="" class="pageForm required-validate">
	<input type="hidden" name="courseId" value="${courseId}"/>
	<h2 class="contentTitle">分派教练</h2>
		<div class="pageFormContent nowrap" layoutH="96">	
			<p>
				<label>教练名称: </label>
					<select name="coachId" class="combox">
						<option value="">--请选择--</option>	
						<c:forEach var="coachItem" items="${responseList}">
							<option value="${coachItem.coachId}">${coachItem.coachName}</option>	
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
