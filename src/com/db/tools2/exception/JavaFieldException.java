package com.db.tools2.exception;


 /**
 @Author H.W
 @createTime 2012-9-7 上午11:42:15
 **/

public class JavaFieldException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676899895139536831L;

	public JavaFieldException(){
		super("JavaFildProcess not Found");
	}
	
	public JavaFieldException(String message){
		super("JavaFildProcess " + message + " not Found");
	}
}
