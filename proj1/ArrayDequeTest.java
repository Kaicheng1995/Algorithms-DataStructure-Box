import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void yourTestHere() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addFirst("c");
        ad1.addFirst("b");
        ad1.addFirst("a");

        //print out abc
        ad1.printDeque();

        //check out get function print a b c
        for (int i = 0; i < 3; i++) {
            System.out.print(ad1.get(i) + " ");
        }
        System.out.println();

        //check size
        assertEquals(ad1.size(), 3);

        ad1.addLast("d");
        ad1.addLast("e");
        ad1.addLast("f");

        //check out get function print out a b c d e f
        for (int i = 0; i < 6; i++) {
            System.out.print(ad1.get(i) + " ");
        }
        System.out.println();

        //check size
        assertEquals(ad1.size(), 6);

        //print out abcdef
        ad1.printDeque();

        //check out remove
        String t = ad1.removeFirst();
        assertEquals(t, "a");
        t = ad1.removeFirst();
        assertEquals(t, "b");
        t = ad1.removeLast();
        assertEquals(t, "f");
        t = ad1.removeLast();
        assertEquals(t, "e");
        ad1.printDeque();
        assertEquals(ad1.size(), 2);

        //check out get function. print out c d
        for (int i = 0; i < 2; i++) {
            System.out.print(ad1.get(i) + " ");
        }
        System.out.println();

        //check out resizing out.
        ArrayDeque<Integer> ad3 = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            ad3.addLast(i);
        }
        assertEquals(ad3.size(), 9);
        assertNull(ad3.get(15));
        assertNull(ad3.get(16)); //error resizing 16.

        //check resizing out by adding a large amount
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            ad2.addLast(i);
        }
        assertEquals(ad2.size(), 100);

        //removing a large amount.
        for (int i = 0; i < 76; i++) {
            ad2.removeFirst();
        }

        //System.out.println(ad2.get(78)); //resizing success error because of resize.
        ad2.printDeque();

    }

}

