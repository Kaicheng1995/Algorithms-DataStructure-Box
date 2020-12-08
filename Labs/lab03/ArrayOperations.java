public class ArrayOperations {
    /**
     * Delete the value at the given position in the argument array, shifting
     * all the subsequent elements down, and storing a 0 as the last element of
     * the array.
     */
    public static void delete(int[] values, int pos) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        // TODO: YOUR CODE HERE
        int[] copy = new int[values.length];
        System.arraycopy(values, 0, copy, 0, pos);
        System.arraycopy(values, pos+1, copy, pos, values.length-pos-1);
        copy[copy.length-1] = 0;

        System.arraycopy(copy, 0, values, 0, values.length);
    }

    /**
     * Insert newInt at the given position in the argument array, shifting all
     * the subsequent elements up to make room for it. The last element in the
     * argument array is lost.
     */
    public static void insert(int[] values, int pos, int newInt) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        // TODO: YOUR CODE HERE
        int[] copy = new int[values.length];
        System.arraycopy(values, 0, copy, 0, pos);
        copy[pos] = newInt;
        System.arraycopy(values, pos, copy, pos+1, values.length-pos-1);

        System.arraycopy(copy, 0, values, 0, values.length);
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        ArrayOperations.insert(values, 0, 0);
        int[] afterInsert1 = {0, 1, 2, 3, 4};
    }
}