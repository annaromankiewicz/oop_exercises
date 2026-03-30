import java.util.ArrayList;

public class RandomAccessDoubleLinkedList extends ArrayList<Integer> {

    /**
     * Initializes an empty list.
     */
    public RandomAccessDoubleLinkedList() {
        super();
    }


    /**
     * Copy constructor which initializes the list with another list.
     * This constructor must COPY all elements of the other list.
     */
    public RandomAccessDoubleLinkedList(RandomAccessDoubleLinkedList other) {
        super();
        if (other != null) {
            int size = other.size();
            for (int index = 0; index < size; index++) {
                super.add(other.elementAt(index));
            }
        }
    }

    /**
     * Inserts a new element with value val at the given index. If the
     * index is larger than the current size, the element is added at the
     * last position in the list. Should index be < 0, then do nothing.
     */
    public void insertAt(int index, int val) { // if a value is already in list, at given index the list moves this value one position to the right
       if (index >= 0) {
           if (index < super.size()) {
               super.add(index, val);
           } else {
               super.addLast(val);
           }
       }
    }


    /**
     * Returns true if an element with the given value exists, false
     * otherwise. However, true is returned upon the first occurrence of
     * val.
     */
    public boolean contains(int val) {
        return super.contains(val);
    }


    /**
     * Removes the element at the given index. False if
     * index > list’s size.
     */
    public boolean removeAt(int index) {
        if (index >= 0 && index < super.size()) {
            super.remove(index);
            return true;
        }
        return false;
    }


    /**
     * Removes all elements with the given value. False if
     * val was not found.
     */
    public boolean removeAll(int val) {
        if (contains(val)) {
            while (contains(val)) {
                super.remove((Integer) val); // remove(Object o) so if I write remove(val) and val is an Integer it would call remove (int index) -> typecast
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the integer value at the given index. If index > list’s
     * size, Integer.MIN_VALUE is returned.
     */
    public int elementAt(int index) {
        if (index >= 0 && index < super.size()) {
            return super.get(index);
        }
        return Integer.MIN_VALUE;
    }
}