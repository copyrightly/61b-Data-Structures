/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which table of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a table in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

    /**
     *  Place any data fields here.
     **/
    DList[] table;
    int length = 0;
    int size = 0;


    /**
     *  Construct a new empty hash table intended to hold roughly sizeEstimate
     *  entries.  (The precise number of tables is up to you, but we recommend
     *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
     **/

    public HashTableChained(int sizeEstimate) {
        int flag = 0;
        for(int i = sizeEstimate; i < 2 * sizeEstimate; i++) {
            for(int j = 2; j < i; j++) {
                if(i % j == 0) {
                    flag = 0;
                    break;
                } else {
                    flag = 1;
                }
            }
            if(flag == 1) {
                length = i;
                break;
            }
        }
        table = new DList[length];
        for (int i = 0; i < length; i++) {
            table[i] = new DList();
        }
    }

    /**
     *  Construct a new empty hash table with a default size.  Say, a prime in
     *  the neighborhood of 100.
     **/

    public HashTableChained() {
        length = 101;
        table = new DList[length];
        for (int i = 0; i < length; i++) {
            table[i] = new DList();
        }
    }

    /**
     *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of hash table) - 1.
     *
     *  This function should have package protection (so we can test it), and
     *  should be used by insert, find, and remove.
     **/

    int compFunction(int code) {
        // Replace the following line with your solution.
        int p = 0;
        int flag = 0;
        for(int i = 100 * length; i < 200 * length; i++) {
            for(int j = 2; j < i; j++) {
                if(i % j == 0) {
                    flag = 0;
                    break;
                } else {
                    flag = 1;
                }
            }
            if(flag == 1) {
                p = i;
                break;
            }
        }
        return (Math.abs(code + 1) % p) % length;
    }

    /**
     *  Returns the number of entries stored in the dictionary.  Entries with
     *  the same key (or even the same key and value) each still count as
     *  a separate entry.
     *  @return number of entries in the dictionary.
     **/

    public int size() {
        // Replace the following line with your solution.
        return size;
    }

    /**
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/

    public boolean isEmpty() {
        // Replace the following line with your solution.
        return size() == 0;
    }

    /**
     *  Create a new Entry object referencing the input key and associated value,
     *  and insert the entry into the dictionary.  Return a reference to the new
     *  entry.  Multiple entries with the same key (or even the same key and
     *  value) can coexist in the dictionary.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/

    public Entry insert(Object key, Object value) {
        // Replace the following line with your solution.
        Entry newEntry = new Entry();
        newEntry.key = key;
        newEntry.value = value;
        table[compFunction(key.hashCode())].insertFront(newEntry);
        size++;
        return newEntry;
    }

    /**
     *  Search for an entry with the specified key.  If such an entry is found,
     *  return it; otherwise return null.  If several entries have the specified
     *  key, choose one arbitrarily and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/

    public Entry find(Object key) {
        // Replace the following line with your solution.
        int position = compFunction(key.hashCode());
        if (table[position].length() == 0) {
            return null;
        } else {
            DListNode node = table[position].front();
            while (node != table[position].next(table[position].back())) {
                if (((Entry) node.item).key().equals(key)) return (Entry) node.item;
                node = table[position].next(node);
            }
            return null;
        }
    }

    /**
     *  Remove an entry with the specified key.  If such an entry is found,
     *  remove it from the table and return it; otherwise return null.
     *  If several entries have the specified key, choose one arbitrarily, then
     *  remove and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */

    public Entry remove(Object key) {
        // Replace the following line with your solution.
        int position = compFunction(key.hashCode());
        if (table[position].length() == 0) {
            return null;
        } else {
            DListNode node = table[position].front();
            while (node != table[position].next(table[position].back())) {
                if (((Entry) node.item).key().equals(key)) {
                    table[position].remove(node);
                    return (Entry) node.item;
                }
                node = table[position].next(node);
            }
            return null;
        }
    }

    /**
     *  Remove all entries from the dictionary.
     */
    public void makeEmpty() {
        // Your solution here.
        for (int i = 0; i < length; i++) {
            table[i] = new DList();
        }
    }

    public void countCollision() { //toString()
        int numCollision = 0;
        for (int i = 0; i < length; i++) {
            System.out.print("bucket[" + i + "]:");
            if (table[i].length() > 0) {
                DListNode node = table[i].front();
                while (node != table[i].next(table[i].back())) {
                    System.out.print("(" + ((Entry) node.item).key() + "," + ((Entry) node.item).value() + ") ");
                    node = table[i].next(node);
                }
                numCollision = numCollision + table[i].length() - 1;
            }
            System.out.print("\n");
        }
        double expected = size() - length + length * Math.pow((1 - 1.0 / length),size());
        System.out.println("expected number:" + expected);
        System.out.println("number of collisions:" + numCollision);
    }

    public static void main(String[] args) {
        HashTableChained table = new HashTableChained(4);
        System.out.println("=====================size, isEmpty=========================");
        System.out.println((int) Math.pow(2,40) * 2);
        System.out.println((int) Math.pow(2,41) + (int) Math.pow(2,40));
        System.out.println((int) Math.pow(2,41) + (int) Math.pow(2,40) + (int) Math.pow(2,39));
        //Test hashTable methods
        System.out.println("*************** size, isEmpty ***************");
        System.out.println("table's size is: " + table.size());
        System.out.println("table is Empty: " + table.isEmpty());

        System.out.println("*************** insert ***************");
        table.insert("1", "The first one");
        table.insert("2", "The second one");
        table.insert("3", "The third one");
        table.insert("4", "The fourth one");
        table.insert("5","The fifth one");
        System.out.println("table's size is: " + table.size());
        System.out.println("table is Empty: " + table.isEmpty());
        table.countCollision();

        System.out.println("*************** find, remove ***************");
        Entry e1 = table.find("1");
        if(e1 == null)
            System.err.println("find() has a problem.");
        else
            System.out.println("Found " + e1);

        Entry e2 = table.find("2");
        if(e2 == null)
            System.err.println("find() has a problem.");
        else
            System.out.println("Found " + e2);

        Entry e3 = table.find("10");
        if(e3 != null)
            System.err.println("find() has a problem.");
        else
            System.out.println("Cannot find 10");

        Entry e4 = table.remove("10");
        if(e4 != null)
            System.err.println("remove() has a problem.");
        else
            System.out.println("10 cannot be deleted.");

        Entry e5 = table.remove("4");
        if(e5 == null) {
            System.err.println("remove() has a problem");
        }
        else {
            System.out.println("Deleted is " + e5);
        }
        table.countCollision();

        System.out.println("*************** makeEmpty ***************");
        table.makeEmpty();
        table.countCollision();
    }

}