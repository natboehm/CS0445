import java.util.Arrays;
import java.util.Random;

/*
 * Natalie Boehm, CS0445, Assignment 1: card game of War
 */

public class MultiDS<T> implements PrimQ<T>, Reorder {
	
	int maxArrayLength;
	int numItem;
	T positionZero;
	T[] primQ;
	
	public MultiDS() {}
	
	@SuppressWarnings("unchecked")
	public MultiDS(int i) {
		maxArrayLength = i;
		numItem = 0;
		positionZero = null;
		
		primQ = (T[]) new Object[maxArrayLength];
	}
	
	// implements PrimQ<T>
	public boolean addItem(T item) {
		boolean success = true;
		
		if (full()) {
			success = false; 
		} else {
			if (primQ[numItem] == null) {
				primQ[numItem] = item;
				numItem++;
				success = true;
			}
		}
		return success;
	}
	
	public T removeItem() {
		
		if (!empty()) {
			int i = 0; 
			positionZero = primQ[i];
			
			for (int p = 1; p <= (numItem-1); p++) {
				primQ[(p-1)] = primQ[p];
			}
			numItem--;
			primQ[numItem] = null; 
			
			return positionZero; 
		} else {
			System.out.println("I'm a fucking moron!");
			return null;
		}
	}
	
	public boolean full() {
		if (numItem == maxArrayLength) 
			return true; 
		else 
			return false; 
	}
	
	public boolean empty() {
		if (numItem == 0)
			return true;
		else 
			return false;
	}
	
	public int size() { return numItem; }

	@SuppressWarnings("unchecked")
	public void clear() {
		this.primQ = (T[]) new Object[maxArrayLength];
		numItem = 0; 
	}
	
	// implements Reorder
	@SuppressWarnings("unchecked")
	public void reverse() {
		T[] reversePrimQ = (T[]) new Object[numItem];
		
		for(int i = 0; i < numItem; i++) {
			reversePrimQ[numItem-1-i] = primQ[i];
			//System.out.println("foo reversed");
		}
		
		primQ = reversePrimQ; 
		//System.out.println("reversePrimQ copied to PrimQ");
	}
	
	public void shiftRight() { 
		
		if (numItem > 0) {
			T positionCurrentSize = primQ[(numItem-1)];
			
			for (int i = numItem-1; i > 0; i--) {
				primQ[i] = primQ[i - 1]; 
			}
		
			primQ[0] = positionCurrentSize; 
		}
	}
	
	public void shiftLeft() {
		
		if (numItem > 0) {
			T zeroItem = primQ[0];	
			
			for (int i = 1; i < numItem; i++) {
				primQ[(i-1)] = primQ[i];
			}
			
		primQ[numItem - 1] = zeroItem; 
		}
	}
	
	public void shuffle() {
		Random rand = new Random(); 
		
		int randomNumber = 0;
		T tempNumber; 
		
		for (int i = 0; i < numItem; i++) {
			randomNumber = rand.nextInt(i + 1);

			tempNumber = primQ[i];
			primQ[i] = primQ[randomNumber];
			primQ[randomNumber] = tempNumber;
		}
	}
	
	public String toString() {
	 
		StringBuffer str = new StringBuffer();
		
		for (int i = 0; i < numItem; i++) {
			T qOutput = this.primQ[i];
			str.append(" " + qOutput.toString());
		}
		
		return ("Contents:\n " + str.toString());
	}
 }
