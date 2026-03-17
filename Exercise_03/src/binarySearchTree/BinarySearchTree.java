package binarySearchTree;

public class BinarySearchTree {

    /**
     * Inner class for the binary tree node.
     **/
    protected class BinaryTreeNode {
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public int data;

        public BinaryTreeNode(int elem) {
            data = elem;
            left = null;
            right = null;
        }
    }

    /**
     * Root node of the tree.
     **/
    protected BinaryTreeNode root;

    /**
     * Number of elements stored in the tree.
     */
    protected int size;

    /**
     * Inserts the given element. Duplicate elements are not allowed.
     * Returns true if insertion was successful, false otherwise.
     */
    public boolean insert(int elem) {
        BinaryTreeNode prev = null;
        BinaryTreeNode current = root;
        boolean right = false;
        BinaryTreeNode n = new BinaryTreeNode(elem);
        if (root == null) {
            root = n;
            size++;
            return true;
        } else {
            if (root.data == elem) return false;
            else {
                while (current != null) {
                    if (elem < current.data) {
                        prev = current;
                        current = prev.left;
                        right = false;
                    } else {
                        prev = current;
                        current = prev.right;
                        right = true;
                    }
                }
                if (right) {
                    prev.right = n;
                } else {
                    prev.left = n;
                }
                size++;
                return true;
            }

        }
    }

        /** Searches for the (first) element with the given key. Returns true
         if it could be found, false otherwise. */
        public boolean find (int key) {
            BinaryTreeNode n = new BinaryTreeNode(key);
            BinaryTreeNode prev = null;
            BinaryTreeNode current = root;
            if (root == null) return false;
            else if (root.data == key) {
                return true;
            } else while (current != null && current.data != key) {
                if (key < current.data) {
                    prev = current;
                    current = prev.left;
                } else {
                    prev = current;
                    current = prev.right;
                }
            }
            if (current == null) {
                return false;
            }
            return true;
        }


        /** Removes the element with the given key. Returns true if
         the key could be found (and removed), false otherwise. */
        public boolean remove (int key) {
            BinaryTreeNode current = root;
            BinaryTreeNode prev = null;
            if (root == null) {
                return false;
            } else {
                while (current != null && current.data != key) {
                    if (key < current.data) {
                        prev = current;
                        current = prev.left;
                    } else {
                        prev = current;
                        current = prev.right;
                    }
                }
                if (current != null) {
                    if (current.data == key && current.right == null && current.left == null) { // case 1: node w/ childnodes
                        prev = null;
                    } else if ((current.left == null && current.right != null) || (current.left != null && current.right == null)) {  // case 2: node has just one child
                        // check if the node we want to remove is left or right child of parent = prev
                        if (key < prev.data) { // child we want to remove is on the left
                            if (current.left != null)
                                prev.left = current.left; // left child is not null
                            else prev.right = current.right; // right child is not null
                        } else { // right child of parent
                            if (current.left != null)
                                prev.right = current.left; // left child is not null
                            else prev.right = current.right; // right child is not null
                        }
                        // implementation of case 3...

                    }

                }
            }
            return true;
        }


        /** Returns the number of elements stored in the tree. */
        public int size () {
            return size;
        }


        /** Returns the parent element of the given key. Integer.MIN_VALUE if
         no parent can be found. */
        public int getParent(int key){
            BinaryTreeNode n = new BinaryTreeNode(key);
            BinaryTreeNode prev = null;
            BinaryTreeNode current = root;
            if (root == null) return Integer.MIN_VALUE;
            else if (root.data == key) {
                return Integer.MIN_VALUE;
            } else while (current.data != key && current != null) {
                if (key < current.data) {
                    prev = current;
                    current = prev.left;
                } else {
                    prev = current;
                    current = prev.right;
                }
            }
            if (current == null) {
                return Integer.MIN_VALUE;
            }
            return prev.data;
        }

//
//        /** Returns the elements of the tree in ascending (inorder traversal)
//         or descending (reverse inorder traversal) order. */
//        public int[] toArray (boolean ascending){
//        }
//        /** Returns the elements of the tree (postorder traversal). */
//        public int[] toArrayPostOrder () {
//        }
//        /** Returns the elements of the tree (preorder traversal). */
//        public int[] toArrayPreOrder () {
//        }
//        /** Returns largest number stored in the tree. Integer.MIN_VALUE if
//         no largest element can be found*/
//        public int max () {
//        }
//        /** Returns smallest number stored in the tree. Integer.MIN_VALUE if
//         no smallest element can be found */
//        public int min () {
//        }
//        /** Represents the tree in a human readable form. */
//        public String toString () {
//        }
//    }

}
