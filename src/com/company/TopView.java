import java.util.*;
import java.io.*;

class TopView {

    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    /*
    
    class Node 
        int data;
        Node left;
        Node right;
    */
    static class Slice{
        int value;
        int level;

        public Slice(int value, int level) {
            this.value = value;
            this.level = level;
        }
    }

    static Map<Integer, Slice> m = new TreeMap<>();

    public static void topView(Node root) {
        m.put(0, new Slice(root.data, 0));
        topView(root.left, -1, 1);
        topView(root.right, 1, 1);
        m.forEach((k, v) -> System.out.print(v.value + " "));
    }

    public static void topView(Node root, int distance, int level) {
        if (root != null){
            Slice curSlice = m.get(distance);
            if (curSlice == null || level < curSlice.level){
                m.put(distance, new Slice(root.data, level));
            }
            topView(root.left, distance - 1, level + 1);
            topView(root.right, distance + 1, level + 1);
        }
    }


    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input.txt"));
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }
}