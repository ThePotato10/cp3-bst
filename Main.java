// By JD Peppelman, December 2024
// Java implementation of a binary search tree for programming 3
// Has the tree data structure, plus insert, search, delete, and toString methods

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(6, null);
        tree.insert(4, null);
        tree.insert(2, null);
        tree.insert(5, null);
        tree.insert(1, null);
        tree.insert(3, null);

        tree.printTree();
    }
}