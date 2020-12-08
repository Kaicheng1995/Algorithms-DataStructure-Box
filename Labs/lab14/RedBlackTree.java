public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given BTree (2-3-4) TREE. */
    public RedBlackTree(BTree<T> tree) {
        Node<T> btreeRoot = tree.root;
        root = buildRedBlackTree(btreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3-4 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null) {
            return null;
        }
        if (r.getItemCount() == 1) {
            // TODO: Replace with code to create a 2 node equivalent
            RBTreeNode<T> rb = new RBTreeNode<>(true, r.getItemAt(0));
            if(r.getChildrenCount() != 0){
                rb.left = buildRedBlackTree(r.getChildAt(0));
                rb.right = buildRedBlackTree(r.getChildAt(1));
            }
            return rb;
        } else if (r.getItemCount() == 2) {
            // TODO: Replace with code to create a 3 node equivalent
            RBTreeNode<T> rb = new RBTreeNode<>(true, r.getItemAt(1));
            rb.left = new RBTreeNode<>(false, r.getItemAt(0));
            if(r.getChildrenCount() != 0){
                rb.right = buildRedBlackTree(r.getChildAt(2));
                rb.left.right = buildRedBlackTree(r.getChildAt(1));
                rb.left.left = buildRedBlackTree(r.getChildAt(0));
            }
            return rb;
        } else {
            // TODO: Replace with code to create a 4 node equivalent
            RBTreeNode<T> rb = new RBTreeNode<>(true, r.getItemAt(1));
            rb.left = new RBTreeNode<>(false, r.getItemAt(0));
            rb.right = new RBTreeNode<>(false, r.getItemAt(2));
            if(r.getChildrenCount() != 0){
                rb.left.left = buildRedBlackTree(r.getChildAt(0));
                rb.left.right = buildRedBlackTree(r.getChildAt(1));
                rb.right.left = buildRedBlackTree(r.getChildAt(2));
                rb.right.right = buildRedBlackTree(r.getChildAt(3));
            }
            return rb;
        }

    }


    /* Flips the color of NODE and its children. Assume that NODE has both left
       and right children. */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /* Rotates the given node NODE to the right. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        try {
            RBTreeNode<T> rtn = new RBTreeNode<>(node.isBlack, node.left.item, node.left.left, null);
            rtn.right = new RBTreeNode<>(false, node.item, node.left.right, node.right);
            return rtn;
        }catch(NullPointerException ex){
            return node;
        }
    }

    /* Rotates the given node NODE to the left. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        try {
            RBTreeNode<T> rtn = new RBTreeNode<>(node.isBlack, node.right.item, null, node.right.right);
            rtn.left = new RBTreeNode<>(false, node.item, node.left, node.right.left);
            return rtn;
        }catch (NullPointerException ex){
            return node;
        }
    }

    public void insert(T item) {   
        root = insert(root, item);  
        root.isBlack = true;    
    }

    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // TODO: YOUR CODE HERE
        if (node == null) {
            return new RBTreeNode<>(false, item);
        }

        int comp = item.compareTo(node.item);
        if (comp == 0) {
            return node;
        } else if (comp < 0) {
            node.left = insert(node.left,item);
        } else {
            node.right = insert(node.right,item);
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    /* Returns whether the given node NODE is red. Null nodes (children of leaf
       nodes are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

}
