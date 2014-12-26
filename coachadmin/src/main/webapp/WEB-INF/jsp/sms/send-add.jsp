<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<script type="text/javascript">
function save(){
	var $form = $($("#objForm3"));

	if (!$form.valid()) {
		return false;
	}
	$.ajax({
			type:'POST',
			url:'${ctx}/sms/send/create.htm',
			data:$("#objForm3").formSerialize(),//序列化表单里所有的内容
			success: function(data){	
				switch(data){
						case "\"success\"":	
							navTabAjaxDone({"statusCode":"200", "message":"创建成功.", "navTabId":"短信管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});	
							break;
						case "\"input\"":	
							navTabAjaxDone({"statusCode":"300", "message":"产品签名不能为空", "navTabId":"", "forwardUrl":"", "callbackType":"", "rel":""});							
							break;	
						default:
							navTabAjaxDone({"statusCode":"300", "message":"Save Error, Please retry.", "navTabId":"短信管理", "forwardUrl":"", "callbackType":"closeCurrent", "rel":""});							
							break;	
					}
			}
			});
}

function getContact(){
	$.ajax({
			type:'POST',
			url:"${ctx}/sms/contact/getContactCount.htm?spName=" + $("#spName").val()+'&areaId='+$("#areaId").val()+'&tagName='+$("#tagName").val(),
			data:null,//序列化表单里所有的内容
			success: function(data){	
			   $("#contacCount").html(data);
			}
			});
}
</script>

<div class="pageContent">
	<form name="objForm3" id="objForm3" method="post" action="" class="pageForm required-validate">
	<input type="hidden" name = "sendType" value = "0"/>
	<h2 class="contentTitle">创建短信</h2>
		<div class="pageFormContent nowrap" layoutH="96">	
			<dl>
				<dt>短信内容: </dt>
				<dd><textarea name="message" cols="70" rows="4" maxlength="200" class="required"></textarea></dd>
			</dl>	
			<!-- <dl>
				<dt>发送类型：</dt>
				<dd>
					<label>立即发送：<input type="radio" name="sendType" checked value="0"/></label>
					<label>定时发送：<input type="radio" name="sendType" value="1"/></label>
				</dd>
			</dl> -->
			<!-- <dl>
				<dt>发送时间：</dt>
				<dd>
					<input class="date textInput" type="text" size="20" dateFmt="yyyy-MM-dd HH:mm" mmStep="15" readonly="true" value="" name="sendTime">
					<a class="inputDateButton" href="javascript:void(0)">选择</a>
				</dd>
			</dl> -->
			
			<dl>
				<dt>短信签名：</dt>
				<dd><select name="productName" class="combox">
						<c:forEach var="item" items="${productNameList}">
						<option value="${item}">${item}</option>		
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<dl>
				<dt>运营商：</dt>
				<dd><select name="spName" class="combox" onchange="javascript:getContact();" id="spName">
						<option value="">全部</option>	
						<c:forEach var="item" items="${spNameList}">
						<option value="${item}">${item}</option>		
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>省：</dt>
				<dd>
					<select name="areaId" class="combox" onchange="javascript:getContact();" id="areaId">
						<option value="">全部</option>	
						<option value="-1">其他</option>	
						<c:forEach var="item" items="${areaList}">
						<option value="${item.id}">${item.name}</option>		
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>分组名：</dt>
				<dd>
					<select name="tagName" class="combox" onchange="javascript:getContact();" id="tagName">
						<option value="">全部</option>	
						<c:forEach var="item" items="${tagNameList}">
						<option value="${item}">${item}</option>		
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>号码数量：</dt>
				<dd id="contacCount">
				${totalCount}
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="save();">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
