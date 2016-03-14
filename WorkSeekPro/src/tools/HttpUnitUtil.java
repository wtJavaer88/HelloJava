package tools;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpUnitUtil
{

	public static HtmlPage getHtmlPage(String url)
	{

		/** HtmlUnit请求web页面 */
		WebClient wc = new WebClient(BrowserVersion.CHROME);
		wc.getOptions().setUseInsecureSSL(true);
		wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		wc.getOptions().setCssEnabled(false); // 禁用css支持
		wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
		wc.getOptions().setTimeout(10000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
		wc.getOptions().setDoNotTrackEnabled(false);

		HtmlPage page = null;
		try
		{
			page = wc.getPage(url);
			if (page != null)
			{
				// System.out.println(page.asXml());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return page;
	}
}