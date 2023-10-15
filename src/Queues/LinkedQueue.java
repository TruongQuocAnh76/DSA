package Queue;

public class LinkedQueue<E> implements Queue<E> {
  private SinglyNode top;
  private SinglyNode bot;
  private int size;

  public LinkedQueue() {
    size = 0;
  }

  @Override
  public void enqueue(E element) {
    SinglyNode temp = new SinglyNode(element);
    if (size == 0) {
      bot = temp;
      top = bot;
    } else {
      if(size == 1) top.next = temp;

      bot.next = temp;
      bot = temp;
    }
    size++;

  }

  @Override
  public E dequeue() {
    if(isEmpty()) return null;
    E data = top.data;
    top = top.next;
    size--;
    return data;
  }

  @Override
  public E peek() {
    if(isEmpty()) return null;

    return top.data;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (SinglyNode node = top; node != null; node = node.next) sb.append(node.data).append(" ");
    return sb.toString();
  }

  private class SinglyNode {
    private E data;
    private SinglyNode next;

    public SinglyNode(E element) {
      data = element;
    }
  }
}
