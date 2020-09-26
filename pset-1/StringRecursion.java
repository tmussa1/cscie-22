

public class StringRecursion {

    public static void printLetters(String str){

        if(str == null || str.equals("")){
            return;
        }

        if(str.length() == 1){
            System.out.print(str.charAt(0));
            return;
        } else {
            System.out.print(str.charAt(0) + ", ");
        }

        printLetters(str.substring(1));
    }

    public static String replace(String str, char oldChar, char newChar){

        if(str == null || str.equals("")){
            return str;
        }

        String rest = replace(str.substring(1), oldChar, newChar);

        if(str.charAt(0) == oldChar){
            return newChar + rest;
        } else {
            return str.charAt(0) + rest;
        }
    }

    public static int indexOf(char ch, String str){

        if(str == null || str.equals("")){
            return -1;
        }
        int index = indexOf(ch, str.substring(0, str.length() - 1));

        if(str.charAt((str.length() - 1)) == ch && index == -1){
            return str.length() - 1;
        }
        return index;
    }

    public static void main(String [] args) {
//        printLetters("Rabbit");
//        printLetters("I like to recurse!");
//        System.out.println(replace("base case", 'e', 'y'));
//        System.out.println(replace("base case", 'r', 'y'));
//        System.out.println(indexOf('b', "Rabbit"));
//        System.out.println(indexOf('P', "Rabbit"));
    }
}
