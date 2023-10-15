package Queue;

public class HeapPriorityQueue<E extends Comparable> implements Queue<E> {
  // queue is in decreasing order
  private int size;
  private BinaryHeap<E> heap;

  public HeapPriorityQueue() {
    size = 0;
    heap = new BinaryHeap<E>();
  }

  @Override
  public void enqueue(E element) {
    heap.insert(element);
    size++;
  }

  @Override
  public E dequeue() {
    if (size == 0) return null;
    size--;
    return heap.deleteMax();
  }

  public E peek() {
    if (size == 0) return null;
    return heap.max();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }
}
