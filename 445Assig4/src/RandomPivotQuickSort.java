import java.util.Random;

/*
 *  Natalie Boehm, CS 0445, Assignment 4: QuickSort Comparison
 */

public class RandomPivotQuickSort {
	
	public static <T extends Comparable<? super T>> void quickSort(T[] array, int n) {
		quickSort(array, 0, n - 1);
	}
	
	public static <T extends Comparable<? super T>> int getPivotIndex(T[] array, int first, int last) {
		int pivotIndex = 0;
		Random r = new Random();
		
		pivotIndex = r.nextInt(array.length);
		
		return pivotIndex;
	}
	
	public static <T extends Comparable<? super T>> void quickSort(T[] array, int first, int last) {
		if (first < last) {
			int pivotIndex = partition(array, first, last);
			
			quickSort(array, first, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, last);
		}
	}
	
	private static <T extends Comparable<? super T>> int partition(T[] a, int first, int last) {
		int pivotIndex = getPivotIndex(a, first, last);
		T pivot = a[pivotIndex];
		
		int indexFromLeft = first;
		int indexFromRight = last - 1;
		boolean done = false;
		
		while (!done) {
			while (a[indexFromLeft].compareTo(pivot) < 0)
				indexFromLeft++;
			
			while (a[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first)
				indexFromRight--;
			
			if (indexFromLeft < indexFromRight) {
				swap(a, indexFromLeft, indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			} else {
				done = true;
			}
		}
		
		swap(a, pivotIndex, indexFromLeft);
		pivotIndex = indexFromLeft;
		
		return pivotIndex;
	}
	
	private static void swap(Object [] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}	
}
