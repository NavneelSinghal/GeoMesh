public class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
    T F;
    T S;
    Pair(T F, T S) {
        if (F.compareTo(S) > 0) {
            this.S = F;
            this.F = S;
            return;
        }
        this.F = F;
        this.S = S;
    }
    public int compareTo(Pair<T> p) {
        int ch = this.F.compareTo(p.F);
        if (ch != 0) {
            return ch;
        } else {
            return this.S.compareTo(p.S);
        }
    }
}