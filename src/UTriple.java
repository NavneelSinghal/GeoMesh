public class UTriple<T extends Comparable<T>>
    implements Comparable<UTriple<T>> {
    T x, y, z;
    @SuppressWarnings("unchecked")
    UTriple(T x, T y, T z) {
        T[] a;
        a = (T[])(new Comparable[3]);
        a[0] = x;
        a[1] = y;
        a[2] = z;
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 3 - i; j++) {
                if (a[j - 1].compareTo(a[j]) > 0) {
                    T temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        this.x = a[0];
        this.y = a[1];
        this.z = a[2];
    }
    public int compareTo(UTriple<T> u) {
        int xx, yy, zz;
        xx = x.compareTo(u.x);
        yy = y.compareTo(u.y);
        zz = z.compareTo(u.z);
        if (xx != 0) {
            return xx;
        } else if (yy != 0) {
            return yy;
        } else
            return zz;
    }
}
