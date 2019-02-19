package Practica1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueueUnitTests {
    @Test
    public void addedElementIsInTheQueue() {
        Queue queue = new Queue(10);
        queue.enqueue(5);
        Assertions.assertEquals(queue.dequeue(),5);
    }

    @Test
    public void sizeIsHigherThanBefore() {
        Queue queue = new Queue(10);
        int beforeSize = queue.size();
        queue.enqueue(5);
        Assertions.assertEquals(beforeSize + 1, queue.size());
    }

    @Test
    public void throwExceptionWhenDequeuingAnEmptyQueue() {
        Queue queue = new Queue(10);
        QueueException thrown = Assertions.assertThrows(
            QueueException.class,
                () -> queue.dequeue()
        );
        Assertions.assertTrue(thrown.getMessage().contains("dequeue operation on empty queue"));
    }

    @Test
    public void sizeLowerThanBefore() {
        Queue queue = new Queue(10);
        queue.enqueue(5);
        queue.enqueue(5);
        queue.enqueue(5);
        int queueSize = queue.size();
        queue.dequeue();
        Assertions.assertEquals(queueSize - 1, queue.size());
    }

    @Test
    public void fullQueue() {
        Queue queue = new Queue(1);
        Assertions.assertFalse(queue.full());
        queue.enqueue(1);
        Assertions.assertTrue(queue.full());
    }

    @Test
    public void cannotAddToFullQueues() {
        Queue queue = new Queue(0);
        QueueException thrown = Assertions.assertThrows(
                QueueException.class,
                () -> queue.enqueue(4)
        );
        Assertions.assertTrue(thrown.getMessage().contains("enqueue"));
    }

    @Test
    public void emptyQueue() {
        Queue queue = new Queue(10);
        Assertions.assertTrue(queue.empty());
    }

}
