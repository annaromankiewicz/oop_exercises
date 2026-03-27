import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListStackTest {

    ListStack l1 = new ListStack();
    ListStack l2 = new ListStack();

    @BeforeEach
    void setUp() {
        l1.pushBack(1);
        l1.pushBack(2);
        l1.pushBack(3);
        l1.pushBack(4);
    }

    @Test
    void constructorOther() {
        l2 = new ListStack(l1);
        assertTrue(l1.equals(l2));

        l2.clear();
        assertFalse(l1.equals(l2));
    }

    @Test
    void push() {
        assertEquals(0,l2.elements());
        l2.push(3);
        assertEquals(1,l2.elements());
        l2.push(7);
        assertEquals(2,l2.elements());

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