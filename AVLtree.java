package avltree;

/**
 *
 * @author Kyle Martinez & Andrew Vogel
 */
public class AVLtree {

    public Node root;

    public AVLtree() {
        root = null;
    }

    //insert value x at the root YYY
    public void insert(int x) {
        root = insert(x, root);
    }

    private Node insert(int key, Node node) {
        if (node == null) {
            node = new Node(key);
        } else if (key < node.key) {
            Node nodePar = node;
            node.left = insert(key, node.left);
            node.left.parent = nodePar;
            if (balanceFactor(node) == 2) {
                if (key < node.left.key) {
                    node = rightRotate(node);
                } else {
                    node = leftRotate(node);
                }
            }
        } else if (key > node.key) {
            Node nodePar = node;
            node.right = insert(key, node.right);
            node.right.parent = nodePar;
            if (balanceFactor(node) == 2) {
                if (key > node.right.key) {
                    node = leftRotate(node);
                } else {
                    node = rightRotate(node);
                }
            }
        } else {
            node.height = maximum(height(node.left), height(node.right));
        }
        return node;
    }

    private int balanceFactor(Node node) {
        int L = node.left.height;
        int R = node.right.height;
        if (L - R >= 2) {
            return -1;
        } else if (L - R <= -2) {
            return 1;
        }
        return 0;
    }

    //returns true if the key value is equal to x
    public boolean search(int x) {
        boolean found = false;
        Node y = root;
        while ((x != 0) && !found) {

            if (x > y.key) {
                y = y.left;
            } else if (x < y.key) {
                y = y.right;
            } else {
                found = true;
                break;
            }
        }
        return found;
    }

    public Node searchKey(Node node, int i) {
        if (i < node.key) {
            return searchKey(node.left, i);
        } else if (i > node.key) {
            return searchKey(node.right, i);
        } else {
            return node;
        }
    }

    //max value between 2 nodes YYY
    public Node max(Node x, Node y) {
        if (x.key > y.key) {
            return x;
        } else {
            return y;
        }
    }

    //max value between 2 ints YYY
    public int maximum(int x, int y) {
        if (x > y) {
            return x;
        } else {
            return y;
        }
    }

    //the height of a node x YYY
    private int height(Node node) {
        if (node.left == null && node.right == null) {
            return 0;
        } else if (node.left == null) {
            return 1 + height(node.right);
        } else if (node.right == null) {
            return 1 + height(node.left);
        } else {
            return 1 + maximum(height(node.left), height(node.right));
        }
    }

    private Node leftRotate(Node node) {
        Node q = node;
        Node p = q.right;
        Node c = q.left;
        Node a = p.left;
        Node b = p.right;
        q = new Node(q.key, c, a);
        p = new Node(p.key, q, b);
        return p;
    }

    private Node rightRotate(Node node) {
        Node q = node;
        Node p = q.left;
        Node c = q.right;
        Node a = p.left;
        Node b = p.right;
        q = new Node(q.key, b, c);
        p = new Node(p.key, a, q);
        return p;
    }

    //returns successor of the element x
    public int successor(Node node) {
        if (node.right != null) {
            Node y = node.right;
            while (y.left != null) {
                y = y.left;
            }
            return y.key;
        } else {
            Node y = node.parent;
            while (y != null && node == y.right) {
                node = y;
                y = y.parent;
            }
            return y.key;
        }
    }

    public int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    //returns the rank/position of element x in the linear order..
    public int rank(Node node, int k) {
        //returns #elements  < = key in subtree of x
        if (node == null) {
            return 0;
        }
        if (k < node.key) {
            return (rank(node.left, k));
        }
        if (k == node.key) {
            return (size(node.left) + 1);
        }
        return (1 + size(node.left) + rank(node.right, k));

    }

    //return the pointer to Node contatining kth smallest key
    public Node Select(Node node, int k) {
        if (node == null) {
            return null;
        }
        if (k <= size(node.left)) {
            return Select(node.left, k);
        } else if (k == size(node.left) + 1) {
            return node;
        } else {
            return Select(node.right, k - 1 - size(node.left));
        }
    }
}
