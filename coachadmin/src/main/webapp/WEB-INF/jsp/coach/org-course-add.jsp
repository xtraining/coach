<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function save(){
	var $form = $($("#orgCourseForm"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/coach/org/createCourse.htm',
			data:$("#orgCourseForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "\"success\"":	
							navTabAjaxDone({"statusCode":"200", "message":"创建成功。", "navTabId":"机构课程", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "\"input\"":	
							navTabAjaxDone({"statusCode":"300", "message":"证据号码已存在，请修改后重试。", "navTabId":"机构教练", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							navTabAjaxDone({"statusCode":"300", "message":"创建失败，请重试。", "navTabId":"机构课程", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>

<div class="pageContent">
	<form name="orgCourseForm" id="orgCourseForm" method="post" action="" class="pageForm required-validate">
	<input type="hidden" name="orgId" value="${orgId}"/>
	<h2 class="contentTitle">课程信息</h2>
		<div class="pageFormContent nowrap" layoutH="96">	
			<dl>
				<dt>课程名称：</dt>
				<dd><input type="text" name="name" size="60" class="required" maxlength="128"/></dd>
			</dl>
			<dl>
				<dt>地址：</dt>
				<dd><input type="text" name="address" size="60" class="required" maxlength="128"/></dd>
			</dl>
			<dl>
				<dt>场地：</dt>
				<dd><input type="text" name="groundName" size="60" class="required" maxlength="128"/></dd>
			</dl>
			<dl>
				<dt>课程介绍: </dt>
				<dd><textarea name="description" cols="70" rows="6" maxlength="2000" class="required"></textarea></dd>
			</dl>
			<dl>
				<dt>开始时间：</dt>
				<dd><input type="text" name="startTimeStr" class="required date" dateFmt="yyyy-MM-dd HH:mm" readonly="true"/><a class="inputDateButton" href="javascript:;">选择</a></dd>
			</dl>
			<!-- <dl>
				<dt>结束时间：</dt>
				<dd><input type="text" name="endTimeStr" class="required date" dateFmt="yyyy-MM-dd HH:mm" readonly="true"/><a class="inputDateButton" href="javascript:;">选择</a></dd>
			</dl> -->
			<dl>
				<dt>有效期：</dt>
				<dd><input type="text" name="expiryDateStr" class="date" maxlength="128" dateFmt="yyyy-MM-dd" readonly="true"/><a class="inputDateButton" href="javascript:;">选择</a></dd>
			</dl>
			<dl>
				<dt>总课时：</dt>
				<dd><input type="text" name="courseHour" size="60" class="required number" maxlength="128"/>&nbsp;小时</dd>
			</dl>	
			<dl>
				<dt>每课时：</dt>
				<dd><input type="text" name="lessonHour" size="60" class="required number" maxlength="128"/>&nbsp;小时</dd>
			</dl>	
			<dl>
				<dt>循环周期：</dt>
				<dd><input type="text" name="recycleDay" size="60" class="required" maxlength="128"/></dd>
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
