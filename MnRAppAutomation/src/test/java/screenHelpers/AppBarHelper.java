/**
 * 
 */
package screenHelpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.AppBar;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class AppBarHelper extends DriverHelper implements AppBar {

	private LocatorReader elements;
	
	public AppBarHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void verifyScreenTitle(String str_expectedTitle) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("AppBar.ScreenTitle"), "Flutter", 30);
			
			assertEqual(elements.getLocator("AppBar.ScreenTitle"), "Flutter", str_expectedTitle);
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void tapOnProfileIcon() {
		
		try {
			
			waitForElementToPresent(elements.getLocator("AppBar.ProfileIconInactive"), "Flutter", 10);
			
			if (isElementPresent(elements.getLocator("AppBar.ProfileIconInactive"), "Flutter")) {
				
				tapOn(elements.getLocator("AppBar.ProfileIconInactive"), "Profile icon", "Flutter");
				
			} else {
				
				negativeComment_withScreenshot("'Profile' icon is not present.", "profileIconNotPresent");
			}
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
		
	}

}
