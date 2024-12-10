public class Main {
    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(6, null);
        tree.insert(3, null);
        tree.insert(5, null);

        System.out.println(tree.root.left.right.key);
    }
}