package tools;

import com.wnc.basic.BasicStringUtil;

public class UrlParamUtil
{
    String baseUrl = "";
    int paramCount = 0;
    String paramsUrl = "";

    public UrlParamUtil(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    public void clearMemory()
    {
        this.baseUrl = null;
        this.paramsUrl = null;
    }

    /**
     * 加入请求参数, 并返回当前的url
     * 
     * @param key
     * @param value
     * @return
     */
    public String appendParam(String key, String value)
    {
        if (!BasicStringUtil.isNullString(key, value))
        {
            if (0 == paramCount)
            {
                paramsUrl = baseUrl + "?" + key + "=" + value;
            }
            else
            {
                paramsUrl += "&" + key + "=" + value;
            }
            paramCount++;
            return paramsUrl;
        }
        return baseUrl;
    }

    public String getAllString(String... args)
    {
        String retStr = new String("");
        int len = 0;
        if (args != null)
        {
            len = args.length;
        }
        else
        {
            System.out.println("为空返回");
            return baseUrl;
        }
        if (len < 2 || len % 2 == 1)
        {
            return baseUrl;
        }

        // System.out.println("参数成对出现!");
        String comp = "";
        String preChar = "?";
        String str = null;
        String value = null;
        for (int i = 0; i < len; i += 2)
        {
            if (i > 0)
            {
                preChar = "&";
            }
            str = args[i];
            value = args[i + 1];

            if (i == len - 2)
            {
                retStr = appendParam(str, value);
                return retStr;
            }
            else
            {
                appendParam(str, value);
            }
            str = null;
            value = null;
        }// end of for
        return baseUrl;
    }// end of function
}
