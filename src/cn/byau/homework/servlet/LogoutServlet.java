/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.byau.homework.service.UserService;

/**
 *
 * @author Administrator
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(LogoutServlet.class);
	UserService userService = new UserService();
	//ClazzService clazzService = new ClazzService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	

	public  void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");// 处理响应编码
		request.setCharacterEncoding("UTF-8");
		// 1 获得执行的方法名 selectAll
		String method = request.getParameter("method");
		if (method.equals("logout")) {
			logout(request, response);
		} 
		
	

	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         HttpSession session=request.getSession();  
		session.invalidate();
        response.sendRedirect("login.jsp");

	}

	

	

	

}
