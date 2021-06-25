package utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;
import pro.truongsinh.appium_flutter.FlutterFinder;

public class CommonFunctions extends DriverHelper{
	
	public CommonFunctions(AppiumDriver<MobileElement> driver, FlutterFinder find) {
		super(driver, find);
		
	}

	public static void main(String[] args){
		
		System.out.println("Splitter"+stringSplitter("Location LHR: Location UX9: Location London: Location Ukrain"));
	}
	
	public static List<String> stringSplitter(String inputString){
		
		List<String> list = new ArrayList<String>();
		
		try {
			
			String[] str_arr = inputString.split(";");
			
			for (String x : str_arr) {
				
				list.add(x.trim());
			}
			
		} catch (Exception e) {
			
		}
		
		
		return list;
		
	}
	
	public static void swipe(AppiumDriver<MobileElement> driver,int x_start, int y_start, int x_stop, int y_stop, int duration) {

		new TouchAction(driver).press(PointOption.point(x_start, y_start))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(duration)))
				.moveTo(PointOption.point(x_stop, y_stop)).release().perform();
	}
	
	

}
