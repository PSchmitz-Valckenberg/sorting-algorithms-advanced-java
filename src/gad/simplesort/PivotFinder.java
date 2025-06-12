package gad.simplesort;

import java.util.Random;
public interface PivotFinder {

	int findPivot(int[] numbers, int from, int to);

	static PivotFinder getLastPivot() {
		return new PivotFinder() {

			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return to;
			}

			@Override
			public String toString() {
				return "The last element";
			}
		};
	}

	static PivotFinder getMidPivot() {
		return new PivotFinder() {

			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return from + (to - from) / 2;
			}

			@Override
			public String toString() {
				return "The middle element";
			}
		};
	}

	static PivotFinder getRandomPivot(int seed) {
		Random random = new Random(seed);

		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return from + random.nextInt(to - from + 1);
			}

			@Override
			public String toString() {
				return "A random element";
			}
		};
	}

	static PivotFinder getMedianPivotFront(int numberOfConsideredElements) {
		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				int actualElements = Math.min(numberOfConsideredElements, to - from + 1);
				int[] indices = new int[actualElements];

				for (int i = 0; i < actualElements; i++) {
					indices[i] = from + i;
				}

				int[][] valuesWithIndices = new int[actualElements][2];
				for (int i = 0; i < actualElements; i++) {
					valuesWithIndices[i][0] = numbers[indices[i]];
					valuesWithIndices[i][1] = indices[i];
				}

				java.util.Arrays.sort(valuesWithIndices, java.util.Comparator.comparingInt(a -> a[0]));
				int medianIndex = actualElements / 2;
				return valuesWithIndices[medianIndex][1];
			}

			@Override
			public String toString() {
				return "The median of the first " + numberOfConsideredElements + " elements";
			}
		};
	}


	static PivotFinder getMedianPivotDistributed(int numberOfConsideredElements) {
		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				int length = to - from + 1;
				int actualElements = Math.min(numberOfConsideredElements, length);

				if (actualElements <= 0)
					return from;

				int maxDistance = (length - 1) / (actualElements - 1);

				int[] indices = new int[actualElements];
				for (int i = 0; i < actualElements; i++) {
					indices[i] = from + i * maxDistance;
				}

				int[][] valuesWithIndices = new int[actualElements][2];
				for (int i = 0; i < actualElements; i++) {
					valuesWithIndices[i][0] = numbers[indices[i]];
					valuesWithIndices[i][1] = indices[i];
				}

				java.util.Arrays.sort(valuesWithIndices, java.util.Comparator.comparingInt(a -> a[0]));

				int medianIndex = actualElements / 2;

				return valuesWithIndices[medianIndex][1];
			}

			@Override
			public String toString() {
				return "The median of " + numberOfConsideredElements + " elements distributed throughout the array";
			}
		};
	}

}
