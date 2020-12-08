import org.junit.Test;
import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    /** Adds a few things to the deque, checking isEmpty() and size() are correct,
     * finally printing the results. */
    @Test
    public void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        LinkedListDeque<String> lld1 = new LinkedListDeque<>();

        // Java will try to run the below code.
        // If there is a failure, it will jump to the finally block before erroring.
        // If all is successful, the finally block will also run afterwards.
        try {

            assertTrue(lld1.isEmpty());

            lld1.addFirst("front");
            assertEquals(1, lld1.size());
            assertFalse(lld1.isEmpty());

            lld1.addLast("middle");
            assertEquals(2, lld1.size());

            lld1.addLast("back");
            assertEquals(3, lld1.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }

    }

    /** Adds an item, then removes an item, and ensures that deque is empty afterwards. */
    @Test
    public void addRemoveTest() {
        System.out.println("Running add/remove test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

        try {
            assertTrue(lld1.isEmpty());

            lld1.addFirst(10);
            assertFalse(lld1.isEmpty());

            lld1.removeFirst();
            assertTrue(lld1.isEmpty());
        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }

    /** Removes an item from an empty deque and check if it returns null. */
    @Test
    public void removeEdgeTest() {
        System.out.println("Edge case: Running remove test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        try {
            lld1.addFirst(10);
            lld1.removeLast();
            assertNull(lld1.removeFirst());
        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }

    /** get an item which the index is out of range */
    @Test
    public void getEdgeTest() {
        System.out.println("Edge case: Running get test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        try {
            lld1.addFirst(5);
            lld1.addLast(6);
            assertNull(lld1.get(3));
            assertNull(lld1.get(-1));
        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }


    /** get an item which the index in the range */
    @Test
    public void getTest() {
        System.out.println("Running get test.");

        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        try {
            lld1.addFirst("how");
            lld1.addLast("are");
            lld1.addLast("you");
            assertEquals("you", lld1.get(2));
        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }



    /** get an item which the index is out of range (Recursively) */
    @Test
    public void getRecursiveEdgeTest() {
        System.out.println("Edge case: Running getRecursive test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        try {
            lld1.addFirst(5);
            lld1.addLast(6);
            lld1.addLast(7);
            assertNull(lld1.get(3));
            assertNull(lld1.get(-1));

        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }



    /** get an item which the index in the range (Recursively) */
    @Test
    public void getRecursiveTest() {
        System.out.println("Running getRecursive test.");

        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        try {
            lld1.addFirst("how");
            lld1.addLast("are");
            lld1.addLast("you");
            lld1.addLast("!");
            assertEquals("!", lld1.getRecursive(3));
        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }
}
