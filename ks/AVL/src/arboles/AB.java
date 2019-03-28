package arboles;

import listas.Lista;
import listas.ListaEnlazada;

public abstract class AB<T extends Comparable<? super T>> implements
		ArbolBinario<T> {
	protected Nodo<T> raiz;

	/**
	 * Nodos del árbol binario
	 */
	protected static class Nodo<S extends Comparable<? super S>> {
		protected S contenido;
		protected Nodo<S> hijoIzq, hijoDch;
		protected int altura;

		protected Nodo(S elt) {
			this(elt, null, null);
		}

		protected Nodo(S e, Nodo<S> izq, Nodo<S> dch) {
			contenido = e;
			hijoIzq = izq;
			hijoDch = dch;
			altura = 1;
		}

		protected int altura() {
			return altura;
		}

		public String toString() {
			return "[" + (hijoIzq == null ? "" : (hijoIzq.toString() + "]"))
					+ contenido.toString()
					+ (hijoDch == null ? "" : ("[" + hijoDch.toString())) + "]";
		}

		protected boolean equilibrado() {
			if (Math.abs(altura(hijoIzq) - altura(hijoDch)) <= 1) {
				if (hijoIzq == null || hijoDch == null) {
					return true;
				} else {
					return hijoDch.equilibrado() && hijoIzq.equilibrado();
				}
			} else {
				return false;
			}
		}

		protected int altura(Nodo<S> n) {
			return n == null ? 0 : n.altura();
		}

		protected boolean ordenado() {
			return (hijoIzq == null || (hijoIzq.contenido.compareTo(contenido) < 0 && hijoIzq
					.ordenado()))
					&& (hijoDch == null || (hijoDch.contenido
							.compareTo(contenido) > 0 && hijoDch.ordenado()));
		}

		protected boolean pertenece(S e) {
			return contenido.equals(e)
					|| (hijoIzq != null && hijoIzq.pertenece(e))
					|| (hijoDch != null && hijoDch.pertenece(e));
		}

		protected Lista<S> inOrden() {
			Lista<S> l;
			if (hijoIzq == null) {
				l = new ListaEnlazada<S>();
			} else {
				l = hijoIzq.inOrden();
			}
			l.agregar(contenido);
			if (hijoDch != null) {
				l.encadenar(hijoDch.inOrden());
			}
			return l;
		}

		protected int numElementos() {
			return 1 + (hijoIzq == null ? 0 : hijoIzq.numElementos())
					+ (hijoDch == null ? 0 : hijoDch.numElementos());
		}
	}

	public int numElementos() {
		return vacio() ? 0 : raiz.numElementos();
	}

	/**
	 * Devuelve una lista con el camino desde la raíz al elemento dado como
	 * argumento. Si hay elementos repetidos devuelve el camino a uno de esos
	 * elementos. Si no está devuelve null.
	 * 
	 * @param elem
	 *            elemento hasta el que se busca el camino
	 * @return lista con el camino encontrado
	 */
	public Lista<T> camino(T e) {
		if (vacio()) {
			throw new ABBException("El elemento no está en el árbol");
		} else {
			return camino(e, raiz, new ListaEnlazada<T>());
		}
	}

	/**
	 * Devuelve una lista con el camino desde la raíz al elemento dado como
	 * argumento. Si hay elementos repetidos devuelve el camino a uno de esos
	 * elementos. Si no está devuelve null.
	 * 
	 * @param elem
	 *            elemento hasta el que se busca el camino
	 * @param l
	 *            lista en la que se va acumulando el camino hasta el momento
	 * @return lista con el camino encontrado
	 */
	protected Lista<T> camino(T elem, Nodo<T> n, ListaEnlazada<T> l) {
		l.agregar(n.contenido);
		if (elem.equals(n.contenido)) {
			return l;
		} else if (n.hijoIzq != null && n.hijoIzq.pertenece(elem)) {
			return camino(elem, n.hijoIzq, l);
		} else if (n.hijoDch != null && n.hijoDch.pertenece(elem)) {
			return camino(elem, n.hijoDch, l);
		} else {
			throw new ABBException("El elemento no está en el árbol");
		}
	}

	public boolean equilibrado() {
		return raiz == null || raiz.equilibrado();
	}

	public boolean ordenado() {
		return raiz == null || raiz.ordenado();
	}

	public Lista<T> inOrden() {
		if (vacio()) {
			return new ListaEnlazada<T>();
		} else {
			return raiz.inOrden();
		}
	}

	public boolean vacio() {
		return raiz == null;
	}

	public boolean pertenece(T e) {
		return raiz != null && raiz.pertenece(e);
	}

	public String toString() {
		if (raiz == null) {
			return "[]";
		} else {
			return raiz.toString();
		}
	}

	public int altura() {
		return altura(raiz);
	}

	protected int altura(Nodo<T> n) {
		return n == null ? 0 : n.altura();
	}
}
