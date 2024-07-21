/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.NoSuchElementException;

public class RandomizedQueueTests {
    public static void main(String[] args) {

        RandomizedQueueTests t = new RandomizedQueueTests();

        t.isEmpty();

        t.size();

        t.enqueue();

        t.dequeue();

        t.sample();

        t.iterator();

        StdOut.println("tests succefull complete");

    }

    private void isEmpty() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        if (!q.isEmpty())
            throw new UnsupportedOperationException("test_isEmpty JustCreated failed");

        q.enqueue("A");
        if (q.isEmpty())
            throw new UnsupportedOperationException("test_isEmpty AfterEnqueue failed");

        q.dequeue();
        if (!q.isEmpty())
            throw new UnsupportedOperationException("test_isEmpty AfterRemoveLast failed");
    }

    private void size() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        if (q.size() != 0)
            throw new UnsupportedOperationException("size() JustCreated failed");

        q.enqueue("A");
        if (q.size() != 1)
            throw new UnsupportedOperationException("size() AfterEnqueue failed");

        q.dequeue();
        if (q.size() > 0)
            throw new UnsupportedOperationException("size() AfterRemoveLast failed");
    }

    private void enqueue() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            q.enqueue(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "enqueue() null (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("enqueue() null (no exception) failed");

    }

    private void dequeue() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            q.dequeue();
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof NoSuchElementException))
                throw new UnsupportedOperationException(
                        "dequeue() null (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("dequeue() null (no exception) failed");

        // resize twice and desize quarter

        java.util.List<String> etalon = new java.util.ArrayList<String>();
        etalon.addAll(java.util.Arrays
                              .asList("1", "2", "3", "4", "5",
                                      "6",
                                      "7", "8"));

        for (String item : etalon)
            q.enqueue(item);

        int i = 0;
        while (i++ < 8) {
            String item = q.dequeue();

            if (!etalon.contains(item))
                throw new UnsupportedOperationException(
                        "dequeue() item failed");

            etalon.remove(item);
        }

        if (q.size() != 0)
            throw new UnsupportedOperationException("dequeue() last failed");
    }

    private void sample() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            q.sample();
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof NoSuchElementException))
                throw new UnsupportedOperationException(
                        "sample() null (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("sample() null (no exception) failed");

        q.enqueue("1");

        if (q.sample() != "1")
            throw new UnsupportedOperationException(
                    "sample() item 1 failed");

        if (q.sample() != "1")
            throw new UnsupportedOperationException(
                    "sample() item 2 failed");
    }

    private void iterator() {
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while (q.iterator().hasNext())
            throw new UnsupportedOperationException("iterator empty failed");

        java.util.Iterator<String> iterator = q.iterator();

        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            iterator.remove();
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof UnsupportedOperationException))
                throw new UnsupportedOperationException(
                        "iterator() remove (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("iterator() remove (no exception) failed");

        q.enqueue("A");
        iterator = q.iterator();
        if (!iterator.hasNext())
            throw new UnsupportedOperationException("iterator() hasNext 1 failed");

        String item = iterator.next();
        if (item != "A")
            throw new UnsupportedOperationException("iterator() next 1 failed");

    }

}
