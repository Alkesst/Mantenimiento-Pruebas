package Triangulo;

public class Triangulo {
	private int p;
	private int q;
	private int r;
	
	public Triangulo(int p, int q, int r) {
		this.setP(p);
		this.setQ(q);
		this.setR(r);
	}
	
	private boolean ladosMayoresQueCero() {
		return p > 0 && q > 0 && r > 0;
	}
	
	private boolean trianguloValido() {
		return p+q>r && p+r>q && q+r>p;
	}
	
	public boolean esEscaleno() {
		return ladosMayoresQueCero() && trianguloValido() && (p!=q && p!=r && q!=r);
	}
	
	public boolean esIsosceles() { 
		return ladosMayoresQueCero() && trianguloValido() && (p==q||p==r||q==r) && !(p==q && q==r); 
	}
	
	public boolean esEquilatero() {
		return ladosMayoresQueCero() && trianguloValido() && (p==q && q==r);
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getQ() {
		return q;
	}

	public void setQ(int q) {
		this.q = q;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
}
