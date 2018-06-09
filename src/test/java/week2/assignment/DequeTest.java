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
	void setup() {
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
		assertThat(deque).hasSize(1);
	}

	@Test
	void addLastItemToAnEmptyDeque() {
		deque.addLast("Str");
		assertThat(deque.isEmpty()).isFalse();
		assertThat(deque).hasSize(1).containsOnly("Str");
	}

	@Test
	void removeFirstItemFromOneSizeDeque() {
		deque.addFirst("Str");
		assertThat(deque.removeFirst()).isEqualTo("Str");
		assertThat(deque.isEmpty()).isTrue();
		assertThat(deque).hasSize(0).doesNotContain("Str");
	}

	@Test
	void removeLastItemFromOneSizeDeque() {
		deque.addFirst("Str");
		assertThat(deque.removeLast()).isEqualTo("Str");
		assertThat(deque.isEmpty()).isTrue();
		assertThat(deque).hasSize(0).doesNotContain("Str");
	}

	@Test
	void addMultipleItemsToAnEmptyDeque() {
		deque.addFirst("Str1");
		deque.addLast("Str2");
		deque.addFirst("Str3");
		deque.addLast("Str4");
		assertThat(deque.isEmpty()).isFalse();
		assertThat(deque).hasSize(4).containsExactly("Str3", "Str1", "Str2", "Str4");
	}

	@Test
	void removeFirstItemFromMultipleItemsDeque() {
		deque.addFirst("Str1");
		deque.addLast("Str2");
		deque.addFirst("Str3");
		deque.addLast("Str4");
		assertThat(deque.removeFirst()).isEqualTo("Str3");
		assertThat(deque.isEmpty()).isFalse();
		assertThat(deque).hasSize(3).containsExactly("Str1", "Str2", "Str4");;
	}

	@Test
	void removeLastItemFromMultipleItemsDeque() {
		deque.addFirst("Str1");
		deque.addLast("Str2");
		deque.addFirst("Str3");
		deque.addLast("Str4");
		assertThat(deque.removeLast()).isEqualTo("Str4");
		assertThat(deque.isEmpty()).isFalse();
		assertThat(deque).hasSize(3).containsExactly("Str3", "Str1", "Str2");;
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
	void failToIterateAnEmptyDeque() {
		Iterator iterator = deque.iterator();
		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(UnsupportedOperationException.class, iterator::remove);
	}
}