package hr.fer.ikrpelnik.utils;

/**
 * Class that serves as a pointer to some integer array.
 * It's useful for situation that requires swaping two arrays. 
 * No copying is done in that case, only swaping two variables of this class is needed.
 * @author Ivan Krpelnik
 *
 */
public class PointerToInt {
	
	private int[] array;
	
	/**
	 * Constructor.
	 * @param array - array to point at.
	 * @throws IllegalArgumentException if array is null.
	 */
	public PointerToInt(int[] array) {
		if(array == null) {
			throw new IllegalArgumentException("Array must not be null!");
		}
		this.array = array;
	}
	
	/**
	 * Getter for array.
	 * @return array.
	 */
	public int[] getArray() {
		return array;
	}
	
	/**
	 * Gets element at some index.
	 * @param index
	 * @return int in array at given index.
	 * @throws IndexOutOfBoundsException if index is invalid.
	 */
	public int get(int index) {
		return array[index];
	}
	
	/**
	 * Sets value at given index in array.
	 * @param index - given index
	 * @param val - given value
	 * @throws IndexOutOfBoundsException if index is invalid.
	 */
	public void set(int index, int val) {
		array[index] = val;
	}
	
	/**
	 * Returns length of array.
	 * @return array length.
	 */
	public int length() {
		return array.length;
	}
	
}