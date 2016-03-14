package entity;

import java.sql.Date;

public class FundManager
{
	public String mgrName = "";// 基金经理名称
	public int mgrId = 0; // 基金经理ID
	public String mgrCode = ""; // 基金经理编码 ,默认为介绍url的后缀
	public String tt_url = "";// 在天天网的介绍页面
	public int recentFundId = 0; // 最新任职基金
	public int fundCounts = 0; // 同时负责基金数目
	public String firstWorkDate = "";// 第一次任职时间
	public Date recentUpTime; // 更新日期
	public String pinyin_all = ""; // 拼音全拼
	public String pinyin_jx = ""; // 拼音简拼
}
