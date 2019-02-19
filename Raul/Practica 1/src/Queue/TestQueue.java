package Queue;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestQueue {

	@Test
	public void testQueue() {
		Queue<Integer> q = null;
		assertNull("La cola debe ser null",q);
		q = new Queue<Integer>(10);
		assertNotNull("La cola no debe ser null",q);
	}

	@Test
	public void testEmpty() {
		Queue<Integer> q = new Queue<Integer>(2);
		assertTrue("La cola debe estar vacia",q.empty());
		q.enqueue(5);
		assertFalse("La cola no debe estar vacia",q.empty());
	}

	@Test
	public void testFull() {
		Queue<Integer> q = new Queue<Integer>(2);
		q.enqueue(5);
		assertFalse("La cola no debe estar llena 1/2",q.full());
		q.enqueue(5);
		assertTrue("La cola debe estar llena 2/2",q.full());
	}

	@Test
	public void testEnqueue() {
		Queue<Integer> q = new Queue<Integer>(2);
		q.enqueue(5);
		assertTrue("El tamaño de la cola debe ser 1",q.size() == 1);
		q.enqueue(5);
		assertTrue("El tamaño de la cola debe ser 2",q.size() == 2);
	}

	@Test
	public void testDequeue() {
		Queue<Integer> q = new Queue<Integer>(2);
		q.enqueue(5);
		q.enqueue(7);
		assertTrue("El numero obtenido debe ser 5",q.dequeue() == 5);
		assertTrue("El numero obtenido debe ser 7",q.dequeue() == 7);
	}

	@Test
	public void testSize() {
		Queue<Integer> q = new Queue<Integer>(2);
		q.enqueue(5);
		assertTrue("El tamaño de la cola debe ser 1",q.size() == 1);
		q.enqueue(3);
		assertTrue("El tamaño de la cola debe ser 2",q.size() == 2);
	}

}
