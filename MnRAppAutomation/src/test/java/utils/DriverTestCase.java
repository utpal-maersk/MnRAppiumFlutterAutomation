package utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ImmutableMap;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.helper.ProcessHelper;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screenHelpers.AppBarHelper;
import screenHelpers.DashboardScreenHelper;
import screenHelpers.DemoScreenHelper;
import screenHelpers.HomeScreenHelper;
import screenHelpers.InterstitialScreenHelper;
import screenHelpers.LandingScreenHelper;
import screenHelpers.LoginScreenHelper;
import screenHelpers.ProfileScreenHelper;
import screenHelpers.SetCustomerHelper;
import screensInterface.AppBar;
import screensInterface.DashboardScreen;
import screensInterface.DemoScreen;
import screensInterface.HomeScreen;
import screensInterface.InterstitialScreen;
import screensInterface.IntroductionScreen;
import screensInterface.LandingScreen;
import screensInterface.LoginScreen;
import screensInterface.ProfileScreen;
import screensInterface.SetCustomer;


public abstract class DriverTestCase {

	public static ThreadLocal<DesiredCapabilities> cap = new ThreadLocal<DesiredCapabilities>();
	public static DesiredCapabilities capabilities;

	public static ThreadLocal<AppiumDriver<MobileElement>> appiumDriver = new ThreadLocal<AppiumDriver<MobileElement>>();
	public static AppiumDriver<MobileElement> driver;

	public static ThreadLocal<FlutterFinder> f_finder = new ThreadLocal<FlutterFinder>();
	public static FlutterFinder find;

	public static ThreadLocal<AppiumDriverLocalService> appium = new ThreadLocal<AppiumDriverLocalService>();
	public static AppiumDriverLocalService appiumService;
	protected static ExtentReports extent;
	public static ThreadLocal<ExtentTest> exTest = new ThreadLocal<ExtentTest>();
	public static ExtentTest test;
	public Device device;
	public Process process;
	public static ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
	public static String selectedDomain = "";
	public String platformName = "";
	public String deviceNameBrowserstack="";
	public String platformVersionBrowserstack="";
	public static String str_platformName_flag_Android = "";
	public static String str_platformName_flag_iOS = "";
	public static String deviceName_iOS = "";
	public static String deviceName_android = "";
	public static String deviceVersion_android = "";
	public static String deviceVersion_ios = "";
	public static String deviceUDID_iOS = "";
	public static String deviceUDID_android = "";
	public static String appName_android = "";
	public static String appName_iOS = "";
	public static String maxTry = "";
	public static int int_maxTry = 0;
	public static String testCase_name = "";
	public static String brand_name="";
	private int testCounter=0;

	public static String str_testReportFolderName = "";
	public static int port = 0;
	public static String str_browserStackFlag = "";
	public static String str_LocalFlag = "";
	public static String str_local_iOS_PlatformFlag = "";
	public static String str_local_iOS_Simulator_PlatformFlag = "";
	public static String str_local_android_emulator_PlatformFlag = "";
	public static String str_local_android_PlatformFlag = "";
	public static String str_browserStack_iOS_PlatformFlag = "";
	public static String str_browserStack_android_PlatformFlag = "";
	public static String str_environment = "";
	public static List<String> list_temp = new ArrayList<String>();

	public static Boolean flag_execution = false;
	
	public IOSWorkAround iosWorkAround = new IOSWorkAround();

	// Declaring Class objects
	public static ThreadLocal<Constants> constants = new ThreadLocal<Constants>();
	public Constants obj_constant;


	public static ThreadLocal<PropertyReader> property = new ThreadLocal<PropertyReader>();
	public PropertyReader propertyReader;

	
	public static ThreadLocal<DemoScreen> demo = new ThreadLocal<DemoScreen>();
	public DemoScreen demoHelper;
	
	public static ThreadLocal<IntroductionScreen> intro = new ThreadLocal<IntroductionScreen>();
	public IntroductionScreen introHelper;

	public static ThreadLocal<InterstitialScreen> inters = new ThreadLocal<InterstitialScreen>();
	public InterstitialScreen interstitialHelper;
	
	public static ThreadLocal<LoginScreen> login = new ThreadLocal<LoginScreen>();
	public LoginScreen loginHelper;
	
	public static ThreadLocal<DashboardScreen> dashboard = new ThreadLocal<DashboardScreen>();
	public DashboardScreen dashboardHelper;
	
	public static ThreadLocal<SetCustomer> customer = new ThreadLocal<SetCustomer>();
	public SetCustomer setCustomer;
	
	public static ThreadLocal<LandingScreen> landing = new ThreadLocal<LandingScreen>();
	public LandingScreen landingScreen;
	
	public static ThreadLocal<ProfileScreen> profile = new ThreadLocal<ProfileScreen>();
	public ProfileScreen profileScreenHelper;
	
	public static ThreadLocal<AppBar> bar = new ThreadLocal<AppBar>();
	public AppBar appBarHelper;
	
	public static ThreadLocal<HomeScreen> home = new ThreadLocal<HomeScreen>();
	public HomeScreen homeScreenHelper;
	
	// Home Screen Helper setter & getter.

	public HomeScreen getHomeScreen() {

		return home.get();
	}

	public void setHomeScreen(HomeScreen homeHelper) {

		home.set(homeHelper);

	}
	
	//Introduction screen getter & setter
	public IntroductionScreen getIntro() {
		return intro.get();
	}

	public void setIntro(IntroductionScreen introscreen) {
		intro.set(introscreen);
	}
	
	//Interstitial screen getter & setter
	public InterstitialScreen getInterstitialScreen() {

		return inters.get();
	}

	public void setInterstitialScreen(InterstitialScreen intersHelper) {

		inters.set(intersHelper);

	}
	
	//Login screen getter & setter
	public LoginScreen getLoginScreen() {

		return login.get();
	}

	public void setLoginScreen(LoginScreen loginHelper) {

		login.set(loginHelper);

	}
	
	//Dashboard screen getter & setter
	public DashboardScreen getDashboardScreen() {

		return dashboard.get();
	}

	public void setDashboardScreen(DashboardScreen dashboardHelper) {

		dashboard.set(dashboardHelper);

	}
	
	//SetCustomer screen getter & setter
		public SetCustomer getCustomerScreen() {

			return customer.get();
		}

		public void setCustomerScreen(SetCustomer customerScreen) {

			customer.set(customerScreen);

		}
	
	//Landing Screen getter & setter
	
	public LandingScreen getLandingScreen() {
		
		return landing.get();
	}	
	
	public void setLandingScreen(LandingScreen land) {
		
		landing.set(land);
	}
	
	public ProfileScreen getProfileScreen() {
		
		return profile.get();
	}	
	
	public void setProfileScreen(ProfileScreen profile_temp) {
		
		profile.set(profile_temp);
	}
	
	public AppBar getAppBar() {
		
		return bar.get();
	}	
	
	public void setAppBar(AppBar appBar) {
		
		bar.set(appBar);
	}

	// Property Reader Getter & Setter
	public PropertyReader getPropertyReader() {

		return property.get();
	}

	public void setPropertyReader(PropertyReader propertyreader) {

		property.set(propertyreader);

	}

	// Appium Server Setter & Getter
	public AppiumDriverLocalService getAppiumServer() {

		return appium.get();
	}

	public void setAppiumServer(AppiumDriverLocalService appiumserver) {

		appium.set(appiumserver);

	}
	
	public Constants getConstants() {

		return constants.get();
	}

	public void setConstants(Constants cons) {

		constants.set(cons);

	}

	@BeforeSuite
	public void createTestRun() {
		try {
			
			 //System.getProperty("BrandName");
			brand_name = "maerskline";
			
			System.out.println("Selected brand name >>>>> "+brand_name);
			
			str_environment = excel.getCellData("TestSet", "Environment",2).trim();
			
			selectedDomain = excel.getCellData("TestSet", "Domain",2).trim();
			
			if (str_environment.equalsIgnoreCase("BROWSERSTACK")) {
				
				str_local_iOS_PlatformFlag = excel.getCellData("TestSet", "iOS", 2).trim();
				 System.out.println("Browserstack iOS flag: " +str_local_iOS_PlatformFlag);

				 str_local_android_PlatformFlag = excel.getCellData("TestSet","Android", 2).trim();
				 System.out.println("Browserstack android flag: " +str_local_android_PlatformFlag);
				
			} else if (str_environment.equalsIgnoreCase("LOCAL")) {
				
				str_local_iOS_PlatformFlag = excel.getCellData("TestSet", "iOS", 2).trim();
				
				// System.out.println("Local iOS flag: " +str_local_iOS_PlatformFlag);

				str_local_iOS_Simulator_PlatformFlag = excel.getCellData("TestSet", "iOS Simulator", 2).trim();
				
				// System.out.println("Local iOS simulator flag: " +str_local_iOS_Simulator_PlatformFlag);

				str_local_android_emulator_PlatformFlag = excel.getCellData("TestSet", "Android Emulator",2).trim();
				
				// System.out.println("Local Android emulator flag:"+str_local_android_emulator_PlatformFlag);

				str_local_android_PlatformFlag = excel.getCellData("TestSet","Android", 2).trim();
				
				//System.out.println("Local android flag: " +str_local_android_PlatformFlag);

				deviceName_iOS = excel.getCellData("TestSet", "iOS Device", 2).trim().split(":")[0];
				
				 System.out.println("iOS Device name: " + deviceName_iOS);

				deviceName_android = excel.getCellData("TestSet", "Android Device", 2).trim().split(":")[0];
				
				System.out.println("Android Device name: " + deviceName_android);


			//	DriverTestCase.appName_android = System.getProperty("AppName_android");
				
				DriverTestCase.appName_android = "app-preprod-debug.apk";

			//	DriverTestCase.appName_iOS = System.getProperty("AppName_iOS");
				
				DriverTestCase.appName_iOS = "Runner.app";

				System.out.println("App name android: " + appName_android);

				System.out.println("App name iOS: " + appName_iOS);

			}
			
			

			try {
				
				System.err.println("Folder size >>> "+list_temp);
				
				if(list_temp.size() > 5) {
				for (String folderName : getFolderName_TestReports()) {

					System.out.println("Folder Name: " + folderName);

					clearFolder_TestReportFolder(folderName);

				}

				clearFolder("TestReports");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			str_testReportFolderName = createFolder_byGivenName(selectedDomain + "_");
			extent = ExtentManager.GetExtent();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@BeforeTest
	public void setReportingConfig() {

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean platformExecutionFlag() {

		flag_execution = false;
		if (str_platformName_flag_Android.equalsIgnoreCase("Android")
				&& str_local_android_PlatformFlag.equalsIgnoreCase("Yes")) {

			flag_execution = true;
		}

		if (str_platformName_flag_Android.equalsIgnoreCase("Android")
				&& str_local_android_emulator_PlatformFlag.equalsIgnoreCase("Yes")) {

			flag_execution = true;
		}

		if (str_platformName_flag_iOS.equalsIgnoreCase("iOS") && str_local_iOS_PlatformFlag.equalsIgnoreCase("Yes")) {

			flag_execution = true;
		}

		if (str_platformName_flag_Android.equalsIgnoreCase("Android")
				&& str_browserStack_android_PlatformFlag.equalsIgnoreCase("Yes")) {

			flag_execution = true;
		}

		if (str_platformName_flag_iOS.equalsIgnoreCase("iOS")
				&& str_browserStack_iOS_PlatformFlag.equalsIgnoreCase("Yes")) {

			flag_execution = true;
		}

		if (str_platformName_flag_iOS.equalsIgnoreCase("iOS")
				&& str_local_iOS_Simulator_PlatformFlag.equalsIgnoreCase("Yes")) {

			System.err.println("<<<<<<<< inside simulator if >>>>>>>>");
			flag_execution = true;
		}

		return flag_execution;
	}

	@Parameters({"platFormName","portNumber","DeviceName","DeviceVersion"})
	@BeforeClass
	public void invokeTheTestSet(String platFormName,String portNumber,String DeviceName, String DeviceVersion) {

		try {
			
			platformName = platFormName;

			if (str_environment.equalsIgnoreCase("LOCAL")) {
				if (platFormName.equalsIgnoreCase("iOS")) {
					// create log folder
					clearFolder("AppiumLogs");
				}
				DeviceInfo deviceInfo = null;
				if (str_local_android_emulator_PlatformFlag.equalsIgnoreCase("Yes")) {
					if (this.platformName.equalsIgnoreCase("Android")) {

						DriverTestCase.deviceUDID_android = excel.getCellData("TestSet", "Android Device", 2)
								.split(":")[1].trim();
						DriverTestCase.deviceVersion_android = excel.getCellData("TestSet", "Android Device Version",
								2);

					}
				}

				else if (str_local_android_PlatformFlag.equalsIgnoreCase("Yes")) {
					if (platFormName.equalsIgnoreCase("Android")) {
						deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
						device = deviceInfo.getFirstDevice();
						DriverTestCase.deviceUDID_android = device.getUniqueDeviceID().trim();

						//	DriverTestCase.deviceUDID_android = excel.getCellData("TestSet", "Local - Android Device", 2).split(":")[1];

						//	DriverTestCase.deviceUDID_android = "192.168.1.7:5555";

						System.out.println("Device UDID: " + DriverTestCase.deviceUDID_android);

						System.out.println("Platform version: " + device.getProductVersion().trim());

						DriverTestCase.deviceVersion_android = device.getProductVersion().trim();

						//	DriverTestCase.deviceVersion_android = excel.getCellData("TestSet", "Local - Android Device Version", 2);

						System.out.println("Device Version: " + DriverTestCase.deviceVersion_android);
					}
				}
				if (str_local_iOS_PlatformFlag.equalsIgnoreCase("Yes")) {
					if (platFormName.equalsIgnoreCase("iOS")) {
						deviceInfo = new DeviceInfoImpl(DeviceType.IOS);
						device = deviceInfo.getFirstDevice();
						DriverTestCase.deviceUDID_iOS = device.getUniqueDeviceID().trim();
						System.out.println("Device UDID: " + DriverTestCase.deviceUDID_iOS);
						DriverTestCase.deviceVersion_ios = device.getProductVersion().trim();
						System.out.println("Device Version: " + DriverTestCase.deviceVersion_ios);
					}
				} else if (str_local_iOS_Simulator_PlatformFlag.equalsIgnoreCase("Yes")) {
					if (platFormName.equalsIgnoreCase("iOS")) {

						deviceInfo = new DeviceInfoImpl(DeviceType.IOSSIMULATOR);
						/*DriverTestCase.deviceUDID_iOS = device.getUniqueDeviceID().trim();
						System.out.println("Device UDID: " + DriverTestCase.deviceUDID_iOS);
						DriverTestCase.deviceVersion_ios = device.getProductVersion().trim();
						System.out.println("Device Version: " + DriverTestCase.deviceVersion_ios);*/
						DriverTestCase.deviceUDID_iOS = excel.getCellData("TestSet", "iOS Device", 2).split(":")[1]
								.trim();
						System.out.println("Device UDID: " + DriverTestCase.deviceUDID_iOS);

						DriverTestCase.deviceVersion_ios = excel.getCellData("TestSet", "iOS Device Version", 2);
						System.out.println("Device Version: " + DriverTestCase.deviceVersion_ios);
					}
				} 
			}
		//	if (str_environment.equalsIgnoreCase("BROWSERSTACK")) {
				
				deviceNameBrowserstack=DeviceName;
				platformVersionBrowserstack=DeviceVersion;
		//	}
			obj_constant = new Constants();
			setConstants(obj_constant);
			
			getConstants().brandName = System.getProperty("BrandName");
			startServer(DriverTestCase.str_environment, platFormName,portNumber,DeviceName,DeviceVersion);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	@BeforeMethod
	public void createTestDescription(Method method) {

		testCase_name = method.getName();
	}

	public void setTestCaseDescription(String testCase_name, String test_description) {
		test = ExtentManager.createTest(testCase_name + " - " + test_description, " is tested on " + this.deviceNameBrowserstack
				+ " with version: "+this.platformVersionBrowserstack + " >> Test Description - " + "<b>" + test_description + "</b>" + "");

		setExtentTest(test);
	}

	@AfterMethod
	public void updateTestExecutionStatus() {

	}

	@AfterClass
	public void afterClass() throws IOException {

		if (str_local_android_emulator_PlatformFlag.equalsIgnoreCase("Yes")
				|| str_local_android_PlatformFlag.equalsIgnoreCase("Yes")
						&& str_platformName_flag_Android.equalsIgnoreCase("Android")) {
			
			propertyReader = null;
			

			try {
				
			} catch (Exception e) {

			}

			if (getDriver() != null) {

				getDriver().quit();

				System.err.println("Kill appium driver... >>>>>>");
			}
		}

		if (str_local_iOS_PlatformFlag.equalsIgnoreCase("Yes")
				|| str_local_iOS_Simulator_PlatformFlag.equalsIgnoreCase("Yes")
						&& str_platformName_flag_iOS.equalsIgnoreCase("iOS")) {
			
			/*String base64String = ((CanRecordScreen)getDriver()).stopRecordingScreen();
			byte[] data = Base64.decodeBase64(base64String);
			String
			destinationPath= System.getProperty("user.dir")+ "Recording/testExecution.mp4";
			Path path = Paths.get(destinationPath);
			Files.write(path, data);*/
			
			if (getDriver() != null) {
				getDriver().quit();

				System.err.println("Kill appium driver... >>>>>>");
			}
		}

		//getAppiumServer().stop();

	}

	@AfterTest
	public void afterAllTest() {

	}

	@AfterSuite
	public void afterTestSuite() {

		ExtentManager.GetExtent().flush();

		try {

			process = ProcessHelper.runTimeExec("/usr/bin/killall -KILL node");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void startServer(String execution_env, String platformName,String portNumber, String deviceName, String platformVersion) {
		try {
			switch (execution_env.toUpperCase()) {
			case "LOCAL":

				switch (platformName.toUpperCase()) {
				case "ANDROID":

					if (str_local_android_PlatformFlag.equalsIgnoreCase("Yes")
							|| str_local_android_emulator_PlatformFlag.equalsIgnoreCase("Yes")) {
						
						propertyReader = new PropertyReader("android-Capabilities");
						
						appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
								.usingDriverExecutable(new File("/usr/local/bin/node"))
								.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
								.withArgument(GeneralServerFlag.LOCAL_TIMEZONE).usingPort(Integer.valueOf(portNumber))
								.withLogFile(new File(System.getProperty("user.dir") + "/AppiumLogs/app_android.logs")));
						setAppiumServer(appiumService);
						getAppiumServer().start();
						
						File classpathRoot = new File(System.getProperty("user.dir")+propertyReader.readApplicationFile("app_path"));
						File app = new File(classpathRoot.getCanonicalPath(), propertyReader.readApplicationFile("app_Name"));
						
						setConstants(obj_constant);
						getConstants().androidLocatorFileName = "Android_locators.xml";
						
						capabilities = new DesiredCapabilities();
						setCapabilities(capabilities);
						getCapabilities().setCapability("app", app.getAbsolutePath());
						getCapabilities().setCapability(MobileCapabilityType.AUTOMATION_NAME, propertyReader.readApplicationFile("AutomationName"));
						getCapabilities().setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
						getCapabilities().setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
						
						getCapabilities().setCapability(MobileCapabilityType.UDID, DriverTestCase.deviceUDID_android);
						getCapabilities().setCapability("platformVersion", DriverTestCase.deviceVersion_android);
						getCapabilities().setCapability("deviceName", deviceName_android);
						getCapabilities().setCapability("retryBackoffTime", 20000);
						
						if(propertyReader.readApplicationFile("appInstallationFlag").equalsIgnoreCase("Yes")){
						getCapabilities().setCapability("noReset", false);
						getCapabilities().setCapability("fullReset", true);
						}else if(propertyReader.readApplicationFile("appInstallationFlag").equalsIgnoreCase("No")){
							getCapabilities().setCapability("noReset", true);
							getCapabilities().setCapability("fullReset", false);
						}
						driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:"+portNumber+"/wd/hub"),
								getCapabilities());

						setAppiumDriver(driver);
						find = new FlutterFinder(getDriver());
						setFlutterFinder(find);
						getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						platformName = "Android";
						str_platformName_flag_Android = "Android";
						
						getConstants().PLATFORMNAME = platformName;
						
						setPropertyReader(propertyReader);
						
						interstitialHelper = new InterstitialScreenHelper(getDriver(), getFinder());
						setInterstitialScreen(interstitialHelper);
						
						loginHelper = new LoginScreenHelper(getDriver(), getFinder());
						setLoginScreen(loginHelper);
						
						setCustomer = new SetCustomerHelper(getDriver(), getFinder());
						setCustomerScreen(setCustomer);
						
						dashboardHelper = new DashboardScreenHelper(getDriver(), getFinder());
						setDashboardScreen(dashboardHelper);
						
						landingScreen = new LandingScreenHelper(getDriver(),getFinder());
						setLandingScreen(landingScreen);
						
						profileScreenHelper = new ProfileScreenHelper(getDriver(), getFinder());
						setProfileScreen(profileScreenHelper);
						
						homeScreenHelper = new HomeScreenHelper(getDriver(), getFinder());
						setHomeScreen(homeScreenHelper);
						
						appBarHelper = new AppBarHelper(getDriver(), getFinder());
						setAppBar(appBarHelper);

					}
					break;

				case "IOS":
					if (str_local_iOS_PlatformFlag.equalsIgnoreCase("Yes")
							|| str_local_iOS_Simulator_PlatformFlag.equalsIgnoreCase("Yes")) {
						
						System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						
						propertyReader = new PropertyReader("iOS-Capabilities");
						
						appiumService = AppiumDriverLocalService.buildService(
								new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
										.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
										.withArgument(GeneralServerFlag.LOCAL_TIMEZONE).usingPort(Integer.valueOf(portNumber)).withLogFile(
												new File(System.getProperty("user.dir") + "/AppiumLogs/app_ios.logs")));

						setAppiumServer(appiumService);
						getAppiumServer().start();

						File classpathRoot_iOS = new File(System.getProperty("user.dir")+propertyReader.readApplicationFile("app_path"));
						File app_iOS = new File(classpathRoot_iOS.getCanonicalPath(), propertyReader.readApplicationFile("app_Name"));
						
						setConstants(obj_constant);
						getConstants().iOSLocatorFileName = "iOS_Locators.xml";
						
						capabilities = new DesiredCapabilities();
						setCapabilities(capabilities);
						getCapabilities().setCapability("app", app_iOS.getAbsolutePath());

						getCapabilities().setCapability(MobileCapabilityType.AUTOMATION_NAME, propertyReader.readApplicationFile("AutomationName"));
						getCapabilities().setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
						getCapabilities().setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, propertyReader.readApplicationFile("wdaLocalPort"));

						getCapabilities().setCapability(MobileCapabilityType.UDID, DriverTestCase.deviceUDID_iOS);
						
						getCapabilities().setCapability("platformVersion", DriverTestCase.deviceVersion_ios);
						getCapabilities().setCapability(MobileCapabilityType.DEVICE_NAME, deviceName_iOS);
						//getCapabilities().setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
						
						if(propertyReader.readApplicationFile("appInstallationFlag").equalsIgnoreCase("Yes")){
							getCapabilities().setCapability("noReset", false);
							getCapabilities().setCapability("fullReset", true);
							}else if(propertyReader.readApplicationFile("appInstallationFlag").equalsIgnoreCase("No")){
								getCapabilities().setCapability("noReset", true);
								getCapabilities().setCapability("fullReset", false);
							}
						
						getCapabilities().setCapability("retryBackoffTime", 20000);
						
						getCapabilities().setCapability(IOSMobileCapabilityType.UPDATE_WDA_BUNDLEID,propertyReader.readApplicationFile("wdaUpdatedBundleID"));
						
						/*if (str_local_iOS_PlatformFlag.equalsIgnoreCase("Yes")) {
							iosWorkAround.start();
						}*/
						
						driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:"+portNumber+"/wd/hub"),
								getCapabilities());
						
						setAppiumDriver(driver);
						
					//	((CanRecordScreen)getDriver()).startRecordingScreen(new IOSStartScreenRecordingOptions());
						
						find = new FlutterFinder(getDriver());
						setFlutterFinder(find);
						platformName = "iOS";
						str_platformName_flag_iOS = "iOS";
						getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						
						setPropertyReader(propertyReader);
						
						getConstants().PLATFORMNAME = platformName;
						
						homeScreenHelper = new HomeScreenHelper(getDriver(), getFinder());
						setHomeScreen(homeScreenHelper);
						
						interstitialHelper = new InterstitialScreenHelper(getDriver(), getFinder());
						setInterstitialScreen(interstitialHelper);
						
						loginHelper = new LoginScreenHelper(getDriver(), getFinder());
						setLoginScreen(loginHelper);
						
						setCustomer = new SetCustomerHelper(getDriver(), getFinder());
						setCustomerScreen(setCustomer);
						
						dashboardHelper = new DashboardScreenHelper(getDriver(), getFinder());
						setDashboardScreen(dashboardHelper);
						
						getConstants().brandName = System.getProperty("BrandName");
						
						landingScreen = new LandingScreenHelper(getDriver(),getFinder());
						setLandingScreen(landingScreen);
						
						profileScreenHelper = new ProfileScreenHelper(getDriver(), getFinder());
						setProfileScreen(profileScreenHelper);
						
						appBarHelper = new AppBarHelper(getDriver(), getFinder());
						setAppBar(appBarHelper);
						
					}
					break;

				default:
					break;
				}

				break;

			case "BROWSERSTACK":
				
				switch (platformName.toUpperCase()) {
				case "ANDROID":
					
					
					if (str_local_android_PlatformFlag.equalsIgnoreCase("Yes")
							|| str_local_android_emulator_PlatformFlag.equalsIgnoreCase("Yes")) {
						
						setConstants(obj_constant);
						getConstants().androidLocatorFileName = "Android_locators.xml";
						
						capabilities = new DesiredCapabilities();
						setCapabilities(capabilities);	
						
						// Set your access credentials
					      getCapabilities().setCapability("browserstack.user", "ahmerhashmi5");
					      getCapabilities().setCapability("browserstack.key", "6HzKVfB6JTooTK4gFp4s");
					      
					      //bs://11c23328b2f432d36838ba5c972415e4d18c5361
					      // Set URL of the application under test
					      getCapabilities().setCapability("app", "bs://b54585d2a06c112e01cc60af73efd338384eb57e");
					      
					      getCapabilities().setCapability("browserstack.appium_version", "1.21.0");
					      getCapabilities().setCapability("automationName", "Flutter");
					      
					      // Specify device and os_version for testing
					      getCapabilities().setCapability("device", deviceName);
					      getCapabilities().setCapability("os_version", platformVersion);
					        
					      // Set other BrowserStack capabilities
					      getCapabilities().setCapability("project", "Shipment Rewrite");
					      getCapabilities().setCapability("build", "Android Build");
					      getCapabilities().setCapability("name", deviceName);
					        
					      
					      // Initialise the remote Webdriver using BrowserStack remote URL
					      // and desired capabilities defined above
					        driver = new AndroidDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), getCapabilities());
					        
					        setAppiumDriver(driver);
							find = new FlutterFinder(getDriver());
							setFlutterFinder(find);
							getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							platformName = "Android";
							str_platformName_flag_Android = "Android";
							
							getConstants().PLATFORMNAME = platformName;
							
							setPropertyReader(propertyReader);
							
							homeScreenHelper = new HomeScreenHelper(getDriver(), getFinder());
							setHomeScreen(homeScreenHelper);
							
							interstitialHelper = new InterstitialScreenHelper(getDriver(), getFinder());
							setInterstitialScreen(interstitialHelper);
							
							loginHelper = new LoginScreenHelper(getDriver(), getFinder());
							setLoginScreen(loginHelper);
							
							setCustomer = new SetCustomerHelper(getDriver(), getFinder());
							setCustomerScreen(setCustomer);
							
							dashboardHelper = new DashboardScreenHelper(getDriver(), getFinder());
							setDashboardScreen(dashboardHelper);
							
							landingScreen = new LandingScreenHelper(getDriver(),getFinder());
							setLandingScreen(landingScreen);
							
							profileScreenHelper = new ProfileScreenHelper(getDriver(), getFinder());
							setProfileScreen(profileScreenHelper);
							
							appBarHelper = new AppBarHelper(getDriver(), getFinder());
							setAppBar(appBarHelper);

					}
					break;

				case "IOS":

					if (str_local_iOS_PlatformFlag.equalsIgnoreCase("Yes")
							|| str_local_iOS_Simulator_PlatformFlag.equalsIgnoreCase("Yes")) {
						
						System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						
						propertyReader = new PropertyReader("iOS-Capabilities");
						
						setConstants(obj_constant);
						getConstants().iOSLocatorFileName = "iOS_Locators.xml";
						
						capabilities = new DesiredCapabilities();
						setCapabilities(capabilities);	
						
						getCapabilities().setCapability("browserstack.user", "ahmerhashmi5");
					    getCapabilities().setCapability("browserstack.key", "6HzKVfB6JTooTK4gFp4s");
					      
					      //bs://11c23328b2f432d36838ba5c972415e4d18c5361
					      // Set URL of the application under test
					    getCapabilities().setCapability("app", "bs://e3228e85e852c72bfed190ae1f85cb86966859fc");
					      
					    getCapabilities().setCapability("browserstack.appium_version", "1.21.0");
					    getCapabilities().setCapability("automationName", "Flutter");
					      
					      // Specify device and os_version for testing
					    getCapabilities().setCapability("device", deviceName);
					    getCapabilities().setCapability("os_version", platformVersion);
					        
					      // Set other BrowserStack capabilities
					      getCapabilities().setCapability("project", "Shipment Rewrite");
					      getCapabilities().setCapability("build", "iOS Build");
					      getCapabilities().setCapability("name", deviceName);
						
					      driver = new IOSDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"),
									getCapabilities());
					      
					      setAppiumDriver(driver);
							
							//	((CanRecordScreen)getDriver()).startRecordingScreen(new IOSStartScreenRecordingOptions());
								
					      find = new FlutterFinder(getDriver());
					      setFlutterFinder(find);
					      platformName = "iOS";
					      str_platformName_flag_iOS = "iOS";
					      getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
								
					      setPropertyReader(propertyReader);
								
					      getConstants().PLATFORMNAME = platformName;
								
					      homeScreenHelper = new HomeScreenHelper(getDriver(), getFinder());
					      setHomeScreen(homeScreenHelper);
								
					      interstitialHelper = new InterstitialScreenHelper(getDriver(), getFinder());
					      setInterstitialScreen(interstitialHelper);
								
					      loginHelper = new LoginScreenHelper(getDriver(), getFinder());
					      setLoginScreen(loginHelper);
								
					      setCustomer = new SetCustomerHelper(getDriver(), getFinder());
					      setCustomerScreen(setCustomer);
								
					      dashboardHelper = new DashboardScreenHelper(getDriver(), getFinder());
					      setDashboardScreen(dashboardHelper);
								
					      getConstants().brandName = System.getProperty("BrandName");
								
					      landingScreen = new LandingScreenHelper(getDriver(),getFinder());
					      setLandingScreen(landingScreen);
					      
					      profileScreenHelper = new ProfileScreenHelper(getDriver(), getFinder());
					      setProfileScreen(profileScreenHelper);
					      
					      appBarHelper = new AppBarHelper(getDriver(), getFinder());
							setAppBar(appBarHelper);
						
					}
					break;

				default:
					break;
				}

				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		return formattedDate.replace(":", "_").replace("/", "_");
	}

	public static String createFolder_byGivenName(String str_FolderName) {
		String folderName = "";
		try {
			String dynamicFolderName = str_FolderName + "TestReport_" + getDate();
			File file = new File(System.getProperty("user.dir") + "/TestReports/" + dynamicFolderName);
			if (!file.exists()) {
				if (file.mkdir()) {
					folderName = dynamicFolderName;
					
				} else {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folderName;
	}

	public void skipTestCase(String testCaseName) {

		test.skip(testCaseName + " got skipped");
	}

	public static String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

	public static String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}

	public void clearFolder(String folderName) {
		try {
			String path = getPath();
			File directory = new File(path + "/" + folderName);
			for (File f : directory.listFiles())
				f.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clearFolder_TestReportFolder(String folderName) {
		String path = getPath();
		File directory = new File(path + "/TestReports/" + folderName);
		for (File f : directory.listFiles())
			f.delete();
	}

	public String[] getFolderName_TestReports() {

		String[] str_arr = null;

		try {

			File file = new File(System.getProperty("user.dir") + "/TestReports/");
			String[] directories = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File current, String name) {
					return new File(current, name).isDirectory();
				}
			});

			str_arr = directories;
			
			System.err.println("Folder name >>> "+str_arr);
			
			for (String x : str_arr) {
				
				list_temp.add(x);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str_arr;

	}

	public String getDartObservatoryPort(String fileName) {

		String portNumber = "";
		String service_url = "";
		List<String> lines = Collections.emptyList();
		try {

			Thread.sleep(2000);

			lines = Files.readAllLines(Paths.get(getPath() + "/AppiumLogs/" + fileName + ".logs"),
					StandardCharsets.UTF_8);

			for (String string : lines) {
				if (string.contains("Connecting to Dart Observatory")) {

					service_url = string;
					break;

				}
			}

			portNumber = service_url.split("//")[1].split(":")[1].split("/")[0];
			System.err.println("Service URL: " + portNumber);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return portNumber;

	}

	public AppiumDriver<MobileElement> getDriver() {

		return appiumDriver.get();
	}

	public void setAppiumDriver(AppiumDriver<MobileElement> driver) {

		appiumDriver.set(driver);

	}

	public DesiredCapabilities getCapabilities() {

		return cap.get();
	}

	public void setCapabilities(DesiredCapabilities cp) {

		cap.set(cp);

	}

	public FlutterFinder getFinder() {

		return f_finder.get();
	}

	public void setFlutterFinder(FlutterFinder ff) {

		f_finder.set(ff);

	}

	public void setExtentTest(ExtentTest et) {

		exTest.set(et);
	}

	public ExtentTest getExtTest() {

		return exTest.get();
	}

	public void checkExecution(String dataRunMode, String testCaseName) {
		if (dataRunMode.equalsIgnoreCase(Constants.RUNMODE_NO)) {

			getExtTest().skip("Run Mode is set to 'No'.. Test Case got SKIPPED.");
			throw new SkipException("Skipping the Test : " + testCaseName + " as the Runmode of Test Data is No");
		}

	}

}

class IOSWorkAround extends Thread {

	public Process process;

	public void run() {

		String portNumber = "";
		String service_url = "";
		List<String> lines = Collections.emptyList();
		try {

			Thread.sleep(30000);

			lines = Files.readAllLines(Paths.get(DriverTestCase.getPath() + "/AppiumLogs/app_ios.logs"),
					StandardCharsets.UTF_8);

			for (String string : lines) {
				if (string.contains("Connecting to Dart Observatory")) {

					service_url = string;
					break;

				}
			}

			portNumber = service_url.split("//")[1].split(":")[1].split("/")[0];
			System.err.println("Service URL: " + portNumber);
			process = ProcessHelper.runTimeExec("iproxy " + portNumber + " " + portNumber);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}

