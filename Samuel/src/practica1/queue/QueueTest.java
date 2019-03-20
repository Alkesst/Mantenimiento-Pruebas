package practica1.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    @authors: Samuel Jurado Quintana y Pedro Díaz Gutierrez
 */

class QueueTest {

    @org.junit.jupiter.api.Test
    void empty() {
        Queue<Integer> q = new Queue<>(10);
        assertTrue(q.empty());
    }

    @org.junit.jupiter.api.Test
    void full() {
        Queue<Integer> q = new Queue<>(1);
        q.enqueue(5);
        assertTrue(q.full());
    }

    @org.junit.jupiter.api.Test
    void enqueueAndSizeGrow() {
        Queue<Integer> q = new Queue<>(10);
        int sizePre = q.size();
        q.enqueue(5);
        assertEquals(q.size(), sizePre + 1);
        assertEquals((int)5, (int)q.dequeue());
    }

    @org.junit.jupiter.api.Test
    void dequeueAndSizeDecrease() {
        Queue<Integer> q = new Queue<>(10);
        q.enqueue(5);
        int sizePre = q.size();
        assertEquals((int)5, (int)q.dequeue());
        assertEquals(sizePre - 1, q.size());
    }

    @org.junit.jupiter.api.Test
    void size() {
        Queue<Integer> q = new Queue<>(10);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(7);
        assertEquals(3, q.size());
    }

    @Test
    void throwExceptionIfDequeueEmptyQueue() {
        Queue<Integer> q = new Queue<>(10);
        QueueException thrown = assertThrows(
                QueueException.class,
                q::dequeue
        );
    }

    @Test
    void throwExceptionIfEnqueueFullQueue() {
        Queue<Integer> q = new Queue<>(1);
        q.enqueue(5);
        QueueException thrown = assertThrows(
                QueueException.class,
                () -> q.enqueue(10)
        );
    }

    @Test
    void addedElementIsInTheQueue() {
        Queue<Integer> queue = new Queue<>(10);
        queue.enqueue(5);
        assertEquals((int)queue.dequeue(),(int) 5);
    }

    @Test
    void sizeIsHigherThanBefore() {
        Queue<Integer> queue = new Queue<>(10);
        int beforeSize = queue.size();
        queue.enqueue(5);
        assertEquals(beforeSize + 1, queue.size());
    }

    @Test
    void throwExceptionWhenDequeuingAnEmptyQueue() {
        Queue<Integer> queue = new Queue<>(10);
        QueueException thrown = assertThrows(
                QueueException.class,
                queue::dequeue
        );
        assertTrue(thrown.getMessage().contains("dequeue operation on empty queue"));
    }

    @Test
    void sizeLowerThanBefore() {
        Queue<Integer> queue = new Queue<>(10);
        queue.enqueue(5);
        queue.enqueue(5);
        queue.enqueue(5);
        int queueSize = queue.size();
        queue.dequeue();
        assertEquals(queueSize - 1, queue.size());
    }

    @Test
    void fullQueue() {
        Queue<Integer> queue = new Queue<>(1);
        assertFalse(queue.full());
        queue.enqueue(1);
        assertTrue(queue.full());
    }

    @Test
    void cannotAddToFullQueues() {
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
    void emptyQueue() {
        Queue<Integer> queue = new Queue<>(10);
        assertTrue(queue.empty());
    }

    @Test
    void queueOfSize0() {
        QueueException thrown = assertThrows(QueueException.class, () -> new Queue<Integer>(0));
    }

    @Test
    void negativeSizeQueue() {
        QueueException thrown = assertThrows(QueueException.class,
                () -> new Queue<Integer>(-1));
    }

    @Test
    void headYtailCirculanBien() {
        Queue<Integer> q = new Queue<Integer>(2);
        q.enqueue(2);
        q.enqueue(3);
        assertTrue(q.full());
        q.dequeue();
        q.dequeue();
        assertTrue(q.empty());
        q.enqueue(3);
        assertEquals((int)3, (int)q.dequeue());
    }

}