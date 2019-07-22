package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import utilities.CommonUtils;
import utilities.Constants;
import utilities.Locators;

public class Explore {
	public static void openPage(AndroidDriver driver) throws Exception {
		CommonUtils.openTab(driver, "Explore");
	}

	public static boolean verifyPage(AndroidDriver driver) {
		if (driver.findElements(By.id(Locators.ExploreTitle)).size()>0) {
			return true;
		} else {
			return false;
		}
	}

	public static void openRandomRmcChannel(AndroidDriver driver) throws Exception {
		// TODO Auto-generated method stub
		int channelNumber = CommonUtils.getRandomNumber(Constants.RMC_CHANNEL_COUNT);
		driver.findElements(By.id(Locators.RMCChannelIcon_ID)).get(channelNumber).click();
		System.out.println(CommonUtils.timestamp() + "Channel number " + channelNumber);
	}

	public static boolean verifyRMCVideo(AndroidDriver driver) throws Exception {

		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		if ((driver.findElementsById(Locators.CoverUpIcon_ID).size() > 0)
				|| (driver.findElementsById(Locators.CoverShareIcon_ID).size() > 0)) {
			return true;
		} else
			return false;
	}

}
