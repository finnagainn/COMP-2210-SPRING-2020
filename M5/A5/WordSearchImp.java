//It's best to name your imports, but there were so many
//that I just starred them.
import java.util.*;
import java.io.*;

/**
 * @author Kaffeein Bellamy
 * @version 3/24/2020
 * 
 * This is the class that sets up the word search.
 * */
public class WordSearchImp implements WordSearchGame {

    int[] cellX;
    int[] cellY;
    int length;
    //global variables, can't use these for index tracking
    int i; 
    int j;

    boolean lexiconBeacon;
    StringBuilder nextWord;
    List<Integer> wayToGo;
    private static final int MAX_NEIGHBOURS = 8;
    TreeSet<String> treeOfWords;
    String[][] board;
    int[][] order;
    boolean[][] wasVisited;


    // if you're looking for my constructor, scroll down
    //like...alllllll the way down

    public void loadLexicon(String fileName) {

        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        /* Multiple ways to open a file in java.
         Below is borrowed from COMP 1210. */
        try {
            Scanner scan = new Scanner(new File(fileName));
            while (scan.hasNext()) {
                String word = scan.next();
                scan.nextLine().toUpperCase();
                treeOfWords.add(word.toUpperCase());
            }

            scan.close();
        }

        catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }

        lexiconBeacon = true;
    }


    public void setBoard(String[] letterArray) {
        //illegal args check
        if (letterArray == null) {
            throw new IllegalArgumentException();
        }

        boolean squared = true;

        Double root = (letterArray.length % (Math.sqrt(letterArray.length)));

        if (root != 0) {
            squared = false;
        }

        if (squared == false) {
            throw new IllegalArgumentException();
        }

        Integer s = (int) Math.round(Math.sqrt(letterArray.length));
        String[][] table = new String[s][s];

        order = new int[s][s];
        wasVisited = new boolean[s][s];
        length = s;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                table[j][i] = letterArray[(length * j) + i].toUpperCase();
                order[j][i] = (length * j + i);
            }
        }

        board = table;
    }


    public String getBoard() {

        String output = new String();
        for (int k = 0; k < length; k++) {
            for (int m = 0; m < length; m++) {
                output += ("(" + board[m][k] + ")");

            }
        }
        return output;
    }


    public SortedSet<String> getAllValidWords(int minimumWordLength) {
        //might delete this check
        if (treeOfWords == null) {
            throw new IllegalArgumentException();
        }

        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }

        if (!lexiconBeacon) {
            throw new IllegalStateException();
        }

        clear();

        SortedSet<String> validWords = new TreeSet<String>();

        for (String w : treeOfWords) {
            List<Integer> discovered = isOnBoard(w);

            if ((discovered != null) && (discovered.size() != 0)) {
                if (w.length() >= minimumWordLength) {
                    validWords.add(w);
                }
            }

        }
        return validWords;
    }


    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {

        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }

        if (!lexiconBeacon) {
            throw new IllegalStateException();
        }
        int score = 0;
        for (String word : words) {
            if (word.length() > minimumWordLength) {
                score += (word.length() - minimumWordLength + 1);
            }
        }
        return score;
    }

    public boolean isValidPrefix(String prefixToCheck) {

        if (prefixToCheck == null) {
            throw new IllegalArgumentException();
        }

        if (!lexiconBeacon) {
            throw new IllegalStateException();
        }

        String ceiling = treeOfWords.ceiling(prefixToCheck.toUpperCase());

        //boolean isValidPre = ((ceiling != null) || (ceiling.toUpperCase().compareTo(prefixToCheck) < 0));
        
 
        return ceiling != null ? ceiling.startsWith(prefixToCheck) : false;

        //return isValidPre;
    }


    public boolean isValidWord(String wordToCheck) {

        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }

        if (!lexiconBeacon) {
            throw new IllegalStateException();
        }

        boolean isValidWrd = treeOfWords.contains(wordToCheck);
        return isValidWrd;
    }


    public List<Integer> isOnBoard(String wordToCheck) {

        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }

        if (!lexiconBeacon) {
            throw new IllegalStateException();
        }

        clear();

        wayToGo = new ArrayList<Integer>();

        for (int k = 0; k < length; k++) {
            for (int m = 0; m < length; m++) {
                clear();
                j = m;
                i = k;

                if (sameLetterAtFront(board[i][j], wordToCheck)) {
                    StringBuilder nextWord = new StringBuilder();
                    wayToGo = new ArrayList<Integer>();

                    if (singleWordDFS(i, j, wordToCheck, nextWord, wayToGo)) {
                        return wayToGo;
                    }

                }
            }
        }
        
        return wayToGo;
    }

    //from the lecture videos
    private void visit() {
        wasVisited[i][j] = true;

    }

    //undoes visit()
    private void undoVisit(int c, int d) {
        wasVisited[c][d] = false;
    }

    private boolean hasBeenSeen() {
        return wasVisited[i][j];
    }

    //clean up
    private void clear() {
        for (boolean[] row : wasVisited) {
            Arrays.fill(row, false);
        }
    }

    private void read() {
        nextWord.append(board[i][j]);
        wayToGo.add(order[i][j]);
    }

    //opposite of appending word to board
    private void undoRead() {
        nextWord.deleteCharAt(nextWord.toString().length() - 1);
        wayToGo.remove(wayToGo.size() - 1);
    }


    private boolean isValid() {
        boolean flag = ((i >= 0) && (i < length) && (j >= 0) && (j < length));
        
        return flag;
    }


    private int neighbors() {
        String[] nbrs = new String[MAX_NEIGHBOURS];
        cellX = new int[MAX_NEIGHBOURS];
        cellY = new int[MAX_NEIGHBOURS];
        int count = 0;

        for (int w = -1; w <= 1; w++) {
            for (int v = -1; v <= 1; v++) {
                if (!((w == 0) && (v == 0))) {
                    if (isInBounds(i + v, j + w)) {
                        String neighbor = board[i + v][j + w];
                        nbrs[count] = neighbor;
                        cellY[count] = i + v;
                        cellX[count] = j + w;
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public boolean sameLetterAtFront(String str1, String str2) {

        //String firstLetter = String.valueOf(str1.charAt(0));
        //String secLetter = String.valueOf(str2.charAt(0));

        Character firstLetter =  str1.charAt(0);
        Character secLetter = str2.charAt(0);
        boolean sameFront = firstLetter.equals(secLetter);
        return sameFront;

    }

    public boolean isInBounds(int a, int b) {
        boolean boundCheck = (a < length) && (a >= 0) && (b < length) && (b >= 0);
        return boundCheck;
    }


    public boolean singleWordDFS(int c, int d, String searchWord, StringBuilder wordSoFar, List<Integer> route) {
        i = c; //again, global variables at the top
        j = d;

        wayToGo = route;
        nextWord = wordSoFar;
        String position = board[i][j];

        //illegal args check #1
        if (!isValid()) {
            return false;
        }

        //illegal args check #2
        if (hasBeenSeen()) {
            return false;
        }

        //if lines get read, ^ input passed checks
        StringBuilder duplicate = new StringBuilder();
        duplicate.append(nextWord.toString());

        if (!searchWord.contains(duplicate.append(position))) {
            return false;
        }

        visit();
        read();

        if (nextWord.toString().equals(searchWord)) {
            return true;
        }

        int grouping = neighbors();
        int[] x = Arrays.copyOf(cellX, grouping);
        int[] y = Arrays.copyOf(cellY, grouping);

        for (int r = 0; r < grouping; r++) {
            int nbrX = x[r];
            int nbrY = y[r];

            if (singleWordDFS(nbrY, nbrX, searchWord, nextWord, wayToGo)) {
                return true;
            }

        }

        undoVisit(c, d);
        undoRead();
        
        return false;

    }

    //Constructor builds a default board to play with
    public WordSearchImp() {

        order = new int[4][4];
        wasVisited = new boolean[4][4];
        length = 4;
        treeOfWords = new TreeSet<String>();
        board = new String[][]{
                {"R", "G", "D", "y"},
                {"X", "A", "E", "P"},
                {"O", "H", "S", "E"},
                {"I", "F", "Q", "P"}};

        for (int a = 0; a < length; a++) {
            for (int b = 0; b < length; b++) {
                order[a][b] = ((length * b) + a);
            }
        }
    }


}