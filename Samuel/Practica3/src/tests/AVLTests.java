/*
 * @authors: Samuel Jurado Quintana, Juan García Ruiz
 */

package tests;

import listas.*;

import static org.junit.Assert.assertTrue;

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
		AVL<Integer> arbol = avlArray(new Integer[] {2});
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
	
	
}
