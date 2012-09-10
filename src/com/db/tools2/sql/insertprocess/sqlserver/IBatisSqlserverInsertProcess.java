package com.db.tools2.sql.insertprocess.sqlserver;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.fieldprocess.ColumnType2JAVATypeUtils;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午01:13:09
 **/

public class IBatisSqlserverInsertProcess implements ISQLProcess{

	@Override
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor) {
		StringBuilder insertSQL = new StringBuilder();	//IBATIS INSERT语句
		StringBuilder insertSQLKey = new StringBuilder(); //INSERT时需要插入的KEY子句
		StringBuilder insertSQLValue = new StringBuilder();//INSERT时需要的VALUE子句
		insertSQL.append("INSERT INTO " + tbName + "(");
		for(DBDescVO dbDescVO:dbDescList){
			String javaFieldName = ColumnType2JAVATypeUtils.getJAVAType(dbDescVO.getDataType());
			insertSQLKey.append("\r\n\t<isNotEmpty prepend=\",\" property=\"" + javaFieldName.toString() + "\">" + dbDescVO.getColumnName() + " </isNotEmpty>");
			insertSQLValue.append("\r\n\t<isNotEmpty prepend=\",\" property=\"" + javaFieldName.toString() + "\"> #" + javaFieldName.toString() + "# </isNotEmpty>");
		}
		
		insertSQL.append(insertSQLKey);
		insertSQL.append("\r\n ) VALUES (");
		insertSQL.append(insertSQLValue);
		insertSQL.append("\r\n)");
		return insertSQL.toString();
	}

}
