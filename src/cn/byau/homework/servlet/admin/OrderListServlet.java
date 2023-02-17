/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.sql.dialect.postgresql.visitor.PGEvalVisitor;

import cn.byau.homework.entity.Kind;
import cn.byau.homework.entity.OrderList;
import cn.byau.homework.service.KindService;
import cn.byau.homework.service.OrderListService;
import cn.byau.homework.utils.CommonUtils;
import cn.byau.homework.utils.PageBean;

/**
 *
 * @author Administrator
 */
@WebServlet("/admin/OrderListServlet")//此处OrderListServlet改为StudentServlet后订单管理显示全部页面正常
public class OrderListServlet extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(OrderListServlet.class);
	OrderListService orderListService = new OrderListService();
	KindService kindService = new KindService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		} else if (method.equals("listByPage")) {
			listByPage(request, response);

		} else if (method.equals("deleteBatchByNos")) {
			deleteBatchByNos(request, response);
		} else if (method.equals("exportExcel")) {
			exportExcel(request, response);
		} else if (method.equals("download")) {
			download(request, response);
		}

	}

	public void insertPre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException { // 1、接收插入的信息

		try {
			List<Kind> kindList = kindService.list();
			logger.info("种类数据" + kindList);

			request.setAttribute("kindList", kindList);
			String path = "student/insert.jsp";
			request.getRequestDispatcher(path).forward(request, response);

		} catch (Exception e) {
			logger.warn("insertPre方法出现错误" + e.getMessage());
		}
	}

	public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { // 1、接收插入的信息

		try {
			OrderList orderList = CommonUtils.toBean(request.getParameterMap(), OrderList.class);
			logger.info("insert方法入参{}" + orderList);
			PrintWriter out = response.getWriter();
			int result = orderListService.insert(orderList);
			if (result == 0) {
				out.println("数据已经存在");
			} else if (result == 1) {
				out.println("添加成功，过1秒到订单列表页面");
			} else {
				out.println("添加失败，过1秒到订单列表页面");
			}
			response.setHeader("refresh", "1;OrderListServlet?method=listByPage");

		} catch (Exception e) {

			logger.warn("insert方法出现错误" + e.getMessage());
		}
	}

	public void updatePre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 接收参数
		try {
			String orderID = request.getParameter("orderID");
			logger.info("orderID{}" ,orderID );
            OrderList orderList=orderListService.getOrderList(orderID);
			
			request.setAttribute("o", orderList);

			List<Kind> kindList = kindService.list();
			logger.info("订单数据" + kindList);

			request.setAttribute("kindList", kindList);

			String path = "student/update.jsp";

			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			OrderList orderList = CommonUtils.toBean(request.getParameterMap(), OrderList.class);
			logger.info("update方法入参{}" + orderList);
			PrintWriter out = response.getWriter();
			boolean f = orderListService.update(orderList);
			if (f) {
				out.println("更新成功，过1秒到订单列表页面");

			} else {
				out.println("更新失败，过1秒到订单列表页面");
			}
			response.setHeader("refresh", "1;OrderListServlet?method=listByPage");
		} catch (Exception e) {
			logger.warn("update方法出现错误" + e.getMessage());
		}
	}

	public void deleteBatchByNos(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		String nos[] = request.getParameterValues("orderID");
		logger.info("批量删除方法入参{}" + Arrays.toString(nos));
		PrintWriter out = response.getWriter();
		try {
			boolean f = orderListService.deleteBatchByNos(nos);
				
			if (f) {
				out.println("删除成功，过1秒到订单列表页面");

			} else {
				out.println("删除失败，过1秒到订单列表页面");
			}
			response.setHeader("refresh", "1;OrderListServlet?method=listByPage");
		} catch (Exception e) {
			logger.warn("批量删除方法出现错误" + e.getMessage());
		}
	}

	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		// response.getWriter().write("你看着办");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + "data.xls");
		try {
			ServletOutputStream out1 = response.getOutputStream();
			// out1.flush();
			HSSFWorkbook wb = orderListService.getHSSFWorkbook();
			wb.write(out1);
			out1.close();
		} catch (Exception e) {
			logger.warn("导出方法出现错误" + e.getMessage());
		}

	}

	// 下载模板时使用
	public void download(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String fileName = request.getParameter("fileName");
		response.setHeader("content-disposition", "attachment;filename=" + fileName);

		String path = getServletContext().getRealPath("/") + "download/";
		FileInputStream is = new FileInputStream(path + fileName);

		ServletOutputStream out2 = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			out2.write(buffer, 0, len);
		}
		out2.close();

	}

	public void listByPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 接收参数
		try {
			// 1.获取要显示的页码数
			String sCurrentPage = request.getParameter("currentPage");
			if (sCurrentPage == null) {
				sCurrentPage = "1";
			}
			int currentPage = Integer.parseInt(sCurrentPage);

			String keyword = request.getParameter("keyword");
			if (keyword == null) {
				keyword = "";
			}

			// 2.根据指定的页数，取得响应的信息

			PageBean<OrderList> pageBean1 = orderListService.listByPage(currentPage, keyword);
			logger.info("pagebean{}" + pageBean1);
			request.setAttribute("pageBean", pageBean1);
			request.setAttribute("keyword", keyword);

			// 3.携带信息，跳转页面显示
			request.getRequestDispatcher("student/listByPage.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn("listByPage方法出现错误" + e.getMessage());
		}

	}

}
