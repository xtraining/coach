<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>虎扑教练调查问卷</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
<link href="<%=path %>/css/common.css" rel="stylesheet">
</head>
<body>
<script>
var apiUrl = {
	send: '<%=path %>/servlet/saveSurvey'
}
/**
 * 投票数据
 * @title     {标题}
 * @type      {类型}   单选radio 多选checkbox
 * @options   {选项题目}
 * @underline {其它选项}
 */
var questionAllData = [
	{
		title: "您是体育培训行业教练吗?",
		type: "radio",
		options: [
			{
				name: "这都被你发现了，其实我是教练。",
				next: 1
			},
			{
				name: "我想做教练很久了。",
				next: 2
			},
			{
				name: "我是进来找教练的。",
				next: 3
			},
			{
				name: "酱油，路过",
				next: 3
			}
		]
	},
	{
		title: "您是什么运动项目的教练（可多选）",
		type: "checkbox",
		options: [
			{
				name: "足球",
				next: 3
			},
			{
				name: "篮球",
				next: 3
			},
			{
				name: "游泳",
				next: 3
			},
			{
				name: "网球",
				next: 3
			},
			{
				name: "健身",
				next: 3
			},
			{
				name: "跑步",
				next: 3
			},
			{
				name: "其他",
				next: 3,
				underline: true
			}
		]
	},
	{
		title: "您想做什么运动项目的教（可多选）",
		type: "checkbox",
		options: [
			{
				name: "足球",
				next: 3
			},
			{
				name: "篮球",
				next: 3
			},
			{
				name: "游泳",
				next: 3
			},
			{
				name: "网球",
				next: 3
			},
			{
				name: "健身",
				next: 3
			},
			{
				name: "跑步",
				next: 3
			},
			{
				name: "其他",
				next: 3,
				underline: true
			}
		]
	},
	{
		title: "您的性别是",
		type: "radio",
		options: [
			{
				name: "精壮的纯爷们",
				next: 4
			},
			{
				name: "安静的萌妹子",
				next: 4
			}
		]
	},
	{
		title: "您的年纪是",
		type: "radio",
		options: [
			{
				name: "22岁及以下",
				next: 5
			},
			{
				name: "23~28岁",
				next: 5
			},
			{
				name: "29~35岁",
				next: 5
			},
			{
				name: "35岁以上",
				next: 5
			}
		]
	},
	{
		title: "您是如何开始做教练的",
		type: "radio",
		underline: true,
		options: [
			{
				name: "体育专业学生，专业对口",
				next: 6
			},
			{
				name: "（退役）职业运动员，咱是专业的",
				next: 6
			},
			{
				name: "体育爱好者，高手在民间",
				next: 6
			},
			{
				name: "其他",
				next: 6,
				underline: true
			}
		]
	},
	{
		title: "您干这一行几年了",
		type: "radio",
		options: [
			{
				name: "1年以内",
				next: "end"
			},
			{
				name: "1~3年",
				next: "end"
			},
			{
				name: "3~5年",
				next: "end"
			},
			{
				name: "5~10年",
				next: "end"
			},
			{
				name: "10年以上",
				next: "end"
			}
		]
	}
];
</script>
<div class="ui-header">
	<h2>虎扑调查中心</h2>
	<p>谢谢您参与我们的调研，虎扑看球因为有您的支持才会变得更好。成功提交问卷的朋友将有机会获得虎扑看球提供的限量战衫。</p>
</div>
<div class="wrap">
	<div class="switch-wrap" id="J_switchWrap">
		<div class="switch-effect J_switchEffect">
			<div class="start-question">
				<div class="start-text">
					<a href="javascript:" class="button-start" id="J_buttonStart">开始答题</a>
				</div>
				<div class="survey-logo">
					<img src="<%=path %>/images/survey-logo.png" alt="虎扑体育"/>
				</div>
			</div>
		</div>
		<div class="switch-effect J_switchEffect J_endSwitchEffect">
			<div class="end-switch">
				<div class="setCity">
					<div class="title">你居住的城市是</div>
					<div class="bd">
						<div id="distpicker2">
							<select id="province5" class="form-control"></select>
							<select id="city5" class="form-control"></select>
							<select id="district5" class="form-control"></select>
						</div>
					</div>
				</div>
				<div class="bottom-wrap">
					<div class="textarea-info">
						<div class="title">
							请留下虎扑ID/手机号，加入虎扑教练联盟QQ群386629461，方便在之后工作人员与您取得联系，虎扑会妥善保障您的信息安全。
						</div>
						<div class="bd">
							<textarea class="textarea" value="" id="J_textareaInfo" name="info"></textarea>
						</div>
					</div>
					<div class="button-box">
						<input type="button" class="ui-button button-submit" id="J_button_submit" value="提交答卷"/>
					</div>
				</div>
			</div>
		</div>
		<div class="switch-effect J_switchEffect">
			<div class="success-wrap">
				<div class="title">
					亲爱的朋友：
					<p>您的问卷已经提交，感谢您的参与，加入虎扑教练联盟QQ群386629461，与虎扑同行，更多精彩。</p>
				</div>
				<div class="bd">
					<div class="share">
						<span class="info">请帮忙转发问卷：</span>
						<div class="share-list" id="J_shrae_newList"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script src="<%=path %>/js/libs/jquery/jquery.1.10.2.js"></script>
<script src="<%=path %>/js/libs/jquery/jquery-migrate.min.js"></script>
<script src="<%=path %>/js/libs/distpicker/distpicker.data.js"></script>
<script src="<%=path %>/js/libs/distpicker/distpicker.min.js"></script>
<link href="<%=path %>/js/libs/share/jquery.share.css" rel="stylesheet">
<script src="<%=path %>/js/libs/share/qrcode/qrcode.min.js"></script>
<script src="<%=path %>/js/libs/share/jquery.share.js"></script>
<script src="<%=path %>/js/views/common.js"></script>
<script>
jQuery(function(){
	jQuery("#distpicker2").distpicker({
		province: "---- 所在省 ----",
		city: "---- 所在市 ----",
		district: "---- 所在区 ----"
	});

	// 分享功能
	jQuery('#J_shrae_newList').share({
		theme: 'text',
		shareConfig: {
			searchPic: false,
			url: 'http://www.hupu.com',
			title: '“号外！！号外！！虎扑体育有可能要招教练了！！如果您是体育培训教练，赶紧看过来，亮出你的身份，奔放点，别控制，不限专业哦～'
		},
		social: ['qzone', 'weibo', 'weixin']
	});


});
</script>