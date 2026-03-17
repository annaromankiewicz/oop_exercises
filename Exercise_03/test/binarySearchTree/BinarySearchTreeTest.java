package binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    BinarySearchTree b1 = new BinarySearchTree();
    BinarySearchTree b2 = new BinarySearchTree();

    @BeforeEach
    void setUp() {
        b1.insert(3);
        b1.insert(8);
        b1.insert(15);
        b1.insert(2);
        b1.insert(20);
        b1.insert(11);
    }

    @Test
    void insert() {

    }

    @Test
    void findEmpty() {
        assertFalse(b2.find(1));
    }


    @Test
    void find() {
        // key is root
        assertTrue(b1.find(3));

        assertTrue(b1.find(11));

        assertFalse(b1.find(100));

    }

    @Test
    void remove() {

    }

    @Test
    void size() {
        assertEquals(6, b1.size());
    }

    @Test
    void getParentEmpty() {
        assertEquals(Integer.MIN_VALUE, b1.getParent(2));
    }

    @Test
    void getParent() {
        assertEquals(3, b1.getParent(2));
        assertEquals(3, b1.getParent(8));

        assertEquals(8, b1.getParent(15));

        assertEquals(15, b1.getParent(20));
        assertEquals(15, b1.getParent(11));

    }

    @Test
    void toArray() {
    }

    @Test
    void toArrayPostOrder() {
    }

    @Test
    void toArrayPreOrder() {
    }

    @Test
    void max() {
    }

    @Test
    void min() {
    }

    @Test
    void testToString() {
    }
}