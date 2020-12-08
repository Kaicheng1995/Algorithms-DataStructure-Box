public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    /* Creates an empty BST. */
    public BinarySearchTree() {
        super();
        // TODO: YOUR CODE HERE
    }

    /* Creates a BST with root as ROOT. */
    public BinarySearchTree(TreeNode root) {
        super(root);
        // TODO: YOUR CODE HERE
    }

    /* Returns true if the BST contains the given KEY. */
    public boolean contains(T key) {
        // TODO: YOUR CODE HERE
        return contains_helper(root, key);
    }

    /* Helper func */
    public boolean contains_helper(TreeNode t, T key) {
        if (t == null) {
            return false;
        } else if (t.item.equals(key)) {
            return true;
        } else if (key.compareTo(t.item) < 0) {
            return contains_helper(t.left, key);
        } else {
            return contains_helper(t.right, key);
        }
    }


    /* Adds a node for KEY iff KEY isn't in the BST already. */
    public void add(T key) {
        // TODO: YOUR CODE HERE
        if (!contains(key)) {
            if (root != null) {
                add_helper(root, key);
            } else {
                root = new TreeNode(key);
            }
        }
    }

    /* Helper func */
    public void add_helper(TreeNode t, T key) {
        if (t.item.compareTo(key) < 0) {
            if (t.right==null){
                t.right=new TreeNode(key);
            }else{
                add_helper(t.right,key);
            }
        }else{
            if (t.left==null){
                t.left=new TreeNode(key);
            }else{
                add_helper(t.left,key);
            }
        }
    }


    /* Deletes a node from the BST. 
     * Even though you do not have to implement delete, you 
     * should read through and understand the basic steps.
    */
    public T delete(T key) {
        TreeNode parent = null;
        TreeNode curr = root;
        TreeNode delNode = null;
        TreeNode replacement = null;
        boolean rightSide = false;

        while (curr != null && !curr.item.equals(key)) {
            if (((Comparable<T>) curr.item).compareTo(key) > 0) {
                parent = curr;
                curr = curr.left;
                rightSide = false;
            } else {
                parent = curr;
                curr = curr.right;
                rightSide = true;
            }
        }
        delNode = curr;
        if (curr == null) {
            return null;
        }

        if (delNode.right == null) {
            if (root == delNode) {
                root = root.left;
            } else {
                if (rightSide) {
                    parent.right = delNode.left;
                } else {
                    parent.left = delNode.left;
                }
            }
        } else {
            curr = delNode.right;
            replacement = curr.left;
            if (replacement == null) {
                replacement = curr;
            } else {
                while (replacement.left != null) {
                    curr = replacement;
                    replacement = replacement.left;
                }
                curr.left = replacement.right;
                replacement.right = delNode.right;
            }
            replacement.left = delNode.left;
            if (root == delNode) {
                root = replacement;
            } else {
                if (rightSide) {
                    parent.right = replacement;
                } else {
                    parent.left = replacement;
                }
            }
        }
        return delNode.item;
    }
}