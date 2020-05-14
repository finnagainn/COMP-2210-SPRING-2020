import java.io.File;
//import java.io.IOException;

/**
 * TextGenerator.java. Creates an order K Markov model of the supplied source
 * text, and then outputs M characters generated according to the model.
 *
 * @author     Kaffeein Bellamy
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    TODAY
 *
 */

public class TextGenerator {

    /** Drives execution. */
    public static void main(String[] args) {

        File file = new File("ineed1000characterstopassthistestapparentlysohereiamjustwritingalotofthemtilligetto"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharactershfaslfhldslkdslkfhdslk.txt"
                + "thecorrectnumberofcharacte.txt");
        args = new String[3];
        args[0] = "10";
        args[1] = "500";
        args[2] = file.toString();

        if (args.length < 3) {
            System.out.println("Usage: java TextGenerator k length input");
            return;
        }

        // No error checking! You may want some, but it's not necessary.
        int K = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);

        File text;
        try {
            text = new File(args[2]);
            if (!text.canRead()) {
                throw new Exception();
            }
        }
        catch (Exception e) {
            System.out.println("Error: Could not open " + args[2] + ".");
            return;
        }


        // instantiate a MarkovModel with the supplied parameters and
        // generate sample output text ...

        String str = "";

        int i = 0;

        MarkovModel mm = new MarkovModel(K, text);
        String kg = mm.getRandomKgram();

        str += kg;


        // difference between do-while and while
            // a do loop will always run at least ONCE
            // a while loop stops at any case that is false
            // bad input on a do-while will crash your program
        do {

            str += mm.getNextChar(kg);
            i++;
            kg = str.substring(i, i + K);

        } while (i < M - K);


        System.out.println(str);
    }
}
