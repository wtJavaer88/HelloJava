package tools;

import org.jsoup.nodes.Element;

import com.wnc.basic.BasicNumberUtil;
import com.wnc.basic.BasicStringUtil;
import com.wnc.string.PatternUtil;

public class HtmlUtil
{
	public static String getElementText(Element e)
	{
		String text = "";
		if (e != null && e.text() != null)
		{
			text = e.text();
		}
		return text;
	}

	/**
	 * 
	 * @param e
	 *            Element对象
	 * @param pattern
	 *            正则表达式,如\\d+表示数字,\\d+.\\d+表示小数
	 * @param index
	 *            0表示第一个,1表示最后一个匹配的
	 * @return
	 */
	public static double getNumberText(Element e, String pattern, int index)
	{
		double numStr = 0.0;
		if (e != null)
		{
			numStr = getNumberTextByStr(e.text(), pattern, index);
		}
		return numStr;
	}

	public static double getNumberText(Element e)
	{
		double numStr = 0.0;
		if (e != null)
		{
			String rightStr = PatternUtil.getFirstPattern(e.text(), "\\d+.\\d+");
			numStr = BasicNumberUtil.getDouble(rightStr);
		}
		return numStr;
	}

	public static double getNumberTextByStr(String str, String pattern, int index)
	{
		Double numStr = 0.0;
		if (BasicStringUtil.isNotNullString(str))
		{
			String rightStr = "";
			rightStr = PatternUtil.getPatternByIndex(str, pattern, index);

			numStr = BasicNumberUtil.getDouble(rightStr);
			// java.text.DecimalFormat df = new
			// java.text.DecimalFormat("########.00");
			// df.format(numStr.doubleValue());

		}
		return numStr;
	}

	// 获取大数字, 比如80000248
	public static String getBigDecimalStr(String str, String pattern, int index)
	{
		String rightStr = "";
		if (BasicStringUtil.isNotNullString(str))
		{
			if (index == 0)
			{
				rightStr = PatternUtil.getFirstPattern(str, pattern);
			}
			else if (index == 1)
			{
				rightStr = PatternUtil.getLastPattern(str, pattern);
			}

		}
		return rightStr;
	}

	/**
	 * 
	 * @param e
	 * @return 默认匹配2015-12-24格式
	 */
	public static String getDateText(Element e)
	{
		return getDateText(e, "\\d+-\\d+-\\d+");
	}

	/**
	 * 
	 * @param e
	 * @param pattern
	 *            如\\d+-\\d+-\\d+表示2015-12-24类型
	 * @return
	 */
	public static String getDateText(Element e, String pattern)
	{
		String dateStr = "";
		if (e != null && BasicStringUtil.isNotNullString(e.text()))
		{
			dateStr = PatternUtil.getFirstPattern(e.text(), pattern);
		}
		return dateStr;
	}

	public static String getDateText(Element e, String pattern, int index)
	{
		String dateStr = "";
		if (e != null && BasicStringUtil.isNotNullString(e.text()))
		{
			dateStr = PatternUtil.getPatternByIndex(e.text(), pattern, index);
		}
		return dateStr;
	}
}
