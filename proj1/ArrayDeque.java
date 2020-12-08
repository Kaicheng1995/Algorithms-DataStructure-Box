public class ArrayDeque<T> implements Deque<T>{

    // instances
    private T[] items;
    private int front;     // two sentinel to avoid resizing array if add T
    private int back;
    private int Size;
    private static double WasteLine = 0.25;


    /* Constructor: empty deque */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 0;
        back = 1;
        Size = 0;
    }


    /*****************
     * Helper Method
     *****************/

    // 1. check full
    private boolean isFull() {
        return Size == items.length;
    }

    // 2. check waste
    private boolean isWaste() {
        return items.length >= 16 && Size < items.length * WasteLine;
    }

    // 3. return the index of Next T
    private int Next_One(int index) {
        return (index + 1) % items.length;
    }

    // 4. return the index of Previous T
    private int Prev_One(int index) {
        return  (index - 1 + items.length) % items.length;
    }

    // 5. resize the array to what we want
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int Old_Index = Next_One(front);
        for (int New_Index = 0; New_Index < Size; New_Index++) {
            a[New_Index] = items[Old_Index];
            Old_Index = Next_One(Old_Index);
        }
        items = a;
        front = capacity - 1;
        back = Size;
    }

    // 6. upsize if full
    private void Upsize() {
        resize(Size * 2);
    }

    // 7. downsize if waste
    private void Downsize() {
        resize(items.length / 2);
    }




    /*****************
     * Method
     *****************/

    // Add first: must take constant time
    @Override
    public void addFirst(T x) {
        if (isFull()) {
            Upsize();
        }
        items[front] = x;
        front = Prev_One(front);
        Size++;
    }


    // Add last: must take constant time
    @Override
    public void addLast(T x) {
        if(isFull()) {
            Upsize();
        }
        items[back] = x;
        back = Next_One(back);
        Size++;
    }


    // return size
    @Override
    public int size() {
        return Size;
    }

    // Prints the items in the deque from first to last, separated by a space
    @Override
    public void printDeque() {
        for (int i = Next_One(front); i != back; i = Next_One(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }


    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if(isWaste()) {
            Downsize();
        }
        if(!isEmpty()) {
            front = Next_One(front);
            T output = items[front];
            items[front] = null;
            Size--;
            return output;
        }else {
            return null;
        }
    }

    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeLast() {
        if(isWaste()) {
            Downsize();
        }
        if(!isEmpty()) {
            back = Prev_One(back);
            T output = items[back];
            items[back] = null;
            Size--;
            return output;
        }else {
            return null;
        }
    }

    // Gets the item at the given index
    @Override
    public T get(int index) {
        if (index > Size) {
            return null;
        }else {
            return items[Next_One(front + index)];
        }
    }
}
