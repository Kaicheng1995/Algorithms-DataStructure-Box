public class SLList {

    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }


    /**
     * 1.1 insert:
     *  takes in an integer x and an integer position. It inserts x at the given position
     */

    public void insert(int x, int position) {
        if (first == null || position == 0) {
            addFirst(x);
            return;
        }
        IntNode cursor = first;
        int i = 1;
        while (i < position && cursor.next != null) {
            cursor = cursor.next;
            i ++;
        }
        cursor.next = new IntNode(x, cursor.next);
    }

    /**
     * 1.2 reverse:
     * reverse the element using iteration ( should not use new )
     */

    public void reverse() {
        if (first == null || first.next == null) {
            return;
        }
        IntNode ptr = first.next;
        first.next = null;
        while (ptr != null) {
            IntNode cursor = ptr.next;
            ptr.next = first;
            first = ptr;
            ptr = cursor;
        }
    }

    /**
     * 1.3 reverse:
     * using recursion
     */
    public IntNode reverse_recur(IntNode R) {
        if (R == null || R.next == null) {
            return R;
        }
        IntNode end = R.next;
        IntNode reserved = reverse_recur(R.next);
        end.next = R;
        R.next = null;
        return reserved;
    }


    /**
     * 2.1 insert method for array
     *    (here I use arraycopy, actually we can also use iteration)
     */

    public static int[] insert(int[] arr, int item, int position) {
        position = Math.min(position, arr.length);
        int[] newarray = new int[arr.length + 1];
        newarray[position] = item;
        System.arraycopy(arr, 0 ,newarray, 0, position);
        System.arraycopy(arr, position, newarray, position + 1, arr.length - position);
        return newarray;
    }


    /**
     * 2.2 array reverse - destructively
     *
     */

    public static void reverse(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size/2; i++) {
            int temp = arr[i];
            arr[i] = arr[size - i - 1];
            arr[size - i - 1] = temp;
        }
    }


    /**
     * non-destructive replicate
     * For example, replicate([3, 2, 1]) would return [3, 3, 3, 2, 2, 1].
     */

    public static int[] replicate(int[] arr) {
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            size = size + arr[i];
        }
        int[] replicate = new int[size];
        int position = 0;

        for (int i = 0; i < arr.length; i++) {
            int stop = arr[i];
            position = position + stop;

            for (int j = position - arr[i]; j < position; j++) {
                replicate[j] = arr[i];
            }
        }
        return replicate;
    }



    //另一种解法 for each
    public static int[] replicate(int[] arr) {
        int size = 0;
        for (int i = 0; i < arr.length; i++) {
            size = size + arr[i];
        }
        int[] replicate = new int[size];
        int i = 0;
        for (int item : replicate) {
            for (int j = 0; j < item; j++) {
                replicate[i] = item;
                i++;
            }
        }
        return replicate;
    }
}
