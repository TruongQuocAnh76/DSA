package Queue;

public interface Queue<E> {
    public void enqueue(E element);
    public E dequeue();
    public E peek();
    public int size();
    public boolean isEmpty();

}
