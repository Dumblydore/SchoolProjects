import java.lang.reflect.Array;

/**
 * Created by medwar40 on 12/4/15.
 */
public class TreeSort {

    public static <V> void sort(V[] input) {
        BinarySearchTree<V> tree = new BinarySearchTree<>();
        for(V v : input)
            tree.add(v);
        tree.toArray(input);
    }
}
