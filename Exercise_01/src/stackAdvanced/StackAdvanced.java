package stackAdvanced;

public class StackAdvanced {
    private int size;
    private int[] stack;
    private int top;

    /**
     * Constructor initializes stack with a standard size.
     */
    public StackAdvanced() {
        this(5);
    }

    /**
     * Constructor initializes stack with the given size.
     */
    public StackAdvanced(int size) {
        this.size = size;
        this.top = 0;
        this.stack = new int[size];
    }

    /**
     * Copy constructor initializes stack with another stack.
     * This constructor must COPY all elements of the other stack.
     * The elements of the other stack must NOT be changed!
     */
    public StackAdvanced(StackAdvanced other) {
        this.size = other.size;
        this.top = other.top;
        this.stack = new int[other.size];
        for (int i = 0; i < other.top; i++) {
            this.stack[i] = other.stack[i];
        }
    }

    /**
     * Deinitializes the object; sets the object fields to initial values (0 or null)
     */
    protected void finalize() {
        this.size = 0;
        this.top = 0;
        this.stack = null;
    }


    /**
     * Pushes all elements from another stack onto this one. If another stack
     * [4,5] is pushed onto this stack [1,2,3], the result is [1,2,3,4,5] and
     * not [1,2,3,5,4]. The elements of the other stack must NOT be changed!
     */
    public void push(StackAdvanced other) {
        if (this.size >= this.top + other.top) {      // works only if the stack has enough space
            for (int i = 0; i < other.top; i++) {
                push(other.stack[i]);
            }
        }
    }

    /**
     * Clones this Stack instance and returns an exact COPY.
     */
    public StackAdvanced clone() {
        StackAdvanced clone = new StackAdvanced(this);
        return clone;
    }

    /**
     * Returns true if the other stack is equal to this one, false otherwise.
     * The contents of the two stacks must not be changed!
     */
    public boolean equals(StackAdvanced other) {
        int minSize = this.size;
        if (other.size < this.size) {
            minSize = other.size;
        }
        for (int i = 0; i < minSize; i++) {
            if (this.stack[i] == other.stack[i])
                return false;
        }
        return (this.top == other.top && this.size == other.size);
    }


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
     * Returns the number of elements in the stack
     */
    public int elements() {
        return top;
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
        for (int i = 0; i < top; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(stack[i]);
        }
        System.out.print("]");
    }

}

