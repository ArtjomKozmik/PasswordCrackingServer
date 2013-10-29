/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Synchronization;

/**
 *
 * @author Artjom
 */
public class Controller {

    static ArrayList<String> words = new ArrayList<>();
    static ArrayList<List> arrayOfWords = new ArrayList<>();
    private static Controller instance = null;

    private Controller() {
//        catchException();
//        splitDictionary();
    }

    public synchronized static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) {

                instance = new Controller();
            }
        }
        return instance;
    }

    public static ArrayList<String> readFromFile() throws IOException {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("test.txt");

            final BufferedReader dictionary = new BufferedReader(fileReader);
            while (true) {
                final String dictionaryEntry = dictionary.readLine();

                if (dictionaryEntry == null) {
                    break;
                }
                words.add(dictionaryEntry);
            }
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
        return words;
    }

    private static void splitDictionary() {
        int startPoint = 0;
        int lastIndex = words.size();
        //change number of words
        int endPoint = 2;
        int jump = endPoint;
        int numberOfSplits = 0;
        numberOfSplits = roundUpSplit(lastIndex, endPoint);

        for (int i = 0; i < numberOfSplits; i++) {
            arrayOfWords.add(words.subList(startPoint, endPoint));


            startPoint = startPoint + jump;
            endPoint = endPoint + jump;
            if (endPoint > lastIndex) {
                endPoint = lastIndex;

            }

        }
    }

    private void catchException() {
        try {
            readFromFile();
        } catch (IOException exception) {
        }
    }

    private static int roundUpSplit(int lastIndex, int endPoint) {
        int numberOfSplits;
        if (lastIndex % endPoint > endPoint / 2) {
            numberOfSplits = lastIndex / endPoint;
        } else {
            numberOfSplits = lastIndex / endPoint + 1;
        }
        return numberOfSplits;
    }
}
