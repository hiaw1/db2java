package com.db.tools2.sql.selectprocess.mysql;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午01:13:09
 **/

public class OriginaMysqlSelectProcess implements ISQLProcess{

	@Override
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor) {
		StringBuilder sb = new StringBuilder();
		StringBuilder searchQuery = new StringBuilder();
		sb.append("SELECT ");
		for(DBDescVO dbDescVO:dbDescList){
			String javaFieldName = fieldProcessor.getJAVAField(dbDescVO.getColumnName());
			sb.append("\r\n\t" + dbDescVO.getColumnName() + " AS " + javaFieldName + ",");
			searchQuery.append("\r\n\t AND ").append(dbDescVO.getColumnName()).append("=?");
		}
		
		String temp = sb.toString().substring(0, sb.toString().length() - 1);
		sb.delete(0, sb.length());
		sb.append(temp);
		
		sb.append("\r\n\t FROM " + tbName);
		sb.append("\r\n\t WHERE 1=1 ");
		sb.append(searchQuery);
		return sb.toString();
	}

}
