package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    Queue q1, q2;

    @BeforeEach
    void setUp() {
        q1 = new Queue();
        q2 = new Queue();

        q1.initQueue(5);
        q2.initQueue(7);
    }

    @Test
    void initQueue() {
        assertEquals(5, q1.size());
        assertEquals(7, q2.size());
    }

    @Test
    void clear() {
        // top > bottom
        q1.enqueue(3);
        q1.enqueue(5);
        q1.enqueue(7);
        q1.enqueue(11);

        assertEquals(4, q1.elements());

        q1.clear();
        assertEquals(0, q1.elements());

        // top < bottom
        q1.enqueue(3);
        q1.enqueue(5);
        q1.enqueue(7);
        q1.dequeue();
        q1.dequeue();
        q1.enqueue(6);
        q1.enqueue(8);
        assertEquals(3, q1.elements());
        q1.clear();
        assertEquals(0, q1.elements());

    }

    @Test
    void enqueue() {
        q1.enqueue(3);
        q1.enqueue(5);
        q1.enqueue(7);
        q1.enqueue(11);

        assertEquals(4, q1.elements());

        q1.enqueue(13);

        // top < bottom
        q1.dequeue();
        q1.dequeue();
        q1.enqueue(17);
        assertEquals(4, q1.elements());

    }

    @Test
    void dequeue() {
        assertEquals(0, q1.elements());
        assertEquals(Integer.MIN_VALUE, q1.dequeue());

        q1.enqueue(2);
        q1.enqueue(5);
        q1.enqueue(7);
        q1.enqueue(11);
        q1.enqueue(13);
        assertEquals(5, q1.elements());

        assertEquals(2, q1.dequeue());
        assertEquals(5, q1.dequeue());


        // dequeue from queue with top < bottom
        q1.enqueue(20);
        q1.enqueue(30);

        assertEquals(7, q1.dequeue());
        assertEquals(11, q1.dequeue());
        assertEquals(13, q1.dequeue());
        assertEquals(20, q1.dequeue());
        assertEquals(30, q1.dequeue());


    }

    @Test
    void peek() {

        assertEquals(Integer.MIN_VALUE, q1.peek());

        q1.enqueue(2);
        assertEquals(2, q1.peek());

        q1.enqueue(5);
        assertEquals(5, q1.peek());

        q1.enqueue(7);
        assertEquals(7, q1.peek());

        q1.enqueue(11);
        assertEquals(11, q1.peek());
    }

    @Test
    void elements() {
        q1.enqueue(3);
        assertEquals(1, q1.elements());
        q1.enqueue(5);
        q1.enqueue(7);
        q1.enqueue(11);
        q1.enqueue(33);

        assertEquals(5, q1.elements());

        // overflow
        q1.enqueue(10);
        assertEquals(5, q1.elements());


        q1.dequeue();
        q1.dequeue();
        q1.dequeue();
        assertEquals(2, q1.elements());
        q1.enqueue(1);
        q1.enqueue(2);
        assertEquals(4, q1.elements());
    }

    @Test
    void size() {
        assertEquals(5, q1.size());
        assertEquals(7, q2.size());

        q1.enqueue(12);
        q1.enqueue(50);
        assertEquals(5, q1.size());

        q2.enqueue(2);
        q2.enqueue(5);
        q2.enqueue(7);
        q2.enqueue(11);

        assertEquals(7, q2.size());

    }

    @Test
    void print() {
        //  Set up a stream to capture console output
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream1));

        q1.print();

        String printedOutput1 = outputStream1.toString().trim();
        assertEquals("[]", printedOutput1);


        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream2));

        q1.enqueue(50);
        q1.enqueue(30);
        q1.enqueue(22);
        q1.enqueue(25);

        q1.print();

        String printedOutput2 = outputStream2.toString().trim();
        assertEquals("[50,30,22,25]", printedOutput2);


        q1.enqueue(70);

        ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream3));

        q1.print();
        String printedOutput3 = outputStream3.toString().trim();
        assertEquals("[50,30,22,25,70]", printedOutput3);


        // print queue with top < bottom

        q1.dequeue();
        q1.dequeue();
        q1.enqueue(42);
        q1.enqueue(100);

        ByteArrayOutputStream outputStream4 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream4));

        q1.print();
        String printedOutput4 = outputStream4.toString().trim();
        assertEquals("[22,25,70,42,100]", printedOutput4);


    }

}