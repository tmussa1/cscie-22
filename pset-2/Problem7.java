

public class Problem7 {

    public static void pairSums(int k, int[] arr){

    }

    public static void pairSumsImproved(int k, int[] arr){

        Sort.mergeSort(arr);

        for(int i = 0, j = arr.length - 1; i < arr.length && j >= 0 && j < arr.length; ){

            if(arr[i] + arr[j] > k){
                j--;
            } else if(arr[i] + arr[j] == k && i < j){
                System.out.println(arr[i] + " + " + arr[j] + " = " + k);
                i++;
            } else {
                i++;
            }
        }

        //The algorithm runs in O(nlogn + n) which is O(nlogn). A single loop is sliding from
        //left to right and right to left simultaneously thereby making comparisons and
        //only advancing the appropriate index
    }

    public static void main(String [] args){
        System.out.println("____________________________________________");
        Problem7.pairSumsImproved(12, new int []{10, 4, 7, 7, 8, 5, 15});
        System.out.println("____________________________________________");
        Problem7.pairSumsImproved(12, new int []{11, 14, 17, 7, 18, 6, 15});
        System.out.println("____________________________________________");
        Problem7.pairSumsImproved(12, new int []{11, 14, 17, 6, 18, 6, 15});
        System.out.println("____________________________________________");
    }
}