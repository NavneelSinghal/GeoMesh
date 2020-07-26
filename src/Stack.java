
public class Stack<T extends Comparable<T>> {
    T arr[];
    private int idx = 1;
    @SuppressWarnings("unchecked")
    Stack() {
        arr = (T[]) new Comparable[1];
        idx = 0;
    }
    @SuppressWarnings("unchecked")
    public void push(T val) {
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
    public T pop() {
        if (idx == 0)
            return null;
        T ret = arr[idx - 1];
        arr[idx - 1] = null;
        idx--;
        return ret;
    }
    public T get(int i) { return arr[i]; }
    public void set(int i, T val) { arr[i] = val; }
    public int size() { return idx; }
}

// note that this holds only for generic arrays which have comparable elements
// if you need to use a normal thing, you need to replace Comparable with Object
