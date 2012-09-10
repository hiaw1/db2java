package com.db.tools2.fieldprocess;

import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 上午10:33:01
 这个接口主要是为了以后可以进行多种不同的实现
 比如说使用数据库原字段，以下划线为分隔的驼峰等
 **/

public abstract class BaseFieldProcessor {
	
	/**
	 * 
	 * 根据DB里的字段名输出对应的JAVA字段定义
	 * @param columnName
	 * @return
	 */
	public abstract String getJAVAField(String columnName);
	
	/**
	 * 
	 * 根据DB里的字段名完成得到JAVA字段的GET和SET方法 
	 * @param dbDescVO
	 * @return
	 */
	public String getFieldGetAndSetMethod(DBDescVO dbDescVO){
		String fieldType = ColumnType2JAVATypeUtils.getJAVAType(dbDescVO.getDataType().toUpperCase());
		String fieldName = getJAVAField(dbDescVO.getColumnName());
		String MethodFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
		
		StringBuilder methodStr = new StringBuilder();
		methodStr.append("\r\n\tpublic").append(fieldType).append("get").append(MethodFieldName).append("(){");
		methodStr.append("\r\n\t\treturn this.").append(fieldName).append(";");
		methodStr.append("\r\n\t}");
		
		methodStr.append("\r\n\tpublic void").append(" set").append(MethodFieldName).append("(").append(fieldType).append("" + fieldName).append("){");
		methodStr.append("\r\n\t\tthis.").append(fieldName).append(" = ").append(fieldName).append(";");
		methodStr.append("\r\n\t}\r\n");
		return methodStr.toString();
	}
	
	/**
	 * 
	 * 得到JAVA源码中的字段定义
	 * @param dbDescVO
	 * @return
	 */
	public String getJAVASrc(DBDescVO dbDescVO){
		StringBuilder result = new StringBuilder();
		if(dbDescVO.getComments() != null && dbDescVO.getComments().length() > 0){
			result.append("\r\n \t/**\r\n\t*\r\n\t").append(dbDescVO.getComments()).append("\r\n\t*/\r\n");
		}
		
	
		result.append("\tprivate").append(ColumnType2JAVATypeUtils.getJAVAType(dbDescVO.getDataType().toUpperCase()));
		result.append(getJAVAField(dbDescVO.getColumnName()) + ";\r\n");
		return result.toString();
	}
	
	/**
	 * 
	 * 根据DB里的字段名完成得到JAVA字段的GET和SET方法 
	 * @param dbDescVO
	 * @return
	 */
	public String getFieldIncludeMethod(DBDescVO dbDescVO){
		StringBuilder result = new StringBuilder();
		result.append(getJAVASrc(dbDescVO)).append(getFieldGetAndSetMethod(dbDescVO));
		return result.toString();
	}
	
}
