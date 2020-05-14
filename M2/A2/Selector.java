import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Kaffeein Bellamy
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      //checking for size and comparator initialization
      if(coll == null || comp == null) {
         //no need for string inside exception throw
         throw new IllegalArgumentException();
      }
      //make the collection an iterable object by
      //assigning it to an Iterator object that is of
      //generic type (This is the same as the arraylist
      //initialization syntax. <T> could have been any letter, but
      //<T> is used in the header of the driver class, so it
      // is used here
      if (coll.isEmpty()) {
          throw new NoSuchElementException();
      }
      //Iterator<T> itr = coll.iterator();  ---> instead of Iterator you can use arraylist
      ArrayList<T> arr = new ArrayList<T>(coll);

      // we haven't sorted yet but we assign the first value
      // of the collection iterable to a variable
      // that will eventually be the minimum value.
      T minimum = arr.get(0);
      

      for (int i = 1; i < arr.size(); i++) {
         if (comp.compare(arr.get(i), minimum) < 0) {
            minimum = arr.get(i);
         }
      }
      return minimum;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
       
      if (coll.isEmpty()) {
          throw new NoSuchElementException();
      }      
       
      ArrayList<T> arr = new ArrayList<T>(coll);
      
      T maximum = arr.get(0);

      for (int i = 1; i < arr.size(); i++) {
         if (comp.compare(arr.get(i), maximum) > 0) {
            maximum = arr.get(i);
         }

      }
      return maximum;
   }
 


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() || (k < 1) || (k > coll.size())) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> arr = new ArrayList<T>(coll);
      //arr is sorted based on the comp class ordering
      java.util.Collections.sort(arr, comp);

      //if arr is only one long,  just return 0th index
      if ((arr.size() == 1) && (k == 1)) {
         return arr.get(0);
      }
      for (int i = 1; i < arr.size(); i++) {
         if (arr.get(i) == arr.get(i -1)) {
            arr.remove(i);
            i--;
         
         }

      }

      //after cleaning the duplicates out of the arraylist
      // you should be left with no more than k values inside
      //however, it is possible for the list to be trimmed
      //to be shorter than the value of k. The exception below
      //handles that.
      if (k > arr.size()) {
         throw new NoSuchElementException();
      }
         
      T kmin = arr.get(k -1);
         
      return kmin;
      
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() || (k < 1) || (k > coll.size())) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> arr = new ArrayList<T>(coll);
      
      arr.sort(comp);
      if ((arr.size() == 1) && (k == 1)) {
         return arr.get(0);
      }
      
      for (int i = 1; i < arr.size(); i++) {
         if (arr.get(i) == arr.get(i -1)) {
            arr.remove(i);
            i--;
         }  
      }
      
      if (k > arr.size()) {
         throw new NoSuchElementException();
      }
      
      T kmax = arr.get(arr.size() - k);
      
      return kmax;
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if ((coll.isEmpty()) || (comp.compare(high, low) < 0)) {
         throw new NoSuchElementException();
      }



      ArrayList<T> arr_inner = new ArrayList<T>(coll);
      ArrayList<T> arr_outer = new ArrayList<T>();

      for (int i = 0; i < arr_inner.size(); i++) {
         if(comp.compare(arr_inner.get(i), high) <= 0
                 && comp.compare(arr_inner.get(i), low) >= 0) {
            arr_outer.add(arr_inner.get(i));
         }
      }

      Collection<T> collect = arr_outer;

      if (collect.contains(null) || collect.isEmpty()) {
         throw new NoSuchElementException();
      }

      return collect;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      //null container and compartator check
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }

      //check for empty container
      if (coll.isEmpty()) {
         throw new NoSuchElementException(); 
      }

      
      T flr = max(coll, comp);
      
      boolean flag = false;
      
      for (T item : coll) {
         if (comp.compare(item, key) >= 0 && comp.compare(item, flr) <= 0) {
            flag = true;
            flr = item;

         }
      }

      if (flag) {
         return flr;
      }
      throw new NoSuchElementException();
   
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      //checking for null container and null comparator class
      if (coll == null || comp == null) {
         //throw exception
         throw new IllegalArgumentException();
      }

      //have to check for this after the null because zero != null
      if (coll.isEmpty()) {
         throw new NoSuchElementException(); 
      }

      // you may call other methods to streamline your process
      // the floor method will first take in the min method call
      //       to first store the min value/object in the collection

      // a for loop
      T flr = min(coll, comp);
      boolean flag = false;
      for (T item : coll) {
         if (comp.compare(item, key) <= 0 && comp.compare(item, flr) >= 0) {
            flag = true;
            flr = item;

         }
      }
      if (flag) {
         return flr;
      }
      //if the
      throw new NoSuchElementException();
   }

}
