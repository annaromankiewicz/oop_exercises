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
     * save the minimal and maximal value in the tree
     */
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

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
        current = getBinaryTreeNode(elem);  // returns null if root is null -> already checked above
        if (current.data != elem) {         // current is parent of new BinaryTreeNode with data = elem
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
        if (p == null) return false; // tree is empty
        else return p.data == key;
    }


    // helper

    /**
     * returns the BinaryTreeNode with val = key or if it is not found it returns the last BinaryTreeNode before the location where
     * the BinaryTreeNode with requested key should be found
     */

    private BinaryTreeNode getBinaryTreeNode(int key) {
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

    /**
     * checks if the node with val = elem is on the left or right of parent.
     * Returns true if node is right child of parent and false if child is on the left or
     * if parent is null -> not best solution - better with exception (after we have learnt it)
     */
   private boolean isRight(BinaryTreeNode parent, int elem) {
        if (parent != null) {
            return elem > parent.data;
        }
        return false;
    }


    /**
     * Removes the element with the given key. Returns true if
     * the key could be found (and removed), false otherwise.
     */
    public boolean remove(int key) { // left or right from parent check just one time
        if (root == null) return false;

        BinaryTreeNode current = getBinaryTreeNode(key); // node we want to remove
        BinaryTreeNode parent = getParentNode(key);     // parent of node we want to remove

        if (current == null || current.data != key) return false; // key not in tree

        boolean right = parent != null && isRight(parent, key); // check if the node we want to remove is left or right child of parent

        // case 1: leaf node
        if (current.left == null && current.right == null) {
            if (parent == null) root = null;             // removing root
            else if (right) parent.right = null;
            else parent.left = null;

            // case 2: node has just one child
        } else if (current.left == null || current.right == null) {
            BinaryTreeNode child = (current.left != null) ? current.left : current.right; // child that replaces current
            if (parent == null) root = child;            // removing root
            else if (right) parent.right = child;    // right child replaces current
            else parent.left = child;     // left child replaces current

            // case 3a: left child has no right successor → promote left child
        } else if (current.left.right == null) {
            current.left.right = current.right;           // attach current's right subtree to left child
            if (parent == null) root = current.left;     // removing root
            else if (right) parent.right = current.left; // connect parent (right) with current.left
            else parent.left = current.left;  // connect parent (left) with current.left

            // case 3a: right child has no left successor → promote right child
        } else if (current.right.left == null) {
            current.right.left = current.left;            // attach current's left subtree to right child
            if (parent == null) root = current.right;    // removing root
            else if (right) parent.right = current.right; // connect parent (right) with current.right
            else parent.left = current.right;  // connect parent (left) with current.right

            // case 3b: general case — find inorder successor = symmetrical next (right-left-left-...-null)
        } else {
            BinaryTreeNode parentSymmetricalNext = current;       // parent of the inorder successor
            BinaryTreeNode symmetricalNext = current.right;       // start at right subtree
            while (symmetricalNext.left != null) {                // walk as far left as possible
                parentSymmetricalNext = symmetricalNext;
                symmetricalNext = symmetricalNext.left;
            }

            // step 2: detach symmetricalNext — left pointer of parent references to right child of successor
            parentSymmetricalNext.left = symmetricalNext.right;  // successor was further down left spine

            // step 3: replace current with symmetricalNext
            symmetricalNext.left = current.left;
            symmetricalNext.right = current.right;

            // step 4: restore parent-child relation
            if (parent == null) root = symmetricalNext;          // removing root
            else if (right) parent.right = symmetricalNext;  // connect parent (right) with symmetricalNext
            else parent.left = symmetricalNext;   // connect parent (left) with symmetricalNext
        }

        size--;
        if (size > 0) {
            int[] sorted = toArray(true);
            min = sorted[0];
            max = sorted[size - 1];
        }
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

    private BinaryTreeNode getParentNode(int key) {
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
        int[] a = new int[size];
        toArray(a, ascending, 0, root);
        return a;  // no more manual reversal needed
    }

    //helper

    /**
     * Recursive methode to get always the value of the lower visited node
     */
    private int toArray(int[] ret, boolean ascending, int offset, BinaryTreeNode n) {
        if (n == null) return offset;
        if (ascending) {
            offset = toArray(ret, ascending, offset, n.left);   // left first
            ret[offset++] = n.data;
            offset = toArray(ret, ascending, offset, n.right);
        } else {
            offset = toArray(ret, ascending, offset, n.right);  // right first
            ret[offset++] = n.data;
            offset = toArray(ret, ascending, offset, n.left);
        }
        return offset;
    }


    /**
     * Returns the elements of the tree (postorder traversal).
     */
    public int[] toArrayPostOrder() {
        int[] ret = new int[size];
        postOrder(ret, 0, root);
        return ret;
    }

    // helper

    /**
     * Recursive methode to get always the value of the right visited node
     */
    private int postOrder(int[] ret, int offset, BinaryTreeNode n) {
        if (n == null) return offset;
        offset = postOrder(ret, offset, n.left);
        offset = postOrder(ret, offset, n.right);
        ret[offset++] = n.data;
        return offset;
    }


    /**
     * Returns the elements of the tree (preorder traversal).
     */
    public int[] toArrayPreOrder() {
        int[] ret = new int[size];
        preOrder(ret, 0, root);
        return ret;
    }

    // helper

    /**
     * Recursive methode to get always the value of the left visited node
     */
    private int preOrder(int[] ret, int offset, BinaryTreeNode n) {
        if (n == null) return offset;
        ret[offset++] = n.data;
        offset = preOrder(ret, offset, n.left);
        offset = preOrder(ret, offset, n.right);
        return offset;
    }


    /**
     * Returns largest number stored in the tree. Integer.MIN_VALUE if
     * no largest element can be found
     */
    public int max() { // right right right till next is null
        if (root == null) return Integer.MIN_VALUE;
        return this.max;
    }

    /**
     * Returns smallest number stored in the tree. Integer.MIN_VALUE if
     * no smallest element can be found
     */
    public int min() {
        if(root == null) return Integer.MIN_VALUE;
        return this.min;
    }

    /**
     * Represents the tree in a human readable form.
     */

    public String toString() {
        return toStringHelper(root, 0);
    }

    // here I got help of AI (claude), the idea of the recursion I had on my own, but I used some inspiration also of the example the prof showed us
    private String toStringHelper(BinaryTreeNode node, int depth) {
        if (node == null) return "";
        return toStringHelper(node.right, depth + 1)
                + "   ".repeat(depth) + node.data + "\n"
                + toStringHelper(node.left, depth + 1);
    }


}



