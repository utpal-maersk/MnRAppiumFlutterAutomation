/**
 * 
 */
package screenHelpers;

import java.io.IOException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.IntroductionScreen;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class IntroductionScreenHelper extends DriverHelper implements IntroductionScreen {

	private LocatorReader elements;
	
	public IntroductionScreenHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void verifyWelcomeTextOnIntroScreen(String str_text) {
		
		try {
			waitForElementToPresent(elements.getLocator("IntroductionScreen.WelcomeMessage"), "Flutter", 120);
			
			visibilityAssert(elements.getLocator("IntroductionScreen.WelcomeMessage"), "Flutter", "'Welcome' text is present", "'Welcome' text is not present");
			
			assertEqual(elements.getLocator("IntroductionScreen.WelcomeMessage"), "Flutter", str_text);
			
		} catch (IOException e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifyAnimationOnIntroScreen() {
		
		try {
			
			waitForElementToPresent(elements.getLocator("IntroductionScreen.AnimationImage"), "Flutter", 20);
			
			visibilityAssert(elements.getLocator("IntroductionScreen.AnimationImage"), "Flutter", "'Animation image' is present", "'Animation image' is not present");
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifyLoadingTextOnIntroScreen() {
		
		try {
			
			waitForElementToPresent(elements.getLocator("IntroductionScreen.LoadingText"), "Flutter", 120);
			
			visibilityAssert(elements.getLocator("IntroductionScreen.LoadingText"), "Flutter", "'Loading' text is present", "'Loading' text is not present");
			
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

}
