package avltree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Kyle Martinez & Andrew Vogel
 */
public class AVLTreeTester {

    public static void main(String[] args) {
        AVLtree avl = new AVLtree();
        try {
            FileReader file = new FileReader("AVLtree-input.txt");
            BufferedReader br = new BufferedReader(file);
            String line;
            String val;
            while ((line = br.readLine()) != null) {
                val = line.substring(0,2);
                //System.out.println(line);
                if (val.equals("IN")) {
                    String[] parts = line.split("\\s");
                    int value = Integer.parseInt(parts[1]);
                    avl.insert(value);
                } else if (val.equals("SR")) {
                    String[] parts = line.split("\\s");
                    int value = Integer.parseInt(parts[1]);
                    boolean sResult = avl.search(value);
                    System.out.println("The tree contains" + value + ". Search Result:" + sResult);
                } else if (val.equals("SC")) {
                    String[] parts = line.split("\\s");
                    int value = Integer.parseInt(parts[1]);
                    int succ = avl.successor(avl.searchKey(avl.root, value));
                    System.out.println("Successor to " + value + "is" + succ);
                } else if (val.equals("SE")) {
                    String[] parts = line.split("\\s");
                    int value = Integer.parseInt(parts[1]);
                    Node node = avl.Select(avl.root, value);
                    System.out.println("The" + value + "th smallest key is" + node);
                } else if (val.equals("RA")) {
                    String[] parts = line.split("\\s");
                    int value = Integer.parseInt(parts[1]);
                    int rank = avl.rank(avl.root, value);
                    System.out.println("The rank of " + value + "is" + rank);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
