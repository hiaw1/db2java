package com.db.tools2;

 /**
 @Author H.W
 @createTime 2012-9-7 上午11:29:26
 **/

public class Constants {

	/**
	 * ORACLE数据库
	 */
	public static String DB_TYPE_ORACLE = "ORACLE";
	
	/**
	 * MYSQL数据库
	 */
	public static String DB_TYPE_MYSQL = "MYSQL";
	
	/**
	 * SQLServer数据库
	 */
	public static String DB_TPYE_SQLSERVER = "SQLSERVER";
	
	
	/**
	 * 原生SQL
	 */
	public static String SQL_TYPE_ORIGINAL = "ORIGINAL";
	
	/**
	 * IBATIS SQL
	 */
	public static String SQL_TYPE_IBATIS = "IBATIS";
	
	/**
	 * MyBatis SQL
	 */
	public static String SQL_TYPE_MYBATIS = "MYBATIS";
	
	/**
	 * 使用DB中的字段名作为JAVA FIELD名称
	 */
	public static String JAVA_FIELD_ORIGINAL = "FIELDORIGINAL";
	
	/**
	 * 以下划线分隔生成对应的JAVA FILED名称（以下划线分隔）
	 */
	public static String JAVA_FIELD_CAMEL_HUMP = "CAMELHUMP";
	
	/**
	 * ORACLE的class12连接字符DRIVER
	 */
	public static String DRIVER_ORACLE_CLASS12 = "oracle.jdbc.OracleDriver";
	
	/**
	 * MYSQL的mysql-connector连接DRIVER
	 */
	public static String DRIVER_MYSQL_CONNECTOR = "com.mysql.jdbc.Driver";
}
