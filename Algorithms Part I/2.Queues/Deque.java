import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int quantity = 0;

    private Node first = null;
    private Node last = null;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return quantity;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null)
            throw new IllegalArgumentException("you try to add null item");

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (quantity == 0) {
            first.next = null;
            first.prev = null;
            last = first;
        }
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        quantity++;
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null)
            throw new IllegalArgumentException("you try to add null item");

        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (quantity == 0) {
            last.next = null;
            last.prev = null;
            first = last;
        }
        else {
            last.prev = oldlast;
            oldlast.next = last;
        }
        quantity++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (quantity == 0)
            throw new NoSuchElementException("you try to remove from empty deque");
        Item item = first.item;
        first = first.next;
        if (first != null)
            first.prev = null;
        quantity--;
        if (quantity == 0) {
            last = null;
            first = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (quantity == 0)
            throw new NoSuchElementException("you try to remove from empty deque");
        Item item = last.item;
        last = last.prev;
        if (last != null)
            last.next = null;
        quantity--;
        if (quantity == 0) {
            last = null;
            first = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove operator doesn't allow");
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException("next operator for empty iterator");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> d = new Deque<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            d.addLast(s);
        }

        for (String item : d)
            StdOut.println(item);
    }

}
