import java.util.LinkedList;
import java.util.Iterator;

public class BST<T> {

    BSTNode<T> root;

    public BST(LinkedList<T> list) {
        root = sortedIterToTree(list.iterator(), list.size());
    }

    /* Returns the root node of a BST (Binary Search Tree) built from the given
       iterator ITER  of N items. ITER will output the items in sorted order,
       and ITER will contain objects that will be the item of each BSTNode. */
    private BSTNode<T> sortedIterToTree(Iterator<T> iter, int N) {
        // TODO: YOUR CODE HERE
        if (N == 0) {
            return null;
        }
        else if (N == 1) {
            return new BSTNode<>(iter.next());
        }
        return new BSTNode<>(sortedIterToTree(iter, N/2), iter.next(), sortedIterToTree(iter, N - N / 2 - 1));
    }

    /* Prints the tree represented by ROOT. */
    private void print() {
        print(root, 0);
    }

    private void print(BSTNode<T> node, int d) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < d; i++) {
            System.out.print("  ");
        }
        System.out.println(node.item);
        print(node.left, d + 1);
        print(node.right, d + 1);
    }

    class BSTNode<T> {
        T item;
        BSTNode<T> left;
        BSTNode<T> right;

        BSTNode(T item) {
            this.item = item;
        }

        BSTNode(BSTNode<T> left, T obj, BSTNode<T> right) {
            this.left = left;
            this.item = obj;
            this.right = right;
        }
    }
}
