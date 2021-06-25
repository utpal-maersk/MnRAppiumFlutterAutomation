/**
 * 
 */
package screensInterface;

/**
 * @author Ahmer Hashmi
 *
 */
public interface ProfileScreen {
	
	public void tapOnChangeCustomer();
	
	public void tapOnBiometricToggle();
	
	public void tapOnLogOut();
	
	public void confirmLogOut(String str_textDesc);
	
	public void declineLogOut();
	
	public void verifyUsername();
	
	public void verifyCustomerName(String str_expectedName);
	
	public void verifyCustomerCode(String str_expectedCode);
	
	public void tapOnSendFeedbackButton();
	

}
