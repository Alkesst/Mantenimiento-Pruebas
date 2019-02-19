package Practica1;

public class Triangulo {
    private int catetoA;
    private int catetoB;
    private int catetoC;

    public Triangulo(int catetoA, int catetoB, int catetoC) {
        this.catetoA = catetoA;
        this.catetoB = catetoB;
        this.catetoC = catetoC;
    }

    public boolean esEscaleno() {
        return (catetoA!=catetoB && catetoA!=catetoC && catetoB!=catetoC);
    }


    public boolean esIsosceles() {
        return (catetoA==catetoB||catetoA==catetoC||catetoB==catetoC) &&
                !(catetoA==catetoB && catetoB==catetoC);
    }


    public boolean esEquilatero() {
        return catetoA == catetoB && catetoB == catetoC;
    }
}
