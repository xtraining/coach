<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>智勤教育-关于我们</title>
    <link rel="stylesheet" href="../static_resource/css/hello-coach-index.css"/>
</head>
<body class="main-page">
    <% com.coach.util.SessionContainer container = (com.coach.util.SessionContainer)session.getAttribute("SESSION_CONTAINER"); %>
    <!--顶部通栏 start-->
    <div class="top-bar">
        <div class="container">
            <div class="user-info">
                <strong>用户名，<%=container.getPhoneNumber() %>！</strong>
                <a class="btn" href="../login/logout">注销</a>
            </div>
        </div>
    </div>
    <!--顶部通栏 end-->
    <!-- 主体 start -->
    <div class="main-content">
        <div class="inner">
            <!-- 左侧 start -->
            <div class="left-content">
                <!-- 当前班级 start -->
                <div id="JCurrentClass" class="box-A">
                    <div class="hd">
                        <span>当前班级</span>
                        <em></em>
                    </div>
                    <div class="bd">
                        <div class="add-item">
                            <i class="icon-add"></i>
                            <p>点击添加新班级</p>
                        </div>
                        <ul class="class-list">
                            <li><span class="li-loading">loging...</span></li>
                        </ul>
                    </div>
                </div>
                <!-- 当前班级 end -->
                <!-- 结课班级 start -->
                <div id="JEndClass" class="box-A">
                    <div class="hd hd-null">
                        <span>结课班级</span>
                        <em></em>
                    </div>
                    <div class="bd">
                        <ul class="class-list" style="display:none">
                            <li><span class="li-loading">loging...</span></li>
                        </ul>
                    </div>
                </div>
                <!-- 结课班级 end -->
            </div>
            <!-- 左侧 end -->
            <!-- 当中 start -->
            <div class="center-content">
                <!-- 选中班级 start -->
                <div id="JCurrentClassInfo" class="box-B">
                    <div class="hd hd-null">
                        <%--<div class="hd-inner-show">--%>
                            <%--<div class="hd-panel">--%>
                                <%--<div class="text-box">--%>
                                    <%--<span>浦江足球一队</span>--%>
                                    <%--<em>134</em>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="right-btn-groups">--%>
                                <%--<a class="btn-grey" id="JHdReviseClassCancel" href="javascript:;">重命名</a>--%>
                                <%--<a class="btn-red" id="JHdReviseClassRemove" href="javascript:;">删除</a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="hd-inner-edit" style="display: none">--%>
                            <%--<div class="hd-panel">--%>
                                <%--<div class="text-input">--%>
                                    <%--<input class="input-class-name" type="text" value="浦江足球一队"/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="right-btn-groups">--%>
                                <%--<a class="btn-grey" id="JHdRenameClassConfirm" href="javascript:;">确定</a>--%>
                                <%--<a class="btn-red" id="JHdRenameClassCancel" href="javascript:;">取消</a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="bd">
                        <div class="add-item">
                            <i class="icon-add"></i>
                            <p>点击添加新学员</p>
                        </div>
                        <div class="member-panel">
                            <%--<label for="all-members">全选</label>--%>
                            <%--<input type="checkbox" class="all-member" id="all-members" name="all-members"/>--%>
                            <%--<div class="batch-select">--%>
                                <%--<select disabled name="batchMoving" id="batchMoving">--%>
                                    <%--&lt;%&ndash;<option value="">批量更换班级至</option>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<option value="1">浦江足球一队</option>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<option value="2">浦江足球二队</option>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<option value="3">浦江足球三队</option>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<option value="4">浦江足球四队</option>&ndash;%&gt;--%>
                                <%--</select>--%>
                            <%--</div>--%>
                            <%--<div class="right-btn-group">--%>
                                <%--<a id="JRemoveMember" class="btn-red" href="javascript:;">删除</a>--%>
                            <%--</div>--%>
                        </div>
                        <ul class="member-list">
                            <!--<li>-->
                                <!--<input class="one-member" type="checkbox" id="member-1"/>-->
                                <!--<label class="member-name" for="member-1">金丹丹</label>-->
                                <!--<div class="member-phone">-->
                                    <!--13821874521-->
                                <!--</div>-->
                                <!--<div class="select-wrap">-->
                                    <!--<select>-->
                                        <!--<option value="">批量更换班级至</option>-->
                                        <!--<option value="1">浦江足球一队</option>-->
                                        <!--<option value="2">浦江足球二队</option>-->
                                        <!--<option value="3">浦江足球三队</option>-->
                                        <!--<option value="4">浦江足球四队</option>-->
                                    <!--</select>-->
                                <!--</div>-->
                            <!--</li>-->
                            <!--<li>-->
                                <!--<input class="one-member" type="checkbox" id="member-2"/>-->
                                <!--<label class="member-name" for="member-2">金丹丹</label>-->
                                <!--<div class="member-phone">-->
                                    <!--13821874521-->
                                <!--</div>-->
                                <!--<div class="select-wrap">-->
                                    <!--<select>-->
                                        <!--<option value="">批量更换班级至</option>-->
                                        <!--<option value="1">浦江足球一队</option>-->
                                        <!--<option value="2">浦江足球二队</option>-->
                                        <!--<option value="3">浦江足球三队</option>-->
                                        <!--<option value="4">浦江足球四队</option>-->
                                    <!--</select>-->
                                <!--</div>-->
                            <!--</li>-->
                            <!--<li>-->
                                <!--<input class="one-member" type="checkbox" id="member-3"/>-->
                                <!--<label class="member-name" for="member-3">金丹丹</label>-->
                                <!--<div class="member-phone">-->
                                    <!--13821874521-->
                                <!--</div>-->
                                <!--<div class="select-wrap">-->
                                    <!--<select>-->
                                        <!--<option value="">批量更换班级至</option>-->
                                        <!--<option value="1">浦江足球一队</option>-->
                                        <!--<option value="2">浦江足球二队</option>-->
                                        <!--<option value="3">浦江足球三队</option>-->
                                        <!--<option value="4">浦江足球四队</option>-->
                                    <!--</select>-->
                                <!--</div>-->
                            <!--</li>-->
                            <!--<li>-->
                                <!--<input class="one-member" type="checkbox" id="member-4"/>-->
                                <!--<label class="member-name" for="member-4">金丹丹</label>-->
                                <!--<div class="member-phone">-->
                                    <!--13821874521-->
                                <!--</div>-->
                                <!--<div class="select-wrap">-->
                                    <!--<select>-->
                                        <!--<option value="">批量更换班级至</option>-->
                                        <!--<option value="1">浦江足球一队</option>-->
                                        <!--<option value="2">浦江足球二队</option>-->
                                        <!--<option value="3">浦江足球三队</option>-->
                                        <!--<option value="4">浦江足球四队</option>-->
                                    <!--</select>-->
                                <!--</div>-->
                            <!--</li>-->
                            <!--<li>-->
                                <!--<input class="one-member" type="checkbox" id="member-5"/>-->
                                <!--<label class="member-name" for="member-5">金丹丹</label>-->
                                <!--<div class="member-phone">-->
                                    <!--13821874521-->
                                <!--</div>-->
                                <!--<div class="select-wrap">-->
                                    <!--<select>-->
                                        <!--<option value="">批量更换班级至</option>-->
                                        <!--<option value="1">浦江足球一队</option>-->
                                        <!--<option value="2">浦江足球二队</option>-->
                                        <!--<option value="3">浦江足球三队</option>-->
                                        <!--<option value="4">浦江足球四队</option>-->
                                    <!--</select>-->
                                <!--</div>-->
                            <!--</li>-->
                        </ul>
                    </div>
                </div>
                <!-- 选中班级 end -->
            </div>
            <!-- 当中 end -->
            <!-- 右侧 start -->
            <div class="right-content">
                <!-- 选中学员 start -->
                <div id="JCurrentMemberInfo" class="box-C">
                    <div class="hd">
                        <div class="hd-inner-show">
                            <div class="hd-panel">
                                <div class="text-box">
                                    <span>金丹丹</span>
                                    <em>13816889625</em>
                                </div>
                            </div>
                            <div class="right-btn-group">
                                <a class="btn-red" id="JHdReviseMemberCancel" href="javascript:;">修改</a>
                            </div>
                        </div>
                        <div class="hd-inner-edit" style="display: none">
                            <div class="hd-panel">
                                <div class="text-input" >
                                    <input class="input-member-name" type="text" value="金丹丹"/>
                                    <input class="input-member-phone" type="text" value="13816889625"/>
                                </div>
                            </div>
                            <div class="right-btn-groups">
                                <a class="btn-grey" id="JHdRenameMemberConfirm" href="javascript:;">确定</a>
                                <a class="btn-red" id="JHdRenameMemberCancel" href="javascript:;">取消</a>
                            </div>
                        </div>
                    </div>
                    <div class="bd">
                        <div class="attendance-box">
                            <p class="attendance-tit"><span>点名记录：</span></p>
                            <ul class="attendance-list">
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-off--%>
                            <%--">未到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-off--%>
                            <%--">未到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-off--%>
                            <%--">未到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-off--%>
                            <%--">未到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-off--%>
                            <%--">未到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-off--%>
                            <%--">未到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-off--%>
                            <%--">未到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="odd"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-on">已到</b></li>--%>
                                <%--<li class="even"><span class="time">14/09/12 17:00</span><span class="place">虹口足球场</span><span class="address">上海东大名路687号</span><b class="tag-off--%>
                            <%--">未到</b></li>--%>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- 选中学员 end -->
            </div>
            <!-- 右侧 end -->
        </div>
    </div>
    <!-- 主体 end -->
    <!-- 底部 start -->
    <div class="footer">
        <div class="inner">
            <a href="#">客服中心</a>
            <a href="#">关于我们</a>
        </div>
    </div>
    <form action="" id="foromtest">
        <input type="text" name="memberName" value="guwei"/><input type="text" name="phoneNumber" value="181"/>
        <input type="text" name="memberName" value="guwei2"/><input type="text" name="phoneNumber" value="212"/>
    </form>
    <!-- 底部 end -->
    <script data-main="../static_resource/js/main" src="../static_resource/js/vendor/require.js"></script>
</body>
</html>