package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.android.AndroidDriver;
import utilities.CommonUtils;
import utilities.Locators;

public class More {
	public static void openPage(AndroidDriver driver) throws NoSuchElementException {
		CommonUtils.openTab(driver, "More");
	}

	public static boolean verifyPage(AndroidDriver driver) {
		if (CommonUtils.findTitle(driver).equalsIgnoreCase("More")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	public static void openProfile(AndroidDriver driverD) throws NoSuchElementException {
		driverD.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driverD.findElement(By.id(Locators.ProfileIconInMore_ID)).click();

	}
	
	public static void openStickerStore(AndroidDriver driverD) throws NoSuchElementException{
		driverD.findElement(By.name("Sticker Store")).click();
	}
	
	public static void openSettings(AndroidDriver driverD) throws NoSuchElementException{
		driverD.findElement(By.name("Settings")).click();
	}
	
	
	public static void navigateBackToMore(AndroidDriver driverD) {
		int i = 0;
		while (i < 2 && (!CommonUtils.findTitle(driverD).equalsIgnoreCase("More"))) {
			i = i + 1;
			driverD.navigate().back();
		}
	}
}
