/**
 * 
 */
package screenHelpers;

import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import locators.LocatorReader;
import pro.truongsinh.appium_flutter.FlutterFinder;
import screensInterface.SetCustomer;
import utils.DriverHelper;

/**
 * @author Ahmer Hashmi
 *
 */
public class SetCustomerHelper extends DriverHelper implements SetCustomer {

	private LocatorReader elements;
	
	public String customerName;
	
	public SetCustomerHelper(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
		if (getConstants().PLATFORMNAME.equalsIgnoreCase("iOS")) {
			
			elements = new LocatorReader(getConstants().iOSLocatorFileName);
			
		}else if(getConstants().PLATFORMNAME.equalsIgnoreCase("Android")){
			
			elements = new LocatorReader(getConstants().androidLocatorFileName);
		}
		
	}

	@Override
	public void verifySetCustomerTitle(String str_title) {
		
		try {
			
			waitForElementToPresent(elements.getLocator("SetCustomer.SetCustomerTitle"), "Flutter", 60);
			
			assertEqual(elements.getLocator("SetCustomer.SetCustomerTitle"), "Flutter", str_title);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifyCustomerList(String str) {
		
		try {
			
			
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void verifySubText(String str_subText) {
		try {
			
			waitForElementToPresent(elements.getLocator("SetCustomer.SubText"), "Flutter", 5);
			
			assertEqual(elements.getLocator("SetCustomer.SubText"), "Flutter", str_subText);
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public void selectCustomerCode(String str_customerCode) {
		
		try {
			
			boolean flag= true;
			
			if (isElementPresent(elements.getLocator("SetCustomer.CustomerCode")+"0", "Flutter")) {
				
				for (int i = 0; i < 30; i++) {
					
						System.out.println("Customer code >> "+i+" "+getText(elements.getLocator("SetCustomer.CustomerCode")+i, "Flutter"));
						
					if (getText(elements.getLocator("SetCustomer.CustomerCode")+i, "Flutter").trim().equalsIgnoreCase(str_customerCode)) {
						
						customerName = getText(elements.getLocator("SetCustomer.TradingName")+i, "Flutter");
						
					//	scrollUntilVisible_countryDropDown(elements.getLocator("SetCustomer.ScrollerContainer"), elements.getLocator("SetCustomer.CustomerCode")+i);
						
						tapOn(elements.getLocator("SetCustomer.CustomerCode") + i,
								str_customerCode + " customer code", "Flutter");
						
						flag=true;
						break;
						
					} else {
						
						flag=false;
					}
				}
				
				if (!flag) {
					
					negativeComment_withScreenshot("'"+str_customerCode+"' customer code is not present on set cusomer screen.", "noCustomerFound");
				}
				
			} else {
				
				negativeComment_withScreenshot("No customer code is displayed.", "noCustomerCode");
				
				Assert.assertTrue(false);
			}
			
		} catch (Exception e) {
			
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

	@Override
	public String getCustomerName() {
		
		return customerName;
	}

	@Override
	public void handleBiometricPopup(String str_biometricText, String str_userGuidance) {
		
		try {
			waitForElementToPresent(elements.getLocator("BiometricAvailable.BiometricCancelButton"), "Flutter", 3);
			
			if (isElementPresent(elements.getLocator("BiometricAvailable.BiometricCancelButton"), "Flutter")) {
				
				positiveComment_withScreenshot("Biometric popup is present.", "biometricPopup");
				
				if (isTextPresent(elements.getLocator("BiometricAvailable.BiometricDescription"), str_biometricText, "Flutter")) {
					
					positiveComment_withoutScreenshot("Biometric 'Description' text is displayed as expected. EXPECTED: "+str_biometricText+" and FOUND: "+getText(elements.getLocator("BiometricAvailable.BiometricDescription"), "Flutter")+"'");
					
				} else {
					
					negativeComment_withoutScreenshot("Biometric description text not matched");
				}
				
				tapOn(elements.getLocator("BiometricAvailable.BiometricCancelButton"), "Cancel button", "Flutter");
				
			} else if(isElementPresent(elements.getLocator("BiometricNotAvailable.BiometricAlertTitle"), "Flutter")){
				
				if (isTextPresent(elements.getLocator("BiometricNotAvailable.BiometricDescription"), str_userGuidance, "Flutter")) {
					
					positiveComment_withScreenshot("User guidance message displayed as expected.", "userguidance");
					
					tapOn(elements.getLocator("BiometricNotAvailable.BiometricOkButton"), "Ok button", "Flutter");
					
				} else {
					
					negativeComment_withoutScreenshot("User guidance description text not matched");
					
				}
				
				
			}else {
				
				negativeComment_withScreenshot("Biometric popup is not present.", "biometricPopup");
			}
			
		} catch (Exception e) {
			negativeComment_withoutScreenshot("Exception occurred: "+e.getMessage());
		}
		
	}

}
