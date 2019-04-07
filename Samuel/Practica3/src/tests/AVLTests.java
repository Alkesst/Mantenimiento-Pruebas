/*
 * @authors: Samuel Jurado Quintana, Juan García Ruiz
 */

package tests;

import listas.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import arboles.*;

public class AVLTests {
	public static <T extends Comparable<? super T>>ABB<T> abbArray(T[] valores){
		ABB<T> arbol = new ABB<T>();
		for(T valors : valores) {
			arbol.inserta(valors);
		}
		return arbol;
	}
	
	public static <T extends Comparable<? super T>>AVL<T> avlArray(T[] valores){
		AVL<T> arbol = new AVL<T>();
		for(T valors : valores) {
			arbol.inserta(valors);
		}
		return arbol;
	}
	
	<T> boolean equals(T[] valores, Lista<T> lista) {
		int i = 0;
		boolean equals = false;
		if(valores.length == lista.longitud()) {
			equals = true;
			while(equals && i<valores.length) {
				equals &= valores[i].equals(lista.elemento(i));
				i++;
			}
		}
		return equals;
	}
	
	<T extends Comparable<? super T>> void compruebaAVL(T[] valores, ArbolBinario<T> arbol) {
		assertTrue(equals(valores, arbol.inOrden()));
		assertTrue(arbol.equilibrado());
		assertTrue(arbol.ordenado());
	}
	
	@Test
	public void insertarNodoArbolVacio() {
		AVL<Integer> arbol = avlArray(new Integer[] {});
		arbol.inserta(2);
		compruebaAVL(new Integer[]{2}, arbol);
	}
	
	@Test
	public void insertarNodoNoExiste1Elemento() {
		AVL<Integer> arbol = avlArray(new Integer[] {3});
		arbol.inserta(2);
		compruebaAVL(new Integer[]{2, 3}, arbol);
	}
	
	@Test
	public void insertarNodoExiste1Elemento() {
		AVL<Integer> arbol = avlArray(new Integer[] {3});
		arbol.inserta(3);
		compruebaAVL(new Integer[]{3}, arbol);
	}
	
	@Test
	public void insertarNodoExisteNElementos() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		AVL<Integer> arbol = avlArray(valors);
		arbol.inserta(46);
		Arrays.sort(valors);
		compruebaAVL(valors, arbol);
	}
	
	@Test
	public void insertarNodoNoExisteNElementosNoDeseq() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		Integer[] expected = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43, 24};
		AVL<Integer> arbol = avlArray(valors);
		arbol.inserta(24);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void insertarNodoNoExisteNElementosIzqIzq() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		Integer[] expected = {46, 35, 74, 11, 39, 49, 80, 10, 23, 38, 42, 96, 41, 43};
		AVL<Integer> arbol = avlArray(valors);
		arbol.inserta(10);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void insertarNodoNoExisteNElementosDerIzq() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		Integer[] expected = {46, 35, 74, 11, 39, 49, 80, 23, 38, 42, 96, 41, 43, 40};
		AVL<Integer> arbol = avlArray(valors);
		arbol.inserta(40);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void insertarNodoNoExisteNElementosDerDer() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		Integer[] expected = {46, 35, 74, 11, 39, 49, 80, 23, 38, 42, 96, 41, 43, 100};
		AVL<Integer> arbol = avlArray(valors);
		arbol.inserta(100);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void insertarNodoNoExisteNElementosIzqDer() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		Integer[] expected = {46, 35, 74, 11, 39, 49, 80, 23, 38, 42, 96, 41, 43, 12};
		AVL<Integer> arbol = avlArray(valors);
		arbol.inserta(12);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void borrarNodoArbolVacio() {
		AVL<Integer> arbol = avlArray(new Integer[] {});
		ABBException thrown = assertThrows(
				ABBException.class,
                () -> arbol.elimina(2)
        );
	}
	
	@Test
	public void borrarNodoNoExiste1Elemento() {
		AVL<Integer> arbol = avlArray(new Integer[] {3});
		ABBException thrown = assertThrows(
				ABBException.class,
                () -> arbol.elimina(2)
        );
	}
	
	@Test
	public void borrarNodoExiste1Elemento() {
		AVL<Integer> arbol = avlArray(new Integer[] {3});
		arbol.elimina(3);
		compruebaAVL(new Integer[]{}, arbol);
	}
	
	@Test
	public void borrarNodoExisteNElementosNoDeseq() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		Integer[] expected = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 43};
		AVL<Integer> arbol = avlArray(valors);
		arbol.elimina(41);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void borrarNodoNoExisteNElementos() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		AVL<Integer> arbol = avlArray(valors);
		ABBException thrown = assertThrows(
				ABBException.class,
                () -> arbol.elimina(295)
        );
	}
	
	@Test
	public void borrarNodoExisteNElementosIzqIzq() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		Integer[] expected = {46, 35, 74, 23, 39, 49, 80, 38, 42, 96, 41, 43};
		AVL<Integer> arbol = avlArray(valors);
		arbol.elimina(11);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void borrarNodoExisteNElementosDerIzq() {
		Integer[] valors = {46, 23, 50, 11, 39, 51, 38, 42};
		Integer[] expected = {46, 23, 50, 11, 39, 38, 42};
		AVL<Integer> arbol = avlArray(valors);
		arbol.elimina(51);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void borrarNodoExisteNElementosDerDer() {
		Integer[] valors = {46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43};
		Integer[] expected = {46, 35, 74, 11, 39, 49, 80, 23, 38, 42, 41, 43};
		AVL<Integer> arbol = avlArray(valors);
		arbol.elimina(96);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void borrarNodoExisteNElementosIzqDer() {
		Integer[] valors = {40, 38, 50, 39, 48, 51, 47, 49};
		Integer[] expected = {40, 38, 50, 48, 51, 47, 49};
		AVL<Integer> arbol = avlArray(valors);
		arbol.elimina(39);
		Arrays.sort(expected);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void insertarPasandoPor1_4_8_15() {
		Integer[] valors = {40,38};
		Integer[] expected = {37,38,40};
		AVL<Integer> arbol = avlArray(valors);
		arbol.inserta(37);
		compruebaAVL(expected, arbol);
	}
	
	@Test
	public void insertarPasandoPor1_4_8_10() {
		Integer[] valors = {38,40};
		Integer[] expected = {38,40,41};
		AVL<Integer> arbol = avlArray(valors);
		arbol.inserta(41);
		compruebaAVL(expected, arbol);
	}
}
