<%--
  Created by IntelliJ IDEA.
  User: mmx
  Date: 2018/9/11
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <div class="box-body">
        <div class="form-group">
            <div class="row">
                <div class="col-xs-5">
                    <label for="created" class="col-xl-5 control-label">创建时间</label>
                    <input type="text" class="form-control " id="created" name="created" value="<fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly="true">
                </div>
                <div class="col-xs-5 ">
                    <label class=" col-xl-4 control-label">更新时间</label>
                    <input type="text" class="form-control " name="updated" value="<fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly="true">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-xs-5">
                <label class=" col-xl-3  control-label">用户名</label>
                    <input type="text" class="form-control " name="username" value="${tbUser.username}" readonly="true">
                </div>
                <div class="col-xs-5 ">
                <label  class="  col-xl-3   control-label">手机</label>
                    <input type="text" class="form-control " name="phone" value="${tbUser.phone}" readonly="true">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-xs-5">
                    <label for="email" class="col-xl-3 control-label">邮箱</label>
                    <input type="text" class="form-control " id="email" name="email" value="${tbUser.email}" readonly="true">
                </div>
            </div>
        </div>

    </div>
        <!-- /.box-body -->
    </div>