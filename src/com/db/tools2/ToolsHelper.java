package com.db.tools2;


 /**
 @Author H.W
 @createTime 2012-9-7 上午10:53:50
 * 这是一个辅助类，也是配置类，定义了在整个生成过程中需要的一切参数<br/>
 **/

public class ToolsHelper {

	/**
	 * 数据库类型，默认ORACLE
	 */
	private String dbType = Constants.DB_TYPE_ORACLE;
	
	/**
	 * SQL类型，默认原生
	 */
	private String sqlType = Constants.SQL_TYPE_ORIGINAL;
	
	/**
	 * javaField的生成规划，默认使用DB字段名
	 */
	private String javaFieldregulations = Constants.JAVA_FIELD_ORIGINAL;
	
	
	private String dbURL;
	
	private String dbUsername;
	
	private String dbPwd;
	
	private String driver;
	
	
	/**
	 * 
	 * @param driver 		连接使用的driverClass,参见{@link Constants.DRIVER_ORACLE_CLASS12}和{@link Constants.DRIVER_MYSQL_CONNECTOR}
	 * @param dbURL  		连接URL
	 * @param dbUsername	连接使用的用户名
	 * @param dbPwd			连接使用的密码
	 */
	public ToolsHelper(String driver, String dbURL, String dbUsername, String dbPwd) {
		super();
		this.driver = driver;
		this.dbURL = dbURL;
		this.dbUsername = dbUsername;
		this.dbPwd = dbPwd;
	}

	/**
	 * 
	 * @param dbType					数据库类型 参见{@link Constants}中以DB_TYPE开头的常量
	 * @param sqlType					生成SQL类型 参见{@link Constants}中以SQL_TYPE开头的常量
	 * @param javaFieldregulations		JAVA Field生成规则,参见{@link Constants}中以JAVA_FIELD开头的常量
	*  @param driver 					连接使用的driverClass,参见{@link Constants.DRIVER_ORACLE_CLASS12}和{@link Constants.DRIVER_MYSQL_CONNECTOR}
	 * @param dbURL  					连接URL
	 * @param dbUsername				连接使用的用户名
	 * @param dbPwd						连接使用的密码
	 */
	public ToolsHelper(String dbType, String sqlType,
			String javaFieldregulations, String driver, 
			String dbURL, String dbUsername, String dbPwd) {
		super();
		this.driver = driver;
		this.dbType = dbType;
		this.sqlType = sqlType;
		this.javaFieldregulations = javaFieldregulations;
		this.dbURL = dbURL;
		this.dbUsername = dbUsername;
		this.dbPwd = dbPwd;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getJavaFieldregulations() {
		return javaFieldregulations;
	}

	public void setJavaFieldregulations(String javaFieldregulations) {
		this.javaFieldregulations = javaFieldregulations;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPwd() {
		return dbPwd;
	}

	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	
	
}
