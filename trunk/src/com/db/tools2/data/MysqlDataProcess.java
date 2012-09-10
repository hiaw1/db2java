package com.db.tools2.data;

import java.sql.ResultSet;

import com.db.tools2.utils.LoggerUtils;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午08:48:04
 **/

public class MysqlDataProcess extends BaseDataProcess{
	
	private static MysqlDataProcess instance = new MysqlDataProcess();
	
	private MysqlDataProcess(){
		structSQL = "show full fields from `TABLENAME`";
	}
	
	public static MysqlDataProcess getInstance(){
		return instance;
	}

	@Override
	protected DBDescVO getDBDesc(ResultSet rs) {
		try{
			String columnName = rs.getString("Field");
			String dataType = rs.getString("type");
			String comments = rs.getString(("Comment"));
			return new DBDescVO(columnName, dataType, comments);
		}catch (Exception e) {
			LoggerUtils.getLogger().error(e.getMessage(), e);
			return null;
		}
	}

}
