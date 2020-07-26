public class Queue<T extends Comparable<T>> {
    @SuppressWarnings("unchecked") T arr[] = (T[]) new Comparable[2];
    public int first = 0, last = 0, size = 0;

    @SuppressWarnings("unchecked")
    public void enqueue(T val) {
        if (arr.length == size) {
            T[] temp = (T[]) new Comparable[2 * size];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[(first + i) % arr.length];
            }
            arr = temp;
            first = 0;
            last = size;
        }
        arr[last] = val;
        size++;
        last++;
        if (last >= arr.length)
            last = last - arr.length;
    }

    public T dequeue() {
        if (arr.length == 0)
            return null;
        else {
            T val = arr[first];
            arr[first] = null;
            size--;
            first++;
            if (first >= arr.length)
                first -= arr.length;
            return val;
        }
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
}