<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function checkupload(){
	var iframe = $("#callbackframe");
	var doc = iframe[0].contentDocument || iframe[0].content;
	var response = $(doc).find("body").text();
	if(response == "success"){
		navTabAjaxDone({"statusCode":"200", "message":"创建成功", "navTabId":"", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
	}  else {
		navTabAjaxDone({"statusCode":"300", "message":"创建失败，请关闭重试.", "navTabId":"推荐内容", "forwardUrl":"", "callbackType":"", "rel":""});							
	} 
}

</script>

<div class="pageContent">
	<form action="${ctx}/story/top/create.htm" method="post" enctype="multipart/form-data" class="pageForm required-validate"  onsubmit="return iframeCallback(this, checkupload)">
	<input type="hidden" name="type" value="0"/>
	<h2 class="contentTitle">新增推荐</h2>
		<div class="pageFormContent nowrap" layoutH="96">
			<dl>
				<dt>名称： </dt>
				<dd>
					<input type="text" name="name" maxlength="40" size="40" class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>权重： </dt>
				<dd>
					<input type="text" name="topOrder" maxlength="10" size="40" class="required digits" value="0"/>
				</dd>
			</dl>
			<dl>
				<dt>收藏基数： </dt>
				<dd>
					<input type="text" name="collectionNum" maxlength="10" size="40" class="required digits" value="1"/>
				</dd>
			</dl>
			<dl>
				<dt>标签： </dt>
				<dd>
					<input type="text" name="tags" maxlength="200" size="40" class="" value=""/>
				</dd>
			</dl>
			<dl>
				<dt>开始时间：</dt>
				<dd>
					<input type="text" name="startTimeStr" class="date required" dateFmt="yyyy-MM-dd HH:mm" value="${nowTime}" readonly="true"/>
					<a class="inputDateButton" href="javascript:;">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>状态： </dt>
				<dd>
					<input type="radio" name="status" value="0" checked/>显示 <input type="radio" name="status" value="1" />隐藏
				</dd>
			</dl>
			
			<dl>
				<dt>首页图片 ：</dt>
				<dd><input type="file" name="imageFile" size="20" class="required"/>	</dd>
			</dl>
			<dl>
				<dt>详情图片 ：</dt>
				<dd><input type="file" name="detailImageFile" size="20" class="required"/>	</dd>
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
