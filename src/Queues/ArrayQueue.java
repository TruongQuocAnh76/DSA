package Queue;

public class ArrayQueue<E> implements Queue<E>{
  private E[] array;
  private int size;
  private int top;

  public ArrayQueue() {
    array = (E[]) new Object[4];
    size = 0;
    top = 0;
  }

  public void enqueue(E element) {
    if (size == array.length) enlarge();

    array[(size + top) % array.length] = element;
    size++;
  }

  private void enlarge() {
    E[] temp = (E[]) new Object[2 * array.length];
    for (int i = 0; i < size; i++) temp[i] = array[(top + i) % array.length];
    array = temp;
  }

  public E dequeue() {
    if (size == 0) return null;

    size--;
    return array[top++ % array.length];
  }

  public E peek() {
    if (size == 0) return null;
    return array[top % array.length];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size; i++)
      sb.append(array[(top + i) % array.length]).append(" ");
    return sb.toString();
  }
}
