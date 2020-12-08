import org.junit.Test;
import static org.junit.Assert.*;

public class IntArrayListStepperTest {

    @Test
    public void stepByZeroTest() {
        IntArrayList lst = new IntArrayList();
        lst.addlast(1);
        lst.addlast(2);
        lst.addlast(3);
        lst.addlast(4);

        IntArrayListStepper stepper = new IntArrayListStepper(lst, 0);
        assertEquals(stepper.get(0), 1);
        assertEquals(stepper.get(1), 1);
        assertEquals(stepper.get(2), 1);
        assertEquals(stepper.get(3), 1);
        assertEquals(stepper.get(50), 1);
    }

    @Test
    public void stepByOneTest() {
        IntArrayList lst = new IntArrayList();
        lst.addlast(1);
        lst.addlast(2);
        lst.addlast(3);
        lst.addlast(4);

        IntArrayListStepper stepper = new IntArrayListStepper(lst, 1);
        assertEquals(stepper.get(0), 1);
        assertEquals(stepper.get(1), 2);
        assertEquals(stepper.get(2), 3);
        assertEquals(stepper.get(3), 4);
    }

    @Test
    public void stepByOneNegativeTest() {
        IntArrayList lst = new IntArrayList();
        lst.addlast(1);
        lst.addlast(2);
        lst.addlast(3);
        lst.addlast(4);

        IntArrayListStepper stepper = new IntArrayListStepper(lst, 1);
        assertEquals(stepper.get(-4), 1);
        assertEquals(stepper.get(-3), 2);
        assertEquals(stepper.get(-2), 3);
        assertEquals(stepper.get(-1), 4);
    }

    @Test
    public void stepByThreeTest() {
        IntArrayList lst = new IntArrayList();
        lst.addlast(1);
        lst.addlast(2);
        lst.addlast(3);
        lst.addlast(4);
        lst.addlast(5);
        lst.addlast(6);
        lst.addlast(7);
        lst.addlast(8);
        lst.addlast(9);

        IntArrayListStepper stepper = new IntArrayListStepper(lst, 3);
        assertEquals(stepper.get(0), 1);
        assertEquals(stepper.get(1), 4);
        assertEquals(stepper.get(2), 7);
    }

    @Test
    public void stepByThreeNegativeTest() {
        IntArrayList lst = new IntArrayList();
        lst.addlast(1);
        lst.addlast(2);
        lst.addlast(3);
        lst.addlast(4);
        lst.addlast(5);
        lst.addlast(6);
        lst.addlast(7);
        lst.addlast(8);
        lst.addlast(9);

        IntArrayListStepper stepper = new IntArrayListStepper(lst, 3);
        assertEquals(stepper.get(-3), 3);
        assertEquals(stepper.get(-2), 6);
        assertEquals(stepper.get(-1), 9);
    }
}