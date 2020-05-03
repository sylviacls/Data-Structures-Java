
/**
 * HashTable implementation using Genrics and array as the backing data structure
 * Collision treatment: Open addressing - Linear Probation
 */

public class SimpleHashTable<K,V> {
    private Node<K,V>[] table;

    @SuppressWarnings("unchecked")
    public SimpleHashTable() {
        this.table = new Node[11];
    }

    /**
     * Hash Function 
     * @param key
     * @return the hashed key
     */
    public int hashKey(K key) {
        int hashedKey = key.hashCode() % this.table.length;
        if(hashedKey < 0) {
            hashedKey += this.table.length;
        }
        return hashedKey; 
    }

    /**
     * Insert an key/value pair in the table
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int hashedKey = hashKey(key);
        int stopIndex = hashedKey;

        //search for the next empty slot
        while (this.table[hashedKey] != null && hashedKey != stopIndex - 1) {
      //      System.out.println("Collisition! key = " + key + "  hashkey tried = " + hashedKey);
            hashedKey++;
            hashedKey = hashedKey % this.table.length;
        }

        //there are 2 option in this point: we find an empty space or the table is full
        if(this.table[hashedKey] != null) {
            System.out.println("Sorry, there's already an employee at position " + hashedKey
                                + " Not possible to add " + value);
        } else {
            this.table[hashedKey] = new Node<K,V>(key, value);
        }
    }

    /**
     * Find the correct hashedKey used to store the value
     * @param key
     * @return
     */
    public int findKey(K key){
        int hashedKey = hashKey(key);
        int stopIndex = hashedKey;

        while(hashedKey != stopIndex - 1 && 
            this.table[hashedKey] != null && 
            !this.table[hashedKey].getKey().equals(key)){
            hashedKey = (hashedKey + 1) % this.table.length;
        }

        if(this.table[hashedKey] == null) {
            return -1;
        } else{
            return hashedKey;
        }

    }
    /**
     * Remove the element whose key is given
     * @param key
     * @return the removed element or null
     */

    @SuppressWarnings("unchecked")
    public V remove(K key) {
        int hashKey = findKey(key);

        if(hashKey == -1) return null;

        V itemRemoved = this.table[hashKey].getValue();
        this.table[hashKey] = null;

        //organizing the table in order of avoid null values between prior hashed key
        // and the linear probing method
        Node<K,V>[] oldTable = this.table;
        this.table = new Node[oldTable.length];
        for (Node<K,V> node : oldTable) {
            if(node != null) {
                put(node.getKey(), node.getValue());
            }
        }
        return itemRemoved;
    }

    /**
     * Return the Value correspondent to the given key
     * @param key
     * @return value founded or -1
     */
    public V get(K key) {
        int hashedKey = findKey(key);

        if(hashedKey == -1) return null;

        return this.table[hashedKey].getValue();
    }

    /**
     * Auxiliar method to print the table
     */
    public void print() {
        for (int i = 0; i < table.length; i++) {
            Node<K,V> node = table[i];
            if(node != null) {
                System.out.println("Key = " + node.getKey()
                + " Value = " + node.getValue());
            } else{
                System.out.println("Empty");
            }

        }
    }
    
    public static void main(String[] args) {
        SimpleHashTable<String,String> table = new SimpleHashTable<String,String>();
        table.put("123", "Sylvia");
        table.put("3211", "Gabriella");
        table.put("456", "Pedro");
        table.put("7891", "Felipe");
        table.put("8383", "Carol");
        table.put("2992", "Danilo");   
        table.put("11", "B");   
        table.print();
        System.out.println("Now removing");
        System.out.println("Removed item = "+ table.remove("2992"));
        System.out.println("Removed item = "+ table.remove("11"));
        table.print();
        System.out.println("Now adding");
        table.put("11", "B");  
        table.print();
        System.out.println("Value founded = " +table.get("11"));
    }
}

class Node<K,V> {

    private K key;
    private V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;

    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    

}