package stack;

public class Stack {
    private int size;
    private int[] stack;
    private int top;

    /**
     * Initializes the stack instance
     */
    public void initStack(int size) {
        this.top = 0;
        this.size = size;
        this.stack = new int[size];
    }


    /**
     * Clears all elements from the stack
     */
    public void clear() {
        if (this.top > 0) {
            for (int i = 0; i < this.top; i++) {
                this.stack[i] = 0;
            }
            this.top = 0;
        }
    }


    /**
     * Pushes an element onto the stack
     */
    public void push(T elem) {
        if (this.size > 0 && this.stack != null) {
            if (this.top < this.size) {
                this.stack[this.top] = val;
                this.top++;
            }
        }
    }

    /**
     * Returns the top element of the stack and removes it
     */
    public int pop() {
        T elem;
        if (this.top > 0) {
            this.top--;
            val = this.stack[this.top];
            this.stack[this.top] = 0;
            return val;
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Returns the top element of the stack without removing it
     */
    public int peek() {
        if (this.top > 0) return this.stack[this.top-1];
        return Integer.MIN_VALUE;
    }


    /**
     * Returns the number of elements in the stack
     */
    public int elements() {
        return this.top;
    }

    /**
     * Returns the maximum size of the stack
     */
    public int size() {
        return this.size;
    }


    /**
     * Prints all elements in the stack
     */
    public void print() {
        System.out.print("[");
        for (int i = 0; i < this.top; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(this.stack[i]);
        }
        System.out.print("]");
    }
}
