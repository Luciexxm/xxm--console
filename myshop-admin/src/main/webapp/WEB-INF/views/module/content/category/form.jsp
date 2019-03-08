<%--
  Created by IntelliJ IDEA.
  User: mmx
  Date: 2018/9/12
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>

<html>
<head>
    <%--<link href="https://cdn.bootcss.com/zTree.v3/3.5.33/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">--%>
        <link href="https://cdn.bootcss.com/wangEditor/10.0.13/wangEditor.min.css" rel="stylesheet">
        <link href="https://cdn.bootcss.com/zTree.v3/3.5.33/css/metroStyle/metroStyle.min.css" rel="stylesheet">
    <jsp:include page="/WEB-INF/includes/module/user/header.jsp"/>
    <link href="https://cdn.bootcss.com/dropzone/5.4.0/min/dropzone.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/assets/plugins/dropZone/dropzone.css"/>
    <title>小莫后台| 控制后台 </title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="/WEB-INF/includes/module/user/nav.jsp"/>

    <jsp:include page="/WEB-INF/includes/module/user/menu.jsp"/>
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
                <li class="active">商品信息</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">


                    <!-- Horizontal Form -->
                    <div class="box box-info"></div>
                    <div class="box-header with-border">
                        <c:if test="${message != null}">
                            <div class="alert alert-${baseResult.status ==200?"success":"danger"} alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert" ar{}ia-hidden="true">×</button>
                                <h4><i class="icon fa fa-ban"></i> ${message}</h4>
                            </div>
                        </c:if>
                        <h3 class="box-title">${tbContentCategory.id == "0" ?"新增":"编辑"}商品信息</h3>
                        <div class="row">
                        </div>
                        <!-- /.box-header -->

                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/category/save" method="post" modelAttribute="tbContentCategory">
                            <form:hidden path="id"/>
                            <div class="box-body ">
                                <div class="form-group ">
                                    <label for="parentId" class="col-sm-2 control-label">商品所属类别</label>
                                    <div class="col-sm-10">
                                        <form:hidden id="categoryId" path="parentId" />
                                        <input class="form-control required" id="categoryPid" readonly="true" placeholder="请选择" value="${tbContentCategory.parentName.name  eq null ? '/': tbContentCategory.parentName.name}"/>
                                    </div>
                                </div>

                                <div class="form-group ">
                                    <label for="name" class="col-sm-2 control-label">名称</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="name" placeholder="名称"  />
                                    </div>
                                </div>

                                <div class="form-group ">
                                    <label for="sortOrder" class="col-sm-2 control-label">排序</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required digits" path="sortOrder" placeholder="排序"/>
                                    </div>
                                </div>


                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>

                            <!-- /.box-footer -->
                        </form:form>
                    </div>
                </div>
            </div>
            </div>
        </section>
    </div>
    <jsp:include page="/WEB-INF/includes/module/user/copyright.jsp"/>

    <div class="modal modal-info fade" id="modal-info">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Info Modal</h4>
                </div>
                <div class="modal-body">
                    <p>One fine body&hellip;</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-outline">Save changes</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
</div>
<sys:model title="展示类别"/>
<jsp:include page="/WEB-INF/includes/module/user/footer.jsp"/>
<script src="https://cdn.bootcss.com/zTree.v3/3.5.33/js/jquery.ztree.core.js"></script>
<script src="/static/assets/app/validator.js"></script>
<script src="https://cdn.bootcss.com/dropzone/5.4.0/min/dropzone.min.js"></script>
<script src="https://cdn.bootcss.com/wangEditor/10.0.13/wangEditor.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/assets/plugins/ueditor/ueditor.config.js"> </script>
<script type="text/javascript" charset="utf-8" src="/static/assets/plugins/ueditor/ueditor.all.js"></script>


<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<%--<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/static/assets/plugins/ueditor/zh-cn.js"></script>--%>
<script>
    $(function () {
        datatable.initDateTree("dataTree");
    })
</script>
</body>
</html>
