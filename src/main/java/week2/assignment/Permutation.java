package week2.assignment;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Permutation {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		if (k > 0) {
			RandomizedQueue<String> queue = new RandomizedQueue<>();
			while (!StdIn.isEmpty()) queue.enqueue(StdIn.readString());
			for (int i = 0; i < k; i++) StdOut.println(queue.dequeue());
		}
	}
}
