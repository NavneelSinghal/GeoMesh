
public class Triangle implements TriangleInterface, Comparable<Triangle> {
    static float eps = (float)1e-3;
    Point a, b, c;
    // a, b, c are in sorted order
    Edge ab, bc, ca;
    ArrList<Triangle> triangles;
    int index = 0;
    int var = 0;

    Triangle(Point a, Point b, Point c, Edge bc1, Edge ca1, Edge ab1) {
        UTriple<Point> u = new UTriple<Point>(a, b, c);
        this.a = u.x;
        this.b = u.y;
        this.c = u.z;
        ab = new Edge(a, b);
        bc = new Edge(b, c);
        ca = new Edge(c, a);
        triangles = new ArrList<Triangle>();
    }

    @Override
    public int compareTo(Triangle arg0) {

        int xx = a.compareTo(arg0.a);
        int yy = b.compareTo(arg0.b);
        int zz = c.compareTo(arg0.c);
        if (xx != 0)
            return xx;
        else if (yy != 0)
            return yy;
        else
            return zz;
    }

    @Override
    public PointInterface[] triangle_coord() {
        PointInterface[] a = new PointInterface[3];
        a[0] = this.a;
        a[1] = this.b;
        a[2] = this.c;
        return a;
    }

    public void addTriangle(Triangle t) { triangles.push_back(t); }

    public String toString() {
        return "[" + a.toString() + "," + b.toString() + "," + c.toString() +
            "]";
    }

    public static boolean isnotvalid(Point p1, Point p2, Point p3) {
        //		Point p = new Point(0, 0, 0);
        //		p.add(p1.cross(p2));
        //		p.add(p2.cross(p3));
        //		p.add(p3.cross(p1));
        //		//System.out.println(p);
        //		float w = p.x*p.x + p.y*p.y + p.z*p.z;
        //		float l1 = (new Edge(p1, p2)).length();
        //		float l2 = (new Edge(p3, p2)).length();
        //		float l3 = (new Edge(p1, p3)).length();
        //		float mx = l1;
        //		if(mx < l2) mx = l2;
        //		if(mx < l3) mx = l3;
        //		return w/(mx*mx) < eps*eps;
        Point w = diff(p1, p2);
        Point u = diff(p3, p2);
        Point v = diff(p1, p3);
        float a1 = (w.cross(u)).mag();
        float a2 = (u.cross(v)).mag();
        float a3 = (v.cross(w)).mag();
        float d1 = w.dot(u);
        float d2 = u.dot(v);
        float d3 = v.dot(w);
        return (a1 / d1 < eps * eps) || (a2 / d2 < eps * eps) ||
            (a3 / d3 < eps * eps);
    }

    public static Point diff(Point p1, Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z);
    }
}
