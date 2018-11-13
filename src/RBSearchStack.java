/**
 * Implementation of a stack for the red-black search tree. New implementation required to include complexities behind
 * what is stored in the trees (keep entire node).
 * @author Tyler Mainguy
 * @version 1.0.0
 */
public class RBSearchStack {

    public RBTreeVertex node;
    public RBSearchStack next;
    public RBSearchStack top;
    //Initialize stack
    public RBSearchStack() {
        this.top = null;
    }
    //Push a value onto the stack. Create new node with node passed in. Point new node at what old node pointed to. Top is now new node
    public void push(RBTreeVertex v) {
        RBSearchStack newElement = new RBSearchStack();
        newElement.node = v;
        newElement.next = this.top;
        this.top = newElement;
    }
    //Popping a value off the stack. Point top of the stack to what the popped value was pointing to. Return the popped value
    public RBTreeVertex pop() {
        RBTreeVertex returnVal = this.top.node;
        this.top = this.top.next;
        return returnVal;
    }
    //If the stack is empty, return true. False otherwise.
    public boolean isEmpty() {
        return (this.top == null);
    }
}