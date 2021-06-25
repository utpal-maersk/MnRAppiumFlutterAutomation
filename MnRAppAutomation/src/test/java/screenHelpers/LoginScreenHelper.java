/**
 * 
 */
package screenHelpers;

import org.junit.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.LoginScreen;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class LoginScreenHelper extends DriverHelper implements LoginScreen {

	private LocatorReader elements;
	
	public LoginScreenHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void verifyWelcomeTextAtLoginScreen(String str_Welcometext) {
		
		try {
			
			if (isElementPresent(elements.getLocator("OnBoardingScreen.SkipButton"), "Flutter")) {
				
				tapOn(elements.getLocator("OnBoardingScreen.SkipButton"), "Skip button", "Flutter");
			}
			
			waitForElementToPresent(elements.getLocator("LoginScreen.WelcomeText"), "Flutter", 60);
			
			assertEqual(elements.getLocator("LoginScreen.WelcomeText"), "Flutter", str_Welcometext);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifySubTextAtLoginScreen(String str_subText) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("LoginScreen.WelcomeTextSub"), "Flutter", 5);
			
			assertEqual(elements.getLocator("LoginScreen.WelcomeTextSub"), "Flutter", str_subText);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void tapOnBrandIconByName(String str_brandName) {
		
		try {
			
			switch (str_brandName.toUpperCase()) {
			
			case "MAERSKLINE":
				
				tapOn(elements.getLocator("LoginScreen.MaerskBrandIcon"), "Maerskline brand icon", "Flutter");
				
				break;
				
			case "SEALAND":
				
				tapOn(elements.getLocator("LoginScreen.SealandBrandIcon"), "Sealand brand icon", "Flutter");
				
				break;
				
			default:
				break;
			}
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void enterUserName(String str_placeHolder, String str_username) {
		
		try {
			
			if (!str_username.equalsIgnoreCase("NA")) {
				enterText(elements.getLocator("LoginScreen.UserNameField"), "Username", "Flutter",
						str_username);
			}else {
				enterText(elements.getLocator("LoginScreen.UserNameField"), "Username", "Flutter",
						"");
			}
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
			
		}
		
	}

	@Override
	public void enterPassword(String str_placeHolder, String str_password) {
		
		try {
			
			if (!str_password.equalsIgnoreCase("NA")) {
				
				enterText(elements.getLocator("LoginScreen.PasswordField"), "Password", "Flutter",
						str_password);
			}else {
				
				enterText(elements.getLocator("LoginScreen.PasswordField"), "Password", "Flutter",
						"");
			}
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
			
		}
		
	}

	@Override
	public void verifyForgotUsernameLinkAvailability() {
		
		try {
			
			if (isElementPresent(elements.getLocator("LoginScreen.UsernameLink"), "Flutter")) {
				
				positiveComment_withoutScreenshot("'Forgot username' link is present.");
				
			} else {
				
				negativeComment_withoutScreenshot("'Forgot username' link is not present.");
			}
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifyForgotPasswordLinkAvailability() {
		
		try {
			
			if (isElementPresent(elements.getLocator("LoginScreen.PasswordLink"), "Flutter")) {
				
				positiveComment_withoutScreenshot("'Forgot password' link is present.");
				
			} else {
				
				negativeComment_withoutScreenshot("'Forgot password' link is not present.");
			}
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifyRegisterHereLinkAvailability() {
		
		try {
			
			if (isElementPresent(elements.getLocator("LoginScreen.RegisterHere"), "Flutter")) {
				
				positiveComment_withoutScreenshot("'Register Here' link is present.");
				
			} else {
				
				negativeComment_withoutScreenshot("'Register Here' link is not present.");
			}
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void tapOnLoginButton() {
		
		try {
			
			tapOn(elements.getLocator("LoginScreen.LoginButton"), "Login button", "Flutter");
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void tapOnForgotUsernameLink() {
		
		try {
			
			tapOn(elements.getLocator("LoginScreen.UsernameLink"), "Username link", "Flutter");
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void tapOnForgotPasswordLink() {
		
		try {
			
			tapOn(elements.getLocator("LoginScreen.PasswordLink"), "Password link", "Flutter");
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifyErrorMessage(String str_errorMessage) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("LoginScreen.ErrorMessage"), "Flutter", 60);
			
			assertEqual(elements.getLocator("LoginScreen.ErrorMessage"), "Flutter", str_errorMessage);
			
			WaitForElementNotVisible(elements.getLocator("LoginScreen.ErrorMessage"), 10);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void logOut() {
		
		try {
			waitForElementToPresent(elements.getLocator("LogOut.ProfileTab"), "Flutter", 30);
			
			if (isElementPresent(elements.getLocator("LogOut.ProfileTab"), "Flutter")) {
				
				tapOn(elements.getLocator("LogOut.ProfileTab"), "Profile tab", "Flutter");
				
				waitForElementToPresent(elements.getLocator("LogOut.LogoutButton"), "Flutter", 30);
				
				if (isElementPresent(elements.getLocator("LogOut.LogoutButton"), "Flutter")) {
					
					tapOn(elements.getLocator("LogOut.LogoutButton"), "Log out button", "Flutter");
					
					waitForElementToPresent(elements.getLocator("LogOut.LogOutConfirm"), "Flutter", 30);
					
					if (isElementPresent(elements.getLocator("LogOut.LogOutConfirm"), "Flutter")) {
						
						tapOn(elements.getLocator("LogOut.LogOutConfirm"), "confirm button", "Flutter");
						
					} else {
						
						negativeComment_withScreenshot("Confirm button is not present.", "profileTab");
						
						Assert.assertTrue(false);
					}
					
				} else {
					
					negativeComment_withScreenshot("Logout button is not present.", "profileTab");
					
					Assert.assertTrue(false);
				}
				
			} else {
				
				negativeComment_withScreenshot("Profile tab is not present.", "profileTab");
				
				Assert.assertTrue(false);
			}
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}
	
	

}
