<%-- 
    Document   : update
    Created on : 2012-9-21, 14:12:03
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>





<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>商品类型</h1>
       

        <form action="${pageContext.request.contextPath}/admin/KindServlet?method=update" method="post">
            <table border="0">
                <tr>
                    <td>类型编号</td>
                    <td><input type="text" name="kindNo" value="${kind.kindNo} "></td>

                </tr>
                <tr>
                    <td>类型名称</td>
                    <td><input type="text" name="kindName" value="${kind.kindName}"></td>

                </tr>

          

            <tr>
                <td><input type="submit" value="更新"></td>
            </tr>


        </table>

    </form>



</body>
</html>
