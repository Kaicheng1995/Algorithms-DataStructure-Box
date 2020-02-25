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
