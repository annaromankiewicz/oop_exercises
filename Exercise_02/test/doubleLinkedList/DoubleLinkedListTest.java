package doubleLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void testCopyConstructorIsDeepCopy() {
        DoubleLinkedList copy = new DoubleLinkedList(dl1); // dl1 = 1<->2<->3<->4

        // Mutate the original
        dl1.popFront();
        dl1.append(99);

        // Copy must be completely unaffected
        assertEquals("1<->2<->3<->4", copy.toString());
        assertEquals(4, copy.size());
    }


    @Test
    void clear() {
        dl1.clear();
        assertEquals(Integer.MIN_VALUE, dl1.peekFront());
        assertEquals(Integer.MIN_VALUE, dl1.popFront());
    }

    @Test
    void testPrepend() {
        dl2.prepend(1);
        assertEquals(1, dl2.peekFront());

        dl2.prepend(3);
        assertEquals(3, dl2.peekFront());
    }

    @Test
    void testAppend() {
        dl2.append(1);
        assertEquals(1, dl2.peekFront());

        dl2.append(3);
        assertEquals(1, dl2.peekFront());

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


    @Test
    void testPopBackEmpty() {
        // popBack from empty list
        assertEquals(Integer.MIN_VALUE, dl2.popBack());
    }


    @Test
    void testPopBackFull() {
        assertEquals(4, dl1.popBack());
        assertEquals(3, dl1.popBack());
        assertEquals(2, dl1.popBack());
        assertEquals(1, dl1.popBack());
        assertEquals(Integer.MIN_VALUE, dl1.popBack());
    }

    @Test
    void testPeekBackEmpty() {

        // popBack from empty list
        assertEquals(Integer.MIN_VALUE, dl2.peekBack());

    }

    @Test
    void testPeekBackFull() {

        assertEquals(4, dl1.peekBack());

    }

    @Test
    void testSizeEmpty() {
        assertEquals(0, dl2.size());
    }

    @Test
    void testSizeFull() {
        assertEquals(4, dl1.size());
    }

    @Test
    void testSizeAfterOperations() {
        dl1.popFront();
        assertEquals(3, dl1.size());
        dl1.append(99);
        assertEquals(4, dl1.size());
    }

    @Test
    void testReverseEmpty() {
        dl2.reverse();
        assertEquals(Integer.MIN_VALUE, dl2.peekFront());
    }

    @Test
    void testReverseOneElement() {
        dl2.prepend(1);
        dl2.reverse();

        assertEquals(1, dl2.popFront());
        assertEquals(Integer.MIN_VALUE, dl2.popFront());
    }

    @Test
    void testReverseTwoElements() {
        dl2.append(10);
        dl2.append(20);
        dl2.reverse();

        assertEquals(20, dl2.popFront());
        assertEquals(10, dl2.popFront());
        assertEquals(Integer.MIN_VALUE, dl2.popFront());
    }


    @Test
    void testReverse() {
        assertEquals("1<->2<->3<->4", dl1.toString());
        dl1.reverse();
        assertEquals("4<->3<->2<->1", dl1.toString());

        assertEquals(4, dl1.popFront());
        assertEquals(3, dl1.popFront());
        assertEquals(2, dl1.popFront());
        assertEquals(1, dl1.popFront());
        assertEquals(Integer.MIN_VALUE, dl1.popFront());
    }


    @Test
    void testFinalize() {
        dl1.finalize();
        assertEquals(0, dl1.size());

    }

    @Test
    void testPrependOtherEmptyList() {
        dl1.prepend(dl2);

        assertEquals("1<->2<->3<->4", dl1.toString());
    }

    @Test
    void testPrependOtherListIntoEmpty() {
        dl2.append(10);
        dl2.append(20);
        DoubleLinkedList empty = new DoubleLinkedList();
        empty.prepend(dl2);
        assertEquals("10<->20", empty.toString());
    }

    @Test
    void testPrependOtherList() {
        dl2.append(10);
        dl2.append(20);
        dl1.prepend(dl2);

        assertEquals("10<->20<->1<->2<->3<->4", dl1.toString());
    }

    @Test
    void testAppendOtherEmptyList() {
        dl1.append(dl2);

        assertEquals("1<->2<->3<->4", dl1.toString());
    }

    @Test
    void testAppendOtherList() {

        dl2.append(10);
        dl2.append(20);
        dl1.append(dl2);

        assertEquals("1<->2<->3<->4<->10<->20", dl1.toString());

    }

    @Test
    void testCloneIsDeepCopy() {
        dl2 = dl1.clone();
        dl1.popFront();
        assertEquals("1<->2<->3<->4", dl2.toString());
    }

    @Test
    void testCloneOtherList() {

        dl2 = dl1.clone();
        assertEquals("1<->2<->3<->4", dl1.toString());

    }

    @Test
    void testEqualsEmpty() {
        dl1.clear();

        assertTrue(dl1.equals(dl2));

    }


    @Test
    void testEquals() {
        dl2 = dl1.clone();


        assertTrue(dl1.equals(dl2));

        dl2.reverse();
        assertFalse(dl1.equals(dl2));
    }

    @Test
    void testToStringEmpty() {
        assertEquals("", dl2.toString());
    }


    @Test
    void testToStringFull() {
        assertEquals("1<->2<->3<->4", dl1.toString());
    }

    @Test
    void testSearchEmpty() {
        assertFalse(dl2.search(1));

    }


    @Test
    void testSearchFull() {
        assertTrue(dl1.search(1));
        assertTrue(dl1.search(2));
        assertTrue(dl1.search(3));
        assertTrue(dl1.search(4));
        assertFalse(dl1.search(10));
    }

}