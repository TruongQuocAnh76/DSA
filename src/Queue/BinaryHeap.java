package PriorityQueue;

public class BinaryHeap {
  private final int DEFAULT_SIZE = 4;
  private int[] array;
  private int size;

  public BinaryHeap() {
    array = new int[DEFAULT_SIZE];
    size = 0;
  }

  public void insert(int value) {
    if (size == 0) array[1] = value;
    else {
      if (size + 1 == array.length) enlarge();

      int childrenIdx = size + 1;
      array[childrenIdx] = value;

      while (childrenIdx > 1) {
        if(array[childrenIdx] <= array[childrenIdx / 2]) break;
        swim(childrenIdx);
        childrenIdx /= 2;
      }
    }
    size++;
  }

  private void swim(int idx) {
    int temp = array[idx];
    array[idx] = array[idx / 2];
    array[idx / 2] = temp;
  }

  public int deleteMax() {
    if(size == 0) return -1;

    int max = array[1];

    array[1] = array[size];
    int parentIdx = 1;
    int childrenIdx;
    while((parentIdx * 2 <= size)) {
      childrenIdx = (array[parentIdx * 2] < array[parentIdx * 2 + 1]) ? parentIdx * 2 + 1 : parentIdx * 2; // take the index of the larger child
      if(array[parentIdx] >= array[childrenIdx]) break;
      sink(parentIdx, childrenIdx);
      parentIdx = childrenIdx;
    }

    size--;
    return max;
  }

  private void sink(int parentIdx, int childrenIdx) {
    int temp = array[parentIdx];
    array[parentIdx] = array[childrenIdx];
    array[childrenIdx] = temp;
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
    int[] temp = new int[array.length * 2];
    System.arraycopy(array, 0, temp, 0, array.length);
    array = temp;
  }
}
