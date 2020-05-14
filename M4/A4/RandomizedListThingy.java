import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * This is the class that builds off of the RandomizedList         interface.
 
 * @author Kaffeein Bellamy
 * @version 3/9/2020
 * */
public class RandomizedListThingy<T> implements RandomizedList<T> {

    /**
     * First difficulty: The name of the class must use the diamond operator
     * on generic type if the class it implements also uses generic type
     *
     *  Second difficulty: You have to define all methods up the hierarchy
     *  here. Because this class implements RandomizedList and RandomizedList
     *  implements List, this class class has to define methods for both
     *  RandomizedList and List
     * */

    //These are the variables you work with in the hands on
    private T[] elements; // working array
    private int size; // size of array
    //because you will be catching illegal args, you can guarantee that the array
        //will at least have one element. You can duplicate the array, copy its elements
        //and reassign the duplicate to the working array to get more space
    private static final int DEF_CAPACITY = 1;

    public RandomizedListThingy () {
        //use the constructor to make an instance of the class
        this(DEF_CAPACITY);
    }

    @SuppressWarnings("unchecked") // do this to turn off compiler warnings
    public RandomizedListThingy(int capacity) {
        //When an instance of this class is created, the user ses the capacity
        //You cast it to generic type with (T[])
        elements = (T[]) new Object[capacity];
        size = 0;
    }
    
    public int size() {
        return size;
    }
    
    @Override //lets the reader know that this method was redefined
    public Iterator<T> iterator() {
        //this object is defined in a nested class below
        return new ArrayIterator(elements, this.size());
    }
    
    @Override
    public void add(T element) {
        //You can see this exact method structure in the lecture videos
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(elements.length * 2);
        }
        elements[size] = element;

        size += 1; //increment size by one after successful add
    }
    
    @Override
    public boolean isEmpty() {
        boolean flag = false;
        if (size == 0) {
            flag = true;
        }
        return flag;

        /**
         * Better way to do this ^
         *
         * Like the isFull() method, "return size == 0"
         * OR---
         *
         * change the <flag == true> line to <return true>
         * and make the last line return false
         * */
    }

    /** Method checks if array is full */
    public boolean isFull() {
        return size == elements.length;
    }

    /** This method will help me find the element I'm looking for */
    private int find(T wanted) {
        int index = -1; //index set to -1 for not found item
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(wanted)) {
                index = i;
            }
        }
        return index;
    }


    @Override
    public T remove() {
        //first check if the array is empty
        if (isEmpty()) {
            return null; // if is is empty, we return null
        }
        
        int index = find(sample());

        if (index < 0) {   // if you can't find it, the index from find was -1
            throw new NoSuchElementException();
        }

        T item = elements[index]; //this is what gets returned
        // we assign the element we want to remove to the end of the array
        elements[index] = elements[--size];
        //the deletion is done by setting it to null
        elements[size] = null;

        //here you resize the array
        if ((size > 0) && (size < elements.length / 4)) {
            resize(elements.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        //the param passed will be the size of the temporary array
        T[] temp = (T[]) new Object[capacity];
        //can't use a for each loop because you need index tracking
        for (int i = 0; i < this.size(); i++) {
            temp[i] = elements[i];
        }
        //at the end of the loop, reassign the temporary to the global var
        elements = temp;
    }



    /**
     * Selects but does not remove an element selected uniformly at random from
     * the elements currently in the list. If the list is empty this method
     * return null.
     */

    public T sample() {
        if (isEmpty()) {
            return null;
        }
        int sample = new Random().nextInt(size());
        return elements[sample];
    }



    /**You may nest classes within classes. Here, we define methods
     * for the Iterator interface. The structure of the methods for this
     * class are typical Java standards, so just look up the API and
     * get inspired from that. This is talked about in one of the lecture videos */
    public class ArrayIterator<T> implements Iterator<T> {
        private T[] holdingCell;
        private int current;
        private int counter;


        public ArrayIterator(T[] elements, int size) {
            counter = size;
            holdingCell = elements; // from this class, not the outer one
            current = 0;
        }

        public boolean hasNext() {
            return (current < counter);
        }

        public T next() {
            if (!hasNext()) { //if there is only one element, terminal will yell at you
                throw new NoSuchElementException();
            }
            //According to the Java API for the Random class
                //the nextInt method accepts an integer as a
                //param and generates an int between 0 and the
                //passed param (excluding the param, so param -1)

            int index = new Random().nextInt(counter); // generates number between 0 and counter
            T item = holdingCell[index];
            //each time the call is made, counter is a different size,
            // so the index should never (hardly ever) be the same
            if (hasNext()) {
                holdingCell[index] = holdingCell[counter - 1];
                holdingCell[counter - 1] = item;
                counter--;
            }
            return item;
        }

    }
}



