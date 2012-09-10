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
 @createTime 2012-9-7 下午12:18:21
 **/

public class DBToolsFactory {
	private static DBToolsFactory instance = new DBToolsFactory();
	
	/**
	 * 任务Map
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
	 * 用默认的DBToolsBuilder添加任务
	 * @param tableName
	 * @throws DBToolsBuilderException 如果没有设置默认的DBToolsBuilder
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
	 * 根据helper和表名添加任务
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
	 * 添加任务
	 * @param tableName
	 * @param builder
	 * @throws DBToolsBuilderException 如果有相同的表名
	 */
	public void addBuildTask(String tableName, DBToolsBuilder builder) throws DBToolsBuilderException{
		if(buildTaskMap.get(tableName) != null){
			throw new DBToolsBuilderException(tableName + " already exists");
		}else{
			synchronized (buildTaskMap) {
				this.buildTaskMap.put(tableName, builder);
			}
			if(defaultBuilder == null){
				defaultBuilder = builder;
			}
		}
	}
	
	/**
	 * 
	 * 开始处理所有的任务
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
