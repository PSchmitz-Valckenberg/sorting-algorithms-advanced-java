package gad.simplesort;

public class DualPivotQuicksort extends SortAlgorithm {
	private DualPivotFinder pivotFinder;
	private int selectionSortSize;
	private Selectionsort selectionSort;

	public DualPivotQuicksort(DualPivotFinder pivotFinder, int selectionSortSize) {
		this.pivotFinder = pivotFinder;
		this.selectionSortSize = selectionSortSize;
		// TODO: Selectionsort Optimierung
		selectionSort = new Selectionsort();
	}

	@Override
	public void sort(int[] numbers, Result result, int from, int to) {
		if (from >= to) return;

		result.startDualPivotQuicksort(numbers, from, to);

		if (to - from + 1 <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int[] pivots = pivotFinder.findPivot(numbers, from, to);
		int p1 = pivots[0];
		int p2 = pivots[1];

		// sicherstellen dass p1 < p2
		if (numbers[p1] > numbers[p2]) {
			int tmp = p1;
			p1 = p2;
			p2 = tmp;
		}

		// Pivots an die Enden
		swap(numbers, from, p1);
		swap(numbers, to, p2);

		int pivot1 = numbers[from];
		int pivot2 = numbers[to];

		int lt = from + 1;
		int gt = to - 1;
		int i = lt;

		while (i <= gt) {
			if (numbers[i] < pivot1) {
				swap(numbers, i, lt);
				lt++;
			} else if (numbers[i] > pivot2) {
				swap(numbers, i, gt);
				gt--;
				i--; // nochmal anschauen
			}
			i++;
		}

		swap(numbers, from, lt - 1);
		swap(numbers, to, gt + 1);

		result.logPartialArray(numbers, from, lt - 2);
		result.logPartialArray(numbers, lt, gt);
		result.logPartialArray(numbers, gt + 2, to);

		sort(numbers, result, from, lt - 2);
		sort(numbers, result, lt, gt);
		sort(numbers, result, gt + 2, to);
	}


	@Override
	public String toString() {
		return "DualPivotQuicksort (Pivot: " + pivotFinder + ", Selectionsort for: " + selectionSortSize + ")";
	}
}
