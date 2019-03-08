var datatable = function () {

    /**
     * 初始化 icheck
     */
    var handlericheck = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });
    }
    /**
     *   dataTable 初始化
     * @param url
     * @param columns
     * @returns {jQuery}
     */
    var handlerdataTable = function (url, columns) {
        var dataTable = $('#dataTable').DataTable({
            serverSide: true,
            paging: true,
            info: true,
            lengthChange: false,
            deferRender: true,
            searching: false,
            pageLength: 7,
            bSort: false,//禁止排序
             scrollX: true,
            bAutoWidth: false,//自动宽度
            //数据来源（包括处理分页，排序，过滤） ，即url，action，接口，等等
            ajax: {
                url: url,
                type: "GET"
            },
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "columns": columns,

            //重新绘制后的回调
            drawCallback: function (settings) {
                //初始化 icheck
                handlericheck();
                //选中所有复选框
                handerAllCheckbox();
            },
        });
        bindEvent();

        return dataTable;
        //回调事件重置
        function bindEvent() {
            $("#btndataTableReset").on("click", function () {
                window.location.reload();
            })
        }
    }

    /**
     * 选中所有复选框
     */
    var handerAllCheckbox = function () {
        //获取 复选框的Boss
        var master = $('input[type="checkbox"].icheck_master');
        // 选中所有复选框
        master.iCheck('uncheck');
        master.on("ifClicked", function () {
            if (master.is(":checked")) {
                $('input[type="checkbox"].minimal').iCheck('uncheck');
                // console.log($(this).attr("id"));
            }
            else {
                $('input[type="checkbox"].minimal').iCheck('check');
            }
        });
    }


    /**
     * 删除单个用户信息
     * @param url
     */
    var handerdelete = function (url,message) {
        handlershowModalPrimary(message == null ?"确定删除该数据吗？":message,function () {
            window.location.href = url;
        });
    }

    /**
     * 展示默认模态框
     */
    var handlershowModalDefault = function (message) {
      //  $( '#modal-default .modal-dialog').removeClass( 'modal-lg');
        $('#modal-default').modal('show');
        $("#modal-default .modal-body p").html(message);
        $("#btnAlertOk").unbind("click");
        $("#btnAlertOk").bind("click", function () {
            $("#modal-alert").modal("hide");
        });
    }
    /**
     * 展示默认模态框(大)
     */
    var handlershowModalLg = function () {
        $( '#modal-default .modal-dialog').addClass( 'modal-lg');
        $('#modal-default').modal('show');
        $("#btnAlertOk").unbind("click");
        $("#btnAlertOk").bind("click", function () {
            $("#modal-alert").modal("hide");
        });
    }

    /**\
     *展示确定模态框
     * @param message
     * @param callback
     */
    var handlershowModalPrimary = function (message,callback) {
        $('#modal-primary').modal('show');
        $("#modal-primary .modal-body p").html(message);
        $("#btn_isOK").unbind("click");
        $("#btn_isOK").bind("click", function () {
            callback();
        });
    }


    /**
     * 查看用户信息
     * @param message
     */
    var handlercheckMessage = function (url) {
        $.ajax({
            'url': url,
            'type': 'GET',
            'dataType':'html',
            'success':function (data) {
               handlershowModalLg();
                $('.modal-message').html(data);
            }
        })
    }

    /**
     * 编辑提示
     * @param url
     */
    var handlerEditorMessage = function(url){
        var idArray = $('input[type="checkbox"].icheck_slave:checked');
        if (idArray != null && idArray.length > 1) {
           handlershowModalDefault('只能选择一条数据')
        }
        else if(idArray.length === 0){
            handlershowModalDefault('至少选择一条数据')
        }
        else{
            window.location.href=url+idArray.attr("id");
        }
    }
    /**
     * 批量删除所有
     */
    var handerdeleteMutli = function (url) {
        var idArray = $('input[type="checkbox"].icheck_slave:checked');
        if (idArray != null && idArray.length > 0) {
            handlershowModalPrimary('是否要删除该数用户的信息',function () {
                var ids = "";
                idArray.each(function () {
                    ids += $(this).attr("id");
                    if (idArray.length > 1) {
                        ids += ',';
                        idArray.length--;
                    }
                });
                window.location.href = url + ids;
            });
        }
        else {
            handlershowModalPrimary("请至少选择一条以上的信息",function () {
                $('#modal-primary').modal('hide');
            });
        }
    }

    var handlerDataTree = function (url) {
        var setting = {

            //是否可以多选
            view: {
                selectedMulti: false
            },
            //开启异步请求
            async: {
                enable: true,
                url:url,
                type:'GET',
                autoParam:["id"]
            },
            callback: {
                beforeClick: function (treeId, treeNode) {
                    $('#categoryPid').val(treeNode.name);
                    $('#categoryId').val(treeNode.id);
                   /* if( $('#categoryPid').val() == $("#name").val()){
                        alert("不能选择相同 本身为父节点");
                    }*/
                },
            }
        };

        $('#categoryPid').bind('click', function () {
            datatable.showModalDefault('<ul id="treeDemo" class="ztree"></ul>');
            $.fn.zTree.init($("#treeDemo"), setting);
        })
    }
    var handlerDropzone = function (dropId,picId) {
        Dropzone.autoDiscover = false;
        $("div#"+dropId).dropzone({
            url: "/upload/image",
            dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
            paramName: "dropzFile", // 传到后台的参数名称
            init: function () {
                this.on("success", function (file, data) {
                    // 上传成功触发的事件
                    $("#"+picId).val(data.dropImgUrl);
                });
            }
        });
    }
    /**
     * 富文本编辑
     */
    var handlerWangEditor =function () {
        var E = window.wangEditor;
        var editor = new E('#Myeditor');
        var $content = $('#content')
        editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            $content.val(html)
        }


        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '/upload/image'
        editor.customConfig.uploadFileName = 'wangEditorFiles'

        editor.create();
        editor.txt.html($content.val());
    }


    return {
        initdataTable: function (url, columns) {
            return handlerdataTable(url, columns);
        },
        //初始化 icheck
        initicheck: function () {
            return handlericheck();
        },
        /**
         * 展示模态框和删除
         * @param url
         */
        showDelete: function (url,message) {
            handerdelete(url,message);
        },
        /**
         * 批量删除
         * @param url
         */
        deleteMutli: function (url) {
            handerdeleteMutli(url);
        },
        /**
         * 查看用户信息
         */
        checkMessage:function (url) {
            handlercheckMessage(url);
        },

        /**
         * 编辑
         * @param url
         */
        editorMessage:function (url) {
             handlerEditorMessage(url);
        },
        /**
         * 展示默认模态框
         * @param message
         */
        showModalDefault:function (message) {
            handlershowModalDefault(message);
        },
        /**
         * 展示确定模态框
         */
        showModalPrimary:function(message,callback) {
        handlershowModalPrimary(message,callback);
    },
        /**
         * 初始化树形数据
         * @param url
         */
        initDateTree:function (url) {
            handlerDataTree(url);
        },
        /**
         * 初始化图片上传
         * @param dropId
         * @param picId
         */
        initDropZone:function (dropId,picId) {
            handlerDropzone(dropId,picId)
        },
        /**
         * 富文本编辑
         */
        initWangEditor:function () {
            handlerWangEditor()
        }
    }
}();
//初始化器
$(document).ready(function () {
    datatable.initicheck();

})