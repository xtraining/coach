<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function checkupload(){
	var iframe = $("#callbackframe");
	var doc = iframe[0].contentDocument || iframe[0].content;
	var response = $(doc).find("body").text();
	if(response == "success"){
		navTabAjaxDone({"statusCode":"200", "message":"创建成功", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
	}  else if(response == "input1"){
		navTabAjaxDone({"statusCode":"300", "message":"请为故事关选择类.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	}  else {
		navTabAjaxDone({"statusCode":"300", "message":"创建失败，请关闭重试.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	} 
}

</script>

<div class="pageContent">
	<form action="${ctx}/story/artifact/create.htm" method="post" enctype="multipart/form-data" class="pageForm required-validate"  onsubmit="return iframeCallback(this, checkupload)">
	<input type="hidden" name="type" value="1"/>
	<input type="hidden" name="materialType" value="0"/>
	<h2 class="contentTitle">新增故事</h2>
		<div class="pageFormContent nowrap" layoutH="96">
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
				<dt>分类：</dt>
				<dd>
					<select name="categoryId" class="combox">
						<option value="-1">请选择分类</option>	
						<c:forEach var="item" items="${categories}">
						<option value="${item.id}" >${item.name}</option>		
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>分类权重： </dt>
				<dd>
					<input type="text" name="categoryOrder" maxlength="200" size="40" class="required digits" value="0"/>
				</dd>
			</dl>
			<dl>
				<dt>标签： </dt>
				<dd>
					<input type="text" name="tags" maxlength="200" size="40" class="" value=""/>
				</dd>
			</dl>
			<dl>
				<dt>播放次数： </dt>
				<dd>
					<input type="text" name="hitNum" size="40" class="digits required" value="2"/>
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
			<dl>
				<dt>音频文件 ：</dt>
				<dd><input type="file" name="mediaFile" size="20" class="required"/>	</dd>
			</dl>
			<dl>
				<dt>列表图片 ：</dt>
				<dd><input type="file" name="listImageFile" size="20" class="required"/>	</dd>
			</dl>
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
