/**
 * 1. S只剩一个node时，rest = null
 * 2. S剩两个node时，rest = square ( 指向最后一个node )
 * 3. S剩三个node时，rest = square ( 指向倒数第二个node )
 * 4. S初始指向数为3个node，即与代入 x 的指向相同, square ( x )
 * 5. rest 最终的值是null，其次都是square()，即 return M 的值
 * 
 * 两种方法，以上是recursion，另一种是iteration
 */

public class IntSquare {
    public int first;
    public IntSquare last;

    public IntSquare(int f, IntSquare l){
        first = f;
        last = l;
    }

    public static IntSquare square(IntSquare S){
        if (S == null){
            return S;
        }
        else{
            IntSquare rest = square(S.last);           //如果直接用 S = S.last, 只能循环一次，只有recursion能反复循环
            IntSquare M = new IntSquare(S.first * S.first, rest);
            return M;
        }
    }

    public static IntSquare squareDestructive(IntSquare S){
        IntSquare D = S;
        while (D != null){
            D.first *= D.first;
            D = D.last;
        }
        return D;
    }
    
    public static void main(String[] args){
        IntSquare x = new IntSquare(10, null);
        x = new IntSquare(20, x);
        x = new IntSquare(30, x);
        IntSquare y = square(x);
    }
}
