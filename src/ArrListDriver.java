
public class ArrListDriver {
    public static void main(String args[]) {
        ArrList<Integer> aa = new ArrList<Integer>();
        for (int i = 0; i < 10; i++) {
            aa.push_back(i);
            System.out.println(aa.size());
            for (int j = 0; j < aa.size(); j++) {
                System.out.print(aa.get(j) + " ");
            }
            System.out.println();
        }

        UTriple<Integer> p1 = new UTriple<Integer>(1, 2, 3);
        UTriple<Integer> p2 = new UTriple<Integer>(1, 4, 2);
        System.out.println(p1.compareTo(p2));
        
        Pair<Integer> p11 = new Pair<Integer>(1, 2);
        Pair<Integer> p21 = new Pair<Integer>(1, 4);
        System.out.println(p11.compareTo(p21));
        
        Point p = new Point(1.0f, 2.0f, 3.0f);
        Point q = new Point(2.0f, 1.0f, 3.0f);
        System.out.println(p.compareTo(q));

        Point r = new Point(1.0f, 2.0f, 4.0f);
        Edge e1 = new Edge(q, r);
        Edge e2 = new Edge(r, p);
        Edge e3 = new Edge(p, q);

        Triangle t = new Triangle(p, q, r, e1, e2, e3);
        Edge e = new Edge(p, q);
        PointInterface[] pq = e.edgeEndPoints();
        for (int i = 0; i < 2; i++) {
            System.out.println(pq[i]);
        }
        PointInterface[] pqr = t.triangle_coord();
        for (int i = 0; i < 3; i++) {
            System.out.println(pqr[i]);
        }
    }
}
