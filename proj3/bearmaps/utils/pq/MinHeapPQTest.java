package bearmaps.utils.pq;

import org.apache.commons.math3.analysis.function.Min;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinHeapPQTest {

    @Test
    public void testing() {
        NaiveMinPQ<Integer> nPQ = new NaiveMinPQ<>();
        MinHeapPQ<Integer> PQ = new MinHeapPQ<>();

        nPQ.insert(1, 9);
        nPQ.insert(2, 8);
        PQ.insert(1, 9);
        PQ.insert(2, 8);
        //assertEquals(nPQ.size(), PQ.size());
        //assertEquals(nPQ.peek(), PQ.peek());

        nPQ.poll();
        PQ.poll();
        //assertEquals(nPQ.size(), PQ.size());
        //assertEquals(nPQ.peek(), PQ.peek());

        nPQ.insert(4, 1);
        PQ.insert(4, 1);
        //assertEquals(nPQ.size(), PQ.size());
        //assertEquals(nPQ.peek(), PQ.peek());
        nPQ.changePriority(4, 9);
        PQ.changePriority(4, 9);
       // assertEquals(nPQ.size(), PQ.size());
        //assertEquals(nPQ.peek(), PQ.peek());

        nPQ.insert(5, 3);
        nPQ.insert(6, 3);
        PQ.insert(5, 3);
        PQ.insert(6, 3);
        //assertEquals(nPQ.peek(), PQ.peek());
        nPQ.changePriority(6, 9);
        PQ.changePriority(6, 9);
//        assertEquals(nPQ.peek(), PQ.peek());
//        assertEquals(nPQ.poll(), PQ.poll());
//        assertEquals(nPQ.poll(), PQ.poll());
//        assertEquals(nPQ.poll(), PQ.poll());
//        assertEquals(nPQ.poll(), PQ.poll());
        PQ.poll();
        PQ.poll();
        PQ.poll();
        PQ.poll();

        nPQ.insert(0, 7);
        nPQ.insert(1, 8);
        nPQ.insert(2, 8);
        PQ.insert(0, 7);
        PQ.insert(1, 8);
        PQ.insert(2, 8);
        nPQ.changePriority(0, 10);
        PQ.changePriority(0, 10);
//        assertEquals(nPQ.poll(), PQ.poll());
//        assertEquals(nPQ.poll(), PQ.poll());
//        assertEquals(nPQ.poll(), PQ.poll());



    }

}