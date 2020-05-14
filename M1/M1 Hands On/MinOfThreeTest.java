import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 1, 1);
   }
   
   @Test public void min1Case1Test() {
      int actual = MinOfThree.min1(1,3,2);
      int expected = 1;
      assertEquals(expected, actual);
   }
   
   @Test public void min1Case2Test() {
      int actual = MinOfThree.min1(2,3,1);
      int expected = 1;
      assertEquals(expected, actual);
   
   } 
   
   @Test public void min1Case3Test() {
      int actual = MinOfThree.min1(3,2,1);
      int expected = 1;
      assertEquals(expected, actual);
      
   }   
   
   @Test public void min1Case4Test() {
      int actual = MinOfThree.min1(2,1,3);
      int expected = 1;
      assertEquals(expected, actual);
      
   }   
   
   @Test public void min1Case5Test() {
      /**
         a is less than b, first condition shorts
         
         b is less than a and b is less than c
         
         b is returned
      */   
   
      int expected = 1;
      int actual = MinOfThree.min1(3,1,2);
      assertEquals(expected, actual);
         
   
   }
   
   @Test public void min1Case6Test() {
      int actual = MinOfThree.min1(1,2,3);
      int expected = 1;
      assertEquals(expected, actual);
      
   }      
   
   @Test public void min1Case7Test() {
      int actual = MinOfThree.min1(2,2,3);
      int expected = 3;
      assertEquals(expected, actual);
   }   
   
   //Min 2 Tests below 
   @Test public void min2Case1Test() {
      int actual = MinOfThree.min2(1,3,2);
      int expected = 1;
      assertEquals(expected, actual);
   }
   
   @Test public void min2Case2Test() {
      int actual = MinOfThree.min2(2,3,1);
      int expected = 1;
      assertEquals(expected, actual);
   
   } 
   
   @Test public void min2Case3Test() {
      int actual = MinOfThree.min2(3,2,1);
      int expected = 1;
      assertEquals(expected, actual);
      
   }   
   
   @Test public void min2Case4Test() {
      int actual = MinOfThree.min2(2,1,3);
      int expected = 1;
      assertEquals(expected, actual);
      
   }   
   
   @Test public void min2Case5Test() {
      /**
         a is less than b, first condition shorts
         
         b is less than a and b is less than c
         
         b is returned
      */   
   
      int expected = 1;
      int actual = MinOfThree.min2(3,1,2);
      assertEquals(expected, actual);
         
   
   }
   
   @Test public void min2Case6Test() {
      int actual = MinOfThree.min2(1,2,3);
      int expected = 1;
      assertEquals(expected, actual);
      
   }  
   
   @Test public void min2Case7Test() {
      int actual = MinOfThree.min2(2,2,3);
      int expected = 2;
      assertEquals(expected, actual);
   }   

}
