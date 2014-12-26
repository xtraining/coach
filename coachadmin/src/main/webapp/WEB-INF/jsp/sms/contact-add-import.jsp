<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function checkupload(){
	var iframe = $("#callbackframe");
	var doc = iframe[0].contentDocument || iframe[0].content;
	var response = $(doc).find("body").text();
	if(response == "success"){
		navTabAjaxDone({"statusCode":"200", "message":"创建成功", "navTabId":"号码管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
	}  else {
		navTabAjaxDone({"statusCode":"300", "message":"创建失败，请关闭重试.", "navTabId":"号码管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	} 
}

</script>

<div class="pageContent">
	<form action="${ctx}/sms/contact/import.htm" method="post" enctype="multipart/form-data" class="pageForm required-validate"  onsubmit="return iframeCallback(this, checkupload)">
	<h2 class="contentTitle">导入手机号</h2>
		<div class="pageFormContent nowrap" layoutH="96">
			<dl>
				<dt>分组名： </dt>
				<dd><input type="text" name="tagName" size="50" class="required" maxlength="128"/></dd>
			</dl>	
			<dl>
				<dt>Excel文件 ：</dt>
				<dd><input type="file" name="excelFile" size="20" class="required"/>	</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">导入</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
