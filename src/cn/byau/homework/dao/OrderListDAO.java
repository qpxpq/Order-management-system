/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import cn.byau.homework.entity.OrderList;
import cn.byau.homework.utils.JDBCUtils;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("unused")
public class OrderListDAO {
	private final Logger logger = Logger.getLogger(OrderListDAO.class);
	

	/**
	 * 查询所有学生
	 * 
	 * @throws SQLException
	 */

	public List<OrderList> list() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "SELECT orderID ,product ,number ,name ,o.kindNo,kindName "
				+ "FROM orderList o LEFT JOIN kind k ON o.kindNo=k.kindNo";
		return queryRunner.query(sql, new BeanListHandler<OrderList>(OrderList.class));//????????????????????????????????????????????????????????????????????????????????????
	}
	/**
	 * 普通用户,管理自己班级的学生时使用???????????????????????????????????????????????????????????????????????????
	 * 
	 * @param kindNo
	 * @return 
	 * @throws SQLException
	 */

	public List<OrderList> listByKindNo(String kindNo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "SELECT orderID ,product ,number ,name ,o.kindNo,kindName "
				+ "FROM orderList o LEFT JOIN kind k "
				+ "ON o.kindNo=k.kindNo WHERE o.kindNo=?";
		return queryRunner.query(sql, new BeanListHandler<OrderList>(OrderList.class), kindNo);//????????????????????????????????????????????
	}

	/**
	 * 批量添加学生，从Excel中导入数据使用
	 */

	public boolean insertBatch(List<OrderList> orderList) {
		logger.info("insertBatch方法入参{}" + orderList);
		String sql = "INSERT INTO orderList((orderID, product, number, name, kindNo) VALUES(?,?,?,?,?)";
		boolean insertSuccessFlag = false;
		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
			// 得到连接对象
			Object[][] params = new Object[orderList.size()][];
			for (int i = 0; i < params.length; i++) {
				params[i] = new Object[] { 
						orderList.get(i).getOrderID(),
						orderList.get(i).getProduct(),
						orderList.get(i).getNumber(), 
						orderList.get(i).getName(),
						orderList.get(i).getKindNo() };
			}
			runner.batch(sql, params);

			// 如果插入成功，则肯定能执行到此段代码
			insertSuccessFlag = true;

		} catch (SQLException e) {
			logger.warn("insertBatch方法出现错误" + e.getMessage());
		}
		return insertSuccessFlag;
	}

	public boolean insert(OrderList orderList) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "INSERT INTO orderList(orderID, product, number, name, kindNo) VALUES(?,?,?,?,?)";
		Object[] params= {
				orderList.getOrderID(), 
				orderList.getProduct(), 
				orderList.getNumber(), 
				orderList.getName(),
				orderList.getKindNo()
				};
		return runner.update(sql, params) == 1;
	}

	/**
	 * 根据学号删除学生
	 */

	public boolean delete(String no) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String deleteString = "delete from orderList " + "where orderID=?";
		return runner.update(deleteString, no) == 1;
	}

	/**
	 * 批量删除学生
	 * 
	 * @param nos
	 * @return 
	 * @throws SQLException
	 */
	public boolean deleteBatchByNos(String[] nos) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		boolean successFlag = false;
		Object[][] params = new Object[nos.length][];// 高维也就是行数确定执行sql语句的次数，低维也就是列数是给？赋值
		for (int i = 0; i < params.length; i++) {// 循环行数,决定SQL语句执行的次数
			params[i] = new Object[] { nos[i] };// 给低维也就是列数“？”赋值，每行只给一列赋值，决定每条SQL语句的参数个数
		}
		runner.batch("delete from orderList where orderID=?", params);
		// 如果插入成功，则肯定能执行到此段代码
		successFlag = true;
		return successFlag;

	}

	/**
	 * 根据学号查询学生
	 */

	public OrderList getOrderList(String no) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT orderID, product, number, name, kindNo from orderList  where orderID=?";
		return queryRunner.query(sql, new BeanHandler<OrderList>(OrderList.class), no);//?????????????????????????????????????????????????????????????????????????????????????
	}

	/**
	 * 更新学生信息
	 */

	public boolean update(OrderList orderList) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String updateString = "update orderList set product=?,number=?,name=?,kindNO=? where orderID=?";
		Object[] params= {
				orderList.getProduct(), 
				orderList.getNumber(), 
				orderList.getName(),
				orderList.getKindNo(),
				orderList.getOrderID()		
		};
		int count = queryRunner.update(updateString,params);
		return count == 1;
	}

	/**
	 * 根据页码查询学生信息
	 */

	public List<OrderList> listByPage(int currentPage, String keyword) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT orderID ,product ,number ,name ,o.kindNo,kindName "
				+ "FROM orderList o LEFT JOIN kind k ON o.kindNo=k.kindNo "
				+ "WHERE o.orderID like ? or o.product like ?" + " limit ? , ?";
		// 第一个?号：需要查询多少条数据 第二个?号：跳过之前多少条记录
		// 第一页 --- 5 0 （currentPage-1）*5
		// 第二页 --- 5 5 （currentPage-1）*5
		// 第三页 --- 5 10 （currentPage-1）*5
		return queryRunner.query(sql, 
				new BeanListHandler<OrderList>(OrderList.class), //?????????????????????????????????????????????????????????????????????????????????????
				"%" + keyword + "%", 
				"%" + keyword + "%",
				(currentPage - 1) * 5,5);
	}

	/**
	 * 查询有多少条记录
	 */

	// ScalarHandler 返回查询结果第一行 某一个字段的值
	public int count(String keyword) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT count(*)  FROM orderList o LEFT JOIN kind k ON o.kindNo=k.kindNo "
				+ "WHERE o.orderID like ? or o.product like ?";
		Long result = (Long) queryRunner.query(sql, new ScalarHandler(1), "%" + keyword + "%",
				"%" + keyword + "%");
		return result.intValue();
	}

}
