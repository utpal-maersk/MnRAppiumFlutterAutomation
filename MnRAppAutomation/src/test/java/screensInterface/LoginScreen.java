/**
 * 
 */
package screensInterface;

/**
 * @author Ahmer Hashmi
 *
 */
public interface LoginScreen {
	
	
	public void verifyWelcomeTextAtLoginScreen(String str_Welcometext);
	
	public void verifySubTextAtLoginScreen(String str_subText);
	
	/**
	 * @param str_brandName
	 * 
	 * Provide brand name like MAERSKLINE,SAFMARINE,SEALAND
	 */
	public void tapOnBrandIconByName(String str_brandName);
	
	public void enterUserName(String str_placeHolder,String str_username);
	
	public void enterPassword(String str_placeHolder,String str_password);
	
	public void verifyForgotUsernameLinkAvailability();
	
	public void verifyForgotPasswordLinkAvailability();
	
	public void verifyRegisterHereLinkAvailability();
	
	public void tapOnLoginButton();
	
	public void tapOnForgotUsernameLink();
	
	public void tapOnForgotPasswordLink();
	
	public void verifyErrorMessage(String str_errorMessage);
	
	public void logOut();

}
