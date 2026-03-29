package queueAdvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueAdvancedTest {
    QueueAdvanced q1;
    QueueAdvanced q2;
    QueueAdvanced q3;


    @BeforeEach
    void setUp() {
        q1 = new QueueAdvanced(); //size = 7
        q2 = new QueueAdvanced();
        q3 = new QueueAdvanced(4);


    }

    @Test
    void testFinalize() {
        q1.enqueue(21);
        q1.enqueue(42);
        q1.enqueue(63);

        assertEquals(3, q1.elements());

        q1.finalize();

        assertTrue(q1.elements() == 0 && q1.size() == 0);
    }

    @Test
    void testEnqueue() {
        q1.enqueue(3);
        q1.enqueue(5);
        q1.enqueue(7);
        q1.enqueue(11);

        q2.enqueue(3);
        q2.enqueue(5);
        q2.enqueue(7);
        q2.enqueue(11);
        q2.enqueue(1);
        q2.enqueue(2);


        q3.enqueue(1);
        q3.enqueue(2);

        q1.enqueue(q3);

        assertTrue(q2.equals(q1));
    }

    @Test
    void testEquals() {

        // empty queues with same size
        assertTrue(q1.equals(q2));

        // make a copy of the queue with constructor
        q1.enqueue(3);
        q1.enqueue(5);
        q1.enqueue(7);
        q1.enqueue(11);

        QueueAdvanced q4 = new QueueAdvanced(q1);
        assertTrue(q1.equals(q4));

        q3.enqueue(1);
        q3.enqueue(2);
        q3.enqueue(3);
        q3.enqueue(4);
        q3.dequeue();
        q3.dequeue();
        q3.enqueue(5);
        q3.enqueue(6);

        QueueAdvanced q5 = new QueueAdvanced(q3);
        assertTrue(q3.equals(q5));
        assertTrue(q3.equals(q3.clone()));
    }

    @Test
    void testClone() {
        q1.enqueue(3);
        q1.enqueue(5);
        q1.enqueue(7);
        q1.enqueue(11);

        assertTrue(q1.equals(q1.clone()));
    }



    @Test
    void testToString() {
        // empty queue with size = 4
        assertEquals("[]", q3.toString());

        q3.enqueue(1);
        assertEquals("[1]", q3.toString());
        q3.enqueue(2);
        assertEquals("[1,2]", q3.toString());
        q3.enqueue(3);
        q3.enqueue(4);

        assertEquals("[1,2,3,4]", q3.toString());

        q3.dequeue();
        q3.dequeue();
        // top < bottom
        q3.enqueue(5);
        q3.enqueue(6);
        assertEquals("[3,4,5,6]", q3.toString());
    }

    @Test
    void search() {
        q1.enqueue(2);
        q1.enqueue(4);
        q1.enqueue(8);

        assertTrue(q1.search(8));
        assertFalse(q1.search(10));

        q3.enqueue(1);
        q3.enqueue(2);
        q3.enqueue(3);
        q3.enqueue(4);
        q3.dequeue();
        q3.dequeue();
        q3.enqueue(5);
        q3.enqueue(6);

        assertTrue(q3.search(6));

    }
}