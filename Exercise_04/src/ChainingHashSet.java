public class ChainingHashSet {
//    /** Array which stores overflow lists that are indexed by the hash
//     code of their elements. */
//    private RandomAccessDoubleLinkedList[] array;
//    /** Initializes an empty hashtable with the given number of overflow
//     lists. */
//    public ChainingHashSet(int indexSize) { ... }
//    /** Inserts a new element val into the hashtable (hashcode = val %
//     array.length), if it did not exist in the table before. Returns true
//     if a new element was inserted, false if it already existed. */
//    public boolean insert (int val) { ... }
//    /** Returns true if the given value is already stored in the
//     hashtable, false otherwise. */
//    public boolean contains (int val) { ... }
//    /** Removes the given element from the hashtable if it exists.
//     Returns true if an element was removed, false if no such
//     element existed. */
//    public boolean remove (int val) { ... }
//    /** Counts the amount of values, which are stored in the same
//     overflow list */
//    public int getOverflowCount(int hashVal) { ... }
//
//    /** Counts the amount of elements in the hashtable by adding the
//     amount of elements in each overflow list. */
//    public int elements() { ... }
}