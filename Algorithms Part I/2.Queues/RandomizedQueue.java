import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (n == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] a2 = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            a2[i] = a[i];
        a = a2;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("you try to add null item");

        if (a.length == n) resize(n * 2);

        a[n++] = item;

        /*for (int i = 0; i < a.length; i++) {
            StdOut.print(String.valueOf(a[i]) + ' ');
        }
        StdOut.println(n);*/
    }

    // remove and return a random item
    public Item dequeue() {

        if (size() == 0)
            throw new NoSuchElementException("you try to add null item");

        if (a.length == n / 4) resize(n / 2);

        int randIndex = 0;
        if (n > 1)
            randIndex = StdRandom.uniform(n - 1);
        Item result = a[randIndex];
        a[randIndex] = a[n - 1];
        a[n - 1] = null;
        n--;
        return result;
    }

    // return a random item (but do not remove it)
    public Item sample() {

        if (size() == 0)
            throw new NoSuchElementException("you try to add null item");

        int randIndex = 0;
        if (n > 1)
            randIndex = StdRandom.uniform(n - 1);

        return a[randIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int current;
        private Item[] randQueue;

        public ListIterator() {
            current = n - 1;
            if (n > 0) {
                randQueue = (Item[]) new Object[n];
                for (int i = 0; i < n; i++)
                    randQueue[i] = a[i];
            }

            if (n > 1)
                StdRandom.shuffle(randQueue);

        }

        public boolean hasNext() {
            return current > -1;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove operator doesn't allow");
        }

        public Item next() {
            if (current < 0)
                throw new NoSuchElementException("next operator for empty iterator");
            return randQueue[current--];
        }
    }

    public static void main(String[] args) {

        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            q.enqueue(s);
        }

        for (String item : q)
            StdOut.println(item);

    }
}
