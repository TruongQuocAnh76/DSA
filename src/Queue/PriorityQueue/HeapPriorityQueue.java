package PriorityQueue;

public class HeapPriorityQueue {
  // queue is in decreasing order
  private int size;
  private BinaryHeap heap;

  public HeapPriorityQueue() {
    size = 0;
    heap = new BinaryHeap();
  }

  public void inserting(int value) {
    heap.insert(value);
    size++;
  }

  public int removeMax() {
    if(size == 0) return -1;
    size--;
    return heap.deleteMax();
  }

  public int peek() {
    if(size == 0) return -1;
    return heap.max();
  }

}
