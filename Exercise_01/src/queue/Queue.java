package queue;

public class Queue {
    private int top; // first empty
    private int bottom;
    private int size;
    private int[] queue;


    /**
     * Initializes the queue instance
     */
    public void initQueue(int size) {
        this.size = size;
        this.bottom = -1;
        this.top = 0;
        this.queue = new int[size];
    }

    /**
     * Clears all elements from the queue
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            queue[i] = 0;
        }
        bottom = -1; // starting value of bottom
        top = 0;
    }

    /**
     * Enqueues an element at the back of the queue
     */
    public void enqueue(int val) {
        if (bottom == -1) { // starting point - empty queue
            queue[0] = val;
            bottom++;
        } else if (top == bottom - 1 || (top == size - 1 && bottom == 0)) {                // prevents overflow
            return;
        } else {
            if (top == size - 1 && bottom != 0) {
                top = 0;
            } else {
                top++;
            }
            queue[top] = val;
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
     * Returns the front element of the queue without removing it
     */
    public int peek() {

        if (bottom == -1) {
            return Integer.MIN_VALUE;
        }
        return queue[top];
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

    /**
     * Prints all elements in the queue
     */
    public void print() {
        System.out.print("[");
        if (bottom != -1) {
            if (bottom < top) {
                for (int i = bottom; i <= top; i++) {
                    System.out.print(queue[i]);
                    if (i >= bottom && i < top) {
                        System.out.print(",");
                    }
                }
            } else {
                for (int i = bottom; i < size; i++) {
                    System.out.print(queue[i]);
                    System.out.print(",");
                }
                for (int i = 0; i <= top; i++) {
                    System.out.print(queue[i]);
                    if (i != top) {
                        System.out.print(",");
                    }
                }
            }
        }
        System.out.print("]");
    }
}
