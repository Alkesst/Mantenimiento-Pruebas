/*
 * @author: Jesus Parejo Aliaga & Alejandro Garau Madrigal
 */
package Practica1.Queue;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QueueUnitTests {
    @Test
    public void addedElementIsInTheQueue() {
        Queue<Integer> queue = new Queue<>(10);
        queue.enqueue(5);
        assertEquals(queue.dequeue(),5);
    }

    @Test
    public void sizeIsHigherThanBefore() {
        Queue<Integer> queue = new Queue<>(10);
        int beforeSize = queue.size();
        queue.enqueue(5);
        assertEquals(beforeSize + 1, queue.size());
    }

    @Test
    public void throwExceptionWhenDequeuingAnEmptyQueue() {
        Queue<Integer> queue = new Queue<>(10);
        QueueException thrown = assertThrows(
            QueueException.class,
                queue::dequeue
        );
        assertTrue(thrown.getMessage().contains("dequeue operation on empty queue"));
    }

    @Test
    public void sizeLowerThanBefore() {
        Queue<Integer> queue = new Queue<>(10);
        queue.enqueue(5);
        queue.enqueue(5);
        queue.enqueue(5);
        int queueSize = queue.size();
        queue.dequeue();
        assertEquals(queueSize - 1, queue.size());
    }

    @Test
    public void fullQueue() {
        Queue<Integer> queue = new Queue<>(1);
        assertFalse(queue.full());
        queue.enqueue(1);
        assertTrue(queue.full());
    }

    @Test
    public void cannotAddToFullQueues() {
        Queue<Integer> queue = new Queue<>(1);
        // tenemos que llenar la queue primero
        queue.enqueue(5);
        QueueException thrown = assertThrows(
                QueueException.class,
                () -> queue.enqueue(4)
        );
        assertTrue(thrown.getMessage().contains("enqueue"));
    }

    @Test
    public void emptyQueue() {
        Queue<Integer> queue = new Queue<>(10);
        assertTrue(queue.empty());
    }

    @Test
    public void queueOfSize0() {
        QueueException thrown = assertThrows(QueueException.class, () -> new Queue<Integer>(0));
    }

    @Test
    public void negativeSizeQueue() {
        QueueException thrown = assertThrows(QueueException.class,
                () -> new Queue<Integer>(-1));
    }

    @Test
    void multipleEnqueueAndDequeueTest() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());
        queue.enqueue(2);
        queue.enqueue(1);
        assertEquals(2, queue.dequeue());
        assertEquals(1, queue.dequeue());
    }

}
