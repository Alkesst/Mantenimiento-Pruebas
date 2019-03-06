/*
 * @author: Jesus Parejo Aliaga & Alejandro Garau Madrigal
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TrianguloTest {

    @Test
    void checkSidesNotNegativeSideA() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(-1, 1, 1));
        Assertions.assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkSidesNotNegativeSideB() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(1, -1, 1));
        Assertions.assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkSidesNotNegativeSideC() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 1, -1));
        Assertions.assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkNot0SideA() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(0, 1, 1));
        Assertions.assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkNot0SideB() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 0, 1));
        Assertions.assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void checkNot0SideC() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 1, 0));
        Assertions.assertTrue(thrown.getMessage().contains("less or equal"));
    }

    @Test
    void esEscaleno() {
        Triangulo triangulo = new Triangulo(5, 4, 3);
        Assertions.assertTrue(triangulo.esEscaleno());
    }

    @Test
    void esIsosceles() {
        Triangulo triangulo = new Triangulo(5, 3, 3);
        Assertions.assertTrue(triangulo.esIsosceles());
    }

    @Test
    void esEquilatero() {
        Triangulo triangulo = new Triangulo(5, 5, 5);
        Assertions.assertTrue(triangulo.esEquilatero());
    }

    @Test
    void testCaseForThe7thTestCaseC() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(2, 2, 4));
        Assertions.assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe7thTestCaseB() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(2, 4, 2));
        Assertions.assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe7thTestCaseA() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(4, 2, 2));
        Assertions.assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe9thTestCaseA() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(3, 1, 1));
        Assertions.assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe9thTestCaseB() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 3, 1));
        Assertions.assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void testCaseForThe9thTestCaseC() {
        TrianguloException thrown = Assertions.assertThrows(TrianguloException.class,
                () -> new Triangulo(1, 1, 3));
        Assertions.assertTrue(thrown.getMessage().contains("suma de lados"));
    }

    @Test
    void toStringTestCase() {
        Triangulo triangulo = new Triangulo(3, 2, 3);
        Assertions.assertEquals(triangulo.toString(), "Triangulo{catetoA=3, catetoB=2, catetoC=3}");
    }

    @Test
    void tipoReturnsIsoscelesWhenTheTriangleIsIsosceles() {
        Triangulo triangulo = new Triangulo(5, 3, 3);
        Assertions.assertEquals(triangulo.tipo(), Triangulo.TipoTriangulo.Isosceles);
    }

    @Test
    void tipoReturnsEquilateroWhenTheTriangleIsEquilatero() {
        Triangulo triangulo = new Triangulo(5, 5, 5);
        Assertions.assertEquals(triangulo.tipo(), Triangulo.TipoTriangulo.Equilatero);
    }

    @Test
    void tipoReturnsEscalenoWhenTheTriangleIsEscaleno() {
        Triangulo triangulo = new Triangulo(5, 4, 3);
        Assertions.assertEquals(triangulo.tipo(), Triangulo.TipoTriangulo.Escaleno);
    }

    @Test
    void createNewTriangle() {
        Assertions.assertEquals(new Triangulo(3, 3, 5), Triangulo.creaTriangulo("3 3 5"));
    }

    @Test
    void wrongNumberOfSidesThrowsException() {
        TrianguloException thrown = Assertions.assertThrows(
                TrianguloException.class,
                () -> Triangulo.creaTriangulo("3 3")
        );
    }
}