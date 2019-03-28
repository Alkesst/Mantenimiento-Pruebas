/***
 * Alejandro Garau Madrigal
 * Raúl Morales Perujo
 */

package tests;


import arboles.AVL;
import arboles.ArbolBinario;
import listas.Lista;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
AVL.equals no esta bien implementado
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
}
