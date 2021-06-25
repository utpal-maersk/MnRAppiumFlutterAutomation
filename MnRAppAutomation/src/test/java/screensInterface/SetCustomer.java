package screensInterface;

public interface SetCustomer {
	
	
	public void verifySetCustomerTitle(String str_title);
	
	public void verifyCustomerList(String str);
	
	public void verifySubText(String str_subText);
	
	public void selectCustomerCode(String str_customerCode);
	
	public String getCustomerName();
	
	public void handleBiometricPopup(String str_biometricText, String str_userGuidance);
	

}
