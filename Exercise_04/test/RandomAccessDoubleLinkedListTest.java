import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomAccessDoubleLinkedListTest {

    RandomAccessDoubleLinkedList l1 = new RandomAccessDoubleLinkedList();
    RandomAccessDoubleLinkedList l2 = new RandomAccessDoubleLinkedList();

    @BeforeEach
    void setUp() {
        l1.add(7);
        l1.add(13);
        l1.add(17);
        l1.add(19);
    }

    @Test
    void ConstructorOther() {
        l2 = new RandomAccessDoubleLinkedList(l1);
        assertEquals(l1, l2);        // uses l1.equals(l2) internally — passes if equals() is overridden
        l2.clear();
        assertNotEquals(l1, l2);     // after clearing l2, equals() should return false
    }


    @Test
    void insertAtLast() {
        l1.insertAt(4, 23);
        assertEquals(23, l1.elementAt(4));
        assertEquals(5, l1.size());
    }

    @Test
    void insertAtFront() {
        l1.insertAt(0, 3);
        assertEquals(3, l1.elementAt(0));
        assertEquals(5, l1.size());
    }

    @Test
    void insertAt() {
        l1.insertAt(3, 9);
        assertEquals(9, l1.elementAt(3));
        assertEquals(5, l1.size());
    }

    @Test
    void insertAtEmpty() {
        l2.insertAt(3, 9);
        assertEquals(Integer.MIN_VALUE, l2.elementAt(3));
        assertEquals(9, l2.elementAt(0));
        assertEquals(1, l2.size());
    }


    @Test
    void containsEmpty() {
        assertFalse(l2.contains(10));
    }

    @Test
    void contains() {
        assertFalse(l1.contains(5));
    }


    @Test
    void removeAt() {
        // top 7, 13, 17, 19 top
        assertEquals(7, l1.elementAt(0));
        l1.removeAt(0);
        assertEquals(13, l1.elementAt(0));
    }

    @Test
    void removeAtEmpty() {
        assertEquals(Integer.MIN_VALUE, l1.elementAt(10));
        l2.removeAt(10);
        assertEquals(Integer.MIN_VALUE, l1.elementAt(10));
    }


    @Test
    void removeAll() {
        l2.add(10);
        l2.add(20);
        l2.add(10);
        l2.add(30);
        l2.add(40);
        l2.add(50);
        l2.add(10);
        l2.add(10);
        l2.add(10);
        l2.add(40);

        assertEquals(10, l2.size());
        l2.removeAll(10);
        assertEquals(5, l2.size());
        l2.removeAll(40);
        assertEquals(3, l2.size());

        l2.removeAll(1);
        assertEquals(3, l2.size());
    }

    @Test
    void elementAt() {
        assertEquals(7, l1.elementAt(0));
        assertEquals(13, l1.elementAt(1));
        assertEquals(17, l1.elementAt(2));
        assertEquals(19, l1.elementAt(3));
    }
}