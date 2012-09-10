package com.db.tools2.sql;

import java.util.List;

import com.db.tools2.fieldprocess.BaseFieldProcessor;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 下午01:15:17
 **/

public interface ISQLProcess {

	/**
	 * 
	 * 生成对应的SQL
	 * @param tbName
	 * @param dbDescList
	 * @param fieldProcessor
	 * @return
	 */
	public String generationSQL(String tbName, List<DBDescVO> dbDescList, BaseFieldProcessor fieldProcessor);
}
