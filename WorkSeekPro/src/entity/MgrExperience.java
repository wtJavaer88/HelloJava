package entity;

import java.sql.Date;

public class MgrExperience
{
	public int mgrId = 0; // 经理ID
	public String fundCode = ""; // 基金编号
	public String beginDate = ""; // 上任日期
	public String endDate = ""; // 终止日期
	public double mercantileRate = 0.0; // 回报率
	public Date recentUpTime; // 更新日期
}
