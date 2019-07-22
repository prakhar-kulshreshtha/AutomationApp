package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import utilities.CommonUtils;
import utilities.Locators;

public class Contacts {
	public static void openPage(AndroidDriver driver) throws Exception {
		CommonUtils.openTab(driver, "Contacts");
	}

	public static boolean verifyPage(AndroidDriver driver) {
		if (CommonUtils.findTitle(driver).equalsIgnoreCase("Contacts")) {
			return true;
		} else {
			return false;
		}
	}

	public static void openGroup(AndroidDriver driverD, String groupname) {
		driverD.findElement(By.id(Locators.GroupsInContacts_ID)).click();
		try {
			driverD.findElement(By.name(groupname)).click();
		} catch (Exception e) {
			driverD.scrollTo(groupname);
			driverD.findElement(By.name(groupname)).click();
		}
		try {
			driverD.findElement(By.id(Locators.MessageOptionInSelectedContact_ID)).click();
		} catch (Exception e) {
			// driverD.scrollTo("Profile");
			driverD.findElement(By.id(Locators.MessageOptionInSelectedContact_ID)).click();
		}
	}

	public static void openChannels(AndroidDriver driverD) {
		driverD.findElement(By.id(Locators.ChannelsInContacts)).click();
	}

	public static boolean searchChannelinChannels(AndroidDriver driverD, String channelname)
			throws NoSuchElementException {

		List<WebElement> channelList = driverD.findElements(By.id(Locators.ChannelInChannelList_ID));
		for (int channelcount = 0; channelcount < 10; channelcount++) {
			if (channelList.get(channelcount).getText().equals(channelname)) {
				return true;
			}
		}
		return false;
	}
	
	public static void openChannelfromChannels(AndroidDriver driverD, String channelname)
			throws NoSuchElementException {
		try {
			driverD.findElement(By.name(channelname)).click();
		} catch (Exception e) {
			driverD.scrollTo(channelname);
			driverD.findElement(By.name(channelname)).click();
		}

	}

	public static void launchChatSession(AndroidDriver driver, String contact) throws Exception {
		try {
			driver.findElement(By.name(contact)).click();
		} catch (Exception e) {
			driver.scrollTo(contact);
			driver.findElement(By.name(contact)).click();
		}
		try {
			driver.findElement(By.id(Locators.MessageOptionInSelectedContact_ID)).click();
		} catch (Exception e) {
			driver.scrollTo("Profile");
			driver.findElement(By.id(Locators.MessageOptionInSelectedContact_ID)).click();
		}
	}

	public static void navigateBackToContacts(AndroidDriver driverD) {
		int i = 0;
		while (i < 2 && (!CommonUtils.findTitle(driverD).equalsIgnoreCase("Contacts"))) {
			i = i + 1;
			driverD.navigate().back();
		}
	}

	public static void searchContact(AndroidDriver driverD, String contact) {
		driverD.findElement(By.id(Locators.SearchBar)).sendKeys(contact);
	}
}
