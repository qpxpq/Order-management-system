<%-- 
    Document   : update
    Created on : 2012-9-21, 14:12:03
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>修改用户</h1>
        

        <form action="${pageContext.request.contextPath}/admin/OrderListServlet?method=update" method="post">
           <table border="0">
            <tr>
                <td>订单号</td>
                <td><input type="text" name="orderID" value="${o.orderID}"></td>
            </tr>
            <tr>
                <td>商品名</td>
                <td><input type="text" name="product" value="${o.product}"></td>
          </tr>
            <tr>
                <td>数量</td>
                <td><input type="text" name="number" value="${o.number}"></td>

            </tr>
            
            <tr>
                <td>姓名</td>
                <td><input type="text" name="name" value="${o.name}"></td>
            </tr>
            
              <tr>
                    <td>类型</td>
                    <td>
                        <SELECT  name="kindNo" style="width: 130px;">
                            <c:forEach items="${kindList}" var="kind">
                            
                            <option value="${kind.kindNo}" <c:if test="${kind.kindNo==o.kindNo}">selected </c:if> > ${kind.kindName} </option>
                            </c:forEach>
                        </SELECT>
                <tr>
                    <td><input type="submit" value="更新"></td>
                </tr>
            </table>

        </form>



    </body>
</html>
