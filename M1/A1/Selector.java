import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Kaffeein Bellamy
* @author   Dean Hendrix (dh@auburn.edu)
* @version  TODAY
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      /**
         Plan A:
         
         initialize an int to the first value of the array.
         
      
      */
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("IllegalArgumentException");
      }
      
      int min = a[0];
       //a.length -1 is off by one
      for (int i = 0; i < a.length; i++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
   
      if ((a == null) || (a.length == 0)) {
         throw new IllegalArgumentException("IllegalArgumentException");
      }   
   
      int max = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {

     
      /**
         I think I want to sort them first, 
         store them in another array, then 
         pick the index of that array
      
      */

      
        return -99;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      return -99;
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
       if ((a == null) || (a.length == 0)) {
            throw new IllegalArgumentException("IllegalArgumentException");

       }
       int[] r = new int[a.length];
        int[] e = new int[0];
        int counter = 0;
        int i = 0;

        for (int element : a) {

            if ((low <= a[i]) && (high >= a[i])) {
                r[counter] = a[i];
                counter++;
            }
            i++;
        }

        if (counter == 0) {
            return e;

        }
        r = Arrays.copyOf(r, counter);


       return r;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      return -99;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      return -99;
   }

}
