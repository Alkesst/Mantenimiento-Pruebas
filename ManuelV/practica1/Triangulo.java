/* Autores:
Jose Francisco Aldana Martin
Manuel Veredas Galdeano
 */

public class Triangulo {
    public enum TipoDeTriangulo {
        equilatero, isosceles, escaleno
    }

    private int length1;
    private int length2;
    private int length3;

    public static Triangulo creaTriangulo(String lados) {
        int l1, l2, l3;
        try {
            String[] tokensLados = lados.split(" ");

            if (tokensLados.length != 3) {
                throw new TrianguloException("Numero de argumentos erroneos");
            }

            l1 = Integer.parseInt(tokensLados[0]);
            l2 = Integer.parseInt(tokensLados[1]);
            l3 = Integer.parseInt(tokensLados[2]);

        } catch (NumberFormatException ex) {
            throw new TrianguloException("Valores para los lados no enteros");
        }
        return new Triangulo(l1, l2, l3);
    }

    public Triangulo(int length1, int length2, int length3) {
        //Check if any are equal or lower than 0
        if (length1 <= 0 || length2 <= 0 || length3 <= 0)
            throw new TrianguloException("Al menos uno de los lados es menor o igual a cero");

        //Check sum of 2 sides is greater than third
        if (length1 + length2 <= length3)
            throw new TrianguloException("Los lados no pueden formar un triangulo");

        if (length1 + length3 <= length2)
            throw new TrianguloException("Los lados no pueden formar un triangulo");

        if (length2 + length3 <= length1)
            throw new TrianguloException("Los lados no pueden formar un triangulo");

        this.length1 = length1;
        this.length2 = length2;
        this.length3 = length3;
    }

    public boolean esEscaleno() {
        boolean tresDistintos = (length1 != length2) && (length1 != length3) && (length2 != length3);

        return tresDistintos;
    }

    public boolean esIsosceles() {
        boolean unoYDosIguales = (length1 == length2) && (length1 != length3);
        boolean unoYTresIguales = (length1 == length3) && (length1 != length2);
        boolean dosyTresIguales = (length2 == length3) && (length2 != length1);

        boolean esIsosceles = unoYDosIguales || unoYTresIguales || dosyTresIguales;

        return esIsosceles;
    }

    public boolean esEquilatero() {
        boolean tresIguales = (length1 == length2) && (length1 == length3);

        return tresIguales;
    }

    public TipoDeTriangulo tipo() {
        if(esEscaleno()) {
            return TipoDeTriangulo.escaleno;
        }
        else if(esIsosceles()) {
            return TipoDeTriangulo.isosceles;
        }
        else if(esEquilatero()) {
            return TipoDeTriangulo.equilatero;
        }
        else {
            throw new TrianguloException("El triangulo no es de ningun tipo");
        }
    }

    @Override
    public String toString() {
        return "Triangulos de lados (" + length1 + "," + length2 + "," + length3 + ")";
    }
}
