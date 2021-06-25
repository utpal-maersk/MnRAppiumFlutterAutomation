/**
 * 
 */
package screenHelpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.DashboardScreen;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class DashboardScreenHelper extends DriverHelper implements DashboardScreen {

	private LocatorReader elements;
	
	public DashboardScreenHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void verifyDashboardScreenTitle(String str_title) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("DashboardScreen.TitleText"), "Flutter", 60);
			
			assertEqual(elements.getLocator("DashboardScreen.TitleText"), "Flutter", str_title);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

}
