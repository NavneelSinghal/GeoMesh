public class Shape implements ShapeInterface {

    RBTree<UTriple<Point>, Triangle> tr =
        new RBTree<UTriple<Point>, Triangle>();

    RBTree<Pair<Point>, Edge> ed = new RBTree<Pair<Point>, Edge>();

    RBTree<OTriple<Float>, Point> po = new RBTree<OTriple<Float>, Point>();

    ArrList<Point> polist = new ArrList<Point>();

    ArrList<Edge> edlist = new ArrList<Edge>();

    ArrList<Triangle> trlist = new ArrList<Triangle>();

    int edgedeg1 = 0, edgedeg2 = 0, edgedegmore = 0;

    public boolean ADD_TRIANGLE(float[] coord) {
        Point temp1 = new Point(coord[0], coord[1], coord[2]);
        Point temp2 = new Point(coord[3], coord[4], coord[5]);
        Point temp3 = new Point(coord[6], coord[7], coord[8]);

        if (Triangle.isnotvalid(temp1, temp2, temp3))
            return false;

        Point p1, p2, p3;
        RedBlackNode<OTriple<Float>, Point> p1t, p2t, p3t;
        OTriple<Float> t1 = new OTriple<Float>(coord[0], coord[1], coord[2]);
        OTriple<Float> t2 = new OTriple<Float>(coord[3], coord[4], coord[5]);
        OTriple<Float> t3 = new OTriple<Float>(coord[6], coord[7], coord[8]);
        
        p1t = po.search1(t1);
        p2t = po.search1(t2);
        p3t = po.search1(t3);
        
        int newpt[] = {0, 0, 0};
        
        if (p1t != null) {
            p1 = p1t.getValues().get(0);
        } else {
            p1 = new Point(coord[0], coord[1], coord[2]);
            po.insert(t1, p1);
            polist.push_back(p1);
            newpt[0] = 1;
        }
        
        if (p2t != null) {
            p2 = p2t.getValues().get(0);
        } else {
            p2 = new Point(coord[3], coord[4], coord[5]);
            po.insert(t2, p2);
            polist.push_back(p2);
            newpt[1] = 1;
        }
        
        if (p3t != null) {
            p3 = p3t.getValues().get(0);
        } else {
            p3 = new Point(coord[6], coord[7], coord[8]);
            po.insert(t3, p3);
            polist.push_back(p3);
            newpt[2] = 1;
        }
        
        UTriple<Point> ttt = new UTriple<Point>(p1, p2, p3);
        RedBlackNode<UTriple<Point>, Triangle> n = tr.search1(ttt);
        
        if (n != null) {
            return true;
        } else {
            Pair<Point> pp1 = new Pair<Point>(p2, p3),
                        pp2 = new Pair<Point>(p3, p1),
                        pp3 = new Pair<Point>(p1, p2);
            RedBlackNode<Pair<Point>, Edge> ed1 = ed.search1(pp1),
                                            ed2 = ed.search1(pp2),
                                            ed3 = ed.search1(pp3);
            Edge e1, e2, e3;
            int[] olddeg = new int[3];
            int[] newdeg = new int[3];
            if (ed1 != null) {
                e1 = ed1.getValues().get(0);
                olddeg[0] = e1.triangles.size();
            } else {
                e1 = new Edge(p2, p3);
                p2.edges.push_back(e1);
                p3.edges.push_back(e1);
                edlist.push_back(e1);
                ed.insert(pp1, e1);
                olddeg[0] = 0;
            }
            if (ed2 != null) {
                e2 = ed2.getValues().get(0);
                olddeg[1] = e2.triangles.size();
            } else {
                e2 = new Edge(p3, p1);
                p3.edges.push_back(e2);
                p1.edges.push_back(e2);
                edlist.push_back(e2);
                ed.insert(pp2, e2);
                olddeg[1] = 0;
            }
            if (ed3 != null) {
                e3 = ed3.getValues().get(0);
                olddeg[2] = e3.triangles.size();
            } else {
                e3 = new Edge(p1, p2);
                p1.edges.push_back(e3);
                p2.edges.push_back(e3);
                edlist.push_back(e3);
                ed.insert(pp3, e3);
                olddeg[2] = 0;
            }
            Triangle tri = new Triangle(p1, p2, p3, e1, e2, e3);
            tri.index = trlist.size();
            trlist.push_back(tri);
            p1.triangles.push_back(tri);
            p2.triangles.push_back(tri);
            p3.triangles.push_back(tri);
            int i = 0, j = 0, k = 0;
            ArrList<Triangle> tr1 = e1.triangles, tr2 = e2.triangles,
                              tr3 = e3.triangles;
            while (i < tr1.size() && j < tr2.size() && k < tr3.size()) {

                if (tr1.get(i).index <= tr2.get(j).index &&
                    tr1.get(i).index <= tr3.get(k).index) {
                    tri.triangles.push_back(tr1.get(i));
                    tr1.get(i).triangles.push_back(tri);
                    i++;
                } else if (tr2.get(j).index <= tr1.get(i).index &&
                           tr2.get(j).index <= tr3.get(k).index) {
                    tri.triangles.push_back(tr2.get(j));
                    tr2.get(j).triangles.push_back(tri);
                    j++;
                } else if (tr3.get(k).index <= tr2.get(j).index &&
                           tr3.get(k).index <= tr1.get(i).index) {
                    tri.triangles.push_back(tr3.get(k));
                    tr3.get(k).triangles.push_back(tri);
                    k++;
                }
            }
            while (i < tr1.size() && j < tr2.size()) {
                if (tr1.get(i).index <= tr2.get(j).index) {
                    tri.triangles.push_back(tr1.get(i));
                    tr1.get(i).triangles.push_back(tri);
                    i++;
                } else {
                    tri.triangles.push_back(tr2.get(j));
                    tr2.get(j).triangles.push_back(tri);
                    j++;
                }
            }
            while (i < tr1.size() && k < tr3.size()) {
                if (tr1.get(i).index <= tr3.get(k).index) {
                    tri.triangles.push_back(tr1.get(i));
                    tr1.get(i).triangles.push_back(tri);
                    i++;
                } else {
                    tri.triangles.push_back(tr3.get(k));
                    tr3.get(k).triangles.push_back(tri);
                    k++;
                }
            }
            while (k < tr3.size() && j < tr2.size()) {
                if (tr3.get(k).index < tr2.get(j).index) {
                    tri.triangles.push_back(tr3.get(k));
                    tr3.get(k).triangles.push_back(tri);
                    k++;
                } else {
                    tri.triangles.push_back(tr2.get(j));
                    tr2.get(j).triangles.push_back(tri);
                    j++;
                }
            }
            while (i < tr1.size()) {
                tri.triangles.push_back(tr1.get(i));
                tr1.get(i).triangles.push_back(tri);
                i++;
            }
            while (j < tr2.size()) {
                tri.triangles.push_back(tr2.get(j));
                tr2.get(j).triangles.push_back(tri);
                j++;
            }
            while (k < tr3.size()) {
                tri.triangles.push_back(tr3.get(k));
                tr3.get(k).triangles.push_back(tri);
                k++;
            }
            tr.insert(ttt, tri);
            e1.triangles.push_back(tri);
            e2.triangles.push_back(tri);
            e3.triangles.push_back(tri);
            newdeg[0] = e1.triangles.size();
            newdeg[1] = e2.triangles.size();
            newdeg[2] = e3.triangles.size();
            for (int ii = 0; ii < 3; ii++) {
                if (olddeg[ii] == 1) {
                    edgedeg1--;
                } else if (olddeg[ii] == 2) {
                    edgedeg2--;
                } else if (olddeg[ii] > 2) {
                    edgedegmore--;
                }
                if (newdeg[ii] == 1) {
                    edgedeg1++;
                } else if (newdeg[ii] == 2) {
                    edgedeg2++;
                } else if (newdeg[ii] > 2) {
                    edgedegmore++;
                }
            }
            return true;
        }
    }

    public int TYPE_MESH() {
        if (edgedeg1 == 0 && edgedegmore == 0)
            return 1;
        else if (edgedegmore != 0)
            return 3;
        else
            return 2;
    }

    public void lensort2(ArrList<Edge> e, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            lensort2(e, left, mid);
            lensort2(e, mid + 1, right);
            Edge[] l = new Edge[mid - left + 1];
            Edge[] r = new Edge[right - mid];
            for (int i = left; i <= mid; i++) {
                l[i - left] = e.get(i);
            }
            for (int i = mid + 1; i <= right; i++) {
                r[i - mid - 1] = e.get(i);
            }
            int i = 0, j = 0;
            while (i < l.length && j < r.length) {
                if (l[i].length() < (r[j].length())) {
                    e.set(left + i + j, l[i]);
                    i++;
                } else {
                    e.set(left + i + j, r[j]);
                    j++;
                }
            }
            while (i < l.length) {
                e.set(left + i + j, l[i]);
                i++;
            }
            while (j < r.length) {
                e.set(left + i + j, r[j]);
                j++;
            }
        }
    }

    public EdgeInterface[] BOUNDARY_EDGES() {
        ArrList<Edge> temp = new ArrList<Edge>();
        for (int i = 0; i < edlist.size(); i++) {
            if (edlist.get(i).triangles.size() == 1) {
                temp.push_back(edlist.get(i));
            }
        }
        int sz = temp.size();
        if (sz != 0) {
            lensort2(temp, 0, temp.size() - 1);
            EdgeInterface[] e = new EdgeInterface[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                e[i] = temp.get(i);
            }
            return e;
        } else {
            return null;
        }
    }

    void dfs(int n, int visited[], int num) {
        Stack<Triangle> s = new Stack<Triangle>();
        s.push(trlist.get(n));
        while (s.size() != 0) {
            Triangle t = s.pop();
            if (visited[t.index] == -1) {
                visited[t.index] = num;
            }
            for (int i = 0; i < t.triangles.size(); i++) {
                if (visited[t.triangles.get(i).index] == -1) {
                    s.push(t.triangles.get(i));
                }
            }
        }
    }

    public int COUNT_CONNECTED_COMPONENTS() {
        int sz = trlist.size();
        int[] visited = new int[sz];
        for (int i = 0; i < sz; i++)
            visited[i] = -1;
        int num = 0;
        for (int i = 0; i < sz; i++) {
            if (visited[i] == -1) {
                dfs(i, visited, num);
                num++;
            }
        }
        return num;
    }

    public TriangleInterface[] NEIGHBORS_OF_TRIANGLE(float[] coord) {
        Point p1 = new Point(coord[0], coord[1], coord[2]);
        Point p2 = new Point(coord[3], coord[4], coord[5]);
        Point p3 = new Point(coord[6], coord[7], coord[8]);
        UTriple<Point> utr = new UTriple<Point>(p1, p2, p3);
        RedBlackNode<UTriple<Point>, Triangle> node = tr.search1(utr);
        Triangle[] tttt;
        if (node == null) {
            return null;
        } else {
            Triangle t = node.getValues().get(0);
            tttt = new Triangle[t.triangles.size()];
            for (int i = 0; i < t.triangles.size(); i++) {
                tttt[i] = t.triangles.get(i);
            }
            return tttt;
        }
    }

    public EdgeInterface[] EDGE_NEIGHBOR_TRIANGLE(float[] coord) {
        Point p1 = new Point(coord[0], coord[1], coord[2]);
        Point p2 = new Point(coord[3], coord[4], coord[5]);
        Point p3 = new Point(coord[6], coord[7], coord[8]);
        UTriple<Point> utr = new UTriple<Point>(p1, p2, p3);
        RedBlackNode<UTriple<Point>, Triangle> node = tr.search1(utr);
        if (node == null) {
            return null;
        } else {
            EdgeInterface[] e = new EdgeInterface[3];
            Triangle t = node.getValues().get(0);
            e[0] = t.bc;
            e[1] = t.ca;
            e[2] = t.ab;
            return e;
        }
    }

    public PointInterface[] VERTEX_NEIGHBOR_TRIANGLE(float[] coord) {
        Point p1 = new Point(coord[0], coord[1], coord[2]);
        Point p2 = new Point(coord[3], coord[4], coord[5]);
        Point p3 = new Point(coord[6], coord[7], coord[8]);
        UTriple<Point> utr = new UTriple<Point>(p1, p2, p3);
        RedBlackNode<UTriple<Point>, Triangle> node = tr.search1(utr);
        if (node == null) {
            return null;
        } else {
            PointInterface[] e = new PointInterface[3];
            Triangle t = node.getValues().get(0);
            e[0] = t.a;
            e[1] = t.b;
            e[2] = t.c;
            return e;
        }
    }

    public TriangleInterface[] EXTENDED_NEIGHBOR_TRIANGLE(float[] coord) {
        Point p1 = new Point(coord[0], coord[1], coord[2]);
        Point p2 = new Point(coord[3], coord[4], coord[5]);
        Point p3 = new Point(coord[6], coord[7], coord[8]);
        UTriple<Point> utr = new UTriple<Point>(p1, p2, p3);
        RedBlackNode<UTriple<Point>, Triangle> node = tr.search1(utr);
        if (node == null) {
            return null;
        } else {
            Point[] e = new Point[3];
            Triangle t = node.getValues().get(0);
            e[0] = t.a;
            e[1] = t.b;
            e[2] = t.c;
            int i = 0, j = 0;
            i = 0;
            j = 0;
            ArrList<Triangle> ans = new ArrList<Triangle>();

            @SuppressWarnings("unchecked")
            ArrList<Triangle>[] tr = (ArrList<Triangle>[]) new ArrList[3];
            for (int id = 0; id < 3; id++) {
                tr[id] = e[id].triangles;
            }
            int[] id = new int[3];
            boolean ok =
                tr[0].size() > 0 && tr[1].size() > 0 && tr[2].size() > 0;
            while (ok) {
                Triangle mn = tr[0].get(id[0]);
                int mnidx = 0;
                for (int ii = 1; ii < 3; ii++) {
                    if (tr[ii].get(id[ii]).index < mn.index) {
                        mn = tr[ii].get(id[ii]);
                        mnidx = ii;
                    }
                }
                ans.push_back(mn);
                id[mnidx]++;
                ok = ok && (id[mnidx] < tr[mnidx].size());
            }

            for (int ii = 0; ii < 3; ii++) {
                int fi = ii;
                int se = (ii + 1) % 3;
                while (id[fi] < tr[fi].size() && id[se] < tr[se].size()) {
                    Triangle mn = tr[fi].get(id[fi]);
                    int mnidx = fi;
                    if (mn.index > tr[se].get(id[se]).index) {
                        mn = tr[se].get(id[se]);
                        mnidx = se;
                    }
                    ans.push_back(mn);
                    id[mnidx]++;
                }
            }

            for (int ii = 0; ii < 3; ii++) {
                while (id[ii] < tr[ii].size()) {
                    ans.push_back(tr[ii].get(id[ii]));
                    id[ii]++;
                }
            }

            ArrList<Triangle> temp = new ArrList<Triangle>();
            for (i = 0; i < ans.size() - 1; i++) {
                if (ans.get(i) != ans.get(i + 1)) {
                    temp.push_back(ans.get(i));
                }
            }
            temp.push_back(ans.get(i));
            if (temp.size() == 1)
                return null;
            TriangleInterface[] ret = new TriangleInterface[temp.size() - 1];
            j = 0;
            for (i = 0; i < temp.size(); i++) {
                if (temp.get(i) != t) {
                    ret[j] = temp.get(i);
                    j++;
                }
            }
            return ret;
        }
    }

    public TriangleInterface[] INCIDENT_TRIANGLES(float[] coord) {
        OTriple<Float> f = new OTriple<Float>(coord[0], coord[1], coord[2]);
        RedBlackNode<OTriple<Float>, Point> o = po.search1(f);
        if (o != null) {
            ArrList<Triangle> pppp = o.getValues().get(0).triangles;
            TriangleInterface[] ans = new TriangleInterface[pppp.size()];
            for (int i = 0; i < pppp.size(); i++) {
                ans[i] = pppp.get(i);
            }
            return ans;
        } else {
            return null;
        }
    }

    public PointInterface[] NEIGHBORS_OF_POINT(float[] coord) {
        OTriple<Float> f = new OTriple<Float>(coord[0], coord[1], coord[2]);
        Point ff = new Point(coord[0], coord[1], coord[2]);
        RedBlackNode<OTriple<Float>, Point> o = po.search1(f);
        if (o != null) {
            ArrList<Edge> pppp = o.getValues().get(0).edges;
            PointInterface[] ans = new PointInterface[pppp.size()];
            for (int i = 0; i < pppp.size(); i++) {
                Edge e = pppp.get(i);
                if (e.e.F.compareTo(ff) != 0) {
                    ans[i] = e.e.F;
                } else
                    ans[i] = e.e.S;
            }
            return ans;
        } else {
            return null;
        }
    }

    public EdgeInterface[] EDGE_NEIGHBORS_OF_POINT(float[] coord) {
        OTriple<Float> f = new OTriple<Float>(coord[0], coord[1], coord[2]);
        RedBlackNode<OTriple<Float>, Point> o = po.search1(f);
        if (o != null) {
            ArrList<Edge> pp = o.getValues().get(0).edges;
            EdgeInterface[] ans = new EdgeInterface[pp.size()];
            for (int i = 0; i < pp.size(); i++) {
                ans[i] = pp.get(i);
            }
            return ans;
        } else {
            return null;
        }
    }

    public TriangleInterface[] FACE_NEIGHBORS_OF_POINT(float[] coord) {
        OTriple<Float> f = new OTriple<Float>(coord[0], coord[1], coord[2]);
        RedBlackNode<OTriple<Float>, Point> o = po.search1(f);
        if (o != null) {
            ArrList<Triangle> pp = o.getValues().get(0).triangles;
            TriangleInterface[] ans = new TriangleInterface[pp.size()];
            for (int i = 0; i < pp.size(); i++) {
                ans[i] = pp.get(i);
            }
            return ans;
        } else {
            return null;
        }
    }

    public boolean IS_CONNECTED(float[] coord1, float[] coord2) {
        Point p1 = new Point(coord1[0], coord1[1], coord1[2]);
        Point p2 = new Point(coord1[3], coord1[4], coord1[5]);
        Point p3 = new Point(coord1[6], coord1[7], coord1[8]);
        UTriple<Point> utr = new UTriple<Point>(p1, p2, p3);
        RedBlackNode<UTriple<Point>, Triangle> node = tr.search1(utr);

        Point p11 = new Point(coord2[0], coord2[1], coord2[2]);
        Point p21 = new Point(coord2[3], coord2[4], coord2[5]);
        Point p31 = new Point(coord2[6], coord2[7], coord2[8]);
        UTriple<Point> utr1 = new UTriple<Point>(p11, p21, p31);
        RedBlackNode<UTriple<Point>, Triangle> node1 = tr.search1(utr1);
        if (node == null || node1 == null) {
            return false;
        } else {
            Triangle t1 = node.getValues().get(0);
            Triangle t2 = node1.getValues().get(0);
            int[] visited = new int[trlist.size()];
            for (int i = 0; i < visited.length; i++)
                visited[i] = -1;
            dfs(t1.index, visited, 0);
            return (visited[t2.index] == 0);
        }
    }

    public TriangleInterface[] TRIANGLE_NEIGHBOR_OF_EDGE(float[] coord) {
        Point p1 = new Point(coord[0], coord[1], coord[2]);
        Point p2 = new Point(coord[3], coord[4], coord[5]);
        Pair<Point> p = new Pair<Point>(p1, p2);
        RedBlackNode<Pair<Point>, Edge> node = ed.search1(p);
        if (node != null) {
            Edge e = node.getValues().get(0);
            TriangleInterface[] edg = new TriangleInterface[e.triangles.size()];
            for (int i = 0; i < e.triangles.size(); i++) {
                edg[i] = e.triangles.get(i);
            }
            return edg;
        } else {
            return null;
        }
    }

    public int MAXIMUM_DIAMETER() {
        int sz = trlist.size();
        int[] visited = new int[sz];
        for (int i = 0; i < sz; i++)
            visited[i] = -1;
        int num = 0;
        // System.out.println("dfs started");
        for (int i = 0; i < sz; i++) {
            if (visited[i] == -1) {
                dfs(i, visited, num);
                num++;
            }
        }
        int[] freq = new int[num];
        for (int i = 0; i < num; i++)
            freq[i] = 0;
        for (int i = 0; i < sz; i++) {
            freq[visited[i]]++;
        }
        int mx = 0;
        int mxidx = -1;
        for (int i = 0; i < num; i++) {
            if (freq[i] > mx) {
                mxidx = i;
                mx = freq[i];
            }
        }
        if (mx == 1) {
            return 0;
        }
        ArrList<Triangle> t = new ArrList<Triangle>();
        for (int i = 0; i < sz; i++) {
            if (visited[i] == mxidx) {
                t.push_back(trlist.get(i));
            }
        }
        boolean[] vis = new boolean[trlist.size()];
        for (int i = 0; i < vis.length; i++) {
            vis[i] = false;
        }
        int dia = 0;
        for (int i = 0; i < t.size(); i++) {
            t.get(i).var = 0;
            Queue<Triangle> q = new Queue<Triangle>();
            q.enqueue(t.get(i));
            vis[t.get(i).index] = true;
            int mxdist = 0;
            while (q.size() > 0) {
                Triangle trr = q.dequeue();
                if (trr.var > mxdist)
                    mxdist = trr.var;
                for (int ii = 0; ii < trr.triangles.size(); ii++) {
                    Triangle trrr = trr.triangles.get(ii);
                    if (!vis[trrr.index]) {
                        q.enqueue(trrr);
                        trrr.var = trr.var + 1;
                        vis[trrr.index] = true;
                    }
                }
            }
            if (dia < mxdist)
                dia = mxdist;
            for (int ii = 0; ii < t.size(); ii++) {
                vis[t.get(ii).index] = false;
            }
        }
        return dia;
    }

    @SuppressWarnings("unchecked")
    public PointInterface[] CENTROID() {
        int sz = trlist.size();
        int[] visited = new int[sz];
        for (int i = 0; i < sz; i++)
            visited[i] = -1;
        int num = 0;
        for (int i = 0; i < sz; i++) {
            if (visited[i] == -1) {
                dfs(i, visited, num);
                num++;
            }
        }
        ArrList<Triangle>[] arr = (ArrList<Triangle>[]) new ArrList[num];
        for (int i = 0; i < num; i++) {
            arr[i] = new ArrList<Triangle>();
        }
        for (int i = 0; i < sz; i++) {
            arr[visited[i]].push_back(trlist.get(i));
        }
        Point[] ans = new Point[num];
        for (int i = 0; i < num; i++) {
            ArrList<Point> p = new ArrList<Point>();
            RBTree<Point, Point> rb = new RBTree<Point, Point>();
            for (int j = 0; j < arr[i].size(); j++) {
                Triangle t = arr[i].get(j);
                if (rb.search1(t.a) == null) {
                    rb.insert(t.a, t.a);
                    p.push_back(t.a);
                }
                if (rb.search1(t.b) == null) {
                    rb.insert(t.b, t.b);
                    p.push_back(t.b);
                }
                if (rb.search1(t.c) == null) {
                    rb.insert(t.c, t.c);
                    p.push_back(t.c);
                }
            }
            int tot = p.size();
            float x = 0, y = 0, z = 0;
            for (int ii = 0; ii < tot; ii++) {
                x += p.get(ii).x;
                y += p.get(ii).y;
                z += p.get(ii).z;
            }
            x /= tot;
            y /= tot;
            z /= tot;
            Point pp = new Point(x, y, z);
            ans[i] = pp;
        }
        ptsort(ans, 0, num - 1);
        return ans;
    }

    public PointInterface CENTROID_OF_COMPONENT(float[] coord) {
        OTriple<Float> f = new OTriple<Float>(coord[0], coord[1], coord[2]);
        RedBlackNode<OTriple<Float>, Point> o = po.search1(f);
        if (o != null) {
            ArrList<Triangle> pppp = o.getValues().get(0).triangles;
            Triangle t = pppp.get(0);
            int[] visited = new int[trlist.size()];
            for (int i = 0; i < visited.length; i++)
                visited[i] = -1;
            dfs(t.index, visited, 0);
            ArrList<Point> parr = new ArrList<Point>();
            RBTree<Point, Point> pts = new RBTree<Point, Point>();

            for (int i = 0; i < trlist.size(); i++) {

                if (visited[i] == 0) {
                    t = trlist.get(i);
                    if (pts.search1(t.a) == null) {
                        parr.push_back(t.a);
                        pts.insert(t.a, t.a);
                    }
                    if (pts.search1(t.b) == null) {
                        parr.push_back(t.b);
                        pts.insert(t.b, t.b);
                    }
                    if (pts.search1(t.c) == null) {
                        parr.push_back(t.c);
                        pts.insert(t.c, t.c);
                    }
                }
            }
            float px = 0, py = 0, pz = 0;
            for (int i = 0; i < parr.size(); i++) {
                Point w = parr.get(i);
                px += w.x;
                py += w.y;
                pz += w.z;
            }
            px /= parr.size();
            py /= parr.size();
            pz /= parr.size();
            Point ans = new Point(px, py, pz);
            return ans;

        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public PointInterface[] CLOSEST_COMPONENTS() {
        int sz = trlist.size();
        if (sz == 0)
            return null;
        int[] visited = new int[sz];
        for (int i = 0; i < sz; i++)
            visited[i] = -1;
        int num = 0;
        for (int i = 0; i < sz; i++) {
            if (visited[i] == -1) {
                dfs(i, visited, num);
                num++;
            }
        }
        if (num == 1)
            return null;
        ArrList<Point>[] ptarr = (ArrList<Point>[]) new ArrList[num];
        ArrList<Triangle>[] trarr = (ArrList<Triangle>[]) new ArrList[num];
        for (int i = 0; i < num; i++) {
            ptarr[i] = new ArrList<Point>();
            trarr[i] = new ArrList<Triangle>();
        }
        for (int i = 0; i < sz; i++) {
            trarr[visited[i]].push_back(trlist.get(i));
        }
        for (int i = 0; i < num; i++) {
            ArrList<Triangle> trr = trarr[i];
            RBTree<Point, Point> p = new RBTree<Point, Point>();
            for (int j = 0; j < trr.size(); j++) {
                Triangle t = trr.get(j);
                Point p1 = t.a, p2 = t.b, p3 = t.c;
                if (p.search1(p1) == null) {
                    ptarr[i].push_back(p1);
                    p.insert(p1, p1);
                }
                if (p.search1(p2) == null) {
                    ptarr[i].push_back(p2);
                    p.insert(p2, p2);
                }
                if (p.search1(p3) == null) {
                    ptarr[i].push_back(p3);
                    p.insert(p3, p3);
                }
            }
        }
        PointInterface[] ans = new PointInterface[2];
        float mindist = Float.MAX_VALUE;
        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                for (int ii = 0; ii < ptarr[i].size(); ii++) {
                    for (int jj = 0; jj < ptarr[j].size(); jj++) {
                        Point pp1 = ptarr[i].get(ii);
                        Point pp2 = ptarr[j].get(jj);
                        if (pp1 == pp2) {
                            ans[0] = pp1;
                            ans[1] = pp2;
                            return ans;
                        } else if (pp1.dist(pp2) < mindist) {
                            mindist = pp1.dist(pp2);
                            ans[0] = pp1;
                            ans[1] = pp2;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public void ptsort(Point[] a, int left, int right) {
        if (left >= right)
            return;
        int mid = (right + left) / 2;
        ptsort(a, left, mid);
        ptsort(a, mid + 1, right);
        Point[] l = new Point[mid - left + 1];
        Point[] r = new Point[right - mid];
        for (int i = left; i <= mid; i++) {
            l[i - left] = a[i];
        }
        for (int i = mid + 1; i <= right; i++) {
            r[i - mid - 1] = a[i];
        }
        int i = 0, j = 0;
        while (i < l.length && j < r.length) {
            if (l[i].compareTo(r[j]) <= 0) {
                a[left + i + j] = l[i];
                i++;
            } else {
                a[left + i + j] = r[j];
                j++;
            }
        }
        while (i < l.length) {
            a[left + i + j] = l[i];
            i++;
        }
        while (j < r.length) {
            a[left + i + j] = r[j];
            j++;
        }
    }
}
