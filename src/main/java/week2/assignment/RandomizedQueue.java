package week2.assignment;

import edu.princeton.cs.introcs.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size = 0;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        if (capacity > size) {
            Item[] temp = (Item[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                temp[i] = queue[i];
            }
            queue = temp;
        }
    }

    private int generateRandomIndex() {
        return StdRandom.uniform(0, size);
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int index = generateRandomIndex();
        Item item = queue[index];
        queue[index] = queue[size - 1];
        queue[size - 1] = null;
        size--;
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return queue[generateRandomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final int[] randomIndex;
        private int count;

        public RandomizedQueueIterator() {
            count = size - 1;
            randomIndex = new int[size];
            for (int i = 0; i < size; i++) {
                randomIndex[i] = i;
            }
            StdRandom.shuffle(randomIndex);
        }

        public boolean hasNext() {
            return count >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return queue[randomIndex[count--]];
        }
    }

}
