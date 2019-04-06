/***
 * Alejandro Garau Madrigal
 * Raúl Morales Perujo
 */

package tests;


import arboles.ABBException;
import arboles.AVL;
import arboles.ArbolBinario;
import listas.Lista;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
	Los métodos equals(), hashCode() y compareTo() no están implementados y deberian estarlo.
	Los test de compareTo() ni siquiera se pueden realizar ya que no es posible llamar a la función.
	Los de equals() y hashCode() son los proporcionados por Java que son incorrectos en este caso.
*/

public class AVLTest {

    private static <T> boolean equals(T[] values, Lista<T> list) {
        boolean equals = true;
        if(values.length != list.longitud()){
            equals = false;
        }
        int i = 0;
        while(equals && i < values.length) {
            equals = values[i].equals(list.elemento(i));
            i++;
        }
        return equals;
    }

    private static <T extends Comparable<? super T>> void compruebaAVL(T[] values, ArbolBinario<T> tree) {
          assertEquals(values.length, tree.inOrden().longitud());
          assertTrue(equals(values, tree.inOrden()));
          tree.equilibrado();
    }

    @SafeVarargs
    private static <T extends Comparable<? super T>> AVL<T> construyeArbol(T...items){
        AVL<T> avl = new AVL<>();
        for (T item : items){
            avl.inserta(item);
        }
        return avl;
    }

    @Test
    void addNodesToEmptyAVLTest() {
        AVL<Integer> avl = construyeArbol();
        avl.inserta(2);
        Integer[] expectedValues = new Integer[]{2};
        compruebaAVL(expectedValues, avl);
    }

    @Test
    void insertNewNodeToAVL1ItemTest(){
          AVL<Integer> avl = construyeArbol(2);
          avl.inserta(5);
          Integer[] expectedValues = new Integer[]{2, 5};
          compruebaAVL(expectedValues, avl);
    }

    @Test
    void insertExistingNodeToAVL1ItemTest(){
        AVL<Integer> avl = construyeArbol(2);
        avl.inserta(2);
        Integer[] expectedValues = new Integer[]{2};
        compruebaAVL(expectedValues, avl);
    }

    @Test
    void insertExistingNodeToAVLNItemTest() {
       AVL<Integer> avl = construyeArbol( 3, 2, 4, 5);
       avl.inserta(5);
       Integer[] expectedValues = new Integer[]{2, 3, 4, 5};
       compruebaAVL(expectedValues, avl);
    }

    @Test
    void insertNewNodeToAVLNItemWithoutRotationsTest(){
        AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43);
        avl.inserta(24);
        Integer[] expectedValues = new Integer[]{11, 23, 24, 35, 38, 39, 41, 42, 43, 46, 49, 74, 80, 96};
        compruebaAVL(expectedValues, avl);
    }

    @Test
    void insertNewNodeToAVLNItemWithRotationLeftLeftTest(){
        AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43);
        avl.inserta(10);
        Integer[] expectedValues = new Integer[]{10, 11, 23, 35, 38, 39, 41, 42, 43, 46, 49, 74, 80, 96};
        compruebaAVL(expectedValues, avl);
    }

    @Test
    void insertNewNodeToAVLNItemWithRotationRightRightTest() {
         AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43);
         avl.inserta(100);
         Integer[] expectedValues = new Integer[]{11, 23, 35, 38, 39, 41, 42, 43, 46, 49, 74, 80, 96, 100};
         compruebaAVL(expectedValues, avl);
    }

    @Test
    void insertNewNodeToAVLNItemWithRotationRightLeftTest() {
        AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43);
        avl.inserta(40);
        Integer[] expectedValues = new Integer[]{11, 23, 35, 38, 39,40, 41, 42, 43, 46, 49, 74, 80, 96};
        compruebaAVL(expectedValues, avl);
    }

    @Test
    void insertNewNodeToAVLNItemWithRotationLeftRightTest() {
        AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43);
        avl.inserta(12);
        Integer[] expectedValues = new Integer[]{11, 12, 23, 35, 38, 39, 41, 42, 43, 46, 49, 74, 80, 96};
        compruebaAVL(expectedValues, avl);
    }
    
    @Test 
    void deleteNodeThatNotExistsFromAVLTest() {
    	AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43);
    	avl.elimina(295);
    	Integer[] expectedValues = new Integer[]{11, 23, 35, 38, 39, 41, 42, 43, 46, 49, 74, 80, 96};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test
    void deleteNodeThatExistsFromAVLWithoutRotationTest() {
    	AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43);
    	avl.elimina(41);
    	Integer[] expectedValues = new Integer[]{11, 23, 35, 38, 39, 42, 43, 46, 49, 74, 80, 96};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test
    void deleteNodeThatExistsFromAVLWithRotationLeftLeftTest() {
    	AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 96, 41, 43);
    	avl.elimina(11);
    	Integer[] expectedValues = new Integer[]{23, 35, 38, 39, 41, 42, 43, 46, 49, 74, 80, 96};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test
    void deleteNodeThatExistsFromAVLWithRotationRightRightTest() {
    	AVL<Integer> avl = construyeArbol(46, 35, 74, 23, 39, 49, 80, 11, 38, 42, 48, 41, 43);
    	avl.elimina(80);
    	Integer[] expectedValues = new Integer[]{11, 23, 35, 38, 39, 41, 42, 43, 46, 48, 49, 74};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test
    void deleteNodeThatExistsFromAVLWithRotationRightLeftTest() {
    	AVL<Integer> avl = construyeArbol(46, 23, 50, 11, 39, 51, 38, 42);
    	avl.elimina(51);
    	Integer[] expectedValues = new Integer[]{11, 23, 38, 39, 42, 46, 50};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test
    void deleteNodeThatExistsFromAVLWithRotationLeftRightTest() {
    	AVL<Integer> avl = construyeArbol(40, 38, 50, 39, 48, 51, 47, 49);
    	avl.elimina(39);
    	Integer[] expectedValues = new Integer[]{38, 40, 47, 48, 49, 50, 51};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test 
    void deleteNodeThatNotExistsFromAVL1ItemTest() {
    	AVL<Integer> avl = construyeArbol(2);
    	avl.elimina(3);
    	Integer[] expectedValues = new Integer[]{2};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test 
    void deleteNodeThatExistsFromAVL1ItemTest() {
    	AVL<Integer> avl = construyeArbol(2);
    	avl.elimina(2);
    	Integer[] expectedValues = new Integer[]{};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test 
    void deleteNodeFromEmptyAVLTest() {
    	AVL<Integer> avl = construyeArbol();
    	avl.elimina(2);
    	Integer[] expectedValues = new Integer[]{};
    	compruebaAVL(expectedValues, avl);
    }
    
    @Test
    void equalsAVLTest() {
    	AVL<Integer> avl1 = construyeArbol(1,2,3,4);
    	AVL<Integer> avl2 = construyeArbol(1,2,3,4);
    	assertEquals(avl1, avl2);
    }
    
    @Test
    void notEqualsAVLTest() {
    	AVL<Integer> avl1 = construyeArbol(5,6,7,8);
    	AVL<Integer> avl2 = construyeArbol(1,2,3,4);
    	assertNotEquals(avl1, avl2);
    } 
        
    @Test
    void sameHashCodeAVLTest() {
    	AVL<Integer> avl1 = construyeArbol(1,2,3,4);
    	AVL<Integer> avl2 = construyeArbol(1,2,3,4);
    	
    	assertEquals(avl1.hashCode(), avl2.hashCode());
    }
    
    @Test
    void notSameHashCodeAVLTest() {
    	AVL<Integer> avl1 = construyeArbol(1,2,3,4);
    	AVL<Integer> avl2 = construyeArbol(5,6,7,8);
    	
    	assertNotEquals(avl1.hashCode(), avl2.hashCode());
    }
}
