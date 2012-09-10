package com.db.tools2.sql.deleteprocess;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.Constants;
import com.db.tools2.exception.DBSQLException;
import com.db.tools2.fieldprocess.JAVAFieldProcessorManager;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.sql.deleteprocess.mysql.IBatisMysqlDeleteProcess;
import com.db.tools2.sql.deleteprocess.mysql.MyBatisMysqlDeleteProcess;
import com.db.tools2.sql.deleteprocess.mysql.OriginaMysqlDeleteProcess;
import com.db.tools2.sql.deleteprocess.oracle.IBatisOracleDeleteProcess;
import com.db.tools2.sql.deleteprocess.oracle.MyBatisOracleDeleteProcess;
import com.db.tools2.sql.deleteprocess.oracle.OriginalOracleDeleteProcess;
import com.db.tools2.sql.deleteprocess.sqlserver.IBatisSqlserverDeleteProcess;
import com.db.tools2.sql.deleteprocess.sqlserver.MyBatisSqlserverDeleteProcess;
import com.db.tools2.sql.deleteprocess.sqlserver.OriginalSqlserverDeleteProcess;

 /**
 @Author H.W
 @createTime 2012-9-7 上午11:16:47
 *　DELETE PROCESSOR 管理者<br/>
 *  管理方式同{#link {@link JAVAFieldProcessorManager}<br/>
 **/

public class DeleteProcessorManager {
	private Map<String, ISQLProcess> sqlProcessMap = new HashMap<String, ISQLProcess>();

	private static DeleteProcessorManager instance = new DeleteProcessorManager(); 
	
	private DeleteProcessorManager(){
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_IBATIS, new IBatisOracleDeleteProcess());
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisOracleDeleteProcess());
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginalOracleDeleteProcess());
		
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_IBATIS, new IBatisMysqlDeleteProcess());
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisMysqlDeleteProcess());
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginaMysqlDeleteProcess());

		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_IBATIS, new IBatisSqlserverDeleteProcess());
		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisSqlserverDeleteProcess());
		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginalSqlserverDeleteProcess());
	}
	
	public static DeleteProcessorManager getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * 得到对应的delete生成器<br/>
	 * @param dbType DB_TYPE+SQL_TYPE  可参见Constants里的DB_TYPE开头和SQL_TYPE_开头的变量
	 * @return
	 * @throws DBSQLException 
	 */
	public ISQLProcess getDeleteProcessor(String type) throws DBSQLException{
		ISQLProcess processor = sqlProcessMap.get(type.toUpperCase());
		if(processor == null){
			throw new DBSQLException("Con't find " + type + " DeleteProcessor");
		}
		return processor;
	}
	
	/**
	 * 
	 * 注册一个新的delete生成器，以后有新的处理方式，直接调用该方法，即可<br/>
	 * @param name
	 * @param deleteProcessor
	 * @return
	 */
	public ISQLProcess registerDeleteProcessor(String name, ISQLProcess deleteProcessor){
		synchronized (sqlProcessMap) {
			sqlProcessMap.put(name.toUpperCase(), deleteProcessor);
		}
		return sqlProcessMap.get(name.toUpperCase());
	}
}
