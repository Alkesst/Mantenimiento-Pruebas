package arboles;

/**
 * �rboles AVL
 */
public class AVL<T extends Comparable<? super T>> extends ABB<T> {

	protected Nodo<T> rotaALaDch(Nodo<T> n) {
		if (n.hijoIzq == null) {
			throw new ABBException("No se puede rotar a la derecha");
		}
		Nodo<T> m = n.hijoIzq;
		n.hijoIzq = m.hijoDch;
		m.hijoDch = n;
		n.altura = Math.max(altura(n.hijoIzq), altura(n.hijoDch)) + 1;
		m.altura = Math.max(altura(m.hijoIzq), altura(m.hijoDch)) + 1;
		return m;
	}

	protected Nodo<T> rotaALaIzq(Nodo<T> n) {
		if (n.hijoDch == null) {
			throw new ABBException("No se puede rotar a la izquierda");
		}
		Nodo<T> m = n.hijoDch;
		n.hijoDch = m.hijoIzq;
		m.hijoIzq = n;
		n.altura = Math.max(altura(n.hijoIzq), altura(n.hijoDch)) + 1;
		m.altura = Math.max(altura(m.hijoIzq), altura(m.hijoDch)) + 1;
		return m;
	}

	protected Nodo<T> inserta(T elt, Nodo<T> n) {
		if (n == null) {
			n = new Nodo<T>(elt);
		} else if (elt.equals(n.contenido)) {
			// descartamos el elemento
		} else if (elt.compareTo(n.contenido) < 0) {
			n.hijoIzq = inserta(elt, n.hijoIzq);
			n.altura = Math.max(altura(n.hijoDch), altura(n.hijoIzq)) + 1;
			if (altura(n.hijoIzq) - altura(n.hijoDch) > 1) {
				if (elt.compareTo(n.hijoIzq.contenido) < 0) {
					n = rotaALaDch(n);
				} else {
					n.hijoIzq = rotaALaIzq(n.hijoIzq);
					n = rotaALaDch(n);
				}
			}
		} else {
			n.hijoDch = inserta(elt, n.hijoDch);
			n.altura = Math.max(altura(n.hijoDch), altura(n.hijoIzq)) + 1;
			if (altura(n.hijoDch) - altura(n.hijoIzq) > 1) {
				if (elt.compareTo(n.hijoDch.contenido) > 0) {
					n = rotaALaIzq(n);
				} else {
					n.hijoDch = rotaALaDch(n.hijoDch);
					n = rotaALaIzq(n);
				}
			}
		}
		return n;
	}

	protected Nodo<T> elimina(T elt, Nodo<T> n) {
		if (n != null) {
			if (elt.compareTo(n.contenido) < 0) {
				n.hijoIzq = elimina(elt, n.hijoIzq);
				if (altura(n.hijoDch) - altura(n.hijoIzq) > 1) {
					if (altura(n.hijoDch.hijoIzq) <= altura(n.hijoDch.hijoDch)) {
						n = rotaALaIzq(n);
					} else {
						n.hijoDch = rotaALaDch(n.hijoDch);
						n = rotaALaIzq(n);
					}
				}
				n.altura = Math.max(altura(n.hijoDch), altura(n.hijoIzq)) + 1;
			} else {
				if (elt.equals(n.contenido)) {
					if (n.hijoDch == null) {
						n = n.hijoIzq;
					} else {
						n.contenido = min(n.hijoDch);
						n.hijoDch = eliminaMin(n.hijoDch);
					}
				} else {
					// if (elt.compareTo(r.contenido) > 0) {
					n.hijoDch = elimina(elt, n.hijoDch);
				}
				if (n != null) {
					if (altura(n.hijoIzq) - altura(n.hijoDch) > 1) {
						if (altura(n.hijoIzq.hijoIzq) >= altura(n.hijoIzq.hijoDch)) {
							n = rotaALaDch(n);
						} else {
							n.hijoIzq = rotaALaIzq(n.hijoIzq);
							n = rotaALaDch(n);
						}
					}
					n.altura = Math.max(altura(n.hijoDch), altura(n.hijoIzq)) + 1;
				}
			}
		}
		return n;
	}
}