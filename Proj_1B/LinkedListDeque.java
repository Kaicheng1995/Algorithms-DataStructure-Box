public class LinkedListDeque<T> implements Deque<T>{

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
    public int size;

    //Create an empty list
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    //addFirst
    @Override
    public void addFirst(T item){
        Node A = new Node(sentinel, item, sentinel.next);
        sentinel.next = A;
        sentinel.next.next.prev = A;
        size += 1;
    }

    //addLast
    @Override
    public void addLast(T item){
        Node A = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = A;
        sentinel.prev.prev.next = A;
        size += 1;
    }

    //isEmpty
    @Override
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    //size
    @Override
    public int size(){
        return size;
    }

    //printDeque
    @Override
    public void printDeque(){
        Node p = sentinel;
        for (int i=0; i<size; i++){
            System.out.println(p.next.item);
            p = p.next;
        }
    }

    //removeFirst
    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        else {
            T output = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return output;
        }
    }

    //removeLast
    @Override
    public T removeLast(){
        if (size == 0){
            return null;
        }
        else {
            T output = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return output;
        }
    }

    //get
    @Override
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



    //deep copy of "other" list
    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for(int i=0; i<other.size(); i++){
            addLast(other.get(i));
        }
    }


    //main function
    public static void main(String[] args){
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(10);
        L.addFirst(20);
        L.addFirst(30);
        L.addFirst(40);
        L.addFirst(50);
        L.addFirst(60);
        L.addFirst(70);
        L.addFirst(80);
        L.addFirst(90);
        L.addFirst(100);
        L.addLast(5);
        L.addLast(4);
        L.addLast(3);
        L.addLast(2);
        L.addLast(1);
        L.addLast(0);
        L.removeFirst();
        L.removeLast();
        L.printDeque();

        System.out.println("get result is: " + L.get(2));
        System.out.println("get result is: " + L.getRecursive(2));
    }
}
