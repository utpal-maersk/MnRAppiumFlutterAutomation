package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pro.truongsinh.appium_flutter.FlutterFinder;
import pro.truongsinh.appium_flutter.finder.FlutterElement;
import utils.DriverTestCase;

public abstract class DriverHelper extends DriverTestCase {

	public Boolean flag_Go = false;
	public Boolean flag_Go_textPresent = false;
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	AppiumDriver<MobileElement> driver;
	FlutterFinder find;

	public DriverHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		this.driver = driver;
		this.find = find;

	}

	/**
	 * It will return the FlutterElement based on the input.
	 * 
	 * @param locator
	 * @return
	 */
	public FlutterElement FlutterElementFinder(String locator) {
		FlutterElement result = null;

		if (locator.startsWith("keyvalue=")) {

			result = getFinder().byValueKey(locator.replaceAll("keyvalue=", ""));

		} else if (locator.startsWith("type=")) {

			result = getFinder().byType(locator.replaceAll("type=", ""));

		} else if (locator.startsWith("tooltip=")) {

			result = getFinder().byTooltip(locator.replaceAll("tooltip=", ""));

		} else if (locator.startsWith("semanticlabel=")) {

			result = getFinder().bySemanticsLabel(locator.replaceAll("semanticlabel=", ""));
		} else if (locator.startsWith("text=")) {
			result = getFinder().text(locator.replaceAll("text=", ""));
		} else {

			getExtTest().fail("Invalid locator strategy has passed, Given locator is :'" + locator
					+ "' Please check the locator in xml file.");
			Assert.assertTrue(false);
		}

		return result;
	}

	/**
	 * It will return the By type element based on the input.
	 * 
	 * @param locator
	 * @return
	 */

	public By ByLocator(String locator) {
		By result = null;

		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("/")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("#")) {
			result = By.name(locator.replace("#", ""));
		}else if (locator.startsWith("class=")) {
			result = By.className(locator.replace("class=", ""));
		} else if (locator.startsWith("name=")) {
			result = By.name(locator.replace("name=", ""));
		}else if (locator.startsWith("id=")) {
			result = By.id(locator.replace("id=", ""));
		} else if (locator.startsWith("(")) {
			result = By.xpath(locator);
		} else {

			result = By.id(locator);
		}

		return result;
	}

	/**
	 * Boolean function return false if element not present on screen.
	 * 
	 * @param locator
	 * @return
	 */
	public Boolean isElementPresent(String locator, String context_type) {

		Boolean result = false;

		switch (context_type.toUpperCase()) {
		case "NATIVE":

			getDriver().context("NATIVE_APP");

			try {
				getDriver().findElement(ByLocator(locator));
				result = true;
			} catch (Exception ex) {
			}
			getDriver().context("FLUTTER");

			break;

		case "FLUTTER":

			try {
				getDriver().context("FLUTTER");
				
				getDriver().executeScript("flutter:waitFor", FlutterElementFinder(locator),2);
				result = true;
			} catch (Exception e1) {
			//	e1.printStackTrace();
				result = false;
			}

			break;

		default:
			break;
		}

		try {

		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurreed: " + e.getMessage());
		}

		return result;
	}

	/**
	 * It will max wait till given time to make given element to visible on
	 * screen.
	 * 
	 * @param locator
	 * @param timeOut
	 * @throws IOException
	 */
	public void waitForElementToPresent(String locator, String contextType, int timeOut) throws IOException {
		try {

			switch (contextType.toUpperCase()) {
			case "NATIVE":

				getDriver().context("NATIVE_APP");

				flag_Go = false;

				for (int i = 0; i < timeOut; i++) {
					try {
						if (isElementPresent(locator, contextType)) {

							flag_Go = true;
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				
				if (!flag_Go) {
					infoLogger("Element not present within define time.");
				}

				getDriver().context("FLUTTER");

				break;

			case "FLUTTER":

				getDriver().context("FLUTTER");
				
				flag_Go = false;
				
				try {
					getDriver().executeScript("flutter:waitFor", FlutterElementFinder(locator), timeOut);
					flag_Go = true;
				} catch (Exception e) {
					
					infoLogger("Element not present within define time.");
				}

				break;

			default:
				break;
			}

		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception Occurred: "+e.getMessage());
		}
	}

	public boolean isAlertPresent() {

		switchContextType("Native");
		boolean status = true;

		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 5);
			if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
				System.err.println("alert not present");
				status = false;
			} else {

				System.err.println("alert is present");
				status = true;
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			status = false;

		}
		return status;
	}

	
	
	/**
	 * wait for element to not visible on screen. Test case got terminated if
	 * the element will not disappeared in given time.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	/*
	 * public void waitForElementNotVisible(String locator, int timeOut){ try {
	 * boolean status = true; waitForElementToPresent(locator, 1); String
	 * startTime = getCurrentTimeUsingCalendar(); int time_diff=0; while
	 * (status) { try { if (isElementPresent(locator)) {
	 * 
	 * }else{ break; }
	 * 
	 * String endTime = getCurrentTimeUsingCalendar(); SimpleDateFormat format =
	 * new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	 * 
	 * Date d1 = null; Date d2 = null;
	 * 
	 * try { d1 = format.parse(startTime); d2 = format.parse(endTime);
	 * 
	 * DateTime dt1 = new DateTime(d1); DateTime dt2 = new DateTime(d2);
	 * 
	 * System.err.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 +
	 * " seconds, ");
	 * 
	 * time_diff = Seconds.secondsBetween(dt1, dt2).getSeconds() % 60;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } if(time_diff>timeOut){
	 * DriverTestCase.test.fail("Element is not disappeared after "
	 * +timeOut+" seconds, terminating the test case.",
	 * MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path(
	 * "preLoader_icon")).build());
	 * Assert.assertTrue(false,"Element is not disappeared after "
	 * +timeOut+" seconds, terminating the test case."); }else{ continue; } }
	 * catch (NoSuchElementException ne) { // TODO Auto-generated catch block
	 * //ne.printStackTrace(); continue; } catch(StaleElementReferenceException
	 * se){ //se.printStackTrace(); continue; } }
	 * 
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * } }
	 */


	/**
	 * Description: Enter text into text field also pass context type
	 * 
	 * @param locator
	 * @param str_fieldName
	 * @param contextType
	 * @param str_textValue
	 */
	public void enterText(String locator, String str_fieldName, String contextType, String str_textValue) {
		try {

			switch (contextType.toUpperCase()) {
			case "NATIVE":

				waitForElementToPresent(locator, contextType, 2);
				if (isElementPresent(locator, contextType)) {

					getDriver().context("NATIVE_APP");
					MobileElement element = getDriver().findElement(ByLocator(locator));
					element.click();
					element.sendKeys(str_textValue);
					Thread.sleep(200);
					positiveComment_withScreenshot(
							"'" + str_textValue + "' text enter into '" + str_fieldName + "' field.",
							str_fieldName + "_inputValue");

					getDriver().context("FLUTTER");

				} else {

					negativeComment_withScreenshot(str_fieldName + " not present.", str_fieldName + "_notPresent");
					
					Assert.assertTrue(false, "Element not present.");
				}

				break;

			case "FLUTTER":

				waitForElementToPresent(locator, contextType, 2);
				if (isElementPresent(locator, "flutter")) {
					MobileElement element = FlutterElementFinder(locator);
					try {
						System.out.println("Element value: " + element);
					} catch (Exception e) {
						e.printStackTrace();
					}
					element.click();
					element.clear();
					getDriver().executeScript("flutter:enterText", str_textValue);
					Thread.sleep(200);
					positiveComment_withScreenshot(
							"'" + str_textValue + "' text enter into '" + str_fieldName + "' field.",
							str_fieldName + "_inputValue");
				}else {
					
					negativeComment_withScreenshot("'"+str_fieldName+"' element not present on screen.", str_fieldName+"_notPresent");
					
					Assert.assertTrue(false, "Element not present.");
				}
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * It will return text by providing appropriate locator.
	 * 
	 * @param locator
	 * @param contextType
	 * @return
	 */
	public String getText(String locator, String contextType) {
		String output = "null";

		switch (contextType.toUpperCase()) {
		case "NATIVE":

			try {
				if (isElementPresent(locator, contextType)) {

					getDriver().context("NATIVE_APP");

					output = getDriver().findElement(ByLocator(locator)).getText().trim();

					getDriver().context("FLUTTER");

				} else {

					negativeComment_withScreenshot("Elements with locator '" + locator + "' not present.",
							"elementNotPresent");

				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case "FLUTTER":

			try {
				if (isElementPresent(locator, "Flutter")) {
					switchContextType("FLUTTER");
					output = FlutterElementFinder(locator).getText().trim();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		default:
			break;
		}

		return output;
	}

	/**
	 * @param locator
	 * @param str_elementName
	 * @param contextType
	 */
	public void tapOn(String locator, String str_elementName, String contextType) {
		try {

			switch (contextType.toUpperCase()) {
			case "NATIVE":

				switchContextType("NATIVE");

				MobileElement ele_tap = getDriver().findElement(ByLocator(locator));
				ele_tap.click();
				positiveComment_withoutScreenshot("Tap on " + str_elementName);

				getDriver().context("FLUTTER");
				break;

			case "FLUTTER":

				switchContextType("FLUTTER");

				try {
					if (isElementPresent(locator, "flutter")) {
						MobileElement ele = FlutterElementFinder(locator);
						ele.click();
						positiveComment_withoutScreenshot("Tap on " + str_elementName);
					}else {
						visibilityAssert(locator, contextType, str_elementName+" is displayed as expected.", str_elementName+" is not displayed.");
					}
				} catch (Exception e) {
					
					negativeComment_withScreenshot("Exception Occurred:", "exception");
					
					e.printStackTrace();
				}
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCurrentTimeUsingCalendar() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMMM");
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

	/**
	 * Capture the screenshot. You need to provide screenshot name in param.
	 * 
	 * @param str_screenshotName
	 * @return
	 */
	public String getScreenShotPath_path(String str_screenshotName) {
		String locationScreenShot = null;
		String screenShotName = null;
		String screenShotPath = null;
		try {
			TakesScreenshot ts = (TakesScreenshot) getDriver();
			File source = ts.getScreenshotAs(OutputType.FILE);
			screenShotName = str_screenshotName + getCurrentTimeUsingCalendar_screenshot().replaceAll(":", "_");
			locationScreenShot = System.getProperty("user.dir") + "/TestReports/"
					+ DriverTestCase.str_testReportFolderName + "/" + screenShotName + ".png";
			screenShotPath = "" + screenShotName + ".png";
			File screenshotLocation = new File(locationScreenShot);
			FileUtils.copyFile(source, screenshotLocation);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return screenShotPath;

	}
	
	
	public void WaitForElementNotVisible(String locator, int int_TimeOut) {
		try {
			
			getDriver().context("FLUTTER");
			
			getDriver().executeScript("flutter:waitForAbsent", FlutterElementFinder(locator),int_TimeOut);
			
			if (isElementPresent(locator, "flutter")) {
				
				negativeComment_withoutScreenshot("After waiting the define time, progress indicator stil present on the screen");
				Assert.assertTrue(false,"After waiting the define time, progress indicator stil present on the screen");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			negativeComment_withScreenshot("After waiting the define time, progress indicator stil present on the screen","progressIndicator");
			Assert.assertTrue(false,"After waiting the define time, progress indicator stil present on the screen");
			
		}
	}
	

	public void switchContextType(String str_contextType) {
		try {
			//System.out.println("Context >>> "+getDriver().getContextHandles());
			switch (str_contextType.toUpperCase()) {
			case "NATIVE":

				getDriver().context("NATIVE_APP");

				break;

			case "FLUTTER":

				getDriver().context("FLUTTER");

				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Positive step log into Test Report along with screenshot.
	 * 
	 * @param str_positive_comment
	 * @param str_screenshotName
	 */
	public void positiveComment_withScreenshot(String str_positive_comment, String str_screenshotName) {
		try {
			getExtTest().pass(str_positive_comment,
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path(str_screenshotName)).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Positive step log into Test Report without screenshot.
	 * 
	 * @param str_positive_comment
	 */
	public void positiveComment_withoutScreenshot(String str_positive_comment) {
		try {

			getExtTest().pass(str_positive_comment);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Negative step log into Test Report with screenshot.
	 * 
	 * @param str_neegative_comment
	 * @param str_screenshotName
	 */
	public void negativeComment_withScreenshot(String str_neegative_comment, String str_screenshotName) {
		try {

			getExtTest().fail(str_neegative_comment,
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path(str_screenshotName)).build());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Negative step log into Test Report without screenshot.
	 * 
	 * @param str_negative_comment
	 */
	public void negativeComment_withoutScreenshot(String str_negative_comment) {
		try {

			getExtTest().fail(str_negative_comment);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void infoLogger(String str_info){
		
		getExtTest().info(str_info);
	}

	/**
	 * Select date from calendar based on input value.
	 * 
	 * @param str_Start_date
	 * @param str_End_Date
	 */

	public void pickerwheelStep(MobileElement element, String direction, double offset) {
		Map<String, Object> params = new HashMap<>();
		params.put("order", direction);
		params.put("offset", offset);
		params.put("element", ((RemoteWebElement) element).getId());
		getDriver().executeScript("mobile: selectPickerWheelValue", params);
	}

	/**
	 * Scroll till element visible.
	 * 
	 * @param parent_locator
	 * @param element_locator
	 */
	public void scrollUntilVisible(String parent_locator, String visibleText) {

		try {
			/*getDriver().executeScript("flutter:scrollUntilVisible", FlutterElementFinder(parent_locator),
					new HashMap<String, Object>() {
						{
							put("item", getFinder().byValueKey(visibleText));
							put("dxScroll", 90);
						    put("dyScroll", -400);
							put("frequency", 5);
						}
					});*/
			
			/*getDriver().executeScript("flutter:scrollUntilVisible", FlutterElementFinder(parent_locator), new HashMap<String, Object>() {{
				  put("item", FlutterElementFinder(visibleText));
				  put("dx", 90);
				  put("dy", -300);
				  put("durationMilliseconds", 2000);
				  put("frequency", 5);
				}});*/
			
			 getDriver().executeScript("flutter:scrollUntilVisible", FlutterElementFinder(parent_locator), new HashMap<String, Object>() {{
			      put("item", FlutterElementFinder(visibleText));
			      put("dxScroll", 90);
			      put("dyScroll", -400.0);
			    }});
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	public void scrollDown(String parent_locator, String visibleText){
		try {
			switchContextType("Flutter");
			getDriver().executeScript("flutter:scrollIntoView",FlutterElementFinder(parent_locator), new HashMap<String, Object>() {{
				put("alignment", 0.5);
			    }});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scrollUntilVisible_countryDropDown(String parent_locator, String visibleText) {

		try {
			/*getDriver().executeScript("flutter:scrollIntoView", FlutterElementFinder(parent_locator),
					new HashMap<String, Object>() {
						{
							put("item", getFinder().text(visibleText));
							put("dxScroll", 90);
						    put("dyScroll", -400);
						    put("durationMilliseconds", 2000);
							put("frequency", 5);
						}
					});*/
			
			getDriver().executeScript("flutter:scrollUntilVisible", FlutterElementFinder(parent_locator), new HashMap<String, Object>() {{
				  put("item", FlutterElementFinder(visibleText));
				  put("dx", 90);
				  put("dy", -300);
				  put("durationMilliseconds", 2000);
				  put("frequency", 5);
				}});
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	/**
	 * Boolean function to verify text is present or not.
	 * 
	 * @param locator
	 * @param str_text_to_compare
	 * @return
	 */
	public boolean isTextPresent(String locator, String str_text_to_compare, String str_ContextType) {

		boolean status = false;

		switch (str_ContextType.toUpperCase()) {
		case "NATIVE":

			try {
				switchContextType("Native");
				MobileElement ele_text = getDriver().findElement(ByLocator(locator));
				String text = ele_text.getText().trim();

				if (text.equalsIgnoreCase(str_text_to_compare)) {

					status = true;
				}

			} catch (Exception e) {
				negativeComment_withScreenshot(e.getMessage(), "error_occurred");
			}

			switchContextType("Flutter");

			break;

		case "FLUTTER":

			try {
				switchContextType("Flutter");
				MobileElement element = FlutterElementFinder(locator);
				String text = element.getText().trim();

				if (text.toLowerCase().replaceAll("[^a-zA-Z0-9]", "").trim().equalsIgnoreCase(str_text_to_compare.toLowerCase().replaceAll("[^a-zA-Z0-9]", "").trim())) {

					status = true;
				}

			} catch (Exception e) {

				negativeComment_withScreenshot(e.getMessage(), "error_occurred");

			}

			break;

		default:
			break;
		}

		return status;

	}

	/**
	 * Wait for text present
	 * 
	 * @param locator
	 * @param text
	 * @param timeout
	 */
	public void WaitForTextPresent(String locator, String text, int timeout, String contextType) {
		try {
			waitForElementToPresent(locator, contextType, 3);
			flag_Go_textPresent = false;
			if (isElementPresent(locator, contextType)) {
				for (int i = 0; i < timeout; i++) {
					if (isTextPresent(locator, text, contextType)) {
						flag_Go_textPresent = true;
						break;
					}

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void WaitForTextPresent_fromList(String locator, String text, int timeout, String contextType) {
		try {
			flag_Go_textPresent = false;
			boolean status = false;
			waitForElementToPresent(locator, contextType, 3);

			for (int i = 0; i < timeout; i++) {
				try {
					if (isElementPresent(locator, contextType)) {
						// System.err.println(">>>>>>>> Inside text wait
						// function >>>>>>>>>>>>>>");
						switchContextType("Native");
						for (int j = 0; j < timeout; j++) {
							List<MobileElement> list_ele = getDriver().findElements(ByLocator(locator));
							// System.err.println(">>>>> Text element size: " +
							// list_ele.size());
							for (MobileElement ele : list_ele) {
								// System.err.println("Text >>>>>>>>>> : " +
								// ele.getText().trim());
								if (ele.getText().trim().contains(text)) {
									// System.err.println("Text detected
									// >>>>>>>>>>>");
									flag_Go_textPresent = true;
									status = true;
									break;

								}
							}
							if (status) {
								break;
							}
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (status) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getCurrentTimeUsingCalendar_screenshot() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

	public void compareTwoList(List<String> actual_list, List<String> expected_list,
			String str_elementName_List_OR_Country) {
		try {
			actual_list.stream().sorted().collect(Collectors.toList());
			expected_list.stream().sorted().collect(Collectors.toList());
			List<String> missingElement = new ArrayList<String>();
			// test.pass("Actual list: "+actual_list);
			// test.pass("Expected list: "+expected_list);
			int i = 0;
			int j = 1;
			try {
				for (i = 0; i < expected_list.size(); i++) {
					if (expected_list.get(i).equalsIgnoreCase(actual_list.get(i))) {

						getExtTest().pass(j + ". '" + expected_list.get(i) + "' " + str_elementName_List_OR_Country
								+ " is present.");

					} else {

						missingElement.add(expected_list.get(i).toString());
					}
					j++;
				}
			} catch (IndexOutOfBoundsException e) {
				// test.error("Error: "+e.getMessage());
				missingElement.add(expected_list.get(i).toString());

			}

			if (missingElement.size() > 0) {
				for (String missing_element : missingElement) {
					getExtTest().fail("'" + missing_element + "' " + str_elementName_List_OR_Country + " is missing.");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int getDayNumber(String date_MMM_DD_YYYY) {
		int dayNumber = 0;
		try {
			Calendar c = Calendar.getInstance();
			Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(date_MMM_DD_YYYY);
			c.setTime(date1);
			dayNumber = c.get(Calendar.DAY_OF_WEEK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dayNumber;
	}

	public String getMonthCount(String month){
		String monthCount="";
		try {
			
			SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputFormat.parse(month));
			SimpleDateFormat outputFormat = new SimpleDateFormat("MM"); // 01-12
			System.err.println(">>>>>>>>>>>> "+outputFormat.format(cal.getTime()));
			
			monthCount = String.valueOf(outputFormat.format(cal.getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return monthCount;
	}
	
	public String dateSplitter(String date_MMM_DD_YYYY) {
		String output = "";
		try {

			String[] str_arr = date_MMM_DD_YYYY.split("/");

			output = str_arr[1];

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}
	
	
	public String getPriceDigit(String locator, String contextType){
		
		String price="";
		
		try {
			
			price = getText(locator, contextType).replaceAll("[^0-9.]", "");
			
		} catch (Exception e) {
			
		}
		
		return price;
	}
	
	public double getFormatted(double ft_price){
		double val = 0.0;
		try {
			
			String str_val = df.format(ft_price);
			
			val = Double.valueOf(str_val);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
		return val;
	}
	
	public String getFormatted(String str_price){
		String str_val = "";
		try {
			
			str_val = df.format(str_price);
			
			
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
		return str_val;
	}
	
	public Boolean verifyTitle(String locator, String expectedTitle, String str_ContextType, String str_screenName) {

		Boolean result = false;
		
		try {
					if(getText(locator, str_ContextType).trim().equals(expectedTitle)) {
						
						result = true;
						
					} else {
						
						result = false;
					}
				
			} catch (Exception e) {
				e.printStackTrace();
				negativeComment_withScreenshot("Exception Occurrd: "+e.getMessage(), "error_occurred");
			}
		
		return result;	
		
		}
	
	public void WaitForAlertPresent(int timeOut){
		try {
			switchContextType("Native");
			new WebDriverWait(driver,timeOut).until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			
		}
	}
	
	
	public void visibilityAssert(String locator, String str_ContextType, String positiveMessage, String negativeMessage) {
		try {
			
				if (isElementPresent(locator, str_ContextType)) {
					
					positiveComment_withScreenshot(positiveMessage, locator+"_Found");
					
				} else {
					
					negativeComment_withScreenshot(negativeMessage, locator+"_notFound");
					
					Assert.assertTrue(isElementPresent(locator, str_ContextType), negativeMessage);
				}
				
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
	}
	
	public void assertEqual(String locator, String str_ContextType,String str_value) {
		try {
			
				if (isElementPresent(locator, str_ContextType)) {
					if (isTextPresent(locator, str_value, str_ContextType)) {

						positiveComment_withScreenshot("'" + str_value + " is displayed as expected",
								locator + "_Found");

					} else {

						negativeComment_withScreenshot(
								"Expected :'" + str_value + "' BUT FOUND:'" + getText(locator, str_ContextType) + "'",
								str_value + "_notMatched");

						Assert.assertEquals(getText(locator, str_ContextType), str_value,
								"Expected :'" + str_value + "' BUT FOUND:'" + getText(locator, str_ContextType) + "'");
					} 
				}else {
					
					negativeComment_withScreenshot(locator+" is not present on the screen.", locator+"_notfound");
					
					Assert.assertTrue(false);
				}
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
	}

}
