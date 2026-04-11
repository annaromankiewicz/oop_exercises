public class ChainingHashSet {

// Überlegen bzw. recherchieren Sie, warum es hier sinnvoll ist die ArrayList und nicht die
// LinkedList Klasse aus java.util zu verwenden.
//
// Antwort:
// Vorteil: Zugriff über den Index ist bei der ArrayList O(1), da Elemente in einem
// dynamisch wachsenden Array gespeichert werden und direkt über den Index angesprochen
// werden können. Bei der LinkedList hingegen ist der Zugriff über den Index O(n), da
// alle Elemente vom Anfang bis zur gesuchten Position durchlaufen werden müssen.
//
// Hinten einfügen (append) ist bei beiden Strukturen gleich effizient — O(1) amortisiert
// bei ArrayList und O(1) bei LinkedList (da ein Tail-Pointer vorhanden ist).
//
// Im ChainingHashSet wird jeder Bucket über einen Index angesprochen (hash(key) % capacity).
// Daher ist der schnelle Indexzugriff der ArrayList entscheidend. Die LinkedList würde
// diesen Vorteil nicht bieten und wäre für diesen Anwendungsfall deutlich langsamer.
//
// Zusätzlich verbraucht die ArrayList weniger Speicher, da die LinkedList für jeden
// Knoten zwei zusätzliche Zeiger (prev und next) speichern muss.
//
// Fazit: Für das ChainingHashSet ist die ArrayList eindeutig die bessere Wahl.

    /**
     * Array which stores overflow lists that are indexed by the hash
     * code of their elements.
     */
    private RandomAccessDoubleLinkedList[] array;

    /**
     * Calculates the hashcode for every val with values in [Integer.MIN_VALUE; Integer.MAX_VALUE]
     * Handles all values in the given Interval of Integers correctly, because it is returning
     * positive hashcodes for all values, which are necessary to save them in the ArrayList.
     * But it is handling negative values like in official HashSets, because -6 and 6 are not getting
     * saved with the same hash -> hash(-6)=1 and hash(6)=6
     */
    private int hash(T elem) {
        // inner round bracket: result negative, second round bracket: result is positive
        return ((val % array.length) + array.length) % array.length;
    }

    /**
     * Initializes an empty hashtable with the given number of overflow
     * lists. Allows indixSizes > 0 because an array with length 0 can't
     * store values and would cause a division by zero in hash(T elem)!
     */
    public ChainingHashSet(int indexSize) {
        if (indexSize > 0) {
            array = new RandomAccessDoubleLinkedList[indexSize];
        } // array stays null because it is not initialized
    }

    /**
     * Inserts a new element val into the hashtable (hashcode = val %
     * array.length), if it did not exist in the table before. Returns true
     * if a new element was inserted, false if it already existed.
     */
    public boolean insert(T elem) { // we use the absolute value of hashcode of negative values to not lose data
        if (this.array != null) {
            int hashcode = hash(val);
            if (array[hashcode] == null) { // first element with the hashcode
                array[hashcode] = new RandomAccessDoubleLinkedList();
                array[hashcode].add(val);
                return true;
            } else if (!array[hashcode].contains(val)) {
                array[hashcode].add(val);
                return true;
            }
            return false;
        } return false;
    }


    /**
     * Returns true if the given value is already stored in the
     * hashtable, false otherwise.
     */
    public boolean contains(T elem) {
        if (this.array != null) {
            int hashcode = hash(val);
            if (array[hashcode] == null) return false; // List of given val with hashcode is empty
            return (array[hashcode].contains(val));
        } return false;
    }


    /**
     * Removes the given element from the hashtable if it exists.
     * Returns true if an element was removed, false if no such
     * element existed.
     */
    public boolean remove(T elem) {
        if (this.array != null) {
            if (this.contains(val)) {
                int hashcode = hash(val);
                array[hashcode].remove((Integer) val); // remove(Object o) -> casting necessary to call function with Integer
                return true;
            }
            return false;
        } return false;
    }

    /**
     * Counts the amount of values, which are stored in the same
     * overflow list
     */
    public int getOverflowCount(int hashVal) {
        if (this.array == null || hashVal < 0 || hashVal >= array.length) { // invalid input for hashVal
            return Integer.MIN_VALUE;
        }
            if (array[hashVal] == null) return 0; // Overflowlist is not initialized
            return array[hashVal].size();
        }

    /**
     * Counts the amount of elements in the hashtable by adding the
     * amount of elements in each overflow list.
     */
    public int elements() {
        if (this.array != null) {
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    sum += array[i].size();
                }
            }
            return sum;
        } return Integer.MIN_VALUE; // array was initialized with invalid indexValue -> this.array is null
    }
}