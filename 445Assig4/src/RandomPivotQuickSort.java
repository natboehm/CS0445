import java.util.Random;

/*
 *  Natalie Boehm, CS 0445, Assignment 4: QuickSort Comparison
 */

public class RandomPivotQuickSort {
	
	public static <T extends Comparable<? super T>> void quickSort(T[] array, int n) {
		quickSort(array, 0, n - 1);
	}
	
	public static <T extends Comparable<? super T>> int getPivotIndex(T[] array, int first, int last) {
		int randPivotIndex = 0;
		Random r = new Random();
		
		randPivotIndex = r.nextInt((last - first) + 1) + first;
		
		return randPivotIndex;
	}
	
	public static <T extends Comparable<? super T>> void quickSort(T[] array, int first, int last) {
		if (first < last) {
			int randPivotIndex = getPivotIndex(array, first, last);
			swap(array, randPivotIndex, last);
			int pivotIndex = partition(array, first, last);
			
			quickSort(array, first, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, last);
		}
	}
	
	private static <T extends Comparable<? super T>> int partition(T[] a, int first, int last) {
		int pivotIndex = last;
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
	
	public static <T extends Comparable<? super T>> String toString(T[] array) {
		StringBuilder s = new StringBuilder();
		T data;
		
		for (int i = 0; i < array.length; i++) {
			data = array[i];
			s.append(data + " ");
		}
		
		return s.toString();
	}
}
