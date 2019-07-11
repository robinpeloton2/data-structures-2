public abstract class HashTable {

    int capacity = 10;
    int size = 0;

    /**
     *
     * @return size of the hash table.
     */
    public int size() {
        return size;
    }

    /**
     * Adds the given object to the hash table as long as it is not a duplicate
     * 
     * @param obj
     * @return true if the element is successfully added
     */
    abstract boolean add(Object obj);

    /**
     * Loops through the table and checks for a given object
     * 
     * @param obj
     * @return true if this hash table contains the given object
     */
    abstract boolean contains(Object obj);

    /**
     * 
     * @param obj
     * @return Removes the given object from the array if present
     */
    abstract boolean remove(Object obj);
}