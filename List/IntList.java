/**
 * 1.create a List
 * 2.create method size to calculate the size of the list
 * 3.create method get to access the List, get the ith item of the list
 */
public class IntList {
    public int first;                     //content 1
    public IntList last;                  //content 2
    public IntList(int f, IntList l) {    //constructor: enable input
        first = f;
        last = l;
    }

    public static void main(String[] agrs) {
        IntList x = new IntList(15, null);     //Intlist x：reference variable, create a pointer/box to store the address of IntList(15, null)
        x = new IntList(10, x);
        x = new IntList(5, x);          // create a List
        System.out.println(x.last.first);
        System.out.println(x.last.last);  //here prints out the address of the new IntList

        System.out.println(sizeR1(x));  // size Recursion 1 : creat a static method
        System.out.println(x.sizeR2());  // size Recursion 2 : creat a non-static method, here returns a address
        System.out.println(x.iterativeSize());  // size Iteration : for, while, do while
        System.out.println(x.get(0));  // get Iteration
        System.out.println(x.getrecursion(2));  // get Recursion
    }

    //________ size RECURSION 1 ________
    public static int sizeR1(IntList l) {      //NOTICE: (if) non-static method sizeR1(IntList) cannot be referenced from a static context
        if (l.last == null) {
            return 1;
        } else {
            return 1 + sizeR1(l.last);
        }
    }
    //________  size RECURSION 2 ________
    public int sizeR2() {                      // (if static)
        if (this.last == null) {               // non-static variable this cannot be referenced from a static context
            return 1;
        } else {
            return 1 + this.last.sizeR2();
        }
    }
    //________  size ITERATION ________
    public int iterativeSize(){
        int size = 0;
        IntList p = this;
        while(p != null){                      // here p points at the first node's address, if p==null, it means there's nothing
            size += 1;
            p = p.last;
        }
        return size;
    }
    //********* (WRONG CASES) **********
    /*public int iterative(){
        int length = 0;
        while(this.last != null){
            length += 1;            //错误。一直在循环，因为条件一直固定存在）
        }
    }*/

    //________ get ITERATION ________
    public int get(int i){
        IntList a = this;
        int result = 0;              // NOTICE: 局部变量不经初始化无法使用
        for(int j=0; j<i+1; j++){
            result = a.first;
            a = a.last;
        }
        return result;
    }

    //________ get RECURSION ________
    public int getrecursion(int i){
        if(i == 0){
            return this.first;          // NOTICE: Every recursion needs a base case!!!!
        }
        else{
            return this.last.getrecursion(i-1);  // 起到了往后移的作用
        }
    }
}
