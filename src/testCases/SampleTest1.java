package testCases;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import utilities.CommonUtils;

public class SampleTest1 {
	AndroidDriver driver;
	AndroidDriver driverB;

	@BeforeTest
	public void checkprecondition() {
		try {
			driver = CommonUtils.launchAppOnTestDevice();
			driverB = CommonUtils.launchAppOnReferenceDevice();
			CommonUtils.checkForWhiteCircle(driver, driverB);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void sampleTestcase(){
		System.out.println("Sample test case 1 been executed");
	}
}
