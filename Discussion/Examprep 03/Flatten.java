public class Flatten {

    /**
     * takes in a 2-D array x and returns a 1-D array that
     * contains all of the arrays in x concatenated together
     */
    public static int[] flatten(int[][] x) {

        int totalLength = 0;
        for (int i = 0; i < x.length; i++) {
            totalLength = x[i].length + totalLength;
        }

        int[] a = new int[totalLength];

        int aIndex = 0;
        for (int[] item : x) {
            for (int j = 0; j < item.length; j++) {
                a[aIndex] = item[j];
                aIndex++;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[][] x = new int[][] {{1,2}, {2,3}, {3,3,2}};
        int[] y = flatten(x);
    }
}
