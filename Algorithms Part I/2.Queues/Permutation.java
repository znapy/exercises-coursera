import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {

        if (args.length != 1)
            throw new IllegalArgumentException("write argument");

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            q.enqueue(s);
        }

        if (k > q.size())
            throw new IllegalArgumentException("k > n");

        int i = 0;
        for (String item : q) {
            if (i++ == k) break;
            StdOut.println(item);
        }

    }
}
