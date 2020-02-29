public class LinkedListDeque<T> {

    //nested class
    public class Node{
        public Node prev;
        public T item;
        public Node next;

        //constructor for nested class
        public Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }


    //create instances
    public Node Front;
    public Node End;
    public int Size;

    //create an empty linkedlistdeque
    public LinkedListDeque(){
        Front = new Node(null, null, null);
        End = new Node(null, null, null);
        Front.prev = End;
        Front.next = End;
        End.prev = Front;
        End.next = Front;
        Size = 0;
    }

    //initialize the first node
    public LinkedListDeque(T x){
        Front = new Node(null, null, null);
        End = new Node(null, null, null);
        Front.prev = End;
        End.next = Front;
        Front.next = new Node(Front, x, End);
        End.prev = Front.next;
        Size = 1;
    }

    //addFirst (把线剪干净）
    public void addFirst(T item){
        Front.next.prev = null;
        Front.next = new Node(Front, item, Front.next);
        Front.next.next.prev = Front.next;

        Size += 1;
    }

    //addLast (把线剪干净）
    public void addLast(T item){
        End.prev.next = null;
        End.prev = new Node(End.prev, item, End);
        End.prev.prev.next = End.prev;

        Size += 1;
    }

    //isEmpty
    public boolean isEmpty(){
        if (this.Size == 0){
            return true;
        }
        return false;
    }

    //Size
    public int size(){
        return Size;
    }

    //printDeque
    public void printDeque(){
        Node p = Front;
        for(int i=0; i<Size; i++){
            System.out.println(p.next.item);
            p = p.next;
        }
    }

    //removeFirst (线不用剪完也行）
    public T removeFirst(){
        if(Size == 0){
            return null;
        }
        else {
            Front.next = Front.next.next;
            Front.next.prev = Front;
            Size -= 1;
            return Front.next.item;
        }
    }

    //removeLast  (线不用剪完也行）
    public T removeLast(){
        if(Size == 0){
            return null;
        }
        else {
            End.prev = End.prev.prev;
            End.prev.next = End;
            Size -= 1;
            return End.prev.item;
        }
    }

    //get - iteration
    public T get(int index){
        Node p = Front;
        int i = 0;
        while(i < index){
            p = p.next;
        }
        return p.item;
    }


//    //get - recursion
//    public T getRecursive(int index){
//        if (index == 1){
//            return this.Front.next.item;
//        }
//        else{
//            remove the node, and recur again(need to use above method)
//            return this.Front.next.getRecursive(index-1);
//        }
//    }


//    //create a deep copy of other
//    public LinkedListDeque(LinkedListDeque other){
//
//    }



    public static void main(String[] args){
        LinkedListDeque<Integer> L = new LinkedListDeque<>(10);
        L.addFirst(20);
        L.addFirst(30);
        L.addFirst(40);
        L.addLast(5);
        L.addLast(4);
        L.removeFirst();
        L.removeLast();
        L.printDeque();
    }

}
