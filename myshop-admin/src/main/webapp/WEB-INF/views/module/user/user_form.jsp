
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <li class="active">用户信息</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">


                    <!-- Horizontal Form -->
                    <div class="box box-info"> </div>
                    <div class="box-header with-border">
                        <c:if test="${message != null}">
                            <div class="alert alert-${baseResult.status ==200?"success":"danger"} alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert" ar{}ia-hidden="true">×</button>
                                <h4><i class="icon fa fa-ban"></i> ${message}</h4>
                            </div>
                        </c:if>
                        <h3 class="box-title">${tbUser.id == "0" ?"新增":"编辑"}用户信息</h3>
                        <div class="row">
                        </div>
                        <!-- /.box-header -->

                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                            <form:hidden path="id"/>
                            <div class="box-body ">
                                <div class="form-group ">
                                    <label for="username" class="col-sm-2 control-label">用户名</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="username" placeholder="请输入用户名"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">密码</label>
                                    <div class="col-sm-10">
                                        <form:password id="password" path="password" cssClass="form-control required" placeholder="请输入用户密码"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone" class="col-sm-2 control-label">手机</label>
                                    <div class="col-sm-10">
                                        <form:input id="phone" path="phone" cssClass="form-control mobile " placeholder="请输入用户手机"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label ">邮箱</label>
                                    <div class="col-sm-10">
                                        <form:input id="email" path="email" cssClass="form-control email" placeholder="请输入用户邮箱"/>
                                    </div>
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
        </section>
    </div>
    <jsp:include page="../../../includes/module/user/copyright.jsp"/>

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
<jsp:include page="../../../includes/module/user/footer.jsp"/>

<script src="/static/assets/app/validator.js"></script>
<script>
    $(function () {
        Validate.init();
        Validate.validateForm("inputForm");
    })
</script>
</body>
</html>
