package grab.dispatch;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tools.UrlParamUtil;

import com.wnc.basic.BasicStringUtil;
import com.wnc.threads.CustomTaskDealClass;

public class PageDealClass extends CustomTaskDealClass
{
    List list = null;
    Document doc = null;
    /**
     * 当前要搜索的页码
     */
    public String p = "1";
    /**
     * 工作经验的选择
     */
    public String we = null;
    /**
     * 工作区域的选择
     */
    public String jl = null;
    /**
     * 关键词的选择
     */

    private String kw = "java";
    public String baseUrl = "http://sou.zhaopin.com/jobs/searchresult.ashx";

    public String getKw()
    {
        return kw;
    }

    public void setKw(String kw)
    {
        this.kw = kw;
    }

    public PageDealClass(List list)
    {
        this.list = list;
    }

    @Override
    public boolean deal(Object obj)
    {
        if (obj instanceof Map)
        {
            Map map = (Map) obj;
            this.jl = (String) map.get("jl");
            this.we = (String) map.get("we");
            this.p = (String) map.get("p");
            map = null;
        }
        UrlParamUtil urlUtil = new UrlParamUtil(baseUrl);
        String href = urlUtil
                .getAllString("jl", jl, "kw", kw, "we", we, "p", p);
        // String href = new String(baseUrl + "?jl=" + jl + "&kw=" + kw + "&we="
        // + we + "&p=" + p);

        // System.out.println(href);
        doc = getDoc(href);
        href = null;
        urlUtil.clearMemory();
        urlUtil = null;

        Elements eles = doc.select(".newlist");

        for (Element element : eles)
        {
            String urlString = getHref(element);
            synchronized (this)
            {
                if (BasicStringUtil.isNotNullString(urlString))
                {
                    this.list.add(urlString);
                }
            }
            // 防止内存溢出
            urlString = null;
        }
        doc = null;
        return false;
    }

    public Document getDoc(String p)
    {

        Connection con = Jsoup.connect(p);
        con.timeout(300000);
        con.header("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
        try
        {
            doc = con.get();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        finally
        {
            con = null;
        }

        return doc;
    }

    /**
     * 起点:获取工作链接
     * 
     * @param element
     * @return
     */
    private String getHref(Element element)
    {
        if (element != null)
        {
            Elements elements = element.select("a");
            if (elements != null && elements.size() > 0)
            {
                return elements.first().attr("href");
            }
        }

        return null;
    }

    @Override
    public Object clone()
    {
        PageDealClass work = new PageDealClass(this.list);
        work.basicExcuteStatus = this.basicExcuteStatus;
        work.basicErrorMessage = this.basicErrorMessage;
        work.showMessageFlag = this.showMessageFlag;
        return work;
    }

    @Override
    public String getClassName()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
