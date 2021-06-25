/**
 * 
 */
package screensInterface;

/**
 * @author Ahmer Hashmi
 *
 */
public interface LandingScreen {
	
	public void verifyTitleText(String str_text);
	
	public void verifySubLable(String str_subText);
	
	/**
	 * @param str_name
	 * 
	 * Please provide top navigation icons names like SHIPMENT, VESSEL, PORT, OFFICE
	 */
	public void tapOnTopNavigationIconByName(String str_name);
	
	public void verifyWatermarkTextOfSearchField(String str_navigation, String str_text);
	
	public void tapOnSearchFieldByName(String str_fieldName);
	
	public void tapOnBottomNavigationIconByName(String str_iconName);
	
	
	

}
