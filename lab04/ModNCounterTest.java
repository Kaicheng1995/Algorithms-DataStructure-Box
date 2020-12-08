import org.junit.Test;

import static org.junit.Assert.*;

public class ModNCounterTest {


    @Test
    public void testConstructor() {
        ModNCounter c = new ModNCounter(0);
        assertTrue(c.value() == 0);
    }

    @Test
    public void testIncrement() throws Exception {
        ModNCounter c = new ModNCounter(0);
        c.increment();
        assertTrue(c.value()  == 1);
        c.increment();
        assertTrue(c.value() == 2);
    }

    @Test
    public void testReset() throws Exception {
        ModNCounter c = new ModNCounter(0);
        c.increment();
        c.reset();
        assertTrue(c.value() == 0);
    }
}