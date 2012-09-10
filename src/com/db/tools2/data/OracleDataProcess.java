package com.db.tools2.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.tools2.utils.LoggerUtils;
import com.db.tools2.vo.DBDescVO;

 /**
 @Author H.W
 @createTime 2012-9-7 ����08:18:11
 **/

public class OracleDataProcess extends BaseDataProcess{
	
	private static OracleDataProcess instance = new OracleDataProcess();
	
	private OracleDataProcess(){
		/**
		 * ORACLE��ѯ���ݿ�ṹ�����
		 */
		structSQL = "select A.column_name ,A.data_type ,A.data_length ����,A.data_precision ����λ,"
			+ " A.Data_Scale С��λ,A.nullable �����ֵ,A.Data_default ȱʡֵ,B.comments,"
			+ " C.IndexCount ��������"
			+ " from "
			+ " user_tab_columns A,"
			+ " user_col_comments B,"
			+ " (select count(*) IndexCount,Column_Name from User_Ind_Columns where Table_Name = 'TABLENAME' group by Column_Name) C"
			+ " where"
			+ " A.Table_Name = B.Table_Name"
			+ " and A.Column_Name = B.Column_Name"
			+ " and A.Column_Name = C.Column_Name(+)"
			+ " and A.Table_Name = 'TABLENAME'";
	}
	
	public static OracleDataProcess getInstance(){
		return instance;
	}

	@Override
	protected DBDescVO getDBDesc(ResultSet rs) {
		String columnName;
		try {
			columnName = rs.getString("column_name");
			String dataType = rs.getString("data_type");
			String comments = rs.getString(("comments"));
			return new DBDescVO(columnName, dataType, comments);
		} catch (SQLException e) {
			LoggerUtils.getLogger().error(e.getMessage(), e);
			return null;
		}
		
	}

}
