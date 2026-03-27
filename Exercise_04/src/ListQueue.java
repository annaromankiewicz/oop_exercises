import mc.opr.dll.MyDoubleLinkedList;

public class ListQueue extends MyDoubleLinkedList {
    /**
     * Initializes an empty queue.
     */
    public ListQueue() {
        super();
    }

    /**
     * Copy constructor which initializes the queue with another queue.
     * This constructor must COPY all elements of the other queue.
     */
    public ListQueue(ListQueue other) {
        super();
        if (other != null) {
            int index = 0;
            int size = other.elements();
            while (index < size) {
                super.pushBack(other.peekElementAt(index));
                index++;
            }
        }
    }

    /**
     * Enqueues an element at the back of the queue.
     */
    public void enqueue(int val) {
        super.pushBack(val);
    }

    /**
     * Dequeues the element at the front of the queue.
     */
    public int dequeue() {
       return super.popFront();
    }

    /**
     * Returns the front element of the queue without removing it.
     */
    public int peek() {
        return super.peekFront();
    }

    /** Method for tests */
    public boolean equals(ListQueue l) {
        if (this.elements() != l.elements()) {
            return false;
        }
        int index= 0;
        boolean allElementsEqual = true;
            while (index < l.elements()) {
                allElementsEqual = this.peekElementAt(index) == l.peekElementAt(index);
                index++;
            } return allElementsEqual;
    }
}