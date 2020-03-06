public class IntList_Sans {

    public int first;
    public IntList_Sans rest;

    public IntList_Sans(int f, IntList_Sans r) {
        first = f;
        rest = r;
    }


    /**
     *  Non-destructively creates a copy of x that contains no occurences of y.
     */
    public static IntList_Sans ilsans(IntList_Sans x, int y) {

        if (x == null) {                  //base case : x == null
            return null;
        }
        if (x.first == y) {               //存在等于y的node，就继续recur，否则就直接新建
            return ilsans(x.rest, y);
        }
        return new IntList_Sans(x.first, ilsans(x.rest, y));
    }

    /**
     * Destructively modify and return x to contain no occurences of y,
     * without using the keyword "new".
     * 这个方法有个弊端就是第一个永远改不了 （所以是有问题的）
     */
     public static IntList_Sans dilsans(IntList_Sans x, int y) {
         if (x == null) {
             return null;
         }
         x.rest = dilsans(x.rest, y);    //x.rest是剩下的列，dilsans(x.rest, y)是将剩下的列清理干净
         if (x.first == y) {             //这里的x永远跟着input走，x是不断变化的
             return x.rest;
         }
         return x;
     }

    /**
     * RECURSION:
     * 1. base case : tell when stops 什么时候recur停止
     * 2. call method itself : has symbolic meaning  象征意义
     * 3. statements : actual content that drives the method 造成意义的实际公式内容
     */



    public static void main(String[] args) {
        IntList_Sans test = new IntList_Sans(1, null);
        test = new IntList_Sans(2, test);
        test = new IntList_Sans(3, test);
        test = new IntList_Sans(4, test);
        IntList_Sans output = dilsans(test, 3);

    }
}
