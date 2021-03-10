import java.util.LinkedList;

import static org.junit.Assert.*;

public class HashTableTest {

    @org.junit.Before
    public void setUp() throws Exception {
        HashTable<Integer,String> table = new HashTable(10);
        System.out.println(table.insert(1, "hi"));
        //System.out.println(table.insert(1, "hi"));
        System.out.println(table.update(1, "bye"));
        System.out.println(table.lookup(1));
        table.insert(2, "hi");
        table.insert(3, "hi");
        table.insert(4, "hi");
        table.insert(5, "hi");
        table.insert(6, "hi");
        table.insert(7, "tgf");
        System.out.println(table.size());
        System.out.println(table.capacity());
        System.out.println(table.lookup(7));



    }

    @org.junit.Test
    public void insert() {
    }
}