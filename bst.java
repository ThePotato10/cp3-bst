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
                if (curr.left == null) {
                    curr.left = new Node(key);
                } else {
                    insert(key, curr.left);
                }
            } else {
                // Go right
                if (curr.right == null) {
                    curr.right = new Node(key);
                } else {
                    insert(key, curr.right);
                }
            }
        }
    }

    /*boolean search(int key) {

    }

    int remove(int key) {

    }

    public String toString() {
        
    }*/
}
