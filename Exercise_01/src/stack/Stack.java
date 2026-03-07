package stack;

public class Stack {
    private int size;
    private int[] stack;
    private int top;

    /**
     * Initializes the stack instance
     */
    public void initStack(int size) {
        top = 0;
        this.size = size;
        stack = new int[size];
    }


    /**
     * Clears all elements from the stack
     */
    public void clear() {
        if (top > 0) {
            for (int i = 0; i < size; i++) {
                stack[i] = 0;
            }
            top = 0;
        }
    }


    /**
     * Pushes an element onto the stack
     */
    public void push(int val) {
        if (top < size) {
            stack[top] = val;
            top++;
        } else {
            System.out.println("overflow");
        }
    }

    /**
     * Returns the top element of the stack and removes it
     */
    public int pop() {
        if (top > 0) {
            top--;
            return stack[top];
        }
        System.out.println("underflow");
        return -1;
    }

    /**
     * Returns the top element of the stack without removing it
     */
    public int peek() {
        if (top > 0) return stack[top-1];
        System.out.println("stack is empty");
        return -1;
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
