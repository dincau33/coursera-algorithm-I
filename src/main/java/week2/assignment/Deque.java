package week2.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int size = 0;
	private Node<Item> head = null;
	private Node<Item> tail = null;

	private class Node<Item> {
		private final Item item;
		private Node<Item> next;
		private Node<Item> prev;

		public Node(Item item, Node<Item> prev, Node<Item> next) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}

	// construct an empty deque
	public Deque() {
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) throw new IllegalArgumentException();
		Node<Item> oldHead = head;
		head = new Node<>(item, null, oldHead);
		if (isEmpty()) {
			tail = head;
		} else {
			oldHead.prev = head;
		}
		size++;
	}

	// add the item to the end
	public void addLast(Item item) {
		if (item == null) throw new IllegalArgumentException();
		Node<Item> oldTail = tail;
		tail = new Node<>(item, oldTail, null);
		if (isEmpty()) {
			head = tail;
		} else {
			oldTail.next = tail;
		}
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = head.item;
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		size--;
		return item;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = tail.item;
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return item;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeIterator(head);
	}

	private class DequeIterator implements Iterator<Item> {

		private Node<Item> current;

		private DequeIterator(Node<Item> head) {
			current = head;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

}
