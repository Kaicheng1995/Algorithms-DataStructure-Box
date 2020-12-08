import org.junit.Test;

import static org.junit.Assert.*;

public class MeasurementTest {

    @Test
    public void Constructor1() {
        Measurement t = new Measurement();
        assertTrue(t.getFeet() == 0);
        assertTrue(t.getInches() == 0);
    }

    @Test
    public void Constructor2() {
        Measurement t = new Measurement(1);
        assertTrue(t.getFeet() == 1);
        assertTrue(t.getInches() == 0);
    }

    @Test
    public void Constructor3() {
        Measurement t = new Measurement(1,2);
        assertTrue(t.getFeet() == 1);
        assertTrue(t.getInches() == 2);
    }


    @Test
    public void plus() {
        Measurement t1 = new Measurement();
        Measurement t2 = new Measurement(1,2);
        assertTrue(t1.plus(t2).getFeet() == 1);
        assertTrue(t1.plus(t2).getInches() == 2);
    }

    @Test
    public void minus() {
        Measurement t1 = new Measurement(3,4);
        Measurement t2 = new Measurement(0,10);
        assertTrue(t1.minus(t2).getFeet() == 2);
        assertTrue(t1.minus(t2).getInches() == 6);
    }

    @Test
    public void multiple() {
        Measurement t = new Measurement(0,7);
        assertTrue(t.multiple(3).getFeet() == 1);
        assertTrue(t.multiple(3).getInches() == 9);
    }

    @Test
    public void testToString() {
        Measurement t = new Measurement(3,1);
        assertEquals("3'1\"", t.toString());
    }
}