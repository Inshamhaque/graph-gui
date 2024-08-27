import java.util.NoSuchElementException;
//custom queue generation
public class ArrayQueue<T> {
    // we have used generics to pass the data in any format (Integer, List, LL, etc)
    private T[] array;
    private int front, rear, size, capacity;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity]; // this is an unusal way of creating array in generics because the original way will not work in
        //in this case...
        this.front = this.size = 0;
        this.rear = capacity - 1;
    }

    // enqueue operation... employed the functionality of circular queue
    public void enqueue(T item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue overflow");
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = array[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
