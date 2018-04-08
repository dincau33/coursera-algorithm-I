package week2.assignment;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void isEmpty() {
        Deque deque = new Deque<String>();
        assertTrue(deque.isEmpty());
        deque.addFirst("Str");
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
        deque.addLast("Str");
        assertFalse(deque.isEmpty());
        deque.removeLast();
        assertTrue(deque.isEmpty());
        deque.addFirst("Str");
        deque.addFirst("Str");
        deque.addLast("Str");
        deque.removeFirst();
        deque.addFirst("Str");
        deque.removeFirst();
        deque.removeLast();
        assertFalse(deque.isEmpty());
    }

    @Test
    void size() {
        Deque deque = new Deque<String>();
        assertEquals(0, deque.size());
        deque.addFirst("Str");
        assertEquals(1, deque.size());
        deque.removeFirst();
        assertEquals(0, deque.size());
        deque.addLast("Str");
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
        deque.addFirst("Str");
        deque.addFirst("Str");
        deque.addFirst("Str");
        deque.removeFirst();
        deque.addFirst("Str");
        deque.removeFirst();
        deque.removeLast();
        assertEquals(1, deque.size());
    }

    @Test
    void addFirst() {
        Deque deque = new Deque<String>();
        assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
    }

    @Test
    void addLast() {
        Deque deque = new Deque<String>();
        assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
    }

    @Test
    void removeFirst() {
        Deque deque = new Deque<String>();
        assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
    }

    @Test
    void removeLast() {
        Deque deque = new Deque<String>();
        assertThrows(NoSuchElementException.class, () -> deque.removeLast());
    }

    String dequeToString(Deque<String> d) {
        StringBuilder s = new StringBuilder();
        for (Object item : d) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    @Test
    void iterator() {
        Deque deque = new Deque<String>();
        Iterator iterator = deque.iterator();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
        assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
        deque.addFirst("Str2");
        deque.addLast("Str3");
        deque.addFirst("Str1");
        deque.addLast("Str4");
        assertEquals("Str1 Str2 Str3 Str4 ",dequeToString(deque));
        deque.removeFirst();
        deque.addFirst("Str1");
        deque.removeLast();
        deque.addLast("Str4");
        assertEquals("Str1 Str2 Str3 Str4 ",dequeToString(deque));
    }
}