

public class Node implements Comparable<Node> {
    private double value;
    private char ch;
    public Node left;
    public Node right;

    public Node(double value, char ch, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.ch = ch;
        this.value = value;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.value = 0;
        this.ch = '*';
    }

    private double getValue() {
        double val = 0;
        if (isLeaf()) {
            return value;
        }
        if (left != null) {
            val += left.getValue();
        }
        if (right != null) {
            val += right.getValue();
        }
        return val;
    }

    public boolean isLeaf() {
        return right == null && left == null;
    }

    public int compareTo(Node n) {
        if (this.getValue() > n.getValue()) return 1;
        if (this.getValue() < n.getValue()) return -1;
        else return 0;
    }

    public String toString() {
        if(ch == '\n')
            return "\\n";
        if(ch == ' ')
            return "[ ]";
        return Character.toString(ch);
    }
}