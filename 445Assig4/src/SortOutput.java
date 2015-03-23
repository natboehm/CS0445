import java.text.DecimalFormat;

/*
 *  Natalie Boehm, Assignment 4: QuickSort Comparison, class to store data to output to file or command line.
 */

public class SortOutput {
	
	String 	algorithm;
	int 	arraySize;
	String 	order;
	int 	numberTrials;
	String 	averageTime;
	
	DecimalFormat df = new DecimalFormat("#0.########");
	
	Integer[] array;
	Integer[] iArray;
	
	public SortOutput(Integer[] a, Integer[] i, float ave, int s, int n, int ord, int alg) {
		array = a;
		averageTime = df.format(ave);
		numberTrials = n;
		arraySize = s;
		
		if (ord == 1) {
			order = "Already Sorted";
			iArray = i;
		} else if (ord == 2) {
			order = "Reverse Sorted";
			iArray = i;
		} else if (ord == 3) {
			order = "Random";
			iArray = i;
		}
		
		if (alg == 1) 
			algorithm = "Simple QuickSort";
		else if (alg == 2) 
			algorithm = "Median of 3 QuickSort, Base Case < 5";
		else if (alg == 3)
			algorithm = "Median of 3 QuickSort, Base Case < 10";
		else if (alg == 4)
			algorithm = "Median of 3 QuickSort, Base Case < 20";
		else if (alg == 5)
			algorithm = "Random Pivot QuickSort"; 
		else if (alg == 6)
			algorithm = "MergeSort";
	}

	public String fileToString() {
		StringBuilder f = new StringBuilder();
		
		f.append("Algorithm: " + algorithm);
		f.append("\n");
		f.append("Array Size: " + arraySize);
		f.append("\n");
		f.append("Order: " + order);
		f.append("\n");
		f.append("Number of Trials: " + numberTrials);
		f.append("\n");
		f.append("Average Time per Trial: " + averageTime + " secs");
		f.append("\n");
		f.append("\n");
		
		return f.toString();
	}
	
	public String traceToString() {
		StringBuilder t = new StringBuilder();
		
		t.append("Algorithm: " + algorithm);
		t.append("\n");
		t.append("Array Size: " + arraySize);
		t.append("\n");
		t.append("Data Configuration: " + order);
		t.append("\n");
		t.append("Initial Array: " );
		for (int i = 0; i < arraySize; i++) 
			t.append(iArray[i] + " ");
		t.append("\n");
		t.append("Array After Sorting: ");
		for (int i = 0; i < arraySize; i++)
			t.append(array[i] + " ");
		t.append("\n");
		t.append("Average Time per Trial: " + averageTime + " nanosecs");
		t.append("\n");
		
		return t.toString();
	}
}
