import javax.management.remote.rmi._RMIConnection_Stub;

public class Part1 {

    private static class IntNode {

        private int val;
        private IntNode next;

        public IntNode(int val, IntNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static class DNode {
        private char ch;
        private DNode next;
        private DNode prev;

        public DNode(char ch, DNode next, DNode prev) {
            this.ch = ch;
            this.next = next;
            this.prev = prev;
        }
    }

    static DNode initNexts(DNode last){

        while(last.prev != null){
            last.prev.next = last;
            last = last.prev;
        }

        return last;
    }

    static <T> boolean findItemInStack(Stack<T> stack, T item){

        boolean itemFound = false;

        Queue<T> queue = null;

        while(!stack.isEmpty()){

            T itemFromStack = stack.pop();
            queue.insert(itemFromStack);

            if(itemFromStack.equals(item)){
                itemFound = true;
                break;
            }
        }

        while(!queue.isEmpty()){
            stack.push(queue.remove());
        }

        return itemFound;
    }

    static void printOddsRecursive(IntNode first){

        if(first == null){
            return;
        }

        if(first.val % 2 == 1){
            System.out.println(first.val);
        }

        printOddsRecursive(first.next);
    }

    public static void printOddsIterative(IntNode first) {

        if(first == null){
            return;
        }

        if(first.val % 2 == 1){
            System.out.println(first.val);
        }

        first = first.next;
    }

    public static LLList intersectNaive(LLList list1, LLList list2) {
        LLList inters = new LLList();

        for (int i = 0; i < list1.length(); i++) {
            Object item1 = list1.getItem(i);
            for (int j = 0; j < list2.length(); j++) {
                Object item2 = list2.getItem(j);
                if (item2.equals(item1)) {
                    inters.addItem(item2, inters.length());
                    break;   // move onto the next item from list1
                }
            }
        }

        return inters;
    }

    public static LLList intersect(LLList list1, LLList list2) {

        LLList inters = new LLList();
        ListIterator iterator = list1.iterator();
        ListIterator iterator2 = list2.iterator();

        while(iterator.hasNext()){

            Object item1 = iterator.next();

            while(iterator2.hasNext()){

                if(item1.equals(iterator2.next())){
                    inters.addItem(item1, inters.length());
                    break;
                }
            }
        }
        return inters;
    }



    public static void main(String [] args){

        Part1.IntNode node = new Part1.IntNode(1, null);

        for(int i = 2; i < 7; i++){
            Part1.IntNode newNode = new Part1.IntNode(i, node);
            node = newNode;
        }
        printOddsRecursive(node);

        LLList list1 = new LLList(new Integer[]{-1 , 4 , 7, 8, 5});
        LLList list2 = new LLList(new Integer[]{-1 , 6 , 9, 2, 4, 4});
        System.out.println(intersect(list1, list2).toString());

        DNode a = new DNode('a', null, null);
        DNode b = new DNode('b', null, a);
        DNode c = new DNode('c', null, b);
        DNode d = new DNode('d', null, c);
        DNode lastNode = new DNode('e', null, d);

        DNode dNode = initNexts(lastNode);

        while(dNode != null){
            System.out.println(dNode.ch);
            dNode = dNode.next;
        }

    }
}
