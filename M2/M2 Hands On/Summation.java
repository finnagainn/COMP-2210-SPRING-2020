/**
 * Provides recursive and iterative implementations of summation function.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-03-23
 */
public class Summation {

	/** Returns the sum of 1..n for n > 0. */
	public static int sumI(int n) {
		/**
		 * Explanation of this method: Method accepts an int called n
		 * A int called sum is initialized to 1.
		 * a for loop initializes an int called i to 2 and runs
		 * AS LONG AS i IS LESS THAN OR EQUAL TO n (the accepted parameter)
		 * i is increased by 1 at the end of each run
		 *
		 * On each run of the for loop, sum is increased by the value of i,
		 * which is 2. At the end of the for loop's run, the final value of
		 * sum is returned (should be sum + 2n);
		 */
		int sum = 1;
		for (int i = 2; i <= n; i++) {
			sum = sum + i;
		}
		return sum;
	}

	/** Returns the sum of 1..n  */
	public static int sumR(int n) {
		/** This block creates an overflow error lol
		 *
		int sum = 1;
		return sum + sumR(n + 1);
		 */

		/** Also nope!
		int sum = 1;
		return sumR(sum + 2 * n);
		 */

		/** Nope
		int sum = 1;
		for (int i = 2; i <= n; i++) {
			sum = sumR(sum + i);
		}
		return sum;
		 */
		int sum = 1;
		if (n <= sum) {
			return n;
		}
		return n + sumR(n -1);

	}

	/** Drives execution. */
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			int s1 = sumI(i);
			int s2 = sumR(i);
			System.out.println(i + ": " + s1 + ", " + s2);
		}

		int sum = sumI(5);
		sum = sumR(5);
		System.out.println(sum);
	}
}
