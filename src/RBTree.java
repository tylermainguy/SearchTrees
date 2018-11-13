/**
 * Implementation of a red-black tree. Functionality allows for adding nodes, finding max or total depth, and
 * searching for a given value x in the tree.
 * @author Tyler Mainguy
 * @version 1.0.0
 */
public class RBTree {
    public RBTreeVertex root;

    //Initialize RB tree by passing in RBTreeVertex to act as the root, and colour it black
    public RBTree() {
        this.root = new RBTreeVertex();
        this.root.colour = 'b';
    }
    //Shell insert function that passes in the root as the start value, and x as the input value (colour root black after insert)
    public void insert(int x) {
        this.root = recInsert(this.root, x);
        this.root.colour = 'b';
    }
    //Recursive insert function that passes in a current vertex, and the value to insert. Returns the new root of the subtree passed in
    private RBTreeVertex recInsert(RBTreeVertex curr, int x) {
        //If the current vertex is a leaf, insert the vertex there
        if(curr.isLeaf) {
            return new RBTreeVertex(x);
        }
        //If the current vertex greater than x value, recurse down left subtree to find insert point.
        else if (curr.value > x){
            curr.leftChild = recInsert(curr.leftChild, x);
            //If parent returned is black, no issues so return fixed subtree
            if(curr.leftChild.colour == 'b') {
                return curr;
            }
            //If parent returned is red
            else {
                //If left or right child of parent are red, there is a R-R conflict
                if(curr.leftChild.leftChild.colour == 'r' || curr.leftChild.rightChild.colour == 'r') {
                    //If parents left child is red, return fixed subtree produced by leftleftFix function
                    if(curr.leftChild.leftChild.colour == 'r') {
                        return leftLeftFix(curr);
                    }
                    //If parents right child is red, return fixed subtree produced by leftRightFix function
                    else {
                        return leftRightFix(curr);
                    }

                }
                //If both of parents children are black, no conflicts so return subtree
                else {
                    return curr;
                }
            }
        }
        //If current vertex is less than search value, recurse down right subtree to find insert point
        else {
            curr.rightChild = recInsert(curr.rightChild,x);
            //If parent is black then there is no conflict
            if(curr.rightChild.colour == 'b') {
                return curr;
            }
            //If parent is red
            else {
                //If either of parents children are red, there is a R-R conflict
                if(curr.rightChild.rightChild.colour == 'r' || curr.rightChild.leftChild.colour == 'r') {
                    //If parents left child is red, return fixed subtree returned by the rightLeftFix function
                    if(curr.rightChild.leftChild.colour == 'r') {
                        return rightLeftFix(curr);
                        //If parents right child is red, return fixed subtree returned by the rightRightFix function
                    }
                    else {
                        return rightRightFix(curr);
                    }
                }
                //If both children are black, no issues so return subtree as is.
                else {
                    return curr;
                }
            }
        }
    }

    //Function to fix the RR conflict, where the parent is red, and the parents left child is red.
    private RBTreeVertex leftLeftFix(RBTreeVertex gp) {
        RBTreeVertex p = gp.leftChild;
        RBTreeVertex s = gp.rightChild;
        //If the sibling is red, recolour the parent and sibling black, and the grandparent red. Then return the fixed subtree
        if(s.colour == 'r') {
            p.colour = 'b';
            s.colour = 'b';
            gp.colour = 'r';
            return gp;
        }
        //If sibling is black, perform a single rotation.Grandparents left child becomes the parents right child, parents right child
        //becomes the grandparent(parent=root), colour the parent black and the grandparent red
        else {
            gp.leftChild = p.rightChild;
            p.rightChild = gp;
            p.colour = 'b';
            gp.colour = 'r';
            return p;
        }
    }
    //Function to fix the RR conflict, where the parent is red and the parents right child is red
    private RBTreeVertex leftRightFix(RBTreeVertex gp) {
        RBTreeVertex p = gp.leftChild;
        RBTreeVertex s = gp.rightChild;
        //If the sibling is red, recolour the parent and sibling black, and the grandparent red. Return the fixed subtree
        if(s.colour == 'r') {
            p.colour = 'b';
            s.colour = 'b';
            gp.colour = 'r';
            return gp;
        }
        //If the sibling is black, perform a double rotation. Parents right child becomes childs left child, grandparents left child
        //becomes childs right child, childs left and right children become parent and grandparent respectively (child is now root).
        //Recolour child and grandparent black and red respectively, and return the new subtree.
        else {
            RBTreeVertex c = p.rightChild;
            p.rightChild = c.leftChild;
            gp.leftChild = c.rightChild;
            c.leftChild = p;
            c.rightChild = gp;
            c.colour = 'b';
            gp.colour = 'r';
            return c;
        }
    }
    //Function to fix the RR conflict, parent is right child of grandparent,where the parent is red, and the parents right child is red.
    private RBTreeVertex rightRightFix(RBTreeVertex gp) {
        RBTreeVertex p = gp.rightChild;
        RBTreeVertex s = gp.leftChild;
        //If the sibling is red, recolour the parent and sibling black, and the grandparent red. Then return the fixed subtree
        if(s.colour == 'r') {
            p.colour = 'b';
            s.colour = 'b';
            gp.colour = 'r';
            return gp;
        }
        //If sibling is black, perform a single rotation.Grandparents right child becomes the parents left child, parents left child
        //becomes the grandparent(parent=root), colour the parent black and the grandparent red
        else {
            gp.rightChild = p.leftChild;
            p.leftChild = gp;
            p.colour = 'b';
            gp.colour = 'r';
            return p;
        }
    }
    //Function to fix the RR conflict, grandparents right child is the parent, where the parent is red and the parents right child is red
    private RBTreeVertex rightLeftFix(RBTreeVertex gp) {
        RBTreeVertex p = gp.rightChild;
        RBTreeVertex s = gp.leftChild;
        //If the sibling is red, recolour the parent and sibling black, and the grandparent red. Return the fixed subtree
        if(s.colour == 'r') {
            p.colour = 'b';
            s.colour = 'b';
            gp.colour = 'r';
            return gp;
        }
        //If the sibling is black, perform a double rotation. Parents left child becomes childs right child, grandparents right child
        //becomes childs left child, childs left and right children become grandparent and parent respectively (child is now root).
        //Recolour child and grandparent black and red respectively, and return the new subtree.
        else {
            RBTreeVertex c = p.leftChild;
            p.leftChild = c.rightChild;
            gp.rightChild = c.leftChild;
            c.rightChild = p;
            c.leftChild = gp;
            c.colour = 'b';
            gp.colour = 'r';
            return c;
        }
    }
    //Function that prints every value encountered on the search path for some value x in the tree (whether or not x exists in the tree or not)
    public void searchPath(int x) {
        RBSearchStack stack = new RBSearchStack();
        //Create a stack, where every value in the stack is a value encountered searching for value x
        stack = recSearchPath(this.root, x, stack);
        RBTreeVertex currentNode;
        RBSearchStack reverseStack = new RBSearchStack();
        //Create a reverse stack in order to put the values found in correct order
        while(!stack.isEmpty()) {
            reverseStack.push(stack.pop());
        }
        //Pop stack and output each value and colour for each node in the stack, until the stack is empty
        while(!reverseStack.isEmpty()) {
            currentNode = reverseStack.pop();
            System.out.println(currentNode.value+" "+currentNode.colour+" ");
        }
    }
    //Recursive search function that searches for some value x, pushing every value found on the way onto a stack.
    private RBSearchStack recSearchPath(RBTreeVertex curr, int x, RBSearchStack stack) {
        //If value is not in the tree or the tree is empty, return stack of values in search path
        if (curr.isLeaf) {
            return stack;
        }
        //If value found, push onto the stack and return the stack
        else if (curr.value == x) {
            stack.push(curr);
            return stack;
        }
        //If value is greater than current no	de's value, push value onto stack and recurse down right subtree
        else if (curr.value < x) {
            stack.push(curr);
            recSearchPath(curr.rightChild, x, stack);
        }
        //If value is less than current node's value, push value onto stack and recurse down left subtree
        else {
            stack.push(curr);
            recSearchPath(curr.leftChild, x, stack);
        }
        //Should not reach here, IDE is stupid and makes me put this line in
        return stack;
    }
    //Shell function hiding the recursive findMaxDepth function
    public int maxDepth() {
        return findMaxDepth(this.root, 1);
    }
    //Recursive function that finds the max depth of the tree;
    private int findMaxDepth(RBTreeVertex curr, int currDepth) {
        //If null, either leaf reached or tree empty. Return depth - 1
        //(last place with a vertex, or 0 in the case of empty tree)
        if(curr.isLeaf) {
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
    //Shell function to hide recursive function within
    public int totalDepth() {
        return findTotalDepth(this.root,1);
    }
    //Recursive function that finds the total depth of a RB tree.
    private int findTotalDepth(RBTreeVertex curr, int currDepth) {
        //If current points to null, return 0 (empty tree or leaf reached)
        if(curr.isLeaf) {
            return 0;
        }
        //Else add current depth to the sum of the depths found in the left and right tree after recursive function calls
        else {
            return currDepth + findTotalDepth(curr.leftChild,currDepth+1) + findTotalDepth(curr.rightChild, currDepth+1);
        }
    }
}
