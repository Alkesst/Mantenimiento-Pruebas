package practica1.triangle;

/*
    @authors: Samuel Jurado Quintana y Pedro Díaz Gutiérrez
*/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrianguloTest {

    @Test
    void escalenoValidoEvaluaTrue() {
        Triangulo t = new Triangulo(2, 3, 4);
        assertTrue(t.esEscaleno());
        assertFalse(t.esIsosceles());
    }

    @Test
    void equilateroValidoEvaluaTrue() {
        Triangulo t = new Triangulo(3, 3, 3);
        assertTrue(t.esEquilatero());
        assertTrue(t.esIsosceles());
        assertFalse(t.esEscaleno());
    }

    @Test
    void isoscelesValidoEvaluaTrue() {
        Triangulo t = new Triangulo(3, 2, 3);
        assertTrue(t.esIsosceles());
        assertFalse(t.esEscaleno());
    }

    @Test
    void permutacionesEvaluaBien() {
        Triangulo ordenado = new Triangulo(2, 3, 4);
        Triangulo t = new Triangulo(3, 2, 4);
        assertEquals(ordenado.esEscaleno(), t.esEscaleno());
        assertEquals(ordenado.esIsosceles(), t.esIsosceles());
    }

    @Test
    void trianguloLadoA0LanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(0, 2, 1));
        assertEquals(thrown.getMessage(), "lado 0 o negativo");
    }

    @Test
    void trianguloLadoB0LanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(1, 0, 2));
        assertEquals(thrown.getMessage(), "lado 0 o negativo");
    }

    @Test
    void trianguloLadoC0LanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(1, 2, 0));
        assertEquals(thrown.getMessage(), "lado 0 o negativo");
    }

    @Test
    void trianguloLadoANegativoLanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(-2, 2, 2));
        assertEquals(thrown.getMessage(), "lado 0 o negativo");
    }

    @Test
    void trianguloLadoBNegativoLanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(2, -2, 2));
        assertEquals(thrown.getMessage(), "lado 0 o negativo");
    }

    @Test
    void trianguloLadoCNegativoLanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(2, 2, -2));
        assertEquals(thrown.getMessage(), "lado 0 o negativo");
    }

    @Test
    void sumaLadosIgualALanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(4, 2, 2));
        assertEquals(thrown.getMessage(), "suma menor o igual");
    }

    @Test
    void sumaLadosIguaBALanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(2, 4, 2));
        assertEquals(thrown.getMessage(), "suma menor o igual");
    }

    @Test
    void sumaLadosIgualCLanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(2, 2, 4));
        assertEquals(thrown.getMessage(), "suma menor o igual");
    }

    @Test
    void sumaLadosMenorALanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(5, 2, 2));
        assertEquals(thrown.getMessage(), "suma menor o igual");
    }

    @Test
    void sumaLadosMenorBLanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(2, 5, 2));
        assertEquals(thrown.getMessage(), "suma menor o igual");
    }

    @Test
    void sumaLadosMenorCLanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(2, 2, 5));
        assertEquals(thrown.getMessage(), "suma menor o igual");
    }

    @Test
    void trianguloLados0LanzaError() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> new Triangulo(0, 0, 0));
        assertEquals(thrown.getMessage(), "lado 0 o negativo");
    }

    @Test
    void toStringWorks() {
        Triangulo t = new Triangulo(2, 3, 4);
        assertEquals("A: 2, B: 3, C: 4", t.toString());
    }

    @Test
    void escalenoTipoEscaleno() {
        Triangulo t = new Triangulo(2, 3, 4);
        assertEquals(TipoTriangulo.escaleno, t.tipo());
    }

    @Test
    void isoscelesTipoIsosceles() {
        Triangulo t = new Triangulo(2, 4, 4);
        assertEquals(TipoTriangulo.isosceles, t.tipo());
    }

    @Test
    void equilateroTipoEquilatero() {
        Triangulo t = new Triangulo(3, 3, 3);
        assertEquals(TipoTriangulo.equilatero, t.tipo());
    }

    @Test
    void crearTrianguloValidoEscaleno() {
        Triangulo t = Triangulo.creaTriangulo("2 3 4");
        assertTrue(t.esEscaleno());
        assertEquals(TipoTriangulo.escaleno, t.tipo());
    }

    @Test
    void crearTrianguloValidoIsosceles() {
        Triangulo t = Triangulo.creaTriangulo("2 4 4");
        assertTrue(t.esIsosceles());
        assertEquals(TipoTriangulo.isosceles, t.tipo());
    }

    @Test
    void crearTrianguloValidoEquilatero() {
        Triangulo t = Triangulo.creaTriangulo("4 4 4");
        assertTrue(t.esEquilatero());
        assertEquals(TipoTriangulo.equilatero, t.tipo());
    }

    @Test
    void crearTrianguloNumeroLadosDistinto3() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> Triangulo.creaTriangulo("1 2"));
        assertEquals("no triangulo", thrown.getMessage());
    }

    @Test
    void crearTrianguloLado0() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> Triangulo.creaTriangulo("1 2 0"));
        assertEquals("lado 0 o negativo", thrown.getMessage());
    }

    @Test
    void crearTrianguloLadoNegativo() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> Triangulo.creaTriangulo("1 -2 4"));
        assertEquals("lado 0 o negativo", thrown.getMessage());
    }

    void crearTrianguloSumaLadosFalla() {
        TrianguloException thrown = assertThrows(TrianguloException.class, () -> Triangulo.creaTriangulo("1 3 4"));
        assertEquals("suma menor o igual", thrown.getMessage());
    }

    void crearTrianguloValorNoNumerico() {
        assertThrows(NumberFormatException.class, () -> Triangulo.creaTriangulo("1 3 auicda"));
    }
}