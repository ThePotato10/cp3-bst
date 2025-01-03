// By JD Peppelman, December 2024
// Java implementation of a binary search tree for programming 3
// Has the tree data structure, plus insert, search, delete, and toString methods

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(6);
        tree.insert(4);
        tree.insert(2);
        tree.insert(5);
        tree.insert(1);
        tree.insert(3);

        tree.printTree();
        System.out.println(tree.toString());
        System.out.println(tree.height(tree.root.left));
    }
}