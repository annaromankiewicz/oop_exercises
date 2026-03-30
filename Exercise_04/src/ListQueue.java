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

    /** Method for test */
    public boolean equals(ListQueue l) {
        int sizeOther = l.elements();
        if (super.elements() != sizeOther) {
            return false;
        }
        int index= 0;
        boolean isEqual = false;
            while (index < sizeOther) {
                isEqual = super.peekElementAt(index) == l.peekElementAt(index);
                if (!isEqual) return false;
                index++;
            } return isEqual;
    }
}