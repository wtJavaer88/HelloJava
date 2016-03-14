package entity;

import java.sql.Date;

public class FundInfo
{
	public int getComId()
	{
		return comId;
	}

	public void setComId(int comId)
	{
		this.comId = comId;
	}

	public String getFundName()
	{
		return fundName;
	}

	public void setFundName(String fundName)
	{
		this.fundName = fundName;
	}

	public int getFundId()
	{
		return fundId;
	}

	public void setFundId(int fundId)
	{
		this.fundId = fundId;
	}

	public String getFundCode()
	{
		return fundCode;
	}

	public void setFundCode(String fundCode)
	{
		this.fundCode = fundCode;
	}

	public String getFundType()
	{
		return fundType;
	}

	public void setFundType(String fundType)
	{
		this.fundType = fundType;
	}

	public String getTt_url()
	{
		return tt_url;
	}

	public void setTt_url(String tt_url)
	{
		this.tt_url = tt_url;
	}

	public String getTt_tieba_url()
	{
		return tt_tieba_url;
	}

	public void setTt_tieba_url(String tt_tieba_url)
	{
		this.tt_tieba_url = tt_tieba_url;
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

	public String getBuy_State()
	{
		return buy_State;
	}

	public void setBuy_State(String buy_State)
	{
		this.buy_State = buy_State;
	}

	public String getFee()
	{
		return fee;
	}

	public void setFee(String fee)
	{
		this.fee = fee;
	}

	public String getMgrName()
	{
		return mgrName;
	}

	public void setMgrName(String fundMgr)
	{
		this.mgrName = fundMgr;
	}

	private int comId = 0; // 所属公司ID
	private String fundName = "";// 基金名称
	private int fundId = 0; // 基金Code
	private String fundCode = "000000"; // 基金编号
	private String mgrName = "某某"; // 基金经理名字
	private String fundType = "";
	private String tt_url = "";// 在天天网的介绍页面
	private String tt_tieba_url = "";// 在天天网的基金吧页面
	private Date recentUpTime; // 更新日期
	private String pinyin_all = ""; // 拼音全拼
	private String pinyin_jx = ""; // 拼音简拼
	private String buy_State = "";
	private String fee = "";
	// private double newValue = 0.0;// 最新的净值
	// private double newIncreaseRate = 0.00;// 最新的涨幅

}
