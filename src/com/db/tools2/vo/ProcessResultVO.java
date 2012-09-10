package com.db.tools2.vo;

 /**
 @Author H.W
 @createTime 2012-8-23 ����03:51:34
 @Company ������ͨ��Ϣ�Ƽ����޹�˾
 @Description ������ɺ����װ����
 **/

public class ProcessResultVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5034207860959036637L;

	private String javaSrc;
	
	private String insertSQL;
	
	private String updateSQL;
	
	private String querySQL;
	
	private String deleteSQL;

	public String getQuerySQL() {
		return querySQL;
	}

	public void setQuerySQL(String querySQL) {
		this.querySQL = querySQL;
	}

	public String getDeleteSQL() {
		return deleteSQL;
	}

	public void setDeleteSQL(String deleteSQL) {
		this.deleteSQL = deleteSQL;
	}

	public String getJavaSrc() {
		return javaSrc;
	}

	public void setJavaSrc(String javaSrc) {
		this.javaSrc = javaSrc;
	}

	public String getInsertSQL() {
		return insertSQL;
	}

	public void setInsertSQL(String insertSQL) {
		this.insertSQL = insertSQL;
	}

	public String getUpdateSQL() {
		return updateSQL;
	}

	public void setUpdateSQL(String updateSQL) {
		this.updateSQL = updateSQL;
	}
	
	
}
