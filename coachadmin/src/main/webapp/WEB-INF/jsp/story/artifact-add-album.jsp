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
	<h2 class="contentTitle">新增专辑</h2>
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
				<dt>状态： </dt>
				<dd>
					<input type="radio" name="status" value="0" checked/>显示 <input type="radio" name="status" value="1" />隐藏
				</dd>
			</dl>
			<dl></dl>
			<div class="divider"></div>
			<div class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected"><a href="javascript:void(0)"><span>关联故事</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height: 250px;">
				<div>
					<table class="list nowrap itemDetail" addButton="增加故事" width="100%">
						<thead>
							<tr>
								<th type="text" name="items[#index#].topicOrder" defaultVal="#index#" size="5" fieldClass="digits">序号</th>
								<th type="lookup" name="items[#index#].topicInfo.topicName" lookupGroup="items[#index#].topicInfo" lookupUrl="modules/topic!select.action" size="60" fieldClass="required">故事名称</th>
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
