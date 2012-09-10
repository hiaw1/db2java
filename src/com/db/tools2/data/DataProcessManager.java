package com.db.tools2.data;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.Constants;
import com.db.tools2.ToolsHelper;
import com.db.tools2.exception.DBSQLException;
import com.db.tools2.exception.JavaFieldException;

 /**
 @Author H.W
 @createTime 2012-9-7 下午08:51:21
 **/

public class DataProcessManager {

	private static DataProcessManager instance = new DataProcessManager();
	
	/**
	 * 数据库类型对应的数据处理
	 * <DB_TYPE, BaseDataProcess>
	 */
	private Map<String, BaseDataProcess> dataProcessMap = new HashMap<String, BaseDataProcess>();
	
	private DataProcessManager(){
		dataProcessMap.put(Constants.DB_TYPE_ORACLE, OracleDataProcess.getInstance());
		dataProcessMap.put(Constants.DB_TYPE_MYSQL, MysqlDataProcess.getInstance());
	}
	
	public static DataProcessManager getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * 得到对应的data生成器<br/>
	 * @param dbType DB_TYPE 可参见Constants里的DB_TYPE开头
	 * @return
	 * @throws DBSQLException 
	 * @throws JavaFieldException
	 */
	public BaseDataProcess getDataProcess(ToolsHelper helper) throws DBSQLException{
		BaseDataProcess processor = dataProcessMap.get(helper.getDbType());
		if(processor == null){
			throw new DBSQLException("Con't find " + helper.getDbType() + " DataProcess");
		}
		processor.setHelper(helper);
		return processor;
	}
	
	/**
	 * 
	 * 注册一个新的data生成器，以后有新的处理方式，直接调用该方法，即可<br/>
	 * @param name
	 * @param BaseDataProcess
	 * @return
	 */
	public BaseDataProcess registerInsertProcessor(String name, BaseDataProcess dataProcess){
		synchronized (dataProcessMap) {
			dataProcessMap.put(name, dataProcess);
		}
		return dataProcessMap.get(name);
	} 
}
