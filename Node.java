package avltree;

/**
 *
 * @author Kyle Martinez & Andrew Vogel
 */
public class Node {

    int key, height;
    Node left, right, parent;

    public Node(int x) {
        this(x, null, null);
    }

    public Node(int x, Node left, Node right) {
        this.left = left;
        this.right = right;
        key = x;
 }

    //finds the height of Node x, longest path to some leaf
    public int height(Node x) {
        if (left == null && right == null) {
          height = 1;
        }
        else if (left == null) {
          height = right.height + 1;
        }
        else if (right == null) {
        height = left.height + 1;
        }
        else {
        height = Math.max(left.height, right.height) + 1;
        }
        return height;
    }

    //max height of 2 Nodes x, y
    public int max(Node x, Node y) {
        if (height(x) > height(y)) {
            return height(x);
        } else if (height(x) < height(y)) {
            return height(y);
        } else {
            return height(x);
        }
    }
}
