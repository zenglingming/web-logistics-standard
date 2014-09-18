package com.incito.logistics.pages;

import org.openqa.selenium.By;

/**
 * 定义完善用户信息页面元素
 * 
 * */
public class AddUserInfoPage {
	/** 姓名输入框 */
	public static final By AUIP_INPUT_NAME = By.name("name");
	/**联系电话输入框*/
	public static final By AUIP_INPUT_TEL = By.name("tel");
	/**身份证输入框*/
	public static final By AUIP_INPUT_COMPANY = By.name("company");
	/**身份证输入框*/
	public static final By AUIP_INPUT_IDCARD = By.name("icard");
	/**身份证输入框*/
	public static final By AUIP_SELECT_PROVINCE = By.id("province");
	/**身份证输入框*/
	public static final By AUIP_INPUT_CITY = By.id("city");
	/**身份证输入框*/
	public static final By AUIP_INPUT_REGION = By.id("region");
	/**身份证输入框*/
	public static final By AUIP_INPUT_ADDRESS = By.id("address");	
	/**上传图片按钮1*/
	public static final By AUIP_INPUT_UPLOAD1 = By.className("fileinput-new");
	/**提交按钮*/
	public static final By AUIP_BUTTON_SUBMIT = By.xpath("//button[text()='提交']");
	/**返回按钮*/
	public static final By AUIP_BUTTON_BACK = By.xpath("//button[text()='返回']");
	/**顶部title*/
	public static final By AUIP_TEXT_TITLE = By.xpath("//span[text()='请完善您的个人信息']");
	/**姓名文本*/
	public static final By AUIP_TEXT_NAME = By.xpath("//label[text()='姓名：']");
	/**联系电话文本*/
	public static final By AUIP_TEXT_TEL = By.xpath("//label[text()='联系电话：']");
	/**身份证号码文本*/
	public static final By AUIP_TEXT_IDCARD = By.xpath("//label[text()='身份证号：']");
	/**公司地址文本*/
	public static final By AUIP_TEXT_ADDRESS = By.xpath("//label[text()='公司地址：']");
	/**公司名称文本*/
	public static final By AUIP_TEXT_COMPANY = By.xpath("//label[text()='公司名称：']");
	/**证件照片文本*/
	public static final By AUIP_TEXT_PHOTO = By.xpath("//label[text()='证件照片：']");

	
	
	
	

}