import java.util.Arrays;

public class Problem8 {

    public static int [] findIntersect(int [] a1 , int [] a2){

        int size = Math.min(a1.length, a2.length);
        int [] result = new int [size];
        int k = 0;

        Sort.mergeSort(a1);
        Sort.mergeSort(a2);

        for(int i = 0, j = 0; i < a1.length && j < a2.length; ){

            if(a1[i] < a2[j]){
                i++;
            } else if(a1[i] == a2[j]){

                if(k > 0){

                    if(result[k -1] != a1[i]) {
                        result[k] = a1[i];
                        i++; j++; k++;
                    }
                } else {
                    result[k] = a1[i];
                    i++; j++; k++;
                }
            } else {
                j++;
            }
        }

        return result;
    }
    public static void main(String [] args){
        System.out.println("____________________________________________");
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 2, 2, 2, 3, 3, 2, 10};
        int[] result = Problem8.findIntersect(a1, a2);
        System.out.println(Arrays.toString(result));
        System.out.println("____________________________________________");
        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        result = Problem8.findIntersect(a3, a4);
        System.out.println(Arrays.toString(result));
    }
}
