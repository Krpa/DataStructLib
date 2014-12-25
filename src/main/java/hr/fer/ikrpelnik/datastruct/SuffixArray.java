package hr.fer.ikrpelnik.datastruct;

import java.util.Arrays;

import hr.fer.ikrpelnik.utils.PointerToInt;


/**
 * Class that represents Suffix Array.
 * Construction is done in O(n*log^2(n)) time and O(n) memory where n
 * is the length of given text. Sorting of suffixes is done by a variant of
 * bucket sort.
 * @author Ivan Krpelnik
 *
 */
public class SuffixArray {

	private String text;
	private int textLen;
	private PointerToInt bucket, nBucket, tmp;
	private Suffix[] suffix;
	private int H;
	
	/**
	 * Constructor.
	 * @param text - given text for suffix array.
	 * @throws IllegalArgumentException if text is null.
	 */
	public SuffixArray(String text) {
		if(text == null) {
			throw new IllegalArgumentException("Text must not be null!");
		}
		this.text = text;
		init();
	}
	
	/**
	 * Method that initializes needed private members and calls method to sort suffixes.
	 */
	private void init() {
		textLen = text.length()+1;
		bucket = new PointerToInt(new int[textLen]);
		nBucket = new PointerToInt(new int[textLen]);
		suffix = new Suffix[textLen];
		
		for(int i = 0; i < textLen; ++i) {
			suffix[i] = new Suffix(i);
		}
		
		suffixSort();
	}
	
	/**
	 * Method sorts suffixes.
	 */
	private void suffixSort() {
		H = 0;
		Arrays.sort(suffix);
		boolean c = updateBuckets();
		
		for(H = 1; c; H *= 2) {
			Arrays.sort(suffix);
			c = updateBuckets();
		}
	}
	
	/**
	 * Updates buckets and returns true if sorting should continue.
	 * @return true if sorting should continue, false otherwise.
	 */
	private boolean updateBuckets() {
		boolean c = false;
		int start = 0, id = 0;
		for(int i = 0; i < textLen; ++i) {
			if(i != 0 && !suffix[i].equal(suffix[i-1])) {
				id++;
				start = i;
			}
			c |= start != i;
			nBucket.set(suffix[i].getIndex(), id);
		}
		tmp = nBucket;
		nBucket = bucket;
		bucket = tmp;
		return c;
	}
	
	/**
	 * Getter for suffixes.
	 * @param index - index of wanted suffix.
	 * @return String Suffix at given index in this suffix array.
	 */
	public String getSuffix(int index) {
		return suffix[index].toString();
	}
	
	/**
	 * Class that represents suffix in {@link SuffixArray}.
	 * @author Ivan Krpelnik
	 *
	 */
	private class Suffix implements Comparable<Suffix> {
		
		private int index;
		
		public Suffix(int index) {
			this.index = index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
		
		/**
		 * Method used to compare this suffix to some other suffix.
		 * @param other Suffix
		 * @return false if other is null or if other comes first in lexicographical order.
		 */
		private boolean lessThan(Suffix other) {
			if(other == null) {
				return false;
			}
			if(H == 0) {
				return charAt(index) < charAt(other.index);
			} else if(bucket.get(index) == bucket.get(other.index)) {
				return bucket.get(index+H) < bucket.get(other.index+H);
			} else {
				return bucket.get(index) < bucket.get(other.index);
			}
		}
		
		private char charAt(int index) {
			if(index + 1 >= textLen) {
				return '\0';
			}
			return text.charAt(index);
		}
		
		/**
		 * Method compares this suffix to some other.
		 * @param other - other suffix
		 * @return true if they are equal, false otherwise.
		 */
		private boolean equal(Suffix other) {
			return !this.lessThan(other) && !other.lessThan(this);
		}
		
		@Override
		public int compareTo(Suffix other) {
			if(this.lessThan(other)) {
				return -1;
			} else if(this.equal(other)) {
				return 0;
			} else {
				return 1;
			}
		}
		
		@Override
		public String toString() {
			return text.substring(index);
		}
		
	}
}
