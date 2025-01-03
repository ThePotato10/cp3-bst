import java.util.ArrayList;

class BST {
    public Node root;

    public BST() {
        root = null;
    }
  
    // Inserts an element into the correct position in the BST
    // Precondition: curr is either a valid tree node or null
    // Postcondition: element is inserted, nothing is returned
    private void insert(int key, Node curr) {
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

    public void insert(int key) {
        insert(key, null);
    }

    // Searches the tree and returns true if the element exists in the tree
    // Precondition: key is an integer
    // Postcondition: returns a boolean indicating whether the element exists
    public boolean search(int key) {
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

    // Removes an element from the tree, maintaining tree structure
    // Precondition: element is in the tree
    // Postcondition: returns the removed element
    public int remove(int key) {
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

    // Returns a layer-based string representation of the tree, where each newline is a different layer
    // Precondition: tree is a valid BST
    // Postcondition: returns a string represtation of the tree
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

    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTOrNot(Node root, int minValue, int maxValue) {
        // check for root is not null or not
        if (root == null) {
            return true;
        }
        // check for current node value with left node value and right node value and recursively check for left sub tree and right sub tree
        if(root.key >= minValue && root.key <= maxValue && isBSTOrNot(root.left, minValue, root.key) && isBSTOrNot(root.right, root.key, maxValue)){
            return true;
        }
        return false;
    }

 

   // please use the following pieces of code to display your tree in a more easy to follow style (Note* you'll need to place the Trunk class in it's own file)
    public static void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public void printTree() {
        printTree(root, null, false);
    }

    private void printTree(Node root, Trunk prev, boolean isLeft) {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.key);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.left, trunk, false);
    }

    // Clockwise rotation of a node
    private void rotateRight(Node subRoot, Node prev) {
        Node temp = subRoot.left;
        subRoot.left = subRoot.left.right;

        temp.right = subRoot;
        subRoot = temp;

        if (prev == null) {
            root = subRoot;
        } else {
            if (prev.key < subRoot.key) prev.right = subRoot;
            else prev.left = subRoot;
        }
    }

    // Counterclockwise rotation of a node
    private void rotateLeft(Node subRoot, Node prev) {
        Node temp = subRoot.right;
        subRoot.right = subRoot.right.left;

        temp.left = subRoot;
        subRoot = temp;

        if (prev == null) {
            root = subRoot;
        } else {
            if (prev.key < subRoot.key) prev.right = subRoot;
            else prev.left = subRoot;
        }
    }

    public int height(Node node) {
        int height = 0;

        ArrayList<Node> currLayer = new ArrayList<Node>();
        
        currLayer.add(node);

        while (!currLayer.stream().allMatch(x -> x == null)) {
            ArrayList<Node> nextLayer = new ArrayList<Node>();

            for (int i = 0; i < currLayer.size(); i++) {
                if (currLayer.get(i) != null) {
                    nextLayer.add(currLayer.get(i).left);
                    nextLayer.add(currLayer.get(i).right);
                }
            }

            currLayer = nextLayer;
            height++;
        }

        return height;
    }

    public int balance(Node node) {
        return countDown(node.left) - countDown(node.right);
    }

    private int countDown(Node node) {
        System.out.println("recursive call");
        int down = 0;

        if (node == null) return 0;

        if (node.left != null) down += (1 + countDown(node.left));
        if (node.right != null) down += (1 + countDown(node.right));

        return down;
    }
}
