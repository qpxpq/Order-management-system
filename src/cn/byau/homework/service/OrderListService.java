package cn.byau.homework.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.byau.homework.dao.OrderListDAO;
import cn.byau.homework.entity.Kind;
import cn.byau.homework.entity.OrderList;
import cn.byau.homework.utils.ExcelUtils;
import cn.byau.homework.utils.PageBean;


/**
 * 这是学生查询的业务实现
 * 
 * @author
 *
 */
public class OrderListService {
	private final Logger logger = Logger.getLogger(OrderListDAO.class);
	OrderListDAO orderListDAO = new OrderListDAO();

//	public List<orderList> list() throws SQLException {
//
//		orderListDAO orderListDAO = new orderListDAO();
//
//		return orderListDAO.list();
//	}

	public List<OrderList> listByKindNo(String kindNo){
		List<OrderList> list = null;

		try {
			list = orderListDAO.listByKindNo(kindNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

		
	}

	public boolean deleteBatchByNos(String[] nos)  {
		boolean f = false;
		try {
			f = orderListDAO.deleteBatchByNos(nos);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return f;

		
	}

	public OrderList getOrderList(String orderID)  {
		OrderList orderList = null;

		try {
			orderList = orderListDAO.getOrderList(orderID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
		//return orderListDAO.getorderList(orderID);
	}

	public int insert(OrderList orderList) {


		try {
			if(orderListDAO.getOrderList(orderList.getOrderID())!=null){

				return 0; //已经存在
				
			}
			if(orderListDAO.insert(orderList)) {

				return 1;
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return 2;
	}

	public boolean delete(String orderID)  {

		boolean f = false;
		try {
			f = orderListDAO.delete(orderID);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return f;

	}

	public boolean update(OrderList orderList)  {

		boolean f = false;
		try {
			f = orderListDAO.update(orderList);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return f;
	}

	/**
	 * 导出学生时使用
	 * 
	 * @return
	 * @throws Exception
	 */
	public HSSFWorkbook getHSSFWorkbook() throws Exception {
		OrderListDAO orderListDAO = new OrderListDAO();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<OrderList> orderListList = orderListDAO.list();
		// logger.info("getHSSFWorkbook方法入参{}" + orderListList);
		HSSFWorkbook wb = new HSSFWorkbook();
		// 标题行抽出字段
		String[] title = {  "订单号", "商品名", "数量", "姓名", "类型" };
		// 设置sheet名称，并创建新的sheet对象
		String sheetName = "订单信息一览";
		Sheet stuSheet = wb.createSheet(sheetName);
		// 获取表头行
		Row titleRow = stuSheet.createRow(0);
		// 创建单元格，设置style居中，字体，单元格大小等
		CellStyle style = wb.createCellStyle();
		Cell cell = null;
		// 把已经写好的标题行写入excel文件中
		for (int i = 0; i < title.length; i++) {
			cell = titleRow.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		// 把从数据库中取得的数据一一写入excel文件中
		Row row = null;
		for (int i = 0; i < orderListList.size(); i++) {
			// 创建list.size()行数据
			row = stuSheet.createRow(i + 1);
			// 把值一一写进单元格里
			// 设置第一列为自动递增的序号
			row.createCell(0).setCellValue(i + 1);
			row.createCell(1).setCellValue(orderListList.get(i).getOrderID());
			row.createCell(2).setCellValue(orderListList.get(i).getProduct());
			row.createCell(3).setCellValue(orderListList.get(i).getNumber());
			row.createCell(4).setCellValue(orderListList.get(i).getName());
			row.createCell(5).setCellValue(orderListList.get(i).getKindName());

			// 把时间转换为指定格式的字符串再写入excel文件中
//	        if (orderListList.get(i).getEnterTime() != null) {
//	            row.createCell(4).setCellValue(sdf.format(orderListList.get(i).getEnterTime()));
//	        }
		}
		// 设置单元格宽度自适应，在此基础上把宽度调至1.5倍
		for (int i = 0; i < title.length; i++) {
			stuSheet.autoSizeColumn(i, true);
			stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 15 / 10);
		}

		return wb;

	}

	public List<OrderList> ReadExcel(File f) {

		List<OrderList> orderListList = new ArrayList<OrderList>();
		OrderList orderList = null;
		String orderID = null;
		String product = null;
		int number = 0;
		String name = null;
		String kindNo = null;
		ExcelUtils excelUtils = new ExcelUtils();
		try {
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			HSSFWorkbook hssfworkbook = new HSSFWorkbook(new FileInputStream(f));
			HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
			HSSFRow hssfrow = null;
			// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
			int rows = hssfsheet.getPhysicalNumberOfRows();
			for (int j = 1; j <= rows - 1; j++) {
				hssfrow = hssfsheet.getRow(j);
				orderID = excelUtils.getValue(hssfrow.getCell(0));
				product = excelUtils.getValue(hssfrow.getCell(1));
				number = Integer.parseInt(excelUtils.getValue(hssfrow.getCell(2)));
				name = excelUtils.getValue(hssfrow.getCell(3));
				kindNo = excelUtils.getValue(hssfrow.getCell(4));
				orderList = new OrderList(orderID, product, number, name, kindNo);
				orderListList.add(orderList);
			}

		} catch (Exception e) {
			System.out.println("readExcel方法出现错误" + e.getMessage());
			// flag = false;
		}
		return orderListList;

	}

	

	/**
	 * 通过给定页查询学生信息
	 */

	public PageBean<OrderList> listByPage(int currentPage, String keyword) throws SQLException {

		// 生成分页显示的bean类型
		OrderListDAO orderListDao = new OrderListDAO();
		
		PageBean<OrderList> pageBean = new PageBean<OrderList>();
		pageBean.setCurrentPage(currentPage); // 设置当前页
		pageBean.setPageSize(5);// 设置每一页显示的数量
		List<OrderList> list = orderListDao.listByPage(currentPage, keyword);
		pageBean.setList(list); // 设置当前页面的学生数据

		int totalSize = orderListDao.count(keyword); // count是总记录数
		pageBean.setTotalSize(totalSize); // 设置总记录数
		int totalPage=totalSize % 5 == 0 ?totalSize/5 : totalSize/5+1;
		pageBean.setTotalPage(totalPage); // 设置总页数
		return pageBean;
	}

}
