import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/* 
 *  Natalie Boehm, CS 0445, Assignment 4: QuickSort Comparison 
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
		
		fillArray(arraySize, numberTrials, fileName);
	}
	
	public static void fillArray(int arraySize, int numberTrials, String fileName) {
		Integer[] alreadySorted = fillAlreadySorted(arraySize);
		Integer[] reverseSorted = fillReverseSorted(arraySize);
		Integer[] random = null;
		sortArrays(alreadySorted, reverseSorted, random, arraySize, numberTrials, fileName);
	}
	
	public static void sortArrays(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, String fileName) {
		for (int i = 0; i < numberTrials; i++) {
			reverseSorted = fillReverseSorted(arraySize);
			random 		  = fillRandom(arraySize);
			//System.out.println("Unsorted reverse S: " + SimpleQuickSort.toString(reverseSorted));
			//System.out.println("Unsorted random S: " + SimpleQuickSort.toString(random));
			useSimpleQuick(alreadySorted, reverseSorted, random, arraySize, numberTrials, i, fileName);
			
			reverseSorted = fillReverseSorted(arraySize);
			random 		  = copyArray(random, arraySize);
			//System.out.println("Unsorted reverse A: " + MedOfThreeQuickSort.toString(reverseSorted));
			//System.out.println("Unsorted random A: " + MedOfThreeQuickSort.toString(random));
			useMedOfThreeA(alreadySorted, reverseSorted, random, arraySize, numberTrials, i, fileName);
			
			reverseSorted = fillReverseSorted(arraySize);
			random 		  = copyArray(random, arraySize);
			//System.out.println("Unsorted reverse B: " + MedOfThreeQuickSort.toString(reverseSorted));
			//System.out.println("Unsorted reverse B: " + MedOfThreeQuickSort.toString(random));
			useMedOfThreeB(alreadySorted, reverseSorted, random, arraySize, numberTrials, i, fileName);
			
			reverseSorted = fillReverseSorted(arraySize);
			random 		  = copyArray(random, arraySize);
			useMedOfThreeC(alreadySorted, reverseSorted, random, arraySize, numberTrials, i, fileName);
			
			reverseSorted = fillReverseSorted(arraySize);
			random 		  = copyArray(random, arraySize);
			useRandomPivot(alreadySorted, reverseSorted, random, arraySize, numberTrials, i, fileName);
		}	
	}
	
	public static void useSimpleQuick(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, String fileName) {
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadySortedSimpleQuick(alreadySorted, arraySize);
		reverseTotal = reverseSimpleQuick(reverseSorted, arraySize);
		randTotal 	 = randomSimpleQuick(random, arraySize);	
	
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s = new SortOutput(alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 1);
			SortOutput r = new SortOutput(reverseSorted, reverseAverage, arraySize, numberTrials, 2, 1);
			SortOutput rand = new SortOutput(random, randAverage, arraySize, numberTrials, 3, 1);
			
			outputData(arraySize, s, r, rand, fileName);
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
	
	public static void useMedOfThreeA(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, String fileName) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(5);
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadyMedThree(alreadySorted, arraySize);
		reverseTotal = reverseMedThree(reverseSorted, arraySize);
		randTotal 	 = randomMedThree(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s = new SortOutput(alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 2);
			SortOutput r = new SortOutput(reverseSorted, reverseAverage, arraySize, numberTrials, 2, 2);
			SortOutput rand = new SortOutput(random, randAverage, arraySize, numberTrials, 3, 2);
			
			outputData(arraySize, s, r, rand, fileName);
		}
	}
	
	public static void useMedOfThreeB(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, String fileName) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(10);
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadyMedThree(alreadySorted, arraySize);
		reverseTotal = reverseMedThree(reverseSorted, arraySize);
		randTotal 	 = randomMedThree(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s = new SortOutput(alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 3);
			SortOutput r = new SortOutput(reverseSorted, reverseAverage, arraySize, numberTrials, 2, 3);
			SortOutput rand = new SortOutput(random, randAverage, arraySize, numberTrials, 3, 3);
			
			outputData(arraySize, s, r, rand, fileName);
		}
	}
	
	public static void useMedOfThreeC(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, String fileName) {
		MedOfThreeQuickSort m = new MedOfThreeQuickSort(20);
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadyMedThree(alreadySorted, arraySize);
		reverseTotal = reverseMedThree(reverseSorted, arraySize);
		randTotal    = randomMedThree(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s = new SortOutput(alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 4);
			SortOutput r = new SortOutput(reverseSorted, reverseAverage, arraySize, numberTrials, 2, 4);
			SortOutput rand = new SortOutput(random, randAverage, arraySize, numberTrials, 3, 4);
			
			outputData(arraySize, s, r, rand, fileName);
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
	
	public static void useRandomPivot(Integer[] alreadySorted, Integer[] reverseSorted, Integer[] random, int arraySize, int numberTrials, int i, String fileName) {
		long alreadyTotal = 0, reverseTotal = 0, randTotal = 0;
		float alreadyAverage, reverseAverage, randAverage;
		
		alreadyTotal = alreadyRandomPivot(alreadySorted, arraySize);
		reverseTotal = reverseRandomPivot(reverseSorted, arraySize);
		randTotal    = randomRandomPivot(random, arraySize);	
		
		if (i == (numberTrials-1)) {
			alreadyAverage = ((float)alreadyTotal/numberTrials)/billion;
			reverseAverage = ((float)reverseTotal/numberTrials)/billion;
			randAverage    = ((float)randTotal/numberTrials)/billion;
			
			SortOutput s = new SortOutput(alreadySorted, alreadyAverage, arraySize, numberTrials, 1, 5);
			SortOutput r = new SortOutput(reverseSorted, reverseAverage, arraySize, numberTrials, 2, 5);
			SortOutput rand = new SortOutput(random, randAverage, arraySize, numberTrials, 3, 5);
			
			outputData(arraySize, s, r, rand, fileName);
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
	
	public static void outputData(int arraySize, SortOutput sorted, SortOutput reverse, SortOutput random, String fileName) {
		
		System.out.println(sorted.fileToString());
		System.out.println(reverse.fileToString());
		System.out.println(random.fileToString());
		
		
		if (arraySize <= 20) 
			outputCommandLine(sorted, reverse, random);
		
		outputToFile(sorted, reverse, random, fileName);
	}
	
	public static void outputCommandLine(SortOutput sorted, SortOutput reverse, SortOutput random) {
		System.out.println(sorted.traceToString());
		System.out.println(reverse.traceToString());
		System.out.println(random.traceToString());
	}
	
	public static void outputToFile(SortOutput sorted, SortOutput reverse, SortOutput random, String fileName) {
		BufferedWriter w;
		try {
			w = new BufferedWriter(new FileWriter(new File(fileName)));
			w.write(sorted.fileToString());
			w.write(reverse.fileToString());
			w.write(random.fileToString());
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
