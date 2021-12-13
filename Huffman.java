import java.util.ArrayList;
public class Huffman {
  public Node root;
  public ArrayList<Node> queue = new ArrayList<Node>();
  public class Node {
    int freq;
    char c;
    Node left;
    Node right;
    Node(char c, int freq) {
      this.c = c;
      this.freq = freq;
      left = null;
      right = null;
    }
    Node(Node n) {
      c = n.c;
      freq = n.freq;
      left = null;
      right = null;
    }
    Node(Node a, Node b) {
      c = 0;
      freq = a.freq + b.freq;
      if(a.freq > b.freq) {
        left = a;
        right = b;
      }
      else if (a.freq < b.freq) {
        left = b;
        right = a;
      }
      else if (a.c < b.c) {
        left = a;
        right = b;
      }
      else {
        right = b;
        left = a;
      }
    }
  }
  public void buildFromQueue() {
    root = null;
    for(int i = 0; i < queue.size();) {
      Node a = new Node(queue.get(i++));
      if (i == queue.size()) {
        if (root != null) {
          root = new Node(a, root);
        }
        else {
          root = a;
        }
        break;
      }
      Node b = new Node(queue.get(i++));
      Node c = new Node(a, b);
      if(root != null) {
        root = new Node(c, root);
      }
      else {
        root = c;
      }
    }
  }
  public void buildSortedTree(String s) {
    int n = 256;
    int[] frequency = new int[n];
    for(int i = 0; i < s.length();i++) {
      frequency[s.charAt(i)]++;
    }
    for (int i = 0; i < n; i++) {
      if (frequency[i] > 0) {
        Node nd = new Node((char) i, frequency[i]);
        insert(nd);
      }
    }
  }
  public void insert(Node nd) {
    Node curr = root;
    if (root == null) {
      root = nd;
      return;
    }
    boolean inserted = false;
    while (!inserted) {
      if(nd.freq < curr.freq) {
        if(curr.left == null) {
          curr.left = nd;
          inserted = true;
        }
        else {
          curr = curr.left;
        }
      }
      else {
        if (curr.right == null) {
          curr.right = nd;
          inserted = true;
        }
        else {
          curr = curr.right;
        }
      }
    }
  }
  public void huff_code(Node nd, String s) {
    if(nd.left != null) {
      huff_code(nd.left, s + '0');
    }
    if(nd.left == null && nd.right == null) {
      System.out.println(nd.c + " " + String.valueOf(nd.freq) + " " + s);
    }
    if(nd.right != null) {
      huff_code(nd.right, s + '1');
    }
  }
  public void extract(Node nd) {
    if(nd.left != null) {
      extract(nd.left);
    }
    queue.add(nd);
    if(nd.right != null) {
      extract(nd.right);
    }
  }
  public void huffman(String s) {
    buildSortedTree(s);
    extract(root);
    buildFromQueue();
    huff_code(root, "");
  }
  public static void main(String args[])  {
    Huffman h = new Huffman();
    h.huffman("create a huffman tree");
  }
}