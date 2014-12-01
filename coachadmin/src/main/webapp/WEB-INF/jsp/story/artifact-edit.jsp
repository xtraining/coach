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
		navTabAjaxDone({"statusCode":"300", "message":"请为故事关联分类.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	}  else if(response == "input2"){
		navTabAjaxDone({"statusCode":"300", "message":"请为故事关联标签.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
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
			<div class="divider"></div>
			<div class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected"><a href="javascript:void(0)"><span>关联标签</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height: 150px;">
				<div>
					<table class="list nowrap itemDetail" addButton="增加" width="100%">
						<thead>
							<tr>
								<th type="text" name="tag[#index#].tagOrder" defaultVal="#index#" size="5" fieldClass="digits">序号</th>
								<th type="lookup" name="tag[#index#].name" lookupGroup="tag[#index#]" lookupUrl="${ctx}/story/tag/select.htm" size="60" fieldClass="required">标签名称</th>
								<th type="del" width="60">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${tags}" varStatus="status">  
						<tr class="unitBox">
							<td>
								<input class="digits required textInput" type="text" size="10" value="${status.index+1}" name="tag['${status.index}'].tagOrder">
							</td>
							<td>
								<input type="hidden" name="tag['${status.index}'].id" value="${item.id}">
								<input class="required readonly textInput" type="text" size="60" lookuppk="id" name="tag['${status.index}'].name" value="${item.name}">
								<a class="btnLook" title="查找带回" lookuppk="id" lookupgroup="tag['${status.index}']" href="${ctx}/story/tag/select.htm">查找带回</a>
							</td>
							<td>
								<a class="btnDel " href="javascript:void(0)">删除</a>
							</td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
			</div>	
			<div class="divider"></div>
			<div class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected"><a href="javascript:void(0)"><span>关联分类</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height: 150px;">
				<div>
					<table class="list nowrap itemDetail" addButton="增加" width="100%">
						<thead>
							<tr>
								<th type="text" name="category[#index#].categoryOrder" defaultVal="#index#" size="5" fieldClass="digits">序号</th>
								<th type="lookup" name="category[#index#].name" lookupGroup="category[#index#]" lookupUrl="${ctx}/story/category/select.htm" size="60" fieldClass="required">分类名称</th>
								<th type="del" width="60">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${categories}" varStatus="status">  
						<tr class="unitBox">
							<td>
								<input class="digits required textInput" type="text" size="10" value="${status.index+1}" name="category['${status.index}'].categoryOrder">
							</td>
							<td>
								<input type="hidden" name="category['${status.index}'].id" value="${item.id}">
								<input class="required readonly textInput" type="text" size="60" lookuppk="id" name="category['${status.index}'].name" value="${item.name}">
								<a class="btnLook" title="查找带回" lookuppk="id" lookupgroup="category['${status.index}']" href="${ctx}/story/category/select.htm">查找带回</a>
							</td>
							<td>
								<a class="btnDel " href="javascript:void(0)">删除</a>
							</td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
			</div>
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
