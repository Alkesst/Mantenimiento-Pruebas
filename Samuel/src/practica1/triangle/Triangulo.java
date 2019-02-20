package practica1.triangle;

public class Triangulo {
    int p;
    int q;
    int r;

    public Triangulo(int p, int q, int r) {
        this.p = p;
        this.q = q;
        this.r = r;
    }

    public boolean esEscaleno() {
        return p != q && p != r && q != r;
    }

    public boolean esIsosceles() {
        return p == q || p == r || q == r;
    }

    public boolean esEquilatero() {
        return p == q && p == r;
    }
}
