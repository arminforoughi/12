import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("rawtypes")
public class HashTable<K, D> {
    private final int minimumthreshold = 10;
    private final int getterresultsize = 2;
    private final int doublesize = 2;
    private final double lfactormax = 0.66667;


    protected class TableEntry<K, D> {
        private K key;
        private D data;

        public TableEntry(K key, D data) {
            this.key = key;
            this.data = data;
        }

        @Override
        public boolean equals(Object obj) {
            if ((obj == null) || !(obj instanceof TableEntry))
                return false;
            return key.equals(((TableEntry<?, ?>) obj).key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    private LinkedList<TableEntry<K, D>>[] table;
    private int nElems;


    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity < minimumthreshold) {
            throw new IllegalArgumentException();
        }
        this.table = new LinkedList[capacity];
    }

    public boolean insert(K key, D data) {
        if ((double) size() / capacity() > lfactormax) {
            //checks the load factor then rehash
            rehash();
        }
        int[] result = getter(key, data);
        TableEntry temp = new TableEntry<>(key, data);

        if (result[1] == -1) {
            // if there is no linkedlist initialized
            table[result[0]] = new LinkedList<>();
        }
        
        if (lookup(key) == null) {
            // lookup returns null if key is not found
            table[result[0]].add(temp); //add temp to the linkedlist
            this.nElems++; // implements number of elements
            return true;
        }
        return false;

    }

    public boolean update(K key, D newData) {
        int[] result = getter(key, null);
        TableEntry temp = new TableEntry<>(key, newData);
        table[result[0]].set(result[1], temp);
        return true;
    }

    private int[] getter(K key, D newData) {
        int hkey = hashValue(key); // hash key
        int[] result = new int[getterresultsize]; // creates a result with hashkey and index
        result[0] = hkey; //sets hash key as index 0 of result
        TableEntry temp = new TableEntry<>(key, newData); // temp node to find
        result[1] = -1;
        if (table[hkey] instanceof LinkedList) {
            for (int i = 0; i < table[hkey].size(); i++) {
                //loops through the getter to find the temp node
                if (table[hkey].get(i).equals(temp)) {
                    result[1] = i;
                    break;
                }
            }
        }
            // if linkedlist doesn't exists
        return result;
    }

    public boolean delete(K key) {
        if (lookup(key) == null) {
            return false;
        }
        int[] result = getter(key, null);
        table[result[0]].remove(result[1]);
        this.nElems--;
        return true;
    }

    public D lookup(K key) {
        int[] result = getter(key, null); //get the hashnum and index of key
        if (result[1] == -1) {
            //if index doesnt exists return null

            return null;
        }
        try {
            return (D) table[result[0]].get(result[1]).data;
            // return the data at that index and hash num
        } catch (NullPointerException e) {
            // return null if no data found

            return null;
        }
    }

    public int size() {
        return nElems;
    }

    public int capacity() {
        return table.length;
    }

    private int hashValue(K key) {
        return Math.abs(key.hashCode() % capacity());
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        HashTable<K, D> temp = new HashTable(capacity() * doublesize);
        for (int i = 0; i < capacity(); i++) {
            // for every list in table
            if (table[i] instanceof LinkedList) { //checks if linkedlist exists
                for (int j = 0; j < table[i].size(); j++) {
                    // for every element in linkedlist
                    TableEntry tempn = table[i].get(j);
                    temp.insert((K) tempn.key, (D) tempn.data);
                }
            }

        }
        this.table = temp.table;
    }


    public static void main(String[] args) {

        HashTable<String, String> table = new HashTable(10);

        File file = new File(args[0]); // reads the file
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                //while loop reads everyline
                String word = scanner.nextLine().trim();
                table.insert(word, word); //insets word into table
            }
            scanner.close();//set table to tablelist

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return;
        }

        System.out.println(table.delete("the"));
        System.out.println(table.size());
        System.out.println(table.capacity());

    }
}
