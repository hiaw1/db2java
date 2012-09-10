package com.db.tools2.fieldprocess;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.utils.LoggerUtils;

 /**
 @Author H.W
 @createTime 2012-9-7 ����09:31:16
 **/

public class ColumnType2JAVATypeUtils {

	/**
	 * DB�е�������JAVA���͵Ķ�Ӧ��ϵ
	 */
	private static Map<String, String> correspondingMap = new HashMap<String, String>();
	
	
	/**
	 * 
	 * ����DB����ֶ����͵õ�JAVA����
	 * @param columnType
	 * @return
	 */
	public static String getJAVAType(String columnType){
		String result = " String ";
		if(columnType.startsWith("INT") || columnType.startsWith("TINYINT")
		   || columnType.startsWith("SMALLINT") || columnType.startsWith("MEDIUMINT")
		   || columnType.startsWith("NUMBER")
		   ){
			result = " Integer ";
		}else if(columnType.startsWith("BIGINT")){
			result = " Long ";
		}else if(columnType.startsWith("FLOAT")){
			result = " Float ";
		}else if(columnType.startsWith("DOUBLE") || columnType.startsWith("DECIMAL")){
			result = " Double ";
		}else{
			if(correspondingMap.get(columnType) != null){
				result = correspondingMap.get(columnType);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * ע���µ�����
	 * @param dbFieldType
	 * @param javaFieldType
	 * @return
	 */
	public static String registerCorresponding(String dbFieldType, String javaFieldType){
		if(correspondingMap.get(dbFieldType) != null){
			LoggerUtils.getLogger().error(dbFieldType + " corresponding is already exists");
		}else{
			synchronized (correspondingMap) {
				correspondingMap.put(dbFieldType, " " + javaFieldType + " ");
			}
		}
		return correspondingMap.get(dbFieldType);
	}
}
