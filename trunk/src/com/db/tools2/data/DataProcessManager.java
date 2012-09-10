package com.db.tools2.data;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.Constants;
import com.db.tools2.ToolsHelper;
import com.db.tools2.exception.DBSQLException;
import com.db.tools2.exception.JavaFieldException;

 /**
 @Author H.W
 @createTime 2012-9-7 ����08:51:21
 **/

public class DataProcessManager {

	private static DataProcessManager instance = new DataProcessManager();
	
	/**
	 * ���ݿ����Ͷ�Ӧ�����ݴ���
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
	 * �õ���Ӧ��data������<br/>
	 * @param dbType DB_TYPE �ɲμ�Constants���DB_TYPE��ͷ
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
	 * ע��һ���µ�data���������Ժ����µĴ���ʽ��ֱ�ӵ��ø÷���������<br/>
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
