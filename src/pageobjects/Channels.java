package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import utilities.CommonUtils;
import utilities.Locators;

public class Channels {
	public static void openPage(AndroidDriver driver) throws Exception {
		CommonUtils.openTab(driver, "Channels");
	}

	public static boolean verifyPage(AndroidDriver driver) {
		if (CommonUtils.findTitle(driver).equalsIgnoreCase("Channels")) {
			return true;
		} else {
			return false;
		}
	}

	public static String followFirstUnfollowedPublicChannel(AndroidDriver driver) throws Exception {
		List<WebElement> channelList;
		String channelName = null;
		try {
			channelList = driver.findElements(By.id(Locators.ChannelInChannelList_ID));
		} catch (NoSuchElementException ex) {
			throw new Exception("Channel list is not available");

		}
		for (int channelcount = 0; channelcount < 10; channelcount++) {
			try {

				channelList.get(channelcount).click();

				if (driver.findElements(By.name("Follow")).size() > 0) {
					channelName = driver.findElement(By.id(Locators.ChannelName_ID)).getText();
					System.out.println(channelName);
					driver.findElement(By.name("Follow")).click();
					driver.navigate().back();
					break;
				} else {
					driver.findElement(By.id(Locators.BackButton_ID)).click();
					continue;
				}

			} catch (NoSuchElementException ex) {
				channelName = "No public channel Found";
				driver.findElement(By.id(Locators.BackButton_ID));
				continue;
			}
		}
	return channelName;
	}
	
	public static void unfollowChannel(AndroidDriver driverD, String channel) throws Exception{
		Contacts.openChannels(driverD);
		Contacts.openChannelfromChannels(driverD, channel);
		CommonUtils.clickTitle(driverD);
		CommonUtils.clickMoreOptions(driverD);
		driverD.findElement(By.name("Unfollow")).click();
		Thread.sleep(1000);
		driverD.findElement(By.name("Unfollow")).click();
		CommonUtils.navigateBack(driverD);
		
	}
	
	

	
	public static void openSpecificChannel(AndroidDriver driverD, String channelName){
		try {
			driverD.findElement(By.name(channelName)).click();
		} catch (Exception e) {
			driverD.scrollTo(channelName);
			driverD.findElement(By.name(channelName)).click();
		}
	}

	public static void searchChannel(AndroidDriver driverD,String channelName){
		driverD.findElement(By.id(Locators.SearchChannelBox_ID)).sendKeys(channelName);
	}

	

}
