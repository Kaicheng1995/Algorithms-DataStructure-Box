/**
 * STEP1: create global class - (SLList), all stuff should be included here
 * STEP2: create local calss - (IntNode), with 2 inputs
 * STEP3: recreate IntNode class to - (SLList), with only 1 input
 * STEP4: Use SLList class to fill main function
 * STEP5: create (addFirst) method to add x to the front of the list
 * STEP6: create (getFirst) method to get the fiRST item of the list
 *
 * Comparison: SLList vs IntList
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

    //*********  recreate IntList(1 input) *********
    private IntNode first;       //THE ONLY content of SLList Class
                                 //private: you cannot use this from outside the SLList class

    public SLList(int x) {       //constructor: enable input of SLList
        first = new IntNode(x, null);         //creat a pointer/address to store the value/stuff of IntNode(x,null)
    }

    //________ main function________
    public static void main(String[] args) {
        SLList L = new SLList(10);
        L.addFirst(15);                 //优点：更清楚简单了
        L.addFirst(20);
        System.out.println(L.first);       // the address of IntNode(10,null)
        System.out.println(L.first.item);   // 10
        System.out.println(L.first.next);   // null
        System.out.println(L.getFirst());   // 20

        L.first.next.next = L.first.next;   // Private variables and methods can only be accessed by code inside the same .java file
                                            // When you create a public member (i.e. method or variable), be careful, because you're effectively committing to supporting that member's behavior exactly as it is now, forever.

    }

    //________ addFirst method________
    public void addFirst(int x) {            //这个 Method 把IntList文件里 main function 里的步骤拆分了，因为SLList没有地址可填就单独创建了一个method
        first = new IntNode(x, first);       //从最底层 IntNode 做，否则从SLList做会乱
    }

    //________ getFirst method________
    public int getFirst() {
        return first.item;
    }
}


                    /** Conmparison:
                     * 1. The SLList class acts as a middleman between the list user and the naked recursive data structure
                     * 2. Users of IntList are need to know Java references well, and be able to think recursively
                     * 3. SLList is much simple to use, using the provided methods
                     * 4. Implement addFirst in IntList is very ineffient
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
