package com.db.tools2;


 /**
 @Author H.W
 @createTime 2012-9-7 ����10:53:50
 * ����һ�������࣬Ҳ�������࣬���������������ɹ�������Ҫ��һ�в���<br/>
 **/

public class ToolsHelper {

	/**
	 * ���ݿ����ͣ�Ĭ��ORACLE
	 */
	private String dbType = Constants.DB_TYPE_ORACLE;
	
	/**
	 * SQL���ͣ�Ĭ��ԭ��
	 */
	private String sqlType = Constants.SQL_TYPE_ORIGINAL;
	
	/**
	 * javaField�����ɹ滮��Ĭ��ʹ��DB�ֶ���
	 */
	private String javaFieldregulations = Constants.JAVA_FIELD_ORIGINAL;
	
	
	private String dbURL;
	
	private String dbUsername;
	
	private String dbPwd;
	
	private String driver;
	
	
	/**
	 * 
	 * @param driver 		����ʹ�õ�driverClass,�μ�{@link Constants.DRIVER_ORACLE_CLASS12}��{@link Constants.DRIVER_MYSQL_CONNECTOR}
	 * @param dbURL  		����URL
	 * @param dbUsername	����ʹ�õ��û���
	 * @param dbPwd			����ʹ�õ�����
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
	 * @param dbType					���ݿ����� �μ�{@link Constants}����DB_TYPE��ͷ�ĳ���
	 * @param sqlType					����SQL���� �μ�{@link Constants}����SQL_TYPE��ͷ�ĳ���
	 * @param javaFieldregulations		JAVA Field���ɹ���,�μ�{@link Constants}����JAVA_FIELD��ͷ�ĳ���
	*  @param driver 					����ʹ�õ�driverClass,�μ�{@link Constants.DRIVER_ORACLE_CLASS12}��{@link Constants.DRIVER_MYSQL_CONNECTOR}
	 * @param dbURL  					����URL
	 * @param dbUsername				����ʹ�õ��û���
	 * @param dbPwd						����ʹ�õ�����
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
