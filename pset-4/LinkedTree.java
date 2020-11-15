/*
 * LinkedTree.java
 *
 * Computer Science E-22
 *
 * Modifications and additions by:
 *     name:
 *     username:
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        private Node parent;     // reference to the parent. NOT YET MAINTAINED!
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }

    public LinkedTree(int[] keys, Object[] dataItems){

        //Sort the array
        SortHelper.quickSort(keys, dataItems);

        //helper method to insert data items
        insertBalanced(keys, dataItems);
    }

    private void insertBalanced(int[] keys, Object[] dataItems) {
        //Bootstrapping a recursive method
        insertBalancedRecursive(keys, dataItems, 0, dataItems.length);
    }

    private void insertBalancedRecursive(int[] keys, Object[] dataItems, int first, int last) {
        int medium = ((last - first) / 2) + first;

        /**
         * Similar technique to quick sort,
         * insert the middle element then recursively perform this on the
         * left subtree
         */
        if(first < medium){
            insert(keys[medium], dataItems[medium]);
            insertBalancedRecursive(keys, dataItems, first, medium);
        }

        /*** insert the middle element then recursively perform this on the
         * right subtree
         */
        if(last > medium + 1){
            insert(keys[medium], dataItems[medium]);
            insertBalancedRecursive(keys, dataItems, medium, last);
        }

        /**
         * Special case where the ends meet,
         * insert the middle element
         */
        if(last == medium || first == medium){
            insert(keys[medium], dataItems[medium]);
        }
    }

    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
            root.parent = null;
        } else if (key < parent.key) {
            parent.left = newNode;
            parent.left.parent = parent;
        } else {
            parent.right = newNode;
            parent.right.parent = parent;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
                root.parent = null;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
                parent.left.parent = parent;
            } else {
                parent.right = toDeleteChild;
                parent.right.parent = parent;
            }
        }
    }
    
    /* Returns a preorder iterator for this tree. */
    public LinkedTreeIterator preorderIterator() {
        return new PreorderIterator();
    }

    /* Returns an inorder iterator for this tree. */
    public LinkedTreeIterator inorderIterator() {
        return new InorderIterator();
    }

    /* 
     * inner class for a preorder iterator 
     * IMPORTANT: You will not be able to actually use objects from this class
     * to perform a preorder iteration until you have modified the LinkedTree
     * methods so that they maintain the parent fields in the Nodes.
     */
    private class PreorderIterator implements LinkedTreeIterator {
        private Node nextNode;
        
        private PreorderIterator() {
            // The traversal starts with the root node.
            nextNode = root;
        }
        
        public boolean hasNext() {
            return (nextNode != null);
        }
        
        public int next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            
            // Store a copy of the key to be returned.
            int key = nextNode.key;
            
            // Advance nextNode.
            if (nextNode.left != null) {
                nextNode = nextNode.left;
            } else if (nextNode.right != null) {
                nextNode = nextNode.right;
            } else {
                // We've just visited a leaf node.
                // Go back up the tree until we find a node
                // with a right child that we haven't seen yet.
                Node parent = nextNode.parent;
                Node child = nextNode;
                while (parent != null &&
                       (parent.right == child || parent.right == null)) {
                    child = parent;
                    parent = parent.parent;
                }
                
                if (parent == null) {
                    nextNode = null;  // the traversal is complete
                } else {
                    nextNode = parent.right;
                }
            }
            
            return key;
        }
    }

    /*
    Inorder iterator
     */
    private class InorderIterator implements LinkedTreeIterator{

        private Node nextNode;

        private InorderIterator() {

            Node trav = root;

            /*
             * Find the left most child of the tree for root
             */
            while(trav != null && trav.left != null){
                trav = trav.left;
            }

            nextNode = trav;
        }

        /*
         * whether we have left to traverse
         */
        @Override
        public boolean hasNext() {
            return (nextNode != null);
        }

        @Override
        public int next() {

            /*
             * Next node is null. Never happens if we check hasNext first
             */
            if (nextNode == null) {
                throw new NoSuchElementException();
            }

            //Save the key we are returning
            int key = nextNode.key;

            Node trav = nextNode;

            //If we have a right child, move to the left most child of the right child
            if(trav.right != null){

                trav = trav.right;

                while(trav != null && trav.left != null){
                    trav = trav.left;
                }

                nextNode = trav;

            } else {

                //If we don't have a right child, move up the parents to find nextNode
                if(trav.parent != null) {

                    if (trav.parent.right == trav) {

                        //If the right child of our parent is the same as where we are, we
                        //have already seen it so skip
                        while (trav != null && trav.parent != null && trav.parent.right == trav) {
                            trav = trav.parent;
                        }

                        if(trav == null || trav.parent == null){
                            nextNode = null;
                        } else {
                            nextNode = trav.parent;
                        }

                    } else {
                        nextNode = trav.parent;
                    }
                    //We exceeded the tree
                } else {
                    nextNode = null;
                }
            }


            return key;
        }
    }

    /*
     * "wrapper method" for the recursive depthInTree() method
     * from PS 4, Problem 2
     */
    public int depth(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }
        
        return depthInTree(key, root, 0);
    }
    
    /*
     * original version of the recursive depthInTree() method  
     * from PS 4, Problem 2. You will write a more efficient version
     * of this method.
     */
    private static int depthInTree(int key, Node root, int depth) {
        if (root == null) {
            return -1;
        }
        if(key == root.key) {
            return depth;     // found
        } else if(key < root.key){
            return depthInTree(key, root.left, depth + 1);
        } else if(key > root.key){
            return depthInTree(key, root.right, depth + 1);
        }

        return -1;    // not found in either subtree
    }

    /**
     * Determine the depth of a node in a tree recursively
     * @param key
     * @return
     */
    public int depthIter(int key){

        /*
        If the root is empty
         */
        if(root == null){
            return -1;
        }

        int depth = 0;
        Node trav = root;

        while(key != trav.key){

            /**
             * Go left or right based on the key's value. Break out of the loop if either is null
             */
            if(key < trav.key){

                if(trav.left == null){
                    break;
                }

                trav = trav.left;
                depth += 1;
            } else if(key > trav.key){

                if(trav.right == null){
                    break;
                }

                trav = trav.right;
                depth += 1;
            }

            /**
             * Break out of the loop if trav is null
             */
            if(trav == null){
                break;
            }
        }

        /**
         * Return depth if key is found
         */
        if(key == trav.key){
            return depth;
        }

        return -1; //key is not found
    }

    /**
     * Wrapper method for summing even keys
     * @return
     */
    public int sumEvens(){
        return sumEvensInTree(root);
    }

    private static int sumEvensInTree(Node root){

        /**
         * Return 0 when root of the tree or subtree is null.
         */
        if(root == null){
            return 0;
        }

        /**
         * Traverse left and write accumlating even sums
         */
        int sum = sumEvensInTree(root.left) + sumEvensInTree(root.right);

        /**
         * Add it to sum on the way back if key is even
         */
        if(root.key % 2 == 0){
            sum += root.key;
        }

        return sum;
    }

    /**
     * Delete the maximum element in the tree
     * @return
     */
    public int deleteMax(){

        /**
         * If the tree is empty
         */
        if(root == null){
            return -1;
        }

        int maxKey = 0;
        Node trail = null, trav = root;

        /**
         * Go as far to the right as possible since
         * that is where we will find the maximum
         * The maximum element doesn't have a right child
         */
        while(trav.right != null){
            trail = trav;
            trav = trav.right;
        }

        /**
         * Set the previous element right child to
         * the left child of what we are deleting
         * if there is a left child
         */
        if(trav.left != null){
            trail.right = trav.left;
            maxKey = trav.key;
            trav.left = null;
            /**
             * If it doesn't have a left child, set the reference
             * to the right child of the previous element to null
             */
        } else {
            maxKey = trav.key;
            trail.right = null;
        }

        return maxKey;
    }
    
    public static void main(String[] args) {

        System.out.println("--- Testing depth() from Problem 2 part 2 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("(0) Testing on empty tree from Problem 2 part 2 empty tree, depth of 11");
            System.out.println("empty tree: " + tree.depthIter(11));
            System.out.println();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            int results = tree.depth(56);
            System.out.println("(1) Testing on tree from Problem 2 part 2, depth of 56");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 2);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add at least two unit tests for each method from Problem 7.
         * Test a variety of different cases. 
         * Follow the same format that we have used above.
         * 
         * IMPORTANT: Any tests for your inorder iterator from Problem 8
         * should go BEFORE your tests of the deleteMax method.
         */

        System.out.println("--- Testing depthIter() from Problem 7 part 1 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("(0) Testing on empty tree from Problem 7 part 1 empty tree, depth of 11");
            System.out.println("empty tree: " + tree.depthIter(11));
            System.out.println();
            int[] keys = {21, 44, 75, 22, 9, 91, 66, 77, 11};
            tree.insertKeys(keys);
            System.out.println("(1) Testing on tree from Problem 7 part 1, depth of 21");
            System.out.println("actual results:");
            System.out.println(tree.depthIter(21));
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depthIter(21) == 0);
            System.out.println();
            System.out.println("(2) Testing on tree from Problem 7 part 1, depth of 77");
            System.out.println("actual results:");
            System.out.println(tree.depthIter(77));
            System.out.println("expected results:");
            System.out.println(4);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depthIter(77) == 4);
            System.out.println();
            System.out.println("(3) Testing on tree from Problem 7 part 1, depth of 9");
            System.out.println("actual results:");
            System.out.println(tree.depthIter(9));
            System.out.println("expected results:");
            System.out.println(1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depthIter(9) == 1);
            System.out.println();
            System.out.println("(3) Testing on tree from Problem 7 part 1 with key that doesn't exist, depth of 97");
            System.out.println("actual results:");
            System.out.println(tree.depthIter(97));
            System.out.println("expected results:");
            System.out.println(-1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depthIter(97) == -1);
            System.out.println();
        } catch(Exception ex){
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + ex);
        }

        System.out.println("--- Testing sumEven() from Problem 7 part 2 ---");
        System.out.println();

        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("(0) Testing on empty tree from Problem 7 part 2 with empty tree");
            System.out.println("empty tree: " + tree.sumEvens());
            System.out.println();
            int[] keys = {12, 1, 4, 8, 5, 7};
            tree.insertKeys(keys);
            System.out.println("(1) Testing on tree from Problem 7 part 2 with evens {12, 4, 8}");
            System.out.println("actual results:");
            System.out.println(tree.sumEvens());
            System.out.println("expected results:");
            System.out.println(24);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.sumEvens() == 24);
            System.out.println();

            LinkedTree tree2 = new LinkedTree();
            int keys2 [] = {1, 5, 2, 4, 7};
            tree2.insertKeys(keys2);
            System.out.println("(2) Testing on tree from Problem 7 part 2 with evens {2, 4}");
            System.out.println("actual results:");
            System.out.println(tree2.sumEvens());
            System.out.println("expected results:");
            System.out.println(6);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree2.sumEvens() == 6);
            System.out.println();

            LinkedTree tree3 = new LinkedTree();
            int keys3 [] = {1, 5, 3, 9, 7};
            tree2.insertKeys(keys2);
            System.out.println("(3) Testing on tree from Problem 7 part 2 with no evens");
            System.out.println("actual results:");
            System.out.println(tree3.sumEvens());
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree3.sumEvens() == 0);
            System.out.println();
        } catch(Exception ex){
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + ex);
        }

        System.out.println("--- Testing inorderIterator() from Problem 8 ---");
        System.out.println();
        try{
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            int [] inorder = new int[keys.length];
            int count = 0;
            tree.insertKeys(keys);
            LinkedTreeIterator iter = tree.inorderIterator();
            while (iter.hasNext()) {
                int key = iter.next();
                inorder[count] = key;
                count++;
                System.out.println(key);
            }
            System.out.println("(0) Testing part 8 the first element to be traversed");
            System.out.println("actual results:");
            System.out.println(inorder[0]);
            System.out.println("expected results:");
            System.out.println(13);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(inorder[0] == 13);
            System.out.println();
            System.out.println("(1) Testing part 8 the fourth element to be traversed");
            System.out.println("actual results:");
            System.out.println(inorder[3]);
            System.out.println("expected results:");
            System.out.println(35);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(inorder[3] == 35);
            System.out.println();
            System.out.println("(2) Testing part 8 the last element to be traversed");
            System.out.println("actual results:");
            System.out.println(inorder[keys.length - 1]);
            System.out.println("expected results:");
            System.out.println(70);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(inorder[keys.length - 1] == 70);
            System.out.println();
        } catch(Exception ex){
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + ex);
        }

        System.out.println("--- Testing deleteMax() from Problem 7 part 3 ---");
        System.out.println();

        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("(0) Testing on empty tree from Problem 7 part 3 with empty tree");
            System.out.println("empty tree: " + tree.deleteMax());
            System.out.println();
            int[] keys = {11, 22, 5, 78, 24, 99, 57, 32, 68};
            tree.insertKeys(keys);
            System.out.println("(1) Testing on tree from Problem 7 part 3 delete max 99");
            System.out.println("Deleting : " + tree.deleteMax());
            System.out.println("Searching for deleted item actual results:");
            System.out.println(tree.search(99));
            System.out.println("expected results:");
            System.out.println(""+ null);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.search(99) == null);
            System.out.println();
            System.out.println("(2) Testing on tree from Problem 7 part 3 deleting the next max 78");
            System.out.println("Deleting : " + tree.deleteMax());
            System.out.println("Searching for deleted item actual results:");
            System.out.println(tree.search(78));
            System.out.println("expected results:");
            System.out.println(""+ null);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.search(78) == null);
            System.out.println();
            LinkedTree tree2 = new LinkedTree();
            System.out.println("(3) Testing on tree from Problem 7 part 3 deleting with an empty tree");
            System.out.println("actual results:");
            System.out.println(tree2.deleteMax());
            System.out.println("expected results:");
            System.out.println(-1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree2.deleteMax() == -1);
            System.out.println();
        } catch(Exception ex){
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + ex);
        }

        System.out.println("--- Testing insertBalanced() from Problem 9 ---");
        System.out.println();
        try{
            int[] keys = {10, 8, 4, 2, 15, 12, 7};
            String[] dataItems = {"d10", "d8", "d4", "d2", "d15", "d12", "d7"};
            LinkedTree tree = new LinkedTree(keys, dataItems);
            System.out.println("--- The tree looks like :");
            tree.levelOrderPrint();
            System.out.println("---");
            System.out.println();
            System.out.println("(1) Testing on tree from Problem 9, depth of 8 node");
            System.out.println("actual results:");
            System.out.println(tree.depth(8));
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depth(8) == 0);
            System.out.println();
            System.out.println("(2) Testing on tree from Problem 9, depth of 12 node");
            System.out.println("actual results:");
            System.out.println(tree.depth(12));
            System.out.println("expected results:");
            System.out.println(1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depth(12) == 1);
            System.out.println();
            System.out.println("(2) Testing on tree from Problem 9, depth of 15 node");
            System.out.println("actual results:");
            System.out.println(tree.depth(15));
            System.out.println("expected results:");
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depth(15) == 2);
            System.out.println();
        } catch(Exception ex){
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + ex);
        }
     }
}
