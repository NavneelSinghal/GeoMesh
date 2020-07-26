public class Point implements PointInterface, Comparable<Point> {

    float x, y, z;
    public ArrList<Edge> edges;
    public ArrList<Triangle> triangles;

    Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        edges = new ArrList<Edge>();
        triangles = new ArrList<Triangle>();
    }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
    public float[] getXYZcoordinate() {
        float[] ans = new float[3];
        ans[0] = x;
        ans[1] = y;
        ans[2] = z;
        return ans;
    }
    public int compareTo(Point p) {
        if (x != p.x) {
            return Float.compare(x, p.x);
        }
        if (y != p.y) {
            return Float.compare(y, p.y);
        }
        return Float.compare(z, p.z);
    }
    public float dist(Point a) {
        return (x - a.x) * (x - a.x) + (y - a.y) * (y - a.y) +
            (z - a.z) * (z - a.z);
    }
    public void addEdge(Edge e) { edges.push_back(e); }
    public void addTriangle(Triangle t) { triangles.push_back(t); }
    public String toString() { return "(" + x + "," + y + "," + z + ")"; }
    public Point cross(Point p) {
        float px = 0, py = 0, pz = 0;
        px = y * p.z - z * p.y;
        py = z * p.x - x * p.z;
        pz = x * p.y - y * p.x;
        return new Point(px, py, pz);
    }
    public void add(Point p) { 
        x += p.x;
        y += p.y;
        z += p.z;
    }
    public float mag() { 
        return x * x + y * y + z * z;
    }
    public float dot(Point p) { 
        return (x * p.x + y * p.y + z * p.z) * (x * p.x + y * p.y + z * p.z);
    }
}
