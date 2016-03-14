package test.mywork;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.wnc.basic.BasicStringUtil;
import com.wnc.string.PatternUtil;
import com.wnc.tools.SoupUtil;

public class TestZhilian
{
	@Test
	public void test()
	{
		Document doc = SoupUtil
				.getDoc("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E9%80%89%E6%8B%A9%E5%9C%B0%E5%8C%BA&kw=java&p=2&isadv=0");
		// Elements eles = doc.getElementsByClass("newlist");
		// System.out.println(doc);

		Elements eles = doc.select(".newlist");
		for (Element element : eles)
		{
			String urlString = getHref(element);
			System.out.println("                  AAA:" + urlString);
			if (!BasicStringUtil.isNullString(urlString))
			{
				Document subDocument = SoupUtil.getDoc(urlString);
				Elements basicInfos = subDocument.select(".terminalpage-left>ul>li");
				getPrice(basicInfos.first().text());

				String htmlTxt = subDocument.select(".tab-inner-cont").first()
						.html();
				prepareWork(htmlTxt);

				System.out.println("公司简介:"
						+ subDocument.select(".tab-inner-cont").get(1).text());
			}
		}

	}

	/***
	 * 重点:任职条件和职责,或者还有福利
	 * 
	 * @param htmlTxt
	 */
	private void prepareWork(String htmlTxt)
	{
		// TODO Auto-generated method stub
		htmlTxt = htmlTxt.replace("</br>", "咼").replace("&nbsp;", "");
		String[] tokens = htmlTxt.split("咼");
		for (int i = 0; i < tokens.length; i++)
		{
			System.out.println(tokens[i].replaceAll("<([^>]*)>", ""));
		}
	}

	private void testPattern()
	{
		String string = "岗位职责： 1、根据项目需求，進行任务分配，完成相应模块软件的设计、开发、编程任务； 2、撰写软件产品开发技术文档，解決团队执行中碰到的技术困难； 3、负责对目前现有应用系统的维护和优化工作。 任职要求： 1、计算机科学与技术、软件工程等计算机软件专业； 2、具备扎实JAVA基础知识，熟悉J2EE应用的相关开发技术； 3、熟练掌握面向对象基础和设计模式，熟悉java开源框架，如Spring、Hibernate、Struts等； 4、熟悉主流关系型数据库（oracle、mysql等）； 5、熟悉JavaScript、CSS、HTML4/5、JQuery，对DWR、Bootstrap、EasyUI、DWZ、ExtJS有一定了解； 6、具备良好的编码风格和开发习惯，熟练掌握UML设计工具； 7、熟悉Linux操作系统； 8、具备一定的独立分析问题，解决问题的能力； 9、具备清晰逻辑思维，良好的沟通技巧，团队合作精神，可承受一定的工作压力。   工作地址： 北京市东城区和平里东街民旺园 北京思能科贸有限公司该公司其他职位 公司概况： 北京思能科贸有限公司，成立于2003年，是由中国互联网资深精英联合海归博士创立的互联网服务企业，旨在提供中国最好的智能化办公信息系统。 发展状况： 公司致力为广大中小企业、事业单位、政府机构及科研院校等提供完备的办公智能信息系统（SIR）。公司曾为中国邮政集团、中国南方电网、安邦保险、施耐德电气、徐州矿务集团等国内一流企业提供过服务。公司2013年独立研发出专为中小机构办公服务的SIR企业智能信息系统。 发展愿景： 为中小企业提供优质的智能化办公服务，降低中小企业IT使用和维护成本，提高企业信息化水平和管理效率。 公司的核心价值： “专注 创新 包容” 公司主要产品： SIR(SmartInstitutional Repository) 软硬件一体的企业智能数据服务系统。 企业邮箱：基于CMAIL平台的高端企业邮箱。 文档管理系统：各行业通用的简单易行的文档管理和数据存储系统。 销售业绩以及网络： 公司致力于为广大中小企业、事业单位、政府机构及科研院校等提供智能办公信息化解决方案和服务，帮助中小机构实现智能化办公和企业数据管理。同时，在北京、湖南、广西、山西及海南省均建立了销售网络。 售后服务： 公司有完备的售后服务团队，由经验丰富的IT工程师为企业提供使用培训和售后维修服务，并提供系统免费升级、系统性能监控、现场安装，以及在服务期内免费更换硬件等服务。";
		System.out.println(PatternUtil.getFirstPattern(string, "岗位[\\s\\S]*任职要求"));

	}

	/**
	 * 获得工资范围
	 * 
	 * @param priceStr
	 */
	private void getPrice(String priceStr)
	{
		// priceStr = "6001-8000元/月";
		// priceStr = "面议";
		String firstString = PatternUtil.getFirstPattern(priceStr, "\\d+");
		String lastString = PatternUtil.getLastPattern(priceStr, "\\d+");
		System.out.println("工资: " + firstString + "  A:B " + lastString);

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
}
