<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function checkupload(){
	var iframe = $("#callbackframe");
	var doc = iframe[0].contentDocument || iframe[0].content;
	var response = $(doc).find("body").text();
	if(response == "success"){
		navTabAjaxDone({"statusCode":"200", "message":"创建成功", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
	}  else {
		navTabAjaxDone({"statusCode":"300", "message":"创建失败，请关闭重试.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	} 
}

</script>

<div class="pageContent">
	<form action="${ctx}/story/artifact/create.htm" method="post" enctype="multipart/form-data" class="pageForm required-validate"  onsubmit="return iframeCallback(this, checkupload)">
	<input type="hidden" name="type" value="0"/>
	<input type="hidden" name="materialType" value="0"/>
	<h2 class="contentTitle">新增故事</h2>
		<div class="pageFormContent" layoutH="96">
			<dl>
				<dt>标题： </dt>
				<dd>
					<input type="text" name="title" size="40" class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>副标题： </dt>
				<dd>
					<input type="text" name="subtitle" size="40"/>
				</dd>
			</dl>
			<dl>
				<dt>播放次数： </dt>
				<dd>
					<input type="text" name="hitNum" size="40" class="digits required" value="3"/>
				</dd>
			</dl>
			<dl>
				<dt>状态： </dt>
				<dd>
					<input type="radio" name="status" value="0" checked/>显示 <input type="radio" name="status" value="1" />隐藏
				</dd>
			</dl>
			<dl>
				<dt>时长(秒)： </dt>
				<dd>
					<input type="text" name="duration" maxlength="40" class="digits required" value="1"/>
				</dd>
			</dl>
			<dl></dl>
			<div class="divider"></div>
			<dl>
				<dt>列表图片 ：</dt>
				<dd><input type="file" name="listImageFile" size="20"/>	</dd>
			</dl>
			<dl></dl>
			<%for(int i = 0; i < 10; i++){%>
			<dl>
				<dt>详情图片<%=i+1 %> ：</dt>
				<dd><input type="file" name="detailImageFile" size="20"/>	</dd>
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
