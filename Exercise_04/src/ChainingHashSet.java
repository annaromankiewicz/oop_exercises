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
     * Initializes an empty hashtable with the given number of overflow
     * lists.
     */
    public ChainingHashSet(int indexSize) {
        if (indexSize > 0) {
            array = new RandomAccessDoubleLinkedList[indexSize];
        }
    }

    /**
     * Inserts a new element val into the hashtable (hashcode = val %
     * array.length), if it did not exist in the table before. Returns true
     * if a new element was inserted, false if it already existed.
     */
    public boolean insert(int val) {
        int hashcode = val % array.length;
        if (array[hashcode] == null) { // first element with the hashcode
            array[hashcode] = new RandomAccessDoubleLinkedList();
            array[hashcode].add(val);
            return true;
        } else if (!array[hashcode].contains(val)) {
            array[hashcode].add(val);
            return true;
        }
        return false;
    }


    /**
     * Returns true if the given value is already stored in the
     * hashtable, false otherwise.
     */
    public boolean contains(int val) {
        int hashcode = val % array.length;
        if (array[hashcode] == null) return false; // List of given val with hashcode is empty
        return (array[hashcode].contains(val));
    }


    /**
     * Removes the given element from the hashtable if it exists.
     * Returns true if an element was removed, false if no such
     * element existed.
     */
    public boolean remove(int val) {
        if (this.contains(val)) {
            int hashcode = val % array.length;
            array[hashcode].remove((Integer) val); // remove(Object o) -> casting necessary to call function with Integer
            return true;
        }
        return false;
    }

    /**
     * Counts the amount of values, which are stored in the same
     * overflow list
     */
    public int getOverflowCount(int hashVal) {
        if (hashVal < 0 || hashVal >= array.length) return -1; // invalid input for hashVal
        if (array[hashVal] == null) return 0;
        return array[hashVal].size();
    }

    /**
     * Counts the amount of elements in the hashtable by adding the
     * amount of elements in each overflow list.
     */
    public int elements() {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                sum += array[i].size();
            }
        }
        return sum;
    }
}