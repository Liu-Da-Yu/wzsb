<script>
    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#uploadImage' //绑定元素
            ,url: 'upload' //上传接口
            ,done: function(res){

                copyText(res.data);

                layer.alert(res.data, {
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                    ,anim: 6 //动画类型
                });


            }
            ,error: function(){
                //请求异常回调
            }
        });
    });

    function copyText(text) {
        var textarea = document.createElement("textarea"); //创建input对象
        var currentFocus = document.activeElement; //当前获得焦点的元素
        var toolBoxwrap = document.getElementById('NewsToolBox'); //将文本框插入到NewsToolBox这个之后
        toolBoxwrap.appendChild(textarea); //添加元素
        textarea.value = text;
        textarea.focus();
        if (textarea.setSelectionRange) {
            textarea.setSelectionRange(0, textarea.value.length); //获取光标起始位置到结束位置
        } else {
            textarea.select();
        }
        try {
            var flag = document.execCommand("copy"); //执行复制
        } catch (eo) {
            var flag = false;
        }
        toolBoxwrap.removeChild(textarea); //删除元素
        currentFocus.focus();
        return flag;
    }


</script>