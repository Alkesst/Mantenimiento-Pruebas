package Practica1.Triangulo;

public class Triangulo {
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
}
