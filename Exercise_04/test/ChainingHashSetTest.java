import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashSetTest {
    ChainingHashSet hashSet1 = new ChainingHashSet(7);
    ChainingHashSet hashSet2 = new ChainingHashSet(9);

    @BeforeEach
    void setUp() {
        hashSet1.insert(5);
        hashSet1.insert(8);
        hashSet1.insert(9);
        hashSet1.insert(11);
        hashSet1.insert(13);
        hashSet1.insert(14);
        hashSet1.insert(27);
        hashSet1.insert(39);
        hashSet1.insert(41);
        hashSet1.insert(52);
        hashSet1.insert(64);
        hashSet1.insert(78);
    }

    @Test
    void insertNewValue() {
        assertFalse(hashSet1.contains(26));
        assertTrue(hashSet1.insert(26));
        assertTrue(hashSet1.contains(26));
    }

    @Test
    void insertExistingValue() {
        assertTrue(hashSet1.contains(5));
        assertFalse(hashSet1.insert(5));
    }

    @Test
    void insertEmpty() {
        assertFalse(hashSet2.contains(1));
        hashSet2.insert(1);
        assertTrue(hashSet2.contains(1));
    }


    @Test
    void containsEmpty() {
        assertFalse(hashSet2.contains(10));
    }

    @Test
    void contains() {
        assertTrue(hashSet1.contains(5));
        assertTrue(hashSet1.contains(8));
        assertTrue(hashSet1.contains(9));
        assertTrue(hashSet1.contains(11));
        assertTrue(hashSet1.contains(13));
        assertTrue(hashSet1.contains(27));
        assertTrue(hashSet1.contains(39));
        assertTrue(hashSet1.contains(41));
        assertTrue(hashSet1.contains(52));
        assertTrue(hashSet1.contains(64));
        assertTrue(hashSet1.contains(78));
    }

    @Test
    void removeEmpty() {
        assertFalse(hashSet2.remove(10));
    }

    @Test
    void remove() {
        assertTrue(hashSet1.contains(5));
        assertTrue(hashSet1.remove(5));
        assertFalse(hashSet1.contains(5));
    }



    @Test
    void getOverflowCountEmpty() {
        assertEquals(0, hashSet2.getOverflowCount(0));
        assertEquals(0, hashSet2.getOverflowCount(1));
        assertEquals(0, hashSet2.getOverflowCount(2));
        assertEquals(0, hashSet2.getOverflowCount(3));
        assertEquals(0, hashSet2.getOverflowCount(4));
        assertEquals(0, hashSet2.getOverflowCount(5));
        assertEquals(0, hashSet2.getOverflowCount(6));
        assertEquals(0, hashSet2.getOverflowCount(7));
        assertEquals(0, hashSet2.getOverflowCount(8));
    }

    @Test
    void getOverflowCount() {
        assertEquals(1, hashSet1.getOverflowCount(0));
        assertEquals(3, hashSet1.getOverflowCount(1));
        assertEquals(1, hashSet1.getOverflowCount(2));
        assertEquals(1, hashSet1.getOverflowCount(3));
        assertEquals(2, hashSet1.getOverflowCount(4));
        assertEquals(1, hashSet1.getOverflowCount(5));
        assertEquals(3, hashSet1.getOverflowCount(6));
    }

    @Test
    void getOverflowCountInvalidHashValue() {
        assertEquals(-1, hashSet2.getOverflowCount(11));
        assertEquals(-1, hashSet2.getOverflowCount(-5));
    }

    @Test
    void elementsEmpty() {
        assertEquals(0, hashSet2.elements());
    }

    @Test
    void elements() {
        assertEquals(12, hashSet1.elements());
    }


}