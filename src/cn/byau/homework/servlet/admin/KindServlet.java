/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.byau.homework.entity.Kind;
import cn.byau.homework.entity.OrderList;
import cn.byau.homework.service.KindService;
import cn.byau.homework.utils.CommonUtils;

/**
 *
 * @author Administrator
 */
@WebServlet("/admin/KindServlet")
public class KindServlet extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(OrderListServlet.class);
	KindService kindService = new KindService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public  void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");// 处理响应编码
		request.setCharacterEncoding("UTF-8");
		// 1 获得执行的方法名 selectAll
		String method = request.getParameter("method");
		if (method.equals("insertPre")) {
			insertPre(request, response);
		} else if (method.equals("insert")) {
			insert(request, response);
		} else if (method.equals("updatePre")) {
           updatePre(request, response); 
		} else if (method.equals("update")) {
           update(request, response);
		}
		else if (method.equals("list")) {
	           list(request, response);
			}
		else if (method.equals("delete")) {
	           delete(request, response);
			}
		
	

	}

	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		try {
			List<Kind> kindList = kindService.list();
			

			request.setAttribute("kindList", kindList);
			String path = "clazz/list.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			
		} catch (Exception e) {
			logger.warn("list方法出现错误" + e.getMessage());
		}

	}

	

	public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { // 1、接收插入的信息

		try {
			//String kindNo = request.getParameter("kindNo");
			//String kindName = request.getParameter("kindName");
			//Kind kind = new Kind (kindNo,kindName);
			//OrderList orderList = CommonUtils.toBean(request.getParameterMap(), OrderList.class);
			//List<Kind> kindList = kindService.list();
				
			Kind kind = CommonUtils.toBean(request.getParameterMap(), Kind.class);
			System.out.print(kind);
			logger.info("insert方法入参{}" + kind);
			System.out.print(kind);
			PrintWriter out = response.getWriter();
			//clazz.setClazzName("里");
			int result = kindService.insert(kind);
			if (result==0) {
				out.println("数据已经存在");
			}
			else if (result==1) {
				out.println("添加成功，过1秒到学生列表页面");
			} else {
				out.println("添加失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;KindServlet?method=list");

		} catch (Exception e) {

			logger.warn("insert方法出现错误" + e.getMessage());
		}
	}
	public void insertPre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException { 

		try {
			
			String path = "clazz/insert.jsp";
			request.getRequestDispatcher(path).forward(request, response);

		} catch (Exception e) {
			logger.warn("方法出现错误" + e.getMessage());
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			request.setCharacterEncoding("UTF-8");
			Kind kind = CommonUtils.toBean(request.getParameterMap(), Kind.class);
			logger.info("update方法入参{}" + kind);
			PrintWriter out = response.getWriter();
			boolean f = kindService.update(kind);
			if (f) {
				out.println("更新成功，过1秒到学生列表页面");

			} else {
				out.println("更新失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;KindServlet?method=list");
		} catch (Exception e) {
			logger.warn("update方法出现错误" + e.getMessage());
		}
	}
	public void updatePre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 接收参数
		try {
			String kindNo = request.getParameter("kindNo");
			logger.info("updatePre方法入参{}" + kindNo);
			request.setAttribute("kind", kindService.getKind(kindNo));

		
		
			String path = "clazz/update.jsp";

			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			logger.warn("delete方法出现错误" + e.getMessage());
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			String kindNo = request.getParameter("kindNo");

			logger.info("delete方法入参{}" + kindNo);
			PrintWriter out = response.getWriter();
			boolean f = kindService.delete(kindNo);
			if (f) {
				out.println("删除成功，过1秒到学生列表页面");

			} else {
				out.println("删除失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;KindServlet?method=list");
		} catch (Exception e) {
			logger.warn("delete方法出现错误" + e.getMessage());
		}
	}

	

}
