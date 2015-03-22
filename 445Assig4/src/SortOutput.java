import java.text.DecimalFormat;

/*
 *  Natalie Boehm, Assignment 4: QuickSort Comparison
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
	
	public SortOutput(Integer[] a, float ave, int s, int n, int ord, int alg) {
		array = a;
		averageTime = df.format(ave);
		numberTrials = n;
		arraySize = s;
		
		if (ord == 1) {
			order = "Already Sorted";
			iArray = Assig4.fillAlreadySorted(arraySize);
		} else if (ord == 2) {
			order = "Reverse Sorted";
			iArray = Assig4.fillReverseSorted(arraySize);
		} else if (ord == 3) {
			order = "Random";
			iArray = Assig4.fillRandom(arraySize);
			// TODO use same random array as what sorted 
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
	}
	
	/*
	public String getAlgorithm() { return algorithm; }
	
	public void setAlgorithm(String a) { algorithm = a; }

	public int getArraySize() { return arraySize; }
	
	public void setArraySize(int s) { arraySize = s; }
	
	public String getOrder() { return order; }
	
	public void setOrder(String o) { order = o; }
	
	public int getNumberTrials() { return numberTrials; }
	
	public void setNumberTrials(int t) { numberTrials = t; }
	
	public long getAverageTime() { return averageTime; }
	
	public void setAverageTime(int ave) { averageTime = ave; }
	
	public Integer[] getArray() { return array; }
	
	public void setRandom(Integer[] y) { array = y; }
	*/
	
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
