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

    /** Method for tests. Without this method list1.equals(list2) would use the Object.equals() method
     * that just compares the references. For override it should have this signature: equals(Object o)
     * but here it works like this */

    public boolean equals(ListStack l) {
        if (l == null) return false;
        int sizeOther = l.elements();
        if (this.elements() != sizeOther) {
            return false;
        }
        int index= 0;
        boolean isEqual = true;
        while (index < sizeOther) {
            isEqual = super.peekElementAt(index) == l.peekElementAt(index);
            if (!isEqual) return false;
            index++;
        } return isEqual;
    }

}