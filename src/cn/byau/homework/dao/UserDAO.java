/**
 * @(#)UserDAO.java
 *
 *
 * @author
 * @version 1.00 2019/4/30
 */
package cn.byau.homework.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.byau.homework.entity.User;
import cn.byau.homework.utils.JDBCUtils;
import cn.byau.homework.utils.MD5;

public class UserDAO {

	
	/**
	 * 登录使用的方法
	 * @param userId
	 * @param password
	 * @return
	 */
	public User getUserByUserIDAndPassword(String userID, String password) 
			throws SQLException,NoSuchAlgorithmException{
		// 建立连接对象
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select userID,userName,password,userType,email"
				+ " from user "
				+ "where userID=? and password=?";
		String  MD5Password=MD5.getMD5(password);
		return queryRunner.query(sql, new BeanHandler<User>(User.class), 
				userID,MD5Password);	
		
		
	}
	

	
	/**
	 * 修改用户名和密码
	 * @param user
	 * @return
	 * @throws SQLException
	 */

	public boolean updatePassword(User user) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String updateString = "update user set password=? where UserID=?";
		int count = queryRunner.update(updateString, 
				user.getPassword(),
				user.getUserID());
		return count == 1;
	}
	

}