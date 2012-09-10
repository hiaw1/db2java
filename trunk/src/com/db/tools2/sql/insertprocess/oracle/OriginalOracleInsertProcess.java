package com.db.tools2.sql.insertprocess.oracle;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午01:13:09
 **/

public class OriginalOracleInsertProcess implements ISQLProcess{

	@Override
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor) {
		StringBuilder insertSQL = new StringBuilder();	//IBATIS INSERT语句
		StringBuilder insertSQLKey = new StringBuilder(); //INSERT时需要插入的KEY子句
		StringBuilder insertSQLValue = new StringBuilder();//INSERT时需要的VALUE子句
		insertSQL.append("INSERT INTO " + tbName + "(");
		for(DBDescVO dbDescVO:dbDescList){
			insertSQLKey.append(dbDescVO.getColumnName() + ",");
			insertSQLValue.append("?,");
		}
		
		String insertKeyTemp = insertSQLKey.toString().substring(0, insertSQLKey.length() - 1);
		insertSQLKey.delete(0, insertSQLKey.length());
		insertSQLKey.append(insertKeyTemp);
		
		String insertValueTemp = insertSQLValue.toString().substring(0, insertSQLValue.length() - 1);
		insertSQLValue.delete(0, insertSQLValue.length());
		insertSQLValue.append(insertValueTemp);
		
		insertSQL.append(insertSQLKey);
		insertSQL.append("\r\n ) VALUES (");
		insertSQL.append(insertSQLValue);
		insertSQL.append(")");
		return insertSQL.toString();
	}

}
