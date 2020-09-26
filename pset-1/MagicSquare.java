/* 
 * MagicSquare.java
 * 
 * Computer Science E-22
 * 
 * Modified by: <your name>, <your e-mail address>
 */

import java.util.*;

public class MagicSquare {
    // the current contents of the cells of the puzzle values[r][c]
    // gives the value in the cell at row r, column c
    private int[][] values;
    
    // the order (i.e., the dimension) of the puzzle
    private int order;
    
    /*
     * Creates a MagicSquare object for a puzzle with the specified
     * dimension/order.
     */
    public MagicSquare(int order) {
        this.values = new int[order][order];
        this.order = order;
        
        // Add code to this constructor as needed to initialize
        // the fields that you add to the object.
    }
    
    /*
     * This method should call the separate recursive-backtracking method
     * that you will write, passing it the appropriate initial parameter(s).
     * It should return true if a solution is found, and false otherwise.
     */
    public boolean solve() {
        // Replace the line below with your implementation of this method.
        // REMEMBER: The recursive-backtracking code should NOT go here.
        // See the comments above.
        return false;
    }
    
    /*
     * Displays the current state of the puzzle.
     * You should not change this method.
     */
    public void display() {
        for (int r = 0; r < order; r++) {
            printRowSeparator();
            for (int c = 0; c < order; c++) {
                System.out.print("|");
                if (values[r][c] == 0) {
                    System.out.print("   ");
                } else {
                    if (values[r][c] < 10) {
                        System.out.print(" ");
                    }
                    System.out.print(" " + values[r][c] + " ");
                }
            }
            System.out.println("|");
        }
        printRowSeparator();
    }
    
    // A private helper method used by display()
    // to print a line separating two rows of the puzzle.
    private void printRowSeparator() {
        for (int i = 0; i < order; i++) {
            System.out.print("-----");
        }
        System.out.println("-");
    }
    
    public static void main(String[] args) {
        /*
         * You should NOT change any code in this method
         */
        
        Scanner console = new Scanner(System.in);
        System.out.print("What order Magic Square? ");
        int order = console.nextInt();
        
        MagicSquare puzzle = new MagicSquare(order);
        if (puzzle.solve()) {
            System.out.println("Here's the solution:");
            puzzle.display();
        } else {
            System.out.println("No solution found.");
        }
    }
}