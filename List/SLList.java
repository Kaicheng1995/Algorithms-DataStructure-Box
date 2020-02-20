/**
 * STEP1: create global class - (SLList), all stuff should be included here
 * STEP2: create local calss - (IntNode), with 2 inputs
 * STEP3: recreate IntNode class to - (SLList), with only 1 input
 * STEP4: Use SLList class to fill main function
 * STEP5: create (addFirst) method to add x to the front of the list
 * STEP6: create (getFirst) method to get the fiRST item of the list
 * STEP7: create (addLast) method to add x to the end of the list
 * STEP8: create (size) method both interatively and recursively to calculate size of the list
 * STEP9: create (szie_fast) method to improve (size) method
 * STEP10: create empty list and fix the addlast bug （use sentinel)
 *
 *
 * Comparison: SLList vs IntList
 * Invariant: An invariant is a fact about a data structure that is guaranteed to be true
 */

public class SLList {
    //________ create IntList(2 input) ________
    public class IntNode {
        public int item;                 //content of IntNode Class
        public IntNode next;             //content of IntNode Class

        public IntNode(int i, IntNode n) {   //constructor: enable input of IntNode
            item = i;
            next = n;
        }
    }




    /*********  recreate IntList(1 input) *********/
    private IntNode sentinel;       //the content of SLList Class, 自始至终sentinel都不计入正式的node
                                 //private: you cannot use this from outside the SLList class
    public int fast_size;



    /*********  create empty list and fix addLast bug *********/
    //________ create empty list ________
    public SLList(){
        sentinel = new IntNode(63,null);
        fast_size = 0;
    }
    //________ recreate IntNode class ________
    public SLList(int x){                  //constructor: enable input of SLList
        sentinel = new IntNode(63, null);  //initialize the first node
        sentinel.next = new IntNode(x,null);    //creat a pointer/address to store the value/stuff of IntNode(x,null)
        fast_size = 1;
    }



    //________ main function________
    public static void main(String[] args) {
        SLList L = new SLList(10);
        L.addFirst(15);                 //优点：更清楚简单了
        L.addFirst(20);
        L.addLast(30);
        L.addLast(40);
        System.out.println(L.sentinel);       // the address of IntNode(10,null)
        System.out.println(L.sentinel.item);   // 63 (随便设的）
        System.out.println(L.sentinel.next);   // the address of IntNode(15,null)
        System.out.println(L.getFirst());   // 20
        System.out.println(L.size_iter());   // 5
        System.out.println(L.size_recur());  // 5
        System.out.println(L.fast_size());   // 5 , the method of fast_size()
        System.out.println(L.fast_size);     // 5 , the instance of SLList


        L.sentinel.next.next = L.sentinel.next;   // Private variables and methods can only be accessed by code inside the same .java file
                                            // When you create a public member (i.e. method or variable), be careful, because you're effectively committing to supporting that member's behavior exactly as it is now, forever.

    }

    //________ addFirst method________
    public void addFirst(int x) {            //这个 Method 把IntList文件里 main function 里的步骤拆分了，因为SLList没有地址可填就单独创建了一个method
        sentinel.next = new IntNode(x, sentinel.next);      //从最底层 IntNode 做，否则从SLList做会乱
        fast_size += 1;
    }

    //________ getFirst method________
    public int getFirst() {
        return sentinel.next.item;    //sentinel 不计入正式的node
    }

    //________ addLast method________
    public void addLast(int x) {
        IntNode p = sentinel;                  // sentinel is a box of SLList, and always the first NODE at a list
        while(p.next != null){              // It's like a cursor!!!
            p = p.next;
        }
        p.next = new IntNode(x, null);  // 加到最后
        fast_size += 1;
    }

    //________ size method（iteratively)________
    public int size_iter() {
        IntNode p = sentinel;           //看图就知道sentinel永远是第一个NODE的地址，从此往后移动游标
        int s = 0;
        while(p.next != null){
            p = p.next;
            s += 1;
        }
        return s;      //计算了sentinel
    }

    //________ size method（recursively)________
    private int size_recur(IntNode p){                      //最终要写成 L.size_recur的形式，但L里并没有 next instance，所以只能建两个名称相同的method
        if (p.next == null){
            return 1;
        }
        return 1 + size_recur(p.next);
    }
    public int size_recur(){
        return size_recur(sentinel.next);
    }

    //________ fast size method________
    public int fast_size(){
        return fast_size;
    }
}


                    /** Conmparison:
                     * 1. The SLList class acts as a middleman between the list user and the naked recursive data structure
                     * 2. Users of IntList are need to know Java references well, and be able to think recursively
                     * 3. SLList is much simple to use, using the provided methods
                     * 4. Implement addFirst in IntList is very ineffient.
                     */

                    /* SLList: */
                    SLList L = new SLList(15);
                    L.addFirst(10);
                    L.addFirst(5);
                    int x = L.getFirst();

                    /* IntList: */
                    IntList L = new IntList(15, null);
                    L = new IntList(10, L);
                    L = new IntList(5, L);
                    int x = L.first;

                    /** Invariant:
                     * A SLList with a sentinel node has at least the following invariants:
                     *    1. The sentinel reference always points to a sentinel node.
                     *    2. The front item (if it exists), is always at sentinel.next.item.
                     *    3. The size variable is always the total number of items that have been added.
                     */
