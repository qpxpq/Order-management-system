/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.utils;

import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author Administrator
 */
public class ExcelUtils {
	/**
	 * @param cell 一个单元格的对象
	 * @return 返回该单元格相应的类型的值
	 */
	public String getValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		String value = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			} else {
				// 强制类型转换
				cell.setCellType(Cell.CELL_TYPE_STRING);
				value = cell.getStringCellValue();
				//为什么不直接用String.valueOf(cell.getNumericCellValue());
				//因为getNumericCellValue返回的是double。如果是整数，则带小数点。
				//在ReadExcel中 age = Integer.parseInt(excelUtil.getValue(hssfrow.getCell(2)));
				//会出错
				break;
			}

		case Cell.CELL_TYPE_STRING: // 字符串
			value = cell.getStringCellValue();
			break;

		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			value = cell.getBooleanCellValue() + "";
			break;

		case Cell.CELL_TYPE_FORMULA: // 公式
			value = cell.getCellFormula() + "";
			break;

		case Cell.CELL_TYPE_BLANK: // 空值
			value = "";
			break;
//		case Cell.CELL_TYPE_ERROR: // 故障
//			value = "非法字符";
//			break;

		default:
			value = "未知类型";
			break;
		}
		return value;
	}

}
