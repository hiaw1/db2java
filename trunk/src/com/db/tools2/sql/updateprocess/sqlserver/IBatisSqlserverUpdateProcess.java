package com.db.tools2.sql.updateprocess.sqlserver;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午01:13:09
 **/

public class IBatisSqlserverUpdateProcess implements ISQLProcess{

	@Override
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor) {
		StringBuilder updateSQL = new StringBuilder();				//IBATIS UPDATE语句
		StringBuilder searchQuery = new StringBuilder();			//WHERE后面的子句
		
		updateSQL.append("UPDATE " + tbName);
		updateSQL.append("\r\n\t<dynamic prepend=\"SET\">");
		for(DBDescVO dbDescVO:dbDescList){
			String javaFieldName = fieldProcessor.getJAVAField(dbDescVO.getColumnName());
			if(!dbDescVO.getColumnName().toLowerCase().equals("id")){
				updateSQL.append("\r\n\t\t<isNotNull prepend=\",\" property=\"" + javaFieldName.toString() + "\"> " + dbDescVO.getColumnName() + " = #" + javaFieldName.toString() + "# </isNotNull>");
				searchQuery.append("\r\n\t\t<isNotNull prepend=\"AND\" property=\"" + javaFieldName.toString() + "\"> " + dbDescVO.getColumnName() + " = #" + javaFieldName.toString() + "# </isNotNull>");
			}
		}
		updateSQL.append("\r\n\tWHERE 1=1 ");
		updateSQL.append(searchQuery.toString());
		return updateSQL.toString();
	}

}
