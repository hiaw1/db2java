package com.db.tools2.fieldprocess;


 /**
 @Author H.W
 @createTime 2012-9-7 ����10:37:36
 �����»����Զ��շ��ʶ
 **/

public class CamelHumpProcessor extends BaseFieldProcessor{

	private static CamelHumpProcessor instance = new CamelHumpProcessor();
	
	private CamelHumpProcessor(){
	}
	
	public static CamelHumpProcessor getInstance(){
		return instance;
	}

	@Override
	public String getJAVAField(String columnName) {
		StringBuilder javaFieldName = new StringBuilder();
		String[] columnArr = columnName.split("[_]");
		javaFieldName.append(columnArr[0].toLowerCase());
		for(int i = 1;i < columnArr.length;i ++){			//����JAVA FIELD����
			String temp = columnArr[i].substring(0, 1).toUpperCase() + columnArr[i].substring(1, columnArr[i].length()).toLowerCase();
			javaFieldName.append(temp);
		}
		return javaFieldName.toString();
	}
}
