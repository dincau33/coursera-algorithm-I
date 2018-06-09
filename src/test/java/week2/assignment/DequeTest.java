package week2.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class DequeTest {

	private Deque<String> deque;

	@BeforeEach
	void setup(){
		deque = new Deque<>();
	}

	@Test
	void newDequeIsEmpty() {
		assertThat(deque.isEmpty()).isTrue();
		assertThat(deque.size()).isEqualTo(0);
	}

	@Test
	void addFirstItemToAnEmptyDeque() {
		deque.addFirst("Str");
		assertThat(deque.isEmpty()).isFalse();
		assertThat(deque.size()).isEqualTo(1);
	}

	@Test
	void addLastItemToAnEmptyDeque() {
		deque.addLast("Str");
		assertThat(deque.isEmpty()).isFalse();
		assertThat(deque.size()).isEqualTo(1);
	}

	@Test
	void removeFirstItemFromOneSizeDeque() {
		deque.addFirst("Str");
		deque.removeFirst();
		assertThat(deque.isEmpty()).isTrue();
		assertThat(deque.size()).isEqualTo(0);
	}

	@Test
	void removeLastItemFromOneSizeDeque() {
		deque.addFirst("Str");
		deque.removeLast();
		assertThat(deque.isEmpty()).isTrue();
		assertThat(deque.size()).isEqualTo(0);
	}

	@Test
	void addMultipleItemsToAnEmptyDeque(){
		deque.addFirst("Str1");
		deque.addLast("Str2");
		deque.addFirst("Str3");
		deque.addLast("Str4");
		assertThat(deque.isEmpty()).isFalse();
		assertThat(deque.size()).isEqualTo(4);
	}

	@Test
	void failToAddFirstNullItemToDeque() {
		assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
	}

	@Test
	void failToAddLastNullItemToDeque() {
		assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
	}

	@Test
	void failToRemoveFirstItemFromAnEmptyDeque() {
		assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
	}

	@Test
	void failToRemoveLastItemFromAnEmptyDeque() {
		assertThrows(NoSuchElementException.class, () -> deque.removeLast());
	}

	@Test
	void failToIterateAnEmptyDeque(){
		Iterator iterator = deque.iterator();
		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(UnsupportedOperationException.class, iterator::remove);
	}

	@Test
	void useIteratorToPrintDequeAsAString() {
		deque.addFirst("Str2");
		deque.addLast("Str3");
		deque.addFirst("Str1");
		deque.addLast("Str4");
		assertThat(dequeToString(deque)).isEqualTo("Str1 Str2 Str3 Str4 ");
	}

	private String dequeToString(Deque<String> d) {
		StringBuilder s = new StringBuilder();
		for (Object item : d) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}
}