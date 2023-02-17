<%-- 
    Document   : insert
    Created on : 2010-9-20, 10:21:47
    Author     : Administrator
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>添加订单</h1>
        <form action="${pageContext.request.contextPath}/admin/OrderListServlet?method=insert" method="post">
            <table border="0">

                    <td>商品</td>
                    <td><input type="text" name="product" value="泡面"></td>

                </tr>
                <tr>
                    <td>数量</td>
                    <td><input type="text" name="number" value="10"></td>

                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" value="张三"></td>

                </tr>
                <tr>
                    <td>种类</td>
                    <td>
                        <SELECT  name="kindNo" style="width: 130px;">
                            <c:forEach items="${kindList}" var="kind">
                            
                            <option value="${kind.kindNo}"> ${kind.kindName} </option>
                            </c:forEach>
                        </SELECT>
                    </td>

                </tr>
            </table>
            <input type="submit" value="添加">
        </form>

    </body>
</html>
