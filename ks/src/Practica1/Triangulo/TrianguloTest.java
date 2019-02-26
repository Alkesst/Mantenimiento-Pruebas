/*
 * @author: Jesus Parejo Aliaga & Alejandro Garau Madrigal
 */
package Practica1.Triangulo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrianguloTest {

    @Test
    void checkSidesNotNegativeSideA() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(-1, 1, 1));
        assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkSidesNotNegativeSideB() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(1, -1, 1));
        assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkSidesNotNegativeSideC() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 1, -1));
        assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkNot0SideA() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(0, 1, 1));
        assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkNot0SideB() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 0, 1));
        assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkNot0SideC() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 1, 0));
        assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void esEscaleno() {
        Triangulo triangulo = new Triangulo(5, 4, 3);
        assertTrue(triangulo.esEscaleno());
    }

    @Test
    void esIsosceles() {
        Triangulo triangulo = new Triangulo(5, 3, 3);
        assertTrue(triangulo.esIsosceles());
    }

    @Test
    void esEquilatero() {
        Triangulo triangulo = new Triangulo(5, 5, 5);
        assertTrue(triangulo.esEquilatero());
    }

    @Test
    void testCaseForThe7thTestCaseC() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(2, 2, 4));
        assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe7thTestCaseB() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(2, 4, 2));
        assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe7thTestCaseA() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(4, 2, 2));
        assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe9thTestCaseA() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(3, 1, 1));
        assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe9thTestCaseB() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 3, 1));
        assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe9thTestCaseC() {
        TrianguloException thrown = assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 1, 3));
        assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void toStringTestCase() {
        Triangulo triangulo = new Triangulo(3, 2, 3);
        assertEquals(triangulo.toString(), "Triangulo{catetoA=3, catetoB=2, catetoC=3}");
    }

    @Test
    void tipoReturnsIsoscelesWhenTheTriangleIsIsosceles() {
        Triangulo triangulo = new Triangulo(5, 3, 3);
        assertEquals(triangulo.tipo(), Triangulo.TipoTriangulo.Isosceles);
    }

    @Test
    void tipoReturnsEquilateroWhenTheTriangleIsEquilatero() {
        Triangulo triangulo = new Triangulo(5, 5, 5);
        assertEquals(triangulo.tipo(), Triangulo.TipoTriangulo.Equilatero);
    }

    @Test
    void tipoReturnsEscalenoWhenTheTriangleIsEscaleno() {
        Triangulo triangulo = new Triangulo(5, 4, 3);
        assertEquals(triangulo.tipo(), Triangulo.TipoTriangulo.Escaleno);
    }

    @Test
    void createNewTriangle() {
        assertEquals(new Triangulo(3, 3, 5), Triangulo.creaTriangulo(3, 3, 5));
    }

}