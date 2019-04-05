package listas;

/**
 * Interfaz con las operaciones b�sicas sobre listas. Tambi�n implementa la
 * interfaz Iterable<T>
 * 
 * @param <T>
 *            Tipo de los objetos que se almacenan en la lista.
 */
public interface Lista<T> extends Vaciable, Iterable<T> {

	/**
	 * Devuelve una lista del mismo tipo que la receptora
	 * 
	 */
	public Lista<T> especimen();

	/**
	 * Inserta un elemento en una posici�n determinada de la lista.
	 * 
	 * @param pos
	 *            Posici�n donde insertar.
	 * @param elem
	 *            Elemento a insertar.
	 * @throws Lanza
	 *             una excepci�n ListaException si se indica una posici�n fuera de
	 *             rango.
	 */
	public void insertar(int pos, T elem) throws ListaException;

	/**
	 * Elimina el elemento de la lista que ocupa determinada posici�n.
	 * 
	 * @param pos
	 *            Posici�n del elemento a eliminar.
	 * @throws Lanza
	 *             una excepci�n ListaException si se indica una posici�n fuera de
	 *             rango.
	 */
	public void eliminar(int pos) throws ListaException;

	/**
	 * Devuelve el elemento de la lista que ocupa determinada posici�n.
	 * 
	 * @param pos
	 *            Posici�n del elemento que se desea consultar.
	 * @return Elemento requerido.
	 * 
	 * @throws Lanza
	 *             una excepci�n ListaException si se indica una posici�n fuera de
	 *             rango.
	 */
	public T elemento(int pos);

	/**
	 * A�ade un elemento al final de la lista.
	 * 
	 * @param elem
	 *            Elemento a a�adir.
	 */
	public void agregar(T elem);

	/**
	 * A�ade los elementos de la lista argumento al final de la lista.
	 * 
	 * @param l
	 *            Lista de elementos a a�adir.
	 */
	public void agregarTodos(Lista<T> l);

	/**
	 * Inserta los elementos de la lista argumento en l aposici�n especificada.
	 * 
	 * @param l
	 *            Lista de elementos a a�adir.
	 */
	public void insertarTodos(int i, Lista<T> l);

	/**
	 * Se devuelve la posici�n de determinado elemento si est� en la lista, -1 en
	 * caso de que no est�.
	 * 
	 * @param elem
	 *            Elemento a localizar.
	 * @return Posicion del elemento en la lista, -1 si no est�
	 */
	public int posicion(T elem);

	/**
	 * Encadena los elementos de una lista m a la lista actual.
	 * 
	 * @param lista
	 *            Lista con los elementos a encadenar.
	 */
	public void encadenar(Lista<? extends T> lista);

	/**
	 * Invierte los elementos de la lista.
	 */
	public void invertir();

	/**
	 * Devuelve una nueva lista con los elementos de la lista actual comprendidos
	 * entre dos posiciones (ambas inclusive)
	 * 
	 * @param inicio
	 *            Posici�n inicial de la sublista.
	 * @param fin
	 *            Posici�n final de la sublista
	 * @return Sublista comprendida entre las posiciones inicio y fin. Si no existe
	 *         tal sublista devolvemos la lista vac�a.
	 */
	public Lista<T> sublista(int inicio, int fin);
}