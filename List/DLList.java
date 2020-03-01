public class DLList<T>{

    //Node Class
    public class Node{
        public Node prev;
        public T item;
        public Node next;

        public Node(Node f, T i, Node l){
            prev = f;
            item = i;
            next = l;
        }
    }

    //Create Instances
    public Node sentinel;
    public int Size;

    //Create empty list
    public DLList(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        Size = 0;
    }

    //addFirst
    public void addFirst(T item){
        Node A = new Node(sentinel, item, sentinel.next);
        sentinel.next = A;
        sentinel.next.next.prev = A;
        Size += 1;
    }

    //addLast
    public void addLast(T item){
        Node A = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = A;
        sentinel.prev.prev.next = A;
        Size += 1;
    }

    //isEmpty
    public boolean isEmpty(){
        if(Size == 0){
            return true;
        }
        return false;
    }

    //size
    public int size(){
        return Size;
    }

    //printDeque
    public void printDeque(){
        Node p = sentinel;
        for (int i=0; i<Size; i++){
            System.out.println(p.next.item);
            p = p.next;
        }
    }

    //removeFirst
    public T removeFirst(){
        if (Size == 0){
            return null;
        }
        else {
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            Size -= 1;
            return sentinel.next.item;
        }
    }

    //removeLast
    public T removeLast(){
        if (Size == 0){
            return null;
        }
        else {
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            Size -= 1;
            return sentinel.prev.item;
        }
    }

    //get
    public T get(int index){
        Node p = sentinel;
        int i = 0;
        while (i < index){
            p = p.next;
            i += 1;
        }
        return p.item;
    }

    //get recursion
    public T getRecursive(int index, Node A){
        if (index == 0){
            return A.item;
        }
        else {
            A = A.next;
            return getRecursive(index-1, A);
        }
    }
    public T getRecursive(int index){
        return getRecursive(index, sentinel);
    }


    //main function
    public static void main(String[] args){
        DLList<Integer> L = new DLList<>();
        L.addFirst(10);
        L.addFirst(20);
        L.addFirst(30);
        L.addFirst(40);
        L.addLast(5);
        L.addLast(4);
        L.removeFirst();
        L.removeLast();
        L.printDeque();

        System.out.println("get result is: " + L.get(2));
        System.out.println("get result is: " + L.getRecursive(2));
    }

    //deep copy of "other" list
    public DLList(DLList<T> other){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        Size = 0;

        for(int i=0; i<other.size(); i++){
            addLast(other.get(i));
        }
    }
}
