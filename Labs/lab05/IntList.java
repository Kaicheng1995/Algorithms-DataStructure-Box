/** A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 *
 * @author Maurice Lee and Wan Fung Chui
 */

public class IntList {

    /** The integer stored by this node. */
    public int item;
    /** The next node in this IntList. */
    public IntList next;

    /** Constructs an IntList storing ITEM and next node NEXT. */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /** Constructs an IntList storing ITEM and no next node. */
    public IntList(int item) {
        this(item, null);
    }

    /** Returns an IntList consisting of the elements in ITEMS.
     * IntList L = IntList.list(1, 2, 3);
     * System.out.println(L.toString()) // Prints 1 2 3 */
    public static IntList of(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next = new IntList(items[i]);
            last = last.next;
        }
        return head;
    }


    /**
     * return the size of the list
     */
    public int size() {
        if (this.next == null) {
            return 1;
        } else {
            return 1 + this.next.size();
        }
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException
     * if index out of bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public int get(int position) {
        if(position > this.size() - 1 || position < 0) {
            throw new IllegalArgumentException("YOUR MESSAGE HERE");
        }
        else {
            if (position == 0) {
                return this.item;          // NOTICE: Every recursion needs a base case!!
            } else {
                return this.next.get(position - 1);  // 起到了往后移的作用
            }
        }
    }

    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "1 2 3".
     *
     * @return The String representation of the list.
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < this.size(); i++) {
            output += this.get(i) + " ";
        }
        return output.substring(0,output.length()-1);
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */
    public boolean equals(Object obj) {
        if((!(obj instanceof IntList))) {
            return false;
        }
        IntList p = (IntList) obj;
        if (this.size() != p.size()) {
            return false;
        }
        else {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) != p.get(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Adds the given value at the end of the list.
     *
     * @param value, the int to be added.
     */
    public void add(int value) {
        IntList temp = this;
        if (temp == null) {
            temp = new IntList(value);
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new IntList(value, null);
    }

    /**
     * Returns the smallest element in the list.
     *
     * @return smallest element in the list
     */
    public int smallest() {
        int smallest = this.item;
        for (int i = 0; i < this.size(); i++) {
            if (smallest > this.get(i)) {
                smallest = this.get(i);
            }
        }
        return smallest;
    }

    /**
     * Returns the sum of squares of all elements in the list.
     *
     * @return The sum of squares of all elements.
     */
    public int squaredSum() {
        int sum = 0;
        for (int i = 0; i < this.size(); i++) {
            sum += this.get(i) * this.get(i);
        }
        return sum;
    }

    /**
     * Destructively squares each item of the list.
     *
     * @param L list to destructively square.
     */
    public static void dSquareList(IntList L) {
        IntList p = L;
        while (p != null) {
            p.item = p.item * p.item;
            p = p.next;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L list to non-destructively square.
     * @return the squared list.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.item * L.item, null);
        IntList ptr = res;
        L = L.next;
        while (L != null) {
            ptr.next = new IntList(L.item * L.item, null);
            L = L.next;
            ptr = ptr.next;
        }
        return res;
    }

    /** Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L list to non-destructively square.
     * @return the squared list.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.item * L.item, squareListRecursive(L.next));
    }

    /**
     * Returns a new IntList consisting of A followed by B,
     * destructively.
     *
     * @param A list to be on the front of the new list.
     * @param B list to be on the back of the new list.
     * @return new list with A followed by B.
     */
    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        IntList temp = A;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = B;
        return A;
    }

    /**
     * Returns a new IntList consisting of A followed by B,
     * non-destructively.
     *
     * @param A list to be on the front of the new list.
     * @param B list to be on the back of the new list.
     * @return new list with A followed by B.
     */
     public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        IntList output = new IntList(A.item, catenate(A.next, B));
        return output;
     }
}