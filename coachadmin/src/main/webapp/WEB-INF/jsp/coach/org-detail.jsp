<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function save(){
	var $form = $($("#orgForm"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/coach/org/update.htm',
			data:$("#orgForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "\"success\"":	
							navTabAjaxDone({"statusCode":"200", "message":"Save Success.", "navTabId":"机构管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "\"input\"":	
							navTabAjaxDone({"statusCode":"300", "message":"机构名称已存在，请修改后重试。", "navTabId":"机构管理", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							navTabAjaxDone({"statusCode":"300", "message":"创建失败，请重试。", "navTabId":"机构管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>

<div class="pageContent">
	<form name="orgForm" id="orgForm" method="post" action="" class="pageForm required-validate">
	<input type="hidden" name="orgId" value="${editObj.orgId}"/>
	<h2 class="contentTitle">机构信息</h2>
		<div class="pageFormContent nowrap" layoutH="96">	
			<dl>
				<dt>机构名称：</dt>
				<dd><input type="text" name="orgName" size="60" class="required" maxlength="128" value="${editObj.orgName}"/></dd>
			</dl>
			<dl>
				<dt>机构地址：</dt>
				<dd><input type="text" name="address" size="60" class="required" maxlength="128" value="${editObj.address}"/></dd>
			</dl>
			<dl>
				<dt>联系人: </dt>
				<dd><input type="text" name="contact" size="60" class="required" maxlength="128" value="${editObj.contact}"/></dd>
			</dl>	
			<dl>
				<dt>电话: </dt>
				<dd><input type="text" name="phoneNumber" size="60" class="phone required" maxlength="128" value="${editObj.phoneNumber}"/></dd>
			</dl>			
			<dl>
				<dt>机构介绍: </dt>
				<dd><textarea name="description" cols="70" rows="8" maxlength="2000" class="required">${editObj.description}</textarea></dd>
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
