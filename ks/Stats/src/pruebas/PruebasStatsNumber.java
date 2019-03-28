package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import statistics.StatsNumber;

public class PruebasStatsNumber {
	private static final double DELTA = 0.001;

    @Test
    void testConstructorNullThrowsError() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> new StatsNumber(null)
        );
    }

    @Test
    void testConstructorArraySize0ThrowsError() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> new StatsNumber(new Number[0])
        );
    }

    @Test
    void testMediaArraySizeN() {
    	StatsNumber stats = new StatsNumber(new Number[]{1, 2, 7, 10, 14, 2});
        assertEquals(6.0, stats.media());
    }


    @Test
    void testMedianaArrayOddSize() {
    	StatsNumber stats = new StatsNumber(new Number[]{5, -1, 2, 14, 4});
        assertEquals(4.0, stats.mediana());
    }

    @Test
    void testMedianArrayEvenSize() {
        // This should return 2 instead of 3.
    	StatsNumber stats = new StatsNumber(new Number[]{1, 2, 4, 5});
        assertEquals(2.0, stats.mediana());
    }


    @Test
    void testVarianzaArraySizeN() {
    	StatsNumber stats = new StatsNumber(new Number[]{1, 5, 6, 7, 9});
        assertEquals(7.04, (double)stats.varianza(), DELTA);
    }


    @Test
    void testModaArrayWithSameElementsThrowsError() {
        // This should throw an error
    	StatsNumber stats = new StatsNumber(new Number[]{1, 1, 1, 1, 1, 1, 1, 1});
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                stats::moda
        );
    }

    @Test
    void testModaArrayWithMoreThan1DifferentElement() {
    	StatsNumber stats = new StatsNumber(new Number[]{1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10, 10});
        Number[] expectedValues = new Number[]{4.0, 10.0};
        Number[] actualValues = stats.moda();
        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    void testModaArrayLength1() {
        StatsNumber stats = new StatsNumber(new Number[]{1});
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                stats::moda
        );
    }

    @Test
    void testVarianzaArrayLength1() {
        StatsNumber stats = new StatsNumber(new Number[]{1});
        assertEquals(0, (double) stats.varianza(), DELTA);
    }

    @Test
    void testMediaArrayLength1() {
        StatsNumber stats = new StatsNumber(new Number[]{1});
        assertEquals(1, stats.media());
    }
}
