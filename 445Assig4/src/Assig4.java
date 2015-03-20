import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/* 
 *  Natalie Boehm, CS 0445, Assignment 4: QuickSort Comparison 
 */

public class Assig4 {
	static Scanner inScan = new Scanner(System.in);
	
	public static void main(String [] args) {
		inputTestInfo();
	}
	
	public static void inputTestInfo() {
		int arraySize 	 = inputArraySize();
		int numberTrials = inputTrialNumber();
		String fileName  = inputFileName();
		// TODO sort arrays trialNumber times, average trials, print out average
		
		fillArray(arraySize, numberTrials);
	}
	
	public static void fillArray(int arraySize, int numberTrials) {
		Integer[] alreadySorted = fillAlreadySorted(arraySize);
		Integer[] reverseSorted = fillReverseSorted(arraySize);
		Integer[] random = null;
		sortArrays(alreadySorted, reverseSorted, random, arraySize, numberTrials);
	}
	
	public static void sortArrays(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials) {
		for (int i = 0; i < numberTrials; i++) {
			reverseSorted = copyArray(reverseSorted, arraySize);
			random 		  = fillRandom(arraySize);
			useSimpleQuick(alreadySorted, reverseSorted, random, arraySize, numberTrials, i);
			
			reverseSorted = copyArray(reverseSorted, arraySize);
			random 		  = copyArray(random, arraySize);
			useMedOfThreeA(alreadySorted, reverseSorted, random, arraySize, numberTrials, i);
			
			reverseSorted = copyArray(reverseSorted, arraySize);
			random 		  = copyArray(random, arraySize);
			useMedOfThreeB(alreadySorted, reverseSorted, random, arraySize, numberTrials, i);
			
			reverseSorted = copyArray(reverseSorted, arraySize);
			random 		  = copyArray(random, arraySize);
			useMedOfThreeC(alreadySorted, reverseSorted, random, arraySize, numberTrials, i);
			
			reverseSorted = copyArray(reverseSorted, arraySize);
			random 		  = copyArray(random, arraySize);
			useRandomPivot(alreadySorted, reverseSorted, random, arraySize, numberTrials, i);
		}	
	}
	
	public static void useSimpleQuick(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i) {
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		long alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadySortedSimpleQuick(alreadySorted, arraySize);
		reverseTotal = reverseSimpleQuick(reverseSorted, arraySize);
		randTotal 	 = randomSimpleQuick(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = alreadyTotal % numberTrials;
			reverseAverage = reverseTotal % numberTrials;
			randAverage    = randTotal % numberTrials;
			
			// TODO store or output data? 
		}
	}
	
	public static long alreadySortedSimpleQuick(Integer[] alreadySorted, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		SimpleQuickSort.quickSort(alreadySorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		return total;
	}
	
	public static long reverseSimpleQuick(Integer[] reverseSorted, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		SimpleQuickSort.quickSort(reverseSorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		
		return total;
	}
	
	public static long randomSimpleQuick(Integer[] random, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		SimpleQuickSort.quickSort(random, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		
		return total;
	}
	
	public static Integer[] copyArray(Integer[] array, int arraySize) {
		Integer[] newArray = new Integer[arraySize];
		
		for (int i = 0; i < arraySize; i++) 
			newArray[i] = array[i];
		
		return newArray;
	}
	
	public static void useMedOfThreeA(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(5);
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		long alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadyMedThree(alreadySorted, arraySize);
		reverseTotal = reverseMedThree(reverseSorted, arraySize);
		randTotal 	 = randomMedThree(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = alreadyTotal % numberTrials;
			reverseAverage = reverseTotal % numberTrials;
			randAverage    = randTotal % numberTrials;
			
			// TODO store or output data? 
		}
	}
	
	public static void useMedOfThreeB(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(10);
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		long alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadyMedThree(alreadySorted, arraySize);
		reverseTotal = reverseMedThree(reverseSorted, arraySize);
		randTotal 	 = randomMedThree(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = alreadyTotal % numberTrials;
			reverseAverage = reverseTotal % numberTrials;
			randAverage    = randTotal % numberTrials;
			
			// TODO store or output data? 
		}
	}
	
	public static void useMedOfThreeC(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(20);
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		long alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadyMedThree(alreadySorted, arraySize);
		reverseTotal = reverseMedThree(reverseSorted, arraySize);
		randTotal    = randomMedThree(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = alreadyTotal % numberTrials;
			reverseAverage = reverseTotal % numberTrials;
			randAverage    = randTotal % numberTrials;
			
			// TODO store or output data? 
		}
	}
	
	public static long alreadyMedThree(Integer[] alreadySorted, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		MedOfThreeQuickSort.quickSort(alreadySorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		
		return total;
	}
	
	public static long reverseMedThree(Integer[] reverseSorted, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		MedOfThreeQuickSort.quickSort(reverseSorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		
		return total;
	}
	
	public static long randomMedThree(Integer[] random, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		MedOfThreeQuickSort.quickSort(random, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		
		return total;
	}
	
	public static void useRandomPivot(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i) {
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		long alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadyRandomPivot(alreadySorted, arraySize);
		reverseTotal = reverseRandomPivot(reverseSorted, arraySize);
		randTotal    = randomRandomPivot(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = alreadyTotal % numberTrials;
			reverseAverage = reverseTotal % numberTrials;
			randAverage    = randTotal % numberTrials;
			
			// TODO store or output data? 
		}
	}
	
	public static long alreadyRandomPivot(Integer[] alreadySorted, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		RandomPivotQuickSort.quickSort(alreadySorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		
		return total;
	}
	
	public static long reverseRandomPivot(Integer[] reverseSorted, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		RandomPivotQuickSort.quickSort(reverseSorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		
		return total;
	}
	
	public static long randomRandomPivot(Integer[] random, int arraySize) {
		long start, finish, delta, total = 0;
		
		start = System.nanoTime();
		RandomPivotQuickSort.quickSort(random, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		total += delta;
		
		return total;
	}
	
	public static Integer[] fillAlreadySorted(int arraySize) {
		Integer[] alreadySorted = new Integer[arraySize];
		int j = 0;
		
		for (int i = 0; i < arraySize; i++)
			alreadySorted[i] = j++;
		
		return alreadySorted;
	}
	
	public static Integer[] fillReverseSorted(int arraySize) {
		Integer[] reverseSorted = new Integer[arraySize];
		int j = arraySize;
		
		for (int i = 0; i < arraySize; i++) {
			reverseSorted[i] = j;
			j--;
		}
		
		return reverseSorted;
	}
	
	public static Integer[] fillRandom(int arraySize) {
		Integer[] random = new Integer[arraySize];
		Random r = new Random();
		int data;
		
		for (int i = 0; i < arraySize; i++) {
			data = r.nextInt();
			random[i] = data;
		}
		
		return random;
	}
	
	public static int inputArraySize() {
		int arraySize;
		
		System.out.println("Enter array size: ");
		arraySize = inScan.nextInt();
		return arraySize;
	}
	
	public static int inputTrialNumber() {
		int numberTrials;
		
		System.out.println("Enter number of trials: ");
		numberTrials = inScan.nextInt();
		inScan.nextLine();
		return numberTrials;
	}
	
	public static String inputFileName() {
		String fileName;
		
		System.out.println("Enter file name: ");
		fileName = inScan.nextLine();
		return fileName;
	}
	
	public static void outputData(int arraySize) {
		if (arraySize <= 20) 
			outputCommandLine();
		
		outputToFile();
	}
	
	public static void outputCommandLine() {
		/*
			System.out.print("Already Sorted: ");
			for (int i = 0; i < arraySize; i++) 
				System.out.print(alreadySorted[i] + " ");
			System.out.println("");
			
			System.out.print("Reverse array: ");
			for(int i = 0; i < arraySize; i++) 
				System.out.print(reverseSorted[i] + " ");
			System.out.println("");
			
			System.out.print("Random Array: ");
			for (int i = 0; i < arraySize; i++) 
				System.out.print(random[i] + " ");
			System.out.println("");	
		*/
	}
	
	public static void outputToFile() {
		
	}
}
