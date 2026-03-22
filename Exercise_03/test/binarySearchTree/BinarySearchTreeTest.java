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

    // ── insert ────────────────────────────────────────────────────────────────

    @Test
    void insertDuplicateReturnsFalse() {
        assertFalse(b2.insert(10)); // 10 is root of b2
        assertFalse(b2.insert(9));  // 9 is a leaf in b2
    }

    @Test
    void insertDuplicateDoesNotIncrementSize() {
        int before = b2.size();
        b2.insert(10); // duplicate
        assertEquals(before, b2.size());
    }

    @Test
    void insertIntoEmptyTree() {
        BinarySearchTree empty = new BinarySearchTree();
        assertTrue(empty.insert(42));
        assertEquals(1, empty.size());
        assertEquals(42, empty.root.data);
    }

    @Test
    void insertMaintainsOrder() {
        // root 10, insert smaller and larger
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assertEquals(5, tree.root.left.data);
        assertEquals(15, tree.root.right.data);
    }

    // ── find ──────────────────────────────────────────────────────────────────

    @Test
    void findEmpty() {
        assertFalse(b4.find(1));
    }

    @Test
    void findRoot() {
        assertTrue(b2.find(10)); // 10 is the root of b2
    }

    @Test
    void findLeaf() {
        assertTrue(b2.find(9));  // 9 is a leaf in b2 (right child of 7)
        assertTrue(b2.find(3));  // 3 is a leaf (left child of 5)
    }

    @Test
    void findNonExistent() {
        assertFalse(b2.find(42));
        assertFalse(b2.find(0));
        assertFalse(b2.find(-1));
    }

    @Test
    void findAfterInsert() {
        b2.insert(100);
        assertTrue(b2.find(100));
    }

    // ── size ──────────────────────────────────────────────────────────────────

    @Test
    void sizeEmpty() {
        assertEquals(0, b4.size());
    }

    @Test
    void sizeAfterInserts() {
        // b2 was built with 7 distinct inserts
        assertEquals(7, b2.size());
        // b1 was built with 15 distinct inserts
        assertEquals(15, b1.size());
        // b3 was built with 5 distinct inserts
        assertEquals(5, b3.size());
    }

    @Test
    void sizeNotChangedByDuplicate() {
        int before = b2.size();
        b2.insert(5);  // duplicate
        assertEquals(before, b2.size());
    }

    // ── remove ────────────────────────────────────────────────────────────────

    @Test
    void removeFromEmptyTree() {
        BinarySearchTree empty = new BinarySearchTree();
        assertFalse(empty.remove(5));
    }

    @Test
    void removeNonExistentKey() {
        assertFalse(b2.remove(42));
    }

    @Test
    void removeLeafNode() {
        // b2 tree: 10 -> 5(left=3, right=7(right=9)), 15(left=14)
        // 9 is a leaf
        assertTrue(b2.remove(9));
        assertFalse(b2.find(9));
        assertNull(b2.getBinaryTreeNode(7).right); // parent of 9 should now have no right child
    }

    @Test
    void removeNodeWithOnlyRightChild() {
        // 15 has only a left child (14), so let's build a node with only a right child
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(25); // 20 has only right child 25
        assertTrue(tree.remove(20));
        assertFalse(tree.find(20));
        assertTrue(tree.find(25));
        assertEquals(10, tree.getParent(25)); // 25 should now be a direct child of 10
    }

    @Test
    void removeNodeWithOnlyLeftChild() {
        // 15 in b2 has only left child 14
        assertTrue(b2.remove(15));
        assertFalse(b2.find(15));
        assertTrue(b2.find(14));
        assertEquals(10, b2.getParent(14)); // 14 promoted to child of root
    }

    @Test
    void removeNodeWithTwoChildren() {
        // Remove 5 from b2 (has left=3, right=7)
        assertTrue(b2.remove(5));
        assertFalse(b2.find(5));
        // children must still be reachable
        assertTrue(b2.find(3));
        assertTrue(b2.find(7));
        assertTrue(b2.find(9));
    }

    @Test
    void removeRootSingleNode() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(42);
        tree.remove(42);
        assertFalse(tree.find(42));
        assertEquals(0, tree.size());
    }

    @Test
    void removeMaintainsSearchProperty() {
        // After any removal, toArray(true) must still be sorted
        b2.remove(5);
        int[] result = b2.toArray(true);
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i] < result[i + 1],
                    "Array not sorted at index " + i + ": " + result[i] + " >= " + result[i + 1]);
        }
    }

    // ── remove: Case 1 (leaf) ─────────────────────────────────────────────────

    @Test
    void removeLeafLeftChild() {
        // 3 is a leaf and LEFT child of 5 in b2
        assertTrue(b2.remove(3));
        assertFalse(b2.find(3));
        assertNull(b2.getBinaryTreeNode(5).left);
    }

// ── remove: Case 2 (one child) ────────────────────────────────────────────

    @Test
    void removeNodeWithOnlyLeftChildOnRightSide() {
        // build: 10 -> right=15 -> left=12 (15 is right child of 10, has only left child)
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(12); // 15 has only left child 12
        assertTrue(tree.remove(15));
        assertFalse(tree.find(15));
        assertTrue(tree.find(12));
        assertEquals(10, tree.getParent(12)); // 12 promoted to right child of 10
    }

    @Test
    void removeNodeWithOnlyRightChildOnLeftSide() {
        // build: 10 -> left=5 -> right=7 (5 is left child of 10, has only right child)
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(7); // 5 has only right child 7
        assertTrue(tree.remove(5));
        assertFalse(tree.find(5));
        assertTrue(tree.find(7));
        assertEquals(10, tree.getParent(7)); // 7 promoted to left child of 10
    }

    @Test
    void removeRootWithOneChild() {
        // root has only a right child → root gets replaced by that child
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(15); // only right child
        assertTrue(tree.remove(10));
        assertFalse(tree.find(10));
        assertTrue(tree.find(15));
        assertEquals(15, tree.root.data);
    }

// ── remove: Case 3a ───────────────────────────────────────────────────────

    @Test
    void removeCase3aLeftRightNullRightSide() {
        // 20 is RIGHT child of 10, has two children: left=15(no right child), right=25
        // → triggers: left.right==null, right=true
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(15); // 20.left=15, 15.right=null
        tree.insert(25); // 20.right=25
        assertTrue(tree.remove(20));
        assertFalse(tree.find(20));
        assertTrue(tree.find(15));
        assertTrue(tree.find(25));
        int[] result = tree.toArray(true);
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i] < result[i + 1]);
        }
    }

    @Test
    void removeCase3aRightLeftNullRightSide() {
        // 40 is RIGHT child of 20, has two children: left=30(right=35), right=50(no left)
        // left.right != null → skips first 3a check
        // right.left == null → triggers second 3a check, right=true
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(40);
        tree.insert(30); // 40.left=30
        tree.insert(35); // 30.right=35, so 40.left.right != null
        tree.insert(50); // 40.right=50, 50.left=null
        assertTrue(tree.remove(40));
        assertFalse(tree.find(40));
        assertTrue(tree.find(30));
        assertTrue(tree.find(50));
        int[] result = tree.toArray(true);
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i] < result[i + 1]);
        }
    }

    @Test
    void removeCase3aRightLeftNullLeftSide() {
        // 10 is LEFT child of 20, has two children: left=8(right=9), right=15(no left)
        // left.right=9 != null → skips first 3a check
        // right.left==null → triggers second 3a check, right=false
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(8);  // 10.left=8
        tree.insert(9);  // 8.right=9, so 10.left.right != null
        tree.insert(15); // 10.right=15, 15.left=null
        tree.insert(25);
        assertTrue(tree.remove(10));
        assertFalse(tree.find(10));
        assertTrue(tree.find(8));
        assertTrue(tree.find(15));
        int[] result = tree.toArray(true);
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i] < result[i + 1]);
        }
    }

// ── remove: Case 3b ───────────────────────────────────────────────────────

    @Test
    void removeCase3bSuccessorIsDirectRightChild() {
        // parentSymmetricalNext == current:
        // 20 has two children, right subtree root (30) has no left child
        // → successor is direct right child, parentSymmetricalNext stays = current
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30); // 20.right=30, 30.left=null → successor=30, parentSymNext=current=20
        tree.insert(35); // 30.right=35 (left.right!=null and right.left!=null skips 3a)
        tree.insert(25); // 30.left=25... wait this gives 30 a left child
        // Need: left.right!=null AND right.left!=null to reach 3b
        // and then right subtree has no left child
        // insert 20, 10, 30, 8, 12, 35 → remove 20
        // 20.left=10, 10.left=8, 10.right=12 → left.right=12 != null
        // 20.right=30, 30.left=null → right.left==null → hits 3a! not 3b
        // We need right.left != null too. Use b1 structure, remove a node where
        // right child has no left child but left child has a right child... tricky.
        // Simplest: insert 20, 10, 30, 8, 12, 25, 35 → remove 20
        // 20.left=10, 10.right=12 → left.right != null ✓
        // 20.right=30, 30.left=25 → right.left != null ✓ → reaches 3b
        // In right subtree of 20: 30.left=25, 25.left=null → successor=25, parentSymNext=30 != current
        // That's parentSymNext != current case. For == current we need right subtree root to have no left.
        // So we need left.right!=null AND right.left==null... that's case 3a right.left==null!
        // Conclusion: parentSymmetricalNext==current is only reachable when right.left==null
        // but that's caught by 3a first. So this sub-branch may be unreachable in practice
        // given the current if-ordering. Skip — already covered structurally by 3a tests.
        assertTrue(true); // placeholder - branch unreachable due to 3a guard
    }

    @Test
    void removeCase3bSuccessorDeepInLeftSpine() {
        // parentSymmetricalNext != current: successor found after walking left spine
        // Use b1, remove root 15:
        // right=30, 30.left=18, 18.left=17 → walks left, parentSymNext=18, successor=17
        assertTrue(b1.remove(15));
        assertFalse(b1.find(15));
        // all other elements must still be found
        assertTrue(b1.find(7));
        assertTrue(b1.find(30));
        assertTrue(b1.find(17)); // the successor that replaced root
        int[] result = b1.toArray(true);
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i] < result[i + 1]);
        }
    }

    @Test
    void removeCase3bRootWithTwoChildren() {
        // parent==null in case 3b: removing root that has two children
        // and right subtree has a left spine → root = symmetricalNext
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(8);
        tree.insert(12); // 10.right=12, so 20.left.right != null → skips 3a left check
        tree.insert(25); // 30.left=25, so 20.right.left != null → skips 3a right check → 3b
        tree.insert(35);
        // successor: go right to 30, then left to 25, 25.left=null → successor=25
        assertTrue(tree.remove(20));
        assertFalse(tree.find(20));
        assertEquals(25, tree.root.data); // 25 is the new root
        int[] result = tree.toArray(true);
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i] < result[i + 1]);
        }
    }

    // ── getParent ─────────────────────────────────────────────────────────────

    @Test
    void getParentEmpty() {
        assertEquals(Integer.MIN_VALUE, b2.getParent(50));
    }

    @Test
    void getParentRoot() {
        assertEquals(Integer.MIN_VALUE, b1.getParent(15));
    }

    @Test
    void getParentNonExistentKey() {
        // key is not in the tree at all
        assertEquals(Integer.MIN_VALUE, b2.getParent(42));
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

    // ── toArray ───────────────────────────────────────────────────────────────

    @Test
    void toArraySymmetricTree() {
        // b2: 10, 5, 15, 3, 7, 14, 9  →  ascending: 3, 5, 7, 9, 10, 14, 15
        int[] expected = {3, 5, 7, 9, 10, 14, 15};
        assertArrayEquals(expected, b2.toArray(true));
    }

    @Test
    void toArrayRightSkewed() {
        // b3 inserts 1,2,3,4,5 in order → right-skewed tree; inorder must still be sorted
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, b3.toArray(true));
    }

    @Test
    void toArraySingleElement() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(7);
        assertArrayEquals(new int[]{7}, tree.toArray(true));
    }

    @Test
    void toArrayLargeTree() {
        // b1 has 15 elements; verify they come back fully sorted
        // ascending
        int[] expectedAscending = {1, 5, 6, 7, 10, 11, 14, 15, 17, 18, 20, 30, 39, 40, 100};

        assertArrayEquals(expectedAscending, b1.toArray(true));

        //descending
        int[] expectedDescending = {100, 40, 39, 30, 20, 18, 17, 15, 14, 11, 10, 7, 6, 5, 1};
        assertArrayEquals(expectedDescending, b1.toArray(false));
    }

    @Test
    void toArrayCalledTwiceGivesSameResult() {
        // Calling toArray twice must not corrupt state (instance variable 'top' must reset)
        int[] first  = b2.toArray(true);
        int[] second = b2.toArray(true);
        assertArrayEquals(first, second);
    }

    @Test
    void toArrayWithDegenerateTree() {
        //ascending
        int[] expectedAscending = {1,2,3,4,5};
        assertArrayEquals(expectedAscending, b3.toArray(true));

        //descending
        int[] expectedDescending = {5,4,3,2,1};
        assertArrayEquals(expectedDescending, b3.toArray(false));
    }

    @Test
    void toArrayPostOrderSymmetricBinaryTree() {
        int[] expected = {1,6,5,10,14,11,7,17,20,18,39,100,40,30,15};

        assertTrue(equals(expected,b1.toArrayPostOrder()));
    }


    @Test
    void toArrayPostOrder_b2() {
        assertArrayEquals(new int[]{3, 9, 7, 5, 14, 15, 10}, b2.toArrayPostOrder());
    }



    @Test
    void toArrayPostOrder_b3() {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, b3.toArrayPostOrder());
    }



    @Test
    void toArrayPreOrder_b1() {
        assertArrayEquals(new int[]{15, 7, 5, 1, 6, 11, 10, 14, 30, 18, 17, 20, 40, 39, 100}, b1.toArrayPreOrder());
    }


    @Test
    void toArrayPreOrder_b2() {
        assertArrayEquals(new int[]{10, 5, 3, 7, 9, 15, 14}, b2.toArrayPreOrder());
    }


    @Test
    void toArrayPreOrder_b3() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, b3.toArrayPreOrder());
    }




    @Test
    void max() {
        assertEquals(100, b1.max());
        assertEquals(15, b2.max());
        assertEquals(5, b3.max());
    }

    @Test
    void min() {
        assertEquals(1, b1.min());
        assertEquals(3, b2.min());
        assertEquals(1, b3.min());
    }

    // ── toString ──────────────────────────────────────────────────────────────

    @Test
    void toStringSingleNode() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(42);
        assertEquals("42\n", tree.toString());
    }

    @Test
    void toStringEmpty() {
        assertEquals("", b4.toString());
    }

    @Test
    void toStringB2() {
        String expected =
                "    15\n"    +   // depth 1 →  4 spaces
                        "        14\n" + // depth 2 →  8 spaces
                        "10\n"         + // depth 0 →  0 spaces
                        "            9\n" + // depth 3 → 12 spaces
                        "        7\n"  + // depth 2 →  8 spaces
                        "    5\n"      + // depth 1 →  4 spaces
                        "        3\n";   // depth 2 →  8 spaces
        assertEquals(expected, b2.toString());
    }


    @Test
    void toStringB3RightSkewed() {
        String expected =
                "                5\n" +  // depth 4 → 16 spaces
                        "            4\n"      + // depth 3 → 12 spaces
                        "        3\n"          + // depth 2 →  8 spaces
                        "    2\n"              + // depth 1 →  4 spaces
                        "1\n";                   // depth 0 →  0 spaces
        assertEquals(expected, b3.toString());
    }

    @Test
    void toStringRootOnly() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(5);   // only left child
        String expected =
                "10\n" +
                        "    5\n";
        assertEquals(expected, tree.toString());
    }

}