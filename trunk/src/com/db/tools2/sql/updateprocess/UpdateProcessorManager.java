package com.db.tools2.sql.updateprocess;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.Constants;
import com.db.tools2.exception.DBSQLException;
import com.db.tools2.fieldprocess.JAVAFieldProcessorManager;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.sql.updateprocess.mysql.IBatisMysqlUpdateProcess;
import com.db.tools2.sql.updateprocess.mysql.MyBatisMysqlUpdateProcess;
import com.db.tools2.sql.updateprocess.mysql.OriginaMysqlUpdateProcess;
import com.db.tools2.sql.updateprocess.oracle.IBatisOracleUpdateProcess;
import com.db.tools2.sql.updateprocess.oracle.MyBatisOracleUpdateProcess;
import com.db.tools2.sql.updateprocess.oracle.OriginalOracleUpdateProcess;
import com.db.tools2.sql.updateprocess.sqlserver.IBatisSqlserverUpdateProcess;
import com.db.tools2.sql.updateprocess.sqlserver.MyBatisSqlserverUpdateProcess;
import com.db.tools2.sql.updateprocess.sqlserver.OriginalSqlserverUpdateProcess;

 /**
 @Author H.W
 @createTime 2012-9-7 上午11:16:47
 *　update PROCESSOR 管理者<br/>
 *  管理方式同{#link {@link JAVAFieldProcessorManager}<br/>
 **/

public class UpdateProcessorManager {
	private Map<String, ISQLProcess> sqlProcessMap = new HashMap<String, ISQLProcess>();

	private static UpdateProcessorManager instance = new UpdateProcessorManager(); 
	
	private UpdateProcessorManager(){
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_IBATIS, new IBatisOracleUpdateProcess());
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisOracleUpdateProcess());
		sqlProcessMap.put(Constants.DB_TYPE_ORACLE + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginalOracleUpdateProcess());
		
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_IBATIS, new IBatisMysqlUpdateProcess());
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisMysqlUpdateProcess());
		sqlProcessMap.put(Constants.DB_TYPE_MYSQL + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginaMysqlUpdateProcess());

		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_IBATIS, new IBatisSqlserverUpdateProcess());
		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_MYBATIS, new MyBatisSqlserverUpdateProcess());
		sqlProcessMap.put(Constants.DB_TPYE_SQLSERVER + ":" + Constants.SQL_TYPE_ORIGINAL, new OriginalSqlserverUpdateProcess());
	}
	
	public static UpdateProcessorManager getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * 得到对应的update生成器<br/>
	 * @param dbType DB_TYPE+SQL_TYPE  可参见Constants里的DB_TYPE开头和SQL_TYPE_开头的变量
	 * @return
	 * @throws DBSQLException 
	 */
	public ISQLProcess getUpdateProcessor(String type) throws DBSQLException{
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
	 * @param updateProcess
	 * @return
	 */
	public ISQLProcess registerUpdateProcessor(String name, ISQLProcess updateProcess){
		synchronized (sqlProcessMap) {
			sqlProcessMap.put(name.toUpperCase(), updateProcess);
		}
		return sqlProcessMap.get(name.toUpperCase());
	}
}
