package gad.simplesort;

public class main {
    public static void main(String[] args) {
        PivotFinder pivotFinder = PivotFinder.getMedianPivotDistributed(3);
        int[] arr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result1 = pivotFinder.findPivot(arr1, 0, 2);
        System.out.println("Test 1: Erwartet 1, Ergebnis: " + result1);

        pivotFinder = PivotFinder.getMedianPivotDistributed(5);
        int[] arr2 = {9, 1, 8, 5, 2, 3, 5, 1, 0, 7};
        int result2 = pivotFinder.findPivot(arr2, 0, 4);
        System.out.println("Test 2: Erwartet 3, Ergebnis: " + result2);

        int result3 = pivotFinder.findPivot(arr2, 2, 8);
        System.out.println("Test 3: Erwartet 3 oder 6, Ergebnis: " + result3);
    }

}
