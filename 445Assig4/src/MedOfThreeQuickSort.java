
/*
 *  Natalie Boehm, CS 0445, Assignment 4: QuickSort Comparison
 */

public class MedOfThreeQuickSort {
	private static int minSize;
	
	public MedOfThreeQuickSort(int size) {
		minSize = size;
	}
	
	public static <T extends Comparable<? super T>> void quickSort(T[] array, int n) {
		quickSort(array, 0, n - 1);
	}
	
	public static <T extends Comparable<? super T>> void quickSort(T[] a, int first, int last) {
		if (last - first + 1 < minSize) {
			insertionSort(a, first, last);
		} else {
			int pivotIndex = partition(a, first, last);
			
			quickSort(a, first, pivotIndex - 1);
			quickSort(a, pivotIndex + 1, last);
		}
	}
	
	private static <T extends Comparable<? super T>> int partition(T[] a, int first, int last) {
		int mid = (first + last)/2;
		sortFirstMiddleLast(a, first, mid, last);
		
		swap(a, mid, last - 1);
		int pivotIndex = last - 1;
		T pivot = a[pivotIndex];
		
		int indexFromLeft = first + 1;
		int indexFromRight = last - 2;
		boolean done = false;
		
		while (!done) {
			while (a[indexFromLeft].compareTo(pivot) < 0)
				indexFromLeft++;
			
			while (a[indexFromRight].compareTo(pivot) > 0)
				indexFromRight--;
			
			assert a[indexFromLeft].compareTo(pivot) >= 0 && a[indexFromRight].compareTo(pivot) <= 0;
			
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
	
	private static <T extends Comparable<? super T>> void sortFirstMiddleLast(T[] a, int first, int mid, int last) {
		order(a, first, mid);
		order(a, mid, last);
		order(a, first, mid);
	}
	
	private static <T extends Comparable<? super T>> void order(T[] a, int i, int j) {
		if (a[i].compareTo(a[j]) > 0)
			swap(a, i, j);
	}
	
	private static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static <T extends Comparable<? super T>> void insertionSort(T[] a, int n) {
		insertionSort(a, 0, n - 1);
	}
	
	public static <T extends Comparable<? super T>> void insertionSort(T[] a, int first, int last) {
		int unsorted, index;
		
		for (unsorted = first + 1; unsorted <= last; unsorted++) {
			T firstUnsorted = a[unsorted];
			insertInOrder(firstUnsorted, a, first, unsorted - 1);
		}
	}
	
	private static <T extends Comparable<? super T>> void insertInOrder(T element, T[] a, int begin, int end) {
		int index;
		
		for (index = end; (index >= begin) && (element.compareTo(a[index]) < 0); index--)
			a[index + 1] = a[index];
		
		a[index + 1] = element;
	}
}
