package doubleLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleLinkedListTest {
    DoubleLinkedList dl1;
    DoubleLinkedList dl2;

    @BeforeEach
    void setUp() {
        dl1 = new DoubleLinkedList();
        dl2 = new DoubleLinkedList();

        dl1.append(1);
        dl1.append(2);
        dl1.append(3);
        dl1.append(4);
    }

    @Test
    void clear() {
        dl1.clear();
        assertEquals(Integer.MIN_VALUE, dl1.peekFront());
        assertEquals(Integer.MIN_VALUE, dl1.popFront());
    }

    @Test
    void prepend() {
        dl2.prepend(1);
        assertEquals(1,dl2.peekFront());

        dl2.prepend(3);
        assertEquals(3,dl2.peekFront());
    }

    @Test
    void testAppend() {
        dl2.append(1);
        assertEquals(1,dl2.peekFront());

        dl2.append(3);
        assertEquals(1,dl2.peekFront());

    }

    @Test
    void get() {
        assertEquals(1, dl1.get(0));
        assertEquals(2, dl1.get(1));
        assertEquals(3, dl1.get(2));
        assertEquals(4, dl1.get(3));
        assertEquals(Integer.MIN_VALUE, dl1.get(100));
    }

    @Test
    void popFront() {
        assertEquals(1, dl1.popFront());
        assertEquals(2, dl1.popFront());
        assertEquals(3, dl1.popFront());
        assertEquals(4, dl1.popFront());
        assertEquals(Integer.MIN_VALUE, dl1.popFront());
    }

    @Test
    void peekFront() {
        assertEquals(1, dl1.peekFront());
        assertEquals(1, dl1.peekFront());

    }
}