package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;

public class ConfigApp {
	
	static AndroidDriver driverB;
	public  static AndroidDriver configJC() throws MalformedURLException
	{
		try{
				DesiredCapabilities capabilities = new DesiredCapabilities();
		  	  // device(S3) Configuration
		    	capabilities.setCapability("deviceName",Constants.Device1);
			    capabilities.setCapability(CapabilityType.BROWSER_NAME,  "");
			    //capabilities.setCapability("platformVersion", Constants.S3Version);
		  	    capabilities.setCapability("platformName", "Android");
		  	    capabilities.setCapability("appPackage", Constants.appPackage);
		  	    capabilities.setCapability("udid", Constants.UDID1);
		  	    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		  	    capabilities.setCapability("appActivity", Constants.launcherActivity);
		  	    capabilities.setCapability("appWaitActivity", Constants.mainActivity);
		  	    AndroidDriver driver = new AndroidDriver(new URL(Constants.APPIUM_SERVER_URL), capabilities);		  	  
		  	  	System.out.println("Appium instance created");
		  	  	
		  	  	return driver;
			
		}catch(Exception e){
			throw new SkipException("configuration error in driver1");
		}
		
			    
	}
	
	public static AndroidDriver configJCRegisterpage() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
	  	  
    	capabilities.setCapability("deviceName",Constants.Device1);
	    capabilities.setCapability(CapabilityType.BROWSER_NAME,  "");
	    capabilities.setCapability("platformName", "Android");
  	    capabilities.setCapability("appPackage", Constants.appPackage);
  	    capabilities.setCapability("udid", Constants.UDID1);
  	    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
  	    //App Activity
  	    capabilities.setCapability("appWaitActivity", ".ui.activitys.register.RegisterMobileActivity");
  	    //capabilities.setCapability("appWaitActivity", Constants.mainActivity);
  	    capabilities.setCapability("appActivity", Constants.launcherActivity);
  	    AndroidDriver driver = new AndroidDriver(new URL(Constants.APPIUM_SERVER_URL), capabilities);		
  	    return driver;
	}
	
	
	//Config 2nd appium driver
	public static AndroidDriver configJC2() throws MalformedURLException
	{
		try{
			DesiredCapabilities capabilities = new DesiredCapabilities();
		    capabilities.setCapability("deviceName",Constants.S4Name);
		    capabilities.setCapability(CapabilityType.BROWSER_NAME,  "");
		    capabilities.setCapability("platformVersion", Constants.S4Version);
		    capabilities.setCapability("platformName", "Android");
		    capabilities.setCapability("udid", Constants.UDID2);
		    capabilities.setCapability("appPackage", Constants.appPackage);
		    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		    capabilities.setCapability("appActivity", Constants.launcherActivity);
		    capabilities.setCapability("appWaitActivity", Constants.mainActivity);	    
		    AndroidDriver driverB = new AndroidDriver(new URL(Constants.APPIUM_SERVER_URL2), capabilities);
		    return driverB;
		}catch(Exception e){
			throw new SkipException("configuration error in DriverB");
		}
		
	}

}
