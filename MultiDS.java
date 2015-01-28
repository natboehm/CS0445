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
        /* first, you should consider seeding the random number generator, until you're ready to submit your finished product
         * This will help you reproduce your results.
         * Ramirez should have given you the seed that he used in his sample program...... but that would be reasonable.
        */
		Random rand = new Random();
        // final SEED = 7;
        // Random rand = new Random(SEED);

        /* Shuffling algorithm:
         * First, make a new array for the results. This array is of size original.length
         * Now, pick a random position out of the original array  --> position = rand.nextInt(original.length)
         * Drop that value into the first position in the results array. ---> results[0] = original[position]
         * Now, make a third array, of size (original.length - 1).
         * Copy the values up until and excluding 'position' into the third array.
         * Then copy all of the values after position into the third array, like this:
         *      for i = 0; i < position; i++ { third[i] = original[i]; }
         *      for (i = position + 1; i < original.length; i++) { third[i] = original[i] ; } // do you see how we copy everything except skip the value we selected?
         * Set original equal to third.  --> original = third ;
         * 
         * What you've just done is randomly selected a value from the original array to be the first value of the resulting array.
         * Then you've removed that value from the original array.
         * Repeat this process until the original array is empty. At that point, your resulting array will be full!
         *
         * You can repeat the pseduocode above by wrapping it in a for loop. If you see on line 3 of the pseudocode, I say to set results[0] to the new value. That's correct, for the first time around, you want to fill the first position of the results array. The second time, you want to fill the second position, and the third time, you want to fill the third position. So it is here that you replace 0 with your loop counter from the enclosing for loop.
         * Oh, one other thing. Replace ".length" with ".numItems()" when approperate. Remember, you can't have null values randomly interspersed throughout the array.
        */ 

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
