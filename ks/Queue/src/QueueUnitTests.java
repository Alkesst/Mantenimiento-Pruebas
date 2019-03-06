/*
 * @author: Jesus Parejo Aliaga & Alejandro Garau Madrigal
 */


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class QueueUnitTests {
    @Test
    public void addedElementIsInTheQueue() {
        Queue<Integer> queue = new Queue<>(10);
        queue.enqueue(5);
        Assertions.assertEquals(queue.dequeue(),5);
    }

    @Test
    public void sizeIsHigherThanBefore() {
        Queue<Integer> queue = new Queue<>(10);
        int beforeSize = queue.size();
        queue.enqueue(5);
        Assertions.assertEquals(beforeSize + 1, queue.size());
    }

    @Test
    public void throwExceptionWhenDequeuingAnEmptyQueue() {
        Queue<Integer> queue = new Queue<>(10);
        QueueException thrown = Assertions.assertThrows(
            QueueException.class,
                queue::dequeue
        );
        Assertions.assertTrue(thrown.getMessage().contains("dequeue operation on empty queue"));
    }

    @Test
    public void sizeLowerThanBefore() {
        Queue<Integer> queue = new Queue<>(10);
        queue.enqueue(5);
        queue.enqueue(5);
        queue.enqueue(5);
        int queueSize = queue.size();
        queue.dequeue();
        Assertions.assertEquals(queueSize - 1, queue.size());
    }

    @Test
    public void fullQueue() {
        Queue<Integer> queue = new Queue<>(1);
        Assertions.assertFalse(queue.full());
        queue.enqueue(1);
        Assertions.assertTrue(queue.full());
    }

    @Test
    public void cannotAddToFullQueues() {
        Queue<Integer> queue = new Queue<>(1);
        // tenemos que llenar la queue primero
        queue.enqueue(5);
        QueueException thrown = Assertions.assertThrows(
                QueueException.class,
                () -> queue.enqueue(4)
        );
        Assertions.assertTrue(thrown.getMessage().contains("enqueue"));
    }

    @Test
    public void emptyQueue() {
        Queue<Integer> queue = new Queue<>(10);
        Assertions.assertTrue(queue.empty());
    }

    @Test
    public void queueOfSize0() {
        QueueException thrown = Assertions.assertThrows(QueueException.class, () -> new Queue<Integer>(0));
    }

    @Test
    public void negativeSizeQueue() {
        QueueException thrown = Assertions.assertThrows(QueueException.class,
                () -> new Queue<Integer>(-1));
    }

    @Test
    void multipleEnqueueAndDequeueTest() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        Assertions.assertEquals(3, queue.dequeue());
        Assertions.assertEquals(4, queue.dequeue());
        Assertions.assertEquals(5, queue.dequeue());
        queue.enqueue(2);
        queue.enqueue(1);
        Assertions.assertEquals(2, queue.dequeue());
        Assertions.assertEquals(1, queue.dequeue());
    }

}
