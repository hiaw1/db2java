package com.db.tools2.sql.deleteprocess.sqlserver;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 ÏÂÎç01:13:09
 **/

public class IBatisSqlserverDeleteProcess implements ISQLProcess{

	@Override
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t<delete id=\"delete\"" + tbName + ">");
		sb.append("\r\n\t\tDELETE FROM " + tbName);
		sb.append("\r\n\tWHERE ID=#id#");
		sb.append("\r\n\t</delete>");
		return sb.toString();
	}

}
