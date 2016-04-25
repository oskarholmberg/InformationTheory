import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Created by oskar on 2016-04-25.
 */
public class Huffman {

    private int[] dist = new int[255];
    private int fileLength;
    private PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        new Huffman("LifeOnMars.txt");
    }

    public Huffman(String fileLocation) {
        parseFile(fileLocation);
        createNodes();
        buildTree();
        Node root = pq.poll();
        printCode(root, "");
        System.out.println("Distribution array to use with MATLAB:");
        System.out.print("[");
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] != 0) {
                System.out.print((double) dist[i] / fileLength + " ");
            }
        }
        System.out.print("]'");
    }

    private void parseFile(String fileLocation) {
        fileLength = 0;
        try {
            FileInputStream is = new FileInputStream(new File(fileLocation));
            int c = is.read();
            while (c != -1) {
                dist[c]++;
                fileLength++;
                c = is.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNodes() {
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] != 0) {
                pq.add(new Node((double) dist[i] / fileLength, (char) i, null, null));
            }
        }
    }

    private void buildTree() {
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left, right);
            pq.add(parent);
        }
    }

    private void printCode(Node root, String code) {
        if (root.isLeaf()) {
            System.out.println(root.toString() + "\twith encoding: " + code);
        } else {
            printCode(root.left, code + 0);
            printCode(root.right, code + 1);
        }
    }
}
