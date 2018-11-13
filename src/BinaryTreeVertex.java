/**
 * Binary tree vertex used in the binary search tree class. Used to store node value,
 * along with left and right child.
 * @author: Tyler Mainguy
 * @version: 1.0.0
 */
public class BinaryTreeVertex {
    public int value;
    public BinaryTreeVertex leftChild;
    public BinaryTreeVertex rightChild;

    //Initialize a new node
    public BinaryTreeVertex(int value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }
}
