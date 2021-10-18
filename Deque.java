/* *****************************************************************************
 *  Name:    Devin Plumb
 *  NetID:   dplumb
 *  Precept: P06
 *
 *  Description:  A data type for a deque, a generalization of a stack and a
 *                queue that supports adding and removing items from either the
 *                front or the back of the data structure.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private DequeNode first; // first node in the deque
    private DequeNode last; // last node in the deque
    private int size; // number of nodes in the deque

    private class DequeNode {
        private final Item data; // the actual item associated with the node
        private DequeNode next; // the node linked in front of the present node
        private DequeNode previous; // the node linked behind the present node

        // constructor for an unconnected Node
        public DequeNode(Item data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument");
        DequeNode newFirst = new DequeNode(item);
        if (size == 0) last = newFirst;
        else {
            DequeNode oldFirst = first;
            newFirst.previous = oldFirst;
            oldFirst.next = newFirst;
        }
        first = newFirst;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument");
        DequeNode newLast = new DequeNode(item);
        if (size == 0) first = newLast;
        else {
            DequeNode oldLast = last;
            newLast.next = oldLast;
            oldLast.previous = newLast;
        }
        last = newLast;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException("no item to remove");
        Item oldFirst = first.data;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first.previous.next = null;
            first = first.previous;
        }
        size--;
        return oldFirst;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException("no item to remove");
        Item oldLast = last.data;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            last.next.previous = null;
            last = last.next;
        }
        size--;
        return oldLast;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // iterator data type
    private class DequeIterator implements Iterator<Item> {
        private DequeNode current = first; // the current node the iterator is on

        // constructor
        public DequeIterator() {
        }

        // is there a next item in the iterator?
        public boolean hasNext() {
            return current != null;
        }

        // this method is optional in Iterator interface
        public void remove() {
            throw new UnsupportedOperationException();
        }

        // returns the next item in the iterator (and advances the iterator)
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.previous;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);

        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 2 * size; i++) {
            if (StdRandom.uniform(2) > 1)
                deque.addFirst(StdRandom.uniform(100));
            else deque.addLast(StdRandom.uniform(100));
        }

        for (int i = 0; i < size; i++) {
            if (StdRandom.uniform(2) > 1)
                StdOut.print(deque.removeFirst() + " ");
            else StdOut.print(deque.removeLast() + " ");
        }
        StdOut.println();

        StdOut.println(deque.size());
        StdOut.println(deque.isEmpty());

        Deque<Integer> deque2 = new Deque<Integer>();
        for (int i = 0; i < size; i++) {
            if (StdRandom.uniform(2) == 0) deque2.addFirst(i);
            else deque2.addLast(i);
        }

        for (int i = 0; i < size; i++)
            StdOut.print(deque2.removeFirst() + " ");
        StdOut.println();

        for (int i = 0; i < size; i++)
            deque2.addFirst(i);

        for (int i = 0; i < size; i++) {
            if (StdRandom.uniform(2) == 0)
                StdOut.print(deque2.removeFirst() + " ");
            else StdOut.print(deque2.removeLast() + " ");
        }
        StdOut.println();

        StdOut.println(deque2.size());
        StdOut.println(deque2.isEmpty());

        StdOut.print("Deque Iterator: ");
        Iterator<Integer> iter = deque.iterator();
        while (iter.hasNext())
            StdOut.print(iter.next() + " ");
        StdOut.println();
    }

}
