package gad.simplesort;

public class Quicksort extends SortAlgorithm {
	private PivotFinder pivotFinder;
	private int selectionSortSize;
	private Selectionsort selectionSort;

	public Quicksort(PivotFinder pivotFinder, int selectionSortSize) {
		this.pivotFinder = pivotFinder;
		this.selectionSortSize = selectionSortSize;
		selectionSort = new Selectionsort();
	}

	@Override
	public void sort(int[] numbers, Result result, int from, int to) {
		if (from >= to) return;

		result.startQuicksort(numbers, from, to);

		if (to - from + 1 <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int pivotIndex = pivotFinder.findPivot(numbers, from, to);
		if (pivotIndex != to) {
			swap(numbers, pivotIndex, to);
		}

		int p = numbers[to];
		int indexL = from - 1;
		int indexR = to;

		while (indexL < indexR) {
			do indexL++; while (numbers[indexL] < p);
			do indexR--; while (numbers[indexR] > p && indexR > from);

			if (indexL < indexR) {
				swap(numbers, indexL, indexR);
			}
		}

		swap(numbers, indexL, to);

		result.logPartialArray(numbers, from, indexL - 1);
		result.logPartialArray(numbers, indexL + 1, to);

		sort(numbers, result, from, indexL - 1);
		sort(numbers, result, indexL + 1, to);
	}


	@Override
	public String toString() {
		return "Quicksort (Pivot: " + pivotFinder + ", Selectionsort for: " + selectionSortSize + ")";
	}
}
