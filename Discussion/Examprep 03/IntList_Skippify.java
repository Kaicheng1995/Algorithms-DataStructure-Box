/**
 * Suppose we have the following IntList class, as defined in lecture and lab, with an
 * added skippify function.
 * Suppose that we define two IntLists as follows.
 * 1 IntList A = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
 * 2 IntList B = IntList.list(9, 8, 7, 6, 5, 4, 3, 2, 1);
 * Fill in the method skippify such that the result of calling skippify on A and B
 * are as below:
 * - After calling A.skippify(), A: (1, 3, 6, 10)
 * - After calling B.skippify(), B: (9, 7, 4)
 */

public class IntList_Skippify {
    public int first;
    public IntList_Skippify rest;

    public IntList_Skippify(int f, IntList_Skippify r) {
        first = f;
        rest = r;
    }

    public void skippify() {
        IntList_Skippify p = this;         //p作用 ：修改this的连接关系
        int n = 1;
        while (p != null) {
            IntList_Skippify next = p.rest;            //next作用： 寻找下一个node
            for (int i = 0; i < n; i++) {     //初始循环一次
                if (next == null) {
                    break;
                }
                next = next.rest;
            }
            p.rest = next;  //连接新发现的下一个node
            p = p.rest;  //连接完毕后，开始切换到新连接的那个node，为下一次连接准备
            n++;
        }
    }

    public static void main(String[] args) {
        IntList_Skippify A = new IntList_Skippify(1, null);
        A.rest = new IntList_Skippify(2, null);
        A.rest.rest = new IntList_Skippify(3, null);
        A.rest.rest.rest = new IntList_Skippify(4, null);
        A.rest.rest.rest.rest = new IntList_Skippify(5, null);
        A.rest.rest.rest.rest.rest = new IntList_Skippify(6, null);

        A.skippify();
    }
}
