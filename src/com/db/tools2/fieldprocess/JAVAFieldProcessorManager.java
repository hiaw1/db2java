package com.db.tools2.fieldprocess;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.Constants;
import com.db.tools2.exception.JavaFieldException;

 /**
 @Author H.W
 @createTime 2012-9-7 ����10:39:35
 * Ϊ�����Ժ����չ������ʹ����һ��fieldProcessorMap�����洢��ϵ,<br/>
 * ����ͨ��{@link #registerFieldProcessor()}�����Ϳ�������Զ����JAVA�ֶδ���
 * 
 **/

public class JAVAFieldProcessorManager {
	
	private Map<String, BaseFieldProcessor> fieldProcessorMap = new HashMap<String, BaseFieldProcessor>();

	private static JAVAFieldProcessorManager instance = new JAVAFieldProcessorManager(); 
	
	private JAVAFieldProcessorManager(){
		fieldProcessorMap.put(Constants.JAVA_FIELD_ORIGINAL, OriginalFieldProcessor.getInstance());
		fieldProcessorMap.put(Constants.JAVA_FIELD_CAMEL_HUMP, CamelHumpProcessor.getInstance());
	}
	
	public static JAVAFieldProcessorManager getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * �õ�JAVA FIELD�Ĵ����࣬���û���ҵ������ء�ԭ��FIELD������<br/>
	 * @param javaFieldType �ɲμ�Constants���JAVA_FIELD_��ͷ�ĳ���<br/>
	 * @return
	 * @throws JavaFieldException 
	 */
	public BaseFieldProcessor getFieldProcessor(String javaFieldType) throws JavaFieldException{
		BaseFieldProcessor processor = fieldProcessorMap.get(javaFieldType.toUpperCase());
		if(processor == null){
			processor = fieldProcessorMap.get(Constants.JAVA_FIELD_ORIGINAL);
			throw new JavaFieldException(javaFieldType);
		}
		return processor;
	}
	
	/**
	 * 
	 * ע��һ���µ�JAVA�ֶδ����Ժ����µĴ���ʽ��ֱ�ӵ��ø÷���������<br/>
	 * @param name
	 * @param fieldProcessor
	 * @return
	 */
	public BaseFieldProcessor registerFieldProcessor(String name, BaseFieldProcessor fieldProcessor){
		synchronized (fieldProcessorMap) {
			fieldProcessorMap.put(name.toUpperCase(), fieldProcessor);
		}
		return fieldProcessorMap.get(name.toUpperCase());
	}
}
