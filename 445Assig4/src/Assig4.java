import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/* 
 *  Natalie Boehm, CS 0445, Assignment 4: QuickSort Comparison, Main program for sorting 
 *  arrays with random, already sorted, and reverse sorted data utilizing a simple QuickSort, 
 *  Median of Three QuickSort, and Random Pivot Quick Sort.
 */

public class Assig4 {
	static Scanner inScan = new Scanner(System.in);
	static long billion = 1000000000;
	
	public static void main(String [] args) {
		inputTestInfo();
	}
	
	public static void inputTestInfo() {
		int arraySize 	 = inputArraySize();
		int numberTrials = inputTrialNumber();
		String fileName  = inputFileName();
		BufferedWriter w = null;
		
		try {
			w = new BufferedWriter(new FileWriter(new File(fileName), true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fillArray(arraySize, numberTrials, w);
	}
	
	public static void fillArray(int arraySize, int numberTrials, BufferedWriter w) {
		Integer[] alreadySorted = fillAlreadySorted(arraySize);
		Integer[] reverseSorted = fillReverseSorted(arraySize);
		Integer[] random 		= null;
		
		sortArrays(alreadySorted, reverseSorted, random, arraySize, numberTrials, w);
	}
	
	public static void sortArrays(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, BufferedWriter w) {
		random = fillRandom(arraySize);
		Integer[] randomSample = copyArray(random, arraySize);
		
		for (int i = 0; i < numberTrials; i++) {	
			reverseSorted = fillReverseSorted(arraySize);
			randomSample  = copyArray(random, arraySize);
			useSimpleQuick(alreadySorted, reverseSorted, randomSample, arraySize, numberTrials, i, w);
		
			reverseSorted = fillReverseSorted(arraySize);
			randomSample  = copyArray(random, arraySize);
			useMedOfThreeA(alreadySorted, reverseSorted, randomSample, arraySize, numberTrials, i, w);
			
			reverseSorted = fillReverseSorted(arraySize);
			randomSample  = copyArray(random, arraySize);
			useMedOfThreeB(alreadySorted, reverseSorted, randomSample, arraySize, numberTrials, i, w);
			
			reverseSorted = fillReverseSorted(arraySize);
			randomSample  = copyArray(random, arraySize);
			useMedOfThreeC(alreadySorted, reverseSorted, randomSample, arraySize, numberTrials, i, w);
			
			reverseSorted = fillReverseSorted(arraySize);
			randomSample  = copyArray(random, arraySize);
			useRandomPivot(alreadySorted, reverseSorted, randomSample, arraySize, numberTrials, i, w);
			
			random = fillRandom(arraySize);
		}	
	}
	
	public static void useSimpleQuick(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, BufferedWriter w) {
		long alreadyTime, reverseTime, randTime;
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		Integer[] initialReverse = copyArray(reverseSorted, arraySize);
		Integer[] initialRandom  = copyArray(random, arraySize);
		
		alreadyTime  = alreadySortedSimpleQuick(alreadySorted, arraySize);
		alreadyTotal += alreadyTime;
		reverseTime  = reverseSimpleQuick(reverseSorted, arraySize);
		reverseTotal += reverseTime;
		randTime 	 = randomSimpleQuick(random, arraySize);
		randTotal 	 += randTime;
		
		if (arraySize <= 20) {
			SortOutput sTrace 	 = new SortOutput(alreadySorted, alreadySorted, alreadyTime, arraySize, numberTrials, 1, 1);
			SortOutput rTrace 	 = new SortOutput(reverseSorted, initialReverse, reverseTime, arraySize, numberTrials, 2, 1);
			SortOutput randTrace = new SortOutput(random, initialRandom, randTime, arraySize, numberTrials, 3, 1);
			
			outputCommandLine(sTrace, rTrace, randTrace);
		}
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s 	= new SortOutput(alreadySorted, alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 1);
			SortOutput r 	= new SortOutput(reverseSorted, initialReverse, reverseAverage, arraySize, numberTrials, 2, 1);
			SortOutput rand = new SortOutput(random, initialRandom, randAverage, arraySize, numberTrials, 3, 1);
			
			outputToFile(s, r, rand, w);
		}
	}
	
	public static long alreadySortedSimpleQuick(Integer[] alreadySorted, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		SimpleQuickSort.quickSort(alreadySorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		return delta;
	}
	
	public static long reverseSimpleQuick(Integer[] reverseSorted, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		SimpleQuickSort.quickSort(reverseSorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start; 
		return delta;
	}
	
	public static long randomSimpleQuick(Integer[] random, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		SimpleQuickSort.quickSort(random, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		return delta;
	}
	
	public static Integer[] copyArray(Integer[] array, int arraySize) {
		Integer[] newArray = new Integer[arraySize];
		
		for (int i = 0; i < arraySize; i++) 
			newArray[i] = array[i];
		
		return newArray;
	}
	
	public static void useMedOfThreeA(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, BufferedWriter w) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(5);
		long alreadyTime, reverseTime, randTime;
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		Integer[] initialReverse = copyArray(reverseSorted, arraySize);
		Integer[] initialRandom  = copyArray(random, arraySize);
		
		alreadyTime  = alreadyMedThree(alreadySorted, arraySize);
		alreadyTotal += alreadyTime;
		reverseTime  = reverseMedThree(reverseSorted, arraySize);
		reverseTotal += reverseTime;
		randTime 	 = randomMedThree(random, arraySize);	
		randTotal 	 += randTime;
		
		if (arraySize <= 20) {
			SortOutput sTrace 	 = new SortOutput(alreadySorted, alreadySorted, alreadyTime, arraySize, numberTrials, 1, 2);
			SortOutput rTrace 	 = new SortOutput(reverseSorted, initialReverse, reverseTime, arraySize, numberTrials, 2, 2);
			SortOutput randTrace = new SortOutput(random, initialRandom, randTime, arraySize, numberTrials, 3, 2);
			
			outputCommandLine(sTrace, rTrace, randTrace);
		}
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s 	= new SortOutput(alreadySorted, alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 2);
			SortOutput r 	= new SortOutput(reverseSorted, initialReverse, reverseAverage, arraySize, numberTrials, 2, 2);
			SortOutput rand = new SortOutput(random, initialRandom, randAverage, arraySize, numberTrials, 3, 2);
			
			outputToFile(s, r, rand, w);
		}
	}
	
	public static void useMedOfThreeB(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, BufferedWriter w) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(10);
		long alreadyTime, reverseTime, randTime;
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		Integer[] initialReverse = copyArray(reverseSorted, arraySize);
		Integer[] initialRandom  = copyArray(random, arraySize);
		
		alreadyTime  = alreadyMedThree(alreadySorted, arraySize);
		alreadyTotal += alreadyTime;
		reverseTime  = reverseMedThree(reverseSorted, arraySize);
		reverseTotal += reverseTime;
		randTime 	 = randomMedThree(random, arraySize);	
		randTotal 	 += randTime;
		
		if (arraySize <= 20) {
			SortOutput sTrace 	 = new SortOutput(alreadySorted, alreadySorted, alreadyTime, arraySize, numberTrials, 1, 3);
			SortOutput rTrace 	 = new SortOutput(reverseSorted, initialReverse, reverseTime, arraySize, numberTrials, 2, 3);
			SortOutput randTrace = new SortOutput(random, initialRandom, randTime, arraySize, numberTrials, 3, 3);
			
			outputCommandLine(sTrace, rTrace, randTrace);
		}
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s 	= new SortOutput(alreadySorted, alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 3);
			SortOutput r 	= new SortOutput(reverseSorted, initialReverse, reverseAverage, arraySize, numberTrials, 2, 3);
			SortOutput rand = new SortOutput(random, initialRandom, randAverage, arraySize, numberTrials, 3, 3);
			
			outputToFile(s, r, rand, w);
		}
	}
	
	public static void useMedOfThreeC(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, BufferedWriter w) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(20);
		long alreadyTime, reverseTime, randTime;
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		Integer[] initialReverse = copyArray(reverseSorted, arraySize);
		Integer[] initialRandom  = copyArray(random, arraySize);
		
		alreadyTime  = alreadyMedThree(alreadySorted, arraySize);
		alreadyTotal += alreadyTime;
		reverseTime  = reverseMedThree(reverseSorted, arraySize);
		reverseTotal += reverseTime;
		randTime 	 = randomMedThree(random, arraySize);	
		randTotal 	 += randTime;
		
		if (arraySize <= 20) {
			SortOutput sTrace 	 = new SortOutput(alreadySorted, alreadySorted, alreadyTime, arraySize, numberTrials, 1, 4);
			SortOutput rTrace 	 = new SortOutput(reverseSorted, initialReverse, reverseTime, arraySize, numberTrials, 2, 4);
			SortOutput randTrace = new SortOutput(random, initialRandom, randTime, arraySize, numberTrials, 3, 4);
			
			outputCommandLine(sTrace, rTrace, randTrace);
		}
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s	= new SortOutput(alreadySorted, alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 4);
			SortOutput r 	= new SortOutput(reverseSorted, initialReverse, reverseAverage, arraySize, numberTrials, 2, 4);
			SortOutput rand = new SortOutput(random, initialRandom, randAverage, arraySize, numberTrials, 3, 4);
			
			outputToFile(s, r, rand, w);
		}
	}
	
	public static long alreadyMedThree(Integer[] alreadySorted, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		MedOfThreeQuickSort.quickSort(alreadySorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		return delta;
	}
	
	public static long reverseMedThree(Integer[] reverseSorted, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		MedOfThreeQuickSort.quickSort(reverseSorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		return delta;
	}
	
	public static long randomMedThree(Integer[] random, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		MedOfThreeQuickSort.quickSort(random, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		return delta;
	}
	
	public static void useRandomPivot(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, BufferedWriter w) {
		long alreadyTime, reverseTime, randTime;
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		Integer[] initialReverse = copyArray(reverseSorted, arraySize);
		Integer[] initialRandom  = copyArray(random, arraySize);
		
		alreadyTime  = alreadyRandomPivot(alreadySorted, arraySize);
		alreadyTotal += alreadyTime;
		reverseTime  = reverseRandomPivot(reverseSorted, arraySize);
		reverseTotal += reverseTime;
		randTime 	 = randomRandomPivot(random, arraySize);	
		randTotal 	 += randTime;
		
		if (arraySize <= 20) {
			SortOutput sTrace 	 = new SortOutput(alreadySorted, alreadySorted, alreadyTime, arraySize, numberTrials, 1, 5);
			SortOutput rTrace 	 = new SortOutput(reverseSorted, initialReverse, reverseTime, arraySize, numberTrials, 2, 5);
			SortOutput randTrace = new SortOutput(random, initialRandom, randTime, arraySize, numberTrials, 3, 5);
			
			outputCommandLine(sTrace, rTrace, randTrace);
		}
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s 	= new SortOutput(alreadySorted, alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 5);
			SortOutput r 	= new SortOutput(reverseSorted, initialReverse, reverseAverage, arraySize, numberTrials, 2, 5);
			SortOutput rand = new SortOutput(random, initialRandom, randAverage, arraySize, numberTrials, 3, 5);
			
			outputToFile(s, r, rand, w);
			try {
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static long alreadyRandomPivot(Integer[] alreadySorted, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		RandomPivotQuickSort.quickSort(alreadySorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		return delta;
	}
	
	public static long reverseRandomPivot(Integer[] reverseSorted, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		RandomPivotQuickSort.quickSort(reverseSorted, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		return delta;
	}
	
	public static long randomRandomPivot(Integer[] random, int arraySize) {
		long start, finish, delta = 0;
		
		start = System.nanoTime();
		RandomPivotQuickSort.quickSort(random, arraySize);
		finish = System.nanoTime();
		
		delta = finish - start;
		return delta;
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
		if (!inScan.hasNextInt()) {
			System.out.println("Invalid input");
			System.exit(0);
		}
		arraySize = inScan.nextInt();

		return arraySize;
	}
	
	public static int inputTrialNumber() {
		int numberTrials;
		
		System.out.println("Enter number of trials: ");
		if (!inScan.hasNextInt()) {
			System.out.println("Invalid input");
			System.exit(0);
		}
		numberTrials = inScan.nextInt();
		inScan.nextLine();
			
		return numberTrials;
	}
	
	public static String inputFileName() {
		String fileName;
		
		System.out.println("Enter file name: ");
		fileName = inScan.nextLine();
		if (fileName.equals("")) {
			System.out.println("Invalid input");
			System.exit(0);
		}
		
		return fileName;
	}
	
	public static void outputCommandLine(SortOutput sorted, SortOutput reverse, SortOutput random) {
		System.out.println(sorted.traceToString());
		System.out.println(reverse.traceToString());
		System.out.println(random.traceToString());
	}
	
	public static void outputToFile(SortOutput sorted, SortOutput reverse, SortOutput random, BufferedWriter w) {
		try {
			w.append(sorted.fileToString());
			w.append(reverse.fileToString());
			w.append(random.fileToString());	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
