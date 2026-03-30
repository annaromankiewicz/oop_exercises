import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListStackTest {

    ListStack l1 = new ListStack();
    ListStack l2 = new ListStack();

    @BeforeEach
    void setUp() {
        l1.push(1);
        l1.push(2);
        l1.push(3);
        l1.push(4);
    }

    @Test
    void equal() {
        l2 = new ListStack(l1);
        assertTrue(l1.equals(l2));

        l1.push(6);
        l2.push(7);

        assertFalse(l1.equals(l2));

    }

    @Test
    void constructorOther() {
        l2 = new ListStack(l1);
        assertTrue(l1.equals(l2));

        l2.clear();
        assertFalse(l1.equals(l2));
    }

    @Test
    void constructorOtherNull() {
        l2 = new ListStack(new ListStack());
        assertEquals(0, l2.elements());
        assertEquals(Integer.MIN_VALUE, l2.peek());
        assertEquals(Integer.MIN_VALUE, l2.pop());
    }

    @Test
    void pushOnEmptyListStack() {
        assertEquals(0,l2.elements());
        l2.push(3);
        assertEquals(1,l2.elements());
        l2.push(7);
        assertEquals(2,l2.elements());

    }

    @Test
    void push() {
        assertEquals(4,l1.elements());
        l1.push(3);
        assertEquals(5,l1.elements());
        assertEquals(3, l1.peek());
        l1.push(7);
        assertEquals(6,l1.elements());
        assertEquals(7, l1.peek());

    }

    @Test
    void popEmpty() {
        assertEquals(Integer.MIN_VALUE, l2.pop());
    }



    @Test
    void pop() {
        assertEquals(4, l1.pop());
        assertEquals(3, l1.pop());
        assertEquals(2, l1.pop());
        assertEquals(1, l1.pop());

        // empty

        assertEquals(Integer.MIN_VALUE, l1.pop());
    }

    @Test
    void peekEmpty() {
        assertEquals(Integer.MIN_VALUE, l2.peek());
    }

    @Test
    void peek() {
        assertEquals(4, l1.peek());
        l1.pop();
        assertEquals(3, l1.peek());
    }

}