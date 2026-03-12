package doubleLinkedList;

public class DoubleLinkedList {

    /**
     * Pointer to the first and last element of the list
     */
    private Node head, tail;


    /**
     * Constructor initializes an empty list.
     */
    public DoubleLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Clears all elements from the linked list
     */
    public void clear() {
        head = null;
        tail = null;
    }


    /**
     * Adds an element at the front of the linked list.
     */
    public void prepend(int val) {
        Node n = new Node();
        n.val = val;
        if (head == null) {
            head = n;
            tail = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
    }


    /**
     * Adds an element at the back of the linked list.
     */
    public void append(int val) {
        Node n = new Node();
        n.val = val;
        if (head == null) {
            head = n;
            tail = n;
        }
        n.prev = tail;
        tail.next = n;
        tail = n;
    }

    /**
     * Returns the element at position ‘index’. Returns
     * Integer.MIN_VALUE if ‘index’ is invalid.
     */
    public int get(int index) {
        Node p = head;
        int count = 0;
        while (p != null) {
            if (count == index) {
                return p.val;
            }
            p = p.next;
            count++;
        }  return Integer.MIN_VALUE;

    }

    /**
     * Removes and returns the front element of the linked list. Returns
     * Integer.MIN_VALUE if empty
     */
    public int popFront() {
            if (head == null) return Integer.MIN_VALUE;
            if (head == tail) {// last element in list
            Node p = head;
            head = null;
            tail = null;
            return p.val;
            } else {
                Node p = head;
                head = p.next;
                head.prev = null;
                return p.val;
            }
    }

    /**
     * Returns the front element of the list without removing it.
     * Returns Integer.MIN_VALUE if empty
     */
    public int peekFront() {
            if (head == null) return Integer.MIN_VALUE;
            return head.val;
    }
}

