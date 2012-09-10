package com.db.tools2.sql.updateprocess.sqlserver;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 ÏÂÎç01:13:09
 **/

public class OriginalSqlserverUpdateProcess implements ISQLProcess{

	@Override
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor) {
		StringBuilder updateSQL = new StringBuilder();				//IBATIS UPDATEÓï¾ä
		StringBuilder updateQuerySQL = new StringBuilder();
		updateSQL.append("UPDATE " + tbName);
		updateSQL.append("\r\n\tSET");
		for(DBDescVO dbDescVO:dbDescList){
			updateSQL.append("\r\n\t\t+\"" + dbDescVO.getColumnName() + "=?,\"");
			updateQuerySQL.append("\r\n\t\t+\" AND " + dbDescVO.getColumnName() + "=?");
		}
		String temp = updateSQL.toString().substring(0, updateSQL.length() - 2);
		temp = temp + "\"";
		updateSQL.delete(0, updateSQL.length());
		updateSQL.append(temp);
		updateSQL.append("\r\n\t+\"WHERE 1 = 1\"").append(updateQuerySQL);
		return updateSQL.toString();
	}

}
