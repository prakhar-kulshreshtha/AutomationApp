package utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CommonUtils {
	public static String sendStatus = null;

	public static boolean networkUnavailableCheckOnStart(AndroidDriver driver) {
		try {
			driver.findElement(By.name("Network unavailable, please check the Network Settings."));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean networkUnavailablePopUpOnCall(AndroidDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.name("Network unavailable,check network."));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String findTitle(AndroidDriver driver) {
		try {
			return driver.findElement(By.id(Locators.Title_ID)).getText();
		} catch (Exception e) {

		}
		return "AppClosed";
	}

	public static void clickTitle(AndroidDriver driver) throws NoSuchElementException {
		driver.findElement(By.id(Locators.Title_ID)).click();
		;

	}

	public static boolean presenceOfWhiteCircle(AndroidDriver driver) {
		try {
			driver.findElement(By.id(Locators.NetworkLoadingCircle));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean presenceOfProgressBarInStartup(AndroidDriver driver) {
		try {
			driver.findElement(By.id(Locators.ProgressBarInStartupID));
			return true;
		} catch (NoSuchElementException notConnected) {

			return false;
		}
	}

	public static boolean presenceOfProgressStatus(AndroidDriver driver) {
		try {
			driver.findElement(By.id(Locators.MSG_Sending_Progress_id));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void sendText(WebDriver driver, String message) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id(Locators.EmoticonButtonInChat_ID)).click();
		driver.findElement(By.id(Locators.EditTextInChat_ID)).sendKeys(message);
		driver.findElement(By.id(Locators.SendButtonInChat_ID)).click();
	}

	public static boolean checkPresenceOfRedExclamation(AndroidDriver driver) {
		try {
			driver.findElement(By.id(Locators.MSG_Not_Sent_Status_id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean checkSentMsgStatus(AndroidDriver driver) {
		try {
			driver.findElement(By.id(Locators.MSG_Sent_Status_id));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static String sentStatus(AndroidDriver driver) throws InterruptedException {

		boolean sentMsgStatus = checkSentMsgStatus(driver);
		System.out.print("sentMsgStatus :" + sentMsgStatus);
		if (sentMsgStatus)
			sendStatus = Constants.MSGSent;
		else {
			// Check send Status
			boolean status = presenceOfProgressStatus(driver);
			System.out.print("Presence OF watch :" + status);
			if (status)
				Thread.sleep(50000);
			sentMsgStatus = checkSentMsgStatus(driver);
			System.out.print("sentMsgStatus :" + sentMsgStatus);
			if (sentMsgStatus)
				sendStatus = Constants.MSGSent;
			else {
				boolean redMarkStatus = checkPresenceOfRedExclamation(driver);
				System.out.print("redMarkStatus :" + redMarkStatus);
				if (redMarkStatus) {
					Thread.sleep(40000);
					if (checkSentMsgStatus(driver))
						sendStatus = Constants.MSGSent;
					else
						sendStatus = Constants.MSGNotSent;
				}
			}
		}

		return sendStatus;

	}

	public static boolean verifyMsgText(AppiumDriver receiverD, String sentMsg) throws InterruptedException {
		// Verification start
		Thread.sleep(500);
		receiverD.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Check Total number of messages present
		List<WebElement> msgs = receiverD.findElements(By.id("com.jiochat.jiochatapp:id/tail_main_tv"));

		if (msgs != null && msgs.size() != 0) {
			System.out.print("Size " + msgs.size());
			// Total number of messages
			int i = msgs.size();

			// Last message
			String lastMsg = "";

			lastMsg = msgs.get(i - 1).getText();

			System.out.println("lastMsg " + lastMsg);
			System.out.println("  " + "SENT MESSAGE :" + sentMsg);
			int msgSize = lastMsg.length();
			// lastMsg = lastMsg.substring(0, msgSize - 1);
			System.out.println("lastMsg :" + lastMsg);
			// Verify
			// Add code for DriverL to send message
			if (sentMsg.equalsIgnoreCase(lastMsg)) {
				System.out.print("TExt Match");
				return true;
			} else {
				System.out.print("TExt Not Match");
				return false;
			}

			// Verification End

		} else
			return false;
	}

	public static void takeScreenshot(WebDriver driver, String testCaseName) throws Exception {

		try {

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scrFile, new File(Constants.Path_ScreenShot + testCaseName + ".jpg"));

		} catch (Exception e) {
			System.out.print("Screen shot fail");
			throw new Exception();

		}

	}

	//////////////////////////////////////////////////////////////
	/*
	 * public static void hitButtonLocatedAt(AppiumDriver driver, int x, int y)
	 * { HashMap<String, Integer> addCall = new HashMap<String, Integer>();
	 * addCall.put("x", x); // in pixels from left addCall.put("y", y); // in
	 * pixels from top driver.executeScript("mobile: tap", addCall); }
	 */
	///////////////////////////////////////////////////////////////////
	public static void clickMoreOptionsInChat(AndroidDriver driverD) throws Exception {
		driverD.findElements(By.id("com.jiochat.jiochatapp:id/nav_bar_action_visiable_item")).get(1).click();
	}

	public static void clickMoreOptions(AndroidDriver driverD) throws Exception {
		driverD.findElement(By.id("com.jiochat.jiochatapp:id/nav_bar_action_visiable_item")).click();
	}

	public static void clearSingleChatHistory(AndroidDriver driver) throws Exception {
		clickMoreOptionsInChat(driver);
		Thread.sleep(200);
		driver.findElement(By.name("Chat Information")).click();
		driver.findElement(By.name("Clear Chat History")).click();
		driver.findElement(By.id(Locators.OK_ClearChat_ID)).click();
		Thread.sleep(500);
		driver.navigate().back();

	}

	public static void clearSingleChatHistory2(AndroidDriver driver) throws Exception {
		clickMoreOptionsInChat(driver);
		Thread.sleep(200);
		driver.findElement(By.name("Chat Information")).click();
		driver.findElement(By.name("Clear Chat History")).click();
		driver.findElement(By.id(Locators.OK_ClearChat_ID)).click();
		Thread.sleep(500);
		driver.navigate().back();

	}

	public static void openTab(AndroidDriver driver, String tabName) throws NoSuchElementException {
		List<WebElement> tabslist = driver.findElements(By.id(Locators.TabLabelsID));
		for (WebElement tab : tabslist) {
			if (tab.getText().equals(tabName)) {
				tab.click();
				break;
			} else
				continue;
		}
	}

	/*
	 * public static void launchChatSession(AndroidDriver driver, String
	 * contact) throws Exception { openTab(driver, "Contacts"); try {
	 * driver.findElement(By.name(contact)).click(); } catch (Exception e) {
	 * driver.scrollTo(contact); driver.findElement(By.name(contact)).click(); }
	 * try {
	 * driver.findElement(By.id(Locators.MessageOptionInSelectedContact_ID)).
	 * click(); } catch (Exception e) { driver.scrollTo("Profile");
	 * driver.findElement(By.id(Locators.MessageOptionInSelectedContact_ID)).
	 * click(); } }
	 */
	public static void unlinkDevice(AndroidDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			openTab(driver, "More");
			driver.findElement(By.name("Settings")).click();
			driver.findElement(By.id(Locators.AccountOptionInSettings_ID)).click();

			driver.findElement(By.id(Locators.UnlinkDeviceOption_ID)).click();

			driver.findElement(By.id(Locators.OK_ClearChat_ID)).click();

		} catch (Exception e) {
			System.out.println("Problem in Unlinking device");
		}
	}

	public static boolean verifySticker(AppiumDriver receiverD) throws InterruptedException {
		// Verification start
		Thread.sleep(500);
		receiverD.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Check Total number of messages present
		List<WebElement> msgs = receiverD.findElements(By.id(Locators.StickerInChat_ID));

		if (msgs != null && msgs.size() != 0) {
			System.out.print("Size " + msgs.size());
			// Total number of messages
			int i = msgs.size();

			// Last message
			// String lastMsg = "";
			//
			// lastMsg = msgs.get(i-1).getText();
			System.out.println("Number of stickers recieved" + i);
			// Verification End
			return true;

		} else
			return false;
	}

	/*
	 * public static String followPublicChannel(AndroidDriver driver) throws
	 * Exception { List<WebElement> channelList; String channelName = null; try
	 * { channelList =
	 * driver.findElements(By.id(Locators.ChannelInChannelList_ID)); } catch
	 * (NoSuchElementException ex) { throw new Exception(
	 * "Channel list is not available");
	 * 
	 * }
	 * 
	 * for (int channelcount = 0; channelcount < 10; channelcount++) { try {
	 * 
	 * driver.findElements(By.id(Locators.ChannelInChannelList_ID))
	 * .get(channelcount).click();
	 * 
	 * if (driver.findElements(By.name("Follow")).size() > 0) { channelName =
	 * driver.findElement(By.id(Locators.ChannelName_ID)).getText();
	 * System.out.println(channelName);
	 * driver.findElement(By.name("Follow")).click(); driver.navigate().back();
	 * break; } else {
	 * driver.findElement(By.id(Locators.BackButton_ID)).click(); continue; }
	 * 
	 * } catch (NoSuchElementException ex) { channelName =
	 * "No public channel Found";
	 * driver.findElement(By.id(Locators.BackButton_ID)); continue; } } return
	 * channelName;
	 * 
	 * }
	 */
	/*
	 * public static boolean verifyChannel(AndroidDriver driverD, String
	 * channelName) throws Exception { openTab(driverD, "Chats");
	 * List<WebElement> tabslist = driverD
	 * .findElements(By.id(Locators.RecentChats_ID)); for (WebElement tab :
	 * tabslist) { if (tab.getText().equals("Channels")) { tab.click(); break; }
	 * else continue; } Thread.sleep(1000); List<WebElement> channellist =
	 * driverD .findElements(By.id(Locators.RecentChats_ID)); for (WebElement
	 * curchannel : channellist) { if (curchannel.getText().equals(channelName))
	 * { return true; } else continue; } return false; }
	 */
	public static boolean verifyChatMessage(AndroidDriver driverD, String message) throws NoSuchElementException {
		List<WebElement> chatlist = driverD.findElements(By.id(Locators.RecentChats_ID));
		for (WebElement tab : chatlist) {
			if (tab.getText().equals(Constants.receiverName)) {
				tab.click();
				break;
			} else
				continue;
		}
		if (driverD.findElements(By.name(message)).size() > 0) {
			return true;
		}

		return false;
	}

	public static void openRecentChat(AndroidDriver driverD, String chatname) throws Exception {

		List<WebElement> tabslist = driverD.findElements(By.id(Locators.RecentChats_ID));
		for (WebElement tab : tabslist) {
			if (tab.getText().equals(chatname)) {
				tab.click();
				break;
			} else
				continue;
		}
	}

	public static String sendTextToChannel(AndroidDriver driverD, String channel) throws Exception {
		String message = generateRandomString(30);
		openRecentChat(driverD, channel);
		driverD.findElement(By.id(Locators.KeyboardUpIconInPublicChannel_ID)).click();
		sendText(driverD, message);
		return message;
	}

	/*
	 * public static void unfollowChannel(AndroidDriver driverD, String channel)
	 * throws Exception { openRecentChat(driverD, "Channels");
	 * Thread.sleep(1000); List<WebElement> channellist = driverD
	 * .findElements(By.id(Locators.RecentChats_ID)); for (WebElement curchannel
	 * : channellist) { if (curchannel.getText().equals(channel)) {
	 * curchannel.click();
	 * driverD.findElement(By.id(Locators.BackButton_ID)).click();
	 * driverD.findElement(By.id(Locators.MoreOrAttachmentButton_ID)).click();
	 * driverD.findElement(By.name("Unfollow")).click(); Thread.sleep(1000);
	 * driverD.findElement(By.name("Unfollow")).click();
	 * driverD.navigate().back(); break; } else continue; }
	 * 
	 * }
	 */
	public static void clearRMCcaches(AndroidDriver driverD) throws Exception {
		openTab(driverD, "More");
		driverD.findElement(By.name("Settings")).click();
		System.out.println("Catche size" + driverD.findElement(By.id(Locators.cacheSizeFromExplore_ID)).getText());
		driverD.findElement(By.id(Locators.cacheSizeFromExplore_ID)).click();
		driverD.navigate().back();
	}

	public static String timestamp() {
		String time = "";
		SimpleDateFormat form = new SimpleDateFormat("hh:mm:ss:SS");
		time = form.format(new Date(System.currentTimeMillis()));
		return time;
	}

	/*
	 * public static void launchGroupChat(AndroidDriver driverD, String
	 * groupname) throws Exception { CommonUtils.openTab(driverD, "Contacts");
	 * driverD.findElement(By.id(Locators.GroupsInContacts_ID)).click(); try {
	 * driverD.findElement(By.name(groupname)).click(); } catch (Exception e) {
	 * driverD.scrollTo(groupname);
	 * driverD.findElement(By.name(groupname)).click(); } try {
	 * driverD.findElement(By.id(Locators.MessageOptionInSelectedContact_ID)).
	 * click(); } catch (Exception e) { // driverD.scrollTo("Profile");
	 * driverD.findElement(By.id(Locators.MessageOptionInSelectedContact_ID)).
	 * click(); } }
	 */
	public static String generateRandomString(int RANDOM_STRING_LENGTH) {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber(RANDOM_STRING_LENGTH);
			char ch = Constants.CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public static int getRandomNumber(int limit) {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(limit);
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public static String getlastMessageText(AndroidDriver driver) throws Exception {
		// TODO Auto-generated method stub
		String lastMsg = "";
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Check Total number of messages present
		List<WebElement> msgs = driver.findElements(By.id(Locators.MessageInChat_ID));

		if (msgs != null && msgs.size() != 0) {
			System.out.print("Size " + msgs.size());
			// Total number of messages
			int i = msgs.size();
			// Last message
			lastMsg = msgs.get(i - 1).getText();
			System.out.println("lastMsg " + lastMsg);

		}
		return lastMsg;
	}

	public static void navigateToHomeScreen(AndroidDriver driver, String tab) {
		// TODO Auto-generated method stub
		int i = 0;
		while (i < 2 && (!CommonUtils.findTitle(driver).equalsIgnoreCase(tab))) {
			i = i + 1;
			driver.navigate().back();
			// logger.info("Press Back " + i);

		}
	}

	public static void navigateBack(AndroidDriver driverD) {
		driverD.findElement(By.id("com.jiochat.jiochatapp:id/nav_bar_icon")).click();
	}

	public static AndroidDriver launchAppOnTestDevice() throws MalformedURLException {
		AndroidDriver driver = ConfigApp.configJC();

		boolean status = CommonUtils.networkUnavailableCheckOnStart(driver);
		if (status) {
			System.out.println("Network Unavailable device1 hence closing App");
			driver.quit();
		} else
			System.out.println("Network available on launch");

		return driver;

	}

	public static AndroidDriver launchAppOnReferenceDevice() throws MalformedURLException {

		AndroidDriver driverB = ConfigApp.configJC2();
		boolean status2 = CommonUtils.networkUnavailableCheckOnStart(driverB);
		if (status2) {
			System.out.println("Network Unavailable in device2 hence closing App");
			driverB.quit();
		} else
			System.out.println("Network available in device2 on launch");

		return driverB;
	}

	public static boolean checkForWhiteCircle(AndroidDriver driver, AndroidDriver driverB) {
		if (CommonUtils.presenceOfWhiteCircle(driver) || (CommonUtils.presenceOfWhiteCircle(driverB))) {
			System.out.println("White Circle Present hence closing App on both devices");
			driver.closeApp();
			driverB.quit();
			return false;
		}
		return true;
	}

}
