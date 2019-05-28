package selenium.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WordReference {

	private static final String PATH = "/Users/alec/Documents/Universidad/Tercero/2Cuatri/chromedriver";
	
	static WebDriver driver;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	
	@Test
	public void test() {
		driver.get("http://www.wordreference.com/es/");
		assertEquals("Diccionarios de Español, Ingles, Francés, Portugués - WordReference.com", driver.getTitle());

		Select select = new Select(driver.findElement(By.name("dict")));
		select.selectByVisibleText("Español-Inglés");

		WebElement tf = driver.findElement(By.name("w"));
		tf.sendKeys("pan");
		tf.submit();

		List<WebElement> td = driver.findElements(By.className("ToWrd"));
		boolean encontrado = false;
		Iterator<WebElement> it = td.iterator();
		while (!encontrado && it.hasNext())
			encontrado = it.next().getText().contains("bread");
		assertTrue(encontrado);
	}
}