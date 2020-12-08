public class LinkedListDeque<T> implements Deque<T>{

    // Node Class
    private class Node {
        private Node pre;
        private T item;
        private Node next;

        // Node constructor
        public Node(Node p, T i, Node n){
            pre = p;
            item = i;
            next = n;
        }
    }


    // Create 2 instances
    private Node sentinel;
    private int Size;


    // Deque constructor
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        Size = 0;
    }

    // Add first: must take constant time
    @Override
    public void addFirst(T item) {
        Node n = new Node(sentinel, item, sentinel.next);
        sentinel.next = n;
        sentinel.next.next.pre = n;
        Size++;
    }

    // Add last: must take constant time
    @Override
    public void addLast(T item) {
        Node n = new Node(sentinel.pre, item, sentinel);
        sentinel.pre = n;
        sentinel.pre.pre.next = n;
        Size++;
    }

    // Return size
    @Override
    public int size() {
        return Size;
    }

    // Prints the items in the deque from first to last, separated by a space
    @Override
    public void printDeque() {
        Node p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (Size == 0) {
            return null;
        }else {
            T output = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.pre = sentinel;
            Size--;
            return output;
        }
    }


    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeLast() {
        if (Size == 0) {
            return null;
        }else {
            T output = sentinel.pre.item;
            sentinel.pre = sentinel.pre.pre;
            sentinel.pre.next = sentinel;
            Size--;
            return output;
        }
    }

    // Gets the item at the given index: Using iteration
    @Override
    public T get(int index) {
        if (index < 0 || index > Size - 1) {
            return null;
        }
        else {
            int i = 0;
            Node p = sentinel.next;
            while (i < index) {
                p = p.next;
                i++;
            }
            return p.item;
        }
    }

    // recursion needs a helper method to avoid reinitialize node
    private T getRecursiveHelper(int index, Node n) {
        if (index < 0 || index > Size - 1) {
            return null;
        }
        else {
            if (index == 0) {       //base case
                return n.item;
            }else {
                n = n.next;         //traverse
                return getRecursiveHelper(index - 1, n);
            }
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

}
