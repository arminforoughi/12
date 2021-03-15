import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FADAFTest {
    FADAF<Integer, Integer> a = new FADAF(10);
    @Before
    public void setUp() throws Exception {

        a.insert(1,1,1);
        System.out.println(a.size());
        System.out.println(a.nUniqueKeys(1));
        //System.out.println(a.remove(1,1));
        //ystem.out.println(a.lookup(1));
    }

    @Test
    public void size() {
    }

    @Test
    public void nUniqueKeys() {
    }

    @Test
    public void insert() {
        System.out.println(a.insert(1,1,1));
    }

    @Test
    public void lookup() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void removeAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getAllKeys() {
    }

    @Test
    public void getUniqueKeysInRange() {
    }

    @Test
    public void getMinKey() {
    }

    @Test
    public void getMaxKey() {
    }
}