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
					<table class="list nowrap itemDetail" addButton="添加" width="100%">
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
			<%for(int i = 0; i < 10; i++){%>
			<dl>
				<dt>图片<%=i+1 %> ：</dt>
				<dd><input type="file" name="imageFile" size="20"/>	</dd>
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
