// Alejandro Garau Madrigal y Manuel Sanchez
package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestUMA {
	
	private static final String PATH = "/Users/alec/Documents/Universidad/Tercero/2Cuatri/chromedriver";
	
	WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*
		 * Ponemos a español la página web de la UMA
		 * */
		driver.get("http://www.uma.es/");
		
		
		WebElement navElements = 
				driver.findElement(By.id("navbar"));
		List<WebElement> elements = navElements.findElements(By.className("dropdown"));
		WebElement loginDropdown = elements.get(1);
		loginDropdown.click();
		WebElement languageSelecter = loginDropdown.findElement(By.className("dropdown-menu"));
		List<WebElement> lis = languageSelecter.findElements(By.tagName("li"));
		WebElement lang = lis.get(8);
		lang.findElements(By.tagName("a")).get(0).click();
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test
	public void testCreditosMantenimiento() {		
		assertEquals("Inicio - Universidad de Málaga", driver.getTitle());
	
		WebElement headerButtons = driver.findElement(By.id("header_buttons"));
		WebElement studyButton = headerButtons.findElement(By.className("blockBody")).findElement(By.linkText("Estudiar"));
		studyButton.click();
		
		WebElement ofertaGrados = driver.findElement(By.linkText("Grado"));
		ofertaGrados.click();
		
		
		driver.findElement(By.linkText("Grado en Ingeniería del Software")).click();
		
		driver.findElement(By.linkText("PROGRAMACIÓN DOCENTE")).click();
		
		driver.findElement(By.linkText("Mantenimiento y Pruebas del Software")).click();
		
		
		WebElement mainContent = driver.findElement(By.id("main_content"));
		WebElement fieldSet = mainContent.findElements(By.tagName("fieldset")).get(0);
		WebElement divCreditos = fieldSet.findElements(By.tagName("div")).get(10);
		
		assertEquals("6 Teóricos 0 Prácticos", divCreditos.getText());
	}
	
	@Test
	  public void testNumeroTlf() {
	    
	    WebElement navElements = 
	        driver.findElement(By.id("navbar"));
	      
	      List<WebElement> elements = navElements.findElements(By.className("dropdown"));
	      WebElement contact = elements.get(0);
	      contact.click();
	      
	      
	      WebElement searchByPersonal = contact.findElement(By.className("dropdown-menu"));
	      List<WebElement> listContact = searchByPersonal.findElements(By.tagName("a"));
	      WebElement personal = listContact.get(1);
	      personal.click();
	      
	      WebElement name = driver.findElement(By.id("id_nombre"));
	      name.sendKeys("Francisco");
	      WebElement lastname = driver.findElement(By.id("id_apellido_1"));
	      lastname.sendKeys("Duran");
	      lastname.submit();
	      
	      WebElement press = driver.findElement(By.linkText("Duran Muñoz, Francisco Javier"));
	      press.click();
	      
	     List <WebElement> number = driver.findElements(By.className("col-md-10"));
	     int n = Integer.parseInt(number.get(0).getText());
	     
	     assertEquals(n, 952132820);
	      
	      
	  }
	  
	  @Test
	  public void coloquioTest() {
	    
	    WebElement mHeader = driver.findElement(By.className("menu-header"));
	    WebElement bBody = mHeader.findElement(By.className("blockBody"));
	    List <WebElement> list = bBody.findElements(By.className("dropdown-toggle"));
	    WebElement services = list.get(4);
	    services.click();
	    
	    WebElement cultura = driver.findElement(By.linkText("Cultura"));
	    cultura.click();
	    
	    WebElement menu = driver.findElement(By.id("highlightmenu"));
	    WebElement cC = menu.findElement(By.linkText("CONTENEDOR CULTURAL"));
	    cC.click();
	    
	    WebElement mHeader1 = driver.findElement(By.className("menu-header"));
	    List <WebElement> listM = mHeader1.findElements(By.className("dropdown-toggle"));
	    WebElement music = listM.get(2);
	    music.click();
	    
	    WebElement coloquio = driver.findElement(By.partialLinkText("Coloquio + proyección de la película"));
	    coloquio.click();
	    
	    WebElement contentBody = driver.findElement(By.className("contentbody"));
		String[] participantesExpected = {"Javier Domínguez", "Marce Merino", "José Fernando Troyano", "Javier Denis"};
		List<String> participantes = new ArrayList<>();
        for (int i = 3; i < 7; i++) {
            participantes.add(contentBody.findElements(By.tagName("p")).get(i)
                    .findElement(By.tagName("strong")).getText());
        }
        assertArrayEquals(participantesExpected, participantes.toArray());
	  }

}
