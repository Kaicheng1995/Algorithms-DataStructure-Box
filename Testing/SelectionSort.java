/**
 * Find the smallest item in an array (dynamic）
 * Move it to the front （dynamic）
 * Sort （combine above 2 method）
 */


public class SelectionSort {


    /*
    find the smallest item starting from index "front"
     */
    public static int find_Smallest(String[] A, int front) {

        int Smallest = front;
        for (int i = front; i < A.length; i++) {

            int compare = A[Smallest].compareTo(A[i]);
            if (compare > 0) {
                Smallest = i;
            }
        }
        return Smallest;
    }


    /*
    move it to the front
     */
    public static void swap(String[] A, int a, int b) {

        String temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }


    /*
    Sort
     */
    public static void Sort(String[] A) {

        for (int i = 0; i < A.length; i++) {

            int S = find_Smallest(A, i);

            swap(A, i, S);
        }
    }

}
