
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>

    <jsp:include page="../../../includes/module/user/header.jsp"/>
    <title>小莫后台| 控制后台 </title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../../../includes/module/user/nav.jsp" />

    <jsp:include page="../../../includes/module/user/menu.jsp" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->

            <section class="content">
                <!-- Horizontal Form -->
                <c:if test="${message != null}">
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" ar{}ia-hidden="true">×</button>
                        <h4><i class="icon fa fa-check"></i>  ${message}</h4>
                    </div>
                </c:if>
                
                
                <div class="box box-info box-info-search " style="display: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title">高级查询</h3>
                    </div>
                    <!-- /.box-header -->

                    <!-- form start -->
                    <form class="form-horizontal">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="email" class="col-sm-1 control-label">邮箱</label>
                                <div class="col-xs-3 ">
                                    <input type="text" class="form-control " name="email" id="email" placeholder="邮箱">
                                </div>
                                <label for="username" class="col-sm-1 control-label">用户名</label>
                                <div class="col-xs-3 ">
                                    <input type="text" name="username" class="form-control" id="username"
                                           placeholder="用户名">
                                </div>
                                <label for="phone" class="col-sm-1 control-label">手机</label>
                                <div class="col-xs-3 ">
                                    <input type="text" name="phone" class="form-control " id="phone" placeholder="手机">
                                </div>
                            </div>

                            <!-- /.box-body -->
                            <div class="box-footer">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <button id="btndataTableReset" type="button" id="btn_reset" class="btn btn-default">重置</button>
                                        <button type="button" id="btndataTableSearch" class="btn btn-info pull-right">查询
                                        </button>
                                    </div>

                                </div>
                            </div>
                        <!-- /.box-footer -->
                        </div>
                    </form>
                </div>


                <div class="row">
                    <div class="col-sm-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">用户列表</h3>
                                <div class="row" style="padding-left:10px;">
                                    <a href="/user/form" type="button" class="btn btn-sm btn-success"><i class="fa fa-plus"></i>添加</a> &nbsp
                                    <%--<button  class="btn btn-sm btn-danger "  onclick="datatable.deleteMutli('/user/deleteMulti?ids=')" ><i class="fa fa-plus" ></i>删除</button> &nbsp--%>
                                    <a type="button" onclick="datatable.deleteMutli('/user/deleteMulti?ids=')" class="btn btn-sm btn-danger"  data-toggle="modal" data-target="#modal-danger" ><i class="fa fa-minus"></i>删除</a> &nbsp
                                    <a type="button"  onclick="datatable.editorMessage('/user/form?id=')" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>编辑</a> &nbsp
                                    <button class="btn btn-sm   btn-default" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"><i class="fa  fa-search"></i>搜索</button>
                                </div>
                            </div>
                            <div class="box-body">
                                <table id="dataTable" class="table table-bordered table-hover text-nowrap text-center ">
                                    <thead>
                                    <tr>
                                        <th>
                                            <input type="checkbox" class="minimal icheck_master"  />
                                        </th>
                                        <th>ID</th>
                                        <th>用户名</th>
                                        <th>邮箱</th>
                                        <th>手机</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
    </div>
    <jsp:include page="../../../includes/module/user/copyright.jsp"/>


</div>
<jsp:include page="../../../includes/module/user/footer.jsp"/>
<sys:model />
<script src="/static/assets/app/app.js"></script>
<script>
    $(function () {
        var dataTable = datatable.initdataTable("/user/page",
            [
                {
                    "data": function (row, type, set, meta) {
                        return '<input id="' + row.id + '" type="checkbox" class="minimal icheck_slave"  />'
                    }
                },
                {"data": "id"},
                {"data": "username"},
                {"data": "email"},
                {"data": "phone"},
                {
                    "data": function (row, type, set, meta) {
                        return DateTime.format(new Date(row.updated), "yyyy-MM-dd HH:mm:ss");
                    }
                },
                {
                    "data": function (row, type, set, meta) {
                        return '<a href="javascript:void(0);"  onclick="datatable.checkMessage(\'/user/detail?id=\' + '+row.id + ',\'查看用户信息\')" type="button"  class="btn btn-sm btn-default"><i class="fa fa-search"></i>查看</a> &nbsp &nbsp' +
                            '<a href="/user/form?id=' + row.id + '" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>编辑</a> &nbsp &nbsp' +
                            '<a  href="javascript:void(0);" onclick="datatable.showDelete(\'/user/delete?id=\'+'+row.id+')" type="button" class="btn btn-sm btn-danger"><i class="fa fa-trash-o"></i>删除</a>'
                    }
                }
            ]
        )
     //   $('#dataTable').DataTable()
        $("#btndataTableSearch").on("click",function () {
            dataTable.settings()[0].ajax.data = {
                "username": $("#username").val(),
                "email": $("#email").val(),
                "phone": $("#phone").val()
            };
            dataTable.ajax.reload();
        })
    });

</script>
</body>
</html>
