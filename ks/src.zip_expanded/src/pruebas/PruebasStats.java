/**
 * @authors: Juan Garcia Ruiz, Alejandro Garau Madrigal
 */
package pruebas;

import org.junit.jupiter.api.Test;
import statistics.Stats;

import static org.junit.jupiter.api.Assertions.*;

public class PruebasStats {

    private static final double DELTA = 0.001;

    @Test
    void testConstructorNullThrowsError() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> new Stats(null)
        );
    }

    @Test
    void testConstructorArraySize0ThrowsError() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> new Stats(new int[0])
        );
    }

    @Test
    void testMediaArraySizeN() {
        Stats stats = new Stats(new int[]{1, 2, 7, 10, 14, 2});
        assertEquals(6, stats.media());
    }


    @Test
    void testMedianaArrayOddSize() {
        Stats stats = new Stats(new int[]{5, -1, 2, 14, 4});
        assertEquals(stats.mediana(), 4);
    }

    @Test
    void testMedianArrayEvenSize() {
        // This should return 2 instead of 3.
        Stats stats = new Stats(new int[]{1, 2, 4, 5});
        assertEquals(2, stats.mediana());
    }


    @Test
    void testVarianzaArraySizeN() {
        Stats stats = new Stats(new int[]{1, 5, 6, 7, 9});
        assertEquals(7.04, stats.varianza(), DELTA);
    }


    @Test
    void testModaArrayWithSameElementsThrowsError() {
        // This should throw an error
        Stats stats = new Stats(new int[]{1, 1, 1, 1, 1, 1, 1, 1});
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                stats::moda
        );
    }

    @Test
    void testModaArrayWithMoreThan1DifferentElement() {
        Stats stats = new Stats(new int[]{1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10, 10});
        int[] expectedValues = new int[]{4, 10};
        int[] actualValues = stats.moda();
        assertArrayEquals(expectedValues, actualValues);
    }
}
