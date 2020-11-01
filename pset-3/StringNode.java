/*
 * StringNode.java
 *
 * Computer Science E-22, Harvard University
 * 
 * modified by:
 *   name:
 *   email:
 */

import java.io.*;
import java.util.*;

/**
 * A class for representing a string using a linked list.
 * Each character of the string is stored in a separate node.  
 *
 * This class represents one node of the linked list.  The string as a
 * whole is represented by storing a reference to the first node in
 * the linked list. The methods in this class are static methods that
 * take a reference to a string linked-list as a parameter. This
 * approach allows us to use recursion to write many of the methods,
 * and it also allows the methods to handle empty strings, which are 
 * represented using a value of null.
 */
public class StringNode {
    private char ch;
    private StringNode next;

    /**
     * Constructor
     */
    public StringNode(char c, StringNode n) {
        this.ch = c;
        this.next = n;
    }

    /**
     * getNode - private helper method that returns a reference to
     * node i in the given linked-list string.  If the string is too
     * short or if the user passes in a negative i, the method returns null.
     */
    private static StringNode getNode(StringNode str, int i) {
        if (i < 0 || str == null) {    // base case 1: not found
            return null;
        } else if (i == 0) {           // base case 2: just found
            return str;
        } else {
            return getNode(str.next, i - 1);
        }
    }

    /*****************************************************
     * Public methods (in alphabetical order)
     *****************************************************/

    /**
     * charAt - returns the character at the specified index of the
     * specified linked-list string, where the first character has
     * index 0.  If the index i is < 0 or i > length - 1, the method
     * will end up throwing an IllegalArgumentException.
     */
    public static char charAt(StringNode str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("the string is empty");
        } 
          
        StringNode node = getNode(str, i);

        if (node != null) {
            return node.ch;     
        } else {
            throw new IllegalArgumentException("invalid index: " + i);
        }
    }
    
    /**
     * compareAlpha - compares two linked-list strings to determine
     * which comes first alphabetically (i.e., according  to the ordering 
     * used for words in a dictionary). 
     * 
     * It returns:
     *    1 if str1 comes first alphabetically
     *    2 if str2 comes first alphabetically
     *    0 if str1 and str2 represent the same string
     * 
     * The empty string comes before any non-empty string, 
     * and the prefix of a string comes before the string
     * itself (e.g., "be" comes before "become").
     */
    public static int compareAlpha(StringNode str1, StringNode str2) {

        while(str1 != null || str2 != null){

            /**
             * Same comparisons as recursive implementation
             */
            if(str1 == null){
                return 1;
            } else if(str2 == null){
                return 2;
            } else if(str1.ch < str2.ch){
                return 1;
            } else if(str2.ch < str1.ch){
                return 2;
            }

            str1 = str1.next;
            str2 = str2.next;
        }

        //They are equal if there is no early returns
        return 0;
    }

    /**
     * convert - converts a standard Java String object to a linked-list
     * string and returns a reference to the linked-list string
     */
    public static StringNode convert(String s) {
        if (s.length() == 0) {
            return null;
        }

        StringNode firstNode = new StringNode(s.charAt(0), null);
        StringNode prevNode = firstNode;
        StringNode nextNode;

        for (int i = 1; i < s.length(); i++) {
            nextNode = new StringNode(s.charAt(i), null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }

        return firstNode;
    }
    
    /**
     * copy - returns a copy of the given linked-list string
     */
    public static StringNode copy(StringNode str) {

        StringNode node = null, trav = null;

        /**
         * Null checks and initialize
         */
        if(str != null){

            /**
             * For more than one elements
             */
            if(str.next != null){
                node = new StringNode(str.ch, str.next);
                str = str.next;
                trav = node;
            } else {
                /**
                 * For a single element
                 */
                node = new StringNode(str.ch, null);
                return node;
            }
        }

        /**
         * Traverse the list and copy
         */
        while(str != null){

            node.next = new StringNode(str.ch, str.next);
            node = node.next;
            str = str.next;
        }

        return trav;
    }

    /**
     * deleteChar - deletes character i in the given linked-list string and
     * returns a reference to the resulting linked-list string
     */
    public static StringNode deleteChar(StringNode str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("string is empty");
        } else if (i < 0) { 
            throw new IllegalArgumentException("invalid x: " + i);
        } else if (i == 0) { 
            str = str.next;
        } else {
            StringNode prevNode = getNode(str, i-1);
            if (prevNode != null && prevNode.next != null) {
                prevNode.next = prevNode.next.next;
            } else {
                throw new IllegalArgumentException("invalid index: " + i);
            }
        }

        return str;
    }
    
    /** 
     * insertBefore - inserts the specified new character (newChar) 
     * before the first occurrence of the specified character (afterChar)
     * in the linked-list string to which str refers.
     * If beforeChar is not in the string, the method adds the character
     * to the end of the string. Returns a reference to the first node
     * in the modified linked list, because the first node can change.
     */
    public static StringNode insertBefore(StringNode str, char newChar, 
                                         char beforeChar) {

        StringNode newNode = new StringNode(newChar, null);

        /**
         * If the node is empty or if inserting before the first element
         */
        if(str == null){
            return newNode;
        } else if(str.ch == beforeChar){
           newNode.next = str;
           return newNode;
        }

        insertBefore(str.next, newChar, beforeChar);

        /**
         * Insert before the element that is found or append to the end
         */
        if(str.next != null && str.next.ch == beforeChar){
            newNode.next = str.next;
            str.next = newNode;
        } else if(str.next == null){
            str.next = newNode;
        }

        return str;
    }

    /**
     * insertChar - inserts the character ch before the character
     * currently in position i of the specified linked-list string.
     * Returns a reference to the resulting linked-list string.
     */
    public static StringNode insertChar(StringNode str, int i, char ch) {
        StringNode newNode, prevNode;

        if (i < 0) { 
            throw new IllegalArgumentException("invalid index: " + i);
        } else if (i == 0) {
            newNode = new StringNode(ch, str);
            str = newNode;
        } else {
            prevNode = getNode(str, i - 1);
            if (prevNode != null) {
                newNode = new StringNode(ch, prevNode.next);
                prevNode.next = newNode;
            } else {
                throw new IllegalArgumentException("invalid index: " + i);
            }
        }

        return str;
    }

    /**
     * insertSorted - inserts character ch in the correct position
     * in a sorted list of characters (i.e., a sorted linked-list string)
     * and returns a reference to the resulting list.
     */
    public static StringNode insertSorted(StringNode str, char ch) {
        StringNode newNode, trail, trav;

        // Find where the character belongs.
        trail = null;
        trav = str;
        while (trav != null && trav.ch < ch) {
            trail = trav;
            trav = trav.next;
        }

        // Create and insert the new node.
        newNode = new StringNode(ch, trav);
        if (trail == null) {
            // We never advanced the prev and trav references, so
            // newNode goes at the start of the list.
            str = newNode;
        } else { 
            trail.next = newNode;
        }
            
        return str;
    }

    /**
     * length - recursively determines the number of characters in the
     * linked-list string to which str refers
     */
    public static int length(StringNode str) {
        if (str == null) {
            return  0;
        } else {
            return 1 + length(str.next);
        }
    }

    /**
     * numOccur - find the number of occurrences of the character
     * ch in the linked list to which str refers
     * Iterative version
     */
    public static int numOccur(StringNode str, char ch) {

        int count = 0;

        /**
         * Count occurences
         */
        while(str != null){

            if(str.ch == ch){
                count += 1;
            }
            str = str.next;
        }

        return count;
    }

    /**
     * print - recursively writes the specified linked-list string to System.out
     */
    public static void print(StringNode str) {
        if (str == null) {
            return;
        } else {
            System.out.print(str.ch);
            print(str.next);
        }
    }

    /**
     * read - reads a string from an input stream and returns a
     * reference to a linked list containing the characters in the string
     */
    public static StringNode read(InputStream in) throws IOException { 
        char ch = (char)in.read();

        if (ch == '\n') {    // the string ends when we hit a newline character
            return null;         
        } else {
            StringNode restOfString = read(in);
            StringNode first = new StringNode(ch, restOfString);
            return first;
        }
    }
    
    /*
     * toString - creates and returns the Java string that
     * the current StringNode represents.  Note that this
     * method -- unlike the others -- is a non-static method.
     * Thus, it will not work for empty strings, since they
     * are represented by a value of null, and we can't use
     * null to invoke this method.
     */
    public String toString() {
        String str = "";
        
        StringNode trav = this;   // start trav on the current node    
        while (trav != null) {
            str = str + trav.ch;
            trav = trav.next;
        }
         
        return str;
    }
    
    /**
     * toUpperCase - converts all of the characters in the specified
     * linked-list string to upper case.  Modifies the list itself,
     * rather than creating a new list.
     * Recursive version
     */
    public static void toUpperCase(StringNode str) {

        if(str == null){
            return;
        }

        /**
         * Recursively change to uppercase
         */
        str.ch = Character.toUpperCase(str.ch);

        toUpperCase(str.next);
    }

    public static StringNode reverseInPlace(StringNode str){

        if(str == null){
            return str;
        }

        reverseInPlace(str.next);

        str.next = str;
        str = str.next;

        return str;
    }

    public static int lastIndexOf(StringNode str, char ch){

        if(str == null){
           return 0;
        }

        int rest = 1 + lastIndexOf(str.next, ch);

        System.out.println("rest " + rest);

        if(str.ch == ch){
            return length(str) - rest;
        }

        return rest;
    }
              
    public static void main(String[] args) throws IOException {
//        Scanner in = new Scanner(System.in);
//
//        // toUpperCase
//        StringNode str = StringNode.convert("fine");
//        System.out.print("Here's a string: ");
//        System.out.println(str);    // implicit toString call
//        System.out.print("Here it is in upper-case letters: ");
//        StringNode.toUpperCase(str);
//        System.out.println(str);
//
//        // numOccur
//        System.out.print("Enter a string: ");
//        String s = in.nextLine();
//        StringNode str1 = StringNode.convert(s);
//        System.out.print("\nWhat character to count? ");
//        char ch = in.nextLine().charAt(0);
//        int count = StringNode.numOccur(str1, ch);
//        System.out.println("There are " + count + " occurrences of " + ch);
//
//        // copy and deleteChar
//        int n = -1;
//        while (n < 0) {
//            System.out.print("\nWhat # character to delete (>= 0)? ");
//            n = in.nextInt();
//            in.nextLine();
//        }
//        StringNode copyStr1 = StringNode.copy(str1);
//        try {
//            str1 = StringNode.deleteChar(str1, n);
//            StringNode.print(str1);
//        } catch (IllegalArgumentException e) {
//            System.out.println("The string is too short.");
//        }
//
//        // The copy should be unchanged!
//        str1 = copyStr1;
//        System.out.print("\nReturning to the unchanged copy: ");
//        System.out.println(copyStr1);
//
//        // compareAlpha
//        System.out.print("\nType another string: ");
//        s = in.nextLine();
//        StringNode str2 = StringNode.convert(s);
//        System.out.print("\ncomparing " + str1 + " and " + str2 + " gives: ");
//        System.out.println(StringNode.compareAlpha(str1, str2));
//
//        // insertBefore
//        System.out.print("What character to insert? ");
//        ch = in.nextLine().charAt(0);
//        System.out.print("\nWhat character to insert before? ");
//        char before = in.nextLine().charAt(0);
//        str1 = StringNode.insertBefore(str1, ch, before);
//        System.out.println(str1);
//
//        System.out.println("Reversing");
//        //str1 = StringNode.reverseInPlace(str1);
//        System.out.println(str1);

        StringNode s4 = StringNode.convert("singing");
        System.out.println(StringNode.lastIndexOf(s4, 'n'));
        System.out.println(StringNode.lastIndexOf(s4, 'i'));
    }
}
