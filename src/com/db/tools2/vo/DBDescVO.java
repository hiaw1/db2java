package com.db.tools2.vo;

 /**
 @Author H.W
 @createTime 2012-8-23 ����03:59:29
 @Company ������ͨ��Ϣ�Ƽ����޹�˾
 @Description ���������Ϣ
 **/

public class DBDescVO {

	/**
	 * �ֶ���
	 */
	private String columnName;
	
	/**
	 * ��������
	 */
	private String dataType;
	
	/**
	 * ��ע
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
