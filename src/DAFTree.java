

import java.util.*;

@SuppressWarnings("rawtypes")
public class DAFTree<K extends Comparable<? super K>, D> implements Iterable {

    private DAFNode<K, D> root;
    private int nElems;
    //public PriorityQueue<K> unielems;
    private LinkedList<K> unielems = new LinkedList<>();
    private K maxkey;
    private K minkey;



    protected class DAFNode<K extends Comparable<? super K>, D> {
        K key;
        D data;
        int count; // duplicate counter
        DAFNode<K, D> left, right;

        /**
         * constructor of DAFNode
         *
         * @param key
         * @param data
         * @throws NullPointerException
         */
        public DAFNode(K key, D data) {

            this.key = key;
            this.data = data;
            this.count = 1;
            this.left = null;
            this.right = null;

        }

        public DAFNode(K key, D data, int nCopy) {
            this.key = key;
            this.data = data;
            this.count = nCopy;
            this.left = null;
            this.right = null;
        }


    }

    public DAFTree() {
        this.root = null;
        this.nElems = 0;
    }
    /**
     * helper method to get the node from the key
     * return the node of the keys are the same
     * or returns the last node the parent
     * @param key
     * @return node at that key
     * @return null if key is not found or tree is empty
     *
     */
    private DAFNode getnode(K key) {
        DAFNode temp = root;
        while (temp != null) {
            if (temp.key.compareTo(key) == 0) {
                return temp;
            } else if (temp.key.compareTo(key) < 0) {
                if (temp.right == null) {
                    return temp;
                }
                temp = temp.right;
            } else if (temp.key.compareTo(key) > 0) {
                if (temp.left == null) {
                    return temp;
                }
                temp = temp.left;
            }
        }
        return temp;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public int size() {
        return nElems;
    }
    /**
     * returns the total number of unique keys stored in the FADAF.
     *
     * @return int
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public int nUniqueKeys() {
        return unielems.size();
    }
    /**
     * inserts key to the tree
     *
     * @param key the key to insert
     * @param data the data for the key
     * @param nCopy the amount of times to count
     * @return DAFNode<K, D> the node that was inserted or if already exists
     * @throws NullPointerException key or data are null
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> insert(K key, D data, int nCopy) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFNode temp = getnode(key); // returns the node or null if it doesnt exists
        if (temp == null) {
        }
        DAFNode newnode = new DAFNode(key, data, nCopy); //creates a new node
        if (root == null) { // if tree is empty sets it as root
            // temp is only null when the root is null
            this.root = newnode;
        } else if (temp.key.compareTo(key) == 0) {
            unielems.remove(key);
            //if the key already exsits it implements the count
            temp.count += nCopy;
            return temp;
        } else if (temp.key.compareTo(key) < 0) {
            temp.right = newnode;
        } else if (temp.key.compareTo(key) > 0) {
            temp.left = newnode;
        }
        if (nCopy == 1) {
            unielems.add(key);
        }
        this.nElems++;
        return newnode;
    }
    /**
     * inserts the given key to the tree nCopy times
     *
     * @param key the key to insert
     * @param nCopy the amount of times to count
     * @return DAFNode<K, D> the node that already exists
     * @return null if key is not found
     * @throws NullPointerException key or data are null
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> insertDuplicate(K key, int nCopy) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFNode temp = getnode(key);
        if (temp.key.compareTo(key) == 0) {
            //if the key already exsits it implements the count
            temp.count += nCopy;
            return temp;
        }
        //returns null if data is not the same or temp is null
        return null;
    }
    /**
     * A method that checks if a node with the given key is stored in the tree
     *
     * @param key the key to insert
     * @return DAFNode<K, D> the node that already exists
     * @return null if key is not found
     * @throws NullPointerException key  are null
     */
    public DAFNode<K, D> lookup(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        DAFNode temp = getnode(key);
        if (temp == null) {
            return null;
        } else if (temp.key.compareTo(key) == 0) {
            return temp;
        }
        return null;
    }
    /**
     * updates the data associated with the given key to newData.
     * This method returns the node instance if the update is successful,
     * or null if the key is not found.
     *
     * @param key the key to update
     * @param newData the data for the key
     * @return DAFNode<K, D> the node that already exists
     * @return null if key is not found
     * @throws NullPointerException key or data are null
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> updateData(K key, D newData) {
        if (key == null || newData == null) {
            throw new NullPointerException();
        }
        DAFNode temp = getnode(key);
        if (temp == null) {
            return null;
        } else if (temp.key.compareTo(key) == 0){
           temp.data = newData;
           return temp;
        }
        return null;

    }
    /**
     * A method that removes the node with the given key from the tree nCopy times.
     *
     * @param key the key to remove
     * @param nCopy the amount of times to remove
     * @return DAFNode<K, D> the node that already exists
     * @return null if key is not found
     * @throws IllegalArgumentException when ncopy is less then one
     * @throws NullPointerException key or data are null
     */
    public DAFNode<K, D> remove(K key, int nCopy) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (nCopy < 1) {
            throw new IllegalArgumentException();
        }
        DAFNode temp = getnode(key);
        if (temp == null) {
            return null;
        } else if (temp.key.compareTo(key) == 0) {
            // checks if the keys are the same
            temp.count -= nCopy;
            if (temp.count == 1) {
                unielems.add(key);
            } else if (temp.count <= 0) {
                removenode((K) temp.key);
            }
            return temp;
        }
        // returns null when data not found
        return null;
    }
    /**
     * removes the whole node from the tree
     *
     * @param key the key to remove
     * @return true if it was removed false otherwise
     * @throws NullPointerException key or data are null
     */
    public boolean removenode(K key) {
        DAFNode par = null;
        DAFNode cur = root;
        while (cur != null) { // Search for node
            if (cur.key.compareTo(key) == 0) { // Node found
                if (cur.left == null && cur.right == null) { // Remove leaf
                    if (par == null) {// Node is root
                        this.root = null;
                        return true;
                    } else if (par.left.key.compareTo(cur.key) == 0) {
                        par.left = null;
                        return true;
                    } else {
                        par.right = null;
                        return true;
                    }
                } else if (cur.right == null) { // Remove node with only left child
                    if (par == null){
                        this.root = cur.left; // Node is root
                        return true;
                    } else if (par.left == cur){
                        par.left = cur.left;
                        return true;
                    } else{
                        par.right = cur.left;
                        return true;
                    }
                } else if (cur.left == null) {    // Remove node with only right child
                    if (par == null){// Node is root
                        this.root = cur.right;
                        return true;
                    } else if (par.left == cur){ // if node is the left child
                        par.left = cur.right;
                        return true;
                    } else {
                        par.right = cur.right; // if node is the right child
                        return true;
                    }
                } else {  // Remove node with two children
                    // Find successor (leftmost child of right subtree)
                    DAFNode suc = cur.right;
                    while (suc.left != null){
                        suc = suc.left;
                    }
                    D successorData = (D) suc.data;
                    int successorcount = suc.count;
                    K successorkey = (K) suc.key;
                    cur.key = successorkey;
                    cur.data = successorData;
                    cur.count = successorcount;
                    removenode((K) suc.key);     // Remove successor
                    return true;
                }
                //return false;// Node found and removed
            } else if (cur.key.compareTo(key) < 0) { // Search right
                par = cur;
                cur = cur.right;
            } else {        // Search left
                par = cur;
                cur = cur.left;
            }
        }
        return false;// Node not found
    }
    /**
     * A method that finds the node with the most extreme key.  If the tree is empty, return null.
     *
     * @param isMax If isMax is true return the max node; otherwise,return the min.
     * @return min or max node
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> findExtreme(boolean isMax) {
        return getmaxmin(root, isMax);
    }
    /**
     * helper method to return max or min of that node also used in removenode
     */
    private DAFNode<K, D> getmaxmin(DAFNode node, boolean ismax) {
        if (node == null) {
            return null;
        }
        DAFNode temp = node;
        if (ismax) {
            while (temp.right != null) {
                temp = temp.right;
            }
            return temp;
        } else {
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public class DAFTreeIterator implements Iterator<K> {
        DAFNode temp = root;
        Stack<DAFNode<K, D>> stack;
        int parr = 0;
        K multi;
        /**
         * A constructor that initializes a DAFTree iterator that iterates
         * through all keys in the tree (including duplicates).
         *
         */
        public DAFTreeIterator() {
            this.stack = new Stack<>();
            if (temp == null) {
                return;
            }

            this.stack.push(temp);
            while (temp.left != null) {
                temp = temp.left;
                this.stack.push(temp);
            }
        }
        /**
         * A method that checks if the iterator has more elements to return.
         * This method returns true if there are more elements, and false otherwise.
         *
         * @return  bolean true if there is more element to return
         */
        public boolean hasNext() {
            //if (temp.left == null) {

                //return false;
            //}
            //return true;
            return (!stack.isEmpty() || parr > 0);
        }
        /**
         * returns the next element in stack
         *
         * @return the next key on the stack
         */
        public K next() {

            if (parr > 0) {
                this.parr--;
              //  //this.temp = stack.peek();
                return multi;
            }

            this.temp = this.stack.pop();
            if (temp.count > 1) {
                this.parr = temp.count - 1;
                this.multi = (K) temp.key;
            }
            if (temp == null) {
                throw new NoSuchElementException();
            }

            K key = (K) temp.key;
            if (!(temp.right == null)) {
                this.stack.push(temp.right);
                temp = temp.right;
                while (!(temp.left == null)) {
                    temp = temp.left;
                    this.stack.push(temp);

                }
            }
            return key;
        }
    }
    /**
     * A method that returns a new DAFTree iterator instance.
     *
     */
    public Iterator<K> iterator() {
        return new DAFTreeIterator();
    }

}
