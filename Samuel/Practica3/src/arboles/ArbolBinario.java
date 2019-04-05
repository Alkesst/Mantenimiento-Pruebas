package arboles;

import listas.Lista;
 
/**
 * Interfaz de �rboles binarios con operaciones t�picas.
 * 
 * @param <T>
 */
public interface ArbolBinario<T extends Comparable<? super T>> {
	/**
	 * Comprueba si un �rbol est� vac�o.
	 */
	boolean vacio();

	/**
	 * Devuelve el n�mero de elementos.
	 */
	int numElementos();

	/**
	 * Devuelve la altura de un �rbol. Un �rbol vac�o tiene altura 0. La altura
	 * de un �rbol no vac�o es igual a 1 m�s el m�ximo de las alturas de sus
	 * hijos.
	 * 
	 * @return altura de �rbol.
	 */
	int altura();

	/**
	 * Comprueba si el elemento est� en el �rbol.
	 * 
	 * @param elem
	 *            elemento a buscar.
	 * @return true si est�, false si no.
	 */
	boolean pertenece(T elem);

	/**
	 * Devuelve una lista con el recorrido en inorden de un �rbol.
	 * 
	 * @return recorrido en inorden
	 */
	Lista<T> inOrden();

	/**
	 * Devuelve la lista con el recorrido desde el nodo ra�z hasta el elemento
	 * pasado como argumento.
	 * 
	 * @param elem
	 *            es el elemento hasta el que se pide el camino.
	 * @return camino hasta el elemento.
	 * @throws ABBException
	 *             lanza una excepci�n del tipo ABBException si el elemento no
	 *             esta en el �rbol.
	 */
	Lista<T> camino(T elem) throws ABBException;

	/**
	 * Comprueba si el �rbol est� ordenado.
	 * 
	 * @return true si est� ordenado, o false si no.
	 */
	boolean ordenado();

	/**
	 * Comprueba si el �rbol est� equilibrado en altura.
	 * 
	 * @return true si est� equilibrado, o false si no.
	 */ 
	boolean equilibrado();

	/**
	 * inserta un elemento en el �rbol
	 * 
	 * @param elem
	 *            elemento a insertar
	 */
	void inserta(T elem);

	/**
	 * elimina un elemento en el �rbol
	 * 
	 * @param elem
	 *            elemento a eliminar
	 */
	void elimina(T elem);
}
