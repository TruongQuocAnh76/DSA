package b1;

public class UnsortedLinkedPriorityQueue<K extends Comparable, E>
    implements PriorityQueueInterface {
  int n = 0;
  private NodeEntry<K, E> head;
  private NodeEntry<K, E> tail;

  public UnsortedLinkedPriorityQueue() {
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
    if (n == 0) {
      head = (NodeEntry<K, E>) entry;
      tail = (NodeEntry<K, E>) entry;
    } else {
      tail.next = (NodeEntry<K, E>) entry;
      tail = tail.next;
    }
    n++;
  }

  @Override
  public void insert(Object o, Object o2) {
    NodeEntry<K, E> temp = new NodeEntry<>((K) o, (E) o2);
    insert(temp);
  }

  @Override
  public Entry removeMin() {
    Entry<K, E> min = head;
    NodeEntry<K, E> prevMin = null;
    NodeEntry<K, E> current;
    for (current = head; current.next != null; current = current.next)
      if (current.next.key.compareTo(min.getKey()) < 0) {
        min = current.next;
        prevMin = current;
      }

    if (prevMin == null) head = head.next;
    else prevMin.next = prevMin.next.next;
    n--;

    return min;
  }

  @Override
  public Entry min() {
    Entry<K, E> min = head;
    NodeEntry<K, E> current;
    for (current = head; current.next != null; current = current.next)
      if (current.next.key.compareTo(min.getKey()) < 0) min = current.next;

    return min;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(NodeEntry current = head; current != null; current = current.next)
      sb.append(current.key.toString()).append(":").append(current.element.toString());
    return sb.toString();
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
}
