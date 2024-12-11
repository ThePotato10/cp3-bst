import java.util.ArrayList;

class BST {
    public Node root;

    public BST() {
        root = null;
    }
  
    void insert(int key, Node curr) {
        if (root == null) {
            root = new Node(key);

        } else {
            if (curr == null) curr = root;
            
            if (key < curr.key) {
                // Go left
                if (curr.left == null) curr.left = new Node(key);
                else insert(key, curr.left);
                
            } else {
                // Go right
                if (curr.right == null) curr.right = new Node(key);
                else insert(key, curr.right);

            }

        }
    }

    boolean search(int key) {
        Node curr = root;

        while (curr != null) {
            if (curr.key == key) {
                return true;

            } else {
                if (key < curr.key) curr = curr.left;
                else curr = curr.right;

            }
        }

        return false;
    }

    int remove(int key) {
        Node curr = root;

        while (curr.left != null || curr.right != null) {
            if (curr.left != null && curr.left.key == key) {
                int val = curr.left.key;

                if (curr.left.left == null && curr.left.right == null) curr.left = null;
                else if (curr.left.left == null) curr.left = curr.left.right;
                else if (curr.left.right == null) curr.left = curr.left.left;
                else {
                    Node replacement = curr.left;

                    if (replacement.right == null) {
                        curr.left.key = replacement.key;
                        curr.left.left = null;

                    } else {
                        while (replacement.right.right != null) replacement = replacement.right;

                        curr.left.key = replacement.right.key;
                        replacement.right = null;

                    }
                }

                return val;

            } else if (curr.right != null && curr.right.key == key) {
                int val = curr.right.key;

                if (curr.right.left == null && curr.right.right == null) curr.right = null;
                else if (curr.right.left == null) curr.right = curr.right.right;
                else if (curr.right.right == null) curr.right = curr.right.left;
                else {
                    Node replacement = curr.right;

                    if (replacement.left == null) {
                        curr.right.key = replacement.key;
                        curr.right.right = null;

                    } else {
                        while (replacement.left.left != null) replacement = replacement.left;

                        curr.right.key = replacement.left.key;
                        replacement.left = null;

                    }
                }

                return val;

            } else {
                if (key < curr.key) curr = curr.left;
                else curr = curr.right;

            }
        }

        return Integer.MIN_VALUE;
    }

    public String toString() {
        String treeString = "";
        ArrayList<Node> currLayer = new ArrayList<Node>();
        
        currLayer.add(root);

        while (!currLayer.stream().allMatch(x -> x == null)) {
            ArrayList<Node> nextLayer = new ArrayList<Node>();

            for (int i = 0; i < currLayer.size(); i++) {
                if (currLayer.get(i) != null) {
                    treeString += Integer.toString(currLayer.get(i).key) + " ";

                    nextLayer.add(currLayer.get(i).left);
                    nextLayer.add(currLayer.get(i).right);
                }
            }

            currLayer = nextLayer;
            treeString += "\n";
        }

        return treeString;
    }
}