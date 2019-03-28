package practica1.triangle;

/*
    @authors: Samuel Jurado Quintana y Pedro Díaz Gutiérrez
*/

public class Triangulo {
    int p;
    int q;
    int r;
    TipoTriangulo tipo;

    public static Triangulo creaTriangulo(String args) {
        String[] ladosString = args.split("\\s++");
        int[] lados = new int[3];
        if (ladosString.length != 3) {
            throw new TrianguloException("no triangulo");
        }
        for (int i = 0; i<ladosString.length; i++) {
            lados[i] = Integer.parseInt(ladosString[i]);
        }
        return new Triangulo(lados[0], lados[1], lados[2]);
    }

    public Triangulo(int p, int q, int r) {
        if (p <= 0 || q <= 0 || r <= 0) {
            throw new TrianguloException("lado 0 o negativo");
        } else if (p >= q + r || q >= p + r || r >= p + q) {
            throw new TrianguloException("suma menor o igual");
        } else {
            this.p = p;
            this.q = q;
            this.r = r;
            if (esEscaleno()) {
                tipo = TipoTriangulo.escaleno;
            }
            if (esIsosceles()) {
                tipo = TipoTriangulo.isosceles;
            }
            if (esEquilatero()) {
                tipo = TipoTriangulo.equilatero;
            }
        }
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

    public String toString() {
        return String.format("A: %d, B: %d, C: %d", p, q, r);
    }

    TipoTriangulo tipo() {
        return tipo;
    }
}
