package com.db.tools2.data;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.tools2.ToolsHelper;
import com.db.tools2.utils.LoggerUtils;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午08:12:59
 **/

public abstract class BaseDataProcess {

	protected ToolsHelper helper;
	
	protected String structSQL;
	
	
	
	public String getStructSQL() {
		return structSQL;
	}



	public void setStructSQL(String structSQL) {
		this.structSQL = structSQL;
	}



	public ToolsHelper getHelper() {
		return helper;
	}



	public void setHelper(ToolsHelper helper) {
		this.helper = helper;
	}



	/**
	 * 
	 * 得到表对应的字段描述文件
	 * @param tableName 目标表名
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<DBDescVO> getAllList(String tableName){
		List<DBDescVO> dbDescVOList = new ArrayList<DBDescVO>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			Driver driver = (Driver)Class.forName(helper.getDriver()).newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(helper.getDbURL(), helper.getDbUsername(), helper.getDbPwd());
			String struct = structSQL.replace("TABLENAME", tableName);
			
			statement = conn.prepareStatement(struct);
			rs = statement.executeQuery();
			
			while(rs.next()){
				dbDescVOList.add(getDBDesc(rs));
				
			}
		}catch (Exception e) {
			LoggerUtils.getLogger().error(e.getMessage(), e);
		}finally{
			try{
				rs.close();
				statement.close();
				conn.close();
			}catch(Exception e){
				LoggerUtils.getLogger().error(e.getMessage(), e);
			}
			return dbDescVOList;
		}
	}
	
	/**
	 * 
	 * 根据结果集获得对应的DBDescVO
	 * @param rs
	 * @return
	 */
	protected abstract DBDescVO getDBDesc(ResultSet rs);
}
