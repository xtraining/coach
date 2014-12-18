<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function checkupload(){
	var iframe = $("#callbackframe");
	var doc = iframe[0].contentDocument || iframe[0].content;
	var response = $(doc).find("body").text();
	if(response == "success"){
		navTabAjaxDone({"statusCode":"200", "message":"保存成功", "navTabId":"首页推荐", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
	}  else {
		navTabAjaxDone({"statusCode":"300", "message":"保存失败，请关闭重试.", "navTabId":"首页推荐", "forwardUrl":"", "callbackType":"", "rel":""});							
	} 
}
function deleteImg(){
	$("#imageUrl").val("");
	$("#uploadImageDD").html('<input type="file" name="listImageFile" size="20" class="required"/>');
}

function viewFile(url, title){
	window.open(url,'图片预览','height=700,width=1100,top=50,left=50,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes, status=yes');
}
</script>

<div class="pageContent">
	<form action="${ctx}/story/top/update.htm" method="post" enctype="multipart/form-data" class="pageForm required-validate"  onsubmit="return iframeCallback(this, checkupload)">
	<input type="hidden" name="id" value="${editObj.id}"/>
	<input type="hidden" name="type" value="0"/>
	<input type="hidden" name="listImageFileName" id="imageUrl" value="${editObj.listImageFileName}"/>
	<input type="hidden" name="listImageFileUrl" value="${editObj.listImageFileUrl}"/>
	<h2 class="contentTitle">推荐详情</h2>
		<div class="pageFormContent nowrap" layoutH="96">
			<dl>
				<dt>名称： </dt>
				<dd>
					<input type="text" name="name" maxlength="10" size="40" class="required" value="${editObj.name}"/>
				</dd>
			</dl>
			<dl>
				<dt>权重： </dt>
				<dd>
					<input type="text" name="topOrder" maxlength="10" size="40" class="required digits" value="${editObj.topOrder}"/>
				</dd>
			</dl>
			<dl>
				<dt>收藏基数： </dt>
				<dd>
					<input type="text" name="collectionNum" maxlength="10" size="40" class="required digits" value="${editObj.collectionNum}"/>
				</dd>
			</dl>
			<dl>
				<dt>标签： </dt>
				<dd>
					<input type="text" name="tags" maxlength="200" size="40" class="" value="${editObj.tags}"/>
				</dd>
			</dl>
			<dl>
				<dt>开始时间：</dt>
				<dd>
					<input type="text" name="startTimeStr" class="date required" dateFmt="yyyy-MM-dd HH:mm" value="${editObj.startTimeStr}" readonly="true"/>
					<a class="inputDateButton" href="javascript:;">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>状态： </dt>
				<dd>
					<input type="radio" name="status" value="0" <c:if test="${editObj.status == 0}">checked</c:if>/>显示 
					<input type="radio" name="status" value="2" <c:if test="${editObj.status == 2}">checked</c:if>/>隐藏
				</dd>
			</dl>
			
			<dl>
				<dt>首页图片 ：</dt>
				<dd id="uploadImageDD">
				    <input type="file" name="listImageFile" size="20"/>
				    <c:if test="${editObj.listImageFileUrl != null}">
				    	<a href="javascript:deleteImg();" title="删除" style="color:#00F;">删除</a>&nbsp;&nbsp;
				   	 	<a href="javascript:viewFile('${editObj.listImageFileUrl}', '缩略图');" title="查看图片" style="color:#00F;">查看图片</a>
				    </c:if>
				</dd>
			</dl>
			<div class="divider"></div>
			<div class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected"><a href="javascript:void(0)"><span>添加故事</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height: 250px;">
				<div>
					<table class="list nowrap itemDetail" addButton="添加故事" width="100%">
						<thead>
							<tr>
								<th type="text" name="artifact[#index#].artifactOrder" defaultVal="#index#" size="5" fieldClass="digits">序号</th>
								<th type="lookup" name="artifact[#index#].title" lookupGroup="artifact[#index#]" lookupUrl="${ctx}/story/artifact/select.htm" size="60" fieldClass="required">故事名称</th>
								<th type="del" width="60">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${artifacts}" varStatus="status">  
						<tr class="unitBox">
							<td>
								<input class="digits required textInput" type="text" size="10" value="${status.index+1}" name="artifact['${status.index}'].artifactOrder">
							</td>
							<td>
								<input type="hidden" name="artifact['${status.index}'].id" value="${item.id}">
								<input class="required readonly textInput" type="text" size="60" lookuppk="id" name="artifact['${status.index}'].title" value="${item.title}">
								<a class="btnLook" title="查找带回" lookuppk="id" lookupgroup="artifact['${status.index}']" href="${ctx}/story/artifact/select.htm">查找带回</a>
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
