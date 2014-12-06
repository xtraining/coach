<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function checkupload(){
	var iframe = $("#callbackframe");
	var doc = iframe[0].contentDocument || iframe[0].content;
	var response = $(doc).find("body").text();
	if(response == "success"){
		navTabAjaxDone({"statusCode":"200", "message":"保存成功", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
	}  else if(response == "input1"){
		navTabAjaxDone({"statusCode":"300", "message":"请为故事设置分类.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	}  else {
		navTabAjaxDone({"statusCode":"300", "message":"保存失败，请关闭重试.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	} 
}
function deleteImg(){
	$("#imageUrl").val("");
	$("#uploadImageDD").html('<input type="file" name="listImageFile" size="20" class="required"/>');
}

function deleteMedia(){
	$("#fileUrl").val("");
	$("#uploadMediaDD").html('<input type="file" name="mediaFile" size="20" class="required"/>');
}

function viewFile(url, title){
	window.open(url,'图片预览','height=700,width=1100,top=50,left=50,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes, status=yes');
}
</script>

<div class="pageContent">
	<form action="${ctx}/story/artifact/update.htm"  method="post" enctype="multipart/form-data" class="pageForm required-validate"  onsubmit="return iframeCallback(this, checkupload)">
	<input type="hidden" name="id" value="${editObj.id}"/>
	<input type="hidden" name="imageId" value="${editObj.imageId}"/>
	<input type="hidden" name="imageUrl" id="imageUrl" value="${editObj.imageUrl}"/>
	<input type="hidden" name="imageName" value="${editObj.imageName}"/>
	<input type="hidden" name="fileUrl" id="fileUrl" value="${editObj.fileUrl}"/>
	<input type="hidden" name="fileName" value="${editObj.fileName}"/>
	<h2 class="contentTitle">更新故事</h2>
		<div class="pageFormContent nowrap" layoutH="96">
			<dl>
				<dt>标题： </dt>
				<dd>
					<input type="text" name="title" size="40" class="required" value="${editObj.title}"/>
				</dd>
			</dl>
			<dl>
				<dt>副标题： </dt>
				<dd>
					<input type="text" name="subtitle" size="40" value="${editObj.subtitle}"/>
				</dd>
			</dl>
			<dl>
				<dt>分类：</dt>
				<dd>
					<select name="categoryId" class="combox">
						<option value="-1">请选择分类</option>	
						<c:forEach var="item" items="${categories}">
						<option value="${item.id}" <c:if test="${editObj.categoryId == item.id}">selected</c:if>>${item.name}</option>		
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>分类权重： </dt>
				<dd>
					<input type="text" name="categoryOrder" maxlength="200" size="40" class="required digits" value="${editObj.categoryOrder}"/>
				</dd>
			</dl>
			<dl>
				<dt>标签： </dt>
				<dd>
					<input type="text" name="tags" maxlength="200" size="40" class="" value="${editObj.tags}"/>
				</dd>
			</dl>
			<dl>
				<dt>播放次数： </dt>
				<dd>
					<input type="text" name="hitNum" size="40" class="digits required" value="${editObj.hitNum}"/>
				</dd>
			</dl>
			<dl>
				<dt>状态： </dt>
				<dd>
					<input type="radio" name="status" value="0"  <c:if test="${editObj.status == 0}">checked</c:if>/>显示
					<input type="radio" name="status" value="2"  <c:if test="${editObj.status == 2}">checked</c:if>/>隐藏
				</dd>
			</dl>
			<dl>
				<dt>时长(秒)： </dt>
				<dd>
					<input type="text" name="duration" maxlength="40" class="digits required" value="${editObj.duration}"/>
				</dd>
			</dl>
			<dl></dl>
			<dl>
				<dt>音频文件 ：</dt>
				<dd id="uploadMediaDD">
				    <input type="file" name="mediaFile" size="20"/>
				    <c:if test="${editObj.fileUrl != null}">
				    	<a href="javascript:deleteMedia();" title="删除" style="color:#00F;">删除</a>&nbsp;&nbsp;
				   	 	<a href="javascript:viewFile('${editObj.fileUrl}', '试听');" title="试听" style="color:#00F;">试听</a>
				    </c:if>
				</dd>
			</dl>
			<dl>
				<dt>列表图片 ：</dt>
				<dd id="uploadImageDD">
				    <input type="file" name="listImageFile" size="20"/>
				    <c:if test="${editObj.imageUrl != null}">
				    	<a href="javascript:deleteImg();" title="删除" style="color:#00F;">删除</a>&nbsp;&nbsp;
				   	 	<a href="javascript:viewFile('${editObj.imageUrl}', '缩略图');" title="查看图片" style="color:#00F;">查看图片</a>
				    </c:if>
				</dd>
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
