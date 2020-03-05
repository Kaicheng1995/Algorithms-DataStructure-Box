import org.junit.Test;

import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void TestisSameNumber() {
        assertTrue(Flik.isSameNumber(128,128));
        assertFalse(Flik.isSameNumber(128,500));
    }

}