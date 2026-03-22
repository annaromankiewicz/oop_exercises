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

    /* counts the elements in array **/
    private int top = 0;
    private int min;
    private int max;

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
        BinaryTreeNode current;
        BinaryTreeNode n = new BinaryTreeNode(elem);
        if (root == null) {
            root = n;
            size++;
            min = root.data;
            max = root.data;
            return true;
        }
        current = getBinaryTreeNode(elem);
        if (current.data != elem) { // current is parent of new BinaryTreeNode with data = elem
            if (isRight(current, elem)) {
                current.right = n;
                if (n.data > max) max = n.data;
            } else {
                current.left = n;
                if (n.data < min) min = n.data;
            }
            size++;
            return true;
        }
        return false;
    }


    /**
     * Searches for the (first) element with the given key. Returns true
     * if it could be found, false otherwise.
     */
    public boolean find(int key) {
        BinaryTreeNode p = getBinaryTreeNode(key);
        if (p == null) return false;
        else if (getBinaryTreeNode(key).data == key) return true;
        return false;
    }


    // helper

    /**
     * returns the BinaryTreeNode with val = key or if it is not found it returns the last BinaryTreeNode before the location where
     * the BinaryTreeNode with requested key should be inserted
     */

    public BinaryTreeNode getBinaryTreeNode(int key) {
        BinaryTreeNode prev = null;
        BinaryTreeNode current = root;
        if (root == null) return null;
        else if (root.data == key) {
            return root;        // key found
        } else while (current != null && current.data != key) {
            if (key < current.data) {
                prev = current;
                current = current.left;
            } else {
                prev = current;
                current = current.right;
            }
        }
        if (current == null) {
            return prev; // key not found
        }
        return current; // key found
    }

    // helper

    /** checks if the node with val = elem is on the left or right of parent.
     * Returns true if node is right child of parent and false if child is on the left or
     * if parent is null */
     boolean isRight(BinaryTreeNode parent, int elem) {
         if (parent != null) {
             return elem > parent.data;
         } return false;
    }


    /**
     * Removes the element with the given key. Returns true if
     * the key could be found (and removed), false otherwise.
     */
    public boolean remove(int key) { // left or right from parent check just one time
        BinaryTreeNode current = root;
        BinaryTreeNode parent = null;
        if (root == null) {
            return false;
        }
            current = getBinaryTreeNode(key); // node we want to remove if current.data = key
            parent = getParentNode(key);

        boolean right = parent != null && isRight(parent, key); // check if the node we want to remove is left or right child of parent

        if (current.data != key) return false;
        // case 1: node w/ children
            if (current.right == null && current.left == null) {
                if (parent == null) {
                    root = null;                  // removing root
                } else if (right) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
                size--;
                return true;

                // case 2: node has just one child
            } else if (current.left == null || current.right == null) {
                // The child that replaces current
                BinaryTreeNode child = (current.left != null) ? current.left : current.right;

                // CHANGE 6: special case for removing root with one child
                if (parent == null) {
                    root = child;
                } else if (right) {
                    parent.right = child;
                } else {
                    parent.left = child;
                }
                size--;
                return true; // CHANGE 7: you were missing return true here
            }

        // case 3a
            if (current.left.right == null) {
                if (right) {                                  // connect prev (left or right) with current.left
                    current.left.right = current.right;
                    parent.right = current.left;
                } else {
                    current.left.right = current.right;                 // left child of current is new right child of old right child
                    parent.left = current.left;                           // left child is new node (instead of current)
                }
                size--;
                return true;
            } else if (current.right.left == null) {
                if (right) {                                  // connect prev (left or right) with current.left
                    current.right.left = current.left;                 // left child of current is new right child of old right child
                    parent.right = current.right;
                } else {
                    current.right.left = current.left;                 // left child of current is new right child of old right child
                    parent.left = current.right;                           // left child is new node (instead of current)
                }
                size--;
                return true;
            }
            // CASE 3b: general case — find inorder successor
            BinaryTreeNode parentSymmetricalNext = current;
            BinaryTreeNode symmetricalNext = current.right;
            BinaryTreeNode p = current.right;

            while (p.left != null) {
                parentSymmetricalNext = symmetricalNext;
                symmetricalNext = p.left;
                p = p.left;
            }

        // detach symmetricalNext from its current position
            if (parentSymmetricalNext == current) {     // symmetrical next is direct right child of current
                parentSymmetricalNext.right = symmetricalNext.right;
            } else {
                parentSymmetricalNext.left = symmetricalNext.right;
            }

        // replace current with symmetricalNext
            symmetricalNext.left = current.left;
            symmetricalNext.right = current.right;

        // restore parent-child relation
            if (parent == null) {
                root = symmetricalNext;
            } else if (right) {
                parent.right = symmetricalNext;
            } else {
                parent.left = symmetricalNext;
            }
            size--;
            return true;
    }


    /**
     * Returns the number of elements stored in the tree.
     */
    public int size() {
        return size;
    }

    //helper

    /**
     * Returns the parent node of element with the given key
     */

    public BinaryTreeNode getParentNode(int key) {
        BinaryTreeNode n = new BinaryTreeNode(key);
        BinaryTreeNode prev = null;
        BinaryTreeNode current = root;
        if (root == null) return null;
        else if (root.data == key) {
            return null; // check what to return if elem is in root
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
            return null;
        }
        return prev;
    }


    /**
     * Returns the parent element of the given key. Integer.MIN_VALUE if
     * no parent can be found.
     */
    public int getParent(int key) {
        BinaryTreeNode parent = getParentNode(key);
        if (parent == null) return Integer.MIN_VALUE;
        return parent.data;
    }


    /**
     * Returns the elements of the tree in ascending (inorder traversal)
     * or descending (reverse inorder traversal) order.
     */
    public int[] toArray(boolean ascending) {
        top = 0;
        int[] a = new int[size];
        a = inorder(a, root);
        if (ascending) {
            return a;
        } else {
            int[] b = new int[size];
            for (int i = 0, j = size - 1; i < size && j >= 0; i++, j--) {
                b[i] = a[j];
            }
            return b;
        }
    }

    //helper

    /**
     * Recursive methode to get always the value of the lower visited node
     */
    int[] inorder(int[] a, BinaryTreeNode root) {
        if (root == null) return a;
        if (root.left != null) {
            if (isExternalNode(root.left)) {
                a[top++] = root.left.data;
            } else {
                inorder(a, root.left);
            }
        }
        if (top < size) {
            a[top++] = root.data;
        }
        if (root.right != null) {
            if (isExternalNode(root.right)) {
                a[top++] = root.right.data;
            } else {
                inorder(a, root.right);
            }
        }
        return a;
    }


    // helper
    /**
     * Returns true if Node is an external node, only use it when p is not null
     */
    boolean isExternalNode(BinaryTreeNode p) {
            return (p.left == null && p.right == null);
    }


    /**
     * Returns the elements of the tree (postorder traversal).
     */
    public int[] toArrayPostOrder() {
        top = 0;
        int[] a = new int[size];
        return postOrder(a, root);
    }

    // helper

    /**
     * Recursive methode to get always the value of the right visited node
     */
    int[] postOrder(int[] a, BinaryTreeNode root) {
        if (root == null) return a;
        if (isExternalNode(root)) {
            a[top] = root.data;
            return a;
        }
        if (root.left != null) {
            if (isExternalNode(root.left)) {
                a[top++] = root.left.data;
            } else {
                postOrder(a, root.left);
            }
        }
        if (root.right != null) {
            if (isExternalNode(root.right)) {
                a[top++] = root.right.data;
            } else {
                postOrder(a, root.right);
            }
        }
        if (top < size) {
            a[top++] = root.data;
        }
        return a;
    }


    /**
     * Returns the elements of the tree (preorder traversal).
     */
    public int[] toArrayPreOrder() {
        top = 0;
        int[] a = new int[size];
        return preOrder(a, root);
    }

    // helper

    /**
     * Recursive methode to get always the value of the left visited node
     */
    int[] preOrder(int[] a, BinaryTreeNode root) {
        if (root == null) return a;
        a[top++] = root.data;
        if (isExternalNode(root)) {
            return a;
        } else {
            if (root.left != null) {
                if (isExternalNode(root.left)) {
                    a[top++] = root.left.data;
                } else {
                    preOrder(a, root.left);
                }
            }
            if (root.right != null) {
                if (isExternalNode(root.right)) {
                    a[top++] = root.right.data;
                } else {
                    preOrder(a, root.right);
                }
            }
        }
        return a;
    }


        /** Returns largest number stored in the tree. Integer.MIN_VALUE if
         no largest element can be found*/
        public int max () { // right right right till next is null
            return this.max;
        }

        /** Returns smallest number stored in the tree. Integer.MIN_VALUE if
         no smallest element can be found */
        public int min () {
            return this.min;
        }

        /** Represents the tree in a human readable form. */

        public String toString() {
            return toStringRec(root, 0);
        }

    String toStringRec(BinaryTreeNode node, int depth) {
        if (node == null) return "";
        return toStringRec(node.right, depth + 1)
                + "    ".repeat(depth) + node.data + "\n"
                + toStringRec(node.left, depth + 1);
    }



}



