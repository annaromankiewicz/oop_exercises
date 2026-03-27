import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ListQueueTest {
    ListQueue l1 = new ListQueue();
    ListQueue l2 = new ListQueue();
    ListQueue l3 = new ListQueue();

    @BeforeEach
    void setUp() {
        l1.enqueue(1);
        l1.enqueue(2);
        l1.enqueue(3);
        l1.enqueue(4);

        l2.enqueue(10);
        l2.enqueue(20);
        l2.enqueue(30);
        l2.enqueue(40);
        l2.enqueue(50);
    }


    @Test
    void constructorOther() {
       l3 = new ListQueue(l1);
       assertTrue(l1.equals(l3));

       l3.dequeue();
        assertEquals(4, l1.elements());
        assertEquals(3, l3.elements());
        assertFalse(l1.equals(l3));

    }

    @Test
    void enqueueVal() {
        l3.enqueue(5);
        assertEquals(1, l3.elements());
        l3.enqueue(5);
        assertEquals(2, l3.elements());

    }



    @Test
    void dequeueEmpty() {
        assertEquals(Integer.MIN_VALUE, l3.dequeue());
    }

    @Test
    void dequeue() {
        assertEquals(1, l1.dequeue());
        assertEquals(2, l1.dequeue());
        assertEquals(3, l1.dequeue());
        assertEquals(4, l1.dequeue());
    }

    @Test
    void peek() {
        assertEquals(1, l1.peek());
        assertEquals(1, l1.peek());
        assertEquals(10, l2.peek());
        l2.dequeue();
        assertEquals(20, l2.dequeue());
    }

    @Test
    void peekEmpty() {
        assertEquals(Integer.MIN_VALUE, l3.dequeue());
    }
}