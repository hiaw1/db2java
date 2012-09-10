package com.db.tools2.vo;

 /**
 @Author H.W
 @createTime 2012-8-23 下午03:59:29
 @Company 联网汇通信息科技有限公司
 @Description 表格描述信息
 **/

public class DBDescVO {

	/**
	 * 字段名
	 */
	private String columnName;
	
	/**
	 * 数据类型
	 */
	private String dataType;
	
	/**
	 * 备注
	 */
	private String comments;

	
	public DBDescVO(String columnName, String dataType, String comments) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.comments = comments;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
