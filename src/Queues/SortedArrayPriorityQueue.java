package b1;

public class SortedArrayPriorityQueue<K extends Comparable, E> implements PriorityQueueInterface {
  // in descending order
  private final int defaultsize = 10;
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
    if (n + 1 == array.length) enlarge();
    K key = (K) entry.getKey();

    if (n == 0) array[n] = (ArrEntry<K, E>) entry;
    else {
      int idx = binarySearch(array, 0, n, key);
      if (idx < 0) idx = -idx - 1;
      // shift the array
      System.arraycopy(array, idx, array, idx + 1, n - idx);

      array[idx] = (ArrEntry<K, E>) entry;
    }
    n++;
  }

  private int binarySearch(ArrEntry[] a, int fromIndex, int toIndex, K key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      ArrEntry<K, E> midVal = a[mid];

      if (midVal.key.compareTo(key) > 0) low = mid + 1;
      else if (midVal.key.compareTo(key) < 0) high = mid - 1;
      else return mid; // key found
    }
    return -(low + 1); // key not found.
  }

  private void enlarge() {
    ArrEntry<K, E>[] temp = new ArrEntry[array.length * 2];
    System.arraycopy(array, 0, temp, 0, array.length);
    array = temp;
  }

  @Override
  public void insert(Object o, Object o2) {
    ArrEntry<K, E> temp = new ArrEntry<>((K) o, (E) o2);
    insert(temp);
  }

  @Override
  public Entry removeMin() {
    if (n == 0) throw new IllegalStateException("mty queue");
    n--;
    return array[n];
  }

  @Override
  public Entry min() {
    return array[n - 1];
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
