package db.helper;

import java.sql.Connection;
import java.util.Map;

import com.wnc.basic.BasicStringUtil;

import db.DBconnectionMgr;
import db.DbExecMgr;
import etl.PinYinUtil;

public class PinYinHelper
{

	public String tableName = "";
	public String chnNameField = "";
	Connection con = DBconnectionMgr.getConnection();

	public PinYinHelper(String table, String field)
	{
		this.tableName = table;
		this.chnNameField = field;
	}

	public void updateTablePy()
	{
		Map map = DbExecMgr.getSelectSqlMap("SELECT " + chnNameField + " FROM "
				+ tableName + " WHERE PINYIN_ALL IS NULL OR PINYIN_JX IS NULL");
		if (map != null && !map.isEmpty())
		{
			for (int i = 1; i <= map.size(); i++)
			{
				updatePinyin((String) map.get(i));

			}

		}
	}

	private void updatePinyin(String name)
	{
		if (BasicStringUtil.isNullString(name))
		{
			return;
		}
		PinYinUtil pyUtil = new PinYinUtil();
		String allpy = "";
		String jxpy = "";
		allpy = pyUtil.convert2PinYin(name);
		jxpy = "";

		for (int i = 0; i < name.length(); i++)
		{
			jxpy += pyUtil.convert(String.valueOf(name.charAt(i))).charAt(0) + "";
		}
		String updateStr = "UPDATE " + tableName + " SET PINYIN_ALL='" + allpy
				+ "', PINYIN_JX='" + jxpy + "' WHERE " + chnNameField + "='" + name
				+ "'";
		System.out.println(updateStr);

		if (DbExecMgr.execUpdate(con, updateStr) == 0)
		{
			SysLogger.log(con, "更新拼音失败!:" + chnNameField + "=" + name
					+ " &tableName=" + tableName);
		}

	}
}
