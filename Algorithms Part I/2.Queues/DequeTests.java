import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeTests {

    // unit testing (required)
    public static void main(String[] args) {

        DequeTests t = new DequeTests();

        // isEmpty
        t.test_isEmpty_JustCreated();
        t.test_isEmpty_AfterRemoveLast();
        t.test_isEmpty_AfterRemoveFirst();

        // size
        t.test_size_JustCreated();
        t.test_size_AfterAdd();
        t.test_size_AfterRemove();

        t.test_addFirst();

        t.test_addLast();

        t.test_removeFirst();

        t.test_removeLast();

        t.test_iterator();

        t.scenario_1();

        t.scenario_2();

        StdOut.println("tests succefull complete");
    }

    private void scenario_1() {

        Deque<String> d = new Deque<String>();

        d.addFirst("A");
        if (d.isEmpty())
            throw new UnsupportedOperationException("scenario_1 1 failed");

        d.addLast("B");
        if (d.isEmpty())
            throw new UnsupportedOperationException("scenario_1 2 failed");

        d.removeLast();
        if (d.isEmpty())
            throw new UnsupportedOperationException("scenario_1 3 failed");

        d.removeFirst();
        if (!d.isEmpty())
            throw new UnsupportedOperationException("scenario_1 4 failed");

    }

    private void scenario_2() {

        Deque<Integer> d = new Deque<Integer>();

        d.addFirst(1);
        d.addFirst(2);
        d.removeFirst();
        d.addFirst(4);
        Integer item = d.removeLast();
        if (item != 1)
            throw new UnsupportedOperationException("scenario_2 1 failed");

        Iterator<Integer> iterator = d.iterator();
        if (iterator.next() != 4)
            throw new UnsupportedOperationException("scenario_2 2 failed");

        if (iterator.hasNext())
            throw new UnsupportedOperationException("scenario_2 3 failed");

    }


    private void test_isEmpty_JustCreated() {
        Deque<Integer> d = new Deque<Integer>();
        if (!d.isEmpty())
            throw new UnsupportedOperationException("test_isEmpty_JustCreated failed");
    }

    private void test_isEmpty_AfterRemoveLast() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.removeLast();
        if (!d.isEmpty())
            throw new UnsupportedOperationException("test_isEmpty_AfterRemoveLast failed");
    }

    private void test_isEmpty_AfterRemoveFirst() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        d.removeFirst();
        if (!d.isEmpty())
            throw new UnsupportedOperationException("test_isEmpty_AfterRemoveFirst failed");
    }

    private void test_size_JustCreated() {
        Deque<Integer> d = new Deque<Integer>();
        if (d.size() != 0)
            throw new UnsupportedOperationException("test_size_JustCreated failed");
    }

    private void test_size_AfterAdd() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        if (d.size() != 1)
            throw new UnsupportedOperationException("test_size_AfterAdd 1 failed");
        d.addFirst(2);
        if (d.size() != 2)
            throw new UnsupportedOperationException("test_size_AfterAdd 2 failed");
        d.addLast(-1);
        if (d.size() != 3)
            throw new UnsupportedOperationException("test_size_AfterAdd -1 failed");
        d.addLast(-2);
        if (d.size() != 4)
            throw new UnsupportedOperationException("test_size_AfterAdd -2 failed");
    }

    private void test_size_AfterRemove() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        d.removeFirst();
        if (d.size() != 2)
            throw new UnsupportedOperationException("test_size_AfterRemove 3 failed");
        d.removeLast();
        if (d.size() != 1)
            throw new UnsupportedOperationException("test_size_AfterRemove 1 failed");
        d.removeLast();
        if (d.size() != 0)
            throw new UnsupportedOperationException("test_size_AfterRemove 2 failed");
    }

    private void test_addFirst() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        if (d.removeFirst() != 1)
            throw new UnsupportedOperationException("test_addFirst 1 failed");
        d.addFirst(1);
        d.addFirst(2);
        if (d.removeFirst() != 2)
            throw new UnsupportedOperationException("test_addFirst 2 failed");
        d.removeLast();
        d.addFirst(3);
        if (d.removeLast() != 3)
            throw new UnsupportedOperationException("test_addFirst 3 failed");

        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            d.addFirst(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "test_addFirst null (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("test_addFirst null (no exception) failed");

    }

    private void test_addLast() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        if (d.removeLast() != 1)
            throw new UnsupportedOperationException("test_addLast 1 failed");
        d.addLast(1);
        d.addLast(2);
        if (d.removeLast() != 2)
            throw new UnsupportedOperationException("test_addLast 2 failed");
        d.removeFirst();
        d.addLast(3);
        if (d.removeFirst() != 3)
            throw new UnsupportedOperationException("test_addLast 3 failed");

        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            d.addLast(null);
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof IllegalArgumentException))
                throw new UnsupportedOperationException(
                        "test_addLast null (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("test_addLast null (no exception) failed");
    }

    private void test_removeFirst() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        if (d.removeFirst() != 1)
            throw new UnsupportedOperationException("test_removeFirst 1 failed");
        d.addFirst(1);
        d.addFirst(2);
        if (d.removeFirst() != 2)
            throw new UnsupportedOperationException("test_removeFirst 2 failed");
        d.removeFirst(); // removed 1, now empty
        d.addLast(3);
        if (d.removeFirst() != 3)
            throw new UnsupportedOperationException("test_removeFirst 3 failed");

        // 'd' is empty

        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            int result = d.removeFirst();
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof NoSuchElementException))
                throw new UnsupportedOperationException(
                        "test_removeFirst null (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("test_removeFirst null (no exception) failed");
    }

    private void test_removeLast() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        if (d.removeLast() != 1)
            throw new UnsupportedOperationException("test_removeLast 1 failed");
        d.addLast(1);
        d.addLast(2);
        if (d.removeLast() != 2)
            throw new UnsupportedOperationException("test_removeLast 2 failed");
        d.removeLast();
        d.addFirst(3);
        if (d.removeLast() != 3)
            throw new UnsupportedOperationException("test_removeLast 3 failed");

        // 'd' is empty

        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            int result = d.removeLast();
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof NoSuchElementException))
                throw new UnsupportedOperationException(
                        "test_removeLast null (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("test_removeLast null (no exception) failed");
    }

    private void test_iterator() {
        Deque<Integer> d = new Deque<Integer>();
        for (int item : d)
            throw new UnsupportedOperationException("test_iterator empty failed");

        Iterator<Integer> iterator = d.iterator();
        boolean exceptionCatched;
        exceptionCatched = false;
        try {
            iterator.remove();
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof UnsupportedOperationException))
                throw new UnsupportedOperationException(
                        "test_iterator remove (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("test_iterator remove (no exception) failed");

        if (iterator.hasNext())
            throw new UnsupportedOperationException("test_iterator hasNext failed");

        exceptionCatched = false;
        try {
            int result = iterator.next();
        }
        catch (Exception e) {
            exceptionCatched = true;
            if (!(e instanceof NoSuchElementException))
                throw new UnsupportedOperationException(
                        "test_iterator next (incorrect exception) failed");
        }
        if (!exceptionCatched)
            throw new UnsupportedOperationException("test_iterator next (no exception) failed");

        d.addLast(1);
        d.addLast(2);
        d.addLast(3);
        int i = 0;
        for (int item : d)
            if (++i != item)
                throw new UnsupportedOperationException("test_iterator 3 failed");
    }

}
