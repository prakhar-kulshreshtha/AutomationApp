package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import utilities.CommonUtils;
import utilities.Locators;

public class Chats {

	public static void openPage(AndroidDriver driver) throws Exception {
		CommonUtils.openTab(driver, "Chats");
	}

	public static boolean verifyPage(AndroidDriver driver) {
		if (CommonUtils.findTitle(driver).equalsIgnoreCase("Chats")) {
			return true;
		} else {
			return false;
		}
	}

	public static void openRecentChat(AndroidDriver driverD, String contactName) {
		List<WebElement> tabslist = driverD.findElements(By.id(Locators.RecentChats_ID));
		for (WebElement tab : tabslist) {
			if (tab.getText().equals(contactName)) {
				tab.click();
				break;
			} else
				continue;
		}
	}

	public static void navigateBackToChats(AndroidDriver driverD) {
		int i = 0;
		while (i < 2 && (!CommonUtils.findTitle(driverD).equalsIgnoreCase("Chats"))) {
			i = i + 1;
			driverD.navigate().back();
		}
	}

	public static void MoreOptions(AndroidDriver driverD) {
		driverD.findElements(By.id(Locators.MoreOrAttachmentButton_ID)).get(1).click();
	}

	public static void searchChat(AndroidDriver driverD, String text) {
		driverD.findElements(By.id(Locators.MoreOrAttachmentButton_ID)).get(0).click();
		driverD.findElement(By.id(Locators.SearchBar)).sendKeys(text);
	}
	

}
