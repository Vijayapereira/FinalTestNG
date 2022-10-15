package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
	static WebDriver driver;
	static String browser;
	static String url;

	public static void readConfig() {
		try {
			InputStream input;
			input = new FileInputStream("src\\main\\java\\config\\config.properties");

			Properties prop = new Properties();
			prop.load(input);

			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static WebDriver init() {

		readConfig();

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return driver;

	}

	public static void tearDown() {

		driver.close();
		driver.quit();

	}
}
