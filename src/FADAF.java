import java.util.*;

@SuppressWarnings("rawtypes")
public class FADAF<K extends Comparable<? super K>, D> {

    private HashTable<K, D> htable;
    private DAFTree<K, D> dtree;


    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public FADAF(int capacity) {
        this.htable = new HashTable<>(capacity);
        this.dtree = new DAFTree<>();
    }

    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public int size() {
        return htable.size();

    }
    /**
     * returns the unique keys from the linkedlist
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */

    public int nUniqueKeys() {
        dtree.nUniqueKeys();
        return dtree.nUniqueKeys();
    }

    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public boolean insert(K key, D data, int nCopy) {
        return htable.insert(key, dtree.insert(key, data, nCopy);
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public int lookup(K key) {
        //return htable.lookup(key);
        return dtree.lookup(key).count;
        //return -1;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public boolean remove(K key, int nCopy) {
        return (htable.delete(key) && dtree.remove(key, nCopy).key.equals(key));

    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public boolean removeAll(K key) {
        return (htable.delete(key) && dtree.removenode(key));

    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public boolean update(K key, D newData) {
        dtree.updateData(key, newData);
        htable.update(key, newData);
        return false;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public List<K> getAllKeys(boolean allowDuplicate) {
        if (!allowDuplicate) {
            return (List<K>) dtree.unielems;
        }
        // loops thrugh the itterator to get all the keys.
        LinkedList<K> result = new LinkedList<>();
        Iterator<K> treeit = dtree.iterator();
        while (treeit.hasNext()) {
            result.add(treeit.next());
        }
        return null;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public List<K> getUniqueKeysInRange(K lower, K upper) {
        // uses a prioty queue to store the uniqe lists
        LinkedList<K> result = new LinkedList<>();
        K temp;
        temp = dtree.unielems.peek();
        while (temp.compareTo(lower) > 0 && temp.compareTo(upper) < 0) {
            temp = dtree.unielems.poll();
            result.add(temp);
        }
        // uses a linkedlist to store the unique elements
        for (int i = 0; i < dtree.unielems.size(); i++) {
            temp = dtree.unielems.get(i);
            if (temp.compareTo(lower) > 0 && temp.compareTo(upper) < 0) {
                result.add(temp);
            }
        }
        return result;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public K getMinKey() {
        dtree.findExtreme(false);
        return null;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public K getMaxKey() {
        dtree.findExtreme(true);
        return null;
    }

}
