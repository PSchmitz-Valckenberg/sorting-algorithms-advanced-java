package gad.simplesort;

import java.util.Random;
import java.util.Arrays;

public interface DualPivotFinder {

	int[] findPivot(int[] numbers, int from, int to);

	static DualPivotFinder getFirstLastPivot() {
		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				return new int[] { from, to };
			}
			@Override
			public String toString() {
				return "The first and last element";
			}
		};
	}

	static DualPivotFinder getRandomPivot(int seed) {
		Random random = new Random(seed);

		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				int i1 = from + random.nextInt(to - from + 1);
				int i2;
				do {
					i2 = from + random.nextInt(to - from + 1);
				} while (i1 == i2);
				return new int[] { i1, i2 };
			}

			@Override
			public String toString() {
				return "Two random elements";
			}
		};
	}

	static DualPivotFinder getMedianPivotFront(int numberOfConsideredElements) {
		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				int len = to - from + 1;
				int count = Math.min(numberOfConsideredElements, len);

				int[][] values = new int[count][2];
				for (int i = 0; i < count; i++) {
					values[i][0] = numbers[from + i];
					values[i][1] = from + i;
				}

				Arrays.sort(values, (a, b) -> Integer.compare(a[0], b[0]));

				int low = values[1][1];
				int high = values[count - 2][1];
				return new int[] { low, high };
			}

			@Override
			public String toString() {
				return "The thirds of the first " + numberOfConsideredElements + " elements";
			}
		};
	}

	static DualPivotFinder getMedianPivotDistributed(int numberOfConsideredElements) {
		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				int len = to - from + 1;
				int count = Math.min(numberOfConsideredElements, len);

				if (count < 2) return new int[] { from, to };

				int dist = (len - 1) / (count - 1);
				int[][] values = new int[count][2];

				for (int i = 0; i < count; i++) {
					int idx = from + i * dist;
					if (idx > to) idx = to;
					values[i][0] = numbers[idx];
					values[i][1] = idx;
				}

				Arrays.sort(values, (a, b) -> Integer.compare(a[0], b[0]));

				int low = values[1][1];
				int high = values[count - 2][1];
				return new int[] { low, high };
			}

			@Override
			public String toString() {
				return "The thirds of " + numberOfConsideredElements + " elements distributed thoughout the array";
			}
		};
	}
}
