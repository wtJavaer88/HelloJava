package entity;

import java.util.ArrayList;
import java.util.List;

public class ITWorkInfo
{

	public String getWorkCity()
	{
		return workCity;
	}

	public void setWorkCity(String workCity)
	{
		this.workCity = workCity;
	}

	public String getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(String publishDate)
	{
		this.publishDate = publishDate;
	}

	public String getWorkPost()
	{
		return workPost;
	}

	public void setWorkPost(String workPost)
	{
		this.workPost = workPost;
	}

	public String getWorkPlace()
	{
		return workPlace;
	}

	public void setWorkPlace(String workPlace)
	{
		this.workPlace = workPlace;
	}

	public String getWorkAge()
	{
		return workAge;
	}

	public void setWorkAge(String workAge)
	{
		this.workAge = workAge;
	}

	public String getWorkUrl()
	{
		return workUrl;
	}

	/**
	 * 当前爬虫地址
	 * 
	 * @param workUrl
	 */
	public void setWorkUrl(String workUrl)
	{
		this.workUrl = workUrl;
	}

	public int[] getSalary()
	{
		return salary;
	}

	/**
	 * 设置薪水区间
	 * 
	 * @param workSkills
	 */
	public void setSalary(int[] salary)
	{
		this.salary = salary;
	}

	public String getCompName()
	{
		return compName;
	}

	/**
	 * 设置公司名字
	 * 
	 * @param workSkills
	 */
	public void setCompName(String compName)
	{
		this.compName = compName;
	}

	public String getComPlace()
	{
		return comPlace;
	}

	/**
	 * 设置公司地址
	 * 
	 * @param workSkills
	 */
	public void setComPlace(String comPlace)
	{
		this.comPlace = comPlace;
	}

	public String getComSelfUrl()
	{
		return comSelfUrl;
	}

	/**
	 * 设置公司自己的页面
	 * 
	 * @param workSkills
	 */
	public void setComSelfUrl(String comSelfUrl)
	{
		this.comSelfUrl = comSelfUrl;
	}

	public String getComZhaopinUrl()
	{
		return comZhaopinUrl;
	}

	/**
	 * 设置公司在网站上的页面
	 * 
	 * @param workSkills
	 */
	public void setComZhaopinUrl(String comZhaopinUrl)
	{
		this.comZhaopinUrl = comZhaopinUrl;
	}

	public String getComProperty()
	{
		return comProperty;
	}

	/**
	 * 设置公司性质
	 * 
	 * @param workSkills
	 */
	public void setComProperty(String comProperty)
	{
		this.comProperty = comProperty;
	}

	public int getComWorkers()
	{
		return comWorkers;
	}

	/**
	 * 设置公司人数规模,取最小值
	 * 
	 * @param workSkills
	 */
	public void setComWorkers(int comWorkers)
	{
		this.comWorkers = comWorkers;
	}

	public String getCompDesc()
	{
		return compDesc;
	}

	/**
	 * 设置公司简介
	 * 
	 * @param workSkills
	 */
	public void setCompDesc(String compDesc)
	{
		this.compDesc = compDesc;
	}

	public String getWelfare()
	{
		return welfare;
	}

	/**
	 * 设置公司福利
	 * 
	 * @param workSkills
	 */
	public void setWelfare(String welfare)
	{
		this.welfare = welfare;
	}

	public List<String> getWorkDuties()
	{
		return workDuties;
	}

	/**
	 * 设置任职职责
	 * 
	 * @param workSkills
	 */
	public void setWorkDuties(List<String> workDuties)
	{
		this.workDuties = workDuties;
	}

	public List<String> getWorkSkills()
	{
		return workSkills;
	}

	/**
	 * 设置任职要求
	 * 
	 * @param workSkills
	 */
	public void setWorkSkills(List<String> workSkills)
	{
		this.workSkills = workSkills;
	}

	private int[] salary = { 0, 0 }; // 薪水
	private String compName = "";// 公司名字
	private String comPlace = "";// 公司地址
	private String comSelfUrl = ""; // 公司网址
	private String comZhaopinUrl = ""; // 公司在招聘网网址

	private String comProperty = "";// 公司性质
	private int comWorkers = 0;// 公司员工数, 取最小值
	private String compDesc = "";// 公司简介
	private String welfare = ""; // 公司福利
	private String publishDate = ""; // 发布日期

	private String workPost = "工作岗位";
	private String workCity = "工作城市";
	private String workPlace = "";// 工作地址
	private String workAge = "1-3";// 工作年限
	private String workUrl = "";// 当前工作信息网址
	private List<String> workDuties = new ArrayList<String>(); // 工作职责
	private List<String> workSkills = new ArrayList<String>(); // 工作要求

}
