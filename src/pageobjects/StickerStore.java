package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import utilities.CommonUtils;
import utilities.Locators;

public class StickerStore {
	public static boolean verifyPage(AndroidDriver driver) {
		if (CommonUtils.findTitle(driver).equalsIgnoreCase("Sticker Store")) {
			return true;
		} else {
			return false;
		}
	}

	public static void navigateBackToMore(AndroidDriver driverD) {
		CommonUtils.navigateBack(driverD);
	}

	public static String stickerDownload(AndroidDriver driverD) {
		String stickername;
		stickername = driverD.findElements(By.id(Locators.StickerDownloadButton_ID)).get(1).getText();
		driverD.findElements(By.id(Locators.StickerDownloadButton_ID)).get(1).click();
		System.out.println("Sticker downloading started");
		long startTime = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(driverD, 50000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(Locators.StickerDownloaded_ID)));
		long endTime = System.currentTimeMillis();
		long actualTime = (endTime - startTime) / 1000;
		System.out.println(CommonUtils.timestamp() + "Sticker " + stickername + "downloaded in " + actualTime);
		return stickername;
	}

	public static boolean verifystickerDownloaded(AndroidDriver driverD, String stickername) {
		boolean status = false;
		try {
			CommonUtils.clickMoreOptions(driverD);
			if (driverD.findElements(By.id(Locators.MyStickersName_ID)).size() > 0) {
				List<WebElement> stickers = driverD.findElements(By.id(Locators.MyStickersName_ID));

				for (WebElement sticker : stickers) {
					if (sticker.getText().equals(stickername)) {
						status = true;
						break;
					}
				}
			}
			CommonUtils.navigateBack(driverD);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem verifying sticker \n stacktrace" + e.getMessage() + e.getStackTrace());
		}

		return status;
	}

	public static void deleteSticker(AndroidDriver driverD, String stickername) {
		try {
			CommonUtils.clickMoreOptions(driverD);
			while (driverD.findElements(By.id(Locators.StickerDelete_ID)).size() != 0) {
				driverD.findElements(By.id("com.jiochat.jiochatapp:id/item_emoticon_arrow")).get(0).click();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem deleting sticker \n stacktrace" + e.getMessage() + e.getStackTrace());
		}
	}

}
