import javafx.scene.layout.GridPane;

import java.util.*;

@SuppressWarnings("rawtypes")
public class DAFTree<K extends Comparable<? super K>, D> implements Iterable {

    private DAFNode<K, D> root;
    private int nElems;
    //public PriorityQueue<K> unielems;
    public LinkedList<K> unielems;
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
     * or returns the last node either smaller or bigger
     * @param key
     * @return node at that key
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
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public int nUniqueKeys() {
        return unielems.size();
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> insert(K key, D data, int nCopy) {
        DAFNode temp = getnode(key);
        DAFNode newnode = new DAFNode(key, data, nCopy); //creates a new node
        if (root == null) { // if tree is empty sets it as root
            this.root = newnode;
            this.nElems += 1;
            return newnode;
        } else if (temp.key.equals(key)) {
            unielems.remove(key);
            //if the key already exsits it implements the count
            temp.count++;
            return temp;
        } else if (lookup(key) == null) {
            if (temp.key.compareTo(key) == 0) {
                return null;
            } else if (temp.key.compareTo(key) < 0) {
                temp.right = newnode;
            } else if (temp.key.compareTo(key) > 0) {
                temp.left = newnode;
            }
            if (nCopy == 1) {
                unielems.add(key);
            }
        }
        //getmaxmin(key);
        return newnode;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> insertDuplicate(K key, int nCopy) {
        if (lookup(key).key.equals(key)) {
            //if the key already exsits it implements the count
            DAFNode temp = getnode(key);
            temp.count++;
            return temp;
        }
        return null;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> lookup(K key) {
        DAFNode temp = getnode(key);
        if (temp == null) {
            return null;
        } else if (temp.key.equals(key)) {
            return temp;
        }
        return null;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> updateData(K key, D newData) {
        DAFNode temp = getnode(key);
        if (temp == null) {
            return null;
        } else {
           temp.data = newData;
           return temp;
        }

    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> remove(K key, int nCopy) {
        DAFNode temp = getnode(key);
        if (temp == null) {
            return null;
        }
        temp.count =- nCopy;
        if (temp.count == 1) {
            unielems.add(key);
        } else if (temp.count <= 0) {
            removenode((K) temp.key);

        }
        return temp;
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
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
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public DAFNode<K, D> findExtreme(boolean isMax) {
        return getmaxmin(root, isMax);
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    private DAFNode<K, D> getmaxmin(DAFNode node, boolean ismax) {
        DAFNode temp = node;
        if (ismax) {
            while (node.right != null) {
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
        /**
         * constructor of HashTable
         *
         * @param capacity
         * @throws IllegalArgumentException when capacity less then minimumthreshold
         */
        public DAFTreeIterator() {
            this.stack = new Stack<>();
            if (temp == null) {
                return;
            }
            this.stack.push(temp);
            while (hasNext()) {
                this.temp = temp.left;
                this.stack.push(temp);
            }
        }
        /**
         * constructor of HashTable
         *
         * @param capacity
         * @throws IllegalArgumentException when capacity less then minimumthreshold
         */
        public boolean hasNext() {
            if (temp.left == null) {

                return false;
            }
            //return true;
            return !stack.isEmpty();
        }
        /**
         * constructor of HashTable
         *
         * @param capacity
         * @throws IllegalArgumentException when capacity less then minimumthreshold
         */
        public K next() {
            if (stack.peek().count > 1) {
                this.temp = stack.peek();
                return (K) temp.key;
            }
            this.temp = this.stack.pop();
            if (temp == null) {
                //K key = (K) temp.key;
            }
            K key = (K) temp.key;
            if (!(temp.right == null)) {
                this.stack.push(temp.right);
                this.temp = temp.right;
                while (!(temp.left == null)) {
                    this.temp = temp.left;
                    this.stack.push(temp);
                }
            }
            return key;
        }
    }
    /**
     * constructor of HashTable
     *
     * @param capacity
     * @throws IllegalArgumentException when capacity less then minimumthreshold
     */
    public Iterator<K> iterator() {
        return new DAFTreeIterator();
    }

}
