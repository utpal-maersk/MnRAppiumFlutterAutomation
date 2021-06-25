package testScripts;

import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.Test;
import utils.DataProviders;
import utils.DriverTestCase;

public class TestScripts extends DriverTestCase{
	
	private int count=0;
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify Interstitial screens.")
	public void TC_001(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				getInterstitialScreen().handleAlerts();
				
				//verify welcome text at intro screen
		//		getInterstitialScreen().verifyTitle(data.get("First Title Screen"));
				
				//verify animation image at introduction screen
		//		getInterstitialScreen().verifyImage();
				
				//verify loading text at introduction screen.
			//	getInterstitialScreen().verifyDescriptionText(data.get("First Screen Description"));
				
				//
				getInterstitialScreen().tapOnNextButton();
				
				//
			//	getInterstitialScreen().verifyTitle(data.get("Second Title Screen"));
				
				//
			//	getInterstitialScreen().verifyDescriptionText(data.get("Second Screen Description"));
				
				//
				getInterstitialScreen().tapOnNextButton();
				
				//
			//	getInterstitialScreen().verifyTitle(data.get("Third Screen Title"));
				
				//
			//	getInterstitialScreen().verifyDescriptionText(data.get("Third Screen Description"));
				
				//
				getInterstitialScreen().tapOnNextButton();
				
				getInterstitialScreen().tapOnNextButton();
			//	getInterstitialScreen().tapOnWelcomeToMyfinanceButton();
				
				Thread.sleep(3000);
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify landing screens.")
	public void TC_002(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//
				getLandingScreen().verifyTitleText(data.get("Landing Screen Title"));
				
				//
				getLandingScreen().verifySubLable(data.get("Landing Screen Sub Header"));
				
				//
				getLandingScreen().tapOnTopNavigationIconByName(data.get("Top Nav 2"));
				
				//
				getLandingScreen().tapOnTopNavigationIconByName(data.get("Top Nav 3"));
				
				//
				getLandingScreen().tapOnTopNavigationIconByName(data.get("Top Nav 4"));
				
				//
				getLandingScreen().tapOnTopNavigationIconByName(data.get("Top Nav 1"));
				
				//
				getLoginScreen().tapOnLoginButton();
				
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify login feature with invalid password")
	public void TC_003(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//verify title at login screen.
				getLoginScreen().verifyWelcomeTextAtLoginScreen(data.get("Login Screen Title"));
				
				//verify sub title text at login screen.
				getLoginScreen().verifySubTextAtLoginScreen(data.get("Login Screen Sub Title"));
				
				//
				getLoginScreen().enterUserName(data.get("Brand Label"), data.get("Username"));
				
				//
				getLoginScreen().enterPassword(data.get("Brand Label"), data.get("Password"));
				
				//
				getLoginScreen().tapOnLoginButton();
				
				//
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify login feature with invalid username.")
	public void TC_004(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//
				getLoginScreen().enterUserName(data.get("Brand Label"), data.get("Username"));
				
				//
				getLoginScreen().enterPassword(data.get("Brand Label"), data.get("Password"));
				
				//
				getLoginScreen().tapOnLoginButton();
				
				//
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify login feature with locked account.")
	public void TC_005(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//
				getLoginScreen().enterUserName(data.get("Brand Label"), data.get("Username"));
				
				//
				getLoginScreen().enterPassword(data.get("Brand Label"), data.get("Password"));
				
				//
				getLoginScreen().tapOnLoginButton();
				
				//
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
			
			e.printStackTrace();
		}
		
		count++;
	}
	
		@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify login flow with no customer associated")
	public void TC_006(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//verify title at login screen.
				getLoginScreen().verifyWelcomeTextAtLoginScreen(data.get("Login Screen Title"));
				
				//verify sub title text at login screen.
				getLoginScreen().verifySubTextAtLoginScreen(data.get("Login Screen Sub Title"));
				
				//verify to tab on maerskline brand icon.
				getLoginScreen().tapOnBrandIconByName(data.get("Brand"));
				
				//
				getLoginScreen().enterUserName(data.get("Brand Label"), data.get("Username"));
				
				//
				getLoginScreen().enterPassword(data.get("Brand Label"), data.get("Password"));
				
				//
				getLoginScreen().tapOnLoginButton();
				
				//
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
			
			e.printStackTrace();
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify login flow with the user who doesn't have access")
	public void TC_007(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//verify title at login screen.
				getLoginScreen().verifyWelcomeTextAtLoginScreen(data.get("Login Screen Title"));
				
				//verify sub title text at login screen.
				getLoginScreen().verifySubTextAtLoginScreen(data.get("Login Screen Sub Title"));
				
				//verify to tab on maerskline brand icon.
				getLoginScreen().tapOnBrandIconByName(data.get("Brand"));
				
				//
				getLoginScreen().enterUserName(data.get("Brand Label"), data.get("Username"));
				
				//
				getLoginScreen().enterPassword(data.get("Brand Label"), data.get("Password"));
				
				//
				getLoginScreen().tapOnLoginButton();
				
				//
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
			
			e.printStackTrace();
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify positive login flow with multiple customer codes")
	public void TC_008(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//verify title at login screen.
				getLoginScreen().verifyWelcomeTextAtLoginScreen(data.get("Login Screen Title"));
				
				//
				getLoginScreen().enterUserName(data.get("Brand Label"), data.get("Username"));
				
				//
				getLoginScreen().enterPassword(data.get("Brand Label"), data.get("Password"));
				
				//
				getLoginScreen().tapOnLoginButton();
				
				if (data.get("Associated Customer").trim().equalsIgnoreCase("MULTIPLE")) {
					
					//
					getCustomerScreen().verifySetCustomerTitle(data.get("Set Customer Title"));
					
					//
					getCustomerScreen().verifySubText(data.get("Sub Text"));
					
					//
					getCustomerScreen().selectCustomerCode(data.get("Customer Code"));
					
					//
					getCustomerScreen().handleBiometricPopup(getPropertyReader().readApplicationFile("BiometricDesc"),getPropertyReader().readApplicationFile("BiometricUserGuidance"));
					
					//
					getHomeScreen().verifyHomeScreenTitleText(data.get("Home Screen Title"), data.get("Home Screen Sub-Text"));
					
				}else {
					
					//
					getCustomerScreen().handleBiometricPopup(getPropertyReader().readApplicationFile("BiometricDesc"),getPropertyReader().readApplicationFile("BiometricUserGuidance"));
					
					//
					getHomeScreen().verifyHomeScreenTitleText(data.get("Home Screen Title"), data.get("Home Screen Sub-Text"));
				}
				
				//
				getAppBar().tapOnProfileIcon();
				
				//
				getAppBar().verifyScreenTitle(getPropertyReader().readApplicationFile("ProfileScreenTitle"));
				
				//
				getProfileScreen().tapOnLogOut();
				
				//
				getProfileScreen().confirmLogOut(getPropertyReader().readApplicationFile("logoutAlertMessage"));
				
				//
				getLandingScreen().verifyTitleText(data.get("Landing Screen Title"));
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify positive login flow with single customer code")
	public void TC_009(Hashtable<String,String> data){
		
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//verify title at login screen.
				getLoginScreen().verifyWelcomeTextAtLoginScreen(data.get("Login Screen Title"));
				
				//verify to tab on maerskline brand icon.
				getLoginScreen().tapOnBrandIconByName(data.get("Brand"));
				
				//
				getLoginScreen().enterUserName(data.get("Brand Label"), data.get("Username"));
				
				//
				getLoginScreen().enterPassword(data.get("Brand Label"), data.get("Password"));
				
				//
				getLoginScreen().tapOnLoginButton();
				
				if (data.get("Associated Customer").trim().equalsIgnoreCase("MULTIPLE")) {
					
					//
					getCustomerScreen().verifySetCustomerTitle(data.get("Set Customer Title"));
					
					//
					getCustomerScreen().verifySubText(data.get("Sub Text"));
					
					//
					getCustomerScreen().selectCustomerCode(data.get("Customer Code"));
					
					//
					getCustomerScreen().handleBiometricPopup(getPropertyReader().readApplicationFile("BiometricDesc"),getPropertyReader().readApplicationFile("BiometricUserGuidance"));
					
					//
					getDashboardScreen().verifyDashboardScreenTitle(getCustomerScreen().getCustomerName());
					
				}else {
					
					//
					getCustomerScreen().handleBiometricPopup(getPropertyReader().readApplicationFile("BiometricDesc"),getPropertyReader().readApplicationFile("BiometricUserGuidance"));
					
					//
					getDashboardScreen().verifyDashboardScreenTitle(data.get("Trading Name"));
				}
				
				//
				getLoginScreen().logOut();
				
				//
				getLoginScreen().verifyWelcomeTextAtLoginScreen(data.get("Login Screen Title"));
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	/*
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify to login with valid username and invalid password")
	public void TC_009(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//tap on brand icon as per data sheet
				getLoginScreen().tapOnBrandIconByName(data.get("Brand Name"));
				
				//verify username place holder and enter valid username into username field
				getLoginScreen().enterUserName(data.get("Username Place holder"), data.get("Username"));
				
				//verify password place holder and enter invalid password into password field
				getLoginScreen().enterPassword(data.get("Password Place holder"), data.get("Password"));
				
				//tap on login button
				getLoginScreen().tapOnLoginButton();
				
				//verify if appropriate error message is displayed
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify to login with invalid username and invalid password")
	public void TC_010(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//tap on brand icon as per data sheet
				getLoginScreen().tapOnBrandIconByName(data.get("Brand Name"));
				
				//verify username place holder and enter invalid username into username field
				getLoginScreen().enterUserName(data.get("Username Place holder"), data.get("Username"));
				
				//verify password place holder and enter invalid password into password field
				getLoginScreen().enterPassword(data.get("Password Place holder"), data.get("Password"));
				
				//tap on login button
				getLoginScreen().tapOnLoginButton();
				
				//verify if appropriate error message is displayed
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify to login with empty username and valid password")
	public void TC_011(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//tap on brand icon as per data sheet
				getLoginScreen().tapOnBrandIconByName(data.get("Brand Name"));
				
				//verify username place holder and enter empty username into username field
				getLoginScreen().enterUserName(data.get("Username Place holder"), data.get("Username"));
				
				//verify password place holder and enter valid password into password field
				getLoginScreen().enterPassword(data.get("Password Place holder"), data.get("Password"));
				
				//tap on login button
				getLoginScreen().tapOnLoginButton();
				
				//verify if appropriate error message is displayed
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify to login with valid username and empty password")
	public void TC_012(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//tap on brand icon as per data sheet
				getLoginScreen().tapOnBrandIconByName(data.get("Brand Name"));
				
				//verify username place holder and enter valid username into username field
				getLoginScreen().enterUserName(data.get("Username Place holder"), data.get("Username"));
				
				//verify password place holder and enter empty password into password field
				getLoginScreen().enterPassword(data.get("Password Place holder"), data.get("Password"));
				
				//tap on login button
				getLoginScreen().tapOnLoginButton();
				
				//verify if appropriate error message is displayed
				getLoginScreen().verifyErrorMessage(data.get("Error Message"));
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="regressionDataSet",description="Verify to login with valid username and valid password")
	public void TC_013(Hashtable<String,String> data){
		try {
			
			count++;
			
			if (platformExecutionFlag()) {
				
				System.out.println("Iteration >>>>>>>>>>>>>>>>>>> "+count);
				
				//Create the test case into HTML report
				setTestCaseDescription(testCase_name, data.get("Description"));
				
				//Check execution status of test case from excel sheet.
				checkExecution(data.get("Run Mode"), testCase_name);
				
				//tap on brand icon as per data sheet
				getLoginScreen().tapOnBrandIconByName(data.get("Brand Name"));
				
				//verify username place holder and enter valid username into username field
				getLoginScreen().enterUserName(data.get("Username Place holder"), data.get("Username"));
				
				//verify password place holder and enter valid password into password field
				getLoginScreen().enterPassword(data.get("Password Place holder"), data.get("Password"));
				
				//tap on login button
				getLoginScreen().tapOnLoginButton();
				
				
			}else{
				if (str_platformName_flag_Android.equalsIgnoreCase("Android")) {
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_Android);
					
				} else if(str_platformName_flag_iOS.equalsIgnoreCase("iOS")){
					
					throw new SkipException("Skipping the Test : "+testCase_name+" for platform "+str_platformName_flag_iOS);
					
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().contains("Skipping the Test")){
				
				throw new SkipException("Skipping the Test Case.... Run Mode set to 'No'");
				
			}
		}
		
		count++;
	}*/

}
