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
		navTabAjaxDone({"statusCode":"300", "message":"请为故事关联分类.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
	}  else if(response == "input2"){
		navTabAjaxDone({"statusCode":"300", "message":"请为故事关联标签.", "navTabId":"故事管理", "forwardUrl":"", "callbackType":"", "rel":""});							
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
