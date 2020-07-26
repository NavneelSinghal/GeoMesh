public class ArrList<T extends Comparable<T>> {

    T arr[];
    private int idx = 1;

    @SuppressWarnings("unchecked")
    ArrList() {
        arr = (T[]) new Comparable[1];
        idx = 0;
    }

    @SuppressWarnings("unchecked")
    public void push_back(T val) {
        if (arr.length == idx) {
            T[] a = (T[]) new Comparable[2 * idx];
            for (int i = 0; i < idx; i++) {
                a[i] = arr[i];
            }
            arr = a;
        }
        arr[idx] = val;
        idx++;
    }
    public T get(int i) { return arr[i]; }
    public void set(int i, T val) { arr[i] = val; }
    public int size() { return idx; }
}

// note that this holds only for generic arrays which have comparable elements
// if you need to use a normal thing, you need to replace Comparable with Object
