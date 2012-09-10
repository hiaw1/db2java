package com.db.tools2.sql.insertprocess.oracle;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 ����01:13:09
 **/

public class OriginalOracleInsertProcess implements ISQLProcess{

	@Override
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor) {
		StringBuilder insertSQL = new StringBuilder();	//IBATIS INSERT���
		StringBuilder insertSQLKey = new StringBuilder(); //INSERTʱ��Ҫ�����KEY�Ӿ�
		StringBuilder insertSQLValue = new StringBuilder();//INSERTʱ��Ҫ��VALUE�Ӿ�
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
