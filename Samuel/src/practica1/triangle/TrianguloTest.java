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
    void toStringWorks(){
        Triangulo t = new Triangulo(2,3,4);
        assertEquals("A: 2, B: 3, C: 4", t.toString());
    }

     @Test
    void escalenoTipoEscaleno(){
         Triangulo t = new Triangulo(2,3,4);
         assertEquals(TipoTriangulo.escaleno, t.tipo());
     }

    @Test
    void isoscelesTipoIsosceles(){
        Triangulo t = new Triangulo(2,4,4);
        assertEquals(TipoTriangulo.isosceles, t.tipo());
    }

    @Test
    void equilateroTipoEquilatero(){
        Triangulo t = new Triangulo(3,3,3);
        assertEquals(TipoTriangulo.equilatero, t.tipo());
    }
}