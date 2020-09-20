/* 
 * ArrayBag.java
 * 
 * Computer Science E-22
 *
 * Modified by: <your name>, <your e-mail address>
 */

import java.util.*;

/**
 * An implementation of the Bag ADT using an array.
 */
public class ArrayBag implements Bag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }
    
    /**
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /** 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * containsAll - does this ArrayBag contain all of the items in
     * otherBag?  Returns false if otherBag is null or empty. 
     */
    public boolean containsAll(Bag otherBag) {
        if (otherBag == null || otherBag.numItems() == 0) {
            return false;
        }
        
        Object[] otherItems = otherBag.toArray();
        for (int i = 0; i < otherItems.length; i++) {
            if (! this.contains(otherItems[i])) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }

    @Override
    public int capacity() {
        return this.items.length;
    }

    @Override
    public boolean isFull() {
        return numItems() == capacity();
    }

    @Override
    public void increaseCapacity(int amount) {

        if(amount < 0){
            throw new IllegalArgumentException("Amount can't be negative");
        } else if(amount == 0){
            return;
        }

        Object [] copiedArray = new Object[capacity() + amount];

        for(int i = 0; i < numItems(); i++){
            copiedArray[i] = this.items[i];
        }

        this.items = copiedArray;
    }

    @Override
    public boolean removeItems(Bag other) {

        if(other == null){
            throw new IllegalArgumentException("Item to remove is empty");
        } else if(other.numItems() == 0){
            return false;
        }

        Object [] otherItems = other.toArray();
        boolean isRemoved = false;

        for(int i  = 0; i < other.numItems(); i++){

            if(this.contains(otherItems[i])){

                System.out.println("Other " + otherItems[i]);

                this.remove(otherItems[i]);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public Bag unionWith(Bag other) {

        if(other == null){
            throw new IllegalArgumentException("The parameter can not be empty");
        } else if(other.numItems() == 0 && this.numItems() == 0){
            return new ArrayBag();
        }

        ArrayBag result = new ArrayBag(other.numItems() + this.numItems());
        Object [] otherArray = other.toArray();
        Object [] thisArray = this.toArray();

        for(int i = 0, k = 0; i < other.numItems() || k < this.numItems; i++, k++){

            if(i < other.numItems() && !result.contains(otherArray[i])){
                result.add(otherArray[i]);
            }

            if(k < this.numItems && !result.contains(thisArray[k])){
                result.add(thisArray[k]);
            }
        }

        return result;
    }

    /**
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("size of bag 1: ");
        int size = scan.nextInt();
        ArrayBag bag1 = new ArrayBag(size);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();

        System.out.println("Increase Capacity By: ");
        int amount = scan.nextInt();

        bag1.increaseCapacity(amount);

        System.out.println("New size " + bag1.capacity());

        Bag other = new ArrayBag();

        System.out.println("Other : ");

        for (int i = 1; i < 6; i += 2) {
            System.out.println(i);
            other.add(i + "");
        }
        bag1.removeItems(other);

        System.out.println("Removing other :");

        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }

        System.out.println("Union with : ");

        Object [] result = bag1.unionWith(other).toArray();

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
        Object[] res = bag1.toArray();
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
    }
}
