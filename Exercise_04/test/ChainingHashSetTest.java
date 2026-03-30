import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashSetTest {
    ChainingHashSet hashSet1 = new ChainingHashSet(7);
    ChainingHashSet hashSet2 = new ChainingHashSet(9);
    ChainingHashSet hashSet3 = new ChainingHashSet(-1); // Invalid instantiation

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
    void constructorInvalidIndexSize() {
        hashSet2 = new ChainingHashSet(-1);
        assertEquals(Integer.MIN_VALUE, hashSet2.elements());
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
        assertTrue(hashSet2.insert(1));
        assertTrue(hashSet2.contains(1));
    }

    @Test
    void insertEmptyInvalidInstantiation() {
        assertFalse(hashSet3.contains(10));
        assertFalse(hashSet3.insert(10));
        assertFalse(hashSet3.contains(10));
    }

    @Test
    void insertNegativeValue() {
        assertFalse(hashSet1.contains(-6));
        assertTrue(hashSet1.insert(-6)); // -6 % 7 = -6, (-6 + 7) % 7 = 1  -> bucket 1
        assertTrue(hashSet1.contains(-6));
        assertFalse(hashSet1.insert(-6)); // duplicate must be rejected
    }

    @Test
    void containsEmpty() {
        assertFalse(hashSet2.contains(10));
    }

    @Test
    void containsInvalidInstantiation() {
        assertFalse(hashSet3.contains(10));
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
    void containsNegativeValue() {
        assertFalse(hashSet1.contains(-99));
        hashSet1.insert(-99);
        assertTrue(hashSet1.contains(-99));
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
    void removeNegativeValue() {
        hashSet1.insert(-5);
        assertTrue(hashSet1.contains(-5));
        assertTrue(hashSet1.remove(-5));
        assertFalse(hashSet1.contains(-5));
    }

    @Test
    void removeCollision() {
        assertTrue(hashSet1.contains(8));
        assertTrue(hashSet1.contains(64));
        assertTrue(hashSet1.contains(78));
        assertEquals(3, hashSet1.getOverflowCount(1));
        assertTrue(hashSet1.remove(64));
        assertTrue(hashSet1.contains(8));
        assertFalse(hashSet1.contains(64));
        assertTrue(hashSet1.contains(78));
        assertEquals(2, hashSet1.getOverflowCount(1));
    }


    @Test
    void removeInvalidInstantiation() {
        assertFalse(hashSet3.insert(5));
        assertFalse(hashSet3.contains(5));
        assertFalse(hashSet3.remove(5));
        assertFalse(hashSet3.contains(5));
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
        assertEquals(Integer.MIN_VALUE, hashSet2.getOverflowCount(11));
        assertEquals(Integer.MIN_VALUE, hashSet2.getOverflowCount(-5));
    }

    @Test
    void getOverflowCountInvalidInstantiation() {
        assertEquals(Integer.MIN_VALUE, hashSet3.getOverflowCount(11));
        assertEquals(Integer.MIN_VALUE, hashSet3.getOverflowCount(-5));
    }

    @Test
    void elementsEmpty() {
        assertEquals(0, hashSet2.elements());
    }

    @Test
    void elementsAfterInsertValid() {
        assertEquals(12, hashSet1.elements());
        assertTrue(hashSet1.insert(77));
        assertEquals(13, hashSet1.elements());
        assertTrue(hashSet1.insert(91));
        assertEquals(14, hashSet1.elements());
    }

    @Test
    void elementsAfterRemove() {
        assertEquals(12, hashSet1.elements());
        assertTrue(hashSet1.remove(52));
        assertEquals(11, hashSet1.elements());
        assertTrue(hashSet1.remove(64));
        assertEquals(10, hashSet1.elements());
    }

    @Test
    void elementsInvalidInitiation() {
        assertEquals(Integer.MIN_VALUE, hashSet3.elements());
    }

    @Test
    void elementsAfterNegativeInsert() {
        int before = hashSet1.elements();
        hashSet1.insert(-3);
        assertEquals(before + 1, hashSet1.elements());
    }

}