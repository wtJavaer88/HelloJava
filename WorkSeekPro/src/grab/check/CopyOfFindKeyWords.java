package grab.check;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.wnc.string.PatternUtil;

import db.DBconnectionMgr;
import db.DbExecMgr;

public class CopyOfFindKeyWords
{

    public static void main(String[] args)
    {
        Map map = DbExecMgr
                .getSelectAllSqlMap("SELECT ID,WORKSKILLS FROM WORK_INFO");
        // System.out.println(map.size());
        Connection conn = DBconnectionMgr.getConnection();
        for (int i = 1; i <= map.size(); i++)
        {
            List<String> list = PatternUtil.getPatternStrings(
                    ((Map) map.get(i)).get("WORKSKILLS").toString(),
                    "[a-zA-Z]+[ -\\.  ]?[a-zA-Z1-9]+");
            // System.out.println(list);
            String id = ((Map) map.get(i)).get("ID").toString();
            if (list.size() > 0)
            {
                for (String key : list)
                {
                    DbExecMgr.execOnlyOneUpdate(conn,
                            "INSERT INTO KEYWORDS(WORKID, WORD) VALUES(" + id
                                    + ",'" + key + "')");
                }
            }

        }
    }
}
