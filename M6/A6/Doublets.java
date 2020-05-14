import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Collections;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;


/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Kaffeein Bellamy (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.

    private HashSet<String> visited;
    private HashSet<String> lexicon;


    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {

            lexicon = new HashSet<String>();
            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();

                lexicon.add(str.toUpperCase()); //you pick the case, either upper or lower
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }


    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////
    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
    public int getHammingDistance(String str1, String str2) {
        //check for null
        if (str1 == null || str2 == null) {
            return -1;
        }
        //check equal length
        if (str1.length() != str2.length()) {
            return -1;
        }
        // hamming distance
        int count = 0;
        
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
        }
        return count;
    }
    
    /** node convention */
    private class Node {
        String word;
        Node n;
        //two param constructor
        public Node(String str, Node node) {
            this.word = str;
            this.n = node;
        }
    }

    /**
     * Returns a minimum-length word ladder from start to end. If multiple
     * minimum-length word ladders exist, no guarantee is made regarding which
     * one is returned. If no word ladder exists, this method returns an empty
     * list.
     *
     * Breadth-first search must be used in all implementing classes.
     *
     * @param  start  the starting word
     * @param  end    the ending word
     * @return        a minimum length word ladder from start to end
     */
    public List<String> getMinLadder(String start, String end) {
        visited = new HashSet<String>();
        return breadthFirstSearch(start, end);
    }

    public List<String> breadthFirstSearch(String start, String end) {
        Deque<Node> queue = new ArrayDeque<>();
        List<String> neighborhood = new ArrayList<String>();
        queue.addLast(new Node(start, null));
        
        while (!queue.isEmpty()) {
            Node hold = queue.removeFirst();
            if (hold.word.equals(end)) {
                Node hold2 = hold;
                while (hold2.n != null) {
                    neighborhood.add(hold2.word);
                    hold2 = hold2.n;
                }
                neighborhood.add(start);
                Collections.reverse(neighborhood);
                return neighborhood;
            }
            
            for (String i : getNeighbors(hold.word)) {
                if (!visited.contains(i)) {
                    visited.add(i);
                    queue.addLast(new Node(i, hold));
                }
            }
        }
        return neighborhood;
    }


    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighborhood of the given word
     */
    public List<String> getNeighbors(String word){
        List<String> nbrs = new ArrayList<String>();
        if (word == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < word.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(i, j);
                if (isWord(sb.toString()) && getHammingDistance(word, sb.toString()) == 1) {
                    nbrs.add(sb.toString());
                }
            }
        }
        return nbrs;
    }


    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
    public int getWordCount() {
        return lexicon.size();
    }


    /**
     * Checks to see if the given string is a word.
     *
     * @param  str the string to check
     * @return     true if str is a word, false otherwise
     */
    public boolean isWord(String str) {
        if (str == null) {
            return false;
        }
        if (lexicon.isEmpty()) {
            return false;
        }
        if (lexicon.contains(str.toUpperCase())) {
            return true;
        }
        return false;
    }


    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
    public boolean isWordLadder(List<String> sequence) {
        if (sequence.isEmpty()) {
            return false;
        }
        for (int i = 0; i < sequence.size() - 1; i++) {
            String current = sequence.get(i);
            String next = sequence.get(i + 1);
            if (!isWord(current) || !isWord(next) || getHammingDistance(current, next) != 1) {
                return false;
            }
        }
        return true;
    }
    
}

