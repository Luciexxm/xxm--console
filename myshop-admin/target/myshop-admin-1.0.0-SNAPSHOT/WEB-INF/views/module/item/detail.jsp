<%--
  Created by IntelliJ IDEA.
  User: mmx
  Date: 2018/9/12
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box">
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tr>
                                <th>标题</th>
                                <th>子标题</th>
                            </tr>
                            <tr>
                                <td>${tbContent.title}</td>
                                <td> ${tbContent.subTitle}</td>
                            </tr>
                            <tr>
                                <th >创建时间</th>
                                <th>更新时间</th>
                            </tr>
                            <tr>
                                <td> <input type="text" class="form-control " id="created" name="created" value="<fmt:formatDate value="${tbContent.created}" pattern="yyyy-MM-dd HH:mm:ss" />" /></td>
                                <td>  <input type="text" class="form-control " id="updated" name="updated" value="<fmt:formatDate value="${tbContent.updated}" pattern="yyyy-MM-dd HH:mm:ss" />" /> </td>
                            </tr>
                            <tr>
                                <th >商品说明</th>
                                <th>链接</th>
                            </tr>
                            <tr>
                                <td>${tbContent.titleDesc} </td>
                                <td><a href="${tbContent.url}">${tbContent.url}</a> </td>
                            </tr>
                            <tr>
                                <th >图片1</th>
                                <th>图片2</th>
                            </tr>
                            <tr>
                                <td><a href="${tbContent.pic}"><input type="text" class="form-control " name="pic" value="${tbContent.pic}" readonly="true"/></a></td>
                                <td><a href="${tbContent.pic2}"><input type="text" class="form-control " name="pic2" value="${tbContent.pic2}" readonly="true"/></a></td>
                            </tr>

                        </table>
                        <table>
                            <tr>
                                <th >内容</th>
                            </tr>
                            <div class="form-group "></div>
                            <tr>
                                <td>
                                    <div id="Myeditor"> ${tbContent.content}</div>
                                    <%--<textarea id="container" name="container" style="width: 850px; height: 400px; margin: 0 auto;"></textarea>--%>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>