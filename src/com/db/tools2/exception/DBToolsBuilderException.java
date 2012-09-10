package com.db.tools2.exception;

 /**
 @Author H.W
 @createTime 2012-9-7 обнГ12:31:49
 **/

public class DBToolsBuilderException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1352243931804049180L;
	
	public DBToolsBuilderException(){
		super("Default DBToolsBuilder not found");
	}
	
	public DBToolsBuilderException(String message){
		super(message);
	}

}
