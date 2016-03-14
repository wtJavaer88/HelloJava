package grab.task;

import java.sql.Connection;

import org.jsoup.nodes.Document;

import tools.FileLogger;

import com.wnc.basic.BasicNumberUtil;
import com.wnc.basic.BasicStringUtil;
import com.wnc.string.PatternUtil;
import com.wnc.threads.CustomTaskDealClass;
import com.wnc.tools.SoupUtil;

import db.DBconnectionMgr;
import db.DbExecMgr;
import db.DbField;
import db.DbFieldSqlUtil;
import edu.emory.mathcs.backport.java.util.Arrays;
import entity.ITWorkInfo;

public abstract class WorkSeekTask extends CustomTaskDealClass
{
    Connection con = DBconnectionMgr.getConnection();
    protected ITWorkInfo workInfo = null;
    protected Document doc = null;
    protected String workurl = "";
    protected String worktable = "WORK_INFO";
    protected String webSite = "";// 哪个网站, 子类去实现

    public WorkSeekTask(String webSite)
    {
        this.webSite = webSite;

    }

    public WorkSeekTask()
    {
    }

    /**
     * 根据一个url获取一个工作信息
     */
    @Override
    public boolean deal(Object obj)
    {
        this.workInfo = null;
        this.workInfo = new ITWorkInfo();// 由于是多线程,每次处理新请求需要在这儿重新new一下
        this.workurl = (String) obj;
        parse(workurl);
        if (this.getStatus() == 0)
        {
            return true;
        }

        return false;
    }

    /**
     * 留给子类去实现
     */
    @Override
    public Object clone()
    {
        return null;
    }

    @Override
    public String getClassName()
    {
        return WorkSeekTask.class.getName();
    }

    /**
     * 解析页面上的工作信息,通用模板方法
     * 
     * @param url
     */
    public void parse(String url)
    {
        if (BasicStringUtil.isNullString(url))
        {
            actionStatus(2016021901);
            actionMessage("parse参数不能为空!");
        }
        else
        {
            doc = SoupUtil.getDoc(url);
            if (doc == null)
            {
                actionStatus(2016021902);
                actionMessage("SoupUtil解析异常! url:" + url);
                return;
            }

            try
            {
                getWorkSalary();
                getWorkDesc();
                getCompDesc();
                // 设置为null节省内存
                doc = null;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                actionStatus(2016021903);
                actionMessage("parse异常!" + e.getMessage());
                FileLogger.noramalErr(this.workurl + " Exception:"
                        + e.getMessage()

                        + Arrays.toString(e.getStackTrace()));
            }
            finally
            {
                dataSave();
            }
        }
    }

    /**
     * 为了放在一个统一的模板里, 留给子类去实现
     */
    public abstract void getWorkSalary();

    /**
     * 获取开出的工资,该方法很明确
     * 
     * @return
     */
    public void getWorkSalary(String hrefStr)
    {
        int[] salaryRange =
        { 0, 0 };
        String firstString = PatternUtil.getFirstPattern(hrefStr, "\\d+");
        String lastString = PatternUtil.getLastPattern(hrefStr, "\\d+");

        salaryRange[0] = BasicNumberUtil.getNumber(firstString);
        salaryRange[1] = BasicNumberUtil.getNumber(lastString);
        workInfo.setSalary(salaryRange);
        System.out.println("工资: " + firstString + "  A:B " + lastString);
    }

    /**
     * 获取岗位描述
     * 
     * @return
     */
    public abstract void getWorkDesc();

    /**
     * 获取公司描述,该情形比较简单
     * 
     * @return
     */
    public abstract void getCompDesc();

    /**
     * 保存到数据库
     * 
     * @return
     */
    public boolean dataSave()
    {
        DbFieldSqlUtil sqlUtil = new DbFieldSqlUtil(this.worktable, "");
        sqlUtil.addInsertField(new DbField("Salary_Min",
                workInfo.getSalary()[0] + ""));
        sqlUtil.addInsertField(new DbField("Salary_Max",
                workInfo.getSalary()[1] + ""));
        sqlUtil.addInsertField(new DbField("CompName", getCorrectSql(workInfo
                .getCompName())));
        sqlUtil.addInsertField(new DbField("COMPLACE", getCorrectSql(
                workInfo.getComPlace(), "公司地址：")));
        sqlUtil.addInsertField(new DbField("ComSelfUrl", getCorrectSql(
                workInfo.getComSelfUrl(), "公司主页：")));
        sqlUtil.addInsertField(new DbField("CompName", getCorrectSql(workInfo
                .getCompName())));

        sqlUtil.addInsertField(new DbField("ComZhaopinUrl",
                getCorrectSql(workInfo.getComZhaopinUrl())));
        sqlUtil.addInsertField(new DbField("ComProperty", getCorrectSql(
                workInfo.getComProperty(), "公司性质：")));
        sqlUtil.addInsertField(new DbField("ComWorkers", getCorrectSql(""
                + workInfo.getComWorkers())));
        // sqlUtil.addInsertField(new DbField("CompDesc", getCorrectSql(workInfo
        // .getCompDesc().substring(
        // 0,
        // workInfo.getCompDesc().length() > 1000 ? 1000
        // : workInfo.getCompDesc().length()))));
        sqlUtil.addInsertField(new DbField("CompDesc", getCorrectSql(
                workInfo.getCompDesc(), 1000)));
        sqlUtil.addInsertField(new DbField("WorkDuties", getCorrectSql(
                Arrays.toString(workInfo.getWorkDuties().toArray()), 1000)));
        sqlUtil.addInsertField(new DbField("WorkSkills", getCorrectSql(
                Arrays.toString(workInfo.getWorkSkills().toArray()), 1000)));
        sqlUtil.addInsertField(new DbField("Welfare", getCorrectSql(workInfo
                .getWelfare())));
        sqlUtil.addInsertField(new DbField("WORKURL", getCorrectSql(workInfo
                .getWorkUrl())));
        sqlUtil.addInsertField(new DbField("WORKPLACE", getCorrectSql(
                workInfo.getWorkPlace(), "工作地址：")));
        sqlUtil.addInsertField(new DbField("WORKAGE", getCorrectSql(
                workInfo.getWorkAge(), "工作经验：")));
        sqlUtil.addInsertField(new DbField("WORKCITY", getCorrectSql(
                workInfo.getWorkCity(), "工作地点：")));
        sqlUtil.addInsertField(new DbField("WORKPOST", getCorrectSql(
                workInfo.getWorkPost(), "职位类别：")));
        sqlUtil.addInsertField(new DbField("PublishDate", getCorrectSql(
                workInfo.getPublishDate(), "发布日期：")));

        System.out.println(workInfo.getComPlace() + " "
                + sqlUtil.getInsertSql());
        return DbExecMgr.execOnlyOneUpdate(con, sqlUtil.getInsertSql());
    }

    /**
     * 替换掉一些多余或不符合sql规范的字符
     * 
     * @param value
     * @param repChars
     * @return
     */
    public String getCorrectSql(String value, String... repChars)
    {
        String retStr = value.replace("\n", "").replace("'", "''").trim();
        if (repChars.length > 0)
        {
            for (String string : repChars)
                retStr = retStr.replace(string, "");
        }
        return retStr;
    }

    /**
     * 最多获取多少个字符长度
     * 
     * @param string
     * @param i
     * @return
     */
    private String getCorrectSql(String string, int i)
    {
        string = getCorrectSql(string);
        return string.substring(0, string.length() > i ? i : string.length());
    }
}
