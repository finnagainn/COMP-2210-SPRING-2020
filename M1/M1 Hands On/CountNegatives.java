/**
 * Applies the linear scan strategy to counting the number of negative
 * values in an array.
 *
 * @author Kaffeein Bellamy
 * @version 2020-01-14
 */
public class CountNegatives {

   /**
    * Returns the number of negative values in the given array.
    */
   public static int countNegatives(int[]a) {
      int negativeCount = 0;
      for (int element : a) {
         if (element < 0) {
            negativeCount++;
         }
      }
      return negativeCount;       
   }     

}

