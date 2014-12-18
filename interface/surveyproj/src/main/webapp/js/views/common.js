/**
 * 问题
 */
!function(win, $){


    var Question = {
        init: function() {
            var that = this;

            that.$button = $('#J_buttonStart');

            if ( !that.$button.length ) {
                return;
            }

            that.$wrap = $('#J_switchWrap');
            that.$switch = that.$wrap.find('.J_switchEffect');
            that.$endSwitch = that.$wrap.find('.J_endSwitchEffect');
            that.$list = that.$wrap.find('.J_questionList dd');
            that.$buttonNext = that.$wrap.find('.J_buttonNextQuestion');

            that.$buttonSubmit = $('#J_button_submit');

            that.len = that.$switch.length;
            that.activeNumber = 0;
            that.endNumber = 2;
            that.sendData = {};
            that.resizeWidth = $('.wrap').width();

            //that.resize();
            that.bind();
        },
        /**
         *
         */
        resize: function() {

        },
        bind: function() {
            var that = this;

            that.$button.on('click', function() {
                that.switchRender();
            });

            that.$list.live('click', function() {
                var $input = $(this).find('input');

                $input.attr('checked', 'true');
            });

            that.$buttonNext.live('click', function() {
                var $parent = $(this).parents('.J_questionList'),
                    $inputChecked = $parent.find('input:checked'),
                    val = parseInt( $inputChecked.val() ),
                    next = $inputChecked.data('next');

                if ( !$inputChecked.length ) {
                    alert('请选择答案');
                    return;
                };

                // 第一道题 除第一选择项
                if ( that.activeNumber === 0 && val > 1 ) {
                    var data = questionAllData[ 4 ].options;

                    for ( var i in data ) {
                        data[i].next = 'end';
                    }
                }

                var underlineVal = '',
                    inputVal = [];

                $parent.find('input[type="checkbox"], input[type="radio"]').each(function() {
                    if ( $(this).attr('checked') ) {
                        // 选择其它
                        if ( $(this).siblings('.underline').length ) {
                            underlineVal = $.trim($(this).siblings('.underline').val());
                        }
                        inputVal.push( 'a' + $(this).val() + underlineVal );
                    }
                });

                that.sendData[$inputChecked.attr('name')] = inputVal.join(',');

                //console.log( that.sendData );

                // 结束一道题
                if ( next === 'end' ) {
                    that.activeNumber += 1;
                } else {
                    that.activeNumber = next;
                };

                that.switchRender(next);
            });

            //
            that.$buttonSubmit.on('click', function() {
                that.sendAjax();
            });

        },
        /**
         * 提交
         */
        sendAjax: function() {
            var that = this;

            var $select = $('#distpicker2 select'),
                selectArray = [];

            $select.each(function() {
                if ( !$(this).val() ) {
                    return;
                }

                selectArray.push( $(this).val() );
            });


            if ( selectArray.length < $select.length ) {
                alert('请选择城市');
                return;
            }

            that.sendData.city = selectArray.join(',');

            if ( $.trim($('#J_textareaInfo').val()) ) {

                that.sendData.a = $.trim($('#J_textareaInfo').val());
            }

            //console.log( that.sendData );

            $.ajax({
                url: apiUrl.send,
                type: "POST",
                data: that.sendData,
                dataType: 'json',
                success: function(data) {
                    if ( data.status === 200 ) {
                        that.switchRender('success');
                        return false;
                    };

                    alert(data.msg);
                    window.location.reload();
                }
            });

        },
        /**
         * 切换屏幕
         */
        switchRender: function(val) {
            var that = this;

            if ( that.activeNumber < questionAllData.length && val !== 'end' ) {
                var data = questionAllData[that.activeNumber];

                // 匹配json内容
                var html = that.getQuestionTemplate(data);

                that.$endSwitch.before( html );
            }


            that.$switch = that.$wrap.find('.J_switchEffect');
            that.len = that.$switch.length;

            that.$wrap.css({
                width: (that.resizeWidth * that.len)
            });
            that.$switch.css({
                width: that.resizeWidth
            });

            if ( val === 'end' ) {
                that.len += 1;
            }

            // 成功
            if ( val === 'success' ) {
                that.len += 2;
            }

            that.$wrap.animate({
                'left': -that.resizeWidth * ( that.len - 3 )
            }, 400)
        },
        /**
         * 获取调查模板
         */
        getQuestionTemplate: function(data) {
            var that = this;

            var list_tpl = '';
            var options = '';

            for(var i = 0; i < data.options.length; i++) {
                options = data.options[i];

                list_tpl +=
                    '<dd>' +
                        '<label for="'+ (i + 1) +'">' +
                            '<input type="'+ data.type +'" name="q'+ ( that.activeNumber + 1 ) +'" value="'+ (i + 1) +'" data-next="'+ options.next +'" />' +
                            options.name;

                            if ( options.underline ) {
                                list_tpl += '<input type="text" class="underline" value="" />';
                            }

                            list_tpl += '</label>' +
                    '</dd>';
            }

            var tpl =
                '<div class="switch-effect J_switchEffect">' +
                    '<div class="question-list J_questionList">' +
                        '<dl>' +
                            '<dt class="title">'+ data.title +'</dt>' +
                            list_tpl +
                            '<dd class=""><a href="javascript:" class="ui-button J_buttonNextQuestion" title="下一题">下一题</a></dd>' +
                        '</dl>' +
                    '</div>' +
                '</div>';

            return tpl;
        }
    };

    Question.init();

}(window, jQuery);
