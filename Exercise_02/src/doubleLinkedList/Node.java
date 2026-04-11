package doubleLinkedList;
public class Node {
    /** Ref to the next elem in the list, or null if it is the last */
    public Node next;
    /** Ref to the prev elem in the list, or null if it is the first */
    public Node prev;
    /** Holds the actual data */
    public T elem;

    public void flipNextPrev() {
        Node oldPrev = this.prev;
        this.prev = next;
        next = oldPrev;
    }
}
