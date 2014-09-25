package com.incito.logistics.testcase.adduserinfo;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.incito.logistics.base.BaseParpare;
import com.incito.logistics.pages.AddUserInfoPage;
import com.incito.logistics.pages.HomePage;
import com.incito.logistics.pages.pageshelper.AddUserInfoPagerHelper;
import com.incito.logistics.pages.pageshelper.HomePageHelper;
import com.incito.logistics.pages.pageshelper.LoginPageHelper;
import com.incito.logistics.util.PropertiesDataProvider;

/**
 * @author xy-incito-wk
 * @Description 填写信息联系方式和公司地址提交
 *
 * */
public class AddUserInfoPage_17_Fail_CompanyAddress_Text extends BaseParpare {
	@Test
	public void addUserInfoPageFailCompanyAddress(ITestContext context) {
		String configFilePath = String.valueOf(context.getCurrentXmlTest().getParameter("userInfoPath"));
		int timeOut = Integer.valueOf(context.getCurrentXmlTest().getParameter("timeOut"));
		String register_username = PropertiesDataProvider.getTestData(configFilePath, "register_username");
		String password = PropertiesDataProvider.getTestData(configFilePath, "password");
		By[] bys = { AddUserInfoPage.AUIP_INPUT_NAME, AddUserInfoPage.AUIP_INPUT_TEL, AddUserInfoPage.AUIP_INPUT_COMPANY, AddUserInfoPage.AUIP_INPUT_IDCARD, AddUserInfoPage.AUIP_INPUT_ADDRESS };
		String add_company = PropertiesDataProvider.getTestData(configFilePath, "add_company");
		String add_address = PropertiesDataProvider.getTestData(configFilePath, "add_address");

		HomePageHelper.waitHomePageToLoad(timeOut, seleniumUtil);
		HomePageHelper.enterPage(seleniumUtil, HomePage.HP_BUTTON_LOGIN);
		LoginPageHelper.login(seleniumUtil, register_username, password);
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_BUTTON_FREESEND);
		HomePageHelper.enterPage(seleniumUtil, HomePage.HP_BUTTON_FREESEND);
		AddUserInfoPagerHelper.waitAddUserInfoPageToLoad(timeOut, seleniumUtil);
		for (By by : bys) {
			seleniumUtil.clear(seleniumUtil.findElementBy(by));
		}
		seleniumUtil.selectByIndex(AddUserInfoPage.AUIP_SELECT_PROVINCE, 0);
		
		seleniumUtil.type(seleniumUtil.findElementBy(AddUserInfoPage.AUIP_INPUT_COMPANY), add_company);
		seleniumUtil.address(AddUserInfoPage.AUIP_SELECT_PROVINCE, "1800", AddUserInfoPage.AUIP_SELECT_CITY, "1806", AddUserInfoPage.AUIP_SELECT_REGION, "180602", AddUserInfoPage.AUIP_INPUT_ADDRESS, add_address);
		AddUserInfoPagerHelper.enterPage(seleniumUtil, AddUserInfoPage.AUIP_BUTTON_SUBMIT);
		AddUserInfoPagerHelper.checkAddUserInfoPrompt_CompanyAdress(timeOut, seleniumUtil);
	}

}
