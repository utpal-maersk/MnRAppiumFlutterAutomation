/**
 * 
 */
package screenHelpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.HomeScreen;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class HomeScreenHelper extends DriverHelper implements HomeScreen{

	private LocatorReader elements;
	
	public HomeScreenHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void verifyHomeScreenTitleText(String str_title, String str_subText) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("HomeScreen.WelcomeText"),"Flutter", 20);
			
			if (isTextPresent(elements.getLocator("HomeScreen.WelcomeText"), str_title, "Flutter")) {
				
				positiveComment_withoutScreenshot("'Welcome' text at home screen is displayed as expected.");
				
				if (isTextPresent(elements.getLocator("HomeScreen.WelcomeTextSub"), str_subText, "Flutter")) {
					
					positiveComment_withoutScreenshot("Sub text is displayed as expected at home screen");
					
				} else {
					
					negativeComment_withScreenshot("Sub text not matched with expected.", "subText");
				}
				
			} else {
				
				negativeComment_withScreenshot("Welcome text not matched with expected.", "subText");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

}
