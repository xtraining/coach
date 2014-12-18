<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function checkupload(){
	var iframe = $("#callbackframe");
	var doc = iframe[0].contentDocument || iframe[0].content;
	var response = $(doc).find("body").text();
	if(response == "success"){
		navTabAjaxDone({"statusCode":"200", "message":"创建成功。", "navTabId":"分类管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
	}  else {
		navTabAjaxDone({"statusCode":"300", "message":"创建失败，请重试。", "navTabId":"分类管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
	} 
}
function deleteImg(){
	$("#imageUrl").val("");
	$("#uploadImageDD").html('<input type="file" name="imageFile" size="20" class="required"/>');
}

function viewFile(url, title){
	window.open(url,'图片预览','height=700,width=1100,top=50,left=50,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes, status=yes');
}
</script>
<div class="pageContent">
	<form action="${ctx}/story/category/update.htm" method="post" enctype="multipart/form-data" class="pageForm required-validate"  onsubmit="return iframeCallback(this, checkupload)">
	<input type="hidden" name="id" value="${editObj.id}"/>
	<input type="hidden" name="imageUrl" id="imageUrl" value="${editObj.imageUrl}"/>
	<input type="hidden" name="imageName" value="${editObj.imageName}"/>
	<h2 class="contentTitle">分类信息</h2>
		<div class="pageFormContent nowrap" layoutH="96">	
			<dl>
				<dt>名称: </dt>
				<dd><input type="text" name="name" size="60" class="required" maxlength="128" value="${editObj.name}"/></dd>
			</dl>
			<dl>
				<dt>副标题: </dt>
				<dd><input type="text" name="description" size="60" class="required" maxlength="128" value="${editObj.description}"/></dd>
			</dl>
			<dl>
				<dt>分类图片 ：</dt>
				<dd id="uploadImageDD">
				    <input type="file" name="imageFile" size="20"/>
				    <c:if test="${editObj.imageUrl != null}">
				    	<a href="javascript:deleteImg();" title="删除" style="color:#00F;">删除</a>&nbsp;&nbsp;
				   	 	<a href="javascript:viewFile('${editObj.imageUrl}', '缩略图');" title="查看图片" style="color:#00F;">查看图片</a>
				    </c:if>
				</dd>
			</dl>
			<dl>
				<dt>权重: </dt>
				<dd><input type="text" name="categoryOrder" size="60" class="digits required" maxlength="128" value="${editObj.categoryOrder}"/></dd>
			</dl>			
			<dl>
				<dt>状态： </dt>
				<dd>
					<input type="radio" name="status" value="0" <c:if test="${editObj.status == 0}">checked</c:if>/>显示 
					<input type="radio" name="status" value="1" <c:if test="${editObj.status == 1}">checked</c:if>/>隐藏
				</dd>
			</dl>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
