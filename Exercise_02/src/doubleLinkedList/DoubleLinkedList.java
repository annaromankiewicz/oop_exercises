package doubleLinkedList;

public class DoubleLinkedList {

    /**
     * Pointer to the first and last element of the list
     */
    private Node head, tail;

    /**
     * Saves the number of elements in the list
     */
    private int count;


    /**
     * Constructor initializes an empty list.
     */
    public DoubleLinkedList() {
        head = null;
        tail = null;
        this.count = 0;
    }

    /**
     * Clears all elements from the linked list
     */
    public void clear() {
        head = null;
        tail = null;
        this.count = 0;
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
        this.count++;
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
        } else {
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
        this.count++;
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
        }
        return Integer.MIN_VALUE;

    }

    /**
     * Removes and returns the front element of the linked list. Returns
     * Integer.MIN_VALUE if empty
     */
    public int popFront() {
        if (head == null) return Integer.MIN_VALUE;
        Node p = head;
        if (head == tail) { // last element in list
            head = null;
            tail = null;
        } else {
            head = p.next;
            head.prev = null;
        }
        this.count--;
        return p.val;
    }

    /**
     * Returns the front element of the list without removing it.
     * Returns Integer.MIN_VALUE if empty
     */
    public int peekFront() {
        if (head == null) return Integer.MIN_VALUE;
        return head.val;
    }

    /**
     * Removes and returns the element from the back of the linked list.
     * Returns Integer.MIN_VALUE if empty
     */
    public int popBack() {
        if (tail == null) return Integer.MIN_VALUE;
        Node p = tail; // old tail
        if (tail != head) { // list has 2 or more elements
            tail = tail.prev;
            tail.next = null;
        } else {        // pop last element in list
            head = null;
            tail = null;
        }
        this.count--;
        return p.val;
    }

    /**
     * Returns the element at the back of the list without removing it.
     * Returns Integer.MIN_VALUE if empty
     */
    public int peekBack() {
        if (head == null) return Integer.MIN_VALUE;
        return tail.val;
    }

    /**
     * Returns the number of elements in the double linked list
     */
    public int size() {
        return this.count;
    }

    /**
     * Reverses the order of all elements in the list. “He who is first,
     * shall be last!”
     */
    public void reverse() {
        if (head != null) {
            Node newTail = head;
            Node newHead = tail;
            Node current = head;
            Node next = head.next;
            while (current != null) {
                current.flipNextPrev();    //  helperReverse() method of Node to reverse the references next and prev
                current = next;
                if (current != null) {
                    next = current.next;
                }
            }
            head = newHead;
            tail = newTail;
        }
    }


    // Aufgabe 2


    /**
     * Copy constructor initializes list with another list.
     * This constructor must COPY all elements of the other list.
     * The elements of the other list must NOT be changed!
     */
    public DoubleLinkedList(DoubleLinkedList other) {
        Node p = other.head;
        while (p != null) {
            append(p.val);
            p = p.next;
        }
    }

    /**
     * Deinitializes the object;
     */
    protected void finalize() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Adds all elements from another list at the front of this linked list.
     */
    public void prepend(DoubleLinkedList other) {
        Node p = other.tail;
        while (p!= null) {
            prepend(p.val);
            p = p.prev;
        }
    }

    /**
     * Adds all elements from another list at the back of this linked list.
     */
    public void append(DoubleLinkedList other) {
        Node p = other.head;
        while (p!= null) {
            append(p.val);
            p = p.next;
        }
    }


    /**
     * Clones this DoubleLinkedList instance and returns an exact COPY.
     */
    public DoubleLinkedList clone() {
        return new DoubleLinkedList(this);
    }


    /**
     * Returns true if the other list is equal to this one, false otherwise.
     * The contents of the two lists must not be changed!
     */
    public boolean equals(DoubleLinkedList other) {
        if (other == null) return false;
        if (this.count != other.count) return false;
        if (this.head == null && other.head == null) return true;
        boolean allElementsEqual = false;
        Node current = this.head;
        Node currentOther = other.head;
        while (current != null) {
            allElementsEqual = current.val == currentOther.val;
            if (!allElementsEqual) return false;
            current = current.next;
            currentOther = currentOther.next;
        }
        return allElementsEqual;
    }


    /**
     * Returns a string representation of the list. Example:
     * List of size 5: 1 -> 2 -> 3 -> 4 -> 5
     */
    public String toString() {
        Node p = head;
        StringBuilder s = new StringBuilder();
        while (p != null) {
            s.append(p.val);
            if (p.next != null) {
                s.append("<->");
            }
            p = p.next;
        }
        return s.toString();
    }

    /**
     * Returns true if the element val exists in the list, false otherwise.
     */
    public boolean search(int val) {
        Node p = head;
        while (p != null) {
            if (p.val == val) return true;
            p = p.next;
        }
        return false;
    }


}





































