package cn.byau.homework.service;

import java.sql.SQLException;
import java.util.List;

import cn.byau.homework.dao.KindDAO;
import cn.byau.homework.entity.Kind;
import cn.byau.homework.utils.PageBean;

/**
 * 这是学生查询的业务实现
 * 
 * @author
 *
 */
public class KindService {

	KindDAO kindDAO = new KindDAO();

	public List<Kind> list() {

		List<Kind> list = null;

		try {
			list = kindDAO.list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Kind getKind(String kindNo) {
		Kind kind = null;

		try {
			kind = kindDAO.getKind(kindNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kind;

	}

	public int insert(Kind kind) {
		
		
		try {
			if(kindDAO.getKind(kind.getkindNo())!=null){
				return 0; //班级已经存在
			}
			if(kindDAO.insert(kind)) {
				return 1;
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return 2;
	}

	public boolean delete(String kindNo) {

		boolean f = false;
		try {
			f = kindDAO.delete(kindNo);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return f;
	}

	public boolean update(Kind kind)  {
            
		boolean f = false;
		try {
			f = kindDAO.update(kind);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return f;
	}

}
