package grab.model;

import entity.ITWorkInfo;
import grab.task.WorkSeekTask;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wnc.basic.BasicFileUtil;
import com.wnc.basic.BasicStringUtil;
import com.wnc.basic.BasicXmlUtil;
import com.wnc.string.PatternUtil;

public class ZhiL_GrabWork extends WorkSeekTask
{
    public ZhiL_GrabWork(String webSite)
    {
        super(webSite);
        // TODO Auto-generated constructor stub
    }

    public ZhiL_GrabWork()
    {
        this("智联招聘");
    }

    public static void main(String[] args)
    {
        BasicXmlUtil.main(null);

        new ZhiL_GrabWork()
                .deal("http://jobs.zhaopin.com/548539827250013.htm?ssidkey=y&ss=409&ff=03");
    }

    @Override
    public void getWorkSalary()
    {
        // TODO Auto-generated method stub

        getWorkSalary(doc.select(".terminalpage-left>ul>li").first().text());
    }

    @Override
    public void getWorkDesc()
    {
        workInfo.setWorkUrl(this.workurl);
        // TODO Auto-generated method stub
        System.out.println(" workDesc: " + workInfo.getWorkAge());
        String htmlTxt = doc.select(".tab-inner-cont").first().html();
        String diffs = "罢罷备備贝貝笔筆毕畢边宾賓";
        String replaceChar = "咼";
        for (int i = 0; i < diffs.length(); i++)
        {
            replaceChar = diffs.charAt(i) + "";
            if (!htmlTxt.contains(replaceChar))
            {
                htmlTxt = htmlTxt.replaceAll("<[ ]*+/br[ ]*+>", replaceChar);
                htmlTxt = htmlTxt.replaceAll("<[ ]*+br[ ]*+/>", replaceChar);
                htmlTxt = htmlTxt.replaceAll("<[ ]*+/p[ ]*+>", replaceChar);
                htmlTxt = htmlTxt.replaceAll("<[ ]*+/div[ ]*+>", replaceChar);
                htmlTxt = htmlTxt.replace("&nbsp;", "");
                break;
            }
        }
        // System.out.println(htmlTxt);
        String[] tokens = htmlTxt.split(replaceChar);

        for (int i = 0; i < tokens.length; i++)
        {
            // 替换所有的标签
            String text = tokens[i].replaceAll("<([^>]*)>", "");
            dealWorkDescText(text.trim());
        }
        // System.out.println(workInfo.getWorkDuties());
        // System.out.println(workInfo.getWorkSkills());
        System.out.println(workInfo.getComPlace());
        if (workInfo.getWorkDuties().size() == 0
                || workInfo.getWorkSkills().size() == 0)
        {
            System.out.println(this.workurl + "<<<<<");
            BasicFileUtil.writeFileString(
                    "D:\\Users\\wnc\\MyLog\\workLog219.txt", workurl + "\r\n",
                    "UTF-8", true);
        }

    }

    boolean dutyFlag = false;
    boolean skillFlag = false;

    private void dealWorkDescText(String text)
    {
        if (judgeSkillFlag(text))
        {
            dutyFlag = false;
            skillFlag = true;
        }
        if (judgeDutyFlag(text))
        {
            dutyFlag = true;
        }
        if (text.startsWith("工作地址："))
        {
            skillFlag = false;
            workInfo.setWorkPlace(text);// 后面有公司地址
        }
        // ******
        if (dutyFlag)
        {
            if (!BasicStringUtil.isNullString(text)
                    || workInfo.getWorkDuties().size() == 0)
            {
                workInfo.getWorkDuties().add(text + "@");
            }
            else
            {
                dutyFlag = false;
            }
        }

        // ******
        if (skillFlag)
        {
            if (!BasicStringUtil.isNullString(text)
                    || workInfo.getWorkSkills().size() == 0)
            {
                workInfo.getWorkSkills().add(text + "@");
            }
            else
            {
                skillFlag = false;
            }
        }

        return;
    }

    private boolean judgeSkillFlag(String text)
    {

        return text.length() < 10
                && (text.startsWith("任职要求") || text.startsWith("岗位要求")
                        || text.contains("素质要求") || text.contains("要求"));

    }

    private boolean judgeDutyFlag(String text)
    {
        return text.length() < 10
                && (text.startsWith("工作职责") || text.startsWith("岗位职责")
                        || text.contains("素质要求") || text.contains("职责") || text
                            .contains("资格"));

    }

    @Override
    public void getCompDesc()
    {
        Elements lis1 = doc.select(".terminalpage-left>ul>li");
        workInfo.setWorkCity(lis1.get(1).text());
        workInfo.setPublishDate(PatternUtil.getFirstPattern(lis1.get(2).text(),
                "\\d{4}-\\d{2}-\\d{2}"));
        workInfo.setWorkAge(lis1.get(4).text());
        workInfo.setWorkPost(lis1.get(7).text());

        // TODO Auto-generated method stub
        workInfo.setCompDesc(doc.select(".tab-inner-cont").get(1).text());
        workInfo.setComZhaopinUrl(doc.select(".tab-inner-cont").get(1)
                .select("a").first().attr("href"));
        workInfo.setCompName(doc.select(".tab-inner-cont").get(1).select("a")
                .first().text());

        Elements lis = doc.select(".terminal-company>li");
        // 顺序和
        for (Element e : lis)
        {
            if (e.text().contains("公司规模"))
            {
                workInfo.setComWorkers(Integer.parseInt(PatternUtil
                        .getFirstPattern(e.text(), "\\d+")));
            }
            else if (e.text().contains("公司性质"))
            {
                workInfo.setComProperty(e.text());
            }

            else if (e.text().contains("公司主页"))
            {
                workInfo.setComSelfUrl(e.text());

            }
            else if (e.text().contains("公司地址"))
            {
                workInfo.setComPlace(e.text());
            }
        }

        workInfo.setWelfare(doc.select(".welfare-tab-box").first().html()
                .toUpperCase().replace("\n", "").replace("</SPAN>", "@")
                .replace("<SPAN>", ""));

    }

    @Override
    public ZhiL_GrabWork clone()
    {
        ZhiL_GrabWork work = new ZhiL_GrabWork();
        work.workInfo = new ITWorkInfo();
        return work;
    }

}
