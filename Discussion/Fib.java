public class Fib {
    public static void main(String[] args) {
        System.out.println(fib(6));
    }

    public static int fib(int n) {
        if (n <= 1) {
            return n;    //这步把recursion的n和事实数据联系起来了
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}



// ???????
public class Fib {
    public static void main(String[] args) {
        System.out.println(fib2(1,1,1,1));
    }

    public static int fib2(int n, int k, int f0, int f1) {
        if (n == k) {
            return f0; }
        else {
            return fib2(n, k + 1, f1, f0 + f1); }
    }
}
