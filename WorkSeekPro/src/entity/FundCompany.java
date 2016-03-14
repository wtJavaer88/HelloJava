package entity;

import java.sql.Date;

public class FundCompany
{
	public String getComName()
	{
		return comName;
	}

	public void setComName(String comName)
	{
		this.comName = comName;
	}

	public int getComId()
	{
		return comId;
	}

	public void setComId(int comId)
	{
		this.comId = comId;
	}

	public String getComCode()
	{
		return comCode;
	}

	public void setComCode(String comCode)
	{
		this.comCode = comCode;
	}

	public String getKefuPhone()
	{
		return kefuPhone;
	}

	public void setKefuPhone(String kefuPhone)
	{
		this.kefuPhone = kefuPhone;
	}

	public String getTt_url()
	{
		return tt_url;
	}

	public void setTt_url(String tt_url)
	{
		this.tt_url = tt_url;
	}

	public String getWorkPlace()
	{
		return workPlace;
	}

	public void setWorkPlace(String workPlace)
	{
		this.workPlace = workPlace;
	}

	public String getBossName()
	{
		return bossName;
	}

	public void setBossName(String bossName)
	{
		this.bossName = bossName;
	}

	public int getMgrCounts()
	{
		return mgrCounts;
	}

	public void setMgrCounts(int moneyCounts)
	{
		this.mgrCounts = moneyCounts;
	}

	public String getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getCompChracter()
	{
		return compChracter;
	}

	public void setCompChracter(String compChracter)
	{
		this.compChracter = compChracter;
	}

	public int getLevels()
	{
		return levels;
	}

	public void setLevels(int levels)
	{
		this.levels = levels;
	}

	public double getMoneyTotal()
	{
		return moneyTotal;
	}

	public void setMoneyTotal(double moneyTotal)
	{
		this.moneyTotal = moneyTotal;
	}

	public int getAllFunds()
	{
		return allFunds;
	}

	public void setAllFunds(int allFunds)
	{
		this.allFunds = allFunds;
	}

	public int getNormalFunds()
	{
		return normalFunds;
	}

	public void setNormalFunds(int normalFunds)
	{
		this.normalFunds = normalFunds;
	}

	public int getCoinFunds()
	{
		return coinFunds;
	}

	public void setCoinFunds(int coinFunds)
	{
		this.coinFunds = coinFunds;
	}

	public int getLicaiFunds()
	{
		return licaiFunds;
	}

	public void setLicaiFunds(int licaiFunds)
	{
		this.licaiFunds = licaiFunds;
	}

	public int getQitaFunds()
	{
		return qitaFunds;
	}

	public void setQitaFunds(int qitaFunds)
	{
		this.qitaFunds = qitaFunds;
	}

	public Date getRecentUpTime()
	{
		return recentUpTime;
	}

	public void setRecentUpTime(Date recentUpTime)
	{
		this.recentUpTime = recentUpTime;
	}

	public String getPinyin_all()
	{
		return pinyin_all;
	}

	public void setPinyin_all(String pinyin_all)
	{
		this.pinyin_all = pinyin_all;
	}

	public String getPinyin_jx()
	{
		return pinyin_jx;
	}

	public void setPinyin_jx(String pinyin_jx)
	{
		this.pinyin_jx = pinyin_jx;
	}

	public String getComWeb()
	{
		return comWeb;
	}

	public void setComWeb(String comWeb)
	{
		this.comWeb = comWeb;
	}

	private String comName = "";// 公司名称
	private int comId = 0; // 公司ID
	private String comCode = ""; // 公司Code,默认为介绍url的后缀
	private String comWeb = "";// 公司官网
	private String kefuPhone = "";// 公司客服电话
	private String tt_url = "";// 在天天网的介绍页面
	private String workPlace = "";// 工作地址
	private String bossName = "";// 总经理
	private int mgrCounts = 0;// 经理人数
	private String createDate = "";// 成立日期
	private String compChracter = "";// 公司性质
	private int levels = 0;// 海通评级
	private double moneyTotal = 0.0;// 资金规模,亿为单位
	private int allFunds = 0;// 基金总数
	public int normalFunds = 0;// 普通基金数
	public int coinFunds = 0;// 货币基金数
	public int licaiFunds = 0;// 理财基金数
	public int qitaFunds = 0;// 其他基金数
	public Date recentUpTime; // 更新日期
	public String pinyin_all = ""; // 拼音全拼
	public String pinyin_jx = ""; // 拼音简拼
}
