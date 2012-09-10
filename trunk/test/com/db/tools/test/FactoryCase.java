package com.db.tools.test;

import java.util.List;

import org.junit.Test;

import com.db.tools2.Constants;
import com.db.tools2.DBToolsBuilder;
import com.db.tools2.DBToolsFactory;
import com.db.tools2.ToolsHelper;
import com.db.tools2.exception.DBSQLException;
import com.db.tools2.exception.DBToolsBuilderException;
import com.db.tools2.exception.JavaFieldException;
import com.db.tools2.utils.FileUtils;
import com.db.tools2.utils.LoggerUtils;
import com.db.tools2.vo.ProcessResultVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午07:59:54
 **/

public class FactoryCase {
	
	ToolsHelper helper = new ToolsHelper(Constants.DRIVER_MYSQL_CONNECTOR, "jdbc:mysql://127.0.0.1:3306/ouyang?useUnicode=true&characterEncoding=UTF-8", 
			"root", "830311");
	
	ToolsHelper helper1 = new ToolsHelper(Constants.DRIVER_ORACLE_CLASS12, "jdbc:oracle:thin:@60.169.78.157:1521:hefei", 
			"system", "systemhefei");
	
	private String filePath = "E:/dbTools";
	
	{
		
		helper.setDbType(Constants.DB_TYPE_MYSQL);
		helper.setSqlType(Constants.SQL_TYPE_ORIGINAL);
		helper.setJavaFieldregulations(Constants.JAVA_FIELD_ORIGINAL);
		
		
		
		helper1.setDbType(Constants.DB_TYPE_ORACLE);
		helper1.setSqlType(Constants.SQL_TYPE_ORIGINAL);
		helper1.setJavaFieldregulations(Constants.JAVA_FIELD_CAMEL_HUMP);
		
		
		DBToolsBuilder defaultBuilder;
		try {
			defaultBuilder = new DBToolsBuilder(helper);
			DBToolsFactory.getInstance().setDefaultBuilder(defaultBuilder);
		} catch (JavaFieldException e) {
			e.printStackTrace();
		} catch (DBSQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * 测试单数据库里的一个表的情况
	 * @throws DBToolsBuilderException
	 * @throws DBSQLException 
	 * @throws JavaFieldException 
	 */
	@Test
	public void testSimpleDBSimpleTable() throws DBToolsBuilderException, JavaFieldException, DBSQLException{
		try{
			DBToolsFactory.getInstance().setDefaultBuilder(new DBToolsBuilder(helper));
			DBToolsFactory.getInstance().addBuildTask("contractGoods");
			List<ProcessResultVO> processResltList = DBToolsFactory.getInstance().buildTaskToProcessResultList();
			for(ProcessResultVO processResultVO:processResltList){
				printProcessResultVO(processResultVO);
			}
		}catch(Exception e){
			LoggerUtils.getLogger().error(e.getMessage(), e);
		}
	}
	
	
	
	/**
	 * 
	 * 测试单数据库里的一个表的情况
	 * @throws DBToolsBuilderException
	 * @throws DBSQLException 
	 * @throws JavaFieldException 
	 */
	@Test
	public void testSimpleDBSimpleTableToFile() throws DBToolsBuilderException, JavaFieldException, DBSQLException{
		try{
			DBToolsFactory.getInstance().setDefaultBuilder(new DBToolsBuilder(helper));
			DBToolsFactory.getInstance().addBuildTask("contractGoods");
			List<ProcessResultVO> processResltList = DBToolsFactory.getInstance().buildTaskToProcessResultList();
			for(ProcessResultVO processResultVO:processResltList){
				writeFile(processResultVO, "contractGoods");
			}
		}catch(Exception e){
			LoggerUtils.getLogger().error(e.getMessage(), e);
		}
	}
	
	public void printProcessResultVO(ProcessResultVO processResultVO){
		System.out.println("======================JAVA SRC====================");
		System.out.println(processResultVO.getJavaSrc());
		System.out.println("======================JAVA SRC END====================\r\n");
		
		System.out.println("======================INSERT语句====================");
		System.out.println(processResultVO.getInsertSQL());
		System.out.println("======================INSERT语句结束====================\r\n");
		System.out.println("======================UPDATE语句====================");
		System.out.println(processResultVO.getUpdateSQL());
		System.out.println("======================UPDATE语句结束====================\r\n");
		System.out.println("======================DELETE语句====================");
		System.out.println(processResultVO.getDeleteSQL());
		System.out.println("======================DELETE语句结束====================\r\n");
		System.out.println("======================SELECT语句====================");
		System.out.println(processResultVO.getQuerySQL());
		System.out.println("======================SELECT语句结束====================\r\n");
	}
	
	/**
	 * 
	 * 将对象写入到方法中
	 * @param processResultVO
	 * @param filePath
	 */
	public void writeFile(ProcessResultVO processResultVO, String tbName){
		String tbPath = filePath + "/" + tbName;
		FileUtils.mkDir(tbPath);
		String javaFile = tbPath + "/" + tbName + ".java";
		String sqlFile = tbPath + "/" + tbName + ".sql";
		FileUtils.createFile(javaFile);
		FileUtils.createFile(sqlFile);
		FileUtils.writeToFile(processResultVO.getJavaSrc(), javaFile);
		StringBuilder sqlContent = new StringBuilder();
		
		sqlContent.append("\r\n======================INSERT语句====================\r\n");
		sqlContent.append(processResultVO.getInsertSQL());
		sqlContent.append("\r\n======================INSERT语句结束====================\r\n");
		sqlContent.append("\r\n======================UPDATE语句====================\r\n");
		sqlContent.append(processResultVO.getUpdateSQL());
		sqlContent.append("\r\n======================UPDATE语句结束====================\r\n");
		sqlContent.append("\r\n======================DELETE语句====================\r\n");
		sqlContent.append(processResultVO.getDeleteSQL());
		sqlContent.append("\r\n======================DELETE语句结束====================\r\n");
		sqlContent.append("\r\n======================SELECT语句====================\r\n");
		sqlContent.append(processResultVO.getQuerySQL());
		sqlContent.append("\r\n======================SELECT语句结束====================\r\n");
		FileUtils.writeToFile(sqlContent.toString(), sqlFile);
		
		
		FileUtils.writeToFile(processResultVO.getJavaSrc(), javaFile);
	}
}
