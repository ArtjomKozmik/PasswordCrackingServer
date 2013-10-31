/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artjom
 */
public class Controller {

    static ArrayList<String> words = new ArrayList<>();
    static ArrayList<List> arrayOfWords = new ArrayList<>();
    private static Controller instance = new Controller();
    static List<UserInfo> passwords = new ArrayList<>();
    private static MessageDigest messageDigest;
    public static final String MESSAGE_DIGEST_ALGORITHM = "SHA";
    private static final Logger LOGGER = Logger.getLogger("passwordCracker");
    final String dictionaryLocation = "C:\\Users\\Artjom\\Desktop\\passwords\\webster-dictionary.txt";

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * Reads passwords and returns them Doesn't work with web service for some
     * reason, that is why the client reads the passwords
     *
     * @return
     */
    public static List<UserInfo> readPasswords() {
        try {
            final List<UserInfo> userInfos = PasswordFileHandler.readPasswordFile("C:\\Users\\Artjom\\Desktop\\passwords\\passwords.txt");
            passwords = userInfos;
        } catch (IOException ex) {
            //empty
        }

        return passwords;
    }

    /**
     * Reads the dictionary and splits it in parts
     */
    private Controller() {

        catchException();
        splitDictionary();
    }

    /**
     * Gets instance of the constructor
     *
     * @return instance of the constructor
     */
    public synchronized static Controller getInstance() {

        return instance;
    }

    /**
     * Reads the dictionary and adds words to an ArrayList
     *
     * @param location directory and the name of the dictionary file
     * @return ArrayList with with dictionary words
     * @throws IOException
     */
    public static ArrayList<String> readFromFile(String location) throws IOException {

        FileReader fileReader = null;
        try {

            fileReader = new FileReader(location);
//            fileReader = new FileReader("D:\\tem\\test.txt");

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

    /**
     * Splits the dictionary in parts of the size defined by int endPoint and
     * adds them to an ArrayList of ArrayLists
     */
    private static void splitDictionary() {

        int startPoint = 0;
        int lastIndex = words.size();
        //change number of words
        int endPoint = 25000;
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

    /**
     * Catches IO exception of readFromFile()
     */
    private void catchException() {
        try {
            readFromFile(dictionaryLocation);
        } catch (IOException exception) {
        }
    }

    /**
     * Makes sure that the dictionary is split in a way that all the words are
     * in the ArrayList
     *
     * @param lastIndex
     * @param endPoint
     * @return
     */
    private static int roundUpSplit(int lastIndex, int endPoint) {
        int numberOfSplits;
        if (lastIndex % endPoint > endPoint / 2) {
            numberOfSplits = lastIndex / endPoint;
        } else {
            numberOfSplits = lastIndex / endPoint + 1;
        }
        return numberOfSplits;
    }

    /**
     * Takes the result of slaves work and writes it on a file at a specific
     * directory
     *
     * @param results cracked usernames and passwords
     */
    public static void writeFile(ArrayList<String> results) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\Artjom\\Desktop\\passwords\\Cracked passwords.txt");
            fw.write(results.toString());

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
