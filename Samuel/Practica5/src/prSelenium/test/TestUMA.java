/*
 * Autores:
 * 		Daniel Guerrero González
 * 		Samuel Jurado Quintana
 */

package prSelenium.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TestUMA {
	WebDriver driver;

	/**
	 * Inicialización de las pruebas cargando el driver y poniendo la pagina en español
	 */
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "/home/sjuradoq/Descargas/geckodriver");

		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();

		// And now use this to visit Google
		driver.get("https://www.uma.es");

		// Find the spanish option by class name and link text
		WebElement element = driver.findElement(By.id("navbar")).findElements(By.className("dropdown")).get(1); //get the second element, to select language
		element.click();

		element.findElement(By.linkText("Español")).click();

		//Comprobamos que estamos en la pagina de la UMA
		assertEquals("Inicio - Universidad de Málaga", driver.getTitle());
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	/**
	 * Estudiar -> Estudios Oficiales -> Grado -> Grado en Ingeniería del Software
	 * -> INFORMACIÓN DE GRADO -> PROGRAMACIÓN DOCENTE
	 *
	 * La asignatura "Mantenimiento y Pruebas del Software" del grado Ingeniería del
	 * Software tiene 6 créditos teóricos
	 */
	@Test
	public void testGradeInfo() {
		String credits;
		String realCredits = "6";

		//clickamos en el boton "Estudiar" y la opción "Grados"
		WebElement element = driver.findElement(By.id("header_buttons"));
		element.findElement(By.linkText("Estudiar")).click();
		element.findElement(By.linkText("Grado")).click();

		// Check the title of the page
		assertEquals("OFERTA DE GRADO - Listado de grados" +
				" - Universidad de Málaga", driver.getTitle());


		//clickamos en el link de "Grado de ingeniería del software
		driver.findElement(By.linkText("Grado en Ingeniería del Software")).click();

		// Check the title of the page
		assertEquals("GRADO EN INGENIERÍA DEL SOFTWARE - "
				+ "GRADO EN INGENIERÍA DEL SOFTWARE - INICIO - Universidad de Málaga", driver.getTitle());


		//Clickamos en información docente (información de grado es la pagina actual)
		element = driver.findElement(By.id("sidebar"));
		element.findElement(By.linkText("PROGRAMACIÓN DOCENTE")).click();

		// Check the title of the page
		assertEquals("Asignaturas - Universidad de Málaga", driver.getTitle());

		// Click on subject "Mantenimiento y Pruebas del Software"
		element = driver.findElement(By.linkText("Mantenimiento y Pruebas del Software"));
		element.click();

		// Check the title of the page
		assertEquals("Mantenimiento y Pruebas del Software - Universidad de Málaga", driver.getTitle());

		// We find the credits info of the subject, and compare it with the real credits
		element = driver.findElements(By.tagName("fieldset")).get(0);
		element = element.findElements(By.className("displayField")).get(3);
		credits = element.getText();
		assertEquals(credits.substring(0, 1), realCredits);
	}

	/**
	 * Contacta -> Directorio -> Buscador de personal -> introducimos datos de búsqueda
	 * (Nombre "Francisco" y Apellido 1 "Durán") -> Buscar
	 *
	 * El número de teléfono de Francisco Durán es "952132820"
	 */
	@Test
	public void testTeacherInfo() {
		String realPhoneNumber = "952132820";
		WebElement element;
		String phoneNumber;

		//clickamos en el boton "Contacta" y la opción "Buscador de personal"
		element = driver.findElement(By.id("navbar")).findElements(By.className("dropdown")).get(0); //get the first element, to select "contacta"
		element.click();

		element.findElement(By.linkText("Buscador de personal")).click();

		// Check the title of the page
		assertEquals("DUMA - Buscador de DUMA", driver.getTitle());


		//Introducimos los datos de búsqueda y clickamos en el boton de Submit
		element = driver.findElement(By.id("parametros_busqueda"));
		element.findElement(By.id("id_nombre")).sendKeys("Francisco");
		element.findElement(By.id("id_apellido_1")).sendKeys("Durán");
		element.findElement(By.className("btn-success")).click();

		// Check the title of the page
		assertEquals("DUMA - Buscador de DUMA", driver.getTitle());


		// We click on the result
		driver.findElement(By.linkText("Duran Muñoz, Francisco Javier")).click();

		// Check the title of the page
		assertEquals("DUMA - Datos de Francisco Javier Durán Muñoz en DUMA", driver.getTitle());

		//Cogemos el numero de telefono de la primera columna
		element = driver.findElements(By.className("row")).get(0);
		phoneNumber = element.findElement(By.className("col-md-10")).getText();

		//Comparamos el numero de telefono con el real
		assertEquals(phoneNumber, realPhoneNumber);
	}

	/**
	 * UMA -> Servicios -> Cultura -> Contenedor Cultural -> noticias -> Coloquio
	 * + proyección de la película "Miles Ahead"
	 *
	 * Javier Domínguez, Marce Merino, José Fernando Troyano y Javier Denis
	 * participarán en el coloquio sobre la figura de Miles Davis
	 */
	@Test
	public void testEventParticipants() {
		//clickamos en el boton "Servicios" y la opción "Cultura"
		WebElement element = driver.findElement(By.id("header_buttons"));
		element.findElement(By.linkText("Servicios")).click();
		element.findElement(By.linkText("Cultura")).click();

		// Check the title of the page
		assertEquals("Servicio de Cultura - Cultura" +
				" - Universidad de Málaga", driver.getTitle());


		//clickamos la página del contenedor cultural
		element = driver.findElement(By.id("sidebar"));
		element.findElement(By.linkText("CONTENEDOR CULTURAL")).click();

		// Check the title of the page
		assertEquals("Contenedor Cultural - Contenedor Cultural" +
				 " - Universidad de Málaga", driver.getTitle());

		//clickamos en la página Música
		element = driver.findElement(By.id("header_buttons"));
		element.findElement(By.linkText("Música")).click();

		assertEquals("Contenedor Cultural - Música" +
				" - Universidad de Málaga", driver.getTitle());

		//clickamos en la noticia
		element = driver.findElement(By.className("list-unstyled"));
		element.findElement(By.linkText("Coloquio + proyección de la película \"Miles Ahead\" / Miércoles 15 de mayo"))
				.click();

		assertEquals("Coloquio + proyección de la película \"Miles Ahead\" / Miércoles 15 de mayo" +
				" - Universidad de Málaga", driver.getTitle());

		//Comprobamos el nombre de los participantes
		element = driver.findElement(By.className("contentbody"));
		String[] participantesExpected = {"Javier Domínguez", "Marce Merino", "José Fernando Troyano", "Javier Denis"};
		List<String> participantes = new ArrayList<>();
        for (int i = 3; i < 7; i++) {
            participantes.add(element.findElements(By.tagName("p")).get(i)
                    .findElement(By.tagName("strong")).getText());
        }
        assertArrayEquals(participantesExpected, participantes.toArray());

	}
}
