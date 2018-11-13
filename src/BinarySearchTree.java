/**
 * Binary search tree implementation. This binary search tree stores integer values in
 * it's nodes. Functionality includes adding nodes, finding total depth, finding max depth,
 * and searching for given node values in the tree.
 * @author Tyler Mainguy
 * @version 1.0.0
 */
public class BinarySearchTree {
    //Attribute containing the root of the tree
    public BinaryTreeVertex root;

    //Instantiate tree with binaryTreeVertex as the root
    public BinarySearchTree() {
        this.root = null;
    }
    //Call recursive search function for tree
    public void insert(int x) {
        this.root = recInsert(this.root,x);
    }
    //Recursive function that searches for insert point of new vertex
    private BinaryTreeVertex recInsert(BinaryTreeVertex curr, int x) {
        //If a leaf is reached, insert new value
        if(curr == null) {
            return new BinaryTreeVertex(x);
        }
        //If value is larger than current vertex's value, recurse down the right side of the tree
        else if (x > curr.value) {
            curr.rightChild = recInsert(curr.rightChild,x);
        }
        //If not, recurse down the left side of the tree
        else {
            curr.leftChild = recInsert(curr.leftChild,x);
        }
        //Return if current value is duplicate (safety purposes, even though assignment says there is unique input)
        return curr;
    }
    //Shell function to hide recursive function within
    public int totalDepth() {
        return findTotalDepth(this.root,1);
    }

    private int findTotalDepth(BinaryTreeVertex curr, int currDepth) {
        //If current points to null, return 0 (empty tree or leaf reached)
        if(curr == null) {
            return 0;
        }
        //Else add current depth to the sum of the depths found in the left and right tree after recursive function calls
        else {
            return currDepth + findTotalDepth(curr.leftChild,currDepth+1) + findTotalDepth(curr.rightChild, currDepth+1);
        }
    }
    //Shell function hiding recursive maxDepth function. Calls function on root
    public int maxDepth() {
        return findMaxDepth(this.root, 1);
    }
    //Recursive function that finds the max depth of the tree;
    private int findMaxDepth(BinaryTreeVertex curr, int currDepth) {
        //If null, either leaf reached or tree empty. Return depth - 1
        //(last place with a vertex, or 0 in the case of empty tree)
        if(curr == null) {
            return currDepth - 1;
        }
        //If not null, find max depths in both subtrees by recursively calling the function.
        else {
            int leftDepth = findMaxDepth(curr.leftChild, currDepth+1);
            int rightDepth = findMaxDepth(curr.rightChild, currDepth+1);
            //If depth found in left subtree greater than right, return that depth
            if(leftDepth > rightDepth) {
                return leftDepth;
            }
            //If depth found in right is greater or equal, return the right. (If both subtrees are empty,
            //they both return the depth of the current vertex, which would be the max depth of that subtree.)
            return rightDepth;
        }
    }
    //Function that prints out all values on the search path to find some value x in a binary tree
    public void searchPath(int x) {
        SearchStack stack = new SearchStack();
        //Call the recursive search function, which returns a stack of all values visited
        stack = recSearchPath(this.root, x, stack);
        SearchStack reverseStack = new SearchStack();
        //Reverse the stack to put it in first to last order, and print the values out.
        while(!stack.isEmpty()) {
            reverseStack.push(stack.pop());
        }
        while(!reverseStack.isEmpty()) {
            System.out.print(reverseStack.pop()+" ");
        }
    }
    //Recursive function that searches for value x in a binary tree, and returns stack of all values visited
    //on the search path.
    private SearchStack recSearchPath(BinaryTreeVertex curr, int x, SearchStack stack) {
        //If value is not in the tree or the tree is empty, return stack of values in search path
        if (curr == null) {
            return stack;
        }
        //If value found, push onto the stack and return the stack
        else if (curr.value == x) {
            stack.push(curr.value);
            return stack;
        }
        //If value is greater than current node's value, push value onto stack and recurse down right subtree
        else if (curr.value < x) {
            stack.push(curr.value);
            recSearchPath(curr.rightChild, x, stack);
        }
        //If value is less than current node's value, push value onto stack and recurse down left subtree
        else {
            stack.push(curr.value);
            recSearchPath(curr.leftChild, x, stack);
        }
        //Should not reach here, IDE is stupid and makes me put this line in
        return stack;
    }
}
