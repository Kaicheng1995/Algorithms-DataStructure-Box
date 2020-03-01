/**
 * shortcoming for DLList:
 *      get() function can be slow, because needing to scan one by one
 *
 *
 * shortcoming for AList
 *      add() function can be slow, because needing to create and fill memory (arraycopy)
 *
 *        !!!  SOLUTION 1 : 增加 capacity，例如 reszie (size * 2)
 *        !!!  SOLUTION 2 : 方法一会造成空间浪费，可以设定"usage ratio" = size / item.length, 小于25%会缩减array空间
 */




/** Invariants for AList:
 *
 * 1. The position of next item to be inserted is always size.
 * 2. size is always the number of items in the AList
 * 3. The last item in the list is always in position (size-1)
 */


/**
 * index:   0  1  2  3   4  5
 * array: { 2, 1, 5, 9, -3, 8, null, null, null, null, null, null }
 * size: 6
 */

public class AList<Replace>{

    //create instances
    private Replace[] item;
    private int size;


    //create an empty array
    public AList() {
        item = (Replace[]) new Object[100];          // !!! JAVA DON'T ALLOW TO CREATE ARRAYS OF GENERIC OBJECTS
        size = 0;
    }

    //resize
    public void resize(int capacity){
        Replace[] a = (Replace[]) new Object[capacity];
        System.arraycopy(item, 0, a, 0, size);
        item = a;
    }


    //addlast (+ RESIZE)
    public void addlast(Replace x) {
        if (size == item.length) {
            resize(size + 1);
        }
        item[size] = x;
        size += 1;
    }


    //getlast
    public Replace getlast(){
        return item[size-1];
    }

    //get
    public Replace get(int x){
        return item[x];
    }

    //removelast
    public Replace removelast(){
        item[size-1] = null;          //it's a way to save memory
        size -= 1;
        return item[size-1];
    }

    public static void main(String[] args){
        System.out.println(1);
    }

}
