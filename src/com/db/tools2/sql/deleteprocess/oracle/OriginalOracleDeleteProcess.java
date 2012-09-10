package com.db.tools2.sql.deleteprocess.oracle;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 ÏÂÎç01:13:09
 **/

public class OriginalOracleDeleteProcess implements ISQLProcess{

	@Override
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tDELETE FROM " + tbName);
		sb.append("\r\n\tWHERE ID=?");
		sb.append("\r\n\tint[] types = {Types.INTEGER};");
		sb.append("\r\n\tObject[] params = {id};");
		return sb.toString();
	}

}
