/*
 * ArrayList.java
 *
 * Computer Science E-22
 *
 * modified by:
 *   name: 
 *   email:
 */

import java.util.*;

/*
 * A class that implements our simple List interface using an array.
 */
public class ArrayList implements List {
    private Object[] items;     // the items in the list
    private int length;         // # of items in the list
    
    /*
     * Constructs an ArrayList object with the specified maximum size
     * for a list that is initially empty.
     */
    public ArrayList(int maxSize) {
        items = new Object[maxSize];
        length = 0;
    }
    
    /*
     * Constructs an ArrayList object containing the items in the specified
     * array, and with a max size that is twice the size of that array 
     * (to allow room for growth).
     */
    public ArrayList(Object[] initItems) {
        items = new Object[2 * initItems.length];        
        for (int i = 0; i < initItems.length; i++) {
            items[i] = initItems[i];
        }
        
        length = initItems.length;
    }
    
    /*
     * length - returns the number of items in the list 
     */
    public int length() {
        return length;
    }
    
    /* 
     * isFull - returns true if the list is full, and false otherwise
     */
    public boolean isFull() {
        return (length == items.length);
    }
    
    /* getItem - returns the item at position i in the list */
    public Object getItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
        
        return items[i];
    }
    
    /* 
     * addItem - adds the specified item at position i in the list,
     * shifting the items that are currently in positions i, i+1, i+2,
     * etc. to the right by one.  Returns false if the list is full,
     * and true otherwise.
     */
    public boolean addItem(Object item, int i) {
        if (i < 0 || i > length) {
            throw new IndexOutOfBoundsException();
        } else if (isFull()) {
            return false;
        }
        
        // make room for the new item
        for (int j = length - 1; j >= i; j--) {
            items[j + 1] = items[j];
        }
        
        items[i] = item;
        length++;
        return true;
    }
    
    /* 
     * removeItem - removes the item at position i in the list,
     * shifting the items that are currently in positions i+1, i+2,
     * etc. to the left by one.  Returns a reference to the removed
     * object.
     */
    public Object removeItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
        
        Object removed = items[i];
        
        // fill in the "hole" left by the removed item
        for (int j = i; j < length - 1; j++) {
            items[j] = items[j + 1];
        }
        items[length - 1] = null;
        
        length--;
        return removed;
    }
        
    /*
     * toString - converts the list into a String of the form 
     * {item0, item1, ...}
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < length; i++) {
            str = str + items[i];
            if (i < length - 1) {
                str = str + ", ";
            }
        }
        
        str = str + "}";
        return str;
    }

    /**
     * Removes all occurrences of an item from an ArrayList
     */
    public boolean removeAll(Object item){

        int rightEnd = this.length() - 1;
        boolean isRemoved = false;

        /**
         * Remove the item from the ArrayList on the first pass
         */
        for(int i = 0; i <= rightEnd; i++){

            if(this.items[i].equals(item)){
                isRemoved = true; //whether or not we removed an item
                items[i] = null;
            }
        }

        int nullCount = 0;

        for(int i = 0; i < this.length(); i++){

            /**
             * Count the number of nulls up to the point
             */
            if(this.items[i] == null){
                nullCount++;
            } else {

                /**
                 * If nulls have been encountered already, shift to
                 * the left with however many count of nulls
                 */
                if(nullCount > 0){
                    this.items[i - nullCount] = this.items[i];
                    this.items[i] = null;
                }
            }
        }

        length = length - nullCount;

        return isRemoved;
    }
    
    /*
     * iterator - returns an iterator for this list
     */
    public ListIterator iterator() {
        // still needs to be implemented
        return null;
    }

    public static void main(String [] args){

        String[] letters = {"a", "b", "c", "a", "c", "d", "e", "a"};
        ArrayList list1 = new ArrayList(letters);
        System.out.println(list1.removeAll("a"));
        System.out.println(list1);

        letters = new String [] {"b", "c", "c", "d", "e"};
        list1 = new ArrayList(letters);
        System.out.println(list1.removeAll("c"));
        System.out.println(list1);

        letters = new String [] {"b", "d", "e"};
        list1 = new ArrayList(letters);
        System.out.println(list1.removeAll("x"));
        System.out.println(list1);
    }
}
