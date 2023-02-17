<%-- 
    Document   : list_Questions
    Created on : 2012-9-17, 19:06:01
    Author     : Administrator
--%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
        <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <title>JSP Page</title>
        <script>
            function del(kindNo) {
                if (confirm("你确定要删除吗")) {
                    window.location = "${pageContext.request.contextPath}/admin/KindServlet?method=delete&kindNo=" + kindNo;
                }
            }
        </script>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/admin/KindServlet?method=insertPre">添加商品类型</a> 
        <h1>商品类型数据</h1>
        <table class="table  table-striped">

            <tr>
                <th>类型号</th>

                <th>类型名</th>



                <th colspan="2">操作</th>
            </tr>
            
            <c:if test="${kindList== null || fn:length(kindList) == 0}">
                             <h3>  查询出错或没有数据</h3>
            </c:if>
            <c:forEach items="${kindList}" var="k">

            <tr>
                <td>
                    ${k.kindNo}
                </td>
                <td>
                     ${k.kindName}
                </td>


                <td>
                    <a href="${pageContext.request.contextPath}/admin/KindServlet?method=updatePre&kindNo=${k.kindNo}">更新</a>  
                </td>
                <td>
                    <a href="javascript:del('${k.kindNo}')">删除</a>
                </td>
            </tr>
          </c:forEach>
          
        </table>
    </body>
</html>
