/**
 * Implementation of a stack. Used in the BinarySearchTree class when traversing the tree
 * in order to store values visited on a given path.
 * @author: Tyler Mainguy
 * @version: 1.0.0
 */
public class SearchStack {
    public int value;
    public SearchStack next;
    public SearchStack top;

    //Initialize stack
    public SearchStack() {
        this.top = null;
    }
    //Push value onto the stack.Create new stack element with value passed in.
    // Point new element at what old element pointed to. Top is now new element
    public void push(int x) {
        SearchStack newElement = new SearchStack();
        newElement.value = x;
        newElement.next = this.top;
        this.top = newElement;
    }
    //Pop value from stack. Point stack top to what old top was pointing to.
    public int pop() {
        int x = this.top.value;
        this.top = this.top.next;
        return x;
    }
    //Function that returns true if stack is empty. False otherwise.
    public boolean isEmpty() {
        return this.top == null;
    }
}
