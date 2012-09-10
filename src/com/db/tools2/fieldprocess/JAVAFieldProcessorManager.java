package com.db.tools2.fieldprocess;

import java.util.HashMap;
import java.util.Map;

import com.db.tools2.Constants;
import com.db.tools2.exception.JavaFieldException;

 /**
 @Author H.W
 @createTime 2012-9-7 上午10:39:35
 * 为方便以后的扩展，这里使用了一个fieldProcessorMap用来存储关系,<br/>
 * 后期通过{@link #registerFieldProcessor()}方法就可以完成自定义的JAVA字段处理
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
	 * 得到JAVA FIELD的处理类，如果没有找到，返回“原生FIELD”处理<br/>
	 * @param javaFieldType 可参见Constants里的JAVA_FIELD_开头的常量<br/>
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
	 * 注册一个新的JAVA字段处理，以后有新的处理方式，直接调用该方法，即可<br/>
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
