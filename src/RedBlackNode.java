@SuppressWarnings("unchecked")
public class RedBlackNode<T extends Comparable<T>, E extends Comparable<E>>
    implements RBNodeInterface<E> {

    public T key;
    public int colour = 0; // red is 0
    // by default, every node is red
    public ArrList<E> values = new ArrList<E>();

    public RedBlackNode<T, E> left = null, right = null, parent = null;

    @Override
    public E getValue() {
        return null;
    }

    @Override
    public ArrList<E> getValues() {
        return values;
    }
}
