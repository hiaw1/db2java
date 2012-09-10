package com.db.tools2;

import java.util.List;

import com.db.tools2.data.BaseDataProcess;
import com.db.tools2.data.DataProcessManager;
import com.db.tools2.exception.DBSQLException;
import com.db.tools2.exception.JavaFieldException;
import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.fieldprocess.JAVAFieldProcessorManager;
import com.db.tools2.sql.ISQLProcess;
import com.db.tools2.sql.deleteprocess.DeleteProcessorManager;
import com.db.tools2.sql.insertprocess.InsertProcessorManager;
import com.db.tools2.sql.selectprocess.SelectProcessorManager;
import com.db.tools2.sql.updateprocess.UpdateProcessorManager;
import com.db.tools2.vo.DBDescVO;
import com.db.tools2.vo.ProcessResultVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午12:26:44
 **/

public class DBToolsBuilder {

	private BaseFieldProcessor fieldProcessor;
	
	private ISQLProcess insertProcessor;
	
	private ISQLProcess updateProcessor;
	
	private ISQLProcess selectProcessor;
	
	private ISQLProcess deleteProcessor;
	
	private BaseDataProcess baseDataProcess;
	
	/**
	 * 根据Helper生成Builder
	 * @param helper
	 * @throws JavaFieldException
	 * @throws DBSQLException
	 */
	public DBToolsBuilder(ToolsHelper helper) throws JavaFieldException, DBSQLException{
		fieldProcessor = JAVAFieldProcessorManager.getInstance().getFieldProcessor(helper.getJavaFieldregulations());
		baseDataProcess = DataProcessManager.getInstance().getDataProcess(helper);
		insertProcessor = InsertProcessorManager.getInstance().getInsertProcessor(helper.getDbType() + ":" + helper.getSqlType());
		updateProcessor = UpdateProcessorManager.getInstance().getUpdateProcessor(helper.getDbType() + ":" + helper.getSqlType());
		selectProcessor = SelectProcessorManager.getInstance().getSelectProcessor(helper.getDbType() + ":" + helper.getSqlType());
		deleteProcessor = DeleteProcessorManager.getInstance().getDeleteProcessor(helper.getDbType() + ":" + helper.getSqlType());
	}
	
	
	/**
	 * 
	 * 进行building，生成对应的结果集
	 * @param  tableName
	 * @return ProcessResultVO
	 */
	public ProcessResultVO building(String tableName){
		ProcessResultVO pr = new ProcessResultVO();
		
		StringBuilder javaSrcCode = new StringBuilder();
		List<DBDescVO> dbDescVOList = baseDataProcess.getAllList(tableName);
		for(DBDescVO dbDescVO:dbDescVOList){
			javaSrcCode.append(fieldProcessor.getJAVASrc(dbDescVO));
		}
		
		for(DBDescVO dbDescVO:dbDescVOList){
			javaSrcCode.append(fieldProcessor.getFieldGetAndSetMethod(dbDescVO));
		}
		
		pr.setJavaSrc(javaSrcCode.toString());
		pr.setInsertSQL(insertProcessor.generationSQL(tableName, dbDescVOList, fieldProcessor));
		pr.setUpdateSQL(updateProcessor.generationSQL(tableName, dbDescVOList, fieldProcessor));
		pr.setDeleteSQL(deleteProcessor.generationSQL(tableName, dbDescVOList, fieldProcessor));
		pr.setQuerySQL(selectProcessor.generationSQL(tableName, dbDescVOList, fieldProcessor));
		return pr;
	}


	public BaseFieldProcessor getFieldProcessor() {
		return fieldProcessor;
	}


	public void setFieldProcessor(BaseFieldProcessor fieldProcessor) {
		this.fieldProcessor = fieldProcessor;
	}


	public ISQLProcess getInsertProcessor() {
		return insertProcessor;
	}


	public void setInsertProcessor(ISQLProcess insertProcessor) {
		this.insertProcessor = insertProcessor;
	}


	public ISQLProcess getUpdateProcessor() {
		return updateProcessor;
	}


	public void setUpdateProcessor(ISQLProcess updateProcessor) {
		this.updateProcessor = updateProcessor;
	}


	public ISQLProcess getSelectProcessor() {
		return selectProcessor;
	}


	public void setSelectProcessor(ISQLProcess selectProcessor) {
		this.selectProcessor = selectProcessor;
	}


	public ISQLProcess getDeleteProcessor() {
		return deleteProcessor;
	}


	public void setDeleteProcessor(ISQLProcess deleteProcessor) {
		this.deleteProcessor = deleteProcessor;
	}


	public BaseDataProcess getBaseDataProcess() {
		return baseDataProcess;
	}


	public void setBaseDataProcess(BaseDataProcess baseDataProcess) {
		this.baseDataProcess = baseDataProcess;
	}
	
	
}
