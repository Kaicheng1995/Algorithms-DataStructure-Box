package bearmaps;

import edu.princeton.cs.algs4.TrieSET;
import org.junit.Test;

import static org.junit.Assert.*;

public class AugmentedStreetMapGraphTest {

    @Test
    public void testing() {
        TrieSET ts = new TrieSET();
        ts.add("hello");
        ts.add("he");
        ts.add("helloaad");
        ts.add("heach");
        ts.add("a");
        for (String s : ts.keysWithPrefix("he")) {
            System.out.println(s);
        }
    }

}