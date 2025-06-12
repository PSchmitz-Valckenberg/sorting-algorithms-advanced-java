package gad.simplesort;

public class Mergesort extends SortAlgorithm {
	private int selectionSortSize;
	private Selectionsort selectionSort;

	public Mergesort(int selectionSortSize) {
		this.selectionSortSize = selectionSortSize;
		selectionSort = new Selectionsort();
	}

	@Override
	public void sort(int[] numbers, Result result, int from, int to) {
		if (from >= to) return;

		// Hilfsarray nur einmal erstellen, so groß wie nötig
		int[] helper = new int[to - from + 1];
		sort(numbers, result, from, to, helper);
	}

	public void sort(int[] numbers, Result result, int from, int to, int[] helper) {
		if (from >= to) return;

		result.startMergesort(numbers, from, to);

		// Früh beenden, wenn schon sortiert
		boolean sorted = true;
		for (int i = from; i < to; i++) {
			if (numbers[i] > numbers[i + 1]) {
				sorted = false;
				break;
			}
		}
		if (sorted) return;

		// Jetzt prüfen: kleiner als Grenze → Selectionsort
		if (to - from + 1 <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int mid = (from + to) / 2;
		sort(numbers, result, from, mid, helper);
		sort(numbers, result, mid + 1, to, helper);

		merge(numbers, from, mid, to, helper);
		result.logPartialArray(numbers, from, to);
	}


	private void merge(int[] numbers, int left, int mid, int right, int[] helper) {
		int i = left;
		int j = mid + 1;
		int k = 0;

		while (i <= mid && j <= right) {
			if (numbers[i] <= numbers[j]) {
				helper[k++] = numbers[i++];
			} else {
				helper[k++] = numbers[j++];
			}
		}

		while (i <= mid) {
			helper[k++] = numbers[i++];
		}

		while (j <= right) {
			helper[k++] = numbers[j++];
		}

		// Zurückkopieren
		for (int x = 0; x < k; x++) {
			numbers[left + x] = helper[x];
		}
	}

	@Override
	public String toString() {
		return "Mergesort with helper array (Selectionsort for: " + selectionSortSize + ")";
	}
}
