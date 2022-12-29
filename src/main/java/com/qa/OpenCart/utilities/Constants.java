package com.qa.OpenCart.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
	public static final int DEFAULT_TIME_OUT=5;
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final List<String> FOOTER_LIST=Arrays.asList("About Us",
			"Delivery Information","Privacy Policy","Terms & Conditions","Contact Us", "Returns","Site Map","Brands",
			"Gift Certificates","Affiliate","Specials","My Account","Order History",
			"Wish List","Newsletter");
	
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final List<String > ACCOUNT_PAGE_HEADER_LIST=Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final List<String> ACCOUNT_PAGE_SIDELIST=Arrays.asList("My Account","Edit Account","Password","Address Book",
			"Wish List","Order History","Downloads","Recurring payments","Reward Points","Returns","Transactions","Newsletter","Logout");
	public static final int MACKBOOK_PRODUCT_LIST_COUNT = 3;
	public static final int MACBOOK_AIR_IMAGE_COUNT = 4;
	public static final CharSequence REGISTER_ACCOUNT_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTERDATA_SHEET = "register";
	public static int iMAC_PRODUCT_LIST_COUNT=1;
	
	
	/**
	 * Excel path
	 */
	public static String TEST_DATA_SHEET_PATH = ".\\src\\test\\resource\\testData\\OpenCartTestData.xlsx";

}
