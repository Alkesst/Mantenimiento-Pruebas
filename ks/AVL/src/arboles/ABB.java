package arboles;

import java.util.NoSuchElementException;

import listas.Lista;
import listas.ListaEnlazada;

/**
 * Árboles binarios de búsqueda. ABB. No hay elementos repetidos. Si se intenta
 * insertar un elemento que ya está en el árbol este se descarta.
 * 
 * @author LTO
 * 
 * @param <T>
 */
public class ABB<T extends Comparable<? super T>> extends AB<T> implements
		ArbolBinario<T> {

	public boolean pertenece(T e, Nodo<T> n) {
		if (n.contenido.equals(e)) {
			return true;
		} else if (e.compareTo(n.contenido) < 0) {
			return (n.hijoIzq != null && n.hijoIzq.pertenece(e));
		} else {
			return (n.hijoDch != null && n.hijoDch.pertenece(e));
		}
	}

	protected Lista<T> camino(T elem, Nodo<T> n, ListaEnlazada<T> l) {
		l.agregar(n.contenido);
		if (elem.equals(n.contenido)) {
			return l;
		} else if (n.hijoIzq != null && elem.compareTo(n.contenido) < 0) {
			return camino(elem, n.hijoIzq, l);
		} else if (n.hijoDch != null) {
			return camino(elem, n.hijoDch, l);
		} else {
			throw new ABBException("El elemento no está en el árbol");
		}
	}

	public void inserta(T elt) {
		raiz = inserta(elt, raiz);
	}

	protected Nodo<T> inserta(T elt, Nodo<T> n) {
		if (n == null) {
			n = new Nodo<T>(elt);
		} else if (elt.compareTo(n.contenido) < 0) {
			n.hijoIzq = inserta(elt, n.hijoIzq);
			n.altura = Math.max(altura(n.hijoIzq), altura(n.hijoDch)) + 1;
		} else if (elt.compareTo(n.contenido) > 0) {
			n.hijoDch = inserta(elt, n.hijoDch);
			n.altura = Math.max(altura(n.hijoIzq), altura(n.hijoDch)) + 1;
		}
		return n;
	}

	public void elimina(T elem) {
		raiz = elimina(elem, raiz);
	}

	protected Nodo<T> elimina(T elt, Nodo<T> n) {
		if (n != null) {
			if (elt.equals(n.contenido)) {
				if (n.hijoDch == null) {
					n = n.hijoIzq;
				} else {
					n.contenido = min(n.hijoDch);
					n.hijoDch = eliminaMin(n.hijoDch);
					n.altura = Math.max(altura(n.hijoIzq), altura(n.hijoDch)) + 1;
				}
			} else if (elt.compareTo(n.contenido) < 0) {
				n.hijoIzq = elimina(elt, n.hijoIzq);
				n.altura = Math.max(altura(n.hijoIzq), altura(n.hijoDch)) + 1;
			} else if (elt.compareTo(n.contenido) > 0) {
				n.hijoDch = elimina(elt, n.hijoDch);
				n.altura = Math.max(altura(n.hijoIzq), altura(n.hijoDch)) + 1;
			}
		}
		return n;
	}

	protected T min(Nodo<T> n) {
		if (n != null) {
			while (n.hijoIzq != null) {
				n = n.hijoIzq;
			}
		}
		return n.contenido;
	}

	protected Nodo<T> eliminaMin(Nodo<T> n) {
		if (n == null)
			throw new NoSuchElementException();
		else if (n.hijoIzq != null) {
			n.hijoIzq = eliminaMin(n.hijoIzq);
			n.altura = Math.max(altura(n.hijoIzq), altura(n.hijoDch)) + 1;
			return n;
		} else {
			return n.hijoDch;
		}
	}
}
