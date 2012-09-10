package com.db.tools2.exception;

 /**
 @Author H.W
 @createTime 2012-9-7 下午12:47:30
 **/

public class DBSQLException extends Exception{

	public DBSQLException(){
		super();
	}
	
	public DBSQLException(String message){
		super(message);
	}
}
