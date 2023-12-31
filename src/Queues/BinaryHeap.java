package Queue;

public class BinaryHeap<E extends Comparable> {
  private final int DEFAULT_SIZE = 4;
  private E[] array;
  private int size;

  public BinaryHeap() {
    array = (E[]) new Object[DEFAULT_SIZE];
    size = 0;
  }

  public void insert(E value) {
    if (size == 0) array[1] = value;
    else {
      if (size + 1 == array.length) enlarge();

      int childrenIdx = size + 1;
      array[childrenIdx] = value;

      while (childrenIdx > 1) {
        if(array[childrenIdx].compareTo(array[childrenIdx / 2]) <= 0) break;
        swim(childrenIdx);
        childrenIdx /= 2;
      }
    }
    size++;
  }

  private void swim(int idx) {
    E temp = array[idx];
    array[idx] = array[idx / 2];
    array[idx / 2] = temp;
  }

  public E max() {
    if(size == 0) return null;
    return array[1];
  }

  public E deleteMax() {
    if(size == 0) return null;

    E max = array[1];

    array[1] = array[size];
    int parentIdx = 1;
    int childrenIdx;
    while((parentIdx * 2 <= size)) {
      childrenIdx = (array[parentIdx * 2].compareTo(array[parentIdx * 2 + 1]) < 0) ? parentIdx * 2 + 1 : parentIdx * 2; // take the index of the larger child
      if(array[parentIdx].compareTo(array[childrenIdx]) >= 0) break;
      sink(parentIdx, childrenIdx);
      parentIdx = childrenIdx;
    }

    array[size] = null;
    size--;
    return max;
  }

  private void sink(int parentIdx, int childrenIdx) {
    E temp = array[parentIdx];
    array[parentIdx] = array[childrenIdx];
    array[childrenIdx] = temp;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(int i = 1; i <= size; i++) {
      sb.append(array[i]).append(" ");
    }
    return sb.toString();
  }

  private void enlarge() {
    E[] temp = (E[]) new Object[array.length * 2];
    System.arraycopy(array, 0, temp, 0, array.length);
    array = temp;
  }
}
