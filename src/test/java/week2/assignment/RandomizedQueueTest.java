package week2.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RandomizedQueueTest {

	private RandomizedQueue<String> queue;

	@BeforeEach
	void setup() {
		queue = new RandomizedQueue<>();
	}

	@Test
	void newQueueIsEmpty() {
		assertThat(queue.isEmpty()).isTrue();
		assertThat(queue.size()).isEqualTo(0);
	}

	@Test
	void addItemToAnEmptyQueue() {
		queue.enqueue("Str");
		assertThat(queue.isEmpty()).isFalse();
		assertThat(queue).hasSize(1);
	}

	@Test
	void removeItemFromOneSizeDeque() {
		queue.enqueue("Str");
		assertThat(queue.dequeue()).isEqualTo("Str");
		assertThat(queue.isEmpty()).isTrue();
		assertThat(queue).hasSize(0).doesNotContain("Str");
	}

	@Test
	void addMultipleItemsToAnEmptyDeque() {
		queue.enqueue("Str1");
		queue.enqueue("Str2");
		queue.enqueue("Str3");
		queue.enqueue("Str4");
		assertThat(queue.isEmpty()).isFalse();
		assertThat(queue).hasSize(4).containsExactlyInAnyOrder("Str1", "Str2", "Str3", "Str4");
	}

	@Test
	void removeFirstItemFromMultipleItemsDeque() {
		queue.enqueue("Str1");
		queue.enqueue("Str2");
		queue.enqueue("Str3");
		queue.enqueue("Str4");
		String str = queue.dequeue();
		assertThat(queue.isEmpty()).isFalse();
		assertThat(queue).hasSize(3).doesNotContain(str);
	}

	@Test
	void removeAllTheItemsFromMultipleItemsDeque() {
		queue.enqueue("Str1");
		queue.enqueue("Str2");
		queue.enqueue("Str3");
		queue.enqueue("Str4");
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		assertThat(queue.isEmpty()).isTrue();
	}

	@Test
	void failToEnqueueNullItem() {
		assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
	}

	@Test
	void failToDequeueFromEmptyQueue() {
		assertThrows(NoSuchElementException.class, queue::dequeue);
	}

	@Test
	void failToGetSampleFromEmptyQueue() {
		assertThrows(NoSuchElementException.class, queue::sample);
	}

	@Test
	void getSampleFromOneItemQueue() {
		queue.enqueue("Str");
		assertThat(queue.sample()).isEqualTo("Str");
	}

	@Test
	void getSampleFromMultipleItemQueue() {
		queue.enqueue("Str1");
		queue.enqueue("Str2");
		queue.enqueue("Str3");
		queue.enqueue("Str4");
		assertThat(queue.sample()).isIn("Str1", "Str2", "Str3", "Str4");
	}

	@Test
	void failToIterateAnEmptyQueue() {
		RandomizedQueue queue = new RandomizedQueue<String>();
		Iterator iterator = queue.iterator();
		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(UnsupportedOperationException.class, iterator::remove);
	}
}