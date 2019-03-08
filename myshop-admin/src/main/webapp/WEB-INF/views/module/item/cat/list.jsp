
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css" />
    <jsp:include page="../../../../includes/module/user/header.jsp"/>
    <title>小莫后台| 控制后台 </title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../../../../includes/module/user/nav.jsp" />

    <jsp:include page="../../../../includes/module/user/menu.jsp" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                商品管理
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
                            <label for="title" class="col-sm-1 control-label">标题</label>
                            <div class="col-xs-3 ">
                                <input type="text" class="form-control " name="title" id="title" placeholder="标题">
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
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">商品列表</h3>
                            <div class="row" style="padding-left:10px;">
                                <a href="/item/cat/form" type="button" class="btn btn-sm btn-success"><i class="fa fa-plus"></i>添加</a> &nbsp
                                <button class="btn btn-sm   btn-default" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"><i class="fa  fa-search"></i>搜索</button>
                            </div>
                        </div>
                        <div class="box-body">
                            <table id="treeTable" class="table table-bordered table-hover text-nowrap text-center ">
                                <thead >
                                <tr >
                                    <th>名称</th>
                                    <th>排序</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbItemCats}" var="tbItemCat">
                                    <tr id="${tbItemCat.id}" pId="${tbItemCat.parentId}">
                                        <td>${tbItemCat.name}</td>
                                        <td>${tbItemCat.sortOrder}</td>
                                        <td><fmt:formatDate value="${tbItemCat.updated}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td>
                                            <a href="/item/cat/form?id=${tbItemCat.id}&name=${tbItemCat.name}" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>编辑</a> &nbsp &nbsp
                                            <a  href="javascript:void(0);" onclick="datatable.showDelete('/item/cat/delete?id=${tbItemCat.id}')" type="button" class="btn btn-sm btn-danger"><i class="fa fa-trash-o"></i>删除</a> &nbsp &nbsp
                                            <a  href="/item/cat/form?id=${tbItemCat.id}&name=" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i>下级菜单目录</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../../../../includes/module/user/copyright.jsp"/>

</div>
<jsp:include page="../../../../includes/module/user/footer.jsp"/>
<sys:model />
<script src="/static/assets/app/app.js"></script>
<script src="/static/assets/plugins/treeTable/jquery.treeTable.js"> </script>
<script>
    $(function () {
        $("#treeTable").treeTable({
            column:0,
            expandLevel:2
        })
       // $('#treeTable').treeTable(option);
    })
</script>
</body>
</html>
