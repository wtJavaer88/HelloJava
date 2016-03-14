package db.helper;

import java.sql.Connection;

import db.DbExecMgr;

public class SysLogger
{
	public static void log(Connection con, String content)
	{
		DbExecMgr.execOnlyOneUpdate(con,
				"INSERT INTO SYSLOG(ID,CONTENT) VALUES(S_LOG.NEXTVAL,'" + content
						+ "')");
	}

	public static void logErr(Connection con, String content)
	{
		DbExecMgr.execOnlyOneUpdate(con,
				"INSERT INTO SYSLOG(ID,CONTENT,log_level) VALUES(S_LOG.NEXTVAL,'"
						+ content + "','ERROR')");
	}
}
