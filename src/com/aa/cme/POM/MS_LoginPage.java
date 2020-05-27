package com.aa.cme.POM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aa.cme.Utilities.UtilityFunctions;

public class MS_LoginPage extends UtilityFunctions {

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

	public boolean EnterUN(String Username) throws IOException {
		boolean res = false;
		try {
			if (utilityFunction.Validate("EMAILTXTBOX") == true) {
				utilityFunction.enterValueInEditField("EMAILTXTBOX", Username);
				if (utilityFunction.Validate("NEXTBTN") == true) {

					utilityFunction.clickElement("NEXTBTN");
					res = true;
				}
			} else {
				System.out.println("Failed to Enter UserName");
				return res;
			}
		} catch (Exception e) {
			System.out.println("Entering UserName Exception" + e);
		}
		return res;
	}

	public boolean EnterPwd(String Password) throws IOException {
		boolean res = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='companyLogo']")));
			if (utilityFunction.Validate("PWDTXTBOX") == true) {
				utilityFunction.enterValueInEditField("PWDTXTBOX", Password);
				if (utilityFunction.Validate("SIGNINBTN") == true) {

					utilityFunction.clickElement("SIGNINBTN");
					res = true;
				}
			} else {
				System.out.println("Failed to Enter Password");
				return res;
			}
		} catch (Exception e) {
			System.out.println("Entering Password Exception" + e);
		}
		return res;
	}

	public boolean ValErrMsg(String ErrMsg, String element) throws IOException {
		boolean res = false;
		try {
			driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
			if (utilityFunction.Validate(element) == true) {
				String Str1 = utilityFunction.getElementText(element);
				if (utilityFunction.Validate("SIGNINBTN") == true) {
					if (Str1.equalsIgnoreCase(ErrMsg)) {
						res = true;
							} 
					else {
				System.out.println("Failed to Validate Error Message");
				return res;
			}
				}
				}
		}	catch (Exception e) {
			System.out.println("Validate Error Msg Exception" + e);
		}
		return res;
	}

	public boolean login(String Username, String Password) throws IOException {
		boolean res = false;
		try {
			if (utilityFunction.Validate("EMAILTXTBOX") == true) {
				utilityFunction.enterValueInEditField("EMAILTXTBOX", Username);

				if (utilityFunction.Validate("NEXTBTN") == true) {

					utilityFunction.clickElement("NEXTBTN");
					{
						if (utilityFunction.Validate("PWDTXTBOX") == true) {
							utilityFunction.enterValueInEditField("PWDTXTBOX", Password);

							utilityFunction.clickElement("SIGNINBTN");
							
							SelectWebApp();
						
							res = true;
								}
							}
						} else {
							System.out.println("Failed to login");
							return res;
						}
			
			}
		} catch (Exception e) {
			System.out.println("Login Failed Exception" + e);
		}
		return res;
	}

	public boolean ValAppPromoPage() throws IOException {
		boolean res = false;
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (utilityFunction.Validate("WINAPPBTN") == true && utilityFunction.Validate("WEBAPPBTN")) {
				res = true;
			} else {
				System.out.println("App Promotion Page not displayed");
			}
		} catch (Exception e) {
			System.out.println("Validate App Promotion Page Exception" + e);
		}
		return res;
	}

	public boolean SelectWebApp() throws IOException {
		boolean res = false;
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (utilityFunction.Validate("WEBAPPBTN") == true) {

				utilityFunction.clickElement("WEBAPPBTN");
				res = true;
			} else {
				System.out.println("Web App Link not displayed");
			}
		} catch (Exception e) {
			System.out.println("Selecting WebApp Link Exception" + e);
		}
		return res;
	}

	public boolean ValSignIn() throws IOException {
		boolean res = false;
		try {
			driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
			if (utilityFunction.Validate("CHATPANELTEAMICON") == true
					&& utilityFunction.Validate("USERPROFICON") == true) {
				if (utilityFunction.Validate("CMEICON") == true && utilityFunction.Validate("TEAMSLIST") == true) {
					res = true;
				}
			} else {
				System.out.println("Sign In Page not displayed");
			}
		} catch (Exception e) {
			System.out.println("Validate Successful SignIn Exception" + e);
		}
		return res;
	}

	public boolean SignOut() throws IOException {
		boolean res = false;
		try {
			driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
			if (utilityFunction.Validate("USERPROFICON") == true)
				utilityFunction.clickElement("USERPROFICON");
			Thread.sleep(3000);
			if (utilityFunction.Validate("SIGNOUT")) {
				utilityFunction.clickElement("SIGNOUT");
				res = true;
			} else {
				System.out.println("Sign out Failed");
			}
		} catch (Exception e) {
			System.out.println("Clicking SignOut Exception" + e);
		}
		return res;
	}

	public boolean ValSignOut() throws IOException {
		boolean res = false;
		String str = "Sign out";
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(10000);
			if (driver.getTitle().equalsIgnoreCase(str)) {
				res = true;
			} else {
				System.out.println("Sign out Failed");
			}
		} catch (Exception e) {
			System.out.println("Validate Successful SignOut Exception" + e);
		}
		return res;
	}

	public boolean launchNewTab(String Url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean res = false;
		if (Url != null) {
			js.executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get(readConfigurationFile(Url));
			res = true;
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
}
