package Practica1;

public class Queue<T> {
	private final int MAX_SIZE;
	private T[] data;
	private int head;
	private int tail;
	private int size;

	public Queue(int maxSize) {
		MAX_SIZE = maxSize;
		data = (T[]) new Object[maxSize];
		head = 0;
		tail = 0;
		size = 0;
	}

	public boolean empty() {
		return size == 0;
	}

	public boolean full() {
		return size == MAX_SIZE;
	}

	public void enqueue(T element) {
		if (size == MAX_SIZE)
			throw new QueueException("enqueue operation on full queue");
		data[tail] = element;
		size += 1;
		tail += 1;
		if (tail == MAX_SIZE) {
			tail = 0;
		}
	}

	public T dequeue() {
		if (size == 0)
			throw new QueueException("dequeue operation on empty queue");
		T element = data[head];
		size -= 1;
		head += 1;
		if (head == MAX_SIZE) {
			head = 0;
		}
		return element;
	}
	
	public int size() {
		return size;
	}
}
