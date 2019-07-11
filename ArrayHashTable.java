import java.util.ArrayList;

/**
 * 
 * 
 * @author: White, Robin
 */


public class ArrayHashTable extends HashTable {

    Object table[][]; // Two dimensional array
    int counts[]; // number of elements at each location
    int chainSize = 5; // Initial size of each array, defualt to 5

    /**
     * Default constructor to create an instance of an ArrayHashTable object and
     * initialise it.
     */
    public ArrayHashTable() {
        // Initialize table to an array of arrays of size capacity
        table = new Object[capacity][];
        counts = new int[capacity];

        // Initalise all values in table to null
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }

        // Initalise all counts to 0
        for (int i = 0; i < capacity; i++) {
            counts[i] = 0;
        }
    }

    @Override
    boolean add(Object obj) {
        // Generate hash code within the capacity.
        int hash = obj.hashCode() % this.capacity;

        // Check if entry is empty and create an array of size chainSize
        if (table[hash] == null) {
            Object[] chainAry = new Object[chainSize];
            table[hash] = chainAry;
        }

        // Checks for duplicate objects in the hash table
        if (!contains(obj)) {
            // If the array is full, double the size of the array
            // and copy over the values
            if (table[hash][table[hash].length - 1] != null) {

                // Copy the chain array into a temporary array
                Object tempArr[] = new Object[this.table[hash].length * 2];
                System.arraycopy(this.table[hash], 0, tempArr, 0, this.table[hash].length);

                // Copy the chains back but with the chain size doubled
                this.table[hash] = tempArr;
            }

            // Add the given object to the hash table in the next free space
            boolean added = false;
            int i = 0;
            while (!added) {
                // Check if current position is null then add the passed obj
                //and increase the count of the current hash array and size by 1
                if (table[hash][i] == null) {
                    table[hash][i] = obj;
                    counts[hash]++;
                    size++;
                    added = true;
//                    System.out.println("Added " + table[hash][i] + " with hash " + hash + " to hash table.");
                }
                i++;
            }
            // Item successfully added
            return true;
        }
        return false;
    }

    @Override
    boolean contains(Object obj) {
        int hash = obj.hashCode() % this.capacity;

        // Loop though chain and check for given object, if found return true
        for (int i = 0; i < counts[hash]; i++) {
            if (table[hash][i] == obj) {
                System.out.println(table[hash][i] + " found at positions: [" + hash + "," + i + "].");
                return true;
            }
        }
        return false;
    }

    @Override
    boolean remove(Object obj) {
        int hash = obj.hashCode() % this.capacity;

        for (int i = 0; i < counts[hash]; i++) {
            // If array contains the object passed, set the given object to null
            if (table[hash][i] == obj) {
                System.out.println("Removed " + table[hash][i] + " " + "from hash table.");
                table[hash][i] = null;

                // Move all elements in the array back one to compensate for
                // the object that was removed
                for (int j = i + 1; j < counts[hash]; j++) {
                    table[hash][j - 1] = table[hash][j];
                }

                counts[hash]--;
                size--;

                // Successfully removed the object
                return true;
            }
        }
        return false;
    }
}