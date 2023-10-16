package b1;

public class UnsortedArrayPriorityQueue<K extends Comparable, E> implements PriorityQueueInterface{
    private final int defaultsize = 1000;
    private ArrEntry<K, E> [] array;
    private int n = 0;
    public UnsortedArrayPriorityQueue(){
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
        if(n == array.length) enlarge();
        array[n++] = (ArrEntry<K, E>) entry;
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
        if(n == 0) throw new IllegalStateException("queue is mty");
        Entry<K, E> min = array[0];
        int minIndex = 0;
        // find min n min index
        for (int i = 1; i < n; i++){
            if (array[i].getKey().compareTo(min.getKey()) < 0){
                min = array[i];
                minIndex = i;
            }
        }
        // remove min
        for (int i = minIndex; i < n - 1; i++){
            array[i] = array[i + 1];
        }
        n--;
        return min;
    }

    @Override
    public Entry min() {
        Entry<K, E> min = array[0];
        for (int i = 1; i < n; i++)
            if (array[i].getKey().compareTo(min.getKey()) < 0)
                min = array[i];

        return min;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(array[i].getKey()).append(":").append(array[i].getValue()).append("\n");
        return sb.toString();
    }

    protected class ArrEntry<K, E> implements Entry<K, E>{
        K key;
        E element;
        public ArrEntry (K k, E e){
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
