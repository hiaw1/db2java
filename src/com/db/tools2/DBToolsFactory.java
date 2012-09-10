package com.db.tools2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.db.tools2.exception.DBSQLException;
import com.db.tools2.exception.DBToolsBuilderException;
import com.db.tools2.exception.JavaFieldException;
import com.db.tools2.vo.ProcessResultVO;

 /**
 @Author H.W
 @createTime 2012-9-7 ����12:18:21
 **/

public class DBToolsFactory {
	private static DBToolsFactory instance = new DBToolsFactory();
	
	/**
	 * ����Map
	 * <tableName, DBToolsBuilder>
	 */
	private Map<String, DBToolsBuilder> buildTaskMap = new HashMap<String, DBToolsBuilder>(); 
	
	private DBToolsBuilder defaultBuilder;
	
	
	private DBToolsFactory(){
	}
	
	public static DBToolsFactory getInstance(){
		return instance;
	}

	public DBToolsBuilder getDefaultBuilder() {
		return defaultBuilder;
	}

	public void setDefaultBuilder(DBToolsBuilder defaultBuilder) {
		this.defaultBuilder = defaultBuilder;
	}
	
	/**
	 * 
	 * ��Ĭ�ϵ�DBToolsBuilder�������
	 * @param tableName
	 * @throws DBToolsBuilderException ���û������Ĭ�ϵ�DBToolsBuilder
	 */
	public void addBuildTask(String tableName) throws DBToolsBuilderException{
		if(defaultBuilder != null){
			addBuildTask(tableName, defaultBuilder);
		}else{
			throw new DBToolsBuilderException();
		}
	}
	
	/**
	 * 
	 * ����helper�ͱ����������
	 * @param tableName
	 * @param helper
	 * @throws DBToolsBuilderException
	 * @throws DBSQLException 
	 * @throws JavaFieldException 
	 */
	public void addBuildTask(String tableName, ToolsHelper helper)throws DBToolsBuilderException, JavaFieldException, DBSQLException{
		addBuildTask(tableName, new DBToolsBuilder(helper));
	}
	
	/**
	 * 
	 * �������
	 * @param tableName
	 * @param builder
	 * @throws DBToolsBuilderException �������ͬ�ı���
	 */
	public void addBuildTask(String tableName, DBToolsBuilder builder) throws DBToolsBuilderException{
		if(buildTaskMap.get(tableName) != null){
			throw new DBToolsBuilderException(tableName + " already exists");
		}else{
			synchronized (buildTaskMap) {
				this.buildTaskMap.put(tableName, defaultBuilder);
			}
		}
	}
	
	/**
	 * 
	 * ��ʼ�������е�����
	 */
	public List<ProcessResultVO> buildTaskToProcessResultList(){
		List<ProcessResultVO> processResultList = new ArrayList<ProcessResultVO>();
		Iterator<Map.Entry<String, DBToolsBuilder>> taskEntryIter = buildTaskMap.entrySet().iterator();
		while(taskEntryIter.hasNext()){
			Map.Entry<String, DBToolsBuilder> entry = taskEntryIter.next();
			processResultList.add(entry.getValue().building(entry.getKey()));
		}
		return processResultList;
		
	}

	
}
