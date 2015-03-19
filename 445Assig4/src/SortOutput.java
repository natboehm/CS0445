
/*
 *  Natalie Boehm, Assignment 4: QuickSort Comparison
 */

public class SortOutput {
	
	String 	algorithm;
	int 	arraySize;
	String 	order;
	int 	numberTrials;
	long 	averageTime;
	
	public void setAlgorithm(String a) { algorithm = a; }

	public void setArraySize(int s) { arraySize = s; }
	
	public void setOrder(String o) { order = o; }
	
	public void setNumberTrials(int t) { numberTrials = t; }
	
	public void setAverageTime(int ave) { averageTime = ave; }
	
	public String fileToString() {
		StringBuilder f = new StringBuilder();
		
		f.append("Algorithm: ");
		f.append("\n");
		f.append("Array Size: ");
		f.append("\n");
		f.append("Order: ");
		f.append("\n");
		f.append("Number of Trials: ");
		f.append("\n");
		f.append("Average Time per Trial: ");
		f.append("\n");
		
		return f.toString();
	}
	
	public String traceToString() {
		StringBuilder t = new StringBuilder();
		
		t.append("Algorithm: ");
		t.append("\n");
		t.append("Array Size: ");
		t.append("\n");
		t.append("Data Configuration: ");
		t.append("\n");
		t.append("Initial Array: ");
		t.append("\n");
		t.append("Array After Sorting: ");
		t.append("\n");
		t.append("Time Required For Sort: ");
		t.append("\n");
		
		return t.toString();
	}
}
