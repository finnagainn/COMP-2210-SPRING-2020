import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Kaffeein Bellamy
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    TODAY
 *
 */


public class MarkovModel {
    // Map of <kgram, chars following> pairs that stores the Markov model.
    private HashMap<String, String> model;

    // add other fields as you need them ...
    private String head;

    /**
     * Reads the contents of the file sourceText into a string, then calls
     * buildModel to construct the order K model.
     *
     * DO NOT CHANGE THIS CONSTRUCTOR.
     *
     */
    public MarkovModel(int K, File sourceText) {
        model = new HashMap<>();
        try {
            String text = new Scanner(sourceText).useDelimiter("\\Z").next();
            buildModel(K, text);
        }
        catch (IOException e) {
            System.out.println("Error loading source text: " + e);
        }
    }


    /**
     * Calls buildModel to construct the order K model of the string sourceText.
     *
     * DO NOT CHANGE THIS CONSTRUCTOR.
     *
     */
    public MarkovModel(int K, String sourceText) {
        model = new HashMap<>();
        buildModel(K, sourceText);
    }

    /** Returns the head kgram found in the source text. */
    public String getFirstKgram() {
        return head;
    }


    /**
     * Builds an order K Markov model of the string sourceText.
     */
    private void buildModel(int K, String sourceText) {

        sourceText = sourceText.replaceAll("\n", " ");
        sourceText = sourceText.replaceAll("\r", "");


        int i = 0;

        head = sourceText.substring(i, i + K);

        while (i + K < sourceText.length() + 1) {

            String current = sourceText.substring(i, i + K);

            String str;

            try {
                str = sourceText.substring(i + K, i + K + 1);
            }

            catch (StringIndexOutOfBoundsException e) {
                str = null;
            }

            if (model.putIfAbsent(current, str) != null) {

                String newStr = model.get(current);
                newStr += str;
                model.put(current, newStr);
            }

            i++;
        }
    }




    /** Returns a kgram chosen at random from the source text. */
    public String getRandomKgram() {

        Random r = new Random();

        Object[] keys = getAllKgrams().toArray();

        int i = r.nextInt(model.size());

        String kg = keys[i].toString();
        return kg;
    }


    /**
     * Returns the set of kgrams in the source text.
     *
     * DO NOT CHANGE THIS METHOD.
     *
     */
    public Set<String> getAllKgrams() {
        return model.keySet();
    }


    /**
     * Returns a single character that follows the given kgram in the source
     * text. This method selects the character according to the probability
     * distribution of all characters that follow the given kgram in the source
     * text.
     */
    public char getNextChar(String kgram) {

        String hold = model.get(kgram);
        Random r = new Random();
        int i = r.nextInt(hold.length());

        char finalChar = hold.charAt(i);


        return finalChar;
    }


    /**
     * Returns a string representation of the model.
     * This is not part of the provided shell for the assignment.
     *
     * DO NOT CHANGE THIS METHOD.
     *
     */
    @Override
    public String toString() {
        return model.toString();
    }
}
