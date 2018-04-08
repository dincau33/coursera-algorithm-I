package week2.assignment;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    @Test
    void isEmpty() {
        RandomizedQueue queue = new RandomizedQueue<String>();
        assertTrue(queue.isEmpty());
        queue.enqueue("Str");
        assertFalse(queue.isEmpty());
        queue.enqueue("Str");
        queue.enqueue("Str");
        assertFalse(queue.isEmpty());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void size() {
        RandomizedQueue queue = new RandomizedQueue<String>();
        assertEquals(0, queue.size());
        queue.enqueue("Str");
        assertEquals(1, queue.size());
        queue.enqueue("Str");
        queue.enqueue("Str");
        assertEquals(3, queue.size());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void enqueue() {
        RandomizedQueue queue = new RandomizedQueue<String>();
        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
    }

    @Test
    void dequeue() {
        RandomizedQueue queue = new RandomizedQueue<String>();
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    void sample() {
        RandomizedQueue queue = new RandomizedQueue<String>();
        assertThrows(NoSuchElementException.class, () -> queue.sample());
        queue.enqueue("Str");
        assertEquals("Str", queue.sample());
    }

    String queueToString(RandomizedQueue<String> q) {
        StringBuilder s = new StringBuilder();
        for (Object item : q) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    @Test
    void iterator() {
        RandomizedQueue queue = new RandomizedQueue<String>();
        Iterator iterator = queue.iterator();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
        assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
        queue.enqueue("Str");
        assertEquals("Str ", queueToString(queue));
    }
}