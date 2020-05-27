package com.aa.cme.POM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aa.cme.Utilities.UtilityFunctions;

public class CME_PowAppLoginPage extends UtilityFunctions {

	private UtilityFunctions utilityFunction = new UtilityFunctions();

	public boolean ValLoginPage() throws IOException {
		boolean res = false;
		String str1 = "Sign in to your account";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@alt='Microsoft']")));
			if (driver.getTitle().equalsIgnoreCase(str1)) {
				if (utilityFunction.Validate("EMAILTXTBOX") == true && utilityFunction.Validate("NEXTBTN") == true) {
					if (utilityFunction.Validate("CREATEACCOUNTLINK") == true
							&& utilityFunction.Validate("SIGNINOPT") == true) {
						res = true;
					}
				} else {
					System.out.println("Failed to validate Login Page");
					return res;
				}
			}
		} catch (Exception e) {
			System.out.println("Validate Login Page Exception " + e);
		}
		return res;
	}

	public boolean ClickPowerApp() throws IOException {
		boolean res = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String str = utilityFunction.readObjectRepositoryFile("CHATPANELTEAMICON");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(str)));
			if (utilityFunction.Validate("POWERAPP_ICON") == true) {

				driver.findElement(By.xpath("//*[@id='apps-button']")).click();
				driver.findElement(By.xpath("//*[contains(text(),'ConnectMe Java Api')]//parent::div")).click();
				Thread.sleep(9000);
				res = true;

				/*
				 * utilityFunction.clickElement("POWERAPP_ICON"); res = true;
				 */
			} else {
				System.out.println("PowerApp Icon not displayed");
			}
		} catch (Exception e) {
			System.out.println("Selecting PowerApp Icon Exception" + e);
		}
		return res;
	}

	public boolean ValSTA_WG_ROLE() throws IOException {
		boolean res = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//iframe[@name='embedded-page-container']")));
			driver.switchTo().frame(0);
			driver.switchTo().frame("fullscreen-app-host");
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='appmagic-label-text'])[2]")));
			System.out.println(driver.findElement(By.xpath("(//*[@class='appmagic-label-text'])[2]")).getText());

			if (utilityFunction.Validate("STATIONDRPDWN") == true
					&& utilityFunction.getElementText("STATIONHINTTXT").equals("Select Your Station")) {

				if (utilityFunction.Validate("WGDRPDWN") == true
						&& utilityFunction.getElementText("WGHINTTXT").equals("Select Your Workgroup")) {

					if (utilityFunction.Validate("ROLEDRPDWN") == true
							&& utilityFunction.getElementText("ROLEHINTTXT").equals("Select Your Role")) {
						res = true;
					}
				}
			} else {
				System.out.println("Web App Link not displayed");
			}
		} catch (Exception e) {
			System.out.println("Validate Station,WG & Role Exception" + e);
		}
		return res;
	}

	public static int stepCount(int i) {
		if (i >= 0) {
			int j = i + 1;
			return j;
		}
		return i;
	}

	public boolean ClickStnDrpDwn() throws IOException {
		boolean res = false;
		try {
			/*
			 * driver.switchTo().defaultContent(); WebElement ele =
			 * driver.findElement(By.xpath("//*[@class='toastbody toast-actions']"));
			 * System.out.println(ele.isEnabled()); int width =ele.getSize().getWidth();
			 * Actions action =new Actions(driver);
			 * action.moveToElement(ele).moveByOffset((width/2)-2,
			 * 0).click().build().perform();
			 */
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.switchTo().frame("fullscreen-app-host");
			if (utilityFunction.Validate("STATIONDRPDWN") == true) {
				Thread.sleep(2000);
				utilityFunction.clickElement("STATIONDRPDWN");
				res = true;
			} else {
				System.out.println("Station DropDown was not clicked");
			}
		} catch (Exception e) {
			System.out.println("Clicking Station DropDown Exception" + e);
		}
		return res;
	}

	public boolean ValStationList() throws IOException {
		boolean res = false;
		try {
			ArrayList<String> ExcelStationList = new ArrayList<String>(Arrays.asList("STL", "MCO", "LAX", "LGA"));
			ArrayList<String> AppStationList = new ArrayList<String>();

			if (utilityFunction.Validate("STADRPDWNLIST") == true) {
				Thread.sleep(1000);
				AppStationList = RetAllStations();
				if (AppStationList.containsAll(ExcelStationList)) {
					res = true;
				}

			} else {
				System.out.println("Station List does not Match");
			}
		} catch (Exception e) {
			System.out.println("Validate Station list Exception" + e);
		}
		return res;
	}

	public ArrayList<String> RetAllStations() {
		ArrayList<String> AllStations = new ArrayList<String>();
		try {
			if (utilityFunction.Validate("STADRPDWNLIST") == true) {
				List<WebElement> olist = driver
						.findElements(By.xpath(utilityFunction.readObjectRepositoryFile("STADRPDWNLIST")));
				System.out.println("Total number of Station is = " + olist.size());
				Thread.sleep(1000);
				if (olist.size() > 0) {
					for (WebElement ele : olist) {

						AllStations.add(ele.getText());
					}

				} else {
					System.out.println("Stations are not added to the Array");

				}
			}

		} catch (Exception e) {
			System.out.println("unable to click the dropdown icon for changing the stationG");
		}
		return AllStations;

	}

	public boolean SelectStaWGRole() throws IOException {
		boolean res = false;
		try {
			ClickStnDrpDwn();
			if (utilityFunction.Validate("STADRPDWNLIST") == true) {
				Thread.sleep(1000);			
				driver.findElement(By.xpath("//*[@role='option' and contains(text(),'MCO')]")).click();
				res = true;
			} else {
				System.out.println("Failed to select Desired Station WG & Role");
			}
		} catch (Exception e) {
			System.out.println("Validate Station list Exception" + e);
		}
		return res;
	}

	public boolean ClickSubmitBtn() throws IOException {
		boolean res = false;
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.switchTo().frame("fullscreen-app-host");
			if (utilityFunction.Validate("SUBMITBTN") == true) {

				utilityFunction.clickElement("SUBMITBTN");
				res = true;
			} else {
				System.out.println("Submit Button not clicked");
			}
		} catch (Exception e) {
			System.out.println("Clicking Submit Button Exception" + e);
		}
		return res;
	}

	public boolean ValENRPage() throws IOException {
		boolean res = false;
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.switchTo().frame("fullscreen-app-host");
			if (utilityFunction.Validate("FLIGHTCOUNT") == true) {
				String str = utilityFunction.getElementText("FLIGHTCOUNT");
				String[] splitstr = str.split(" ");
				int i = Integer.parseInt(splitstr[1]);
				if (i > 0) {
					res = true;
				}

			} else {
				System.out.println("Submit Button not clicked");
			}
		} catch (Exception e) {
			System.out.println("Clicking Submit Button Exception" + e);
		}
		return res;
	}

	public boolean ClickLaunchBtn() throws IOException {
		boolean res = false;
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.switchTo().frame("fullscreen-app-host");
			if (utilityFunction.Validate("LAUNCHBTN") == true) {
				utilityFunction.clickElement("LAUNCHBTN");
				Thread.sleep(5000);
				utilityFunction.clickElement("CLOSEICON");

				res = true;
			} else {
				System.out.println("Launch Button not clicked");
			}
		} catch (Exception e) {
			System.out.println("Clicking Launch Button Exception" + e);
		}
		return res;
	}

	public boolean ClickTeamsIcon() throws IOException {
		boolean res = false;
		try {
			driver.switchTo().defaultContent();
			if (utilityFunction.Validate("TEAMSICON") == true) {
				utilityFunction.clickElement("TEAMSICON");
				res = true;
			} else {
				System.out.println("Teams Icon not clicked");
			}
		} catch (Exception e) {
			System.out.println("Clicking Teams Icon Exception" + e);
		}
		return res;
	}

	public boolean ValCMEDashboard() throws IOException {
		boolean res = false;
		try {
			driver.switchTo().defaultContent();
			if (utilityFunction.Validate("FLIGHTLIST") == true) {
				res = true;
			} else {
				System.out.println("CME Dashboard Page not displayed");
			}
		} catch (Exception e) {
			System.out.println("Display CME Dashboard Page Exception" + e);
		}
		return res;
	}

	public boolean ClickStaChannel() throws IOException {
		boolean res = false;
		try {
			driver.switchTo().defaultContent();
			if (utilityFunction.Validate("STNGENTOPIC") == true) {
				utilityFunction.clickElement("STNGENTOPIC");
				Thread.sleep(3000);
				res = true;
			} else {
				System.out.println("Launch Button not clicked");
			}
		} catch (Exception e) {
			System.out.println("Clicking Launch Button Exception" + e);
		}
		return res;
	}

	public boolean randomsendMessage(String message) {
		boolean res = false;
		try {
			Thread.sleep(1000);
			if (utilityFunction.Validate("MSGEDITBOX")) {
				utilityFunction.enterValueInEditField("MSGEDITBOX", message);
				Thread.sleep(2000);
				utilityFunction.clickElement("SENDMSGBTN");
				res = true;
			} else {
				System.out.println("Failed to send the messages.");
			}
		} catch (Exception e) {
			System.out.println("Failed to send the messages.");
		}
		return res;
	}

	public boolean ValLastSendMsg(String message) {
		boolean res = false;
		try {

			if (utilityFunction.Validate("LASTMSG")) {
				String val = utilityFunction.getElementText("LASTMSG");
				if (message.equalsIgnoreCase(val)) {
					res = true;
				}
			} else {
				System.out.println("Failed to send the messages.");
			}
		} catch (Exception e) {
			System.out.println("Failed to send the messages.");
		}
		return res;
	}
}
