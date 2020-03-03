public class QuikMaths {
    public static void multiplyBy3(int[] A) {
        for (int x: A) {
            x = x * 3;
        }
    }
    public static void multiplyBy2(int[] A) {
        int[] B = A;
        for (int i = 0; i < B.length; i+= 1) {
            B[i] *= 2;
        }
    }
    public static void swap (int A, int B ) {
        int temp = B;
        B = A;
        A = temp;
    }
    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{2, 3, 3, 4};
        multiplyBy3(arr);        //foreach 一般情况下不能改变值

        /* Value of arr: {2, 3, 3, 4} */

        arr = new int[]{2, 3, 3, 4};
        multiplyBy2(arr);

        /* Value of arr: {4, 6, 6, 8} */

        int a = 6;
        int b = 7;
        swap(a, b);       //method 只是替代值，不管是primitive 还是reference都是新建的变量，所以这里并没有交换ab，而是交换了AB

        /* Value of a: 6 Value of b: 7 */
    }
}
