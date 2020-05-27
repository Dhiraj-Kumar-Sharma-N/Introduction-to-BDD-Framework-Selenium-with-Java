package com.aa.cme.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.Result.Type;
import cucumber.api.Scenario;
import cucumber.runtime.StepDefinition;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.NodeList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class UtilityFunctions {

	// Declare variables
	static HSSFWorkbook excel_Workbook;
	static HSSFSheet excel_sheet;
	static HSSFRow row;
	static HSSFCell cell;
	public static WebDriver driver;
	private int intObjectSyncTimeOut = 90;
	private int intBrowserSyncTimeOut = 180;
	protected static int DEFAULT_ELEMENT_WAIT_TIME_SECONDS = 60;
	private static Properties properties;
	private Select select;
	private Actions action;
	private Alert alert;
	private DocumentBuilderFactory builderFactory;
	private DocumentBuilder builder;
	private org.w3c.dom.Document xmlDocument;
	private NodeList nodeList;
	private XPath xPath;
	private String data;
	private int ActiverecordCount = 0;
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	public static String strTestCaseName;
	public static String ModuleName;
	public static String DateAndTime;
	public static ExtentTest Scenario;
	public static String ManualSubTailNum;
	public static String tailNumber;
	public static String flightNumber;
	private static String filePath = "Execution_Report" + File.separator + "/CME_Teams.html";
	public static final ArrayList<ArrayList<String>> reports = new ArrayList<>();
	public static final String reportSheet = "sheet1";

	// == TO INITIALIZE WEBDRIVER AND LAUNCH URL == //

	public WebDriver initilizeDriver(String BrowserName) {
		String Wrapper_Path = null;

		try {
			if (BrowserName.trim().equalsIgnoreCase("chrome")) {
				//Create a map to store  preferences 
				Map<String, Object> prefs = new HashMap<String, Object>();

				//add key and value to map as follow to switch off browser notification
				//Pass the argument 1 to allow and 2 to block
				prefs.put("profile.default_content_setting_values.notifications", 2);
			
				ChromeOptions cOptions = new ChromeOptions();
			
				cOptions.setExperimentalOption("prefs", prefs);
				cOptions.addArguments("test-type");
				cOptions.addArguments("start-maximized");
				cOptions.addArguments("--js-flags=--expose-gc");
				cOptions.addArguments("--enable-precise-memory-info");
				cOptions.addArguments("--disable-popup-blocking");
				cOptions.addArguments("--disable-default-apps");
				cOptions.addArguments("--disable-extensions");
				cOptions.addArguments("disable-infobars");
				cOptions.addArguments("--incognito");
				System.setProperty(readConfigurationFile("Browser.DriverName"),
						readConfigurationFile("Browser.DriverPath"));
				driver = new ChromeDriver(cOptions);
				driver.manage().window().maximize();
				driver.get(readConfigurationFile("Browser.BaseURL"));
				Thread.sleep(3000);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(intBrowserSyncTimeOut, TimeUnit.SECONDS);
				return driver;
			}
			if (BrowserName.trim().equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "./ConnectMe_MSAutomation/WebDrivers/IEDriverServer.exe");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "accept");
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability("requireWindowFocus", true);// to move mouse manually
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get(readConfigurationFile("Browser.BaseURL"));
				return driver;
			}
			if (BrowserName.trim().equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.ie.driver", "./ConnectMe_MSAutomation/WebDrivers/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver = new InternetExplorerDriver();
				return driver;
			} else if (BrowserName.trim().equalsIgnoreCase("DesktopApp")
					|| BrowserName.trim().equalsIgnoreCase("DesktopApp")) {
				ChromeOptions options = new ChromeOptions();
				Proxy proxy = new Proxy();
				proxy.setHttpProxy("localhost:3128");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("network.proxy.type", 1);
				cap.setCapability("network.proxy.http", "localhost");
				cap.setCapability("network.proxy.http_port", 3128);
				cap.setCapability("dom.max_script_run_time", 30000);
				cap.setCapability("network.proxy.no_proxies_on",
						"adadvisor.net, intuit.com, doubleclick.net, doubleclick.com, google.com, webengage.com, demdex.net");
				options.setBinary(Wrapper_Path);
				System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
				driver = new ChromeDriver(options);
				Thread.sleep(10000);
				return driver;

			} else {
				System.out.println("Invalid Launch Type");
			}
		} catch (Exception e) {
			System.err.println("Failed to Launch the Application");
			System.out.println(e);
		}

		return null;
	}

	// == TO READ THE CONFIGURATION FILE USING THE STRING NAME AS ARGUMENT == //

	public static String readConfigurationFile(String key) {
		try {
			properties = new Properties();
			properties.load(new FileInputStream("./Test_Configuration/Config.properties"));
			if (key.length() > 0) {
				return properties.getProperty(key).trim();
			}
		} catch (Exception e) {
			System.out.println(e);

			System.err.println("Could not read property " + key + " from Config file.");
		}
		return null;
	}

	// == TO CLOSE THE BEOWSER INSTANCE == //

	public void closure() {
		driver.quit();
	}
	// == TO KILL THE CHROMEDRIVER PROCESS == //

	public void killBrowser() {
		// To kill the chrome driver after execution.
		try {
			Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// == TO READ THE OBJECT REPOSITORY == //

	public String readObjectRepositoryFile(String key) {
		try {
			properties = new Properties();
			properties.load(new FileInputStream("./Test_ObjectRepository/OR.properties"));
			if (key.trim().length() > 0) {
				return properties.getProperty(key).trim();
			}
		} catch (Exception e) {
			System.out.println(e);

			System.err.print("Could not to read Object property " + key);
		}
		return null;
	}

	// == TO VALIDATE IF OBJECT IS DISPALYED OR NOT == //

	public boolean Validate(String objName) {
		objName = objName.trim();
		String locatorType = null;
		String locator = null;
		boolean res = false;
		// WebDriver driver;
		try {
			if (readObjectRepositoryFile(objName).length() > 0) {
				String[] strpropertyname = readObjectRepositoryFile(objName).split("\\|");
				locatorType = strpropertyname[0].toLowerCase().trim();
				locator = strpropertyname[1].trim();

				if (locatorType.length() < 1) {
					System.err.println("Locator type not specified in OR file.");
				}
				if (driver.findElement(By.xpath(locator)).isDisplayed()) {
					// res = false;
					res = true;
					return res;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			System.err.println("The specified webelement " + objName + " was not found");
		}
		return res;
	}

	// == TO ENTER VALUE IN AN EDIT FIELD USING OBJECTNAME == //

	public boolean enterValueInEditField(String objName, String value) {
		try {
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				tempElement.clear();
				tempElement.sendKeys(new CharSequence[] { value });
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);

			System.err.println("Failed to Enter the text field " + objName);
		}
		return false;
	}

	// == TO ENTER VALUE IN AN EDIT FIELD USING WEBELEMENT == //

	public boolean enterValueInEditField(WebElement webelement, String value) {
		try {
			value = value.trim();
			if ((webelement.isDisplayed()) && (webelement.isEnabled())) {
				webelement.clear();
				webelement.sendKeys(new CharSequence[] { value });
				System.out.println(value + " entered successfully in text field");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.print("Failed to Enter value in the text field");
		}
		return false;
	}
	// == TO CLICK ON ELEMENT USING OBJECTNAME == //

	public boolean clickElement(String objName) {
		try {
			WebElement tempElement = getWebElement(objName);
			Thread.sleep(3000);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				tempElement.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Click on Element  " + objName);
		}
		return false;
	}

	// == TO CLICK ON ELEMENT USING WEBELEMENT == //

	public boolean clickElement(WebElement webelement) {
		try {
			if ((webelement.isDisplayed()) && (webelement.isEnabled())) {
				webelement.click();
				System.out.println("WebElement clicked Successfully");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.print("Failed to Click on WebElement");
		}
		return false;
	}

	// == GET ELEMENT TEXT == //

	public String getElementText(String objName) {
		try {
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.getText().trim().length() > 0)) {
				return tempElement.getText().trim();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("No Text Found for the object " + objName);
		}
		return null;
	}

	public boolean waitForElementVisibility(String objName) {
		// WebDriver driver;

		WebDriverWait wait = new WebDriverWait(driver, intObjectSyncTimeOut);
		try {
			if (readObjectRepositoryFile(objName).length() > 0) {
				String[] strpropertyname = readObjectRepositoryFile(objName).split("\\|");
				if (strpropertyname[0].equalsIgnoreCase("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Visibility");
					return true;
				}

				if (strpropertyname[0].equalsIgnoreCase("name")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Visibility");
					return true;
				}
				if (strpropertyname[0].equalsIgnoreCase("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully");
					return true;
				}
				if (strpropertyname[0].equalsIgnoreCase("linkText")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Visibility");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Could not synchronize " + objName + " for Visibility");
		}
		return false;
	}

	public static ExtentReports getExtent() {
		if (extent != null)
			return extent; // avoid creating new instance of html file
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {

		htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.config().setDocumentTitle("CME_MSTeams Automation Report");
		htmlReporter.config().setReportName("ConnectMe Automation Testing");
		return htmlReporter;
	}

	public static ExtentTest createTest(String name, String description) {
		test = extent.createTest(name, description);
		return test;
	}

	public static void failStep(ExtentTest childTest1, String detail) throws IOException {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			String imageBASE64 = ts.getScreenshotAs(OutputType.BASE64);
			String Destination = "<b style=\"background-color:yellow;\" text-align=\"center\">Mouse Hover Here For Screenshot</b><img align=\"middle\" style=\"width:50px;height:50px;\" src=\"data:image/png;charset=utf-8;base64,"
					+ imageBASE64
					+ "\" onmouseover=\"style.height = '300px';style.width = '600px'\" onmouseout=\"style.height = '50px';style.width = '50px'\"></img>";
			childTest1.info(Destination);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			childTest1.log(Status.INFO, "", MediaEntityBuilder.createScreenCaptureFromPath(TakeScreenShot()).build());
			System.err.println(detail);
			org.junit.Assert.fail(detail);
		}
		extent.flush();
	}

	public void afterMethod(String TestResult,String SceneName,String FeatName,Type scenarioStaus) {
		ArrayList<String> report = new ArrayList<>();
		try {
			report.add(TestResult);
			report.add(currentDateAndTime());
			report.add(FeatName);
			report.add(SceneName);
			report.add(scenarioStaus.toString().substring(0,4));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (report.get(4).equalsIgnoreCase(Status.PASS.toString())) {
				UtilityFunctions.testResult(report);
			} else {
				UtilityFunctions.testResult(report);
			}
		}
	}

	public static void testResult(ArrayList<String> report) {
		try {
			String fn = System.getProperty("user.dir") + UtilityFunctions.readConfigurationFile(report.get(0));
			File file = new File(fn);
			FileInputStream in = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(in);
			XSSFSheet sheet = workbook.getSheet(TestCaseListener.reportSheet);
			int size = sheet.getPhysicalNumberOfRows();
			XSSFRow row = sheet.createRow(size);
			row.createCell(0).setCellValue(report.get(1));
			row.createCell(1).setCellValue(report.get(2));
			row.createCell(2).setCellValue(report.get(3));
			String status = report.get(4);
			Cell cell = row.createCell(3);
			CellStyle css = workbook.createCellStyle();
			if (status.equalsIgnoreCase(Status.PASS.toString())) {
				css.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
				css.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cell.setCellStyle(css);
				cell.setCellValue(status);
			} else {
				css.setFillForegroundColor(IndexedColors.RED.getIndex());
				cell.setCellStyle(css);
				cell.setCellValue(status);
			}
			css = null;
			in.close();
			// File f = new File(fn); // updated
			try (FileOutputStream out = new FileOutputStream(fn)) {
				workbook.write(out);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resultOutputFile(String path, String DateAndTime, String ModuleName, String scriptName,
			String PassFail) throws Exception {

		String fn = System.getProperty("user.dir") + UtilityFunctions.readConfigurationFile(path);
		File file = new File(fn);
		FileInputStream in = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int size = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.createRow(size);
		row.createCell(0).setCellValue(DateAndTime);
		row.createCell(1).setCellValue(ModuleName);
		row.createCell(2).setCellValue(scriptName);
		row.createCell(3).setCellValue(PassFail);
		in.close();

		File f = new File(fn); // <-- FILL HERE

		try (FileOutputStream out = new FileOutputStream(f, false)) {
			workbook.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		// workbook.close();

	}

	// Extract value from the WebElement
	public String getValueUsingXpath(String objName) {
		try {
			WebElement tempElement = getWebElement(objName);
			String value = tempElement.getText();
			if ((tempElement != null) && (tempElement.isEnabled())) {
				return value;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to get the Value of the Element  " + objName);
		}
		return null;
	}

	// Extract Attribute from the WebElement
	public String getValueUsingAttribute(String objName, String attribute) {
		try {
			WebElement tempElement = getWebElement(objName);
			String value = tempElement.getAttribute(attribute);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				return value;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to get the Value of the Element  " + objName);
		}
		return null;
	}

	public static String futureDate(int NoOfDays) {
		String res = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			Date today = calendar.getTime();
			calendar.add(Calendar.DAY_OF_YEAR, NoOfDays);
			res = dateFormat.format(calendar.getTime());
			System.out.println("tomorrow: " + res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// to enter value into an edit field
	public boolean enterValueInEditField(String objName, String value, WebDriver passeddriver) {
		WebDriver driver;
		driver = passeddriver;
		try {
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				tempElement.clear();
				tempElement.sendKeys(new CharSequence[] { value });
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);

			System.err.println("Failed to Enter the text field " + objName);
		}
		return false;
	}

	public boolean clickElement(String objName, WebDriver passeddriver) {
		WebDriver driver;
		driver = passeddriver;
		try {
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				tempElement.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Click on Element  " + objName);
		}
		return false;
	}

	public boolean selectCheckBox(String objName, String status) {
		try {
			WebElement tempElement = getWebElement(objName);
			if (tempElement != null) {
				if (status.equalsIgnoreCase("On")) {
					if (tempElement.isEnabled()) {
						if (!tempElement.isSelected()) {
							tempElement.click();
							System.out.println("Check box " + objName + " checked");
							return true;
						}
						System.out.println("Check box " + objName + " is already checked");
						return true;
					}

				} else if ((status.equalsIgnoreCase("Off")) && (tempElement.isEnabled())) {
					if (tempElement.isSelected()) {
						tempElement.click();
						System.out.println("Check box " + objName + " Unchecked");
						return true;
					}
					System.out.println("Check box " + objName + " is already Unchecked");
					return true;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Check/Uncheck the check box");
		}
		return false;
	}

	public boolean selectCheckBox(WebElement webelement, String status) {
		try {
			if (status.equalsIgnoreCase("On")) {
				if ((webelement.isDisplayed()) && (webelement.isEnabled())) {
					if (!webelement.isSelected()) {
						webelement.click();
						System.out.println("Check box checked");
						return true;
					}
					System.out.println("Check box is already checked");
					return true;
				}

			} else if ((status.equalsIgnoreCase("Off")) && (webelement.isDisplayed()) && (webelement.isEnabled())) {
				if (webelement.isSelected()) {
					webelement.click();
					System.out.println("Check box Unchecked");
					return true;
				}
				System.out.println("Check box is already Unchecked");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Check/Uncheck the check box");
		}
		return false;
	}

	public boolean selectItemFromListBoxByValue(String objName, String value) {
		value = value.trim();
		try {
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				select = new Select(tempElement);
				select.selectByValue(value);
				System.out.println(value + " selected from the listbox " + objName);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Select item " + value + " from the list box");
		}
		return false;
	}

	public boolean selectItemFromListBoxByText(String objName, String Text) {
		Text = Text.trim();
		try {
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				select = new Select(tempElement);
				select.selectByVisibleText(Text);
				System.out.println(Text + " selected from the listbox " + objName);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Select item " + Text + " from the list box");
		}
		return false;
	}

	public boolean selectItemFromListBoxByIndex(String objName, int index) {
		try {
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				select = new Select(tempElement);
				select.selectByIndex(index);
				System.out.println("Index " + index + " selected from the listbox " + objName);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Select item index " + index + " from the list box");
		}
		return false;
	}

	public boolean selectItemFromListBoxByValue(WebElement webelement, String value) {
		value = value.trim();
		try {
			if ((webelement.isDisplayed()) && (webelement.isDisplayed())) {
				select = new Select(webelement);
				select.selectByValue(value);
				System.out.println(value + " selected from the listbox");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Select item " + value + " from the list box");
		}
		return false;
	}

	public boolean selectItemFromListBoxByText(WebElement webelement, String Text) {
		// If text is not null only then select the text.
		if (Text.equals("null")) {
			return false;
		} else {
			Text = Text.trim();
			try {
				if (Text.length() > 0) {
					select = new Select(webelement);
					if ((webelement.isEnabled())) {
						select.selectByVisibleText(Text);
						System.out.println(Text + " selected from the listbox");
						return true;
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());

				System.err.println("Failed to Select item " + Text + " from the list box");
			}
			return true;
		}

	}

	public boolean selectItemFromListBoxByIndex(WebElement webelement, int index) {
		try {
			select = new Select(webelement);
			if ((webelement.isDisplayed()) && (webelement.isDisplayed())) {
				select.selectByIndex(index);
				System.out.println("Index " + index + " selected from the listbox");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Failed to Select item index " + index + " from the list box");
		}
		return false;
	}

	public boolean selectRadioButton(List<WebElement> webelement, String value) {
		value = value.trim();
		try {
			for (WebElement element : webelement) {
				if (element.getAttribute("value").equalsIgnoreCase(value)) {
					element.click();
					System.out.println("Radio Button value " + value + " selected successfully");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.err.println("Failed to Select Radio Button value " + value);
		return false;
	}

	public WebElement getWebElement(String objName) {
		objName = objName.trim();
		int size = 0;
		String locatorType = null;
		String locator = null;
		try {
			if (readObjectRepositoryFile(objName).length() > 0) {
				String[] strpropertyname = readObjectRepositoryFile(objName).split("\\|");
				locatorType = strpropertyname[0].toLowerCase().trim();
				locator = strpropertyname[1].trim();

				if (locatorType.length() < 1) {
					System.err.println("Locator type not specified in OR file.");
				}
				size = driver.findElements(By.xpath(locator)).size();
				// if (size <= 0) break label590;
				if (driver.findElement(By.xpath(locator)).isDisplayed()) {
					return driver.findElement(By.xpath(strpropertyname[1]));
				}
				String str1;
				switch ((str1 = locatorType).hashCode()) {
				case 3355:
					if (str1.equals("id")) {
					}
					break;
				case 3373707:
					if (str1.equals("name")) {
					}
					break;
				case 114256029:
					if (str1.equals("xpath"))
						break;
					break;
				case 228335784:
					if (str1.equals("partiallinktext")) {
					}
					break;
				case 858964706:
					if (str1.equals("cssselector")) {
					}
					break;
				case 1195141159:
					if (!str1.equals("linktext")) {
						// break label590;
						size = driver.findElements(By.xpath(locator)).size();
						// if (size <= 0) break label590;
						if (driver.findElement(By.xpath(locator)).isDisplayed()) {
							return driver.findElement(By.xpath(strpropertyname[1]));
						}

						size = driver.findElements(By.name(locator)).size();
						// if (size <= 0) break label590;
						if (driver.findElement(By.name(locator)).isDisplayed()) {
							return driver.findElement(By.name(locator));
						}

						size = driver.findElements(By.id(locator)).size();
						// if (size <= 0) break label590;
						if (driver.findElement(By.id(locator)).isDisplayed()) {
							return driver.findElement(By.id(locator));
						}
					}

					size = driver.findElements(By.linkText(locator)).size();
					if (size > 0) {
						if (driver.findElement(By.linkText(locator)).isDisplayed()) {
							return driver.findElement(By.linkText(locator));
						}

						size = driver.findElements(By.partialLinkText(locator)).size();
						if (size > 0) {
							if (driver.findElement(By.partialLinkText(locator)).isDisplayed()) {
								return driver.findElement(By.partialLinkText(locator));
							}

							size = driver.findElements(By.cssSelector(locator)).size();
							if ((size > 0) && (driver.findElement(By.cssSelector(locator)).isDisplayed()))
								return driver.findElement(By.cssSelector(locator));
						}
					}
					break;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		System.err.println("The specified webelement " + objName + " was not found");
		return null;
	}

	public boolean mouseHoverMenuItem(List<WebElement> webelement) {
		try {
			action = new Actions(driver);
			for (WebElement element : webelement) {
				action.moveToElement(element).build().perform();
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	public void logResults(String folderName, String step, String status, String description) throws IOException {
		Date date = new Date();
		DateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		String strDate = dateFormat.format(date);
		strDate = strDate.replace("/", "").replace(":", "").replace(" ", "").substring(0, 8);

		String logFileDirectory = readConfigurationFile("logFileDirectory") + folderName + "\\";
		String logFileName = logFileDirectory + folderName + "_" + strDate + ".csv";

		createDirectory(logFileDirectory);
		createFile(logFileName);

		System.out.println("Writing Results..");
		BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(new File(logFileName), true));
		writer.write("[" + dateFormat.format(date) + "]" + "," + step + "," + status + "," + description);
		writer.newLine();
		writer.close();
	}

	public static void createDirectory(String dirPath) {
		File file = new File(dirPath);
		file.mkdirs();
	}

	public static void createFile(String logFileName) throws IOException {
		File file = new File(logFileName);
		file.createNewFile();
	}

	public boolean captureScreenShot(String stepName, String FolderName) {
		try {
			File srcFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("Test_Results\\SnapShots\\" + FolderName + "\\" + stepName + ".jpg"),
					true);
			System.out.println("Screenshot captured successfully");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Failed to capture Screenshot");
		}
		return false;
	}

	public boolean captureObjectLevelScreenShot(String objName, String stepName, String FolderName) {
		objName = objName.trim();
		int imageHeight = 0;
		int imageWidth = 0;
		try {
			WebElement tempElement = getWebElement(objName);

			imageHeight = tempElement.getSize().getHeight();

			imageWidth = tempElement.getSize().getWidth();

			File srcFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			BufferedImage fullimage = ImageIO.read(srcFile);
			Point point = tempElement.getLocation();

			BufferedImage subimg = fullimage.getSubimage(point.getX(), point.getY(), imageWidth, imageHeight);
			ImageIO.write(subimg, "png", srcFile);
			FileUtils.copyFile(srcFile, new File("Test_Results\\SnapShots\\" + FolderName + "\\" + stepName + ".jpg"),
					true);
			System.out.println("Screenshot captured successfully");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Failed to capture Screenshot");
		}
		return false;
	}

	public boolean captureObjectLevelScreenShot(WebElement webelement, String stepName, String FolderName) {
		int imageHeight = 0;
		int imageWidth = 0;
		try {
			imageHeight = webelement.getSize().getHeight();

			imageWidth = webelement.getSize().getWidth();

			File srcFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			BufferedImage fullimage = ImageIO.read(srcFile);
			Point point = webelement.getLocation();

			BufferedImage subimg = fullimage.getSubimage(point.getX(), point.getY(), imageWidth, imageHeight);
			ImageIO.write(subimg, "jpg", srcFile);
			FileUtils.copyFile(srcFile, new File("Test_Results\\SnapShots\\" + FolderName + "\\" + stepName + ".jpg"),
					true);
			System.out.println("Screenshot captured successfully");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Failed to capture Screenshot");
		}
		return false;
	}

	public boolean waitForElementVisibility(String objName, WebDriver passeddriver) {
		WebDriver driver;
		driver = passeddriver;
		WebDriverWait wait = new WebDriverWait(driver, intObjectSyncTimeOut);
		try {
			if (readObjectRepositoryFile(objName).length() > 0) {
				String[] strpropertyname = readObjectRepositoryFile(objName).split("\\|");
				if (strpropertyname[0].equalsIgnoreCase("xpath")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Visibility");
					return true;
				}

				if (strpropertyname[0].equalsIgnoreCase("name")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Visibility");
					return true;
				}
				if (strpropertyname[0].equalsIgnoreCase("id")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully");
					return true;
				}
				if (strpropertyname[0].equalsIgnoreCase("linkText")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Visibility");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Could not synchronize " + objName + " for Visibility");
		}
		return false;
	}

	public boolean waitForElementVisibility(WebElement webelement) {
		WebDriverWait wait = new WebDriverWait(driver, intObjectSyncTimeOut);
		try {
			wait.until(ExpectedConditions.visibilityOf(webelement));
			System.out.println("Object Synchronization Successfull for Visibility");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Could not Synchronize Object for Visibility");
		}
		return false;
	}

	public boolean waitForElementVisibility(WebElement webelement, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, intObjectSyncTimeOut);
		try {
			wait.until(ExpectedConditions.visibilityOf(webelement));
			System.out.println("Object Synchronization Successfull for Visibility");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Could not Synchronize Object for Visibility");
		}
		return false;
	}

	public boolean waitForElementInVisibility(String objName) {
		WebDriverWait wait = new WebDriverWait(driver, intObjectSyncTimeOut);
		try {
			if (readObjectRepositoryFile(objName).length() > 0) {
				String[] strpropertyname = readObjectRepositoryFile(objName).split("\\|");
				if (strpropertyname[0].equalsIgnoreCase("xpath")) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Invisibility");
					return true;
				}

				if (strpropertyname[0].equalsIgnoreCase("name")) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Invisibility");
					return true;
				}
				if (strpropertyname[0].equalsIgnoreCase("id")) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Invisibility");
					return true;
				}
				if (strpropertyname[0].equalsIgnoreCase("linkText")) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(strpropertyname[1])));
					System.out.println(objName + " was synchronized successfully for Invisibility");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Could not synchronize " + objName + " for Invisibility");
		}
		return false;
	}

	public boolean waitForElementInVisibility(By by) {
		WebDriverWait wait = new WebDriverWait(driver, intObjectSyncTimeOut);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			System.out.println("Object Synchronization Successfull for Invisibility");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Could not Synchronize Object for Invisibility");
		}
		return false;
	}

	public boolean doubleClick(String objName, WebDriver passeddriver) {
		driver = passeddriver;
		objName = objName.trim();
		try {
			action = new Actions(driver);
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				action.doubleClick(tempElement).build().perform();
				System.out.println("Double clicked on the element " + objName);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Failed to double click on the element " + objName);
		}
		return false;
	}

	public boolean doubleClick(String objName) {

		objName = objName.trim();
		try {
			action = new Actions(driver);
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.isEnabled())) {
				action.doubleClick(tempElement).build().perform();
				System.out.println("Double clicked on the element " + objName);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Failed to double click on the element " + objName);
		}
		return false;
	}

	public boolean doubleClick(WebElement webelement) {
		try {
			action = new Actions(driver);
			if ((webelement.isDisplayed()) && (webelement.isEnabled())) {
				action.doubleClick(webelement).build().perform();
				System.out.println("Double clicked on the webelement successfully");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Failed to double click on the webelement");
		}
		return false;
	}

	public boolean verifyTextExist(String text) {
		try {
			text.trim();
			if (driver.getPageSource().contains(text)) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("Could not find the text " + text);
		}
		return false;
	}

	public boolean clickBrowserBack() {
		driver.navigate().back();
		return true;
	}

	public String getElementText(String objName, WebDriver passeddriver) {
		WebDriver driver;
		driver = passeddriver;
		try {
			WebElement tempElement = getWebElement(objName);
			if ((tempElement != null) && (tempElement.getText().trim().length() > 0)) {
				return tempElement.getText().trim();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("No Text Found for the object " + objName);
		}
		return null;
	}

	public String getEleAttributeText(String objName) {
		WebDriver driver;
		String str = null;
		try {
			WebElement tempElement = getWebElement(objName);
			if (tempElement != null) {
				str = tempElement.getAttribute("value");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("No Text Found for the object " + objName);
		}
		return str;
	}

	public String verifyAlertText(String objName) {
		try {
			if (isAlertPresent(objName)) {
				alert = driver.switchTo().alert();
				String tempText = alert.getText().trim();
				alert.accept();
				if (tempText.length() > 0) {
					return alert.getText().trim();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("No alert text displayed");
		}
		return null;
	}

	public boolean isAlertPresent(String objName) {
		try {
			WebDriverWait alertWait = new WebDriverWait(driver, 5L);
			if (alertWait.until(ExpectedConditions.alertIsPresent()) != null) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			System.err.println("No alert was present");
		}
		return false;
	}

	public boolean acceptAlert(String objName) {
		try {
			if (isAlertPresent(objName)) {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean dismissAlert(String objName) {
		try {
			if (isAlertPresent(objName)) {
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public String generateRandomNumber() {
		String strRandom = "";
		String strNumbers = "123456789";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		for (int i = 0; i < 8; i++)
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		strRandom = "4" + strRandomNumber.toString();
		return strRandom;
	}

	public String getCurrentPageTitle() {
		if (driver.getTitle().trim().length() > 0) {
			return driver.getTitle().trim();
		}
		return null;
	}

	public int initilizeTestDataSource(String TestCaseName) throws javax.xml.parsers.ParserConfigurationException,
			org.xml.sax.SAXException, IOException, javax.xml.xpath.XPathExpressionException {
		System.out.println("Reading test data...");

		FileInputStream file = new FileInputStream(new File("Test_Data\\testdata.xml"));
		builderFactory = DocumentBuilderFactory.newInstance();
		builder = builderFactory.newDocumentBuilder();
		xmlDocument = builder.parse(file);

		xPath = XPathFactory.newInstance().newXPath();
		strTestCaseName = TestCaseName;
		String expression = "//TestCaseName[@name='" + strTestCaseName + "']/TestRecord[@isActive='Yes']";

		nodeList = ((NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET));
		ActiverecordCount = nodeList.getLength();

		System.out.println("Total number of test data records found " + ActiverecordCount);

		if (ActiverecordCount == 0) {
			System.err.println("No Test Records found. Please verify test data source\n");
			return 0;
		}
		return ActiverecordCount;
	}

	public boolean Validate(String objName, WebDriver passeddriver) {
		objName = objName.trim();
		String locatorType = null;
		String locator = null;
		boolean res = false;
		WebDriver driver;
		driver = passeddriver;
		try {
			if (readObjectRepositoryFile(objName).length() > 0) {
				String[] strpropertyname = readObjectRepositoryFile(objName).split("\\|");
				locatorType = strpropertyname[0].toLowerCase().trim();
				locator = strpropertyname[1].trim();

				if (locatorType.length() < 1) {
					System.err.println("Locator type not specified in OR file.");
				}
				if (driver.findElement(By.xpath(locator)).isDisplayed()) {
					// res = false;
					res = true;
					return res;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			System.err.println("The specified webelement " + objName + " was not found");
		}
		return res;
	}

	public String getTestData(String strFieldName, int recordNumber) {
		try {
			String expression = "//TestCaseName[@name='" + strTestCaseName + "']/TestRecord[@isActive='Yes']/"
					+ strFieldName;

			nodeList = ((NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET));

			data = nodeList.item(recordNumber).getFirstChild().getNodeValue();
		} catch (Exception e) {
			System.err.println(strFieldName + " does not exist. Please verify column name\n");
		}

		return data;
	}

	public String getborderColor(String objName) {
		objName = objName.trim();
		String locatorType = null;
		String locator = null;
		try {
			if (readObjectRepositoryFile(objName).length() > 0) {
				String[] strpropertyname = readObjectRepositoryFile(objName).split("\\|");
				locatorType = strpropertyname[0].toLowerCase().trim();
				locator = strpropertyname[1].trim();

				if (locatorType.length() < 1) {
					System.err.println("Locator type not specified in OR file.");
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		String color = null;
		try {
			color = driver.findElement(By.xpath(locator)).getCssValue("border-color");
		} catch (Exception e) {
			System.out.println("XPATH Exception: " + xPath + " Exception: " + e);
		}

		// System.out.println(color);
		String s1 = color.substring(4, 17);
		// System.out.println(s1);
		StringTokenizer st = new StringTokenizer(s1);
		int r = Integer.parseInt(st.nextToken(",").trim());
		int g = Integer.parseInt(st.nextToken(",").trim());
		int b = Integer.parseInt(st.nextToken(",").trim());
		Color c = new Color(r, g, b);
		String hex = "#" + Integer.toHexString(c.getRGB()).substring(2);
		// System.out.println(hex);
		String col = null;
		if (r == 214 && g == 150 && b == 158) {
			System.out.println("Color is Red!");
			col = "Red";

		}
		return col;
	}

	public int sizeValueOfAnyTag(String objName, String tagName) {
		int numberOfFlights;
		objName = objName.trim();
		String locatorType = null;
		String locator = null;

		if (readObjectRepositoryFile(objName).length() > 0) {
			String[] strpropertyname = readObjectRepositoryFile(objName).split("\\|");
			locatorType = strpropertyname[0].toLowerCase().trim();
			locator = strpropertyname[1].trim();

			if (driver.findElement(By.xpath(locator)).isDisplayed()) {

				WebElement industries = driver.findElement(By.xpath(locator));
				List<WebElement> links = industries.findElements(By.tagName(tagName));
				numberOfFlights = links.size();
				return numberOfFlights;

			} else {
				return 0;
			}
		}
		return 0;
	}


	public int RandomNumGenerate(int minNum, int maxNum) {
		Random rand = new Random();
		return (rand.nextInt(maxNum) + minNum);
	}

	public WebElement createAndGetObject(WebDriver oWebDriver, String strObjectName) {

		String arrProps[] = null;
		WebElement oEle = null;
		try {
			arrProps = readObjectRepositoryFile(strObjectName).split("\\|");
			switch (arrProps[0].toLowerCase()) {
			case "id":
				oEle = driver.findElement(By.id(arrProps[1]));
				break;
			case "xpath":
				oEle = driver.findElement(By.xpath(arrProps[1]));
				break;
			case "cssselector":
				oEle = oWebDriver.findElement(By.cssSelector(arrProps[1]));
				break;
			case "name":
				oEle = oWebDriver.findElement(By.name(arrProps[1]));
				break;
			case "linktext":
				oEle = oWebDriver.findElement(By.linkText(arrProps[1]));
				break;
			default:
				System.err.println("The specified webelement " + strObjectName + " was not found");
			}
			return oEle;
		} catch (Exception e) {
			return null;
		}
	}

	public List<WebElement> createAndGetListObjects(WebDriver oWebDriver, String strObjectName) {
		String arrProps[] = null;
		List<WebElement> oEle = null;
		try {
			arrProps = readObjectRepositoryFile(strObjectName).split("\\|");
			switch (arrProps[0].toLowerCase()) {

			case "xpath":
				oEle = oWebDriver.findElements(By.xpath(arrProps[1]));
				break;
			case "cssselector":
				oEle = oWebDriver.findElements(By.cssSelector(arrProps[1]));
				break;
			case "name":
				oEle = oWebDriver.findElements(By.name(arrProps[1]));
				break;
			case "linktext":
				oEle = oWebDriver.findElements(By.linkText(arrProps[1]));
				break;
			case "classname":
				oEle = oWebDriver.findElements(By.className(arrProps[1]));
				break;
			default:
				System.err.println("The specified webelement " + strObjectName + " was not found");
			}
			return oEle;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean refreshPage() throws Exception {
		boolean res = false;
		try {
			driver.navigate().refresh();
			Thread.sleep(5000);
			res = true;
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String currentDateAndTime() {
		String res = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			res = dateFormat.format(cal.getTime());
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String DateValidation() throws Exception {
		String res = null;
		try {
			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			newDateFormat.applyPattern("d MMMMM yyyy");
			String ModifiedDate = newDateFormat.format(date);
			System.out.println(ModifiedDate);
			return ModifiedDate;
		} catch (Exception e) {
			e.printStackTrace();
			return res;
		}
	}

	public static String SystemDate() throws Exception {
		String res = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			res = dateFormat.format(cal.getTime());
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String captureScreenshot() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

		TakesScreenshot ts = (TakesScreenshot) driver;
		String path = System.getProperty("user.dir") + "\\Test_Results\\Screenshots\\" + dateFormat.format(new Date())
				+ ".png";
		try {
			File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(path));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return path;
	}

	public static boolean minimizeBrowser() {
		boolean res = false;
		try {
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_WINDOWS);
			rob.keyPress(KeyEvent.VK_D);
			rob.keyRelease(KeyEvent.VK_WINDOWS);
			rob.keyRelease(KeyEvent.VK_D);
			res = true;

		} catch (Exception e) {
			e.printStackTrace();
			return res;
		}
		return res;
	}

	public static boolean switchToMsi() {
		boolean res = false;
		try {
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_WINDOWS);
			rob.keyPress(KeyEvent.VK_D);
			rob.keyRelease(KeyEvent.VK_WINDOWS);
			rob.keyRelease(KeyEvent.VK_D);
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			return res;
		}
		return res;
	}

	public void wait(String key, int SECONDS) {
		new WebDriverWait(driver, SECONDS)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(readObjectRepositoryFile(key))));
	}

	public void weblelementWait(By by) {
		new WebDriverWait(driver, DEFAULT_ELEMENT_WAIT_TIME_SECONDS)
				.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void addDelay(int milliseconds) {
		try {
			System.out.println(("adding delay: " + milliseconds + " ms"));
			Thread.sleep(milliseconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public int parseTimeToMinutes(String hourFormat) {
		int minutes = 0;
		String[] split = null;

		try {
			if (hourFormat.contains("/")) {
				String[] Time = hourFormat.split("/");
				split = Time[0].split(":");
				minutes += Integer.parseInt(split[0]) * 60;
				minutes += Integer.parseInt(split[1]);
				return minutes + 720;
			} else {
				split = hourFormat.split(":");
				minutes += Integer.parseInt(split[0]) * 60;
				minutes += Integer.parseInt(split[1]);
				return minutes;
			}
			// minutes += Double.parseDouble(split[2])/60;

		} catch (Exception e) {
			return -1;
		}
	}

	public boolean timeCompare(String msgTime) throws ParseException {
		boolean res = false;
		String formattedTime;
		ZoneId zoneId = ZoneId.of("US/Central");
		LocalTime localTime = LocalTime.now(zoneId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
		formattedTime = localTime.format(formatter);
		System.out.println("Current time" + formattedTime);
		System.out.println("Msg time" + msgTime);
		SimpleDateFormat format = new SimpleDateFormat("H:mm");
		Date date1 = format.parse(formattedTime);
		Date date2 = format.parse(msgTime);
		long difference = date1.getTime() - date2.getTime();
		long diffHours = difference / (60 * 60 * 1000) % 24;
		if (4 > diffHours) {
			res = true;
		}
		System.out.print(diffHours + " hours, ");

		return res;
	}

	protected static String TakeScreenShot() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String path = System.getProperty("user.dir") + "\\Test_Results\\Screenshots\\" + dateFormat.format(new Date())
				+ ".png";
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static boolean ScrollToBottomOfPage() {
		Boolean res = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public WebDriver driverChange(WebDriver passeddriver) {
		return driver = passeddriver;
	}

	public String generateRandomCharacter(int length) {
		String strRandom = "";
		String strCharacters = "abcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(24);
		if (length == 1) {
			for (int i = 0; i < 1; i++)
				strRandomNumber.append(strCharacters.charAt(rnd.nextInt(strCharacters.length())));
			strRandom = strRandomNumber.toString();
			return strRandom;
		} else if (length == 2) {
			for (int i = 0; i < 2; i++)
				strRandomNumber.append(strCharacters.charAt(rnd.nextInt(strCharacters.length())));
			strRandom = strRandomNumber.toString();
			return strRandom;
		} else {
			for (int i = 0; i < 24; i++)
				strRandomNumber.append(strCharacters.charAt(rnd.nextInt(strCharacters.length())));
			strRandom = strRandomNumber.toString();
			return strRandom;
		}
	}

	public String generateRandomNumbers(int length) {
		String strRandom = "";
		String strNumbers = "0123456789";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(10);
		if (length == 1) {
			for (int i = 0; i < 1; i++)
				strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
			strRandom = strRandomNumber.toString();
			return strRandom;
		} else if (length == 2) {
			for (int i = 0; i < 2; i++)
				strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
			strRandom = strRandomNumber.toString();
			return strRandom;
		} else {
			for (int i = 0; i < 10; i++)
				strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
			strRandom = strRandomNumber.toString();
			return strRandom;
		}
	}

	public void clickElement(WebElement element, String message) {
		try {
			element.click();
			System.out.println("Clicked " + message);
		} catch (org.openqa.selenium.ElementNotVisibleException v) {
			System.out.println("---- not visible");
			v.printStackTrace();
			try {
				System.out.println("----- try JS click");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", element);
			} catch (Exception e2) {
				try {
					Actions action = new Actions(driver);
					action.moveToElement(element).perform();
					action.moveToElement(element).click().perform();
				} catch (Exception e3) {
					System.err.println("----- fail JS click");
					e2.printStackTrace();
					System.err.println("Element not visible. Failed to click using JS and Action\n" + message + "\n"
							+ element.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to click element " + message + "\n" + element.toString());
		}
	}

	public void newclickElement(String key, String message) {
		try {
			WebElement button = driver.findElement(By.xpath(readObjectRepositoryFile(key)));
			clickElement(button, message);
		} catch (Exception e) {
			e.printStackTrace();
			// failTest("Unable to find element " + message + "\n" + xPression.toString());
		}
	}

	public boolean isElementPresentNDisplayed(String key) {
		try {
			WebElement element = driver.findElement(By.xpath(readObjectRepositoryFile(key)));

			if (!element.isDisplayed()) {
				System.err.println("Element is NOT displayed : " + key);
				return false;
			} else {
				System.out.println("Element found is displayed : " + key);
				return true;
			}
		} catch (NoSuchElementException e) {
			System.err.println("Element not found for the expression : " + key + ". " + e.toString());
			return false;
		} // Added catch block to handle StaleElementReference Exception
		catch (StaleElementReferenceException e) {
			System.err.println("Stale Element Exception found");
			return false;
		}
	}

	public boolean isElementAttributeEnabled(String key, String expected) {
		try {
			WebElement element = driver.findElement(By.xpath(readObjectRepositoryFile(key)));
			// verify if disabled or not
			if ("enabled".equals(expected)) {
				if (!element.isEnabled()) {
					System.err.println(element + " is disabled");
				} else {
					return true;
				}
			} else if ("disabled".equals(expected)) {
				if (element.isEnabled()) {
					System.err.println(element + " is enabled");
				} else {
					return true;
				}
			} else {
				System.out.println("Invalid input for  enabled attribute: " + expected);
			}
		} catch (Exception e) {
			System.out.println("Invalid input for enabled attribute: " + expected);
		}
		return false;
	}

	public void waitForRefreshCall() {
		try {
			System.out.println(("adding delay: 2mins(Untill Refresh call)"));
			Thread.sleep(120000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static boolean timeCompare(String msgTime, int timeDiff) throws ParseException {
		boolean res = false;
		String formattedTime;
		ZoneId zoneId = ZoneId.of("US/Central");
		LocalTime localTime = LocalTime.now(zoneId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		formattedTime = localTime.format(formatter);
		System.out.println("Current time " + formattedTime);
		System.out.println("Msg time " + msgTime);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(formattedTime);
		Date date2 = format.parse(msgTime);
		long difference = date1.getTime() - date2.getTime();
		long diffHours = difference / (60 * 60 * 1000) % 24;
		if (timeDiff >= diffHours) {
			res = true;
		}
		System.out.print("Given timings is not with in the time range of " + diffHours + " hours.");

		return res;
	}

	public static void highlightElementWebElement(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript(
				"arguments[0].setAttribute('style', 'background: yellow; border: 5px solid red;font-weight: bold;');",
				element);
		Thread.sleep(2000);
		js.executeScript("arguments[0].removeAttribute('style','');", element);
	}

	public static void unHighlightElementWebElement(WebDriver driver, WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(1000);
		js.executeScript("arguments[0].removeAttribute('style','');", element);
	}

	public static void clickOnElementUsingJS(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	
	
	public void CreateExcelReport() {
	String filePath = System.getProperty("user.dir") + File.separator + "Execution_Report" + File.separator;
	File[] reportFiles = new File[] { new File(filePath + "TestResults.xlsx")};
	for (File f : reportFiles) {
		try {
			XSSFWorkbook xssf = new XSSFWorkbook(new FileInputStream(f));
			int sheetIndex = xssf.getSheetIndex(reportSheet);
			if (!(sheetIndex == -1))
				xssf.removeSheetAt(sheetIndex);
			FileOutputStream out = new FileOutputStream(f);
			xssf.createSheet(reportSheet);
			Sheet sheet = xssf.getSheet(reportSheet);
			Row row = sheet.createRow(0);
			CellStyle css = xssf.createCellStyle();
			for (int i = 0; i < 4; i++) {
				css.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
				css.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				css.setBorderBottom(BorderStyle.THICK);
				css.setRightBorderColor(IndexedColors.BLACK.getIndex());
				css.setBorderRight(BorderStyle.THICK);
				row.createCell(i).setCellStyle(css);
			}
			row.getCell(0).setCellValue("DATE");
			row.getCell(1).setCellValue("FEATURE NAME");
			row.getCell(2).setCellValue("SCENARIO/TESTCASE");
			row.getCell(3).setCellValue("STATUS");
			xssf.write(out);
			out.close();
			xssf.close();
		} catch (Exception e) {
			e.printStackTrace();
			continue;
		}

	}

}
}