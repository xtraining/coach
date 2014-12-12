/*global require*/
'use strict';

require.config({
    baseUrl: "scripts",
    urlArgs: "bust=" + (new Date()).getTime(),
    paths: {
        'jquery': 'vendor/jquery',
        'dialog': 'vendor/dialog',
        'dialog-config': 'vendor/dialog-config',
        'popup': 'vendor/popup',
        'selectbox': 'vendor/selectbox',
        'sha1': 'vendor/sha1'
    },
    shim : {
        jquery: {
            exports: 'jquery'
        },
        dialog: {
            exports: 'dialog'
        },
        selectbox: {
            deps: ['jquery'],
            exports: 'selectbox'
        }
    }
});
// 主界面
require([
        'jquery',
        'dialog',
        'selectbox',
        'sha1'
    ], function ($, dialog, selectbox, hex_sha1) {
    console.log(hex_sha1('abc'));

    var currentClassBox = $('#JCurrentClass'),
        currentClassList  = currentClassBox.find('.class-list');
    currentClassList.html('<li>1</li><li>1</li><li>1</li>');

    function tpl (tpl, data) {
        return tpl.replace(/{{(.*?)}}/g, function ($1, $2) {
            var _result = data[$2] === undefined ? '0' : data[$2];
            return _result;
        });
    }

    $.ajax({
        type: "GET",
        url: "../../coach/service",
        dataType: "json",
        data: {"sign": "D5E8DFF6636929292E96A099CBF1407D7A04DA0A",
            "v": "1.0",
            "method": "team.getAllList",
            "format": "json",
            "coachId": "20",
            "sessionId": "100BBF85-5CC1-440A-B43A-7C06E2EC7653",
            "appKey": "web_user"},
        success: function(data){
            console.log(data)
        }
    })
    $('select').each(function () {
        selectbox(this);
    });
});

// login
//require([
//    'jquery'
//], function ($) {
//
//
//
//    function imgLoad(src,callback){
//        var img = new Image();
//        img.src= src;
//        if (img.complete || img.width) {
//            callback && callback.call(img);
//        } else {
//            img.onload = function() {
//                callback && callback.call(img);
//            }
//        }
//    }
//    imgLoad('images/login-big-bg.jpg',function(){
//        var imgWrap = $('.login-bg-wrap');
//        function getSize(){
//            return {
//                height: $(window).height(),
//                width: $(window).width()
//            }
//        }
//        $(window).resize(function() {
//            var __size = getSize();
//            console.log(__size)
//            imgWrap.find('img').css({
//                'width': __size.width,
//                'height': __size.height
//            })
//        });
//        var _size = getSize();
//        this.height = _size.height;
//        this.width = _size.width;
//        imgWrap.append(this)
//    });
//
//});

//    });
//
//});
