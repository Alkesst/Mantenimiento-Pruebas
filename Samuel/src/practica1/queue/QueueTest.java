package practica1.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(5, q.dequeue());
    }

    @org.junit.jupiter.api.Test
    void dequeueAndSize() {
        Queue<Integer> q = new Queue<>(10);
        q.enqueue(5);
        int sizePre = q.size();
        assertEquals(5, q.dequeue());
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
}