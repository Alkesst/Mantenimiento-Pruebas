import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/* Autores:
Jose Francisco Aldana Martin
Manuel Veredas Galdeano
 */

class QueueTest {
    private int size = 5;
    private Queue<Integer> emptyQ;
    private Queue<Integer> fullQ;


    @Test
    public void empty() {
        emptyQ = new Queue<Integer>(size);
        assertTrue(emptyQ.empty());
    }

    @Test
    public void full() {
        fullQ = new Queue<Integer>(size);

        for (int i = 0; i < size; i++) {
            fullQ.enqueue(i);
        }
        assertTrue(fullQ.full());
    }

    @Test
    public void enqueueWhenFull() {
        fullQ = new Queue<Integer>(size);

        for (int i = 0; i < size; i++) {
            fullQ.enqueue(i);
        }

        assertThrows(QueueException.class, () -> {
            fullQ.enqueue(99);
        });
    }

    @Test
    public void dequeueWhenEmpty() {
        emptyQ = new Queue<Integer>(size);

        assertThrows(QueueException.class, () -> {
           emptyQ.dequeue();
        });
    }

    @Test
    public void enqueue() {
        emptyQ = new Queue<Integer>(size);

        int previousSize = emptyQ.size();
        emptyQ.enqueue(99);

        assertEquals(emptyQ.size(), previousSize + 1);
    }

    @Test
    public void dequeueSize() {
        fullQ = new Queue<Integer>(size);

        for (int i = 0; i < size; i++) {
            fullQ.enqueue(i);
        }

        int previousSize = fullQ.size();
        fullQ.dequeue();

        assertEquals(fullQ.size(), previousSize - 1);
    }

    @Test
    public void enqueueDequeueOneElement() {
        emptyQ = new Queue<Integer>(size);

        int previousSize = emptyQ.size();
        int element = 99;
        emptyQ.enqueue(element);

        assertEquals(emptyQ.dequeue(), element);
    }

    @Test
    public void enqueueDequeue() {
        emptyQ = new Queue<Integer>(size);

        Integer[] elementos = new Integer[size];
        for (int i = 0; i < size; i++) {
            elementos[i] = i;
            emptyQ.enqueue(i);
        }

        for (int i = 0; i < size; i++) {
            Integer tmp = emptyQ.dequeue();
            assertEquals(tmp, elementos[i]);
        }
    }

    @Test
    public void enqueueDequeueMultipleTimes() {
        emptyQ = new Queue<Integer>(size);

        for (int j = 0; j < 10; j ++) {
            Integer[] elementos = new Integer[size];
            for (int i = 0; i < size; i++) {
                elementos[i] = i;
                emptyQ.enqueue(i);
            }

            for (int i = 0; i < size; i++) {
                Integer tmp = emptyQ.dequeue();
                assertEquals(tmp, elementos[i]);
            }
        }
    }

    @Test
    public void negativeSize() {
        assertThrows(QueueException.class, () -> {
            final Queue<Integer> negativeSizeQueue = new Queue<Integer>(-1);
        });
    }

    @Test
    public void sizeZero() {
        assertThrows(QueueException.class, () -> {
            final Queue<Integer> zeroSizeQueue = new Queue<Integer>(0);
        });
    }
}