/**
 * 
 */
package screenHelpers;

import java.util.regex.Pattern;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.InterstitialScreen;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class InterstitialScreenHelper extends DriverHelper implements InterstitialScreen {

	private LocatorReader elements;
	
	public InterstitialScreenHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void verifyTitle(String str_title) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("OnBoardingScreen.TitleText"), "Flutter", 60);
			
			assertEqual(elements.getLocator("OnBoardingScreen.TitleText"), "Flutter", str_title);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifyImage() {
		
		try {
			
			waitForElementToPresent(elements.getLocator("OnBoardingScreen.Image"), "Flutter", 10);
			
			visibilityAssert(elements.getLocator("OnBoardingScreen.Image"), "Flutter", "'Image' is present.", "Image' is not present.");
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifyDescriptionText(String str_content) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("OnBoardingScreen.DescriptionText"), "Flutter", 5);
			
			assertEqual(elements.getLocator("OnBoardingScreen.DescriptionText"), "Flutter", str_content);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void tapOnSkipButton() {
		
		try {
			
			tapOn(elements.getLocator("OnBoardingScreen.SkipLinkFirstScreen"), "Skip Button", "Flutter");
			
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}
	
	@Override
	public void tapOnNextButton() {
		
		try {
		
			waitForElementToPresent(elements.getLocator("OnBoardingScreen.NextButton"), "Flutter", 60);	
			tapOn(elements.getLocator("OnBoardingScreen.NextButton"), "Next Button", "Flutter");
			
			/*waitForElementToPresent(elements.getLocator("OnBoardingScreen.testLocator1"), "Flutter", 60);
			enterText(elements.getLocator("OnBoardingScreen.testLocator1"), "User name", "Flutter", "usernametest");
			
			enterText(elements.getLocator("OnBoardingScreen.testLocator2"), "password name", "Flutter", "passwordtest");*/
		
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	

	@Override
	public void tapOnWelcomeToMyfinanceButton() {
		try {
			
			tapOn(elements.getLocator("OnBoardingScreen.WelcomeToMyFinanceButton"), "Welcome to myFinance button", "Flutter");
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void handleAlerts() {
		
		try {
			
			if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) 
			
			 {
				if (isAlertPresent()) {

					getDriver().switchTo().alert().accept();

					positiveComment_withoutScreenshot("Alert got handled.");

				} else {

					positiveComment_withoutScreenshot("Alert not appeared.");
				} 
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
