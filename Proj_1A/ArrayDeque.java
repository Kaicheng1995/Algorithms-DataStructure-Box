public class ArrayDeque<T>{

    private T[] items;
    private int front;
    private int back;
    private int size;
    private static double WasteLine = 0.25;

    /* empty deque */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        front = 0;                    //initialize the front sentinel's index in array is 0
        back = 1;                     //initialize back sentinel's index in array is 1
        size = 0;
    }


    /* check full */
    public boolean isFull(){
        return size == items.length;
    }

    /* check waste */
    public boolean isWaste(){
        return items.length >=16
                && (size / items.length) < WasteLine;
    }

    /* check empty */
    public boolean isEmpty(){
        return size == 0;
    }

    /* return size */
    public int Size(){
        return size;
    }


    /**
     * add one recursively
     *
     * @return the Index of the next T of the (Input index)
     *即： 输入一个 array index，返回其所在位置下一位的 array index， 由于是循环的，所以需要除以长度
     *
     */
    public int Next_One(int index){
        return (index + 1) % items.length;       //index + 1 的最大值也就是和 length 相等而已 (不直接用 index + 1是因为要循环)
    }


    /**
     * add one recursively
     *
     * @return the Index of the next T of the (Input index)
     *即： 输入一个 array index，返回其所在位置下一位的 array index， 由于是循环的，所以需要除以长度
     *
     */
    public int Prev_One(int index){
        return (index - 1 + items.length) % items.length;   // 求0位的上一位时为负数，所以再加一个长度去除就没问题了，不影响余数
    }


    /* resize */
    public void resize(int capacity){
        T[] a = (T[]) new Object[capacity];

        //the index of first T in old array
        int old_Index = Next_One(front);                 // front 上一位的 index，即第一个 T 的 index 位置

        //copy items to new array
        for (int new_Index = 0; new_Index < size; new_Index++){

            a[new_Index] = items[old_Index];      // 因为是 recursive，所以不方便用 arraycopy

            old_Index = Next_One(old_Index);        // move to the next T. when passed whole size
        }

        items = a;
        front = capacity - 1;  //因为新的array，0位是第一个 T，所以 front's index 直接是 capacity - 1
        back = size;    //因为新的array，0位是第一个 T，所以 back's index 直接是size


    }




    /**
     * Check full, then add
     */


    /* Upsize */
    public void Upsize(){
        resize(size * 2);
    }

    /* addFirst */
    public void addFirst(T x){
        if (isFull()){
            Upsize();
        }
        items[front] = x;     //把新加的 T 放在原 front 的位置上，front 自动往前退一步, 其值减 1
        front = Prev_One(front);
        size += 1;
    }

    /* addLast */
    public void addLast(T x){
        if (isFull()){
            Upsize();
        }
        items[back] = x;
        back = Next_One(back);
        size += 1;
    }



    /**
     * Check waste, then remove
     */

    /* Downsize */
    public void Downsize(){
        resize(items.length / 2);     //size都不足25%，直接把长度砍半
    }


    /* remove and return the first T*/
    public T removeFirst(){
        if (isWaste()){
            Downsize();
        }
        front = Next_One(front);
        T toRemove = items[front];
        items[front] = null;
        if (!isEmpty()){
            size -= 1;          //防止已经是空的情况下，front和back重合，size变成负数
        }
        return toRemove;
    }

    /* remove and return the last T */
    public T removeLast(){
        if (isWaste()){
            Downsize();
        }
        back = Prev_One(back);
        T toRemove = items[back];
        items[back] = null;
        if (!isEmpty()){
            size -= 1;
        }
        return toRemove;
    }


    /**
     * print out T in deque
     */
    public void printDeque(){

        //从第一个 T 开始，直到 index 达到 back，每次 i 都要递进，因为 i 是array index，所以需要调用 Next_One
        for (int i = Next_One(front); i != back; i = Next_One(i)){

            System.out.print(items[i] + " ");

        }
        System.out.println();
    }



    /**
     * get the ith T
     */
    public T get(int i){
        if (i > size){
            return null;
        }
        return items[Next_One(front + i - 1)];

    }


    /**
     * create a deep copy of "other"
     */
    public ArrayDeque(ArrayDeque<T> other){

        items = (T[]) new Object[8];
        front = 0;
        back = 1;
        size = 0;

        for(int i = Next_One(other.front); i != other.back; i = Next_One(i)){
            addLast(other.items[i]);
        }
    }

    /**
     * main function
     */
    public static void main(String args[]){
        ArrayDeque<Integer> test = new ArrayDeque<>();

        test.addLast(10);
        test.addLast(20);
        test.addFirst(5);
        test.addFirst(4);
        test.addFirst(3);
        test.removeFirst();
        test.removeLast();

        System.out.println(test.get(2));
        System.out.println(test.Size());

        ArrayDeque<Integer> copy = new ArrayDeque<>(test);

        System.out.println(copy.get(2));
        System.out.println(test.Size());
    }

}
