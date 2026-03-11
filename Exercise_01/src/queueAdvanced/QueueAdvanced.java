package queueAdvanced;

public class QueueAdvanced {
    private int top; // first empty
    private int bottom;
    private int size;
    private int[] queue;


    /**
     * Constructor initializes queue with a standard size.
     */
    public QueueAdvanced() {
        this(7);
    }

    /**
     * Constructor initializes queue with the given size.
     */
    public QueueAdvanced(int size) {
        this.top = 0;
        this.bottom = -1;
        this.size = size;
        this.queue = new int[size];
    }

    /**
     * Copy constructor initializes queue with another queue.
     * This constructor must COPY all elements of the other queue.
     * The elements of the other queue must NOT be changed!
     */
    public QueueAdvanced(QueueAdvanced other) {
        this.top = other.top;
        this.bottom = other.bottom;
        this.size = other.size;
        this.queue = new int[size];

        if (top > bottom) {
            for (int i = bottom; i <= top; i++) {
                queue[i] = other.queue[i];
            }
        } else {    // top <= bottom
            for (int i = bottom; i < size; i++) {
                queue[i] = other.queue[i];
            }
            for (int i = 0; i <= top; i++) {
                queue[i] = other.queue[i];
            }
        }
    }

    /**
     * Deinitializes the object; sets the object fields to initial values (0 or null)
     */
    protected void finalize() {
        this.top = 0;
        this.bottom = -1;
        this.size = 0;
        this.queue = null;
    }

    /**
     * Enqueues all elements from another queue onto this one. If another queue
     * [4,5] is enqueued into this queue [1,2,3], the result is [1,2,3,4,5] and
     * not [1,2,3,5,4]. The elements of the other queue must NOT be changed!
     */

    public void enqueue(QueueAdvanced other) {
        if (this.size - top - 1 > other.top + 1) {
            for (int i = 0; i <= other.top; i++) {
                enqueue(other.queue[i]);
            }
        }
    }

    /**
     * Clones this Queue instance and returns an exact COPY.
     */
    public QueueAdvanced clone() {
        QueueAdvanced clone = new QueueAdvanced(size);
        clone.top = this.top;
        clone.bottom = this.bottom;

        if (bottom != -1) {
            if (top > bottom) {
                for (int i = 0; i <= top; i++) {
                    clone.queue[i] = queue[i];
                }
            } else {
                for (int i = bottom; i < size; i++) {
                    clone.queue[i] = queue[i];
                }
                for (int i = 0; i <= top; i++) {
                    clone.queue[i] = queue[i];
                }
            }
        }
        return clone;
    }


    /**
     * Returns true if the other queue is equal to this one, false otherwise.
     * The contents of the two queues must not be changed!
     */
    public boolean equals(QueueAdvanced other) {
        boolean isAllEqual = false;
        if (this.size == other.size && this.bottom == other.bottom && this.top == other.top) {
            for (int i = 0; i < size; i++) {
                if (this.queue[i] == other.queue[i]) isAllEqual = true;
            }
        }
        return isAllEqual;
    }

    /**
     * Returns a string representation of the queue.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        if (bottom != -1 && bottom < top) {
            for (int i = bottom; i <= top; i++) {
                s.append(queue[i]);
                if (i >= bottom && i < top) {
                    s.append(",");
                }
            }
        } else if (bottom != -1 && top < bottom) {
            for (int i = bottom; i < size; i++) {
                s.append(queue[i]);
                s.append(",");
            }
            for (int i = 0; i <= top; i++) {
                s.append(queue[i]);
                if (i != top) {
                    s.append(",");
                }
            }
        }
        s.append("]");
        return s.toString();
    }


    /**
     * Returns true if the element val exists in the stack, false otherwise.
     */
    public boolean search(int val) {
        if (bottom == -1) return false; // empty queue

        if (bottom <= top) {
            for (int i = bottom; i <= top; i++) {
                if (queue[i] == val) return true;
            }
        } else {
            for (int i = bottom; i < size; i++) {
                if (queue[i] == val) return true;
            }
            for (int i = 0; i <= top; i++) {
                if (queue[i] == val) return true;
            }
        }
        return false;
    }


    /**
     * Enqueues an element at the back of the queue
     */
    public void enqueue(int val) {
        if (!(top == bottom - 1 || (top == size - 1 && bottom == 0))) {
            if (bottom == -1) { // starting point - empty queue
                queue[0] = val;
                bottom++;
            } else {
                if (top == size - 1 && bottom != 0) {
                    top = 0;
                } else {
                    top++;
                }
                queue[top] = val;
            }
        }
    }

    /**
     * Dequeues the element at the front of the queue
     */
    public int dequeue() {
        int val;
        if (bottom == -1) { // empty queue
            return Integer.MIN_VALUE;
        }
        val = queue[bottom];
        queue[bottom] = 0;

        if (bottom == top) {
            bottom = -1;            // queue is empty -> reset
            top = 0;
        } else if (bottom == size - 1) { // queue is like a ring
            bottom = 0;
        } else {
            bottom++;
        }
        return val;
    }


    /**
     * Returns the number of elements in the queue
     */
    public int elements() {
        if (bottom == -1)
            return 0;
        if (top > bottom) {
            return top - bottom + 1;
        } else if (bottom != top) {
            return size - (bottom - top - 1); // bottom - top -1 gives us the amount of free places in the queue
        } else return 1;
    }

    /**
     * Returns the maximum size of the queue
     */
    public int size() {
        return size;
    }


}
