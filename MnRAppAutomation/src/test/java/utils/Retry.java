package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int retrycount = 0;
	int maxretyrcount =DriverTestCase.int_maxTry;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		//System.err.println("Maximum try from sheet: "+DriverTestCase.int_maxTry);
		//System.err.println("Maximum try: "+maxretyrcount);
		if (retrycount<DriverTestCase.int_maxTry){
			System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retrycount+1) + " time(s).");
			retrycount++;
            return true;
		}
		
		return false;
	}

	 public String getResultStatusName(int status) {
	    	String resultName = null;
	    	if(status==1)
	    		resultName = "SUCCESS";
	    	if(status==2)
	    		resultName = "FAILURE";
	    	if(status==3)
	    		resultName = "SKIP";
			return resultName;
	    }
}