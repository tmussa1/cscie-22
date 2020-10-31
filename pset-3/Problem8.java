/*
 * Problem8.java
 */

public class Problem8 {

    /*
    Checks if a given String is a palindrome
     */
    public static boolean isPal(String str){

        /*
        An empty String or a string of length 1 is always a palindrome
        The String can't be null
         */
        if(str == null){
            throw new IllegalArgumentException("Empty parameter is not allowed");
        } else if(str.equals("") || str.length() == 1){
            return true;
        }

        Stack<Character> stack = new LLStack<>();
        Queue<Character> queue = new LLQueue<>();

        /**
         * Ignore anything but lowercase and uppercase letters when adding to the stack and the queue
         */
        for(int i = 0; i < str.length(); i++){

            if((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
                    || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')){

                stack.push(Character.toLowerCase(str.charAt(i)));
                queue.insert(Character.toLowerCase(str.charAt(i)));
            }
        }

        /*
        Popping the stack is LIFO and popping the queue is FIFO
        Top of the stack is what was the originally the last added element
        Front of the stack is the first added element
        If those two are not equal when removing an item one by one, we can be sure that we don't have a palindrome
         */
        while(!stack.isEmpty() && !queue.isEmpty()){

            if(stack.pop() != queue.remove()){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        /*
         * We encourage you to add more unit tests below that test a variety
         * of different cases, although doing so is not required.
         */
    }
}