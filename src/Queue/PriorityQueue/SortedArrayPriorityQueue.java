package b1;

public class SortedArrayPriorityQueue<K extends Comparable, E> implements PriorityQueueInterface {
  private final int defaultsize = 1000;
  private ArrEntry<K, E>[] array;
  private int n = 0;

  public SortedArrayPriorityQueue() {
    array = new ArrEntry[defaultsize];
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
    K key = (K) entry.getKey();
    int i;
    for (i = 0; i < n; i++) // find position i to insert
    if (array[i].getKey().compareTo(key) > 0) {
        for (int j = n; j > i; j--) // shift all elements after i to the right
        array[j] = array[j - 1];
        break;
      }
    // insert
    array[i] = (ArrEntry<K, E>) entry;
    n++;
  }

  @Override
  public void insert(Object o, Object o2) {
    ArrEntry<K, E> temp = new ArrEntry<>((K) o, (E) o2);
    insert(temp);
  }

  @Override
  public Entry removeMin() {
    Entry<K, E> min = array[0];
    for (int i = 0; i < n; i++) array[i] = array[i + 1];

    n--;
    return min;
  }

  @Override
  public Entry min() {
    return array[0];
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++)
      sb.append(array[i].getKey()).append(":").append(array[i].getValue()).append("\n");
    return sb.toString();
  }

  protected class ArrEntry<K, E> implements Entry<K, E> {
    K key;
    E element;

    public ArrEntry(K k, E e) {
      key = k;
      element = e;
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
