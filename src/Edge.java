
public class Edge implements EdgeInterface, Comparable<Edge> {

    Pair<Point> e;
    ArrList<Triangle> triangles;

    Edge(Point a, Point b) {
        e = new Pair<Point>(a, b);
        triangles = new ArrList<Triangle>();
    }
    @Override
    public int compareTo(Edge e) {
        int a = this.e.F.compareTo(e.e.F);
        if (a != 0) {
            return a;
        } else {
            return this.e.S.compareTo(e.e.S);
        }
    }
    @Override
    public PointInterface[] edgeEndPoints() {
        PointInterface[] a = new PointInterface[2];
        a[0] = e.F;
        a[1] = e.S;
        return a;
    }
    public Float length() { // this returns the square of the length
        return e.F.dist(e.S);
    }
    @Override
    public String toString() {
        return "[" + e.F.toString() + "," + e.S.toString() + "]";
    }
}
