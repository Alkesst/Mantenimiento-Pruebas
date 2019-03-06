/*
 * @author: Jesus Parejo Aliaga & Alejandro Garau Madrigal
 */

import java.util.Objects;

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
        else if(esIsosceles())
            return TipoTriangulo.Isosceles;
        else
            return TipoTriangulo.Escaleno;
    }

    @Override
    public String toString() {
        return "Triangulo{" +
                "catetoA=" + catetoA +
                ", catetoB=" + catetoB +
                ", catetoC=" + catetoC +
                '}';
    }

    public static Triangulo creaTriangulo(String stringSides) {
        int ladoA, ladoB, ladoC;
        String[] sides = stringSides.split(" ");
        if(sides.length != 3 )
            throw new TrianguloException("No hay 3 lados proporcionados");
        ladoA = Integer.parseInt(sides[0]);
        ladoB = Integer.parseInt(sides[1]);
        ladoC = Integer.parseInt(sides[2]);
        return new Triangulo(ladoA, ladoB, ladoC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangulo triangulo = (Triangulo) o;
        return catetoA == triangulo.catetoA &&
                catetoB == triangulo.catetoB &&
                catetoC == triangulo.catetoC;
    }

    @Override
    public int hashCode() {
        return Objects.hash(catetoA, catetoB, catetoC);
    }
}
