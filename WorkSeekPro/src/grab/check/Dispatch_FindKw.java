package grab.check;

import java.sql.Connection;
import java.util.Map;

import com.wnc.threads.BasicTaskDispatcher;

import db.DBconnectionMgr;
import db.DbExecMgr;

public class Dispatch_FindKw extends BasicTaskDispatcher
{

    @Override
    public void buildTaskList()
    {
        // TODO Auto-generated method stub
        Map map = DbExecMgr
                .getSelectAllSqlMap("SELECT ID,WORKSKILLS FROM WORK_INFO");
        // System.out.println(map.size());
        Connection conn = DBconnectionMgr.getConnection();
        for (int i = 1; i <= map.size(); i++)
        {
            this.taskList.add((Map) map.get(i));
        }
        System.out.println("this.taskList.size():" + this.taskList.size());
    }

    public static void main(String[] args)
    {
        BasicTaskDispatcher dispatcher = new Dispatch_FindKw();
        dispatcher.buildTaskList();
        dispatcher.buildThreadList(4, new FindKeyWords());
        dispatcher.paiTask();
        dispatcher.doTask();
        dispatcher.waitOperating();
    }
}
