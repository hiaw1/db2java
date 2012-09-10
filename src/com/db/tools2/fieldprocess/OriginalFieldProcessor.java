package com.db.tools2.fieldprocess;


 /**
 @Author H.W
 @createTime 2012-9-7 上午10:35:39
 使用原字段
 **/

public class OriginalFieldProcessor extends BaseFieldProcessor{

	private static OriginalFieldProcessor instance = new OriginalFieldProcessor();
	
	private OriginalFieldProcessor(){
	}
	
	public static OriginalFieldProcessor getInstance(){
		return instance;
	}
	
	@Override
	public String getJAVAField(String columnName) {
		return columnName;
	}

}
