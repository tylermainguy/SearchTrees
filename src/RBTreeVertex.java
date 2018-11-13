/**
 * Red black tree vertex. Contains data about value stored, if it's a leaf, it's color, and it's children.
 * @author Tyler Mainguy
 * @version 1.0.0
 */
public class RBTreeVertex {
    public int value;
    public char colour;
    public RBTreeVertex leftChild;
    public RBTreeVertex rightChild;
    public boolean isLeaf;

    //Construct new node of RB tree, and create two black leaves as its children
    public RBTreeVertex(int value) {
        this.value = value;
        this.isLeaf = false;
        this.colour = 'r';
        this.leftChild = new RBTreeVertex();
        this.rightChild = new RBTreeVertex();
    }
    //Use seperate constructor for leaves in order to avoid infinite recursion
    public RBTreeVertex() {
        this.value = 0;
        this.colour = 'b';
        this.isLeaf = true;
    }
}
