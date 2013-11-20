package com.srikanth.datastructures;

public class BigONotation {

	/**
	 * Useful Links:-
	 * http://rob-bell.net/2009/06/a-beginners-guide-to-big-o-notation/
	 * http://stackoverflow.com/questions/107165/big-o-for-eight-year-olds
	 * http://www.briandupreez.net/2011/03/simple-big-o-notation-post.html
	 * http://stackoverflow.com/questions/487258/plain-english-explanation-of-big-o
	 * http://stackoverflow.com/questions/360748/computational-complexity-of-fibonacci-sequence
	 * http://www.mathsisfun.com/numbers/fibonacci-sequence.html
	 */
	private BigONotation() {
		
	}
	/**
	 * It is for O(1) 
	 * O(1) describes an algorithm that will always execute in the same time (or space) regardless of the size of the input data set.
	 * @param strings
	 * @return
	 */
	static boolean isFirstElementNull(String... strings) {
		if(strings[0] == null) {
			return true;
		}
		return false;
	}
	/**
	 * It is for O(N)
	 * O(N) describes an algorithm whose performance will grow linearly and in direct proportion to the size of the input data set. 
	 * The example below also demonstrates how Big O favours the worst-case performance scenario; a matching string could be
	 * found during any iteration of the for loop and the function would return early, but Big O notation will always assume the 
	 * upper limit where the algorithm will perform the maximum number of iterations.
	 * @param strings
	 * @param value
	 * @return
	 */
	static boolean ContainsValue(String[] strings, String value) {
		for(int i = 0; i < strings.length; i++) {
			if(strings[i] == value) {
				return true;
			}
		}
		return false;
	}
	/**
	 * It is for O(N2).
	 * O(N2) represents an algorithm whose performance is directly proportional to the square of the size of the input data set. 
	 * This is common with algorithms that involve nested iterations over the data set. Deeper nested iterations will result in 
	 * O(N3), O(N4) etc.
	 * @param strings
	 * @return
	 */
	static boolean ContainsDuplicates(String[] strings) {
		
		for(int i = 0; i < strings.length; i++) {
			for(int j = 0; j < strings.length; j++) {
				// Don't compare with self
				if(i == j) 	{
					continue;
				}

				if(strings[i] == strings[j]) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * It is for O(2N) or O(2^N).
	 * O(2N) denotes an algorithm whose growth will double with each additional element in the input data set. 
	 * The execution time of an O(2N) function will quickly become very large.
	 * Recursive computation of Fibonacci numbers is a good example of O(2N) algorithm (though O(2N) is not a tight bound for it).
	 * @param n
	 * @return
	 */
	static int getFibonacciNumber(int n) {
		if(n <= 1) {
			return n;
		}
		else 
			return getFibonacciNumber(n-2) + getFibonacciNumber(n-1); 
	}
}
