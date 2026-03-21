package binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    BinarySearchTree b1 = new BinarySearchTree();
    BinarySearchTree b2 = new BinarySearchTree();
    BinarySearchTree b3 = new BinarySearchTree();
    BinarySearchTree b4 = new BinarySearchTree();

    @BeforeEach
    void setUp() {
        b1.insert(15);
        b1.insert(7);
        b1.insert(30);
        b1.insert(5);
        b1.insert(11);
        b1.insert(18);
        b1.insert(40);
        b1.insert(1);
        b1.insert(6);
        b1.insert(10);
        b1.insert(14);
        b1.insert(17);
        b1.insert(20);
        b1.insert(39);
        b1.insert(100);

        b2.insert(10);
        b2.insert(5);
        b2.insert(15);
        b2.insert(3);
        b2.insert(7);
        b2.insert(14);
        b2.insert(9);

        b3.insert(1);
        b3.insert(2);
        b3.insert(3);
        b3.insert(4);
        b3.insert(5);

    }

    @Test
    void insert() {
        b2.insert(10);
        assertTrue(b2.find(10));
    }

    @Test
    void findEmpty() {

        assertFalse(b4.find(1));
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
        assertEquals(Integer.MIN_VALUE, b2.getParent(50));
    }

    @Test
    void getParentRoot() {
        assertEquals(Integer.MIN_VALUE, b1.getParent(15));
    }


    @Test
    void getParent() {
        assertEquals(15, b1.getParent(7));
        assertEquals(15, b1.getParent(30));
        assertEquals(7, b1.getParent(5));
        assertEquals(7, b1.getParent(11));
        assertEquals(30, b1.getParent(18));
        assertEquals(30, b1.getParent(40));
        assertEquals(5, b1.getParent(1));
        assertEquals(5, b1.getParent(6));
        assertEquals(11, b1.getParent(10));
        assertEquals(11, b1.getParent(14));
        assertEquals(18, b1.getParent(17));
        assertEquals(18, b1.getParent(20));
        assertEquals(40, b1.getParent(39));
        assertEquals(40, b1.getParent(100));
    }

    // helper
    boolean equals(int[] a, int[] b) {
        if (a.length != b.length) return false;
        boolean isEqual = true;
        for (int i = 0; i<a.length; i++){
            isEqual = (a[i]==b[i]);
        } return isEqual;
    }

    @Test
    void toArray() {
        int[] a = {1, 5, 6, 7, 10, 11, 14, 15, 17, 18, 20, 30, 39, 40, 100};

//        b1.toArray(true);
//        assertTrue(equals(a, b1.toArray(true)));
//
        int[] b = {3, 5, 7, 9, 10, 14, 15};

//        b2.toArray(true);
//        b2.toArray(true);


        int[] array = b2.toArray(true);
        assertTrue(equals(b, array));
//
        int[] c = {1, 2, 3, 4, 5};

//        assertTrue(equals(c, b3.toArray(true)));


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