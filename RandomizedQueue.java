/* *****************************************************************************
 *  Name:    Devin Plumb
 *  NetID:   dplumb
 *  Precept: P06
 *
 *  Description:  A data type where the item removed is chosen uniformly at
 *                random among items in the data structure.
 *
 *                CITATION: RestackingArray.java, particularly the resizing
 *                method, from Ch. 1.3
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a; // the data structure that holds enqueued items
    private int n; // the number of items actually held in a

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[1]; // compile warning
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument");
        if (n == a.length) resize(2 * a.length);
        a[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int k = StdRandom.uniform(n);
        Item item = a[k];
        a[k] = a[n - 1];
        a[n - 1] = null; // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) resize(a.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[StdRandom.uniform(n)];
    }

    // CITATION, ResizingArrayStack.java resize method
    // textbook implementation, chapter 1.3
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity]; // compile warning
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    // randomized iterator
    private class RandomizedIterator implements Iterator<Item> {

        private int i = 0; // counter
        private final Item[] b; // specific iterator ordering

        // constructor
        public RandomizedIterator() {
            resize(n);
            StdRandom.shuffle(a);
            b = a;
        }

        // have we iterated through all items
        public boolean hasNext() {
            return i < n;
        }

        // this method is optional in Iterator interface
        public void remove() {
            throw new UnsupportedOperationException();
        }

        // returns the next item in the iterator (and advances the iterator)
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return b[i++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);

        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < size; i++)
            queue.enqueue(i);

        StdOut.println(queue.size());
        StdOut.println(queue.isEmpty());
        StdOut.println(queue.sample());

        for (int i = 0; i < size; i++)
            StdOut.print(queue.dequeue() + " ");
        StdOut.println();

        StdOut.println(queue.size());
        StdOut.println(queue.isEmpty());

        for (int i = 0; i < size; i++)
            queue.enqueue(i);

        StdOut.print("Randomized Queue Iterator: ");
        Iterator<Integer> iter = queue.iterator();
        while (iter.hasNext())
            StdOut.print(iter.next() + " ");
        StdOut.println();
    }

}
