package com.db.tools2.sql.selectprocess;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.Constants;
import com.db.tools2.exception.DBSQLException;
import com.db.tools2.fieldprocess.JAVAFieldProcessorManager;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.sql.selectprocess.mysql.IBatisMysqlSelectProcess;
import com.db.tools2.sql.selectprocess.mysql.MyBatisMysqlSelectProcess;
import com.db.tools2.sql.selectprocess.mysql.OriginaMysqlSelectProcess;
import com.db.tools2.sql.selectprocess.oracle.IBatisOracleSelectProcess;
import com.db.tools2.sql.selectprocess.oracle.MyBatisOracleSelectProcess;
import com.db.tools2.sql.selectprocess.oracle.OriginalOracleSelectProcess;
import com.db.tools2.sql.selectprocess.sqlserver.IBatisSqlserverSelectProcess;
import com.db.tools2.sql.selectprocess.sqlserver.MyBatisSqlserverSelectProcess;
import com.db.tools2.sql.selectprocess.sqlserver.OriginalSqlserverSelectProcess;

 /**
 @Author H.W
 @createTime 2012-9-7 上午11:16:47
 *　Select PROCESSOR 管理者<br/>
 *  管理方式同{#link {@link JAVAFieldProcessorManager}<br/>
 **/

public class SelectProcessorManager {
	private Map<String, ISQLProcess> sqlProcessMap = new HashMap<String, ISQLProcess>();

	private static SelectProcessorManager instance = new SelectProcessorManager(); 
	
	private SelectProcessorManager(){
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_IBATIS, new IBatisOracleSelectProcess());
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisOracleSelectProcess());
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginalOracleSelectProcess());
		
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_IBATIS, new IBatisMysqlSelectProcess());
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisMysqlSelectProcess());
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginaMysqlSelectProcess());

		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_IBATIS, new IBatisSqlserverSelectProcess());
		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisSqlserverSelectProcess());
		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginalSqlserverSelectProcess());
	}
	
	public static SelectProcessorManager getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * 得到对应的SELECT生成器<br/>
	 * @param dbType DB_TYPE+SQL_TYPE  可参见Constants里的DB_TYPE开头和SQL_TYPE_开头的变量
	 * @return
	 * @throws DBSQLException 
	 */
	public ISQLProcess getSelectProcessor(String type) throws DBSQLException{
		ISQLProcess processor = sqlProcessMap.get(type.toUpperCase());
		if(processor == null){
			throw new DBSQLException("Con't find " + type + " SelectProcessor");
		}
		return processor;
	}
	
	/**
	 * 
	 * 注册一个新的Select生成器，以后有新的处理方式，直接调用该方法，即可<br/>
	 * @param name
	 * @param selectProcessor
	 * @return
	 */
	public ISQLProcess registerSelectProcessor(String name, ISQLProcess selectProcessor){
		synchronized (sqlProcessMap) {
			sqlProcessMap.put(name.toUpperCase(), selectProcessor);
		}
		return sqlProcessMap.get(name.toUpperCase());
	}
}
