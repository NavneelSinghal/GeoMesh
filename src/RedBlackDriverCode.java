public class RedBlackDriverCode {
    public static void main(String args[]) {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        String aa = "AA";
        String ab = "AB";
        String cc = "CC";
        String dd = "DD";
        String cd = "CD";
        String ba = "BA";
        String bb = "BB";
        RBTree<Integer, String> r = new RBTree<Integer, String>();
        r.insert(a, aa);
        r.insert(a, ab);
        ;
        r.insert(d, dd);
        r.insert(c, cc);
        r.insert(b, bb);
        r.insert(b, ba);
        r.insert(c, cd);
        RedBlackNode<Integer, String> w = r.search1(3);
        RedBlackNode<Integer, String> w2 = r.search1(5);
        RedBlackNode<Integer, String> w3 = r.search1(2);
        RedBlackNode<Integer, String> w4 = r.search1(1);
        // use search1 for returning null if not found
        if (w != null)
            for (int i = 0; i < w.values.size(); i++) {
                System.out.print(w.values.get(i));
            }
        System.out.println("");
        if (w2 != null)
            for (int i = 0; i < w2.values.size(); i++) {
                System.out.print(w2.values.get(i));
            }
        System.out.println("");
        if (w3 != null)
            for (int i = 0; i < w3.values.size(); i++) {
                System.out.print(w3.values.get(i));
            }
        System.out.println("");
        if (w4 != null)
            for (int i = 0; i < w4.values.size(); i++) {
                System.out.print(w4.values.get(i));
            }
        System.out.println("");
    }
}