<%-- 
    Document   : list
    Created on : 2019-10-27, 6:51:11
    Author     : tai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/admin/OrderListServlet?method=download&fileName=data.xls">下载模板</a>
        <a href="upload.jsp">上传文件</a>
        
    </body>
</html>
