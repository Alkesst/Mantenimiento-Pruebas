import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* Autores:
Jose Francisco Aldana Martin
Manuel Veredas Galdeano
 */

class TrianguloTest {
    @Test
    public void escalenoValido() {
        Triangulo escaleno = new Triangulo(2,3,4);
        assertTrue(escaleno.esEscaleno());
    }

    @Test
    public void testEquilatero() {
        Triangulo t = new Triangulo(3, 3, 3);
        assertTrue(t.esEquilatero());
    }

    @Test
    public void isoscelesValido() {
        Triangulo isosceles = new Triangulo(3,3,4);
        assertTrue(isosceles.esIsosceles());
    }

    @Test
    public void testPermutacionesTrianguloValido() {
        Triangulo t = new Triangulo(2, 3, 4);
        assertTrue(t.esEscaleno());
        Triangulo t2 = new Triangulo(3, 2, 4);
        assertTrue(t2.esEscaleno());
        Triangulo t3 = new Triangulo(4, 3, 2);
        assertTrue(t3.esEscaleno());
        Triangulo t4 = new Triangulo(2, 4, 3);
        assertTrue(t4.esEscaleno());
        Triangulo t5 = new Triangulo(3, 4, 2);
        assertTrue(t5.esEscaleno());
        Triangulo t6 = new Triangulo(4, 2, 3);
        assertTrue(t6.esEscaleno());
    }

    @Test
    public void ladoLongitudCero() {
        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(1,2,0);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(0,4,4);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(1,0,1);
        });
    }

    @Test
    public void longitudNegativa () {
        assertThrows(TrianguloException.class, () -> {
            Triangulo t = t = new Triangulo(-3, 3, 3);
        });
    }

    @Test
    public void dosLadosSumaIgualTercero() {
        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(2,2,4);
        });
    }

    @Test
    public void dosLadosSumaIgualTerceroPermutaciones() {
        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(2,2,4);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(2,4,2);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(4,2,2);
        });
    }

    @Test
    public void dosLadosSumaMenosTercero() {
        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(1,2,4);
        });
    }

    @Test
    public void dosLadosSumaMenosTerceroPermutaciones() {
        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(1,2,4);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(1,4,2);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(2,1,4);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(2,4,1);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(4,1,2);
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(4,2,1);
        });

    }

    @Test
    public void todosLadosLongitudCero() {
        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = new Triangulo(0,0,0);
        });
    }

    @Test
    public void toStringTest() {
        Triangulo t = new Triangulo(2,3,4);
        assertEquals("Triangulos de lados (2,3,4)", t.toString());
    }

    @Test
    public void tipoTriangulo () {
        Triangulo t = new Triangulo(3, 3, 3);
        assertEquals(t.tipo(), Triangulo.TipoDeTriangulo.equilatero);
        Triangulo t2 = new Triangulo(2, 3, 4);
        assertEquals(t2.tipo(), Triangulo.TipoDeTriangulo.escaleno);
        Triangulo t3 = new Triangulo(3, 3, 4);
        assertEquals(t3.tipo(), Triangulo.TipoDeTriangulo.isosceles);
    }

    @Test
    public void trianguloValoresNoEnteros() {
        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = Triangulo.creaTriangulo("0.5 1.3 2.9");
        });
    }

    @Test
    public void trianguloNumeroValoresErroneo() {
        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = Triangulo.creaTriangulo("1 3");
        });

        assertThrows(TrianguloException.class, ()-> {
            Triangulo t = Triangulo.creaTriangulo("5 1 2 3");
            t.esEscaleno();
        });

    }
}