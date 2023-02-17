<%-- 
    Document   : list_Questions
    Created on : 2017-9-17, 19:06:01
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
        <script>
            function checkAll() {
                var nos = document.getElementsByName("orderID");
                for (var i = 0; i < nos.length; i++) {
                    nos[i].checked = document.getElementById("ifAll").checked;
                }
            }

            function selectOrderList() {
                var nos = document.getElementsByName("orderID");
                var flag = false;
                for (var i = 0; i < nos.length; i++) {
                    if (nos[i].checked) {
                        flag = true;
                        break;
                    }
                }
                if (flag==false) {
                    alert("请选择需要删除的订单");
                    return;
                }
                if (window.confirm("确认删除吗")) {
                    document.OrderListform.submit();

                }
            }

        </script>
    </head>
    <body>
        
        
        <form
		action="OrderListServlet?method=listByPage"
		method="post" id="f1" class="form-inline">
		<div class="row alert alert-info form-inline"
			style="margin: 0px; padding: 5px;">
			<div class="form-group">
				 <input type="text" class="form-control"
					name="keyword" value="${keyword}" placeholder="请输入订单号或商品" />
			</div>
			<input type="submit" value="查询订单" class="btn btn-success" /> <a
				href="${pageContext.request.contextPath}/admin/OrderListServlet?method=insertPre"
				class="btn btn-info">添加订单</a>
				
				
         <input   type="button" value="删除" class="btn btn-danger" onClick="selectOrderList()">
		</div>
	</form>
         
        <form name="OrderListform"  action="${pageContext.request.contextPath}/admin/OrderListServlet?method=deleteBatchByNos" method="post">
            <%

            %>
            <table class="table table-striped table-bordered" >
                <tr>
                    <th width="10"  align="center">
                        <input type="checkbox" name="ifAll" id="ifAll"
                               onClick="checkAll()">
                    </th>
                    <th>订单号</th>
                    <th>商品名</th>
                    <th>数量</th>
                    <th>姓名</th>
                    <th>类型</th>
                    <th>操作</th>
                    

                </tr>
                </tr>
               <c:forEach items="${pageBean.list}" var="o">
                <tr>
                    <td>
                        <input type="checkbox" name="orderID" 
                               value="${o.orderID} ">
                    </td>
                    <td>
                        ${o.orderID}
                    </td>
                    <td>
                        ${o.product}
                    </td>
                    <td>
                        ${o.number}
                    </td>
                    <td>
                        ${o.name}
                    </td>
                    <td>
                        ${o.kindNo}
                    </td>
                     <td>
                    <a href="${pageContext.request.contextPath}/admin/OrderListServlet?method=updatePre&no=${o.orderID}"  class="btn btn-info">更新</a>  
                </td>
                </tr>     
                </c:forEach>
                 <tr>
			  	<td colspan="7">
			  		第 ${pageBean.currentPage } / ${pageBean.totalPage }
			  		&nbsp;&nbsp;
			  		每页显示${pageBean.pageSize }条  &nbsp;&nbsp;&nbsp;
			  		总的记录数${pageBean.totalSize } &nbsp;&nbsp;&nbsp;
			  		<c:if test="${pageBean.currentPage !=1 }">
			  			<a href="OrderListServlet?method=listByPage&keyword=${keyword}&currentPage=1">首页</a>
						| <a href="OrderListServlet?method=listByPage&keyword=${keyword}&currentPage=${pageBean.currentPage-1 }">上一页</a>
			  		</c:if>
			  		
			  		
			  		<c:forEach begin="1" end="${pageBean.totalPage}" var="i">
			  			<c:if test="${pageBean.currentPage == i }">
			  				${i}
			  			</c:if>
			  			<c:if test="${pageBean.currentPage != i }">
			  				<a href="OrderListServlet?method=listByPage&keyword=${keyword}&currentPage=${i }">${i }</a>
			  			</c:if>
			  		
			  		</c:forEach>
			  		
			  		
			  		<c:if test="${pageBean.currentPage !=pageBean.totalPage }">
			  			<a href="OrderListServlet?method=listByPage&keyword=${keyword}&currentPage=${pageBean.currentPage+1 }">下一页</a> | 
			  			<a href="OrderListServlet?method=listByPage&keyword=${keyword}&currentPage=${pageBean.totalPage }">尾页</a>
			  		</c:if>
			  	</td>
			  </tr>
                
            </table>
        </form>
    </body>
</html>
