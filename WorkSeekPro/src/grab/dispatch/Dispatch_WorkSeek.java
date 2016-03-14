package grab.dispatch;

import grab.model.ZhiL_GrabWork;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.wnc.basic.BasicNumberUtil;
import com.wnc.basic.BasicRunTimeUtil;
import com.wnc.string.PatternUtil;
import com.wnc.threads.BasicTaskDispatcher;
import com.wnc.tools.SoupUtil;

public class Dispatch_WorkSeek extends BasicTaskDispatcher
{
    int pageSize = 40;

    String jl = "武汉";// 地点
    String we = "0305";// 工作年限

    public Dispatch_WorkSeek(String jl, String we)
    {
        this.jl = jl;
        this.we = we;
    }

    @Override
    public void buildTaskList()
    {
        BasicTaskDispatcher dispatcher = new BasicTaskDispatcher()
        {
            @Override
            public void buildTaskList()
            {
                Document doc = SoupUtil
                        .getDoc("http://sou.zhaopin.com/jobs/searchresult.ashx?jl="
                                + jl + "&kw=java&p=1&we=" + we);

                Element element = doc.select(".search_yx_tj").first();
                System.out.println(PatternUtil.getFirstPattern(element.text(),
                        "\\d+"));
                double num = BasicNumberUtil.getDouble(PatternUtil
                        .getFirstPattern(element.text(), "\\d+"));
                if (num > 0)
                {
                    for (int i = 1; i <= 90 && i <= Math.ceil(num / pageSize); i++)
                    {
                        Map map = new HashMap();
                        map.put("jl", jl);
                        map.put("we", we);
                        map.put("p", i + "");
                        this.taskList.add(map);

                    }
                }
            }
        };
        dispatcher.buildTaskList();
        dispatcher.buildThreadList(4, new PageDealClass(this.taskList));
        dispatcher.paiTask();
        dispatcher.doTask();
        dispatcher.waitOperating();
        for (Object obj : this.taskList)
        {
            obj = null;
        }
    }

    public static void main(String[] args)
    {
        String[] jls =
        { "北京", "上海", "天津", "重庆", "广东", "湖北", "陕西", "四川", "辽宁", "吉林", "江苏",
                "山东", "浙江", "广西", "安徽", "河北", "山西", "内蒙", "黑龙江", "福建", "江西",
                "河南", "湖南", "海南", "贵州", "云南", "西藏", "甘肃", "青海", "宁夏", "新疆",
                "香港", "澳门", "台湾省" };
        String[] wes =
        { "0000", "0001", "0103", "0305", "0510", "1099" };
        Dispatch_WorkSeek test = null;
        for (int i = 11; i < jls.length; i++)
        {
            String city = jls[i];
            for (String year : wes)
            {
                BasicRunTimeUtil timeUtil = new BasicRunTimeUtil("");
                timeUtil.beginRun();
                test = new Dispatch_WorkSeek(city, year);
                test.buildTaskList();

                test.buildThreadList(4, new ZhiL_GrabWork());
                test.paiTask();
                test.doTask();
                test.waitOperating();

                System.out.println("Time:" + timeUtil.getCurrentRunSecond());
            }
        }
    }
}
