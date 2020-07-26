public class OTriple<T extends Comparable<T>>
    implements Comparable<OTriple<T>> {
    public T x, y, z;
    OTriple(T a, T b, T c) {
        x = a;
        y = b;
        z = c;
    }
    public int compareTo(OTriple<T> u) {
        int xx, yy, zz;
        xx = x.compareTo(u.x);
        yy = y.compareTo(u.y);
        zz = z.compareTo(u.z);
        if (xx == 0 && yy == 0) {
            return zz;
        } else if (xx == 0) {
            return yy;
        } else
            return xx;
    }
}
