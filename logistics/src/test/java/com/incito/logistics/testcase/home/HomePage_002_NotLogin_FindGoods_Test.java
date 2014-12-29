package com.incito.logistics.testcase.home;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.incito.logistics.pages.HomePage;
import com.incito.logistics.pages.LoginPage;
import com.incito.logistics.pages.pageshelper.HomePageHelper;
import com.incito.logistics.pages.pageshelper.LoginPageHelper;
import com.incito.logistics.plugins.father.HomeNotLoginFather;
/*
*@author  xy-incito-wy
*@Description 主页未登录的情况下 点击“找货源” 应该弹出登陆界面
*
* */
public class HomePage_002_NotLogin_FindGoods_Test extends HomeNotLoginFather {
	@Test
	public void findGoods(ITestContext context) {
		HomeNotLoginFather.homeNotLoginParpare(context, seleniumUtil);
		HomePageHelper.enterPage(seleniumUtil, HomePage.HP_LINK_FINDGOODS);
		LoginPageHelper.waitLoginPageToLoad(timeOut, seleniumUtil);
		LoginPageHelper.checkLoginPageText(seleniumUtil);
		LoginPageHelper.enterPage(seleniumUtil, LoginPage.LP_BUTTON_CLOSE);

	}

}
