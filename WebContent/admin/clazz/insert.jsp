<%-- 
    Document   : insert
    Created on : 2010-9-20, 10:21:47
    Author     : Administrator
--%>




<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>添加商品类型</h1>
        <form action="${pageContext.request.contextPath}/admin/KindServlet?method=insert" method="post">
            <table border="0">
                <tr>
                    <td>类型编号</td>
                    <td><input type="text" name="kindNo" value="10"></td>

                </tr>
                <tr>
                    <td>类型名称</td>
                    <td><input type="text" name="kindName" value="商品类型"></td>

                </tr>
               
            </table>
            <input type="submit" value="添加">
        </form>

    </body>
</html>
