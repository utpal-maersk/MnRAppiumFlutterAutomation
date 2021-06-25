/**
 * 
 */
package screensInterface;

/**
 * @author Ahmer Hashmi
 *
 */
public interface InterstitialScreen {
	
	public void verifyTitle(String str_title);
	
	public void verifyImage();
	
	public void verifyDescriptionText(String str_content);
	
	public void tapOnSkipButton();
	
	public void tapOnNextButton();
	
	public void tapOnWelcomeToMyfinanceButton();
	
	public void handleAlerts();
	

}
