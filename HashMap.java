import java.util.ArrayList;
import java.util.LinkedList;

public class HashMap<K, V> {
    private int size;
    private double loadFactor;
    private LinkedList<HashMapEntry<K, V>>[] hashTable;

    // Constructors
    // Time complexity: O(1)
    public HashMap() {
        this(100, 0.9);
    }

    // Time complexity: O(log n)
    public HashMap(int c) { // c for capacity, and invoke third constructor
        this(c, 0.9);
    }

    // Time complexity: O(log n)
    public HashMap(int c, double lf) { // create the hashtable
        hashTable = new LinkedList[trimToPowerOf2(c)];
        loadFactor = lf;
        size = 0;
    }

    // private methods
    // Time complexity: O(log n)
    private int trimToPowerOf2(int c) {
        int capacity = 1;
        while (capacity < c)
            capacity = capacity << 1; // shift left one position is equivalnet to * 2
        return capacity;
    }

    private int hash(int hashCode) {
        return hashCode & (hashTable.length - 1); // hashCode % hashTable
    }

    // public interface
    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        for (int i = 0; i < hashTable.length; i++)
            if (hashTable[i] != null)
                hashTable[i].clear(); // clearing the linkedlist at index i
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    // search for key - returns true if found
    public boolean containsKey(K key) {
        if (get(key) != null)
            return true;
        return false;
    }

    // returns the value of key if found, null otherwise
    public V get(K key) {
        int HTIndex = hash(key.hashCode());
        if (hashTable[HTIndex] != null) { // there is not a non-empty linkedlist
            LinkedList<HashMapEntry<K, V>> ll = hashTable[HTIndex];
            for (HashMapEntry<K, V> entry : ll) { // O(1)
                if (entry.getKey().equals(key))
                    return entry.getValue();
            }
        }
        return null;
    }

    // remove a key if found
    public void remove(K key) {
        int HTIndex = hash(key.hashCode());
        if (hashTable[HTIndex] != null) { // key is in the hash map
            LinkedList<HashMapEntry<K, V>> ll = hashTable[HTIndex];
            for (HashMapEntry<K, V> entry : ll) {
                if (entry.getKey().equals(key)) { // O(n), similar to get method but deletes it
                    ll.remove(entry);
                    size--;
                    break;
                }
            }
        }
    }

    // adds a new key or modifies an existing key
    public V put(K key, V value) {
        if (get(key) != null) { // The key is in the hash map
            int HTIndex = hash(key.hashCode());
            LinkedList<HashMapEntry<K, V>> ll;
            ll = hashTable[HTIndex];
            for (HashMapEntry<K, V> entry : ll) {
                if (entry.getKey().equals(key)) {
                    V old = entry.getValue();
                    entry.setValue(value);
                    return old;
                }
            }
        }
        // key not in the hash map - check load factor
        if (size >= hashTable.length * loadFactor)
            rehash(); // O(n) if called when we exceed loadfactor
        int HTIndex = hash(key.hashCode());
        // create a new LL if empty
        if (hashTable[HTIndex] == null) {
            hashTable[HTIndex] = new LinkedList<>();
        }
        HashMapEntry<K, V> newEntry = new HashMapEntry<>(key, value);
        LinkedList<HashMapEntry<K, V>> ll = hashTable[HTIndex];
        ll.add(newEntry);
        size++;
        return value;
    }

    // private method
    private void rehash() {
        ArrayList<HashMapEntry<K, V>> list = toList();
        hashTable = new LinkedList[hashTable.length << 1];
        size = 0;
        for (HashMapEntry<K, V> entry : list)
            put(entry.getKey(), entry.getValue());
    }

    // returns the elements of the hash map as a list
    public ArrayList<HashMapEntry<K, V>> toList() {
        ArrayList<HashMapEntry<K, V>> list = new ArrayList<>();
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                LinkedList<HashMapEntry<K, V>> ll = hashTable[i];
                for (HashMapEntry<K, V> entry : ll)
                    list.add(entry);
            }
        }
        return list;
    }

    // returns the elements of the hash map as a string
    public String toString() {
        String out = "[";
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                for (HashMapEntry<K, V> entry : hashTable[i])
                    out += entry.toString();
                out += "\n";
            }
        }
        out += "]";
        return out;
    }
}