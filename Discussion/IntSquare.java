/**
 * 1. S只剩一个node时，rest = null
 * 2. S剩两个node时，rest = square ( 指向最后一个node )
 * 3. S剩三个node时，rest = square ( 指向倒数第二个node )
 * 4. S初始指向数为3个node，即与代入 x 的指向相同, square ( x )
 * 5. rest 最终的值是null，其次都是square()，即 return M 的值
 *
 * square: 不破坏原有列，创建了新的列
 * squareDestructive: 破坏了原有列，直接在原有列上修改内容
 *
 * 分别用 recursion 和 iteration 来写出4种方法
 *
 * if else不能用在iteration上，因为两步判断直接结束，只有recursion可以重复call method进行循环，
 * 所以iteration适合 while loop，for loop 等本身自带loop的工具
 *
 */


public class IntSquare {
    public int first;
    public IntSquare last;

    public IntSquare(int f, IntSquare l){
        first = f;
        last = l;
    }


    public static void main(String[] args) {
        IntSquare x = new IntSquare(10, null);
        x = new IntSquare(20, x);
        x = new IntSquare(30, x);
        IntSquare y = square_Recur(x);
        IntSquare z = square_Iter(x);
        IntSquare w = squareDestructive_Recur(x);
        IntSquare h = squareDestructive_Iter(x);
    }




        /** square - recursion*/
    public static IntSquare square_Recur(IntSquare S){
        if (S == null){
            return S;
        }
        else{
            IntSquare rest = square_Recur(S.last);           //如果直接用 S = S.last, 只能循环一次，只有recursion能反复循环
            IntSquare M = new IntSquare(S.first * S.first, rest);
            return M;
        }
    }
        /** square - iteration*/
    public static IntSquare square_Iter(IntSquare S){
        if(S == null){
            return S;
        }
        IntSquare B = S.last;
        IntSquare M = new IntSquare(S.first * S.first, null);
        IntSquare C = M;
        while (B != null){
            C.last = new IntSquare(B.first * B.first, null);
            B = B.last;               //B负责填数字，C负责指针
            C = C.last;
        }
        return M;
    }




    /** Destructive - recursion*/
    public static IntSquare squareDestructive_Recur(IntSquare S){
        if(S == null){
            return S;
        }
        else{
            S.first *= S.first;
            squareDestructive_Recur(S.last);
        }
        return S;
    }


    /** Destructive - iteration*/
    public static IntSquare squareDestructive_Iter(IntSquare S){
        while (S != null){
            S.first *= S.first;
            S = S.last;
        }
        return S;
    }
}
