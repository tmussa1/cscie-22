/* 
 * MagicSquare.java
 * 
 * Computer Science E-22
 * 
 * Modified by: Tofik Mussa, tmussa@g.harvard.edu
 */

import java.util.*;

public class MagicSquare {

    private int[][] values;

    private int [] colSums;
    private int [] rowSums;
    private int [] usedValues;
    private int usedValuesCount;
    private int order;
    private int possibleValues;

    public MagicSquare(int order) {
        this.values = new int[order][order];
        this.order = order;
        this.colSums = new int[order];
        this.rowSums = new int[order];
        this.usedValues = new int[(this.order * this.order)];
        this.usedValuesCount = 0;
        this.possibleValues = (int) Math.pow(this.order, 2);

        for(int i = 0; i < order; i++){
            this.colSums[i] = (int) (((Math.pow(order, 3)) + order) /2);
            this.rowSums[i] = (int) (((Math.pow(order, 3)) + order) /2);
        }
    }

    private boolean isPossible(int row, int col, int val){

        if(Arrays.asList(this.usedValues).contains(val)
                || this.values[row][col] != 0){
            return false;
        }

        if(col == order - 1 && this.rowSums[row] - val != 0) {
                return false;
        }

        if(row == order - 1 && this.colSums[col] - val != 0){
            return false;
        }

        if(this.colSums[col] - val > 0 && this.rowSums[row] - val > 0){
            return true;
        }

        return true;
    }

    private void placeValue(int row, int col, int val){
        this.values[row][col] = val;
        this.colSums[col] -= val;
        this.rowSums[row] -= val;
        this.usedValues[this.usedValuesCount] = val;
        this.usedValuesCount++;
    }

    private void removeValue(int row, int col, int val){
        this.values[row][col] = 0;
        this.colSums[col] += val;
        this.rowSums[row] += val;
        this.removeElementAndShiftArray(val);
    }

    private void removeElementAndShiftArray(int val) {

       int index = 0;

       for(int i = 0; i < this.usedValuesCount; i++) {
           if (this.usedValues[i] == val) {
               index = i;
           }
       }

       for(int k = index; k < this.usedValuesCount - 1; k++){
           this.usedValues[k] = this.usedValues[k + 1];
       }

       this.usedValuesCount--;
    }


    public boolean solveMagicSquare(int row, int colParam){

        if(row == this.order){
            this.display();
            return true;
        }

        if(colParam == this.order){
            return true;
        }

        for (int val = 1; val <= this.possibleValues; val++) {

            if (this.isPossible(row, colParam, val)){

                this.placeValue(row, colParam, val);

                if(solveMagicSquare(row,colParam + 1)){
                    solveMagicSquare(row + 1, 0);
                }

                this.removeValue(row, colParam, val);
            }
        }
        return false;
    }


    public boolean solve() {
        return solveMagicSquare(0, 0);
    }

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

    private void printRowSeparator() {
        for (int i = 0; i < order; i++) {
            System.out.print("-----");
        }
        System.out.println("-");
    }
    
    public static void main(String[] args) {

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