/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cn.byau.homework.entity.Kind;
import cn.byau.homework.utils.JDBCUtils;

/**
 *
 * @author Administrator
 */
public class KindDAO {


	/**
	 * 查询所有学生
	 * 查询商品种类
	 * @throws SQLException
	 */

	public List<Kind> list() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "select kindNo,kindName from kind order by kindNo";
		return queryRunner.query(sql, new BeanListHandler<Kind>(Kind.class));//???????????????????????????????????????????????????????????????????????
	}

	/**
	 * 添加学生
	 * 添加商品种类
	 */

	public boolean insert(Kind kind) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "INSERT INTO kind(" + "kindNo,kindName" + " ) VALUES(?,?)";
		return runner.update(sql, kind.getkindNo(), kind.getkindName()) == 1;
	}

	/**
	 * 根据学号删除学生
	 * 根据商品种类号删除商品种类
	 */

	public boolean delete(String kindNo) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String deleteString = "delete from kind " + "where kindNo=?";
		return runner.update(deleteString, kindNo) == 1;
	}

	/**
	 * 根据学号查询学生
	 * 根据商品种类号查询商品种类
	 */

	public Kind getKind(String kindNo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select kindNo,kindName " + "from kind where kindNo=?";
		return queryRunner.query(sql, new BeanHandler<Kind>(Kind.class), kindNo);
	}

	/**
	 * 更新学生信息
	 */

	public boolean update(Kind kind) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String updateString = "update kind set kindName=? where kindNo=?";
		return queryRunner.update(updateString,
				kind.getkindName(),
				kind.getkindNo()) == 1;
	}

}
