/**
 * 
 */
package screenHelpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.DemoScreen;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class DemoScreenHelper extends DriverHelper implements DemoScreen {

	private LocatorReader elements;
	
	public DemoScreenHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		System.err.println("Platform name: >>>>>>>>>>>>>>> "+getConstants().PLATFORMNAME);
		System.err.println("Platform name: >>>>>>>>>>>>>>> "+getConstants().PLATFORMNAME);
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void handleLaunchingGesture() {
		
		try {
			
			waitForElementToPresent(elements.getLocator("HomeScreen.EmailField"), "Flutter", 60);
			
			if (isElementPresent(elements.getLocator("HomeScreen.EmailField"), "Flutter")) {
				
				enterText(elements.getLocator("HomeScreen.EmailField"), "Email ", "Flutter", "ahmer.hashmi@maersk.com");
				
			} else {

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void verifyHomeScreen() {
		// TODO Auto-generated method stub
		
	}

}
