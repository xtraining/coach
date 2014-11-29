<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function save(){
	var $form = $($("#taskForm"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/story/task/create.htm?sourceFrom=1',
			data:$("#taskForm").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "\"success\"":	
							dialogAjaxDone({"statusCode":"200", "message":"创建成功。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "\"input\"":	
							dialogAjaxDone({"statusCode":"300", "message":"请选择来源网站。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							dialogAjaxDone({"statusCode":"300", "message":"分派失败，请重试。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>

<div class="pageContent">
	<form name="taskForm" id="taskForm" method="post" action="" class="pageForm required-validate">
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
					<table class="list nowrap itemDetail" addButton="关联标签" width="100%">
						<thead>
							<tr>
								<th type="text" name="items[#index#].topicOrder" defaultVal="#index#" size="5" fieldClass="digits">序号</th>
								<th type="lookup" name="items[#index#].topicInfo.topicName" lookupGroup="items[#index#].topicInfo" lookupUrl="modules/topic!select.action" size="60" fieldClass="required">标签名称</th>
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
