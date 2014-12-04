<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function save(){
	var $form = $($("#taskForm1"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/story/task/saveAccept.htm',
			data:$("#taskForm1").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "success":	
							navTabAjaxDone({"statusCode":"200", "message":"已入库成功。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "input1":	
							navTabAjaxDone({"statusCode":"300", "message":"请设置分类。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						case "input2":	
							navTabAjaxDone({"statusCode":"300", "message":"请设置标签。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							navTabAjaxDone({"statusCode":"300", "message":"入库失败，请重试。", "navTabId":"任务管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}
</script>

<div class="pageContent">
	<form name="taskForm1" id="taskForm1" method="post" action="" class="pageForm required-validate">
	<input type="hidden" name="type" value="0"/>
	<input type="hidden" name="taskId" value="${taskId}"/>
	<input type="hidden" name="downloadTaskId" value="${downloadTaskId}"/>
	<h2 class="contentTitle">入库</h2>
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="save();">创建</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
