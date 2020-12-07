public class Heap {

    public static boolean isHeap(int[] arr) {
        if (arr == null) {
            return false;
        }

        return isHeapTree(arr, 0);
    }

    private static boolean isHeapTree(int[] arr, int i) {

        Queue<Integer> queue = new LLQueue<>();
        queue.insert(i);

        /**
         * The algorithm is similar to a level order traversal
         */
        while(!queue.isEmpty()){

            Integer currentIndex = queue.remove();

            int leftChildIndex = (2 * currentIndex) + 1, rightChildIndex = (2 * currentIndex) + 2;

            /**
             * Break out of the loop if either of the children are bigger than the parent
             */
            if((leftChildIndex < arr.length && arr[leftChildIndex] > arr[currentIndex])
                    || (rightChildIndex < arr.length && arr[rightChildIndex] > arr[currentIndex])){
                return false;
            }

            /**
             * Insert the children indexes if they don't overflow
             */
            if(leftChildIndex < arr.length){
                queue.insert(leftChildIndex);
            }

            if(rightChildIndex < arr.length){
                queue.insert(rightChildIndex);
            }
        }

        /**
         * If we didn't break early, we return true
         */
        return true;
    }

    public static void main(String [] args){

        int[] arr = {48, 25, 16, 10, 18, 36, 47, 5, 7, 9, 10};

        System.out.println(isHeapTree(arr, 1));

        System.out.println(isHeapTree(arr, 2));

        System.out.println(isHeapTree(arr, 0));
    }
}
