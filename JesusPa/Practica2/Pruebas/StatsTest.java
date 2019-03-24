/*
 * @author: Jesus Parejo Aliaga & Samuel Jurado Quintana
 */

package Pruebas;
import statistics.Stats;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StatsTest {
	/*
	@Test
	public void media0Elementos() {
		Stats stat = new Stats(new int[5]);
		RuntimeException runtimeException = assertThrows(RuntimeException.class, stat::media);
	}
	
	@Test
	public void varianza0Elementos() {
		Stats stat = new Stats(new int[5]);
		RuntimeException runtimeException = assertThrows(RuntimeException.class, stat::varianza);
	}
	
	@Test
	public void mediana0Elementos() {
		Stats stat = new Stats(new int[5]);
		RuntimeException runtimeException = assertThrows(RuntimeException.class, stat::mediana);
	}
	
	@Test
	public void moda0Elementos() {
		Stats stat = new Stats(new int[5]);
		RuntimeException runtimeException = assertThrows(RuntimeException.class, stat::moda);
	}
	*/
	
	@Test
	public void vectorNullLanzaError() {
		assertThrows(IllegalArgumentException.class, () -> new Stats(null));
	}
	
	@Test
	public void vectorTamano0LanzaError() {
		assertThrows(IllegalArgumentException.class, () -> new Stats(new int[0]));
	}
	
	@Test
	public void media1Elementos() {
		int[] array = {5};
		Stats stat = new Stats(array);
		assertEquals(5, stat.media());
	}
	
	@Test
	public void mediaNElementos() {
		int[] array = {1, 3, 4, 7, 10, 2};
		Stats stat = new Stats(array);
		assertEquals(4.5, stat.media());
	}
	
	@Test
	public void varianza1Elementos() {
		int[] array = {5};
		Stats stat = new Stats(array);
		assertEquals(0, stat.varianza());
	}
	
	@Test
	public void varianzaNElementos() {
		int[] array = {1, 3, 4, 7, 10};
		Stats stat = new Stats(array);
		assertEquals(10, stat.varianza());
	}

	@Test
	public void mediana1Elemento() {
		int[] array = {5};
		Stats stat = new Stats(array);
		assertEquals(5, stat.mediana());
	}
	
	@Test
	public void medianaNElementosPar() {
		int[] array = {1, 3, 4, 6, 7, 10};
		Stats stat = new Stats(array);
		assertEquals(4, stat.mediana());
	}
	
	@Test
	public void medianaNElementosImpar() {
		int[] array = {1, 3, 4, 7, 10};
		Stats stat = new Stats(array);
		assertEquals(4, stat.mediana());
	}
	
	@Test
	public void medianaNElementosParDesordenado() {
		int[] array = {10, 1, 4, 6, 3, 7};
		Stats stat = new Stats(array);
		assertEquals(4, stat.mediana());
	}
	
	@Test
	public void medianaNElementosImparDesordenado() {
		int[] array = {7, 1, 10, 3, 4};
		Stats stat = new Stats(array);
		assertEquals(4, stat.mediana());
	}
	
	@Test
	public void moda1Elemento() {
		int[] array = {5};
		Stats stat = new Stats(array);
		assertThrows(RuntimeException.class, stat::moda);
	}
	
	@Test
	public void modaTodosElementosIguales() {
		int[] array = {5, 5, 5, 5};
		Stats stat = new Stats(array);
		assertThrows(RuntimeException.class, stat::moda);
	}
	
	@Test
	public void modaElementosDistintos1Moda() {
		int[] array = {5, 5, 4, 6, 5, 4, 10, 8};
		int[] expected = {5};
		Stats stat = new Stats(array);
		assertArrayEquals(expected, stat.moda());
	}
	
	@Test
	public void modaElementosDistintosVariasModas() {
		int[] array = {5, 5, 4, 6, 5, 4, 10, 8, 4, 6};
		int[] expected = {4, 5};
		Stats stat = new Stats(array);
		assertArrayEquals(expected, stat.moda());
	}
}
