<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function save(){
	var $form = $($("#orgCoachForm"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/coach/org/updateCoach.htm',
			data:$("#orgCoachForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "\"success\"":	
							navTabAjaxDone({"statusCode":"200", "message":"更新成功。", "navTabId":"", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "\"input\"":	
							navTabAjaxDone({"statusCode":"300", "message":"证据号码已存在，请修改后重试。", "navTabId":"机构教练", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							navTabAjaxDone({"statusCode":"300", "message":"创建失败，请重试。", "navTabId":"机构教练", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>

<div class="pageContent">
	<form name="orgCoachForm" id="orgCoachForm" method="post" action="" class="pageForm required-validate">
	<input type="hidden" name="orgCoachId" value="${editObj.orgCoachId}"/>
	<input type="hidden" name="orgId" value="${editObj.orgId}"/>
	<h2 class="contentTitle">教练信息</h2>
		<div class="pageFormContent nowrap" layoutH="96">	
			<dl>
				<dt>教练姓名：</dt>
				<dd><input type="text" name="coachName" size="60" class="required" maxlength="128" value="${editObj.coachName}"/></dd>
			</dl>
			<dl>
				<dt>证据类型：</dt>
				<dd>
					<select name="idType" class="combox">
						<option value="0" <c:if test="${editObj.idType eq '0'}">selected</c:if>>身份证</option>	
						<option value="1" <c:if test="${editObj.idType eq '1'}">selected</c:if>>护照</option>		
					</select>
				</dd>
			</dl>
			<dl>
				<dt>证据号码: </dt>
				<dd><input type="text" name="idNumber" size="60" class="required" maxlength="128"  value="${editObj.idNumber}"/></dd>
			</dl>	
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="save();">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
