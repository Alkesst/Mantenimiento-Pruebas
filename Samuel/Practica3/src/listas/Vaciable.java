package listas;

/**
 * Interfaz que incluye m�todos para determinar el tama�o de un objeto y si est�
 * vac�o o no.
 */
public interface Vaciable {
	/**
	 * Informa si una lista est� vac�a o no.
	 * 
	 * @return true si est� vacia
	 */
	public boolean esVacia();

	/**
	 * Devolvemos el tama�o de la lista.
	 * 
	 * @return Entero representando el tama�o.
	 */
	public int longitud();
}