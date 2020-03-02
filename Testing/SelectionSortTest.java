import edu.princeton.cs.algs4.Selection;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectionSortTest {

    @Test
    public void find_Smallest() {
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;
        int actual = SelectionSort.find_Smallest(input, 2);

        assertEquals(expected, actual);
    }

    @Test
    public void swap() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "have", "i", "egg"};

        SelectionSort.swap(input, 0, 2);
        assertArrayEquals(expected, input);
    }

    @Test
    public void sort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};

        Selection.sort(input);
        assertArrayEquals(expected, input);

    }
}
