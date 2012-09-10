package com.db.tools2.sql.insertprocess;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.Constants;
import com.db.tools2.exception.DBSQLException;
import com.db.tools2.fieldprocess.JAVAFieldProcessorManager;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.sql.insertprocess.mysql.IBatisMysqlInsertProcess;
import com.db.tools2.sql.insertprocess.mysql.MyBatisMysqlInsertProcess;
import com.db.tools2.sql.insertprocess.mysql.OriginaMysqlInsertProcess;
import com.db.tools2.sql.insertprocess.oracle.IBatisOracleInsertProcess;
import com.db.tools2.sql.insertprocess.oracle.MyBatisOracleInsertProcess;
import com.db.tools2.sql.insertprocess.oracle.OriginalOracleInsertProcess;
import com.db.tools2.sql.insertprocess.sqlserver.IBatisSqlserverInsertProcess;
import com.db.tools2.sql.insertprocess.sqlserver.MyBatisSqlserverInsertProcess;
import com.db.tools2.sql.insertprocess.sqlserver.OriginalSqlserverInsertProcess;

 /**
 @Author H.W
 @createTime 2012-9-7 上午11:16:47
 *　INSERT PROCESSOR 管理者<br/>
 *  管理方式同{#link {@link JAVAFieldProcessorManager}<br/>
 **/

public class InsertProcessorManager {
	private Map<String, ISQLProcess> sqlProcessMap = new HashMap<String, ISQLProcess>();

	private static InsertProcessorManager instance = new InsertProcessorManager(); 
	
	private InsertProcessorManager(){
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_IBATIS, new IBatisOracleInsertProcess());
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisOracleInsertProcess());
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginalOracleInsertProcess());
		
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_IBATIS, new IBatisMysqlInsertProcess());
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisMysqlInsertProcess());
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginaMysqlInsertProcess());

		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_IBATIS, new IBatisSqlserverInsertProcess());
		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisSqlserverInsertProcess());
		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginalSqlserverInsertProcess());
	}
	
	public static InsertProcessorManager getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * 得到对应的Insert生成器<br/>
	 * @param dbType DB_TYPE+SQL_TYPE  可参见Constants里的DB_TYPE开头和SQL_TYPE_开头的变量
	 * @return
	 * @throws DBSQLException 
	 */
	public ISQLProcess getInsertProcessor(String type) throws DBSQLException{
		ISQLProcess processor = sqlProcessMap.get(type.toUpperCase());
		if(processor == null){
			throw new DBSQLException("Con't find " + type + " InsertProcessor");
		}
		return processor;
	}
	
	/**
	 * 
	 * 注册一个新的Insert生成器，以后有新的处理方式，直接调用该方法，即可<br/>
	 * @param name
	 * @param insertProcessor
	 * @return
	 */
	public ISQLProcess registerInsertProcessor(String name, ISQLProcess insertProcessor){
		synchronized (sqlProcessMap) {
			sqlProcessMap.put(name.toUpperCase(), insertProcessor);
		}
		return sqlProcessMap.get(name.toUpperCase());
	}
}
