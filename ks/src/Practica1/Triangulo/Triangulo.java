/*
 * @author: Jesus Parejo Aliaga & Alejandro Garau Madrigal
 */
package Practica1.Triangulo;

public class Triangulo {
    public enum TipoTriangulo {
        Escaleno, Isosceles, Equilatero
    }
    private int catetoA;
    private int catetoB;
    private int catetoC;

    public Triangulo(int catetoA, int catetoB, int catetoC) {
        if(catetoA <= 0 || catetoB <= 0 || catetoC <= 0)
            throw new TrianguloException("Sides must not be less or equal than 0");
        if(!isValid(catetoA, catetoB, catetoC))
            throw new TrianguloException("La suma de lados de dos lados no puede dar como resultado el tercer lado");
        this.catetoA = catetoA;
        this.catetoB = catetoB;
        this.catetoC = catetoC;
    }

    private boolean isValid(int catetoA, int catetoB, int catetoC) {
        return catetoA + catetoB > catetoC &&
                catetoB + catetoC > catetoA &&
                catetoA + catetoC > catetoB;
    }


    public boolean esEscaleno() {
        return (catetoA != catetoB && catetoA != catetoC && catetoB != catetoC);
    }


    public boolean esIsosceles() {
        return (catetoA == catetoB || catetoA == catetoC || catetoB == catetoC) &&
                !(catetoA == catetoB && catetoB == catetoC);
    }


    public boolean esEquilatero() {
        return catetoA == catetoB && catetoB == catetoC;
    }

    public TipoTriangulo tipo() {
        if(esEquilatero())
            return TipoTriangulo.Equilatero;
        if(esEscaleno())
            return TipoTriangulo.Escaleno;
        if(esIsosceles())
            return TipoTriangulo.Isosceles;
        // para que java no me llore
        return null;
    }

    @Override
    public String toString() {
        return "Triangulo{" +
                "catetoA=" + catetoA +
                ", catetoB=" + catetoB +
                ", catetoC=" + catetoC +
                '}';
    }
}
