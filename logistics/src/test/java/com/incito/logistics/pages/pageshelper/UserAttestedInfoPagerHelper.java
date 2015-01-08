package com.incito.logistics.pages.pageshelper;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.ITestContext;

import com.incito.logistics.pages.UserAttestedInfoPage;
import com.incito.logistics.util.SeleniumUtil;

/**
 * @author xy-incito-wy
 * @description 完善用户信息页面帮助类
 * */
public class UserAttestedInfoPagerHelper {
	public static Logger logger = Logger.getLogger(UserAttestedInfoPagerHelper.class.getName());

	/** 等待完善信息页面元素加载出来 */
	public static void waitUserAttestedInfoPageToLoad(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking addUserInfo page elements");
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_TEXT_TITLE);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_INPUT_ADDRESS);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_SELECT_CITY);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_INPUT_COMPANY);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_INPUT_IDCARD);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_INPUT_NAME);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_SELECT_REGION);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_INPUT_TEL);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_SELECT_PROVINCE);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_INPUT_UPLOAD);

		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_BUTTON_BACK);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_BUTTON_SUBMIT);
		logger.info("Check addUserInfo page elements completed");
	}
	
	/** 等待完善信息页面元素加载出来 */
	public static void waitUserAttestingInfoPageToLoad(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking addUserInfo page elements");
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_TEXT_TITLE);
		logger.info("Check addUserInfo page elements completed");
	}

	/** 检查完善信息页面上的文本 */
	public static void checkUserUnattestedInfoPageText(SeleniumUtil seleniumUtil) {
		logger.info("Start checking addUserInfo page text");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_TITLE).getText(), "认证信息");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_TITLE_DES).getText(), "请完善认证信息，通过认证后让更多的司机关注到您的货源信息，并提升订单成交率。" + "\n" + "您还可以查看更详细的车源与货源。");

		int size = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_DES).size();
		String tabs[] = { "姓名", "联系电话", "图形码", "短信验证码", "身份证号", "公司名称", "公司地址", " ", "证件照片" };
		for (int i = 0; i < size; i++) {
			if (i == 7)
				continue;
			String temp = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_DES).get(i).getText().replaceAll(" ", "");
			temp = temp.substring(temp.indexOf("*") + 1, temp.lastIndexOf("："));
			seleniumUtil.isTextCorrect(temp, tabs[i]);
		}
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEL_PROVING).getText().replaceAll(" ", ""), "发送验证码至手机");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_BUTTON_SUBMIT).getText().replaceAll(" ", ""), "提交");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_BUTTON_BACK).getText().replaceAll(" ", ""), "返回");
		logger.info("Check addUserInfo page text completed");
	}

	/** 完善信息页面上点击“提交”或者“返回”按钮 */
	public static void enterPage(SeleniumUtil seleniumUtil, By elementLocator) {
		seleniumUtil.click(seleniumUtil.findElementBy(elementLocator));
	}

	/** 认证用户信息界面完善信息页面上传照片 */
	public static void upLoadPhoto(ITestContext context, SeleniumUtil seleniumUtil, By by, String[] filepath) {
		String browserName = context.getCurrentXmlTest().getParameter("browserName");
		for (int i = 0; i < filepath.length; i++) {
			seleniumUtil.click(seleniumUtil.findElementsBy(by).get(i));
			seleniumUtil.handleUpload(browserName, new File(filepath[i]));
		}
	}

	/** 认证用户信息界面完善用户信息中输入用户的手机号码和验证码的封装 */
	public static void typeTelChit(SeleniumUtil seleniumUtil, By[] bys, String... info) {
		seleniumUtil.type(seleniumUtil.findElementBy(bys[0]), info[0]);
		seleniumUtil.waitForElementToLoad(15, bys[1]);
		seleniumUtil.type(seleniumUtil.findElementBy(bys[1]), info[1]);
		seleniumUtil.click(seleniumUtil.findElementBy(bys[3]));
		seleniumUtil.pause(1500);
		seleniumUtil.type(seleniumUtil.findElementBy(bys[2]), info[2]);
	}

	/** 检查完善信息页面:全为空的提示语 */
	// 公司名称提示语没有，待修改之后在修复该自动化脚本，因为不知道该提示语为什么！
	public static void checkUserAuthenticationInfoPrompt(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_TITLE).getText(), "认证信息");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_NAME_PROMPT).getText(),
		// "请输入2-8个中文字符");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_TEL_PROMPT).getText(),
		// "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_IDCARD_PROMPT).getText(),
		// "请输入正确的身份证号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_ADDRESS_PROMPT).getText(),
		// "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面：只填写姓名的提示语 */
	public static void checkUserAuthenticationInfoPrompt_Name(int timeOut, SeleniumUtil seleniumUtil, Map<String, String> data) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),
		// "");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面：只填写联系方式的提示语 */
	public static void checkUserAuthenticationInfoPrompt_Tel(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面：只填写身份证号的提示语 */
	public static void checkUserAuthenticationInfoPrompt_Idcard(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面：只填写公司名称的提示语 */
	// 是个bug现在还没有修复
	public static void checkUserAuthenticationPrompt_Company(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面：只填写公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_Address(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名和联系方式的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameTel(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名和身份证号的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameIdcard(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名和公司名称的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameCompany(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名和公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameAdress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了联系方式和身份证号的提示语 */
	public static void checkUserAuthenticationInfoPrompt_TelIdcard(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了联系方式和公司名称的提示语 */
	public static void checkUserAuthenticationInfoPrompt_TelCompany(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了联系方式和公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_TelAdress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了身份证号和公司名称的提示语 */
	public static void checkUserAuthenticationInfoPrompt_IdcardCompany(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了身份证号和公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_IdcardAdress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了公司名称和公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_CompanyAdress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名，联系方式，身份证号的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameTelIdcard(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名，联系方式，公司名称的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameTelCompany(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名，联系方式，公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameTelAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名，身份证号，公司名称的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameIdcardCompany(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名，身份证号，公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameIdcardAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了姓名，公司名称，公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameCompanyAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了联系方式，身份证号，公司名称的提示语 */
	public static void checkUserAuthenticationInfoPrompt_TelIdcardCompany(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了联系方式，身份证号，公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_TelIdcardAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了联系方式，公司名称，公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_TelCompanyAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写了身份证号，公司名称，公司地址的提示语 */
	public static void checkUserAuthenticationInfoPrompt_IdcardCompanyAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面：填写姓名，电话，身份证号，公司名称的提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameTelIdcardCompany(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写姓名，电话，身份证号，公司地址提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameTelIdcardAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写姓名，电话，公司名称，公司地址提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameTelCompanyAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写姓名，身份证号，公司名称，公司地址提示语 */
	public static void checkUserAuthenticationInfoPrompt_NameIdcardCompanyAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:填写电话，身份证号，公司名称，公司地址提示语 */
	public static void checkUserAuthenticationInfoPrompt_TelIdcardCompanyAddress(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");

		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_NAME_PROMPT).getText(), "请输入2-8个中文字符");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_TEL_PROMPT).getText(), "请输入正确的电话号码");
		// seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY_PROMPT).getText(),"");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_IDCARD_PROMPT).getText(), "请输入正确的身份证号码");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_INPUT_ADDRESS_PROMPT).getText(), "省 不能为空");

		logger.info("Check checkAddUserInfoPrompt page text completed");
	}

	/** 检查完善信息页面:必填项全部填写 */
	public static void checkUserAttestedInfoPrompt_All(int timeOut, SeleniumUtil seleniumUtil) {
		logger.info("Start checking checkAddUserInfoPrompt page text");
		seleniumUtil.pause(1000);
		seleniumUtil.waitForElementToLoad(timeOut, UserAttestedInfoPage.UAIP_POPUP_TRUE);
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_POPUP_TRUE).getText().trim(), "认证信息提交成功，我们将在24小时内完成审核，您现在可以去发布货源了！");
		logger.info("Check checkAddUserInfoPrompt page text completed");
	}
	//********************认证审核中的帮助类方法***********************************************
	/** 检查认证信息页面上的文本（预览界面） */
	public static void checkUserAttestingInfoPageText(SeleniumUtil seleniumUtil) {
		logger.info("Start checking addUserInfo page text");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_TITLE).getText(), "认证信息");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_NOTE_ATTESTING).getText(), "您的资料正在审核中，请耐心等待！");

		int size = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTING).size();
		String tabs[] = { "姓名", "联系电话", "身份证号", "公司名称", "公司地址", "证件照片" };
		for (int i = 0; i < size; i++) {
			String temp = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTING).get(i).getText().replaceAll(" ", "");
			temp = temp.substring(0, temp.lastIndexOf("："));
			seleniumUtil.isTextCorrect(temp, tabs[i]);
		}
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_CONTACT_ATTESTING).getText(), "如您有任何疑问，请电话联系我们：400-860-3060(周一至周日9:00---18:00)");
		logger.info("Check addUserInfo page text completed");
	}
	//********************已认证的帮助类方法***********************************************
	/** 检查认证信息页面上的文本（预览界面） */
	public static void checkUserAttestedInfoPageText(SeleniumUtil seleniumUtil) {
		logger.info("Start checking addUserInfo page text");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_TITLE).getText(), "认证信息");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_NOTE1_ATTESTED).getText(), "您的资料已通过认证！");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_NOTE2_ATTESTED).getText(), "恭喜您认证成功，现在您可以发布已认证的货源，货源信息更加可靠，司机会更加快速的找到您！");
		
		int size = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTING).size();
		String tabs[] = { "姓名", "联系电话", "身份证号", "公司名称", "公司地址", "证件照片" };
		for (int i = 0; i < size; i++) {
			String temp = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTING).get(i).getText().replaceAll(" ", "");
			temp = temp.substring(0, temp.lastIndexOf("："));
			seleniumUtil.isTextCorrect(temp, tabs[i]);
		}
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_CONTACT_ATTESTING).getText(), "如您有任何疑问，请电话联系我们：400-860-3060(周一至周日9:00---18:00)");
		logger.info("Check addUserInfo page text completed");
	}
	/** 检查认证信息页面上的文本（修改界面） */
	public static void checkUserAttestedInfoPageTextModify(SeleniumUtil seleniumUtil) {
		logger.info("Start checking addUserInfo page text");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_TITLE).getText(), "认证信息");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_NOTE1_ATTESTED).getText(), "您的资料已通过认证！");
		
		int size = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTED).size();
		String tabs[] = { "姓名", "联系电话", "身份证号", "公司名称", "公司地址", " ", "证件照片" };
		for (int i = 0; i < size; i++) {
			if (i == 5)
				continue;
			String temp = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTED).get(i).getText().replaceAll(" ", "");
			temp = temp.substring(temp.indexOf("*") + 1, temp.lastIndexOf("："));
			seleniumUtil.isTextCorrect(temp, tabs[i]);
		}
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_CONTACT_ATTESTING).getText(), "如您有任何疑问，请电话联系我们：400-860-3060(周一至周日9:00---18:00)");
		logger.info("Check addUserInfo page text completed");
	}
	
	/** 检查认证修改信息页面上的修改未成功检查 */
	public static void checkUserAttestedInfoPageModifyFail(SeleniumUtil seleniumUtil) {
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_PASSWORD).getText(), "*确认登陆密码：");
	}
	/** 检查认证修改信息页面上的修改成功检查 */
	public static void checkUserAttestedInfoPageModifySuccess(SeleniumUtil seleniumUtil) {
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_NOTE2_ATTESTED).getText(), "恭喜您认证成功，现在您可以发布已认证的货源，货源信息更加可靠，司机会更加快速的找到您！");
	}
	//********************认证驳回的帮助类方法***********************************************
	/** 检查认证信息页面上的文本（预览界面） */
	public static void checkUserAttestRejectInfoPageText(SeleniumUtil seleniumUtil) {
		logger.info("Start checking addUserInfo page text");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_TITLE).getText(), "认证信息");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_NOTE1_ATTESTED).getText(), "您提交的认证资料被驳回，请参照驳回原因，修改后重新提交！");
		
		int size = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTING).size();
		String tabs[] = { "姓名", "联系电话", "身份证号", "公司名称", "公司地址", "证件照片" };
		for (int i = 0; i < size; i++) {
			String temp = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTING).get(i).getText().replaceAll(" ", "");
			temp = temp.substring(0, temp.lastIndexOf("："));
			seleniumUtil.isTextCorrect(temp, tabs[i]);
		}
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_CONTACT_ATTESTING).getText(), "如您有任何疑问，请电话联系我们：400-860-3060(周一至周日9:00---18:00)");
		logger.info("Check addUserInfo page text completed");
	}
	/** 检查认证信息页面上的文本（修改界面） */
	public static void checkUserAttestRejectInfoPageTextModify(SeleniumUtil seleniumUtil) {
		logger.info("Start checking addUserInfo page text");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_TITLE).getText(), "认证信息");
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_NOTE1_ATTESTED).getText(), "您提交的认证资料被驳回，请参照驳回原因，修改后重新提交！");
		
		int size = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTED).size();
		String tabs[] = { "姓名", "联系电话", "身份证号", "公司名称", "公司地址", " ", "证件照片" };
		for (int i = 0; i < size; i++) {
			if (i == 5)
				continue;
			String temp = seleniumUtil.findElementsBy(UserAttestedInfoPage.UAIP_TEXT_INFO_ATTESTED).get(i).getText().replaceAll(" ", "");
			temp = temp.substring(temp.indexOf("*") + 1, temp.lastIndexOf("："));
			seleniumUtil.isTextCorrect(temp, tabs[i]);
		}
		seleniumUtil.isTextCorrect(seleniumUtil.findElementBy(UserAttestedInfoPage.UAIP_TEXT_CONTACT_ATTESTEDREJECT).getText(), "如您有任何疑问，请电话联系我们：400-860-3060(周一至周日9:00---18:00)");
		logger.info("Check addUserInfo page text completed");
	}

}
