import mc.opr.dll.MyDoubleLinkedList;

public class ListStack extends MyDoubleLinkedList {

    /** Initializes an empty stack. */
    public ListStack() {
        super();
    }

    /** Copy constructor which initializes the stack with another stack.
     This constructor must COPY all elements of the other stack. */
    public ListStack(ListStack other) {
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


    /** Pushes an element onto the stack. */
    public void push(int val) {
        super.pushBack(val);
        }


    /** Returns the top element of the stack and removes it. */
    public int pop() {
        return super.popBack();
    }

    /** Returns the top element of the stack without removing it. */
    public int peek() {
        return super.peekBack();
    }

    /** Method for tests */
    public boolean equals(ListStack l) {
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