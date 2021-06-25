/**
 * 
 */
package screenHelpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.LandingScreen;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class LandingScreenHelper extends DriverHelper implements LandingScreen{
	
	private LocatorReader elements;

	public LandingScreenHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void verifyTitleText(String str_text) {
		
		try {
			waitForElementToPresent(elements.getLocator("LandingScreen.WelcomeText"), "Flutter", 20);
			
			assertEqual(elements.getLocator("LandingScreen.WelcomeText"), "Flutter", str_text);
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifySubLable(String str_subText) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("LandingScreen.WelcomeTextSub"), "Flutter", 3);
			
			assertEqual(elements.getLocator("LandingScreen.WelcomeTextSub"), "Flutter", str_subText);
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	
	/* 
	 * Please provide top navigation icons names like SHIPMENT, VESSEL, PORT, OFFICE
	 */
	@Override
	public void tapOnTopNavigationIconByName(String str_name) {
		
		try {
			switch (str_name.toUpperCase()) {
			case "SHIPMENT":
				
				tapOn(elements.getLocator("LandingScreen.ShipmentTopIcon"), "shipment icon", "Flutter");
				
				break;
			case "VESSEL":
				
				tapOn(elements.getLocator("LandingScreen.VesselTopIcon"), "vessel icon", "Flutter");
				
				break;	
			case "PORT":
				
				tapOn(elements.getLocator("LandingScreen.PortTopIcon"), "port icon", "Flutter");
				
				break;
			case "OFFICE":
				
				tapOn(elements.getLocator("LandingScreen.OfficeTopIcon"), "office icon", "Flutter");
				
				break;	

			default:
				break;
			}
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
			
		}
		
	}

	@Override
	public void verifyWatermarkTextOfSearchField(String str_navigation, String str_text) {
		
		try {
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void tapOnSearchFieldByName(String str_fieldName) {
		
		try {
			
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public void tapOnBottomNavigationIconByName(String str_iconName) {
		
		try {
			
		} catch (Exception e) {
			
		}
		
	}

}
