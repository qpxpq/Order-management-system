<%-- 
    Document   : index
    Created on : 2015-11-15, 10:46:17
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <title></title>

            <link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/index.css" type="text/css" media="screen" />


            <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery.min.js"></script>
            <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/tendina.min.js"></script>
            <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/common.js"></script>

    </head>
    <body>
        <!--顶部-->
        <div class="layout_top_header">
            <div style="float: left"><span style="font-size: 16px;line-height: 45px;padding-left: 20px;color: #8d8d8d">管理后台</h1></span></div>
            <div id="ad_setting" class="ad_setting"></div>
        </div>
        <!--顶部结束-->
        <!--菜单-->
        <div class="layout_left_menu">
            <ul id="menu">
                 <li class="childUlLi">
                    <a href="#"  target="menuFrame"><i class="glyph-icon icon-home"></i>商品类型管理</a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/admin/KindServlet?method=list" target="menuFrame">
                                <i class="glyph-icon icon-chevron-right"></i>显示全部记录</a>
                        </li>
                    </ul>
                </li>
                
                <li class="childUlLi">
                    <a href="#"  target="menuFrame"><i class="glyph-icon icon-home"></i>订单管理</a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/admin/OrderListServlet?method=listByPage" target="menuFrame">
                                <i class="glyph-icon icon-chevron-right"></i>显示全部记录</a>
                        </li>
                        <li><a href="student/import/list.jsp" target="menuFrame">
                                <i class="glyph-icon icon-chevron-right"></i>导入数据</a>
                        </li>
                        <li><a href="student/output/list.jsp" target="menuFrame">
                                <i class="glyph-icon icon-chevron-right"></i>导出数据</a>
                        </li>
                       
                        


                    </ul>
                </li>
                
                 
              <li class="childUlLi">
                    <a href="#"  target="menuFrame"> <i class="glyph-icon icon-reorder"></i>日志管理</a>
                    <ul>
                        <li><a href="loginfo/list.jsp" target="menuFrame">
                                <i class="glyph-icon icon-chevron-right"></i>登录日志管理</a></li>
                                <li><a href="loginfo/list.jsp" target="menuFrame">
                                <i class="glyph-icon icon-chevron-right"></i>操作日志管理</a></li>
                       

                    </ul>
                </li>
              
              
              
                <li class="childUlLi">
                    <a href="#"  target="menuFrame"> <i class="glyph-icon icon-reorder"></i>系统管理</a>
                    <ul>
                        
                        <li><a href="password/modifypasswordfirst.jsp" target="menuFrame">
                                <i class="glyph-icon icon-chevron-right"></i>修改密码</a></li>
                       
                        <li><a href="${pageContext.request.contextPath}/LogoutServlet?method=logout"><i class="glyph-icon icon-chevron-right"></i>退出登录</a></li>

                    </ul>
                </li>


            </ul>
        </div>
        <!--菜单-->
        <div id="layout_right_content" class="layout_right_content">

            <div class="route_bg">
                <a href="#">主页</a><i class="glyph-icon icon-chevron-right"></i>
                <a href="#">管理</a>
            </div>
            <div class="mian_content">
                <div id="page_content">
                    <iframe id="menuFrame" name="menuFrame" src="welcome.jsp" style="overflow:visible;"
                            scrolling="yes" frameborder="no" width="100%" height="100%"></iframe>
                </div>
            </div>
        </div>
        <div class="layout_footer">
            <p>Copyright ©信息与电气工程学院</p>
        </div>
    </body>
</html>
