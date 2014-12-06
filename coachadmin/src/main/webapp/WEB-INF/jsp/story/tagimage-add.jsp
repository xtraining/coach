<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function checkupload(){
	var iframe = $("#callbackframe");
	var doc = iframe[0].contentDocument || iframe[0].content;
	var response = $(doc).find("body").text();
	if(response == "success"){
		navTabAjaxDone({"statusCode":"200", "message":"创建成功", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
	}  else if(response == "input"){
		navTabAjaxDone({"statusCode":"300", "message":"请添加标签.", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	}  else {
		navTabAjaxDone({"statusCode":"300", "message":"创建失败，请关闭重试.", "navTabId":"图库管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	} 
	
}

</script>

<div class="pageContent">
	<form action="${ctx}/story/tagimage/create.htm" method="post" enctype="multipart/form-data" class="pageForm required-validate"  onsubmit="return iframeCallback(this, checkupload)">
	<input type="hidden" name="type" value="0"/>
	<h2 class="contentTitle">新增图片</h2>
		<div class="pageFormContent" layoutH="96">
			<%for(int i = 0; i < 10; i++){%>
			<dl>
				<dt>图片<%=i+1 %> ：</dt>
				<dd><input type="file" name="imageFile" size="20"/>	</dd>
			</dl>
			<dl>
				<dt>标签 ：</dt>
				<dd><input type="text" name="tags" maxlength="200" size="60" class="" value=""/></dd>
			</dl>
			<%} %>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
