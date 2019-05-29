package selenium.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test1Selenium {
	
	private static final String PATH = "/Users/alec/Documents/Universidad/Tercero/2Cuatri/chromedriver";
	
	@Test
	public void testFirefox() {
		System.setProperty("webdriver.gecko.driver", PATH);
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		assertEquals("Google", driver.getTitle());
		driver.quit();
	}

	@Test
	public void testChrome() {
		System.setProperty("webdriver.chrome.driver", PATH);
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		assertEquals("Google", driver.getTitle());
		driver.quit();
	}
}
