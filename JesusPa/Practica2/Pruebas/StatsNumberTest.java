package Pruebas;

import statistics.StatsNumber;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StatsNumberTest {
	@Test
	public void vectorNullLanzaError() {
		assertThrows(IllegalArgumentException.class, () -> new StatsNumber(null));
	}
	
	@Test
	public void vectorTamano0LanzaError() {
		assertThrows(IllegalArgumentException.class, () -> new StatsNumber(new Number[0]));
	}
	
	@Test
	public void media1Elementos() {
		Number[] array = {5};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(5.0, stat.media());
	}
	
	@Test
	public void mediaNElementos() {
		Number[] array = {1, 3, 4, 7, 10, 2};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(4.5, stat.media());
	}
	
	@Test
	public void varianza1Elementos() {
		Number[] array = {5};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(0.0, stat.varianza());
	}
	
	@Test
	public void varianzaNElementos() {
		Number[] array = {1, 3, 4, 7, 10};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(10.0, stat.varianza());
	}
	
	@Test
	public void mediana1Elemento() {
		Number[] array = {5};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(5.0, stat.mediana());
	}
	
	@Test
	public void medianaNElementosPar() {
		Number[] array = {1, 3, 4, 6, 7, 10};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(4.0, stat.mediana());
	}

	@Test
	public void medianaNElementosImpar() {
		Number[] array = {1, 3, 4, 7, 10};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(4.0, stat.mediana());
	}
	
	@Test
	public void medianaNElementosParDesordenado() {
		Number[] array = {10, 1, 4, 6, 3, 7};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(4.0, stat.mediana());
	}
	
	@Test
	public void medianaNElementosImparDesordenado() {
		Number[] array = {7, 1, 10, 3, 4};
		StatsNumber stat = new StatsNumber(array);
		assertEquals(4.0, stat.mediana());
	}
	
	@Test
	public void moda1Elemento() {
		Number[] array = {5};
		StatsNumber stat = new StatsNumber(array);
		assertThrows(RuntimeException.class, stat::moda);
	}
	
	@Test
	public void modaTodosElementosIguales() {
		Number[] array = {5, 5, 5, 5};
		StatsNumber stat = new StatsNumber(array);
		assertThrows(RuntimeException.class, stat::moda);
	}
	
	@Test
	public void modaElementosDistintos1Moda() {
		Number[] array = {5, 5, 4, 6, 5, 4, 10, 8};
		Number[] expected = {5.0};
		StatsNumber stat = new StatsNumber(array);
		assertArrayEquals(expected, stat.moda());
	}
	
	@Test
	public void modaElementosDistintosVariasModas() {
		Number[] array = {5, 5, 4, 6, 5, 4, 10, 8, 4, 6};
		Number[] expected = {4.0, 5.0};
		StatsNumber stat = new StatsNumber(array);
		assertArrayEquals(expected, stat.moda());
	}
}
