package b1;

public class SortedLinkedPriorityQueue<K extends Comparable, E> implements PriorityQueueInterface {
  // sorted in increasing order
  int n = 0;
  private NodeEntry<K, E> head;
  private NodeEntry<K, E> tail;

  public SortedLinkedPriorityQueue() {
    head = null;
    tail = null;
  }

  @Override
  public int size() {
    return n;
  }

  @Override
  public boolean isEmpty() {
    return n == 0;
  }

  @Override
  public void insert(Entry entry) {
    // insert and sort the list
    NodeEntry<K, E> temp = (NodeEntry<K, E>) entry;
    if (n == 0) {
      head = temp;
      tail = temp;
    } else {
      NodeEntry<K, E> current = head;
      NodeEntry<K, E> prev = null;
      // find the position to insert
      while (current != null && current.key.compareTo(temp.key) < 0) {
        prev = current;
        current = current.next;
      } // end of the loop, temp is greater than prev, smaller than current

      if (prev
          == null) { // prev == null meaning temp is smaller than head -> temp inserted to the front
        temp.next = head;
        head = temp;
      } else {
        temp.next = current;
        prev.next = temp;
      }
    }
    n++;
  }

  @Override
  public void insert(Object o, Object o2) {
    Entry<K, E> temp = new NodeEntry<>((K) o, (E) o2);
    insert(temp);
  }

  @Override
  public Entry removeMin() {
    if (n == 0) return null;
    NodeEntry<K, E> min = head;
    head = head.next;
    n--;
    return min;
  }

  @Override
  public Entry min() {
    return head;
  }

  protected class NodeEntry<K, E> implements Entry<K, E> {
    private K key;
    private E element;
    private NodeEntry<K, E> next;

    public NodeEntry(K k, E e) {
      key = k;
      element = e;
      next = null;
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public E getValue() {
      return element;
    }
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (NodeEntry current = head; current != null; current = current.next)
      sb.append(current.key.toString()).append(":").append(current.element.toString());
    return sb.toString();
  }
}
