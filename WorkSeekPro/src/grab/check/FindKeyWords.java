package grab.check;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.wnc.string.PatternUtil;
import com.wnc.threads.CustomTaskDealClass;

import db.DBconnectionMgr;
import db.DbExecMgr;

public class FindKeyWords extends CustomTaskDealClass
{
    Connection conn = DBconnectionMgr.getConnection();

    public boolean deal(Object obj)
    {
        if (obj instanceof Map)
        {
            Map map = (Map) obj;

            List<String> list = PatternUtil.getPatternStrings(
                    map.get("WORKSKILLS").toString(),
                    "[a-zA-Z\\.]+[0-9]*[[-\\s\\.]?[a-zA-Z0-9]*]{1,}");
            // System.out.println(list);
            String id = map.get("ID").toString();
            if (list.size() > 0)
            {
                for (String key : list)
                {
                    DbExecMgr.execOnlyOneUpdate(conn,
                            "INSERT INTO KEYWORDS(WORKID, WORD) VALUES(" + id
                                    + ",'" + key.toUpperCase() + "')");
                }
            }

        }
        return true;
    }

    @Override
    public Object clone()
    {
        // TODO Auto-generated method stub
        FindKeyWords findKeyWords = new FindKeyWords();
        return findKeyWords;
    }

    @Override
    public String getClassName()
    {
        // TODO Auto-generated method stub
        return FindKeyWords.class.getName();
    }
}
