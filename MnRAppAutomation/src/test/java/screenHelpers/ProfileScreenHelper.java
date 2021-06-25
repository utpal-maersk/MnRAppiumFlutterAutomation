/**
 * 
 */
package screenHelpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.ProfileScreen;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class ProfileScreenHelper extends DriverHelper implements ProfileScreen {

	private LocatorReader elements;
	
	public ProfileScreenHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
	}


	@Override
	public void tapOnChangeCustomer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tapOnBiometricToggle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tapOnLogOut() {
		
		try {
			
			scrollUntilVisible(elements.getLocator("ProfileScreen.parentScroll"), elements.getLocator("ProfileScreen.logoutIconLeft"));
			
			tapOn(elements.getLocator("ProfileScreen.logoutIconLeft"), "Logout tab", "Flutter");
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void confirmLogOut(String str_textDesc) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("LogOut.alertTitleLogout"), "Flutter", 3);
			
			if (isElementPresent(elements.getLocator("LogOut.alertTitleLogout"), "Flutter")) {
				
				if (isTextPresent(elements.getLocator("LogOut.alertMessageLogout"), str_textDesc, "Flutter")) {
					
					positiveComment_withScreenshot("Description text displayed as expected.", "logoutAlert");
					
					tapOn(elements.getLocator("LogOut.alertLogoutButton"), "Logout button", "Flutter");
					
				} else {
					
					negativeComment_withoutScreenshot("Logout alert description is not matched with expected.");
				}
				
			} else {
				
				negativeComment_withScreenshot("Logout alert not present.", "logoutAlert");
			}
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void declineLogOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyUsername() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyCustomerName(String str_expectedName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyCustomerCode(String str_expectedCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tapOnSendFeedbackButton() {
		// TODO Auto-generated method stub
		
	}

}
